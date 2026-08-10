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
fun TokyoOutsideAreaScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "Outside Tokyo", onBack = onBack) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            SectionHeader("Nikko")
            BodyText(
                "Nikko is the picturesque forest-and-shrine day trip everyone means when they " +
                    "ask about it: Nikko Toshogu is a lavishly decorated Shinto shrine set among " +
                    "towering old-growth cedar forest, part of a UNESCO World Heritage site along " +
                    "with the neighboring Rinnoji and Futarasan shrines/temples. The setting - " +
                    "ornate gilded buildings rising out of deep forest - is what makes it so " +
                    "photogenic."
            )
            BodyText(
                "When it's open: 8:00-17:00 April-October, 8:00-16:00 November-March (last entry " +
                    "an hour before closing), open daily year-round. Adult entry is about ¥1,300."
            )
            BodyText(
                "How to get there: the fastest direct option is the Tobu limited express train " +
                    "from Tokyo's Asakusa Station to Tobu-Nikko Station, about 2 hours with no " +
                    "transfer. Alternatively, take the JR Tohoku Shinkansen from Tokyo/Ueno " +
                    "Station to Utsunomiya, then transfer to the local JR Nikko Line - a bit " +
                    "faster in theory but requires a change of trains. From Nikko Station, " +
                    "Toshogu is about a 35-minute walk or a short bus ride (the World Heritage " +
                    "Sightseeing bus stops right outside the station)."
            )

            SectionHeader("Kamakura")
            BodyText(
                "A seaside town about an hour from Shinjuku via the direct Shonan-Shinjuku Line - " +
                    "home to the Great Buddha and Tsurugaoka Hachimangu, Kamakura's most important " +
                    "shrine, a 10-minute walk from Kamakura Station."
            )
            BodyText(
                "This is where to see yabusame - horseback archery. Riders in Kamakura-period " +
                    "hunting attire gallop down a range within the shrine grounds, shooting at " +
                    "targets at full speed. It's performed as part of Tsurugaoka Hachimangu's " +
                    "Reitaisai festival, held every year September 14-16, with the yabusame ritual " +
                    "itself on the final day (September 16)."
            )
            Spacer(Modifier.height(16.dp))
        }
    }
}
