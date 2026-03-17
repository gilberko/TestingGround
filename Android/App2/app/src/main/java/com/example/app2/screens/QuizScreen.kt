package com.example.app2.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.app2.quiz.QuizEvent
import com.example.app2.quiz.QuizViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizScreen(
    onQuizComplete: () -> Unit,
    quizViewModel: QuizViewModel = viewModel()
) {
    val state by quizViewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        quizViewModel.events.collect { event ->
            when (event) {
                QuizEvent.QuizComplete -> onQuizComplete()
            }
        }
    }

    val question = state.questions.getOrNull(state.currentIndex)
    val total = state.questions.size

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Question ${state.currentIndex + 1} / $total") })
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            LinearProgressIndicator(
                progress = { if (total > 0) (state.currentIndex.toFloat() / total) else 0f },
                modifier = Modifier.fillMaxWidth()
            )

            if (question != null) {
                SuggestionChip(
                    onClick = {},
                    label = { Text(question.tense.displayLabel) }
                )

                Text(
                    text = question.subject.displayLabel,
                    style = MaterialTheme.typography.headlineLarge
                )

                ElevatedCard(modifier = Modifier.fillMaxWidth()) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
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

                if (state.isAnswerRevealed) {
                    Spacer(modifier = Modifier.height(8.dp))
                    val isLastQuestion = state.currentIndex >= total - 1
                    Button(
                        onClick = { quizViewModel.nextQuestion() },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(if (isLastQuestion) "See Results" else "Next")
                    }
                }
            }
        }
    }
}
