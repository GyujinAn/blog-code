package com.example.observerspringkotlin.domain.user

import com.example.observerspringkotlin.infrastructure.StateData
import com.example.observerspringkotlin.infrastructure.event.EventPublisher
import java.lang.Exception
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class Users (
    @Id
    val id: UUID = UUID.randomUUID(),

    @Column
    var bloodPressure: Int = 100,

    @Column
    var oxygenSaturation: Int = 100,

    @Column
    var healthStatus: HealthStatus = HealthStatus.GOOD,

    ) {
    fun updateState(stateData: StateData) {
        if (this.id != stateData.userId) {
            throw Exception()
        }

        if (this.bloodPressure - stateData.bloodPressure > 20 || this.oxygenSaturation - stateData.oxygenSaturation > 20) {
            EventPublisher.publish(ChangedEmergencyStatusEvent(stateData.userId))
            healthStatus = HealthStatus.EMERGENCY
        } else if ((stateData.bloodPressure < 80 || stateData.bloodPressure > 140) || (oxygenSaturation < 90) ) {
            EventPublisher.publish(ChangedBadStatusEvent(stateData.userId))
            healthStatus = HealthStatus.BAD
        } else {
            healthStatus = HealthStatus.GOOD
        }

        this.bloodPressure = stateData.bloodPressure
        this.oxygenSaturation = stateData.oxygenSaturation
    }
}

enum class HealthStatus {
    GOOD, BAD, EMERGENCY
}