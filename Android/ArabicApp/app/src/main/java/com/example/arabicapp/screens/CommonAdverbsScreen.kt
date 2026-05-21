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
        CADSectionHeader("Common Adverbs — ظروف شائعة")
        CADBodyText("Most adverbs in Palestinian Arabic never change form — no gender, no conjugation. They are simply placed near the word or phrase they modify.")

        CADSectionHeader("Time")
        CADBodyText("هلق / هلأ (hala') — now / right now")
        CADBodyText("تو (taww) — just now / this very moment")
        CADBodyText("لسا (lissa) — still / yet / just (recently happened)")
        CADBodyText("اليوم (il-yoom) — today")
        CADBodyText("بكرا (bukra) — tomorrow")
        CADBodyText("مبارح (mbaariḥ) — yesterday")
        CADBodyText("بعدين (ba'dein) — later / then / afterwards")
        CADBodyText("قبل ('abl) — before / earlier / ago")
        CADBodyText("دايمان (dayman) — always")
        CADBodyText("أبداً (abadan) — never / not at all")
        CADBodyText("أحياناً (aḥyaanan) — sometimes")
        CADBodyText("نادراً (naadiran) — rarely / seldom")
        CADBodyText("عادةً ('aadatan) — usually / normally")
        CADBodyText("في الغالب (fil-ghaalib) — mostly / generally")
        CADBodyText("من زمان (min zamaan) — a long time ago / for a long time")
        CADBodyText("كمان شوي (kamaan shwayy) — in a little while / soon")
        CADBodyText("في الأول (fil-awwal) — at first / firstly")
        CADBodyText("أخيراً (akhiiran) — finally / at last")
        CADBodyText("فجأة (faj'atan) — suddenly")

        CADSectionHeader("Place")
        CADBodyText("هون (hawn) — here")
        CADBodyText("هناك (hunaak) — there")
        CADBodyText("فوق (foo') — up / above / upstairs")
        CADBodyText("تحت (taḥt) — down / below / downstairs")
        CADBodyText("برّا (barra) — outside")
        CADBodyText("جوّا (jawwa) — inside")
        CADBodyText("جنب (janb) — next to / beside")
        CADBodyText("على طول ('ala ṭuul) — straight ahead (direction) / immediately (time) / always (habit)")

        CADSectionHeader("Degree and Quantity")
        CADBodyText("كتير (ktir) — a lot / very / very much")
        CADBodyText("شوية (shwayy) — a little / a bit")
        CADBodyText("زيادة (ziyaade) — too much / extra")
        CADBodyText("تقريباً (ta'riiban) — approximately / about")
        CADBodyText("على الأقل ('ala il-a'all) — at least")
        CADBodyText("على الأكثر ('ala il-aktar) — at most")
        CADBodyText("بس (bass) — just / only")
        CADBodyText("كمان (kamaan) — also / too / as well")
        CADBodyText("خصوصاً (khuṣuuṣan) — especially / particularly")
        CADBodyText("مع بعض (ma' ba'aḍ) — together")
        CADBodyText("لحاله (laḥaalo) / لحالها (laḥaasha) — alone (masculine / feminine)")

        CADSectionHeader("Manner")
        CADBodyText("بسرعة (bisur'a) — quickly / fast")
        CADBodyText("بهدوء (bihudu') — calmly / slowly / gently")
        CADBodyText("بالضبط (bil-ḍabṭ) — exactly / precisely")
        CADBodyText("بالعكس (bil-'aks) — on the contrary / the opposite")
        CADBodyText("بجد (bijad) — seriously / for real")
        CADBodyText("بصراحة (biṣaraaḥa) — honestly / frankly")
        CADBodyText("بسهولة (bishuule) — easily")
        CADBodyText("مباشرةً (mubaashara) — directly / right away")
        CADBodyText("من جديد (min jadiid) — again / once more")

        CADSectionHeader("Certainty and Possibility")
        CADBodyText("طبعاً (ṭab'an) — of course / naturally")
        CADBodyText("أكيد (akiid) — definitely / for sure / certainly")
        CADBodyText("بالتأكيد (bit-ta'kiid) — definitely / absolutely")
        CADBodyText("ممكن (mumkin) — maybe / possibly / perhaps")
        CADBodyText("ربما (rubbama) — maybe / perhaps (slightly more formal)")
        CADBodyText("يمكن (yumkin) — maybe / it's possible")
        CADBodyText("ولا بالأحلام (wala bil-aḥlaam) — not even in dreams / absolutely not")

        CADSectionHeader("Agreement and Correctness")
        CADBodyText("صح (ṣaḥ) — right / correctly / true")
        CADBodyText("غلط (ghalaṭ) — wrong / incorrectly")
        CADBodyText("زبط (zabaṭ) — right / correct / it worked  (colloquial)")
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
