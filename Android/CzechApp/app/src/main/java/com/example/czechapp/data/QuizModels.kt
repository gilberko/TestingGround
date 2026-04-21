package com.example.czechapp.data

import com.google.gson.annotations.SerializedName

// ── Verb data ─────────────────────────────────────────────────────────────────

data class VerbQuizEntry(
    val id: String,
    val infinitive: String,
    val english: String,
    val perfective: String,
    val ja: String,
    val ty: String,
    @SerializedName("on_ona") val onOna: String,
    val my: String,
    val vy: String,
    @SerializedName("oni_ony") val oniOny: String
)

data class VerbsQuizFile(val verbs: List<VerbQuizEntry>)

fun VerbQuizEntry.allForms(): List<Pair<String, String>> = listOf(
    "já"        to ja,
    "ty"        to ty,
    "on/ona"    to onOna,
    "my"        to my,
    "vy"        to vy,
    "oni/ony"   to oniOny
)

// ── Vocabulary data ───────────────────────────────────────────────────────────

data class VocabularyEntry(val id: String, val english: String, val czech: String)
data class VocabularyQuizFile(val vocabulary: List<VocabularyEntry>)

// ── Shared quiz state ─────────────────────────────────────────────────────────

data class QuizQuestion(
    val promptWord: String,
    val promptLabel: String,
    val questionText: String,
    val correctAnswer: String,
    val choices: List<String>
)

data class QuizState(
    val questions: List<QuizQuestion> = emptyList(),
    val currentIndex: Int = 0,
    val selectedAnswer: String? = null,
    val score: Int = 0,
    val isFinished: Boolean = false
) {
    val currentQuestion: QuizQuestion? get() = questions.getOrNull(currentIndex)
    val isAnswered: Boolean get() = selectedAnswer != null
}
