package com.example.italytravel.screens

import androidx.compose.runtime.Composable

private const val REGION = "Region in Rome"
private const val ADDRESS = "Address"
private const val GETTING_THERE = "Getting there"
private const val WHAT = "What is it"
private const val HISTORY = "History"
private const val FIND = "What you'll find there"
private const val PRICES = "Prices"
private const val HOURS = "Hours and days"

@Composable
fun RomeFornoPizzaScreen(onBack: () -> Unit) {
    InfoScreen(title = "Rome - Forno and Pizza", onBack = onBack) {
        SectionHeader("Forno and Roman Pizza al Taglio")
        BodyText(
            "A forno is a bakery. Roman pizza al taglio ('cut') - also called pizza in teglia or alla pala - is baked in large rectangular trays. " +
                "You point at the one you want, show the size with your hands or say 'un pezzo così' (a piece this big); it is cut with scissors, weighed, and you pay by weight."
        )
        BodyText(
            "Pizza bianca is plain - just olive oil and salt, crisp outside and soft inside; the Roman classic is to have it split and filled with mortadella ('pizza e mortazza'). " +
                "Pizza rossa has only tomato. Typical prices are about 15-30 euros per kilo, so a snack-size piece costs a few euros."
        )
        BodyText("None of these places is gluten free - for celiac-safe options see Rome -> Gluten Free. Hours come from listings that often disagree - check before going.")

        PlaceEntry(
            "Forno Ceralli (Forno Ceralli 1920)",
            REGION to "Centro Storico, Pantheon area.",
            ADDRESS to "Piazza Rondanini 42 - a small square between the Pantheon and Piazza Navona.",
            GETTING_THERE to "No metro nearby: 2-3 min walk from the Pantheon, or a bus to Largo di Torre Argentina / Corso del Rinascimento.",
            WHAT to "A traditional wood-fired bakery from the Castelli Romani hills, with a counter shop in the historic center.",
            HISTORY to "The Ceralli family bakery was founded in 1920 in Frascati, the wine town in the hills south-east of Rome, and is run today by fourth-generation baker Francesco Ceralli. " +
                "The Rome shop opened in November 2024. Everything is still baked in the family's wood-fired oven in Frascati and brought into Rome every morning.",
            FIND to "Pizza bianca alla pala and pizza rossa (the reviewers' favourite), many kinds of bread, Frascati-style ciambelline al vino (crunchy biscuits made with white wine), " +
                "artisanal panettone at Christmas, and ready-made dishes such as lasagna al ragù. Counter service with only a little seating.",
            PRICES to "Pizza is sold by weight - no price list was found, ask at the counter.",
            HOURS to "Daily, about 8:30-20:00 (one listing says until 21:00)."
        )

        PlaceEntry(
            "Forno Campo de' Fiori",
            REGION to "Centro Storico, Campo de' Fiori - on the square of the famous morning market.",
            ADDRESS to "Campo de' Fiori 22, with a second counter just around the corner at Vicolo del Gallo 14.",
            GETTING_THERE to "Tram 8 or many buses to Largo di Torre Argentina, then about 5 min walk.",
            WHAT to "Rome's most famous spot for pizza bianca - a historic bakery where locals and visitors buy a strip of pizza and eat it on the square or by the fountain.",
            HISTORY to "Run by the Roscioli baking family - Fabrizio Roscioli took over the bakery in 1979. Its exact age is disputed: remains found in the cellar suggest a bakery here around 1880, " +
                "while other sources say 'since the 1920s' or even older. The Vicolo del Gallo shop next door opened in 2001, mainly for filled pizza.",
            FIND to "Crisp, olive-oily pizza bianca and pizza rossa by the slice, pizza with other toppings, pizza bianca filled with mortadella (at Vicolo del Gallo), " +
                "plus bread, biscuits and crostate (jam tarts).",
            PRICES to "By weight: a generous strip is about 2-5 euros, a small slice about 1.50-3 euros.",
            HOURS to "Monday-Saturday about 7:30/8:00-14:30 and 16:30/16:45-20:00. Closed Sunday, and closed on Saturday afternoons in July and August. Afternoon times vary - check."
        )

        PlaceEntry(
            "L'Antico Forno di Fontana di Trevi",
            REGION to "Trevi (Centro Storico) - a few steps from the Trevi Fountain.",
            ADDRESS to "Via delle Muratte 8.",
            GETTING_THERE to "Metro A Barberini, then about 7-10 min walk.",
            WHAT to "A busy old-style bakery right next to the Trevi Fountain - a good place for a quick, inexpensive lunch or snack instead of the tourist restaurants around the fountain.",
            HISTORY to "It claims to be one of Rome's oldest bakeries, baking for 'over two centuries', in a building said to date from 1468 (these claims could not be verified independently).",
            FIND to "Pizza al taglio, focaccia, many changing kinds of bread (hazelnut breadsticks, olive rolls), cornetti and croissants, cakes, small pastries and sweets.",
            PRICES to "No price list found - ask at the counter; expect slightly higher prices because of the location.",
            HOURS to "Daily 7:00-20:00. Popular on social media, so expect a queue at busy times."
        )

        PlaceEntry(
            "Red Pizza - Trastevere",
            REGION to "Trastevere.",
            ADDRESS to "Via della Scala 73 - near Santa Maria della Scala, on the walk from Ponte Sisto toward Piazza di Santa Maria in Trastevere.",
            GETTING_THERE to "Cross Ponte Sisto from the center and walk about 5 min, or tram 8 to Belli / Trastevere, then about 10 min walk.",
            WHAT to "A casual pizza place for a quick bite - cut-to-size pizza - open very late, which makes it handy in Trastevere's nightlife area.",
            HISTORY to "A recent shop; no founding history was found.",
            FIND to "Pizza by the slice with classic Roman toppings. A delivery listing under the same name also shows round pizzas (e.g. Margherita about 7 euros), possibly from a different branch.",
            PRICES to "Slice prices not listed - expect the usual Rome range of a few euros per piece.",
            HOURS to "Daily from about 11:30; until about 00:30 Sunday-Thursday, 01:30 Friday and 02:00 Saturday. Rated about 4.5/5 (188 reviews, Restaurant Guru)."
        )
    }
}
