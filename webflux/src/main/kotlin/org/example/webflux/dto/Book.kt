package org.example.webflux.dto

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.relational.core.mapping.Column
import java.time.LocalDateTime

class Book(
    @Id
    val bookId: Long = 0,
    val titleKorean: String? = null,
    val titleEnglish: String? = null,
    val description: String? = null,
    val author: String? = null,
    val isbn: String? = null,
    val publishDate: String? = null,

    @CreatedDate
    val createdAt: LocalDateTime? = null,

    @LastModifiedDate
    @Column("last_modified_at")
    val modifiedAt: LocalDateTime? = null,
)

fun BookDto.Post.toBook(): Book {
    return Book(
        titleKorean = this.titleKorean,
        titleEnglish = this.titleEnglish,
        description = this.description,
        author = this.author,
        isbn = this.isbn,
        publishDate = this.publishDate,
    )
}

fun BookDto.Patch.toBook(): Book {
    return Book(
        bookId = this.bookId,
        titleKorean = this.titleKorean,
        titleEnglish = this.titleEnglish,
        description = this.description,
        author = this.author,
        isbn = this.isbn,
        publishDate = this.publishDate,
    )
}

fun Book.toBookResponse(): BookDto.Response {
    return BookDto.Response(
        bookId = bookId,
        titleKorean = titleKorean ?: "",
        titleEnglish = titleEnglish ?: "",
        description = description ?: "",
        author = author ?: "",
        isbn = isbn ?: "",
        publishDate = publishDate ?: ""
    )
}

fun Book.copyWith(
    bookId: Long = this.bookId,
    titleKorean: String? = this.titleKorean,
    titleEnglish: String? = this.titleEnglish,
    description: String? = this.description,
    author: String? = this.author,
    isbn: String? = this.isbn,
    publishDate: String? = this.publishDate,
    createdAt: LocalDateTime? = this.createdAt,
    modifiedAt: LocalDateTime? = this.modifiedAt
) = Book(
    bookId = bookId,
    titleKorean = titleKorean,
    titleEnglish = titleEnglish,
    description = description,
    author = author,
    isbn = isbn,
    publishDate = publishDate,
    createdAt = createdAt,
    modifiedAt = modifiedAt
)