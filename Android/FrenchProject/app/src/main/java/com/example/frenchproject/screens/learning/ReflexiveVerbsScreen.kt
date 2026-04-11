package com.example.frenchproject.screens.learning

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.frenchproject.ui.theme.FrenchBlue
import com.example.frenchproject.ui.theme.FrenchNavy

private data class ReflexiveRow(val subject: String, val reflexive: String, val form: String)

private val appellerRows = listOf(
    ReflexiveRow("je", "me / m'", "m'appelle"),
    ReflexiveRow("tu", "te / t'", "t'appelles"),
    ReflexiveRow("il / elle / on", "se / s'", "s'appelle"),
    ReflexiveRow("nous", "nous", "nous appelons"),
    ReflexiveRow("vous", "vous", "vous appelez"),
    ReflexiveRow("ils / elles", "se / s'", "s'appellent")
)

private val leverRows = listOf(
    ReflexiveRow("je", "me", "me lève"),
    ReflexiveRow("tu", "te", "te lèves"),
    ReflexiveRow("il / elle / on", "se", "se lève"),
    ReflexiveRow("nous", "nous", "nous levons"),
    ReflexiveRow("vous", "vous", "vous levez"),
    ReflexiveRow("ils / elles", "se", "se lèvent")
)

private val coucherRows = listOf(
    ReflexiveRow("je", "me", "me couche"),
    ReflexiveRow("tu", "te", "te couches"),
    ReflexiveRow("il / elle / on", "se", "se couche"),
    ReflexiveRow("nous", "nous", "nous couchons"),
    ReflexiveRow("vous", "vous", "vous couchez"),
    ReflexiveRow("ils / elles", "se", "se couchent")
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReflexiveVerbsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Reflexive Verbs", color = Color.White) },
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

            // ── What are reflexive verbs ─────────────────────────────────────
            item {
                Text(
                    text = "A reflexive verb is one where the subject performs the action on itself — \"I wash myself\", \"she calls herself\", \"we get up\". In French these verbs are identified by the pronoun se (or s') in front of the infinitive: se lever (to get up), s'appeler (to be called), se coucher (to go to bed).",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }

            // ── Reflexive pronouns ───────────────────────────────────────────
            item { RefSectionHeader("Reflexive Pronouns") }
            item {
                Card(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(2.dp)
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        val pronounPairs = listOf(
                            "je" to "me (m' before vowel)",
                            "tu" to "te (t' before vowel)",
                            "il / elle / on" to "se (s' before vowel)",
                            "nous" to "nous",
                            "vous" to "vous",
                            "ils / elles" to "se (s' before vowel)"
                        )
                        pronounPairs.forEachIndexed { i, (subj, pron) ->
                            if (i > 0) HorizontalDivider(color = Color(0xFFE0E0E0), modifier = Modifier.padding(vertical = 3.dp))
                            Row(modifier = Modifier.fillMaxWidth()) {
                                Text(
                                    text = subj,
                                    color = FrenchNavy,
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 13.sp,
                                    modifier = Modifier.width(120.dp)
                                )
                                Text(
                                    text = pron,
                                    color = FrenchBlue,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 13.sp
                                )
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "The reflexive pronoun is placed between the subject and the verb. It contracts to m'/t'/s' before a vowel or silent h.",
                    style = MaterialTheme.typography.bodySmall,
                    fontStyle = FontStyle.Italic,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }

            // ── s'appeler ────────────────────────────────────────────────────
            item {
                Spacer(modifier = Modifier.height(8.dp))
                RefSectionHeader("s'appeler — to be called / to call oneself (Présent)")
            }
            item {
                ReflexiveConjCard(appellerRows)
                Spacer(modifier = Modifier.height(6.dp))
                ExCard {
                    RefExample("Je m'appelle Marie.", "My name is Marie. (lit. I call myself Marie.)")
                    RefExample("Comment tu t'appelles ?", "What's your name?")
                    RefExample("Il s'appelle Paul.", "His name is Paul.")
                }
            }

            // ── se lever ─────────────────────────────────────────────────────
            item {
                Spacer(modifier = Modifier.height(12.dp))
                RefSectionHeader("se lever — to get up (Présent)")
            }
            item {
                ReflexiveConjCard(leverRows)
                Spacer(modifier = Modifier.height(6.dp))
                ExCard {
                    RefExample("Je me lève à sept heures.", "I get up at seven o'clock.")
                    RefExample("Elle se lève tôt.", "She gets up early.")
                    RefExample("Nous nous levons tard le week-end.", "We get up late on weekends.")
                }
            }

            // ── se coucher ───────────────────────────────────────────────────
            item {
                Spacer(modifier = Modifier.height(12.dp))
                RefSectionHeader("se coucher — to go to bed (Présent)")
            }
            item {
                ReflexiveConjCard(coucherRows)
                Spacer(modifier = Modifier.height(6.dp))
                ExCard {
                    RefExample("Tu te couches tard.", "You go to bed late.")
                    RefExample("Ils se couchent à minuit.", "They go to bed at midnight.")
                }
            }

            // ── Negation ─────────────────────────────────────────────────────
            item {
                Spacer(modifier = Modifier.height(12.dp))
                RefSectionHeader("Negation with Reflexive Verbs")
            }
            item {
                Card(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(2.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            "In negation, ne comes before the reflexive pronoun, and pas comes after the verb.",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        RefCodeBlock("je ne me lève pas    →  I don't get up\ntu ne t'appelles pas  →  you are not called\nil ne se couche pas   →  he doesn't go to bed")
                    }
                }
            }

            // ── Passé composé ────────────────────────────────────────────────
            item {
                Spacer(modifier = Modifier.height(12.dp))
                RefSectionHeader("Reflexive Verbs in the Passé Composé")
            }
            item {
                Card(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFE8EDF8)),
                    elevation = CardDefaults.cardElevation(2.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            "All reflexive verbs use ÊTRE as their auxiliary in the passé composé — never avoir.",
                            fontWeight = FontWeight.SemiBold,
                            color = FrenchNavy,
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            "Formation:  subject + reflexive pronoun + être (conjugated) + participe passé",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text("se lever — passé composé:", fontWeight = FontWeight.SemiBold, color = FrenchNavy, fontSize = 13.sp)
                        Spacer(modifier = Modifier.height(4.dp))
                        RefCodeBlock(
                            "je me suis levé(e)\n" +
                            "tu t'es levé(e)\n" +
                            "il s'est levé  /  elle s'est levée\n" +
                            "nous nous sommes levé(e)s\n" +
                            "vous vous êtes levé(e)(s)\n" +
                            "ils se sont levés  /  elles se sont levées"
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            "⚠ Agreement rule:",
                            fontWeight = FontWeight.SemiBold,
                            color = Color(0xFF8B0000),
                            fontSize = 13.sp
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            "The participe passé agrees in gender and number with the subject — just like other être verbs. Add -e for feminine, -s for plural, -es for feminine plural.",
                            style = MaterialTheme.typography.bodyMedium,
                            lineHeight = 22.sp
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        RefExample("Elle s'est levée tôt.", "She got up early. (feminine → -e added)")
                        RefExample("Ils se sont levés à midi.", "They got up at noon. (masc. plural → -s added)")
                        RefExample("Je me suis couché(e) tard.", "I went to bed late.")
                    }
                }
            }

            // ── Imparfait ────────────────────────────────────────────────────
            item {
                Spacer(modifier = Modifier.height(12.dp))
                RefSectionHeader("Reflexive Verbs in the Imparfait")
            }
            item {
                Card(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(2.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            "In the imparfait, conjugation is straightforward — just keep the reflexive pronoun in place and conjugate the verb normally using its imparfait stem.",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        RefCodeBlock(
                            "je me levais      (I used to get up / I was getting up)\n" +
                            "tu te levais\n" +
                            "il/elle se levait\n" +
                            "nous nous levions\n" +
                            "vous vous leviez\n" +
                            "ils/elles se levaient"
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        RefExample("Quand j'étais enfant, je me levais tôt.", "When I was a child, I used to get up early.")
                    }
                }
            }

            // ── Common reflexive verbs ───────────────────────────────────────
            item {
                Spacer(modifier = Modifier.height(12.dp))
                RefSectionHeader("Common Reflexive Verbs")
            }
            item {
                Card(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(2.dp)
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        val commonVerbs = listOf(
                            "s'appeler" to "to be called",
                            "se lever" to "to get up",
                            "se coucher" to "to go to bed",
                            "se réveiller" to "to wake up",
                            "se laver" to "to wash (oneself)",
                            "s'habiller" to "to get dressed",
                            "se déshabiller" to "to get undressed",
                            "se reposer" to "to rest",
                            "se souvenir (de)" to "to remember",
                            "s'asseoir" to "to sit down",
                            "se dépêcher" to "to hurry",
                            "se promener" to "to go for a walk",
                            "se sentir" to "to feel",
                            "s'amuser" to "to have fun",
                            "se tromper" to "to make a mistake",
                            "se retrouver" to "to meet up"
                        )
                        commonVerbs.forEachIndexed { i, (fr, en) ->
                            if (i > 0) HorizontalDivider(color = Color(0xFFEEEEEE), modifier = Modifier.padding(vertical = 3.dp))
                            Row(modifier = Modifier.fillMaxWidth()) {
                                Text(
                                    text = fr,
                                    fontWeight = FontWeight.Bold,
                                    color = FrenchBlue,
                                    fontSize = 13.sp,
                                    modifier = Modifier.width(160.dp)
                                )
                                Text(
                                    text = en,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = FrenchNavy
                                )
                            }
                        }
                    }
                }
            }

            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}

@Composable
private fun RefSectionHeader(title: String) {
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
private fun ReflexiveConjCard(rows: List<ReflexiveRow>) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            rows.forEachIndexed { i, row ->
                if (i > 0) HorizontalDivider(color = Color(0xFFE0E0E0), modifier = Modifier.padding(vertical = 3.dp))
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = row.subject,
                        color = FrenchNavy,
                        fontWeight = FontWeight.Medium,
                        fontSize = 13.sp,
                        modifier = Modifier.width(120.dp)
                    )
                    Text(
                        text = row.form,
                        fontWeight = FontWeight.Bold,
                        color = FrenchBlue,
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}

@Composable
private fun ExCard(content: @Composable ColumnScope.() -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF0F3FA)),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            content()
        }
    }
}

@Composable
private fun RefExample(french: String, english: String) {
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
        modifier = Modifier.padding(bottom = 4.dp)
    )
}

@Composable
private fun RefCodeBlock(code: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF0F3FA), RoundedCornerShape(4.dp))
            .padding(8.dp)
    ) {
        Text(
            text = code,
            fontFamily = FontFamily.Monospace,
            fontSize = 12.sp,
            color = FrenchNavy,
            lineHeight = 20.sp
        )
    }
}
