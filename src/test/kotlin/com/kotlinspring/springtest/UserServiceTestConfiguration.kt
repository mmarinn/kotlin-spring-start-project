package com.kotlinspring.springtest

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers


@TestConfiguration
@Testcontainers
class UserServiceTestConfiguration {

    @Container
    val postgreSQLContainer = PostgreSQLContainer<Nothing>("postgres").apply {
        withDatabaseName("postgres")
        withUsername("postgres")
        withPassword("postgres")
        val arrayList = ArrayList<Int>(1)
        arrayList.add(5432)
        exposedPorts = arrayList
    }


    @Bean
    fun dataSource(): HikariDataSource {
        val hirakiConfig = HikariConfig()
        postgreSQLContainer.start()
        hirakiConfig.jdbcUrl = postgreSQLContainer.jdbcUrl
        hirakiConfig.username = postgreSQLContainer.username
        hirakiConfig.password = postgreSQLContainer.password

        return HikariDataSource(hirakiConfig)
    }

}