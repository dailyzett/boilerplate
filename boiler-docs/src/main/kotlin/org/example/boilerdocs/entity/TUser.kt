package org.example.boilerdocs.entity

import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "T_USER")
class TUser(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int = 0,

    val name: String? = null,
    val phone: String? = null,
    val hobby: String? = null,
    val email: String? = null,

    @Column(name = "birth_date")
    val birthDate: LocalDate? = null,
    val address: String? = null,
)