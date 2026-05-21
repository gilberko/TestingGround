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
        ADJSectionHeader("Adjectives in Palestinian Arabic — الصفات")
        ADJBodyText("Adjectives in Arabic FOLLOW the noun they describe (unlike English where they come before). They must also agree with the noun in gender (masculine or feminine) and in definiteness (whether it has the article الـ il- or not).")

        ADJSectionHeader("Gender Agreement — Masculine and Feminine")
        ADJBodyText("Masculine adjectives end in a consonant. Feminine adjectives add -a or -e at the end:")
        Spacer(modifier = Modifier.height(8.dp))
        ADJBodyText("كبير (kbir) = big (m)  →  كبيرة (kbiire) = big (f)")
        ADJBodyText("جديد (jdiid) = new (m)  →  جديدة (jdiide) = new (f)")
        ADJBodyText("حلو (ḥilu) = nice/beautiful (m)  →  حلوة (ḥilwe) = nice (f)")
        ADJBodyText("منيح (mniḥ) = good (m)  →  منيحة (mniḥa) = good (f)")
        ADJBodyText("طويل (ṭawil) = tall/long (m)  →  طويلة (ṭawiila) = tall (f)")
        Spacer(modifier = Modifier.height(8.dp))
        ADJBodyText("Examples with nouns:")
        ADJBodyText("• بيت كبير — beit kbir — a big house  (بيت is masculine)")
        ADJBodyText("• سيارة كبيرة — sayyaara kbiire — a big car  (سيارة is feminine)")
        ADJBodyText("• رجّال منيح — rajjaal mniḥ — a good man")
        ADJBodyText("• بنت منيحة — bint mniḥa — a good girl")
        ADJBodyText("• فيلم طويل — film ṭawil — a long movie")
        ADJBodyText("• قصة طويلة — 'iṣṣa ṭawiila — a long story  (قصة is feminine)")

        ADJSectionHeader("Indefinite — \"A Green Apple\"")
        ADJBodyText("Arabic has no word for \"a\" — an unmodified noun is already indefinite (meaning \"a ___\"). The adjective simply follows the noun with no article:")
        Spacer(modifier = Modifier.height(8.dp))
        ADJBodyText("تفاحة (tuffaḥa) = an apple")
        ADJBodyText("تفاحة خضرا (tuffaḥa khad'ra) = a green apple")
        ADJBodyText("  (خضرا is the feminine form of أخضر, agreeing with تفاحة which is feminine)")
        Spacer(modifier = Modifier.height(8.dp))
        ADJBodyText("More indefinite examples:")
        ADJBodyText("• بيت قديم — beit 'adiim — an old house")
        ADJBodyText("• كتاب مهم — ktaab muhimm — an important book")
        ADJBodyText("• فيلم حلو — film ḥilu — a nice movie")
        ADJBodyText("• بنت طويلة — bint ṭawiila — a tall girl")
        ADJBodyText("• يوم حار — yoom ḥarr — a hot day")
        ADJBodyText("• سيارة سريعة — sayyaara srii'a — a fast car")

        ADJSectionHeader("Definite — \"The Green Apple\"")
        ADJBodyText("To say \"the green apple\" (a specific one), BOTH the noun AND the adjective take the definite article il- (الـ):")
        Spacer(modifier = Modifier.height(8.dp))
        ADJBodyText("التفاحة الخضرا — it-tuffaḥa il-khad'ra — the green apple")
        ADJBodyText("البيت الكبير — il-beit il-kbir — the big house")
        ADJBodyText("السيارة الجديدة — is-sayyaara il-jdiide — the new car")
        ADJBodyText("الكتاب المهم — il-ktaab il-muhimm — the important book")
        ADJBodyText("الولد الطويل — il-walad iṭ-ṭawil — the tall boy")
        ADJBodyText("البنت الحلوة — il-bint il-ḥilwe — the nice/beautiful girl")

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
        ADJBodyText("• الجو منيح — il-jaw mniḥ — the weather IS good  (predicate)")
        ADJBodyText("• الجو المنيح — il-jaw il-mniḥ — the good weather  (attribute)")
        ADJBodyText("• التفاحة خضرا — it-tuffaḥa khad'ra — the apple IS green  (predicate)")
        ADJBodyText("• التفاحة الخضرا — it-tuffaḥa il-khad'ra — the green apple  (attribute)")
        ADJBodyText("• السيارة غالية — is-sayyaara ghaalie — the car IS expensive  (predicate)")
        ADJBodyText("• السيارة الغالية — is-sayyaara il-ghaalie — the expensive car  (attribute)")

        ADJSectionHeader("Plural Adjectives")
        ADJBodyText("For people (and some animals), adjectives use a sound plural by adding -iin:")
        Spacer(modifier = Modifier.height(4.dp))
        ADJBodyText("تعبان (ta'baan) = tired → تعبانين (ta'baaniin) = tired (plural)")
        ADJBodyText("كويس (kwayyis) = good → كويسين (kwayyisiin) = good/well (plural)")
        ADJBodyText("مبسوط (mabsuuṭ) = happy → مبسوطين (mabsuuṭiin) = happy (plural)")
        ADJBodyText("طويل (ṭawil) = tall → طوال (ṭwaal) = tall (plural, broken plural)")
        Spacer(modifier = Modifier.height(8.dp))
        ADJBodyText("Examples:")
        ADJBodyText("• الولاد تعبانين — il-wlaad ta'baaniin — the boys are tired")
        ADJBodyText("• هم كويسين — humme kwayyisiin — they are doing well")
        ADJBodyText("• الناس مبسوطين — in-naas mabsuuṭiin — the people are happy")
        ADJBodyText("• الأولاد طوال — il-awlaad ṭwaal — the boys are tall")
        Spacer(modifier = Modifier.height(4.dp))
        ADJBodyText("For inanimate objects (things), Arabic typically uses a feminine singular adjective form for plurals — but this is complex and varies. In everyday Palestinian speech, this rule is often relaxed.")

        ADJSectionHeader("Quick Summary")
        ADJBodyText("1. Adjective follows noun: بيت كبير (not كبير بيت)")
        ADJBodyText("2. Adjective agrees in gender: add -a/-e for feminine nouns")
        ADJBodyText("3. No article on both = indefinite phrase: بيت كبير = a big house")
        ADJBodyText("4. Article on both = definite phrase: البيت الكبير = the big house")
        ADJBodyText("5. Article only on noun = \"IS\" statement: البيت كبير = the house IS big")

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun ADJSectionHeader(text: String) {
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
private fun ADJBodyText(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(vertical = 2.dp)
    )
}
