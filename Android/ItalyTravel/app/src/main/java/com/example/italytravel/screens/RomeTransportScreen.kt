package com.example.italytravel.screens

import androidx.compose.runtime.Composable

@Composable
fun RomeTransportScreen(onBack: () -> Unit) {
    InfoScreen(title = "Rome - Public Transportation", onBack = onBack) {
        BodyText(
            "Prices below are 2026 figures from several sources; some (especially airport taxi fares) differ between sources - check before you travel. " +
                "Beware of unofficial drivers offering rides inside the airports and stations: use only the official taxi rank or booked transport."
        )

        GroupHeader("Airports")
        PlaceEntry(
            "Fiumicino Airport (FCO)",
            "What it is" to "Rome's main international airport (Leonardo da Vinci), about 30 km west of the center by the sea.",
            "Leonardo Express train" to "Non-stop train to Roma Termini, about 32 minutes, every 15-30 minutes, about 14 euros one way. Last train from the airport around 23:20. The airport station is connected to the terminals by covered walkways.",
            "FL1 regional train" to "About 8 euros. Does NOT go to Termini - stops at Trastevere (about 30 min), Ostiense and Tiburtina (about 47 min); good if you stay in Trastevere or near those stations.",
            "Buses" to "Terravision, SIT, TAM and COTRAL buses to Termini (and some to the Vatican area), about 4-7 euros, about 50-70 minutes depending on traffic. COTRAL also runs a few night buses (about 5 euros).",
            "Taxi" to "Fixed fare to anywhere inside the Aurelian Walls (the historic center) - most sources say 55 euros per car (a few say 50), including luggage, for up to 4 passengers. Use the official white taxis at the rank outside arrivals; Fiumicino-licensed taxis may not apply the Rome fixed fare.",
            "Which to choose" to "2-3 people: Leonardo Express is fastest and easy. 4 people with luggage: the fixed-fare taxi costs about the same as 4 train tickets and goes door to door."
        )
        PlaceEntry(
            "Ciampino Airport (CIA)",
            "What it is" to "Rome's smaller airport, about 15 km south-east of the center, used mainly by low-cost airlines (Ryanair, Wizz Air). There is no train station at the airport itself.",
            "Direct bus to Termini" to "Terravision, SIT and COTRAL shuttle buses, about 5-7 euros, about 40-50 minutes.",
            "Ciampino Airlink (bus + train)" to "Bus from the airport to Ciampino town station, then a regional train to Termini - about 2.70 euros in total, about 30-40 minutes; runs roughly 06:00-23:30.",
            "Bus + metro" to "Local bus to Metro A Anagnina, then the metro into the center (normal city ticket).",
            "Taxi" to "Fixed fare inside the Aurelian Walls - most recent sources say 40 euros per car including luggage (older sources say about 30 euros). Same fare to Ostiense and Tiburtina stations."
        )

        GroupHeader("Roma Termini Station")
        PlaceEntry(
            "Roma Termini station",
            "What it is" to "Rome's main railway station and transport hub, in the Esquilino area - the biggest station in Italy.",
            "Trains" to "High-speed trains (Trenitalia Frecciarossa and Italo) to Florence, Naples, Venice, Milan; regional trains (Lake Albano, Castelli Romani); the Leonardo Express to Fiumicino airport (from the far platforms, usually around platforms 23-24 - follow the 'Leonardo Express' signs, it is a long walk).",
            "Metro and buses" to "The only stop where Metro A and Metro B meet. City buses and the airport buses leave from the bus area of Piazza dei Cinquecento in front of the station and the side streets (Via Marsala / Via Giolitti).",
            "Inside the station" to "Shopping arcade (Unieuro, Uniqlo, pharmacy, supermarket), the Mercato Centrale food hall (Via Giolitti side), cafés and fast food, Alice Pizza, left-luggage office, ticket machines and travel offices. Official taxi rank at the main front.",
            "Tips" to "Busy and crowded - watch your bags and pockets. Buy train tickets only from official machines or offices (ignore people offering 'help' at the machines). Regional paper tickets must be validated before boarding; electronic tickets for a specific train do not.",
            "Getting there" to "Metro A or B to Termini, many buses, or walk from Piazza della Repubblica (about 5 min)."
        )

        GroupHeader("Getting Around Rome")
        PlaceEntry(
            "Metro, buses and trams",
            "Network" to "Three metro lines: A (red: Vatican/Ottaviano - Spagna - Termini - San Giovanni - Cinecittà), B / B1 (blue: Termini - Colosseo - Piramide - EUR; branch B1 to Jonio for Porta di Roma) and C (green, eastern suburbs - being extended toward the center). Many buses and trams fill in where the metro does not reach (Pantheon, Piazza Navona, Trastevere - tram 8).",
            "Tickets" to "BIT single ticket 1.50 euros: valid 100 minutes on buses and trams, with one metro ride. Passes: 24 hours 8.50 euros, 48 hours 15 euros, 72 hours 22 euros. (One source reports a planned increase of the BIT to 2 euros - check atac.roma.it.)",
            "Tap and pay" to "You can tap a contactless bank card or phone at the metro gates and on buses - the same card is never charged more than 8.50 euros in 24 hours. Each person needs their own card / phone.",
            "Validate" to "Paper tickets must be stamped in the machine on the bus / tram or at the metro gate - inspectors fine passengers without a validated ticket.",
            "Hours" to "Metro about 5:30-23:30 (Friday and Saturday until about 1:30); night buses (lines starting with N) after that."
        )
        PlaceEntry(
            "Taxis and taxi stands",
            "How taxis work" to "Official Rome taxis are white with a 'TAXI' sign and a license number. You cannot usually hail one on the street - go to a taxi stand (posteggio taxi) or call / book one: phone 060609 (city taxi number), the 'Chiama Taxi' app of Roma Capitale, or apps such as itTaxi and FREENOW. Uber works only as Uber Black / taxi.",
            "Piazza Venezia" to "Yes - there is an official taxi stand at Piazza Venezia (by number 13/15), one of the main central stands, handy after visiting the Vittoriano, the Forum or Via del Corso.",
            "Porta di Roma mall" to "Yes - the city's list of taxi stands includes one at Via Alberto Lionello, by the Casale Redicicoli entrance of the Porta di Roma shopping center, and the mall's info point can book a taxi. Visitors report that sometimes no taxis are waiting there - if so, call 060609 or use an app.",
            "Other main stands" to "Termini (front of the station), Piazza del Popolo, Piazza di Spagna, Largo di Torre Argentina, Piazza della Repubblica, Piazza San Pietro / Vatican, Trastevere (Piazza Sonnino / Belli) and the airports.",
            "Fares" to "Metered inside the city, with extra charges for night, Sundays and holidays and booked pickups (the meter starts when the taxi sets off to fetch you). Fixed fares to and from the airports (see above). Taxis must accept cards - say so before the ride."
        )
    }
}
