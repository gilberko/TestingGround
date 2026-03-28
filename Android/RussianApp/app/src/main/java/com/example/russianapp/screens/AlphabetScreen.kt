package com.example.russianapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private data class RussianLetter(
    val upper: String,
    val lower: String,
    val pronunciation: String,
    val note: String? = null   // extra context when the letter is unusual
)

private val alphabet = listOf(
    RussianLetter("А", "а", "\"a\" as in f-A-ther"),
    RussianLetter("Б", "б", "\"b\" as in B-ed"),
    RussianLetter("В", "в", "\"v\" as in V-ine"),
    RussianLetter("Г", "г", "\"g\" as in G-o"),
    RussianLetter("Д", "д", "\"d\" as in D-og"),
    RussianLetter("Е", "е", "\"ye\" as in Y-Es", "Also softens the preceding consonant"),
    RussianLetter("Ё", "ё", "\"yo\" as in Y-Ork", "Always stressed; sometimes written as Е in informal texts"),
    RussianLetter("Ж", "ж", "\"zh\" like the \"s\" in mea-S-ure"),
    RussianLetter("З", "з", "\"z\" as in Z-oo"),
    RussianLetter("И", "и", "\"ee\" as in s-EE"),
    RussianLetter("Й", "й", "\"y\" as in bo-Y (very short)", "Called \"и краткое\" — short И; never starts a syllable alone"),
    RussianLetter("К", "к", "\"k\" as in K-ite"),
    RussianLetter("Л", "л", "\"l\" as in L-amp"),
    RussianLetter("М", "м", "\"m\" as in M-ap"),
    RussianLetter("Н", "н", "\"n\" as in N-ot"),
    RussianLetter("О", "о", "\"o\" as in m-O-re (when stressed); like \"a\" when unstressed"),
    RussianLetter("П", "п", "\"p\" as in P-et"),
    RussianLetter("Р", "р", "Rolled \"r\" (trilled with the tongue tip)", "Similar to Spanish R; stronger than the English R"),
    RussianLetter("С", "с", "\"s\" as in S-un"),
    RussianLetter("Т", "т", "\"t\" as in T-op"),
    RussianLetter("У", "у", "\"oo\" as in m-OO-n"),
    RussianLetter("Ф", "ф", "\"f\" as in F-an"),
    RussianLetter("Х", "х", "\"kh\" — a throaty sound like Scottish \"lo-CH\"", "Stronger than the English H; produced at the back of the throat"),
    RussianLetter("Ц", "ц", "\"ts\" as in ca-TS"),
    RussianLetter("Ч", "ч", "\"ch\" as in CH-air"),
    RussianLetter("Ш", "ш", "\"sh\" as in SH-op (hard, flat)"),
    RussianLetter("Щ", "щ", "\"shch\" — a softer, longer SH+CH sound", "Keep the tongue forward; sounds like \"fresh CH-eese\" said quickly"),
    RussianLetter("Ъ", "ъ", "Hard sign — no sound of its own", "Separates a prefix ending in a consonant from a following soft vowel (е, ё, ю, я), preventing softening"),
    RussianLetter("Ы", "ы", "No English equivalent — a dark \"i\" sound", "Produced with the back of the tongue raised; between the \"i\" in \"bit\" and the \"u\" in \"but\""),
    RussianLetter("Ь", "ь", "Soft sign — no sound of its own", "Softens the preceding consonant, making it more palatal; e.g. \"н\" sounds like \"ny\" when followed by Ь"),
    RussianLetter("Э", "э", "\"e\" as in m-E-t (plain E, no Y glide)"),
    RussianLetter("Ю", "ю", "\"yu\" as in Y-OU"),
    RussianLetter("Я", "я", "\"ya\" as in Y-Ard", "Also softens the preceding consonant")
)

@Composable
private fun LetterCard(letter: RussianLetter) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Large letter display
            Text(
                text = "${letter.upper} ${letter.lower}",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .padding(end = 16.dp)
                    .weight(0.8f)
            )
            // Pronunciation + note
            Column(modifier = Modifier.weight(2f)) {
                Text(
                    text = letter.pronunciation,
                    style = MaterialTheme.typography.bodyMedium
                )
                if (letter.note != null) {
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = letter.note,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontStyle = FontStyle.Italic
                    )
                }
            }
        }
    }
}

@Composable
private fun PronunciationExceptionCard(title: String, rule: String, examples: List<String>) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = rule,
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(6.dp))
            examples.forEach { example ->
                Text(
                    text = "• $example",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(vertical = 1.dp)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlphabetScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("The Russian Alphabet") },
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
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            item {
                Text(
                    text = "The Russian alphabet (кириллица — Cyrillic) has 33 letters: 10 vowels, 21 consonants, and 2 signs (Ъ and Ь) that modify pronunciation without producing a sound of their own.",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
            items(alphabet) { letter ->
                LetterCard(letter)
            }
            item {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Spelling vs. Pronunciation Exceptions",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(top = 8.dp, bottom = 4.dp)
                )
                HorizontalDivider(color = MaterialTheme.colorScheme.primary)
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Some letter combinations are pronounced differently from how they are spelled. The two most common exceptions for beginners are below.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(vertical = 6.dp)
                )
            }
            item {
                PronunciationExceptionCard(
                    title = "-его / -ого endings — Г sounds like В",
                    rule = "In genitive and adjective endings, the letter Г is pronounced like В (a v-sound), not G. This affects all -ого and -его endings.",
                    examples = listOf(
                        "нового → [но-во-во]  (of a new [thing])",
                        "синего → [си-не-во]  (of the blue [thing])",
                        "его → [е-во]  (his / him)"
                    )
                )
            }
            item {
                PronunciationExceptionCard(
                    title = "Г before К → Х (kh) sound",
                    rule = "When Г appears directly before К, it is pronounced like Х — a throaty kh-sound, as in Scottish \"loch\". This is most visible in the adverb легко and related soft forms.",
                    examples = listOf(
                        "легко → [лех-КО]  (easily)",
                        "мягко → [мях-КО]  (softly)"
                    )
                )
            }
        }
    }
}
