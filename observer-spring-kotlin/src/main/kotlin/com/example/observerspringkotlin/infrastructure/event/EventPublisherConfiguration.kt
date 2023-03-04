package com.example.observerspringkotlin.infrastructure.event

import org.springframework.beans.factory.InitializingBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class EventPublisherConfiguration {
    @Autowired
    private lateinit var applicationContext: ApplicationContext

    @Bean
    fun eventPublisherInitializer(): InitializingBean {
        return InitializingBean { EventPublisher.setPublisher(applicationContext) }
    }
}