package com.example.userserverfirebasespringkotlin

import org.springframework.data.jpa.repository.JpaRepository

interface PermissionRepository: JpaRepository<Permission, Long> {
    fun findAllByIdIn(id: List<Long>): List<Permission>
}