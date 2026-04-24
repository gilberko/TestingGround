package com.example.russianapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

private data class QWord(val russian: String, val english: String, val notes: String)
private data class QCaseRow(val case: String, val form: String, val example: String, val translation: String)
private data class QAnswerEx(val question: String, val questionEn: String, val answer: String, val answerEn: String, val caseNote: String)
private data class QAdverb(val word: String, val english: String, val example: String, val answerEx: String)

private val questionWords = listOf(
    QWord("кто?", "who?", "Nominative — subject"),
    QWord("что?", "what?", "Nom/Acc — subject or object"),
    QWord("когда?", "when?", "adverb — no case"),
    QWord("зачем?", "why? / what for?", "purpose — adverb"),
    QWord("почему?", "why?", "reason/cause — adverb"),
    QWord("как?", "how?", "manner — adverb"),
    QWord("кого?", "whom?", "Accusative / Genitive"),
    QWord("кому?", "to whom?", "Dative"),
    QWord("от кого?", "from whom?", "Genitive (от + Gen)"),
    QWord("с кем?", "with whom?", "Instrumental (с + Inst)"),
    QWord("с чем?", "with what?", "Instrumental (с + Inst)"),
    QWord("куда?", "to where?", "motion / direction"),
    QWord("откуда?", "from where?", "origin"),
    QWord("в какую сторону?", "in what direction?", "Accusative (в + Acc)")
)

private val ktoForms = listOf(
    QCaseRow("Nominative", "кто", "Кто это сделал?", "Who did it?"),
    QCaseRow("Genitive", "кого", "Кого ты боишься?", "Whom are you afraid of?"),
    QCaseRow("Dative", "кому", "Кому ты позвонил?", "Whom did you call?"),
    QCaseRow("Accusative", "кого", "Кого ты видел?", "Whom did you see?"),
    QCaseRow("Instrumental", "с кем", "С кем ты пошёл?", "With whom did you go?"),
    QCaseRow("Prepositional", "о ком", "О ком ты думаешь?", "About whom are you thinking?")
)

private val chtoForms = listOf(
    QCaseRow("Nominative", "что", "Что случилось?", "What happened?"),
    QCaseRow("Genitive", "чего", "Чего ты хочешь?", "What do you want?"),
    QCaseRow("Dative", "чему", "Чему ты учишься?", "What are you studying?"),
    QCaseRow("Accusative", "что", "Что ты читаешь?", "What are you reading?"),
    QCaseRow("Instrumental", "с чем", "С чем ты ел?", "What did you eat it with?"),
    QCaseRow("Prepositional", "о чём", "О чём ты думаешь?", "What are you thinking about?")
)

private val answerExamples = listOf(
    QAnswerEx(
        "Кому ты звонил?", "Whom did you call?",
        "Я звонил другу.", "I called my friend.",
        "другу — Dative (звонить takes Dative)"
    ),
    QAnswerEx(
        "Кто это сделал?", "Who did it?",
        "Это сделал Иван.", "Ivan did it.",
        "Иван — Nominative (subject)"
    ),
    QAnswerEx(
        "С кем ты пошёл?", "With whom did you go?",
        "Я пошёл с другом.", "I went with a friend.",
        "с другом — с + Instrumental"
    ),
    QAnswerEx(
        "Чего ты хочешь?", "What do you want?",
        "Я хочу воды.", "I want water.",
        "воды — Genitive (хотеть takes Genitive)"
    )
)

private val adverbWords = listOf(
    QAdverb("когда?", "when?", "Когда ты пришёл?", "В три часа. (At three o'clock.)"),
    QAdverb("где?", "where?", "Где ты живёшь?", "В Москве. (In Moscow.)"),
    QAdverb("куда?", "to where?", "Куда ты идёшь?", "В магазин. (To the store.)"),
    QAdverb("откуда?", "from where?", "Откуда ты?", "Из Израиля. (From Israel.)"),
    QAdverb("как?", "how?", "Как ты себя чувствуешь?", "Хорошо. (Well.)"),
    QAdverb("зачем?", "why? / what for?", "Зачем ты пришёл?", "Чтобы поговорить. (To talk.)"),
    QAdverb("почему?", "why? (reason)", "Почему ты опоздал?", "Потому что были пробки. (Because of traffic.)")
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuestionsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Questions") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(innerPadding),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ) {
            item { QSectionHeader("Question Words") }
            item { QWordTableCard() }

            item { QSectionHeader("кто and Its Cases") }
            item { QDeclensionCard("кто — Who", ktoForms) }
            item {
                QNoteCard("Genitive and Accusative of кто are identical (кого). Context distinguishes them.")
            }

            item { QSectionHeader("что and Its Cases") }
            item { QDeclensionCard("что — What", chtoForms) }

            item { QSectionHeader("The Answer Follows the Case") }
            item {
                QNoteCard("The case of the question word tells you the case of the expected answer.")
            }
            answerExamples.forEach { ex ->
                item { QAnswerExampleCard(ex) }
            }

            item { QSectionHeader("Adverbial Question Words") }
            item {
                QNoteCard("These question words are adverbs — the answer is also an adverb or phrase. The question word itself does not carry a grammatical case.")
            }
            item { QAdverbTableCard() }

            item { Spacer(modifier = Modifier.height(16.dp)) }
        }
    }
}

