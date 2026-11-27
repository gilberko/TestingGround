package com.example.italytravel.screens

import androidx.compose.runtime.Composable

private const val REGION = "Region in Milan"
private const val ADDRESS = "Address"
private const val GETTING_THERE = "Getting there"
private const val ABOUT = "About"
private const val STYLE = "Italian or specialty"
private const val BEANS = "Coffee beans for sale"
private const val HOURS = "Hours and days"

@Composable
fun MilanCoffeeScreen(onBack: () -> Unit) {
    InfoScreen(title = "Milan - Coffee", onBack = onBack) {
        SectionHeader("How Coffee Works in Milan")
        BodyText(
            "The same as in Rome: espresso-based coffee drunk quickly standing at the counter (al banco), often paying first at the cassa and showing the receipt to the barista. " +
                "Sitting at a table - especially in the Galleria or on Piazza del Duomo - costs noticeably more. " +
                "In Milan the breakfast pastry is usually called a 'brioche' instead of a cornetto."
        )
        BodyText("Hours below come from listings that often disagree - check Google Maps or the place's Instagram before going.")

        GroupHeader("Historic Cafés and Pastry Shops")

        PlaceEntry(
            "Motta Milano 1928 (Galleria / Duomo)",
            REGION to "Centro Storico, Piazza del Duomo - between the Duomo and the Galleria Vittorio Emanuele II.",
            ADDRESS to "Ground floor of 'Il Mercato del Duomo', at the corner of Piazza del Duomo and the Galleria Vittorio Emanuele II.",
            GETTING_THERE to "Metro M1 (red) or M3 (yellow) to Duomo - the exit is right on the square, about 1 min walk.",
            ABOUT to "Angelo Motta - the man behind the famous Motta panettone - opened his first bar here in the Galleria in 1928, and it became a favourite of the Milanese. " +
                "The bar was restored and reopened in 2018 with an elegant retro look (walnut counters, brass details, marble, curved pastry cases; the design won a German Design Award mention). " +
                "Coffee at the counter, pastries, cakes and light meals; it also hosts a counter of Sal De Riso, a famous pastry chef from the Amalfi Coast. " +
                "A great stop before or after visiting the Duomo. Motta panettone is likely sold around Christmas, but this is not confirmed.",
            STYLE to "Italian (historic bar and pasticceria).",
            BEANS to "Not confirmed - the Mercato del Duomo building has food shops; ask inside.",
            HOURS to "Daily 7:00-23:00 (venue's own page)."
        )

        PlaceEntry(
            "Panarello",
            ABOUT to "A historic pastry shop from Genoa (founded there in 1885 by Francesco Panarello); the first Milan shop opened in 1930. " +
                "Each branch is a pastry shop, café (caffetteria) and gelateria. Famous for cannoncini alla crema (puff-pastry horns filled with custard), crostate (fruit and jam tarts) " +
                "and Genoese specialties such as pandolce (the Genoese Christmas cake) and canestrelli biscuits. Good for an Italian breakfast: cappuccino and a brioche at the counter.",
            STYLE to "Italian (traditional pasticceria-bar).",
            BEANS to "Not confirmed - known for pastries rather than its own coffee; ask in the shop.",
            HOURS to "Most branches daily, about 7:30/8:00 to 19:00/19:30 (listings differ - check each branch)."
        )
        LabeledText(
            "Duomo - Via Speronari 3",
            "Region in Milan: Centro Storico, a small street just south of Piazza del Duomo (towards Via Torino). Getting there: Metro M1/M3 Duomo, about 3 min walk. The most central branch."
        )
        LabeledText(
            "San Nazaro - Piazza San Nazaro in Brolo 15",
            "Region in Milan: Centro Storico, by the Basilica of San Nazaro on Corso di Porta Romana. Getting there: Metro M3 Missori, about 5 min walk."
        )
        LabeledText(
            "Moscova - Via della Moscova 52",
            "Region in Milan: Moscova / Brera, near Porta Garibaldi. Getting there: Metro M2 (green) Moscova, a few minutes' walk."
        )
        LabeledText(
            "Belfiore - Via Belfiore 10",
            "Region in Milan: west Milan near Corso Vercelli (a shopping street). A newer branch. Getting there: Metro M1 Wagner or Conciliazione, about 10 min walk."
        )
        LabeledText(
            "Via Tolstoi 1 (unconfirmed)",
            "Region in Milan: south-west (Zona 6), corner of the Via Savona arcades. Listed on TripAdvisor only - may be closed; check before going."
        )
    }
}
