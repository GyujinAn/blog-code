package com.example.observerspringkotlin.infrastructure.notification

interface HospitalNotificationClient {
    fun alert(msg: String)
}