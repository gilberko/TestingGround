package com.example.app2.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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

private data class LetterEntry(val letter: String, val name: String, val guide: String)
private data class PunctEntry(val symbol: String, val english: String, val portuguese: String)
private data class PronunciationEntry(
    val symbol: String,
    val rule: String,
    val phonetic: String,
    val examples: List<Triple<String, String, String>>
)

private val alphabetEntries = listOf(
    LetterEntry("A", "a", "ah"),
    LetterEntry("B", "bê", "bay"),
    LetterEntry("C", "cê", "say"),
    LetterEntry("D", "dê", "day"),
    LetterEntry("E", "e", "eh"),
    LetterEntry("F", "efe", "EH-fe"),
    LetterEntry("G", "gê", "zhay"),
    LetterEntry("H", "agá", "ah-GAH"),
    LetterEntry("I", "i", "ee"),
    LetterEntry("J", "jota", "ZHOH-ta"),
    LetterEntry("K", "capa", "KAH-pa"),
    LetterEntry("L", "ele", "EH-le"),
    LetterEntry("M", "eme", "EH-me"),
    LetterEntry("N", "ene", "EH-ne"),
    LetterEntry("O", "o", "oh"),
    LetterEntry("P", "pê", "pay"),
    LetterEntry("Q", "quê", "kay"),
    LetterEntry("R", "erre", "EH-rre"),
    LetterEntry("S", "esse", "EH-se"),
    LetterEntry("T", "tê", "tay"),
    LetterEntry("U", "u", "oo"),
    LetterEntry("V", "vê", "vay"),
    LetterEntry("W", "dáblio", "DAH-blee-oo"),
    LetterEntry("X", "xis", "shees"),
    LetterEntry("Y", "ípsilon", "EEP-si-lon"),
    LetterEntry("Z", "zê", "zay")
)

private val punctuationEntries = listOf(
    PunctEntry(",", "comma", "vírgula"),
    PunctEntry(".", "period / full stop", "ponto"),
    PunctEntry(";", "semicolon", "ponto e vírgula"),
    PunctEntry("- / —", "hyphen / dash", "hífen / travessão"),
    PunctEntry(":", "colon", "dois pontos"),
    PunctEntry("()", "parentheses", "parênteses"),
    PunctEntry("?", "question mark", "ponto de interrogação"),
    PunctEntry("!", "exclamation mark", "ponto de exclamação"),
    PunctEntry("'", "apostrophe / single quote", "apóstrofo / aspas simples"),
    PunctEntry("\"\"", "double quotes", "aspas duplas"),
    PunctEntry("@", "at sign", "arroba"),
    PunctEntry("&", "ampersand", "e comercial"),
    PunctEntry("/", "slash", "barra oblíqua"),
    PunctEntry("\\", "backslash", "barra invertida")
)

