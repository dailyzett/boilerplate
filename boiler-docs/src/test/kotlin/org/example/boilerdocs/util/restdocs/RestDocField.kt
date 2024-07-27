package org.example.boilerdocs.util.restdocs

import org.springframework.restdocs.payload.FieldDescriptor
import org.springframework.restdocs.payload.JsonFieldType
import org.springframework.restdocs.payload.PayloadDocumentation
import org.springframework.restdocs.payload.ResponseFieldsSnippet

fun customResponseFields(vararg fields: RestDocField): ResponseFieldsSnippet {
    return PayloadDocumentation.responseFields(fields.map { it.descriptor })
}

class RestDocField(
    val descriptor: FieldDescriptor
) {
    infix fun means(value: String): RestDocField {
        descriptor.description(value)
        return this
    }

    fun prependPath(prefix: String): RestDocField {
        return RestDocField(
            PayloadDocumentation.fieldWithPath(prefix + descriptor.path)
                .type((descriptor.type as JsonFieldType))
                .description(descriptor.description)
        )
    }
}

infix fun String.type(docs: DocsFieldTypes): RestDocField {
    return createField(this, docs.type)
}

private fun createField(value: String, type: JsonFieldType): RestDocField {
    val descriptor = PayloadDocumentation.fieldWithPath(value)
        .type(type)
        .description("")

    return RestDocField(descriptor)
}