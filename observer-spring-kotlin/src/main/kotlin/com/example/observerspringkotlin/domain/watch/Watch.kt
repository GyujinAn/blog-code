package com.example.observerspringkotlin.domain.watch

import com.example.observerspringkotlin.infrastructure.StateData
import com.example.observerspringkotlin.infrastructure.event.EventPublisher
import java.lang.Exception
import java.util.EventListener
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
}

enum class Location {
    SAFE_AREA, DANGER_AREA
}