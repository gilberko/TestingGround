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
fun TokyoDisneyScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "Disneyland & DisneySea", onBack = onBack) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            SectionHeader("What it is")
            BodyText(
                "Tokyo Disney Resort (in Urayasu, Chiba, just outside central Tokyo) has two " +
                    "separate parks on one ticket-adjacent site: Tokyo Disneyland, the classic " +
                    "fairy-tale Disney park, and Tokyo DisneySea, a nautical/adventure-themed park " +
                    "found nowhere else in the world. DisneySea's newest area, Fantasy Springs " +
                    "(opened 2024), adds Frozen, Tangled, and Peter Pan lands."
            )

            SectionHeader("Main rides")
            BodyText(
                "Disneyland: Pooh's Hunny Hunt, Big Thunder Mountain, Space Mountain, Haunted " +
                    "Mansion, Pirates of the Caribbean, Beauty and the Beast: A Magical Journey."
            )
            BodyText(
                "DisneySea: Soaring: Fantastic Flight, Toy Story Mania!, Journey to the Center of " +
                    "the Earth, Indiana Jones Adventure, Tower of Terror, 20,000 Leagues Under the " +
                    "Sea, and the Fantasy Springs rides (Frozen, Rapunzel's/Tangled, Peter Pan)."
            )

            SectionHeader("The app, costs, and ride reservations")
            BodyText(
                "The Tokyo Disney Resort App is used to buy park tickets, check hours, and book " +
                    "restaurants. It also runs two queue systems: Disney Premier Access (DPA) lets " +
                    "you pay to reserve a specific entry time for a specific popular ride (roughly " +
                    "¥1,500-2,500 per person per attraction, priced daily in the app), and Standby " +
                    "Pass is a free virtual queue required for some attractions, including the " +
                    "Fantasy Springs rides. So yes — the app can reserve a ride time slot, either " +
                    "paid (DPA) or free (Standby Pass) depending on the ride."
            )
            BodyText(
                "A one-day passport costs roughly ¥7,900-10,900 depending on the date (Tokyo " +
                    "Disney uses variable date-based pricing), on top of any optional DPA purchases."
            )
            Spacer(Modifier.height(16.dp))
        }
    }
}
