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

private data class Pronoun(
    val french: String,
    val english: String,
    val note: String? = null
)

private val pronouns = listOf(
    Pronoun("je (j')", "I", "Becomes j' before a vowel or silent h"),
    Pronoun("tu", "you (singular, informal)", "Used with friends, family, children, pets"),
    Pronoun("il", "he / it (masculine)"),
    Pronoun("elle", "she / it (feminine)"),
    Pronoun("on", "one / we (informal)", "Often used in conversation instead of nous"),
    Pronoun("nous", "we (formal / written)"),
    Pronoun("vous", "you (plural OR formal singular)", "See dedicated card below"),
    Pronoun("ils", "they (masculine or mixed group)"),
    Pronoun("elles", "they (all-feminine group)")
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubjectPronounsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Subject Pronouns", color = Color.White) },
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
                    text = "French has nine subject pronouns. The subject is the person or thing performing the action of the verb.",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
            items(pronouns.size) { index ->
                PronounCard(pronouns[index])
            }
            item {
                Spacer(modifier = Modifier.height(12.dp))
                SectionHeader("The Special Case of « vous »")
            }
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFE8EDF8)),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "vous has two distinct uses:",
                            fontWeight = FontWeight.SemiBold,
                            color = FrenchNavy
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "1. Plural of tu — addressing two or more people, regardless of formality.",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            text = "   Example: Vous êtes mes amis. (You are my friends.)",
                            style = MaterialTheme.typography.bodySmall,
                            fontStyle = FontStyle.Italic,
                            color = FrenchBlue
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "2. Polite / formal singular — addressing a single person you don't know well, an elder, a superior, or anyone to whom you wish to show respect.",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            text = "   Example: Vous êtes le directeur ? (Are you the principal?)",
                            style = MaterialTheme.typography.bodySmall,
                            fontStyle = FontStyle.Italic,
                            color = FrenchBlue
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        HorizontalDivider(color = FrenchBlue.copy(alpha = 0.3f))
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "Vouvoyer vs. Tutoyer",
                            fontWeight = FontWeight.SemiBold,
                            color = FrenchNavy
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "• Vouvoyer — using vous with someone (formal/respectful)\n• Tutoyer — using tu with someone (informal/familiar)\n\nChoosing the wrong form can come across as rude or overly stiff. When in doubt with a stranger or an older person, default to vous. They may invite you to tutoyer.",
                            style = MaterialTheme.typography.bodyMedium,
                            lineHeight = 22.sp
                        )
                    }
                }
            }
            item {
                Spacer(modifier = Modifier.height(12.dp))
                SectionHeader("« on » — the informal we")
            }
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "In everyday spoken French, on is used far more often than nous to mean \"we\".",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "on takes a third-person singular verb form (same as il/elle).",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "On va au cinéma ce soir. → We're going to the cinema tonight.",
                            style = MaterialTheme.typography.bodySmall,
                            fontStyle = FontStyle.Italic,
                            color = FrenchBlue
                        )
                        Text(
                            text = "Nous allons au cinéma ce soir. → (same meaning, more formal)",
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
private fun PronounCard(pronoun: Pronoun) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 10.dp)
        ) {
            Text(
                text = pronoun.french,
                fontWeight = FontWeight.Bold,
                color = FrenchBlue,
                fontSize = 16.sp,
                modifier = Modifier.width(90.dp)
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
private fun SectionHeader(title: String) {
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
