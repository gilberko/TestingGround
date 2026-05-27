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
fun ColorsScreen() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        ColSectionHeader("How Colors Work as Adjectives")
        ColBodyText("In Arabic, a color adjective comes AFTER the noun it describes and must agree with it in gender and number.")
        Spacer(modifier = Modifier.height(8.dp))
        ColBodyText("Masculine singular: أبيض (abyad)")
        ColBodyText("Feminine singular: بيضا (bayda)  — most colors add an 'a' sound")
        ColBodyText("Plural (for things): بيض (bid)  — shorter, different vowel pattern")
        Spacer(modifier = Modifier.height(8.dp))
        ColBodyText("Note: the \"classic\" masculine color form in Arabic has the pattern أَفْعَل (like أبيض، أحمر، أسود). The feminine drops the أ- prefix and adds -ا at the end. The plural uses a different vowel pattern. Colors ending in -i (like بني، زهري) behave differently — they just add -ة for feminine.")

        ColSectionHeader("Black")
        ColBodyText("masculine: أسود — aswad")
        ColBodyText("feminine: سودا — soda")
        ColBodyText("plural: سود — sud")
        ColBodyText("Example: شعر أسود (sha'r aswad = black hair)")

        ColSectionHeader("White")
        ColBodyText("masculine: أبيض — abyad")
        ColBodyText("feminine: بيضا — bayda")
        ColBodyText("plural: بيض — bid")
        ColBodyText("Example: بيت أبيض (beit abyad = white house), سيارة بيضا (sayyara bayda = white car)")

        ColSectionHeader("Red")
        ColBodyText("masculine: أحمر — ahmar")
        ColBodyText("feminine: حمرا — hamra")
        ColBodyText("plural: حمر — humur")
        ColBodyText("Example: تفاحة حمرا (tuffaha hamra = red apple)")

        ColSectionHeader("Blue")
        ColBodyText("masculine: أزرق — azra'")
        ColBodyText("feminine: زرقا — zar'a")
        ColBodyText("plural: زرق — zur'")
        ColBodyText("Example: سما زرقا (sama zar'a = blue sky)")

        ColSectionHeader("Yellow")
        ColBodyText("masculine: أصفر — asfar")
        ColBodyText("feminine: صفرا — safra")
        ColBodyText("plural: صفر — sufur")
        ColBodyText("Example: ليمون أصفر (lemon asfar = yellow lemon)")

        ColSectionHeader("Green")
        ColBodyText("masculine: أخضر — akhdar")
        ColBodyText("feminine: خضرا — khadra")
        ColBodyText("plural: خضر — khudr")
        ColBodyText("Example: ورقة خضرا (war'a khadra = green leaf)")

        ColSectionHeader("Orange")
        ColBodyText("masculine: برتقالي — burtu'ali")
        ColBodyText("feminine: برتقالية — burtu'aliyye")
        ColBodyText("(This type of color — ending in -i — uses -iyye for feminine and -iyyin for plural)")
        ColBodyText("Example: برتقالة برتقالية (burtu'ale burtu'aliyye = orange orange — the fruit and color share the name!)")

        ColSectionHeader("Brown")
        ColBodyText("masculine: بني — bunni")
        ColBodyText("feminine: بنية — buniyye")
        ColBodyText("Example: خبز بني (khubz bunni = brown bread)")

        ColSectionHeader("Pink")
        ColBodyText("masculine: زهري — zahri  (from زهر = flower)")
        ColBodyText("feminine: زهرية — zahriyye")
        ColBodyText("Example: فستان زهري (fustan zahri = pink dress)")

        ColSectionHeader("Purple")
        ColBodyText("masculine: بنفسجي — banafsaji  (from بنفسج = violet flower)")
        ColBodyText("feminine: بنفسجية — banafsajiyye")
        ColBodyText("Example: عنب بنفسجي (ʕinab banafsaji = purple grapes)")

        ColSectionHeader("Turquoise")
        ColBodyText("تركواز — turkwaz")
        ColBodyText("(Borrowed word — often used without inflection for both genders)")
        ColBodyText("Example: فيروزي (firuzi) is also used, from فيروز (firuz = turquoise stone)")

        ColSectionHeader("Gray")
        ColBodyText("masculine: رمادي — ramadi  (from رماد = ash)")
        ColBodyText("feminine: رمادية — ramadiyye")
        ColBodyText("Example: سحاب رمادي (sahab ramadi = gray cloud)")

        ColSectionHeader("Examples in Context")
        ColBodyText("بيت أبيض — beit abyad — white house")
        ColBodyText("سيارة حمرا — sayyara hamra — red car")
        ColBodyText("قميص أزرق — 'amis azra' — blue shirt")
        ColBodyText("عيون خضر — ʕyun khudr — green eyes")
        ColBodyText("شعر أسود — sha'r aswad — black hair")
        ColBodyText("كلب بني — kalb bunni — brown dog")
        ColBodyText("ورد زهري — ward zahri — pink roses")

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun ColSectionHeader(text: String) {
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
private fun ColBodyText(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(vertical = 2.dp)
    )
}
