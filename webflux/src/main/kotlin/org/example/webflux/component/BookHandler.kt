package org.example.webflux.component

import org.example.webflux.dto.BookDto
import org.example.webflux.dto.toBook
import org.example.webflux.dto.toBookResponse
import org.example.webflux.service.BookService
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono
import java.net.URI

@Component("bookHandlerV1")
class BookHandler(
    private val validator: BookValidator,
    private val bookService: BookService,
) {
    fun createBook(request: ServerRequest): Mono<ServerResponse> {
        return request.bodyToMono(BookDto.Post::class.java)
            .doOnNext { this.validator.validate(it) }
            .flatMap { bookService.saveBook(it.toBook()) }
            .flatMap {
                ServerResponse
                    .created(URI.create("/v1/books/${it.bookId}"))
                    .build()
            }
    }

    fun updateBook(request: ServerRequest): Mono<ServerResponse> {
        val bookId = request.pathVariable("book-id").toLongOrNull()
        return request
            .bodyToMono(BookDto.Patch::class.java)
            .doOnNext { this.validator.validate(it) }
            .flatMap { patch ->
                patch.bookId = bookId ?: 0
                return@flatMap bookService.updateBook(patch.toBook())
            }
            .flatMap { book -> ServerResponse.ok().bodyValue(book.toBookResponse()) }
    }
}