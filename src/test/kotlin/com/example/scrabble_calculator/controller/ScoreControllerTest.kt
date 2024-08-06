package com.example.scrabble_calculator.controller

import com.example.scrabble_calculator.dto.request.SubmitWordRequestDto
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ScoreControllerTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun calculateScore_success() {
        mockMvc.perform(
            MockMvcRequestBuilders.get("/scores/calculate")
                .param("word", "example")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk)
            .andExpect(jsonPath("$.score").value(18))
    }

    @Test
    fun calculateScore_invalidWord() {
        mockMvc.perform(
            MockMvcRequestBuilders.get("/scores/calculate")
                .param("word", "lcjfmnwe")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest)
    }

    @Test
    fun submitWord_success() {
        val mapper = ObjectMapper();
        mockMvc.perform(
            MockMvcRequestBuilders.post("/scores")
                .content(mapper.writeValueAsString(getSubmitWordRequestDto("example")))
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk)
            .andExpect(jsonPath("$.score").value(18))
            .andExpect(jsonPath("$.name").value("User"))
            .andExpect(jsonPath("$.word").value("example"))
    }

    @ParameterizedTest
    @ValueSource(strings = ["lcjfmnwe", "a", "susceptible", "123"])
    fun submitWord_invalidWord(invalidWord: String) {
        val mapper = ObjectMapper();
        mockMvc.perform(
            MockMvcRequestBuilders.post("/scores")
                .content(mapper.writeValueAsString(getSubmitWordRequestDto(invalidWord)))
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest)
    }

    @Test
    fun getScores_success() {
        saveWordToDatabase("example")
        saveWordToDatabase("something")
        saveWordToDatabase("an")
        mockMvc.perform(
            MockMvcRequestBuilders.get("/scores")
                .param("size", "2")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk)
            .andExpect(jsonPath("$.size").value(2))
    }

    private fun saveWordToDatabase(word: String) {
        val mapper = ObjectMapper();
        mockMvc.perform(
            MockMvcRequestBuilders.post("/scores")
                .content(mapper.writeValueAsString(getSubmitWordRequestDto(word)))
                .contentType(MediaType.APPLICATION_JSON)
        )
    }

    private fun getSubmitWordRequestDto(word: String): SubmitWordRequestDto {
        return SubmitWordRequestDto(name = "User", word = word)
    }
}