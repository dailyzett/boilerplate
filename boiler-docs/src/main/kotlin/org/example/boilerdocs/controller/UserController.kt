package org.example.boilerdocs.controller

import org.example.boilerdocs.dto.UserDto
import org.springframework.core.env.Environment
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestClient
import org.springframework.web.client.body

@RestController
@RequestMapping("/users")
class UserController(
    env: Environment,
    private val restClient: RestClient,
) {

    val baseUrl = env.getProperty("jsonplaceholder.url")

    @GetMapping("")
    fun userList(): ResponseEntity<List<UserDto>> {
        val userDtoList = restClient.get()
            .uri("$baseUrl/users")
            .retrieve()
            .body<List<UserDto>>()

        return ResponseEntity(userDtoList, HttpStatus.OK)
    }

    @GetMapping("/{id}")
    fun user(@PathVariable("id") id: String): ResponseEntity<UserDto> {
        val userDto = restClient.get()
            .uri("$baseUrl/users/$id")
            .retrieve()
            .body<UserDto>()

        return ResponseEntity(userDto, HttpStatus.OK)
    }
}