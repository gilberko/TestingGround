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
fun OsakaAirportTravelScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "Airport Travel", onBack = onBack) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            SectionHeader("Kansai International Airport (KIX)")
            BodyText(
                "KIX is the main international gateway for the whole **Kansai** region, serving " +
                    "**Osaka**, **Kyoto**, and **Kobe**. It sits on an artificial island, so every option " +
                    "into the city involves crossing the bridge to the mainland first."
            )

            SectionHeader("Train Options")
            BodyText(
                "JR Haruka Express: about ¥2,540 standard fare, ~55 minutes to Tennoji. Foreign " +
                    "tourists can use the discounted Haruka + ICOCA ticket, bringing the price down " +
                    "to around ¥1,800. Carriages have real luggage space and a restroom, making this " +
                    "the most comfortable train option."
            )
            BodyText(
                "Nankai Rapi:t (Limited Express): about ¥1,490, ~40 minutes to Namba. Note there's a " +
                    "limit of two suitcases per person with a maximum weight of 30 kg each, which can " +
                    "get tight for a family with a lot of luggage."
            )
            BodyText(
                "Nankai Airport Express: about ¥970, ~45 minutes to Namba - the cheapest train " +
                    "option, best suited to travelers with light luggage."
            )

            SectionHeader("Bus and Taxi")
            BodyText(
                "Airport Limousine Bus: about ¥1,800, ~1 hour direct to major hotels in the Umeda " +
                    "area - no transfers, and luggage is stowed underneath, making it the most " +
                    "practical option for families with heavy luggage."
            )
            BodyText(
                "Standard taxi: roughly ¥15,000-20,000 plus a late-night surcharge - runs 24 hours " +
                    "from the arrivals hall."
            )
            BodyText(
                "Private transfer van: typically ¥16,000-20,000 for a 7-10 seat vehicle with a " +
                    "driver who helps with luggage - similar cost to a standard taxi for one or two " +
                    "people, but good value for a larger family or group."
            )

            SectionHeader("Traveling with Family and Luggage")
            BodyText(
                "For most families, the Airport Limousine Bus is the stress-free default choice - " +
                    "direct to the hotel, no stairs or transfers, and dedicated luggage storage. Of " +
                    "the trains, the Haruka Express is the better pick over the Rapi:t for families, " +
                    "since the Rapi:t's two-bags-per-person limit can be restrictive when several " +
                    "suitcases are being shared across a smaller number of adults. A private transfer " +
                    "van is worth it when the group has more luggage than passenger seats, or when " +
                    "heading straight to an apartment rather than a hotel with no bellhop to help."
            )
            Spacer(Modifier.height(16.dp))
        }
    }
}
