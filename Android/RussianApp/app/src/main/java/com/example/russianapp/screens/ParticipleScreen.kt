package com.example.russianapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
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

private data class PartSection(
    val header: String,
    val russianTerm: String,
    val formationTitle: String,
    val formationLines: List<String>,
    val mfnpHeader: List<String>,
    val mfnpValues: List<String>,
    val usageNote: String,
    val examples: List<String>
)

private val participleSections = listOf(
    PartSection(
        header = "Present Active Participles",
        russianTerm = "Действительные причастия настоящего времени",
        formationTitle = "Formation: present stem + suffix",
        formationLines = listOf(
            "Type 1 verbs (-ать/-ять)  →  -ущий / -ющий",
            "  читать (to read)  →  читающий",
            "  писать (to write) →  пишущий",
            "Type 2 verbs (-ить/-еть)  →  -ащий / -ящий",
            "  говорить (to speak) →  говорящий",
            "  любить (to love)    →  любящий"
        ),
        mfnpHeader = listOf("Masc.", "Fem.", "Neut.", "Plural"),
        mfnpValues = listOf("читающий", "читающая", "читающее", "читающие"),
        usageNote = "Replaces a который + present verb clause. Only imperfective verbs have present active participles. Declines like an adjective across all 6 cases.",
        examples = listOf(
            "человек, читающий книгу  —  the person reading a book",
            "студент, говорящий по-русски  —  the student speaking Russian",
            "машина, едущая быстро  —  the car going fast"
        )
    ),
    PartSection(
        header = "Past Active Participles",
        russianTerm = "Действительные причастия прошедшего времени",
        formationTitle = "Formation: infinitive stem + suffix",
        formationLines = listOf(
            "Vowel stem (ends in а/е/и before -ть)  →  -вший",
            "  читать  →  читавший",
            "  написать  →  написавший",
            "  купить  →  купивший",
            "Consonant stem  →  -ший",
            "  нести (to carry)  →  нёсший",
            "  идти (to go)  →  шедший"
        ),
        mfnpHeader = listOf("Masc.", "Fem.", "Neut.", "Plural"),
        mfnpValues = listOf("читавший", "читавшая", "читавшее", "читавшие"),
        usageNote = "Describes a subject that performed an action in the past. Works for both imperfective and perfective verbs. Declines like an adjective.",
        examples = listOf(
            "студент, написавший письмо  —  the student who wrote the letter",
            "женщина, купившая книгу  —  the woman who bought the book",
            "солдат, вернувшийся домой  —  the soldier who returned home"
        )
    ),
    PartSection(
        header = "Present Passive Participles",
        russianTerm = "Страдательные причастия настоящего времени",
        formationTitle = "Formation: 1st person plural stem + -мый",
        formationLines = listOf(
            "Type 1: take мы form, replace -м with -мый",
            "  читаем  →  читаемый",
            "  знаем   →  знаемый",
            "Type 2: take мы form, replace -м with -мый",
            "  любим   →  любимый",
            "  видим   →  видимый"
        ),
        mfnpHeader = listOf("Masc.", "Fem.", "Neut.", "Plural"),
        mfnpValues = listOf("читаемый", "читаемая", "читаемое", "читаемые"),
        usageNote = "Only transitive imperfective verbs have present passive participles. Common in literary and formal written registers; rare in everyday speech. Declines like an adjective.",
        examples = listOf(
            "книга, читаемая студентами  —  the book being read by students",
            "язык, любимый миллионами  —  the language loved by millions",
            "задача, решаемая командой  —  the task being solved by the team"
        )
    ),
    PartSection(
        header = "Past Passive Participles",
        russianTerm = "Страдательные причастия прошедшего времени",
        formationTitle = "Formation: from perfective infinitive stem",
        formationLines = listOf(
            "-ать / -ять verbs  →  -анный / -янный",
            "  написать  →  написанный",
            "  сыграть   →  сыгранный",
            "-ить / -еть verbs  →  -енный / -ённый",
            "  решить    →  решённый",
            "  построить →  построенный",
            "  увидеть   →  увиденный",
            "Irregular  →  -тый",
            "  открыть   →  открытый",
            "  взять     →  взятый",
            "  забыть    →  забытый",
            "",
            "Short form (used predicatively, does not decline):",
            "  -анный → -ан/-ана/-ано/-аны",
            "  -енный → -ен/-ена/-ено/-ены",
            "  -тый   → -т/-та/-то/-ты"
        ),
        mfnpHeader = listOf("Masc.", "Fem.", "Neut.", "Plural"),
        mfnpValues = listOf("написанный", "написанная", "написанное", "написанные"),
        usageNote = "The most common participle type. Used in both attributive position (before/after noun) and predicatively as a short form. Short forms are widely used in formal and written Russian.",
        examples = listOf(
            "письмо, написанное им  —  the letter written by him",
            "книга, прочитанная вчера  —  the book read yesterday",
            "Книга прочитана.  —  The book has been read. (short form)",
            "Дверь открыта.  —  The door is open. (short form of открытый)"
        )
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ParticipleScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Participles") },
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
            items(participleSections) { section ->
                PartSectionHeader(section.header)
                PartCard(title = section.russianTerm) {
                    // Formation
                    Text(
                        text = section.formationTitle,
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    section.formationLines.forEach { line ->
                        if (line.isBlank()) {
                            Spacer(modifier = Modifier.height(6.dp))
                        } else {
                            Text(
                                text = line,
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))
                    HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
                    Spacer(modifier = Modifier.height(8.dp))

                    // M/F/N/Pl table
                    Row(modifier = Modifier.fillMaxWidth()) {
                        section.mfnpHeader.forEach { label ->
                            Text(
                                text = label,
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(2.dp))
                    Row(modifier = Modifier.fillMaxWidth()) {
                        section.mfnpValues.forEach { value ->
                            Text(
                                text = value,
                                style = MaterialTheme.typography.bodySmall,
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))
                    HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
                    Spacer(modifier = Modifier.height(8.dp))

                    // Usage note
                    Text(
                        text = section.usageNote,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Examples
                    Text(
                        text = "Examples:",
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    section.examples.forEach { example ->
                        Text(
                            text = "• $example",
                            style = MaterialTheme.typography.bodySmall,
                            fontStyle = FontStyle.Italic,
                            modifier = Modifier.padding(start = 4.dp, bottom = 2.dp)
                        )
                    }
                }
            }
            item { Spacer(modifier = Modifier.height(16.dp)) }
        }
    }
}

@Composable
private fun PartSectionHeader(title: String) {
    Column(modifier = Modifier.fillMaxWidth().padding(top = 16.dp, bottom = 4.dp)) {
        Text(title, style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.primary)
        HorizontalDivider(modifier = Modifier.padding(top = 4.dp), color = MaterialTheme.colorScheme.primary)
    }
}

@Composable
private fun PartCard(title: String, content: @Composable ColumnScope.() -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(title, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant, fontStyle = FontStyle.Italic)
            Spacer(modifier = Modifier.height(8.dp))
            content()
        }
    }
}
