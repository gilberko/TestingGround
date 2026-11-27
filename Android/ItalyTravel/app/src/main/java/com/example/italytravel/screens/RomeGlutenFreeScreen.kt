package com.example.italytravel.screens

import androidx.compose.runtime.Composable

private const val SAFETY = "Gluten-free safety"
private const val FOOD = "Sort of food"
private const val ABOUT = "About"
private const val REGION = "Region in Rome"
private const val ADDRESS = "Address"
private const val GETTING_THERE = "Getting there"
private const val HOURS = "Hours and days"

@Composable
fun RomeGlutenFreeScreen(onBack: () -> Unit) {
    InfoScreen(title = "Rome - Gluten Free", onBack = onBack) {
        SectionHeader("Gluten Free in Rome")
        BodyText(
            "Italy is one of the easiest countries for celiacs: 'senza glutine' means gluten free, and many places are certified by the AIC (Associazione Italiana Celiachia). " +
                "DEDICATED = the whole place is gluten free (safest). OPTIONS = a normal kitchen with a gluten-free menu - tell the staff you are celiac ('sono celiaco / celiaca') and ask how it is prepared."
        )
        BodyText(
            "For more places, use the Find Me Gluten Free app (findmeglutenfree.com): search Rome, filter by 'dedicated gluten-free' and read recent reviews from celiacs. " +
                "Hours below come from listings that often disagree - check before going."
        )

        GroupHeader("Dedicated Gluten Free")

        PlaceEntry(
            "Mama Eat",
            SAFETY to "Dedicated - the whole menu is gluten free (and lactose free options), with separate kitchens and ovens; recognized by the AIC. One of the safest restaurants in Rome for celiacs.",
            FOOD to "Italian restaurant and pizzeria: pizza, pasta, Roman fried starters (supplì), mains and desserts.",
            ABOUT to "A small gluten-free restaurant chain with three branches in Rome (below). Book ahead in the evening.",
            HOURS to "Daily about 11:00 to midnight (official store locator)."
        )
        LabeledText(
            "Trastevere - Via di San Cosimato 7/9",
            "Region in Rome: Trastevere, by Piazza di San Cosimato. Getting there: tram 8 to Viale Trastevere, then about 5 min walk. Phone 06 580 6222."
        )
        LabeledText(
            "Vaticano - Borgo Pio 28",
            "Region in Rome: Borgo, next to the Vatican / St. Peter's. Getting there: Metro A Ottaviano, about 10 min walk. Not shown on every version of the official store locator - check before going."
        )
        LabeledText(
            "Ponte Milvio - Viale di Tor di Quinto 21",
            "Region in Rome: Ponte Milvio (north Rome, a nightlife area). Getting there: tram 2 to Piazza Mancini, then cross the Ponte Milvio bridge on foot."
        )

        PlaceEntry(
            "New Food Gluten Free - Ponte Sisto",
            SAFETY to "Dedicated - a 100% gluten-free bakery (many products also lactose free).",
            FOOD to "Bakery and takeaway: cornetti (incl. Nutella), pastries, mini bignè, cakes, pizza slices and focaccia; gluten-free beer.",
            ABOUT to "Part of the New Food gluten-free chain (also a sit-down restaurant, New Food Experience, in Prati). Small, takeaway only with no real seating - people eat by the river or on the steps nearby.",
            REGION to "Regola (Centro Storico), at the city-center end of Ponte Sisto - cross the bridge and you are in Trastevere.",
            ADDRESS to "Piazza San Vincenzo Pallotti 208.",
            GETTING_THERE to "Tram 8 to Arenula / Belli, then a few minutes' walk; about 5 min walk from Campo de' Fiori.",
            HOURS to "About 9:00-20:00; listings disagree on whether it opens on Sunday - check."
        )

        PlaceEntry(
            "Fiocco di Neve (gelato)",
            SAFETY to "Dedicated - a 100% gluten-free gelateria, including cones and wafers.",
            FOOD to "Artisanal gelato; known for zabaione and riso (rice-pudding) flavours.",
            REGION to "Centro Storico, Pantheon area.",
            ADDRESS to "Via del Pantheon 51 (about 1 min from the Pantheon).",
            GETTING_THERE to "No metro at the Pantheon - walk 10-15 min from Metro A Spagna or Barberini.",
            HOURS to "Daily about 10:00 to midnight. (More in Rome -> Ice Cream.)"
        )

        PlaceEntry(
            "MYO - La Gelateria (gelato)",
            SAFETY to "Reported as all gluten free, including cones (FindMeGlutenFree / Gluto user reports, not an official certification) - confirm at the counter.",
            FOOD to "Gelato, cones, tiramisù and other desserts.",
            REGION to "Centro Storico, Campo de' Fiori.",
            ADDRESS to "Piazza Campo de' Fiori 51.",
            GETTING_THERE to "Tram 8 or buses to Largo di Torre Argentina, then about 5 min walk.",
            HOURS to "Daily about 12:00-23:00. (More in Rome -> Ice Cream.)"
        )

        PlaceEntry(
            "Grom (gelato)",
            SAFETY to "Grom says all its gelato, sorbets and cones are gluten free (cones from its own bakery). One older review said the cones were not - confirm at the counter.",
            FOOD to "Gelato and seasonal fruit sorbets; Italian chain from Turin.",
            REGION to "Several branches: Corso Vittorio Emanuele II 69 (Largo Argentina), Via della Maddalena 30/A (Pantheon), Piazza Navona / Via Agonale 3, Termini station.",
            HOURS to "Varies by branch, e.g. Corso Vittorio Emanuele II 69: Sunday-Thursday 10:30-23:00, Friday-Saturday 10:30-23:30. (Full list in Rome -> Ice Cream.)"
        )

        GroupHeader("Gluten-Free Options (Not Dedicated)")

        PlaceEntry(
            "Pizza in Trevi",
            SAFETY to "Options - a separate gluten-free menu, and reviewers report separate preparation / kitchen and AIC-certified gluten-free options. Sources disagree on whether the kitchen is fully dedicated, so tell the staff you are celiac.",
            FOOD to "Pizza and Italian dishes - most of the menu can be made gluten free.",
            ABOUT to "Family-run pizzeria right next to the Trevi Fountain; a second branch is near Piazza di Spagna.",
            REGION to "Trevi (Centro Storico).",
            ADDRESS to "Via di San Vincenzo 30/30A (by the Trevi Fountain); second branch at Via della Mercede 32 (Piazza di Spagna).",
            GETTING_THERE to "Metro A Barberini, about 7 min walk.",
            HOURS to "Daily, about 10:00/11:00 to 23:00/midnight (listings differ)."
        )

        PlaceEntry(
            "Ristorante Il Miraggio (Trevi)",
            SAFETY to "Options - a gluten-free menu; reviewers praise the gluten-free pizza, fries and desserts and call it good for celiacs. These are diner reports, not an official statement - ask how gluten-free food is prepared.",
            FOOD to "Italian restaurant and pizzeria: pizza, pasta, mains, desserts.",
            ABOUT to "Restaurant-pizzeria in a small lane about 2 min from the Trevi Fountain.",
            REGION to "Trevi (Centro Storico), between the Trevi Fountain and Via del Corso.",
            ADDRESS to "Vicolo Sciarra 59. Phone 06 678 0226.",
            GETTING_THERE to "Metro A Barberini, about 10 min walk.",
            HOURS to "Daily about 11:00-23:00."
        )

        PlaceEntry(
            "La Sagrestia (near the Pantheon)",
            SAFETY to "Options - a gluten-free menu with pizza, pasta and desserts. Not dedicated. Celiac reviewers report careful staff (regular bread kept away); one said there was no printed GF menu but dishes were made gluten free on request. Tell the staff you are celiac.",
            FOOD to "Italian and Roman restaurant-pizzeria: pizza, pasta, mains, desserts.",
            ABOUT to "A restaurant-pizzeria a couple of minutes from the Pantheon - handy for lunch or dinner while sightseeing. " +
                "For a stricter option nearby: Pasta in Corso (Via della Maddalena 43) follows the AIC procedures for gluten-free pasta.",
            REGION to "Centro Storico, Pantheon area (between the Pantheon and Piazza di Sant'Ignazio).",
            ADDRESS to "Via del Seminario 89.",
            GETTING_THERE to "No metro at the Pantheon - walk 10-15 min from Metro A Spagna or Barberini.",
            HOURS to "Not confirmed - check Google Maps (lunch and dinner)."
        )

        GroupHeader("More Places")
        BodyText(
            "See also Rome -> Korean Food (StarBaps: bibimbap can be made gluten free on request) and use the Find Me Gluten Free app to find more dedicated places near you."
        )
    }
}
