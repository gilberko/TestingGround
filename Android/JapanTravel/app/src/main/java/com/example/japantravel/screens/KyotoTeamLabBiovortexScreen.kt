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
fun KyotoTeamLabBiovortexScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "teamLab Biovortex Kyoto", onBack = onBack) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            SectionHeader("What it is / concept")
            BodyText(
                "teamLab's newest and largest museum, opened October 2025 - an indoor immersive " +
                    "digital art space spanning over 10,000 square meters across four floors, with " +
                    "more than 50 interactive artworks. It's bigger than **teamLab Planets** or " +
                    "**Borderless** in **Tokyo**, built around blending light, sound, and visitor " +
                    "interaction into a constantly shifting environment."
            )

            SectionHeader("Where / how to get there")
            BodyText(
                "About a 10-minute walk southwest of Kyoto Station."
            )

            SectionHeader("Is it for the whole family?")
            BodyText(
                "Interactive and immersive like other teamLab venues, and generally family-" +
                    "friendly, though some rooms involve water, mirrors, or darkness - it's worth " +
                    "checking the official site's age and stroller guidance before visiting with " +
                    "very young children."
            )

            SectionHeader("Prices")
            BodyText(
                "Tickets run roughly ¥3,800-5,600 depending on the date and timeslot, with an " +
                    "open-time flexible pass available for up to around ¥12,000."
            )

            SectionHeader("Where to book")
            BodyText(
                "Book online for a specific entry timeslot via the official teamLab website " +
                    "(teamlab.art) or resellers such as Klook or GetYourGuide."
            )
            Spacer(Modifier.height(16.dp))
        }
    }
}
