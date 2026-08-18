package com.example.japantravel.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun OsakaDayPlansScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "Recommended Day Plans", onBack = onBack) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            BodyText(
                "A simple 2-day plan covering the highlights of central Osaka. Day 1 gives a " +
                    "broad overview of the city - history, the modern skyline, and an evening in " +
                    "Minami's food streets - while Day 2 is a focused walking loop through " +
                    "Minami's most distinctive neighborhoods."
            )

            SectionHeader("Day 1: Osaka Castle, Umeda & Dotonbori")
            BodyText(
                "**Osaka Castle & Osaka Castle Park** - Start the day at one of Japan's most " +
                    "famous castles, rebuilt in concrete in 1931 after the original 16th-century " +
                    "keep was destroyed. The surrounding park is huge and pleasant to walk " +
                    "through, especially in cherry blossom season; the museum inside the keep " +
                    "covers the castle's history and the life of its builder, Toyotomi Hideyoshi."
            )
            BodyText(
                "**Osaka Station & Umeda / Umeda Sky Building** - From the castle, it's about " +
                    "15 minutes by subway (Tanimachi Line or the JR Loop Line) to **Umeda**, " +
                    "Osaka's northern hub around Osaka Station. Spend the afternoon among the " +
                    "department stores and underground malls, then head up the **Umeda Sky " +
                    "Building**'s Floating Garden Observatory for a panoramic view of the city - " +
                    "best saved for sunset."
            )
            BodyText(
                "**Dotonbori** - In the evening, take the Midosuji Line about 10 minutes from " +
                    "Umeda down to **Namba/Shinsaibashi** for **Dotonbori**, Osaka's famous " +
                    "canal-side food and neon strip. This is the place for the giant Glico " +
                    "running-man sign, takoyaki, okonomiyaki, and crab restaurants with " +
                    "oversized moving signboards."
            )

            SectionHeader("Day 2: Kuromon Market, Den Den Town, Shinsekai & Namba")
            BodyText(
                "This day stays entirely within walking distance in southern Minami, tracing a " +
                    "loop just south of where Day 1 ended."
            )
            BodyText(
                "**Kuromon Ichiba Market** - A covered market street near Nipponbashi Station, " +
                    "good for a food-focused morning: fresh seafood, wagyu skewers, and fruit " +
                    "stands, much of it sold ready to eat on the spot."
            )
            BodyText(
                "**Den Den Town** - Osaka's answer to Akihabara, right next to Kuromon Market. " +
                    "A few streets of electronics shops, retro game stores, and anime/manga " +
                    "goods."
            )
            BodyText(
                "**Shinsekai** - A short walk south brings you to this retro entertainment " +
                    "district, built in the early 1900s and centered on the **Tsutenkaku Tower**. " +
                    "Known for kushikatsu (deep-fried skewers) and an old-fashioned, slightly " +
                    "kitschy atmosphere."
            )
            BodyText(
                "**Namba** - A short walk or one subway stop back north for shopping and a " +
                    "quieter finish to the day - **Namba Parks**, Takashimaya Osaka, and the " +
                    "narrow, lantern-lit **Hozenji Yokocho** alley. Namba sits right next to " +
                    "Dotonbori, but this stop is about daytime shopping and side streets rather " +
                    "than the canal-side food strip covered on Day 1."
            )
            Spacer(Modifier.height(16.dp))
        }
    }
}
