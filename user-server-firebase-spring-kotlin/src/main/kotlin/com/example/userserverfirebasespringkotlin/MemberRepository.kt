package com.example.userserverfirebasespringkotlin

import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository: JpaRepository<Member, Long?> {
}