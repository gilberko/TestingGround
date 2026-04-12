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

private data class PassiveExample(
    val active: String,
    val activeEnglish: String,
    val passive: String,
    val passiveEnglish: String,
    val note: String? = null
)

private data class PassiveTenseRow(val tense: String, val example: String, val english: String)

private data class PassiveAgreementRow(val subject: String, val form: String, val english: String)

private val tenseRows = listOf(
    PassiveTenseRow("Présent", "La lettre est écrite.", "The letter is being written."),
    PassiveTenseRow("Passé composé", "La lettre a été écrite.", "The letter was written."),
    PassiveTenseRow("Imparfait", "La lettre était écrite.", "The letter was being written."),
    PassiveTenseRow("Futur simple", "La lettre sera écrite.", "The letter will be written."),
    PassiveTenseRow("Conditionnel", "La lettre serait écrite.", "The letter would be written.")
)

private val agreementRows = listOf(
    PassiveAgreementRow("Le livre (m. sg.)", "est lu", "The book is read."),
    PassiveAgreementRow("La lettre (f. sg.)", "est lue", "The letter is read."),
    PassiveAgreementRow("Les livres (m. pl.)", "sont lus", "The books are read."),
    PassiveAgreementRow("Les lettres (f. pl.)", "sont lues", "The letters are read.")
)

private val workedExamples = listOf(
    PassiveExample(
        active = "Le boucher coupe la viande.",
        activeEnglish = "The butcher cuts the meat.",
        passive = "La viande est coupée par le boucher.",
        passiveEnglish = "The meat is cut by the butcher."
    ),
    PassiveExample(
        active = "Marie écrit la lettre.",
        activeEnglish = "Marie writes the letter.",
        passive = "La lettre est écrite par Marie.",
        passiveEnglish = "The letter is written by Marie."
    ),
    PassiveExample(
        active = "Le professeur a corrigé les copies.",
        activeEnglish = "The teacher corrected the papers.",
        passive = "Les copies ont été corrigées par le professeur.",
        passiveEnglish = "The papers were corrected by the teacher."
    ),
    PassiveExample(
        active = "Les enfants mangeaient les gâteaux.",
        activeEnglish = "The children were eating the cakes.",
        passive = "Les gâteaux étaient mangés par les enfants.",
        passiveEnglish = "The cakes were being eaten by the children."
    ),
    PassiveExample(
        active = "Le gouvernement a voté la loi.",
        activeEnglish = "The government passed the law.",
        passive = "La loi a été votée par le gouvernement.",
        passiveEnglish = "The law was passed by the government."
    ),
    PassiveExample(
        active = "On construira le pont.",
        activeEnglish = "They will build the bridge.",
        passive = "Le pont sera construit.",
        passiveEnglish = "The bridge will be built."
    ),
    PassiveExample(
        active = "Tout le monde aimait ce chanteur.",
        activeEnglish = "Everyone loved this singer.",
        passive = "Ce chanteur était aimé de tous.",
        passiveEnglish = "This singer was loved by all.",
        note = "Verbs of feeling/state use de instead of par."
    ),
    PassiveExample(
        active = "On a volé ma voiture.",
        activeEnglish = "They stole my car.",
        passive = "Ma voiture a été volée.",
        passiveEnglish = "My car was stolen.",
        note = "French strongly prefers the on construction: On a volé ma voiture."
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PassiveScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Passive Voice", color = Color.White) },
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
                    text = "In the passive voice, the subject receives the action rather than performing it. French forms the passive with être + past participle, which must agree in gender and number with the subject.",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }

            // Formation
            item { PassiveSectionHeader("Formation") }
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFE8EDF8)),
                    elevation = CardDefaults.cardElevation(2.dp)
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Text(
                            text = "être (conjugated) + past participle",
                            fontWeight = FontWeight.Bold,
                            color = FrenchBlue,
                            fontSize = 15.sp,
                            modifier = Modifier.padding(bottom = 10.dp)
                        )
                        HorizontalDivider(color = FrenchBlue.copy(alpha = 0.25f))
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "Active",
                            fontWeight = FontWeight.SemiBold,
                            color = FrenchNavy,
                            style = MaterialTheme.typography.bodySmall
                        )
                        Text(
                            text = "Le chat mange la souris.",
                            fontStyle = FontStyle.Italic,
                            color = FrenchBlue,
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            text = "The cat eats the mouse.",
                            style = MaterialTheme.typography.bodySmall,
                            color = FrenchNavy,
                            modifier = Modifier.padding(bottom = 10.dp)
                        )
                        HorizontalDivider(color = FrenchBlue.copy(alpha = 0.25f))
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "Passive",
                            fontWeight = FontWeight.SemiBold,
                            color = FrenchNavy,
                            style = MaterialTheme.typography.bodySmall
                        )
                        Text(
                            text = "La souris est mangée par le chat.",
                            fontStyle = FontStyle.Italic,
                            color = FrenchBlue,
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            text = "The mouse is eaten by the cat.",
                            style = MaterialTheme.typography.bodySmall,
                            color = FrenchNavy
                        )
                    }
                }
            }

            // Tenses
            item {
                Spacer(modifier = Modifier.height(12.dp))
                PassiveSectionHeader("Tenses in the Passive")
            }
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(2.dp)
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        tenseRows.forEachIndexed { index, row ->
                            if (index > 0) {
                                HorizontalDivider(
                                    color = FrenchBlue.copy(alpha = 0.15f),
                                    modifier = Modifier.padding(vertical = 8.dp)
                                )
                            }
                            Row(modifier = Modifier.fillMaxWidth()) {
                                Text(
                                    text = row.tense,
                                    fontWeight = FontWeight.SemiBold,
                                    color = FrenchNavy,
                                    fontSize = 12.sp,
                                    modifier = Modifier.width(110.dp)
                                )
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = row.example,
                                        fontStyle = FontStyle.Italic,
                                        color = FrenchBlue,
                                        style = MaterialTheme.typography.bodySmall
                                    )
                                    Text(
                                        text = row.english,
                                        style = MaterialTheme.typography.bodySmall,
                                        color = FrenchNavy
                                    )
                                }
                            }
                        }
                    }
                }
            }

            // Agreement
            item {
                Spacer(modifier = Modifier.height(12.dp))
                PassiveSectionHeader("Agreement of the Past Participle")
            }
            item {
                PassiveRuleCard(
                    body = "The past participle agrees in gender and number with the subject of the passive sentence."
                )
            }
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(2.dp)
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        agreementRows.forEachIndexed { index, row ->
                            if (index > 0) {
                                HorizontalDivider(
                                    color = FrenchBlue.copy(alpha = 0.15f),
                                    modifier = Modifier.padding(vertical = 8.dp)
                                )
                            }
                            Row(modifier = Modifier.fillMaxWidth()) {
                                Text(
                                    text = row.subject,
                                    fontWeight = FontWeight.SemiBold,
                                    color = FrenchNavy,
                                    fontSize = 12.sp,
                                    modifier = Modifier.width(155.dp)
                                )
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = row.form,
                                        fontStyle = FontStyle.Italic,
                                        color = FrenchBlue,
                                        fontWeight = FontWeight.Bold,
                                        style = MaterialTheme.typography.bodySmall
                                    )
                                    Text(
                                        text = row.english,
                                        style = MaterialTheme.typography.bodySmall,
                                        color = FrenchNavy
                                    )
                                }
                            }
                        }
                    }
                }
            }

            // par vs de
            item {
                Spacer(modifier = Modifier.height(12.dp))
                PassiveSectionHeader("The Agent: par vs de")
            }
            item {
                PassiveRuleCard(
                    body = "par (by) — used with action verbs:\n" +
                        "La lettre est écrite par Marie. — The letter is written by Marie.\n\n" +
                        "de (by / of) — used with verbs of feeling, state, or habitual action:\n" +
                        "Ce chanteur est aimé de tous. — This singer is loved by all.\n" +
                        "Il est respecté de ses collègues. — He is respected by his colleagues."
                )
            }

            // Avoiding passive with "on"
            item {
                Spacer(modifier = Modifier.height(12.dp))
                PassiveSectionHeader("Avoiding the Passive with \"on\"")
            }
            item {
                PassiveRuleCard(
                    body = "French often prefers an active construction using on (one / they / people) instead of the passive. This sounds more natural in everyday speech.\n\n" +
                        "Passive:  Ma voiture a été volée.\n" +
                        "With on:  On a volé ma voiture.\n" +
                        "(Both mean: My car was stolen.)\n\n" +
                        "Passive:  La réunion a été annulée.\n" +
                        "With on:  On a annulé la réunion.\n" +
                        "(Both mean: The meeting was cancelled.)"
                )
            }

            // Worked examples
            item {
                Spacer(modifier = Modifier.height(12.dp))
                PassiveSectionHeader("Worked Examples")
            }
            items(workedExamples.size) { i -> PassiveExampleCard(workedExamples[i]) }

            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}

