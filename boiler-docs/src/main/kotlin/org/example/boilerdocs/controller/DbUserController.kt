package org.example.boilerdocs.controller

import org.example.boilerdocs.entity.TUser
import org.example.boilerdocs.repo.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/db/users")
class DbUserController(
    private val userRepository: UserRepository,
) {
    @GetMapping("/{id}")
    fun userDetails(@PathVariable id: Int): ResponseEntity<TUser> {
        val foundUser: TUser? = userRepository.findByIdOrNull(id)
        return ResponseEntity.ok(foundUser)
    }
}