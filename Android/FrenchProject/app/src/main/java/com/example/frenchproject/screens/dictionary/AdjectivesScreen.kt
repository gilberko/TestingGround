package com.example.frenchproject.screens.dictionary

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

private data class Adjective(
    val masculine: String,
    val feminine: String,
    val english: String,
    val note: String? = null
)

private data class AdjSection(val title: String, val adjectives: List<Adjective>)

private val adjSections = listOf(
    AdjSection("Size & Quantity", listOf(
        Adjective("grand", "grande", "big / tall"),
        Adjective("petit", "petite", "small / short"),
        Adjective("gros", "grosse", "fat / large / big"),
        Adjective("long", "longue", "long"),
        Adjective("court", "courte", "short (length)"),
        Adjective("large", "large", "wide / broad"),
        Adjective("étroit", "étroite", "narrow")
    )),
    AdjSection("Quality & Appearance", listOf(
        Adjective("bon", "bonne", "good"),
        Adjective("mauvais", "mauvaise", "bad"),
        Adjective("beau / bel", "belle", "beautiful / handsome", "bel used before masc. vowel: un bel homme"),
        Adjective("joli", "jolie", "pretty / nice-looking"),
        Adjective("laid", "laide", "ugly"),
        Adjective("propre", "propre", "clean / own"),
        Adjective("sale", "sale", "dirty")
    )),
    AdjSection("Age & Time", listOf(
        Adjective("vieux / vieil", "vieille", "old", "vieil before masc. vowel: un vieil ami"),
        Adjective("jeune", "jeune", "young"),
        Adjective("nouveau / nouvel", "nouvelle", "new", "nouvel before masc. vowel: un nouvel ami"),
        Adjective("ancien", "ancienne", "old / former / ancient"),
        Adjective("moderne", "moderne", "modern")
    )),
    AdjSection("Physical Qualities", listOf(
        Adjective("fort", "forte", "strong"),
        Adjective("faible", "faible", "weak"),
        Adjective("rapide", "rapide", "fast / quick"),
        Adjective("lent", "lente", "slow"),
        Adjective("chaud", "chaude", "hot / warm"),
        Adjective("froid", "froide", "cold"),
        Adjective("lourd", "lourde", "heavy"),
        Adjective("léger", "légère", "light (weight)")
    )),
    AdjSection("Emotions & Character", listOf(
        Adjective("heureux", "heureuse", "happy"),
        Adjective("triste", "triste", "sad"),
        Adjective("content", "contente", "pleased / happy"),
        Adjective("sympa / sympathique", "sympa / sympathique", "nice / friendly"),
        Adjective("gentil", "gentille", "kind / nice"),
        Adjective("méchant", "méchante", "mean / naughty"),
        Adjective("drôle", "drôle", "funny"),
        Adjective("sérieux", "sérieuse", "serious"),
        Adjective("calme", "calme", "calm / quiet"),
        Adjective("nerveux", "nerveuse", "nervous / irritable"),
        Adjective("timide", "timide", "shy"),
        Adjective("courageux", "courageuse", "brave / courageous"),
        Adjective("paresseux", "paresseuse", "lazy")
    )),
    AdjSection("Difficulty & Interest", listOf(
        Adjective("facile", "facile", "easy"),
        Adjective("difficile", "difficile", "difficult"),
        Adjective("intéressant", "intéressante", "interesting"),
        Adjective("ennuyeux", "ennuyeuse", "boring"),
        Adjective("important", "importante", "important"),
        Adjective("utile", "utile", "useful"),
        Adjective("inutile", "inutile", "useless")
    )),
    AdjSection("Social & Economic", listOf(
        Adjective("libre", "libre", "free"),
        Adjective("occupé", "occupée", "busy"),
        Adjective("riche", "riche", "rich"),
        Adjective("pauvre", "pauvre", "poor"),
        Adjective("célèbre", "célèbre", "famous"),
        Adjective("seul", "seule", "alone / only")
    ))
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdjectivesScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Adjectives", color = Color.White) },
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
                    text = "French adjectives agree in gender and number with the noun they describe. Most are placed after the noun. However, a small group — BANGS adjectives — come before: Beauty (beau, joli), Age (vieux, jeune, nouveau, ancien), Number (premier, dernier), Goodness (bon, mauvais), Size (grand, petit, gros, long, court).",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                Text(
                    text = "M = masculine  •  F = feminine",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(bottom = 6.dp)
                )
            }
            adjSections.forEach { section ->
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
                items(section.adjectives.size) { i -> AdjCard(section.adjectives[i]) }
            }
            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}

@Composable
private fun AdjCard(adj: Adjective) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 3.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 9.dp)
        ) {
            Row {
                Text(
                    text = if (adj.masculine == adj.feminine) adj.masculine
                           else "${adj.masculine} / ${adj.feminine}",
                    fontWeight = FontWeight.Bold,
                    color = FrenchBlue,
                    fontSize = 14.sp,
                    modifier = Modifier.width(180.dp)
                )
                Text(
                    text = adj.english,
                    style = MaterialTheme.typography.bodyMedium,
                    color = FrenchNavy
                )
            }
            if (adj.note != null) {
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = adj.note,
                    style = MaterialTheme.typography.bodySmall,
                    fontStyle = FontStyle.Italic,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}
