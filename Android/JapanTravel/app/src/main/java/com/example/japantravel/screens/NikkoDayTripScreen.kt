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
fun NikkoDayTripScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "Nikko", onBack = onBack) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            SectionHeader("Getting there")
            BodyText(
                "The fastest direct option is the Tobu limited express train from **Tokyo**'s " +
                    "Asakusa Station to Tobu-Nikko Station, about 2 hours with no transfer. " +
                    "Alternatively, take the JR Tohoku Shinkansen from Tokyo/Ueno Station to " +
                    "**Utsunomiya**, then transfer to the local JR Nikko Line - a bit faster in " +
                    "theory but requires changing trains partway."
            )

            SectionHeader("What to see")
            BodyText(
                "**Nikko Toshogu** is a lavishly decorated Shinto shrine set among towering " +
                    "old-growth cedar forest, part of a UNESCO World Heritage site along with the " +
                    "neighboring **Rinnoji** and **Futarasan** shrines/temples. Open 8:00-17:00 " +
                    "April-October and 8:00-16:00 November-March (last entry an hour before " +
                    "closing), daily year-round; adult entry is about ¥1,300. From Nikko Station, " +
                    "**Toshogu** is about a 35-minute walk or a short ride on the World Heritage " +
                    "Sightseeing bus, which stops right outside the station."
            )

            SectionHeader("When to head back")
            BodyText(
                "Given the roughly 2-hour ride each way, a very early departure from **Tokyo** is " +
                    "worth it to avoid a rushed visit. Direct Tobu limited-express services back " +
                    "to **Asakusa** run less frequently later in the day and seats can sell out, so " +
                    "check (and ideally reserve) your return train in the morning rather than " +
                    "leaving it for the end of the day. Aim to leave **Toshogu** by mid-to-late " +
                    "afternoon to comfortably make a direct evening service back."
            )

            SectionHeader("How long is the train")
            BodyText(
                "About 2 hours each way on the direct Tobu limited express from **Asakusa**."
            )
            Spacer(Modifier.height(16.dp))
        }
    }
}
