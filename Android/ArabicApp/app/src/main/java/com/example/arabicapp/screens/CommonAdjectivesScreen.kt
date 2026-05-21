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
        CASectionHeader("Common Adjectives — صفات شائعة")
        CABodyText("Masculine form listed first, then feminine (add -a/-e). Palestinian dialect: qaf → glottal stop (').")
        CABodyText("Adjectives follow the noun and agree in gender. See the Adjectives screen for the full grammar rules.")

        CASectionHeader("Size and Dimensions")
        CABodyText("كبير / كبيرة (kbir / kbiire) — big / large")
        CABodyText("صغير / صغيرة (zghir / zghiire) — small / little")
        CABodyText("طويل / طويلة (ṭawil / ṭawiila) — tall / long")
        CABodyText("قصير / قصيرة ('aṣir / 'aṣiira) — short")
        CABodyText("عريض / عريضة ('ariiḍ / 'ariiḍa) — wide / broad")
        CABodyText("ضيق / ضيقة (ḍayye' / ḍayyi'a) — narrow / tight")

        CASectionHeader("Age and Condition")
        CABodyText("جديد / جديدة (jdiid / jdiide) — new")
        CABodyText("قديم / قديمة ('adiim / 'adiime) — old (for things, not people)")
        CABodyText("كبير بالسن / كبيرة بالسن (kbir bis-sinn / kbiire bis-sinn) — old (in age, for people)")
        CABodyText("شاب / شابة (shaab / shaabe) — young")

        CASectionHeader("Quality and Character")
        CABodyText("منيح / منيحة (mniḥ / mniḥa) — good / nice")
        CABodyText("كويّس / كويّسة (kwayyis / kwayyise) — good / fine / okay")
        CABodyText("ممتاز / ممتازة (mumtaaz / mumtaaze) — excellent")
        CABodyText("سيء / سيئة (siyyi' / siyyi'a) — bad (emphatic)")
        CABodyText("مهم / مهمة (muhimm / muhimme) — important")
        CABodyText("عادي / عادية ('aadi / 'aadiyye) — normal / ordinary")
        CABodyText("غريب / غريبة (ghariib / ghariibe) — strange / weird / foreign")
        CABodyText("مضحك / مضحكة (maḍḥak / maḍḥake) — funny")

        CASectionHeader("Colors")
        CABodyText("أبيض / بيضا (abyaḍ / beiḍa) — white")
        CABodyText("أسود / سودا (aswad / sooda) — black")
        CABodyText("أحمر / حمرا (aḥmar / ḥamra) — red")
        CABodyText("أخضر / خضرا (akhḍar / khad'ra) — green")
        CABodyText("أزرق / زرقا (azra' / zar'a) — blue  (qaf → glottal stop)")
        CABodyText("أصفر / صفرا (aṣfar / ṣafra) — yellow")
        CABodyText("بني / بنية (bunni / bunniyye) — brown")
        CABodyText("رمادي / رمادية (ramaadi / ramaadiyye) — grey")
        CABodyText("وردي / وردية (wardi / wardiyye) — pink")
        CABodyText("برتقالي / برتقالية (burt'aali / burt'aaliyye) — orange")

        CASectionHeader("Temperature and Physical Properties")
        CABodyText("حار / حارة (ḥaarr / ḥaarre) — hot")
        CABodyText("بارد / باردة (baarid / baarida) — cold")
        CABodyText("دافي / دافية (daafi / daafiye) — warm")
        CABodyText("ثقيل / ثقيلة (t'iil / t'iila) — heavy  (ث → t in Palestinian)")
        CABodyText("خفيف / خفيفة (khfiif / khfiife) — light (weight)")
        CABodyText("ناعم / ناعمة (naa'im / naa'ima) — soft / smooth")
        CABodyText("خشن / خشنة (khishin / khishne) — rough / coarse")

        CASectionHeader("Speed and Ability")
        CABodyText("سريع / سريعة (srii' / srii'a) — fast / quick  (qaf → glottal stop)")
        CABodyText("بطيء / بطيئة (bṭii' / bṭii'a) — slow")
        CABodyText("قوي / قوية ('awi / 'awiyye) — strong")
        CABodyText("ضعيف / ضعيفة (ḍa'iif / ḍa'iife) — weak")

        CASectionHeader("Difficulty and Ease")
        CABodyText("سهل / سهلة (sahl / sahle) — easy")
        CABodyText("صعب / صعبة (ṣa'b / ṣa'be) — difficult / hard")

        CASectionHeader("Price and Cleanliness")
        CABodyText("غالي / غالية (ghaali / ghaaliye) — expensive")
        CABodyText("رخيص / رخيصة (rkhiṣ / rkhiṣa) — cheap / inexpensive")
        CABodyText("نظيف / نظيفة (naḍiif / naḍiife) — clean")
        CABodyText("وسخ / وسخة (wisikh / wiskha) — dirty")

        CASectionHeader("Feelings and Emotions")
        CABodyText("تعبان / تعبانة (ta'baan / ta'baane) — tired")
        CABodyText("مريض / مريضة (mariiḍ / mariiḍa) — sick / ill")
        CABodyText("فرحان / فرحانة (farḥaan / farḥaane) — happy / joyful")
        CABodyText("مبسوط / مبسوطة (mabsuuṭ / mabsuuṭa) — happy / content / pleased")
        CABodyText("زعلان / زعلانة (za'laan / za'laane) — sad / upset")
        CABodyText("خايف / خايفة (khaayif / khaayfe) — afraid / scared")
        CABodyText("متوتر / متوترة (mitawwatir / mitawwatire) — anxious / tense / nervous")
        CABodyText("زاهق / زاهقة (zaahi' / zaahi'a) — bored  (qaf → glottal stop)")
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
