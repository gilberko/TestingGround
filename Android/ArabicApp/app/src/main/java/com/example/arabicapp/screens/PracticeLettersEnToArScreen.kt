package com.example.arabicapp.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

private data class EnToArQuestion(
    val letter: ArabicLetter,
    val formLabel: String,
    val correctForm: String,
    val options: List<Pair<String, ArabicLetter>>,
    val correctIndex: Int
)

private fun generateEnToArQuiz(): List<EnToArQuestion> {
    val selected = ALL_LETTERS.shuffled().take(10)
    return selected.map { letter ->
        val positions = if (letter.isNonConnector) {
            listOf(
                Pair("Middle of word", letter.medialForm),
                Pair("End of word", letter.finalForm)
            )
        } else {
            listOf(
                Pair("Beginning of word", letter.initialForm),
                Pair("Middle of word", letter.medialForm),
                Pair("End of word", letter.finalForm)
            )
        }
        val (formLabel, correctForm) = positions.random()
        val distractors = ALL_LETTERS.filter { it.name != letter.name }.shuffled().take(3)
        val distractorForms = distractors.map { d ->
            val form = when (formLabel) {
                "Beginning of word" -> d.initialForm
                "Middle of word" -> d.medialForm
                else -> d.finalForm
            }
            Pair(form, d)
        }
        val allOptions = (listOf(Pair(correctForm, letter)) + distractorForms).shuffled()
        val correctIndex = allOptions.indexOfFirst { it.second.name == letter.name }
        EnToArQuestion(letter, formLabel, correctForm, allOptions, correctIndex)
    }
}

@Composable
fun PracticeLettersEnToArScreen() {
    var questions by remember { mutableStateOf(generateEnToArQuiz()) }
    var currentIndex by remember { mutableStateOf(0) }
    var selectedAnswer by remember { mutableStateOf(-1) }
    var score by remember { mutableStateOf(0) }
    var showResults by remember { mutableStateOf(false) }

    if (showResults) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Score",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "$score / 10",
                style = MaterialTheme.typography.displayMedium,
                fontWeight = FontWeight.Bold,
                color = if (score >= 7) Color(0xFF2E7D32) else MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = when {
                    score == 10 -> "Perfect!"
                    score >= 7 -> "Well done!"
                    score >= 5 -> "Keep practicing!"
                    else -> "Keep studying the letters!"
                },
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(32.dp))
            Button(
                onClick = {
                    questions = generateEnToArQuiz()
                    currentIndex = 0
                    selectedAnswer = -1
                    score = 0
                    showResults = false
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Try Again")
            }
        }
        return
    }

    val question = questions[currentIndex]
    val answered = selectedAnswer != -1
    val green = Color(0xFF2E7D32)
    val red = Color(0xFFC62828)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Question ${currentIndex + 1} of 10",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = question.letter.name,
                style = MaterialTheme.typography.displaySmall,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = question.formLabel,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(32.dp))
            question.options.forEachIndexed { index, (form, _) ->
                val containerColor = when {
                    !answered -> MaterialTheme.colorScheme.primary
                    index == question.correctIndex -> green
                    index == selectedAnswer -> red
                    else -> MaterialTheme.colorScheme.primary
                }
                Button(
                    onClick = {
                        if (!answered) {
                            selectedAnswer = index
                            if (index == question.correctIndex) score++
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = containerColor),
                    enabled = !answered || index == question.correctIndex || index == selectedAnswer
                ) {
                    Text(
                        text = form,
                        style = MaterialTheme.typography.displayLarge,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
        if (answered) {
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = {
                    if (currentIndex < 9) {
                        currentIndex++
                        selectedAnswer = -1
                    } else {
                        showResults = true
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(if (currentIndex < 9) "Next" else "See Results")
            }
        }
    }
}
