package com.example.observerspringkotlin.infrastructure.notification

import com.example.observerspringkotlin.domain.user.ChangedEmergencyStatusEvent
import com.example.observerspringkotlin.domain.watch.MovedDangerAreaEvent
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component

@Component
class NotificationEventHandler (
    private val hospitalNotificationClient: HospitalNotificationClient,
    private val policeStationNotificationClient: PoliceStationNotificationClient,
) {

    @Async
    @EventListener(MovedDangerAreaEvent::class)
    fun handle (event: MovedDangerAreaEvent) {
        policeStationNotificationClient.alert("user would be in danger area. find me (${event.watchId})")
    }

    @Async
    @EventListener(ChangedEmergencyStatusEvent::class)
    fun handle (event: ChangedEmergencyStatusEvent) {
        hospitalNotificationClient.alert("user is emergency state. find me (${event.userId})")
    }
}