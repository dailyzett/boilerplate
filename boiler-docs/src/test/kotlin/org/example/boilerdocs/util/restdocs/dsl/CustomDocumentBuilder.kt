package org.example.boilerdocs.util.restdocs.dsl

import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler
import org.springframework.restdocs.payload.PayloadDocumentation.requestFields
import org.springframework.restdocs.payload.PayloadDocumentation.responseFields
import org.springframework.restdocs.payload.RequestFieldsSnippet
import org.springframework.restdocs.payload.ResponseFieldsSnippet
import org.springframework.restdocs.request.QueryParametersSnippet
import org.springframework.restdocs.request.RequestDocumentation
import org.springframework.restdocs.snippet.Snippet


data class ApiDocument(
    var docName: String = "",
    var requestParams: QueryParametersSnippet? = null,
    var requestFields: RequestFieldsSnippet? = null,
    var responseFields: ResponseFieldsSnippet? = null,
)

class ApiDocumentBuilder {
    private val apiDocument = ApiDocument()

    fun name(block: () -> String) {
        apiDocument.docName = block()
    }

    fun requestParams(block: ParamsBuilder.() -> Unit) {
        val params = ParamsBuilder().apply(block).build()
        apiDocument.requestParams =
            if (params.isNotEmpty()) RequestDocumentation.queryParameters(params)
            else null
    }

    fun requestBody(block: FieldsBuilder.() -> Unit) {
        val fields = FieldsBuilder().apply(block).build()
        apiDocument.requestFields =
            if (fields.isNotEmpty()) requestFields(fields.map { it.descriptor })
            else null
    }

    fun responseBody(block: FieldsBuilder.() -> Unit) {
        val fields = FieldsBuilder().apply(block).build()
        apiDocument.responseFields =
            if (fields.isNotEmpty()) responseFields(fields.map { it.descriptor })
            else null
    }

    fun build(): ApiDocument = apiDocument
}

fun documentBuilder(block: ApiDocumentBuilder.() -> Unit): RestDocumentationResultHandler {
    val apiDocument = ApiDocumentBuilder().apply(block).build()
    val snippets = mutableListOf<Snippet>()
    apiDocument.requestParams?.let { snippets.add(it) }
    apiDocument.requestFields?.let { snippets.add(it) }
    apiDocument.responseFields?.let { snippets.add(it) }

    return document(
        apiDocument.docName,
        *snippets.toTypedArray()
    )
}