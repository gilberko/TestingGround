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
fun FutureTenseScreen() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        FTSectionHeader("Talking About the Future — المستقبل")
        FTBodyText("Palestinian Arabic has no separate future tense conjugation. Instead, the future is expressed by placing رح (raḥ) before the imperfect (present-form) verb. رح is a fixed particle — it never changes.")
        Spacer(modifier = Modifier.height(8.dp))
        FTBodyText("Structure: رح + imperfect verb")
        FTBodyText("Example: رح يروح (raḥ yruuḥ) = he will go")

        FTSectionHeader("The Imperfect Prefixes")
        FTBodyText("To use رح, you need to know the imperfect (present) form of the verb for each person. The imperfect uses prefixes (and sometimes suffixes):")
        Spacer(modifier = Modifier.height(8.dp))
        FTBodyText("أـ (a-) → I  (1st person singular)")
        FTBodyText("تـ (t-) → you (masculine singular)")
        FTBodyText("تـ...ي (t-...-i) → you (feminine singular)")
        FTBodyText("يـ (y-) → he (3rd person masculine singular)")
        FTBodyText("تـ (t-) → she (3rd person feminine singular)")
        FTBodyText("نـ (n-) → we")
        FTBodyText("تـ...و (t-...-u) → you (plural)")
        FTBodyText("يـ...و (y-...-u) → they")

        FTSectionHeader("Future Conjugation — روح (go)")
        FTBodyText("Imperfect of go: أروح / تروح / يروح (aruuḥ / truuḥ / yruuḥ)")
        Spacer(modifier = Modifier.height(8.dp))
        FTBodyText("أنا رح أروح — ana raḥ aruuḥ — I will go")
        FTBodyText("إنت رح تروح — inta raḥ truuḥ — you (m) will go")
        FTBodyText("إنتِ رح تروحي — inti raḥ truuḥi — you (f) will go")
        FTBodyText("هو رح يروح — huwwe raḥ yruuḥ — he will go")
        FTBodyText("هي رح تروح — hiyye raḥ truuḥ — she will go")
        FTBodyText("إحنا رح نروح — iḥna raḥ nruuḥ — we will go")
        FTBodyText("إنتو رح تروحو — intu raḥ truuḥu — you (pl) will go")
        FTBodyText("هم رح يروحو — humme raḥ yruuḥu — they will go")

        FTSectionHeader("Second Example — أكل (eat)")
        FTBodyText("Imperfect of eat: آكل / تاكل / ياكل (aakul / taakul / yaakul)")
        Spacer(modifier = Modifier.height(8.dp))
        FTBodyText("أنا رح آكل — ana raḥ aakul — I will eat")
        FTBodyText("إنت رح تاكل — inta raḥ taakul — you (m) will eat")
        FTBodyText("إنتِ رح تاكلي — inti raḥ taakulii — you (f) will eat")
        FTBodyText("هو رح ياكل — huwwe raḥ yaakul — he will eat")
        FTBodyText("هي رح تاكل — hiyye raḥ taakul — she will eat")
        FTBodyText("إحنا رح ناكل — iḥna raḥ naakul — we will eat")
        FTBodyText("إنتو رح تاكلو — intu raḥ taakuluu — you (pl) will eat")
        FTBodyText("هم رح ياكلو — humme raḥ yaakuluu — they will eat")

        FTSectionHeader("Third Example — كتب (write)")
        FTBodyText("Imperfect of write: أكتب / تكتب / يكتب (aktub / taktub / yaktub)")
        Spacer(modifier = Modifier.height(8.dp))
        FTBodyText("أنا رح أكتب — ana raḥ aktub — I will write")
        FTBodyText("إنت رح تكتب — inta raḥ taktub — you (m) will write")
        FTBodyText("إنتِ رح تكتبي — inti raḥ taktubi — you (f) will write")
        FTBodyText("هو رح يكتب — huwwe raḥ yaktub — he will write")
        FTBodyText("هي رح تكتب — hiyye raḥ taktub — she will write")
        FTBodyText("إحنا رح نكتب — iḥna raḥ naktub — we will write")
        FTBodyText("إنتو رح تكتبو — intu raḥ taktubu — you (pl) will write")
        FTBodyText("هم رح يكتبو — humme raḥ yaktubu — they will write")

        FTSectionHeader("Negating the Future")
        FTBodyText("Use مش رح (mish raḥ) + imperfect. Do NOT use the ما...ش circumfix for future negation.")
        Spacer(modifier = Modifier.height(4.dp))
        FTBodyText("• مش رح أروح — mish raḥ aruuḥ — I will not go")
        FTBodyText("• مش رح تاكل — mish raḥ taakul — you (m) will not eat")
        FTBodyText("• مش رح يكتب — mish raḥ yaktub — he will not write")
        FTBodyText("• مش رح نرجع — mish raḥ nirja' — we will not return")
        FTBodyText("• مش رح يجو — mish raḥ yijuu — they will not come")

        FTSectionHeader("بدّـ — Intentional Future (\"Going to\")")
        FTBodyText("بدّ (badd = want / intend) + possessive suffixes expresses a strong intention or desire — closer to \"I'm going to\" than a neutral future fact.")
        Spacer(modifier = Modifier.height(8.dp))
        FTBodyText("بدّي (baddi) — I want to / I'm going to")
        FTBodyText("بدّك (baddak) — you (m) want to")
        FTBodyText("بدّك (baddik) — you (f) want to")
        FTBodyText("بدّه (baddo) — he wants to / he's going to")
        FTBodyText("بدّها (baddha) — she wants to")
        FTBodyText("بدّنا (baddna) — we want to")
        FTBodyText("بدّكم (baddkum) — you (pl) want to")
        FTBodyText("بدّهم (baddhum) — they want to")
        Spacer(modifier = Modifier.height(8.dp))
        FTBodyText("Examples:")
        FTBodyText("• بدّي أنام — baddi anaam — I want to sleep / I'm going to sleep")
        FTBodyText("• بدّه يسافر — baddo ysaafir — he wants to travel")
        FTBodyText("• بدّها تشتري سيارة — baddha tishtiri sayyaara — she wants to buy a car")
        FTBodyText("• بدّنا نروح على البحر — baddna nruuḥ 'al-baḥar — we want to go to the beach")
        FTBodyText("• ما بدّيش أروح — ma baddish aruuḥ — I don't want to go")

        FTSectionHeader("رح vs بدّـ — What's the Difference?")
        FTBodyText("رح = neutral future: رح أروح — I will go (it will happen)")
        FTBodyText("بدّي = desire-driven: بدّي أروح — I want to go / I intend to go")
        Spacer(modifier = Modifier.height(8.dp))
        FTBodyText("In everyday Palestinian speech, both are used interchangeably for the future. بدّي can sometimes feel more personal or committed.")

        FTSectionHeader("More Examples in Context")
        FTBodyText("• بكرا رح نروح على البحر — bukra raḥ nruuḥ 'al-baḥar — tomorrow we'll go to the beach")
        FTBodyText("• هو مش رح يجي — huwwe mish raḥ yiji — he's not coming")
        FTBodyText("• إيمتى رح ترجع؟ — emta raḥ tirja'? — when will you come back?")
        FTBodyText("• رح أكلمك بعدين — raḥ akallimak ba'dein — I'll call you later")
        FTBodyText("• هم رح يوصلو بعد شوية — humme raḥ yiuṣluu ba'd shwayy — they'll arrive in a bit")
        FTBodyText("• بدّي أقلك شي — baddi a'ullak shi — I want to tell you something")
        FTBodyText("• رح تكون منيح، لا تخاف — raḥ tkuun mniḥ, la tkhaaf — you'll be fine, don't worry")

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun FTSectionHeader(text: String) {
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
private fun FTBodyText(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(vertical = 2.dp)
    )
}
