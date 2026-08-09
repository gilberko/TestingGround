package com.example.japantravel.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TokyoDomeCityScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "Tokyo Dome City", onBack = onBack) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            SectionHeader("What it is")
            BodyText(
                "An attractions complex next to Tokyo Dome (the baseball stadium) in Bunkyo. " +
                    "Entry to the complex is free - you only pay for the rides you go on."
            )

            SectionHeader("Main rides")
            BodyText(
                "Thunder Dolphin, a roller coaster that loops through the middle of the Big-O " +
                    "ferris wheel and around nearby buildings (¥1,500 per ride); Big-O itself, a " +
                    "centerless ferris wheel with views of Tokyo Tower and Skytree (¥850); plus " +
                    "family-friendly rides in the Attractions Garden area."
            )

            SectionHeader("The app, costs, and ride reservations")
            BodyText(
                "No dedicated app for booking ride time slots was found. Entry to the complex " +
                    "itself is free; rides are pay-per-ride, or you can buy a Day Pass (about " +
                    "¥4,200, unlimited rides), a cheaper Night Pass after 5pm, a Five-Ride ticket, " +
                    "or individual ride tickets."
            )
            Spacer(Modifier.height(16.dp))
        }
    }
}
