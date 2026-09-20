package com.gilberko.japan.screens

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
fun RentingCarsAndDrivingScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "Renting Cars And Driving", onBack = onBack) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
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

            SectionHeader("Highways, Expressways & Tolls")
            BodyText(
                "No sticker needed: unlike Austria or Switzerland, Japan has no prepaid windshield " +
                    "vignette/sticker for expressways. Tolls are collected per use at toll gates " +
                    "instead."
            )
            BodyText(
                "How to pay without ETC: most expressways use a ticket-at-entry, pay-at-exit " +
                    "system - take a ticket from the machine when you enter, then hand it to the " +
                    "attendant (or feed it into the machine) at the exit gate, where the toll is " +
                    "calculated by distance traveled and paid in cash or by card."
            )
            BodyText(
                "What ETC is: Electronic Toll Collection lets you drive through certain lanes " +
                    "without stopping, but it requires **two** things - an in-car ETC unit (a small " +
                    "reader/antenna device) plus a separate **ETC card** inserted into it (not a " +
                    "regular credit or debit card). The unit reads the card and deducts the toll " +
                    "automatically as you pass through."
            )
            BodyText(
                "Is it available for rented cars? Yes - almost all Japanese rental cars now come " +
                    "with an ETC unit already installed. The ETC card itself is typically rented " +
                    "separately at the rental counter for a small fee (roughly ¥330-550), with " +
                    "tolls billed and settled when you return the car - ask for it when you pick " +
                    "up the vehicle."
            )
            BodyText(
                "Do some roads only support ETC? Increasingly, yes - this is becoming an important " +
                    "consideration, not just a minor detail. Through 2026, NEXCO Central and NEXCO " +
                    "West have been converting dozens of toll gates nationwide to **ETC-only, with " +
                    "no staffed or cash lane at all**, as part of a plan to convert nearly all toll " +
                    "stations nationwide by around 2030. If you don't have ETC, plan routes " +
                    "assuming some gates may be ETC-only - and if you do end up in an ETC-only lane " +
                    "by mistake, never reverse; drive forward to a lane marked \"ETC/Support\" and " +
                    "follow staff instructions. Given this trend, it's genuinely worth renting the " +
                    "ETC card with your car rather than planning to rely on cash lanes."
            )

            SectionHeader("Speed Limits & Enforcement")
            BodyText(
                "Officially, there's no legal tolerance - even 1 km/h over a posted limit like 50 " +
                    "is technically a violation, unlike the informal buffer some drivers assume " +
                    "from parts of Europe."
            )
            BodyText(
                "In practice, enforcement is looser than that strict reading, but exactly how " +
                    "much varies by source and isn't officially published - some sources describe " +
                    "practical police tolerance of roughly 10-19 km/h over depending on the road " +
                    "type, while others describe fixed speed cameras only triggering at much " +
                    "larger margins (roughly 30-40 km/h over). These figures are inconsistent " +
                    "across sources, so treat them as rough, unofficial estimates rather than a " +
                    "guaranteed buffer - don't plan on any tolerance existing."
            )
            BodyText(
                "Enforcement methods: fixed speed cameras (commonly called \"Orbis\" in Japan), " +
                    "mobile radar units, and patrolling police with radar or laser guns - all " +
                    "used nationwide, with a heavier presence on rural highways."
            )
            BodyText(
                "Bottom line: treat the posted number as the actual limit to obey, not as a " +
                    "starting point with margin built in."
            )

            SectionHeader("Parking")
            BodyText(
                "On-street parking is restricted, not a free-for-all: legal on-street spaces are " +
                    "specifically marked with white line markings and a \"P\" sign showing " +
                    "operating hours, while a blue sign with a red circle and diagonal line means " +
                    "no parking (a brief stop of a few minutes while staying in the vehicle for " +
                    "loading/unloading is generally fine even there). Don't assume an unmarked " +
                    "area is free to park in - treat unmarked curb space as off-limits."
            )
            BodyText(
                "Coin-operated lots (the main option in cities): pull into a numbered space, and " +
                    "after a few minutes a metal flap or lock plate rises from the ground under " +
                    "the car, physically preventing you from driving off (some larger lots use a " +
                    "gate-arm barrier instead). To leave, go to the central payment kiosk, enter " +
                    "your space number, pay the fee based on how long you stayed, and the flap " +
                    "lowers - you then have a short window (around 3 minutes) to drive away."
            )
            BodyText(
                "Do you need cash? Historically these lots were cash/coin-only, and it's still " +
                    "worth carrying cash as a backup, but IC cards, credit cards, and app-based " +
                    "payment are increasingly accepted at many lots today."
            )
            BodyText(
                "What is Park24 / Times? Park24 Co. is Japan's largest parking lot operator, " +
                    "running the yellow-and-black \"Times\" branded coin parking lots you'll see " +
                    "everywhere nationwide - it's the brand you'll most often park in."
            )
            BodyText(
                "Is there an app? Times Club (Park24's own app) lets you search for available " +
                    "Times lots on a map, reserve certain locations, and pay via the app. Akippa " +
                    "is a separate parking marketplace app where individuals and lot owners list " +
                    "spaces - you search by destination and reserve a specific spot in advance, " +
                    "useful for guaranteeing parking near a specific venue or event."
            )
            BodyText(
                "Illegal parking: Japanese police typically chalk-mark a wheel and the road, " +
                    "returning after 5-10 minutes - if the car hasn't moved, they issue a ticket " +
                    "(a yellow sticker placed on the windshield), and in congested areas may " +
                    "wheel-clamp or tow the car at the owner's expense. Standard fines run roughly " +
                    "¥10,000-18,000, paid after reporting to the police station named on the notice."
            )

            SectionHeader("Railway Crossings")
            BodyText(
                "Yes, you must fully stop - this is an actual legal requirement, not just a " +
                    "suggestion. Japanese traffic law requires drivers to come to a complete stop " +
                    "at every railway level crossing and check both directions before proceeding, " +
                    "even when the barrier is up and no train is visible. It's treated as a core " +
                    "safety habit and is tested as part of standard driving practice in Japan."
            )

            SectionHeader("Traffic Lights")
            BodyText(
                "Placement: driving guides for Japan commonly note that traffic signals are often " +
                    "mounted across the far side of the intersection rather than directly overhead " +
                    "at the stop line as in some other countries - so look toward the far side of " +
                    "the intersection for the light rather than straight up. This is widely " +
                    "repeated in tourist/expat driving guides, though it isn't something we could " +
                    "confirm against an official traffic authority source, so treat it as a " +
                    "helpful general expectation rather than a guaranteed rule at every " +
                    "intersection."
            )
            BodyText(
                "Orientation: standard mounting nationwide is horizontal (green-yellow-red, left " +
                    "to right). In heavy-snow regions - **Hokkaido** and **Tohoku** - signals are " +
                    "commonly mounted vertically instead (red at top), specifically to stop snow " +
                    "from building up on the light visors and blocking visibility."
            )
            BodyText(
                "Arrow signals: directional arrows (usually shown below or alongside the main " +
                    "light) permit a specific movement - most often a protected right turn - while " +
                    "the main light is red, meaning oncoming traffic and pedestrians are held. You " +
                    "may only proceed in the arrow's indicated direction when the main light is red " +
                    "and the arrow is lit."
            )
            Spacer(Modifier.height(16.dp))
        }
    }
}
