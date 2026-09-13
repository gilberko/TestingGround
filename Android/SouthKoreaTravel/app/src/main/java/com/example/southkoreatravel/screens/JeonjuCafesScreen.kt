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
fun JeonjuCafesScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "Cafes", onBack = onBack) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(24.dp)
        ) {
            BodyText(boldNames("Jeonju has an especially large and well-known scene of hanok-style cafes clustered in and around Hanok Village.", listOf("Hanok Village")))

            SectionHeader("Cafe Ireuri")
            BodyText(boldNames("An authentic hanok-style cafe with a traditional pavilion and annex, popular for its photo spots and drinks made with homemade fruit syrups.", listOf("Cafe Ireuri")))

            SectionHeader("Jeonmang Cafe and Guesthouse")
            BodyText(boldNames("A 5th-floor cafe with panoramic views over the hanok rooftops, especially popular at sunset.", listOf("Jeonmang Cafe and Guesthouse")))

            SectionHeader("Postbean Cafe")
            BodyText(boldNames("A two-story cafe with a second-floor terrace overlooking the hanok roofs of the village.", listOf("Postbean Cafe")))

            SectionHeader("Cafe Haengwon and Goznuk")
            BodyText(boldNames("Two distinctive hanok cafes just off the main village bustle — Haengwon is quiet and cozy with a courtyard garden, while Goznuk offers its own take on the hanok-cafe style nearby.", listOf("Haengwon", "Goznuk")))
        }
    }
}
