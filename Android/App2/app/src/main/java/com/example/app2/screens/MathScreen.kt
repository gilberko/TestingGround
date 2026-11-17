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


private val mathCategories = listOf(
    DictCategory(
        "Basic Operations (Operações Básicas)",
        listOf(
            DictWord("mais", "plus (e.g. dois mais dois)"),
            DictWord("menos", "minus"),
            DictWord("vezes / multiplicado por", "times / multiplied by"),
            DictWord("dividido por", "divided by"),
            DictWord("é igual a / igual", "equals / is equal to"),
            DictWord("elevado a … / à potência de …", "raised to the power of …"),
            DictWord("raiz quadrada (de)", "square root (of)")
        )
    ),
    DictCategory(
        "Operation Verbs (Verbos)",
        listOf(
            DictWord("somar / adicionar", "to add"),
            DictWord("subtrair", "to subtract"),
            DictWord("multiplicar", "to multiply"),
            DictWord("dividir", "to divide"),
            DictWord("calcular", "to calculate"),
            DictWord("resolver", "to solve")
        )
    ),
    DictCategory(
        "Symbols & Notation (Símbolos)",
        listOf(
            DictWord("parêntese / parênteses", "parenthesis / parentheses"),
            DictWord("abrir parêntese", "open parenthesis"),
            DictWord("fechar parêntese", "close parenthesis"),
            DictWord("maior que (>)", "greater than"),
            DictWord("menor que (<)", "smaller than"),
            DictWord("maior ou igual a (≥)", "greater than or equal to"),
            DictWord("menor ou igual a (≤)", "less than or equal to")
        )
    ),
    DictCategory(
        "Mathematical Concepts (Conceitos)",
        listOf(
            DictWord("equação (f)", "equation"),
            DictWord("polinómio (m)", "polynomial"),
            DictWord("expressão (f)", "expression"),
            DictWord("variável (f)", "variable"),
            DictWord("coeficiente (m)", "coefficient")
        )
    ),
    DictCategory(
        "Geometry & Trigonometry (Geometria e Trigonometria)",
        listOf(
            DictWord("geometria (f)", "geometry"),
            DictWord("trigonometria (f)", "trigonometry"),
            DictWord("seno (m)", "sine"),
            DictWord("cosseno (m)", "cosine"),
            DictWord("tangente (f)", "tangent"),
            DictWord("cotangente (f)", "cotangent"),
            DictWord("ângulo (m)", "angle"),
            DictWord("triângulo (m)", "triangle")
        )
    )
)

@Composable
fun MathScreen(onBack: () -> Unit) {
    DictScreenScaffold(title = "Math", onBack = onBack) {
        items(mathCategories) { DictCategoryCard(it) }
    }
}
