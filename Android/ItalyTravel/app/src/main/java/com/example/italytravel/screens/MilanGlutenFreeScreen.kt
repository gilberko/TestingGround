package com.example.italytravel.screens

import androidx.compose.runtime.Composable

private const val SAFETY = "Gluten-free safety"
private const val FOOD = "Sort of food"
private const val ABOUT = "About"
private const val REGION = "Region in Milan"
private const val ADDRESS = "Address"
private const val GETTING_THERE = "Getting there"
private const val HOURS = "Hours and days"

@Composable
fun MilanGlutenFreeScreen(onBack: () -> Unit) {
    InfoScreen(title = "Milan - Gluten Free", onBack = onBack) {
        SectionHeader("Gluten Free in Milan")
        BodyText(
            "Like the rest of Italy, Milan is easy for celiacs: 'senza glutine' means gluten free, and many places are certified by the AIC (Associazione Italiana Celiachia). " +
                "DEDICATED = the whole place is gluten free (safest). OPTIONS = a normal kitchen with a gluten-free menu - tell the staff you are celiac ('sono celiaco / celiaca') and ask how it is prepared."
        )
        BodyText(
            "For more places, use the Find Me Gluten Free app (findmeglutenfree.com): search Milan, filter by 'dedicated gluten-free' and read recent reviews from celiacs. " +
                "Hours below come from listings that often disagree - check before going."
        )

        GroupHeader("Dedicated Gluten Free")

        PlaceEntry(
            "Mama Eat (Milan)",
            SAFETY to "The same gluten-free chain as in Rome (see Rome -> Gluten Free): every recipe is available gluten free (and lactose free), and each restaurant has two separate kitchens to avoid contamination; recognized by the AIC. Celiac reviewers say you can order anything - still say you are celiac.",
            FOOD to "Italian restaurant and pizzeria: pizza, pasta, fried starters, mains and desserts.",
            ABOUT to "Two branches reported in Milan (below). Very busy - book ahead in the evening. " +
                "Don't confuse it with 'Procaccini', a different fine-dining restaurant across the street at Via Procaccini 33.",
            HOURS to "Daily about 11:00 to midnight (listing; check before going)."
        )
        LabeledText(
            "Procaccini - Via Giulio Cesare Procaccini 32",
            "Region in Milan: Sempione / Chinatown area, north-west of the center (near Paolo Sarpi). Getting there: tram 10, 12 or 14 to Via Procaccini; or Metro M5 Monumentale / M2 Garibaldi, about 10-15 min walk. The best-known Milan branch."
        )
        LabeledText(
            "Missori - Via Zebedia 9",
            "Region in Milan: Centro Storico, near Piazza Missori, about 10 min walk south of the Duomo. Getting there: Metro M3 Missori, about 3 min walk. A newer branch - listed by the Gluto app, not confirmed on the official site; check before going."
        )

        PlaceEntry(
            "GluFree Bakery",
            SAFETY to "Dedicated - everything is gluten free, made in an AIC-certified bakery; most products are also lactose free.",
            FOOD to "Bakery, pastry shop and rosticceria (savoury snacks): bread, croissants (plain, pistachio, custard-filled), fruit tarts, muffins, cakes, savoury pastries, plus gluten-free beers. Sunday brunch reported.",
            ABOUT to "Opened in 2014; a modern, cosy shop where you can sit for breakfast or take things away - a good place to stock up on gluten-free bread and pastries.",
            REGION to "Porta Romana / Crocetta, just south of the city centre.",
            ADDRESS to "Via Curtatone 6. Phone +39 02 5401 9723.",
            GETTING_THERE to "Metro M3 (yellow) Crocetta or Porta Romana, a few minutes' walk.",
            HOURS to "Reported Monday-Friday 8:00-19:00, Saturday 8:00-18:00, Sunday 9:00-16:00 with brunch about 12:00-14:00 - but one listing says closed on Sunday. Call or check before going."
        )

        PlaceEntry(
            "Bistrò (Corso Magenta)",
            SAFETY to "Dedicated - listed on Find Me Gluten Free as 100% gluten free, and reviewers say no dish contains gluten. One reviewer was not completely sure, so mention you are celiac anyway.",
            FOOD to "French-bistro style restaurant: burgers, sandwiches, pasta, risotto (e.g. porcini and truffle), starters and home-made desserts.",
            ABOUT to "A small, cosy place - book ahead, especially if you are more than two. The gluten-free sign outside is not very visible, so look for the address. Also takeaway and delivery.",
            REGION to "Centro Storico west, on Corso Magenta (the street that leads to Santa Maria delle Grazie / The Last Supper).",
            ADDRESS to "Corso Magenta 9.",
            GETTING_THERE to "Metro M1 (red) Cairoli, about 5 min walk; or M1/M2 Cadorna.",
            HOURS to "Not confirmed - check Google Maps (lunch and dinner)."
        )

        GroupHeader("Gluten-Free Options (Not Dedicated)")

        PlaceEntry(
            "Peperino e Milano",
            SAFETY to "Options - AIC-certified gluten-free pizza with a separate gluten-free menu, and the pizzeria has been part of the AIC network for a long time. " +
                "Reviewers call it excellent and safe for celiacs. Gluten-free pizza is a main focus of the place, but it is not confirmed to be 100% dedicated - tell the staff you are celiac.",
            FOOD to "Pizzeria: gluten-free pizzas (about 10-16.50 euros, Gambero Rosso), gluten-free fried starters and gluten-free desserts.",
            ABOUT to "A pizzeria recommended by the food guide Gambero Rosso among Milan's best gluten-free pizzas; reviewers mention good prices and friendly service.",
            REGION to "Porta Garibaldi / Moscova, north of Brera.",
            ADDRESS to "Viale Francesco Crispi 2.",
            GETTING_THERE to "Metro M2 (green) Moscova or M2/M5 Garibaldi FS, about 5-10 min walk.",
            HOURS to "Not confirmed - check Google Maps (pizzerias mostly open in the evening)."
        )
    }
}
