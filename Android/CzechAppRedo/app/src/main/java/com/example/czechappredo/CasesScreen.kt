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
fun CasesScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Cases", fontSize = 18.sp, fontWeight = FontWeight.Bold) },
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

            // ── Introduction ─────────────────────────────────────────────
            CaseSectionHeader("What Are Cases?")
            CaseNote("Czech has 7 cases. A case is a role that a noun plays in a sentence — subject, object, owner, location, and so on. The noun's ending changes to signal that role. Adjectives and pronouns also change to agree with the noun they describe.")
            CaseNote("Each case answers a question. Learning that question helps you know which ending to use.")

            // ── The 7 Cases ──────────────────────────────────────────────
            CaseSectionHeader("The 7 Cases")

            CaseBlock(
                number = "1",
                caseName = "Nominativ  (Nominative)",
                usage = "The subject of the sentence — who or what is doing the action. Also the dictionary form of a word.",
                example = "Ten muž jde.",
                translation = "The man is going."
            )
            CaseNote("The Nominative is the base/dictionary form — no ending change occurs.")
            CaseMiniTable(
                rows = listOf(
                    "Masc. inanimate" to "hrad",
                    "Masc. animate" to "muž",
                    "Feminine -a" to "žena",
                    "Feminine -e (soft)" to "růže",
                    "Neuter -o" to "město",
                    "Neuter -e (soft)" to "moře",
                    "Fem. consonant stem" to "místnost"
                )
            )

            CaseBlock(
                number = "2",
                caseName = "Genitiv  (Genitive)",
                usage = "Possession (\"of\"), absence, negation, and quantity. Also used after many common prepositions.",
                prepositions = "do, z, od, bez, u, pro, podle, kolem, vedle",
                example = "Jsem bez vody.",
                translation = "I am without water."
            )
            CaseMiniTable(
                rows = listOf(
                    "Masc. inanimate (hard)" to "hrad → hradu",
                    "Masc. inanimate (soft)" to "nůž → nože",
                    "Masc. animate (hard)" to "pán → pána;  tatínek → tatínka;  dědeček → dědečka",
                    "Masc. animate (soft)" to "muž → muže",
                    "Feminine -a" to "žena → ženy",
                    "Feminine -e (soft)" to "růže → růže  (no change)",
                    "Neuter -o" to "město → města",
                    "Neuter -e (soft)" to "moře → moře  (no change)",
                    "Fem. consonant stem" to "místnost → místnosti"
                )
            )

            CaseNote("Fleeting e (pohybné e): Some masculine animate nouns ending in -ek or -eček drop the -e- before their final consonant whenever a case ending is added. Examples: tatínek (daddy) → tatínka (not tatíneka); dědeček (grandpa) → dědečka (not dědečeka). The underlying pattern (hard animate → -a) is unchanged — only the spelling shifts.")

            CaseBlock(
                number = "3",
                caseName = "Dativ  (Dative)",
                usage = "The indirect object — to/for whom something is given or done.",
                prepositions = "k / ke, kvůli, díky, naproti",
                example = "Dám to Janovi.",
                translation = "I'll give it to Jan."
            )
            CaseMiniTable(
                rows = listOf(
                    "Masc. inanimate (hard)" to "hrad → hradu",
                    "Masc. inanimate (soft)" to "nůž → noži",
                    "Masc. animate" to "muž → muži; pán → pánovi",
                    "Feminine -a" to "žena → ženě; matka → matce",
                    "Feminine -e (soft)" to "růže → růži",
                    "Neuter -o" to "město → městu",
                    "Neuter -e (soft)" to "moře → moři",
                    "Fem. consonant stem" to "místnost → místnosti"
                )
            )

            CaseBlock(
                number = "4",
                caseName = "Akuzativ  (Accusative)",
                usage = "The direct object — what is directly affected by the action. For masculine animate nouns the Accusative equals the Genitive; for inanimate nouns it equals the Nominative.",
                prepositions = "na, do, za, pro, přes, skrz",
                example = "Vidím Jana.",
                translation = "I see Jan."
            )
            CaseMiniTable(
                rows = listOf(
                    "Masc. inanimate" to "hrad → hrad  (= Nominative)",
                    "Masc. animate" to "muž → muže; pán → pána; tatínek → tatínka; dědeček → dědečka  (= Genitive)",
                    "Feminine -a" to "žena → ženu; matka → matku",
                    "Feminine -e (soft)" to "růže → růži",
                    "Neuter -o" to "město → město  (= Nominative)",
                    "Neuter -e (soft)" to "moře → moře  (= Nominative)",
                    "Fem. consonant stem" to "místnost → místnost  (= Nominative)"
                )
            )

            CaseNote("Masculine animate accusative = genitive, so the same fleeting-e rule applies: tatínka, dědečka.")

            CaseBlock(
                number = "5",
                caseName = "Vokativ  (Vocative)",
                usage = "Direct address — calling or speaking to someone by name or title.",
                example = "Jene!  /  Pane!",
                translation = "Hey Jan!  /  Sir!"
            )
            CaseMiniTable(
                rows = listOf(
                    "Masc. animate (hard)" to "pán → pane; Jan → Jane",
                    "Masc. animate (soft)" to "muž → muži",
                    "Masc. inanimate" to "(rarely used in direct address)",
                    "Feminine -a" to "žena → ženo; matka → matko",
                    "Feminine -e (soft)" to "růže → růže  (no change)",
                    "Neuter" to "(rarely used in direct address)"
                )
            )

            CaseBlock(
                number = "6",
                caseName = "Lokál  (Locative)",
                usage = "Location or the topic of discussion. Always used with a preposition — it never appears alone.",
                prepositions = "v / ve, na, o, po, při",
                example = "Jsem v Praze.",
                translation = "I am in Prague."
            )
            CaseMiniTable(
                rows = listOf(
                    "Masc. inanimate (hard)" to "hrad → hradě",
                    "Masc. inanimate (soft)" to "nůž → noži",
                    "Masc. animate (hard)" to "pán → pánovi",
                    "Masc. animate (soft)" to "muž → muži",
                    "Feminine -a" to "žena → ženě; matka → matce",
                    "Feminine -e (soft)" to "růže → růži",
                    "Neuter -o" to "město → městě",
                    "Neuter -e (soft)" to "moře → moři",
                    "Fem. consonant stem" to "místnost → místnosti"
                )
            )

            CaseBlock(
                number = "7",
                caseName = "Instrumentál  (Instrumental)",
                usage = "Means (\"by / with\"), accompaniment, and travelling by vehicle. Also used after certain prepositions.",
                prepositions = "s / se, pod, nad, před, za, mezi",
                example = "Jdu autobusem.",
                translation = "I go by bus."
            )
            CaseMiniTable(
                rows = listOf(
                    "Masc. inanimate (hard)" to "hrad → hradem",
                    "Masc. inanimate (soft)" to "nůž → nožem",
                    "Masc. animate" to "muž → mužem; pán → pánem",
                    "Feminine -a" to "žena → ženou; matka → matkou",
                    "Feminine -e (soft)" to "růže → růží  (long -í)",
                    "Neuter -o" to "město → městem",
                    "Neuter -e (soft)" to "moře → mořem",
                    "Fem. consonant stem" to "místnost → místností  (long -í)"
                )
            )

            // ── Declension Tables ────────────────────────────────────────
            CaseSectionHeader("Declension Tables — Singular")
            CaseNote("The singular tables below cover hard and soft patterns for all three genders. Irregular and consonant-stem nouns are noted at the end.")

            Spacer(modifier = Modifier.height(12.dp))
            DeclensionTable(
                noun = "muž  (man)",
                label = "Masculine animate, hard",
                rows = listOf(
                    "1. Nominativ" to "muž",
                    "2. Genitiv" to "muže",
                    "3. Dativ" to "muži",
                    "4. Akuzativ" to "muže",
                    "5. Vokativ" to "muži",
                    "6. Lokál" to "muži",
                    "7. Instrumentál" to "mužem"
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            DeclensionTable(
                noun = "žena  (woman)",
                label = "Feminine, hard  (-a)",
                rows = listOf(
                    "1. Nominativ" to "žena",
                    "2. Genitiv" to "ženy",
                    "3. Dativ" to "ženě",
                    "4. Akuzativ" to "ženu",
                    "5. Vokativ" to "ženo",
                    "6. Lokál" to "ženě",
                    "7. Instrumentál" to "ženou"
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            DeclensionTable(
                noun = "město  (city)",
                label = "Neuter, hard  (-o)",
                rows = listOf(
                    "1. Nominativ" to "město",
                    "2. Genitiv" to "města",
                    "3. Dativ" to "městu",
                    "4. Akuzativ" to "město",
                    "5. Vokativ" to "město",
                    "6. Lokál" to "městě",
                    "7. Instrumentál" to "městem"
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            // ── Animate vs Inanimate ──────────────────────────────────────
            CaseSectionHeader("Masculine Animate vs. Inanimate")
            CaseNote("The key difference between masculine nouns is animacy — whether the noun refers to a living being. Animate nouns (people, animals) take the Genitive form as their Accusative. Inanimate nouns (objects, concepts) take the Nominative form as their Accusative.")
            CaseNote("Compare:  Vidím muže.  (I see the man — animate, Acc = Gen)  vs.  Vidím hrad.  (I see the castle — inanimate, Acc = Nom)")
            Spacer(modifier = Modifier.height(12.dp))
            DeclensionTable(
                noun = "hrad  (castle)",
                label = "Masculine inanimate, hard  — Acc = Nom",
                rows = listOf(
                    "1. Nominativ" to "hrad",
                    "2. Genitiv" to "hradu",
                    "3. Dativ" to "hradu",
                    "4. Akuzativ" to "hrad",
                    "5. Vokativ" to "hrade",
                    "6. Lokál" to "hradě",
                    "7. Instrumentál" to "hradem"
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            // ── Feminine Soft ─────────────────────────────────────────────
            CaseSectionHeader("Feminine Soft Pattern  (-e)")
            CaseNote("Feminine nouns can also end in -e (not just -a). They follow the soft declension pattern. Notice that Nominative, Genitive, and Vocative share the -e form; Dative, Accusative, and Locative use -i; and the Instrumental ends in -í (long vowel).")
            Spacer(modifier = Modifier.height(12.dp))
            DeclensionTable(
                noun = "růže  (rose)",
                label = "Feminine, soft  (-e)",
                rows = listOf(
                    "1. Nominativ" to "růže",
                    "2. Genitiv" to "růže",
                    "3. Dativ" to "růži",
                    "4. Akuzativ" to "růži",
                    "5. Vokativ" to "růže",
                    "6. Lokál" to "růži",
                    "7. Instrumentál" to "růží"
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            // ── Irregular / Exceptional ───────────────────────────────────
            CaseSectionHeader("Irregular and Exceptional Nouns")
            CaseNote("Some nouns do not follow the hard or soft patterns above. Three important types to be aware of:")
            CaseNote("Soft neuter (-e):  moře (sea) ends in -e but is neuter, not feminine. It declines:  moře / moře / moři / moře / moře / moři / mořem. Other common soft neuter nouns include srdce (heart) and pole (field).")
            CaseNote("Stem-changing masculine:  den (day) loses its -e- in most forms. Gen: dne, Dat: dni / dnu, Loc: dni / dnu, Ins: dnem. The same pattern appears in týden (week) and kámen (stone).")
            CaseNote("Highly irregular:  dítě (child) is neuter but uses unique endings unlike any standard pattern — Gen: dítěte, Dat: dítěti, Acc: dítě, Loc: dítěti, Ins: dítětem. Its plural is also completely different: děti (children). Best memorised individually.")

            // ── Plural Tables ─────────────────────────────────────────────
            CaseSectionHeader("Declension Tables — Plural")
            CaseNote("Plural endings vary by gender, animacy, and hard/soft stem. Masculine animate nouns soften the final consonant in the Nominative/Vocative plural (muž → muži, not *mužy). Note that for masculine animate, the Accusative plural equals the Genitive singular — not the Genitive plural.")
            Spacer(modifier = Modifier.height(12.dp))
            DeclensionTable(
                noun = "muži  (men)",
                label = "Masculine animate, hard — plural",
                rows = listOf(
                    "1. Nominativ" to "muži",
                    "2. Genitiv" to "mužů",
                    "3. Dativ" to "mužům",
                    "4. Akuzativ" to "muže",
                    "5. Vokativ" to "muži",
                    "6. Lokál" to "mužích",
                    "7. Instrumentál" to "muži"
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            DeclensionTable(
                noun = "hrady  (castles)",
                label = "Masculine inanimate, hard — plural  (Acc = Nom)",
                rows = listOf(
                    "1. Nominativ" to "hrady",
                    "2. Genitiv" to "hradů",
                    "3. Dativ" to "hradům",
                    "4. Akuzativ" to "hrady",
                    "5. Vokativ" to "hrady",
                    "6. Lokál" to "hradech",
                    "7. Instrumentál" to "hrady"
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            DeclensionTable(
                noun = "ženy  (women)",
                label = "Feminine, hard — plural",
                rows = listOf(
                    "1. Nominativ" to "ženy",
                    "2. Genitiv" to "žen",
                    "3. Dativ" to "ženám",
                    "4. Akuzativ" to "ženy",
                    "5. Vokativ" to "ženy",
                    "6. Lokál" to "ženách",
                    "7. Instrumentál" to "ženami"
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            DeclensionTable(
                noun = "růže  (roses)",
                label = "Feminine, soft — plural",
                rows = listOf(
                    "1. Nominativ" to "růže",
                    "2. Genitiv" to "růží",
                    "3. Dativ" to "růžím",
                    "4. Akuzativ" to "růže",
                    "5. Vokativ" to "růže",
                    "6. Lokál" to "růžích",
                    "7. Instrumentál" to "růžemi"
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            DeclensionTable(
                noun = "města  (cities)",
                label = "Neuter, hard — plural",
                rows = listOf(
                    "1. Nominativ" to "města",
                    "2. Genitiv" to "měst",
                    "3. Dativ" to "městům",
                    "4. Akuzativ" to "města",
                    "5. Vokativ" to "města",
                    "6. Lokál" to "městech",
                    "7. Instrumentál" to "městy"
                )
            )

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun CaseSectionHeader(text: String) {
    Spacer(modifier = Modifier.height(20.dp))
    Text(text = text, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = ButtonBlue)
    Spacer(modifier = Modifier.height(6.dp))
}

@Composable
private fun CaseNote(text: String) {
    Text(
        text = text,
        fontSize = 14.sp,
        color = Color.DarkGray,
        modifier = Modifier.padding(vertical = 4.dp)
    )
}

@Composable
private fun CaseBlock(
    number: String,
    caseName: String,
    usage: String,
    prepositions: String = "",
    example: String,
    translation: String
) {
    Spacer(modifier = Modifier.height(12.dp))
    Text(text = "$number.  $caseName", fontSize = 17.sp, fontWeight = FontWeight.Bold, color = Color.Black)
    Spacer(modifier = Modifier.height(3.dp))
    Text(text = usage, fontSize = 14.sp, color = Color.DarkGray)
    if (prepositions.isNotEmpty()) {
        Text(
            text = "After: $prepositions",
            fontSize = 14.sp,
            fontStyle = FontStyle.Italic,
            color = Color.DarkGray,
            modifier = Modifier.padding(top = 2.dp)
        )
    }
    Text(
        text = example,
        fontSize = 15.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Black,
        modifier = Modifier.padding(top = 5.dp)
    )
    Text(text = translation, fontSize = 14.sp, fontStyle = FontStyle.Italic, color = Color.Gray)
}

@Composable
private fun CaseMiniTable(rows: List<Pair<String, String>>) {
    Spacer(modifier = Modifier.height(6.dp))
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF5F5F5), shape = RoundedCornerShape(6.dp))
            .padding(horizontal = 10.dp, vertical = 6.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Type",
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = ButtonBlue,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = "Form",
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = ButtonBlue,
                modifier = Modifier.weight(1.2f)
            )
        }
        rows.forEach { (type, form) ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp)
            ) {
                Text(text = type, fontSize = 12.sp, color = Color.DarkGray, modifier = Modifier.weight(1f))
                Text(text = form, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color.Black, modifier = Modifier.weight(1.2f))
            }
        }
    }
    Spacer(modifier = Modifier.height(4.dp))
}

@Composable
private fun DeclensionTable(noun: String, label: String, rows: List<Pair<String, String>>) {
    Text(text = noun, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.Black)
    Text(text = label, fontSize = 13.sp, color = Color.Gray, fontStyle = FontStyle.Italic)
    Spacer(modifier = Modifier.height(4.dp))
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Case",
            modifier = Modifier.weight(1f),
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = ButtonBlue
        )
        Text(
            text = "Form",
            modifier = Modifier.weight(0.6f),
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = ButtonBlue
        )
    }
    HorizontalDivider(modifier = Modifier.padding(vertical = 3.dp))
    rows.forEach { (caseName, form) ->
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 3.dp)
        ) {
            Text(text = caseName, modifier = Modifier.weight(1f), fontSize = 15.sp, color = Color.DarkGray)
            Text(
                text = form,
                modifier = Modifier.weight(0.6f),
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }
    }
}
