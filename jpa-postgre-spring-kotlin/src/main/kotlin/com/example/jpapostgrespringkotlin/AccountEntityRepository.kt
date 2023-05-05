package com.example.jpapostgrespringkotlin

import java.util.UUID
import org.springframework.data.jpa.repository.JpaRepository

interface AccountEntityRepository: JpaRepository<Account, UUID>
