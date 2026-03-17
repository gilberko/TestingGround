package com.example.app2.data.model

data class QuizQuestion(
    val verb: Verb,
    val tense: Tense,
    val subject: Subject,
    val correctAnswer: String,
    val choices: List<String>
)

data class AnswerRecord(
    val question: QuizQuestion,
    val selectedAnswer: String,
    val wasCorrect: Boolean
)
