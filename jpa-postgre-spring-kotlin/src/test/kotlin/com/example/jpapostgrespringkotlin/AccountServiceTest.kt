package com.example.jpapostgrespringkotlin

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class AccountServiceTest {

    @Autowired
    private lateinit var accountService: AccountService

    @Test
    fun save() {
        val name = "John"
        val account = accountService.save(name = name)
        assertThat(account.name).isEqualTo(name)
    }
}
