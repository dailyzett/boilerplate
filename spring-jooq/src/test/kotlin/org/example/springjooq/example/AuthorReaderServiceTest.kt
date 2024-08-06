package org.example.springjooq.example

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class AuthorReaderServiceTest {
    @Autowired
    lateinit var authorReaderService: AuthorReaderService

    @Test
    fun getAuthorDetails() {
        val authorList = authorReaderService.getAuthorDetails()
        for (author in authorList) {
            println(author.id)
            println(author.firstName)
            println(author.lastName)
        }
    }
}