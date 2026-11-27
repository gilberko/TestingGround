package com.example.italytravel.screens

data class AttractionPlace(val name: String?, val facts: List<Pair<String, String>>)

data class Attraction(
    val id: String,
    val title: String,
    val intro: String?,
    val places: List<AttractionPlace>
)

private const val WHERE = "Where is it"
private const val GETTING_THERE = "Getting there"
private const val WHAT_TO_DO = "What to do"
private const val HOURS = "Hours and days"
private const val BOOK = "Book in advance?"
private const val PRICES = "Prices"
private const val HOW_TO_BOOK = "How to book"

private fun place(name: String?, vararg facts: Pair<String, String>) = AttractionPlace(name, facts.toList())

private const val PANTHEON_ACCESS =
    "No metro nearby: walk 10-15 min from Metro A Spagna or Barberini, or take a bus to Largo di Torre Argentina."
private const val FREE_NO_BOOKING = "No - it is a free public square / area."
private const val CHURCH_RULES = "Free. Dress code: shoulders and knees covered. Avoid sightseeing during Mass."

val romeAttractions: List<Attraction> = listOf(
    Attraction(
        "campo_de_fiori", "Campo de' Fiori",
        "A lively square in the historic center - market by day, bars by night.",
        listOf(
            place(
                null,
                WHERE to "Centro Storico, a few minutes south of Piazza Navona.",
                GETTING_THERE to "Tram 8 or many buses to Largo di Torre Argentina, then about 5 min walk.",
                WHAT_TO_DO to "Browse the morning market (fruit, vegetables, spices, flowers, souvenirs). See the hooded statue of Giordano Bruno, the philosopher burned here in 1600. " +
                    "Eat pizza bianca from Forno Campo de' Fiori (see Forno and Rome Pizza). In the evening the square fills with bars and young people.",
                HOURS to "The square is always open. Market Monday-Saturday, about 7:00-14:00 (no market on Sunday).",
                BOOK to FREE_NO_BOOKING,
                PRICES to "Free.",
                HOW_TO_BOOK to "Not needed."
            )
        )
    ),
    Attraction(
        "pantheon", "Pantheon",
        "Rome's best-preserved ancient building - a 2,000-year-old temple, now a church.",
        listOf(
            place(
                null,
                WHERE to "Piazza della Rotonda, Centro Storico.",
                GETTING_THERE to PANTHEON_ACCESS,
                WHAT_TO_DO to "Stand under the giant concrete dome and its open oculus (the 'eye' in the roof - rain falls straight in). See the tomb of the painter Raphael and of Italian kings. " +
                    "Afterwards: coffee at Sant'Eustachio or Tazza d'Oro, gelato at Fiocco di Neve or Grom (see Coffee / Ice Cream).",
                HOURS to "Daily about 9:00-19:00, last entry 18:30. Visits are limited during Mass (Saturday and holiday eves 17:00, Sunday and holidays 10:30).",
                BOOK to "Recommended in high season (April-October) and at weekends.",
                PRICES to "7 euros (raised from 5 euros on 1 July 2026 - some older sources still say 5). EU citizens 18-25: 2 euros. Under 18: free. " +
                    "Free on the first Sunday of the month (ticket collected on site only).",
                HOW_TO_BOOK to "Official Musei Italiani website / app (choose a time slot), or buy at the entrance."
            )
        )
    ),
    Attraction(
        "vatican", "Vatican and the Vatican Museums",
        "The world's smallest country: the Vatican Museums with the Sistine Chapel, and St. Peter's Basilica.",
        listOf(
            place(
                "Vatican Museums and Sistine Chapel",
                WHERE to "Vatican City - museum entrance on Viale Vaticano, on the north side of the Vatican walls.",
                GETTING_THERE to "Metro A Ottaviano or Cipro, then about 10 min walk.",
                WHAT_TO_DO to "One of the world's greatest art collections: the Raphael Rooms, the Gallery of Maps, ancient statues, and the Sistine Chapel with Michelangelo's ceiling and Last Judgment. Allow at least 3 hours.",
                HOURS to "Monday-Saturday 8:00-20:00, last entry 18:00. Closed on Sundays and Vatican holidays - EXCEPT the last Sunday of the month, free entry 9:00-14:00 (last entry 12:30), with very long queues.",
                BOOK to "Yes - strongly. Without a booking you can queue for hours.",
                PRICES to "20 euros at the ticket desk, 25 euros online (includes a 5 euro booking fee). Reduced about 8 euros (ages 6-18, students under 26 with ID). Under 6 free. Third-party 'skip the line' tickets cost 30+ euros.",
                HOW_TO_BOOK to "Official site museivaticani.va - choose date and time. Avoid resellers' mark-ups."
            ),
            place(
                "St. Peter's Basilica and Dome",
                WHERE to "Piazza San Pietro, Vatican City.",
                GETTING_THERE to "Metro A Ottaviano, about 10 min walk; or walk along Via della Conciliazione from Castel Sant'Angelo.",
                WHAT_TO_DO to "The largest church in the world: Michelangelo's Pietà, Bernini's bronze canopy, the papal tombs. Climb the dome for the best view of Rome (551 steps, or lift + 320 steps).",
                HOURS to "Basilica daily about 7:00-18:30 (summer) / 7:00-18:00 (winter). Dome from 7:30, until about 18:00 (summer) / 17:00 (winter). Closed during some papal ceremonies.",
                BOOK to "Basilica: no booking - just a security queue (45-120 min at peak; go early). Dome: optional online.",
                PRICES to "Basilica: FREE. Dome: about 10 euros by stairs / 15 euros with the lift at the kiosk (official online tickets cost more, about 17 / 22 euros).",
                HOW_TO_BOOK to "Dome tickets at the kiosk inside, or online at the official basilica site. Dress code: shoulders and knees covered - you can be refused entry."
            )
        )
    ),
    Attraction(
        "colosseum", "Colosseum",
        "The ancient amphitheater - one ticket also covers the Roman Forum and the Palatine Hill.",
        listOf(
            place(
                null,
                WHERE to "Piazza del Colosseo, at the end of Via dei Fori Imperiali.",
                GETTING_THERE to "Metro B Colosseo - it is right outside the station.",
                WHAT_TO_DO to "Walk the stands of the 50,000-seat amphitheater; with the Full Experience ticket also the arena floor and the underground. " +
                    "Then the Roman Forum (the center of ancient Rome) and the Palatine Hill (emperors' palaces, great views).",
                HOURS to "Colosseum opens 8:30 (Forum / Palatine 9:00). Closing: 29 March-30 September 19:15; 1-24 October 18:30; 25 October-end of February 16:30. Last entry 1 hour before closing. Closed 25 December and 1 January.",
                BOOK to "YES - essential. Tickets often sell out; the underground tours sell out within minutes.",
                PRICES to "18 euros - 24-hour ticket for Colosseum + Roman Forum + Palatine. Full Experience (arena / underground) about 22-24 euros. A booking fee of about 2 euros may apply. " +
                    "Free: first Sunday of the month, 25 April, 2 June, 4 November (very crowded).",
                HOW_TO_BOOK to "Official site ticketing.colosseo.it - tickets are released about 30 days ahead. Book with your passport name; ID is checked."
            )
        )
    ),
    Attraction(
        "roman_forum", "Roman Forum and Palatine Hill",
        "The heart of ancient Rome, right next to the Colosseum - and on the same ticket.",
        listOf(
            place(
                "Roman Forum and Palatine Hill",
                WHERE to "Between the Colosseum, Piazza Venezia and the Circus Maximus. Entrances: Via della Salara Vecchia 5/6 (Forum, from Via dei Fori Imperiali) and Via di San Gregorio 30 (Palatine, near the Colosseum).",
                GETTING_THERE to "Metro B Colosseo, then a few minutes' walk.",
                WHAT_TO_DO to "Walk the Via Sacra past temples, the Senate House (Curia), the Arch of Titus and the Arch of Septimius Severus, the House of the Vestal Virgins. " +
                    "Climb the Palatine Hill - where Rome was founded and the emperors lived - for the ruins of their palaces and views over the Forum and the Circus Maximus. " +
                    "For a free view from above: the terraces of the Capitoline Hill behind the Campidoglio.",
                HOURS to "Daily from 9:00; closes like the Colosseum (about 19:15 in summer, 18:30 in October, 16:30 from late October to February); last entry 1 hour before. Closed 25 December and 1 January.",
                BOOK to "Yes - it is part of the Colosseum ticket, which often sells out.",
                PRICES to "Included in the 18 euro Colosseum 24-hour ticket (Colosseum + Roman Forum + Palatine). Free on the first Sunday of the month.",
                HOW_TO_BOOK to "Official site ticketing.colosseo.it. Allow 2-3 hours; bring water and good shoes - there is little shade."
            )
        )
    ),
    Attraction(
        "appian_way", "Appian Way (Via Appia Antica)",
        "The 'queen of roads' - an ancient Roman road through countryside, tombs and catacombs, now a big park.",
        listOf(
            place(
                "Appian Way (Via Appia Antica)",
                WHERE to "Starts at Porta San Sebastiano (south of the Baths of Caracalla) and runs south-east through the Parco dell'Appia Antica.",
                GETTING_THERE to "Bus 118 from Piramide (Metro B) or Circo Massimo to the catacombs area; or bus 660 from Colli Albani (Metro A) to the Tomb of Caecilia Metella.",
                WHAT_TO_DO to "Walk or cycle on the original Roman paving stones under umbrella pines; visit the catacombs of San Callisto or San Sebastiano (underground Christian burial tunnels), the Tomb of Caecilia Metella and the Villa dei Quintili. " +
                    "Bikes and e-bikes can be rented at the Appia Antica info point near the start of the road; guided e-bike tours also run.",
                HOURS to "The road is always open. State archaeological sites are closed on Monday; each catacomb closes on a different weekday. On Sundays and holidays much of the road is closed to cars (check).",
                BOOK to "The park ticket must be reserved (Musei Italiani app); catacomb visits are guided - booking recommended.",
                PRICES to "Walking the road is FREE. State sites: park ticket about 12 euros valid 7 days (single sites about 6-8 euros) since July 2026 - older sources say 8 euros. Catacombs are NOT included: about 10 euros with guide.",
                HOW_TO_BOOK to "Park: parcoappiaantica.it / Musei Italiani app. Catacombs: their own websites or at the entrance. Allow half a day."
            )
        )
    ),
    Attraction(
        "villa_borghese", "Villa Borghese",
        "Rome's central park, with the Galleria Borghese art museum - and in winter, Christmas World.",
        listOf(
            place(
                "The park",
                WHERE to "The big park above Piazza del Popolo and the Spanish Steps.",
                GETTING_THERE to "Metro A Flaminio (from Piazza del Popolo walk up to the Pincio) or Metro A Spagna (walk up from the Spanish Steps).",
                WHAT_TO_DO to "Free park: the Pincio terrace with a view over Piazza del Popolo, rent bikes, electric carts or rickshaws, row a boat on the lake, the Bioparco zoo, and the Zoology Museum (see Science Museums).",
                HOURS to "Park open daily, dawn to dusk.",
                BOOK to "No for the park.",
                PRICES to "Park free; bike / boat rental and the zoo are paid.",
                HOW_TO_BOOK to "Not needed."
            ),
            place(
                "Galleria Borghese",
                WHERE to "Piazzale Scipione Borghese 5, inside the park.",
                GETTING_THERE to "About 15 min walk from Metro A Spagna or Flaminio, or bus to Via Pinciana.",
                WHAT_TO_DO to "A small, stunning museum: Bernini's sculptures (Apollo and Daphne, The Rape of Proserpina), Caravaggio, Raphael, Titian, Canova.",
                HOURS to "Tuesday-Sunday, 2-hour entry slots starting 9:00, 11:00, 13:00, 15:00, 17:00. Closed Monday.",
                BOOK to "YES - booking is MANDATORY for everyone (even free tickets). Slots sell out weeks ahead.",
                PRICES to "About 18 euros (16 + 2 booking fee); the last slot is cheaper. EU 18-25: about 2 euros + fee. Free on the first Sunday of the month, but you still must book.",
                HOW_TO_BOOK to "Official Galleria Borghese website (Musei Italiani platform), as early as possible."
            ),
            place(
                "Christmas World (winter only)",
                WHERE to "Villa Borghese, by Viale del Galoppatoio (near the Via Veneto / Porta Pinciana side).",
                GETTING_THERE to "Metro A Spagna or Barberini, then about 10-15 min walk.",
                WHAT_TO_DO to "A big Christmas village: themed light installations, Santa's village, ice skating, rides and Christmas food stalls - great with kids, best after dark.",
                HOURS to "28 November 2026 - 10 January 2027. Daily 10:00-20:00; 24 and 31 December 10:00-18:00.",
                BOOK to "Yes - recommended, especially weekends and the Christmas holidays (timed entry).",
                PRICES to "Depends on the day: adults 12.50 (off-peak) / 16.50 (standard) / 18.50 (peak) euros; children 3-11: 10.50 / 13.50 / 15.50 euros; under 2 free. Rides and food cost extra.",
                HOW_TO_BOOK to "Official site christmasworld.net. Not included in the Galleria Borghese ticket."
            )
        )
    ),
    Attraction(
        "bioparco", "Rome Zoo (Bioparco di Roma)",
        "Rome's zoo, inside the Villa Borghese park - a good half day with kids.",
        listOf(
            place(
                "Bioparco di Roma (Rome Zoo)",
                WHERE to "Piazzale del Giardino Zoologico 1, on the north side of Villa Borghese. The Zoology Museum is right next door (see Science Museums).",
                GETTING_THERE to "Tram 3 or 19 to the Bioparco stop; or about 20 min walk through Villa Borghese from Metro A Flaminio or Spagna.",
                WHAT_TO_DO to "Over 1,000 animals of about 200 species - giraffes, lions, tigers, elephants, bears, chimpanzees, lemurs, reptiles, birds - in a historic zoo opened in 1911, now run as a conservation park. Feeding times and talks, a play area, a small farm area and snack bars.",
                HOURS to "Open every day except 25 December. About 9:30-17:00 from November to late March; about 9:30-18:00 from late March to late October, until 19:00 on weekends and holidays in spring and summer. Ticket office and last entry 1 hour before closing.",
                BOOK to "Not required, but online tickets avoid the queue on weekends and holidays.",
                PRICES to "Fixed-date ticket about 19 euros adults, about 14 euros reduced (children over 1 m up to 10 years, over-65s); children under 1 m free. Open-date tickets cost more (about 20-26 euros). Online prices change by day.",
                HOW_TO_BOOK to "Official site bioparco.it, or at the ticket office. Allow 2-3 hours; combine with the Zoology Museum, the Villa Borghese lake boats or Galleria Borghese."
            )
        )
    ),
    Attraction(
        "trastevere", "Trastevere",
        "The charming neighborhood 'across the Tiber' - cobbled lanes, ivy, restaurants and nightlife.",
        listOf(
            place(
                null,
                WHERE to "West bank of the Tiber, opposite the Jewish Ghetto and Campo de' Fiori.",
                GETTING_THERE to "Tram 8 to Belli or Viale Trastevere, or walk over Ponte Sisto from Campo de' Fiori.",
                WHAT_TO_DO to "Wander the lanes; see the golden mosaics of Santa Maria in Trastevere (free); Piazza Trilussa at sunset; dinner in a trattoria; the Porta Portese flea market (Sunday mornings). " +
                    "Food tips in this app: Mama Eat (Gluten Free), Igio (Korean Food), Red Pizza (Forno and Pizza).",
                HOURS to "Always open; best late afternoon and evening. Churches close at lunchtime.",
                BOOK to "No - but book popular restaurants for dinner.",
                PRICES to "Free.",
                HOW_TO_BOOK to "Not needed."
            )
        )
    ),
    Attraction(
        "piazza_navona", "Piazza Navona",
        "Rome's most beautiful baroque square, built on the shape of an ancient stadium.",
        listOf(
            place(
                null,
                WHERE to "Centro Storico, between the Pantheon and the Tiber.",
                GETTING_THERE to "Bus to Corso del Rinascimento or Largo di Torre Argentina, then a few minutes' walk. No metro.",
                WHAT_TO_DO to "Bernini's Fountain of the Four Rivers, the church of Sant'Agnese in Agone (free), street artists and painters. " +
                    "In December the square hosts Rome's main Christmas market (see Christmas Markets).",
                HOURS to "Always open.",
                BOOK to FREE_NO_BOOKING,
                PRICES to "Free. Cafés on the square are expensive - sit down only if you accept tourist prices.",
                HOW_TO_BOOK to "Not needed."
            )
        )
    ),
    Attraction(
        "trevi", "Trevi Fountain (Fontana di Trevi)",
        "The most famous fountain in the world - throw a coin over your shoulder to make sure you return to Rome.",
        listOf(
            place(
                null,
                WHERE to "Piazza di Trevi, Trevi (Centro Storico).",
                GETTING_THERE to "Metro A Barberini, about 7 min walk.",
                WHAT_TO_DO to "See the fountain, throw a coin (right hand over left shoulder). Come early morning or late night to avoid the crowds. " +
                    "Nearby: L'Antico Forno di Fontana di Trevi, Pizza in Trevi, Il Miraggio (see Forno / Gluten Free).",
                HOURS to "Square always open. Close-up access to the basin about 9:00-22:00 (last entry about 21:00); on Mondays and Fridays access starts later because of cleaning (sources say 11:30 or 14:00).",
                BOOK to "Not needed to see it from the square. For the basin area, buying the ticket online in advance is easiest.",
                PRICES to "Viewing from the square: FREE. Close-up access to the basin: 2 euros since 2 February 2026 (non-residents). Free: Rome residents, children under 6, disabled visitors and carers.",
                HOW_TO_BOOK to "Online at fontanaditrevi.roma.it (open-dated ticket), at Tourist Infopoints / civic museum ticket offices, or on site - by card (some visitors report cash is not accepted at the entrance)."
            )
        )
    ),
    Attraction(
        "bocca_della_verita", "Bocca della Verità (Mouth of Truth)",
        "The ancient stone face that, legend says, bites the hand of liars - famous from the film Roman Holiday.",
        listOf(
            place(
                null,
                WHERE to "In the portico of the church of Santa Maria in Cosmedin, Piazza della Bocca della Verità, near the Circus Maximus.",
                GETTING_THERE to "Bus to Bocca della Verità, or Metro B Circo Massimo, then about 10 min walk.",
                WHAT_TO_DO to "Put your hand in the mouth for the photo; visit the medieval church and its crypt. Nearby: the Circus Maximus and two Roman temples in the same square.",
                HOURS to "Daily about 9:30-17:50, with a lunch break about 13:00-14:00 (times vary by source). Closed 1 January.",
                BOOK to "No booking - expect a queue for the photo; go early.",
                PRICES to "Free, with a small suggested donation (also covers the church and crypt).",
                HOW_TO_BOOK to "Not needed."
            )
        )
    ),
    Attraction(
        "jewish_ghetto", "Jewish Ghetto (Jewish Quarter)",
        "Home of one of the oldest Jewish communities in Europe - Jews have lived in Rome for over 2,000 years.",
        listOf(
            place(
                "The neighborhood",
                WHERE to "Between Largo di Torre Argentina and the Tiber, around Via del Portico d'Ottavia.",
                GETTING_THERE to "Tram 8 to Arenula, or buses to Largo di Torre Argentina, then about 5 min walk.",
                WHAT_TO_DO to "Walk Via del Portico d'Ottavia and see the ancient Portico of Octavia ruins and the Turtle Fountain (Fontana delle Tartarughe). " +
                    "Eat Roman-Jewish food: carciofi alla giudia (fried artichokes), and the old kosher bakery Boccione (pizza ebraica, ricotta and sour-cherry tart). Many places close on Shabbat.",
                HOURS to "The streets are always open; most kosher shops and restaurants close Friday afternoon / Saturday.",
                BOOK to FREE_NO_BOOKING,
                PRICES to "Free.",
                HOW_TO_BOOK to "Not needed."
            ),
            place(
                "Great Synagogue and Jewish Museum of Rome",
                WHERE to "Via Catalana / Lungotevere de' Cenci, by the river.",
                GETTING_THERE to "Tram 8 to Arenula.",
                WHAT_TO_DO to "The museum tells the 2,000-year history of Roman Jews; the Great Synagogue (Tempio Maggiore, 1904) can be visited only with the guided tour included in the museum ticket. Allow about 2 hours.",
                HOURS to "About Sunday-Thursday 10:00-18:00 in summer (shorter in winter), Friday shorter. CLOSED Saturday and on Jewish holidays.",
                BOOK to "Recommended, especially in high season.",
                PRICES to "About 11-16 euros depending on the ticket (check the official rates page).",
                HOW_TO_BOOK to "Official site museoebraico.roma.it, or at the entrance."
            )
        )
    ),
    Attraction(
        "science_museums", "Science Museums",
        "Rome's science museums are small but good for families.",
        listOf(
            place(
                "Planetario di Roma (Planetarium)",
                WHERE to "EUR, Piazza Giovanni Agnelli 10 (same building as the Museo della Civiltà Romana).",
                GETTING_THERE to "Metro B EUR Fermi, then about 10 min walk.",
                WHAT_TO_DO to "Shows under the dome about stars, planets and space; special evening events.",
                HOURS to "Tuesday-Sunday, closed Monday; show times change every day (see the calendar on the site).",
                BOOK to "Yes - at weekends tickets are sold online only.",
                PRICES to "About 8.50 euros full / 6.50 euros reduced, plus a small online fee.",
                HOW_TO_BOOK to "planetarioroma.it or call 060608."
            ),
            place(
                "Museo Civico di Zoologia (Zoology Museum)",
                WHERE to "Via Ulisse Aldrovandi 18, Villa Borghese - next to the Bioparco zoo.",
                GETTING_THERE to "Tram 3 or 19 to Bioparco, or about 20 min walk from Metro A Flaminio.",
                WHAT_TO_DO to "Natural history: animals, skeletons, dioramas - easy to combine with the zoo.",
                HOURS to "Tuesday-Sunday 9:00-19:00, last entry 18:00. Closed Monday, 1 May, 25 December.",
                BOOK to "Not required; online recommended.",
                PRICES to "10.50 euros full / 5.50 euros reduced.",
                HOW_TO_BOOK to "museocivicodizoologia.it, 060608, or at the ticket office."
            ),
            place(
                "Technotown",
                WHERE to "Villa Torlonia park, Via Lazzaro Spallanzani 1/a (off Via Nomentana).",
                GETTING_THERE to "Buses along Via Nomentana to Villa Torlonia.",
                WHAT_TO_DO to "Hands-on science and technology lab for kids and teens: robotics, 3D, games and experiments.",
                HOURS to "About Tuesday-Sunday 9:30-19:00 (check - hours may have changed).",
                BOOK to "Recommended - activities have limited places.",
                PRICES to "About 8 euros per activity (older price list).",
                HOW_TO_BOOK to "Call 060608 or info@technotown.it."
            )
        )
    ),
    Attraction(
        "welcome_to_rome", "Welcome to Rome",
        "A multimedia show about the history of Rome - a great first-day introduction, especially with kids.",
        listOf(
            place(
                null,
                WHERE to "Corso Vittorio Emanuele II 203, near Piazza Navona.",
                GETTING_THERE to "Bus to Corso Vittorio Emanuele / Largo di Torre Argentina.",
                WHAT_TO_DO to "Films, 3D models and projections showing how Rome grew from its founding to today - the Colosseum, Pantheon and Forum as they once looked. Shows start every 30 minutes and last about 30-50 minutes.",
                HOURS to "Daily, about 9:00/10:00 to 19:00/20:00 (listings differ); last show about an hour before closing.",
                BOOK to "Not usually needed; online is handy at busy times.",
                PRICES to "About 17 euros (online reseller price) - check current prices.",
                HOW_TO_BOOK to "Online (Tiqets, Klook and others) or at the door."
            )
        )
    ),
    Attraction(
        "illusion_museums", "Illusion Museums",
        "Interactive museums of optical illusions - fun photos, good for kids and rainy days.",
        listOf(
            place(
                "Museum of Illusions Rome",
                WHERE to "Via Merulana 17, about 1 min from the Basilica of Santa Maria Maggiore.",
                GETTING_THERE to "About 10 min walk from Termini, or Metro A Vittorio Emanuele.",
                WHAT_TO_DO to "Rooms of optical illusions: the tilted room, infinity room, vortex tunnel, holograms - lots of photo spots.",
                HOURS to "Daily about 10:00-20:00 (until 21:00 Friday-Sunday). Closed Easter and 25 December.",
                BOOK to "YES on Saturdays, Sundays and holidays - entry then is ONLY with an online booking. Recommended on weekdays.",
                PRICES to "Adult about 18-21 euros, youth (6-15) about 12-14 euros, family ticket about 45 euros, under 5 free.",
                HOW_TO_BOOK to "The museum's official website."
            )
        )
    ),
    Attraction(
        "kids_museums", "Kids Museums",
        "Museums made for children - plus other kid-friendly places in Rome.",
        listOf(
            place(
                "Explora - Museo dei Bambini di Roma",
                WHERE to "Via Flaminia 82, near Piazza del Popolo.",
                GETTING_THERE to "Metro A Flaminio, about 5 min walk.",
                WHAT_TO_DO to "A hands-on 'play to learn' museum for ages about 0-12: a mini city with a supermarket, post office, water play, science and nature corners.",
                HOURS to "Tuesday-Sunday in timed sessions of about 1h45; closed Monday.",
                BOOK to "Yes - especially weekends and holidays; sessions fill up.",
                PRICES to "About 10 euros per person (check the official price list).",
                HOW_TO_BOOK to "Official site mdbr.it."
            ),
            place(
                "More for kids",
                WHAT_TO_DO to "Technotown, the Zoology Museum and the Planetarium (see Science Museums), the Bioparco zoo and boats in Villa Borghese, the Museum of Illusions (see Illusion Museums), Welcome to Rome, and Christmas World in winter (see Villa Borghese)."
            )
        )
    ),
    Attraction(
        "churches", "Famous Churches",
        "Rome has over 900 churches. $CHURCH_RULES",
        listOf(
            place(
                "St. Peter's Basilica",
                WHAT_TO_DO to "The largest church in the world - see Vatican and the Vatican Museums for hours, the dome and the security queue."
            ),
            place(
                "San Giovanni in Laterano",
                WHERE to "Piazza di San Giovanni in Laterano.",
                GETTING_THERE to "Metro A San Giovanni.",
                WHAT_TO_DO to "The cathedral of Rome (the Pope's own church as Bishop of Rome). Across the road: the Scala Santa (Holy Stairs).",
                HOURS to "Daily about 7:00-18:30.",
                PRICES to "Free (cloister small fee)."
            ),
            place(
                "Santa Maria Maggiore",
                WHERE to "Piazza di Santa Maria Maggiore, Esquilino.",
                GETTING_THERE to "About 5-10 min walk from Termini.",
                WHAT_TO_DO to "One of the four papal basilicas, with 5th-century mosaics and a gilded ceiling. Nearby: StarBaps (Korean Food), Museum of Illusions.",
                HOURS to "Daily about 7:00-18:45.",
                PRICES to "Free."
            ),
            place(
                "San Paolo fuori le Mura (St. Paul Outside the Walls)",
                WHERE to "Via Ostiense, south of the center.",
                GETTING_THERE to "Metro B Basilica San Paolo.",
                WHAT_TO_DO to "The second largest of the papal basilicas, built over the tomb of St. Paul; huge columned hall and portraits of every pope.",
                HOURS to "Daily about 7:00-18:30.",
                PRICES to "Free (cloister small fee)."
            ),
            place(
                "Also worth seeing (all free)",
                WHAT_TO_DO to "Santa Maria in Trastevere (golden mosaics). San Luigi dei Francesi near the Pantheon (three Caravaggio paintings - bring coins for the lights). " +
                    "Sant'Ignazio (painted fake 'dome' - use the mirror in the nave). San Pietro in Vincoli (Michelangelo's Moses, Metro B Cavour). " +
                    "Santa Maria del Popolo on Piazza del Popolo (two Caravaggios). Churches usually close for lunch, about 12:30-15:30."
            )
        )
    ),
    Attraction(
        "piazza_venezia", "Piazza Venezia",
        "The big central square at the meeting point of ancient and modern Rome.",
        listOf(
            place(
                null,
                WHERE to "At the end of Via del Corso, below the Capitoline Hill.",
                GETTING_THERE to "Many buses stop here. (Metro C is being extended here, but the Venezia station is not open yet.)",
                WHAT_TO_DO to "The huge white Vittoriano / Altare della Patria monument: climb the free terraces for views; a paid panoramic lift ('Roma dal Cielo') goes to the top. " +
                    "Next door: the Capitoline Hill (Michelangelo's square, Capitoline Museums) and the start of Via dei Fori Imperiali to the Colosseum. Coffee: Caffè Toraldo / Plebiscito (see Coffee).",
                HOURS to "Square always open; the Vittoriano terraces are open daily during the day.",
                BOOK to "No for the square and terraces; the panoramic lift can be booked online.",
                PRICES to "Square and terraces free; the panoramic lift is paid (check the official Vittoriano / VIVE site for the current price).",
                HOW_TO_BOOK to "Lift tickets on the official Vittoriano (VIVE) site or at the monument."
            )
        )
    ),
    Attraction(
        "piazza_del_popolo", "Piazza del Popolo",
        "The grand oval square at the old northern gate of Rome.",
        listOf(
            place(
                null,
                WHERE to "Northern end of Via del Corso, below the Pincio and Villa Borghese.",
                GETTING_THERE to "Metro A Flaminio, 2 min walk.",
                WHAT_TO_DO to "The Egyptian obelisk, the 'twin' churches, and Santa Maria del Popolo with two Caravaggio paintings (free). Walk up to the Pincio terrace for the classic view, then into Villa Borghese. " +
                    "Shopping starts here along Via del Corso; Explora (Kids Museums) and Sensorio Coffee Lab (Coffee) are nearby.",
                HOURS to "Always open.",
                BOOK to FREE_NO_BOOKING,
                PRICES to "Free.",
                HOW_TO_BOOK to "Not needed."
            )
        )
    ),
    Attraction(
        "christmas_markets", "Christmas Markets",
        "Rome's Christmas season runs from about 1 December to 6 January (the Epiphany - 'La Befana', the Italian Christmas witch who brings sweets). " +
            "Most markets are free to enter. 2026 dates mostly come from the city's calendar and can shift - check before going.",
        listOf(
            place(
                "Piazza Navona - the main Christmas market",
                WHERE to "Piazza Navona, Centro Storico.",
                GETTING_THERE to "Bus to Corso del Rinascimento or Largo di Torre Argentina.",
                WHAT_TO_DO to "Rome's most famous Christmas market: stalls with toys, sweets, nativity figures (presepi), Befana stockings and a carousel. It ends on 6 January with the Befana festivities.",
                HOURS to "Expected about 1 December 2026 - 6 January 2027; reportedly about 9:00-01:00 (later on holidays). Best in the evening.",
                BOOK to "No.",
                PRICES to "Free entry.",
                HOW_TO_BOOK to "Not needed."
            ),
            place(
                "Christmas World - Villa Borghese",
                WHAT_TO_DO to "Rome's biggest Christmas village (ticketed): 28 November 2026 - 10 January 2027. See Villa Borghese for hours, prices and booking."
            ),
            place(
                "EUR Christmas village",
                WHERE to "EUR district, south Rome (Metro B).",
                HOURS to "About late November / early December 2026 to about 10 January 2027 (sources differ)."
            ),
            place(
                "Neighborhood markets",
                WHAT_TO_DO to "Piazza Mazzini in Prati (about 1 December - 6 January, about 8:00-21:00), Trastevere (about 1-30 December), Piazza Vittorio and Testaccio (about 5-26 December)."
            ),
            place(
                "Short events",
                WHAT_TO_DO to "Artisan Christmas market at the Mercato Centrale, Termini station (4-6 December 2026). " +
                    "Cinecittà World theme park (outside the city, ticketed) runs a Christmas season from 7 November 2026 to 6 January 2027."
            )
        )
    )
)
