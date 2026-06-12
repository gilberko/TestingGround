package com.example.russianapp.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.russianapp.R
import com.example.russianapp.data.NounQuizEntry
import com.example.russianapp.data.NounsQuizFile
import com.example.russianapp.data.QuizQuestion
import com.example.russianapp.data.QuizState
import com.example.russianapp.data.allForms
import com.example.russianapp.viewmodel.ConfigViewModel
import com.example.russianapp.viewmodel.QuizMode
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.random.Random

private fun buildNounQuestions(
    entries: List<NounQuizEntry>,
    count: Int,
    rng: Random = Random.Default
): List<QuizQuestion> {
    val shuffled = entries.shuffled(rng)
    val selected = shuffled.take(count.coerceAtMost(shuffled.size))
    return selected.map { entry ->
        val forms = entry.allForms()
        val targetIndex = rng.nextInt(forms.size)
        val (label, correct) = forms[targetIndex]
        val distractors = forms
            .filterIndexed { i, (_, form) -> i != targetIndex && form != correct }
            .shuffled(rng)
            .take(3)
            .map { (_, form) -> form }
            .toMutableList()
        if (distractors.size < 3) {
            val extra = shuffled
                .filter { it.id != entry.id }
                .flatMap { it.allForms().map { (_, f) -> f } }
                .filter { it != correct && it !in distractors }
                .shuffled(rng)
            distractors.addAll(extra.take(3 - distractors.size))
        }
        QuizQuestion(
            promptWord = entry.nomSg,
            promptLabel = "${entry.gender} — ${entry.english}",
            questionText = "What is the $label form?",
            correctAnswer = correct,
            choices = (distractors.take(3) + correct).shuffled(rng)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NounCasesQuizScreen(configViewModel: ConfigViewModel, onBack: () -> Unit) {
    val context = LocalContext.current
    val numQuestions by configViewModel.numQuestions.collectAsState()
    val quizMode by configViewModel.quizMode.collectAsState()

    val rawJson by produceState<String?>(initialValue = null) {
        value = withContext(Dispatchers.IO) {
            context.resources.openRawResource(R.raw.nouns_quiz)
                .bufferedReader(Charsets.UTF_8).use { it.readText() }
        }
    }

    var quizState by remember { mutableStateOf(QuizState()) }
    var textInput by remember(quizState.currentIndex) { mutableStateOf("") }

    if (rawJson != null && quizState.questions.isEmpty()) {
        val file = Gson().fromJson(rawJson, NounsQuizFile::class.java)
        quizState = QuizState(questions = buildNounQuestions(file.nouns, numQuestions))
    }

    fun rebuild() {
        if (rawJson != null) {
            val file = Gson().fromJson(rawJson, NounsQuizFile::class.java)
            quizState = QuizState(questions = buildNounQuestions(file.nouns, numQuestions))
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Nouns - Cases Quiz") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when {
                rawJson == null -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                quizState.isFinished -> {
                    NounQuizScoreScreen(
                        score = quizState.score,
                        total = quizState.questions.size,
                        onRetry = { rebuild() },
                        onBack = onBack,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                else -> {
                    val question = quizState.currentQuestion
                    if (question != null) {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(horizontal = 16.dp),
                            contentPadding = PaddingValues(vertical = 16.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            item {
                                Text(
                                    text = "Question ${quizState.currentIndex + 1} of ${quizState.questions.size}",
                                    style = MaterialTheme.typography.labelMedium,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                            item {
                                Text(
                                    text = question.promptWord,
                                    style = MaterialTheme.typography.displaySmall,
                                    color = MaterialTheme.colorScheme.primary,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.fillMaxWidth()
                                )
                                Text(
                                    text = question.promptLabel,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.fillMaxWidth()
                                )
                            }
                            item {
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = question.questionText,
                                    style = MaterialTheme.typography.titleMedium,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.fillMaxWidth()
                                )
                            }
                            if (quizMode == QuizMode.FREE_TEXT) {
                                item {
                                    val answered = quizState.isAnswered
                                    OutlinedTextField(
                                        value = textInput,
                                        onValueChange = { if (!answered) textInput = it },
                                        label = { Text("Your answer") },
                                        modifier = Modifier.fillMaxWidth(),
                                        enabled = !answered
                                    )
                                }
                                item {
                                    val answered = quizState.isAnswered
                                    Button(
                                        onClick = {
                                            if (!answered && textInput.isNotBlank()) {
                                                val isCorrect = textInput.trim().equals(question.correctAnswer, ignoreCase = true)
                                                val newScore = if (isCorrect) quizState.score + 1 else quizState.score
                                                quizState = quizState.copy(selectedAnswer = textInput.trim(), score = newScore)
                                            }
                                        },
                                        enabled = !answered && textInput.isNotBlank(),
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        Text("Submit")
                                    }
                                }
                                if (quizState.isAnswered) {
                                    item {
                                        val isCorrect = quizState.selectedAnswer?.trim()?.equals(question.correctAnswer, ignoreCase = true) == true
                                        Text(
                                            text = if (isCorrect) "Correct!" else "Wrong! Answer: ${question.correctAnswer}",
                                            color = if (isCorrect) Color(0xFF4CAF50) else Color(0xFFF44336),
                                            style = MaterialTheme.typography.titleMedium,
                                            fontWeight = FontWeight.Bold,
                                            textAlign = TextAlign.Center,
                                            modifier = Modifier.fillMaxWidth()
                                        )
                                    }
                                }
                            } else {
                                items(question.choices.size) { i ->
                                    val choice = question.choices[i]
                                    val answered = quizState.isAnswered
                                    val isCorrect = choice == question.correctAnswer
                                    val isSelected = choice == quizState.selectedAnswer
                                    val containerColor = when {
                                        !answered -> MaterialTheme.colorScheme.surface
                                        isCorrect -> Color(0xFF4CAF50)
                                        isSelected -> Color(0xFFF44336)
                                        else -> MaterialTheme.colorScheme.surface
                                    }
                                    val contentColor = when {
                                        answered && (isCorrect || isSelected) -> Color.White
                                        else -> MaterialTheme.colorScheme.onSurface
                                    }
                                    OutlinedButton(
                                        onClick = {
                                            if (!answered) {
                                                val newScore = if (isCorrect) quizState.score + 1 else quizState.score
                                                quizState = quizState.copy(
                                                    selectedAnswer = choice,
                                                    score = newScore
                                                )
                                            }
                                        },
                                        modifier = Modifier.fillMaxWidth(),
                                        colors = ButtonDefaults.outlinedButtonColors(
                                            containerColor = containerColor,
                                            contentColor = contentColor
                                        )
                                    ) {
                                        Text(choice, style = MaterialTheme.typography.bodyLarge)
                                    }
                                }
                            }
                            if (quizState.isAnswered) {
                                item {
                                    Button(
                                        onClick = {
                                            val nextIndex = quizState.currentIndex + 1
                                            quizState = if (nextIndex >= quizState.questions.size) {
                                                quizState.copy(isFinished = true)
                                            } else {
                                                quizState.copy(
                                                    currentIndex = nextIndex,
                                                    selectedAnswer = null
                                                )
                                            }
                                        },
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        Text("Next →")
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun NounQuizScoreScreen(
    score: Int,
    total: Int,
    onRetry: () -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Quiz Complete!",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "$score / $total correct",
            style = MaterialTheme.typography.displaySmall,
            color = if (score >= total / 2) Color(0xFF4CAF50) else MaterialTheme.colorScheme.error
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = onRetry, modifier = Modifier.fillMaxWidth()) {
            Text("Try Again")
        }
        OutlinedButton(onClick = onBack, modifier = Modifier.fillMaxWidth()) {
            Text("Back")
        }
    }
}
