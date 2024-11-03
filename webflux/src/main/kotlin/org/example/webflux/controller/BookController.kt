package org.example.webflux.controller

/*
@RestController
@RequestMapping("/v1/books")
class BookController(
    private val bookService: BookService
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun postBook(@RequestBody requestBody: Mono<BookDto.Post>): Mono<BookDto.Response> {
        val book: Mono<Book> = bookService.createBook(requestBody)
        val response: Mono<BookDto.Response> = book.map { it.toBookResponse() }
        return response
    }

    @GetMapping("/test")
    fun helloWorld(): Mono<String> {
        return Mono.just("Hello, world!")
    }
}*/
