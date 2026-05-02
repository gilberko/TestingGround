package com.example.czechappredo

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LettersPronunciationScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Letters & Pronunciation", fontSize = 18.sp, fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        },
        containerColor = Color.White
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {

            // ── 1. Word Stress ───────────────────────────────────────────
            LPSectionHeader("Word Stress")
            LPNote("Czech stress always falls on the first syllable of every word, no matter how long the word is. Long vowels (á, é, í…) make a vowel sound longer — they do NOT move the stress.")
            LPNote("Example: ne-dě-le (Sunday) — stress is on \"ne\", not on the long ě.")

            // ── 2. Short Vowels ──────────────────────────────────────────
            LPSectionHeader("Short Vowels")
            LPRow("a", "\"a\" in father")
            LPRow("e", "\"e\" in bed")
            LPRow("i / y", "\"i\" in sit  (i and y are identical in sound)")
            LPRow("o", "\"o\" in hot")
            LPRow("u", "\"oo\" in book")

            // ── 3. Long Vowels ───────────────────────────────────────────
            LPSectionHeader("Long Vowels — held about twice as long")
            LPRow("á", "\"a\" in father, held longer")
            LPRow("é", "\"e\" in bed, held longer")
            LPRow("í / ý", "\"ee\" in see  (both letters spell the same sound)")
            LPRow("ó", "\"o\" in more, held longer  (rare — mostly loanwords)")
            LPRow("ú / ů", "\"oo\" in moon  (identical sound; ů never starts a word)")

            // ── 4. ě — The Softening Vowel ───────────────────────────────
            LPSectionHeader("ě — The Softening Vowel")
            LPNote("ě is not simply a longer e — it changes the consonant that comes before it:")
            LPRow("dě", "→ \"dye\"")
            LPRow("tě", "→ \"tye\"")
            LPRow("ně", "→ \"nye\"")
            LPRow("mě", "→ \"mnye\"")
            LPNote("Example: město (city) → sounds like \"mnye-sto\"")
            LPNote("After any other consonant, ě simply sounds like \"ye\".")

            // ── 5. Consonants With Háček ─────────────────────────────────
            LPSectionHeader("Consonants With Háček  ( ˇ )")
            LPRow("č", "\"ch\" in cheese")
            LPRow("š", "\"sh\" in shoe")
            LPRow("ž", "\"s\" in measure  /  French j")
            LPRow("ř", "Unique Czech sound — a simultaneous rolled-r and ž. Like trilling your tongue while saying \"zh\". Widely considered the hardest Czech sound for foreigners.", note = "Example: řeka (river), Dvořák")
            LPRow("ň", "\"ny\" in canyon")
            LPRow("ď", "\"dy\" — soft d, like \"dew\" said quickly")
            LPRow("ť", "\"ty\" — soft t, like \"tune\" said quickly")

            // ── 6. Letters That Surprise English Speakers ────────────────
            LPSectionHeader("Letters That Surprise English Speakers")
            LPRow("c", "\"ts\" in cats  (never \"k\" or \"s\" alone)")
            LPRow("j", "\"y\" in yes  (never \"dj\")")
            LPRow("ch", "\"kh\" — like Scottish loch or German Bach. A raspy sound made in the throat.", note = "ch is its own letter in the Czech alphabet, listed after h.")
            LPRow("h", "breathy, voiced — slightly softer than English h")
            LPRow("w", "same as v  (only in foreign words)")
            LPRow("q", "\"kv\"  (only in foreign words)")
            LPRow("x", "\"ks\"  (only in foreign words)")

            // ── 7. Letter Combinations That Change Sound ─────────────────
            LPSectionHeader("Letter Combinations That Change Sound")
            LPRow("ch", "Always \"kh\" — never read as k + h separately. It is a single letter.")
            LPRow("dž", "like \"j\" in jungle. Appears in loanwords.", note = "Example: džem (jam), džíny (jeans)")
            LPNote("The combination of a consonant + ě also changes pronunciation — see the ě section above.")

            // ── 8. Final Devoicing ───────────────────────────────────────
            LPSectionHeader("Final Devoicing")
            LPNote("At the end of a word, voiced consonants automatically become their voiceless counterpart. The spelling does not change — only the pronunciation does.")
            LPRow("b → p", "")
            LPRow("d → t", "")
            LPRow("g → k", "")
            LPRow("v → f", "")
            LPRow("z → s", "")
            LPRow("ž → š", "")
            LPNote("Example: hrad (castle) is spelled with d but pronounced \"hrat\".")

            // ── 9. Voice Assimilation in Clusters ────────────────────────
            LPSectionHeader("Voice Assimilation in Consonant Clusters")
            LPNote("When two consonants appear together, they harmonize in voicing. The last consonant in the cluster decides whether the whole group is voiced or voiceless.")
            LPRow("kde (where)", "k is normally voiceless, but before voiced d it becomes voiced → \"gde\"")
            LPRow("vstát (to stand up)", "v is voiced, but before voiceless st it becomes f → \"fstát\"")

            // ── 10. Syllabic r and l ─────────────────────────────────────
            LPSectionHeader("Syllabic r and l")
            LPNote("In Czech, r and l can act as vowels — they carry a whole syllable by themselves, with no vowel needed.")
            LPRow("krk", "neck — one syllable: k‑R‑k  (no vowel at all)")
            LPRow("vlk", "wolf — one syllable: v‑L‑k")
            LPRow("prst", "finger — one syllable: p‑R‑st")

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun LPSectionHeader(text: String) {
    Spacer(modifier = Modifier.height(20.dp))
    Text(
        text = text,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        color = ButtonBlue
    )
    Spacer(modifier = Modifier.height(6.dp))
}

@Composable
private fun LPRow(letter: String, pronunciation: String, note: String = "") {
    Column(modifier = Modifier.padding(vertical = 3.dp)) {
        if (pronunciation.isNotEmpty()) {
            Text(
                text = buildAnnotatedString {
                    withStyle(SpanStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.Black)) {
                        append(letter)
                    }
                    withStyle(SpanStyle(fontSize = 16.sp, color = Color.DarkGray)) {
                        append("  —  $pronunciation")
                    }
                }
            )
        } else {
            Text(
                text = letter,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }
        if (note.isNotEmpty()) {
            Text(
                text = note,
                fontSize = 13.sp,
                fontStyle = FontStyle.Italic,
                color = Color.Gray,
                modifier = Modifier.padding(start = 4.dp, top = 1.dp)
            )
        }
    }
}

@Composable
private fun LPNote(text: String) {
    Text(
        text = text,
        fontSize = 14.sp,
        color = Color.DarkGray,
        modifier = Modifier.padding(vertical = 4.dp)
    )
}
