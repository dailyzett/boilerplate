package org.example.boilerdocs.util.restdocs

import com.fasterxml.jackson.annotation.JsonInclude.Include
import com.fasterxml.jackson.databind.ObjectMapper


private val mapper: ObjectMapper = ObjectMapper()

fun toJson(a: Any?): String {
    if (a == null) return ""
    mapper.setSerializationInclusion(Include.NON_NULL)
    return mapper.writeValueAsString(a)
}
