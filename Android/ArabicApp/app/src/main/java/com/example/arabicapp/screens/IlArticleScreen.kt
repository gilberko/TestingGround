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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

// Markup: **x** = bold (see RichText.kt).

@Composable
fun IlArticleScreen() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        // 1
        ILSection("1. What Is il-?")
        ILBody("**ال — il- / el-** = **the**. It's written joined to the word, like Hebrew **ה-**: البيت **il-beit** = הבית = the house.")
        ILBody("Palestinian usually says **il-**, Lebanese and Syrian more often **el-** (or just **l-** after a vowel). Formal Arabic (MSA) says **al-**. All of them are the same ال in writing — in this app you'll see both il- and el-.")
        ILBody("Like Hebrew ה-, it **never changes** for masculine / feminine or singular / plural.")
        ILBody("Arabic has no word for \"a / an\": **beit** = a house, **il-beit** = the house (Hebrew: בית / הבית).")

        // 2
        ILSection("2. Singular, Dual, Plural — Same il-")
        ILExample("**il-beit** — البيت — the house  ·  **il-beitein** — البيتين — the two houses  ·  **il-byuut** — البيوت — the houses")
        ILExample("**il-bint** — البنت — the girl  ·  **il-bintein** — البنتين — the two girls  ·  **il-banaat** — البنات — the girls")
        ILExample("**il-walad** — الولد — the boy  ·  **il-wlaad** — الولاد — the boys / the children")
        ILExample("**ij-jaaj** — الجاج — the chicken (collective)  ·  **il-maSaari** — المصاري — the money")
        ILExample("**il-yoom** — اليوم — today (lit. \"the day\")  ·  **il-leile** — الليلة — tonight (lit. \"the night\")")

        // 3
        ILSection("3. When There Is NO il-")
        ILBody("• With a possessive ending: **beiti** — بيتي — my house (not *il-beiti*). Same as Hebrew ביתי, not *הביתי*.")
        ILBody("• With people's names and most city names: **Saami**, **7eifa**, **Beiruut**. (Some place names have il- built in: **il-Quds** — Jerusalem, **esh-Shaam** — Damascus.)")
        ILBody("• On the first noun of a construct (section 4).")
        ILBody("• **hal-** = \"this\" (short for haada + il-): **hal-beit** — this house, **has-sayyaara** — this car. The il- is already inside hal-.")

        // 4
        ILSection("4. Construct (iDaafa) — il- Only on the LAST Noun")
        ILBody("Exactly like Hebrew smichut: **כסא המלך** = the king's chair. Hebrew puts ה- only before the last word (המלך), never before כסא. Arabic does the same:")
        ILExample("**kursi il-malek** — كرسي الملك — the king's chair (= כסא המלך)")
        ILExample("**beit ahli** — بيت أهلي — my parents' house (the last word has a possessive → definite, so no il- anywhere)")
        ILExample("**maftaa7 is-sayyaara** — مفتاح السيارة — the car key (= מפתח המכונית)")
        ILExample("**kaase mayy** — كاسة مي — a glass of water (no il- at all → indefinite, = כוס מים)")
        ILNote("Wrong: *il-kursi il-malek* for \"the king's chair\" — just like *הכסא המלך* is wrong in Hebrew. The il- on the last noun already makes the whole phrase definite.")
        ILNote("More on construct (ta marbuta -it/-et, taba3 = של): Learning → Construct State.")

        // 5
        ILSection("5. Adjectives — il- on BOTH")
        ILBody("Again like Hebrew: **הכיסא הסגול** — ה- before the noun AND before the adjective. Arabic does exactly the same:")
        ILExample("**il-kursi il-banafsaji** — الكرسي البنفسجي — the purple chair (= הכיסא הסגול)")
        ILExample("**kursi banafsaji** — كرسي بنفسجي — a purple chair (= כיסא סגול)")
        ILExample("**il-kursi banafsaji** — الكرسي بنفسجي — the chair IS purple (= הכיסא סגול)")
        ILNote("Same trick as Hebrew: il- on the noun but NOT on the adjective turns the phrase into a full sentence (\"the chair is purple\"). There's no word for \"is\" in the present.")
        ILExample("**il-banaat il-Zghaar** — البنات الزغار — the little girls  ·  **il-banaat Zghaar** — the girls are little")
        ILSub("Construct + adjective")
        ILExample("**kursi il-malek il-kbiir** — كرسي الملك الكبير — the king's big chair OR the big king's chair — ambiguous, just like כסא המלך הגדול. Context decides — or gender: **kursi il-malke il-kbiir** = the queen's big chair (kbiir is masculine, so it can only describe kursi, not malke).")

        // 6
        ILSection("6. Sun Letters and Moon Letters — Quick Review")
        ILBody("The l of il- is pronounced before some letters and swallowed before others. The 28 letters split into two groups of 14, named after the words **ish-shams** (the sun — l swallowed) and **il-qamar** (the moon — l pronounced).")
        ILSub("☀ Sun letters — the l disappears and the letter is doubled")
        ILBody("**ت t · ث t/s · د d · ذ d/z · ر r · ز z · س s · ش sh · ص S · ض D · ط T · ظ Z · ل l · ن n**")
        ILNote("Tip: they're all made with the tip of the tongue near the teeth — the same place as l, so the l just merges into them.")
        ILExample("**es-sayyaara** — السيارة — the car  ·  **esh-shams** — الشمس — the sun  ·  **en-naas** — الناس — the people")
        ILExample("**et-tuffaa7** — التفاح — the apples  ·  **ed-dinya** — الدنيا — the world  ·  **er-raas** — الراس — the head  ·  **eT-Taawle** — الطاولة — the table")
        ILSub("☾ Moon letters — the l is said")
        ILBody("**ء 2 · ب b · ج j · ح 7 · خ kh · ع 3 · غ gh · ف f · ق q/2 · ك k · م m · ه h · و w · ي y**")
        ILExample("**il-qamar / il-2amar** — القمر — the moon  ·  **il-beit** — البيت — the house  ·  **il-7ammaam** — الحمّام — the bathroom")
        ILExample("**il-3arabi** — العربي — the Arabic  ·  **il-kalb** — الكلب — the dog  ·  **il-yoom** — اليوم — today")
        ILNote("ق stays a moon letter even when you pronounce it as 2: il-2ahwe — القهوة — the coffee.")
        ILNote("The writing doesn't change: it's always ال. Only the pronunciation changes (in a vowelled text the sun letter gets a shadda: الشَّمس).")

        // 7
        ILSection("7. Is It the Same in Palestinian / Lebanese / Syrian?")
        ILBody("**Yes** — the sun / moon rule works the same everywhere in the Levant (and in MSA). The differences are small:")
        ILBody("• **ج (j)** — a moon letter in MSA, but in the Levant it usually behaves as a **sun letter** for speakers who say it as \"zh\" (most Lebanese, Syrians and many Palestinians): **ij-jaaj** / **ej-jum3a** (the chicken / Friday). Speakers who say \"dj\" (e.g. some Palestinians, Jordanians) often keep the l: **il-djaaj**. Both are heard.")
        ILBody("• **The vowel:** Palestinian **il-**, Lebanese/Syrian **el-**; both often just **l-** after a vowel (section 9).")
        ILBody("• **Before two consonants** Lebanese and Syrian often say **le- / li-**: **le-ktaab** — the book, **li-mdiir** — the manager (instead of il-ktaab), to avoid three consonants in a row.")
        ILNote("Sources: adrosverse (Palestinian-Jordanian definite article), Lebanese Arabic Institute, Wikipedia (Levantine Arabic grammar). The ج behaviour varies by speaker — listen to your teacher.")

        // 8
        ILSection("8. \"Assimilates\" — Gone, or Just Light?")
        ILBody("**The l is completely gone** — it doesn't become a light l. It turns into a copy of the next consonant, so that consonant is **doubled** (held a little longer):")
        ILExample("السيارة = **es-sayyaara** → said \"ess-sayyaara\", not \"el-sayyaara\" and not \"e-sayyaara\"")
        ILExample("الشمس = **esh-shams** → the sh is held longer: \"esh-sh-ams\"")
        ILBody("Hebrew has the same thing: in **הַשֶּׁמֶשׁ** the dagesh in ש after ה- is exactly this doubling.")
        ILBody("What **does** become very light, or disappears, is the **vowel** of il-: in fast speech and after another vowel it's often just a quick l- or a doubled consonant: **fi l-beit**, **3a-s-suu2**.")
        ILNote("So you probably understood the right idea: you don't hear an l, and the article is very short. The precise way to say it: the l disappears, and what's left is a doubled consonant — a slightly longer s / sh / t etc. That doubling is the only trace of \"the\", so it's worth hearing it: es-sayyaara (the car) vs sayyaara (a car).")

        // 9
        ILSection("9. The i of il- Merges Into the Previous Word")
        ILBody("When il- comes after a short word ending in a vowel (or after b- / w-), its i drops and the l (or the doubled sun letter) joins the previous word:")
        ILExample("**b- + il-beit → bil-beit** — بالبيت — in / at the house  (b is a moon letter → the l stays)")
        ILExample("**b- + is-sayyaara → bis-sayyaara** — بالسيارة — by car / in the car ✔")
        ILNote("Your \"bis-bet\" should be **bil-beit**: ب is a moon letter, so the l of il- is said. bis- only appears before sun letters (bis-sayyaara, bish-shughl, bit-taksi).")
        ILExample("**fi + il-beit → fil-beit / fi l-beit** — في البيت — in the house  ·  **fi s-suu2** — in the market")
        ILExample("**3ala + il- → 3al- / 3a-l-**: **3a-l-beit** — على البيت — to the house  ·  **3as-suu2** — to the market  ·  **3ash-shughl** — to work")
        ILExample("**la- + il- → lal- / las-**: **lal-beit** — للبيت — to/for the house  ·  **lash-shabaab** — for the guys")
        ILExample("**w- + il- → wil- / w-is-**: **il-akl wil-mayy** — the food and the water  ·  **ish-shams wil-2amar** — the sun and the moon")
        ILExample("**min + il- → min il-** / fast: **mn-il-**: **min il-beit** — from the house  ·  **min ish-shughl** — from work")
        ILNote("After a consonant (min, ma3, 3ind) there's no merge: ma3 il-wlaad — with the children, 3ind il-baab — by the door. In fast speech you may still hear ma3 l-wlaad.")

        // 10
        ILSection("Quick Reference")
        ILBody("• **il- / el- / l-** = the · no word for \"a\" · never changes for gender or number")
        ILBody("• No il- with: possessive endings (beiti), names, the first noun of a construct, hal- (this)")
        ILBody("• Construct: il- on the last noun only — **kursi il-malek** (כסא המלך)")
        ILBody("• Adjective: il- on both — **il-kursi il-banafsaji** (הכיסא הסגול); il- only on the noun = a sentence (the chair is purple)")
        ILBody("• Sun letters ت ث د ذ ر ز س ش ص ض ط ظ ل ن → l disappears, letter doubled: **es-sayyaara**")
        ILBody("• Moon letters ء ب ج ح خ ع غ ف ق ك م ه و ي → l said: **il-beit** (Levantine ج often acts as sun: **ij-jaaj**)")
        ILBody("• After b- / fi / 3ala / la- / w-: the i drops — **bil-beit, bis-sayyaara, fil-beit, 3a-s-suu2, lal-beit, wil-mayy**")

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun ILSection(text: String) {
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
private fun ILSub(text: String) {
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
private fun ILBody(text: String) {
    Text(
        text = richText(text),
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(vertical = 2.dp)
    )
}

@Composable
private fun ILExample(text: String) {
    Text(
        text = richText("• $text"),
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(start = 8.dp, top = 2.dp, bottom = 2.dp)
    )
}

@Composable
private fun ILNote(text: String) {
    Text(
        text = richText(text),
        style = MaterialTheme.typography.bodySmall,
        fontStyle = FontStyle.Italic,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        modifier = Modifier.padding(vertical = 3.dp)
    )
}
