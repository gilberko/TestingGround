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
fun PresentTenseScreen() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        PrTSectionHeader("How Present Tense Works — The b- Prefix")
        PrTBodyText("Palestinian Arabic marks the habitual / general present tense by adding a b- prefix to the imperfect (present) verb stem. This is a dialect feature not found in MSA (Modern Standard Arabic).")
        Spacer(modifier = Modifier.height(8.dp))
        PrTBodyText("Compare past vs. present:")
        PrTBodyText("• أكل (akal) — he ate  [past: bare root, no prefix]")
        PrTBodyText("• بياكل (byaakol) — he eats  [present: by- prefix added]")
        Spacer(modifier = Modifier.height(8.dp))
        PrTBodyText("• راح (ra7) — he went  [past]")
        PrTBodyText("• بيروح (byiru7) — he goes  [present]")
        Spacer(modifier = Modifier.height(8.dp))
        PrTBodyText("The b- prefix form changes by person and number:")
        PrTBodyText("• b- → I (أنا ana)")
        PrTBodyText("• bt- → you (m) (إنت inta), she (هي hiyye)")
        PrTBodyText("• bt-...-i → you (f)")
        PrTBodyText("• by- → he (هو huwwe)")
        PrTBodyText("• bn- → we (إحنا i7na)")
        PrTBodyText("• bt-...-u → you (pl, إنتو intu)")
        PrTBodyText("• by-...-u → they (هم/هنّ humme/hinne)")
        Spacer(modifier = Modifier.height(8.dp))
        PrTBodyText("Note: In Palestinian Arabic, هنّ (they f.) uses the same form as هم (they m.) in everyday speech.")

        PrTSectionHeader("Conjugation — اشتغل (to work) — model verb")
        PrTBodyText("أنا بشتغل — ana bishtaghil — I work")
        PrTBodyText("إنت بتشتغل — inta btishtaghil — you (m) work")
        PrTBodyText("إنتِ بتشتغلي — inti btishtaghli — you (f) work")
        PrTBodyText("هو بيشتغل — huwwe byishtaghil — he works")
        PrTBodyText("هي بتشتغل — hiyye btishtaghil — she works")
        PrTBodyText("إحنا بنشتغل — i7na bnishtaghil — we work")
        PrTBodyText("إنتو بتشتغلو — intu btishtaghlu — you (pl) work")
        PrTBodyText("هم بيشتغلو — humme byishtaghlu — they work")

        PrTSectionHeader("Second Example — أكل (to eat)")
        PrTBodyText("أنا باكل — ana baakol — I eat")
        PrTBodyText("إنت بتاكل — inta btaakol — you (m) eat")
        PrTBodyText("إنتِ بتاكلي — inti btaakli — you (f) eat")
        PrTBodyText("هو بياكل — huwwe byaakol — he eats")
        PrTBodyText("هي بتاكل — hiyye btaakol — she eats")
        PrTBodyText("إحنا بناكل — i7na bnaakol — we eat")
        PrTBodyText("إنتو بتاكلو — intu btaaklu — you (pl) eat")
        PrTBodyText("هم بياكلو — humme byaaklu — they eat")

        PrTSectionHeader("Third Example — حكى (to speak / talk) — final-y verb")
        PrTBodyText("Verbs ending in a long -i or -y sound behave slightly differently: you (f) does NOT add an extra -i because the verb already ends in that sound.")
        Spacer(modifier = Modifier.height(4.dp))
        PrTBodyText("أنا بحكي — ana ba7ki — I speak")
        PrTBodyText("إنت بتحكي — inta bta7ki — you (m) speak")
        PrTBodyText("إنتِ بتحكي — inti bta7ki — you (f) speak  ← same as masculine")
        PrTBodyText("هو بيحكي — huwwe bya7ki — he speaks")
        PrTBodyText("هي بتحكي — hiyye bta7ki — she speaks")
        PrTBodyText("إحنا بنحكي — i7na bna7ki — we speak")
        PrTBodyText("إنتو بتحكو — intu bta7ku — you (pl) speak")
        PrTBodyText("هم بيحكو — humme bya7ku — they speak")

        PrTSectionHeader("Negating the Present")
        PrTBodyText("Two options — same as other tenses:")
        Spacer(modifier = Modifier.height(4.dp))
        PrTBodyText("Option 1 — مش (mish) before the verb: [most common]")
        PrTBodyText("• مش باكل — mish baakol — I don't eat / I'm not eating")
        PrTBodyText("• مش بيشتغل — mish byishtaghil — he doesn't work")
        PrTBodyText("• مش بتحكي — mish bta7ki — she doesn't speak")
        Spacer(modifier = Modifier.height(8.dp))
        PrTBodyText("Option 2 — ما...ش (ma...sh) circumfix: [emphatic]")
        PrTBodyText("• ما باكلش — ma bakolsh — I don't eat (at all)")
        PrTBodyText("• ما بيشتغلش — ma bishtaghilsh — he doesn't work (at all)")

        PrTSectionHeader("Present Continuous — عم (3am) + verb")
        PrTBodyText("To express an action happening RIGHT NOW, place عم (3am) directly before the conjugated b- verb.")
        Spacer(modifier = Modifier.height(8.dp))
        PrTBodyText("Compare:")
        PrTBodyText("• بياكل (byaakol) — he eats [habitually, in general]")
        PrTBodyText("• عم بياكل (3am byaakol) — he is eating [right now, at this moment]")
        Spacer(modifier = Modifier.height(8.dp))
        PrTBodyText("Palestinian Arabic keeps the b- prefix alongside عم (3am). Lebanese Arabic often drops it (عم ياكل — 3am yaakol), but Palestinian speakers say عم بياكل.")

        PrTSectionHeader("Continuous Conjugation — اشتغل (to work)")
        PrTBodyText("أنا عم بشتغل — 3am bishtaghil — I am working")
        PrTBodyText("إنت عم بتشتغل — 3am btishtaghil — you (m) are working")
        PrTBodyText("إنتِ عم بتشتغلي — 3am btishtaghli — you (f) are working")
        PrTBodyText("هو عم بيشتغل — 3am byishtaghil — he is working")
        PrTBodyText("هي عم بتشتغل — 3am btishtaghil — she is working")
        PrTBodyText("إحنا عم بنشتغل — 3am bnishtaghil — we are working")
        PrTBodyText("إنتو عم بتشتغلو — 3am btishtaghlu — you (pl) are working")
        PrTBodyText("هم عم بيشتغلو — 3am byishtaghlu — they are working")

        PrTSectionHeader("Continuous Conjugation — أكل (to eat)")
        PrTBodyText("أنا عم باكل — 3am baakol — I am eating")
        PrTBodyText("إنت عم بتاكل — 3am btaakol — you (m) are eating")
        PrTBodyText("إنتِ عم بتاكلي — 3am btaakli — you (f) are eating")
        PrTBodyText("هو عم بياكل — 3am byaakol — he is eating")
        PrTBodyText("هي عم بتاكل — 3am btaakol — she is eating")
        PrTBodyText("إحنا عم بناكل — 3am bnaakol — we are eating")
        PrTBodyText("إنتو عم بتاكلو — 3am btaaklu — you (pl) are eating")
        PrTBodyText("هم عم بياكلو — 3am byaaklu — they are eating")

        PrTSectionHeader("Negating the Continuous")
        PrTBodyText("Use مش عم (mish 3am) — most common form:")
        PrTBodyText("• مش عم باكل — mish 3am baakol — I'm not eating")
        PrTBodyText("• مش عم بيشتغل — mish 3am byishtaghil — he's not working")
        PrTBodyText("• مش عم بتحكو — mish 3am bta7ku — you (pl) are not talking")
        Spacer(modifier = Modifier.height(8.dp))
        PrTBodyText("Emphatic: ما عم...ش (ma 3am...sh):")
        PrTBodyText("• ما عم باكلش — ma 3am bakolsh — I'm really not eating")
        PrTBodyText("• ما عم بيشتغلش — ma 3am bishtaghilsh — he's not working at all")

        PrTSectionHeader("Common Questions in the Present")
        PrTBodyText("• شو بتعمل؟ — shu bta3mal? — what do you (m) do? / what are you doing?")
        PrTBodyText("• وين بتشتغل؟ — wein btishtaghil? — where do you work?")
        PrTBodyText("• عم بتاكل؟ — 3am btaakol? — are you eating right now?")
        PrTBodyText("• شو عم بتعمل؟ — shu 3am bta3mal? — what are you doing (right now)?")
        PrTBodyText("• وين عم بيروح؟ — wein 3am byiru7? — where is he going?")
        PrTBodyText("• ليش مش عم بتجي؟ — leish mish 3am btijji? — why aren't you coming?")

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun PrTSectionHeader(text: String) {
    Spacer(modifier = Modifier.height(20.dp))
    Text(
        text = fixArabizi(text),
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.fillMaxWidth()
    )
    Spacer(modifier = Modifier.height(8.dp))
    HorizontalDivider()
    Spacer(modifier = Modifier.height(8.dp))
}

@Composable
private fun PrTBodyText(text: String) {
    Text(
        text = fixArabizi(text),
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(vertical = 2.dp)
    )
}
