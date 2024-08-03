package org.example.boilerdocs.util.restdocs.dsl

import org.example.boilerdocs.util.restdocs.DocsFieldTypes
import org.example.boilerdocs.util.restdocs.RestDocField
import org.springframework.restdocs.payload.JsonFieldType
import org.springframework.restdocs.payload.PayloadDocumentation

class FieldsBuilder {
    private val fields = mutableListOf<RestDocField>()

    infix fun String.type(docs: DocsFieldTypes): RestDocField {
        val field = createField(this, docs.type)
        fields.add(field)
        return field
    }

    private fun createField(value: String, type: JsonFieldType): RestDocField {
        val descriptor = PayloadDocumentation.fieldWithPath(value)
            .type(type)
            .description("")
        return RestDocField(descriptor)
    }

    operator fun Array<out RestDocField>.unaryPlus() {
        fields.addAll(this)
    }

    fun build(): List<RestDocField> = fields
}