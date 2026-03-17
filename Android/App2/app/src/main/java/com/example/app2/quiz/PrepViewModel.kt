package com.example.app2.quiz

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.app2.data.model.PrepAnswerRecord
import com.example.app2.data.model.PrepQuestion
import com.example.app2.data.repository.PrepRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class PrepState(
    val questions: List<PrepQuestion> = emptyList(),
    val currentIndex: Int = 0,
    val selectedAnswer: String? = null,
    val isAnswerRevealed: Boolean = false,
    val score: Int = 0,
    val answers: List<PrepAnswerRecord> = emptyList()
)

sealed class PrepEvent {
    object QuizComplete : PrepEvent()
}

class PrepViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = PrepRepository(application)

    private val _state = MutableStateFlow(PrepState())
    val state: StateFlow<PrepState> = _state.asStateFlow()

    private val _events = MutableSharedFlow<PrepEvent>()
    val events = _events.asSharedFlow()

    fun startNewQuiz() {
        val questions = repository.questions.shuffled().take(10)
        _state.value = PrepState(questions = questions)
    }

    fun selectAnswer(answer: String) {
        val state = _state.value
        if (state.isAnswerRevealed) return
        val question = state.questions.getOrNull(state.currentIndex) ?: return
        val wasCorrect = answer == question.answer
        _state.update {
            it.copy(
                selectedAnswer = answer,
                isAnswerRevealed = true,
                score = if (wasCorrect) it.score + 1 else it.score,
                answers = it.answers + PrepAnswerRecord(question, answer, wasCorrect)
            )
        }
    }

    fun nextQuestion() {
        val state = _state.value
        val nextIndex = state.currentIndex + 1
        if (nextIndex >= state.questions.size) {
            viewModelScope.launch { _events.emit(PrepEvent.QuizComplete) }
        } else {
            _state.update { it.copy(currentIndex = nextIndex, selectedAnswer = null, isAnswerRevealed = false) }
        }
    }
}
