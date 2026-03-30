package com.example.app2.quiz

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.app2.data.model.QuizDirection
import com.example.app2.data.model.VocabAnswerRecord
import com.example.app2.data.model.VocabQuestion
import com.example.app2.data.model.VocabWord
import com.example.app2.data.model.WordType
import com.example.app2.data.repository.VocabularyRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class VocabQuizState(
    val questions: List<VocabQuestion> = emptyList(),
    val currentIndex: Int = 0,
    val selectedAnswer: String? = null,
    val isAnswerRevealed: Boolean = false,
    val score: Int = 0,
    val answers: List<VocabAnswerRecord> = emptyList(),
    val survivalMode: Boolean = false
)

sealed class VocabQuizEvent {
    object QuizComplete : VocabQuizEvent()
}

class VocabQuizViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = VocabularyRepository(application)

    private val _state = MutableStateFlow(VocabQuizState())
    val state: StateFlow<VocabQuizState> = _state.asStateFlow()

    private val _events = MutableSharedFlow<VocabQuizEvent>()
    val events = _events.asSharedFlow()

    private var lastDirection: QuizDirection = QuizDirection.EN_TO_PT
    private var lastSurvivalMode: Boolean = false

    // Persistent dedup set for survival mode; reset on each new quiz
    private val usedPt = mutableSetOf<String>()

    fun startNewQuiz(direction: QuizDirection, survivalMode: Boolean = false) {
        lastDirection = direction
        lastSurvivalMode = survivalMode
        usedPt.clear()

        if (survivalMode) {
            val first = generateSingleQuestion(direction)
            _state.value = VocabQuizState(
                questions = if (first != null) listOf(first) else emptyList(),
                survivalMode = true
            )
        } else {
            _state.value = VocabQuizState(questions = generateQuestions(direction))
        }
    }

    fun replayLastQuiz() = startNewQuiz(lastDirection, lastSurvivalMode)

    private fun generateQuestions(direction: QuizDirection): List<VocabQuestion> {
        val questions = mutableListOf<VocabQuestion>()
        repeat(10) {
            val q = generateSingleQuestion(direction)
            if (q != null) questions.add(q)
        }
        return questions
    }

    private fun generateSingleQuestion(direction: QuizDirection): VocabQuestion? {
        val allWords = repository.allWords
        var attempts = 0
        while (attempts < 100) {
            val word = allWords.random()
            if (word.pt in usedPt) { attempts++; continue }

            val correctAnswer = if (direction == QuizDirection.EN_TO_PT) word.pt else word.en
            val sameTypePool = repository.wordsOfType(word.type).filter { it.pt != word.pt }

            val distractorWords = sameTypePool.shuffled().take(3)
            val fallbackPool = allWords.filter { it.pt != word.pt && it !in distractorWords }

            val distractorAnswers = distractorWords.map {
                if (direction == QuizDirection.EN_TO_PT) it.pt else it.en
            }.toMutableList()

            if (distractorAnswers.size < 3) {
                val needed = 3 - distractorAnswers.size
                fallbackPool.shuffled().take(needed).forEach { w ->
                    val ans = if (direction == QuizDirection.EN_TO_PT) w.pt else w.en
                    if (ans !in distractorAnswers && ans != correctAnswer) {
                        distractorAnswers.add(ans)
                    }
                }
            }

            if (distractorAnswers.size < 3) { attempts++; continue }

            usedPt.add(word.pt)
            val choices = (distractorAnswers.take(3) + correctAnswer).shuffled()
            return VocabQuestion(word, direction, correctAnswer, choices)
        }
        return null
    }

    fun selectAnswer(answer: String) {
        val state = _state.value
        if (state.isAnswerRevealed) return
        val question = state.questions.getOrNull(state.currentIndex) ?: return
        val wasCorrect = answer == question.correctAnswer
        _state.update {
            it.copy(
                selectedAnswer = answer,
                isAnswerRevealed = true,
                score = if (wasCorrect) it.score + 1 else it.score,
                answers = it.answers + VocabAnswerRecord(question, answer, wasCorrect)
            )
        }
    }

    fun nextQuestion() {
        val state = _state.value
        if (state.survivalMode) {
            val lastAnswerCorrect = state.answers.lastOrNull()?.wasCorrect == true
            if (!lastAnswerCorrect) {
                viewModelScope.launch { _events.emit(VocabQuizEvent.QuizComplete) }
                return
            }
            val newQuestion = generateSingleQuestion(lastDirection)
            if (newQuestion == null) {
                viewModelScope.launch { _events.emit(VocabQuizEvent.QuizComplete) }
                return
            }
            _state.update {
                it.copy(
                    questions = it.questions + newQuestion,
                    currentIndex = it.currentIndex + 1,
                    selectedAnswer = null,
                    isAnswerRevealed = false
                )
            }
        } else {
            val nextIndex = state.currentIndex + 1
            if (nextIndex >= state.questions.size) {
                viewModelScope.launch { _events.emit(VocabQuizEvent.QuizComplete) }
            } else {
                _state.update { it.copy(currentIndex = nextIndex, selectedAnswer = null, isAnswerRevealed = false) }
            }
        }
    }
}
