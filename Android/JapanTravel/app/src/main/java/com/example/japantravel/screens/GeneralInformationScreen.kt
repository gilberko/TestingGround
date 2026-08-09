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
fun GeneralInformationScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "General Information", onBack = onBack) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            SectionHeader("Shinkansen (Bullet Train)")
            BodyText(
                "Book a reserved seat at a ticket counter (\"Midori no Madoguchi\"), a ticket " +
                    "machine, or an app such as SmartEX / the JR East or JR West apps. If you have " +
                    "a Japan Rail Pass, reserved seats are included at no extra cost."
            )
            BodyText(
                "Luggage size limit: bags up to 160cm (length + width + height combined) travel " +
                    "with no reservation needed. Bags between 160cm and 250cm require a free " +
                    "oversized-baggage seat reservation on the Tokaido, Sanyo, and Kyushu " +
                    "Shinkansen lines (this covers the Tokyo-Kyoto-Osaka route). Bags over 250cm " +
                    "are not allowed on board at all. Boarding with an oversized bag without a " +
                    "reservation can incur a ¥1,000 handling fee."
            )
            BodyText(
                "Sending luggage ahead: most hotels can arrange \"takkyubin\" (also called " +
                    "takuhaibin), Japan's luggage-forwarding courier service run by Yamato " +
                    "Transport (the black cat logo). Ask the front desk, fill out a short form " +
                    "with the next hotel's name/address and your check-in date, and pay per bag. " +
                    "Delivery is typically next-day, costs roughly ¥1,650-2,530 per bag, and " +
                    "follows the same 160cm / 25kg size and weight limit."
            )

            SectionHeader("The Yen (¥)")
            BodyText(
                "Japan's currency is the yen (¥). Cash is still widely used alongside cards and " +
                    "IC cards, so it's worth carrying some at all times."
            )
            BodyText(
                "Approximate conversion: 100 JPY ≈ 1.9 ILS (roughly 1 ILS ≈ 52-53 JPY). This rate " +
                    "changes daily, so check the current rate shortly before and during the trip."
            )

            SectionHeader("Onsens & Ryokans")
            BodyText(
                "An onsen is a natural hot spring bath, found as standalone bathhouses or as part " +
                    "of a hotel/ryokan. Bathers wash and rinse thoroughly before entering the " +
                    "water, then bathe nude (swimsuits are generally not worn). Baths are often " +
                    "separated by gender, and some onsens restrict guests with visible tattoos."
            )
            BodyText(
                "A ryokan is a traditional Japanese inn, with tatami-mat rooms, futon bedding laid " +
                    "out on the floor, and often a multi-course kaiseki dinner and breakfast " +
                    "included. Many ryokans have their own onsen baths for guests."
            )

            SectionHeader("IC Cards (Suica / Pasmo / ICOCA)")
            BodyText(
                "These are rechargeable tap-to-pay cards for trains, subways, buses, vending " +
                    "machines, and many convenience stores. Suica is issued by JR East for the " +
                    "Tokyo region; Pasmo is issued by Tokyo's private railway and bus operators; " +
                    "ICOCA is issued by JR West for the Kansai region (Osaka, Kyoto, Kobe)."
            )
            BodyText(
                "Since 2013, these cards (and several others) are mutually usable nationwide, so " +
                    "any one of Suica, Pasmo, or ICOCA works for transit in Tokyo, Kyoto, and " +
                    "Osaka alike. A card can only be refunded at a station in its home region " +
                    "(e.g. a Suica must be refunded at a JR East station in Tokyo)."
            )
            Spacer(Modifier.height(16.dp))
        }
    }
}
