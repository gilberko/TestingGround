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
fun TransportationScreen() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        TrSectionHeader("Vehicles")
        TrBodyText("car — سيارة — sayyara")
        TrBodyText("    f · 2: سيارتين sayyartein · pl: سيارات sayyaraat")
        TrBodyText("motorbike / bike — موتو — moto; also: دراجة نارية — darraje nariyye")
        TrBodyText("    m · 2: تنين موتو tnein moto (loanword — the dual ending is rarely used) · pl: موتورات motoraat")
        TrBodyText("bicycle — بسكليت — basklit; also: دراجة هوائية — darraje hawwayye")
        TrBodyText("    m · 2: بسكليتين basklitein · pl: بسكليتات basklitaat")
        TrBodyText("bus — باص — bas")
        TrBodyText("    m · 2: باصين basein · pl: باصات basaat")
        TrBodyText("train — قطار — 2atar  (qaf → glottal stop)")
        TrBodyText("    m · 2: قطارين 2atarein · pl: قطارات 2ataraat")
        TrBodyText("tram — ترام — tram")
        TrBodyText("    m · 2: تنين ترام tnein tram (loanword) · pl: ترامات tramaat")
        TrBodyText("plane — طيارة — tayyara")
        TrBodyText("    f · 2: طيارتين tayyartein · pl: طيارات tayyaraat")
        TrBodyText("boat — قارب — 2arib")
        TrBodyText("    m · 2: قاربين 2aribein · pl: قوارب 2awaarib")
        TrBodyText("ferry — معدية — ma3adiyye; also: فيري — firi")
        TrBodyText("    f · 2: معديتين ma3adiytein · pl: معديات ma3adiyyaat")
        TrBodyText("ship — باخرة — bakhira; also: سفينة — safine")
        TrBodyText("    f · 2: باخرتين bakhirtein · pl: بواخر bawaakhir")

        TrSectionHeader("People")
        TrBodyText("pedestrian — ماشي — mashi (on foot); مشاة — mushsha (pedestrians, pl)")
        TrBodyText("    m · 2: ماشيين mashyein · pl: مشاة mushaa (as above)")

        TrSectionHeader("Verbs")
        TrBodyText("to drive — يسوق — ysu2  (qaf → glottal stop; present: بيسوق — byisu2)")
        TrBodyText("to fly — يطير — ytir  (present: بيطير — bytir)")
        TrBodyText("to land — ينزل — yinzal  (present: بينزل — byinzal)")
        TrBodyText("to buy a ticket — يشتري تذكرة — yishtri tazkara")

        TrSectionHeader("Tickets")
        TrBodyText("ticket — تذكرة — tazkara")
        TrBodyText("    f · 2: تذكرتين tazkartein · pl: تذاكر tazaakir")
        TrBodyText("train ticket — تذكرة قطار — tazkaret 2atar")
        TrBodyText("    f · 2: تذكرتين قطار tazkartein 2atar · pl: تذاكر قطار tazaakir 2atar")
        TrBodyText("bus ticket — تذكرة باص — tazkaret bas")
        TrBodyText("    f · 2: تذكرتين باص tazkartein bas · pl: تذاكر باص tazaakir bas")

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun TrSectionHeader(text: String) {
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
private fun TrBodyText(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(vertical = 2.dp)
    )
}
