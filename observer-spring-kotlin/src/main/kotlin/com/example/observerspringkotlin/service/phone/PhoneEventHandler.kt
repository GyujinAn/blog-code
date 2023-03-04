package com.example.observerspringkotlin.service.phone

import com.example.observerspringkotlin.domain.user.ChangedBadStatusEvent
import com.example.observerspringkotlin.domain.user.ChangedEmergencyStatusEvent
import com.example.observerspringkotlin.domain.watch.LowedSocEvent
import com.example.observerspringkotlin.domain.watch.LowedSohEvent
import com.example.observerspringkotlin.domain.watch.MovedDangerAreaEvent
import com.example.observerspringkotlin.service.watch.WatchRepository
import org.springframework.context.event.EventListener
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class PhoneEventHandler (
    private val watchRepository: WatchRepository,
    private val phoneRepository: PhoneRepository,
){
    @EventListener(LowedSocEvent::class)
    fun handle (event: LowedSocEvent) {
        val watch = watchRepository.findByIdOrNull(event.watchId) ?: throw Exception()
        val phone = phoneRepository.findByIdOrNull(watch.phoneId) ?: throw Exception()
        phone.changeToLowPowerMode()
        phone.alertToUser("batter is low. you should charge battery.")
    }

    @EventListener(LowedSohEvent::class)
    fun handle (event: LowedSohEvent) {
        val watch = watchRepository.findByIdOrNull(event.watchId) ?: throw Exception()
        val phone = phoneRepository.findByIdOrNull(watch.phoneId) ?: throw Exception()
        phone.changeToNeedToBeRepairedStatus()
        phone.alertToUser("you should go repair shop.")
    }

    @EventListener(MovedDangerAreaEvent::class)
    fun handle (event: MovedDangerAreaEvent) {
        val watch = watchRepository.findByIdOrNull(event.watchId) ?: throw Exception()
        val phone = phoneRepository.findByIdOrNull(watch.phoneId) ?: throw Exception()
        phone.alertToUser("you should go repair shop.")
    }

    @EventListener(ChangedEmergencyStatusEvent::class)
    fun handle (event: ChangedEmergencyStatusEvent) {
        val phone = phoneRepository.findByUserId(event.userId) ?: throw Exception()
        phone.notifyEmergencyStatusEveryone()
    }

    @EventListener(ChangedBadStatusEvent::class)
    fun handle (event: ChangedBadStatusEvent) {
        val phone = phoneRepository.findByUserId(event.userId) ?: throw Exception()
        phone.alertToUser("you should go hospital.")
    }

}
