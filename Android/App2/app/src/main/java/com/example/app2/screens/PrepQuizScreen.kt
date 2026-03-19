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

private val contractionArticles = mapOf(
    "ao" to "o", "à" to "a", "aos" to "os", "às" to "as",
    "do" to "o", "da" to "a", "dos" to "os", "das" to "as",
    "no" to "o", "na" to "a", "nos" to "os", "nas" to "as",
    "pelo" to "o", "pela" to "a", "pelos" to "os", "pelas" to "as",
    "num" to "um", "numa" to "uma", "nuns" to "uns", "numas" to "umas",
    "dum" to "um", "duma" to "uma", "duns" to "uns", "dumas" to "umas"
)

fun stripAbsorbedArticle(sentence: String, answer: String): String {
    val article = contractionArticles[answer] ?: return sentence
    val blankIdx = sentence.indexOf("___")
    if (blankIdx == -1) return sentence
    val afterBlank = sentence.substring(blankIdx + 3)
    val trimmed = afterBlank.trimStart()
    if (trimmed.startsWith("$article ")) {
        val stripped = trimmed.removePrefix("$article ")
        val leadingSpace = afterBlank.length - trimmed.length
        return sentence.substring(0, blankIdx + 3) + afterBlank.substring(0, leadingSpace) + stripped
    }
    return sentence
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrepQuizScreen(
    onQuizComplete: () -> Unit,
    onOpenTutorial: () -> Unit = {},
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
            TopAppBar(
                title = { Text("Question ${state.currentIndex + 1} / $total") },
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
                        val displaySentence = stripAbsorbedArticle(question.sentence, question.answer)
                        val parts = displaySentence.split("___")
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
