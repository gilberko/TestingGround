package com.example.arabicapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun LettersForHebrewSpeakersScreen() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        HEBSectionHeader("Why Hebrew Speakers Have an Advantage")
        HEBBodyText("Hebrew and Arabic are both Semitic languages. This gives Hebrew speakers a massive head start that speakers of other languages don't have:")
        Spacer(modifier = Modifier.height(8.dp))
        HEBBodyText("• Both scripts are written right to left")
        HEBBodyText("• Both are consonant-based — short vowels are usually not written, only implied")
        HEBBodyText("• Both have the same concept of roots: a 3-letter root carries a core meaning, and words are built from it by adding patterns (binyan in Hebrew = wazn in Arabic)")
        HEBBodyText("• ~17 of Arabic's 28 sounds are identical or very close to Hebrew sounds")
        HEBBodyText("• The definite article (ה in Hebrew, ال in Arabic) works similarly")
        Spacer(modifier = Modifier.height(8.dp))
        HEBBodyText("Hebrew has 22 letters. Arabic has 28. The 6 extra Arabic letters are: ث ذ ص ض ظ غ — most of which simplify in Palestinian spoken Arabic anyway.")
        Spacer(modifier = Modifier.height(8.dp))
        HEBBodyText("The main new challenge: Arabic letters take up to 4 different written shapes depending on position in the word. Hebrew has sofit (final) forms for only 5 letters — כ ם ן ף ץ. In Arabic, almost every letter has 4 forms: isolated, initial, medial, and final.")

        HEBSectionHeader("The 28-Letter Reference Table")
        HEBBodyText("All 28 Arabic letters with their Hebrew equivalent and all 4 positional forms. Letters marked * are non-connectors — they only connect to the right and never to the letter that follows.")
        Spacer(modifier = Modifier.height(8.dp))
        HEBTableHeader()
        HEBLetterRow("1",  "ا", "Alef",  "א",        "ا  ·  ا  ·  ـا  ·  ـا  *", true)
        HEBLetterRow("2",  "ب", "Ba",    "ב",        "ب  ·  بـ  ·  ـبـ  ·  ـب", false)
        HEBLetterRow("3",  "ت", "Ta",    "ת",        "ت  ·  تـ  ·  ـتـ  ·  ـت", true)
        HEBLetterRow("4",  "ث", "Tha",   "—",        "ث  ·  ثـ  ·  ـثـ  ·  ـث", false)
        HEBLetterRow("5",  "ج", "Jim",   "—",        "ج  ·  جـ  ·  ـجـ  ·  ـج", true)
        HEBLetterRow("6",  "ح", "Ha",    "ח",        "ح  ·  حـ  ·  ـحـ  ·  ـح", false)
        HEBLetterRow("7",  "خ", "Kha",   "כ (soft)", "خ  ·  خـ  ·  ـخـ  ·  ـخ", true)
        HEBLetterRow("8",  "د", "Dal",   "ד",        "د  ·  د  ·  ـد  ·  ـد  *", false)
        HEBLetterRow("9",  "ذ", "Dhal",  "—",        "ذ  ·  ذ  ·  ـذ  ·  ـذ  *", true)
        HEBLetterRow("10", "ر", "Ra",    "ר",        "ر  ·  ر  ·  ـر  ·  ـر  *", false)
        HEBLetterRow("11", "ز", "Zain",  "ז",        "ز  ·  ز  ·  ـز  ·  ـز  *", true)
        HEBLetterRow("12", "س", "Sin",   "ס",        "س  ·  سـ  ·  ـسـ  ·  ـس", false)
        HEBLetterRow("13", "ش", "Shin",  "ש",        "ش  ·  شـ  ·  ـشـ  ·  ـش", true)
        HEBLetterRow("14", "ص", "Sad",   "צ (≈)",    "ص  ·  صـ  ·  ـصـ  ·  ـص", false)
        HEBLetterRow("15", "ض", "Dad",   "—",        "ض  ·  ضـ  ·  ـضـ  ·  ـض", true)
        HEBLetterRow("16", "ط", "Ta",    "ט (≈)",    "ط  ·  طـ  ·  ـطـ  ·  ـط", false)
        HEBLetterRow("17", "ظ", "Dha",   "—",        "ظ  ·  ظـ  ·  ـظـ  ·  ـظ", true)
        HEBLetterRow("18", "ع", "Ain",   "ע",        "ع  ·  عـ  ·  ـعـ  ·  ـع", false)
        HEBLetterRow("19", "غ", "Ghain", "—",        "غ  ·  غـ  ·  ـغـ  ·  ـغ", true)
        HEBLetterRow("20", "ف", "Fa",    "פ (f)",    "ف  ·  فـ  ·  ـفـ  ·  ـف", false)
        HEBLetterRow("21", "ق", "Qaf",   "ק",        "ق  ·  قـ  ·  ـقـ  ·  ـق", true)
        HEBLetterRow("22", "ك", "Kaf",   "כ (hard)", "ك  ·  كـ  ·  ـكـ  ·  ـك", false)
        HEBLetterRow("23", "ل", "Lam",   "ל",        "ل  ·  لـ  ·  ـلـ  ·  ـل", true)
        HEBLetterRow("24", "م", "Mim",   "מ",        "م  ·  مـ  ·  ـمـ  ·  ـم", false)
        HEBLetterRow("25", "ن", "Nun",   "נ",        "ن  ·  نـ  ·  ـنـ  ·  ـن", true)
        HEBLetterRow("26", "ه", "Ha",    "ה",        "ه  ·  هـ  ·  ـهـ  ·  ـه", false)
        HEBLetterRow("27", "و", "Waw",   "ו",        "و  ·  و  ·  ـو  ·  ـو  *", true)
        HEBLetterRow("28", "ي", "Ya",    "י",        "ي  ·  يـ  ·  ـيـ  ·  ـي", false)

        HEBSectionHeader("Letter Families — Same Shape, Different Dots")
        HEBBodyText("Arabic reuses base shapes very efficiently. Learn one shape and you already know 2–3 letters. Dots are the only difference:")
        Spacer(modifier = Modifier.height(12.dp))

        HEBBodyText("ب  ت  ث — same cup shape")
        HEBBodyText("   ب Ba: 1 dot below  |  ت Ta: 2 dots above  |  ث Tha: 3 dots above")
        Spacer(modifier = Modifier.height(8.dp))

        HEBBodyText("ج  ح  خ — same hooked body")
        HEBBodyText("   ج Jim: dot inside  |  ح Ha: plain  |  خ Kha: dot above")
        Spacer(modifier = Modifier.height(8.dp))

        HEBBodyText("د  ذ — same angled stroke")
        HEBBodyText("   د Dal: plain  |  ذ Dhal: dot above")
        Spacer(modifier = Modifier.height(8.dp))

        HEBBodyText("ر  ز — same curved downstroke")
        HEBBodyText("   ر Ra: plain  |  ز Zain: dot above")
        Spacer(modifier = Modifier.height(8.dp))

        HEBBodyText("س  ش — same three-humped wave")
        HEBBodyText("   س Sin: plain  |  ش Shin: 3 dots above")
        Spacer(modifier = Modifier.height(8.dp))

        HEBBodyText("ص  ض — same looped body")
        HEBBodyText("   ص Sad: plain  |  ض Dad: dot above")
        Spacer(modifier = Modifier.height(8.dp))

        HEBBodyText("ط  ظ — same tall loop with vertical stroke")
        HEBBodyText("   ط Ta: plain  |  ظ Dha: dot above")
        Spacer(modifier = Modifier.height(8.dp))

        HEBBodyText("ع  غ — same eye/hook shape")
        HEBBodyText("   ع Ain: plain  |  غ Ghain: dot above")
        HEBBodyText("   (Note: ע in Hebrew looks very similar to Arabic ع — same letter, same sound!)")
        Spacer(modifier = Modifier.height(8.dp))

        HEBBodyText("ف  ق — similar circle head, different dots")
        HEBBodyText("   ف Fa: 1 dot above  |  ق Qaf: 2 dots above (also a deeper cup shape)")
        Spacer(modifier = Modifier.height(8.dp))

        HEBBodyText("ه  ة — Ha and Tah Marbuta (feminine marker)")
        HEBBodyText("   ه Ha: plain  |  ة Tah Marbuta: 2 dots above — marks feminine nouns")

        HEBSectionHeader("Letters That Match Hebrew")
        HEBBodyText("These letters have near-identical Hebrew counterparts in both sound and concept. Hebrew speakers should find them immediately natural:")
        Spacer(modifier = Modifier.height(8.dp))

        HEBBodyText("ع (Ain)  ↔  ע (Ayin)")
        HEBBodyText("   Identical sound: the deep voiced pharyngeal consonant. Both shapes share the same visual ancestor. Hebrew speakers already know this sound perfectly.")
        Spacer(modifier = Modifier.height(6.dp))

        HEBBodyText("ح (Ha)  ↔  ח (Chet)")
        HEBBodyText("   Identical sound: the breathy, throaty h. In Arabic it is written as an open arch; in Hebrew it is closed. Same phoneme.")
        Spacer(modifier = Modifier.height(6.dp))

        HEBBodyText("ا (Alef)  ↔  א (Aleph)")
        HEBBodyText("   Both serve as vowel carriers and represent the glottal stop. In Palestinian Arabic, ق also merges with the glottal stop — the same sound as ء and א.")
        Spacer(modifier = Modifier.height(6.dp))

        HEBBodyText("ر (Ra)  ↔  ר (Resh)")
        HEBBodyText("   Same r sound. Both are non-connectors (they don't connect to the left in a word). The Arabic ر curves further down.")
        Spacer(modifier = Modifier.height(6.dp))

        HEBBodyText("ل (Lam)  ↔  ל (Lamed)")
        HEBBodyText("   Identical l sound. The Arabic ل has the same tall upright body as the Hebrew ל.")
        Spacer(modifier = Modifier.height(6.dp))

        HEBBodyText("כ (Kaf)  ↔  ك (Kaf)")
        HEBBodyText("   Same letter name, same k sound. The Arabic ك is an open cup shape, visually similar to the Hebrew כ.")
        Spacer(modifier = Modifier.height(6.dp))

        HEBBodyText("ד (Dalet)  ↔  د (Dal)")
        HEBBodyText("   Identical d sound. Both are non-connectors. The Arabic form is a shorter, more angular version of the Hebrew ד.")
        Spacer(modifier = Modifier.height(6.dp))

        HEBBodyText("מ (Mem)  ↔  م (Mim)")
        HEBBodyText("   Same m sound. Arabic م has a small loop body that sits on the line.")
        Spacer(modifier = Modifier.height(6.dp))

        HEBBodyText("נ (Nun)  ↔  ن (Nun)")
        HEBBodyText("   Same n sound. The Arabic ن in its isolated form is a small cup with a dot above.")
        Spacer(modifier = Modifier.height(6.dp))

        HEBBodyText("ה (He)  ↔  ه (Ha)")
        HEBBodyText("   Same h sound. The Arabic ه can look like a small knot or 3-loop shape in connected forms.")
        Spacer(modifier = Modifier.height(6.dp))

        HEBBodyText("ו (Vav)  ↔  و (Waw)")
        HEBBodyText("   Arabic و = w sound; Hebrew ו = v sound (historically both were w). Same non-connector shape — a small hook hanging down.")
        Spacer(modifier = Modifier.height(6.dp))

        HEBBodyText("י (Yod)  ↔  ي (Ya)")
        HEBBodyText("   Same y sound. The smallest letter in both alphabets. Arabic ي in isolated form hangs below the line with 2 dots.")
        Spacer(modifier = Modifier.height(6.dp))

        HEBBodyText("ז (Zayin)  ↔  ز (Zain)")
        HEBBodyText("   Same z sound. Both are non-connectors. Arabic ز is like ر with a dot above.")
        Spacer(modifier = Modifier.height(6.dp))

        HEBBodyText("ש (Shin)  ↔  ش (Shin)")
        HEBBodyText("   Same sh sound. Arabic ش is like س with 3 dots above.")
        Spacer(modifier = Modifier.height(6.dp))

        HEBBodyText("ב (Bet)  ↔  ب (Ba)")
        HEBBodyText("   Same b sound. Arabic ب is the base shape of the ب/ت/ث family — a flat cup.")
        Spacer(modifier = Modifier.height(6.dp))

        HEBBodyText("ת (Tav)  ↔  ت (Ta)")
        HEBBodyText("   Same t sound. Arabic ت is ب with 2 dots above.")
        Spacer(modifier = Modifier.height(6.dp))

        HEBBodyText("פ (Pe/Feh)  ↔  ف (Fa)")
        HEBBodyText("   Same f sound. Arabic ف = Hebrew פ without dagesh (the soft feh). One dot above a circle head.")
        Spacer(modifier = Modifier.height(6.dp))

        HEBBodyText("ק (Qof)  ↔  ق (Qaf)")
        HEBBodyText("   Same letter name, originally same q sound. In urban Palestinian Arabic, ق has shifted to a glottal stop — the same as ء and א. Two dots above a deep cup.")

        HEBSectionHeader("New Sounds for Hebrew Speakers")
        HEBBodyText("These are the letters/sounds that exist in Arabic but not in Hebrew. Most are manageable — and some simplify in Palestinian spoken Arabic:")
        Spacer(modifier = Modifier.height(8.dp))

        HEBBodyText("ث (Tha) — \"th\" as in English \"think\" / \"thin\"")
        HEBBodyText("   No Hebrew letter for this sound. In Palestinian spoken Arabic it simplifies to a plain 't' — so you may rarely need to produce it at all.")
        Spacer(modifier = Modifier.height(6.dp))

        HEBBodyText("ج (Jim) — \"j\" as in English \"jam\" or \"jungle\"")
        HEBBodyText("   Hebrew has no j sound. Some Israeli Hebrew speakers write ג' for a j in foreign words, but it doesn't exist natively. This is a genuinely new sound to practice.")
        Spacer(modifier = Modifier.height(6.dp))

        HEBBodyText("ذ (Dhal) — \"dh\" as in English \"the\", \"this\", \"that\"")
        HEBBodyText("   No Hebrew equivalent. In Palestinian spoken Arabic it simplifies to a plain 'd'. Easy to manage in practice.")
        Spacer(modifier = Modifier.height(6.dp))

        HEBBodyText("ص  ض  ط  ظ — The Emphatic (Pharyngealized) Consonants")
        HEBBodyText("   These are the hardest sounds for any non-native speaker. Each is a regular consonant (s, d, t, dh) produced while pulling the back of the tongue toward the throat — creating a heavier, darker quality.")
        Spacer(modifier = Modifier.height(4.dp))
        HEBBodyText("   ص Sad — emphatic s (heavier than Hebrew ס)")
        HEBBodyText("   ض Dad — emphatic d (no Hebrew counterpart)")
        HEBBodyText("   ط Ta — emphatic t (Hebrew ט/Tet is the closest — both are historically the same sound)")
        HEBBodyText("   ظ Dha — in Palestinian Arabic merges with ض, pronounced as emphatic d")
        Spacer(modifier = Modifier.height(4.dp))
        HEBBodyText("   Note: Hebrew ט (Tet) and Arabic ط (Ta) are historically the same emphatic t. Hebrew speakers already have that muscle memory — ط will feel natural.")
        Spacer(modifier = Modifier.height(6.dp))

        HEBBodyText("غ (Ghain) — a gargled/uvular consonant")
        HEBBodyText("   Produced at the back of the throat, like a gurgling or gargling r. French speakers know this as their uvular r. No Hebrew equivalent. In Palestinian Arabic it often softens, especially in fast speech.")
        Spacer(modifier = Modifier.height(8.dp))
        HEBBodyText("Good news: Hebrew speakers master ع (Ain) and ח (Chet) without effort — these are the two sounds that trip up English speakers the most. You are already ahead.")

        HEBSectionHeader("The 4 Positional Forms — For Hebrew Readers")
        HEBBodyText("In Hebrew, you have sofit (final) forms for 5 letters only: כ → ך  |  מ → ם  |  נ → ן  |  פ → ף  |  צ → ץ")
        Spacer(modifier = Modifier.height(8.dp))
        HEBBodyText("In Arabic, almost every letter has 4 forms. The shape changes because Arabic is fully cursive — every letter (except non-connectors) joins the next letter on both sides.")
        Spacer(modifier = Modifier.height(8.dp))
        HEBBodyText("The 4 forms:")
        HEBBodyText("• Isolated — the letter stands alone, not connected to anything")
        HEBBodyText("• Initial — at the start of a word, connected to the letter after it")
        HEBBodyText("• Medial — in the middle, connected on both left and right")
        HEBBodyText("• Final — at the end of a word, connected only to the letter before it")
        Spacer(modifier = Modifier.height(8.dp))
        HEBBodyText("Example — ب (Ba) in context:")
        HEBBodyText("   Isolated: ب")
        HEBBodyText("   Initial (in بيت 'beit', house): بـ")
        HEBBodyText("   Medial (in كتب 'ktab', he wrote): ـبـ")
        HEBBodyText("   Final (in حبّ 'hubb', love): ـب")
        Spacer(modifier = Modifier.height(8.dp))
        HEBBodyText("The 6 Non-Connectors: ا  د  ذ  ر  ز  و")
        HEBBodyText("These 6 letters never connect to the letter that follows them — only to the letter before. Their isolated and initial forms are identical, and their medial and final forms are identical.")
        Spacer(modifier = Modifier.height(4.dp))
        HEBBodyText("This means a non-connector always breaks the chain: the letter after it starts a new connected group, even in the middle of a word. Hebrew readers can think of it like a letter that always forces a gap to its left.")
        Spacer(modifier = Modifier.height(4.dp))
        HEBBodyText("Example — the word وَلَد (walad = boy): و is a non-connector, so ل connects only to د, not back to و.")

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun HEBTableHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFD0D0D0))
            .padding(vertical = 4.dp, horizontal = 2.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("#", modifier = Modifier.weight(0.4f), style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold)
        Text("Letter", modifier = Modifier.weight(0.6f), style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold)
        Text("Name", modifier = Modifier.weight(1.2f), style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold)
        Text("Hebrew", modifier = Modifier.weight(1.0f), style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold)
        Text("End · Mid · Start · Isolated", modifier = Modifier.weight(2.8f), style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold)
    }
}

@Composable
private fun HEBLetterRow(num: String, arabic: String, name: String, heb: String, forms: String, shaded: Boolean) {
    val bg = if (shaded) Color(0xFFF0F0F0) else Color.Transparent
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(bg)
            .padding(vertical = 4.dp, horizontal = 2.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(num, modifier = Modifier.weight(0.4f), style = MaterialTheme.typography.bodySmall)
        Text(arabic, modifier = Modifier.weight(0.6f), style = MaterialTheme.typography.bodyMedium)
        Text(name, modifier = Modifier.weight(1.2f), style = MaterialTheme.typography.bodySmall)
        Text(heb, modifier = Modifier.weight(1.0f), style = MaterialTheme.typography.bodySmall)
        Text(forms, modifier = Modifier.weight(2.8f), style = MaterialTheme.typography.bodySmall)
    }
}

@Composable
private fun HEBSectionHeader(text: String) {
    Spacer(modifier = Modifier.height(20.dp))
    Text(
        text = text,
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.fillMaxWidth()
    )
    Spacer(modifier = Modifier.height(8.dp))
    HorizontalDivider()
    Spacer(modifier = Modifier.height(8.dp))
}

@Composable
private fun HEBBodyText(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(vertical = 2.dp)
    )
}
