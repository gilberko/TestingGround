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
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.app2.quiz.PrepEvent
import com.example.app2.quiz.PrepViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrepQuizScreen(
    onQuizComplete: () -> Unit,
    prepViewModel: PrepViewModel = viewModel()
) {
    val state by prepViewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        prepViewModel.startNewQuiz()
        prepViewModel.events.collect { event ->
            when (event) {
                PrepEvent.QuizComplete -> onQuizComplete()
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
                ElevatedCard(modifier = Modifier.fillMaxWidth()) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        val parts = question.sentence.split("___")
                        val annotated = buildAnnotatedString {
                            parts.forEachIndexed { index, part ->
                                append(part)
                                if (index < parts.size - 1) {
                                    withStyle(SpanStyle(fontWeight = FontWeight.Bold, color = Color(0xFF1565C0))) {
                                        append("___")
                                    }
                                }
                            }
                        }
                        Text(
                            text = annotated,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }

                Spacer(modifier = Modifier.height(4.dp))

                question.choices.forEach { choice ->
                    val buttonColor = when {
                        !state.isAnswerRevealed -> null
                        choice == question.answer -> Color(0xFF2E7D32)
                        choice == state.selectedAnswer -> Color(0xFFC62828)
                        else -> null
                    }
                    OutlinedButton(
                        onClick = { prepViewModel.selectAnswer(choice) },
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
                    question.hint?.let { hint ->
                        Text(
                            text = hint,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    val isLastQuestion = state.currentIndex >= total - 1
                    Button(
                        onClick = { prepViewModel.nextQuestion() },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(if (isLastQuestion) "See Results" else "Next")
                    }
                }
            }
        }
    }
}
