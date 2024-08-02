package com.example.scrabble_calculator.dto.request

import jakarta.validation.constraints.NotBlank

class ScoreRequestDto(
    word: String,
    @field:NotBlank(message = "Name must not be blank")
    val name: String,
) : WordRequestDto(word)