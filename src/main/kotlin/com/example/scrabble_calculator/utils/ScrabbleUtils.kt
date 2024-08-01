package com.example.scrabble_calculator.utils

import com.example.scrabble_calculator.enums.ScrabbleLetterScore

object ScrabbleUtils {
    fun calculateWordScore(word: String): Int {
        return word.uppercase().sumOf { letter -> ScrabbleLetterScore.valueOf(letter.toString()).score }
    }
}