private val pronunciationEntries = listOf(
    PronunciationEntry(
        symbol = "lh",
        rule = "Palatal lateral consonant.",
        phonetic = "Like 'ly' in 'million'",
        examples = listOf(
            Triple("filho", "FEE-lyoo", "son"),
            Triple("trabalho", "tra-BA-lyoo", "work")
        )
    ),
    PronunciationEntry(
        symbol = "nh",
        rule = "Palatal nasal consonant.",
        phonetic = "Like 'ny' in 'canyon'",
        examples = listOf(
            Triple("vinho", "VEE-nyoo", "wine"),
            Triple("manhã", "ma-NYAH(n)", "morning")
        )
    ),
    PronunciationEntry(
        symbol = "rr / r- (word-initial)",
        rule = "Strong guttural R.",
        phonetic = "Like French 'r' (back of throat)",
        examples = listOf(
            Triple("carro", "KA-rroo", "car"),
            Triple("rato", "RRA-too", "mouse")
        )
    ),
    PronunciationEntry(
        symbol = "r (intervocalic single)",
        rule = "Single R between vowels — soft tap.",
        phonetic = "A light tongue tap — like the 'r' in Spanish 'pero'",
        examples = listOf(
            Triple("caro", "KA-roo", "expensive"),
            Triple("para", "PA-ra", "for / to")
        )
    ),
    PronunciationEntry(
        symbol = "s (intervocalic single)",
        rule = "Single S between vowels — voiced.",
        phonetic = "Sounds like 'z'",
        examples = listOf(
            Triple("casa", "KA-za", "house"),
            Triple("mesa", "MEH-za", "table")
        )
    ),
    PronunciationEntry(
        symbol = "ss",
        rule = "Double S — always a hard 's' sound.",
        phonetic = "Always hard 's' (never 'z')",
        examples = listOf(
            Triple("passo", "PA-soo", "step"),
            Triple("osso", "OH-soo", "bone")
        )
    ),
    PronunciationEntry(
        symbol = "ch",
        rule = "Always produces a 'sh' sound in EP.",
        phonetic = "Like 'sh' in 'shoe'",
        examples = listOf(
            Triple("chave", "SHA-ve", "key"),
            Triple("chuva", "SHOO-va", "rain")
        )
    ),
    PronunciationEntry(
        symbol = "x",
        rule = "Highly variable — context-dependent.",
        phonetic = "'sh', 'z', 'ks', or 's' depending on word",
        examples = listOf(
            Triple("caixa", "KAI-sha", "box (sh)"),
            Triple("táxi", "TAK-si", "taxi (ks)"),
            Triple("próximo", "PRO-si-moo", "next (s)")
        )
    ),
    PronunciationEntry(
        symbol = "j",
        rule = "Always a voiced 'zh' sound.",
        phonetic = "Like French 'j' or 's' in 'measure'",
        examples = listOf(
            Triple("janela", "zha-NEH-la", "window"),
            Triple("hoje", "OH-zhe", "today")
        )
    ),
    PronunciationEntry(
        symbol = "z (word-final)",
        rule = "Word-final Z is devoiced to 'sh' in EP.",
        phonetic = "Pronounced 'sh' at end of word",
        examples = listOf(
            Triple("paz", "PASH", "peace"),
            Triple("luz", "LOOSH", "light"),
            Triple("feliz", "fe-LEESH", "happy")
        )
    ),
    PronunciationEntry(
        symbol = "ã / ão / ãe",
        rule = "Nasal vowels — unique to Portuguese.",
        phonetic = "No English equivalent; vowel + nasal resonance",
        examples = listOf(
            Triple("manhã", "ma-NYAH(n)", "morning"),
            Triple("pão", "POWNG(n)", "bread"),
            Triple("mãe", "MYAH(n)e", "mother")
        )
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PronunciationScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Pronunciation") },
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
            item {
                Spacer(modifier = Modifier.height(4.dp))
                ElevatedCard(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(
                            text = "These rules apply to European Portuguese (EP). Brazilian Portuguese differs significantly.",
                            style = MaterialTheme.typography.bodyMedium,
                            fontStyle = FontStyle.Italic,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

            item {
                AlphabetCard()
            }

            items(pronunciationEntries) { entry ->
                PhonemeCard(entry)
            }

            item {
                StressRulesCard()
            }

            item {
                NotableFeaturesCard()
            }

            item {
                PunctuationCard()
            }

            item { Spacer(modifier = Modifier.height(16.dp)) }
        }
    }
}

@Composable
private fun PhonemeCard(entry: PronunciationEntry) {
    ElevatedCard(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = entry.symbol,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = entry.rule,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = 2.dp)
            )
            Text(
                text = entry.phonetic,
                style = MaterialTheme.typography.bodySmall,
                fontStyle = FontStyle.Italic,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            // Header
            Row(
                modifier = Modifier.fillMaxWidth().padding(bottom = 2.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "Word",
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "Guide",
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "Meaning",
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f)
                )
            }
            entry.examples.forEach { (word, guide, meaning) ->
                Row(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = word,
                        style = MaterialTheme.typography.bodySmall,
                        fontStyle = FontStyle.Italic,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = guide,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = meaning,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}

@Composable
private fun AlphabetCard() {
    ElevatedCard(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = "The Alphabet — Letter Names",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = "K, W and Y were officially added back in the 2009 Orthographic Agreement.",
                style = MaterialTheme.typography.bodySmall,
                fontStyle = FontStyle.Italic,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Row(
                modifier = Modifier.fillMaxWidth().padding(bottom = 2.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "Letter",
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(0.6f)
                )
                Text(
                    text = "Name",
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "Sounds like",
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f)
                )
            }
            alphabetEntries.forEach { entry ->
                Row(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = entry.letter,
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.weight(0.6f)
                    )
                    Text(
                        text = entry.name,
                        style = MaterialTheme.typography.bodySmall,
                        fontStyle = FontStyle.Italic,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = entry.guide,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}

@Composable
private fun PunctuationCard() {
    ElevatedCard(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = "Punctuation & Special Characters",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Row(
                modifier = Modifier.fillMaxWidth().padding(bottom = 2.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "Sign",
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(0.6f)
                )
                Text(
                    text = "English",
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1.4f)
                )
                Text(
                    text = "Portuguese",
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1.4f)
                )
            }
            punctuationEntries.forEach { entry ->
                Row(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = entry.symbol,
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.weight(0.6f)
                    )
                    Text(
                        text = entry.english,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.weight(1.4f)
                    )
                    Text(
                        text = entry.portuguese,
                        style = MaterialTheme.typography.bodySmall,
                        fontStyle = FontStyle.Italic,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.weight(1.4f)
                    )
                }
            }
        }
    }
}

@Composable
private fun StressRulesCard() {
    ElevatedCard(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = "Stress Rules",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            listOf(
                "1. Accent mark (á, é, ó, etc.) → stress on that syllable (overrides all other rules).",
                "2. Ending -a / -e / -o / -am / -em / -ens → stress the penultimate (second-to-last) syllable.",
                "3. Ending -i / -u / -im / -um / -r / -l / -z / -x → stress the final syllable.",
                "4. -ção / -são → stress the final syllable: nação = na-SOWNG."
            ).forEach { rule ->
                Text(
                    text = rule,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
            }
        }
    }
}

@Composable
private fun NotableFeaturesCard() {
    ElevatedCard(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = "Notable Features",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            listOf(
                "Vowel reduction: unstressed 'e' and 'o' are nearly silent — \"de nada\" ≈ \"DNAH-da\".",
                "Final -e: typically silent in EP — \"tarde\" ≈ \"TARD\".",
                "eu (pronoun): pronounced \"EH-oo\", not \"YOO\".",
                "qu + e/i: u is silent (\"que\" = \"ke\"); but qua/quo = \"kwa\"/\"kwo\".",
                "gu + e/i: u is silent (\"guerra\" = \"GEH-rra\"); but gua = \"gwa\"."
            ).forEach { bullet ->
                Text(
                    text = "• $bullet",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
            }
        }
    }
}
