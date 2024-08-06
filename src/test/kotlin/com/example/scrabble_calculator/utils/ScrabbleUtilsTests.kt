package com.example.scrabble_calculator.utils

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class ScrabbleUtilsTests {
    @ParameterizedTest
    @MethodSource("provideWordForScrabbleScoreCalculation")
    fun testGetScrabbleScore(word: String, expectedScore: Int) {
        assertEquals(expectedScore, ScrabbleUtils.calculateWordScore(word))
    }

    companion object {
        @JvmStatic
        fun provideWordForScrabbleScoreCalculation(): List<Arguments> {
            return listOf(
                Arguments.of("word", 8),
                Arguments.of("newbie", 11)
            )
        }
    }

}