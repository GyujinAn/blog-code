package com.example.observerspringkotlin.infrastructure.event

import org.springframework.context.ApplicationEventPublisher

object EventPublisher {
    private lateinit var publisher: ApplicationEventPublisher

    fun setPublisher(publisher: ApplicationEventPublisher) {
        EventPublisher.publisher = publisher
    }

    fun publish(event: Any) {
        publisher.publishEvent(event)
    }
}