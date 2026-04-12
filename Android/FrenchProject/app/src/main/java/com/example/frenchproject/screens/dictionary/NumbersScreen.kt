package com.example.frenchproject.screens.dictionary

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.frenchproject.ui.theme.FrenchBlue
import com.example.frenchproject.ui.theme.FrenchNavy

private data class NumberEntry(val french: String, val english: String)

private val digits = listOf(
    NumberEntry("zéro", "0 — zero"),
    NumberEntry("un / une", "1 — one (m. / f.)"),
    NumberEntry("deux", "2 — two"),
    NumberEntry("trois", "3 — three"),
    NumberEntry("quatre", "4 — four"),
    NumberEntry("cinq", "5 — five"),
    NumberEntry("six", "6 — six"),
    NumberEntry("sept", "7 — seven"),
    NumberEntry("huit", "8 — eight"),
    NumberEntry("neuf", "9 — nine")
)

private val teens = listOf(
    NumberEntry("dix", "10 — ten"),
    NumberEntry("onze", "11 — eleven"),
    NumberEntry("douze", "12 — twelve"),
    NumberEntry("treize", "13 — thirteen"),
    NumberEntry("quatorze", "14 — fourteen"),
    NumberEntry("quinze", "15 — fifteen"),
    NumberEntry("seize", "16 — sixteen"),
    NumberEntry("dix-sept", "17 — seventeen"),
    NumberEntry("dix-huit", "18 — eighteen"),
    NumberEntry("dix-neuf", "19 — nineteen")
)

private val tensBase = listOf(
    NumberEntry("vingt", "20 — twenty"),
    NumberEntry("trente", "30 — thirty"),
    NumberEntry("quarante", "40 — forty"),
    NumberEntry("cinquante", "50 — fifty"),
    NumberEntry("soixante", "60 — sixty")
)

private val seventies = listOf(
    NumberEntry("soixante-dix", "70 — seventy (lit. sixty-ten)"),
    NumberEntry("soixante et onze", "71 — seventy-one"),
    NumberEntry("soixante-douze", "72 — seventy-two"),
    NumberEntry("soixante-treize", "73 — seventy-three"),
    NumberEntry("soixante-quatorze", "74 — seventy-four"),
    NumberEntry("soixante-quinze", "75 — seventy-five"),
    NumberEntry("soixante-seize", "76 — seventy-six"),
    NumberEntry("soixante-dix-sept", "77 — seventy-seven"),
    NumberEntry("soixante-dix-huit", "78 — seventy-eight"),
    NumberEntry("soixante-dix-neuf", "79 — seventy-nine")
)

private val eightiesAndNineties = listOf(
    NumberEntry("quatre-vingts", "80 — eighty (lit. four-twenties)"),
    NumberEntry("quatre-vingt-un", "81 — eighty-one"),
    NumberEntry("quatre-vingt-deux", "82 — eighty-two"),
    NumberEntry("quatre-vingt-dix", "90 — ninety"),
    NumberEntry("quatre-vingt-onze", "91 — ninety-one"),
    NumberEntry("quatre-vingt-quinze", "95 — ninety-five"),
    NumberEntry("quatre-vingt-dix-neuf", "99 — ninety-nine")
)

private val largeNumbers = listOf(
    NumberEntry("cent", "100 — one hundred"),
    NumberEntry("deux cents", "200 — two hundred"),
    NumberEntry("deux cent cinq", "205 — two hundred and five"),
    NumberEntry("mille", "1 000 — one thousand"),
    NumberEntry("dix mille", "10 000 — ten thousand"),
    NumberEntry("cent mille", "100 000 — one hundred thousand"),
    NumberEntry("un million", "1 000 000 — one million")
)

