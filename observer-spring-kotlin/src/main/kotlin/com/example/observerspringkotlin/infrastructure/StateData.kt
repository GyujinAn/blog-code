package com.example.observerspringkotlin.infrastructure

import com.example.observerspringkotlin.domain.watch.Location
import java.util.UUID

class StateData(
    val userId: UUID,
    var bloodPressure: Int = 100,
    var oxygenSaturation: Int = 95,
    val watchId: UUID,
    var soc: Int = 0,
    var soh: Int = 0,
    var location: Location = Location.SAFE_AREA,
)