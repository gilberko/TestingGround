package com.example.czechapp.screens

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

private data class CzechLetter(
    val letter: String,
    val pronunciation: String,
    val note: String? = null
)

private val specialLetters = listOf(
    CzechLetter("á", "long \"a\" — like \"a\" in f-A-ther, but held longer"),
    CzechLetter("č", "\"ch\" as in CH-air"),
    CzechLetter("ď", "\"dy\" — a softened D, like \"d\" in dew (British English)"),
    CzechLetter("é", "long \"e\" — like \"e\" in m-E-t, but held longer"),
    CzechLetter("ě", "\"ye\" — softens the preceding consonant; like \"ye\" in yes", "After m: pronounced 'mně'; after b/p/v/f: 'bje/pje/vje/fje'"),
    CzechLetter("í / ý", "long \"ee\" — like \"ee\" in s-EE, but held longer", "í and ý are pronounced identically; ý is used in certain word endings"),
    CzechLetter("ň", "\"ny\" — like \"ny\" in ca-NYon"),
    CzechLetter("ó", "long \"o\" — rare; mostly in loanwords"),
    CzechLetter("ř", "Unique to Czech: a trilled \"r\" with a simultaneous \"zh\" or \"sh\" sound", "No equivalent in other languages. Practice by combining a rolled R with a French J."),
    CzechLetter("š", "\"sh\" as in SH-op"),
    CzechLetter("ť", "\"ty\" — a softened T, like \"t\" in tune (British English)"),
    CzechLetter("ú / ů", "long \"oo\" — like \"oo\" in m-OO-n, held longer", "ú appears at the start of words; ů appears in the middle or end"),
    CzechLetter("ž", "\"zh\" — like the \"s\" in mea-S-ure")
)

private data class DigramEntry(val digram: String, val pronunciation: String)
private val digrams = listOf(
    DigramEntry("ch", "\"kh\" — a throaty sound like Scottish \"lo-CH\"; treated as a single letter in the alphabet"),
    DigramEntry("dz", "\"dz\" as in a-DD-S; rare, mainly in loanwords"),
    DigramEntry("dž", "\"j\" as in J-azz; rare, mainly in loanwords")
)

@Composable
private fun LetterCard(letter: CzechLetter) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = letter.letter,
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(end = 16.dp).weight(0.7f)
            )
            Column(modifier = Modifier.weight(2f)) {
                Text(text = letter.pronunciation, style = MaterialTheme.typography.bodyMedium)
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LettersScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Letters & Diacritics") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(innerPadding).padding(horizontal = 16.dp),
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            item {
                Text(
                    text = "Czech uses the Latin alphabet with diacritical marks. The standard letters a–z are pronounced similarly to English. The special letters below have distinct sounds.",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
            item {
                Text(
                    text = "Special Letters",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(top = 4.dp, bottom = 4.dp)
                )
                HorizontalDivider(color = MaterialTheme.colorScheme.primary)
                Spacer(modifier = Modifier.height(4.dp))
            }
            items(specialLetters) { LetterCard(it) }
            item {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Digrams (multi-letter combinations)",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(top = 4.dp, bottom = 4.dp)
                )
                HorizontalDivider(color = MaterialTheme.colorScheme.primary)
                Spacer(modifier = Modifier.height(4.dp))
            }
            items(digrams) { entry ->
                Card(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = entry.digram,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.padding(end = 16.dp).weight(0.5f)
                        )
                        Text(
                            text = entry.pronunciation,
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.weight(2f)
                        )
                    }
                }
            }
            item {
                Spacer(modifier = Modifier.height(8.dp))
                Card(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "Czech is phonetically consistent",
                            style = MaterialTheme.typography.titleSmall,
                            color = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Unlike English, Czech spelling almost perfectly matches pronunciation. Once you learn the sounds of the letters, you can pronounce any word you read.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                    }
                }
            }
        }
    }
}
