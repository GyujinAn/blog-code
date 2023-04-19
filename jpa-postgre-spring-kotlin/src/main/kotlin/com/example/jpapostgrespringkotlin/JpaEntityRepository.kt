package com.example.jpapostgrespringkotlin

import java.time.Instant
import java.util.UUID
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface JpaEntityRepository: JpaRepository<JpaEntity, UUID> {
    @Modifying
    @Query("""
        UPDATE jpa
        SET discription = :discription
        WHERE id = :id
    """, nativeQuery = true)
    fun updateDiscription(
        @Param("id") id: UUID,
        @Param("discription") discription: String,
    )
}
