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
fun OsakaWhereToSleepScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "Osaka - Where To Sleep", onBack = onBack) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            SectionHeader("For Families")
            BodyText(
                "**Tennoji** is a quieter, more residential, better-value base close to " +
                    "Tennoji Zoo and its parks. **Kita / Umeda**, Osaka's business hub, also " +
                    "works for families - very well connected (four subway lines meet at " +
                    "Osaka/Umeda Station) with department stores and easy access citywide."
            )

            SectionHeader("For Nightlife and Young Travelers")
            BodyText(
                "**Dotonbori / Namba / Shinsaibashi** (the Minami area) is the heart of " +
                    "Osaka's nightlife - neon signs, street food, bars, and clubs, loud and " +
                    "energetic well past midnight."
            )

            SectionHeader("For Business Travelers")
            BodyText(
                "**Kita / Umeda** is Osaka's main business district and transit hub. " +
                    "**Honmachi**, just south of the castle and business core, is quieter " +
                    "and caters more to Japanese businesspeople than tourists, often at " +
                    "better hotel rates."
            )

            SectionHeader("Where To Use Caution")
            BodyText(
                "**Nishinari-ku**, specifically the **Kamagasaki / Airin** district (a " +
                    "day-labor area with a visible homeless and poverty-affected " +
                    "population), is best avoided as a first-time base. It isn't especially " +
                    "dangerous statistically and has become popular with budget backpackers " +
                    "for its cheap accommodation, but locals generally steer clear and it's " +
                    "not the most comfortable choice for a first visit. Within Nishinari, " +
                    "**Tobita Shinchi** (a red-light district) and **Haginochaya** are worth " +
                    "avoiding specifically."
            )
            BodyText(
                "Separately, **Shinsekai** (just north of Nishinari, a fun retro sightseeing " +
                    "spot by day) gets rough in its back streets after dark, with street " +
                    "touts - fine for dinner, just stick to the main streets late at night."
            )
            Spacer(Modifier.height(16.dp))
        }
    }
}
