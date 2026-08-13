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
fun GhibliMuseumScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "Ghibli Museum", onBack = onBack) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            SectionHeader("What it is")
            BodyText(
                "A whimsical museum dedicated to Studio Ghibli's art and filmmaking process, " +
                    "conceived by director Hayao Miyazaki himself - it's less a traditional " +
                    "museum and more a walk-through world, with winding stairs, stained glass, " +
                    "and hidden nooks. Highlights include exhibits on how animation is made, a " +
                    "reproduction of a Ghibli animator's studio, a small cinema showing " +
                    "short films exclusive to the museum (not available anywhere else), and a " +
                    "rooftop garden with a life-size robot soldier statue from Laputa: Castle in " +
                    "the Sky."
            )

            SectionHeader("Location")
            BodyText(
                "**Mitaka**, in western **Tokyo**, on the edge of **Inokashira Park**."
            )

            SectionHeader("Ages")
            BodyText(
                "All ages - it's a favorite for families, though the \"Neko Bus\" (Cat Bus) play " +
                    "structure on the second floor, a big draw for younger visitors, is reserved " +
                    "for children only (adults can watch but not climb in)."
            )

            SectionHeader("How to get there")
            BodyText(
                "Take the JR Chuo Line to Mitaka Station, then either a short ride on the " +
                    "community \"Ghibli bus\" that runs directly to the museum, or a pleasant " +
                    "15-20 minute walk through **Inokashira Park**."
            )

            SectionHeader("How to buy tickets")
            BodyText(
                "Tickets are sold exclusively through Lawson Ticket, not at the museum door - " +
                    "there is no walk-in admission. Tickets for a given month go on sale at 10:00 " +
                    "JST on the 10th of the previous month, and popular weekend/holiday slots can " +
                    "sell out within minutes. Adult admission is about ¥1,000. After booking, " +
                    "print the voucher and bring it along with your passport - it's exchanged for " +
                    "a physical film-strip ticket at the entrance."
            )
            Spacer(Modifier.height(16.dp))
        }
    }
}
