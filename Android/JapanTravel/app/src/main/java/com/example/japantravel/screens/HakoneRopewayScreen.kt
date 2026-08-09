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
fun HakoneRopewayScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "The Ropeway", onBack = onBack) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            SectionHeader("The route")
            BodyText(
                "The Hakone Ropeway runs from Sounzan to Togendai, passing directly over " +
                    "Owakudani, an active volcanic valley, on the way. From Owakudani to " +
                    "Togendai the ropeway offers views of Mt. Fuji and Lake Ashi on clear days. " +
                    "It's usually combined with the Hakone Tozan cable car up to Sounzan and, at " +
                    "the Togendai end, the Lake Ashi sightseeing cruise, forming the classic " +
                    "\"Hakone Loop\"."
            )

            SectionHeader("Owakudani: sulfur vents and black eggs")
            BodyText(
                "Owakudani is a still-active volcanic valley with steaming sulfur vents and a " +
                    "strong smell of sulfur in the air. Its signature food is kuro-tamago " +
                    "(\"black eggs\") - regular eggs boiled in the sulfuric hot springs until the " +
                    "shells turn black, sold in packs of five for about ¥500. Tradition says " +
                    "eating one adds seven years to your life."
            )

            SectionHeader("Lake Ashi and the pirate ship")
            BodyText(
                "From Togendai, a Hakone Pirate Ship (a retro-style replica sightseeing ship, not " +
                    "a literal antique) sails across Lake Ashi (Ashinoko), a crater lake formed by " +
                    "Mt. Hakone's last major eruption. The cruise passes Hakone Shrine's floating " +
                    "torii gate, with Mt. Fuji visible over the lake on clear days - best chances " +
                    "are cold, dry mornings from November to February."
            )

            SectionHeader("Day trip or overnight?")
            BodyText(
                "Hakone is about 1.5 hours from Tokyo, so a very early-morning day trip covering " +
                    "the full loop (cable car, ropeway, Owakudani, pirate ship) is doable, " +
                    "especially if a clear Mt. Fuji view is your main goal. That said, most travel " +
                    "guides recommend staying overnight instead - it avoids rushing between " +
                    "stops, and gives you time to relax at a ryokan with its own onsen hot-spring " +
                    "bath."
            )
            Spacer(Modifier.height(16.dp))
        }
    }
}
