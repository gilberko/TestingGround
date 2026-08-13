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
fun GhibliParkScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "Ghibli Park", onBack = onBack) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            SectionHeader("What it is")
            BodyText(
                "**Ghibli Park**, opened in November 2022, is a Studio Ghibli theme park built inside " +
                    "an existing public park rather than a standalone ride-based park. It's " +
                    "designed as a walk-through experience across themed areas - **Ghibli's Grand " +
                    "Warehouse** (indoor exhibits, a cinema, and a recreation of a Ghibli film set), " +
                    "**Hill of Youth**, **Dondoko Forest**, **Witch's Valley**, and **Mononoke Village** - so " +
                    "expect strolling and exploring rather than queuing for rides. Note this is a " +
                    "completely different place from the **Ghibli Museum** in **Mitaka**, **Tokyo**."
            )

            SectionHeader("Which movies you'll find where")
            BodyText(
                "**Ghibli's Grand Warehouse** is the most film-dense area: the climbable Catbus " +
                    "(**My Neighbor Totoro**), a life-size train car recreation with No-Face riding " +
                    "along (**Spirited Away**), a recreation of Yubaba's office (**Spirited Away**), " +
                    "the House Below and Little People's Garden built at tiny scale (**The Secret " +
                    "World of Arrietty**), and a large model of the Savoia S-21 seaplane (**Porco " +
                    "Rosso**)."
            )
            BodyText(
                "**Hill of Youth** has the World Emporium antique shop and violin workroom " +
                    "(**Whisper of the Heart**), and the cat-sized Cat Bureau where Baron and Muta " +
                    "can be seen relaxing (**The Cat Returns**)."
            )
            BodyText(
                "**Dondoko Forest** has Satsuki and Mei's House and the Totoro-shaped Dondoko-do " +
                    "climbing structure (**My Neighbor Totoro**)."
            )
            BodyText(
                "**Mononoke Village** recreates Irontown and Emishi Village, with figures of the " +
                    "Demon Spirit and Lord Okkoto (**Princess Mononoke**)."
            )
            BodyText(
                "**Witch's Valley** covers three witch-themed films: the Okino family home and " +
                    "Gutiokipanja Bakery (**Kiki's Delivery Service**), Howl's Castle and the " +
                    "Hatter's Hat Shop (**Howl's Moving Castle**), and the House of Witches " +
                    "(**Earwig and the Witch**)."
            )
            BodyText(
                "Not currently featured: **Nausicaa of the Valley of the Wind** doesn't have a " +
                    "confirmed dedicated area or exhibit in the park as of this writing - worth " +
                    "checking closer to your visit in case that changes."
            )

            SectionHeader("Where it is")
            BodyText(
                "Inside **Aichi Expo Memorial Park** in **Nagakute City**, **Aichi Prefecture**, about 40 " +
                    "minutes east of central **Nagoya**."
            )

            SectionHeader("How to get there from Tokyo")
            BodyText(
                "Take the Shinkansen from Tokyo Station to **Nagoya** (about 1h40m), then the **Nagoya** " +
                    "subway Higashiyama Line to Fujigaoka Station (about 30 min), then the Linimo " +
                    "line to Expo Memorial Park Station (about 15 min) - roughly 2.5 hours door to " +
                    "door."
            )

            SectionHeader("How to get there from Kyoto")
            BodyText(
                "Take the Tokaido Shinkansen to **Nagoya** (about 35-45 min, since the line passes " +
                    "directly through **Nagoya** on the way to **Tokyo**), then the same local **Nagoya** " +
                    "subway + Linimo connection above. All told it's about 1.5 hours from Kyoto " +
                    "Station, making **Ghibli Park** an easy detour on a **Kyoto**/**Osaka** to **Tokyo** trip."
            )

            SectionHeader("Costs")
            BodyText(
                "There are several different area passes rather than one all-access ticket - only " +
                    "the Premium Pass covers everything. Prices generally range from around " +
                    "¥1,000 to ¥7,000 depending on which areas a pass includes. Shinkansen+ticket " +
                    "bundle packages are also sold for visitors traveling in from **Tokyo**, **Kyoto**, or " +
                    "**Osaka**."
            )

            SectionHeader("When to book tickets")
            BodyText(
                "Every visitor needs an advance, dated ticket - there's no walk-in entry. The " +
                    "booking window for each date opens on a set schedule a few months ahead, and " +
                    "popular dates (weekends, school holidays) can sell out within hours of " +
                    "release, so book as soon as your travel dates open on the official Ghibli " +
                    "Park site. Overseas visitors often find it easier to book through an agent " +
                    "like Klook or KKday, which accept international cards directly."
            )
            Spacer(Modifier.height(16.dp))
        }
    }
}
