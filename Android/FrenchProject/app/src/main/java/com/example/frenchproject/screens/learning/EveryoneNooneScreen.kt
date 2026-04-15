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

private data class ToutForm(
    val french: String,
    val gender: String,
    val use: String,
    val example: String,
    val translation: String
)

private val toutForms = listOf(
    ToutForm("tout", "m. sg.", "everything (pronoun) or before m. sg. noun",
        "tout le temps", "all the time"),
    ToutForm("toute", "f. sg.", "before f. sg. noun",
        "toute la journée", "all day / the whole day"),
    ToutForm("tous", "m. pl.", "before m. pl. noun; standalone pronoun 'all of them'",
        "tous les jours", "every day"),
    ToutForm("toutes", "f. pl.", "before f. pl. noun",
        "toutes les nuits", "every night")
)

private data class NegQuantifier(
    val pattern: String,
    val meaning: String,
    val examples: List<Pair<String, String>>,
    val note: String? = null
)

private val negQuantifiers = listOf(
    NegQuantifier(
        pattern = "ne ... personne",
        meaning = "nobody / no one",
        examples = listOf(
            "Il ne voit personne." to "He sees nobody.",
            "Personne ne vient." to "Nobody comes.",
            "Je n'ai vu personne." to "I didn't see anyone."
        ),
        note = "personne can be the subject: Personne n'est parfait. In compound tenses, personne goes AFTER the past participle."
    ),
    NegQuantifier(
        pattern = "ne ... rien",
        meaning = "nothing / not anything",
        examples = listOf(
            "Il ne mange rien." to "He eats nothing.",
            "Rien ne m'intéresse." to "Nothing interests me.",
            "Je n'ai rien mangé." to "I haven't eaten anything."
        ),
        note = "rien can be the subject: Rien ne va. In compound tenses, rien goes BEFORE the past participle."
    ),
    NegQuantifier(
        pattern = "ne ... nulle part",
        meaning = "nowhere / not anywhere",
        examples = listOf(
            "Elle ne va nulle part." to "She's going nowhere.",
            "Je ne l'ai trouvé nulle part." to "I found it nowhere."
        ),
        note = "In compound tenses, nulle part goes AFTER the past participle (same exception as personne)."
    ),
    NegQuantifier(
        pattern = "ne ... jamais",
        meaning = "never",
        examples = listOf(
            "Je ne fais jamais ça." to "I never do that.",
            "Elle n'est jamais en retard." to "She is never late."
        ),
        note = "In compound tenses, jamais goes BEFORE the past participle: Je n'ai jamais mangé ça."
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EveryoneNooneScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Everybody and No One", color = Color.White) },
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
            // Intro
            item {
                Text(
                    text = "French uses tout/toute/tous/toutes for universal ideas (everything, everyone, every day) and ne…personne/rien/nulle part/jamais for negatives (nobody, nothing, nowhere, never).",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }

            // Section 1: Tout forms
            item { EnnSectionHeader("Tout / Toute / Tous / Toutes") }
            item {
                Card(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFE8EDF8)),
                    elevation = CardDefaults.cardElevation(2.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        toutForms.forEachIndexed { i, form ->
                            if (i > 0) {
                                Spacer(modifier = Modifier.height(8.dp))
                                HorizontalDivider(color = FrenchBlue.copy(alpha = 0.2f))
                                Spacer(modifier = Modifier.height(8.dp))
                            }
                            Row(modifier = Modifier.fillMaxWidth()) {
                                Text(
                                    text = form.french,
                                    fontWeight = FontWeight.Bold,
                                    color = FrenchBlue,
                                    fontSize = 15.sp,
                                    modifier = Modifier.width(60.dp)
                                )
                                Text(
                                    text = form.gender,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = FrenchNavy,
                                    modifier = Modifier.width(56.dp)
                                )
                                Text(
                                    text = form.use,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = FrenchNavy,
                                    modifier = Modifier.weight(1f)
                                )
                            }
                            Spacer(modifier = Modifier.height(3.dp))
                            Text(
                                text = "${form.example}  →  ${form.translation}",
                                style = MaterialTheme.typography.bodySmall,
                                fontStyle = FontStyle.Italic,
                                color = FrenchBlue
                            )
                        }
                    }
                }
            }

            // Section 2: Everybody & Everything
            item {
                Spacer(modifier = Modifier.height(12.dp))
                EnnSectionHeader("Everybody & Everything")
            }
            item {
                Card(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(2.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        EnnEntryRow("tout le monde", "everyone / everybody")
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Tout le monde est là.  →  Everyone is here.",
                            style = MaterialTheme.typography.bodySmall,
                            fontStyle = FontStyle.Italic,
                            color = FrenchBlue
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Tout le monde aime le chocolat.  →  Everyone likes chocolate.",
                            style = MaterialTheme.typography.bodySmall,
                            fontStyle = FontStyle.Italic,
                            color = FrenchBlue
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "⚠ tout le monde always takes a SINGULAR verb (it means 'all the world', treated as one group).",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        HorizontalDivider(color = FrenchBlue.copy(alpha = 0.2f))
                        Spacer(modifier = Modifier.height(10.dp))
                        EnnEntryRow("tout", "everything (pronoun)")
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Il mange tout.  →  He eats everything.",
                            style = MaterialTheme.typography.bodySmall,
                            fontStyle = FontStyle.Italic,
                            color = FrenchBlue
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Tout est possible.  →  Everything is possible.",
                            style = MaterialTheme.typography.bodySmall,
                            fontStyle = FontStyle.Italic,
                            color = FrenchBlue
                        )
                    }
                }
            }

            // Section 3: Every / Each
            item {
                Spacer(modifier = Modifier.height(12.dp))
                EnnSectionHeader("Every / Each — Time Expressions")
            }
            item {
                Card(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(2.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        EnnExampleBlock("tous les matins", "every morning",
                            "Je fais du sport tous les matins.  →  I exercise every morning.")
                        Spacer(modifier = Modifier.height(8.dp))
                        HorizontalDivider(color = FrenchBlue.copy(alpha = 0.2f))
                        Spacer(modifier = Modifier.height(8.dp))
                        EnnExampleBlock("tous les jours", "every day",
                            "Je lis tous les jours.  →  I read every day.")
                        Spacer(modifier = Modifier.height(8.dp))
                        HorizontalDivider(color = FrenchBlue.copy(alpha = 0.2f))
                        Spacer(modifier = Modifier.height(8.dp))
                        EnnExampleBlock("chaque jour", "each day / every day",
                            "Chaque jour est une nouvelle chance.  →  Each day is a new chance.")
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "chaque is invariable (never changes form) and is always followed by a singular noun.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        HorizontalDivider(color = FrenchBlue.copy(alpha = 0.2f))
                        Spacer(modifier = Modifier.height(8.dp))
                        EnnExampleBlock("chaque fois", "each time / every time",
                            "Chaque fois que je le vois, il sourit.  →  Every time I see him, he smiles.")
                        Spacer(modifier = Modifier.height(8.dp))
                        HorizontalDivider(color = FrenchBlue.copy(alpha = 0.2f))
                        Spacer(modifier = Modifier.height(8.dp))
                        EnnExampleBlock("toute la journée", "all day / the whole day",
                            "Il travaille toute la journée.  →  He works all day.")
                    }
                }
            }

            // Section 4: Everywhere & Always
            item {
                Spacer(modifier = Modifier.height(12.dp))
                EnnSectionHeader("Everywhere & Always")
            }
            item {
                Card(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(2.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        EnnExampleBlock("partout", "everywhere",
                            "Il cherche partout.  →  He's looking everywhere.\nElle emporte son téléphone partout.  →  She takes her phone everywhere.")
                        Spacer(modifier = Modifier.height(8.dp))
                        HorizontalDivider(color = FrenchBlue.copy(alpha = 0.2f))
                        Spacer(modifier = Modifier.height(8.dp))
                        EnnExampleBlock("toujours", "always",
                            "Elle est toujours en retard.  →  She is always late.\nJe t'aimerai toujours.  →  I will always love you.")
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Note: toujours also means 'still' (Je suis toujours là = I'm still here). Context tells which meaning is intended.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        HorizontalDivider(color = FrenchBlue.copy(alpha = 0.2f))
                        Spacer(modifier = Modifier.height(8.dp))
                        EnnExampleBlock("chacun / chacune", "each one (pronoun)",
                            "Chacun fait ce qu'il veut.  →  Each one does what they want.\nChacune a sa propre chambre.  →  Each one (f.) has her own room.")
                    }
                }
            }

            // Section 5: Nobody / Nothing / Nowhere / Never
            item {
                Spacer(modifier = Modifier.height(12.dp))
                EnnSectionHeader("Nobody / Nothing / Nowhere / Never")
            }
            items(negQuantifiers.size) { i -> EnnNegCard(negQuantifiers[i]) }

            // Section 6: Double Negative Rule
            item {
                Spacer(modifier = Modifier.height(12.dp))
                EnnSectionHeader("The Double Negative Rule")
            }
            item {
                Card(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFE8EDF8)),
                    elevation = CardDefaults.cardElevation(2.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "Yes — French REQUIRES what looks like a double negative.",
                            fontWeight = FontWeight.Bold,
                            color = FrenchNavy,
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "Structure:  ne + VERB + personne / rien / nulle part / jamais",
                            style = MaterialTheme.typography.bodyMedium,
                            color = FrenchNavy
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Il ne voit personne.  →  He sees nobody.\nElle ne mange rien.  →  She eats nothing.\nJe ne vais nulle part.  →  I'm going nowhere.\nIl ne vient jamais.  →  He never comes.",
                            style = MaterialTheme.typography.bodySmall,
                            fontStyle = FontStyle.Italic,
                            color = FrenchBlue,
                            lineHeight = 20.sp
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        HorizontalDivider(color = FrenchBlue.copy(alpha = 0.25f))
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Unlike English, this double negative is correct and mandatory. Using only one part would be grammatically incomplete.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "Spoken French tip: the ne is often dropped in casual speech.",
                            fontWeight = FontWeight.SemiBold,
                            style = MaterialTheme.typography.bodySmall,
                            color = FrenchNavy
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Je vois personne.  →  (casual) I don't see anyone.\nElle mange rien.  →  (casual) She eats nothing.",
                            style = MaterialTheme.typography.bodySmall,
                            fontStyle = FontStyle.Italic,
                            color = FrenchBlue,
                            lineHeight = 20.sp
                        )
                    }
                }
            }

            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}

