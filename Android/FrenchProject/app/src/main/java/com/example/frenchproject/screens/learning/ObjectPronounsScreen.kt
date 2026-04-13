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

private data class ObjectPronoun(
    val french: String,
    val english: String,
    val note: String? = null
)

private data class ObjectPronounExample(
    val original: String,
    val withPronoun: String,
    val translation: String
)

private val directPronouns = listOf(
    ObjectPronoun("me / m'", "me", "m' before a vowel or silent h"),
    ObjectPronoun("te / t'", "you (singular, informal)", "t' before a vowel or silent h"),
    ObjectPronoun("le / l'", "him / it (masculine)", "l' before a vowel or silent h"),
    ObjectPronoun("la / l'", "her / it (feminine)", "l' before a vowel or silent h"),
    ObjectPronoun("nous", "us"),
    ObjectPronoun("vous", "you (plural or formal)"),
    ObjectPronoun("les", "them")
)

private val indirectPronouns = listOf(
    ObjectPronoun("me / m'", "to me"),
    ObjectPronoun("te / t'", "to you (singular, informal)"),
    ObjectPronoun("lui", "to him / to her"),
    ObjectPronoun("nous", "to us"),
    ObjectPronoun("vous", "to you (plural or formal)"),
    ObjectPronoun("leur", "to them")
)

private val directExamples = listOf(
    ObjectPronounExample(
        "Je regarde le film.",
        "Je le regarde.",
        "I watch it."
    ),
    ObjectPronounExample(
        "J'ai vu le film.",
        "Je l'ai vu.",
        "I saw it. (l' because 'ai' starts with a vowel)"
    ),
    ObjectPronounExample(
        "Il aime la voiture.",
        "Il l'aime.",
        "He likes it."
    ),
    ObjectPronounExample(
        "Nous connaissons Marie.",
        "Nous la connaissons.",
        "We know her."
    ),
    ObjectPronounExample(
        "Tu as vu les enfants ?",
        "Tu les as vus ?",
        "Did you see them?"
    )
)

