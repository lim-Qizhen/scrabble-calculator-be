package com.example.scrabble_calculator.dto.response

import com.fasterxml.jackson.annotation.JsonProperty

class SubmitWordResponseDto(
    score: Int,
    @JsonProperty("word")
    val word: String,
    @JsonProperty("name")
    val name: String
) : ScoreResponseDto(score)