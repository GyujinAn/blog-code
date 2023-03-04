package com.example.observerspringkotlin.service.phone

import com.example.observerspringkotlin.domain.phone.Phone
import org.springframework.boot.autoconfigure.security.SecurityProperties.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface PhoneRepository: JpaRepository<Phone, UUID> {
    fun findByUserId(userId: UUID): Phone?
}