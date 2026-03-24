package com.example.app2.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp

private data class AdverbEntry(val pt: String, val en: String)
private data class AdverbCategory(val title: String, val entries: List<AdverbEntry>)

private val adverbCategories = listOf(
    AdverbCategory(
        "Frequency",
        listOf(
            AdverbEntry("sempre", "always"),
            AdverbEntry("normalmente", "normally / usually"),
            AdverbEntry("frequentemente", "frequently"),
            AdverbEntry("às vezes", "sometimes"),
            AdverbEntry("raramente", "rarely"),
            AdverbEntry("nunca", "never"),
            AdverbEntry("já", "already / now"),
            AdverbEntry("ainda", "still / yet"),
            AdverbEntry("de vez em quando", "from time to time"),
            AdverbEntry("todos os dias", "every day")
        )
    ),
    AdverbCategory(
        "Quantity & Degree",
        listOf(
            AdverbEntry("muito", "a lot / very"),
            AdverbEntry("pouco", "a little / not much"),
            AdverbEntry("bastante", "quite / enough"),
            AdverbEntry("demasiado", "too much"),
            AdverbEntry("mais", "more"),
            AdverbEntry("menos", "less"),
            AdverbEntry("apenas", "only / just"),
            AdverbEntry("só", "only"),
            AdverbEntry("quase", "almost"),
            AdverbEntry("completamente", "completely"),
            AdverbEntry("absolutamente", "absolutely"),
            AdverbEntry("mais ou menos", "more or less")
        )
    ),
    AdverbCategory(
        "Manner",
        listOf(
            AdverbEntry("bem", "well"),
            AdverbEntry("mal", "badly"),
            AdverbEntry("devagar", "slowly"),
            AdverbEntry("depressa", "quickly"),
            AdverbEntry("assim", "like this / this way"),
            AdverbEntry("juntos", "together"),
            AdverbEntry("sozinho / sozinha", "alone"),
            AdverbEntry("de repente", "suddenly")
        )
    ),
    AdverbCategory(
        "Place",
        listOf(
            AdverbEntry("aqui", "here"),
            AdverbEntry("ali", "there (near)"),
            AdverbEntry("lá", "there (far)"),
            AdverbEntry("acolá", "over there"),
            AdverbEntry("dentro", "inside"),
            AdverbEntry("fora", "outside"),
            AdverbEntry("cima", "up / above"),
            AdverbEntry("baixo", "down / below"),
            AdverbEntry("perto", "nearby"),
            AdverbEntry("longe", "far away"),
            AdverbEntry("em frente", "in front"),
            AdverbEntry("atrás", "behind")
        )
    ),
    AdverbCategory(
        "Affirmation & Negation",
        listOf(
            AdverbEntry("sim", "yes"),
            AdverbEntry("não", "no"),
            AdverbEntry("claro", "of course"),
            AdverbEntry("certamente", "certainly"),
            AdverbEntry("talvez", "maybe / perhaps"),
            AdverbEntry("provavelmente", "probably"),
            AdverbEntry("de facto", "in fact"),
            AdverbEntry("exactamente", "exactly")
        )
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdverbsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Adverbs") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(innerPadding),
            contentPadding = androidx.compose.foundation.layout.PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(adverbCategories) { category ->
                ElevatedCard(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        Text(
                            text = category.title,
                            style = MaterialTheme.typography.titleMedium
                        )
                        category.entries.forEach { entry ->
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = entry.pt,
                                    style = MaterialTheme.typography.bodyMedium,
                                    fontStyle = FontStyle.Italic,
                                    modifier = Modifier.weight(1f)
                                )
                                Text(
                                    text = entry.en,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
