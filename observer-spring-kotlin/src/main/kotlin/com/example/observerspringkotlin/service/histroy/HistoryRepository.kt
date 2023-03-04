package com.example.observerspringkotlin.service.histroy

import com.example.observerspringkotlin.domain.History
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface HistoryRepository: JpaRepository<History, UUID> {
}