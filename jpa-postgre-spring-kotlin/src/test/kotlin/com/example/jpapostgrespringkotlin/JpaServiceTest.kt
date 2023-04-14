package com.example.jpapostgrespringkotlin

import org.junit.jupiter.api.Test

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class JpaServiceTest {
    @Autowired private lateinit var jpaService: JpaService

    @Test
    fun saveJpaService() {
        val jpaEntity = jpaService.saveJpaService()
        println(jpaEntity)
    }
}
