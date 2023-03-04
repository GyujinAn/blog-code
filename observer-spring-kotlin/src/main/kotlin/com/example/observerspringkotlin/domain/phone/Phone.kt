package com.example.observerspringkotlin.domain.phone

import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class Phone (
    @Id
    val id: UUID,

    @Column
    var userId: UUID,

    @Column
    var phoneModeStatus: PhoneModeStatus,

    @Column
    var phoneConditionStatus: PhoneConditionStatus,

) {
    fun alertToUser(s: String) {
        println(s)
    }

    fun changeToLowPowerMode() {
        this.phoneModeStatus = PhoneModeStatus.LOW_POWER_MODE
    }

    fun changeToNeedToBeRepairedStatus() {
        this.phoneConditionStatus = PhoneConditionStatus.NEED_TO_BE_REPAIRED
    }

    fun notifyEmergencyStatusEveryone() {
        println("Emergency!! Emergency!!")
    }
}

enum class PhoneModeStatus {
    NORMAL, LOW_POWER_MODE
}

enum class PhoneConditionStatus {
    NORMAL, NEED_TO_BE_REPAIRED
}