@Composable
private fun QSectionHeader(title: String) {
    Column(modifier = Modifier.fillMaxWidth().padding(top = 16.dp, bottom = 4.dp)) {
        Text(title, style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.primary)
        HorizontalDivider(modifier = Modifier.padding(top = 4.dp), color = MaterialTheme.colorScheme.primary)
    }
}

@Composable
private fun QNoteCard(text: String) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text,
                style = MaterialTheme.typography.bodySmall,
                fontStyle = FontStyle.Italic,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun QWordTableCard() {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp)) {
                Text("Russian", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.2f))
                Text("English", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(0.9f))
                Text("Notes", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.5f))
            }
            HorizontalDivider(modifier = Modifier.padding(bottom = 4.dp))
            questionWords.forEachIndexed { i, word ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            if (i % 2 == 0) MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
                            else MaterialTheme.colorScheme.surface
                        )
                        .padding(vertical = 6.dp)
                ) {
                    Text(
                        word.russian,
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.weight(1.2f)
                    )
                    Text(
                        word.english,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.weight(0.9f)
                    )
                    Text(
                        word.notes,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.weight(1.5f)
                    )
                }
            }
        }
    }
}

@Composable
private fun QDeclensionCard(title: String, forms: List<QCaseRow>) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(title, style = MaterialTheme.typography.titleSmall, color = MaterialTheme.colorScheme.primary)
            Spacer(modifier = Modifier.height(8.dp))
            Row(modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp)) {
                Text("Case", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.2f))
                Text("Form", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(0.9f))
                Text("Example / Translation", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(2.0f))
            }
            HorizontalDivider(modifier = Modifier.padding(bottom = 4.dp))
            forms.forEachIndexed { i, row ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            if (i % 2 == 0) MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
                            else MaterialTheme.colorScheme.surface
                        )
                        .padding(vertical = 6.dp)
                ) {
                    Text(
                        row.case,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier.weight(1.2f)
                    )
                    Text(
                        row.form,
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.weight(0.9f)
                    )
                    Column(modifier = Modifier.weight(2.0f)) {
                        Text(row.example, style = MaterialTheme.typography.bodySmall)
                        Text(
                            row.translation,
                            style = MaterialTheme.typography.bodySmall,
                            fontStyle = FontStyle.Italic,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun QAnswerExampleCard(ex: QAnswerEx) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(ex.question, style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold)
            Text(
                ex.questionEn,
                style = MaterialTheme.typography.bodySmall,
                fontStyle = FontStyle.Italic,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                ex.answer,
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                ex.answerEn,
                style = MaterialTheme.typography.bodySmall,
                fontStyle = FontStyle.Italic,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                ex.caseNote,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.secondary
            )
        }
    }
}

@Composable
private fun QAdverbTableCard() {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp)) {
                Text("Word", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(0.9f))
                Text("English", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(0.9f))
                Text("Example / Answer", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(2.2f))
            }
            HorizontalDivider(modifier = Modifier.padding(bottom = 4.dp))
            adverbWords.forEachIndexed { i, adv ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            if (i % 2 == 0) MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
                            else MaterialTheme.colorScheme.surface
                        )
                        .padding(vertical = 6.dp)
                ) {
                    Text(
                        adv.word,
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.weight(0.9f)
                    )
                    Text(
                        adv.english,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.weight(0.9f)
                    )
                    Column(modifier = Modifier.weight(2.2f)) {
                        Text(adv.example, style = MaterialTheme.typography.bodySmall)
                        Text(
                            adv.answerEx,
                            style = MaterialTheme.typography.bodySmall,
                            fontStyle = FontStyle.Italic,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }
    }
}
