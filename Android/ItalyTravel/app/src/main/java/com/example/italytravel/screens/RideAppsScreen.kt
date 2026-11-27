package com.example.italytravel.screens

import androidx.compose.runtime.Composable

private const val WHAT = "What it is"
private const val DRIVERS = "Taxi or its own drivers?"
private const val PRICES = "Prices"
private const val TIPS = "Tips"

@Composable
fun RideAppsScreen(onBack: () -> Unit) {
    InfoScreen(title = "Public Transportation Apps", onBack = onBack) {
        SectionHeader("Taxi Apps and Ride Apps in Italy")
        BodyText(
            "In Italy there are two kinds of cars you can book: TAXIS - white licensed cabs with a meter, which wait at taxi stands - and NCC ('noleggio con conducente') - licensed private-hire cars with a professional driver, usually dark sedans or vans. " +
                "NCC cars must be booked in advance (e.g. through an app); they cannot be hailed on the street or use taxi stands. " +
                "Ride-sharing with ordinary drivers in their own private cars (like UberX / UberPop elsewhere) is not allowed in Rome."
        )
        BodyText(
            "In short: for a normal metered taxi use FREENOW, itTaxi or the 'Taxi' option inside Uber. Uber Black / Comfort and Bolt send private NCC cars, not taxis. " +
                "Rome taxis also have fixed fares from the airports (see Rome -> Public Transportation)."
        )

        PlaceEntry(
            "Uber",
            WHAT to "The American ride app. It works in Rome, but not like in most countries.",
            DRIVERS to "Both: Uber Black (and the newer, cheaper Uber Comfort, launched in Italy in July 2026) use licensed NCC drivers with their own cars. " +
                "The app can also show a regular taxi option - a normal licensed white taxi booked through Uber's agreement with itTaxi. UberX with private drivers started only in Calabria in 2026, not in Rome.",
            PRICES to "Uber Black is usually more expensive than a taxi; the taxi option charges the normal meter fare plus a small booking fee. The price is shown before you book.",
            TIPS to "Handy if you already have the app and a card on file. Check whether you are booking 'Taxi' or 'Black / Comfort' - they are different services."
        )
        PlaceEntry(
            "Bolt",
            WHAT to "The European ride app (from Estonia). Launched in Rome on 18 November 2026.",
            DRIVERS to "Its own drivers only: licensed NCC private-hire cars - in Rome it does NOT book taxis (in Milan it also works with taxis).",
            PRICES to "Shown in the app before booking; as a new service it may run launch discounts.",
            TIPS to "New in Rome, so fewer cars may be available, especially outside the center. Pickups are by app booking only - not at taxi stands."
        )
        PlaceEntry(
            "FREENOW",
            WHAT to "European taxi app (owned by Lyft since 2025), working in Rome, Milan, Naples and other Italian cities.",
            DRIVERS to "Taxis - it books licensed white taxis from local taxi companies.",
            PRICES to "Normal taxi meter fare (plus any booking surcharge); pay in the app with a card or directly to the driver.",
            TIPS to "One of the easiest ways for visitors to get a real taxi in Rome - the app is in English and shows the car on the way."
        )
        PlaceEntry(
            "itTaxi",
            WHAT to "The Italian taxi app of the big taxi cooperatives (in Rome including the 3570 radio taxi) - one of the most used taxi apps in Italy.",
            DRIVERS to "Taxis only - licensed white taxis. It also powers the taxi option inside Uber.",
            PRICES to "Normal meter fare; pay in the app or to the driver.",
            TIPS to "Good alternative to FREENOW; works in many Italian cities."
        )
        PlaceEntry(
            "appTaxi and other ways to call a taxi",
            WHAT to "appTaxi is another taxi-booking app listed for Italy, but it is much less common in Rome - itTaxi and FREENOW are the main ones. " +
                "Rome also has the city's official 'Chiama Taxi' app (Roma Capitale) and the single taxi phone number 060609.",
            DRIVERS to "All of these book licensed taxis.",
            PRICES to "Normal meter fare.",
            TIPS to "No app? Walk to a taxi stand (Termini, Piazza Venezia, Piazza di Spagna, Largo Argentina and others - see Rome -> Public Transportation) or call 060609. Remember: when you book, the meter starts from where the taxi sets off to pick you up."
        )
    }
}
