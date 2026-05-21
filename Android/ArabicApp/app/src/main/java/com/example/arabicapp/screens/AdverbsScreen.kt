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
fun AdverbsScreen() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        ADVSectionHeader("Adverbs in Palestinian Arabic — الظروف")
        ADVBodyText("Most adverbs in Palestinian Arabic are standalone words that never change form — no conjugation, no gender agreement. Some are formed by placing بـ (bi- = with) before a noun, meaning \"with ___ness\". Adjectives are sometimes used directly as adverbs with no modification.")

        ADVSectionHeader("Time Adverbs")
        ADVBodyText("هلق / هلأ (hala') — now / right now")
        ADVBodyText("تو (taww) — just now / this very moment")
        ADVBodyText("لسا (lissa) — still / yet / just (something that just happened)")
        ADVBodyText("بكرا (bukra) — tomorrow")
        ADVBodyText("مبارح (mbaariḥ) — yesterday")
        ADVBodyText("بعدين (ba'dein) — later / then / afterwards")
        ADVBodyText("قبل ('abl) — before / earlier / ago")
        ADVBodyText("دايمان (dayman) — always")
        ADVBodyText("أبداً (abadan) — never / not at all")
        ADVBodyText("أحياناً (aḥyaanan) — sometimes")
        ADVBodyText("نادراً (naadiran) — rarely")
        ADVBodyText("أخيراً (akhiiran) — finally")
        ADVBodyText("فجأة (faj'atan) — suddenly")
        Spacer(modifier = Modifier.height(8.dp))
        ADVBodyText("Examples:")
        ADVBodyText("• هلق أنا مشغول — hala' ana mashghuul — I'm busy right now")
        ADVBodyText("• لسا ما وصلش — lissa ma wiṣilsh — he still hasn't arrived")
        ADVBodyText("• تو وصلت — taww wiṣilit — she just arrived (just this second)")
        ADVBodyText("• قبل كنا هون — 'abl kinna hawn — we used to be here / we were here before")
        ADVBodyText("• دايمان بتتأخر — dayman bitt'akhkhar — you're always late")
        ADVBodyText("• أحياناً بروح، أحياناً ما بروحش — aḥyaanan bruuḥ, aḥyaanan ma bruuḥsh — sometimes I go, sometimes I don't")
        ADVBodyText("• فجأة وقف — faj'atan wi'if — suddenly he stopped")

        ADVSectionHeader("Place Adverbs")
        ADVBodyText("هون (hawn) — here")
        ADVBodyText("هناك (hunaak) — there")
        ADVBodyText("فوق (foo') — up / above / upstairs")
        ADVBodyText("تحت (taḥt) — down / below / downstairs")
        ADVBodyText("برّا (barra) — outside")
        ADVBodyText("جوّا (jawwa) — inside")
        ADVBodyText("جنب (janb) — next to / beside")
        Spacer(modifier = Modifier.height(8.dp))
        ADVBodyText("Examples:")
        ADVBodyText("• تعال هون — ta'aal hawn — come here")
        ADVBodyText("• هو هناك — huwwe hunaak — he's over there")
        ADVBodyText("• الكتاب فوق الطاولة — il-ktaab foo' iṭ-ṭaawle — the book is on the table")
        ADVBodyText("• إجلس جنبي — ijlis janbi — sit next to me")
        ADVBodyText("• روح برّا — ruuḥ barra — go outside")
        ADVBodyText("• هي جوّا — hiyye jawwa — she's inside")

        ADVSectionHeader("Manner Adverbs — بـ + Noun")
        ADVBodyText("Adding بـ (b-) to a noun creates a manner adverb meaning \"with ___\" or \"in a ___ way\":")
        Spacer(modifier = Modifier.height(4.dp))
        ADVBodyText("بسرعة (bisur'a) — quickly (with speed)")
        ADVBodyText("بهدوء (bihudu') — calmly / slowly (with calmness)")
        ADVBodyText("بالضبط (bil-ḍabṭ) — exactly / precisely")
        ADVBodyText("بالعكس (bil-'aks) — on the contrary / the opposite")
        ADVBodyText("بجد (bijad) — seriously / for real")
        ADVBodyText("بسهولة (bishuule) — easily (with ease)")
        ADVBodyText("بصراحة (biṣaraaḥa) — honestly / frankly (with frankness)")
        Spacer(modifier = Modifier.height(8.dp))
        ADVBodyText("Examples:")
        ADVBodyText("• روح بسرعة — ruuḥ bisur'a — go quickly")
        ADVBodyText("• هو بيحكي بهدوء — huwwe biḥki bihudu' — he speaks calmly")
        ADVBodyText("• بالضبط هيك — bil-ḍabṭ heik — exactly like that")
        ADVBodyText("• بجد؟ — bijad? — seriously? / for real?")
        ADVBodyText("• بصراحة ما بعرف — biṣaraaḥa ma ba'refsh — honestly, I don't know")

        ADVSectionHeader("Degree and Quantity Adverbs")
        ADVBodyText("كتير (ktir) — a lot / very / very much")
        ADVBodyText("شوية (shwayy) — a little / a bit")
        ADVBodyText("زيادة (ziyaade) — too much / extra")
        ADVBodyText("بس (bass) — just / only")
        ADVBodyText("كمان (kamaan) — also / too / as well")
        ADVBodyText("على الأقل ('ala il-a'all) — at least")
        ADVBodyText("تقريباً (ta'riiban) — approximately / about")
        Spacer(modifier = Modifier.height(8.dp))
        ADVBodyText("Examples:")
        ADVBodyText("• أنا كتير تعبان — ana ktir ta'baan — I'm very tired")
        ADVBodyText("• شوية بس — shwayy bass — just a little")
        ADVBodyText("• كمان أنا بدّي — kamaan ana baddi — I want one too")
        ADVBodyText("• على الأقل قلّي — 'ala il-a'all 'illi — at least tell me")
        ADVBodyText("• في تقريباً عشرة ناس — fii ta'riiban 'ashara naas — there are about ten people")
        ADVBodyText("• لا تاكل زيادة — la taakul ziyaade — don't eat too much")

        ADVSectionHeader("Certainty and Possibility Adverbs")
        ADVBodyText("طبعاً (ṭab'an) — of course / naturally")
        ADVBodyText("أكيد (akiid) — definitely / for sure")
        ADVBodyText("ممكن (mumkin) — maybe / possibly / perhaps")
        ADVBodyText("ربما (rubbama) — maybe / perhaps (slightly more formal)")
        ADVBodyText("صح (ṣaḥ) — right / correctly / true")
        ADVBodyText("غلط (ghalaṭ) — wrong / incorrectly")
        Spacer(modifier = Modifier.height(8.dp))
        ADVBodyText("Examples:")
        ADVBodyText("• طبعاً بعرف — ṭab'an ba'ref — of course I know")
        ADVBodyText("• أكيد رح أجي — akiid raḥ aji — I'll definitely come")
        ADVBodyText("• ممكن بكرا — mumkin bukra — maybe tomorrow")
        ADVBodyText("• صح فهمت — ṣaḥ fahimt — you understood correctly")
        ADVBodyText("• غلط حكيت — ghalaṭ ḥakeit — you spoke incorrectly / you said the wrong thing")

        ADVSectionHeader("Other Useful Adverbs")
        ADVBodyText("من جديد (min jadiid) — again / once more")
        ADVBodyText("على طول ('ala ṭuul) — right away / immediately (also: always, in some contexts)")
        ADVBodyText("خصوصاً (khuṣuuṣan) — especially / particularly")
        ADVBodyText("مع بعض (ma' ba'aḍ) — together")
        ADVBodyText("لحاله (laḥaalo) / لحالها (laḥaasha) — alone (masculine / feminine)")
        Spacer(modifier = Modifier.height(8.dp))
        ADVBodyText("Examples:")
        ADVBodyText("• قله من جديد — 'ullu min jadiid — tell him again")
        ADVBodyText("• على طول رح أجي — 'ala ṭuul raḥ aji — I'll come right away")
        ADVBodyText("• خصوصاً إنت — khuṣuuṣan inta — especially you")
        ADVBodyText("• هم رحو مع بعض — humme raaḥu ma' ba'aḍ — they went together")
        ADVBodyText("• هي جالسة لحالها — hiyye jaalse laḥaasha — she's sitting alone")
        ADVBodyText("• أخيراً وصل — akhiiran wiṣil — he finally arrived")

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun ADVSectionHeader(text: String) {
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
private fun ADVBodyText(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(vertical = 2.dp)
    )
}
