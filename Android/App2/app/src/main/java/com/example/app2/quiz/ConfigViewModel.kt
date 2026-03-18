package com.example.app2.quiz

import androidx.lifecycle.ViewModel
import com.example.app2.data.model.RegularityFilter
import com.example.app2.data.model.Subject
import com.example.app2.data.model.Tense
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ConfigViewModel : ViewModel() {
    private val _quizMode = MutableStateFlow(QuizMode.MULTIPLE_CHOICE)
    val quizMode: StateFlow<QuizMode> = _quizMode.asStateFlow()

    private val _selectedTenses = MutableStateFlow<Set<Tense>>(emptySet())
    val selectedTenses: StateFlow<Set<Tense>> = _selectedTenses.asStateFlow()

    private val _selectedSubjects = MutableStateFlow<Set<Subject>>(emptySet())
    val selectedSubjects: StateFlow<Set<Subject>> = _selectedSubjects.asStateFlow()

    private val _regularityFilter = MutableStateFlow(RegularityFilter.ALL)
    val regularityFilter: StateFlow<RegularityFilter> = _regularityFilter.asStateFlow()

    fun toggleTense(tense: Tense) {
        _selectedTenses.value = if (tense in _selectedTenses.value)
            _selectedTenses.value - tense else _selectedTenses.value + tense
    }

    fun toggleSubject(subject: Subject) {
        _selectedSubjects.value = if (subject in _selectedSubjects.value)
            _selectedSubjects.value - subject else _selectedSubjects.value + subject
    }

    fun setRegularityFilter(filter: RegularityFilter) {
        _regularityFilter.value = filter
    }

    fun setQuizMode(mode: QuizMode) {
        _quizMode.value = mode
    }
}
