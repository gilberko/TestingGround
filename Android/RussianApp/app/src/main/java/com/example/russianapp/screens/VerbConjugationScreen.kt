package com.example.russianapp.screens

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
import androidx.compose.ui.unit.dp

private data class ConjugationRow(val pronoun: String, val form: String)

private val readType1Rows = listOf(
    ConjugationRow("я",      "читаю"),
    ConjugationRow("ты",     "читаешь"),
    ConjugationRow("он/она", "читает"),
    ConjugationRow("мы",     "читаем"),
    ConjugationRow("вы",     "читаете"),
    ConjugationRow("они",    "читают")
)

private val speakType2Rows = listOf(
    ConjugationRow("я",      "говорю"),
    ConjugationRow("ты",     "говоришь"),
    ConjugationRow("он/она", "говорит"),
    ConjugationRow("мы",     "говорим"),
    ConjugationRow("вы",     "говорите"),
    ConjugationRow("они",    "говорят")
)

private val reflexivePresentRows = listOf(
    ConjugationRow("я",      "моюсь"),
    ConjugationRow("ты",     "моешься"),
    ConjugationRow("он/она", "моется"),
    ConjugationRow("мы",     "моемся"),
    ConjugationRow("вы",     "моетесь"),
    ConjugationRow("они",    "моются")
)

@Composable
private fun SectionHeader(title: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, bottom = 4.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary
        )
        HorizontalDivider(
            modifier = Modifier.padding(top = 4.dp),
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
private fun ConjugationTable(title: String, rows: List<ConjugationRow>) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(8.dp))
            rows.forEach { row ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 2.dp)
                ) {
                    Text(
                        text = row.pronoun,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.weight(1f),
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = row.form,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.weight(2f)
                    )
                }
            }
        }
    }
}

@Composable
private fun PastTenseCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Remove -ть from the infinitive and add a suffix based on gender/number.",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            listOf(
                Triple("Masculine",  "-л",  "он читал / говорил"),
                Triple("Feminine",   "-ла", "она читала / говорила"),
                Triple("Neuter",     "-ло", "оно читало / говорило"),
                Triple("Plural",     "-ли", "они читали / говорили")
            ).forEach { (gender, ending, example) ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 2.dp)
                ) {
                    Text(
                        text = gender,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.weight(2f)
                    )
                    Text(
                        text = ending,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.weight(1f),
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = example,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.weight(3f),
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

@Composable
private fun ImperativeCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Imperatives are formed from the present-tense stem:",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            listOf(
                Pair("ты form",  "Stem + -й / -и / -ь  (drop the personal ending)"),
                Pair("вы form",  "ты form + -те")
            ).forEach { (label, rule) ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 2.dp)
                ) {
                    Text(
                        text = label,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.weight(1.5f),
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = rule,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.weight(3f)
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            HorizontalDivider()
            Spacer(modifier = Modifier.height(8.dp))
            Text("Examples:", style = MaterialTheme.typography.labelLarge)
            Spacer(modifier = Modifier.height(4.dp))
            listOf(
                "читать → читай (ты) / читайте (вы)  — Read!",
                "говорить → говори (ты) / говорите (вы)  — Speak!"
            ).forEach { example ->
                Text(
                    text = "• $example",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(vertical = 1.dp)
                )
            }
        }
    }
}

@Composable
private fun ReflexiveVerbsCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Reflexive verbs express an action directed back at the subject (\"oneself\"). They are formed by adding -ся or -сь to a regular verb.",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "Rule: use -сь after a vowel ending, -ся after a consonant ending (including -ь).",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(12.dp))

            // Present tense table
            Text(
                text = "Present — мыться (to wash oneself)",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(6.dp))
            reflexivePresentRows.forEach { row ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 2.dp)
                ) {
                    Text(
                        text = row.pronoun,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.weight(1f),
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = row.form,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.weight(2f)
                    )
                }
            }
            Spacer(modifier = Modifier.height(12.dp))

            // Past tense
            Text(
                text = "Past — мыться",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(6.dp))
            listOf(
                Triple("Masculine",  "-лся",  "он мылся"),
                Triple("Feminine",   "-лась", "она мылась"),
                Triple("Neuter",     "-лось", "оно мылось"),
                Triple("Plural",     "-лись", "они мылись")
            ).forEach { (gender, ending, example) ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 2.dp)
                ) {
                    Text(
                        text = gender,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.weight(2f)
                    )
                    Text(
                        text = ending,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.weight(1.5f),
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = example,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.weight(2.5f),
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
            Spacer(modifier = Modifier.height(12.dp))

            // Imperative
            Text(
                text = "Imperative — мыться",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "Yes, reflexive imperatives exist! Add -ся/-сь to the regular imperative form.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(4.dp))
            listOf(
                "мойся (ты)  — Wash yourself! (informal)",
                "мойтесь (вы)  — Wash yourself/yourselves! (formal/plural)"
            ).forEach { example ->
                Text(
                    text = "• $example",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(vertical = 1.dp)
                )
            }
        }
    }
}

