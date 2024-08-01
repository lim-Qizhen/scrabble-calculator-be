package com.example.scrabble_calculator.controller.ScoreController

import com.example.scrabble_calculator.dto.response.ScoreResponseDto
import com.example.scrabble_calculator.service.ScoreService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/score")
class ScoreController(private val scoreService: ScoreService) {

    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Validated word and returned calculated score"),
        ApiResponse(responseCode = "400", description = "Bad request")])
    @Operation(summary = "Validate word and calculate score if valid")
    @GetMapping
    fun getScore(@NotBlank
                 @Pattern(regexp = "^[a-zA-Z]+$", message = "Word must contain only letters")
                 @Size(min = 2, max = 10, message = "Word must have between 2 to 10 letters")
                 @RequestParam word: String): ScoreResponseDto {
        return scoreService.getScore(word)
    }
}