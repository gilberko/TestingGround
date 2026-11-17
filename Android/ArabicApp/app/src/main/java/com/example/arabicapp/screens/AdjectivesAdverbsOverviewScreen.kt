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

@Composable
fun AdjectivesAdverbsOverviewScreen() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        AAONote("This screen puts it all together: how an adjective changes with the person it describes (I, you, he, she, we, you all, they), how to soften or strengthen it (shwayy, ktiir and friends), and where adverbs go in a sentence. Palestinian first, Lebanese/Syrian where it differs. For more words: Simple Dictionary → Common Adjectives / Common Adverbs.")

        AAOSectionHeader("1. How Adjectives Work")
        AAOWord("• The adjective comes AFTER the noun, like in Hebrew: beit kbiir — بيت كبير — a big house (בית גדול).")
        AAOWord("• It agrees with the noun or person: masculine, feminine (-e / -a), or plural (-iin).")
        AAOWord("• In the present there is no verb \"to be\", again like Hebrew: ana ta3baan = I (am) tired (אני עייף).")
        AAOSmall("The feminine ending is -e after most letters (7ilwe, mukhiife) and -a after the \"heavy\" letters like T, S, r, 3, 7, kh, q (shaaTra). Both are written ة.")

        AAOSectionHeader("2. Three Adjectives With Every Pronoun")
        AAOSubHeader("nice / pretty / good — حلو 7ilu")
        AAOInfo("m: حلو 7ilu · f: حلوة 7ilwe · pl: حلوين 7ilwiin")
        AAOWord("I (a man speaking) — أنا حلو — ana 7ilu")
        AAOWord("I (a woman speaking) — أنا حلوة — ana 7ilwe")
        AAOWord("you (m) — إنت حلو — inta 7ilu")
        AAOWord("you (f) — إنتِ حلوة — inti 7ilwe")
        AAOWord("he — هو حلو — huwwe 7ilu")
        AAOWord("she — هي حلوة — hiyye 7ilwe")
        AAOWord("we — إحنا حلوين — i7na 7ilwiin")
        AAOWord("you (pl) — إنتو حلوين — intu 7ilwiin")
        AAOWord("they — هم حلوين — humme 7ilwiin  (Leb/Syr: هنّي حلوين — hinne 7ilwiin)")
        AAOExample("el-bint 7ilwe w el-walad 7ilu — البنت حلوة والولد حلو — The girl is pretty and the boy is cute.")

        AAOSubHeader("scary — مخيف mukhiif")
        AAOInfo("m: مخيف mukhiif · f: مخيفة mukhiife · pl: مخيفين mukhiifiin")
        AAOWord("I (a man speaking) — أنا مخيف — ana mukhiif")
        AAOWord("I (a woman speaking) — أنا مخيفة — ana mukhiife")
        AAOWord("you (m) — إنت مخيف — inta mukhiif")
        AAOWord("you (f) — إنتِ مخيفة — inti mukhiife")
        AAOWord("he — هو مخيف — huwwe mukhiif")
        AAOWord("she — هي مخيفة — hiyye mukhiife")
        AAOWord("we — إحنا مخيفين — i7na mukhiifiin")
        AAOWord("you (pl) — إنتو مخيفين — intu mukhiifiin")
        AAOWord("they — هم مخيفين — humme mukhiifiin  (Leb/Syr: hinne mukhiifiin)")
        AAOSmall("For things (not people) in the plural, Levantine often uses the feminine singular: el-aflaam mukhiife = the movies are scary. With people, use -iin.")
        AAOExample("el-film kaan mukhiif ktiir — الفيلم كان مخيف كتير — The movie was very scary.")

        AAOSubHeader("smart — شاطر shaaTer")
        AAOInfo("m: شاطر shaaTer · f: شاطرة shaaTra · pl: شاطرين shaaTriin")
        AAOWord("I (a man speaking) — أنا شاطر — ana shaaTer")
        AAOWord("I (a woman speaking) — أنا شاطرة — ana shaaTra")
        AAOWord("you (m) — إنت شاطر — inta shaaTer")
        AAOWord("you (f) — إنتِ شاطرة — inti shaaTra")
        AAOWord("he — هو شاطر — huwwe shaaTer")
        AAOWord("she — هي شاطرة — hiyye shaaTra")
        AAOWord("we — إحنا شاطرين — i7na shaaTriin")
        AAOWord("you (pl) — إنتو شاطرين — intu shaaTriin")
        AAOWord("they — هم شاطرين — humme shaaTriin  (Leb/Syr: hinne shaaTriin)")
        AAOSmall("shaaTer = smart, clever, good at something (shaaTer bir-riyaaDiyyaat = good at math), and it's the classic praise for kids: shaaTer! / shaaTra! (= כל הכבוד). For \"intelligent\" in the bookish sense: ذكي zaki (m) / ذكية zakiyye (f) / pl: أذكيا azkiya.")
        AAOExample("inti ktiir shaaTra! — إنتِ كتير شاطرة! — You're very smart! (to f)")

        AAOSectionHeader("3. Is There m / f in \"We\", \"You (pl)\" and \"They\"?")
        AAOWord("we — i7na: NO. Same word and same plural adjective for men and women (i7na ta3baaniin).")
        AAOWord("you (pl) — intu: in everyday speech NO. One form for men, women and mixed groups (intu shaaTriin).")
        AAOWord("they — humme / hinne: in everyday speech NO. Palestinian humme, Lebanese/Syrian hinne, for any group (humme 7ilwiin).")
        AAONote("Some speakers, especially rural Palestinian and older people, still have separate feminine plural forms: إنتن intin (you, f pl) and هنّ hinne (they, f), sometimes with a feminine plural adjective (7ilwaat). In the cities and in Lebanon/Syria this is rare, and the -iin form is used for everyone. A group with even one man is always masculine. Not every source agrees on how widespread the feminine plural is, so treat it as regional.")
        AAOSmall("Compare Hebrew: אנחנו has no gender either, but אתם/אתן and הם/הן do. Spoken Levantine has mostly dropped that distinction.")

        AAOSectionHeader("4. Past and Future With an Adjective")
        AAOWord("Present: no verb — ana shaaTer — I am smart")
        AAOWord("Past: kaan + adjective — kunt shaaTer — I was smart;  kaanat 7ilwe — she was pretty;  kaanu mukhiifiin — they were scary")
        AAOWord("Future: ra7 + ykuun — ra7 akuun ta3baan — I'll be tired;  ra7 tkuun 7ilwe — she'll be pretty / it'll be nice (f)")
        AAOExample("bukra ra7 ykuun ej-jaww 7ilu — بكرا رح يكون الجو حلو — Tomorrow the weather will be nice.")

        AAOSectionHeader("5. Modifiers: shwayy, ktiir and Friends")
        AAOWord("very — كتير — ktiir: AFTER the adjective (common Palestinian) or BEFORE (common Lebanese/Syrian)")
        AAOExample("hiyye 7ilwe ktiir / hiyye ktiir 7ilwe — هي حلوة كتير / هي كتير حلوة — She's very pretty.")
        AAOWord("a bit / a little — شوي — shwayy: BEFORE the adjective")
        AAOExample("ana shwayy ta3baan — أنا شوي تعبان — I'm a bit tired. (m)")
        AAOWord("really — عن جد — 3an jadd: usually BEFORE")
        AAOExample("el-film 3an jadd mukhiif — الفيلم عن جد مخيف — The movie is really scary.")
        AAOWord("really, really / so — كتير كتير — ktiir ktiir")
        AAOExample("hu shaaTer ktiir ktiir — هو شاطر كتير كتير — He's really, really smart.")
        AAOWord("very (formal) — جداً — jiddan: always AFTER")
        AAOExample("el-akl Tayyib jiddan — الأكل طيّب جداً — The food is very tasty.")
        AAOWord("too (too much) — زيادة — zyaade: AFTER")
        AAOExample("el-2ahwe 7ilwe zyaade — القهوة حلوة زيادة — The coffee is too sweet.")
        AAOWord("not very — مش كتير — mish ktiir: BEFORE")
        AAOExample("el-ktaab mish ktiir mukhiif — الكتاب مش كتير مخيف — The book isn't very scary.")
        AAOWord("not at all — أبداً — abadan: after a negative")
        AAOExample("mish mukhiif abadan — مش مخيف أبداً — It's not scary at all.")
        AAOWord("that much / so — هالقد — hal-2add: BEFORE")
        AAOExample("ma kunt 3aarif innak hal-2add shaaTer — ما كنت عارف إنك هالقد شاطر — I didn't know you were that smart.")
        AAOWord("also / too — كمان — kamaan: after the adjective or at the end")
        AAOExample("hiyye 7ilwe w shaaTra kamaan — هي حلوة وشاطرة كمان — She's pretty and smart too.")
        AAOWord("more — أكتر — aktar (or the af3al form: a7la = nicer)")
        AAOExample("hu ashTar minni — هو أشطر مني — He's smarter than me.  (More: Learning → Comparisons)")

        AAOSectionHeader("6. Adverbs in Sentences")
        AAOWord("Where do they go? Usually AFTER the verb (and after the object, if there is one). Time words can also go at the start, just like in Hebrew.")
        AAOSubHeader("How (manner)")
        AAOWord("well — منيح — mnii7 (the same word as \"good\", like Hebrew טוב)")
        AAOExample("bti7ki 3arabi mnii7 — بتحكي عربي منيح — You speak Arabic well. (to m)")
        AAOWord("fast / quickly — بسرعة — b-sur3a")
        AAOExample("saa2 b-sur3a — ساق بسرعة — He drove fast.")
        AAOWord("slowly — شوي شوي — shwayy shwayy;  على مهل — 3ala mahl")
        AAOExample("i7ki shwayy shwayy, min faDlak — احكي شوي شوي، من فضلك — Speak slowly, please. (to m)")
        AAOWord("exactly — بالزبط — biz-zabT")
        AAOExample("hada biz-zabT elli biddi yaa — هاد بالزبط اللي بدي ياه — That's exactly what I want.")
        AAOSubHeader("When (time)")
        AAOWord("now — هلّق — halla2;  today — اليوم — el-yoom;  tomorrow — بكرا — bukra;  yesterday — مبارح — mbaari7;  later / afterwards — بعدين — ba3dein;  still / not yet — لسّا — lissa")
        AAOExample("mbaari7 ru7t 3al-ba7r — مبارح رحت عالبحر — Yesterday I went to the sea.")
        AAOExample("ba3dein ba7kiilak — بعدين بحكيلك — I'll tell you later. (to m)")
        AAOSubHeader("Where (place)")
        AAOWord("here — هون — hon;  there — هناك — hunaak;  outside — برّا — barra;  inside — جوّا — juwwa")
        AAOExample("istannaani barra — استنّاني برّا — Wait for me outside. (to m)")
        AAOSubHeader("How often (frequency)")
        AAOWord("always — دايماً — daayman;  sometimes — مرّات — marraat;  never — أبداً — abadan (with a negative);  usually — عادةً — 3aadatan")
        AAOExample("daayman bashrab 2ahwe es-sub7 — دايماً بشرب قهوة الصبح — I always drink coffee in the morning.")
        AAOExample("ma bakol la7me abadan — ما باكل لحمة أبداً — I never eat meat.")
        AAOSubHeader("Adverb + modifier")
        AAOWord("Modifiers work on adverbs too: mnii7 ktiir (very well), shwayy b-sur3a (a bit fast), b-sur3a zyaade (too fast).")
        AAOExample("btisu2 b-sur3a zyaade! — بتسوق بسرعة زيادة! — You drive too fast! (to m)")

        AAONote("Quick summary: adjective after the noun, agrees m / f / pl; no \"to be\" in the present; kaan / ra7 ykuun for past / future. shwayy BEFORE, ktiir after (Pal) or before (Leb/Syr), jiddan and zyaade AFTER. Adverbs usually come after the verb. More words: Simple Dictionary → Common Adjectives and Common Adverbs.")

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun AAOSectionHeader(text: String) {
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
private fun AAOWord(text: String) {
    Text(
        text = fixArabizi(text),
        style = MaterialTheme.typography.bodyMedium,
        fontWeight = FontWeight.SemiBold,
        modifier = Modifier.padding(top = 6.dp, bottom = 2.dp)
    )
}

@Composable
private fun AAOInfo(text: String) {
    Text(
        text = fixArabizi("    $text"),
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(vertical = 1.dp)
    )
}

@Composable
private fun AAOExample(text: String) {
    Text(
        text = fixArabizi("    e.g. $text"),
        style = MaterialTheme.typography.bodyMedium,
        fontStyle = FontStyle.Italic,
        modifier = Modifier.padding(vertical = 1.dp)
    )
}

@Composable
private fun AAOSmall(text: String) {
    Text(
        text = fixArabizi("    $text"),
        style = MaterialTheme.typography.bodySmall,
        modifier = Modifier.padding(vertical = 2.dp)
    )
}

@Composable
private fun AAONote(text: String) {
    Spacer(modifier = Modifier.height(8.dp))
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
    Spacer(modifier = Modifier.height(4.dp))
}

@Composable
private fun AAOSubHeader(text: String) {
    Spacer(modifier = Modifier.height(8.dp))
    Text(
        text = fixArabizi(text),
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.SemiBold
    )
    Spacer(modifier = Modifier.height(4.dp))
}
