package com.example.italytravel.screens

import androidx.compose.runtime.Composable

@Composable
fun RomeIceCreamScreen(onBack: () -> Unit) {
    InfoScreen(title = "Rome - Ice Cream", onBack = onBack) {
        BodyText(
            "Gelato is everywhere in Rome. Tip: avoid shops with towering, brightly colored mounds - good gelato is usually kept flat or in covered metal tubs. " +
                "Giolitti (see Coffee) is also one of Rome's most famous gelaterias."
        )
        BodyText("Hours below come from listings that often disagree - check Google Maps before going.")

        PlaceEntry(
            "Fiocco di Neve",
            "What is it" to "A small artisanal gelateria right next to the Pantheon, also known for its zabaione and rice-pudding (riso) flavours.",
            "Region" to "A Rome-only shop (not a chain). Centro Storico, Pantheon area - about 1 min from the Pantheon.",
            "Address" to "Via del Pantheon 51.",
            "Getting there" to "No metro at the Pantheon. Walk 10-15 min from Metro A Spagna or Barberini, or take a bus to Largo di Torre Argentina.",
            "Gluten free" to "Yes - a 100% gluten-free gelateria, including gluten-free cones and wafers. Celiac reviewers report no cross-contamination worries (Romewise, Tripadvisor).",
            "Hours and days" to "Daily, about 10:00 to midnight."
        )
        PlaceEntry(
            "MYO - La Gelateria",
            "What is it" to "A gelateria right on Campo de' Fiori serving gelato, cones, tiramisù and other desserts.",
            "Region" to "Centro Storico, Campo de' Fiori.",
            "Address" to "Piazza Campo de' Fiori 51.",
            "Getting there" to "Tram 8 or buses to Largo di Torre Argentina, then about 5 min walk.",
            "Gluten free" to "Reported as all gluten free, including the cones - suited to celiacs according to FindMeGlutenFree and Gluto user reports (not an official certification). Confirm at the counter.",
            "Hours and days" to "Daily about 12:00-23:00."
        )
        PlaceEntry(
            "Grom",
            "What is it" to "An Italian gelato chain making gelato 'come una volta' (the old-fashioned way), with seasonal fruit sorbets.",
            "Region" to "Founded in 2003 in Turin, Piedmont (northern Italy) by Guido Martinetti and Federico Grom; owned by Unilever since 2015. Several branches in Rome (below).",
            "Gluten free" to "Grom says all its gelato, sorbets and cones are gluten free (cones made in its own bakery). One older review said the cones were not - confirm at the counter, especially if celiac.",
            "Hours and days" to "Varies by branch (see below)."
        )
        SectionHeader("Grom Branches in Rome")
        LabeledText(
            "Via della Maddalena 30/A (Centro Storico, Pantheon / Campo Marzio)",
            "The original Rome shop, a few steps from the Pantheon. Daily about 11:00 to midnight, until 01:00 on Friday and Saturday."
        )
        LabeledText(
            "Corso Vittorio Emanuele II 69, 00186 Roma (Centro Storico, by Largo di Torre Argentina)",
            "Practically next door to Slow (Coffee, at no. 73), near the corner of Via di Torre Argentina and the Roman temple ruins. Opened in 2023. Many buses and tram 8 stop at Largo Argentina. " +
                "Hours (official Grom site): Sunday-Thursday 10:30-23:00, Friday-Saturday 10:30-23:30."
        )
        LabeledText(
            "Piazza Navona (corner of Via Agonale 3) (Centro Storico)",
            "About a 5-minute walk from Largo Argentina. Hours not confirmed - check."
        )
        LabeledText(
            "Via dei Giubbonari 53 (Centro Storico, Campo de' Fiori)",
            "About a 5-minute walk from Largo Argentina. Listed, but whether it is still open is unverified - check before going."
        )
        LabeledText(
            "Piazza dei Cinquecento (Termini station)",
            "Daily about 8:00-22:00 - handy when arriving or leaving by train."
        )
    }
}