private val ordinals = listOf(
    NumberEntry("premier / première", "1st — first (m. / f.)"),
    NumberEntry("deuxième", "2nd — second"),
    NumberEntry("troisième", "3rd — third"),
    NumberEntry("quatrième", "4th — fourth"),
    NumberEntry("cinquième", "5th — fifth"),
    NumberEntry("sixième", "6th — sixth"),
    NumberEntry("septième", "7th — seventh"),
    NumberEntry("huitième", "8th — eighth"),
    NumberEntry("neuvième", "9th — ninth"),
    NumberEntry("dixième", "10th — tenth")
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NumbersScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Numbers", color = Color.White) },
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
                    text = "French numbers follow regular patterns for most of the range, but the 70s, 80s, and 90s have unique structures rooted in an old base-20 (vigesimal) counting system.",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }

            // Digits 0–9
            item { NumSectionHeader("Digits 0–9") }
            items(digits.size) { i -> NumberEntryCard(digits[i]) }

            // Teens 10–19
            item {
                Spacer(modifier = Modifier.height(8.dp))
                NumSectionHeader("Teens 10–19")
            }
            items(teens.size) { i -> NumberEntryCard(teens[i]) }

            // Tens 20–69
            item {
                Spacer(modifier = Modifier.height(8.dp))
                NumSectionHeader("Tens 20–69")
            }
            items(tensBase.size) { i -> NumberEntryCard(tensBase[i]) }
            item {
                NumRuleCard(
                    title = "Forming numbers 21–69",
                    body = "For most numbers, join the ten and the unit with a hyphen:\n" +
                        "vingt-deux (22), trente-trois (33), quarante-quatre (44)\n\n" +
                        "Exception — use et (and) before un and onze:\n" +
                        "vingt et un (21), trente et un (31), quarante et un (41),\n" +
                        "cinquante et un (51), soixante et un (61)\n\n" +
                        "There is no et before any other number in this range."
                )
            }

            // 70s
            item {
                Spacer(modifier = Modifier.height(8.dp))
                NumSectionHeader("The 70s — soixante-dix series")
            }
            item {
                NumRuleCard(
                    title = "Why no septante?",
                    body = "Standard French has no word for 70. Instead it counts upward from 60:\n" +
                        "70 = soixante-dix (sixty-ten)\n" +
                        "71 = soixante et onze (sixty and eleven) ← only 70s number with et\n" +
                        "72–79 = soixante-douze … soixante-dix-neuf (hyphen, no et)"
                )
            }
            items(seventies.size) { i -> NumberEntryCard(seventies[i]) }

            // 80s & 90s
            item {
                Spacer(modifier = Modifier.height(8.dp))
                NumSectionHeader("The 80s & 90s — quatre-vingt series")
            }
            item {
                NumRuleCard(
                    title = "quatre-vingts — the s rule",
                    body = "80 = quatre-vingts (four-twenties). The s is present when the number stands alone.\n\n" +
                        "The s drops when followed by another digit:\n" +
                        "81 = quatre-vingt-un (no s, no et)\n" +
                        "82 = quatre-vingt-deux (no s, no et)\n\n" +
                        "90 counts on from 80:\n" +
                        "90 = quatre-vingt-dix (80+10)\n" +
                        "91 = quatre-vingt-onze, 95 = quatre-vingt-quinze\n\n" +
                        "There is no et anywhere in the 80s or 90s."
                )
            }
            items(eightiesAndNineties.size) { i -> NumberEntryCard(eightiesAndNineties[i]) }

            // Hundreds & beyond
            item {
                Spacer(modifier = Modifier.height(8.dp))
                NumSectionHeader("Hundreds & Beyond")
            }
            items(largeNumbers.size) { i -> NumberEntryCard(largeNumbers[i]) }
            item {
                NumRuleCard(
                    title = "The s on cents",
                    body = "deux cents (200) — s is present when cent stands alone.\n" +
                        "deux cent cinq (205) — s drops when followed by more digits.\n\n" +
                        "mille is invariable — never adds an s:\n" +
                        "deux mille (2 000), not deux milles."
                )
            }

            // Ordinal numbers
            item {
                Spacer(modifier = Modifier.height(8.dp))
                NumSectionHeader("Ordinal Numbers")
            }
            item {
                NumRuleCard(
                    title = "How ordinals are formed",
                    body = "Add the suffix -ième to the cardinal number:\n" +
                        "deux → deuxième, trois → troisième\n\n" +
                        "Spelling adjustments:\n" +
                        "• neuf → neuvième (f becomes v before -ième)\n" +
                        "• cinq → cinquième (add u before -ième)\n\n" +
                        "premier / première (1st) is the only irregular ordinal — it has distinct masculine and feminine forms. All others ending in -ième are the same for both genders."
                )
            }
            items(ordinals.size) { i -> NumberEntryCard(ordinals[i]) }

            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}

@Composable
private fun NumSectionHeader(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleSmall,
        fontWeight = FontWeight.Bold,
        color = FrenchBlue,
        modifier = Modifier.padding(bottom = 4.dp)
    )
    HorizontalDivider(color = FrenchBlue.copy(alpha = 0.35f))
    Spacer(modifier = Modifier.height(4.dp))
}

@Composable
private fun NumRuleCard(title: String, body: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE8EDF8)),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                color = FrenchBlue,
                fontSize = 13.sp,
                modifier = Modifier.padding(bottom = 6.dp)
            )
            Text(
                text = body,
                style = MaterialTheme.typography.bodySmall,
                color = FrenchNavy,
                lineHeight = 19.sp
            )
        }
    }
}

@Composable
private fun NumberEntryCard(entry: NumberEntry) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 3.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 9.dp)
        ) {
            Text(
                text = entry.french,
                fontWeight = FontWeight.Bold,
                color = FrenchBlue,
                fontSize = 14.sp,
                modifier = Modifier.width(200.dp)
            )
            Text(
                text = entry.english,
                style = MaterialTheme.typography.bodyMedium,
                color = FrenchNavy
            )
        }
    }
}
