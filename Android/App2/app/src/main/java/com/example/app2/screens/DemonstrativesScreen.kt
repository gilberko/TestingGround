package com.example.app2.screens

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.ElevatedCard
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DemonstrativesScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("This, That, Here & There") },
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
                .padding(innerPadding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item { OverviewCard() }
            item { DemonstrativeAdjectivesCard() }
            item { NeuterPronounsCard() }
            item { HereCard() }
            item { ThereCard() }
        }
    }
}

// ── Card 1: Overview ─────────────────────────────────────────────────────────

@Composable
private fun OverviewCard() {
    ElevatedCard(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                "The Three-Distance System",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                "English has two levels of distance — this vs that, here vs there. European Portuguese has three, based on who is closest to the thing: the speaker, the listener, or neither.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(4.dp))

            DemoRow(label = "Near speaker (me)", pt = "este / essa / aqui / cá", en = "this / here")
            DemoRow(label = "Near listener (you)", pt = "esse / isso / aí", en = "that / there (by you)")
            DemoRow(label = "Far from both", pt = "aquele / aquilo / ali / lá", en = "that / there (over there)")

            Spacer(modifier = Modifier.height(4.dp))
            Text(
                "Note: Brazilian Portuguese often collapses the first two levels, using esse/isso for both. European Portuguese keeps all three distinct.",
                style = MaterialTheme.typography.bodySmall,
                fontStyle = FontStyle.Italic,
                color = MaterialTheme.colorScheme.tertiary
            )
        }
    }
}

// ── Card 2: Demonstrative Adjectives ─────────────────────────────────────────

@Composable
private fun DemonstrativeAdjectivesCard() {
    ElevatedCard(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                "Demonstrative Adjectives & Pronouns",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                "These agree with the noun in gender and number. They can stand before a noun (adjective) or replace it (pronoun).",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            HorizontalDivider(modifier = Modifier.padding(vertical = 2.dp))

            DemoEntry(
                term = "este / esta / estes / estas",
                meaning = "this (near me)",
                description = "Used when the object is close to the speaker. This is the equivalent of English \"this\". Agrees with gender and number.",
                examples = listOf(
                    "Quero este livro." to "I want this book. (m)",
                    "Esta casa é bonita." to "This house is beautiful. (f)",
                    "Estes sapatos são caros." to "These shoes are expensive. (m.pl)"
                )
            )

            HorizontalDivider(modifier = Modifier.padding(vertical = 2.dp))

            DemoEntry(
                term = "esse / essa / esses / essas",
                meaning = "that (near you)",
                description = "Used when the object is close to the listener, or when referring to something the listener just mentioned. English doesn't have a direct equivalent — it's a \"that\" specifically tied to the other person.",
                examples = listOf(
                    "Esse livro que tens é bom?" to "Is that book you have good?",
                    "Essa ideia não me convence." to "That idea of yours doesn't convince me.",
                    "Não gosto dessas calças." to "I don't like those trousers."
                ),
                note = "\"Desse / dessa\" = \"of that\" (de + esse/essa contracted). Common in spoken EP."
            )

            HorizontalDivider(modifier = Modifier.padding(vertical = 2.dp))

            DemoEntry(
                term = "aquele / aquela / aqueles / aquelas",
                meaning = "that (over there)",
                description = "Used when the object is far from both the speaker and the listener — the most distant of the three levels. Can also refer to something in the distant past.",
                examples = listOf(
                    "Aquele edifício é muito alto." to "That building over there is very tall.",
                    "Quem é aquela mulher?" to "Who is that woman over there?",
                    "Aqueles anos foram difíceis." to "Those years were difficult. (distant past)"
                ),
                note = "\"Daquele / daquela\" = \"of that\" (de + aquele/aquela contracted). E.g. \"O dono daquele carro\" = The owner of that car."
            )
        }
    }
}

// ── Card 3: Neuter Pronouns ───────────────────────────────────────────────────

@Composable
private fun NeuterPronounsCard() {
    ElevatedCard(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                "Neuter Pronouns — Isto, Isso, Aquilo",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                "These are invariable — no gender, no plural. Use them when you don't know what the thing is (e.g. pointing at something unidentified), or when referring to an idea, situation, or action rather than a specific noun.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            HorizontalDivider(modifier = Modifier.padding(vertical = 2.dp))

            DemoEntry(
                term = "isto",
                meaning = "this (thing) — near speaker",
                description = "Refers to something near the speaker that hasn't been named, or to an abstract idea/situation the speaker is presenting.",
                examples = listOf(
                    "O que é isto?" to "What is this?",
                    "Isto é inacreditável!" to "This is unbelievable!",
                    "Não quero isto." to "I don't want this."
                )
            )

            HorizontalDivider(modifier = Modifier.padding(vertical = 2.dp))

            DemoEntry(
                term = "isso",
                meaning = "that (thing) — near listener",
                description = "Refers to something near the listener, or to something the listener just said or did.",
                examples = listOf(
                    "Não faças isso!" to "Don't do that!",
                    "Isso é verdade." to "That is true.",
                    "Não gosto disso." to "I don't like that."
                ),
                note = "\"Disso\" = de + isso (contracted). Very common: \"Não me lembro disso\" = I don't remember that."
            )

            HorizontalDivider(modifier = Modifier.padding(vertical = 2.dp))

            DemoEntry(
                term = "aquilo",
                meaning = "that (thing) — far from both",
                description = "Refers to something far from both speaker and listener, when the thing has no clear gender or is unknown.",
                examples = listOf(
                    "O que é aquilo?" to "What is that (over there)?",
                    "Aquilo foi um erro." to "That was a mistake.",
                    "Não gosto daquilo." to "I don't like that."
                ),
                note = "\"Daquilo\" = de + aquilo (contracted)."
            )
        }
    }
}

