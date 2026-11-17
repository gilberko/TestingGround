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


private val officeWords = listOf(
    DictWord("o trabalho", "work / job"),
    DictWord("o emprego", "job / employment", "more specific than \"trabalho\""),
    DictWord("o local de trabalho", "workplace"),
    DictWord("o escritório", "office"),
    DictWord("a caneta", "pen"),
    DictWord("a borracha", "eraser"),
    DictWord("o lápis", "pencil"),
    DictWord("o papel", "paper"),
    DictWord("a página", "page"),
    DictWord("a impressora", "printer"),
    DictWord("imprimir", "to print"),
    DictWord("as tesouras", "scissors", "always plural in PT"),
    DictWord("um diagrama", "a diagram")
)

private val architectureWords = listOf(
    DictWord("o engenheiro / a engenheira", "engineer"),
    DictWord("medir", "to measure"),
    DictWord("o arquiteto / a arquiteta", "architect", "EP spelling; BP: arquiteto/arquiteta"),
    DictWord("desenhar", "to design / to draw"),
    DictWord("a planta", "the blueprint / floor plan", "also: o projeto")
)

private val securityWords = listOf(
    DictWord("o polícia / a polícia", "police officer", "EP: \"o polícia\" m, \"a polícia\" f"),
    DictWord("a polícia", "the police (institution)"),
    DictWord("o oficial do exército / a oficial do exército", "army officer"),
    DictWord("o soldado / a soldada", "soldier"),
    DictWord("o exército", "army"),
    DictWord("uma arma", "a weapon"),
    DictWord("prender / deter", "to arrest"),
    DictWord("o uniforme", "uniform")
)

private val healthcareWords = listOf(
    DictWord("o médico / a médica", "doctor"),
    DictWord("tratar", "to treat"),
    DictWord("o enfermeiro / a enfermeira", "nurse"),
    DictWord("o farmacêutico / a farmacêutica", "pharmacist")
)

private val servicesWords = listOf(
    DictWord("o secretário / a secretária", "secretary"),
    DictWord("a loja", "a shop / store"),
    DictWord("o cabeleireiro / a cabeleireira", "hairdresser / barber", "EP general term"),
    DictWord("o barbeiro", "barber", "male-specific"),
    DictWord("cortar o cabelo", "to cut hair")
)

private val hospitalityWords = listOf(
    DictWord("o restaurante", "restaurant"),
    DictWord("o empregado de mesa / a empregada de mesa", "waiter / waitress", "EP term"),
    DictWord("o chef / a chef", "chef"),
    DictWord("o cozinheiro / a cozinheira", "cook"),
    DictWord("cozinhar", "to cook"),
    DictWord("a ementa", "menu", "EP term — BP: o cardápio"),
    DictWord("pedir", "to order"),
    DictWord("pagar", "to pay"),
    DictWord("a conta", "the bill")
)

private val tradesWords = listOf(
    DictWord("o canalizador / a canalizadora", "plumber", "EP term"),
    DictWord("os canos", "pipes"),
    DictWord("consertar / arranjar", "to fix", "\"arranjar\" more colloquial"),
    DictWord("o cano entupido", "clogged pipe")
)

private val cafeWords = listOf(
    DictWord("o/a barista", "barista"),
    DictWord("o café", "café / coffee shop"),
    DictWord("o expresso", "espresso", "EP spelling"),
    DictWord("o cappuccino", "cappuccino"),
    DictWord("a máquina de café", "coffee machine"),
    DictWord("torrar", "to roast"),
    DictWord("os grãos de café", "coffee beans"),
    DictWord("os grãos", "beans"),
    DictWord("o chá", "tea")
)

private val artsWords = listOf(
    DictWord("o artista / a artista", "artist"),
    DictWord("o pintor / a pintora", "painter"),
    DictWord("a escultura", "sculpture"),
    DictWord("a pintura", "painting"),
    DictWord("o músico / a música", "musician"),
    DictWord("a guitarra", "guitar", "electric/classical; \"a viola\" = acoustic in EP"),
    DictWord("o piano", "piano"),
    DictWord("o teclado", "keyboard"),
    DictWord("a flauta", "flute"),
    DictWord("o baixo", "bass guitar"),
    DictWord("o cantor / a cantora", "singer"),
    DictWord("o ator / a atriz", "actor / actress"),
    DictWord("o/a astronauta", "astronaut", "same form for m/f"),
    DictWord("o político / a política", "politician")
)

@Composable
fun WorkplacesScreen(onBack: () -> Unit) {
    DictScreenScaffold(title = "Workplaces & Jobs", onBack = onBack) {
        item { DictCategoryCard("Office & General", officeWords) }
        item { DictCategoryCard("Architecture & Engineering", architectureWords) }
        item { DictCategoryCard("Security & Law", securityWords) }
        item { DictCategoryCard("Healthcare", healthcareWords) }
        item { DictCategoryCard("Administration & Services", servicesWords) }
        item { DictCategoryCard("Food & Hospitality", hospitalityWords) }
        item { DictCategoryCard("Trades", tradesWords) }
        item { DictCategoryCard("Café & Coffee", cafeWords) }
        item { DictCategoryCard("Arts & Entertainment", artsWords) }
    }
}
