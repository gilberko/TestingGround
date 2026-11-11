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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun NounsSingularPluralScreen() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        NSPSectionHeader("Every Noun Is Masculine or Feminine")
        NSPBodyText("Arabic has no neutral \"it\". Every noun — even a table or an idea — is either masculine (m) or feminine (f), and adjectives, verbs and pronouns change to agree with it.")
        Spacer(modifier = Modifier.height(8.dp))
        NSPSubHeader("How to recognise a feminine noun")
        NSPBodyText("• Most feminine nouns end in ة (tah marbuta), pronounced -e or -a:")
        NSPBodyText("   سيارة — sayyara — car (f)")
        NSPBodyText("   مدرسة — madrase — school (f)")
        NSPBodyText("   شجرة — shajara — tree (f)")
        NSPBodyText("• Female people and animals are feminine whatever the ending: إم — imm — mother; بنت — bint — girl.")
        NSPBodyText("• A handful of common nouns are feminine with no ة — they just have to be memorised:")
        NSPBodyText("   شمس — shams — sun · أرض — ard — earth / ground · إيد — 2iid — hand")
        NSPBodyText("   عين — 3ein — eye · إجر — ijr — leg / foot · دار — daar — home / house")
        NSPBodyText("• Everything else is masculine: بيت — beit — house; كتاب — ktaab — book; باب — baab — door.")
        Spacer(modifier = Modifier.height(8.dp))
        NSPNote("Rule of thumb: ends in ة → feminine. No ة → masculine, unless it's a female being or one of the short list of exceptions.")

        NSPSectionHeader("Singular, Dual and Plural")
        NSPBodyText("English has two numbers: one / many. Arabic has three:")
        NSPBodyText("• Singular — exactly 1: كلب — kalb — a dog")
        NSPBodyText("• Dual — exactly 2: كلبين — kalbein — two dogs")
        NSPBodyText("• Plural — 3 or more: كلاب — klaab — dogs")
        Spacer(modifier = Modifier.height(4.dp))
        NSPBodyText("So yes — the plural really starts at 3. For 2 you use the dual instead.")

        NSPSectionHeader("The Dual — Two of Something")
        NSPBodyText("The dual is just an ending added to the singular. You don't even need the word \"two\":")
        Spacer(modifier = Modifier.height(4.dp))
        NSPSubHeader("Masculine (no ة): add -ein")
        NSPBodyText("كلب → كلبين — kalb → kalbein — two dogs")
        NSPBodyText("بيت → بيتين — beit → beitein — two houses")
        NSPBodyText("يوم → يومين — yom → yomein — two days")
        NSPBodyText("كتاب → كتابين — ktaab → ktaabein — two books")
        NSPBodyText("ألف → ألفين — alf → alfein — two thousand")
        Spacer(modifier = Modifier.height(4.dp))
        NSPSubHeader("Feminine with ة: add -tein")
        NSPBodyText("سيارة → سيارتين — sayyara → sayyartein — two cars")
        NSPBodyText("ساعة → ساعتين — sa3a → sa3tein — two hours")
        NSPBodyText("مرة → مرتين — marra → martein — twice")
        NSPBodyText("شجرة → شجرتين — shajara → shajartein — two trees")
        Spacer(modifier = Modifier.height(8.dp))
        NSPNote("Why the t? You guessed right — it is the tah marbuta. ة is really a hidden t that is silent at the end of a word, and comes back as soon as anything is attached: sayyara → sayyar-t-i (my car) → sayyar-t-ein (two cars).")
        Spacer(modifier = Modifier.height(8.dp))
        NSPBodyText("Feminine nouns without ة take plain -ein: إيد → إيدين — 2iid → 2iidein — two hands; عين → عينين — 3ein → 3einein — two eyes.")
        Spacer(modifier = Modifier.height(4.dp))
        NSPBodyText("Alternative: for emphasis people also say تنين / تنتين + plural — tnein wlaad (two boys), tintein banaat (two girls). The dual ending is the everyday form.")
        Spacer(modifier = Modifier.height(4.dp))
        NSPBodyText("Before possessive endings, Palestinians usually switch to تنين: \"my two brothers\" = ikhwati it-tnein rather than a dual.")

        NSPSectionHeader("Plurals — Three Kinds")
        NSPSubHeader("1. Sound masculine plural: -iin")
        NSPBodyText("Mostly for male people and professions:")
        NSPBodyText("معلّم → معلّمين — mu3allim → mu3allmiin — teachers")
        NSPBodyText("مهندس → مهندسين — muhandis → muhandsiin — engineers")
        NSPBodyText("فلسطيني → فلسطينيين — falastiini → falastiiniyyiin — Palestinians")
        Spacer(modifier = Modifier.height(4.dp))
        NSPSubHeader("2. Sound feminine plural: -aat")
        NSPBodyText("Most ة nouns, female professions, and many loanwords — drop the ة and add -aat:")
        NSPBodyText("سيارة → سيارات — sayyara → sayyaraat — cars")
        NSPBodyText("معلّمة → معلّمات — mu3allme → mu3allmaat — female teachers")
        NSPBodyText("باص → باصات — baas → baasaat — buses")
        Spacer(modifier = Modifier.height(4.dp))
        NSPSubHeader("3. Broken plural: the word changes inside")
        NSPBodyText("The most common kind — the vowels inside the word are rearranged. There are patterns, but in practice you learn each plural with its noun:")
        NSPBodyText("ولد → ولاد — walad → wlaad — boys / children")
        NSPBodyText("بيت → بيوت — beit → byuut — houses")
        NSPBodyText("كلب → كلاب — kalb → klaab — dogs")
        NSPBodyText("كتاب → كتب — ktaab → kutub — books")
        NSPBodyText("باب → بواب — baab → bwaab — doors")
        NSPBodyText("شارع → شوارع — shaari3 → shawaari3 — streets")
        Spacer(modifier = Modifier.height(8.dp))
        NSPNote("Every noun in the Simple Dictionary screens now shows its gender, its dual and its plural, so you can learn them together.")

        NSPSectionHeader("Numbers + Nouns — Which Form When?")
        NSPBodyText("This is where Arabic surprises English speakers. The form of the noun depends on the number:")
        Spacer(modifier = Modifier.height(4.dp))
        NSPSubHeader("1 — singular (number optional, comes after)")
        NSPBodyText("كلب — kalb — a dog · كلب واحد — kalb waa7ad — one (single) dog")
        NSPSubHeader("2 — dual (no number needed)")
        NSPBodyText("كلبين — kalbein — two dogs")
        NSPSubHeader("3 to 10 — PLURAL, with the short number form")
        NSPBodyText("تلت كلاب — tlat klaab — three dogs")
        NSPBodyText("خمس سيارات — khams sayyaraat — five cars")
        NSPBodyText("عشر بيوت — 3ashr byuut — ten houses")
        NSPBodyText("The number loses its final -e before the noun: tlaate → tlat, khamse → khams.")
        NSPSubHeader("11 and above — back to SINGULAR")
        NSPBodyText("حدعشر كلب — 7da3shar kalb — eleven dogs")
        NSPBodyText("عشرين سيارة — 3ishriin sayyara — twenty cars")
        NSPBodyText("مية شيكل — miit shekel — a hundred shekels")
        NSPBodyText("ألف سنة — alf sane — a thousand years")
        Spacer(modifier = Modifier.height(4.dp))
        NSPBodyText("So you were right: there IS a point where the singular comes back — and that number is 11. From 11 to 99 the noun is singular, and the same goes for 100, 1000 and above.")
        Spacer(modifier = Modifier.height(4.dp))
        NSPSubHeader("Compound numbers follow their last part")
        NSPBodyText("مية وتلت كتب — miyye w-tlat kutub — 103 books (ends in 3 → plural)")
        NSPBodyText("مية وعشرين كتاب — miyye w-3ishriin ktaab — 120 books (ends in 20 → singular)")
        NSPBodyText("ألفين — alfein — 2000 (dual of alf)")

        NSPSectionHeader("Adjectives With Dual and Plural Nouns")
        NSPBodyText("• Dual nouns take the plural adjective: كلبين كبار — kalbein kbaar — two big dogs.")
        NSPBodyText("• People in the plural take the plural adjective: ولاد صغار — wlaad zghaar — small children.")
        NSPBodyText("• Things and animals in the plural take either the feminine singular or the plural: السيارات حلوة / حلوين — is-sayyaraat 7ilwe / 7ilwiin — the cars are nice. Feminine singular is the more traditional choice.")

        NSPSectionHeader("Summary")
        NSPBodyText("Singular / dual / plural / 11+:")
        Spacer(modifier = Modifier.height(4.dp))
        NSPBodyText("dog (m) — kalb / kalbein / tlat klaab / 3ishriin kalb")
        NSPBodyText("house (m) — beit / beitein / tlat byuut / 3ishriin beit")
        NSPBodyText("car (f) — sayyara / sayyartein / tlat sayyaraat / 3ishriin sayyara")
        NSPBodyText("hour (f) — sa3a / sa3tein / tlat saa3aat / 3ishriin sa3a")
        Spacer(modifier = Modifier.height(8.dp))
        NSPNote("Checking your guesses:\n• Nouns are masculine or feminine — correct.\n• Plural is for 3 or more — correct; 2 has its own dual form.\n• After some number the singular returns — correct, from 11 upward (and for 100, 1000…).\n• Dual = -en / -ten — correct; pronounced -ein / -tein in Palestinian, and the t is indeed the tah marbuta coming back.")

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun NSPSectionHeader(text: String) {
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
private fun NSPSubHeader(text: String) {
    Spacer(modifier = Modifier.height(8.dp))
    Text(
        text = fixArabizi(text),
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.SemiBold
    )
    Spacer(modifier = Modifier.height(4.dp))
}

@Composable
private fun NSPBodyText(text: String) {
    Text(
        text = fixArabizi(text),
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(vertical = 2.dp)
    )
}

@Composable
private fun NSPNote(text: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer)
    ) {
        Text(
            text = fixArabizi(text),
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(12.dp)
        )
    }
}