// ── Card 4: Here — Aqui vs Cá ────────────────────────────────────────────────

@Composable
private fun HereCard() {
    ElevatedCard(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                "Here — Aqui vs Cá",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                "Both mean \"here\", but they are not interchangeable. The key rule: aqui points to a precise spot; cá suggests movement toward the speaker or a general area around the speaker.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            HorizontalDivider(modifier = Modifier.padding(vertical = 2.dp))

            DemoEntry(
                term = "aqui",
                meaning = "here (precise, specific location)",
                description = "Pinpoints an exact spot — where you are standing, where the object is. Used with static verbs like estar, ficar, ser.",
                examples = listOf(
                    "Estou aqui." to "I am here. (at this exact spot)",
                    "O livro está aqui." to "The book is here.",
                    "Aqui é o meu lugar." to "This is my seat / here is my place."
                )
            )

            HorizontalDivider(modifier = Modifier.padding(vertical = 2.dp))

            DemoEntry(
                term = "cá",
                meaning = "here (movement toward speaker / general area)",
                description = "Used with verbs of motion (vir, trazer, chegar) or to indicate a general area rather than a precise point. In EP, cá is very natural in commands and directions.",
                examples = listOf(
                    "Vem cá!" to "Come here!",
                    "Traz isso cá." to "Bring that here.",
                    "Fica cá em casa." to "Stay here at home. (general area)",
                    "Cá em Portugal fazemos assim." to "Here in Portugal we do it this way."
                ),
                note = "Quick rule: use cá with movement (vir, trazer, chegar) and aqui for location (estar, ficar, estar)."
            )
        }
    }
}

// ── Card 5: There — Aí, Ali, Lá ──────────────────────────────────────────────

@Composable
private fun ThereCard() {
    ElevatedCard(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                "There — Aí, Ali & Lá",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                "Portuguese has three words where English only has \"there\". Each maps to a different distance or relationship.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            HorizontalDivider(modifier = Modifier.padding(vertical = 2.dp))

            DemoEntry(
                term = "aí",
                meaning = "there (near you / the listener)",
                description = "Points to a place close to the person you are speaking with — the \"there\" that means \"by you\". Mirrors aqui/cá for the listener's side.",
                examples = listOf(
                    "O livro está aí?" to "Is the book there (by you)?",
                    "Fica aí, já vou." to "Stay there, I'm coming.",
                    "O que está aí?" to "What is there (where you are)?"
                )
            )

            HorizontalDivider(modifier = Modifier.padding(vertical = 2.dp))

            DemoEntry(
                term = "ali",
                meaning = "there (visible, specific, not far)",
                description = "Points to a specific visible spot that is neither close to the speaker nor close to the listener — you can see it and point at it. It's more precise than lá.",
                examples = listOf(
                    "Senta-te ali." to "Sit there. (pointing at a specific visible chair)",
                    "A paragem está ali." to "The bus stop is there. (visible)",
                    "Ele está ali ao fundo." to "He's there at the end."
                )
            )

            HorizontalDivider(modifier = Modifier.padding(vertical = 2.dp))

            DemoEntry(
                term = "lá",
                meaning = "there (far / general / movement away)",
                description = "The most general \"there\" — farther away, not necessarily visible, or used when direction matters more than the exact spot. Also used with verbs of motion going away from the speaker.",
                examples = listOf(
                    "Ela está lá fora." to "She's out there.",
                    "Vou lá amanhã." to "I'll go there tomorrow.",
                    "Lá em cima / lá em baixo." to "Up there / down there.",
                    "Lá no Porto é diferente." to "There in Porto it's different."
                ),
                note = "Ali = specific and visible (\"sit there\"). Lá = general or far (\"go there\", \"over there somewhere\")."
            )
        }
    }
}

// ── Shared composables ────────────────────────────────────────────────────────

@Composable
private fun DemoEntry(
    term: String,
    meaning: String,
    description: String,
    examples: List<Pair<String, String>>,
    note: String? = null
) {
    Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
        Text(term, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.SemiBold)
        Text(meaning, style = MaterialTheme.typography.bodyMedium, fontStyle = FontStyle.Italic, color = MaterialTheme.colorScheme.primary)
        Text(description, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
        Spacer(modifier = Modifier.height(2.dp))
        examples.forEach { (pt, en) ->
            Text(
                "\u2022 $pt",
                style = MaterialTheme.typography.bodySmall,
                fontStyle = FontStyle.Italic
            )
            Text(
                "  $en",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        if (note != null) {
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                "Note: $note",
                style = MaterialTheme.typography.bodySmall,
                fontStyle = FontStyle.Italic,
                color = MaterialTheme.colorScheme.tertiary
            )
        }
    }
}

@Composable
private fun DemoRow(label: String, pt: String, en: String) {
    Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(1.dp)) {
        Text(label, style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.SemiBold, color = MaterialTheme.colorScheme.primary)
        Text(
            "$pt  —  $en",
            style = MaterialTheme.typography.bodySmall,
            fontStyle = FontStyle.Italic,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}
