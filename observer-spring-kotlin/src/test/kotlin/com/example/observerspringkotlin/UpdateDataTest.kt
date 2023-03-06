package com.example.observerspringkotlin

import com.example.observerspringkotlin.domain.phone.Phone
import com.example.observerspringkotlin.domain.user.Users
import com.example.observerspringkotlin.domain.watch.Location
import com.example.observerspringkotlin.domain.watch.Watch
import com.example.observerspringkotlin.infrastructure.StateData
import com.example.observerspringkotlin.service.UpdateStateDataServiceNewVersion
import com.example.observerspringkotlin.service.phone.PhoneRepository
import com.example.observerspringkotlin.service.user.UserRepository
import com.example.observerspringkotlin.service.watch.WatchRepository
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class UpdateDataTest {

    @Autowired private lateinit var updateStateDataServiceNewVersion: UpdateStateDataServiceNewVersion
    @Autowired private lateinit var userRepository: UserRepository
    @Autowired private lateinit var phoneRepository: PhoneRepository
    @Autowired private lateinit var watchRepository: WatchRepository

    @Test
    fun updateData(){
        val stateData = createGivenData()

        stateData.forEach {
            updateStateDataServiceNewVersion.updateData(it)
        }

    }

    private fun createGivenData() : List<StateData>{

        val user = Users()
        userRepository.save(user)

        val phone = Phone(
            userId = user.id
        )
        phoneRepository.save(phone)

        val watch = Watch(
            phoneId = phone.id
        )
        watchRepository.save(watch)

        val stateData = (1..100).map {
            val stateData = StateData(
                userId = user.id,
                watchId = watch.id,
                soh = 11,
                soc = it,
            )

            if(it == 30) {
                stateData.oxygenSaturation = 89
            }

            if(it in 51..59) {
                stateData.bloodPressure = 50
            }

            if(it == 99) {
                stateData.location = Location.DANGER_AREA
            }

            stateData
        }

        return listOf()
    }
}