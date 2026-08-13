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
fun GhibliParkDayTripScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "Ghibli Park", onBack = onBack) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            SectionHeader("Getting there")
            BodyText(
                "Take the Shinkansen from Tokyo Station to **Nagoya** (about 1h40m), then the Nagoya " +
                    "subway Higashiyama Line to Fujigaoka Station (about 30 min), then the Linimo " +
                    "line to Expo Memorial Park Station (about 15 min) - roughly 2.5 hours door to " +
                    "door, inside **Aichi Expo Memorial Park** in **Nagakute** City, **Aichi** Prefecture."
            )

            SectionHeader("What to see")
            BodyText(
                "**Ghibli Park**, opened November 2022, is a Studio Ghibli theme park built inside an " +
                    "existing public park, designed as a walk-through experience across themed " +
                    "areas rather than ride-based: **Ghibli's Grand Warehouse** (indoor exhibits, a " +
                    "cinema, a recreation of a Ghibli film set), **Hill of Youth**, **Dondoko Forest**, " +
                    "**Witch's Valley**, and **Mononoke Village**. Every visitor needs an advance, dated " +
                    "timed-entry ticket - there's no walk-in entry, and popular dates can sell out " +
                    "within hours of the booking window opening."
            )

            SectionHeader("When to head back")
            BodyText(
                "Because the return trip has three separate legs (Linimo, Nagoya subway, then the " +
                    "Shinkansen) before you're even back in **Tokyo**, build in a real buffer before " +
                    "the park's evening closing time rather than cutting it close - missing a " +
                    "connection here costs more time than on a simpler single-train day trip."
            )

            SectionHeader("How long is the train")
            BodyText(
                "About 2.5 hours door to door from **Tokyo**, including the Shinkansen (~1h40m) plus " +
                    "the Nagoya subway and Linimo connections."
            )
            Spacer(Modifier.height(16.dp))
        }
    }
}
