package com.example.italytravel.screens

import androidx.compose.runtime.Composable

private const val ABOUT = "About"
private const val FOOD = "Type of food"
private const val PRICES = "Prices"
private const val GF = "Gluten free"
private const val LOW_CARB = "Low carb"

@Composable
fun RomeFastFoodScreen(onBack: () -> Unit) {
    InfoScreen(title = "Rome - Fast Food Chains", onBack = onBack) {
        SectionHeader("Fast Food in Rome")
        BodyText(
            "All four chains have many branches around Rome - Termini station, the center, and both big shopping centers. " +
                "'Senza glutine' means gluten free. For low carb, a burger can usually be ordered without the bun (ask at the counter or look for the option on the ordering kiosk). " +
                "For celiacs, the real risk is cross-contamination in shared kitchens and fryers."
        )
        BodyText("Prices are approximate (2026) and vary by branch.")

        PlaceEntry(
            "McDonald's",
            ABOUT to "The American chain - the biggest fast food chain in Italy, many branches also have a McCafé coffee counter.",
            FOOD to "Burgers, chicken (McChicken, nuggets), fries, salads, desserts, coffee.",
            PRICES to "Big Mac about 6.50-6.90 euros; a McMenu (burger + fries + drink) about 9-12 euros, e.g. Big Mac or McChicken menu about 9.60 euros.",
            GF to "Yes - one item: the Gluten Free Burger, made with Schär. The gluten-free bun comes packaged and is prepared separately, and the AIC (Italian Celiac Association) confirms it is sold as 'senza glutine'. " +
                "Fries: McDonald's itself advises celiacs against fried items in restaurants without dedicated fryers and packaging procedures - treat the fries as NOT safe unless staff confirm a dedicated fryer. " +
                "Ask clearly: a 2026 complaint reports a branch that refused a gluten-free Happy Meal.",
            LOW_CARB to "Burgers without the bun, salads (choice varies by branch)."
        )
        PlaceEntry(
            "Old Wild West",
            ABOUT to "An Italian chain (Cigierre group) styled as an American Wild West steakhouse. A sit-down restaurant with table service, not a counter - so slower and more expensive than fast food.",
            FOOD to "Burgers, steaks, ribs, grilled and fried chicken, fried starters, fries, desserts.",
            PRICES to "Burgers about 12-17 euros (e.g. American Burger 13.90 euros); sharing platters for two about 35 euros.",
            GF to "Yes, at most branches - an AIC-agreed gluten-free menu that you must ask for: burgers with a gluten-free bun, chicken nuggets, a wrap, and at some branches fries and desserts, plus gluten-free beer. " +
                "Cutlery often comes sealed with the AIC logo. Not every branch offers it and the kitchen is not dedicated - check with the branch and tell staff you are celiac.",
            LOW_CARB to "The best of the four: steaks, ribs, grilled chicken, salads, or a burger without the bun."
        )
        PlaceEntry(
            "Burger King",
            ABOUT to "The American chain known for flame-grilled burgers.",
            FOOD to "Burgers (Whopper), chicken burgers and nuggets, fries, onion rings, desserts.",
            PRICES to "From about 1.80-2.10 euros (plain hamburger) up to about 14-18 euros (Triple Whopper menu); a regular Whopper menu is roughly 9-12 euros.",
            GF to "No - Burger King Italy has no gluten-free bun (there is a public petition asking for one). You can order a burger without the bun, but with a shared kitchen it is not safe for celiacs.",
            LOW_CARB to "Burgers without the bun, salads."
        )
        PlaceEntry(
            "KFC (Kentucky Fried Chicken)",
            ABOUT to "The American fried-chicken chain.",
            FOOD to "Breaded fried chicken pieces, buckets, wings, chicken burgers, fries.",
            PRICES to "About 12 euros per meal on average.",
            GF to "No - the chicken is breaded with wheat flour and fryers are shared; Italian reviews report nothing gluten free. " +
                "An older (2018) blog mentioned corn on the cob, plain salad and vanilla sundae, but with flour in the air and shared equipment it is best avoided if you are celiac.",
            LOW_CARB to "Limited - corn or a salad, or remove the breaded skin from the chicken. Not really a low-carb place."
        )

        PlaceEntry(
            "Starbucks",
            ABOUT to "The American coffee chain - a latecomer to Italy (first store in Milan in 2018). Only a few branches in Rome: Piazza di Montecitorio in the center and Termini station (including the 'Terrazza' upper level) - check the Starbucks app for others.",
            FOOD to "Espresso drinks, Frappuccinos, filter coffee, pastries, muffins, sandwiches.",
            PRICES to "Espresso about 1.80 euros, cappuccino about 2.50-3 euros, Frappuccino about 4.70-5.50 euros - much more than an Italian bar (espresso about 1.20-1.50 euros).",
            GF to "No confirmed gluten-free food: pastries contain gluten and are handled on shared counters - not safe for celiacs. Plain coffee drinks are fine.",
            LOW_CARB to "Espresso, americano, unsweetened coffee with milk; avoid syrups and Frappuccinos."
        )
        PlaceEntry(
            "Alice Pizza",
            ABOUT to "A Roman chain of pizza al taglio (cut-to-size pizza) with many branches around Rome, e.g. Termini station (Piazza dei Cinquecento side) and Via delle Grazie near the Vatican, plus many in the suburbs. Hours vary by branch, typically about 8:00/10:00 to 21:00/22:00.",
            FOOD to "Tray pizza cut to the size you want with many toppings, supplì and other fried snacks, drinks.",
            PRICES to "Sold by weight - a meal with a drink is about 7-8 euros (e.g. a combo with 350 g+ of pizza).",
            GF to "No gluten-free options found - the pizza is wheat-based. Not for celiacs.",
            LOW_CARB to "Not really - choose vegetable toppings, but the base is dough."
        )

        GroupHeader("Celiac-Safe Burgers")
        BodyText("For a fully gluten-free burger or pizza, see Rome -> Gluten Free (e.g. Mama Eat), and check the Find Me Gluten Free app.")
    }
}
