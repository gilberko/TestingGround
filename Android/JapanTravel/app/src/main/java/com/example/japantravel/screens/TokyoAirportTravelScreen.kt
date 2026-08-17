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
fun TokyoAirportTravelScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "Airport Travel", onBack = onBack) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            SectionHeader("Narita Airport (NRT)")
            BodyText(
                "Keisei Skyliner: about ¥2,470, ~45 minutes to Ueno / Nippori - the fastest train " +
                    "option, but doesn't reach Tokyo Station directly."
            )
            BodyText(
                "JR Narita Express (N'EX): about ¥3,070, ~55-60 minutes to Tokyo Station, with " +
                    "reserved seating and dedicated luggage racks."
            )
            BodyText(
                "Airport Limousine Bus: about ¥3,200, runs direct to major hotels and stations " +
                    "with no transfers - the bus stows luggage underneath so nobody has to carry " +
                    "it through a station."
            )
            BodyText(
                "Taxi: ¥22,700 and up - by far the most expensive option from Narita, since it's " +
                    "roughly 60-90 minutes from central Tokyo. Rarely worth it except for very late " +
                    "arrivals with no other option running."
            )

            SectionHeader("Haneda Airport (HND)")
            BodyText(
                "Tokyo Monorail or Keikyu Line: under ¥550, 13-20 minutes to central Tokyo - " +
                    "Haneda is much closer to the city than Narita, so the train is both cheap and " +
                    "fast here."
            )
            BodyText(
                "Airport Limousine Bus: about ¥1,200-1,400, direct to major hotels."
            )
            BodyText(
                "Taxi: fixed fares of roughly ¥6,900-9,100 depending on the destination zone, plus " +
                    "tolls of up to about ¥1,950 - reasonable from Haneda given the short distance."
            )

            SectionHeader("Which Airport Will I Land At?")
            BodyText(
                "Most long-haul international flights land at **Narita**, though a growing number of " +
                    "international carriers now use **Haneda** as well, which is considerably closer to " +
                    "central Tokyo. Check the airport code on the flight booking (NRT vs HND) ahead of " +
                    "time so the right transfer option can be planned in advance."
            )

            SectionHeader("Cost and Convenience Comparison")
            BodyText(
                "Trains (Skyliner, N'EX, Monorail, Keikyu) are the cheapest per person and run " +
                    "frequently, but mean carrying luggage through stations and possibly changing " +
                    "lines. Buses cost more than the cheapest train option but go directly to a " +
                    "hotel door with no transfers or stairs. Taxis are the simplest but priciest - " +
                    "far more reasonable from Haneda's fixed short fares than from Narita's long " +
                    "metered ride."
            )

            SectionHeader("Traveling with Family and Luggage")
            BodyText(
                "The Airport Limousine Bus is generally the most family-and-luggage-friendly option " +
                    "at either airport: no stairs, no transfers, and luggage is stowed in a hold " +
                    "underneath rather than carried. The Narita Express and Skyliner both have " +
                    "reserved seating and dedicated luggage racks, so a family can sit together with " +
                    "bags nearby. Shared-van door-to-door transfer services (roughly ¥1,980 per " +
                    "adult, children typically half-price) are worth considering for a lot of " +
                    "luggage or when going straight to an apartment rather than a hotel. Taxis are " +
                    "the least complicated choice but make the most financial sense from Haneda's " +
                    "short fixed fares rather than Narita's much longer ride."
            )
            Spacer(Modifier.height(16.dp))
        }
    }
}
