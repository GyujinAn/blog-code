package com.example.jpapostgrespringkotlin

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class JpaService(
    private val jpaEntityRepository: JpaEntityRepository
) {
    @Transactional
    fun saveJpaDefaultEntity(): JpaEntity {
        return jpaEntityRepository.save(JpaEntity())
    }
}
