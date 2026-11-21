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
fun CommonExpressionsScreen() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        CESectionHeader("Everyday Words")
        CEBodyText("ماشي — mashi — Sure / Okay / Alright")
        CEBodyText("تمام — tamaam — Perfect / Okay / Great  (also \"I'm fine\" — see Politeness and Greetings)")
        CEBodyText("أكيد — akid — Of course / Sure / Definitely")

        CESectionHeader("Little Words — Connecting")
        CEBodyText("و — w- / wa — and  (attached to the next word: ana w-inta = me and you)")
        CEBodyText("أو — aw — or  (in statements: shai aw 2ahwe = tea or coffee)")
        CEBodyText("ولّا — willa — or  (in questions: shai willa 2ahwe? = tea or coffee?)")
        CEBodyText("يمكن — yimkin — maybe / perhaps")
        CEBodyText("ممكن — mumkin — maybe / possible  (also \"could you…?\": mumkin…)")
        CEBodyText("بلكي — balki — maybe / in case  (more rural / old-fashioned)")
        CEBodyText("بالمناسبة — bil-munasabe — by the way")
        CEBodyText("يعني — ya3ni — I mean / like / sort of  (extremely common filler word)")

        CESectionHeader("Little Words — Yes, No, Sure")
        CEBodyText("آه — aah — yes  (the everyday yes)")
        CEBodyText("أيوا — aywa — yes  (a bit more emphatic)")
        CEBodyText("نعم — na3am — yes  (formal; in speech often means \"pardon?\")")
        CEBodyText("لأ — la2 — no  (as an answer)")
        CEBodyText("أكيد — akiid — sure / definitely")
        CEBodyText("طبعاً — tab3an — of course / naturally")

        CESectionHeader("Little Words — \"Not\" With a Verb")
        CEBodyText("**ما** (**ma**) before a verb negates it — it means \"not\", inside a sentence. (لأ la2 is \"no\" as an answer.)")
        CEBodyText("ما بعرف — ma ba3rif — I don't know")
        CEBodyText("ما رحت — ma ru7t — I didn't go")
        CEBodyText("ما بحكي عربي — ma ba7ki 3arabi — I don't speak Arabic")
        CEBodyText("ما بدّي — ma biddi — I don't want")
        Spacer(modifier = Modifier.height(4.dp))
        CEBodyText("Often a -sh is added to the end of the verb for emphasis: ما بعرفش — ma ba3rifsh; ما رحتش — ma ru7tish. Both are normal Palestinian.")
        CEBodyText("مش — mish — not, for everything that isn't a plain verb: mish mni7 (not good), mish hon (not here), mish ra7 aruu7 (I'm not going to go). You'll also hear mish ba3rif. See the Negation screen.")

        CESectionHeader("Reactions and Exclamations")
        CEBodyText("يسلموا — yislamu — Thank you / bless you  (short for yislamu ideik; full forms and reply: see Politeness and Greetings → Everyday Courtesy)")
        CEBodyText("رائع — ra2e3 — Awesome / Wonderful")
        CEBodyText("ممتاز — mumtaz — Excellent / Great")
        CEBodyText("هيك — heik — Like this / that's it / great (Levantine)")
        CEBodyText("هلق! — halla2! — Wow! / Now! (depending on context)")
        CEBodyText("يا إلهي — ya ilahi — Oh my God!")
        CEBodyText("وللا — walla — Really! / I swear! (very common)")
        CEBodyText("مش معقول — mish ma32ul — Unbelievable! / No way!")

        CESectionHeader("Time References")
        CEBodyText("هلّق / هلأ — halla2 / hala2 — now / right now (both spellings and pronunciations are used)")
        CEBodyText("اليوم — il-yom — today")
        CEBodyText("مبارح — mbaari7 — yesterday")
        CEBodyText("بكرا — bukra — tomorrow")

        CESectionHeader("Times of Day")
        CEBodyText("صباح — saba7 — morning")
        CEBodyText("ضهر — duhr — noon / midday")
        CEBodyText("بعد الضهر — ba3d el duhr — afternoon")
        CEBodyText("مسا — masa — evening")
        CEBodyText("ليل — leil — night")
        CEBodyText("نص الليل — nuss el leil — midnight")
        CEBodyText("نص النهار — nuss el nahar — midday / noon")

        CESectionHeader("Meals")
        CEBodyText("فطور — ftuur — breakfast")
        CEBodyText("غدا — ghada — lunch  (the main meal of the day)")
        CEBodyText("عشا — 3asha — dinner / supper")
        CEBodyText("بدنا نفطر / نتغدى / نتعشى — bidna niftar / nitghadda / nit3ashsha — we want to have breakfast / lunch / dinner")

        CESectionHeader("Places — Inside and Outside")
        CEBodyText("برّا — barra — outside")
        CEBodyText("جوّا — juwwa — inside")
        CEBodyText("هو برّا — huwwe barra — he's outside")
        CEBodyText("برّا البلاد — barra il-blaad — abroad (literally: outside the country)")

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun CESectionHeader(text: String) {
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
private fun CEBodyText(text: String) {
    Text(
        text = richText(if (text.startsWith(" ")) boldTermLine(text) else boldHeadwordLine(text)),
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(vertical = 2.dp)
    )
}
