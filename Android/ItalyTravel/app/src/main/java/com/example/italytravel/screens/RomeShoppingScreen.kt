package com.example.italytravel.screens

import androidx.compose.runtime.Composable

private const val BUY = "What to buy"
private const val PRICES = "Prices"
private const val TIER = "Budget or premium"
private const val WHERE = "Where in Rome"
private const val HOURS = "Hours and days"

private const val CENTER_HOURS = "City-center stores usually open daily about 10:00-20:00, including Sunday - check the brand's store locator for the branch you want."

@Composable
fun RomeShoppingScreen(onBack: () -> Unit) {
    InfoScreen(title = "Rome - Shopping", onBack = onBack) {
        SectionHeader("Shopping in Rome")
        BodyText(
            "The main high-street shopping street is Via del Corso (from Piazza del Popolo to Piazza Venezia), with more stores on Via del Tritone, Via Nazionale, Via Cola di Rienzo (Prati) and in the shopping arcade under Termini station. " +
                "Luxury brands are on Via Condotti and around the Spanish Steps. Big malls are in Rome -> Shopping Centers."
        )
        BodyText(
            "Chain stores in the center are usually open every day about 10:00-20:00 (Sundays too); small independent shops may close at lunchtime and on Sunday. " +
                "Budget / mid / premium below is general guidance."
        )

        GroupHeader("Clothing")
        PlaceEntry(
            "Zara",
            BUY to "Spanish fashion for women, men and kids - trendy clothes, shoes, bags.",
            PRICES to "T-shirts and tops about 15-40 euros, trousers 30-50 euros, jackets and coats about 50-150 euros.",
            TIER to "Mid-range.",
            WHERE to "Flagship in Palazzo Bocconi on Via del Corso (reopened after a redesign), plus Euroma2 and Porta di Roma malls.",
            HOURS to CENTER_HOURS
        )
        PlaceEntry(
            "H&M",
            BUY to "Swedish fashion: basics, trendy pieces, kids' clothes, underwear, some home goods.",
            PRICES to "T-shirts from about 7-15 euros, jeans about 25-40 euros.",
            TIER to "Budget to mid.",
            WHERE to "Several stores on and around Via del Corso and in the big malls - check the H&M app for exact addresses.",
            HOURS to CENTER_HOURS
        )
        PlaceEntry(
            "Uniqlo",
            BUY to "Japanese quality basics: Heattech thermal underwear, ultra-light down jackets, fleece, jeans, T-shirts (UT graphic tees).",
            PRICES to "T-shirts about 15-20 euros, Heattech about 15-25 euros, down jackets about 50-100 euros.",
            TIER to "Mid-range, good value for quality.",
            WHERE to "Via del Corso 197, inside Galleria Alberto Sordi (opened April 2024, three floors); a second store at Termini station (opened September 2024).",
            HOURS to "Via del Corso: about 10:00-20:00 Monday-Thursday, a bit later Friday-Sunday."
        )
        PlaceEntry(
            "OVS",
            BUY to "Italy's biggest clothing chain: everyday clothes for the whole family, lots of kids' and baby clothes, underwear, socks, pyjamas.",
            PRICES to "Socks from about 7 euros a pack, T-shirts about 10-15 euros, kids' clothes cheap.",
            TIER to "Budget.",
            WHERE to "Via del Tritone 172, Piazza Vittorio, near Termini (Via Giolitti); OVS Kids at Porta di Roma mall.",
            HOURS to CENTER_HOURS
        )
        PlaceEntry(
            "Doppelgänger",
            BUY to "A Rome-born (1996) menswear brand: suits, tuxedos, shirts, ties, knitwear - smart clothes at accessible prices; some stores for women too.",
            PRICES to "Shirts and ties cheap-to-moderate; complete suits far below designer prices.",
            TIER to "Mid-range - 'affordable smart'.",
            WHERE to "Many stores in Rome: Via del Corso, Via del Tritone, Via Frattina, Piazzale Appio, and the malls.",
            HOURS to CENTER_HOURS
        )
        PlaceEntry(
            "Deichmann",
            BUY to "German shoe chain: shoes for the whole family, sneakers (own brands and some big brands), kids' shoes, bags.",
            PRICES to "Most shoes about 20-50 euros.",
            TIER to "Budget.",
            WHERE to "Mostly outside the historic center: Via Appia Nuova, Via Tiburtina, Via Monte Cervialto, Euroma2 mall and others.",
            HOURS to "Usually about 9:00-20:00 - check the Deichmann store finder."
        )

        GroupHeader("Electronics")
        BodyText(
            "Media Markt is called MediaWorld in Italy (same company, MediaMarktSaturn). Its main Italian rival - the shop that is 'a bit like Media Markt' - is Unieuro. " +
                "Neither has a big store inside the historic center; the handiest for visitors is Unieuro at Termini station."
        )
        PlaceEntry(
            "MediaWorld (Media Markt)",
            BUY to "Electronics superstore: phones, laptops, tablets, cameras, TVs, headphones, gaming consoles and games, chargers and adapters, small and large appliances. Some stores have Apple and Samsung areas.",
            PRICES to "Normal Italian retail prices, with frequent promotions (check the weekly flyer / app).",
            TIER to "Mid-range (big chain).",
            WHERE to "Outside the center: Casal Bertone mall (Via Pollio 50, near Tiburtina station), Via della Lega Lombarda 40 (Piazza Bologna area), Viale della Primavera, Porta di Roma mall (the 'Tech Village'), RomaEst, Tor Vergata, GranRoma.",
            HOURS to "Most stores daily about 9:00-20:00 / 20:30; mall stores 10:00-21:00 or 22:00 (e.g. Porta di Roma 10:00-22:00). Check the store page on mediaworld.it."
        )
        PlaceEntry(
            "Unieuro",
            BUY to "Italy's big electronics and appliance chain - the Italian equivalent of Media Markt: phones, computers, gaming, TVs, small appliances, accessories.",
            PRICES to "Similar to MediaWorld, with frequent promotions.",
            TIER to "Mid-range (big chain).",
            WHERE to "Roma Termini - inside Termini station, level -1 (Piazza dei Cinquecento side): the most central. Also a large store in the Euroma2 mall and about 15 stores around Rome.",
            HOURS to "Termini: daily about 8:00-21:00. Euroma2: daily about 10:00-21:00."
        )

        GroupHeader("K-Pop and Korean Shopping")
        PlaceEntry(
            "Cheng Gong (K-pop shop)",
            BUY to "Often called Rome's first K-pop shop: K-pop albums, photocards, lightsticks, posters, merch and gadgets (BTS, Stray Kids, ATEEZ, TXT and more).",
            PRICES to "Album prices similar to K-pop shops elsewhere in Europe - check in store.",
            TIER to "Specialty shop.",
            WHERE to "Via dello Statuto 35c, Esquilino - a couple of minutes from Piazza Vittorio Emanuele II (Metro A Vittorio Emanuele), about 10 min walk from Termini. Phone 06 6065 9738.",
            HOURS to "Monday-Saturday; closed Sunday. Exact hours differ between listings - check its Instagram / call before going."
        )
        BodyText(
            "Also reported (social-media posts, not confirmed): Discoteca Laziale, a big music and DVD store near Termini (Via Mamiani 62/A, Monday-Saturday 9:00-19:30, closed Sunday) carries K-pop albums; " +
                "and a Korean market on Via Cavour sells K-pop items. For Korean food, see Rome -> Korean Food. Italian online K-pop shops (Nolae, K-Moon, Kaido Italia) ship within Italy."
        )

        GroupHeader("Gifts, Home and Everyday")
        PlaceEntry(
            "Legami (Legami Milano)",
            BUY to "Italian stationery and gift brand (from Bergamo): planners and diaries, gel pens, notebooks, mugs, water bottles, phone accessories, cute gifts - popular for souvenirs and presents.",
            PRICES to "Most items about 3-30 euros.",
            TIER to "Budget to mid.",
            WHERE to "About 17 stores in Rome, e.g. Via del Corso 98, near the Pantheon, Via del Tritone, Termini, Ottaviano, Cola di Rienzo, Euroma2, Porta di Roma.",
            HOURS to CENTER_HOURS
        )
        PlaceEntry(
            "Normal",
            BUY to "Danish discount store: cosmetics, skincare, personal care, cleaning products, snacks and drinks - over 100 new products every week in a maze-like store.",
            PRICES to "Low fixed prices - no sales or promotions.",
            TIER to "Budget.",
            WHERE to "Opened its first Italian stores in March 2025 at RomaEst and Porta di Roma malls (more may have opened since).",
            HOURS to "Mall hours - about 10:00-21:00/22:00."
        )
        PlaceEntry(
            "Kasanova",
            BUY to "Italian chain for the home and kitchen: pots and pans, moka pots, pasta and pizza tools, cutlery, kitchen gadgets, table and bathroom items, small appliances and some luggage - handy for Italian cooking souvenirs.",
            PRICES to "Many small items under 10-20 euros; regular promotions.",
            TIER to "Budget to mid.",
            WHERE to "Mostly outside the historic center: Piazza Vittorio Emanuele II (Esquilino, Metro A Vittorio Emanuele), Via Fabio Massimo 11/13 and Via Candia 35/37 (Prati, near the Vatican), Euroma2 mall, Casetta Mattei mall, Via di Acilia.",
            HOURS to "Usually daily about 9:30/10:00-20:00/20:30; Euroma2 10:00-21:00; small street stores may close Sunday or at lunchtime - check kasanova.com."
        )
        PlaceEntry(
            "La Feltrinelli",
            BUY to "Italy's biggest bookshop chain: books (some in English), maps and travel guides, comics, children's books, music and films, stationery and gifts. Several stores have a café.",
            PRICES to "Normal book prices; a good place for an Italian children's book or a map.",
            TIER to "Mid-range.",
            WHERE to "Galleria Alberto Sordi (Piazza Colonna, on Via del Corso), Largo di Torre Argentina 5/A, Via Vittorio Emanuele Orlando near Piazza della Repubblica / Termini (the Feltrinelli International store there focuses on foreign-language books), Via Appia Nuova 427, Viale Libia.",
            HOURS to "Galleria Alberto Sordi and Largo Argentina: about 9:00-21:00 (Sunday from 10:00). Others about 9:00/10:00-20:00, some with a Sunday lunch break. Hours differ between listings - check lafeltrinelli.it."
        )
        PlaceEntry(
            "Bialetti",
            BUY to "The classic Italian moka pot (Moka Express), electric espresso makers, coffee, cups and cookware - a classic Italian souvenir.",
            PRICES to "Moka pots about 25-60 euros depending on size; coffee and small items cheaper.",
            TIER to "Mid-range.",
            WHERE to "Largo Chigi 4 (on Via del Corso), Via Nazionale 52/54, Via dei Coronari 215 (near Piazza Navona), Termini station, Viale Libia.",
            HOURS to CENTER_HOURS
        )
    }
}
