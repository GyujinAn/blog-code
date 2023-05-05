package com.example.jpapostgrespringkotlin

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AccountService(
    private val accountEntityRepository: AccountEntityRepository
) {
    @Transactional
    fun save(
        name: String,
    ): Account {
        val account = Account(name = name)
        return accountEntityRepository.save(account)
    }
}
