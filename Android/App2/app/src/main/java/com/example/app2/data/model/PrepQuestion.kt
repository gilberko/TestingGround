package com.example.app2.data.model

import kotlinx.serialization.Serializable

@Serializable
data class PrepQuestion(
    val id: Int,
    val sentence: String,
    val answer: String,
    val choices: List<String>,
    val category: String = "general",
    val hint: String? = null
)

data class PrepAnswerRecord(
    val question: PrepQuestion,
    val selectedAnswer: String,
    val wasCorrect: Boolean
)
