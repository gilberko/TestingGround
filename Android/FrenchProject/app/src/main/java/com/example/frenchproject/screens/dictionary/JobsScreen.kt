package com.example.frenchproject.screens.dictionary

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

private data class JobEntry(val masculine: String, val feminine: String, val english: String)
private data class JobSection(val title: String, val entries: List<JobEntry>, val note: String? = null)

private val jobSections = listOf(
    JobSection(
        "Medical",
        listOf(
            JobEntry("médecin", "médecin", "doctor"),
            JobEntry("infirmier", "infirmière", "nurse"),
            JobEntry("chirurgien", "chirurgienne", "surgeon"),
            JobEntry("dentiste", "dentiste", "dentist"),
            JobEntry("psychologue", "psychologue", "psychologist"),
            JobEntry("vétérinaire", "vétérinaire", "veterinarian")
        ),
        note = "médecin is traditionally invariable — both genders use the same form (le/la médecin). Dentiste and psychologue are also the same for both genders."
    ),
    JobSection(
        "Education & Training",
        listOf(
            JobEntry("professeur", "professeure", "teacher"),
            JobEntry("entraîneur", "entraîneuse", "trainer")
        ),
        note = "professeure is officially endorsed (especially in Canada/Quebec). In metropolitan France, le professeur is still commonly used for women."
    ),
    JobSection(
        "Legal & Finance",
        listOf(
            JobEntry("avocat", "avocate", "lawyer"),
            JobEntry("comptable", "comptable", "accountant")
        )
    ),
    JobSection(
        "Engineering",
        listOf(
            JobEntry("ingénieur", "ingénieure", "engineer")
        )
    ),
    JobSection(
        "Trades",
        listOf(
            JobEntry("plombier", "plombière", "plumber"),
            JobEntry("cordonnier", "cordonnière", "cobbler"),
            JobEntry("ouvrier du bâtiment", "ouvrière du bâtiment", "construction worker")
        )
    ),
    JobSection(
        "Administration",
        listOf(
            JobEntry("secrétaire", "secrétaire", "secretary")
        )
    ),
    JobSection(
        "Arts & Media",
        listOf(
            JobEntry("acteur", "actrice", "actor / actress"),
            JobEntry("réalisateur", "réalisatrice", "film director"),
            JobEntry("producteur", "productrice", "film producer")
        )
    ),
    JobSection(
        "Sports",
        listOf(
            JobEntry("athlète", "athlète", "athlete"),
            JobEntry("maître-nageur", "maître-nageuse", "lifeguard")
        )
    ),
    JobSection(
        "Food Service",
        listOf(
            JobEntry("serveur", "serveuse", "waiter / waitress"),
            JobEntry("chef", "cheffe", "chef"),
            JobEntry("barman", "barmaid", "bartender")
        ),
        note = "cheffe is the officially endorsed feminine form per the Académie française (2019). barmaid is an English loanword used in French."
    ),
    JobSection(
        "Retail",
        listOf(
            JobEntry("vendeur", "vendeuse", "salesperson"),
            JobEntry("commerçant", "commerçante", "shopkeeper / shopowner"),
            JobEntry("caissier", "caissière", "cashier")
        )
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JobsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Jobs & Professions", color = Color.White) },
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
                    text = "French job titles have grammatical gender. Many professions have distinct masculine and feminine forms. Some are invariable (same word for both genders).",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 4.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "Masculine",
                        fontWeight = FontWeight.SemiBold,
                        color = FrenchBlue,
                        fontSize = 12.sp,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = "Feminine",
                        fontWeight = FontWeight.SemiBold,
                        fontStyle = FontStyle.Italic,
                        color = FrenchBlue,
                        fontSize = 12.sp,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = "English",
                        fontWeight = FontWeight.SemiBold,
                        color = FrenchNavy,
                        fontSize = 12.sp,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
            jobSections.forEach { section ->
                item {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = section.title,
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = FrenchBlue,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                    HorizontalDivider(color = FrenchBlue.copy(alpha = 0.35f))
                    Spacer(modifier = Modifier.height(4.dp))
                }
                items(section.entries.size) { i -> JobEntryCard(section.entries[i]) }
                if (section.note != null) {
                    item {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            colors = CardDefaults.cardColors(containerColor = Color(0xFFE8EDF8)),
                            elevation = CardDefaults.cardElevation(2.dp)
                        ) {
                            Text(
                                text = section.note,
                                style = MaterialTheme.typography.bodySmall,
                                color = FrenchNavy,
                                modifier = Modifier.padding(12.dp),
                                lineHeight = 18.sp
                            )
                        }
                    }
                }
            }
            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}

@Composable
private fun JobEntryCard(entry: JobEntry) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 3.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 9.dp)
        ) {
            Text(
                text = entry.masculine,
                fontWeight = FontWeight.Bold,
                color = FrenchBlue,
                fontSize = 13.sp,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = entry.feminine,
                fontStyle = FontStyle.Italic,
                color = FrenchBlue,
                fontSize = 13.sp,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = entry.english,
                style = MaterialTheme.typography.bodyMedium,
                color = FrenchNavy,
                modifier = Modifier.weight(1f)
            )
        }
    }
}
