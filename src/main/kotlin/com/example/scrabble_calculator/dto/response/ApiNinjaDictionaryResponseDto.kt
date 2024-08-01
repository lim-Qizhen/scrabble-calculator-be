package com.example.scrabble_calculator.dto.response

import com.fasterxml.jackson.annotation.JsonProperty

class ApiNinjaDictionaryResponseDto(
        @JsonProperty("valid")
        val valid: Boolean,
)