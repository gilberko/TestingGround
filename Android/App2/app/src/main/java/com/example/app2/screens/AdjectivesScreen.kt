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


private val adjectiveCategories = listOf(
    DictCategory(
        "Size & Quantity",
        listOf(
            DictWord("grande", "big / large"),
            DictWord("pequeno / pequena", "small"),
            DictWord("longo / longa", "long"),
            DictWord("curto / curta", "short"),
            DictWord("alto / alta", "tall / high"),
            DictWord("baixo / baixa", "short / low"),
            DictWord("largo / larga", "wide"),
            DictWord("estreito / estreita", "narrow"),
            DictWord("gordo / gorda", "fat"),
            DictWord("magro / magra", "thin"),
            DictWord("muito / muita", "much / many"),
            DictWord("pouco / pouca", "little / few")
        )
    ),
    DictCategory(
        "Quality & Appearance",
        listOf(
            DictWord("bonito / bonita", "beautiful / pretty"),
            DictWord("feio / feia", "ugly"),
            DictWord("novo / nova", "new / young"),
            DictWord("velho / velha", "old"),
            DictWord("limpo / limpa", "clean"),
            DictWord("sujo / suja", "dirty"),
            DictWord("rápido / rápida", "fast"),
            DictWord("lento / lenta", "slow"),
            DictWord("fácil", "easy"),
            DictWord("difícil", "difficult"),
            DictWord("barato / barata", "cheap"),
            DictWord("caro / cara", "expensive")
        )
    ),
    DictCategory(
        "Feelings & Character",
        listOf(
            DictWord("feliz", "happy"),
            DictWord("triste", "sad"),
            DictWord("cansado / cansada", "tired"),
            DictWord("doente", "sick / ill"),
            DictWord("ocupado / ocupada", "busy"),
            DictWord("livre", "free"),
            DictWord("simpático / simpática", "friendly / nice"),
            DictWord("antipático / antipática", "unfriendly"),
            DictWord("inteligente", "intelligent"),
            DictWord("estúpido / estúpida", "stupid"),
            DictWord("forte", "strong"),
            DictWord("fraco / fraca", "weak")
        )
    ),
    DictCategory(
        "Temperature & Condition",
        listOf(
            DictWord("quente", "hot"),
            DictWord("frio / fria", "cold"),
            DictWord("fresco / fresca", "cool / fresh"),
            DictWord("húmido / húmida", "humid"),
            DictWord("seco / seca", "dry"),
            DictWord("aberto / aberta", "open"),
            DictWord("fechado / fechada", "closed"),
            DictWord("cheio / cheia", "full"),
            DictWord("vazio / vazia", "empty")
        )
    ),
    DictCategory(
        "Togetherness & State",
        listOf(
            DictWord("sozinho / sozinha", "alone, lonely"),
            DictWord("acompanhado / acompanhada", "accompanied, not alone"),
            DictWord("isolado / isolada", "isolated"),
            DictWord("solitário / solitária", "solitary, lonely"),
            DictWord("junto / junta", "together, next to"),
            DictWord("separado / separada", "separated, apart")
        )
    )
)

@Composable
fun AdjectivesScreen(onBack: () -> Unit) {
    DictScreenScaffold(title = "Adjectives", onBack = onBack) {
        items(adjectiveCategories) { DictCategoryCard(it) }
    }
}
