package org.example.springjooq

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.transaction.annotation.EnableTransactionManagement

@SpringBootApplication
@EnableTransactionManagement
class SpringJooqApplication

fun main(args: Array<String>) {
    runApplication<SpringJooqApplication>(*args)
}
