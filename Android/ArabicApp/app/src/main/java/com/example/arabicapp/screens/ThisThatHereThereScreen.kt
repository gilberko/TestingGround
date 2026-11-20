package com.example.arabicapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

// Markup: **x** = bold (see RichText.kt).

@Composable
fun ThisThatHereThereScreen() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        // 1
        DEMSection("1. This — Near")
        DEMBody("Palestinian first, then Lebanese/Syrian. All of them mean \"this\" (Hebrew זה / זאת / אלה).")
        DEMBody("**m:** **هادا / هاد — haada / haad**  ·  Leb/Syr **هيدا — hayda**")
        DEMBody("**f:** **هادي / هاي — haadi / haay**  ·  Leb/Syr **هيدي — haydi**")
        DEMBody("**pl:** **هدول — hadool**  ·  Leb/Syr **هيدول — hayduul**  (Lebanese also heard: **هودي — hawdi**)")
        DEMExample("**haada** beiti — هادا بيتي — this is my house  ·  Leb **hayda** beiti")
        DEMExample("**haadi** binti — هادي بنتي — this is my daughter  ·  Leb **haydi** binti")
        DEMExample("**hadool** wlaadi — هدول ولادي — these are my children")
        DEMNote("Plural things (not people) can take either the feminine singular or the plural: **haay** el-kutub / **hadool** el-kutub — these books. You may also hear the old \"dh\" sound in villages (haadha, haadhi, hadhool) — it's the same word; in towns it's d. (Source: adrosverse, Palestinian-Jordanian 2.5.)")

        // 2
        DEMSection("2. That — Far")
        DEMBody("**m:** **هداك — hadaak**  ·  Leb/Syr **هيداك — haydaak**")
        DEMBody("**f:** **هديك — hadiik**  ·  Leb/Syr **هيديك — haydiik**")
        DEMBody("**pl:** **هدولاك — hadoolaak**  ·  Leb/Syr **هيدوليك — hayduulik**  (Lebanese also heard: **هوديك — hawdiik**)")
        DEMExample("**hadaak** el-walad — هداك الولد — that boy")
        DEMExample("**hadiik** el-bint — هديك البنت — that girl")
        DEMExample("**hadoolaak** en-naas — هدولاك الناس — those people")
        DEMExample("miin **hadaak**? — مين هداك؟ — who's that (guy)?")

        // 3
        DEMSection("3. Here and There")
        DEMBody("**here:** **هون — hoon**  (Lebanese also **hawn**)")
        DEMBody("**there / over there:** **هناك — hunaak / hnaak**  ·  Leb/Syr **هنيك — hniik / honiik**")
        DEMExample("ta3a la-**hoon** — تعا لهون — come here")
        DEMExample("ana min **hoon** — أنا من هون — I'm from here")
        DEMExample("ruu7 la-**hunaak** — روح لهناك — go there  ·  Leb ruu7 la-**hniik**")
        DEMExample("el-maT3am **hunaak**, janb el-bank — المطعم هناك، جنب البنك — the restaurant is over there, next to the bank")
        DEMExample("**hoon** w **hunaak** — هون وهناك — here and there")
        DEMExample("mish **hoon** — مش هون — not here (he isn't here)")

        // 4
        DEMSection("4. Is It True That -k Adds Distance?")
        DEMBody("**Yes — basically correct.** In every pair above, the far word is the near word plus a **-k** ending:")
        DEMExample("haada → hada**k**  ·  hayda → hayda**k**  (this → that, m)")
        DEMExample("haadi → hadii**k**  ·  haydi → haydii**k**  (this → that, f)")
        DEMExample("hadool → hadoolaa**k**  (these → those)")
        DEMExample("huna → hunaa**k**  (here → there)")
        DEMSub("Where it comes from")
        DEMBody("The -k goes back to Classical Arabic. There, a demonstrative could take the ending **-ka**, which Arab grammarians call **كاف الخطاب — kaaf el-khiTaab** (\"the kaf of addressing\") — the same k as in -ak \"your\". It points away from the speaker, toward the listener's side or beyond.")
        DEMExample("ذا — dhaa — this  →  ذاك — dhaak — that  →  ذلك — dhaalika — that (far)")
        DEMExample("هنا — huna — here  →  هناك — hunaak — there  →  هنالك — hunaalik — way over there")
        DEMNote("Classical grammarians disagree on the details: some say -k alone is \"middle distance\" and -l-k (dhaalika, hunaalik) is \"far\"; others treat both as far. Spoken Levantine kept only two steps: near (haada, hoon) and far (hadaak, hunaak). (Sources: mawdoo3 and diwanalarab on أسماء الإشارة للبعيد.)")
        DEMSub("But it's not just \"add a k\"")
        DEMBody("• **The vowels and stress change:** HAA-da → ha-DAAK, HAA-di → ha-DIIK. You can't just stick k onto haada (*haadak* is wrong).")
        DEMBody("• **hoon is not the base of hunaak.** Both come from formal **huna** (here): hoon is the shortened spoken form of huna, and hunaak is huna + -aak. So hoon → hunaak looks like \"add k\", but really both grew from the same word.")
        DEMBody("• **It only works for these pointing words.** You can't add -k to other words to mean \"far\": there's no *beitak* = \"that far house\" — beitak just means \"your house\" (the same k, but as \"your\").")
        DEMNote("Hebrew doesn't have this ending — הזה / ההוא and כאן / שם are separate words, so there's nothing to compare it to directly.")

        // 5
        DEMSection("5. Using Them in Sentences")
        DEMSub("\"This is a …\" vs \"this …\"")
        DEMBody("Exactly like Hebrew זה בית (this is a house) vs הבית הזה (this house):")
        DEMExample("**haada beit** — هادا بيت — this is a house  (= זה בית)")
        DEMExample("**haada el-beit** / **el-beit haada** — هادا البيت / البيت هادا — this house  (= הבית הזה; both orders are fine)")
        DEMExample("**haada huwwe el-ktaab** — هادا هو الكتاب — this is the book  (a pronoun is added when the second word has il-, otherwise it would read \"this book\")")
        DEMExample("**hadaak el-walad** / **el-walad hadaak** — that boy")
        DEMSub("The short form hal- = \"this\" + \"the\"")
        DEMBody("Before a noun, \"this\" is often shortened to **هال — hal-**, which already contains il- (like Hebrew הַ in הַיּוֹם = today):")
        DEMExample("**hal-beit** — هالبيت — this house  ·  **hal-marra** — هالمرة — this time  ·  **hal-isbuu3** — هالأسبوع — this week")
        DEMExample("Before sun letters the l is swallowed, just like il-: **has-sayyaara** — هالسيارة — this car  ·  **hash-shi** — هالشي — this thing  (see Learning → il — The Definite Article)")
        DEMNote("hal- is only for \"this\". For \"that\" use the full word: hadaak el-beit, never *hal-…*.")

        // 6
        DEMSection("6. Pointing and Showing")
        DEMExample("**shu haada?** — شو هادا؟ — what's this?  ·  Leb **shu hayda?**")
        DEMExample("**miin hadiik?** — مين هديك؟ — who's that (woman)?")
        DEMExample("**heek** — هيك — like this / so  ·  mish heek — not like that")
        DEMExample("**hayy** / **hayyo** — هيّ / هيّو — here it is / here he is (pointing, handing over). Commonly heard; forms vary by region (f hayya, pl hayyon).")
        DEMExample("**min hoon la-hunaak** — من هون لهناك — from here to there")

        // 7
        DEMSection("7. Quick Reference")
        DEMBody("this: **haada** (m) · **haadi / haay** (f) · **hadool** (pl)  —  Leb **hayda · haydi · hayduul**")
        DEMBody("that: **hadaak** (m) · **hadiik** (f) · **hadoolaak** (pl)  —  Leb **haydaak · haydiik · hayduulik**")
        DEMBody("here: **hoon**  ·  there: **hunaak / hnaak** (Leb **hniik**)")
        DEMBody("this + the: **hal-** (hal-beit, has-sayyaara)")
        DEMBody("-k = farther away: real, it comes from Classical Arabic — but only for these words, and the vowels change too.")

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun DEMSection(text: String) {
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
private fun DEMSub(text: String) {
    Spacer(modifier = Modifier.height(10.dp))
    Text(
        text = fixArabizi(text),
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.fillMaxWidth()
    )
    Spacer(modifier = Modifier.height(4.dp))
}

@Composable
private fun DEMBody(text: String) {
    Text(
        text = richText(text),
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(vertical = 2.dp)
    )
}

@Composable
private fun DEMExample(text: String) {
    Text(
        text = richText("• $text"),
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(start = 8.dp, top = 2.dp, bottom = 2.dp)
    )
}

@Composable
private fun DEMNote(text: String) {
    Spacer(modifier = Modifier.height(6.dp))
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer)
    ) {
        Text(
            text = richText(text),
            style = MaterialTheme.typography.bodyMedium,
            fontStyle = FontStyle.Italic,
            modifier = Modifier.padding(12.dp)
        )
    }
    Spacer(modifier = Modifier.height(4.dp))
}
