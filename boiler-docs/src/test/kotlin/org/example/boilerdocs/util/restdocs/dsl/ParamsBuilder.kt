package org.example.boilerdocs.util.restdocs.dsl

import org.springframework.restdocs.request.ParameterDescriptor
import org.springframework.restdocs.request.RequestDocumentation
import org.springframework.restdocs.snippet.Attributes.key

class ParamsBuilder {
    private val params = mutableListOf<RestDocsParam>()

    infix fun String.means(description: String): RestDocsParam {
        return createParams(this).means(description).also { params.add(it) }
    }

    private fun createParams(
        value: String,
        optional: Boolean = false
    ): RestDocsParam {
        val descriptor = RequestDocumentation
            .parameterWithName(value)
            .description("")
            .attributes(key("defaultValue").value(""))

        if (optional) descriptor.optional()
        return RestDocsParam(descriptor)
    }

    fun build(): List<ParameterDescriptor> = params.map { it.descriptor }
}

class RestDocsParam(
    val descriptor: ParameterDescriptor
) {
    infix fun means(value: String): RestDocsParam {
        descriptor.description(value)
        return this
    }
}