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

private data class MathEntry(val pt: String, val en: String)
private data class MathCategory(val title: String, val entries: List<MathEntry>)

private val mathCategories = listOf(
    MathCategory(
        "Basic Operations (Operações Básicas)",
        listOf(
            MathEntry("mais", "plus (e.g. dois mais dois)"),
            MathEntry("menos", "minus"),
            MathEntry("vezes / multiplicado por", "times / multiplied by"),
            MathEntry("dividido por", "divided by"),
            MathEntry("é igual a / igual", "equals / is equal to"),
            MathEntry("elevado a … / à potência de …", "raised to the power of …"),
            MathEntry("raiz quadrada (de)", "square root (of)")
        )
    ),
    MathCategory(
        "Operation Verbs (Verbos)",
        listOf(
            MathEntry("somar / adicionar", "to add"),
            MathEntry("subtrair", "to subtract"),
            MathEntry("multiplicar", "to multiply"),
            MathEntry("dividir", "to divide"),
            MathEntry("calcular", "to calculate"),
            MathEntry("resolver", "to solve")
        )
    ),
    MathCategory(
        "Symbols & Notation (Símbolos)",
        listOf(
            MathEntry("parêntese / parênteses", "parenthesis / parentheses"),
            MathEntry("abrir parêntese", "open parenthesis"),
            MathEntry("fechar parêntese", "close parenthesis"),
            MathEntry("maior que (>)", "greater than"),
            MathEntry("menor que (<)", "smaller than"),
            MathEntry("maior ou igual a (≥)", "greater than or equal to"),
            MathEntry("menor ou igual a (≤)", "less than or equal to")
        )
    ),
    MathCategory(
        "Mathematical Concepts (Conceitos)",
        listOf(
            MathEntry("equação (f)", "equation"),
            MathEntry("polinómio (m)", "polynomial"),
            MathEntry("expressão (f)", "expression"),
            MathEntry("variável (f)", "variable"),
            MathEntry("coeficiente (m)", "coefficient")
        )
    ),
    MathCategory(
        "Geometry & Trigonometry (Geometria e Trigonometria)",
        listOf(
            MathEntry("geometria (f)", "geometry"),
            MathEntry("trigonometria (f)", "trigonometry"),
            MathEntry("seno (m)", "sine"),
            MathEntry("cosseno (m)", "cosine"),
            MathEntry("tangente (f)", "tangent"),
            MathEntry("cotangente (f)", "cotangent"),
            MathEntry("ângulo (m)", "angle"),
            MathEntry("triângulo (m)", "triangle")
        )
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MathScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Math") },
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
            items(mathCategories) { category ->
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
