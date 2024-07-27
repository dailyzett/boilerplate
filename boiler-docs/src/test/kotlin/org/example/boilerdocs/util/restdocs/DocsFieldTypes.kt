package org.example.boilerdocs.util.restdocs

import org.springframework.restdocs.payload.JsonFieldType

sealed class DocsFieldTypes(val type: JsonFieldType)

data object ARRAY : DocsFieldTypes(JsonFieldType.ARRAY)
data object STRING : DocsFieldTypes(JsonFieldType.STRING)
data object NUMBER : DocsFieldTypes(JsonFieldType.NUMBER)
data object BOOLEAN : DocsFieldTypes(JsonFieldType.BOOLEAN)
data object OBJECT : DocsFieldTypes(JsonFieldType.OBJECT)

