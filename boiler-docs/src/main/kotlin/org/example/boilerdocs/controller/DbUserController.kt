package org.example.boilerdocs.controller

import org.example.boilerdocs.dto.UserReq
import org.example.boilerdocs.entity.TUser
import org.example.boilerdocs.repo.UserRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/db/users")
class DbUserController(
    private val userRepository: UserRepository,
) {
    @PostMapping
    fun userDetails(@RequestBody req: UserReq): ResponseEntity<TUser> {
        val foundUser: TUser? = userRepository.findTUserByNameAndHobby(req.name, req.hobby)
        return ResponseEntity.ok(foundUser)
    }
}