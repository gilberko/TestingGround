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
fun CityAndNatureScreen() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        CnSectionHeader("Urban / City")
        CnBodyText("building — بناية — binaye")
        CnBodyText("    f · 2: بنايتين binaytein · pl: بنايات binayaat")
        CnBodyText("apartment — شقة — sha22a  (qaf → glottal stop in Palestinian)")
        CnBodyText("    f · 2: شقتين sha22tein · pl: شقق sha2a2")
        CnBodyText("house — بيت — beit")
        CnBodyText("    m · 2: بيتين beitein · pl: بيوت byuut")
        CnBodyText("street — شارع — share3")
        CnBodyText("    m · 2: شارعين shar3ein · pl: شوارع shawaari3")
        CnBodyText("street corner — ناصية — nasye")
        CnBodyText("    f · 2: ناصيتين nasytein · pl: نواصي nawaasi")
        CnBodyText("road — طريق — tari2")
        CnBodyText("    m (sometimes f) · 2: طريقين tari2ein · pl: طرق turu2")
        CnBodyText("traffic light — إشارة — ishara; also: إشارة ضوئية — ishara daw2iyye")
        CnBodyText("    f · 2: إشارتين ishartein · pl: إشارات isharaat")
        CnBodyText("pavement / sidewalk — رصيف — rasif  (same word for both)")
        CnBodyText("    m · 2: رصيفين rasifein · pl: أرصفة arsife")
        CnBodyText("fence — سياج — siyaj")
        CnBodyText("    m · 2: سياجين siyajein · pl: سياجات siyajaat")
        CnBodyText("bricks — طوب — tub (collective); طوبة — tuba (one brick)")
        CnBodyText("    m, collective · one brick: طوبة tuba (f) · 2: طوبتين tubtein · counted pl: طوبات tubaat (tlat tubaat)")

        CnSectionHeader("Ground and Land")
        CnBodyText("floor / ground — أرض — ard  (indoor floor: أرضية — ardiyye)")
        CnBodyText("    f · 2: أرضين ardein · pl: أراضي araadi")
        CnBodyText("soil / dirt — تراب — turab")
        CnBodyText("    m · uncountable (no dual/plural in normal use)")
        CnBodyText("sand — رمل — ramel")
        CnBodyText("    m · uncountable (رمال rimaal = sands, poetic)")
        CnBodyText("land — أرض — ard  (also: منطقة — min2ata = area / territory)")
        CnBodyText("    f · 2: أرضين ardein · pl: أراضي araadi (plots of land)")

        CnSectionHeader("Plants and Vegetation")
        CnBodyText("grass — عشب — 3ushb; also colloquially: خضرة — khudra (greenery)")
        CnBodyText("    m · uncountable (أعشاب a3shaab = herbs)")
        CnBodyText("forest / woods — غابة — ghabe")
        CnBodyText("    f · 2: غابتين ghabtein · pl: غابات ghabaat")
        CnBodyText("trees — أشجار — ashjar (pl) / شجرة — shajara (sg, f)")
        CnBodyText("    f (shajara) · 2: شجرتين shajartein · pl: شجر shajar (trees in general) / أشجار ashjaar · counted: tlat shajaraat")
        CnBodyText("flowers — ورد — ward (collective, m) / وردة — warda (one flower, f)")
        CnBodyText("    f (warda) · 2: وردتين wardtein · pl: ورود wruud · counted: tlat wardaat")
        CnBodyText("bush / shrub — شجيرة — shajire")
        CnBodyText("    f · 2: شجيرتين shajirtein · pl: شجيرات shajiraat")
        CnBodyText("mushrooms — فطر — futur (collective); also: عيش الغراب — 3eish el-ghrab  (lit: crow's food)")
        CnBodyText("    m, collective · usually not counted (say shwayyet futur = some mushrooms)")

        CnSectionHeader("Water")
        CnBodyText("lake — بحيرة — bu7eira")
        CnBodyText("    f · 2: بحيرتين bu7eirtein · pl: بحيرات bu7eiraat")
        CnBodyText("pond — بركة — birke")
        CnBodyText("    f · 2: بركتين birketein · pl: برك birak")
        CnBodyText("river — نهر — nahar")
        CnBodyText("    m · 2: نهرين nahrein · pl: أنهار anhaar")
        CnBodyText("sea — بحر — ba7r")
        CnBodyText("    m · 2: بحرين ba7rein · pl: بحور b7uur")
        CnBodyText("ocean — محيط — mu7it")
        CnBodyText("    m · 2: محيطين mu7itein · pl: محيطات mu7itaat")
        CnBodyText("beach — شاطئ — shate2")
        CnBodyText("    m · 2: شاطئين shate2ein · pl: شواطئ shawaate2 (everyday: شط shatt)")

        CnSectionHeader("Sky and Weather")
        CnBodyText("sky — سما — sama")
        CnBodyText("    f (sometimes m) · uncountable in practice (سماوات samawaat = the heavens, religious)")
        CnBodyText("sun — شمس — shams")
        CnBodyText("    f · uncountable (there is one sun)")
        CnBodyText("moon — قمر — 2amar  (qaf → glottal stop)")
        CnBodyText("    m · 2: قمرين 2amarein · pl: أقمار a2maar (moons / satellites)")
        CnBodyText("rain — مطر — matar; it is raining: عم تمطر — 3am tumtur")
        CnBodyText("    m · uncountable (أمطار amtaar = rains, rainfall)")
        CnBodyText("clouds — غيوم — ghyum (pl) / غيمة — gheime (sg, f)")
        CnBodyText("    f (gheime) · 2: غيمتين gheimtein · pl: غيوم ghyuum")
        CnBodyText("snow — تلج — talj; it is snowing: عم يثلج — 3am yithluj")
        CnBodyText("    m · uncountable")

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun CnSectionHeader(text: String) {
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
private fun CnBodyText(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(vertical = 2.dp)
    )
}
