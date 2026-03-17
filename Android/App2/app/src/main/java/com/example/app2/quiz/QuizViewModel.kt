package com.example.app2.quiz

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.app2.data.model.AnswerRecord
import com.example.app2.data.model.QuizQuestion
import com.example.app2.data.model.Subject
import com.example.app2.data.model.Tense
import com.example.app2.data.repository.VerbRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class QuizState(
    val questions: List<QuizQuestion> = emptyList(),
    val currentIndex: Int = 0,
    val selectedAnswer: String? = null,
    val isAnswerRevealed: Boolean = false,
    val score: Int = 0,
    val answers: List<AnswerRecord> = emptyList()
)

sealed class QuizEvent {
    object QuizComplete : QuizEvent()
}

class QuizViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = VerbRepository(application)

    private val _state = MutableStateFlow(QuizState())
    val state: StateFlow<QuizState> = _state.asStateFlow()

    private val _events = MutableSharedFlow<QuizEvent>()
    val events = _events.asSharedFlow()

    private var lastAllowedTenses: Set<Tense> = emptySet()
    private var lastAllowedSubjects: Set<Subject> = emptySet()

    fun startNewQuiz(
        allowedTenses: Set<Tense> = lastAllowedTenses,
        allowedSubjects: Set<Subject> = lastAllowedSubjects
    ) {
        lastAllowedTenses = allowedTenses
        lastAllowedSubjects = allowedSubjects
        val questions = generateQuestions(allowedTenses, allowedSubjects)
        _state.value = QuizState(questions = questions)
    }

    private fun generateQuestions(
        allowedTenses: Set<Tense>,
        allowedSubjects: Set<Subject>
    ): List<QuizQuestion> {
        val verbs = repository.verbs
        val effectiveTenses = if (allowedTenses.isEmpty()) Tense.entries.toList() else allowedTenses.toList()
        val effectiveSubjects = if (allowedSubjects.isEmpty()) Subject.entries.toList() else allowedSubjects.toList()
        val imperativoSubjects = effectiveSubjects.filter { it != Subject.EU }

        val questions = mutableListOf<QuizQuestion>()
        val used = mutableSetOf<String>()

        repeat(10) {
            var attempts = 0
            while (attempts < 100) {
                val tense = effectiveTenses.random()
                val isImperativo = tense == Tense.IMPERATIVO_AFIRMATIVO || tense == Tense.IMPERATIVO_NEGATIVO
                if (isImperativo && imperativoSubjects.isEmpty()) { attempts++; continue }
                val subject = if (isImperativo) imperativoSubjects.random() else effectiveSubjects.random()
                val verb = verbs.random()
                val key = "${verb.infinitive}|${tense}|${subject}"
                if (key in used) { attempts++; continue }

                val correctAnswer = DistractorGenerator.getForm(verb, tense, subject) ?: run { attempts++; return@run null } ?: continue
                val distractors = DistractorGenerator.generate(verb, tense, subject, correctAnswer, verbs)
                if (distractors.size < 3) { attempts++; continue }

                used.add(key)
                val choices = (distractors + correctAnswer).shuffled()
                questions.add(QuizQuestion(verb, tense, subject, correctAnswer, choices))
                return@repeat
            }
        }
        return questions
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
                answers = it.answers + AnswerRecord(question, answer, wasCorrect)
            )
        }
    }

    fun nextQuestion() {
        val state = _state.value
        val nextIndex = state.currentIndex + 1
        if (nextIndex >= state.questions.size) {
            viewModelScope.launch { _events.emit(QuizEvent.QuizComplete) }
        } else {
            _state.update { it.copy(currentIndex = nextIndex, selectedAnswer = null, isAnswerRevealed = false) }
        }
    }
}
