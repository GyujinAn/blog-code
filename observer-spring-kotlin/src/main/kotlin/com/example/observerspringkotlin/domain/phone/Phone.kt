package com.example.observerspringkotlin.domain.phone

import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class Phone (
    @Id
    val id: UUID = UUID.randomUUID(),

    @Column
    var userId: UUID,

    ) {
    fun alertToUser(s: String) {
        println(s)
    }

    fun notifyEmergencyStatusEveryone() {
        println("Emergency!! Emergency!!")
    }
}
