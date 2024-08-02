package com.example.scrabble_calculator.service

import com.example.scrabble_calculator.dto.request.ScoreRequestDto
import com.example.scrabble_calculator.dto.response.ApiNinjaDictionaryResponseDto
import com.example.scrabble_calculator.dto.response.ScoreResponseDto
import com.example.scrabble_calculator.model.Score
import com.example.scrabble_calculator.repository.ScoreRepository
import com.example.scrabble_calculator.utils.ScrabbleUtils
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.web.client.RestClient
import org.springframework.web.client.body

@Service
class ScoreService(var scoreRepository: ScoreRepository) {
    @Value("\${api.api-ninja.key}")
    private lateinit var apiKey: String

    @Value("\${api.api-ninja.base-url}")
    private lateinit var baseUrl: String

    fun calculateScore(word: String): ScoreResponseDto {
        validateWord(word)
        return ScoreResponseDto(ScrabbleUtils.calculateWordScore(word))
    }

    fun submitWord(scoreRequestDto: ScoreRequestDto) {
        validateWord(scoreRequestDto.word)
        val value = ScrabbleUtils.calculateWordScore(scoreRequestDto.word)
        scoreRepository.save(Score(scoreRequestDto.name, value, scoreRequestDto.word))
    }

    fun getScores(pageable: Pageable): Page<Score> {
        return scoreRepository.findAll(pageable)
    }

    private fun validateWord(word: String) {
        val restClient = RestClient.builder()
                .defaultHeader("X-Api-Key", apiKey)
                .build()
        val result = restClient.get()
                .uri("${baseUrl}/dictionary?word={word}", word)
                .retrieve()
                .body<ApiNinjaDictionaryResponseDto>()
        result?.valid?.let { check(it) }
    }
}