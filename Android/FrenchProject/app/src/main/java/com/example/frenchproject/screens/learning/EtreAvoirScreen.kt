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

private data class ConjugationRow(val subject: String, val form: String, val meaning: String)

private val etreRows = listOf(
    ConjugationRow("je", "suis", "I am"),
    ConjugationRow("tu", "es", "you are"),
    ConjugationRow("il / elle / on", "est", "he / she / one is"),
    ConjugationRow("nous", "sommes", "we are"),
    ConjugationRow("vous", "êtes", "you are"),
    ConjugationRow("ils / elles", "sont", "they are")
)

private val avoirRows = listOf(
    ConjugationRow("j'", "ai", "I have"),
    ConjugationRow("tu", "as", "you have"),
    ConjugationRow("il / elle / on", "a", "he / she / one has"),
    ConjugationRow("nous", "avons", "we have"),
    ConjugationRow("vous", "avez", "you have"),
    ConjugationRow("ils / elles", "ont", "they have")
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EtreAvoirScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Être et Avoir", color = Color.White) },
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
                    text = "Être (to be) and avoir (to have) are the two most essential verbs in French. They are both highly irregular, and must be memorized. They also serve as auxiliary verbs in compound tenses such as the passé composé.",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }

            // Être
            item { VerbSectionHeader("Être — to be") }
            item {
                ConjugationCard(etreRows)
            }
            item {
                Spacer(modifier = Modifier.height(6.dp))
                Card(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFE8EDF8)),
                    elevation = CardDefaults.cardElevation(2.dp)
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Text("Common uses of être:", fontWeight = FontWeight.SemiBold, color = FrenchNavy)
                        Spacer(modifier = Modifier.height(4.dp))
                        BulletText("Identity and description: Je suis étudiant. (I am a student.)")
                        BulletText("Origin: Elle est française. (She is French.)")
                        BulletText("Location with être: Il est à Paris. (He is in Paris.)")
                        BulletText("Time: Il est midi. (It is noon.)")
                    }
                }
            }

            // Avoir
            item {
                Spacer(modifier = Modifier.height(12.dp))
                VerbSectionHeader("Avoir — to have")
            }
            item {
                ConjugationCard(avoirRows)
            }
            item {
                Spacer(modifier = Modifier.height(6.dp))
                Card(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFE8EDF8)),
                    elevation = CardDefaults.cardElevation(2.dp)
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Text("Common uses of avoir:", fontWeight = FontWeight.SemiBold, color = FrenchNavy)
                        Spacer(modifier = Modifier.height(4.dp))
                        BulletText("Possession: J'ai un chien. (I have a dog.)")
                        BulletText("Age: Elle a vingt ans. (She is twenty years old.)")
                        BulletText("Expressions: avoir faim (to be hungry), avoir soif (to be thirsty), avoir peur (to be afraid), avoir chaud/froid (to be hot/cold)")
                    }
                }
            }

            // Passé composé auxiliaries
            item {
                Spacer(modifier = Modifier.height(16.dp))
                VerbSectionHeader("Être and Avoir in the Passé Composé")
            }
            item {
                Text(
                    text = "In the passé composé (a past tense), each verb uses either être or avoir as its auxiliary, followed by the participe passé.",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }
            item {
                Card(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(2.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Verbs that use ÊTRE as auxiliary:", fontWeight = FontWeight.SemiBold, color = FrenchBlue)
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "• Verbs of motion and state of being: aller (to go), venir (to come), partir (to leave), arriver (to arrive), entrer (to enter), sortir (to go out), monter (to go up), descendre (to go down), naître (to be born), mourir (to die), rester (to stay), tomber (to fall), retourner (to return), passer (to pass).\n• All reflexive / pronominal verbs (e.g. se lever, se coucher).",
                            style = MaterialTheme.typography.bodyMedium,
                            lineHeight = 22.sp
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            "⚠ Agreement rule: when using être, the participe passé must agree in gender and number with the subject.",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color(0xFF8B0000),
                            fontStyle = FontStyle.Italic
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            "Il est allé. / Elle est allée. / Ils sont allés. / Elles sont allées.",
                            style = MaterialTheme.typography.bodySmall,
                            fontStyle = FontStyle.Italic,
                            color = FrenchBlue
                        )
                    }
                }
            }
            item {
                Spacer(modifier = Modifier.height(8.dp))
                Card(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(2.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Verbs that use AVOIR as auxiliary:", fontWeight = FontWeight.SemiBold, color = FrenchBlue)
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            "The vast majority of French verbs use avoir — including most transitive verbs (those that take a direct object).",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            "J'ai mangé une pomme. → I ate an apple.\nNous avons parlé. → We spoke.",
                            style = MaterialTheme.typography.bodySmall,
                            fontStyle = FontStyle.Italic,
                            color = FrenchBlue
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            "The participe passé does NOT agree with the subject when using avoir (unless a direct object precedes the verb — an advanced rule).",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}

@Composable
private fun VerbSectionHeader(title: String) {
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
private fun ConjugationCard(rows: List<ConjugationRow>) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            rows.forEachIndexed { index, row ->
                if (index > 0) HorizontalDivider(color = Color(0xFFE0E0E0), modifier = Modifier.padding(vertical = 4.dp))
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = row.subject,
                        fontWeight = FontWeight.Medium,
                        color = FrenchNavy,
                        modifier = Modifier.width(110.dp),
                        fontSize = 13.sp
                    )
                    Text(
                        text = row.form,
                        fontWeight = FontWeight.Bold,
                        color = FrenchBlue,
                        fontSize = 15.sp,
                        modifier = Modifier.width(80.dp)
                    )
                    Text(
                        text = row.meaning,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

@Composable
private fun BulletText(text: String) {
    Text(
        text = "• $text",
        style = MaterialTheme.typography.bodySmall,
        lineHeight = 20.sp,
        modifier = Modifier.padding(bottom = 2.dp)
    )
}
