package org.example.webflux

import com.fasterxml.jackson.databind.ObjectMapper
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.reactor.asFlux
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/demo")
class DemoController(
    private val restTemplate: RestTemplate,
    private val mapper: ObjectMapper,
) {

    @GetMapping
    fun weatherDetails(): Mono<List<String>> =
        merge(
            flowOf("Mercury", "Venus", "Earth"),
            flowOf("Mars", "Jupiter", "Saturn"),
            flowOf("Uranus", "Neptune", "Pluto")
        ).asFlux()
            .collectList()
}

