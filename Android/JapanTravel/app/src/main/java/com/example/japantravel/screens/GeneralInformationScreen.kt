package com.example.japantravel.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun GeneralInformationScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "General Information", onBack = onBack) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            SectionHeader("Shinkansen (Bullet Train)")
            BodyText(
                "Book a reserved seat at a ticket counter (\"Midori no Madoguchi\"), a ticket " +
                    "machine, or an app such as SmartEX / the JR East or JR West apps. If you have " +
                    "a Japan Rail Pass, reserved seats are included at no extra cost."
            )
            BodyText(
                "Luggage size limit: bags up to 160cm (length + width + height combined) travel " +
                    "with no reservation needed. Bags between 160cm and 250cm require a free " +
                    "oversized-baggage seat reservation on the Tokaido, Sanyo, and Kyushu " +
                    "Shinkansen lines (this covers the **Tokyo**-**Kyoto**-**Osaka** route). Bags over 250cm " +
                    "are not allowed on board at all. Boarding with an oversized bag without a " +
                    "reservation can incur a ¥1,000 handling fee."
            )
            BodyText(
                "Sending luggage ahead: most hotels can arrange \"takkyubin\" (also called " +
                    "takuhaibin), Japan's luggage-forwarding courier service run by Yamato " +
                    "Transport (the black cat logo). Ask the front desk, fill out a short form " +
                    "with the next hotel's name/address and your check-in date, and pay per bag. " +
                    "Delivery is typically next-day, costs roughly ¥1,650-2,530 per bag, and " +
                    "follows the same 160cm / 25kg size and weight limit."
            )

            SectionHeader("Visit Japan Web (arrival registration for visa-exempt visitors)")
            BodyText(
                "Visit Japan Web (vjw.digital.go.jp) is a free government web service - no app to " +
                    "install - that lets you fill in your immigration arrival card and customs " +
                    "declaration online in advance and generates QR codes to show at the airport. " +
                    "It's for visa-exempt travelers registering their arrival details, not a visa " +
                    "itself - if your nationality requires a visa, you still need to obtain that " +
                    "separately before flying."
            )
            BodyText(
                "Where and when: register any time after booking your flight, at vjw.digital.go.jp. " +
                    "Aim to finish at least about 6 hours before landing so your QR code is active " +
                    "in time - the whole process takes roughly 15 minutes."
            )
            BodyText(
                "Can I still fill in paperwork on arrival? Yes - registering in advance is " +
                    "optional. Paper arrival cards and customs declaration forms are still handed " +
                    "out on the plane and accepted at every Japanese airport if you didn't " +
                    "register beforehand."
            )
            BodyText(
                "The QR code: registering generates two QR codes, one for immigration and one for " +
                    "customs. Screenshot or download them before you land, since airport Wi-Fi " +
                    "right after arrival can be patchy. At major airports (**Narita**, **Haneda**, **Kansai**, " +
                    "**Chubu**, **Fukuoka**) these are scanned at automated \"Joint Kiosks\" that handle " +
                    "immigration and customs together in one step, rather than queueing for each " +
                    "separately."
            )
            BodyText(
                "Is it related to tax-free shopping? Not directly, at least for now - tax-free " +
                    "(consumption tax exempt) shopping is currently its own separate process, done " +
                    "in-store by showing your passport at each participating shop. That said, this " +
                    "is actively changing: from November 1, 2026, Japan is switching tax-free " +
                    "shopping to a refund-based system, where you pay the tax at purchase and then " +
                    "claim a refund before departure - a different procedure from Visit Japan " +
                    "Web's QR codes. Since this is a live change, it's worth checking the current " +
                    "process closer to your travel date."
            )

            SectionHeader("The Yen (¥)")
            BodyText(
                "Japan's currency is the yen (¥). Cash is still widely used alongside cards and " +
                    "IC cards, so it's worth carrying some at all times."
            )
            BodyText(
                "Approximate conversion: 100 JPY ≈ 1.9 ILS (roughly 1 ILS ≈ 52-53 JPY). This rate " +
                    "changes daily, so check the current rate shortly before and during the trip."
            )

            SectionHeader("Currency Exchange")
            BodyText(
                "Where to exchange: banks (SMBC, MUFG, Mizuho), post offices, licensed exchange " +
                    "counters at airports and near major stations (Travelex, World Currency Shop, " +
                    "Daikoku, GPA), and some large hotel lobbies."
            )
            BodyText(
                "How to confirm it's legitimate: it should be licensed by Japan's Ministry of " +
                    "Finance. Look for a permanent storefront, prominently posted rates, and a " +
                    "printed receipt. Be wary of high-pressure \"this rate is only good right now\" " +
                    "tactics - legitimate counters don't rush you."
            )
            BodyText(
                "Which currencies are accepted: USD and EUR are the most widely accepted and get " +
                    "the best rates; many counters also take CNY, KRW, TWD, SGD, HKD, THB, and VND. " +
                    "**Israeli Shekel (ILS) is generally not accepted** at Japanese exchange counters - " +
                    "bring USD or EUR cash to convert, or rely on ATM withdrawals or a travel debit " +
                    "card instead (see below). Foreign currency cannot be used directly as payment " +
                    "anywhere in Japan - everything must be converted to yen first."
            )

            SectionHeader("Prepaid Travel Debit Cards (Wise / Revolut)")
            BodyText(
                "What they are: multi-currency cards you top up via an app and then spend or " +
                    "withdraw in yen at (near-)real exchange rates, without needing a Japanese bank " +
                    "account."
            )
            BodyText(
                "Worth it? Yes - especially useful here since ILS isn't easily exchanged in Japan. " +
                    "Wise gives the mid-market rate every day including weekends, plus free ATM " +
                    "withdrawal up to about ¥30,000/month. Revolut is strong for short trips under " +
                    "its roughly ¥300,000/month fee-free allowance, but its Standard plan adds a " +
                    "weekend markup on currency conversion."
            )
            BodyText(
                "Practical tips: order and activate the card before departure, since a physical " +
                    "card takes time to arrive by mail. Check whether it can be funded directly from " +
                    "ILS or needs an intermediate currency like USD or EUR. **7-Eleven** / Seven Bank " +
                    "ATMs are the most reliably foreign-card-friendly cash machines nationwide, worth " +
                    "keeping as a backup."
            )

            SectionHeader("Onsens & Ryokans")
            BodyText(
                "An onsen is a natural hot spring bath, found as standalone bathhouses or as part " +
                    "of a hotel/ryokan. Bathers wash and rinse thoroughly before entering the " +
                    "water, then bathe nude (swimsuits are generally not worn). Baths are often " +
                    "separated by gender, and some onsens restrict guests with visible tattoos."
            )
            BodyText(
                "A ryokan is a traditional Japanese inn, with tatami-mat rooms, futon bedding laid " +
                    "out on the floor, and often a multi-course kaiseki dinner and breakfast " +
                    "included. Many ryokans have their own onsen baths for guests."
            )

            SectionHeader("IC Cards (Suica / Pasmo / ICOCA)")
            BodyText(
                "These are rechargeable tap-to-pay cards for trains, subways, buses, vending " +
                    "machines, and many convenience stores. Suica is issued by JR East for the " +
                    "**Tokyo** region; Pasmo is issued by **Tokyo**'s private railway and bus operators; " +
                    "ICOCA is issued by JR West for the **Kansai** region (**Osaka**, **Kyoto**, **Kobe**)."
            )
            BodyText(
                "Since 2013, these cards (and several others) are mutually usable nationwide, so " +
                    "any one of Suica, Pasmo, or ICOCA works for transit in **Tokyo**, **Kyoto**, and " +
                    "**Osaka** alike. A card can only be refunded at a station in its home region " +
                    "(e.g. a Suica must be refunded at a JR East station in **Tokyo**)."
            )

            SectionHeader("Public Transportation")
            BodyText(
                "Trains are the backbone of getting around: JR lines, private railways, and " +
                    "subways (Tokyo Metro and Toei in **Tokyo**, Osaka Metro in **Osaka**) cover almost " +
                    "everywhere you'll want to go. They're frequent, punctual, and reasonably " +
                    "priced for city and regional hops - see the Shinkansen section above for " +
                    "longer intercity trips, and the IC Cards section above for how to pay."
            )
            BodyText(
                "Buses fill in the gaps trains don't reach: local city buses (flat or " +
                    "distance-based fare depending on the city, IC card accepted, often pay when " +
                    "you get off), long-distance highway buses (a much cheaper but slower " +
                    "alternative to the Shinkansen for city-to-city travel), and airport " +
                    "limousine buses that run direct routes between airports and major hotels/" +
                    "stations."
            )
            BodyText(
                "Taxis are reliable and safe but noticeably more expensive than trains - useful " +
                    "late at night once trains stop running (roughly midnight to 5am) or when " +
                    "carrying a lot of luggage. Doors open and close automatically - don't touch " +
                    "them. Card payment is increasingly accepted, but it's worth carrying cash as " +
                    "a backup, and tipping is not expected."
            )
            BodyText(
                "Getting to/from the airport with luggage: from **Narita**, the Narita Express, the " +
                    "Skyliner, or an airport limousine bus all reach central **Tokyo** in roughly " +
                    "60-90 minutes. From **Haneda**, the Keikyu Line or the Tokyo Monorail get you " +
                    "into central **Tokyo** in about 30-40 minutes - **Haneda** is much closer to the " +
                    "city than **Narita**. From **Kansai** Airport, the Haruka express connects to **Kyoto** " +
                    "and **Osaka**. Remember that the Shinkansen has limited onboard luggage space " +
                    "(see the oversized-baggage reservation rule in the Shinkansen section above) " +
                    "- for large suitcases, a limousine bus is usually the least stressful option, " +
                    "and takkyubin luggage-forwarding (also covered above) can save you from " +
                    "hauling bags through stations at all."
            )
            BodyText(
                "For families: most stations have elevators, but not every small or older station " +
                    "does, so it's worth checking ahead if you're using a stroller. Avoid rush " +
                    "hour if you can (roughly 7:30-9:30am and 5:30-7:30pm) - trains get seriously " +
                    "packed, which is hard with kids and luggage. IC cards work fine for children " +
                    "too. When you do have both small kids and heavy luggage together, a taxi or " +
                    "limousine bus is often worth the extra cost over squeezing onto a crowded " +
                    "train."
            )

            SectionHeader("Sim and eSim")
            BodyText(
                "Network bands you need: Japan's 4G LTE network runs mainly on bands 1, 3, and " +
                    "19/18/26 in cities, plus 8 and 28 in rural areas. 5G uses the sub-6 bands " +
                    "n77/n78/n79. Most phones from roughly the last five years already support " +
                    "these bands, and your phone must be unlocked to use a local SIM or eSIM."
            )
            BodyText(
                "5G coverage outside major cities is still limited, so a 4G-capable SIM or eSIM " +
                    "is enough for most trips - don't feel you need a 5G-specific plan."
            )
            BodyText(
                "Data-only SIM/eSIM for tourists: global eSIM apps built on Japan's Docomo, au, " +
                    "SoftBank, and Rakuten networks are the simplest option - Airalo, Ubigi, " +
                    "Nomad, and Saily all sell Japan data plans installed instantly via QR code, " +
                    "no store visit needed."
            )
            BodyText(
                "Japan-based providers Mobal and Sakura Mobile also sell data-only eSIMs and " +
                    "physical SIMs, with the advantage of English-language customer support based " +
                    "in Japan itself."
            )
            BodyText(
                "One timing tip: some plans start counting their validity period from the moment " +
                    "you install the eSIM profile, not from first use - install it right before " +
                    "your trip rather than days in advance, or you can burn a day or two before " +
                    "you've even landed."
            )
            BodyText(
                "Data + Voice SIM (with a Japanese phone number): if you need to receive calls or " +
                    "texts locally (for restaurant reservations, delivery, or a longer stay), " +
                    "Mobal and Sakura Mobile both offer physical SIMs bundling a real Japanese " +
                    "phone number together with data, rather than data-only."
            )
            BodyText(
                "Recommendation - for a short trip and data only: Airalo or Ubigi eSIM - install " +
                    "before departure, roughly \$4-15 for 5-20GB over about 30 days, no Japanese " +
                    "number included. If you need a Japanese phone number alongside data: Mobal " +
                    "or Sakura Mobile."
            )

            SectionHeader("Renting a Car / Driving in Japan")
            BodyText(
                "Side of the road: Japan drives on the **left**, with right-hand-drive cars - the " +
                    "opposite of Israel or the US."
            )
            BodyText(
                "International Driving Permit (IDP) validity: an IDP is valid in Japan for one " +
                    "year from its **issue date**, or one year from your entry into Japan - whichever " +
                    "is shorter - regardless of any later expiration date printed on the permit " +
                    "itself. It must also be issued under the 1949 Geneva Convention format."
            )
            BodyText(
                "Why the rule exists: it was introduced to close a loophole where long-term " +
                    "foreign residents (including students) kept renewing or reissuing an IDP via " +
                    "brief trips abroad every few months, letting them drive indefinitely without " +
                    "ever converting to a Japanese license."
            )
            BodyText(
                "Practical tip: get the IDP shortly before departure rather than months ahead, so " +
                    "the one-year issue-date clock doesn't run out mid-trip."
            )

            SectionHeader("When Are Stores Open")
            BodyText(
                "General hours: most retail and department stores run roughly 10am-8pm."
            )
            BodyText(
                "Restaurants: independently-owned restaurants often close between lunch and " +
                    "dinner (e.g. 11am-2pm, then 5pm-11pm) - a siesta-like break similar to Italy. " +
                    "Chain and family restaurants (Gusto, Saizeriya, etc.) usually stay open all day " +
                    "without a break."
            )
            BodyText(
                "Convenience stores: traditionally open 24/7, though a growing number of locations " +
                    "(especially in rural areas) now trial shorter hours - don't assume every " +
                    "**konbini** is open all night."
            )
            BodyText(
                "Alcohol: no nationwide time restriction - convenience stores and supermarkets " +
                    "sell beer, sake, shochu, wine, and canned cocktails any hour of the day. Some " +
                    "municipalities restrict late-night off-premise sales locally (for example, " +
                    "11pm-5am in places)."
            )
            BodyText(
                "Drinking in public: legal nationwide, with no open-container law - drinking a " +
                    "canned drink in a park or on a sidewalk is normal. Exceptions are growing: some " +
                    "areas now have local ordinances banning street drinking during set hours (for " +
                    "example, **Shibuya** bans it 6pm-5am in specific public areas). Being loud, rowdy, " +
                    "or leaving trash behind is considered rude even where it's legal."
            )
            BodyText(
                "Eating or drinking while walking: legal, but a bit frowned upon - most people eat " +
                    "standing near where they bought the food rather than walking with it."
            )
            BodyText(
                "Garbage sorting: Japan separates trash into burnable, non-burnable, PET bottles, " +
                    "cans/glass, and plastic packaging (exact categories vary by municipality), each " +
                    "collected on its own designated day - mixed bags can be left uncollected. " +
                    "Hotel and ryokan guests can just use the room bin; convenience-store trash cans " +
                    "are meant for that store's own purchases, not outside litter."
            )
            Spacer(Modifier.height(16.dp))
        }
    }
}
