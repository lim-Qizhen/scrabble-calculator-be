package com.example.scrabble_calculator.dto.request

import com.example.scrabble_calculator.annotation.Word
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size

open class WordRequestDto(
    @field:Pattern(regexp = "^[a-zA-Z]+$", message = "Word must contain only letters")
    @field:Size(min = 2, max = 10, message = "Word must be between 2 to 10 letters")
    @field:Word
    val word: String,
)