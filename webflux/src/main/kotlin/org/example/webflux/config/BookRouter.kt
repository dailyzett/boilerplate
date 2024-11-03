package org.example.webflux.config

import org.example.webflux.component.BookHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions.route

@Configuration("bookRouterV1")
class BookRouter {
    @Bean
    fun routeBook(handler: BookHandler): RouterFunction<*> {
        return route()
            .POST("/v1/books", handler::createBook)
            .PATCH("/v1/books/{book-id}", handler::updateBook)
            .build()
    }
}