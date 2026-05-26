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
fun VowelsScreen() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        VowSectionHeader("Arabic Is an Abjad — No Vowel Letters")
        VowBodyText("Arabic, like Hebrew, is an abjad: all 28 letters are consonants. There are no dedicated vowel letters. Short vowel sounds are indicated by small diacritical marks called حَرَكَات (harakat, \"movements\") placed above or below letters.")
        Spacer(modifier = Modifier.height(8.dp))
        VowBodyText("In everyday writing — newspapers, books, street signs, social media — these marks are entirely omitted. Fluent readers infer the vowels from context and familiarity with the language.")
        Spacer(modifier = Modifier.height(8.dp))
        VowBodyText("Hebrew speakers already know this system: it is identical in principle to Hebrew nikud (נקוד) being absent in everyday Hebrew text. You learned to read without vowel marks — Arabic works the same way.")

        VowSectionHeader("The Three Short Vowels")
        VowBodyText("Three marks cover the core short vowel sounds:")
        Spacer(modifier = Modifier.height(8.dp))
        VowBodyText("Fatha (فتحة) — written: ـَ")
        VowBodyText("Sound: short \"a\" (like \"a\" in \"cat\"). The mark sits above the letter.")
        VowBodyText("Example: بَ = \"ba\"")
        VowBodyText("Hebrew parallel: patah (פַּתַח) — the פַ mark")
        Spacer(modifier = Modifier.height(8.dp))
        VowBodyText("Kasra (كسرة) — written: ـِ")
        VowBodyText("Sound: short \"i\" (like \"i\" in \"bit\"). The mark sits below the letter.")
        VowBodyText("Example: بِ = \"bi\"")
        VowBodyText("Hebrew parallel: hiriq (חִירִיק) — the dot under the letter")
        Spacer(modifier = Modifier.height(8.dp))
        VowBodyText("Damma (ضمة) — written: ـُ")
        VowBodyText("Sound: short \"u\" (like \"oo\" in \"book\"). The mark sits above the letter.")
        VowBodyText("Example: بُ = \"bu\"")
        VowBodyText("Hebrew parallel: kibbutz / shuruk (קֻ / קוּ)")
        Spacer(modifier = Modifier.height(8.dp))
        VowBodyText("These three marks, combined with the 28 consonants, produce the full range of basic Arabic syllables.")

        VowSectionHeader("No Vowel: Sukun")
        VowBodyText("Sukun (سكون, \"silence\") — written: ـْ")
        VowBodyText("Placed on a consonant to show it has NO following vowel — the consonant is \"bare.\" This occurs at the end of closed syllables or in consonant clusters.")
        Spacer(modifier = Modifier.height(8.dp))
        VowBodyText("Example: بِنْت (bint = girl) — the nun ن carries a sukun, so it is pronounced \"n\" with no vowel after it, creating the cluster \"nt\".")
        Spacer(modifier = Modifier.height(8.dp))
        VowBodyText("Hebrew parallel: shva nach (שְׁוָא נָח) — the two dots under a letter indicating a silent/reduced vowel.")

        VowSectionHeader("Doubled Consonant: Shadda")
        VowBodyText("Shadda (شدة, \"intensity\") — written: ـّ")
        VowBodyText("Placed on a consonant to geminate it — the consonant is written once but pronounced twice (doubled). You hold the consonant slightly longer.")
        Spacer(modifier = Modifier.height(8.dp))
        VowBodyText("Example: مَرَّ (marra = he passed) — the ra ر with shadda is pronounced \"rr\".")
        Spacer(modifier = Modifier.height(4.dp))
        VowBodyText("Shadda is often stacked with a vowel mark at the same time: ـَّ (shadda + fatha), ـِّ (shadda + kasra), ـُّ (shadda + damma).")
        Spacer(modifier = Modifier.height(8.dp))
        VowBodyText("Example: مُدَرِّس (mudarris = teacher) — the ra carries shadda + kasra: \"rri\".")
        Spacer(modifier = Modifier.height(8.dp))
        VowBodyText("Hebrew parallel: dagesh chazak (דָּגֵשׁ חָזָק) — the dot inside a letter that doubles it, as in כַּלָּה (kalla = bride) vs כָּלָה (kala = she finished).")

        VowSectionHeader("Tanwin — Adding an \"n\" Sound")
        VowBodyText("Tanwin (تنوين, \"nunation\") is formed by doubling a vowel mark. This adds an \"n\" sound at the end of the word. Your instinct was right: the \"i\" (or \"ee\") sound gains an \"n\" suffix and becomes \"in\" (or \"een\").")
        Spacer(modifier = Modifier.height(8.dp))
        VowBodyText("Fathatan — written: ـً (two fathas)")
        VowBodyText("Sound: \"an\" at the end of the word.")
        Spacer(modifier = Modifier.height(4.dp))
        VowBodyText("Kasratan — written: ـٍ (two kasras)")
        VowBodyText("Sound: \"in\" at the end of the word — so \"i\" (or \"ee\") becomes \"in\" (or \"een\"). ✓")
        Spacer(modifier = Modifier.height(4.dp))
        VowBodyText("Dammatan — written: ـٌ (two dammas)")
        VowBodyText("Sound: \"un\" at the end of the word.")
        Spacer(modifier = Modifier.height(8.dp))
        VowBodyText("Full example: كِتَابٌ (kitaabun = a book, MSA indefinite). The ـٌ at the end of باب gives it the \"un\" ending.")
        Spacer(modifier = Modifier.height(8.dp))
        VowBodyText("Important: tanwin is primarily an MSA and formal Arabic feature. In Levantine spoken dialect (the dialect this app focuses on), tanwin is rarely if ever used — you will hear it mainly in formal speeches, news broadcasts, Quranic recitation, and set phrases. Do not expect to use it in everyday conversation.")

        VowSectionHeader("Long Vowels: Letters Acting as Vowels")
        VowBodyText("Three consonant letters also function as long vowel markers when they follow the corresponding short vowel:")
        Spacer(modifier = Modifier.height(8.dp))
        VowBodyText("ا (alef) after a fatha = long \"aa\"")
        VowBodyText("Example: كِتَاب (kitaab = book) — the ا stretches the \"a\" into a long \"aa\".")
        Spacer(modifier = Modifier.height(4.dp))
        VowBodyText("و (waaw) after a damma = long \"uu\"")
        VowBodyText("Example: نُور (nuur = light) — the و stretches the \"u\" into a long \"uu\".")
        Spacer(modifier = Modifier.height(4.dp))
        VowBodyText("ي (yaa) after a kasra = long \"ii\"")
        VowBodyText("Example: كَبِير (kabiir = big) — the ي stretches the \"i\" into a long \"ii\".")
        Spacer(modifier = Modifier.height(8.dp))
        VowBodyText("These are actual letters, not diacritics — they appear even in unvocalized text. So even when you drop all the harakat marks, the long vowels remain visible as letters.")

        VowSectionHeader("In Practice: Marks Are Omitted")
        VowBodyText("In real-world Arabic text, harakat are almost never written. Everyday Arabic looks like this:")
        Spacer(modifier = Modifier.height(8.dp))
        VowBodyText("كتب — this could be:")
        VowBodyText("• كَتَبَ (kataba) — he wrote")
        VowBodyText("• كُتِبَ (kutiba) — it was written")
        VowBodyText("• كُتُب (kutub) — books")
        Spacer(modifier = Modifier.height(8.dp))
        VowBodyText("Context and knowledge of the language resolve the ambiguity. This is exactly how Hebrew works: ספר could be sefer (book), sapar (he told), or sapar (barber) — context decides.")
        Spacer(modifier = Modifier.height(8.dp))
        VowBodyText("Vowel marks do appear in:")
        VowBodyText("• The Quran (fully vocalized, for precise recitation)")
        VowBodyText("• Children's books and early reading materials")
        VowBodyText("• Language learning texts (like this app's examples)")
        VowBodyText("• Poetry, some religious texts")
        Spacer(modifier = Modifier.height(4.dp))
        VowBodyText("Learners typically start with fully marked text, then gradually read unvocalized text as vocabulary grows — the same path Hebrew learners take.")

        VowSectionHeader("Levantine Dialect Note")
        VowBodyText("The harakat described above follow MSA (Modern Standard Arabic) conventions. In the Palestinian and Levantine dialect this app focuses on, vowels behave somewhat differently in natural speech:")
        Spacer(modifier = Modifier.height(8.dp))
        VowBodyText("• Short vowels in unstressed syllables are often reduced or dropped entirely. For example, MSA كَتَبَ (kataba) becomes something closer to \"katab\" in Levantine.")
        VowBodyText("• The kasra (\"i\" sound) sometimes shifts toward \"e\" or lengthens to sound like \"ee\" in certain positions.")
        VowBodyText("• Classical word endings (like tanwin case endings) are completely absent in spoken Levantine.")
        Spacer(modifier = Modifier.height(8.dp))
        VowBodyText("In practice: you will rarely need to write harakat. Understanding them helps you read learning materials, religious texts, and children's books. In conversation and in reading everyday Arabic text, you work without them — just as with Hebrew.")

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun VowSectionHeader(text: String) {
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
private fun VowBodyText(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(vertical = 2.dp)
    )
}
