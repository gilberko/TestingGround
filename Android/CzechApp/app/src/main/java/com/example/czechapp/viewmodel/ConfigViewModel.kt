package com.example.czechapp.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

enum class QuizMode { MULTIPLE_CHOICE, FREE_TEXT }

class ConfigViewModel : ViewModel() {
    private val _numQuestions = MutableStateFlow(10)
    val numQuestions: StateFlow<Int> = _numQuestions.asStateFlow()

    fun setNumQuestions(count: Int) {
        _numQuestions.value = count
    }

    private val _quizMode = MutableStateFlow(QuizMode.MULTIPLE_CHOICE)
    val quizMode: StateFlow<QuizMode> = _quizMode.asStateFlow()

    fun setQuizMode(mode: QuizMode) {
        _quizMode.value = mode
    }
}
