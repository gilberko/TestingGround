package com.example.app2.data.model

enum class WordType { NOUN, VERB, ADJECTIVE, ADVERB }

enum class QuizDirection { EN_TO_PT, PT_TO_EN }

data class VocabWord(
    val pt: String,
    val en: String,
    val type: WordType
)

data class VocabQuestion(
    val word: VocabWord,
    val direction: QuizDirection,
    val correctAnswer: String,
    val choices: List<String>
)

data class VocabAnswerRecord(
    val question: VocabQuestion,
    val selectedAnswer: String,
    val wasCorrect: Boolean
)
