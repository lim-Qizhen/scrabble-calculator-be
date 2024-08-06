package com.example.scrabble_calculator.model

import jakarta.persistence.*

@Entity
data class Score(
    @Column(nullable = false)
    val name: String,
    @Column(nullable = false, name = "`value`")
    val value: Int,
    @Column(nullable = false)
    val word: String,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
)