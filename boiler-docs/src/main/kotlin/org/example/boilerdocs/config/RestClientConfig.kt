package org.example.boilerdocs.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.SimpleClientHttpRequestFactory
import org.springframework.web.client.RestClient

@Configuration(proxyBeanMethods = false)
class RestClientConfig {
    @Bean
    fun restClient(): RestClient {
        return RestClient.builder()
            .requestFactory(SimpleClientHttpRequestFactory())
            .build()
    }
}