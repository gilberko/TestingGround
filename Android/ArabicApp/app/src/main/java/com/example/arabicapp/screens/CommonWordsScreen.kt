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
fun CommonWordsScreen() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        CWSectionHeader("People")
        CWBodyText("man — رجّال — rajjal")
        CWBodyText("woman — مرا — mara")
        CWBodyText("boy — ولد — walad")
        CWBodyText("girl — بنت — bint")
        CWBodyText("father — أبو / بابا — abu / baba")
        CWBodyText("mother — أمّ / ماما — umm / mama")
        CWBodyText("uncle (paternal) — عمّ — 3amm")
        CWBodyText("uncle (maternal) — خال — khal")
        CWBodyText("aunt (paternal) — عمّة — 3amme")
        CWBodyText("aunt (maternal) — خالة — khale")
        CWBodyText("grandfather — سيدو / جدّو — sido / jiddo")
        CWBodyText("grandmother — ستّو / تيتا — sitto / teta")
        CWBodyText("brother — أخ — akh")
        CWBodyText("sister — أخت — ukht")
        CWBodyText("friend — رفيق / رفيقة — rafi2 (m) / rafi2a (f)")

        CWSectionHeader("Home and Furniture")
        CWBodyText("house — بيت — beit")
        CWBodyText("room — غرفة — ghurfe")
        CWBodyText("door — باب — bab")
        CWBodyText("window — شبّاك — shabbak")
        CWBodyText("chair — كرسي — kursi")
        CWBodyText("table — طاولة — tawle")

        CWSectionHeader("Clothing")
        CWBodyText("shirt — قميص — 2amis (qaf → glottal stop in Palestinian)")
        CWBodyText("pants — بنطلون — bantalon")
        CWBodyText("dress — فستان — fustan")
        CWBodyText("hat — طاقية — ta2iyye")
        CWBodyText("shoes — كندرة — kundura")
        CWBodyText("socks — شراب — shrab")

        CWSectionHeader("Nature and Weather")
        CWBodyText("sun — شمس — shams")
        CWBodyText("moon — قمر — 2amar (qaf → glottal stop)")
        CWBodyText("cloud — غيمة — gheme")
        CWBodyText("rain — مطر — matar")
        CWBodyText("ice / snow — ثلج — talj (ث → t in Palestinian)")
        CWBodyText("water — مي — may")
        CWBodyText("tree — شجرة — shajara")
        CWBodyText("grass — حشيش — 7shish")
        CWBodyText("flower — وردة — warda")
        CWBodyText("wood — خشب — khashab")
        CWBodyText("field — حقل — 7a2l")

        CWSectionHeader("Kitchen and Eating")
        CWBodyText("food — أكل — akal")
        CWBodyText("cup — كاسة — kase")
        CWBodyText("plate — صحن — sa7an")
        CWBodyText("spoon — ملعقة — mal3a2a")
        CWBodyText("knife — سكين — sikkin")
        CWBodyText("fork — شوكة — shoke")
        CWBodyText("bread — خبيز — khubiz")
        CWBodyText("meat — لحمة — la7me")
        CWBodyText("chicken — دجاج — dajaj")
        CWBodyText("egg — بيضة — beida")
        CWBodyText("cheese — جبنة — jibne")
        CWBodyText("milk — حليب — 7alib")
        CWBodyText("salt — ملح — mil7")
        CWBodyText("pepper (spice) — فلفل — filfil")
        CWBodyText("vegetables — خضرة — khadra")
        CWBodyText("fruits — فواكه — fawake")

        CWSectionHeader("Produce and Ingredients")
        CWBodyText("onion — بصل — basal")
        CWBodyText("garlic — ثوم — tum (ث → t in Palestinian)")
        CWBodyText("tomato — بندورة — bandura (Levantine term)")
        CWBodyText("cucumber — خيار — khyar")
        CWBodyText("pepper (vegetable) — فلفل — filfil")
        CWBodyText("eggplant — باذنجان — bathinjan")
        CWBodyText("parsley — بقدونس — ba2dunis")
        CWBodyText("dill — شبت — shibbit")

        CWSectionHeader("Animals")
        CWBodyText("dog — كلب — kalb")
        CWBodyText("cat — قطة — 2atta (qaf → glottal stop)")
        CWBodyText("cow — بقرة — ba2ara (qaf → glottal stop)")
        CWBodyText("chicken — دجاج — dajaj")
        CWBodyText("camel — جمل — jamal")
        CWBodyText("wolf — ذيب — dib (ذ → d in Palestinian)")
        CWBodyText("squirrel — سنجاب — sinjab")
        CWBodyText("mouse — فارة — fare")
        CWBodyText("snake — حية — 7ayye")
        CWBodyText("scorpion — عقرب — 3a2rab")
        CWBodyText("spider — عنكبوت — 3ankabut")

        CWSectionHeader("Transport and Places")
        CWBodyText("car — سيارة — sayyara")
        CWBodyText("motorcycle — موتوسيكل — motosikl")
        CWBodyText("bicycle — بسكلت — bisiklet")
        CWBodyText("market — سوق — su2 (qaf → glottal stop)")
        CWBodyText("office — مكتب — maktab")
        CWBodyText("work — شغل — shughl")

        CWSectionHeader("Everyday Objects")
        CWBodyText("book — كتاب — ktab")
        CWBodyText("telephone — تلفون — telefon")
        CWBodyText("television — تلفزيون — televizyon")
        CWBodyText("snack — عصرونية — 3asruniyye (afternoon snack)")

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun CWSectionHeader(text: String) {
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
private fun CWBodyText(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(vertical = 2.dp)
    )
}
