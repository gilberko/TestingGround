package com.example.italytravel.screens

import androidx.compose.runtime.Composable

private const val WHAT = "What it is"
private const val WHERE = "Where to try"
private const val GF = "Gluten free"

@Composable
fun RomeFoodScreen(onBack: () -> Unit) {
    InfoScreen(title = "Rome - Food", onBack = onBack) {
        SectionHeader("Roman Food")
        BodyText(
            "Roman cooking is simple and strong: pasta with pecorino and guanciale (cured pork cheek), artichokes, fried starters, and pizza both round and by the slice. " +
                "Gluten-free versions are easy to find at dedicated places - see Rome -> Gluten Free. Restaurant names below are popular choices; book well-known trattorias ahead."
        )

        GroupHeader("Pizza and Snacks")
        PlaceEntry(
            "Pizza (pizza tonda romana)",
            WHAT to "Round Roman pizza: very thin and crispy ('scrocchiarella'), unlike the soft, puffy Neapolitan pizza. Pizzerias mostly open in the evening. Start with fried supplì (rice balls with mozzarella).",
            WHERE to "Da Remo (Testaccio), Emma (near Campo de' Fiori), Ai Marmi (Trastevere), Pizza in Trevi (Trevi).",
            GF to "Mama Eat (100% gluten free), Pizza in Trevi, Il Miraggio and La Sagrestia (gluten-free menus) - see Gluten Free."
        )
        PlaceEntry(
            "Pizza al taglio (pizza cut to size)",
            WHAT to "Pizza baked in big trays, cut with scissors to the size you want and sold by weight - Rome's favourite quick lunch. Classics: pizza bianca (oil and salt), pizza rossa (tomato), potato, mortadella.",
            WHERE to "See Rome -> Forno and Rome Pizza. Also Pizzarium by Gabriele Bonci (near the Vatican, Metro A Cipro) and the Alice Pizza chain.",
            GF to "New Food Gluten Free at Ponte Sisto (dedicated bakery with pizza slices) and Mama Eat."
        )
        GroupHeader("Pastries and Breakfast")
        PlaceEntry(
            "Cornetto",
            WHAT to "The Italian breakfast pastry - softer and sweeter than a French croissant. Plain (vuoto) or filled with custard (crema), jam (marmellata) or Nutella. " +
                "Romans eat it standing at the bar counter with a cappuccino.",
            WHERE to "Any bar or pastry shop in the morning - e.g. the cafés on the Coffee screen.",
            GF to "New Food Gluten Free at Ponte Sisto and La Pasticciera (Via Varese 43, near Termini) - both dedicated gluten-free bakeries."
        )
        PlaceEntry(
            "Bombolone",
            WHAT to "An Italian doughnut: fried, rolled in sugar and filled with custard (crema), Nutella or jam - eaten for breakfast or as a snack. " +
                "Rome's own cousin is the maritozzo: a soft sweet bun split and filled with whipped cream.",
            WHERE to "Most pastry shops (pasticcerie) and bars, best in the morning when fresh.",
            GF to "La Pasticciera near Termini - a 100% gluten-free bakery run by a celiac owner, with filled bomboloni (go in the morning before they sell out). Also Celiachiamo gluten-free shops."
        )
        PlaceEntry(
            "Sfogliatella",
            WHAT to "A Neapolitan shell-shaped pastry filled with ricotta, semolina and candied citrus. Two kinds: riccia (crisp, many thin crackly layers) and frolla (soft shortcrust).",
            WHERE to "Neapolitan-style bars and pastry shops. Toraldo at Plebiscito (Neapolitan coffee roaster, see Coffee) is a good place to ask - not confirmed that it sells them.",
            GF to "Rarely made gluten free - ask at dedicated gluten-free bakeries (New Food, La Pasticciera)."
        )
        PlaceEntry(
            "Cannoli",
            WHAT to "Sicilian fried pastry tubes filled with sweet sheep's-milk ricotta, often with chocolate chips, pistachio or candied orange. The best are filled to order so the shell stays crisp.",
            WHERE to "Sicilian pastry shops, e.g. La Cannoleria Siciliana.",
            GF to "La Pasticciera near Termini (dedicated, freshly filled), New Food (dedicated), Le Altre Farine del Mulino near the Vatican (100% gluten free, filled to order, pricier). " +
                "La Cannoleria Siciliana has some gluten-free cannoli but is not dedicated - not safe for celiacs."
        )

        GroupHeader("Pasta")
        BodyText(
            "Rome's famous pastas are simple: a few ingredients - guanciale (cured pork cheek), pecorino romano, black pepper, egg, tomato. Typical shapes are tonnarelli, rigatoni, spaghetti and bucatini. " +
                "Popular trattorias: Felice a Testaccio, Da Enzo al 29 (Trastevere, no reservations - expect a queue), Armando al Pantheon, Roscioli (near Campo de' Fiori). " +
                "Gluten free: Mama Eat (100% gluten free), Pasta in Corso near the Pantheon, La Sagrestia (gluten-free pasta on request); Felice makes gluten-free cacio e pepe on request. Not at Da Enzo (sauces made with flour)."
        )
        PlaceEntry(
            "Carbonara",
            WHAT to "Pasta with egg yolk, crisp guanciale, pecorino romano and lots of black pepper - creamy from the eggs and cheese only. Never cream, garlic or ham.",
            WHERE to "Roscioli, Armando al Pantheon, Felice a Testaccio and most Roman trattorias.",
            GF to "Mama Eat ('A carbonara', about 13-15 euros), Pasta in Corso near the Pantheon, La Sagrestia (gluten-free pasta on request)."
        )
        PlaceEntry(
            "Amatriciana",
            WHAT to "Tomato sauce with guanciale and pecorino, a little chili and white wine. Named after the town of Amatrice; the Roman classic is bucatini all'amatriciana (thick hollow spaghetti).",
            WHERE to "Any traditional trattoria, e.g. Da Enzo al 29, Felice a Testaccio.",
            GF to "Mama Eat (100% gluten free), Pasta in Corso near the Pantheon, La Sagrestia (gluten-free pasta on request)."
        )
        PlaceEntry(
            "Gricia",
            WHAT to "The 'white amatriciana': guanciale, pecorino and black pepper, no tomato - often with rigatoni or tonnarelli. Said to be the ancestor of amatriciana and carbonara.",
            WHERE to "Roman trattorias - Roscioli and Felice a Testaccio are well known for it.",
            GF to "Mama Eat (100% gluten free), Pasta in Corso near the Pantheon, La Sagrestia (gluten-free pasta on request)."
        )
        PlaceEntry(
            "Cacio e pepe",
            WHAT to "Pasta (usually tonnarelli or spaghetti) with only two ingredients: pecorino romano cheese and black pepper, turned creamy with starchy pasta water. Simple, but hard to make well.",
            WHERE to "Felice a Testaccio (they toss it at your table), Da Enzo al 29, Roscioli.",
            GF to "Mama Eat ('Er cacio e pepe'), Felice a Testaccio (on request), Osteria Cacio e Pepe (gluten-free pasta on request)."
        )
        PlaceEntry(
            "Lasagna",
            WHAT to "Baked layers of fresh egg pasta with ragù (meat sauce), béchamel and parmigiano. Originally from Emilia-Romagna, but common in Rome - a classic Sunday dish.",
            WHERE to "Traditional trattorias; ready-made portions at Forno Ceralli near the Pantheon (see Forno and Rome Pizza).",
            GF to "Mama Eat (lasagna listed on its menus, e.g. Borgo Pio near the Vatican)."
        )
        PlaceEntry(
            "Pasta al ragù and 'bolognese'",
            WHAT to "Ragù is a meat sauce slow-cooked for hours with tomato and wine. In Bologna the real dish is tagliatelle al ragù (ragù alla bolognese). " +
                "'Spaghetti bolognese' is a foreign invention - Romans rarely eat it, and seeing it on a menu often means a tourist restaurant. In Rome order fettuccine al ragù instead.",
            WHERE to "Trattorias - look for fettuccine or tagliatelle al ragù.",
            GF to "Mama Eat and other places with gluten-free pasta (see Gluten Free)."
        )

        PlaceEntry(
            "Rigatoni con la pajata",
            WHAT to "Rigatoni in tomato sauce with pajata - the intestines of a milk-fed calf, cooked so the milk inside thickens like cheese. A dish of Testaccio's old slaughterhouse 'quinto quarto' (offal) tradition - for adventurous eaters.",
            WHERE to "Old-style trattorias in Testaccio - not on every menu.",
            GF to "Ask - only with gluten-free pasta at places that make it."
        )
        PlaceEntry(
            "Fettuccine Alfredo",
            WHAT to "Fresh fettuccine tossed with butter and parmigiano. Invented in Rome in 1914 by Alfredo Di Lelio, but famous mostly abroad - Romans rarely order it, and the cream-and-chicken version is an American invention.",
            WHERE to "Alfredo alla Scrofa (Via della Scrofa, near Piazza Navona), where it is still mixed at the table.",
            GF to "Ask for gluten-free pasta at Mama Eat or Pasta in Corso; the sauce itself is gluten free."
        )
        PlaceEntry(
            "Pasta e ceci",
            WHAT to "A thick, home-style soup of small pasta and chickpeas with rosemary and garlic - traditionally eaten in Rome on Fridays.",
            WHERE to "Traditional trattorias, especially on Fridays.",
            GF to "Chickpeas are gluten free - only with gluten-free pasta at places that make it."
        )

        GroupHeader("Other Roman Dishes")
        PlaceEntry(
            "Polpette",
            WHAT to "Meatballs Roman-style, slow-cooked in tomato sauce (polpette al sugo) - a home-style trattoria main course, usually served without pasta. " +
                "Note: 'polpetti' (with an i) means baby octopus (polpetti in umido) - a different dish.",
            WHERE to "Traditional trattorias.",
            GF to "Polpette usually contain breadcrumbs, so they are NOT gluten free unless the place makes a special version - ask at gluten-free places such as Mama Eat."
        )
        PlaceEntry(
            "Carciofi alla romana (Roman artichokes)",
            WHAT to "Whole artichokes braised slowly with garlic, wild mint (mentuccia), parsley and olive oil - soft and tender. In season about November to April. " +
                "Their Jewish cousin, carciofi alla giudia, is the whole artichoke deep-fried until crisp like a flower.",
            WHERE to "Roman trattorias in season; for carciofi alla giudia, the Jewish Ghetto (e.g. Nonna Betta, Ba'Ghetto, Giggetto).",
            GF to "Naturally gluten free (no flour). For the fried giudia version ask whether the fryer is shared."
        )
        PlaceEntry(
            "Insalata caprese (Caprese salad)",
            WHAT to "Slices of tomato and fresh mozzarella (best is mozzarella di bufala) with basil and olive oil - the colors of the Italian flag. From the island of Capri, eaten all over Italy as a light lunch or starter.",
            WHERE to "Almost every trattoria and café.",
            GF to "Naturally gluten free (just keep the bread on the side)."
        )
        PlaceEntry(
            "Pecorino Romano",
            WHAT to "Hard, salty sheep's-milk cheese with protected origin (PDO) - the base of carbonara, amatriciana, gricia and cacio e pepe.",
            WHERE to "Grated on any Roman pasta. To take home: a vacuum-packed wedge from Salumeria Roscioli, Castroni (Prati), the Campo de' Fiori market or any supermarket.",
            GF to "Naturally gluten free."
        )

        GroupHeader("Roman-Jewish Food (Cucina Ebraica)")
        BodyText(
            "'Ebraico / ebraica' means Jewish. Rome's Jewish community has its own cooking tradition (cucina giudaico-romanesca), more than 2,000 years old - lots of frying, artichokes and salt cod. " +
                "The best place to try it is the Jewish Ghetto (see Attractions); many places are kosher and closed on Shabbat (Friday evening to Saturday)."
        )
        PlaceEntry(
            "Carciofi alla giudia",
            WHAT to "'Jewish-style artichokes': a whole artichoke flattened and deep-fried twice until it opens like a crisp golden flower. In season about November to April.",
            WHERE to "Jewish Ghetto restaurants such as Nonna Betta, Ba'Ghetto and Giggetto.",
            GF to "No flour - naturally gluten free, but ask whether the fryer is shared with battered food."
        )
        PlaceEntry(
            "Pizza ebraica",
            WHAT to "Not a pizza: a dense, chewy sweet bar full of almonds, pine nuts, raisins and candied fruit, a little burnt on top. The famous one is from Boccione, the old kosher bakery of the Ghetto.",
            WHERE to "Boccione, Via del Portico d'Ottavia 1 - best warm on Friday morning. About Sunday-Thursday 7:30/8:00-19:00/19:30, Friday until about 15:30; closed Saturday and Jewish holidays, often closed for much of August (sources differ).",
            GF to "Contains wheat flour - not gluten free."
        )
        PlaceEntry(
            "Crostata di ricotta e visciole",
            WHAT to "A tart filled with sweet ricotta and sour cherries (visciole) - a Roman-Jewish classic.",
            WHERE to "Boccione (see above) and Roman pastry shops.",
            GF to "Contains wheat flour - not gluten free."
        )
        PlaceEntry(
            "Fried starters: fiori di zucca, baccalà, aliciotti",
            WHAT to "Fiori di zucca: courgette (zucchini) flowers stuffed with mozzarella and anchovy, battered and fried. Filetti di baccalà: fried salt-cod fillets. Aliciotti con l'indivia: a baked dish of fresh anchovies and curly endive.",
            WHERE to "Jewish Ghetto restaurants (Nonna Betta, Ba'Ghetto, Giggetto) and Roman pizzerias, where fried starters come before the pizza.",
            GF to "The fried ones are battered with wheat flour - not gluten free (Mama Eat makes gluten-free fried starters)."
        )
    }
}
