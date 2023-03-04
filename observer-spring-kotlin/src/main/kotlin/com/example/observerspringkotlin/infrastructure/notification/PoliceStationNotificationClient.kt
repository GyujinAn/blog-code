package com.example.observerspringkotlin.infrastructure.notification

interface PoliceStationNotificationClient {
    fun alert(msg: String)
}