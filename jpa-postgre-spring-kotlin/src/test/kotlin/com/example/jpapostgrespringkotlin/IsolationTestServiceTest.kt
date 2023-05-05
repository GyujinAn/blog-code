package com.example.jpapostgrespringkotlin

import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class IsolationTestServiceTest {
    @Autowired
    private lateinit var isolationTestService: IsolationTestService

    private val logger = LoggerFactory.getLogger(IsolationTestServiceTest::class.java)

    @Test
    fun withDefault() {
        logger.info("=== withDefault test start ===")
        isolationTestService.withDefault()
        logger.info("=== withDefault test end ===")
    }

    @Test
    fun withReadUncommitted() {
        logger.info("=== withReadUncommitted test start ===")
        isolationTestService.withReadUncommitted()
        logger.info("=== withReadUncommitted test end ===")
    }

    @Test
    fun withReadCommitted() {
        logger.info("=== withReadCommitted test start ===")
        isolationTestService.withReadCommitted()
        logger.info("=== withReadCommitted test end ===")
    }

    @Test
    fun withRepeatableRead() {
        logger.info("=== withRepeatableRead test start ===")
        isolationTestService.withRepeatableRead()
        logger.info("=== withRepeatableRead test end ===")
    }

    @Test
    fun withSerializable() {
        logger.info("=== withSerializable test start ===")
        isolationTestService.withSerializable()
        logger.info("=== withSerializable test end ===")
    }
}
