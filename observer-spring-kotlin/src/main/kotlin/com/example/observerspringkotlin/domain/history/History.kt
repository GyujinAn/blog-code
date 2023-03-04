package com.example.observerspringkotlin.domain

import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class History (
    @Id
    val id: UUID,

    @Column
    val historyType: HistoryType,

    @Column
    val detail: String,
)

enum class HistoryType {
    PHONE, USER, WATCH
}