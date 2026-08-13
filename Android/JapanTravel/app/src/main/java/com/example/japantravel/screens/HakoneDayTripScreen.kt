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
fun HakoneDayTripScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "Hakone", onBack = onBack) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            SectionHeader("Getting there")
            BodyText(
                "The Odakyu Romancecar is the easiest option - a comfortable, reserved-seat " +
                    "limited express running direct from Shinjuku Station to **Hakone-Yumoto**, about " +
                    "85 minutes with no transfers. The **Hakone Free Pass** (sold by Odakyu) bundles " +
                    "the round-trip fare with unlimited local transport (Tozan train/bus/ropeway/ " +
                    "pirate-ship cruise) and is usually the best value for a full day exploring."
            )

            SectionHeader("What to see")
            BodyText(
                "The classic \"**Hakone Loop**\": a Tozan cable car up to **Sounzan**, then the **Hakone** " +
                    "**Ropeway** across to **Togendai**, passing directly over **Owakudani**, an active " +
                    "volcanic valley with steaming sulfur vents. Try kuro-tamago (\"black eggs\") " +
                    "here - regular eggs boiled in the sulfuric hot springs until the shells turn " +
                    "black, sold in packs of five for about ¥500; tradition says eating one adds " +
                    "seven years to your life."
            )
            BodyText(
                "Ropeway direction tip: the standard loop runs **Gora**/**Sounzan** -> **Owakudani** -> " +
                    "**Togendai**, and most tour groups follow it in that order. Riding the loop in " +
                    "reverse - starting with the **Lake Ashi** pirate-ship cruise from **Hakone-machi**/ " +
                    "**Moto-Hakone**, then taking the ropeway backward from **Togendai** to **Owakudani** to " +
                    "**Sounzan** - often means noticeably shorter ropeway queues. Either direction, " +
                    "aim to be at the ropeway before about 10am for the shortest lines, and check " +
                    "for volcanic-activity closures before you head out."
            )
            BodyText(
                "From **Togendai** (or **Hakone-machi** if going in reverse), the **Hakone Pirate Ship** - a " +
                    "retro-style replica sightseeing ship, not a literal antique - sails **Lake Ashi** " +
                    "(**Ashinoko**), passing **Hakone Shrine**'s floating torii gate, with **Mt. Fuji** " +
                    "visible over the lake on clear days (best chances are cold, dry mornings from " +
                    "November to February)."
            )
            BodyText(
                "The **Hakone Open-Air Museum**, at Chokoku-no-Mori Station on the Hakone Tozan Line, " +
                    "is worth building into the day if you like art - Japan's first outdoor " +
                    "museum, opened 1969, with a dedicated **Picasso Pavilion** of 300+ works plus " +
                    "sculptures by Rodin, Henry Moore, and others spread across 70,000m² of " +
                    "grounds."
            )

            SectionHeader("When to head back")
            BodyText(
                "A very early start makes the full loop (cable car, ropeway, **Owakudani**, pirate " +
                    "ship, and possibly the **Open-Air Museum**) doable as a single day trip, " +
                    "especially if a clear **Mt. Fuji** view is your main goal. That said, most travel " +
                    "guides recommend an overnight stay at a ryokan instead of rushing - it avoids " +
                    "cramming stops together and lets you relax in an onsen hot-spring bath " +
                    "afterward."
            )

            SectionHeader("How long is the train")
            BodyText(
                "About 85 minutes each way on the direct Odakyu Romancecar from **Shinjuku**."
            )
            Spacer(Modifier.height(16.dp))
        }
    }
}
