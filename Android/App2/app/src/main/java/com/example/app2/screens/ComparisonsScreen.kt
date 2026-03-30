package com.example.app2.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

private data class IrregularComparison(
    val adjective: String,
    val comparative: String,
    val superlative: String,
    val absoluteSuperlative: String
)

private data class ComparisonAdjEntry(
    val pt: String,
    val en: String,
    val absoluteSuperlative: String
)

private val irregularForms = listOf(
    IrregularComparison("bom / boa", "melhor", "o/a melhor", "ótimo/a"),
    IrregularComparison("mau / má", "pior", "o/a pior", "péssimo/a"),
    IrregularComparison("grande", "maior", "o/a maior", "grandíssimo/a"),
    IrregularComparison("pequeno/a", "menor", "o/a menor", "pequeníssimo/a")
)

private val commonAdjectives = listOf(
    ComparisonAdjEntry("alto/a", "tall / high", "altíssimo/a"),
    ComparisonAdjEntry("baixo/a", "short / low", "baixíssimo/a"),
    ComparisonAdjEntry("rápido/a", "fast", "rapidíssimo/a"),
    ComparisonAdjEntry("lento/a", "slow", "lentíssimo/a"),
    ComparisonAdjEntry("caro/a", "expensive", "caríssimo/a"),
    ComparisonAdjEntry("barato/a", "cheap", "baratíssimo/a"),
    ComparisonAdjEntry("novo/a", "new / young", "novíssimo/a"),
    ComparisonAdjEntry("velho/a", "old", "velhíssimo/a"),
    ComparisonAdjEntry("belo/a", "beautiful", "belíssimo/a"),
    ComparisonAdjEntry("feio/a", "ugly", "feíssimo/a"),
    ComparisonAdjEntry("forte", "strong", "fortíssimo/a"),
    ComparisonAdjEntry("fraco/a", "weak", "fraquíssimo/a"),
    ComparisonAdjEntry("fácil", "easy", "facílimo/a"),
    ComparisonAdjEntry("difícil", "difficult", "dificílimo/a"),
    ComparisonAdjEntry("quente", "hot", "quentíssimo/a"),
    ComparisonAdjEntry("frio/a", "cold", "friíssimo/a"),
    ComparisonAdjEntry("pesado/a", "heavy", "pesadíssimo/a"),
    ComparisonAdjEntry("leve", "light", "levíssimo/a")
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComparisonsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Comparisons") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item { Spacer(modifier = Modifier.height(4.dp)) }

            // Comparative
            item {
                ElevatedCard(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(
                            text = "Comparativo — Comparative",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        listOf(
                            Triple("mais … do que", "more … than", "Este carro é mais rápido do que aquele."),
                            Triple("menos … do que", "less … than", "Este livro é menos interessante do que aquele."),
                            Triple("tão … como", "as … as", "Ela é tão alta como o irmão.")
                        ).forEach { t ->
                            Column {
                                Text(
                                    text = t.first,
                                    style = MaterialTheme.typography.bodyMedium,
                                    fontWeight = FontWeight.SemiBold
                                )
                                Text(
                                    text = t.second,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    modifier = Modifier.padding(bottom = 2.dp)
                                )
                                Text(
                                    text = t.third,
                                    style = MaterialTheme.typography.bodySmall,
                                    fontStyle = FontStyle.Italic,
                                    modifier = Modifier.padding(start = 8.dp, bottom = 8.dp)
                                )
                            }
                        }
                    }
                }
            }

            // Superlative
            item {
                ElevatedCard(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(
                            text = "Superlativo Relativo — Relative Superlative",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        listOf(
                            Triple("o/a/os/as mais …", "the most …", "É o restaurante mais caro da cidade."),
                            Triple("o/a/os/as menos …", "the least …", "É o carro menos potente do grupo.")
                        ).forEach { t ->
                            Column {
                                Text(
                                    text = t.first,
                                    style = MaterialTheme.typography.bodyMedium,
                                    fontWeight = FontWeight.SemiBold
                                )
                                Text(
                                    text = t.second,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    modifier = Modifier.padding(bottom = 2.dp)
                                )
                                Text(
                                    text = t.third,
                                    style = MaterialTheme.typography.bodySmall,
                                    fontStyle = FontStyle.Italic,
                                    modifier = Modifier.padding(start = 8.dp, bottom = 8.dp)
                                )
                            }
                        }
                        Text(
                            text = "Note: the article (o/a/os/as) must agree in gender and number with the noun.",
                            style = MaterialTheme.typography.bodySmall,
                            fontStyle = FontStyle.Italic,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

            // Absolute Superlative
            item {
                ElevatedCard(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(
                            text = "Superlativo Absoluto — Absolute Superlative",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(bottom = 4.dp)
                        )
                        Text(
                            text = "Intensifies an adjective without comparing to anything else.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        Text(
                            text = "muito + adjective:",
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            text = "Estou muito cansado.",
                            style = MaterialTheme.typography.bodySmall,
                            fontStyle = FontStyle.Italic,
                            modifier = Modifier.padding(start = 8.dp, bottom = 8.dp)
                        )
                        Text(
                            text = "adjective + -íssimo/a (general rule):",
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.SemiBold
                        )
                        listOf(
                            "caro → caríssimo/a" to "Este hotel é caríssimo.",
                            "quente → quentíssimo/a" to "A sopa está quentíssima."
                        ).forEach { p ->
                            Column {
                                Text(
                                    text = p.first,
                                    style = MaterialTheme.typography.bodySmall,
                                    modifier = Modifier.padding(start = 8.dp)
                                )
                                Text(
                                    text = p.second,
                                    style = MaterialTheme.typography.bodySmall,
                                    fontStyle = FontStyle.Italic,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    modifier = Modifier.padding(start = 16.dp, bottom = 4.dp)
                                )
                            }
                        }
                        Text(
                            text = "adjective + -ílimo/a (adjectives ending in -il):",
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.SemiBold
                        )
                        listOf("fácil → facílimo/a", "difícil → dificílimo/a").forEach { form ->
                            Text(
                                text = form,
                                style = MaterialTheme.typography.bodySmall,
                                modifier = Modifier.padding(start = 8.dp, bottom = 2.dp)
                            )
                        }
                    }
                }
            }

            // Irregular Forms
            item {
                ElevatedCard(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(
                            text = "Formas Irregulares — Irregular Forms",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        Row(modifier = Modifier.fillMaxWidth()) {
                            listOf("Adjective", "Comparative", "Superlative", "Absolute").forEach { header ->
                                Text(
                                    text = header,
                                    style = MaterialTheme.typography.bodySmall,
                                    fontWeight = FontWeight.SemiBold,
                                    modifier = Modifier.weight(1f)
                                )
                            }
                        }
                        HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))
                        irregularForms.forEach { entry ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 3.dp)
                            ) {
                                Text(
                                    text = entry.adjective,
                                    style = MaterialTheme.typography.bodySmall,
                                    fontStyle = FontStyle.Italic,
                                    modifier = Modifier.weight(1f)
                                )
                                Text(
                                    text = entry.comparative,
                                    style = MaterialTheme.typography.bodySmall,
                                    modifier = Modifier.weight(1f)
                                )
                                Text(
                                    text = entry.superlative,
                                    style = MaterialTheme.typography.bodySmall,
                                    modifier = Modifier.weight(1f)
                                )
                                Text(
                                    text = entry.absoluteSuperlative,
                                    style = MaterialTheme.typography.bodySmall,
                                    modifier = Modifier.weight(1f)
                                )
                            }
                        }
                    }
                }
            }

            // Common adjectives
            item {
                ElevatedCard(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(
                            text = "Common Adjectives for Comparisons",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        Row(modifier = Modifier.fillMaxWidth()) {
                            listOf("Portuguese", "English", "Absolute Superlative").forEach { header ->
                                Text(
                                    text = header,
                                    style = MaterialTheme.typography.bodySmall,
                                    fontWeight = FontWeight.SemiBold,
                                    modifier = Modifier.weight(1f)
                                )
                            }
                        }
                        HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))
                        commonAdjectives.forEach { entry ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 2.dp)
                            ) {
                                Text(
                                    text = entry.pt,
                                    style = MaterialTheme.typography.bodySmall,
                                    fontStyle = FontStyle.Italic,
                                    modifier = Modifier.weight(1f)
                                )
                                Text(
                                    text = entry.en,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    modifier = Modifier.weight(1f)
                                )
                                Text(
                                    text = entry.absoluteSuperlative,
                                    style = MaterialTheme.typography.bodySmall,
                                    modifier = Modifier.weight(1f)
                                )
                            }
                        }
                    }
                }
            }

            item { Spacer(modifier = Modifier.height(16.dp)) }
        }
    }
}
