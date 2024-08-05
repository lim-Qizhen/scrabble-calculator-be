package com.example.scrabble_calculator.dto.response

import com.fasterxml.jackson.annotation.JsonProperty

open class ScoreResponseDto(
    @JsonProperty("score")
    val score: Int
)