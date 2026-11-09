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
fun CommonAdjectivesScreen() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        CASectionHeader("Common Adjectives — صفات شائعة (sifaat shaa2i3a)")
        CABodyText("Masculine form listed first, then feminine (add -a/-e). Palestinian dialect: qaf → glottal stop (').")
        CABodyText("Adjectives follow the noun and agree in gender. See the Adjectives screen for the full grammar rules.")

        CASectionHeader("Size and Dimensions")
        CABodyText("كبير / كبيرة (kbir / kbiire) — big / large")
        CABodyText("صغير / صغيرة (zghir / zghiire) — small / little")
        CABodyText("    Syrian/Jordanian also sghiir / sghiire — same word, with s instead of z")
        CABodyText("طويل / طويلة (tawil / tawiila) — tall / long")
        CABodyText("قصير / قصيرة (2asir / 2asiira) — short")
        CABodyText("عريض / عريضة (3ariid / 3ariida) — wide / broad")
        CABodyText("ضيق / ضيقة (dayye2 / dayyi2a) — narrow / tight")

        CASectionHeader("Age and Condition")
        CABodyText("جديد / جديدة (jdiid / jdiide) — new")
        CABodyText("قديم / قديمة (2adiim / 2adiime) — old (for things, not people)")
        CABodyText("    city speech: 2adiim; rural Palestinian / Druze: qadiim (with a real q) — same word")
        CABodyText("كبير بالسن / كبيرة بالسن (kbir bis-sinn / kbiire bis-sinn) — old (in age, for people)")
        CABodyText("شاب / شابة (shaab / shaabe) — young")

        CASectionHeader("Quality and Character")
        CABodyText("حلو / حلوة (7ilu / 7ilwe) — beautiful / pretty / nice / good / sweet")
        CABodyText("    e.g. el-bint 7ilwe (the girl is pretty) · el-akl 7ilu (the food is good) · shai 7ilu (sweet tea)")
        CABodyText("منيح / منيحة (mni7 / mni7a) — good / nice")
        CABodyText("كويّس / كويّسة (kwayyis / kwayyise) — good / fine / okay")
        CABodyText("ممتاز / ممتازة (mumtaaz / mumtaaze) — excellent")
        CABodyText("سيء / سيئة (siyyi2 / siyyi2a) — bad (emphatic)")
        CABodyText("مهم / مهمة (muhimm / muhimme) — important")
        CABodyText("عادي / عادية (3aadi / 3aadiyye) — normal / ordinary")
        CABodyText("غريب / غريبة (ghariib / ghariibe) — strange / weird / foreign")
        CABodyText("مضحك / مضحكة (mad7ak / mad7ake) — funny")

        CASectionHeader("Colors")
        CABodyText("أبيض / بيضا (abyad / beida) — white")
        CABodyText("أسود / سودا (aswad / sooda) — black")
        CABodyText("أحمر / حمرا (a7mar / 7amra) — red")
        CABodyText("أخضر / خضرا (akhdar / khadra) — green")
        CABodyText("أزرق / زرقا (azra2 / zar2a) — blue  (qaf → glottal stop)")
        CABodyText("أصفر / صفرا (asfar / safra) — yellow")
        CABodyText("بني / بنية (bunni / bunniyye) — brown")
        CABodyText("رمادي / رمادية (ramaadi / ramaadiyye) — grey")
        CABodyText("وردي / وردية (wardi / wardiyye) — pink")
        CABodyText("برتقالي / برتقالية (burt2aali / burt2aaliyye) — orange")

        CASectionHeader("Temperature and Physical Properties")
        CABodyText("حار / حارة (7aarr / 7aarre) — hot")
        CABodyText("بارد / باردة (baarid / baarida) — cold")
        CABodyText("دافي / دافية (daafi / daafiye) — warm")
        CABodyText("ثقيل / ثقيلة (t2iil / t2iila) — heavy  (ث → t in Palestinian)")
        CABodyText("خفيف / خفيفة (khfiif / khfiife) — light (weight)")
        CABodyText("ناعم / ناعمة (naa3im / naa3ima) — soft / smooth")
        CABodyText("خشن / خشنة (khishin / khishne) — rough / coarse")

        CASectionHeader("Speed and Ability")
        CABodyText("سريع / سريعة (srii3 / srii3a) — fast / quick  (qaf → glottal stop)")
        CABodyText("بطيء / بطيئة (btii2 / btii2a) — slow")
        CABodyText("قوي / قوية (2awi / 2awiyye) — strong")
        CABodyText("ضعيف / ضعيفة (da3iif / da3iife) — weak")

        CASectionHeader("Difficulty and Ease")
        CABodyText("سهل / سهلة (sahl / sahle) — easy")
        CABodyText("صعب / صعبة (sa3b / sa3be) — difficult / hard")

        CASectionHeader("Distance")
        CABodyText("قريب / قريبة (2ariib / 2ariibe) — near / close  (rural / Druze: qariib)")
        CABodyText("بعيد / بعيدة (b3iid / b3iide) — far")
        CABodyText("    e.g. el-beit 2ariib (the house is close) · el-mataar b3iid (the airport is far)")

        CASectionHeader("Price, Wealth and Cleanliness")
        CABodyText("غالي / غالية (ghaali / ghaaliye) — expensive")
        CABodyText("رخيص / رخيصة (rkhis / rkhisa) — cheap / inexpensive")
        CABodyText("غني / غنية (ghani / ghaniyye) — rich")
        CABodyText("فقير / فقيرة (fa2iir / fa2iire) — poor  (rural / Druze: faqiir)")
        CABodyText("معتّر / معتّرة (m3attar / m3attara) — poor / broke / \"poor thing\"  (colloquial, Lebanese/Syrian)")
        CABodyText("نظيف / نظيفة (nadiif / nadiife) — clean")
        CABodyText("وسخ / وسخة (wisikh / wiskha) — dirty")

        CASectionHeader("Feelings and Emotions")
        CABodyText("تعبان / تعبانة (ta3baan / ta3baane) — tired")
        CABodyText("مريض / مريضة (mariid / mariida) — sick / ill")
        CABodyText("فرحان / فرحانة (far7aan / far7aane) — happy / joyful")
        CABodyText("مبسوط / مبسوطة (mabsuut / mabsuuta) — happy / content / pleased")
        CABodyText("زعلان / زعلانة (za3laan / za3laane) — sad / upset")
        CABodyText("خايف / خايفة (khaayif / khaayfe) — afraid / scared")
        CABodyText("متوتر / متوترة (mitawwatir / mitawwatire) — anxious / tense / nervous")
        CABodyText("زاهق / زاهقة (zaahi2 / zaahi2a) — bored  (qaf → glottal stop)")
        CABodyText("مسبسط / مسبسطة — very relaxed / unbothered (colloquial, humorous)")

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun CASectionHeader(text: String) {
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
private fun CABodyText(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(vertical = 2.dp)
    )
}
