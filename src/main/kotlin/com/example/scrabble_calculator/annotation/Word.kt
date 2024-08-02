package com.example.scrabble_calculator.annotation

import com.example.scrabble_calculator.validators.WordValidator
import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD)
@MustBeDocumented
@Constraint(validatedBy = [WordValidator::class])
annotation class Word(
    val message: String = "Word must be a valid English word",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)
