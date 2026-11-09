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
fun CommonAdverbsScreen() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        CADSectionHeader("Common Adverbs — ظروف شائعة (zuruuf shaa2i3a)")
        CADBodyText("Most adverbs in Palestinian Arabic never change form — no gender, no conjugation. They are simply placed near the word or phrase they modify.")

        CADSectionHeader("Time")
        CADBodyText("هلق / هلأ (hala2) — now / right now")
        CADBodyText("تو (taww) — just now / this very moment")
        CADBodyText("لسا (lissa) — still / yet / just (recently happened)")
        CADBodyText("اليوم (il-yoom) — today")
        CADBodyText("بكرا (bukra) — tomorrow")
        CADBodyText("مبارح (mbaari7) — yesterday")
        CADBodyText("بعدين (ba3dein) — later / then / afterwards")
        CADBodyText("قبل (2abl) — before / earlier / ago")
        CADBodyText("دايمان (dayman) — always")
        CADBodyText("أبداً (abadan) — never / not at all")
        CADBodyText("أحياناً (a7yaanan) — sometimes")
        CADBodyText("نادراً (naadiran) — rarely / seldom")
        CADBodyText("عادةً (3aadatan) — usually / normally")
        CADBodyText("في الغالب (fil-ghaalib) — mostly / generally")
        CADBodyText("من زمان (min zamaan) — a long time ago / for a long time")
        CADBodyText("كمان شوي (kamaan shwayy) — in a little while / soon")
        CADBodyText("في الأول (fil-awwal) — at first / firstly")
        CADBodyText("أخيراً (akhiiran) — finally / at last")
        CADBodyText("فجأة (faj2atan) — suddenly")

        CADSectionHeader("Place")
        CADBodyText("هون (hawn) — here")
        CADBodyText("هناك (hunaak) — there")
        CADBodyText("فوق (foo2) — up / above / upstairs")
        CADBodyText("تحت (ta7t) — down / below / downstairs")
        CADBodyText("برّا (barra) — outside")
        CADBodyText("جوّا (jawwa) — inside")
        CADBodyText("جنب (janb) — next to / beside")
        CADBodyText("على طول (3ala tuul) — straight ahead (direction) / immediately (time) / always (habit)")

        CADSectionHeader("Degree and Quantity")
        CADBodyText("كتير (ktir) — a lot / very / very much")
        CADBodyText("شوية (shwayy) — a little / a bit")
        CADBodyText("زيادة (ziyaade) — too much / extra")
        CADBodyText("تقريباً (ta2riiban) — approximately / about")
        CADBodyText("على الأقل (3ala il-a2all) — at least")
        CADBodyText("على الأكثر (3ala il-aktar) — at most")
        CADBodyText("بس (bass) — just / only")
        CADBodyText("كمان (kamaan) — also / too / as well")
        CADBodyText("خصوصاً (khusuusan) — especially / particularly")
        CADBodyText("مع بعض (ma3 ba3ad) — together")
        CADBodyText("لحاله (la7aalo) / لحالها (la7aalha) — alone (masculine / feminine)")

        CADSectionHeader("Manner")
        CADBodyText("بسرعة (bisur3a) — quickly / fast")
        CADBodyText("    e.g. suu2 bisur3a — سوق بسرعة — Drive quickly.")
        CADBodyText("شوي شوي (shwayy shwayy) — slowly / little by little")
        CADBodyText("على مهل (3ala mahl) — slowly / take it easy  (Lebanese/Syrian 3a-mahl — عمهل)")
        CADBodyText("    e.g. i7ki shwayy shwayy, min fadlak — احكي شوي شوي، من فضلك — Speak slowly, please.")
        CADBodyText("بهدوء (bihudu2) — calmly / slowly / gently")
        CADBodyText("بالضبط (bil-dabt) — exactly / precisely  (also bizzabt — بالزبط, very common in speech)")
        CADBodyText("    e.g. hada bizzabt elli biddi — هاد بالزبط اللي بدّي — That's exactly what I want.")
        CADBodyText("بأمان (bi-amaan) — safely")
        CADBodyText("    e.g. wsilna bi-amaan — وصلنا بأمان — We arrived safely.")
        CADBodyText("بخطر / بطريقة خطيرة (bi-khatar / b-tarii2a khatiire) — dangerously")
        CADBodyText("    e.g. bisuu2 b-tarii2a khatiire — بسوق بطريقة خطيرة — He drives dangerously.")
        CADBodyText("بالعكس (bil-3aks) — on the contrary / the opposite")
        CADBodyText("بجد (bijad) — seriously / for real")
        CADBodyText("بصراحة (bisaraa7a) — honestly / frankly")
        CADBodyText("بسهولة (bishuule) — easily")
        CADBodyText("مباشرةً (mubaashara) — directly / right away")
        CADBodyText("من جديد (min jadiid) — again / once more")

        CADSectionHeader("How Well — منيح (mnii7)")
        CADBodyText("Note: mnii7 is really an adjective (good / well). In \"ana mnii7\" (I'm well) it describes a person, so it agrees: mnii7 (m) / mnii7a (f) / mnaa7 (pl). After a verb it works as an adverb and doesn't change: bi7ki mnii7 (he speaks well). Lebanese/Syrian say it the same: mni7 / mni7a.")
        CADBodyText("منيح (mnii7) — well")
        CADBodyText("    e.g. ana mnii7 / ana mnii7a — أنا منيح / أنا منيحة — I'm well. (m / f)")
        CADBodyText("    e.g. bti7ki 3arabi mnii7 — بتحكي عربي منيح — You speak Arabic well.")
        CADBodyText("مش منيح (mish mnii7) — not well")
        CADBodyText("    e.g. ana mish mnii7 / mish mnii7a — أنا مش منيح / مش منيحة — I'm not well. (m / f)")
        CADBodyText("مش كتير منيح (mish ktiir mnii7) — not too well / not very well")
        CADBodyText("    e.g. ana mish ktiir mnii7 el-yoom — أنا مش كتير منيح اليوم — I'm not too well today.")
        CADBodyText("مش هالقدّ منيح (mish hal-2add mnii7) — not so well")
        CADBodyText("    e.g. el-akel mish hal-2add mnii7 — الأكل مش هالقدّ منيح — The food is not so good.")

        CADSectionHeader("Modifiers — Very, A Bit, Too, So, Not At All")
        CADBodyText("Modifiers don't change form either. ktiir usually comes after the adjective, but before it is also common.")
        CADBodyText("كتير (ktiir) — very")
        CADBodyText("    e.g. mnii7 ktiir / ktiir mnii7 — منيح كتير / كتير منيح — very good / very well")
        CADBodyText("شوي (shwayy) — a bit / a little")
        CADBodyText("    e.g. ana ta3baan shwayy — أنا تعبان شوي — I'm a bit tired.")
        CADBodyText("كتير / زيادة عن اللزوم (ktiir / zyaade 3an el-lzuum) — too (more than needed)")
        CADBodyText("    e.g. el-2ahwe sukhne ktiir — القهوة سخنة كتير — The coffee is too hot.")
        CADBodyText("    e.g. akalt zyaade 3an el-lzuum — أكلت زيادة عن اللزوم — I ate too much.")
        CADBodyText("هالقدّ (hal-2add) — so / that much  (Lebanese/Syrian also la-hal-daraje — لهالدرجة)")
        CADBodyText("    e.g. leish za3laan hal-2add? — ليش زعلان هالقدّ؟ — Why are you so upset?")
        CADBodyText("أبداً / ولا شي (abadan / wala shi) — not at all  (with a negative)")
        CADBodyText("    e.g. mish ta3baan abadan — مش تعبان أبداً — I'm not tired at all.")
        CADBodyText("    e.g. ma fhimt wala shi — ما فهمت ولا شي — I didn't understand anything at all.")

        CADSectionHeader("Certainty and Possibility")
        CADBodyText("طبعاً (tab3an) — of course / naturally")
        CADBodyText("أكيد (akiid) — definitely / for sure / certainly")
        CADBodyText("بالتأكيد (bit-ta2kiid) — definitely / absolutely")
        CADBodyText("ممكن (mumkin) — maybe / possibly / perhaps")
        CADBodyText("ربما (rubbama) — maybe / perhaps (slightly more formal)")
        CADBodyText("يمكن (yumkin) — maybe / it's possible")
        CADBodyText("ولا بالأحلام (wala bil-a7laam) — not even in dreams / absolutely not")

        CADSectionHeader("Agreement and Correctness")
        CADBodyText("صح (sa7) — right / correctly / true")
        CADBodyText("غلط (ghalat) — wrong / incorrectly")
        CADBodyText("زبط (zabat) — right / correct / it worked  (colloquial)")
        CADBodyText("تمام (tamaam) — fine / okay / perfect")
        CADBodyText("ماشي (maashi) — okay / fine / going well")

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun CADSectionHeader(text: String) {
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
private fun CADBodyText(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(vertical = 2.dp)
    )
}
