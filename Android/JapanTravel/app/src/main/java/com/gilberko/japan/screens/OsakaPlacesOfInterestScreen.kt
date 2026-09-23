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
fun OsakaPlacesOfInterestScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "More Places of Interest", onBack = onBack) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            SectionHeader("Den Den Town")
            BodyText(
                "**Den Den Town** (Nipponbashi) is Osaka's electronics-and-otaku district, similar " +
                    "in spirit to **Akihabara** in **Tokyo**. Its streets are lined with shops " +
                    "selling new and secondhand PC parts and electronics, retro and current video " +
                    "games and consoles, anime and manga merchandise, trading cards, and figures - " +
                    "including several **Mandarake** and **Super Potato**-style specialty stores " +
                    "worth browsing floor by floor."
            )
            BodyText(
                "There's no single fixed closing day for the district, and hours vary shop by " +
                    "shop, but most stores open around 10-11am and close by around 8pm - it's " +
                    "worth checking an individual shop's hours if you have something specific in " +
                    "mind rather than assuming the whole street runs on the same schedule."
            )

            SectionHeader("Nintendo Osaka")
            BodyText(
                "**Nintendo OSAKA**, on the 13th floor of the **LUCUA SOUTH** building in **Umeda** " +
                    "(not at Den Den Town), is Nintendo's official store for the Kansai region, a " +
                    "short walk from Umeda Station. It carries around 2,000 Nintendo-themed goods " +
                    "spanning Mario, The Legend of Zelda, Kirby, Splatoon, Pikmin, and Animal " +
                    "Crossing - plushies, apparel, homeware, and Amiibo - though, like its Tokyo " +
                    "counterpart, it sells merchandise only, not consoles or games."
            )
            BodyText(
                "The store is open daily from about 10am to 8pm."
            )
            Spacer(Modifier.height(16.dp))
        }
    }
}
