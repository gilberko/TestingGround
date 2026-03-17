package com.example.app2.quiz

import androidx.lifecycle.ViewModel
import com.example.app2.data.model.Subject
import com.example.app2.data.model.Tense
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ConfigViewModel : ViewModel() {
    private val _selectedTenses = MutableStateFlow<Set<Tense>>(emptySet())
    val selectedTenses: StateFlow<Set<Tense>> = _selectedTenses.asStateFlow()

    private val _selectedSubjects = MutableStateFlow<Set<Subject>>(emptySet())
    val selectedSubjects: StateFlow<Set<Subject>> = _selectedSubjects.asStateFlow()

    fun toggleTense(tense: Tense) {
        _selectedTenses.value = if (tense in _selectedTenses.value)
            _selectedTenses.value - tense else _selectedTenses.value + tense
    }

    fun toggleSubject(subject: Subject) {
        _selectedSubjects.value = if (subject in _selectedSubjects.value)
            _selectedSubjects.value - subject else _selectedSubjects.value + subject
    }
}
