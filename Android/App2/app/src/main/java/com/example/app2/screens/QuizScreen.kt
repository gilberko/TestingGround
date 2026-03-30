package com.example.app2.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.app2.data.model.Tense
import com.example.app2.quiz.ConfigViewModel
import com.example.app2.quiz.QuizEvent
import com.example.app2.quiz.QuizLengthMode
import com.example.app2.quiz.QuizMode
import com.example.app2.quiz.QuizViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizScreen(
    configViewModel: ConfigViewModel,
    onQuizComplete: () -> Unit,
    quizMode: QuizMode = QuizMode.MULTIPLE_CHOICE,
    onOpenTutorial: () -> Unit = {},
    quizViewModel: QuizViewModel = viewModel()
) {
    val state by quizViewModel.state.collectAsState()
    val selectedTenses by configViewModel.selectedTenses.collectAsState()
    val selectedSubjects by configViewModel.selectedSubjects.collectAsState()
    val regularityFilter by configViewModel.regularityFilter.collectAsState()
    val quizLengthMode by configViewModel.quizLengthMode.collectAsState()

    var textFieldValue by rememberSaveable { mutableStateOf("") }

    LaunchedEffect(Unit) {
        quizViewModel.startNewQuiz(selectedTenses, selectedSubjects, regularityFilter, survivalMode = quizLengthMode == QuizLengthMode.SURVIVAL)
        quizViewModel.events.collect { event ->
            when (event) {
                QuizEvent.QuizComplete -> onQuizComplete()
            }
        }
    }

    // Reset text field when moving to a new question
    LaunchedEffect(state.currentIndex) {
        textFieldValue = ""
    }

    val question = state.questions.getOrNull(state.currentIndex)
    val total = state.questions.size

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        if (state.survivalMode) "Question ${state.currentIndex + 1}"
                        else "Question ${state.currentIndex + 1} / $total"
                    )
                },
                actions = {
                    IconButton(onClick = onOpenTutorial) {
                        Icon(Icons.Default.Info, contentDescription = "Tutorial")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            if (state.survivalMode) {
                LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
            } else {
                LinearProgressIndicator(
                    progress = { if (total > 0) (state.currentIndex.toFloat() / total) else 0f },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            if (question != null) {
                SuggestionChip(
                    onClick = {},
                    label = { Text(question.tense.displayLabel) }
                )

                val subjectLabel = question.subject?.displayLabel ?: ""
                if (subjectLabel.isNotEmpty()) {
                    Text(
                        text = subjectLabel,
                        style = MaterialTheme.typography.headlineLarge
                    )
                }

                val promptText = when (question.tense) {
                    Tense.GERUND -> "What is the gerund of ${question.verb.infinitive}?"
                    Tense.PASSIVA_PRESENTE,
                    Tense.PASSIVA_PRETERITO_PERFEITO,
                    Tense.PASSIVA_PRETERITO_IMPERFEITO,
                    Tense.PASSIVA_FUTURO,
                    Tense.PASSIVA_CONDICIONAL -> "Voz passiva:"
                    else -> null
                }

                ElevatedCard(modifier = Modifier.fillMaxWidth()) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        if (promptText != null) {
                            Text(
                                text = promptText,
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                        Text(
                            text = question.verb.infinitive,
                            style = MaterialTheme.typography.headlineMedium
                        )
                        Text(
                            text = question.verb.english,
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }

                Spacer(modifier = Modifier.height(4.dp))

                if (quizMode == QuizMode.MULTIPLE_CHOICE) {
                    question.choices.forEach { choice ->
                        val buttonColor = when {
                            !state.isAnswerRevealed -> null
                            choice == question.correctAnswer -> Color(0xFF2E7D32)
                            choice == state.selectedAnswer -> Color(0xFFC62828)
                            else -> null
                        }
                        OutlinedButton(
                            onClick = { quizViewModel.selectAnswer(choice) },
                            enabled = !state.isAnswerRevealed,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = choice,
                                color = buttonColor ?: MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                } else {
                    if (!state.isAnswerRevealed) {
                        OutlinedTextField(
                            value = textFieldValue,
                            onValueChange = { textFieldValue = it },
                            label = { Text("Your answer") },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true
                        )
                        Button(
                            onClick = { quizViewModel.selectAnswer(textFieldValue.trim()) },
                            enabled = textFieldValue.isNotBlank(),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Submit")
                        }
                    }
                }

                // Feedback section
                if (state.isAnswerRevealed) {
                    val isCorrect = state.selectedAnswer?.equals(question.correctAnswer, ignoreCase = true) == true
                    Text(
                        text = if (isCorrect) "Correct!" else "Incorrect!",
                        color = if (isCorrect) Color(0xFF2E7D32) else Color(0xFFC62828),
                        style = MaterialTheme.typography.headlineSmall
                    )
                    if (!isCorrect) {
                        Text(
                            text = "The correct answer is: ${question.correctAnswer}",
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    val isLastQuestion = !state.survivalMode && state.currentIndex >= total - 1
                    val endingSurvival = state.survivalMode && state.answers.lastOrNull()?.wasCorrect == false
                    Button(
                        onClick = { quizViewModel.nextQuestion() },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(if (isLastQuestion || endingSurvival) "See Results" else "Next")
                    }
                }
            }
        }
    }
}
