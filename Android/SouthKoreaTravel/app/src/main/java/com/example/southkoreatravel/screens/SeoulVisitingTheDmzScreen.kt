package com.example.southkoreatravel.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SeoulVisitingTheDmzScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "Visiting The DMZ", onBack = onBack) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(24.dp)
        ) {
            SectionHeader("Standard DMZ Tour")
            BodyText(boldNames("The \"Standard DMZ Tour\" is the name commonly used by operators for the general, southern-edge DMZ tour — typically covering the 3rd Infiltration Tunnel, Dora Observatory, Dorasan Station, and Imjingak Park/Freedom Bridge. It requires joining the official DMZ Peace and Security Tourist Program, and a passport check at a military checkpoint beyond Unification Bridge. This is a well-established, routine tour product that's been run daily for decades and is generally considered safe.", listOf("Standard DMZ Tour", "3rd Infiltration Tunnel", "Dora Observatory", "Dorasan Station", "Imjingak Park", "Freedom Bridge", "DMZ Peace and Security Tourist Program", "Unification Bridge")))

            SectionHeader("JSA / Panmunjom Tours")
            BodyText(boldNames("The Joint Security Area (JSA) is also correctly called Panmunjom — both names are used interchangeably by tour operators, the UN Command, and news outlets. A JSA tour is a separate, more restricted product from the Standard DMZ Tour, and it carries more political sensitivity since it sits directly on the Military Demarcation Line.", listOf("Joint Security Area", "JSA", "Panmunjom")))
            BodyText(boldNames("Access to JSA has been genuinely unstable since 2023: civilian tours were suspended in July 2023 after a US soldier crossed into North Korea during a visit, briefly resumed and reversed again in late 2023, partially resumed in May 2025 for specific government/education groups rather than general tourists, and were suspended again around October 2025 amid speculation over a possible US-North Korea summit. As of the most recent reporting, general public access remains inconsistent and tours can be cancelled on short notice. This status can change at any time — check directly with a licensed tour operator or the Korea Tourism Organization immediately before booking, rather than assuming JSA is open.", listOf("JSA")))

            SectionHeader("Independently Visitable: Imjingak Park")
            BodyText(boldNames("Imjingak Park (also called Imjingak Peace Nuri Park) is open to the public with no guided tour required, about 1.5-2 hours from Seoul by public transit or taxi. Inside it is the Bridge of Freedom, a Korean War-era bridge where repatriated prisoners of war and soldiers once crossed back into the South, and the Paju Imjingak Peace Gondola (DMZ Gondola) — billed as the world's only gondola crossing a Civilian Controlled Zone, running about 1.7km from Imjingak Station toward a station near Camp Greaves. Both the park and the gondola can be visited independently, though the gondola charges its own fee (discounted if bundled with a tour ticket).", listOf("Imjingak Park", "Imjingak Peace Nuri Park", "Bridge of Freedom", "Paju Imjingak Peace Gondola", "Camp Greaves")))
            BodyText(boldNames("Everything beyond Imjingak — the 3rd Tunnel, Dora Observatory, Dorasan Station, Odusan Unification Observatory, and of course JSA — sits inside the Civilian Control Zone and requires an official registered-guide tour; you cannot simply drive or walk in.", listOf("Odusan Unification Observatory")))

            SectionHeader("Safety and Rules")
            BodyText("Dress code is strict, especially for JSA: no sleeveless tops, collarless T-shirts, shorts, skirts, sandals, camouflage or military-style clothing, or clothing bearing another country's flag or name. Visitors who don't comply are held on the bus and denied entry — this isn't flexible. Photography is generally allowed at the observatories and park areas, but restricted underground at the 3rd Tunnel and tightly controlled inside JSA (only in guide-designated spots and moments) — never photograph checkpoints or point toward the North Korean side. A physical passport (not a photo of one) is mandatory for every DMZ tour and is checked at a military checkpoint. Stay with your group at all times, and don't attempt the tour while intoxicated — visitors who appear to have been drinking are turned away.")

            SectionHeader("Cell Phones")
            BodyText("Cell phones are generally allowed and usable for photos and video in the DMZ tour areas, subject to the same photography restrictions above — expect your guide to tell you exactly where and when you can and can't take pictures, especially inside JSA.")

            SectionHeader("Travel Insurance")
            BodyText("Whether standard travel insurance covers a South Korea-side DMZ or JSA tour is genuinely unclear and depends entirely on your specific policy's wording — most policies carry a war or armed-conflict exclusion and commonly list North Korea itself as excluded, but no general source confirms whether that extends to a routine guided day-tour on the southern side of the DMZ. Rather than assume either way, check your policy for \"war zone,\" \"restricted area,\" or \"conflict zone\" exclusions and confirm with your insurer before booking.")
        }
    }
}
