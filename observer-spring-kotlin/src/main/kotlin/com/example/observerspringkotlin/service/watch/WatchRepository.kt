package com.example.observerspringkotlin.service.watch

import com.example.observerspringkotlin.domain.watch.Watch
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface WatchRepository: JpaRepository<Watch, UUID> {
}