package com.example.scrabble_calculator.service

import com.example.scrabble_calculator.dto.response.ApiNinjaDictionaryResponseDto
import com.example.scrabble_calculator.dto.response.ScoreResponseDto
import com.example.scrabble_calculator.utils.ScrabbleUtils
import org.springframework.stereotype.Service
import org.springframework.web.client.RestClient
import org.springframework.web.client.body

@Service
class ScoreService {
    fun getScore(word: String): ScoreResponseDto {
        validateWord(word)
        return ScoreResponseDto(ScrabbleUtils.calculateWordScore(word))
    }

    private fun validateWord(word: String) {
        val restClient = RestClient.builder()
                .defaultHeader("X-Api-Key", "bVOrPqpWo6Vv0w88zsLQsA==HIFFOtX4TPGmjKeB")
                .build()
        val result = restClient.get()
                .uri("https://api.api-ninjas.com/v1/dictionary?word={word}", word)
                .retrieve()
                .body<ApiNinjaDictionaryResponseDto>()
        result?.valid?.let { check(it) }
    }
}