package com.example.arabicapp.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

private data class ArabicLetter(
    val isolated: String,
    val name: String,
    val sound: String,
    val initialForm: String,
    val medialForm: String,
    val finalForm: String,
    val isNonConnector: Boolean = false
)

private data class LetterQuestion(
    val letter: ArabicLetter,
    val formLabel: String,
    val displayChar: String,
    val options: List<ArabicLetter>,
    val correctIndex: Int
)

private val ALL_LETTERS = listOf(
    ArabicLetter("ا", "Alef", "ā / glottal stop", "ا", "ـا", "ـا", isNonConnector = true),
    ArabicLetter("ب", "Ba", "b", "بـ", "ـبـ", "ـب"),
    ArabicLetter("ت", "Ta", "t", "تـ", "ـتـ", "ـت"),
    ArabicLetter("ث", "Tha", "th → t or s", "ثـ", "ـثـ", "ـث"),
    ArabicLetter("ج", "Jim", "j", "جـ", "ـجـ", "ـج"),
    ArabicLetter("ح", "Ha", "ḥ (breathy, from throat)", "حـ", "ـحـ", "ـح"),
    ArabicLetter("خ", "Kha", "kh (like Scottish 'loch')", "خـ", "ـخـ", "ـخ"),
    ArabicLetter("د", "Dal", "d", "د", "ـد", "ـد", isNonConnector = true),
    ArabicLetter("ذ", "Dhal", "dh → d", "ذ", "ـذ", "ـذ", isNonConnector = true),
    ArabicLetter("ر", "Ra", "r (rolled/trilled)", "ر", "ـر", "ـر", isNonConnector = true),
    ArabicLetter("ز", "Zain", "z", "ز", "ـز", "ـز", isNonConnector = true),
    ArabicLetter("س", "Sin", "s", "سـ", "ـسـ", "ـس"),
    ArabicLetter("ش", "Shin", "sh", "شـ", "ـشـ", "ـش"),
    ArabicLetter("ص", "Sad", "ṣ (emphatic s)", "صـ", "ـصـ", "ـص"),
    ArabicLetter("ض", "Dad", "ḍ (emphatic d)", "ضـ", "ـضـ", "ـض"),
    ArabicLetter("ط", "Ta", "ṭ (emphatic t)", "طـ", "ـطـ", "ـط"),
    ArabicLetter("ظ", "Dha", "ẓ → emphatic d", "ظـ", "ـظـ", "ـظ"),
    ArabicLetter("ع", "Ain", "ʕ (voiced pharyngeal)", "عـ", "ـعـ", "ـع"),
    ArabicLetter("غ", "Ghain", "gh (gargled r)", "غـ", "ـغـ", "ـغ"),
    ArabicLetter("ف", "Fa", "f", "فـ", "ـفـ", "ـف"),
    ArabicLetter("ق", "Qaf", "q → glottal stop", "قـ", "ـقـ", "ـق"),
    ArabicLetter("ك", "Kaf", "k", "كـ", "ـكـ", "ـك"),
    ArabicLetter("ل", "Lam", "l", "لـ", "ـلـ", "ـل"),
    ArabicLetter("م", "Mim", "m", "مـ", "ـمـ", "ـم"),
    ArabicLetter("ن", "Nun", "n", "نـ", "ـنـ", "ـن"),
    ArabicLetter("ه", "Ha", "h", "هـ", "ـهـ", "ـه"),
    ArabicLetter("و", "Waw", "w", "و", "ـو", "ـو", isNonConnector = true),
    ArabicLetter("ي", "Ya", "y", "يـ", "ـيـ", "ـي")
)

private fun generateQuiz(): List<LetterQuestion> {
    val selected = ALL_LETTERS.shuffled().take(10)
    return selected.map { letter ->
        val forms = if (letter.isNonConnector) {
            listOf(
                Triple("Middle of word", letter.medialForm, "MEDIAL"),
                Triple("End of word", letter.finalForm, "FINAL")
            )
        } else {
            listOf(
                Triple("Beginning of word", letter.initialForm, "INITIAL"),
                Triple("Middle of word", letter.medialForm, "MEDIAL"),
                Triple("End of word", letter.finalForm, "FINAL")
            )
        }
        val (formLabel, displayChar, _) = forms.random()
        val distractors = ALL_LETTERS.filter { it.name != letter.name }.shuffled().take(3)
        val allOptions = (listOf(letter) + distractors).shuffled()
        val correctIndex = allOptions.indexOf(letter)
        LetterQuestion(letter, formLabel, displayChar, allOptions, correctIndex)
    }
}

@Composable
fun PracticeLettersScreen() {
    var questions by remember { mutableStateOf(generateQuiz()) }
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
                    questions = generateQuiz()
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
        Text(
            text = "Question ${currentIndex + 1} of 10",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = question.displayChar,
            style = MaterialTheme.typography.displayLarge,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = question.formLabel,
            style = MaterialTheme.typography.bodyMedium,
            fontStyle = FontStyle.Italic,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(modifier = Modifier.height(32.dp))
        question.options.forEachIndexed { index, letter ->
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
                Text("${letter.name} (${letter.sound})")
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
