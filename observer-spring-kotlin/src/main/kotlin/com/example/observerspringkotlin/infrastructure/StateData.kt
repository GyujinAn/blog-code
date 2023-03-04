package com.example.observerspringkotlin.infrastructure

import com.example.observerspringkotlin.domain.watch.Location
import java.util.UUID

class StateData(
    val userId: UUID,
    val bloodPressure: Int,
    val oxygenSaturation: Int,
    val watchId: UUID,
    val soc: Int,
    val soh: Int,
    val location: Location,
)