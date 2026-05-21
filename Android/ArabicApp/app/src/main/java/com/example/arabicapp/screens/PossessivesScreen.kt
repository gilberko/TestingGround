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
fun PossessivesScreen() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        POSSectionHeader("Possessive Suffixes — ضمائر الملكية")
        POSBodyText("To say \"my house\", \"your book\", \"his car\" in Palestinian Arabic, you attach a suffix directly to the end of the noun. There is no separate word — the suffix IS the possessive.")
        Spacer(modifier = Modifier.height(12.dp))
        POSBodyText("Using بيت (beit = house) as the example:")
        Spacer(modifier = Modifier.height(8.dp))
        POSBodyText("بيتي (beiti) — my house")
        POSBodyText("بيتك (beitak) — your house (masculine)")
        POSBodyText("بيتك (beitik) — your house (feminine)")
        POSBodyText("بيته (beito) — his house")
        POSBodyText("بيتها (beitha) — her house")
        POSBodyText("بيتنا (beitna) — our house")
        POSBodyText("بيتكم (beitkum) — your house (plural)")
        POSBodyText("بيتهم (beithum) — their house")

        POSSectionHeader("The Suffixes at a Glance")
        POSBodyText("-i → my")
        POSBodyText("-ak → your (masculine singular)")
        POSBodyText("-ik → your (feminine singular)")
        POSBodyText("-o / -u → his")
        POSBodyText("-ha → her")
        POSBodyText("-na → our")
        POSBodyText("-kum → your (plural)")
        POSBodyText("-hum → their")

        POSSectionHeader("Second Example — كتاب (ktaab = book)")
        POSBodyText("كتابي (ktaabi) — my book")
        POSBodyText("كتابك (ktaabak) — your book (m)")
        POSBodyText("كتابك (ktaabik) — your book (f)")
        POSBodyText("كتابه (ktaabo) — his book")
        POSBodyText("كتابها (ktaabha) — her book")
        POSBodyText("كتابنا (ktaabna) — our book")
        POSBodyText("كتابكم (ktaabkum) — your book (pl)")
        POSBodyText("كتابهم (ktaabhum) — their book")

        POSSectionHeader("Notes on Pronunciation")
        POSBodyText("• -ak (m) and -ik (f) look identical in Arabic script — بيتك. The vowel (a vs i) and context make the gender clear.")
        POSBodyText("• -o is more common in Palestinian urban speech; -u is also heard, especially in slower or more formal speech.")
        POSBodyText("• When the noun ends in a vowel, the suffix -i becomes -yyi: أبو (abu = father) → أبوي (abuuyi = my father).")
        POSBodyText("• The feminine ending ة (tah marbuta) becomes a regular ت before a suffix: شقة (sha''a = apartment) → شقتي (sha''iti = my apartment).")

        POSSectionHeader("Standalone Possession — إلي، إلك، إله...")
        POSBodyText("The preposition ل (li = for/to) + a suffix creates a standalone possessive meaning \"mine\", \"yours\", \"his\", etc. Use this when you want to say something belongs to someone — separately from a noun.")
        Spacer(modifier = Modifier.height(8.dp))
        POSBodyText("إلي (ili) — mine / for me")
        POSBodyText("إلك (ilak) — yours (masculine) / for you (m)")
        POSBodyText("إلك (ilik) — yours (feminine) / for you (f)")
        POSBodyText("إله (ilu) — his / for him")
        POSBodyText("إلها (ilha) — hers / for her")
        POSBodyText("إلنا (ilna) — ours / for us")
        POSBodyText("إلكم (ilkum) — yours (plural)")
        POSBodyText("إلهم (ilhum) — theirs")
        Spacer(modifier = Modifier.height(8.dp))
        POSBodyText("Examples:")
        POSBodyText("• هاد إلي — haad ili — this is mine")
        POSBodyText("• هاد إله — haad ilu — this is his")
        POSBodyText("• الكتاب إلها — il-ktaab ilha — the book is hers")
        POSBodyText("• السيارة إلنا — is-sayyaara ilna — the car is ours")
        POSBodyText("• مين إله هاد؟ — min ilu haad? — whose is this? (lit. \"for whom is this?\")")

        POSSectionHeader("The Two Structures Compared")
        POSBodyText("بيتي (beiti) = my house")
        POSBodyText("  → suffix on the noun; describes the house as belonging to me")
        Spacer(modifier = Modifier.height(8.dp))
        POSBodyText("البيت إلي (il-beit ili) = the house is mine")
        POSBodyText("  → standalone إلـ form; states that ownership belongs to me")
        Spacer(modifier = Modifier.height(8.dp))
        POSBodyText("Both are grammatically correct. The إلـ form tends to be more emphatic — it highlights the ownership itself.")
        POSBodyText("• هاد بيتي — haad beiti — this is my house")
        POSBodyText("• البيت إلي، مش إله — il-beit ili, mish ilu — the house is MINE, not his")

        POSSectionHeader("More Examples in Context")
        POSBodyText("• وين مفتاحك؟ — wen muftaḥak? — where is your key? (m)")
        POSBodyText("• شفت سيارتها — shift sayyaartha — I saw her car")
        POSBodyText("• أصحابنا جو — aṣḥaabna ju — our friends came")
        POSBodyText("• هاد إله — haad ilu — this is his")
        POSBodyText("• الكلب إلهم — il-kalb ilhum — the dog is theirs")
        POSBodyText("• بيتنا كبير — beitna kbir — our house is big")

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun POSSectionHeader(text: String) {
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
private fun POSBodyText(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(vertical = 2.dp)
    )
}
