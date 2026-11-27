package com.example.italytravel.screens

import androidx.compose.runtime.Composable

@Composable
fun RomeKoreanFoodScreen(onBack: () -> Unit) {
    InfoScreen(title = "Rome - Korean Food", onBack = onBack) {
        PlaceEntry(
            "StarBaps",
            "About" to "A small, family-run Korean restaurant (open since 2018) with a short, home-style menu. A popular choice when you want a break from pasta and pizza.",
            "What to eat" to "Bibimbap (rice bowl with vegetables, egg and meat; can be made vegetarian or vegan on request), tteokbokki (spicy rice cakes), bulgogi (marinated beef), mandu (dumplings), soups and bento / rice-bowl sets.",
            "Region in Rome" to "Monti / Esquilino, a few minutes' walk from the Basilica of Santa Maria Maggiore.",
            "Address" to "Via di Santa Maria Maggiore 116, 00184 Roma.",
            "Getting there" to "Metro B Cavour (about 5 min walk), or Termini station (Metro A + B, trains, airport buses; about 10 min walk).",
            "Hours and days" to "Tuesday to Sunday, lunch about 12:00-15:00 and dinner about 18:00-21:00/21:30. Closed Monday. Listings differ slightly, so check Google Maps before you go.",
            "Gluten free" to "Partly, and only on request. Diners report (FindMeGlutenFree, crowd-sourced) that the bibimbap can be made gluten free, served with tamari / gluten-free soy sauce instead of gochujang, and that staff understood the request. It is NOT a dedicated gluten-free kitchen, so there is a cross-contamination risk for celiacs. Tteokbokki sauce, bulgogi marinade and dumplings are not confirmed gluten free (gochujang and regular soy sauce usually contain wheat). Ask the staff every time."
        )
        PlaceEntry(
            "Igio (IGIO Ristorante Coreano)",
            "About" to "Long-running Korean restaurant in Trastevere, popular with locals and visitors.",
            "What to eat" to "Korean fried chicken, pork belly BBQ grilled at your table, kimchi stew with pork ribs, bibimbap, bulgogi and kimchi.",
            "Region in Rome" to "Trastevere (near Piazza di San Cosimato).",
            "Address" to "Via Roma Libera 26, 00153 Roma (some listings say 24).",
            "Getting there" to "Tram 8 to Viale Trastevere, then about 5 min walk.",
            "Hours and days" to "Tuesday-Sunday, lunch about 12:00-15:00 and dinner about 18:30-22:00/22:45. Closed Monday. Sources differ on closing time - check.",
            "Gluten free" to "The menu marks gluten-free options (also vegan / vegetarian). Not a dedicated gluten-free kitchen - tell the staff if you are celiac. $GF_WARNING"
        )
        PlaceEntry(
            "Koreamor",
            "About" to "Korean restaurant in the lively Pigneto neighborhood, praised for good value.",
            "What to eat" to "Fried chicken, kimchi, mandu (dumplings), Korean pancakes (jeon) and barbecue dishes.",
            "Region in Rome" to "Pigneto (east Rome).",
            "Address" to "Via Luigi Filippo de Magistris 72/78, 00176 Roma.",
            "Getting there" to "Metro C Pigneto, then about 5-10 min walk.",
            "Hours and days" to "Lunch about 12:30-15:00 and dinner about 19:00-22:30. Closed Wednesday; some listings say no lunch on Monday (and Tuesday) - call 06 7016 2215 to check.",
            "Gluten free" to "No gluten-free information found - ask the staff. $GF_WARNING"
        )
        PlaceEntry(
            "Galbi",
            "About" to "Korean barbecue restaurant: meat (bulgogi, galbi - marinated short ribs) grilled over oak wood, plus vegetarian dishes from an open kitchen. Around 45 euros per person without drinks. Book for weekends.",
            "What to eat" to "Korean BBQ - bulgogi and galbi - with side dishes (banchan), plus vegetarian options.",
            "Region in Rome" to "Salario / Trieste, near Piazza Fiume (north of the center).",
            "Address" to "Via Cremera 21, 00198 Roma (one listing says 25).",
            "Getting there" to "Buses to Piazza Fiume, then a short walk. No metro station right next to it.",
            "Hours and days" to "Per its own site: Tuesday lunch 12:00-14:30, Wednesday-Friday 12:00-14:30 and 19:00-22:00, Saturday dinner 19:00-22:00, closed Sunday and Monday. Other listings differ - call 06 8842132.",
            "Gluten free" to "Listed with gluten-free options (Tripadvisor). Not a dedicated gluten-free kitchen - BBQ marinades usually contain soy sauce, so ask. $GF_WARNING"
        )
        PlaceEntry(
            "Midam (미담식당)",
            "About" to "Korean restaurant near Termini with a big menu and generous portions; the menu is explained in Italian, so ordering is easy.",
            "What to eat" to "Dumplings, Korean chicken with sauce, and a wide range of Korean home-style dishes.",
            "Region in Rome" to "Esquilino, near Termini station.",
            "Address" to "Via Filippo Turati 54-56, 00185 Roma.",
            "Getting there" to "About 5 min walk from Termini (Metro A + B, trains), or Metro A Vittorio Emanuele.",
            "Hours and days" to "Lunch about 12:15-15:00 and dinner about 18:15-22:15. Wednesday dinner only. Closed Tuesday. Check before going (06 9761 6155).",
            "Gluten free" to "No gluten-free information found - ask the staff. $GF_WARNING"
        )
    }
}

private const val GF_WARNING =
    "Remember: gochujang, regular soy sauce, marinades and fried batter usually contain wheat."
