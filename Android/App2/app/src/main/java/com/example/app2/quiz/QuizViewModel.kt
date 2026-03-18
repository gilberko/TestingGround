package com.example.app2.quiz

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.app2.data.model.AnswerRecord
import com.example.app2.data.model.QuizQuestion
import com.example.app2.data.model.RegularityFilter
import com.example.app2.data.model.Subject
import com.example.app2.data.model.Tense
import com.example.app2.data.model.isIrregular
import com.example.app2.data.repository.VerbRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

private val PASSIVE_TENSES = setOf(
    Tense.PASSIVA_PRESENTE,
    Tense.PASSIVA_PRETERITO_PERFEITO,
    Tense.PASSIVA_PRETERITO_IMPERFEITO,
    Tense.PASSIVA_FUTURO,
    Tense.PASSIVA_CONDICIONAL
)

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
    private var lastRegularityFilter: RegularityFilter = RegularityFilter.ALL

    fun startNewQuiz(
        allowedTenses: Set<Tense> = lastAllowedTenses,
        allowedSubjects: Set<Subject> = lastAllowedSubjects,
        regularityFilter: RegularityFilter = lastRegularityFilter
    ) {
        lastAllowedTenses = allowedTenses
        lastAllowedSubjects = allowedSubjects
        lastRegularityFilter = regularityFilter
        val questions = generateQuestions(allowedTenses, allowedSubjects, regularityFilter)
        _state.value = QuizState(questions = questions)
    }

    private fun generateQuestions(
        allowedTenses: Set<Tense>,
        allowedSubjects: Set<Subject>,
        regularityFilter: RegularityFilter
    ): List<QuizQuestion> {
        val allVerbs = repository.verbs
        val verbPool = when (regularityFilter) {
            RegularityFilter.ALL -> allVerbs
            RegularityFilter.REGULAR_ONLY -> allVerbs.filter { !it.isIrregular }
            RegularityFilter.IRREGULAR_ONLY -> allVerbs.filter { it.isIrregular }
        }.takeIf { it.isNotEmpty() } ?: allVerbs

        val effectiveTenses = if (allowedTenses.isEmpty()) Tense.entries.toList() else allowedTenses.toList()
        val effectiveSubjects = if (allowedSubjects.isEmpty()) Subject.entries.toList() else allowedSubjects.toList()
        val imperativoSubjects = effectiveSubjects.filter { it != Subject.EU }

        val questions = mutableListOf<QuizQuestion>()
        val used = mutableSetOf<String>()

        repeat(10) {
            var attempts = 0
            while (attempts < 100) {
                val tense = effectiveTenses.random()

                when {
                    tense == Tense.GERUND -> {
                        val verb = verbPool.random()
                        val key = "${verb.infinitive}|${tense}|GERUND"
                        if (key in used) { attempts++; continue }

                        val correctAnswer = VerbForms.gerund(verb)
                        val distractors = DistractorGenerator.generateGerundDistractors(verb, correctAnswer, verbPool)
                        if (distractors.size < 3) { attempts++; continue }

                        used.add(key)
                        val choices = (distractors + correctAnswer).shuffled()
                        questions.add(QuizQuestion(verb, tense, null, correctAnswer, choices))
                        return@repeat
                    }
                    tense in PASSIVE_TENSES -> {
                        if (effectiveSubjects.isEmpty()) { attempts++; continue }
                        val subject = effectiveSubjects.random()
                        val verb = verbPool.random()
                        val key = "${verb.infinitive}|${tense}|${subject}"
                        if (key in used) { attempts++; continue }

                        val correctAnswer = VerbForms.passiveForm(verb, tense, subject) ?: run { attempts++; return@run null } ?: continue
                        val distractors = DistractorGenerator.generatePassiveDistractors(verb, tense, subject, correctAnswer, verbPool)
                        if (distractors.size < 3) { attempts++; continue }

                        used.add(key)
                        val choices = (distractors + correctAnswer).shuffled()
                        questions.add(QuizQuestion(verb, tense, subject, correctAnswer, choices))
                        return@repeat
                    }
                    else -> {
                        val isImperativo = tense == Tense.IMPERATIVO_AFIRMATIVO || tense == Tense.IMPERATIVO_NEGATIVO
                        if (isImperativo && imperativoSubjects.isEmpty()) { attempts++; continue }
                        val subject = if (isImperativo) imperativoSubjects.random() else effectiveSubjects.random()
                        val verb = verbPool.random()
                        val key = "${verb.infinitive}|${tense}|${subject}"
                        if (key in used) { attempts++; continue }

                        val correctAnswer = DistractorGenerator.getForm(verb, tense, subject) ?: run { attempts++; return@run null } ?: continue
                        val distractors = DistractorGenerator.generate(verb, tense, subject, correctAnswer, verbPool)
                        if (distractors.size < 3) { attempts++; continue }

                        used.add(key)
                        val choices = (distractors + correctAnswer).shuffled()
                        questions.add(QuizQuestion(verb, tense, subject, correctAnswer, choices))
                        return@repeat
                    }
                }
            }
        }
        return questions
    }

    fun selectAnswer(answer: String) {
        val state = _state.value
        if (state.isAnswerRevealed) return
        val question = state.questions.getOrNull(state.currentIndex) ?: return
        val wasCorrect = answer.equals(question.correctAnswer, ignoreCase = true)
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
