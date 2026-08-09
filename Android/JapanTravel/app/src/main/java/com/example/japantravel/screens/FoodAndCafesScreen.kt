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
fun FoodAndCafesScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "Food and Cafes", onBack = onBack) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            SectionHeader("Japanese food chains (nationwide)")
            BodyText(
                "Gyudon (beef bowl): Yoshinoya, Sukiya, and Matsuya are the \"Big 3\" - fast, " +
                    "cheap rice bowls topped with simmered beef, found at nearly every train " +
                    "station."
            )
            BodyText(
                "Ramen: Ichiran is known for tonkotsu (pork-bone broth) ramen served in " +
                    "individual booths for solo, no-distraction dining; Ippudo is another major " +
                    "tonkotsu ramen chain."
            )
            BodyText(
                "Curry: CoCo Ichibanya lets you customize spice level, toppings, and rice amount " +
                    "on a Japanese-style curry rice."
            )
            BodyText(
                "Conveyor-belt sushi: Sushiro and Kura Sushi are the biggest chains - plates " +
                    "circulate on a belt (or are sent directly to your table via a small track), " +
                    "usually priced per plate."
            )
            BodyText(
                "Family restaurants: Saizeriya (budget Italian-Japanese fusion) and Ootoya " +
                    "(home-style Japanese set meals) are common all-purpose options with picture " +
                    "menus."
            )
            BodyText(
                "Tempura: Tenya serves fast, affordable tempura rice bowls."
            )

            SectionHeader("Cafe chains")
            BodyText(
                "Yes - % Arabica is real and Japan-founded, originating in Kyoto. Its flagship " +
                    "store is in Higashiyama, with other Kyoto locations in Arashiyama " +
                    "(overlooking the river near Togetsukyo Bridge) and Kawaramachi. It has since " +
                    "expanded to Tokyo, Osaka, and internationally. Other well-known Japan-founded " +
                    "coffee chains include Doutor, Komeda's Coffee, and Tully's."
            )

            SectionHeader("Recommended independent roasteries and cafes (not chains)")
            BodyText(
                "Tokyo: Fuglen Tokyo (Nordic-style light roasts and filter coffee) and Koffee " +
                    "Mameya Kakeru (a tasting-course format, ranked among the World's 100 Best " +
                    "Coffee Shops)."
            )
            BodyText(
                "Osaka: Ult Coffee (also ranked among the World's 100 Best, founded by 2023 World " +
                    "Barista Champion Boram Um) and Spot Coffee, a small-batch roastery in the " +
                    "Nakazakinishi neighborhood."
            )
            BodyText(
                "Kyoto: Weekenders Coffee (Tomikoji) is a long-standing favorite among specialty " +
                    "coffee fans; the % Arabica Arashiyama location is also worth a stop for the " +
                    "riverside view alone, even though it's a chain."
            )

            SectionHeader("Celiac-friendly dining")
            BodyText(
                "T's Kitchen (also marketed as \"Gluten Free T's Kitchen\") is a 100% gluten-free " +
                    "restaurant with two Tokyo locations (Roppongi and Ueno), certified by the " +
                    "Gluten Intolerance Group - the first such certification in Asia. The menu " +
                    "covers Japanese comfort food (gyoza, okonomiyaki, ramen, tempura) as well as " +
                    "Western dishes, all prepared gluten-free; reservations are recommended due to " +
                    "limited seating. See the Specific Celiac Information section for a printable " +
                    "Japanese explanation card to use elsewhere."
            )
            Spacer(Modifier.height(16.dp))
        }
    }
}
