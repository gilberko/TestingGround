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
fun SimAndEsimScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "Sim and eSim", onBack = onBack) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            SectionHeader("Network bands you need")
            BodyText(
                "Japan's 4G LTE network runs mainly on bands 1, 3, and 19/18/26 in cities, plus " +
                    "8 and 28 in rural areas. 5G uses the sub-6 bands n77/n78/n79. Most phones " +
                    "from roughly the last five years already support these bands, and your phone " +
                    "must be unlocked to use a local SIM or eSIM."
            )
            BodyText(
                "5G coverage outside major cities is still limited, so a 4G-capable SIM or eSIM " +
                    "is enough for most trips - don't feel you need a 5G-specific plan."
            )

            SectionHeader("Data-only SIM/eSIM for tourists")
            BodyText(
                "Global eSIM apps built on Japan's Docomo, au, SoftBank, and Rakuten networks are " +
                    "the simplest option: Airalo, Ubigi, Nomad, and Saily all sell Japan data plans " +
                    "installed instantly via QR code, no store visit needed."
            )
            BodyText(
                "Japan-based providers Mobal and Sakura Mobile also sell data-only eSIMs and " +
                    "physical SIMs, with the advantage of English-language customer support based " +
                    "in Japan itself."
            )
            BodyText(
                "One timing tip: some plans start counting their validity period from the moment " +
                    "you install the eSIM profile, not from first use - install it right before " +
                    "your trip rather than days in advance, or you can burn a day or two before " +
                    "you've even landed."
            )

            SectionHeader("Data + Voice SIM (with a Japanese phone number)")
            BodyText(
                "If you need to receive calls or texts locally (for restaurant reservations, " +
                    "delivery, or a longer stay), Mobal and Sakura Mobile both offer physical SIMs " +
                    "bundling a real Japanese phone number together with data, rather than data-only."
            )

            SectionHeader("Recommendation")
            BodyText(
                "For a short trip and data only: Airalo or Ubigi eSIM - install before departure, " +
                    "roughly $4-15 for 5-20GB over about 30 days, no Japanese number included."
            )
            BodyText(
                "If you need a Japanese phone number alongside data: Mobal or Sakura Mobile."
            )
            Spacer(Modifier.height(16.dp))
        }
    }
}
