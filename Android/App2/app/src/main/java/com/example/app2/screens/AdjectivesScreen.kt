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

private data class AdjectiveEntry(val pt: String, val en: String)
private data class AdjectiveCategory(val title: String, val entries: List<AdjectiveEntry>)

private val adjectiveCategories = listOf(
    AdjectiveCategory(
        "Size & Quantity",
        listOf(
            AdjectiveEntry("grande", "big / large"),
            AdjectiveEntry("pequeno / pequena", "small"),
            AdjectiveEntry("longo / longa", "long"),
            AdjectiveEntry("curto / curta", "short"),
            AdjectiveEntry("alto / alta", "tall / high"),
            AdjectiveEntry("baixo / baixa", "short / low"),
            AdjectiveEntry("largo / larga", "wide"),
            AdjectiveEntry("estreito / estreita", "narrow"),
            AdjectiveEntry("gordo / gorda", "fat"),
            AdjectiveEntry("magro / magra", "thin"),
            AdjectiveEntry("muito / muita", "much / many"),
            AdjectiveEntry("pouco / pouca", "little / few")
        )
    ),
    AdjectiveCategory(
        "Quality & Appearance",
        listOf(
            AdjectiveEntry("bonito / bonita", "beautiful / pretty"),
            AdjectiveEntry("feio / feia", "ugly"),
            AdjectiveEntry("novo / nova", "new / young"),
            AdjectiveEntry("velho / velha", "old"),
            AdjectiveEntry("limpo / limpa", "clean"),
            AdjectiveEntry("sujo / suja", "dirty"),
            AdjectiveEntry("rápido / rápida", "fast"),
            AdjectiveEntry("lento / lenta", "slow"),
            AdjectiveEntry("fácil", "easy"),
            AdjectiveEntry("difícil", "difficult"),
            AdjectiveEntry("barato / barata", "cheap"),
            AdjectiveEntry("caro / cara", "expensive")
        )
    ),
    AdjectiveCategory(
        "Feelings & Character",
        listOf(
            AdjectiveEntry("feliz", "happy"),
            AdjectiveEntry("triste", "sad"),
            AdjectiveEntry("cansado / cansada", "tired"),
            AdjectiveEntry("doente", "sick / ill"),
            AdjectiveEntry("ocupado / ocupada", "busy"),
            AdjectiveEntry("livre", "free"),
            AdjectiveEntry("simpático / simpática", "friendly / nice"),
            AdjectiveEntry("antipático / antipática", "unfriendly"),
            AdjectiveEntry("inteligente", "intelligent"),
            AdjectiveEntry("estúpido / estúpida", "stupid"),
            AdjectiveEntry("forte", "strong"),
            AdjectiveEntry("fraco / fraca", "weak")
        )
    ),
    AdjectiveCategory(
        "Temperature & Condition",
        listOf(
            AdjectiveEntry("quente", "hot"),
            AdjectiveEntry("frio / fria", "cold"),
            AdjectiveEntry("fresco / fresca", "cool / fresh"),
            AdjectiveEntry("húmido / húmida", "humid"),
            AdjectiveEntry("seco / seca", "dry"),
            AdjectiveEntry("aberto / aberta", "open"),
            AdjectiveEntry("fechado / fechada", "closed"),
            AdjectiveEntry("cheio / cheia", "full"),
            AdjectiveEntry("vazio / vazia", "empty")
        )
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdjectivesScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Adjectives") },
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
            items(adjectiveCategories) { category ->
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
