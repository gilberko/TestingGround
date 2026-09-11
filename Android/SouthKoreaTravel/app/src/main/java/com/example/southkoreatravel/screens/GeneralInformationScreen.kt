package com.example.southkoreatravel.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun GeneralInformationScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "General Information", onBack = onBack) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(24.dp)
        ) {
            SectionHeader("Currency")
            BodyText("South Korea's currency is the Won (₩, KRW). It comes in large denominations, so don't be alarmed by big numbers on price tags — a ₩15,000 meal is roughly $11, not $15,000.")
            BodyText("As of September 2026, roughly ₩1,330-1,340 = 1 USD. Against the Israeli Shekel, roughly 1 ILS = ₩470-500 (the rate has moved between about ₩470 and ₩540 per shekel over 2026, so it does shift). These are approximate — check a live converter (Wise, XE, or your bank) close to your travel dates rather than relying on a fixed number.")
            BodyText("Cash is still widely used, especially at markets and small street-food stalls, but international Visa/Mastercard are accepted almost everywhere else, including taxis and convenience stores.")

            SectionHeader("Getting Around Cities")
            BodyText("Seoul and Busan both have extensive subway networks (Seoul's is one of the largest in the world), and every city has a dense bus network layered on top — in Seoul, buses are color-coded by role: blue for trunk routes across the city, green for feeder routes to subway stations, red for express routes to satellite cities, and yellow for short circular routes.")
            BodyText("Korea's equivalent of Japan's Suica or Icoca is the T-money card — a single rechargeable card that works on the subway, city buses, and many taxis nationwide, so unlike some regional cards in Japan, one T-money card works everywhere in Korea. Buy one at any convenience store (CU, GS25, 7-Eleven, Emart24, Ministop) for a small fee, or get a \"T-money Only for Foreigners\" card at an airport bus counter (about ₩23,500, including ₩20,000 of preloaded credit). Top it up with cash at a convenience store counter or at English-language subway station kiosks; there's also a Mobile T-money app that lets you load it from a foreign Mastercard, Amex, or UnionPay card.")
            BodyText("To pay, simply tap the card on the reader when entering — and importantly, tap again when exiting the subway or getting off the bus, since fares (and free transfers between bus and subway) are calculated based on both taps.")
            BodyText("Unlike Japan's Shinkansen, there's no reservation-required size limit for everyday subway or city bus luggage — you can bring a normal suitcase on with you. If you're taking the KTX (Korea's high-speed intercity train) with a large bag, note that carry-on space is limited: the official carry-on guideline is about 50×40×20cm/10kg, though larger suitcases are generally tolerated in the luggage racks and compartments at the end of each car if they fit — just expect those spaces to fill up fast on weekends and holidays.")

            SectionHeader("Luggage Forwarding")
            BodyText("Korea doesn't have a walk-into-any-shop service as ubiquitous as Japan's Takkyubin, but same-day luggage delivery does exist and is easy to book online in advance. Services like Zimcarry (branded TRIPEASY at Incheon Airport), Goodlugg, and Luggagent operate airport-to-hotel, hotel-to-hotel, and hotel-to-airport delivery at major airports (Incheon, Gimpo, Gimhae, Jeju) and train stations (Seoul Station, Busan Station, and others). Expect to pay roughly ₩15,000-50,000 depending on the route, and to book ahead rather than just dropping a bag off on the spot.")

            SectionHeader("Entry Requirements")
            BodyText("South Korea normally requires visa-free travelers to pre-register online through K-ETA (Korea Electronic Travel Authorization, at the official site k-eta.go.kr) before flying in. However, Israeli citizens are currently exempt from this K-ETA requirement for tourism — an exemption the Korean government has extended through December 31, 2026 — on top of already being able to visit visa-free for stays of up to 90 days.")
            BodyText("In practice, this means an Israeli tourist currently does not need to fill in anything online or print a confirmation before flying to Korea. That said, the exemption is temporary and K-ETA is set to become mandatory again for all visa-free travelers from January 1, 2027, so if you're traveling after that date, check k-eta.go.kr again closer to your trip in case the rules have changed. If K-ETA ever is required for you, apply at least 72 hours (ideally a week) before your flight — you'll need your passport, a payment card, an email address, and a recent photo.")
        }
    }
}
