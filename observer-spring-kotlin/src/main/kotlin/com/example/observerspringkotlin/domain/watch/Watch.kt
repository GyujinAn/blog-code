package com.example.observerspringkotlin.domain.watch

import com.example.observerspringkotlin.infrastructure.StateData
import com.example.observerspringkotlin.infrastructure.event.EventPublisher
import java.lang.Exception
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class Watch (
    @Id
    val id: UUID,

    @Column
    var soc: Int = 0,

    @Column
    var soh: Int = 100,

    @Column
    var phoneId: UUID,

    @Column
    var location: Location = Location.SAFE_AREA,

    @Column
    var modeStatus: ModeStatus,

    @Column
    var watchConditionStatus: WatchConditionStatus,
) {
    fun updateState(stateData: StateData) {
        if (this.id != stateData.watchId) {
            throw Exception()
        }

        if (stateData.soc < 20) {
            EventPublisher.publish(LowedSocEvent(stateData.watchId, stateData.soc))
        }
        if (stateData.soh < 10) {
            EventPublisher.publish(LowedSohEvent(stateData.watchId, stateData.soh))
        }
        if (stateData.location == Location.DANGER_AREA) {
            EventPublisher.publish(MovedDangerAreaEvent(stateData.watchId))
        }

        soc = stateData.soc
        soh = stateData.soh
        location = stateData.location

    }

    fun changeToLowPowerMode() {
        this.modeStatus = ModeStatus.LOW_POWER_MODE
    }

    fun changeToNeedToBeRepairedStatus() {
        this.watchConditionStatus = WatchConditionStatus.NEED_TO_BE_REPAIRED
    }
}

enum class Location {
    SAFE_AREA, DANGER_AREA
}

enum class ModeStatus {
    NORMAL, LOW_POWER_MODE
}

enum class WatchConditionStatus {
    NORMAL, NEED_TO_BE_REPAIRED
}