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
fun SeoulDayTripSuwonScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "Suwon", onBack = onBack) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(24.dp)
        ) {
            SectionHeader("What's There")
            BodyText(boldNames("Suwon's main draw is Hwaseong Fortress, a UNESCO World Heritage Site — an 18th-century Joseon-era fortress wall about 5.7km long, with gates, command posts, and watchtowers you can walk the full circuit of. Museums covering the fortress's construction and history sit near the wall as well.", listOf("Suwon", "Hwaseong Fortress")))

            SectionHeader("Getting There")
            BodyText(boldNames("Take Subway Line 1 from Seoul Station to Suwon Station — about 55-65 minutes, around ₩1,850. From Suwon Station, it's roughly a 10-minute walk to the southern gate (Paldalmun), or a short bus (36 or 39, ~15 minutes) or taxi (~10 minutes, ₩6,000-8,000) if you'd rather not walk.", listOf("Suwon Station", "Paldalmun")))

            SectionHeader("How Long It Takes")
            BodyText("A half-day trip is enough for most people — walking the full wall circuit takes about 2-3 hours, so with travel time factored in, plan on roughly 4-6 hours round trip from central Seoul.")

            SectionHeader("Does Weather Matter?")
            BodyText("The fortress walk is almost entirely outdoors on an open wall, so heavy rain, extreme heat, or winter ice will make it less comfortable — but nothing closes because of weather, so it's not a trip you need to reschedule around.")

            SectionHeader("Independent or Organized Tour?")
            BodyText("Easily done independently — the subway ride is direct and the wall is self-guided and well-signed. No organized tour is needed for Suwon.")
        }
    }
}
