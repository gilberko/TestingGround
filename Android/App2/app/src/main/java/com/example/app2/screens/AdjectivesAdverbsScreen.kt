package com.example.app2.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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

private data class WordOrderExample(val pt: String, val en: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdjectivesAdverbsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Adjectives and Adverbs") },
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

            item {
                SectionCard(title = "What Are Adjectives & Adverbs?") {
                    BodyText("An adjective describes a noun and agrees with it in gender and number — bonito/bonita/bonitos/bonitas.")
                    BodyText("An adverb describes a verb, an adjective, or another adverb, and is invariable — it never changes form.")
                }
            }

            item {
                SectionCard(title = "Adjective + Noun Word Order") {
                    BodyText("The general rule is the opposite of English: the adjective follows the noun.")
                    ExampleRow(WordOrderExample("carro vermelho", "red car (lit. car red)"))
                    ExampleRow(WordOrderExample("homem alto", "tall man (lit. man tall)"))
                }
            }

            item {
                SectionCard(title = "Exception: Adjectives That Shift Meaning Before vs. After the Noun") {
                    BodyText("A small set of common adjectives change meaning depending on position — before the noun they become more subjective/figurative, after the noun more literal.")
                    ExampleRow(WordOrderExample("um grande homem", "a great man (figurative)"))
                    ExampleRow(WordOrderExample("um homem grande", "a big man (literal size)"))
                    ExampleRow(WordOrderExample("um pobre homem", "a poor fellow (pity)"))
                    ExampleRow(WordOrderExample("um homem pobre", "a man without money"))
                    ExampleRow(WordOrderExample("uma velha amizade", "a long-standing friendship"))
                    ExampleRow(WordOrderExample("uma casa velha", "an old (aged) house"))
                    ExampleRow(WordOrderExample("um novo amigo", "a new friend (just met)"))
                    ExampleRow(WordOrderExample("um amigo novo", "a young friend"))
                    BodyText("Adjectives that conventionally go before the noun: bom, mau, grande, pequeno, belo, ótimo, primeiro, último, próximo, certo, mero, próprio.")
                }
            }

            item {
                SectionCard(title = "Adverb + Verb Word Order") {
                    BodyText("The neutral order is verb + adverb.")
                    ExampleRow(WordOrderExample("Fala bem.", "(He/She) speaks well."))
                    ExampleRow(WordOrderExample("Ele corre rápido.", "He runs fast."))
                    BodyText("Fronting the adverb to the start of the sentence adds emphasis, especially for sentence-level adverbs.")
                    ExampleRow(WordOrderExample("Felizmente, chegámos a tempo.", "Fortunately, we arrived in time."))
                }
            }

            item {
                SectionCard(title = "Adding \"Very\" / \"A Bit\" (Intensifiers)") {
                    BodyText("Degree words go BEFORE the adjective or adverb they intensify, but AFTER the verb when modifying the verb directly.")
                    BodyText("A low-to-high degree scale, with one example each:")
                    ExampleRow(WordOrderExample("Não é nada chique.", "It's not classy at all. (nada = not at all, with não)"))
                    ExampleRow(WordOrderExample("É pouco chique.", "It's not very classy. (pouco = not very/a little)"))
                    ExampleRow(WordOrderExample("A porta está ligeiramente aberta.", "The door is slightly open. (ligeiramente = slightly)"))
                    ExampleRow(WordOrderExample("Estou um pouco cansado.", "I'm a bit tired. (um pouco = a bit)"))
                    ExampleRow(WordOrderExample("Estou algo surpreendido.", "I'm somewhat surprised. (algo = somewhat/rather, more formal/literary)"))
                    ExampleRow(WordOrderExample("Não é muito chique.", "It's not too classy. (não muito = not too)"))
                    ExampleRow(WordOrderExample("Não é tão chique.", "It's not so classy. (não tão = not so, near-synonym of não muito)"))
                    ExampleRow(WordOrderExample("Ele é bastante alto.", "He's quite tall. (bastante = quite/fairly)"))
                    ExampleRow(WordOrderExample("Estou muito cansado.", "I'm very tired. (muito = very/a lot)"))
                    ExampleRow(WordOrderExample("Isto é demasiado caro.", "This is too expensive. (demasiado = too much/excessively)"))
                    BodyText("With a verb directly (no adjective/adverb in between), the degree word goes after the verb:")
                    ExampleRow(WordOrderExample("Gostei muito disto.", "I liked this a lot."))
                    BodyText("Note: these words are invariable as intensifying adverbs — unlike their variable use as quantifying determiners (muita água, poucos amigos).")
                }
            }

            item {
                SectionCard(title = "Combining Multiple Adverbs") {
                    BodyText("When stringing together two or more manner adverbs ending in -mente, only the LAST one keeps the -mente suffix — earlier ones revert to their feminine singular adjective form.")
                    ExampleRow(WordOrderExample("Ele explicou tudo clara e concisamente.", "He explained it clearly and concisely."))
                    ExampleRow(WordOrderExample("Vestiu-se esperta e elegantemente.", "She dressed cleverly and elegantly/classily."))
                }
            }

            item { Spacer(modifier = Modifier.height(16.dp)) }
        }
    }
}

@Composable
private fun SectionCard(title: String, content: @Composable () -> Unit) {
    ElevatedCard(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            content()
        }
    }
}

@Composable
private fun BodyText(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodySmall,
        color = MaterialTheme.colorScheme.onSurfaceVariant
    )
}

@Composable
private fun ExampleRow(example: WordOrderExample) {
    Column(modifier = Modifier.padding(start = 8.dp, top = 2.dp, bottom = 2.dp)) {
        Text(
            text = example.pt,
            style = MaterialTheme.typography.bodySmall,
            fontStyle = FontStyle.Italic
        )
        Text(
            text = example.en,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}
