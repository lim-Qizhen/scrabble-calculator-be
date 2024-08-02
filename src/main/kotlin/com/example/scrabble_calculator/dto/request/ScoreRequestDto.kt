package com.example.scrabble_calculator.dto.request

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size

class ScoreRequestDto(
    @field:NotBlank(message = "Name must not be blank")
    val name: String,
    @field:Pattern(regexp = "^[a-zA-Z]+$", message = "Word must contain only letters")
    @field:Size(min = 2, max = 10, message = "Word must be between 2 to 10 letters")
    val word: String,
)