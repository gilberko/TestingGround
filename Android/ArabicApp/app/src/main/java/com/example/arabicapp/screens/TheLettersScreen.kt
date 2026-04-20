package com.example.arabicapp.screens

import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun TheLettersScreen() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        SectionHeader("The Arabic Alphabet")
        BodyText("Arabic has 28 letters, all consonants. Short vowels are usually not written — they are implied by context. The alphabet is written right to left.")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("ا — Alef (ā / glottal stop)")
        BodyText("ب — Ba (b)")
        BodyText("ت — Ta (t)")
        BodyText("ث — Tha (th → t or s in colloquial)")
        BodyText("ج — Jim (j)")
        BodyText("ح — Ha (ḥ — breathy, from throat)")
        BodyText("خ — Kha (kh — like Scottish 'loch')")
        BodyText("د — Dal (d)")
        BodyText("ذ — Dhal (dh → d or z in colloquial)")
        BodyText("ر — Ra (r — rolled)")
        BodyText("ز — Zain (z)")
        BodyText("س — Sin (s)")
        BodyText("ش — Shin (sh)")
        BodyText("ص — Sad (ṣ — emphatic s)")
        BodyText("ض — Dad (ḍ — emphatic d)")
        BodyText("ط — Ta (ṭ — emphatic t)")
        BodyText("ظ — Dha (ẓ → d or z in colloquial)")
        BodyText("ع — Ain (ʕ — voiced pharyngeal, a unique Arabic sound)")
        BodyText("غ — Ghain (gh — like a gargled r)")
        BodyText("ف — Fa (f)")
        BodyText("ق — Qaf (q → glottal stop ʔ in urban Palestinian)")
        BodyText("ك — Kaf (k)")
        BodyText("ل — Lam (l)")
        BodyText("م — Mim (m)")
        BodyText("ن — Nun (n)")
        BodyText("ه — Ha (h)")
        BodyText("و — Waw (w)")
        BodyText("ي — Ya (y)")

        SectionHeader("Letter Forms")
        BodyText("Most Arabic letters take four different written forms depending on their position in a word:")
        Spacer(modifier = Modifier.height(4.dp))
        BodyText("• Isolated — the letter stands alone")
        BodyText("• Initial — at the beginning of a word, connected to the letter after it")
        BodyText("• Medial — in the middle, connected on both sides")
        BodyText("• Final — at the end, connected only to the letter before it")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Example with ب (Ba):\nIsolated: ب\nInitial: بـ\nMedial: ـبـ\nFinal: ـب")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Six letters are non-connectors — they only connect to the right (the preceding letter) and never to the letter that follows. When they appear mid-word, the next letter starts fresh in initial or isolated form.")
        Spacer(modifier = Modifier.height(4.dp))
        BodyText("Non-connectors: ا (Alef) — د (Dal) — ذ (Dhal) — ر (Ra) — ز (Zain) — و (Waw)")

        SectionHeader("Sun & Moon Letters — What Are They?")
        BodyText("Arabic has 28 letters. When the definite article ال (\"the\") is placed before a word, the pronunciation of its 'l' depends on the first letter of that word. This divides the 28 letters into two groups — sun letters and moon letters.")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("The names come from two Arabic words:")
        BodyText("• شمس (shams) = sun — starts with ش, a sun letter → so \"shams\" itself triggers the rule")
        BodyText("• قمر (qamar) = moon — starts with ق, a moon letter → so \"qamar\" does not trigger the rule")
        BodyText("The categories literally name themselves.")

        SectionHeader("Sun Letters (الحروف الشمسية) — How to Pronounce")
        BodyText("There are 14 sun letters: ت ث د ذ ر ز س ش ص ض ط ظ ن ل")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Rule: When ال precedes a sun letter, the 'l' of ال disappears and is replaced by the sun letter itself — which is then pronounced with a slight doubling (gemination).")
        Spacer(modifier = Modifier.height(4.dp))
        BodyText("In Palestinian colloquial the article is il- (not al-), but the same assimilation applies.")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Examples — one for each sun letter:")
        BodyText("ت — التفاح (the apple): il-tufaḥ → it-tufaḥ")
        BodyText("ث — الثلج (the snow): il-talj → it-talj (ث→t in Palestinian)")
        BodyText("د — الدار (the home): il-dar → id-dar")
        BodyText("ذ — الذهب (the gold): il-dahab → id-dahab (ذ→d in Palestinian)")
        BodyText("ر — الرجل (the man): il-rajjal → ir-rajjal")
        BodyText("ز — الزيت (the oil): il-zeit → iz-zeit")
        BodyText("س — السوق (the market): il-su' → is-su'")
        BodyText("ش — الشمس (the sun): il-shams → ish-shams")
        BodyText("ص — الصحن (the plate): il-ṣaḥan → iṣ-ṣaḥan")
        BodyText("ض — الضيف (the guest): il-ḍeif → iḍ-ḍeif")
        BodyText("ط — الطريق (the road): il-ṭariq → iṭ-ṭariq")
        BodyText("ظ — الظل (the shade): il-dill → id-dill (ظ→d in Palestinian)")
        BodyText("ن — النور (the light): il-nur → in-nur")
        BodyText("ل — الليل (the night): il-leil → il-leil (l already, merges smoothly)")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("How to hear it: you do not say \"il\" then the word — the l slides into the first consonant of the word. il-shams sounds like \"ish-shams\", not \"il shams\".")

        SectionHeader("Moon Letters (الحروف القمرية) — How to Pronounce")
        BodyText("There are 14 moon letters: ا ب ج ح خ ع غ ف ق ك م ه و ي")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Rule: When ال precedes a moon letter, the 'l' is kept and pronounced normally. No assimilation occurs.")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Examples:")
        BodyText("ا — الأرض (the land): il-'ard → il-'ard (no change)")
        BodyText("ب — البيت (the house): il-beit → il-beit")
        BodyText("ج — الجبل (the mountain): il-jabal → il-jabal")
        BodyText("ح — الحليب (the milk): il-ḥalib → il-ḥalib")
        BodyText("خ — الخبيز (the bread): il-khubiz → il-khubiz")
        BodyText("ع — العين (the eye): il-'ein → il-'ein")
        BodyText("غ — الغرفة (the room): il-ghurfe → il-ghurfe")
        BodyText("ف — الفستان (the dress): il-fustan → il-fustan")
        BodyText("ق — القمر (the moon): il-'amar → il-'amar (qaf→glottal, but l stays)")
        BodyText("ك — الكتاب (the book): il-ktab → il-ktab")
        BodyText("م — المدرسة (the school): il-madrasa → il-madrasa")
        BodyText("ه — الهواء (the air): il-hawa → il-hawa")
        BodyText("و — الولد (the boy): il-walad → il-walad")
        BodyText("ي — اليوم (the day): il-yom → il-yom")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Summary: with moon letters, what you write is what you say. With sun letters, the 'l' morphs into the following consonant.")

        SectionHeader("Palestinian/Levantine Pronunciations")
        BodyText("Palestinian Arabic as spoken in cities like Jerusalem, Ramallah, Haifa, and Nazareth has specific pronunciation patterns that differ from MSA and from other dialects.")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("ق (Qaf) → Glottal Stop (ʔ)")
        BodyText("In urban Palestinian Arabic, ق is pronounced as a glottal stop — the same catch in the throat as the pause in the English expression \"uh-oh\".")
        Spacer(modifier = Modifier.height(4.dp))
        BodyText("MSA: qalb (قلب, heart) → Palestinian: 'alb\nMSA: qamar (قمر, moon) → Palestinian: 'amar")
        Spacer(modifier = Modifier.height(4.dp))
        BodyText("Note: Some rural and Bedouin communities in the region retain the original 'q' sound. The glottal stop is the urban norm.")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("ج (Jim) → 'j' as in English \"jam\"")
        BodyText("Palestinian ج is a clean 'j' sound. This distinguishes it from Egyptian Arabic, which pronounces ج as a hard 'g' (like \"go\").")
        Spacer(modifier = Modifier.height(4.dp))
        BodyText("Palestinian: jdeed (جديد, new)\nEgyptian equivalent: gdeed")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("ث (Tha) → 't' or 's'")
        BodyText("The MSA 'th' sound (as in English \"thin\") simplifies in colloquial Palestinian speech.")
        Spacer(modifier = Modifier.height(4.dp))
        BodyText("MSA: thalatha (ثلاثة, three) → Palestinian: talate\nMSA: thani (ثاني, second) → Palestinian: tani")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("ذ (Dhal) and ظ (Dha) → 'd' or 'z'")
        BodyText("Both ذ (voiced 'th' as in \"the\") and ظ (emphatic dh) simplify in colloquial speech.")
        Spacer(modifier = Modifier.height(4.dp))
        BodyText("MSA: hadha (هذا, this) → Palestinian: haada or hada")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("These simplifications are normal features of the spoken dialect — not errors. Native speakers learn the full MSA forms separately through schooling.")

        SectionHeader("Tah Marbuta (ة)")
        BodyText("Tah Marbuta is the letter ة — it looks like ه (Ha) with two dots above it. It marks the feminine gender and appears at the end of many nouns and adjectives.")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("IN ISOLATION (word said alone or at end of phrase):")
        BodyText("Tah Marbuta is pronounced as a plain 'a' sound.")
        Spacer(modifier = Modifier.height(4.dp))
        BodyText("مدرسة (school) → madrasa")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("IN CONSTRUCT STATE (idafa — when one noun is possessed by another) or before a vowel:")
        BodyText("Tah Marbuta is pronounced as 't'. This is common and natural in everyday Palestinian speech.")
        Spacer(modifier = Modifier.height(4.dp))
        BodyText("مدرسة المدينة (the city's school) → madraset il-madina")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("More examples:")
        BodyText("• سيارة (car) → sayyara (alone) / sayyaret abu-Ahmad (Abu Ahmad's car)")
        BodyText("• مدينة (city) → madina (alone) / madiinet il-quds (the city of Jerusalem)")
        BodyText("• لغة (language) → lugha (alone) / lughet il-'arab (the Arabic language)")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Identifying Tah Marbuta is important because it signals feminine nouns — adjectives agreeing with a feminine noun will also carry Tah Marbuta.")

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun SectionHeader(text: String) {
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
private fun BodyText(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(vertical = 2.dp)
    )
}
