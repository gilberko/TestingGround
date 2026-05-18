package com.example.czechappredo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdjectivesScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Adjectives", fontSize = 18.sp, fontWeight = FontWeight.Bold) },
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

            // ── How Czech Adjectives Work ──────────────────────────────────
            AJSection("How Czech Adjectives Work")
            AJNote("Adjectives agree with their noun in gender, number, and case. The ending changes every time the noun's grammatical role changes.")
            AJNote("Examples: dobrý muž (good man, m.) · dobrá žena (good woman, f.) · dobré víno (good wine, n.)")

            // ── Hard and Soft Adjective Types ─────────────────────────────
            AJSection("Hard and Soft Adjective Types")
            AJNote("Czech adjectives come in two types — hard and soft. The type is a fixed property of the adjective itself; it does not depend on which noun the adjective modifies.")
            AJNote("Hard adjectives (the large majority of everyday descriptive adjectives) have distinct endings for each gender in the nominative: -ý for masculine, -á for feminine, -é for neuter. Examples: dobrý / dobrá / dobré (good), nový / nová / nové (new), starý / stará / staré (old).")
            AJNote("Soft adjectives end in -í for ALL genders — the form is the same for masculine, feminine, and neuter in the nominative. They are typically formed with suffixes like -ní or -cí and express relations or categories. Examples: jarní (spring), domácí (homemade), cizí (foreign), poslední (last).")
            AJNote("Important: the noun's own ending (-a or -e) does NOT determine the adjective form. A hard adjective always uses -á for any feminine noun — dobrá žena (woman, ends in -a) and dobrá růže (rose, ends in -e) both use -á. The noun's declension class affects how the noun itself declines, not the adjective.")
            AJNote("Concrete example: 'beautiful' is krásný (m) / krásná (f) / krásné (n). 'A beautiful woman' = krásná žena. 'A beautiful rose' = krásná růže. Both feminine nouns take krásná — the -á ending does not change even though žena ends in -a and růže ends in -e.")
            AJNote("This applies to all genders. For masculine: dobrý muž / dobrý hrad (both use -ý regardless of the noun's own hard/soft class). For neuter: dobré město / dobré moře (both -é). For plural: dobří muži / dobré ženy (the -í / -é split depends only on animate vs. inanimate, not on the noun's declension class). Soft adjectives use -í across the board for all genders and plural in the nominative.")

            // ── Nominative ────────────────────────────────────────────────
            AJSection("Nominative (Subject)")
            AJTable(
                label = "Hard adjective — dobrý (good)",
                rows = listOf(
                    "Masculine" to "-ý → dobrý  (dobrý muž)",
                    "Feminine" to "-á → dobrá  (dobrá žena)",
                    "Neuter" to "-é → dobré  (dobré víno)",
                    "Plural — masc animate" to "-í → dobří  (dobří muži)",
                    "Plural — others" to "-é → dobré  (dobré ženy)"
                )
            )
            AJNote("Soft adjectives end in -í (e.g. jarní = spring, domácí = homemade, cizí = foreign, poslední = last). In the nominative, -í is used for ALL genders — it does not change.")
            AJTable(
                label = "Soft adjective — jarní (spring)",
                rows = listOf(
                    "All genders" to "jarní  (jarní den · jarní zahrada · jarní ráno)"
                )
            )
            AJNote("Common example — unavený / unavená / unavené (tired): Jsem unavený. (I am tired. — man.) / Jsem unavená. (I am tired. — woman.) / Dítě je unavené. (The child is tired.)")
            AJNote("Note on drahý / drahá / drahé: This adjective has two distinct meanings. (1) Expensive — Toto auto je drahé. (This car is expensive.) (2) Dear / precious — used as a term of endearment or in formal/literary address. So 'drahé dítě' means 'dear child' or 'precious child' in the right context — not 'expensive child.' Similarly: Drahý příteli! = Dear friend! / Moje drahá. = My dear. In everyday speech about prices, drahý means expensive. In emotional or formal address, it means dear/precious. Context tells you which is intended.")

            // ── Accusative ────────────────────────────────────────────────
            AJSection("Accusative (Direct Object)")
            AJTable(
                label = "Hard adjective — dobrý",
                rows = listOf(
                    "Masc animate" to "-ého → dobrého  (vidím dobrého muže)",
                    "Masc inanimate" to "-ý (= nom) → dobrý  (vidím dobrý film)",
                    "Neuter" to "-é (= nom) → dobré  (vidím dobré víno)",
                    "Feminine" to "-ou → dobrou  (vidím dobrou ženu)",
                    "Plural" to "-é → dobré  (vidím dobré filmy)"
                )
            )
            AJTable(
                label = "Soft adjective — cizí (foreign)",
                rows = listOf(
                    "Masc animate" to "-ího → cizího  (vidím cizího muže)",
                    "Masc inanimate" to "-í (= nom) → cizí  (vidím cizí vlak)",
                    "Neuter" to "-í (= nom) → cizí  (vidím cizí město)",
                    "Feminine" to "-í → cizí  (vidím cizí ženu)",
                    "Plural" to "-í → cizí  (vidím cizí lidi)"
                )
            )

            // ── Genitive ──────────────────────────────────────────────────
            AJSection("Genitive (Of / From / Without)")
            AJTable(
                label = "Hard adjective — dobrý",
                rows = listOf(
                    "Masc + Neuter" to "-ého → dobrého  (od dobrého muže)",
                    "Feminine" to "-é → dobré  (od dobré ženy)",
                    "Plural" to "-ých → dobrých  (od dobrých lidí)"
                )
            )
            AJTable(
                label = "Soft adjective — cizí (foreign)",
                rows = listOf(
                    "Masc + Neuter" to "-ího → cizího  (od cizího muže)",
                    "Feminine" to "-í → cizí  (od cizí ženy)",
                    "Plural" to "-ích → cizích  (od cizích lidí)"
                )
            )

            // ── Dative ────────────────────────────────────────────────────
            AJSection("Dative (To / For)")
            AJTable(
                label = "Hard adjective — dobrý",
                rows = listOf(
                    "Masc + Neuter" to "-ému → dobrému  (dávám dobrému muži)",
                    "Feminine" to "-é → dobré  (dávám dobré ženě)",
                    "Plural" to "-ým → dobrým  (dávám dobrým lidem)"
                )
            )
            AJNote("Soft adjectives: masc/neuter → jarnímu; feminine → jarní; plural → jarním.")

            // ── Locative ──────────────────────────────────────────────────
            AJSection("Locative (About / In / On — always with a preposition)")
            AJTable(
                label = "Hard adjective — dobrý",
                rows = listOf(
                    "Masc + Neuter" to "-ém → dobrém  (o dobrém muži)",
                    "Feminine" to "-é → dobré  (o dobré ženě)",
                    "Plural" to "-ých → dobrých  (o dobrých lidech)"
                )
            )
            AJNote("Soft adjectives: masc/neuter → jarním; feminine → jarní; plural → jarních.")

            // ── Instrumental ──────────────────────────────────────────────
            AJSection("Instrumental (With / By means of)")
            AJTable(
                label = "Hard adjective — dobrý",
                rows = listOf(
                    "Masc + Neuter" to "-ým → dobrým  (s dobrým mužem)",
                    "Feminine" to "-ou → dobrou  (s dobrou ženou)",
                    "Plural" to "-ými → dobrými  (s dobrými lidmi)"
                )
            )
            AJNote("Soft adjectives: masc/neuter → jarním; feminine → jarní; plural → jarními.")

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun AJSection(text: String) {
    Spacer(modifier = Modifier.height(20.dp))
    Text(text = text, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = ButtonBlue)
    Spacer(modifier = Modifier.height(6.dp))
}

@Composable
private fun AJNote(text: String) {
    Text(
        text = text,
        fontSize = 13.sp,
        fontStyle = FontStyle.Italic,
        color = Color.Gray,
        modifier = Modifier.padding(vertical = 3.dp)
    )
}

@Composable
private fun AJTable(label: String, rows: List<Pair<String, String>>) {
    Text(
        text = label,
        fontSize = 13.sp,
        fontWeight = FontWeight.Bold,
        color = Color.DarkGray,
        modifier = Modifier.padding(top = 6.dp, bottom = 2.dp)
    )
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .background(Color(0xFFF5F5F5), shape = RoundedCornerShape(6.dp))
            .padding(horizontal = 8.dp, vertical = 6.dp)
    ) {
        Column {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Category",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "Form / Example",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray,
                    modifier = Modifier.weight(1.8f)
                )
            }
            HorizontalDivider(
                modifier = Modifier.padding(vertical = 4.dp),
                color = Color.LightGray,
                thickness = 0.5.dp
            )
            rows.forEach { (category, form) ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 3.dp)
                ) {
                    Text(
                        text = category,
                        fontSize = 13.sp,
                        color = Color.DarkGray,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = form,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier.weight(1.8f)
                    )
                }
            }
        }
    }
}
