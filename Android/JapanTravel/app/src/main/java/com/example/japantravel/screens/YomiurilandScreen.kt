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
fun YomiurilandScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "Yomiuriland", onBack = onBack) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            SectionHeader("What it is")
            BodyText(
                "An amusement park in Inagi, on the western edge of Tokyo, with over 40 rides " +
                    "and attractions. It's known for its seasonal night illuminations, " +
                    "\"Jewellumination,\" and runs a popular water park section during summer."
            )

            SectionHeader("Prices")
            BodyText(
                "Admission-only tickets are about ¥1,800 for adults, ¥1,500 for " +
                    "middle/high-school students, and ¥1,000 for kindergarteners, " +
                    "elementary-schoolers, and seniors. A One-Day Passport (admission plus " +
                    "unlimited rides) is also available at a higher, seasonally-varying price - " +
                    "check the official Yomiuriland website for current pricing before visiting."
            )

            SectionHeader("How to get there")
            BodyText(
                "Take the Keio New Line from Shinjuku to Keio Yomiuriland Station, about 35 " +
                    "minutes, then ride the \"Sky Shuttle\" gondola directly to the park entrance. " +
                    "Alternatively, take the Odakyu Line from Shinjuku to Yomiuriland-mae Station " +
                    "(about 22-25 minutes) and continue by Odakyu Bus to the entrance."
            )

            SectionHeader("App")
            BodyText(
                "There's no confirmed official Yomiuriland app for foreign visitors - use the " +
                    "official website or Google Maps to plan your visit and check ride wait times " +
                    "on-site."
            )

            SectionHeader("Famous rides")
            BodyText(
                "Bandit, a high-speed roller coaster reaching about 110 km/h through the park's " +
                    "forest, is the headline ride. Crazy Hyuuu and Crazy Stooon are vertical " +
                    "liftoff/drop rides with high-up views over the surroundings. The park's giant " +
                    "Ferris wheel offers views of Mt. Fuji, Tokyo Tower, and Tokyo Skytree on a " +
                    "clear day."
            )
            Spacer(Modifier.height(16.dp))
        }
    }
}
