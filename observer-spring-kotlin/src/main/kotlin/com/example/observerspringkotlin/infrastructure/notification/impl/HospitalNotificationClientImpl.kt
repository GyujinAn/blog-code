package com.example.observerspringkotlin.infrastructure.notification.impl

import com.example.observerspringkotlin.infrastructure.notification.HospitalNotificationClient
import org.springframework.stereotype.Component

@Component
class HospitalNotificationClientImpl: HospitalNotificationClient {
    override fun alert(msg: String) {
        print(msg)
    }
}