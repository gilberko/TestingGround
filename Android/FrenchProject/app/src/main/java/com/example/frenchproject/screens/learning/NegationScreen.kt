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

private data class NegForm(
    val pattern: String,
    val meaning: String,
    val examples: List<Pair<String, String>>,   // French to English
    val note: String? = null
)

private val negForms = listOf(
    NegForm(
        pattern = "ne ... pas",
        meaning = "not (basic negation)",
        examples = listOf(
            "Je ne mange pas." to "I don't eat.",
            "Elle ne parle pas français." to "She doesn't speak French.",
            "Je n'aime pas ça." to "I don't like that."
        ),
        note = "ne becomes n' before a vowel or silent h."
    ),
    NegForm(
        pattern = "ne ... jamais",
        meaning = "never",
        examples = listOf(
            "Je ne mange jamais de viande." to "I never eat meat.",
            "Il n'est jamais en retard." to "He is never late."
        )
    ),
    NegForm(
        pattern = "ne ... plus",
        meaning = "no longer / not anymore",
        examples = listOf(
            "Je ne fume plus." to "I no longer smoke.",
            "Elle n'habite plus ici." to "She doesn't live here anymore."
        )
    ),
    NegForm(
        pattern = "ne ... rien",
        meaning = "nothing / not anything",
        examples = listOf(
            "Je ne mange rien." to "I eat nothing.",
            "Rien ne m'intéresse." to "Nothing interests me.",
            "Je n'ai rien mangé." to "I haven't eaten anything."
        ),
        note = "rien can be the subject (Rien ne...). In compound tenses, rien goes BEFORE the past participle."
    ),
    NegForm(
        pattern = "ne ... personne",
        meaning = "nobody / not anyone",
        examples = listOf(
            "Je ne vois personne." to "I see nobody.",
            "Personne ne parle." to "Nobody speaks.",
            "Je n'ai vu personne." to "I didn't see anyone."
        ),
        note = "personne can be the subject (Personne ne...). In compound tenses, personne goes AFTER the past participle — unlike rien."
    ),
    NegForm(
        pattern = "ne ... ni ... ni",
        meaning = "neither ... nor",
        examples = listOf(
            "Je ne mange ni viande ni poisson." to "I eat neither meat nor fish.",
            "Il n'a ni frère ni sœur." to "He has neither brother nor sister."
        ),
        note = "Articles (un, une, des, du, de la) are dropped after ni."
    ),
    NegForm(
        pattern = "ne ... que",
        meaning = "only (restriction, not true negation)",
        examples = listOf(
            "Je ne mange que des légumes." to "I only eat vegetables.",
            "Elle ne dort que cinq heures." to "She only sleeps five hours."
        ),
        note = "ne ... que limits rather than negates. Articles are NOT dropped after que."
    ),
    NegForm(
        pattern = "ne ... aucun(e)",
        meaning = "not any / none / no",
        examples = listOf(
            "Je n'ai aucune idée." to "I have no idea.",
            "Aucun étudiant ne parle." to "No student speaks."
        ),
        note = "Agrees in gender: aucun (masculine), aucune (feminine). Can be subject (Aucun ne...)."
    ),
    NegForm(
        pattern = "ne ... nulle part",
        meaning = "nowhere / not anywhere",
        examples = listOf(
            "Je ne vais nulle part." to "I'm going nowhere.",
            "Je ne suis allé nulle part." to "I went nowhere."
        ),
        note = "In compound tenses, nulle part goes AFTER the past participle — same exception as personne."
    ),
    NegForm(
        pattern = "ne ... guère",
        meaning = "hardly / scarcely",
        examples = listOf(
            "Il ne travaille guère." to "He hardly works.",
            "Elle ne dort guère." to "She barely sleeps."
        ),
        note = "Literary and formal register. Rarely heard in everyday speech."
    ),
    NegForm(
        pattern = "ne ... point",
        meaning = "not at all (archaic / emphatic)",
        examples = listOf(
            "Je ne sais point." to "I do not know at all.",
            "Il ne ment point." to "He does not lie."
        ),
        note = "Archaic — found in literature and formal writing. In modern spoken French, use pas instead."
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NegationScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Negation", color = Color.White) },
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
                    text = "French negation wraps the verb in a two-part structure: ne before the verb and a second word (pas, jamais, rien…) after it. The choice of that second word changes the exact shade of negation.",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }

            // Position rules card
            item {
                NegSectionHeader("Placement Rules")
            }
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFE8EDF8)),
                    elevation = CardDefaults.cardElevation(2.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        PlacementRule(
                            label = "Simple tenses",
                            rule = "ne + VERB + negation word",
                            example = "Je ne dors pas. / Il ne mange jamais."
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        HorizontalDivider(color = FrenchBlue.copy(alpha = 0.25f))
                        Spacer(modifier = Modifier.height(10.dp))
                        PlacementRule(
                            label = "Compound tenses (passé composé etc.)",
                            rule = "ne + AUXILIARY + negation word + PARTICIPLE",
                            example = "Je n'ai pas mangé. / Elle n'est jamais venue.\n⚠ Exception: personne and nulle part go AFTER the participle:\nJe n'ai vu personne. / Je ne suis allé nulle part."
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        HorizontalDivider(color = FrenchBlue.copy(alpha = 0.25f))
                        Spacer(modifier = Modifier.height(10.dp))
                        PlacementRule(
                            label = "Infinitives",
                            rule = "Both ne and pas (and most negation words) go BEFORE the infinitive",
                            example = "Je décide de ne pas partir.\nIl préfère ne jamais mentir.\n⚠ Exception: personne and nulle part go AFTER the infinitive:\nJe refuse de ne voir personne."
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        HorizontalDivider(color = FrenchBlue.copy(alpha = 0.25f))
                        Spacer(modifier = Modifier.height(10.dp))
                        PlacementRule(
                            label = "Spoken French",
                            rule = "ne is routinely dropped in casual speech",
                            example = "Je sais pas. / Elle mange plus. / C'est jamais prêt."
                        )
                    }
                }
            }

            // Useful extras
            item {
                Spacer(modifier = Modifier.height(12.dp))
                NegSectionHeader("Useful Combinations")
            }
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(2.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        ComboRow("ne ... pas encore", "not yet",
                            "Je n'ai pas encore mangé. → I haven't eaten yet.")
                        Spacer(modifier = Modifier.height(8.dp))
                        ComboRow("ne ... pas du tout", "not at all",
                            "Je n'aime pas du tout ça. → I don't like that at all.")
                        Spacer(modifier = Modifier.height(8.dp))
                        ComboRow("ne ... pas non plus", "not … either",
                            "Je ne veux pas non plus. → I don't want to either.")
                    }
                }
            }

            // All negation forms
            item {
                Spacer(modifier = Modifier.height(12.dp))
                NegSectionHeader("Negation Forms")
            }
            items(negForms.size) { i -> NegFormCard(negForms[i]) }

            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}

@Composable
private fun NegSectionHeader(title: String) {
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
private fun PlacementRule(label: String, rule: String, example: String) {
    Text(text = label, fontWeight = FontWeight.SemiBold, color = FrenchNavy,
        style = MaterialTheme.typography.bodyMedium)
    Spacer(modifier = Modifier.height(2.dp))
    Text(text = rule, style = MaterialTheme.typography.bodyMedium)
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
private fun ComboRow(pattern: String, meaning: String, example: String) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = pattern,
            fontWeight = FontWeight.Bold,
            color = FrenchBlue,
            fontSize = 13.sp,
            modifier = Modifier.width(175.dp)
        )
        Text(text = meaning, style = MaterialTheme.typography.bodyMedium, color = FrenchNavy)
    }
    Spacer(modifier = Modifier.height(2.dp))
    Text(
        text = example,
        style = MaterialTheme.typography.bodySmall,
        fontStyle = FontStyle.Italic,
        color = FrenchBlue
    )
}

@Composable
private fun NegFormCard(form: NegForm) {
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
