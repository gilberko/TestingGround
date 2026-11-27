package com.example.italytravel.screens

private const val WHERE = "Where is it"
private const val GETTING_THERE = "Getting there"
private const val WHAT_TO_DO = "What to do"
private const val HOURS = "Hours and days"
private const val BOOK = "Book in advance?"
private const val PRICES = "Prices"
private const val HOW_TO_BOOK = "How to book"
private const val KIDS = "For kids"
private const val NOTE = "Note"

private fun milanPlace(name: String?, vararg facts: Pair<String, String>) = AttractionPlace(name, facts.toList())

val milanAttractions: List<Attraction> = listOf(
    Attraction(
        "duomo", "Duomo di Milano",
        "Milan's gothic cathedral - one of the largest churches in the world, built over almost 600 years (1386 to the 1960s), with about 135 spires and thousands of statues. " +
            "On the highest spire stands the golden 'Madonnina', the symbol of the city.",
        listOf(
            milanPlace(
                "The Cathedral",
                WHERE to "Piazza del Duomo, the very center of Milan. The Galleria Vittorio Emanuele II opens off the north side of the square.",
                GETTING_THERE to "Metro M1 (red) or M3 (yellow) to Duomo - you come up right on the square.",
                WHAT_TO_DO to "See the huge stained-glass windows, the statue of St. Bartholomew Flayed (carrying his own skin like a cloak, by Marco d'Agrate, 1562), " +
                    "the red light high above the altar that marks a nail said to be from the Cross, and the archaeological area underneath with the remains of the older baptistery. " +
                    "The Duomo Museum (Museo del Duomo, in the Royal Palace next door) shows original statues and stained glass taken down from the cathedral.",
                HOURS to "Cathedral daily about 9:00-19:00 (last ticket about 18:00). The museum is closed on Wednesday.",
                BOOK to "Recommended, especially at weekends and in high season - queues for tickets and security can be long. From 1 November 2026 official tickets are valid only on the date you choose.",
                PRICES to "Cathedral + Duomo Museum about 10 euros (8 euros on Wednesday, when the museum is closed). Reduced tickets for children; small children free. Resellers charge more.",
                HOW_TO_BOOK to "Official website duomomilano.it (tickets section) or the ticket office beside the cathedral. " +
                    "Dress code: shoulders and knees covered; bags go through a security check."
            ),
            milanPlace(
                "The Terraces (rooftop)",
                WHAT_TO_DO to "Walk on the marble roof among the spires and statues, right up close to the Madonnina - with views over Milan and, on clear days, the Alps. The highlight of a Duomo visit and great for kids.",
                HOURS to "About 9:00-19:00 (last entry about 18:00-18:10). The lift may have shorter hours; in bad weather the terraces can close.",
                PRICES to "Duomo Pass (cathedral + terraces + museum): about 22 euros by stairs, 26 euros by lift; reduced about 11 / 13 euros. Terraces only: about 16 euros by stairs, 18 by lift.",
                BOOK to "Yes - book a time slot, especially for the lift.",
                HOW_TO_BOOK to "duomomilano.it. The stairs are about 250 steps; the lift saves most of the climb but there are still some steps at the top."
            ),
            milanPlace(
                "Galleria Vittorio Emanuele II",
                WHERE to "Next to the Duomo, connecting Piazza del Duomo with Piazza della Scala (La Scala opera house).",
                WHAT_TO_DO to "Walk through the beautiful glass-roofed 19th-century shopping arcade (1865-1877). Tradition: spin around on your heel on the bull mosaic on the floor for good luck. " +
                    "Coffee at Motta Milano 1928 (see Milan -> Coffee).",
                HOURS to "Always open (shops about 10:00-20:00).",
                PRICES to "Free."
            )
        )
    ),
    Attraction(
        "sempione", "Parco Sempione",
        "Milan's big central park (about 38 hectares), right behind the Castello Sforzesco - lawns, a pond, playgrounds and several sights.",
        listOf(
            milanPlace(
                null,
                WHERE to "Between the Castello Sforzesco and the Arco della Pace (Arch of Peace).",
                GETTING_THERE to "Metro M1 Cairoli or M2 Lanza to the castle side; M1 Cadorna for the Triennale side; trams to the Arco della Pace.",
                WHAT_TO_DO to "Picnic and walk to the Arco della Pace (Napoleonic triumphal arch). " +
                    "Torre Branca - a 108 m steel tower (1933) with a lift to the top for a view over the city (opening days and hours vary with the season - check). " +
                    "Triennale Milano - design museum and exhibitions at the park's edge. " +
                    "Acquario Civico (civic aquarium) in a historic Art Nouveau building at the park's edge. Arena Civica - an old 19th-century stadium.",
                KIDS to "A permanent playground with climbing castles, swings, a mini toy-car track and small rides such as a historic little train (paid per ride).",
                HOURS to "The park opens early morning to evening (later in summer); free.",
                PRICES to "Park free. Torre Branca, Triennale and the aquarium charge entry."
            ),
            milanPlace(
                "Are the carnival rides always there?",
                WHAT_TO_DO to "No. The big carnival-style rides are the Luna Park Meneghino - a traditional fair of about 60 rides (bumper cars, carousels, a big wheel, games and sweets stalls) " +
                    "that comes to Parco Sempione for Milan's Carnival (Carnevale Ambrosiano, which in Milan ends a few days later than elsewhere). " +
                    "The 2025 edition ran from 8 February to 16 March. Outside that season only the small permanent rides and playground remain, plus occasional fairs and events at other times of year.",
                HOURS to "In past editions: weekdays about 14:00-19:30, weekends and holidays about 10:00-20:00. Entry is free; you pay per ride.",
                BOOK to "Not needed.",
                PRICES to "Free entry; rides a few euros each.",
                NOTE to "The City of Milan has planned to move the Luna Park Meneghino from Parco Sempione to Quinto Romano (west Milan), possibly from 2026 - not confirmed. Check local news before Carnival."
            )
        )
    ),
    Attraction(
        "castello", "Castello Sforzesco",
        "The great brick castle of the Sforza dukes (15th century, built on an older Visconti fortress) - today home to several city museums with works by Michelangelo, Leonardo da Vinci and Mantegna.",
        listOf(
            milanPlace(
                null,
                WHERE to "Piazza Castello, at the end of Via Dante, about 15 min walk from the Duomo; Parco Sempione is right behind it.",
                GETTING_THERE to "Metro M1 (red) Cairoli (closest) or M2 (green) Lanza; or walk up Via Dante from the Duomo.",
                WHAT_TO_DO to "Walk through the free courtyards (Piazza d'Armi, Corte Ducale, Rocchetta), see the moat and the tall Filarete Tower over the main gate, then visit the museums (one ticket covers all of them).",
                HOURS to "Courtyards daily about 7:00-19:30. Museums Tuesday-Sunday 10:00-17:30 (last entry 16:30); closed Monday.",
                BOOK to "Usually not needed - buy at the ticket office. Book online at busy times.",
                PRICES to "Courtyards free. Museums: 10 euros full price since 10 May 2026 (was 5 euros - some sites still show the old price); reduced about 5 euros; under 18 reported free (check).",
                HOW_TO_BOOK to "Official site milanocastello.it or the ticket office inside the castle."
            ),
            milanPlace(
                "Museums and famous works",
                WHAT_TO_DO to "Pietà Rondanini (Museo Pietà Rondanini, in the old Spanish Hospital) - Michelangelo's last sculpture, still unfinished: he was working on it days before he died in 1564. The castle's most famous work.\n" +
                    "Sala delle Asse (Museum of Ancient Art) - a room painted by Leonardo da Vinci (about 1498) as a pergola of intertwined tree branches. Often closed or partly visible during long restoration work - check before going.\n" +
                    "Museum of Ancient Art - sculpture from Roman times to the Renaissance: the equestrian monument of Bernabò Visconti, the tomb effigy of Gaston de Foix by Bambaia, and the armoury (weapons and armour collection).\n" +
                    "Pinacoteca (picture gallery) - paintings by Andrea Mantegna (the Trivulzio Madonna), Giovanni Bellini, Antonello da Messina, Correggio, Titian and Canaletto.\n" +
                    "Also: the Trivulzio Tapestries of the Months (designed by Bramantino), the Museum of Musical Instruments, the Furniture Museum, Applied Arts, the Egyptian Museum and the Prehistory collection (in the castle basement).",
                KIDS to "Kids usually like the armoury, the Egyptian mummies and running around the courtyards and moat.",
                HOURS to "Allow 2-3 hours for the main museums."
            )
        )
    )
)
