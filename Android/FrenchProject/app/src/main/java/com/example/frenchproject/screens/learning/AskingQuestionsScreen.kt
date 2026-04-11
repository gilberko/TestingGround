package com.example.frenchproject.screens.learning

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.frenchproject.ui.theme.FrenchBlue
import com.example.frenchproject.ui.theme.FrenchNavy

private data class QuestionWord(
    val word: String,
    val meaning: String,
    val examples: List<Pair<String, String>>
)

private val questionWords = listOf(
    QuestionWord(
        word = "qui",
        meaning = "who / whom",
        examples = listOf(
            "Qui est-ce ?" to "Who is it?",
            "Qui parle français ?" to "Who speaks French?",
            "Tu parles à qui ?" to "Who are you talking to?",
            "Avec qui tu sors ?" to "Who are you going out with?"
        )
    ),
    QuestionWord(
        word = "que / qu'est-ce que",
        meaning = "what (as object)",
        examples = listOf(
            "Qu'est-ce que tu fais ?" to "What are you doing?",
            "Que voulez-vous ?" to "What do you want? (formal)",
            "Qu'est-ce que c'est ?" to "What is it / what is this?",
            "Tu fais quoi ?" to "What are you doing? (informal spoken)"
        )
    ),
    QuestionWord(
        word = "qu'est-ce qui",
        meaning = "what (as subject)",
        examples = listOf(
            "Qu'est-ce qui se passe ?" to "What is happening?",
            "Qu'est-ce qui t'inquiète ?" to "What is worrying you?"
        )
    ),
    QuestionWord(
        word = "où",
        meaning = "where",
        examples = listOf(
            "Où habites-tu ?" to "Where do you live?",
            "Où est la bibliothèque ?" to "Where is the library?",
            "Tu vas où ?" to "Where are you going? (informal)",
            "D'où viens-tu ?" to "Where are you from?"
        )
    ),
    QuestionWord(
        word = "quand",
        meaning = "when",
        examples = listOf(
            "Quand arrives-tu ?" to "When are you arriving?",
            "Quand est-ce que le cours commence ?" to "When does the class start?",
            "Tu pars quand ?" to "When are you leaving? (informal)",
            "Depuis quand habites-tu ici ?" to "How long have you lived here? (since when)"
        )
    ),
    QuestionWord(
        word = "pourquoi",
        meaning = "why",
        examples = listOf(
            "Pourquoi tu pleures ?" to "Why are you crying?",
            "Pourquoi est-ce qu'il est parti ?" to "Why did he leave?",
            "Pourquoi pas ?" to "Why not?",
            "Pourquoi apprends-tu le français ?" to "Why are you learning French?"
        )
    ),
    QuestionWord(
        word = "comment",
        meaning = "how / what (name)",
        examples = listOf(
            "Comment tu t'appelles ?" to "What is your name?",
            "Comment ça va ?" to "How are you?",
            "Comment est-ce qu'on dit « hello » en français ?" to "How do you say 'hello' in French?",
            "Comment tu vas au travail ?" to "How do you get to work?"
        )
    ),
    QuestionWord(
        word = "combien (de)",
        meaning = "how much / how many",
        examples = listOf(
            "Combien ça coûte ?" to "How much does it cost?",
            "Combien de frères as-tu ?" to "How many brothers do you have?",
            "Combien de temps faut-il ?" to "How much time is needed?",
            "C'est combien ?" to "How much is it? (very common in shops)"
        )
    ),
    QuestionWord(
        word = "quel / quelle / quels / quelles",
        meaning = "which / what (agrees with noun)",
        examples = listOf(
            "Quel est ton prénom ?" to "What is your first name? (m.)",
            "Quelle heure est-il ?" to "What time is it? (f.)",
            "Quels cours tu préfères ?" to "Which classes do you prefer? (m. pl.)",
            "Quelle est ta couleur préférée ?" to "What is your favorite color?"
        )
    ),
    QuestionWord(
        word = "lequel / laquelle / lesquels / lesquelles",
        meaning = "which one(s) — stands alone, no noun after it",
        examples = listOf(
            "Lequel veux-tu ?" to "Which one do you want? (m.)",
            "Laquelle préfères-tu ?" to "Which one do you prefer? (f.)",
            "Lesquels sont les meilleurs ?" to "Which ones are the best? (m. pl.)"
        )
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AskingQuestionsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Asking Questions", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = FrenchBlue)
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {

            // ── Three ways to ask questions ──────────────────────────────────
            item {
                Text(
                    text = "French has three main ways to turn a statement into a question. All are grammatically correct — they differ in register (formality).",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
            item { QSectionHeader("Three Ways to Ask a Question") }
            item {
                Card(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(2.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        // Method 1
                        Text("1. Rising intonation — informal spoken French", fontWeight = FontWeight.SemiBold, color = FrenchNavy, fontSize = 13.sp)
                        Spacer(modifier = Modifier.height(3.dp))
                        Text("Keep the word order of a statement and raise your voice at the end. Most common in conversation.", style = MaterialTheme.typography.bodySmall)
                        Spacer(modifier = Modifier.height(4.dp))
                        QExample("Tu parles français ?", "You speak French? / Do you speak French?")
                        QExample("Elle habite ici ?", "She lives here?")

                        Spacer(modifier = Modifier.height(10.dp))
                        HorizontalDivider(color = Color(0xFFE0E0E0))
                        Spacer(modifier = Modifier.height(10.dp))

                        // Method 2
                        Text("2. Est-ce que — neutral, very common", fontWeight = FontWeight.SemiBold, color = FrenchNavy, fontSize = 13.sp)
                        Spacer(modifier = Modifier.height(3.dp))
                        Text("Add est-ce que (or est-ce qu' before a vowel) before the statement. No word order change needed.", style = MaterialTheme.typography.bodySmall)
                        Spacer(modifier = Modifier.height(4.dp))
                        QExample("Est-ce que tu parles français ?", "Do you speak French?")
                        QExample("Est-ce qu'elle habite ici ?", "Does she live here?")
                        QExample("Est-ce que vous avez une table ?", "Do you have a table?")

                        Spacer(modifier = Modifier.height(10.dp))
                        HorizontalDivider(color = Color(0xFFE0E0E0))
                        Spacer(modifier = Modifier.height(10.dp))

                        // Method 3
                        Text("3. Inversion — formal / written French", fontWeight = FontWeight.SemiBold, color = FrenchNavy, fontSize = 13.sp)
                        Spacer(modifier = Modifier.height(3.dp))
                        Text("Swap the subject pronoun and verb, joined by a hyphen. If the verb ends in a vowel and the pronoun is il/elle/on, add -t- between them.", style = MaterialTheme.typography.bodySmall)
                        Spacer(modifier = Modifier.height(4.dp))
                        QExample("Parlez-vous français ?", "Do you speak French?")
                        QExample("Habite-t-elle ici ?", "Does she live here? (-t- added)")
                        QExample("Avez-vous une réservation ?", "Do you have a reservation?")
                    }
                }
            }

            // ── Question words ───────────────────────────────────────────────
            item {
                Spacer(modifier = Modifier.height(12.dp))
                QSectionHeader("Question Words")
            }
            item {
                Text(
                    text = "Question words can be combined with any of the three question forms above. The most versatile approach is: question word + est-ce que + subject + verb.",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }
            items(questionWords.size) { i -> QuestionWordCard(questionWords[i]) }

            // ── Quick tips ───────────────────────────────────────────────────
            item {
                Spacer(modifier = Modifier.height(12.dp))
                QSectionHeader("Useful Tips")
            }
            item {
                Card(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFE8EDF8)),
                    elevation = CardDefaults.cardElevation(2.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        TipRow("n'est-ce pas ?", "tag question — \"isn't it? / right?\" — added to the end of any statement")
                        Spacer(modifier = Modifier.height(6.dp))
                        QExample("Il fait beau, n'est-ce pas ?", "The weather is nice, isn't it?")
                        Spacer(modifier = Modifier.height(8.dp))
                        TipRow("non ?", "informal tag — simply add non ? at the end")
                        Spacer(modifier = Modifier.height(6.dp))
                        QExample("Tu viens, non ?", "You're coming, right?")
                        Spacer(modifier = Modifier.height(8.dp))
                        TipRow("quoi ?", "informal \"what?\" used at the end of a sentence or alone")
                        Spacer(modifier = Modifier.height(6.dp))
                        QExample("Tu fais quoi ce soir ?", "What are you doing tonight?")
                    }
                }
            }

            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}

@Composable
private fun QSectionHeader(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold,
        color = FrenchBlue,
        modifier = Modifier.padding(bottom = 4.dp)
    )
    HorizontalDivider(color = FrenchBlue.copy(alpha = 0.4f))
    Spacer(modifier = Modifier.height(4.dp))
}

@Composable
private fun QuestionWordCard(qw: QuestionWord) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row {
                Text(
                    text = qw.word,
                    fontWeight = FontWeight.Bold,
                    color = FrenchBlue,
                    fontSize = 16.sp,
                    modifier = Modifier.width(180.dp)
                )
                Text(
                    text = qw.meaning,
                    style = MaterialTheme.typography.bodySmall,
                    color = FrenchNavy,
                    fontStyle = FontStyle.Italic
                )
            }
            Spacer(modifier = Modifier.height(6.dp))
            HorizontalDivider(color = FrenchBlue.copy(alpha = 0.2f))
            Spacer(modifier = Modifier.height(6.dp))
            qw.examples.forEach { (fr, en) ->
                QExample(fr, en)
            }
        }
    }
}

@Composable
private fun QExample(french: String, english: String) {
    Text(
        text = french,
        fontWeight = FontWeight.Medium,
        color = FrenchBlue,
        fontSize = 13.sp
    )
    Text(
        text = "→ $english",
        style = MaterialTheme.typography.bodySmall,
        fontStyle = FontStyle.Italic,
        color = FrenchNavy,
        modifier = Modifier.padding(bottom = 3.dp)
    )
}

@Composable
private fun TipRow(term: String, explanation: String) {
    Row {
        Text(
            text = term,
            fontWeight = FontWeight.Bold,
            color = FrenchBlue,
            fontSize = 13.sp,
            modifier = Modifier.width(110.dp)
        )
        Text(
            text = explanation,
            style = MaterialTheme.typography.bodySmall,
            color = FrenchNavy,
            modifier = Modifier.weight(1f)
        )
    }
}
