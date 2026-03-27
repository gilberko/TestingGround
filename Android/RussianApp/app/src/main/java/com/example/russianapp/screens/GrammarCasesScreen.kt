package com.example.russianapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp

private data class RussianCase(
    val englishName: String,
    val cyrillicName: String,
    val questionRu: String,
    val questionEn: String,
    val usage: String,
    val example: String
)

private val russianCases = listOf(
    RussianCase(
        englishName  = "Nominative",
        cyrillicName = "Именительный",
        questionRu   = "Кто? Что?",
        questionEn   = "Who? What?",
        usage        = "Subject of the sentence — who or what is performing the action.",
        example      = "Кот спит. (The cat is sleeping.)"
    ),
    RussianCase(
        englishName  = "Genitive",
        cyrillicName = "Родительный",
        questionRu   = "Кого? Чего?",
        questionEn   = "Of whom? Of what?",
        usage        = "Possession, absence, or after numbers (2–4 → genitive singular; 5+ → genitive plural).",
        example      = "У меня нет кота. (I don't have a cat.)"
    ),
    RussianCase(
        englishName  = "Dative",
        cyrillicName = "Дательный",
        questionRu   = "Кому? Чему?",
        questionEn   = "To whom? To what?",
        usage        = "Indirect object — to whom or for whom the action is done.",
        example      = "Я даю книгу другу. (I give the book to a friend.)"
    ),
    RussianCase(
        englishName  = "Accusative",
        cyrillicName = "Винительный",
        questionRu   = "Кого? Что?",
        questionEn   = "Whom? What?",
        usage        = "Direct object — the entity directly receiving the action.",
        example      = "Я читаю книгу. (I am reading a book.)"
    ),
    RussianCase(
        englishName  = "Instrumental",
        cyrillicName = "Творительный",
        questionRu   = "Кем? Чем?",
        questionEn   = "With whom? With what?",
        usage        = "Means, instrument, or accompaniment — by means of, with, or alongside.",
        example      = "Я пишу ручкой. (I write with a pen.)"
    ),
    RussianCase(
        englishName  = "Prepositional",
        cyrillicName = "Предложный",
        questionRu   = "О ком? О чём?",
        questionEn   = "About whom? About what?",
        usage        = "Always used after specific prepositions: в (in), на (on), о (about).",
        example      = "Я думаю о доме. (I am thinking about home.)"
    )
)

@Composable
private fun CaseCard(russianCase: RussianCase) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "${russianCase.englishName} — ${russianCase.cyrillicName}",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "${russianCase.questionRu}  /  ${russianCase.questionEn}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.secondary
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = russianCase.usage,
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = russianCase.example,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontStyle = FontStyle.Italic
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GrammarCasesScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Grammar: Cases") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
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
            item {
                Text(
                    text = "Russian has 6 grammatical cases. Each changes noun endings depending on the word's role in the sentence. A helpful trick: each case answers specific questions.",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
            items(russianCases) { case ->
                CaseCard(case)
            }
        }
    }
}
