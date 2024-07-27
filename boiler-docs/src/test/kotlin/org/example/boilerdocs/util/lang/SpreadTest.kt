package org.example.boilerdocs.util.lang

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class SpreadTest {
    @Test
    fun spreadTest() {
        val arrayOf = arrayOf(1, 2, 3)
        val toList = listOf(*arrayOf)

        println(toList)
        Assertions.assertThat(toList).containsExactly(1, 2, 3)
    }
}