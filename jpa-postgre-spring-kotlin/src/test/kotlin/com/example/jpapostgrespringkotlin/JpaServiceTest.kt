package com.example.jpapostgrespringkotlin

import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.junit.jupiter.api.Test

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull

@SpringBootTest
internal class JpaServiceTest {
    @Autowired private lateinit var jpaService: JpaService
    @Autowired private lateinit var jpaEntityRepository: JpaEntityRepository

    @Test
    fun saveJpaService() {
        val jpaEntity = jpaService.saveJpaDefaultEntity()

        val actual = jpaEntityRepository.findByIdOrNull(jpaEntity.id)

        assertThat(jpaEntity.id).isEqualTo(actual!!.id)
    }
}
