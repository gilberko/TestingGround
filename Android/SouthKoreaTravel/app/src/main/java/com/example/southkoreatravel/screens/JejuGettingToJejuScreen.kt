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
fun JejuGettingToJejuScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "Getting To Jeju", onBack = onBack) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(24.dp)
        ) {
            SectionHeader("By Air")
            BodyText(boldNames("Flying is by far the normal way to reach Jeju — there's no bridge or tunnel to the island. Gimpo (Seoul) to Jeju is remarkably well served: it was the single busiest airline route on Earth in 2025, with around 39,000 seats a day across seven airlines (Korean Air, Asiana, Jeju Air, Jin Air, Eastar Jet and others), so flights run roughly every few minutes at peak times.", listOf("Gimpo", "Jeju", "Korean Air", "Asiana", "Jeju Air", "Jin Air", "Eastar Jet")))
            BodyText(boldNames("The flight itself takes about 1 hour 15 minutes from Gimpo, and one-way fares typically range from roughly ₩40,000-70,000 on budget carriers up to ₩150,000-300,000 on Korean Air/Asiana depending on how far ahead you book. Busan (Gimhae) to Jeju is even shorter, about 1 hour, with similarly cheap fares. Most other Korean cities (Daegu, Gwangju, Cheongju) also have direct flights.", listOf("Gimhae", "Korean Air", "Asiana")))

            SectionHeader("By Ferry")
            BodyText(boldNames("Ferries exist but are a niche option, mostly used by travelers bringing a car or motorbike over rather than typical tourists. The main mainland departure points are Mokpo (about 4.5 hours, several sailings a week) and Wando (the fastest ferry crossing, around 2 hours 40 minutes), both on Korea's southwest coast, plus a smaller daily ferry from Nokdong near Goheung. Fares run roughly ₩30,000-70,000 depending on the operator and seat class.", listOf("Mokpo", "Wando", "Nokdong", "Goheung")))
            BodyText("Ferry routes and schedules change more often than flight routes, so check a current Korean ferry-booking site close to your travel dates rather than assuming a specific route is still running — this is especially true for any route out of Incheon or Busan, which have been unreliable/inactive at various points.")

            SectionHeader("Passport, ID and Paperwork")
            BodyText(boldNames("Jeju is part of South Korea, so flying or sailing there is domestic travel — there's no passport control, customs, or immigration form of any kind, and no need to show your passport specifically. You will need to show photo ID (your passport works fine as a foreigner's ID) at the airport security checkpoint and again at the boarding gate, exactly as with any domestic flight.", listOf("Jeju")))
        }
    }
}
