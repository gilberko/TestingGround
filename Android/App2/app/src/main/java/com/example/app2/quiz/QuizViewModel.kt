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
    val answers: List<AnswerRecord> = emptyList(),
    val survivalMode: Boolean = false
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
    private var lastSurvivalMode: Boolean = false

    // Persistent dedup set for survival mode; reset on each new quiz
    private val usedKeys = mutableSetOf<String>()

    // Cached question-generation params for lazy survival questions
    private var lastVerbPool: List<com.example.app2.data.model.Verb> = emptyList()
    private var lastEffectiveTenses: List<Tense> = emptyList()
    private var lastEffectiveSubjects: List<Subject> = emptyList()
    private var lastImperativoSubjects: List<Subject> = emptyList()

    fun startNewQuiz(
        allowedTenses: Set<Tense> = lastAllowedTenses,
        allowedSubjects: Set<Subject> = lastAllowedSubjects,
        regularityFilter: RegularityFilter = lastRegularityFilter,
        survivalMode: Boolean = lastSurvivalMode
    ) {
        lastAllowedTenses = allowedTenses
        lastAllowedSubjects = allowedSubjects
        lastRegularityFilter = regularityFilter
        lastSurvivalMode = survivalMode

        usedKeys.clear()

        val allVerbs = repository.verbs
        lastVerbPool = when (regularityFilter) {
            RegularityFilter.ALL -> allVerbs
            RegularityFilter.REGULAR_ONLY -> allVerbs.filter { !it.isIrregular }
            RegularityFilter.IRREGULAR_ONLY -> allVerbs.filter { it.isIrregular }
        }.takeIf { it.isNotEmpty() } ?: allVerbs

        lastEffectiveTenses = if (allowedTenses.isEmpty()) Tense.entries.toList() else allowedTenses.toList()
        lastEffectiveSubjects = if (allowedSubjects.isEmpty()) Subject.entries.toList() else allowedSubjects.toList()
        lastImperativoSubjects = lastEffectiveSubjects.filter { it != Subject.EU }

        if (survivalMode) {
            val first = generateSingleQuestion(lastVerbPool, lastEffectiveTenses, lastEffectiveSubjects, lastImperativoSubjects)
            _state.value = QuizState(
                questions = if (first != null) listOf(first) else emptyList(),
                survivalMode = true
            )
        } else {
            val questions = generateQuestions(lastVerbPool, lastEffectiveTenses, lastEffectiveSubjects, lastImperativoSubjects)
            _state.value = QuizState(questions = questions)
        }
    }

    private fun generateQuestions(
        verbPool: List<com.example.app2.data.model.Verb>,
        effectiveTenses: List<Tense>,
        effectiveSubjects: List<Subject>,
        imperativoSubjects: List<Subject>
    ): List<QuizQuestion> {
        val questions = mutableListOf<QuizQuestion>()
        repeat(10) {
            val q = generateSingleQuestion(verbPool, effectiveTenses, effectiveSubjects, imperativoSubjects)
            if (q != null) questions.add(q)
        }
        return questions
    }

    private fun generateSingleQuestion(
        verbPool: List<com.example.app2.data.model.Verb>,
        effectiveTenses: List<Tense>,
        effectiveSubjects: List<Subject>,
        imperativoSubjects: List<Subject>
    ): QuizQuestion? {
        var attempts = 0
        while (attempts < 100) {
            val tense = effectiveTenses.random()

            when {
                tense == Tense.GERUND -> {
                    val verb = verbPool.random()
                    val key = "${verb.infinitive}|${tense}|GERUND"
                    if (key in usedKeys) { attempts++; continue }

                    val correctAnswer = VerbForms.gerund(verb)
                    val distractors = DistractorGenerator.generateGerundDistractors(verb, correctAnswer, verbPool)
                    if (distractors.size < 3) { attempts++; continue }

                    usedKeys.add(key)
                    val choices = (distractors + correctAnswer).shuffled()
                    return QuizQuestion(verb, tense, null, correctAnswer, choices)
                }
                tense in PASSIVE_TENSES -> {
                    if (effectiveSubjects.isEmpty()) { attempts++; continue }
                    val subject = effectiveSubjects.random()
                    val verb = verbPool.random()
                    val key = "${verb.infinitive}|${tense}|${subject}"
                    if (key in usedKeys) { attempts++; continue }

                    val correctAnswer = VerbForms.passiveForm(verb, tense, subject) ?: run { attempts++; return@run null } ?: continue
                    val distractors = DistractorGenerator.generatePassiveDistractors(verb, tense, subject, correctAnswer, verbPool)
                    if (distractors.size < 3) { attempts++; continue }

                    usedKeys.add(key)
                    val choices = (distractors + correctAnswer).shuffled()
                    return QuizQuestion(verb, tense, subject, correctAnswer, choices)
                }
                else -> {
                    val isImperativo = tense == Tense.IMPERATIVO_AFIRMATIVO || tense == Tense.IMPERATIVO_NEGATIVO
                    if (isImperativo && imperativoSubjects.isEmpty()) { attempts++; continue }
                    val subject = if (isImperativo) imperativoSubjects.random() else effectiveSubjects.random()
                    val verb = verbPool.random()
                    val key = "${verb.infinitive}|${tense}|${subject}"
                    if (key in usedKeys) { attempts++; continue }

                    val correctAnswer = DistractorGenerator.getForm(verb, tense, subject) ?: run { attempts++; return@run null } ?: continue
                    val distractors = DistractorGenerator.generate(verb, tense, subject, correctAnswer, verbPool)
                    if (distractors.size < 3) { attempts++; continue }

                    usedKeys.add(key)
                    val choices = (distractors + correctAnswer).shuffled()
                    return QuizQuestion(verb, tense, subject, correctAnswer, choices)
                }
            }
        }
        return null
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
        if (state.survivalMode) {
            val lastAnswerCorrect = state.answers.lastOrNull()?.wasCorrect == true
            if (!lastAnswerCorrect) {
                viewModelScope.launch { _events.emit(QuizEvent.QuizComplete) }
                return
            }
            val newQuestion = generateSingleQuestion(
                lastVerbPool, lastEffectiveTenses, lastEffectiveSubjects, lastImperativoSubjects
            )
            if (newQuestion == null) {
                viewModelScope.launch { _events.emit(QuizEvent.QuizComplete) }
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
                viewModelScope.launch { _events.emit(QuizEvent.QuizComplete) }
            } else {
                _state.update { it.copy(currentIndex = nextIndex, selectedAnswer = null, isAnswerRevealed = false) }
            }
        }
    }
}
