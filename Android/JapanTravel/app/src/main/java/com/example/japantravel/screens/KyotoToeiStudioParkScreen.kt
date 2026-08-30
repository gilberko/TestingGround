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
fun KyotoToeiStudioParkScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "TOEI Kyoto Studio Park", onBack = onBack) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            SectionHeader("What it is")
            BodyText(
                "A working jidaigeki (period-drama) film studio and theme park at **Uzumasa**, " +
                    "built around a recreated Edo-era town street that's still occasionally used " +
                    "as a real backdrop for movies and TV dramas, alongside theme-park-style " +
                    "attractions and shows built on top of the set."
            )

            SectionHeader("Who it's for")
            BodyText(
                "A good fit for families and for samurai/ninja/tokusatsu fans - the costume " +
                    "rentals, mazes, and stage shows appeal to kids, while the historical film " +
                    "sets and production history appeal more to adults. Plan for at least half a " +
                    "day if you want to see the shows and try an attraction or two."
            )

            SectionHeader("Attractions inside")
            BodyText(
                "The Edo-period street sets themselves (open for wandering and photos), a " +
                    "ninja mansion (karakuri trick-house) and ninja maze, a haunted house, " +
                    "live samurai and ninja stage shows, costume rental (ninja, samurai, geisha), " +
                    "and areas themed around Kamen Rider and Super Sentai. The park also houses " +
                    "the **Toei Anime Museum**, a smaller in-park exhibit displaying original " +
                    "cels, sketches, and life-size character models from Toei-produced anime " +
                    "such as Sailor Moon, Dragon Ball, and Super Sentai."
            )

            SectionHeader("Prices and hours")
            BodyText(
                "General admission is roughly ¥2,800 for a full day, with a cheaper twilight " +
                    "ticket of about ¥2,000 after 5pm. Some individual attractions and " +
                    "experiences cost an extra ¥400-800, and cultural costume experiences run " +
                    "around ¥2,700. Hours are approximately 9:00am-5:00pm (last entry 4:00pm) " +
                    "from March to November, and 10:00am-4:30pm (last entry 3:30pm) from " +
                    "December to February - both prices and hours can change seasonally, so it's " +
                    "worth checking the official site before you go."
            )

            SectionHeader("How to buy tickets")
            BodyText(
                "Tickets can be bought at the gate on the day, or booked in advance through the " +
                    "official site (global.toei-eigamura.com) or common ticket resellers such as " +
                    "Klook."
            )

            SectionHeader("How to get there")
            BodyText(
                "About a 5-minute walk from **Uzumasa-Koryuji Station** on the Keifuku " +
                    "(Randen) Arashiyama Line, or roughly a 15-minute walk from **JR Hanazono " +
                    "Station** on the JR Sagano/San-in Line."
            )
            Spacer(Modifier.height(16.dp))
        }
    }
}
