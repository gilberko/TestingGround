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
fun WarnerBrosHarryPotterScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "Warner Bros. The Making Of Harry Potter", onBack = onBack) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            SectionHeader("What it is")
            BodyText(
                "**Warner Bros. Studio Tour Tokyo - The Making of Harry Potter** is a walk-through " +
                    "studio tour, not a ride-based theme park - built on the former **Toshimaen** " +
                    "amusement park site and opened in June 2023. It's the second such studio " +
                    "tour in the world after London, and plan for roughly 4 hours to see the " +
                    "whole thing."
            )

            SectionHeader("Where it is / how to get there")
            BodyText(
                "1-1-7 Kasugacho, Nerima-ku, **Tokyo**. Take the Toei Oedo Line to Toshimaen " +
                    "Station - the entrance is about a 2-minute walk. Alternatively, take the " +
                    "Seibu Ikebukuro Line directly to Toshimaen (about 17 minutes from Ikebukuro " +
                    "Station). A Haneda Airport Limousine Bus route also has a stop serving the " +
                    "main entrance directly."
            )

            SectionHeader("Prices")
            BodyText(
                "Roughly ¥7,000 for adults (18+), ¥5,800 for teens (12-17), and ¥4,200 for " +
                    "children (4-11); under 4 is free. Pricing is date-based and was updated as " +
                    "of July 2026, so treat these as approximate and check the official site for " +
                    "the exact price on your travel dates."
            )

            SectionHeader("Ages")
            BodyText(
                "All ages - it's an easy, accessible walking tour with no rides or thrill " +
                    "elements, though it's most rewarding for visitors already familiar with the " +
                    "Harry Potter films."
            )

            SectionHeader("What it includes")
            BodyText(
                "Walk-through soundstages and original film sets - the Great Hall, Gryffindor " +
                    "common room, and Dumbledore's office among them - plus a recreated Diagon " +
                    "Alley, original props and costumes from the films, and an outdoor backlot " +
                    "area with the Hogwarts Express and the Knight Bus. Food and drink options " +
                    "on site include Butterbeer."
            )

            SectionHeader("How to book tickets")
            BodyText(
                "Advance online booking through the official site (wbstudiotour.jp) is strongly " +
                    "recommended - tickets use timed entry slots. Walk-up tickets exist but a " +
                    "given date and time can be sold out in advance, especially on weekends."
            )
            Spacer(Modifier.height(16.dp))
        }
    }
}
