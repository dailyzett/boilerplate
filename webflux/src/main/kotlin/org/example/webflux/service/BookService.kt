package org.example.webflux.service

import org.example.webflux.dto.Book
import org.example.webflux.dto.BookDto
import org.example.webflux.dto.copyWith
import org.example.webflux.dto.toBook
import org.example.webflux.repo.BookRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.switchIfEmpty

@Service
class BookService(
    private val bookRepository: BookRepository
) {
    fun createBook(book: Mono<BookDto.Post>): Mono<Book> {
        return book.flatMap { Mono.just(it.toBook()) }
    }

    fun saveBook(book: Book): Mono<Book> {
        return verifyExistsIsbn(book.isbn ?: "")
            .then(bookRepository.save(book))
    }

    private fun verifyExistsIsbn(isbn: String): Mono<Void> {
        return bookRepository.findByIsbn(isbn)
            .flatMap {
                return@flatMap Mono.empty()
            }
    }

    fun updateBook(book: Book): Mono<Book> {
        return findVerifiedBook(book.bookId)
            .map { findBook ->
                findBook.copyWith(
                    titleKorean = book.titleKorean ?: findBook.titleKorean,
                    titleEnglish = book.titleEnglish ?: findBook.titleEnglish,
                    description = book.description ?: findBook.description,
                    author = book.author ?: findBook.author,
                    isbn = book.isbn ?: findBook.isbn,
                    publishDate = book.publishDate ?: findBook.publishDate
                )
            }
            .flatMap { updatingBook -> bookRepository.save(updatingBook) }
    }

    fun findBook(bookId: Long): Mono<Book> {
        return findVerifiedBook(bookId)
    }

    private fun findVerifiedBook(bookId: Long): Mono<Book> {
        return bookRepository.findById(bookId)
            .switchIfEmpty {
                return@switchIfEmpty Mono.error(RuntimeException(""))
            }
    }

    fun findBooks(): Mono<List<Book>> {
        return bookRepository.findAll().collectList()
    }
}