package org.example.webflux.dto

import jakarta.validation.constraints.NotBlank

class BookDto {

    data class Post(
        @field:NotBlank(message = "titleKorean must not be blank")
        val titleKorean: String,

        @field:NotBlank(message = "titleKorean must not be blank")
        val titleEnglish: String,

        val description: String,
        val author: String,
        val isbn: String,
        val publishDate: String
    )

    data class Patch(
        var bookId: Long,
        val titleKorean: String,
        val titleEnglish: String,
        val description: String,
        val author: String,
        val isbn: String,
        val publishDate: String
    )

    data class Response(
        val bookId: Long,
        val titleKorean: String,
        val titleEnglish: String,
        val description: String,
        val author: String,
        val isbn: String,
        val publishDate: String
    )
}