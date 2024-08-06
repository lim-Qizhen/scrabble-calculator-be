package com.example.scrabble_calculator.validators

import com.example.scrabble_calculator.annotation.Word
import com.example.scrabble_calculator.dto.response.ApiNinjaDictionaryResponseDto
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.client.RestClient
import org.springframework.web.client.body

class WordValidator : ConstraintValidator<Word, String> {
    @Value("\${api.api-ninja.key}")
    private lateinit var apiKey: String

    @Value("\${api.api-ninja.base-url}")
    private lateinit var baseUrl: String
    override fun isValid(word: String?, p1: ConstraintValidatorContext?): Boolean {
        val restClient = RestClient.builder()
            .defaultHeader("X-Api-Key", apiKey)
            .build()
        try {
            val result = restClient.get()
                .uri("${baseUrl}/dictionary?word={word}", word)
                .retrieve()
                .body<ApiNinjaDictionaryResponseDto>()
            return result?.valid ?: false
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}