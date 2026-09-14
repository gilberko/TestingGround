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
fun SeoulDayTripNamiIslandScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "Nami Island / Gapyeong", onBack = onBack) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(24.dp)
        ) {
            SectionHeader("What's There")
            BodyText(boldNames("Nami Island is known for its tree-lined paths (made famous by the drama Winter Sonata) and is especially popular in autumn foliage and winter snow. The surrounding Gapyeong area has other popular stops often combined with it, like Petite France and the Garden of Morning Calm.", listOf("Nami Island", "Winter Sonata", "Gapyeong", "Petite France", "Garden of Morning Calm")))

            SectionHeader("Getting There")
            BodyText(boldNames("Take the ITX-Cheongchun train from Yongsan or Cheongnyangni Station to Gapyeong Station, about 40-60 minutes (roughly ₩6,000-8,500). From Gapyeong Station, a city tour bus or taxi takes you to Gapyeong Wharf, where a short ferry crosses to the island.", listOf("Yongsan", "Cheongnyangni", "Gapyeong Station", "Gapyeong Wharf")))

            SectionHeader("How Long It Takes")
            BodyText("This is a full-day trip — figure roughly 8-10 hours door-to-door once you add the train, transfer, ferry, a few hours on the island, and the return trip.")

            SectionHeader("Does Weather Matter?")
            BodyText("Yes, quite a bit — it's an outdoor scenic walk on the island plus a ferry crossing, so heavy rain or storms make it much less pleasant (and can affect ferry service). It's most popular, and arguably best, in autumn foliage or winter snow.")

            SectionHeader("Independent or Organized Tour?")
            BodyText("Doable independently via train and ferry, though many visitors book a combined shuttle/tour package instead if they also want to fit in Petite France or the Garden of Morning Calm on the same day, since those aren't within walking distance of Nami Island itself.")
        }
    }
}
