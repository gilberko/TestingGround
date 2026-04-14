package com.example.app2.quiz

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.app2.data.model.QuizDirection
import com.example.app2.data.model.VocabAnswerRecord
import com.example.app2.data.model.VocabQuestion
import com.example.app2.data.model.VocabWord
import com.example.app2.data.repository.VocabularyRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

enum class MemorizePhase { MEMORIZING, QUIZZING, RESULTS }

data class MemorizeVocabState(
    val phase: MemorizePhase = MemorizePhase.MEMORIZING,
    val words: List<VocabWord> = emptyList(),
    val currentWordIndex: Int = 0,
    val countdownSeconds: Int = 5,
    val questions: List<VocabQuestion> = emptyList(),
    val selectedAnswer: String? = null,
    val isAnswerRevealed: Boolean = false,
    val score: Int = 0,
    val answers: List<VocabAnswerRecord> = emptyList()
)

class MemorizeVocabViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = VocabularyRepository(application)

    private val _state = MutableStateFlow(MemorizeVocabState())
    val state: StateFlow<MemorizeVocabState> = _state.asStateFlow()

    private var lastDirection: QuizDirection = QuizDirection.EN_TO_PT
    private var memorizeJob: Job? = null

    fun start(direction: QuizDirection) {
        lastDirection = direction
        memorizeJob?.cancel()

        val allWords = repository.allWords.shuffled().take(10)
        _state.value = MemorizeVocabState(words = allWords)

        memorizeJob = viewModelScope.launch {
            for (wordIndex in allWords.indices) {
                _state.update { it.copy(currentWordIndex = wordIndex, countdownSeconds = 5) }
                for (t in 4 downTo 0) {
                    delay(1000L)
                    _state.update { it.copy(countdownSeconds = t) }
                }
            }
            val questions = generateQuizQuestions(allWords, direction)
            _state.update { it.copy(phase = MemorizePhase.QUIZZING, currentWordIndex = 0, questions = questions) }
        }
    }

    fun replayLastQuiz() = start(lastDirection)

    private fun generateQuizQuestions(words: List<VocabWord>, direction: QuizDirection): List<VocabQuestion> {
        val allWords = repository.allWords
        return words.mapNotNull { word ->
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

            if (distractorAnswers.size < 3) return@mapNotNull null
            val choices = (distractorAnswers.take(3) + correctAnswer).shuffled()
            VocabQuestion(word, direction, correctAnswer, choices)
        }
    }

    fun selectAnswer(answer: String) {
        val state = _state.value
        if (state.isAnswerRevealed) return
        val question = state.questions.getOrNull(state.currentWordIndex) ?: return
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
        val nextIndex = state.currentWordIndex + 1
        if (nextIndex >= state.questions.size) {
            _state.update { it.copy(phase = MemorizePhase.RESULTS) }
        } else {
            _state.update { it.copy(currentWordIndex = nextIndex, selectedAnswer = null, isAnswerRevealed = false) }
        }
    }
}
