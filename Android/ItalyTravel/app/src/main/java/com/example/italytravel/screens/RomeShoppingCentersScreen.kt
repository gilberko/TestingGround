package com.example.italytravel.screens

import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable

@Composable
fun RomeShoppingCentersScreen(onBack: () -> Unit) {
    InfoScreen(title = "Rome - Shopping Centers", onBack = onBack) {
        SectionHeader("Shopping Centers in Rome")
        BodyText(
            "Rome's big malls (centri commerciali) are in the suburbs, outside the historic center, and each has well over 150 shops. " +
                "They are open 7 days a week, including Sundays and most public holidays, usually closing only on December 25, December 26 and January 1. " +
                "On some holidays (Easter, May 1) hours may be reduced - check the mall's website."
        )
        BodyText("Note: Media Markt is called MediaWorld in Italy (same company, MediaMarktSaturn).")

        GroupHeader("Porta di Roma")
        placePhotos["Porta di Roma"]?.let { PlacePhoto(it, "Porta di Roma") }
        LabeledText("Region in Rome", "Bufalotta, north-east Rome, next to the GRA ring road.")
        LabeledText("Address", "Via Alberto Lionello 201.")
        LabeledText(
            "Getting there",
            "Metro B1 to Jonio, then bus 38 or 80 to Valle Melaina / Ventotene (some guides say buses 338 / 344 - check Moovit or Google Maps). " +
                "Bus 38 also runs directly from Termini and bus 80 from Piazza Venezia. By car: GRA exit Bufalotta, free parking."
        )
        LabeledText(
            "How big",
            "Very big: about 161 shops in the gallery, plus a retail park next door with IKEA and Leroy Merlin, a Conad supermarket and a UCI IMAX cinema."
        )
        LabeledText("Food court", "Yes - about 27 restaurants, cafés and fast food places.")
        LabeledText(
            "Hours",
            "Every day: shops 10:00-22:00, restaurants 10:00-23:00, Conad supermarket 9:00-22:00. Closed December 25, December 26 and January 1 (official site)."
        )
        LabeledText("Weekends and holidays", "Open Saturday and Sunday, and on most public holidays.")
        LabeledText("New or old", "Modern - opened in 2007; MediaWorld rebuilt its store as a 'Tech Village' in 2022.")
        SectionHeader("Stores at Porta di Roma")
        StoreCheck("OVS", true, "listed as 'OVS Kids' - may be the kids' store only")
        StoreCheck("Legami", true, "official Legami store list, 10:00-22:00")
        StoreCheck("Deichmann", false, "not in Deichmann's Rome store list")
        StoreCheck("Normal", true, "opened March 2025, one of Normal's first stores in Italy")
        StoreCheck("Zara", true)
        StoreCheck("Media Markt (MediaWorld)", true, "the MediaWorld Tech Village")
        HorizontalDivider()

        GroupHeader("Euroma2")
        placePhotos["Euroma2"]?.let { PlacePhoto(it, "Euroma2") }
        LabeledText("Region in Rome", "EUR, south Rome.")
        LabeledText("Address", "Via Cristoforo Colombo, corner of Viale dell'Oceano Pacifico 83.")
        LabeledText(
            "Getting there",
            "Metro B to EUR Palasport, then a 15-25 min walk or a short taxi ride (about 10-12 euros); or Metro B to EUR Magliana and bus 762 (about 8 min); or bus 779 from EUR Palasport. " +
                "By car: free parking for the first 3 hours."
        )
        LabeledText(
            "How big",
            "Very big: about 160 shops on several floors, including an Apple Store, a LEGO Store, a large Unieuro electronics store and a hypermarket."
        )
        LabeledText("Food court", "Yes.")
        LabeledText("Hours", "Every day: shops 10:00-21:00, food court 10:00-22:00, hypermarket 9:00-21:00.")
        LabeledText("Weekends and holidays", "Open Saturday and Sunday, and on most public holidays - check its site for Christmas / New Year.")
        LabeledText("New or old", "Modern - opened at the end of 2007 (one guide says 2008); markets itself as 'the concept mall'.")
        SectionHeader("Stores at Euroma2")
        StoreCheck("OVS", false, "not in the store lists")
        StoreCheck("Legami", true, "official Legami store list, 10:00-21:00")
        StoreCheck("Deichmann", true, "listed in a store directory (older source) - check")
        StoreCheck("Normal", false, "Normal's Rome stores are at RomaEst and Porta di Roma")
        StoreCheck("Zara", true, "official Zara store list, 10:00-21:00")
        StoreCheck("Media Markt (MediaWorld)", false, "the big electronics store here is Unieuro")
        HorizontalDivider()

        BodyText(
            "Shops open and close - before a special trip, check the mall's own store directory (euroma2.it/negozi, porta-di-roma.klepierre.it)."
        )
    }
}
