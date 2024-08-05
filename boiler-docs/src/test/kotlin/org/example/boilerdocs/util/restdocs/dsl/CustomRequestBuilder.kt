package org.example.boilerdocs.util.restdocs.dsl

import com.fasterxml.jackson.databind.ObjectMapper
import org.example.boilerdocs.util.restdocs.toJson
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders

class RequestBuilder {
    var endPoint: String = ""
    var method: HttpMethod = HttpMethod.GET
    var params: MutableMap<String, String> = mutableMapOf()
    var body: Any? = null

    fun build(): MockHttpServletRequestBuilder {
        val builder = when (method) {
            HttpMethod.GET -> get(endPoint)
            HttpMethod.POST -> post(endPoint)
            HttpMethod.PUT -> put(endPoint)
            HttpMethod.DELETE -> delete(endPoint)
            else -> throw RuntimeException("Unsupported method: $method")
        }
        
        body?.let {
            builder.contentType(MediaType.APPLICATION_JSON)
                .content(ObjectMapper().writeValueAsString(it))
        }

        return builder
    }
}

data class CustomRequestBuilder(
    var endPoint: String = "",
    var method: HttpMethod = HttpMethod.GET,
    var params: MutableMap<String, String> = mutableMapOf(),
    var body: Any? = null
)

fun requestBuilder(
    block: CustomRequestBuilder.() -> Unit
): MockHttpServletRequestBuilder {
    val builder = CustomRequestBuilder().apply(block)
    val mockRequest = when (builder.method) {
        HttpMethod.GET -> MockMvcRequestBuilders.get(builder.endPoint)
        HttpMethod.POST -> MockMvcRequestBuilders.post(builder.endPoint)
        HttpMethod.PUT -> MockMvcRequestBuilders.put(builder.endPoint)
        HttpMethod.DELETE -> MockMvcRequestBuilders.delete(builder.endPoint)
        else -> throw RuntimeException("")
    }

    for ((key, value) in builder.params) {
        mockRequest.param(key, value)
    }

    builder.body?.let {
        mockRequest.content(toJson(it))
    }

    mockRequest.contentType(MediaType.APPLICATION_JSON)

    return mockRequest
}