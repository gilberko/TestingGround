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
fun TokyoSummerlandScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "Tokyo Summerland", onBack = onBack) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            SectionHeader("What it is")
            BodyText(
                "A water park in Akiruno, western Tokyo, split into two parts: the indoor " +
                    "\"Adventure Dome\" with a large wave pool and water slides, and the outdoor " +
                    "\"Adventure Lagoon\" with thrill slides and a lazy river."
            )

            SectionHeader("When it's open, indoor vs. outdoor, rainy days")
            BodyText(
                "The indoor Adventure Dome is open year-round, typically 10:00am-7:00pm, so it's " +
                    "a good rainy-day or off-season option regardless of weather. The outdoor " +
                    "Adventure Lagoon only opens for summer, roughly July through September, " +
                    "typically 9:30am-6:00pm."
            )

            SectionHeader("Prices")
            BodyText(
                "Pricing is dynamic and date-specific - tickets must be bought online in advance. " +
                    "Spring and autumn visits offer the best value, around ¥2,600 for adults and " +
                    "¥1,500 for elementary-school children, while peak summer dates climb to " +
                    "roughly ¥4,100-5,600 for adults."
            )

            SectionHeader("How to get there")
            BodyText(
                "About a 10-minute bus ride from JR Itsukaichi Line's Akigawa Station, or roughly " +
                    "30 minutes by bus from Hachioji Station."
            )

            SectionHeader("Famous rides")
            BodyText(
                "Monster Stream (opened 2024), the Great Journey - at 650 meters, one of Japan's " +
                    "longest lazy rivers - and DEKASLA, a slide with a 20-meter drop, are the " +
                    "park's headline attractions."
            )
            Spacer(Modifier.height(16.dp))
        }
    }
}
