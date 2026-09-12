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
fun SeoulAirportTravelScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "Airport Travel", onBack = onBack) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(24.dp)
        ) {
            SectionHeader("Incheon International Airport (ICN)")
            BodyText(boldNames("Incheon International Airport (ICN) is Seoul's main international gateway, roughly 40-60km from central Seoul depending on your destination — about 45 minutes to just over an hour by most transport options. AREX Express nonstop trains run directly to Seoul Station in about 43 minutes from Terminal 1 (51 minutes from Terminal 2) for a flat ₩13,000 fare (around ₩11,500 if booked online in advance), with reserved seating and dedicated luggage space. The cheaper AREX All-Stop train stops at every station along the line, costs about ₩4,750, and can be paid with a T-money card, but takes longer. Airport Limousine Buses run directly to major hotel districts across the city (Myeongdong, Gangnam, Dongdaemun, and more) for about ₩18,000 per adult, with luggage stowed in an under-bus cargo hold. A regular taxi runs roughly ₩70,000-100,000 to central Seoul, while an International Taxi — with a language-certified English/Japanese/Chinese-speaking driver, booked at a dedicated arrivals-hall counter — costs somewhat more (roughly ₩70,000-85,000 plus tolls) but removes any language barrier.", listOf("Incheon International Airport", "AREX Express", "AREX All-Stop", "Airport Limousine Bus", "International Taxi")))

            SectionHeader("Gimpo International Airport (GMP)")
            BodyText(boldNames("Gimpo International Airport (GMP) is much closer to central Seoul than Incheon and mainly handles domestic and short-haul regional flights. The AREX All-Stop train reaches Seoul Station in about 23 minutes for roughly ₩1,550 — note that the faster AREX Express service does not stop at Gimpo. Gimpo is also directly served by Subway Line 5, Line 9, the Gimpo Goldline, and the Seohae Line, giving quick connections across much of the city. Airport Limousine Buses run roughly every 20-25 minutes during the day (30-40 minutes at night) to major hotel areas, and a taxi typically takes about 40 minutes to the city center in normal traffic, carrying up to 4 passengers with one average suitcase each.", listOf("Gimpo International Airport", "AREX All-Stop", "AREX Express")))

            SectionHeader("Family With Luggage")
            BodyText(boldNames("For a family traveling with strollers and multiple suitcases from Incheon, the Airport Limousine Bus is generally the easiest choice — luggage goes straight into a dedicated under-bus hold, there are no stairs or underground transfers, and the bus drops you close to major hotel areas. AREX, even the express service, still means reaching a platform deep underground at Seoul Station (around level B7) and possibly a further transfer through stairs, ramps, and crowded corridors to reach another subway line — manageable for light packers, but more of a hassle with a family's worth of luggage. A regular or International Taxi is the most door-to-door, stroller-friendly option and becomes cost-competitive once split across three or more family members, though it's the priciest option for a solo or two-person trip. At Gimpo, the shorter distance and extra subway options make transfers less of a burden, but the same logic still applies — the limousine bus or a taxi remains the more comfortable choice over the subway when traveling with a lot of luggage.", listOf("Airport Limousine Bus", "AREX")))
        }
    }
}
