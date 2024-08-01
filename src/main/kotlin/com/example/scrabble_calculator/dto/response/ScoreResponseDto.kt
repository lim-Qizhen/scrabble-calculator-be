package com.example.scrabble_calculator.dto.response

import com.fasterxml.jackson.annotation.JsonProperty

data class ScoreResponseDto(
        @JsonProperty("score")
        var score: Int
)