private val indirectExamples = listOf(
    ObjectPronounExample(
        "Je parle à Marie.",
        "Je lui parle.",
        "I speak to her."
    ),
    ObjectPronounExample(
        "J'ai envoyé un message aux enfants.",
        "Je leur ai envoyé un message.",
        "I sent them a message."
    ),
    ObjectPronounExample(
        "Il téléphone à ses parents.",
        "Il leur téléphone.",
        "He calls them."
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ObjectPronounsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Object Pronouns", color = Color.White) },
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
            item {
                Text(
                    text = "Object pronouns replace a noun that has already been mentioned, so you don't need to repeat it. They always go directly before the verb — or before the auxiliary (avoir / être) in compound tenses like the passé composé.",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }

            // Direct object pronouns
            item {
                Spacer(modifier = Modifier.height(4.dp))
                ObjSectionHeader("Direct Object Pronouns")
            }
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFE8EDF8)),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(
                            text = "Replace the direct object — the thing directly acted upon (no preposition before it).",
                            style = MaterialTheme.typography.bodySmall,
                            color = FrenchNavy
                        )
                    }
                }
            }
            items(directPronouns.size) { i -> ObjPronounCard(directPronouns[i]) }

            item {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Examples",
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.SemiBold,
                    color = FrenchNavy,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
            }
            items(directExamples.size) { i -> ObjExampleCard(directExamples[i]) }

            // Indirect object pronouns
            item {
                Spacer(modifier = Modifier.height(12.dp))
                ObjSectionHeader("Indirect Object Pronouns")
            }
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFE8EDF8)),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(
                            text = "Replace à + person — the person to whom or for whom the action is done.",
                            style = MaterialTheme.typography.bodySmall,
                            color = FrenchNavy
                        )
                    }
                }
            }
            items(indirectPronouns.size) { i -> ObjPronounCard(indirectPronouns[i]) }

            item {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Examples",
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.SemiBold,
                    color = FrenchNavy,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
            }
            items(indirectExamples.size) { i -> ObjExampleCard(indirectExamples[i]) }

            // y and en
            item {
                Spacer(modifier = Modifier.height(12.dp))
                ObjSectionHeader("The Pronouns y and en")
            }
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFE8EDF8)),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Text(
                            text = "y — replaces à + place or thing (not a person)",
                            fontWeight = FontWeight.SemiBold,
                            color = FrenchNavy,
                            fontSize = 14.sp
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Je vais à Paris.  →  J'y vais.",
                            style = MaterialTheme.typography.bodySmall,
                            fontStyle = FontStyle.Italic,
                            color = FrenchBlue
                        )
                        Text(
                            text = "I'm going there.",
                            style = MaterialTheme.typography.bodySmall,
                            color = FrenchNavy
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Il pense à ce problème.  →  Il y pense.",
                            style = MaterialTheme.typography.bodySmall,
                            fontStyle = FontStyle.Italic,
                            color = FrenchBlue
                        )
                        Text(
                            text = "He's thinking about it.",
                            style = MaterialTheme.typography.bodySmall,
                            color = FrenchNavy
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        HorizontalDivider(color = FrenchBlue.copy(alpha = 0.3f))
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = "en — replaces de + noun, or a partitive / quantity expression",
                            fontWeight = FontWeight.SemiBold,
                            color = FrenchNavy,
                            fontSize = 14.sp
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "J'ai besoin d'argent.  →  J'en ai besoin.",
                            style = MaterialTheme.typography.bodySmall,
                            fontStyle = FontStyle.Italic,
                            color = FrenchBlue
                        )
                        Text(
                            text = "I need some (of it).",
                            style = MaterialTheme.typography.bodySmall,
                            color = FrenchNavy
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "J'ai deux chats.  →  J'en ai deux.",
                            style = MaterialTheme.typography.bodySmall,
                            fontStyle = FontStyle.Italic,
                            color = FrenchBlue
                        )
                        Text(
                            text = "I have two (of them).",
                            style = MaterialTheme.typography.bodySmall,
                            color = FrenchNavy
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Tu viens de Paris ?  →  Oui, j'en viens.",
                            style = MaterialTheme.typography.bodySmall,
                            fontStyle = FontStyle.Italic,
                            color = FrenchBlue
                        )
                        Text(
                            text = "Are you from Paris? — Yes, I'm from there.",
                            style = MaterialTheme.typography.bodySmall,
                            color = FrenchNavy
                        )
                    }
                }
            }

            // Placement rule
            item {
                Spacer(modifier = Modifier.height(12.dp))
                ObjSectionHeader("Placement Rule")
            }
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Text(
                            text = "The pronoun goes BEFORE the verb.",
                            fontWeight = FontWeight.SemiBold,
                            color = FrenchNavy
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "• Present tense:  Je le vois.  (I see it.)",
                            style = MaterialTheme.typography.bodySmall,
                            fontStyle = FontStyle.Italic,
                            color = FrenchBlue
                        )
                        Text(
                            text = "• Passé composé:  Je l'ai vu.  (I saw it.) — before the auxiliary avoir/être",
                            style = MaterialTheme.typography.bodySmall,
                            fontStyle = FontStyle.Italic,
                            color = FrenchBlue
                        )
                        Text(
                            text = "• Near future:  Je vais le voir.  (I'm going to see it.) — before the infinitive",
                            style = MaterialTheme.typography.bodySmall,
                            fontStyle = FontStyle.Italic,
                            color = FrenchBlue
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "In negation, the pronoun stays between ne and the verb:",
                            style = MaterialTheme.typography.bodySmall,
                            color = FrenchNavy
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Je ne le vois pas.  (I don't see it.)",
                            style = MaterialTheme.typography.bodySmall,
                            fontStyle = FontStyle.Italic,
                            color = FrenchBlue
                        )
                    }
                }
            }

            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}

@Composable
private fun ObjPronounCard(pronoun: ObjectPronoun) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 3.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 9.dp)
        ) {
            Text(
                text = pronoun.french,
                fontWeight = FontWeight.Bold,
                color = FrenchBlue,
                fontSize = 15.sp,
                modifier = Modifier.width(100.dp)
            )
            Column(modifier = Modifier.weight(1f)) {
                Text(text = pronoun.english, style = MaterialTheme.typography.bodyMedium)
                if (pronoun.note != null) {
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = pronoun.note,
                        style = MaterialTheme.typography.bodySmall,
                        fontStyle = FontStyle.Italic,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

@Composable
private fun ObjExampleCard(example: ObjectPronounExample) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 3.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(horizontal = 14.dp, vertical = 9.dp)) {
            Text(
                text = example.original,
                style = MaterialTheme.typography.bodySmall,
                color = FrenchNavy
            )
            Text(
                text = "→  ${example.withPronoun}",
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,
                color = FrenchBlue,
                fontSize = 14.sp
            )
            Text(
                text = example.translation,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun ObjSectionHeader(title: String) {
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