@Composable
private fun EnnSectionHeader(title: String) {
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
private fun EnnEntryRow(french: String, english: String) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = french,
            fontWeight = FontWeight.Bold,
            color = FrenchBlue,
            fontSize = 14.sp,
            modifier = Modifier.width(160.dp)
        )
        Text(text = english, style = MaterialTheme.typography.bodyMedium, color = FrenchNavy)
    }
}

@Composable
private fun EnnExampleBlock(french: String, english: String, example: String) {
    EnnEntryRow(french, english)
    Spacer(modifier = Modifier.height(3.dp))
    Text(
        text = example,
        style = MaterialTheme.typography.bodySmall,
        fontStyle = FontStyle.Italic,
        color = FrenchBlue,
        lineHeight = 20.sp
    )
}

@Composable
private fun EnnNegCard(form: NegQuantifier) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 10.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = form.pattern,
                    fontWeight = FontWeight.Bold,
                    color = FrenchBlue,
                    fontSize = 15.sp,
                    modifier = Modifier.width(170.dp)
                )
                Text(
                    text = form.meaning,
                    style = MaterialTheme.typography.bodyMedium,
                    color = FrenchNavy,
                    modifier = Modifier.weight(1f)
                )
            }
            Spacer(modifier = Modifier.height(6.dp))
            form.examples.forEach { (french, english) ->
                Text(
                    text = "$french  →  $english",
                    style = MaterialTheme.typography.bodySmall,
                    fontStyle = FontStyle.Italic,
                    color = FrenchBlue,
                    modifier = Modifier.padding(bottom = 2.dp)
                )
            }
            if (form.note != null) {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = form.note,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    lineHeight = 18.sp
                )
            }
        }
    }
}
