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
fun SeoulDayTripKoreanFolkVillageScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "Korean Folk Village", onBack = onBack) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(24.dp)
        ) {
            SectionHeader("What's There")
            BodyText(boldNames("The Korean Folk Village, in Yongin, is a living-history village recreating everyday life from the Joseon era — traditional houses from different regions and social classes, craft demonstrations, and scheduled performances, with extra seasonal festivals and events running at different times of year.", listOf("Korean Folk Village", "Yongin")))

            SectionHeader("Getting There")
            BodyText(boldNames("A direct bus is the easiest option: bus 4101 from Myeongdong (about 45 minutes) or bus 5001-1 from Gangnam/Sinnonhyeon (about 50 minutes). Alternatively, take Subway Line 1 to Suwon Station, then a free shuttle or local bus (37 or 10-5) to the village, about 55 minutes total.", listOf("Myeongdong", "Gangnam", "Suwon Station")))

            SectionHeader("How Long It Takes")
            BodyText("Plan on a full day — there's enough to see (houses, craft demos, scheduled shows) that a half-day feels rushed. Figure roughly 6-8 hours round trip from central Seoul including on-site time.")

            SectionHeader("Does Weather Matter?")
            BodyText("Yes, more than most day trips — many of the performances and craft demonstrations happen outdoors on a schedule, and can be shortened or cancelled in heavy rain. Good weather makes a real difference here.")

            SectionHeader("Independent or Organized Tour?")
            BodyText("Easily independent — the direct buses from Myeongdong and Gangnam make this a straightforward self-guided trip with no tour required.")
        }
    }
}