@Composable
private fun PassiveSectionHeader(title: String) {
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
private fun PassiveRuleCard(body: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE8EDF8)),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Text(
            text = body,
            style = MaterialTheme.typography.bodySmall,
            color = FrenchNavy,
            modifier = Modifier.padding(12.dp),
            lineHeight = 19.sp
        )
    }
}

@Composable
private fun PassiveExampleCard(example: PassiveExample) {
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
                .padding(horizontal = 14.dp, vertical = 10.dp)
        ) {
            Text(
                text = "Active",
                fontWeight = FontWeight.SemiBold,
                color = FrenchNavy,
                fontSize = 11.sp
            )
            Text(
                text = example.active,
                fontStyle = FontStyle.Italic,
                color = FrenchBlue,
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = example.activeEnglish,
                style = MaterialTheme.typography.bodySmall,
                color = FrenchNavy,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            HorizontalDivider(color = FrenchBlue.copy(alpha = 0.2f))
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Passive",
                fontWeight = FontWeight.SemiBold,
                color = FrenchNavy,
                fontSize = 11.sp
            )
            Text(
                text = example.passive,
                fontStyle = FontStyle.Italic,
                color = FrenchBlue,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = example.passiveEnglish,
                style = MaterialTheme.typography.bodySmall,
                color = FrenchNavy
            )
            if (example.note != null) {
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = example.note,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    lineHeight = 18.sp
                )
            }
        }
    }
}
