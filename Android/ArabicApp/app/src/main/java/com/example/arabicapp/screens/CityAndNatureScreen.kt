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
        CnBodyText("building — بناية — binaye (f)")
        CnBodyText("apartment — شقة — shi''a (f)  (qaf → glottal stop in Palestinian)")
        CnBodyText("house — بيت — beit (m)")
        CnBodyText("street — شارع — share' (m)")
        CnBodyText("street corner — ناصية — nasye (f)")
        CnBodyText("road — طريق — tari' (m)")
        CnBodyText("traffic light — إشارة — ishara (f); also: إشارة ضوئية — ishara daw'iyye")
        CnBodyText("pavement / sidewalk — رصيف — rasif (m)  (same word for both)")
        CnBodyText("fence — سياج — siyaj (m)")
        CnBodyText("bricks — طوب — tub (collective); طوبة — tuba (one brick)")

        CnSectionHeader("Ground and Land")
        CnBodyText("floor / ground — أرض — ard (f)  (indoor floor: أرضية — ardiyye)")
        CnBodyText("soil / dirt — تراب — turab (m)")
        CnBodyText("sand — رمل — ramel (m)")
        CnBodyText("land — أرض — ard (f)  (also: منطقة — min'ata = area / territory)")

        CnSectionHeader("Plants and Vegetation")
        CnBodyText("grass — عشب — 'ushb (m); also colloquially: خضرة — khudra (greenery)")
        CnBodyText("forest / woods — غابة — ghabe (f)")
        CnBodyText("trees — أشجار — ashjar (pl) / شجرة — shajara (sg, f)")
        CnBodyText("flowers — ورد — ward (collective, m) / وردة — warda (one flower, f)")
        CnBodyText("bush / shrub — شجيرة — shajire (f)")
        CnBodyText("mushrooms — فطر — futur (collective); also: عيش الغراب — 'eish el-ghrab  (lit: crow's food)")

        CnSectionHeader("Water")
        CnBodyText("lake — بحيرة — buheira (f)")
        CnBodyText("pond — بركة — birke (f)")
        CnBodyText("river — نهر — nahar (m)")
        CnBodyText("sea — بحر — bahr (m)")
        CnBodyText("ocean — محيط — muhit (m)")
        CnBodyText("beach — شاطئ — shate' (m)")

        CnSectionHeader("Sky and Weather")
        CnBodyText("sky — سما — sama (f)")
        CnBodyText("sun — شمس — shams (f)")
        CnBodyText("moon — قمر — 'amar (m)  (qaf → glottal stop)")
        CnBodyText("rain — مطر — matar (m); it is raining: عم تمطر — 'am tumtur")
        CnBodyText("clouds — غيوم — ghyum (pl) / غيمة — gheime (sg, f)")
        CnBodyText("snow — تلج — talj (m); it is snowing: عم يثلج — 'am yithluj")

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
