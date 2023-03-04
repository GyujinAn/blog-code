package com.example.observerspringkotlin.infrastructure.notification.impl

import com.example.observerspringkotlin.infrastructure.notification.PoliceStationNotificationClient
import org.springframework.stereotype.Component

@Component
class PoliceStationNotificationClientImpl: PoliceStationNotificationClient {
    override fun alert(msg: String) {
        print(msg)
    }
}