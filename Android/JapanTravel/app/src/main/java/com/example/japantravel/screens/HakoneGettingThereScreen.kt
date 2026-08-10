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
fun HakoneGettingThereScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "Getting There", onBack = onBack) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            SectionHeader("From Tokyo")
            BodyText(
                "The easiest way is the Odakyu Romancecar, a comfortable reserved-seat limited " +
                    "express running direct from Shinjuku Station to Hakone-Yumoto in about 85 " +
                    "minutes - no transfers. A cheaper, slower alternative is the regular Odakyu " +
                    "line with a transfer partway. The Hakone Free Pass (sold by Odakyu) bundles " +
                    "round-trip transport from Shinjuku with unlimited use of the local Hakone " +
                    "Tozan train/bus/ropeway/pirate-ship cruise network, and is usually the best " +
                    "value if you're spending a full day or more exploring the area."
            )

            SectionHeader("From Kyoto")
            BodyText(
                "Take the Tokaido Shinkansen to Odawara Station. Note that most Nozomi services " +
                    "do not stop at Odawara - use a Hikari or Kodama train (Kodama makes the most " +
                    "stops but is fine for this leg). From Odawara, switch to the Hakone Tozan " +
                    "line train or a bus to reach Hakone-Yumoto and the rest of the Hakone area. " +
                    "Total door-to-door travel time is roughly 2.5-3 hours."
            )
            Spacer(Modifier.height(16.dp))
        }
    }
}
