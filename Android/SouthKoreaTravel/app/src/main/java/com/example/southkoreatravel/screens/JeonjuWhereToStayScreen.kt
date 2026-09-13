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
fun JeonjuWhereToStayScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "Where To Stay", onBack = onBack) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(24.dp)
        ) {
            SectionHeader("Young and Independent Travelers")
            BodyText(boldNames("Staying inside Hanok Village itself, in a traditional hanok guesthouse, is the most popular and atmospheric option — you're within walking distance of teahouses, artisan workshops, and traditional restaurants.", listOf("Hanok Village", "hanok")))

            SectionHeader("Business Travelers")
            BodyText(boldNames("The city center near Hanok Village has modern comfort options within walking distance of the old town, such as ibis Styles Ambassador Jeonju City Center and Shilla Stay Jeonju Hanok Village, both blending modern amenities with easy access to shopping and transport.", listOf("ibis Styles Ambassador Jeonju City Center", "Shilla Stay Jeonju Hanok Village")))

            SectionHeader("Families")
            BodyText(boldNames("The Ajung Lake area is quieter and greener, a good option for families wanting some distance from the busiest parts of Hanok Village while staying nearby.", listOf("Ajung Lake")))

            SectionHeader("Areas To Avoid")
            BodyText("Reports on specific unsafe or run-down neighborhoods in Jeonju are thin and not well corroborated, so this shouldn't be treated as settled fact — the more consistent picture from travel sources is that Jeonju has no notable unsafe areas for tourists, beyond the ordinary common sense of avoiding purely industrial districts at night.")
        }
    }
}
