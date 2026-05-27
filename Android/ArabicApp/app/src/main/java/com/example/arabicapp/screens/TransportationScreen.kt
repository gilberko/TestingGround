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
        TrBodyText("car — سيارة — sayyara (f)")
        TrBodyText("motorbike / bike — موتو — moto (m); also: دراجة نارية — darraje nariyye")
        TrBodyText("bicycle — بسكليت — basklit (m); also: دراجة هوائية — darraje hawwayye")
        TrBodyText("bus — باص — bas (m)")
        TrBodyText("train — قطار — 'atar (m)  (qaf → glottal stop)")
        TrBodyText("tram — ترام — tram (m)")
        TrBodyText("plane — طيارة — tayyara (f)")
        TrBodyText("boat — قارب — 'arib (m)")
        TrBodyText("ferry — معدية — ma'adiyye (f); also: فيري — firi")
        TrBodyText("ship — باخرة — bakhira (f); also: سفينة — safine")

        TrSectionHeader("People")
        TrBodyText("pedestrian — ماشي — mashi (on foot); مشاة — mushsha (pedestrians, pl)")

        TrSectionHeader("Verbs")
        TrBodyText("to drive — يسوق — ysu'  (qaf → glottal stop; present: بيسوق — byisu')")
        TrBodyText("to fly — يطير — ytir  (present: بيطير — bytir)")
        TrBodyText("to land — ينزل — yinzal  (present: بينزل — byinzal)")
        TrBodyText("to buy a ticket — يشتري تذكرة — yishtri tazkara")

        TrSectionHeader("Tickets")
        TrBodyText("ticket — تذكرة — tazkara (f)")
        TrBodyText("train ticket — تذكرة قطار — tazkaret 'atar")
        TrBodyText("bus ticket — تذكرة باص — tazkaret bas")

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
