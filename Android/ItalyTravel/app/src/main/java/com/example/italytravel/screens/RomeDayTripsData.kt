package com.example.italytravel.screens

private const val WHAT = "What is it"
private const val HISTORY = "History"
private const val TODO = "What to do"
private const val BOOK = "Book in advance?"
private const val WEBSITE = "Website"
private const val HOW_TO_BOOK = "How to book"
private const val PRICES = "Prices"
private const val GETTING_THERE = "How to get there"
private const val TRAVEL_TIME = "Travel time"
private const val TIME_THERE = "Time to spend"

private fun site(name: String?, vararg facts: Pair<String, String>) = AttractionPlace(name, facts.toList())

val romeDayTrips: List<Attraction> = listOf(
    Attraction(
        "cinecitta", "Cinecittà",
        "Two very different places share the name: the real film studios in Rome, and a movie theme park outside the city.",
        listOf(
            site(
                "Cinecittà Studios (Cinecittà si Mostra)",
                WHAT to "Rome's legendary film studios - one of the biggest in Europe. Visitors see the exhibition 'Cinecittà si Mostra' (costumes, sets, film history), the MIAC museum of moving images, and on guided tours real outdoor sets such as ancient Rome.",
                HISTORY to "Opened in 1937. Fellini, Ben-Hur, Roman Holiday and many Hollywood and Italian films and series were shot here - it was nicknamed 'Hollywood on the Tiber'.",
                TODO to "Walk the exhibition, see original costumes and props, take the guided tour of the film sets (Studio EL / outdoor sets), visit MIAC.",
                BOOK to "Recommended - guided set tours run only on some days and times.",
                WEBSITE to "cinecitta.com",
                HOW_TO_BOOK to "Official website (Visit section), ticket sellers such as TicketOne, or email visit@cinecitta.it.",
                PRICES to "About 20 euros for the guided set tour + exhibition; about 15 euros for exhibition + MIAC; about 10 euros for one exhibition only (check the official price page).",
                GETTING_THERE to "Via Tuscolana 1055 - Metro A to Cinecittà station; the entrance is right by the exit.",
                TRAVEL_TIME to "About 25 minutes by metro from Termini.",
                TIME_THERE to "2-3 hours. Open 10:00-18:00 (last entry 16:30), closed Tuesday."
            ),
            site(
                "Cinecittà World (theme park)",
                WHAT to "Rome's big movie-themed amusement park, with rides, roller coasters, shows and areas themed on films and TV - good for families with kids.",
                HISTORY to "Opened in 2014 on the site of the former Dinocittà film studios, created by Dino De Laurentiis.",
                TODO to "Rides for all ages, live shows, themed areas; in autumn a Halloween season and from November a big Christmas season.",
                BOOK to "Recommended - online tickets are cheaper and skip the queue.",
                WEBSITE to "cinecittaworld.it",
                HOW_TO_BOOK to "Official website (also park + hotel packages).",
                PRICES to "Adults about 29-39 euros depending on the day; reduced (100-140 cm) about 24-30 euros; small children free (check the height rules).",
                GETTING_THERE to "Via di Castel Romano 200, south of EUR. Shuttle bus from Metro B EUR Palasport (about 5 euros each way, from about 10:30) or by car / taxi.",
                TRAVEL_TIME to "About 40 minutes from EUR; about 1 hour from the center.",
                TIME_THERE to "A full day. Season 21 March 2026 - 6 January 2027, open weekends, holidays and the Christmas holidays, 11:00-18:00 (check the calendar)."
            )
        )
    ),
    Attraction(
        "tivoli", "Tivoli (Villa d'Este, Villa Gregoriana, Villa Adriana)",
        "Tivoli is a hill town east of Rome with three famous villas - two of them UNESCO World Heritage Sites. Villa d'Este and Villa Gregoriana are in the town; Villa Adriana is about 5 km below it. " +
            "Getting there: regional train from Roma Tiburtina to Tivoli (about 50 min - 1 hour; some trains are faster), then about 10 min walk to Villa d'Este; or a COTRAL bus from Ponte Mammolo (Metro B), about 1 hour. " +
            "For Villa Adriana take the local CAT bus 4 / 4X from Piazza Garibaldi in Tivoli. Plan a full day for two villas. " +
            "Tickets for Villa d'Este and Villa Adriana: villae.cultura.gov.it / Musei Italiani app; a combined 'Villae' pass (Villa d'Este + Villa Adriana + Santuario di Ercole) is reported at about 34 euros for 3 days (unconfirmed). Free for under 18, and on the first Sunday of the month, 25 April and 2 June.",
        listOf(
            site(
                "Villa d'Este",
                WHAT to "A Renaissance palace famous for its terraced gardens full of fountains: the Avenue of the Hundred Fountains, the Organ Fountain (which plays music with water), the Fountain of Neptune and the Oval Fountain.",
                HISTORY to "Built in the 1550s-1570s for Cardinal Ippolito II d'Este; a model for gardens across Europe. UNESCO World Heritage Site since 2001.",
                TODO to "Walk down through the gardens and fountains, see the frescoed rooms of the palace, hear the Organ Fountain play (at set times).",
                BOOK to "Recommended in spring, summer and at weekends.",
                WEBSITE to "villae.cultura.gov.it",
                HOW_TO_BOOK to "Official Villae website or the Musei Italiani app; tickets also at the entrance.",
                PRICES to "About 15-20 euros (sources differ); EU 18-25 reduced; under 18 free.",
                GETTING_THERE to "In Tivoli's old town, about 10 min walk from Tivoli station.",
                TRAVEL_TIME to "About 1-1.5 hours from central Rome.",
                TIME_THERE to "About 2 hours. Opens about 8:30; closes about 19:45 in summer and earlier in winter (last entry 1 hour before). Check the closing day (often Monday)."
            ),
            site(
                "Villa Gregoriana",
                WHAT to "A wild, romantic park in a deep gorge: the Great Waterfall (Grande Cascata) of the river Aniene, grottoes, woodland paths, and two ancient Roman temples (of Vesta and the Sibyl) on the cliff top.",
                HISTORY to "Created in 1835 by Pope Gregory XVI after a flood, when a tunnel was dug to divert the river. Restored and run by FAI (Fondo Ambiente Italiano, the Italian National Trust).",
                TODO to "Hike the loop down to the waterfall and back up - steep paths and steps, so wear good shoes; not suitable for strollers.",
                BOOK to "Not usually needed; guided visits must be booked.",
                WEBSITE to "fondoambiente.it (Villa Gregoriana page)",
                HOW_TO_BOOK to "FAI website or at the entrance. FAI members enter free.",
                PRICES to "About 8-10 euros adults; reduced about 3-6 euros (children, students); family ticket available (sources differ).",
                GETTING_THERE to "In Tivoli town (entrance at Largo Sant'Angelo), about 10-15 min walk from Tivoli station.",
                TRAVEL_TIME to "About 1-1.5 hours from central Rome.",
                TIME_THERE to "1.5-2 hours. Seasonal hours, about 9:30-18:30 (in high summer until 19:00/20:00), shorter in winter; last entry 1 hour before closing."
            ),
            site(
                "Villa Adriana (Hadrian's Villa)",
                WHAT to "The enormous country palace of Emperor Hadrian - a whole town of ruins: the Canopus (a long pool lined with statues), the Maritime Theatre (a villa on an island), baths, libraries and gardens.",
                HISTORY to "Built around 118-138 AD for Emperor Hadrian, who also built the Pantheon. UNESCO World Heritage Site since 1999.",
                TODO to "Walk the ruins (a big site - over 100 hectares), see the Canopus and the Maritime Theatre, the model of the villa at the entrance.",
                BOOK to "Recommended in high season.",
                WEBSITE to "villae.cultura.gov.it",
                HOW_TO_BOOK to "Official Villae website / Musei Italiani app, or at the entrance.",
                PRICES to "About 15-20 euros (sources differ); EU 18-25 reduced; under 18 free.",
                GETTING_THERE to "About 5 km below Tivoli: CAT bus 4 / 4X from Piazza Garibaldi in Tivoli, or by car. (Can also be reached from Bagni di Tivoli station + COTRAL bus.)",
                TRAVEL_TIME to "About 1-1.5 hours from central Rome.",
                TIME_THERE to "2-3 hours. Opens 9:00, closing time changes with the season. Little shade - bring water and a hat."
            )
        )
    ),
    Attraction(
        "lake_albano", "Lake Albano (Castel Gandolfo)",
        null,
        listOf(
            site(
                "Lake Albano",
                WHAT to "A round volcanic crater lake in the Alban Hills (Castelli Romani), below the hilltop town of Castel Gandolfo.",
                HISTORY to "The crater is an extinct volcano. The town above has been the popes' summer residence since the 1600s - and became the pope's summer home again in 2025.",
                TODO to "Walk down to the lakeshore, stroll along the water, rent a pedal boat or swim in summer, eat at the lakeside kiosks; enjoy the view terrace in Castel Gandolfo. Nearby Ariccia is famous for porchetta.",
                BOOK to "No for the lake.",
                WEBSITE to "-",
                HOW_TO_BOOK to "Not needed.",
                PRICES to "Free (train about 3 euros each way).",
                GETTING_THERE to "FL4 regional train from Roma Termini to Castel Gandolfo (about hourly); from the station walk 15-20 min down to the lake (steep back up).",
                TRAVEL_TIME to "About 40-45 minutes by train.",
                TIME_THERE to "Half a day to a full day."
            ),
            site(
                "Papal Palace and Gardens of Castel Gandolfo",
                WHAT to "The popes' summer palace, its apartments and the Barberini gardens above the lake.",
                HISTORY to "Built in the 1600s for Pope Urban VIII; opened to the public as a museum in 2016, and again used by the pope in summer since 2025.",
                TODO to "Visit the papal apartments and museum, walk the gardens (some visits by mini-train).",
                BOOK to "Yes - recommended; parts may be closed during the pope's stays or works.",
                WEBSITE to "museivaticani.va (Pontifical Villas of Castel Gandolfo)",
                HOW_TO_BOOK to "Vatican Museums website (Ville Pontificie), or resellers.",
                PRICES to "About 15 euros adults, 12 euros children (check).",
                GETTING_THERE to "In the center of Castel Gandolfo, about 10 min walk up from the station.",
                TRAVEL_TIME to "About 40-45 minutes by train from Termini.",
                TIME_THERE to "About 2 hours."
            )
        )
    ),
    Attraction(
        "lake_bracciano", "Lake Bracciano",
        null,
        listOf(
            site(
                "Lake Bracciano",
                WHAT to "A large, clean volcanic lake north-west of Rome with three lakeside towns: Bracciano, Anguillara Sabazia and Trevignano Romano. Motorboats are banned, so the water is famously clean.",
                HISTORY to "Volcanic crater lake that has supplied water to Rome since ancient times (the Aqua Traiana aqueduct, AD 109).",
                TODO to "Swim from the beaches in summer, walk the lakefront, rent kayaks or pedal boats, eat lake fish; visit the castle in Bracciano (below).",
                BOOK to "No for the lake.",
                WEBSITE to "-",
                HOW_TO_BOOK to "Not needed.",
                PRICES to "Free beaches; train about 4 euros each way.",
                GETTING_THERE to "FL3 regional train from Roma Ostiense, Roma Trastevere or Roma San Pietro to Bracciano; from the station the lakefront is about 15-20 min downhill.",
                TRAVEL_TIME to "About 50-60 minutes by train. Trains are not frequent - check the return times.",
                TIME_THERE to "A full day."
            ),
            site(
                "Castello Orsini-Odescalchi (Bracciano)",
                WHAT to "A beautiful 15th-century castle above the town and the lake, with towers, frescoed halls and armour.",
                HISTORY to "Built by the Orsini family in the 1400s, later owned by the Odescalchi; famous as a wedding venue (Tom Cruise and Katie Holmes married here in 2006).",
                TODO to "Tour the halls and armoury, walk the battlements for the lake view.",
                BOOK to "Check before going - it closes for private events.",
                WEBSITE to "odescalchi.it",
                HOW_TO_BOOK to "At the entrance or the official website.",
                PRICES to "About 10 euros adults, 8 euros children 6-12, under 6 free.",
                GETTING_THERE to "About 10 min uphill walk from Bracciano station.",
                TRAVEL_TIME to "About 50-60 minutes by train from Rome.",
                TIME_THERE to "1-1.5 hours. Hours change between winter and summer."
            )
        )
    ),
    Attraction(
        "lake_nemi", "Lake Nemi",
        null,
        listOf(
            site(
                "Lake Nemi and the village of Nemi",
                WHAT to "A small, deep crater lake in the Castelli Romani hills, surrounded by woods, with the pretty village of Nemi on the rim - famous for its tiny wild strawberries (fragoline di Nemi).",
                HISTORY to "In ancient times the lake was sacred to Diana ('Diana's mirror'), with a great sanctuary on its shore.",
                TODO to "Walk the village and its view terrace, eat strawberries (fresh in spring and early summer; strawberry desserts and liqueur all year), visit the Roman Ships Museum on the shore (below).",
                BOOK to "No.",
                WEBSITE to "-",
                HOW_TO_BOOK to "Not needed.",
                PRICES to "Free.",
                GETTING_THERE to "COTRAL bus from Anagnina (Metro A), or train from Termini to Albano / Genzano / Velletri and then a bus; easiest by car via Via Appia and Genzano.",
                TRAVEL_TIME to "About 1-1.5 hours by public transport; about 45 min by car.",
                TIME_THERE to "Half a day (combine with Lake Albano / Castel Gandolfo for a full Castelli Romani day)."
            ),
            site(
                "Museo delle Navi Romane (Roman Ships Museum)",
                WHAT to "A museum built for Emperor Caligula's two huge ceremonial ships, recovered from the lake.",
                HISTORY to "The ships were raised in 1928-1932 by draining the lake - and destroyed by fire in 1944. The museum shows bronze fittings, models and finds from Diana's sanctuary.",
                TODO to "See the ship models and original bronzes; walk to the lakeshore.",
                BOOK to "No - but call first: a closure notice ran until 31 August 2026.",
                WEBSITE to "Direzione Regionale Musei Lazio (Museo delle Navi Romane page)",
                HOW_TO_BOOK to "At the entrance or the Musei Italiani app. Phone 06 9398040.",
                PRICES to "About 3-5 euros (sources differ); free on the first Sunday of the month.",
                GETTING_THERE to "On the lakeshore below Nemi (Via del Tempio di Diana), free parking.",
                TRAVEL_TIME to "About 1-1.5 hours from Rome by public transport.",
                TIME_THERE to "About 1 hour. Tuesday-Sunday 9:00-19:00 (last entry 18:00), closed Monday."
            )
        )
    ),
    Attraction(
        "lido_di_ostia", "Lido di Ostia",
        null,
        listOf(
            site(
                "Lido di Ostia (Rome's beach)",
                WHAT to "Rome's seaside: a long sandy beach with many stabilimenti (beach clubs renting umbrellas and loungers, with bars and showers) and some free public beaches further south toward Castel Porziano ('i Cancelli').",
                HISTORY to "Developed as Rome's beach resort in the early 1900s, with a seafront promenade and a pier (Pontile).",
                TODO to "Swim and sunbathe (season about May-September), walk the promenade and the pier, eat seafood or gelato by the sea.",
                BOOK to "Not usually; on hot July-August weekends book a beach club or arrive early.",
                WEBSITE to "-",
                HOW_TO_BOOK to "Beach clubs directly (some take online bookings).",
                PRICES to "Free beaches are free; beach clubs charge for umbrella + loungers (varies - higher on summer weekends).",
                GETTING_THERE to "Roma-Lido train from Piramide (Metro B) / Porta San Paolo to Lido Centro, Stella Polare or Castel Fusano. A normal 1.50 euro city ticket is valid.",
                TRAVEL_TIME to "About 30-40 minutes; trains every 15-20 minutes.",
                TIME_THERE to "Half a day to a full day."
            ),
            site(
                "Ostia Antica (ancient Roman port)",
                WHAT to "The excavated port town of ancient Rome - streets, apartment blocks, shops, baths, a theatre still used for shows and beautiful mosaics. Often called 'the Pompeii near Rome', and much less crowded.",
                HISTORY to "Rome's harbor city from the 4th century BC; abandoned after the river silted up and the coast moved, then buried and preserved.",
                TODO to "Walk the Decumanus Maximus (main street), see the theatre, the Square of the Guilds mosaics and the Thermopolium (an ancient bar).",
                BOOK to "Not usually needed.",
                WEBSITE to "ostiaantica.cultura.gov.it",
                HOW_TO_BOOK to "Official website / Musei Italiani app, or at the entrance.",
                PRICES to "About 12-18 euros (sources differ); free on the first Sunday of the month.",
                GETTING_THERE to "Same Roma-Lido train - get off at Ostia Antica (two stops before the beach), then about 5-10 min walk.",
                TRAVEL_TIME to "About 30 minutes from Piramide.",
                TIME_THERE to "2-3 hours. Closed Monday. Little shade - bring water. Easy to combine with the beach."
            )
        )
    )
)
