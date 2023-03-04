package com.example.observerspringkotlin.service.histroy

import com.example.observerspringkotlin.domain.History
import com.example.observerspringkotlin.domain.HistoryType
import com.example.observerspringkotlin.domain.user.ChangedEmergencyStatusEvent
import com.example.observerspringkotlin.domain.watch.LowedSohEvent
import com.example.observerspringkotlin.domain.watch.MovedDangerAreaEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class HistoryEventHandler(
    private val historyRepository: HistoryRepository
) {

    @EventListener(LowedSohEvent::class)
    fun handle (event: LowedSohEvent) {
        val history = History(
            id = UUID.randomUUID(),
            historyType = HistoryType.WATCH,
            detail = "watch(${event.watchId}) soh is ${event.soh}"
        )
        historyRepository.save(history)
    }

    @EventListener(MovedDangerAreaEvent::class)
    fun handle (event: MovedDangerAreaEvent) {
        val history = History(
            id = UUID.randomUUID(),
            historyType = HistoryType.WATCH,
            detail = "watch(${event.watchId}) is in danger are"
        )
        historyRepository.save(history)
    }

    @EventListener(ChangedEmergencyStatusEvent::class)
    fun handle (event: ChangedEmergencyStatusEvent) {
        val history = History(
            id = UUID.randomUUID(),
            historyType = HistoryType.USER,
            detail = "${event.userId} user status is changed to Emergency"
        )
        historyRepository.save(history)
    }

}
