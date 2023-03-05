package com.example.observerspringkotlin.service

import com.example.observerspringkotlin.domain.History
import com.example.observerspringkotlin.domain.HistoryType
import com.example.observerspringkotlin.domain.user.HealthStatus
import com.example.observerspringkotlin.domain.watch.Location
import com.example.observerspringkotlin.domain.watch.MovedDangerAreaEvent
import com.example.observerspringkotlin.infrastructure.StateData
import com.example.observerspringkotlin.infrastructure.event.EventPublisher
import com.example.observerspringkotlin.infrastructure.notification.HospitalNotificationClient
import com.example.observerspringkotlin.infrastructure.notification.PoliceStationNotificationClient
import com.example.observerspringkotlin.service.histroy.HistoryRepository
import com.example.observerspringkotlin.service.phone.PhoneRepository
import com.example.observerspringkotlin.service.user.UserRepository
import com.example.observerspringkotlin.service.watch.WatchRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class UpdateStateDataServiceOldVersion(
    private val watchRepository: WatchRepository,
    private val userRepository: UserRepository,
    private val phoneRepository: PhoneRepository,
    private val historyRepository: HistoryRepository,
    private val hospitalNotificationClient: HospitalNotificationClient,
    private val policeStationNotificationClient: PoliceStationNotificationClient,
) {

    @Transactional
    fun updateData(stateData: StateData) {

        val user = userRepository.findByIdOrNull(stateData.userId) ?: throw Exception()

        if (user.id != stateData.userId) {
            throw java.lang.Exception()
        }

        //step 1-1
        if (user.bloodPressure - stateData.bloodPressure > 20 || user.oxygenSaturation - stateData.oxygenSaturation > 20) {
            //step 1-1-1
            val phone = phoneRepository.findByUserId(user.id) ?: throw Exception()
            phone.notifyEmergencyStatusEveryone()

            //step 1-1-2
            val history = History(
                id = UUID.randomUUID(),
                historyType = HistoryType.USER,
                detail = "${user.id} user status is changed to Emergency"
            )
            historyRepository.save(history)

            //step 1-1-3
            hospitalNotificationClient.alert("user is emergency state. find me (${user.id})")

            user.healthStatus = HealthStatus.EMERGENCY
        } else //step 1-2
            if ((stateData.bloodPressure < 80 || stateData.bloodPressure > 140) || (user.oxygenSaturation < 90) ) {
            //step 1-2-1
            val phone = phoneRepository.findByUserId(user.id) ?: throw Exception()
            phone.alertToUser("you should go hospital.")

            user.healthStatus = HealthStatus.BAD
        } else {
            user.healthStatus = HealthStatus.GOOD
        }

        //step 1
        user.bloodPressure = stateData.bloodPressure
        user.oxygenSaturation = stateData.oxygenSaturation

        val watch = watchRepository.findByIdOrNull(stateData.watchId) ?: throw Exception()

        if (watch.id != stateData.watchId) {
            throw java.lang.Exception()
        }

        //step 2-1
        if (stateData.soc < 20) {
            //step 2-1-1
            val watch = watchRepository.findByIdOrNull(stateData.watchId) ?: throw Exception()
            watch.changeToLowPowerMode()

            //step 2-1-2
            val phone = phoneRepository.findByIdOrNull(watch.phoneId) ?: throw Exception()
            phone.alertToUser("watch batter is low. you should charge battery.")
        }
        //step 2-2
        if (stateData.soh < 10) {
            //step 2-2-1
            val watch = watchRepository.findByIdOrNull(stateData.watchId) ?: throw Exception()
            watch.changeToNeedToBeRepairedStatus()

            //step 2-2-2
            val phone = phoneRepository.findByIdOrNull(watch.phoneId) ?: throw Exception()
            phone.alertToUser("you should go repair shop.")
        }
        //step 2-3
        if (stateData.location == Location.DANGER_AREA) {
            //step 2-3-1
            val watch = watchRepository.findByIdOrNull(stateData.watchId) ?: throw Exception()
            val phone = phoneRepository.findByIdOrNull(watch.phoneId) ?: throw Exception()
            phone.alertToUser("you are in danger area.")

            //step 2-3-2
            val history = History(
                id = UUID.randomUUID(),
                historyType = HistoryType.WATCH,
                detail = "watch(${stateData.watchId}) is in danger are"
            )
            historyRepository.save(history)

            //step 2-3-3
            policeStationNotificationClient.alert("user would be in danger area. find me (${stateData.watchId})")
            EventPublisher.publish(MovedDangerAreaEvent(stateData.watchId))
        }

        //step 2
        watch.soc = stateData.soc
        watch.soh = stateData.soh
        watch.location = stateData.location
    }
}