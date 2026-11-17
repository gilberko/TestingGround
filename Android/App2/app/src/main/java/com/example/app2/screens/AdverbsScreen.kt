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


private val adverbCategories = listOf(
    DictCategory(
        "Frequency",
        listOf(
            DictWord("sempre", "always"),
            DictWord("normalmente", "normally / usually"),
            DictWord("frequentemente", "frequently"),
            DictWord("às vezes", "sometimes"),
            DictWord("raramente", "rarely"),
            DictWord("nunca", "never"),
            DictWord("já", "already / now"),
            DictWord("ainda", "still / yet"),
            DictWord("de vez em quando", "from time to time"),
            DictWord("todos os dias", "every day")
        )
    ),
    DictCategory(
        "Quantity & Degree",
        listOf(
            DictWord("muito", "a lot / very"),
            DictWord("pouco", "a little / not much"),
            DictWord("bastante", "quite / enough"),
            DictWord("demasiado", "too much"),
            DictWord("mais", "more"),
            DictWord("menos", "less"),
            DictWord("apenas", "only / just"),
            DictWord("só", "only"),
            DictWord("quase", "almost"),
            DictWord("completamente", "completely"),
            DictWord("absolutamente", "absolutely"),
            DictWord("mais ou menos", "more or less")
        )
    ),
    DictCategory(
        "Manner",
        listOf(
            DictWord("bem", "well"),
            DictWord("mal", "badly"),
            DictWord("devagar", "slowly"),
            DictWord("depressa", "quickly"),
            DictWord("assim", "like this / this way"),
            DictWord("juntos", "together"),
            DictWord("sozinho / sozinha", "alone"),
            DictWord("de repente", "suddenly")
        )
    ),
    DictCategory(
        "Place",
        listOf(
            DictWord("aqui", "here"),
            DictWord("ali", "there (near)"),
            DictWord("lá", "there (far)"),
            DictWord("acolá", "over there"),
            DictWord("dentro", "inside"),
            DictWord("fora", "outside"),
            DictWord("cima", "up / above"),
            DictWord("baixo", "down / below"),
            DictWord("perto", "nearby"),
            DictWord("longe", "far away"),
            DictWord("em frente", "in front"),
            DictWord("atrás", "behind")
        )
    ),
    DictCategory(
        "Affirmation & Negation",
        listOf(
            DictWord("sim", "yes"),
            DictWord("não", "no"),
            DictWord("claro", "of course"),
            DictWord("certamente", "certainly"),
            DictWord("talvez", "maybe / perhaps"),
            DictWord("provavelmente", "probably"),
            DictWord("de facto", "in fact"),
            DictWord("exactamente", "exactly")
        )
    )
)

@Composable
fun AdverbsScreen(onBack: () -> Unit) {
    DictScreenScaffold(title = "Adverbs", onBack = onBack) {
        items(adverbCategories) { DictCategoryCard(it) }
    }
}
