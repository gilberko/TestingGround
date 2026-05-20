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
    val name: String,           // Russian name of the letter (e.g. "бэ", "и краткое")
    val pronunciation: String,
    val note: String? = null
)

private data class PunctuationEntry(
    val sign: String,
    val english: String,
    val russian: String,
    val note: String? = null
)

private val alphabet = listOf(
    RussianLetter("А", "а", "а",            "\"a\" as in f-A-ther"),
    RussianLetter("Б", "б", "бэ",           "\"b\" as in B-ed"),
    RussianLetter("В", "в", "вэ",           "\"v\" as in V-ine"),
    RussianLetter("Г", "г", "гэ",           "\"g\" as in G-o"),
    RussianLetter("Д", "д", "дэ",           "\"d\" as in D-og"),
    RussianLetter("Е", "е", "е",            "\"ye\" as in Y-Es", "Also softens the preceding consonant"),
    RussianLetter("Ё", "ё", "ё",            "\"yo\" as in Y-Ork", "Always stressed; sometimes written as Е in informal texts"),
    RussianLetter("Ж", "ж", "жэ",           "\"zh\" like the \"s\" in mea-S-ure"),
    RussianLetter("З", "з", "зэ",           "\"z\" as in Z-oo"),
    RussianLetter("И", "и", "и",            "\"ee\" as in s-EE"),
    RussianLetter("Й", "й", "и краткое",   "\"y\" as in bo-Y (very short)", "Never starts a syllable alone"),
    RussianLetter("К", "к", "ка",           "\"k\" as in K-ite"),
    RussianLetter("Л", "л", "эл",           "\"l\" as in L-amp"),
    RussianLetter("М", "м", "эм",           "\"m\" as in M-ap"),
    RussianLetter("Н", "н", "эн",           "\"n\" as in N-ot"),
    RussianLetter("О", "о", "о",            "\"o\" as in m-O-re (when stressed); like \"a\" when unstressed"),
    RussianLetter("П", "п", "пэ",           "\"p\" as in P-et"),
    RussianLetter("Р", "р", "эр",           "Rolled \"r\" (trilled with the tongue tip)", "Similar to Spanish R; stronger than the English R"),
    RussianLetter("С", "с", "эс",           "\"s\" as in S-un"),
    RussianLetter("Т", "т", "тэ",           "\"t\" as in T-op"),
    RussianLetter("У", "у", "у",            "\"oo\" as in m-OO-n"),
    RussianLetter("Ф", "ф", "эф",           "\"f\" as in F-an"),
    RussianLetter("Х", "х", "ха",           "\"kh\" — a throaty sound like Scottish \"lo-CH\"", "Stronger than the English H; produced at the back of the throat"),
    RussianLetter("Ц", "ц", "цэ",           "\"ts\" as in ca-TS"),
    RussianLetter("Ч", "ч", "чэ",           "\"ch\" as in CH-air"),
    RussianLetter("Ш", "ш", "ша",           "\"sh\" as in SH-op (hard, flat)"),
    RussianLetter("Щ", "щ", "ща",           "\"shch\" — a softer, longer SH+CH sound", "Keep the tongue forward; sounds like \"fresh CH-eese\" said quickly"),
    RussianLetter("Ъ", "ъ", "твёрдый знак", "Hard sign — no sound of its own", "Separates a prefix ending in a consonant from a following soft vowel (е, ё, ю, я), preventing softening"),
    RussianLetter("Ы", "ы", "ы",            "No English equivalent — a dark \"i\" sound", "Produced with the back of the tongue raised; between the \"i\" in \"bit\" and the \"u\" in \"but\""),
    RussianLetter("Ь", "ь", "мягкий знак",  "Soft sign — no sound of its own", "Softens the preceding consonant, making it more palatal; e.g. \"н\" sounds like \"ny\" when followed by Ь"),
    RussianLetter("Э", "э", "э",            "\"e\" as in m-E-t (plain E, no Y glide)"),
    RussianLetter("Ю", "ю", "ю",            "\"yu\" as in Y-OU"),
    RussianLetter("Я", "я", "я",            "\"ya\" as in Y-Ard", "Also softens the preceding consonant")
)

private val punctuationMarks = listOf(
    PunctuationEntry(",",   "Comma",              "Запятая"),
    PunctuationEntry(".",   "Period / Full stop",  "Точка"),
    PunctuationEntry(";",   "Semicolon",           "Точка с запятой"),
    PunctuationEntry("-",   "Hyphen",              "Дефис",
        "Joins compound words and splits words at line breaks: красно-белый (red-and-white)"),
    PunctuationEntry("—",   "Dash (em dash)",      "Тире",
        "Much more common in Russian than in English: marks dialogue (— Привет!), replaces omitted verbs (Москва — столица), and introduces list items"),
    PunctuationEntry(":",   "Colon",               "Двоеточие"),
    PunctuationEntry("()",  "Parentheses",         "Скобки"),
    PunctuationEntry("?",   "Question mark",       "Вопросительный знак"),
    PunctuationEntry("!",   "Exclamation mark",    "Восклицательный знак"),
    PunctuationEntry("' '", "Single quotes",       "Одинарные кавычки"),
    PunctuationEntry("\" \"","Double quotes",      "Кавычки (Russian style: «ёлочки»)"),
    PunctuationEntry("@",   "At sign",             "Собака («dog»)"),
    PunctuationEntry("&",   "Ampersand",           "Амперсанд"),
    PunctuationEntry("/",   "Slash",               "Косая черта"),
    PunctuationEntry("\\",  "Backslash",           "Обратная косая черта")
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
            Text(
                text = "${letter.upper} ${letter.lower}",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .padding(end = 8.dp)
                    .weight(0.6f)
            )
            Text(
                text = letter.name,
                fontSize = 13.sp,
                fontStyle = FontStyle.Italic,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .padding(end = 8.dp)
                    .weight(0.85f)
            )
            Column(modifier = Modifier.weight(1.55f)) {
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

@Composable
private fun PunctuationTable() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Sign",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.weight(0.4f)
                )
                Text(
                    text = "English",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.weight(1.2f)
                )
                Text(
                    text = "Russian",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.weight(1.4f)
                )
            }
            HorizontalDivider(color = MaterialTheme.colorScheme.primary)
            punctuationMarks.forEachIndexed { index, entry ->
                Column(modifier = Modifier.fillMaxWidth()) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 6.dp, bottom = if (entry.note != null) 2.dp else 6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = entry.sign,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.weight(0.4f)
                        )
                        Text(
                            text = entry.english,
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.weight(1.2f)
                        )
                        Text(
                            text = entry.russian,
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.weight(1.4f)
                        )
                    }
                    if (entry.note != null) {
                        Text(
                            text = entry.note,
                            style = MaterialTheme.typography.bodySmall,
                            fontStyle = FontStyle.Italic,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(start = 4.dp, bottom = 6.dp)
                        )
                    }
                }
                if (index < punctuationMarks.lastIndex) {
                    HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
                }
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
            item {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Punctuation Marks",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(top = 8.dp, bottom = 4.dp)
                )
                HorizontalDivider(color = MaterialTheme.colorScheme.primary)
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Common punctuation marks with their English and Russian names.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(vertical = 6.dp)
                )
                PunctuationTable()
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}
