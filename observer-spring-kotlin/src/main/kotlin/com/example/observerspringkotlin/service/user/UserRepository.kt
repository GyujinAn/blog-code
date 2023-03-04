package com.example.observerspringkotlin.service.user

import com.example.observerspringkotlin.domain.user.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface UserRepository: JpaRepository<User, UUID> {
}