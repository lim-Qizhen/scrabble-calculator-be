package com.example.scrabble_calculator.service

import com.example.scrabble_calculator.dto.request.ScoreRequestDto
import com.example.scrabble_calculator.dto.response.ScoreResponseDto
import com.example.scrabble_calculator.model.Score
import com.example.scrabble_calculator.repository.ScoreRepository
import com.example.scrabble_calculator.utils.ScrabbleUtils
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class ScoreService(var scoreRepository: ScoreRepository) {
    fun calculateScore(word: String): ScoreResponseDto {
        return ScoreResponseDto(ScrabbleUtils.calculateWordScore(word))
    }

    fun submitWord(scoreRequestDto: ScoreRequestDto) {
        val value = ScrabbleUtils.calculateWordScore(scoreRequestDto.word)
        scoreRepository.save(Score(scoreRequestDto.name, value, scoreRequestDto.word))
    }

    fun getScores(pageable: Pageable): Page<Score> {
        return scoreRepository.findAll(pageable)
    }
}