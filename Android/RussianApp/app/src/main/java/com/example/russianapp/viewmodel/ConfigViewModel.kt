package com.example.russianapp.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ConfigViewModel : ViewModel() {
    private val _numQuestions = MutableStateFlow(10)
    val numQuestions: StateFlow<Int> = _numQuestions.asStateFlow()

    fun setNumQuestions(count: Int) {
        _numQuestions.value = count
    }
}