@Composable
private fun PassiveVoiceCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Russian has two main ways to express passive meaning:",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(10.dp))

            // Reflexive passive
            Text(
                text = "1. Present passive with -ся",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Add -ся to the imperfective verb. The subject becomes the thing acted upon.",
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = "• Письмо пишется. (The letter is being written.)",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(top = 2.dp)
            )
            Text(
                text = "• Книги продаются здесь. (Books are sold here.)",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(top = 2.dp, bottom = 8.dp)
            )

            HorizontalDivider()
            Spacer(modifier = Modifier.height(8.dp))

            // Short participle passive
            Text(
                text = "2. Past / Future passive with быть + short participle",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Form the short passive participle from the perfective infinitive. Replace -ть with the suffix below. The participle agrees in gender and number with the subject.",
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Example: написать (to write) → написан / написана / написано / написаны",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(10.dp))

            // Gender table header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp)
            ) {
                Text("Form",      style = MaterialTheme.typography.labelMedium, modifier = Modifier.weight(2f))
                Text("Participle", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.primary, modifier = Modifier.weight(1.5f))
                Text("Example",   style = MaterialTheme.typography.labelMedium, modifier = Modifier.weight(3f))
            }
            HorizontalDivider(modifier = Modifier.padding(bottom = 4.dp))

            listOf(
                Triple("Masculine (sg)",  "написан",   "Роман будет написан. (The novel will be written.)"),
                Triple("Feminine (sg)",   "написана",  "Книга будет написана. (The book will be written.)"),
                Triple("Neuter (sg)",     "написано",  "Письмо будет написано. (The letter will be written.)"),
                Triple("Plural",          "написаны",  "Письма будут написаны. (The letters will be written.)")
            ).forEach { (form, participle, example) ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 3.dp)
                ) {
                    Text(form,       style = MaterialTheme.typography.bodySmall, modifier = Modifier.weight(2f))
                    Text(participle, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.primary, modifier = Modifier.weight(1.5f))
                    Text(example,    style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant, modifier = Modifier.weight(3f))
                }
            }

            Spacer(modifier = Modifier.height(10.dp))
            HorizontalDivider()
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Plural note: use будут (not будет) with plural subjects and the plural participle ending in -ы/-и.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = "• Все задания будут выполнены. (All tasks will be completed.)",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(top = 2.dp)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VerbConjugationScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Verb Conjugation") },
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
            contentPadding = PaddingValues(bottom = 24.dp)
        ) {
            item { SectionHeader("Present Tense") }
            item {
                Text(
                    text = "Russian verbs have two main conjugation types. The key difference is visible in the они (they) form: Type 1 ends in -ут/-ют, Type 2 ends in -ат/-ят.",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
            item { ConjugationTable("Type 1 (-ать/-ять): читать (to read)", readType1Rows) }
            item { ConjugationTable("Type 2 (-ить/-еть): говорить (to speak)", speakType2Rows) }
            item { SectionHeader("Past Tense") }
            item { PastTenseCard() }
            item { SectionHeader("Imperative") }
            item { ImperativeCard() }
            item { SectionHeader("Reflexive Verbs (-ся / -сь)") }
            item { ReflexiveVerbsCard() }
            item { SectionHeader("Passive Voice (Страдательный залог)") }
            item { PassiveVoiceCard() }
        }
    }
}
