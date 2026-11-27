package com.example.italytravel.screens

import androidx.compose.runtime.Composable

private const val REGION = "Region in Rome"
private const val ADDRESS = "Address"
private const val GETTING_THERE = "Getting there"
private const val ABOUT = "About"
private const val STYLE = "Italian or specialty"
private const val BEANS = "Coffee beans for sale"
private const val HOURS = "Hours and days"

private const val PANTHEON_ACCESS =
    "No metro at the Pantheon. Walk 10-15 min from Metro A Spagna or Barberini, or take a bus to Largo di Torre Argentina / Via del Corso."

@Composable
fun RomeCoffeeScreen(onBack: () -> Unit) {
    InfoScreen(title = "Rome - Coffee", onBack = onBack) {
        SectionHeader("How Coffee Works in Rome")
        BodyText(
            "Italian bars serve espresso-based coffee fast and cheap at the counter (al banco). " +
                "In busy places you often pay first at the cassa and show the receipt to the barista. " +
                "Sitting at a table can cost more. Cappuccino is traditionally a morning drink."
        )
        BodyText(
            "Italian (traditional): darker-roasted house blends, often Arabica + Robusta, served as a quick espresso. " +
                "Specialty (third-wave): lighter roasts, single-origin beans, filter / pour-over options, and baristas who explain the coffee."
        )
        BodyText("Hours below come from listings that often disagree - check Google Maps or the place's Instagram before going.")

        GroupHeader("Traditional Italian Coffee Bars & Roasters")

        PlaceEntry(
            "Sant'Eustachio Il Caffè",
            REGION to "Centro Storico, Pantheon area (about 2 min from the Pantheon).",
            ADDRESS to "Piazza di Sant'Eustachio 82.",
            GETTING_THERE to PANTHEON_ACCESS,
            ABOUT to "One of Rome's most famous coffee bars, open since 1938. Roasts its own Arabica beans over a wood fire. " +
                "The signature 'gran caffè' is a frothy, creamy espresso that comes ALREADY SWEETENED - say 'amaro' if you want it without sugar.",
            STYLE to "Italian (artisanal, traditional).",
            BEANS to "Yes - beans, ground coffee and capsules in the bright yellow packaging; a popular souvenir.",
            HOURS to "Daily, about 7:30 to 01:00 (later on Friday and Saturday)."
        )
        PlaceEntry(
            "Emporio Sant'Eustachio",
            REGION to "Centro Storico, Pantheon area (around the corner from Sant'Eustachio Il Caffè).",
            ADDRESS to "Via della Maddalena 36.",
            GETTING_THERE to PANTHEON_ACCESS,
            ABOUT to "The same company's newer, roomier café-shop: tables, Wi-Fi, food and lunch, plus the full range of Sant'Eustachio coffee products and gifts. " +
                "Good when you want to sit down instead of drinking at the crowded original bar.",
            STYLE to "Italian - the same Sant'Eustachio house coffee, not a specialty roaster.",
            BEANS to "Yes - beans, ground coffee, capsules and gift packs.",
            HOURS to "Daily, about 7:30 to 22:00/23:00 (aggregator listings)."
        )
        PlaceEntry(
            "Tazza d'Oro (La Casa del Caffè)",
            REGION to "Centro Storico, Pantheon area (just off Piazza della Rotonda).",
            ADDRESS to "Via degli Orfani 84.",
            GETTING_THERE to PANTHEON_ACCESS,
            ABOUT to "Historic bar roasting its own coffee since 1944; house blend 'La Regina dei Caffè'. " +
                "Famous for granita di caffè con panna - frozen coffee granita with whipped cream, perfect on a hot day.",
            STYLE to "Italian (traditional roaster).",
            BEANS to "Yes - beans and ground coffee to take home.",
            HOURS to "Monday-Saturday 7:00-20:00; Sunday about 10:00/10:30-19:00/19:30 (sources differ on Sunday)."
        )
        PlaceEntry(
            "Giolitti",
            REGION to "Centro Storico, Pantheon / Montecitorio area (about 3 min from the Pantheon).",
            ADDRESS to "Via degli Uffici del Vicario 40.",
            GETTING_THERE to PANTHEON_ACCESS,
            ABOUT to "Historic family business (since 1900), most famous for its gelato, but also a full classic coffee bar with pastries. Very busy in the afternoon.",
            STYLE to "Italian (traditional bar).",
            BEANS to "Not confirmed - no own-brand beans found; ask in the shop.",
            HOURS to "Daily, about 7:00 to 01:30 per most listings (one says until midnight)."
        )
        PlaceEntry(
            "illy Montecitorio (the illy café on Giolitti's street)",
            REGION to "Centro Storico, Pantheon / Montecitorio area.",
            ADDRESS to "Via degli Uffici del Vicario 31/32 - the same street as Giolitti (formerly 'Espressamente illy').",
            GETTING_THERE to PANTHEON_ACCESS,
            ABOUT to "Flagship café of illy, the big Italian coffee brand from Trieste. Reopened after a restyle with a new look and a food menu.",
            STYLE to "Italian (large brand, one signature house blend).",
            BEANS to "illy stores sell illy beans, ground coffee and capsules - ask at the counter.",
            HOURS to "Unclear - an older listing says about 8:00-19:00, closed Sunday. Check before going."
        )
        PlaceEntry(
            "Castroni",
            REGION to "Prati (near the Vatican).",
            ADDRESS to "Via Cola di Rienzo 196.",
            GETTING_THERE to "Metro A Ottaviano or Lepanto, then about 5-10 min walk.",
            ABOUT to "Historic gourmet food shop (since 1932) with a busy standing coffee bar. Roasts its own coffee. Also great for Italian and international delicacies.",
            STYLE to "Italian (traditional roaster).",
            BEANS to "Yes - beans or ground, including blends and single origins from Central America, Africa and Indonesia.",
            HOURS to "Monday-Saturday about 8:00-20:00; Sunday listed as about 9:30-20:00 by one source - check."
        )
        PlaceEntry(
            "Sciascia Caffè 1919",
            REGION to "Prati (near the Vatican).",
            ADDRESS to "Via Fabio Massimo 80/A.",
            GETTING_THERE to "Metro A Ottaviano or Lepanto, then about 5 min walk.",
            ABOUT to "Elegant historic bar and roaster (brand from 1919, at this location since 1937). Known for its caffè al cioccolato - espresso in a cup lined with melted dark chocolate.",
            STYLE to "Italian (traditional roaster).",
            BEANS to "It is a torrefazione (roaster), but retail bean sales were not confirmed - ask.",
            HOURS to "About 7:00-21:00; Sunday may be shorter."
        )
        PlaceEntry(
            "Torrefazione Ciamei",
            REGION to "Esquilino (near Piazza Vittorio).",
            ADDRESS to "Via Emanuele Filiberto 57.",
            GETTING_THERE to "Metro A Vittorio Emanuele or Manzoni, then about 5 min walk.",
            ABOUT to "Family roaster making its own blends since the early 1900s, with a coffee bar and shop at the company's headquarters.",
            STYLE to "Italian (Arabica / Robusta house blends).",
            BEANS to "Yes - beans and freshly ground coffee; a good souvenir.",
            HOURS to "Monday-Saturday 7:00-20:00. Closed Sunday."
        )
        PlaceEntry(
            "Caffè Toraldo (also called Bar Toraldo)",
            REGION to "Centro Storico, Pigna / Piazza Venezia area.",
            ADDRESS to "Via del Plebiscito 104 - look for the 'Plebiscito' sign. It is inside Plebiscito (Mercato Plebiscito), a food-hall-style market where you find the Toraldo café counter " +
                "and the Ristorante Plebiscito, which serves Italian food and pizza and has a wine bar. Listed as 'Toraldo - Caffè del Plebiscito'; Toraldo cafés are often called 'Bar Toraldo'.",
            GETTING_THERE to "Many buses stop at Piazza Venezia, a few steps away; about 7 min walk from the Pantheon.",
            ABOUT to "The Rome café of Toraldo, a Neapolitan coffee roaster since 1960. Neapolitan-style espresso - dark-roasted and strong - with Neapolitan pastries: " +
                "reviewers praise the babà, cornetti, fried pizza and fresh sandwiches. Coffee is a little pricier than average.",
            STYLE to "Italian (traditional Neapolitan).",
            BEANS to "Toraldo sells its blends (beans, ground coffee, pods and capsules) - ask at the counter.",
            HOURS to "Daily 7:00-23:00 (official Plebiscito site)."
        )
        PlaceEntry(
            "Artigiano del Caffè",
            REGION to "Prati (near the Vatican).",
            ADDRESS to "Via Famagosta 14.",
            GETTING_THERE to "Metro A Ottaviano, about 3 min walk.",
            ABOUT to "Small micro-roastery and bar that roasts everything in-house: six blends plus single-origin beans, with good pastries.",
            STYLE to "In between - an Italian espresso bar that leans toward specialty.",
            BEANS to "Yes - its own blends and single origins.",
            HOURS to "Monday-Saturday from about 7:00, closing 14:00/16:00/18:00 depending on the listing; Sunday about 8:00-13:00."
        )

        GroupHeader("Specialty (Third-Wave) Coffee")

        PlaceEntry(
            "Slow - Specialty Coffee & Pastry Lab",
            REGION to "Centro Storico (Largo di Torre Argentina).",
            ADDRESS to "Corso Vittorio Emanuele II 73. (A second listing, 'Slow Roma', shows Via del Pellegrino 75 - possibly another branch.)",
            GETTING_THERE to "Largo di Torre Argentina: many buses and tram 8. About 10 min walk from the Pantheon.",
            ABOUT to "Specialty coffee with pastries made in-house. Seating around a communal table; busy in the morning and pastries sell out early.",
            STYLE to "Specialty.",
            BEANS to "Not confirmed - ask (specialty cafés usually sell bags).",
            HOURS to "Daily 8:00-20:00 per most listings (one says 8:00-16:00) - check."
        )
        PlaceEntry(
            "Faro - Luminari del Caffè",
            REGION to "Salario / Castro Pretorio (near Porta Pia).",
            ADDRESS to "Via Piave 55.",
            GETTING_THERE to "About 10 min walk from Metro B Castro Pretorio or Metro A Repubblica.",
            ABOUT to "One of Rome's best-known specialty bars - top rating ('Tre Tazzine') in the Gambero Rosso Bar 2025 guide. The coffee menu rotates and baristas explain each coffee.",
            STYLE to "Specialty.",
            BEANS to "Ask - retail bags are often available.",
            HOURS to "Weekdays about 7:00/8:00-17:00/18:00, later opening on weekends (sources differ; check Instagram @farorome)."
        )
        PlaceEntry(
            "Luna by Faro",
            REGION to "Monti (between Piazza Barberini and Santa Maria Maggiore).",
            ADDRESS to "Via delle Quattro Fontane 175.",
            GETTING_THERE to "Metro A Barberini or Repubblica, then a short walk.",
            ABOUT to "Faro's bigger sister café: specialty coffee plus a bakery and brunch.",
            STYLE to "Specialty.",
            BEANS to "Ask.",
            HOURS to "From 8:00; weekdays until about 16:00-17:00, weekends until about 17:00-19:00 (sources differ)."
        )
        PlaceEntry(
            "Fax Factory",
            REGION to "Pigneto (east Rome, a lively neighborhood).",
            ADDRESS to "Via Antonio Raimondi 87.",
            GETTING_THERE to "Metro C Pigneto, then a short walk.",
            ABOUT to "Opened in 2018. Espresso, pour-over and other brew methods with Italian and international roasters plus a house blend. Doubles as an art gallery for Roman artists.",
            STYLE to "Specialty.",
            BEANS to "Ask.",
            HOURS to "Monday-Saturday 8:00-18:00, Sunday 9:00-13:00."
        )
        PlaceEntry(
            "Sensorio Coffee Lab",
            REGION to "Flaminio (just north of Piazza del Popolo).",
            ADDRESS to "Via Flaminia 69.",
            GETTING_THERE to "Metro A Flaminio or tram 2, then a short walk.",
            ABOUT to "Specialty coffee lab with its own roasting and an online shop.",
            STYLE to "Specialty.",
            BEANS to "Yes.",
            HOURS to "Weekdays 8:00-16:00 (its own site also mentions 18:00), weekends 9:00-16:00."
        )
    }
}
