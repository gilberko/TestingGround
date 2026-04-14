package com.example.app2.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.app2.data.model.QuizDirection
import com.example.app2.data.model.WordType
import com.example.app2.quiz.MemorizePhase
import com.example.app2.quiz.MemorizeVocabViewModel

private fun WordType.label() = when (this) {
    WordType.NOUN -> "Noun"
    WordType.VERB -> "Verb"
    WordType.ADJECTIVE -> "Adjective"
    WordType.ADVERB -> "Adverb"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MemorizeVocabScreen(
    direction: QuizDirection,
    onBack: () -> Unit,
    viewModel: MemorizeVocabViewModel = viewModel()
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(direction) {
        viewModel.start(direction)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        when (state.phase) {
                            MemorizePhase.MEMORIZING -> "Memorize These Words"
                            MemorizePhase.QUIZZING -> "Quiz Time!"
                            MemorizePhase.RESULTS -> "Results"
                        }
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
        when (state.phase) {
            MemorizePhase.MEMORIZING -> {
                val word = state.words.getOrNull(state.currentWordIndex)
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .padding(horizontal = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Word ${state.currentWordIndex + 1} of ${state.words.size}",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    LinearProgressIndicator(
                        progress = { (state.currentWordIndex.toFloat() / state.words.size.coerceAtLeast(1)) },
                        modifier = Modifier.fillMaxWidth()
                    )

                    if (word != null) {
                        SuggestionChip(onClick = {}, label = { Text(word.type.label()) })

                        ElevatedCard(modifier = Modifier.fillMaxWidth()) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(24.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Text(
                                    text = word.pt,
                                    style = MaterialTheme.typography.displaySmall,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Center
                                )
                                Text(
                                    text = word.en,
                                    style = MaterialTheme.typography.headlineSmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Box(
                        modifier = Modifier
                            .size(80.dp)
                            .background(MaterialTheme.colorScheme.primaryContainer, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "${state.countdownSeconds}",
                            style = MaterialTheme.typography.displaySmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                    Text(
                        text = "seconds remaining",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        repeat(state.words.size) { i ->
                            Box(
                                modifier = Modifier
                                    .size(10.dp)
                                    .background(
                                        if (i == state.currentWordIndex)
                                            MaterialTheme.colorScheme.primary
                                        else
                                            MaterialTheme.colorScheme.outlineVariant,
                                        CircleShape
                                    )
                            )
                        }
                    }
                }
            }

            MemorizePhase.QUIZZING -> {
                val question = state.questions.getOrNull(state.currentWordIndex)
                val total = state.questions.size
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = "Question ${state.currentWordIndex + 1} / $total",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    LinearProgressIndicator(
                        progress = { if (total > 0) state.currentWordIndex.toFloat() / total else 0f },
                        modifier = Modifier.fillMaxWidth()
                    )

                    if (question != null) {
                        SuggestionChip(onClick = {}, label = { Text(question.word.type.label()) })

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
                            val isLast = state.currentWordIndex >= total - 1
                            Button(
                                onClick = { viewModel.nextQuestion() },
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(if (isLast) "See Results" else "Next")
                            }
                        }
                    }
                }
            }

            MemorizePhase.RESULTS -> {
                val score = state.score
                val total = state.questions.size
                val message = when {
                    score == total -> "Perfeito! Excelente trabalho!"
                    score >= total * 0.8 -> "Muito bem! Keep it up!"
                    score >= total * 0.6 -> "Bom trabalho! Keep practicing!"
                    else -> "Continue a praticar! You'll get there!"
                }

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    item {
                        Spacer(modifier = Modifier.height(8.dp))
                        ElevatedCard(modifier = Modifier.fillMaxWidth()) {
                            Column(
                                modifier = Modifier.padding(24.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "$score / $total",
                                    style = MaterialTheme.typography.displayLarge,
                                    textAlign = TextAlign.Center
                                )
                                Text(
                                    text = message,
                                    style = MaterialTheme.typography.bodyLarge,
                                    textAlign = TextAlign.Center,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            OutlinedButton(onClick = onBack, modifier = Modifier.weight(1f)) {
                                Text("Home")
                            }
                            Button(
                                onClick = { viewModel.replayLastQuiz() },
                                modifier = Modifier.weight(1f)
                            ) {
                                Text("Play Again")
                            }
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                    }

                    items(state.answers) { record ->
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            colors = CardDefaults.cardColors(
                                containerColor = if (record.wasCorrect)
                                    Color(0xFFE8F5E9) else Color(0xFFFFEBEE)
                            )
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        text = record.question.word.pt,
                                        style = MaterialTheme.typography.titleMedium
                                    )
                                    Text(
                                        text = if (record.wasCorrect) "✓" else "✗",
                                        style = MaterialTheme.typography.titleMedium,
                                        color = if (record.wasCorrect) Color(0xFF2E7D32) else Color(0xFFC62828)
                                    )
                                }
                                Text(
                                    text = record.question.word.en,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                                Text(
                                    text = "Correct: ${record.question.correctAnswer}",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = Color(0xFF2E7D32)
                                )
                                if (!record.wasCorrect) {
                                    Text(
                                        text = "Your answer: ${record.selectedAnswer}",
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = Color(0xFFC62828)
                                    )
                                }
                            }
                        }
                    }

                    item { Spacer(modifier = Modifier.height(16.dp)) }
                }
            }
        }
    }
}
