package com.example.scrabble_calculator.dto.request

import jakarta.validation.constraints.NotBlank

class SubmitWordRequestDto(
    word: String,
    @field:NotBlank(message = "Name must not be blank")
    val name: String,
) : CalculateScoreRequestDto(word)