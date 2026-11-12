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
fun AdjectivesScreen() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        ADJSectionHeader("Adjectives in Palestinian Arabic — الصفات (is-sifaat)")
        ADJBodyText("Adjectives in Arabic FOLLOW the noun they describe (unlike English where they come before). They must also agree with the noun in gender (masculine or feminine) and in definiteness (whether it has the article الـ il- or not).")

        ADJSectionHeader("Gender Agreement — Masculine and Feminine")
        ADJBodyText("Masculine adjectives end in a consonant. Feminine adjectives add -a or -e at the end:")
        Spacer(modifier = Modifier.height(8.dp))
        ADJBodyText("كبير (kbir) = big (m)  →  كبيرة (kbiire) = big (f)")
        ADJBodyText("جديد (jdiid) = new (m)  →  جديدة (jdiide) = new (f)")
        ADJBodyText("حلو (7ilu) = nice/beautiful (m)  →  حلوة (7ilwe) = nice (f)")
        ADJBodyText("منيح (mni7) = good (m)  →  منيحة (mni7a) = good (f)")
        ADJBodyText("طويل (tawil) = tall/long (m)  →  طويلة (tawiila) = tall (f)")
        Spacer(modifier = Modifier.height(8.dp))
        ADJBodyText("Examples with nouns:")
        ADJBodyText("• بيت كبير — beit kbir — a big house  (بيت is masculine)")
        ADJBodyText("• سيارة كبيرة — sayyaara kbiire — a big car  (سيارة is feminine)")
        ADJBodyText("• رجّال منيح — rajjaal mni7 — a good man")
        ADJBodyText("• بنت منيحة — bint mni7a — a good girl")
        ADJBodyText("• فيلم طويل — film tawil — a long movie")
        ADJBodyText("• قصة طويلة — 2issa tawiila — a long story  (قصة is feminine)")

        ADJSectionHeader("Indefinite — \"A Green Apple\"")
        ADJBodyText("Arabic has no word for \"a\" — an unmodified noun is already indefinite (meaning \"a ___\"). The adjective simply follows the noun with no article:")
        Spacer(modifier = Modifier.height(8.dp))
        ADJBodyText("تفاحة (tuffa7a) = an apple")
        ADJBodyText("تفاحة خضرا (tuffa7a khadra) = a green apple")
        ADJBodyText("  (خضرا khadra is the feminine form of أخضر akhdar, agreeing with تفاحة tuffa7a which is feminine)")
        Spacer(modifier = Modifier.height(8.dp))
        ADJBodyText("More indefinite examples:")
        ADJBodyText("• بيت قديم — beit 2adiim — an old house")
        ADJBodyText("• كتاب مهم — ktaab muhimm — an important book")
        ADJBodyText("• فيلم حلو — film 7ilu — a nice movie")
        ADJBodyText("• بنت طويلة — bint tawiila — a tall girl")
        ADJBodyText("• يوم حار — yoom 7arr — a hot day")
        ADJBodyText("• سيارة سريعة — sayyaara srii3a — a fast car")

        ADJSectionHeader("Definite — \"The Green Apple\"")
        ADJBodyText("To say \"the green apple\" (a specific one), BOTH the noun AND the adjective take the definite article il- (الـ il-):")
        Spacer(modifier = Modifier.height(8.dp))
        ADJBodyText("التفاحة الخضرا — it-tuffa7a il-khadra — the green apple")
        ADJBodyText("البيت الكبير — il-beit il-kbir — the big house")
        ADJBodyText("السيارة الجديدة — is-sayyaara il-jdiide — the new car")
        ADJBodyText("الكتاب المهم — il-ktaab il-muhimm — the important book")
        ADJBodyText("الولد الطويل — il-walad it-tawil — the tall boy")
        ADJBodyText("البنت الحلوة — il-bint il-7ilwe — the nice/beautiful girl")

        ADJSectionHeader("The Key Distinction — Attribute vs Predicate")
        ADJBodyText("This is one of the most important rules in Arabic adjectives. Whether the adjective has the definite article or not (when the noun is definite) changes the ENTIRE meaning:")
        Spacer(modifier = Modifier.height(8.dp))
        ADJBodyText("البيت الكبير — il-beit il-kbir — the big house  (attribute: describing the house)")
        ADJBodyText("البيت كبير — il-beit kbir — the house IS big  (predicate: stating a fact)")
        Spacer(modifier = Modifier.height(8.dp))
        ADJBodyText("The rule:")
        ADJBodyText("• BOTH noun and adjective have il- → noun phrase: \"the big house\"")
        ADJBodyText("• Only the noun has il-, adjective has no article → predicate: \"the house IS big\" (no verb needed — Arabic drops \"is\")")
        Spacer(modifier = Modifier.height(8.dp))
        ADJBodyText("More examples of the distinction:")
        ADJBodyText("• الجو منيح — il-jaw mni7 — the weather IS good  (predicate)")
        ADJBodyText("• الجو المنيح — il-jaw il-mni7 — the good weather  (attribute)")
        ADJBodyText("• التفاحة خضرا — it-tuffa7a khadra — the apple IS green  (predicate)")
        ADJBodyText("• التفاحة الخضرا — it-tuffa7a il-khadra — the green apple  (attribute)")
        ADJBodyText("• السيارة غالية — is-sayyaara ghaalie — the car IS expensive  (predicate)")
        ADJBodyText("• السيارة الغالية — is-sayyaara il-ghaalie — the expensive car  (attribute)")

        ADJSectionHeader("Plural Adjectives")
        ADJBodyText("For people (and some animals), adjectives use a sound plural by adding -iin:")
        Spacer(modifier = Modifier.height(4.dp))
        ADJBodyText("تعبان (ta3baan) = tired → تعبانين (ta3baaniin) = tired (plural)")
        ADJBodyText("كويس (kwayyis) = good → كويسين (kwayyisiin) = good/well (plural)")
        ADJBodyText("مبسوط (mabsuut) = happy → مبسوطين (mabsuutiin) = happy (plural)")
        ADJBodyText("طويل (tawil) = tall → طوال (twaal) = tall (plural, broken plural)")
        Spacer(modifier = Modifier.height(8.dp))
        ADJBodyText("Examples:")
        ADJBodyText("• الولاد تعبانين — il-wlaad ta3baaniin — the boys are tired")
        ADJBodyText("• هم كويسين — humme kwayyisiin — they are doing well")
        ADJBodyText("• الناس مبسوطين — in-naas mabsuutiin — the people are happy")
        ADJBodyText("• الأولاد طوال — il-awlaad twaal — the boys are tall")
        Spacer(modifier = Modifier.height(4.dp))
        ADJBodyText("For inanimate objects (things), Arabic typically uses a feminine singular adjective form for plurals — but this is complex and varies. In everyday Palestinian speech, this rule is often relaxed.")

        ADJSectionHeader("Very, A Bit, Really — Where Do They Go?")
        ADJBodyText("Words like very (מאוד), a bit (קצת) and really (ממש) don't change form — no gender, no plural. What matters is where they stand:")
        Spacer(modifier = Modifier.height(8.dp))
        ADJBodyText("كتير — ktiir — very (מאוד)")
        ADJBodyText("  Most common in Palestinian: AFTER the adjective → حلو كتير — 7ilu ktiir — very nice")
        ADJBodyText("  Also heard BEFORE, especially Lebanese/Syrian → كتير حلو — ktiir 7ilu — very nice. Both are understood everywhere.")
        ADJBodyText("  • البيت كبير كتير — el-beit kbiir ktiir — the house is very big")
        ADJBodyText("  • هي تعبانة كتير — hiyye ta3baane ktiir — she is very tired")
        Spacer(modifier = Modifier.height(8.dp))
        ADJBodyText("شوي — shwayy — a bit / a little (קצת)")
        ADJBodyText("  Usually BEFORE the adjective (like Hebrew קצת עייף); after is also fine:")
        ADJBodyText("  • أنا شوي تعبان — ana shwayy ta3baan — I'm a bit tired  (also: ana ta3baan shwayy)")
        ADJBodyText("  • الأكل شوي مالح — el-akl shwayy maali7 — the food is a bit salty")
        Spacer(modifier = Modifier.height(8.dp))
        ADJBodyText("عن جد — 3an jadd — really / seriously (ממש)")
        ADJBodyText("  Usually right BEFORE the adjective or at the end of the sentence:")
        ADJBodyText("  • الفيلم عن جد حلو — el-film 3an jadd 7ilu — the movie is really good")
        ADJBodyText("  Another way to say ממש: double the very → حلو كتير كتير — 7ilu ktiir ktiir — really, really nice")
        Spacer(modifier = Modifier.height(8.dp))
        ADJBodyText("More of these words:")
        ADJBodyText("• مش كتير — mish ktiir — not very:  مش كتير غالي — mish ktiir ghaali — not very expensive")
        ADJBodyText("• تماماً — tamaaman — completely (after):  جاهز تماماً — jaahiz tamaaman — completely ready")
        ADJBodyText("• زيادة — zyaade — too (much) (after):  غالي زيادة — ghaali zyaade — too expensive")
        ADJBodyText("• شوي كتير — shwayy ktiir — a bit too much:  هاد شوي كتير — haad shwayy ktiir — that's a bit much")
        ADJBodyText("• جداً — jiddan — very (formal/MSA, ALWAYS after):  مهم جداً — muhimm jiddan — very important")
        Spacer(modifier = Modifier.height(8.dp))
        ADJBodyText("Worked example — הספר הזה ממש מוגזם (this book is really over the top):")
        ADJBodyText("• الكتاب هاد عن جد مبالغ فيه — el-ktaab haad 3an jadd mbaalagh fii — this book is really exaggerated")
        ADJBodyText("• الكتاب هاد زيادة كتير — el-ktaab haad zyaade ktiir — this book is way too much  (everyday way to say מוגזם)")
        ADJBodyText("  Word order: el-ktaab haad = הספר הזה (noun first, then \"this\" — like Hebrew). Short form: هالكتاب — hal-ktaab — this book (ha- + el-, a bit like Hebrew ה-).")

        ADJSectionHeader("Quick Summary")
        ADJBodyText("1. Adjective follows noun: بيت كبير (not كبير بيت)")
        ADJBodyText("2. Adjective agrees in gender: add -a/-e for feminine nouns")
        ADJBodyText("3. No article on both = indefinite phrase: بيت كبير = a big house")
        ADJBodyText("4. Article on both = definite phrase: البيت الكبير = the big house")
        ADJBodyText("5. Article only on noun = \"IS\" statement: البيت كبير = the house IS big")
        ADJBodyText("6. Very / a bit: حلو كتير (7ilu ktiir) or كتير حلو · شوي تعبان (shwayy ta3baan)")

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun ADJSectionHeader(text: String) {
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
private fun ADJBodyText(text: String) {
    Text(
        text = fixArabizi(text),
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(vertical = 2.dp)
    )
}
