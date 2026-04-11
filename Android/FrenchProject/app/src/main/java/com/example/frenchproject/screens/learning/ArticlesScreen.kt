package com.example.frenchproject.screens.learning

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

private data class ArticleRow(
    val article: String,
    val gender: String,
    val example: String,
    val translation: String
)

private val definiteArticles = listOf(
    ArticleRow("le", "masculine singular", "le livre", "the book"),
    ArticleRow("la", "feminine singular", "la porte", "the door"),
    ArticleRow("l'", "before vowel or silent h", "l'ami, l'heure", "the friend, the hour"),
    ArticleRow("les", "all plurals", "les livres", "the books")
)

private val indefiniteArticles = listOf(
    ArticleRow("un", "masculine singular", "un chat", "a cat"),
    ArticleRow("une", "feminine singular", "une table", "a table"),
    ArticleRow("des", "all plurals", "des amis", "some friends / friends")
)

private val partitiveArticles = listOf(
    ArticleRow("du", "masculine singular (de + le)", "du pain", "some bread"),
    ArticleRow("de la", "feminine singular", "de la soupe", "some soup"),
    ArticleRow("de l'", "before vowel or silent h", "de l'eau", "some water"),
    ArticleRow("des", "all plurals", "des légumes", "some vegetables")
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticlesScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Articles", color = Color.White) },
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
                    text = "Every French noun has a grammatical gender — masculine or feminine. The article that precedes the noun must agree with that gender. There is no neutral gender in French.",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }

            // Definite articles
            item {
                ArticleSectionHeader("Definite Articles — « the »")
            }
            item {
                Text(
                    text = "Used when referring to a specific, known thing — equivalent to English \"the\".",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }
            items(definiteArticles.size) { i -> ArticleCard(definiteArticles[i]) }

            // Indefinite articles
            item {
                Spacer(modifier = Modifier.height(12.dp))
                ArticleSectionHeader("Indefinite Articles — « a / some »")
            }
            item {
                Text(
                    text = "Used for unspecified, countable nouns — equivalent to English \"a\", \"an\", or \"some\".",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }
            items(indefiniteArticles.size) { i -> ArticleCard(indefiniteArticles[i]) }

            // Partitive articles
            item {
                Spacer(modifier = Modifier.height(12.dp))
                ArticleSectionHeader("Partitive Articles — « some » (uncountable)")
            }
            item {
                Text(
                    text = "Used with uncountable nouns (substances, abstract things) to mean \"some\" or an unspecified amount. English often omits the article entirely.",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }
            items(partitiveArticles.size) { i -> ArticleCard(partitiveArticles[i]) }

            // Contraction note
            item {
                Spacer(modifier = Modifier.height(12.dp))
                ArticleSectionHeader("Contractions to Know")
            }
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFE8EDF8)),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "de + le  →  du       (never « de le »)\nde + les →  des      (never « de les »)\nà + le   →  au       (never « à le »)\nà + les  →  aux      (never « à les »)",
                            fontFamily = androidx.compose.ui.text.font.FontFamily.Monospace,
                            fontSize = 13.sp,
                            color = FrenchNavy,
                            lineHeight = 22.sp
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Je vais au marché. → I'm going to the market.\nIl parle des enfants. → He's talking about the children.",
                            style = MaterialTheme.typography.bodySmall,
                            fontStyle = FontStyle.Italic,
                            color = FrenchBlue
                        )
                    }
                }
            }

            // Negation note
            item {
                Spacer(modifier = Modifier.height(12.dp))
                ArticleSectionHeader("Articles After Negation")
            }
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "After ne … pas, un/une/du/de la/des all become de (or d' before a vowel).",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "J'ai un chat. → Je n'ai pas de chat.\nElle mange du pain. → Elle ne mange pas de pain.",
                            style = MaterialTheme.typography.bodySmall,
                            fontStyle = FontStyle.Italic,
                            color = FrenchBlue
                        )
                    }
                }
            }

            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}

@Composable
private fun ArticleSectionHeader(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold,
        color = FrenchBlue,
        modifier = Modifier.padding(bottom = 4.dp)
    )
    HorizontalDivider(color = FrenchBlue.copy(alpha = 0.4f))
    Spacer(modifier = Modifier.height(4.dp))
}

@Composable
private fun ArticleCard(row: ArticleRow) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 10.dp)
        ) {
            Text(
                text = row.article,
                fontWeight = FontWeight.Bold,
                color = FrenchBlue,
                fontSize = 18.sp,
                modifier = Modifier.width(60.dp)
            )
            Column(modifier = Modifier.weight(1f)) {
                Text(text = row.gender, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "${row.example}  —  ${row.translation}",
                    style = MaterialTheme.typography.bodyMedium,
                    fontStyle = FontStyle.Italic
                )
            }
        }
    }
}
