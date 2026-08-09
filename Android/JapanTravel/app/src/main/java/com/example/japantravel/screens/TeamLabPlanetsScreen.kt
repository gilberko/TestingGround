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
fun TeamLabPlanetsScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "teamLab Planets", onBack = onBack) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            SectionHeader("What it is")
            BodyText(
                "An immersive digital-art museum in Toyosu (Koto Ward), right by Shin-Toyosu " +
                    "Station. It's not a ride park but a walk-through art experience: you go " +
                    "barefoot through knee-deep water rooms with projected digital art beneath " +
                    "your feet, plus a garden dome where you walk among flowers. Four large " +
                    "artwork spaces and two gardens in total."
            )

            SectionHeader("Tickets and tips")
            BodyText(
                "Entry is timed and must be booked in advance - slots regularly sell out weeks " +
                    "ahead. Book through the official website or platforms like Klook. Wear " +
                    "clothes you don't mind getting wet (no skirts/dresses); shorts or rolled-up " +
                    "trousers work well, and a small towel is provided."
            )
            Spacer(Modifier.height(16.dp))
        }
    }
}
