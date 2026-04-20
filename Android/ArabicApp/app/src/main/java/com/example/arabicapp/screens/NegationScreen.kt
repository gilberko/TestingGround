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
fun NegationScreen() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        NegSectionHeader("Saying No")
        NegBodyText("The word for \"no\" in Palestinian Arabic is:")
        Spacer(modifier = Modifier.height(4.dp))
        NegBodyText("لا — la")
        Spacer(modifier = Modifier.height(8.dp))
        NegBodyText("Examples:")
        NegBodyText("• Do you want tea? — la, shukran (no, thank you)")
        NegBodyText("• Is this the market? — la, mish hawn (no, not here)")

        NegSectionHeader("Negating Nouns and Adjectives — مش (mish)")
        NegBodyText("To say something is NOT a noun or NOT an adjective, use مش (mish) before it.")
        Spacer(modifier = Modifier.height(4.dp))
        NegBodyText("Pattern: مش + noun/adjective")
        Spacer(modifier = Modifier.height(8.dp))
        NegBodyText("Examples:")
        NegBodyText("• مش كتاب — mish ktab — not a book")
        NegBodyText("• مش كبير — mish kbir — not big")
        NegBodyText("• أنا مش تعبان — ana mish ta'ban — I am not tired")
        NegBodyText("• هاد مش بيتنا — had mish betna — this is not our house")
        NegBodyText("• هي مش طالبة — hiyye mish talbe — she is not a student")
        Spacer(modifier = Modifier.height(8.dp))
        NegBodyText("مش (mish) can also appear at the end for emphasis:")
        NegBodyText("• هاد صح مش — had saḥ mish — this is correct, isn't it? (tag question)")

        NegSectionHeader("Negating Verbs — the ما...ش Circumfix")
        NegBodyText("In Palestinian Arabic, verbs are negated using a two-part circumfix: ما (ma) before the verb and ش (-sh) attached at the end.")
        Spacer(modifier = Modifier.height(4.dp))
        NegBodyText("Pattern: ما + verb + ش")
        Spacer(modifier = Modifier.height(8.dp))
        NegBodyText("Present tense examples:")
        NegBodyText("• بحكي (bḥki, I speak) → ما بحكيش (ma bḥkish) — I don't speak")
        NegBodyText("• بعرف (ba'ref, I know) → ما بعرفش (ma ba'refsh) — I don't know")
        NegBodyText("• بدّي (baddi, I want) → ما بدّيش (ma baddish) — I don't want")
        NegBodyText("• بياكل (biyakel, he eats) → ما بياكلش (ma biyakelsh) — he doesn't eat")
        NegBodyText("• بتفهم (btifham, you understand) → ما بتفهمش (ma btifhamsh) — you don't understand")
        Spacer(modifier = Modifier.height(8.dp))
        NegBodyText("Past tense examples:")
        NegBodyText("• رحت (ruḥt, I went) → ما رحتش (ma ruḥtish) — I didn't go")
        NegBodyText("• أكل (akal, he ate) → ما أكلش (ma akalsh) — he didn't eat")
        NegBodyText("• شفت (shift, I saw) → ما شفتش (ma shiftish) — I didn't see")
        NegBodyText("• جيت (jiit, you came) → ما جيتش (ma jiitish) — you didn't come")
        Spacer(modifier = Modifier.height(8.dp))
        NegBodyText("Note: ما alone (without ش) is also heard, especially in more formal or older speech, but the ما...ش circumfix is the standard colloquial form in Palestinian Arabic.")

        NegSectionHeader("Nothing, No One, Never")
        NegBodyText("NOTHING")
        NegBodyText("• ولا شي — wala shi — nothing (literally \"not a thing\")")
        NegBodyText("• ما شفت ولا شي — ma shift wala shi — I didn't see anything")
        Spacer(modifier = Modifier.height(8.dp))
        NegBodyText("NO ONE")
        NegBodyText("• محدّ — maḥad — no one / nobody")
        NegBodyText("• ولا حدا — wala ḥada — no one (emphatic)")
        NegBodyText("• محدّ هون — maḥad hawn — there's no one here")
        Spacer(modifier = Modifier.height(8.dp))
        NegBodyText("NEVER")
        NegBodyText("• أبداً — abadan — never / not at all")
        NegBodyText("• ما رحتش أبداً — ma ruḥtish abadan — I never went")
        NegBodyText("• مش أبداً — mish abadan — not at all")

        NegSectionHeader("Double Negation")
        NegBodyText("Palestinian Arabic (like many spoken dialects) uses double negation naturally — it is grammatically correct and does not cancel out.")
        Spacer(modifier = Modifier.height(4.dp))
        NegBodyText("• ما شفتش ولا حدا — ma shiftish wala ḥada — I didn't see anyone")
        NegBodyText("• ما عندي ولا شي — ma 'indi wala shi — I don't have anything")
        NegBodyText("• ما بحكيش أبداً — ma bḥkish abadan — I never speak / I don't speak at all")

        NegSectionHeader("Quick Reference")
        NegBodyText("لا (la) — No")
        NegBodyText("مش (mish) — Not (before nouns/adjectives)")
        NegBodyText("ما...ش (ma...sh) — Verb negation circumfix")
        NegBodyText("ولا شي (wala shi) — Nothing")
        NegBodyText("محدّ (maḥad) — No one")
        NegBodyText("أبداً (abadan) — Never")

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun NegSectionHeader(text: String) {
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
private fun NegBodyText(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(vertical = 2.dp)
    )
}
