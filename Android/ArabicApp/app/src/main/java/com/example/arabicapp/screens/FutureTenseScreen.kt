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
        FTSectionHeader("Talking About the Future — المستقبل (il-mustaqbal)")
        FTBodyText("Palestinian Arabic has no separate future tense conjugation. Instead, the future is expressed by placing رح (ra7) before the imperfect (present-form) verb. رح is a fixed particle — it never changes.")
        Spacer(modifier = Modifier.height(8.dp))
        FTBodyText("Structure: رح (ra7) + imperfect verb")
        FTBodyText("Example: رح يروح (ra7 yruu7) = he will go")

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
        FTBodyText("Imperfect of go: أروح / تروح / يروح (aruu7 / truu7 / yruu7)")
        Spacer(modifier = Modifier.height(8.dp))
        FTBodyText("أنا رح أروح — ana ra7 aruu7 — I will go")
        FTBodyText("إنت رح تروح — inta ra7 truu7 — you (m) will go")
        FTBodyText("إنتِ رح تروحي — inti ra7 truu7i — you (f) will go")
        FTBodyText("هو رح يروح — huwwe ra7 yruu7 — he will go")
        FTBodyText("هي رح تروح — hiyye ra7 truu7 — she will go")
        FTBodyText("إحنا رح نروح — i7na ra7 nruu7 — we will go")
        FTBodyText("إنتو رح تروحو — intu ra7 truu7u — you (pl) will go")
        FTBodyText("هم رح يروحو — humme ra7 yruu7u — they will go")

        FTSectionHeader("Second Example — أكل (eat)")
        FTBodyText("Imperfect of eat: آكل / تاكل / ياكل (aakul / taakul / yaakul)")
        Spacer(modifier = Modifier.height(8.dp))
        FTBodyText("أنا رح آكل — ana ra7 aakul — I will eat")
        FTBodyText("إنت رح تاكل — inta ra7 taakul — you (m) will eat")
        FTBodyText("إنتِ رح تاكلي — inti ra7 taakulii — you (f) will eat")
        FTBodyText("هو رح ياكل — huwwe ra7 yaakul — he will eat")
        FTBodyText("هي رح تاكل — hiyye ra7 taakul — she will eat")
        FTBodyText("إحنا رح ناكل — i7na ra7 naakul — we will eat")
        FTBodyText("إنتو رح تاكلو — intu ra7 taakuluu — you (pl) will eat")
        FTBodyText("هم رح ياكلو — humme ra7 yaakuluu — they will eat")

        FTSectionHeader("Third Example — كتب (write)")
        FTBodyText("Imperfect of write: أكتب / تكتب / يكتب (aktub / taktub / yaktub)")
        Spacer(modifier = Modifier.height(8.dp))
        FTBodyText("أنا رح أكتب — ana ra7 aktub — I will write")
        FTBodyText("إنت رح تكتب — inta ra7 taktub — you (m) will write")
        FTBodyText("إنتِ رح تكتبي — inti ra7 taktubi — you (f) will write")
        FTBodyText("هو رح يكتب — huwwe ra7 yaktub — he will write")
        FTBodyText("هي رح تكتب — hiyye ra7 taktub — she will write")
        FTBodyText("إحنا رح نكتب — i7na ra7 naktub — we will write")
        FTBodyText("إنتو رح تكتبو — intu ra7 taktubu — you (pl) will write")
        FTBodyText("هم رح يكتبو — humme ra7 yaktubu — they will write")

        FTSectionHeader("Negating the Future")
        FTBodyText("Use مش رح (mish ra7) + imperfect. Do NOT use the ما...ش circumfix for future negation.")
        Spacer(modifier = Modifier.height(4.dp))
        FTBodyText("• مش رح أروح — mish ra7 aruu7 — I will not go")
        FTBodyText("• مش رح تاكل — mish ra7 taakul — you (m) will not eat")
        FTBodyText("• مش رح يكتب — mish ra7 yaktub — he will not write")
        FTBodyText("• مش رح نرجع — mish ra7 nirja3 — we will not return")
        FTBodyText("• مش رح يجو — mish ra7 yijuu — they will not come")

        FTSectionHeader("بدّـ (bidd-) — Intentional Future (\"Going to\")")
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
        FTBodyText("• بدّنا نروح على البحر — baddna nruu7 3al-ba7ar — we want to go to the beach")
        FTBodyText("• ما بدّيش أروح — ma baddish aruu7 — I don't want to go")

        FTSectionHeader("رح (ra7) vs بدّـ (bidd-) — What's the Difference?")
        FTBodyText("رح = neutral future: رح أروح — I will go (it will happen)")
        FTBodyText("بدّي = desire-driven: بدّي أروح — I want to go / I intend to go")
        Spacer(modifier = Modifier.height(8.dp))
        FTBodyText("In everyday Palestinian speech, both are used interchangeably for the future. بدّي (biddi) can sometimes feel more personal or committed.")

        FTSectionHeader("More Examples in Context")
        FTBodyText("• بكرا رح نروح على البحر — bukra ra7 nruu7 3al-ba7ar — tomorrow we'll go to the beach")
        FTBodyText("• هو مش رح يجي — huwwe mish ra7 yiji — he's not coming")
        FTBodyText("• إيمتى رح ترجع؟ — emta ra7 tirja3? — when will you come back?")
        FTBodyText("• رح أكلمك بعدين — ra7 akallimak ba3dein — I'll call you later")
        FTBodyText("• هم رح يوصلو بعد شوية — humme ra7 yiusluu ba3d shwayy — they'll arrive in a bit")
        FTBodyText("• بدّي أقلك شي — baddi a2ullak shi — I want to tell you something")
        FTBodyText("• رح تكون منيح، لا تخاف — ra7 tkuun mni7, la tkhaaf — you'll be fine, don't worry")

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
