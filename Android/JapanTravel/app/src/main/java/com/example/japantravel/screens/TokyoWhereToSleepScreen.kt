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
fun TokyoWhereToSleepScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "Tokyo - Where To Sleep", onBack = onBack) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            SectionHeader("For Families")
            BodyText(
                "**Ueno** (zoo, museums, Ueno Park, and generally larger, cheaper hotel " +
                    "rooms) and **Asakusa** (Senso-ji Temple, a traditional atmosphere, " +
                    "family-run hotels and ryokan) are calm, walkable bases. **Odaiba** " +
                    "suits families prioritizing teamLab, Legoland Discovery Center, and " +
                    "waterfront parks, though it's a bit removed from central Tokyo (reached " +
                    "via the Yurikamome or Rinkai Line)."
            )

            SectionHeader("For Nightlife and Young Travelers")
            BodyText(
                "**Shibuya** is Tokyo's most iconic nightlife base - the Scramble Crossing, " +
                    "clubs, bars, and youth culture. **Shinjuku** (Kabukicho, Golden Gai) has " +
                    "the densest concentration of bars on the doorstep, plus the best " +
                    "late-night rail connections of any ward. **Roppongi** leans " +
                    "international and expat, with clubs open until dawn."
            )

            SectionHeader("For Business Travelers")
            BodyText(
                "**Tokyo Station / Marunouchi / Otemachi / Nihonbashi**: one transfer from " +
                    "any Shinkansen line, walking distance to Ginza, and calmer than the " +
                    "entertainment districts. Home to business-oriented hotels like the " +
                    "**Four Seasons Otemachi**, **Conrad**, **Mandarin Oriental**, and " +
                    "**Hotel Metropolitan Marunouchi**."
            )

            SectionHeader("Where To Use Caution")
            BodyText(
                "**Kabukicho** (Shinjuku) is physically safe - well-lit, heavily trafficked, " +
                    "and police-patrolled - but it's Tokyo's red-light and entertainment " +
                    "core, and the epicenter of the **\"bottakuri\"** scam, where a friendly " +
                    "tout leads a visitor into a bar that then demands an enormous bill. " +
                    "It's fine to visit or even stay near - just don't follow touts, and " +
                    "only enter venues that display prices upfront."
            )
            Spacer(Modifier.height(16.dp))
        }
    }
}
