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
        ADVSectionHeader("Adverbs in Palestinian Arabic — الظروف (iz-zuruuf)")
        ADVBodyText("Most adverbs in Palestinian Arabic are standalone words that never change form — no conjugation, no gender agreement. Some are formed by placing بـ (bi- = with) before a noun, meaning \"with ___ness\". Adjectives are sometimes used directly as adverbs with no modification.")

        ADVSectionHeader("Time Adverbs")
        ADVBodyText("هلق / هلأ (hala2) — now / right now")
        ADVBodyText("تو (taww) — just now / this very moment")
        ADVBodyText("لسا (lissa) — still / yet / just (something that just happened)")
        ADVBodyText("بكرا (bukra) — tomorrow")
        ADVBodyText("مبارح (mbaari7) — yesterday")
        ADVBodyText("بعدين (ba3dein) — later / then / afterwards")
        ADVBodyText("قبل (2abl) — before / earlier / ago")
        ADVBodyText("دايمان (dayman) — always")
        ADVBodyText("أبداً (abadan) — never / not at all")
        ADVBodyText("أحياناً (a7yaanan) — sometimes")
        ADVBodyText("نادراً (naadiran) — rarely")
        ADVBodyText("أخيراً (akhiiran) — finally")
        ADVBodyText("فجأة (faj2atan) — suddenly")
        Spacer(modifier = Modifier.height(8.dp))
        ADVBodyText("Examples:")
        ADVBodyText("• هلق أنا مشغول — hala2 ana mashghuul — I'm busy right now")
        ADVBodyText("• لسا ما وصلش — lissa ma wisilsh — he still hasn't arrived")
        ADVBodyText("• تو وصلت — taww wisilit — she just arrived (just this second)")
        ADVBodyText("• قبل كنا هون — 2abl kinna hawn — we used to be here / we were here before")
        ADVBodyText("• دايمان بتتأخر — dayman bitt2akhkhar — you're always late")
        ADVBodyText("• أحياناً بروح، أحياناً ما بروحش — a7yaanan bruu7, a7yaanan ma bruu7sh — sometimes I go, sometimes I don't")
        ADVBodyText("• فجأة وقف — faj2atan wi2if — suddenly he stopped")

        ADVSectionHeader("Place Adverbs")
        ADVBodyText("هون (hawn) — here")
        ADVBodyText("هناك (hunaak) — there")
        ADVBodyText("فوق (foo2) — up / above / upstairs")
        ADVBodyText("تحت (ta7t) — down / below / downstairs")
        ADVBodyText("برّا (barra) — outside")
        ADVBodyText("جوّا (jawwa) — inside")
        ADVBodyText("جنب (janb) — next to / beside")
        Spacer(modifier = Modifier.height(8.dp))
        ADVBodyText("Examples:")
        ADVBodyText("• تعال هون — ta3aal hawn — come here")
        ADVBodyText("• هو هناك — huwwe hunaak — he's over there")
        ADVBodyText("• الكتاب فوق الطاولة — il-ktaab foo2 it-taawle — the book is on the table")
        ADVBodyText("• إجلس جنبي — ijlis janbi — sit next to me")
        ADVBodyText("• روح برّا — ruu7 barra — go outside")
        ADVBodyText("• هي جوّا — hiyye jawwa — she's inside")

        ADVSectionHeader("Manner Adverbs — بـ (bi-) + Noun")
        ADVBodyText("Adding بـ (b-) to a noun creates a manner adverb meaning \"with ___\" or \"in a ___ way\":")
        Spacer(modifier = Modifier.height(4.dp))
        ADVBodyText("بسرعة (bisur3a) — quickly (with speed)")
        ADVBodyText("بهدوء (bihudu2) — calmly / slowly (with calmness)")
        ADVBodyText("بالضبط (bil-dabt) — exactly / precisely")
        ADVBodyText("بالعكس (bil-3aks) — on the contrary / the opposite")
        ADVBodyText("بجد (bijad) — seriously / for real")
        ADVBodyText("بسهولة (bishuule) — easily (with ease)")
        ADVBodyText("بصراحة (bisaraa7a) — honestly / frankly (with frankness)")
        Spacer(modifier = Modifier.height(8.dp))
        ADVBodyText("Examples:")
        ADVBodyText("• روح بسرعة — ruu7 bisur3a — go quickly")
        ADVBodyText("• هو بيحكي بهدوء — huwwe bi7ki bihudu2 — he speaks calmly")
        ADVBodyText("• بالضبط هيك — bil-dabt heik — exactly like that")
        ADVBodyText("• بجد؟ — bijad? — seriously? / for real?")
        ADVBodyText("• بصراحة ما بعرف — bisaraa7a ma ba3refsh — honestly, I don't know")

        ADVSectionHeader("Degree and Quantity Adverbs")
        ADVBodyText("كتير (ktir) — a lot / very / very much")
        ADVBodyText("شوية (shwayy) — a little / a bit")
        ADVBodyText("زيادة (ziyaade) — too much / extra")
        ADVBodyText("بس (bass) — just / only")
        ADVBodyText("كمان (kamaan) — also / too / as well")
        ADVBodyText("على الأقل (3ala il-a2all) — at least")
        ADVBodyText("تقريباً (ta2riiban) — approximately / about")
        Spacer(modifier = Modifier.height(8.dp))
        ADVBodyText("Examples:")
        ADVBodyText("• أنا كتير تعبان — ana ktir ta3baan — I'm very tired")
        ADVBodyText("• شوية بس — shwayy bass — just a little")
        ADVBodyText("• كمان أنا بدّي — kamaan ana baddi — I want one too")
        ADVBodyText("• على الأقل قلّي — 3ala il-a2all 3illi — at least tell me")
        ADVBodyText("• في تقريباً عشرة ناس — fii ta2riiban 3ashara naas — there are about ten people")
        ADVBodyText("• لا تاكل زيادة — la taakul ziyaade — don't eat too much")

        ADVSectionHeader("Certainty and Possibility Adverbs")
        ADVBodyText("طبعاً (tab3an) — of course / naturally")
        ADVBodyText("أكيد (akiid) — definitely / for sure")
        ADVBodyText("ممكن (mumkin) — maybe / possibly / perhaps")
        ADVBodyText("ربما (rubbama) — maybe / perhaps (slightly more formal)")
        ADVBodyText("صح (sa7) — right / correctly / true")
        ADVBodyText("غلط (ghalat) — wrong / incorrectly")
        Spacer(modifier = Modifier.height(8.dp))
        ADVBodyText("Examples:")
        ADVBodyText("• طبعاً بعرف — tab3an ba3ref — of course I know")
        ADVBodyText("• أكيد رح أجي — akiid ra7 aji — I'll definitely come")
        ADVBodyText("• ممكن بكرا — mumkin bukra — maybe tomorrow")
        ADVBodyText("• صح فهمت — sa7 fahimt — you understood correctly")
        ADVBodyText("• غلط حكيت — ghalat 7akeit — you spoke incorrectly / you said the wrong thing")

        ADVSectionHeader("Other Useful Adverbs")
        ADVBodyText("من جديد (min jadiid) — again / once more")
        ADVBodyText("على طول (3ala tuul) — right away / immediately (also: always, in some contexts)")
        ADVBodyText("خصوصاً (khusuusan) — especially / particularly")
        ADVBodyText("مع بعض (ma3 ba3ad) — together")
        ADVBodyText("لحاله (la7aalo) / لحالها (la7aalha) — alone (masculine / feminine)")
        Spacer(modifier = Modifier.height(8.dp))
        ADVBodyText("Examples:")
        ADVBodyText("• قله من جديد — 2ullu min jadiid — tell him again")
        ADVBodyText("• على طول رح أجي — 3ala tuul ra7 aji — I'll come right away")
        ADVBodyText("• خصوصاً إنت — khusuusan inta — especially you")
        ADVBodyText("• هم رحو مع بعض — humme raa7u ma3 ba3ad — they went together")
        ADVBodyText("• هي جالسة لحالها — hiyye jaalse la7aalha — she's sitting alone")
        ADVBodyText("• أخيراً وصل — akhiiran wisil — he finally arrived")

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun ADVSectionHeader(text: String) {
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
private fun ADVBodyText(text: String) {
    Text(
        text = fixArabizi(text),
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(vertical = 2.dp)
    )
}
