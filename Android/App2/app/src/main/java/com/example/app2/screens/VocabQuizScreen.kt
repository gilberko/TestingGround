package com.example.app2.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.example.app2.data.model.QuizDirection
import com.example.app2.data.model.WordType
import com.example.app2.quiz.ConfigViewModel
import com.example.app2.quiz.QuizLengthMode
import com.example.app2.quiz.VocabQuizEvent
import com.example.app2.quiz.VocabQuizViewModel

private fun WordType.displayLabel() = when (this) {
    WordType.NOUN -> "Noun"
    WordType.VERB -> "Verb"
    WordType.ADJECTIVE -> "Adjective"
    WordType.ADVERB -> "Adverb"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VocabQuizScreen(
    direction: QuizDirection,
    configViewModel: ConfigViewModel,
    onQuizComplete: () -> Unit,
    onBack: () -> Unit,
    viewModel: VocabQuizViewModel = viewModel()
) {
    val state by viewModel.state.collectAsState()
    val quizLengthMode by configViewModel.quizLengthMode.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.startNewQuiz(direction, survivalMode = quizLengthMode == QuizLengthMode.SURVIVAL)
        viewModel.events.collect { event ->
            when (event) {
                VocabQuizEvent.QuizComplete -> onQuizComplete()
            }
        }
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
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
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
                    label = { Text(question.word.type.displayLabel()) }
                )

                val promptLabel = if (direction == QuizDirection.EN_TO_PT)
                    "What is the Portuguese?" else "What is the English?"

                val displayWord = if (direction == QuizDirection.EN_TO_PT)
                    question.word.en else question.word.pt

                ElevatedCard(modifier = Modifier.fillMaxWidth()) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = promptLabel,
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = displayWord,
                            style = MaterialTheme.typography.headlineMedium
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
                        onClick = { viewModel.selectAnswer(choice) },
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
                    val isCorrect = state.selectedAnswer == question.correctAnswer
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
                        onClick = { viewModel.nextQuestion() },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(if (isLastQuestion || endingSurvival) "See Results" else "Next")
                    }
                }
            }
        }
    }
}
