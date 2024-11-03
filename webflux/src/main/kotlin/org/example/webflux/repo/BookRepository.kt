package org.example.webflux.repo

import org.example.webflux.dto.Book
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Mono

interface BookRepository : ReactiveCrudRepository<Book, Long> {
    fun findByIsbn(isbn: String): Mono<Book>
}