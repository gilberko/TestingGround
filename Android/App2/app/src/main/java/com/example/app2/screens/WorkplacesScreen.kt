package com.example.app2.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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

private data class WorkEntry(val pt: String, val en: String, val note: String = "")

private val officeWords = listOf(
    WorkEntry("o trabalho", "work / job"),
    WorkEntry("o emprego", "job / employment", "more specific than \"trabalho\""),
    WorkEntry("o local de trabalho", "workplace"),
    WorkEntry("o escritório", "office"),
    WorkEntry("a caneta", "pen"),
    WorkEntry("a borracha", "eraser"),
    WorkEntry("o lápis", "pencil"),
    WorkEntry("o papel", "paper"),
    WorkEntry("a página", "page"),
    WorkEntry("a impressora", "printer"),
    WorkEntry("imprimir", "to print"),
    WorkEntry("as tesouras", "scissors", "always plural in PT"),
    WorkEntry("um diagrama", "a diagram")
)

private val architectureWords = listOf(
    WorkEntry("o engenheiro / a engenheira", "engineer"),
    WorkEntry("medir", "to measure"),
    WorkEntry("o arquiteto / a arquiteta", "architect", "EP spelling; BP: arquiteto/arquiteta"),
    WorkEntry("desenhar", "to design / to draw"),
    WorkEntry("a planta", "the blueprint / floor plan", "also: o projeto")
)

private val securityWords = listOf(
    WorkEntry("o polícia / a polícia", "police officer", "EP: \"o polícia\" m, \"a polícia\" f"),
    WorkEntry("a polícia", "the police (institution)"),
    WorkEntry("o oficial do exército / a oficial do exército", "army officer"),
    WorkEntry("o soldado / a soldada", "soldier"),
    WorkEntry("o exército", "army"),
    WorkEntry("uma arma", "a weapon"),
    WorkEntry("prender / deter", "to arrest"),
    WorkEntry("o uniforme", "uniform")
)

private val healthcareWords = listOf(
    WorkEntry("o médico / a médica", "doctor"),
    WorkEntry("tratar", "to treat"),
    WorkEntry("o enfermeiro / a enfermeira", "nurse"),
    WorkEntry("o farmacêutico / a farmacêutica", "pharmacist")
)

private val servicesWords = listOf(
    WorkEntry("o secretário / a secretária", "secretary"),
    WorkEntry("a loja", "a shop / store"),
    WorkEntry("o cabeleireiro / a cabeleireira", "hairdresser / barber", "EP general term"),
    WorkEntry("o barbeiro", "barber", "male-specific"),
    WorkEntry("cortar o cabelo", "to cut hair")
)

private val hospitalityWords = listOf(
    WorkEntry("o restaurante", "restaurant"),
    WorkEntry("o empregado de mesa / a empregada de mesa", "waiter / waitress", "EP term"),
    WorkEntry("o chef / a chef", "chef"),
    WorkEntry("o cozinheiro / a cozinheira", "cook"),
    WorkEntry("cozinhar", "to cook"),
    WorkEntry("a ementa", "menu", "EP term — BP: o cardápio"),
    WorkEntry("pedir", "to order"),
    WorkEntry("pagar", "to pay"),
    WorkEntry("a conta", "the bill")
)

private val tradesWords = listOf(
    WorkEntry("o canalizador / a canalizadora", "plumber", "EP term"),
    WorkEntry("os canos", "pipes"),
    WorkEntry("consertar / arranjar", "to fix", "\"arranjar\" more colloquial"),
    WorkEntry("o cano entupido", "clogged pipe")
)

private val cafeWords = listOf(
    WorkEntry("o/a barista", "barista"),
    WorkEntry("o café", "café / coffee shop"),
    WorkEntry("o expresso", "espresso", "EP spelling"),
    WorkEntry("o cappuccino", "cappuccino"),
    WorkEntry("a máquina de café", "coffee machine"),
    WorkEntry("torrar", "to roast"),
    WorkEntry("os grãos de café", "coffee beans"),
    WorkEntry("os grãos", "beans"),
    WorkEntry("o chá", "tea")
)

private val artsWords = listOf(
    WorkEntry("o artista / a artista", "artist"),
    WorkEntry("o pintor / a pintora", "painter"),
    WorkEntry("a escultura", "sculpture"),
    WorkEntry("a pintura", "painting"),
    WorkEntry("o músico / a música", "musician"),
    WorkEntry("a guitarra", "guitar", "electric/classical; \"a viola\" = acoustic in EP"),
    WorkEntry("o piano", "piano"),
    WorkEntry("o teclado", "keyboard"),
    WorkEntry("a flauta", "flute"),
    WorkEntry("o baixo", "bass guitar"),
    WorkEntry("o cantor / a cantora", "singer"),
    WorkEntry("o ator / a atriz", "actor / actress"),
    WorkEntry("o/a astronauta", "astronaut", "same form for m/f"),
    WorkEntry("o político / a política", "politician")
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkplacesScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Workplaces & Jobs") },
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
                .padding(innerPadding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item { WorkWordCard("Office & General", officeWords) }
            item { WorkWordCard("Architecture & Engineering", architectureWords) }
            item { WorkWordCard("Security & Law", securityWords) }
            item { WorkWordCard("Healthcare", healthcareWords) }
            item { WorkWordCard("Administration & Services", servicesWords) }
            item { WorkWordCard("Food & Hospitality", hospitalityWords) }
            item { WorkWordCard("Trades", tradesWords) }
            item { WorkWordCard("Café & Coffee", cafeWords) }
            item { WorkWordCard("Arts & Entertainment", artsWords) }
        }
    }
}

@Composable
private fun WorkWordCard(title: String, entries: List<WorkEntry>) {
    ElevatedCard(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            HorizontalDivider(modifier = Modifier.padding(vertical = 2.dp))

            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    "Portuguese",
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.weight(1.1f),
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    "English",
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.weight(0.9f),
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            entries.forEach { entry ->
                Row(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.weight(1.1f)) {
                        Text(entry.pt, style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.SemiBold)
                        if (entry.note.isNotEmpty()) {
                            Text(
                                entry.note,
                                style = MaterialTheme.typography.labelSmall,
                                fontStyle = FontStyle.Italic,
                                color = MaterialTheme.colorScheme.tertiary
                            )
                        }
                    }
                    Text(
                        entry.en,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.weight(0.9f),
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}
