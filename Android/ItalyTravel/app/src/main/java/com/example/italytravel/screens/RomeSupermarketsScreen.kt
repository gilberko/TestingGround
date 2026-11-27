package com.example.italytravel.screens

import androidx.compose.runtime.Composable

private const val WHAT = "What it is"
private const val TYPES = "Store types"
private const val PRICES = "Prices"
private const val WHERE = "Where in Rome"

@Composable
fun RomeSupermarketsScreen(onBack: () -> Unit) {
    InfoScreen(title = "Rome - Supermarkets", onBack = onBack) {
        SectionHeader("Supermarkets in Rome")
        BodyText(
            "In the historic center you mostly find small city supermarkets (minimarkets) - Carrefour Express, Conad City, Pam Local, Coop, Despar, Elite and others. " +
                "They open long hours, often 7 days a week, and sell water, drinks, snacks, fruit, bread, pasta, cheese, wine and basics - handy, but a bit more expensive and with less choice. " +
                "Large supermarkets and hypermarkets (much bigger choice, lower prices) are in the outer neighborhoods and the shopping malls."
        )
        BodyText(
            "Tips: bring a bag or pay a few cents for one at the checkout. For loose fruit and vegetables put on the plastic gloves, then weigh and label them yourself at the scale (note the product number) before the till. " +
                "Many small stores open about 8:00-21:00 or later; on Sundays and holidays some open shorter hours (some Conad stores only in the morning) - check the hours on the door or in the chain's app."
        )

        GroupHeader("The Main Chains")
        PlaceEntry(
            "Coop",
            WHAT to "Italy's big consumer cooperative chain, known for its good own-brand products ('Prodotti Coop').",
            TYPES to "Coop (neighborhood supermarkets), Ipercoop (hypermarkets in malls).",
            PRICES to "Mid-range; a bit more than the discounters.",
            WHERE to "Neighborhood stores around the city; the Ipercoop hypermarket inside the Euroma2 mall."
        )
        PlaceEntry(
            "Carrefour",
            WHAT to "French supermarket chain with many stores in Rome.",
            TYPES to "Carrefour Express (small city stores - the most common supermarket in the center, often with long hours), Carrefour Market (medium), Carrefour Iper (hypermarkets).",
            PRICES to "Express stores are pricier; Market and Iper are cheaper.",
            WHERE to "Many Express stores in the center - e.g. two near the Pantheon (below), near Campo de' Fiori, Trastevere and Termini."
        )
        PlaceEntry(
            "Pam",
            WHAT to "Italian supermarket chain (from Venice).",
            TYPES to "Pam Local / Pam City (small city stores), Pam supermarkets, Panorama (large hypermarkets).",
            PRICES to "Mid-range; small stores well rated for fresh food, cheese and drinks.",
            WHERE to "Pam Local stores in the historic center and around Termini; Pam City at the GranRoma mall."
        )
        PlaceEntry(
            "Conad",
            WHAT to "Italy's largest supermarket group - a cooperative of independent retailers.",
            TYPES to "Conad City (small city stores), Conad / Conad Superstore (larger), Spazio Conad (very large hypermarkets).",
            PRICES to "Mid-range; good own-brand ('Sapori & Dintorni' for regional specialties - nice food souvenirs).",
            WHERE to "Conad City stores across the city (e.g. Trastevere); the big Spazio Conad inside the Porta di Roma mall."
        )
        PlaceEntry(
            "Other chains",
            WHAT to "In the center you will also see Despar / Eurospar, Elite, Sma / Simply and Todis (a discount chain). " +
                "The cheapest discounters - Lidl, Eurospin and MD - are mostly in the outer neighborhoods, best reached by car or bus.",
            PRICES to "Discounters (Todis, Lidl, Eurospin, MD) are the cheapest; Elite and the small Express / City stores the most expensive."
        )

        GroupHeader("Where to Shop")
        PlaceEntry(
            "Near the Pantheon",
            WHAT to "Carrefour Express, Via dei Cestari 43 (about 2 min from the Pantheon, toward Largo Argentina). " +
                "Carrefour Express, Via Giustiniani 15/B (between the Pantheon and San Luigi dei Francesi). " +
                "A Coop is also listed at Via dei Cestari 42/44 - right next to the Carrefour address, so it may be the same shop under an older name. " +
                "There is also a well-rated Pam Local in the Centro Storico a few minutes away.",
            PRICES to "Small city stores - convenient but pricier; check opening hours (the center stores usually open late and on Sundays)."
        )
        PlaceEntry(
            "In the shopping malls",
            WHAT to "Euroma2: the large Ipercoop hypermarket (about 9:00-21:00). " +
                "Porta di Roma: the very large Spazio Conad (about 7,800 m2, about 9:00-22:00). " +
                "Both have a huge choice and lower prices than the center - good for stocking up, gluten-free sections and food souvenirs. See Rome -> Shopping Centers for getting there.",
            PRICES to "Normal supermarket prices with many offers."
        )
    }
}
