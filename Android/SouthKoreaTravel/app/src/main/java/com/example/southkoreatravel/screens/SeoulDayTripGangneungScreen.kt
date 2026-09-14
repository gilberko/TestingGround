package com.example.southkoreatravel.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SeoulDayTripGangneungScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "Gangneung", onBack = onBack) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(24.dp)
        ) {
            SectionHeader("What's There")
            BodyText(boldNames("Gangneung is an east-coast city known for its beaches, the Anmok Coffee Street (a cluster of seaside cafes), Ojukheon (a preserved historic house and garden), and seafood/fish markets.", listOf("Gangneung", "Anmok Coffee Street", "Ojukheon")))

            SectionHeader("Getting There")
            BodyText(boldNames("Take the KTX-Eum high-speed train from Seoul Station — roughly 1.5 to 2.5 hours each way depending on the specific train.", listOf("KTX-Eum", "Seoul Station")))

            SectionHeader("How Long It Takes")
            BodyText("This is a much longer day trip than the others — with 3-5 hours of travel alone round trip, it really only works as an early-out, late-back full day, or is better done as an overnight if your schedule allows it.")

            SectionHeader("Does Weather Matter?")
            BodyText("It matters more for beach time than for the coffee street, Ojukheon, or the markets, so a rainy day still leaves plenty to do — just don't plan around the beach specifically if the forecast looks bad.")

            SectionHeader("Independent or Organized Tour?")
            BodyText("Fully doable independently via KTX — no tour is needed. Book your KTX tickets in advance where possible, since trains can sell out on weekends and holidays.")
        }
    }
}
