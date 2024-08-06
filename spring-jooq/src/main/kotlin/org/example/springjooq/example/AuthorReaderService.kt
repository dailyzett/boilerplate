package org.example.springjooq.example

import org.jooq.DSLContext
import org.jooq.generated.Tables
import org.jooq.generated.tables.records.AuthorRecord
import org.springframework.stereotype.Service

@Service
class AuthorReaderService(
    private val dsl: DSLContext
) {
    fun getAuthorDetails(): List<AuthorRecord> {
        val fetch = dsl.selectFrom(Tables.AUTHOR).fetch()
        return fetch.into(AuthorRecord::class.java)
    }
}