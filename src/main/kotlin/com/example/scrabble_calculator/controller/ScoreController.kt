package com.example.scrabble_calculator.controller.ScoreController

import com.example.scrabble_calculator.dto.request.ScoreRequestDto
import com.example.scrabble_calculator.dto.request.WordRequestDto
import com.example.scrabble_calculator.dto.response.ScoreResponseDto
import com.example.scrabble_calculator.model.Score
import com.example.scrabble_calculator.service.ScoreService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import jakarta.validation.Valid
import org.springdoc.core.annotations.ParameterObject
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/scores")
class ScoreController(private val scoreService: ScoreService) {

    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Validated word and returned calculated score"),
            ApiResponse(responseCode = "400", description = "Bad request")]
    )
    @Operation(summary = "Validate word and calculate score if valid")
    @GetMapping("/calculate")
    fun getScore(
        @Valid @ParameterObject wordRequestDto: WordRequestDto
    ): ScoreResponseDto {
        return scoreService.calculateScore(wordRequestDto.word)
    }

    @ApiResponses(
        value = [
            ApiResponse(responseCode = "201", description = "Saved word successfully"),
            ApiResponse(responseCode = "400", description = "Bad request")]
    )
    @Operation(summary = "Submit word")
    @PostMapping
    fun submitWord(@Valid @RequestBody scoreRequestDto: ScoreRequestDto) {
        return scoreService.submitWord(scoreRequestDto)
    }

    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Get scores successfully"),
            ApiResponse(responseCode = "400", description = "Bad request")]
    )
    @Operation(summary = "Get scores")
    @GetMapping
    fun getScores(
        @ParameterObject
        @PageableDefault(page = 0, size = 10, sort = ["value"], direction = Sort.Direction.DESC) pageable: Pageable
    ): Page<Score> {
        return scoreService.getScores(pageable)
    }
}