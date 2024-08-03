package org.example.boilerdocs.repo

import org.example.boilerdocs.entity.TUser
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<TUser, Int> {
    fun findTUserByNameAndHobby(name: String, hobby: String): TUser?
}