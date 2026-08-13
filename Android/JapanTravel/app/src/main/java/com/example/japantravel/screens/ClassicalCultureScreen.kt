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
fun ClassicalCultureScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "Classical Japanese Culture", onBack = onBack) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            SectionHeader("Bunraku")
            BodyText(
                "Traditional Japanese puppet theatre: large puppets are each operated in full " +
                    "view of the audience by three visible puppeteers, accompanied by chanted " +
                    "narration (tayu) and shamisen music."
            )
            BodyText(
                "Where to watch: its home is the **National Bunraku Theatre** in **Osaka**, with major " +
                    "runs in January, April, July-August, and November (tickets roughly " +
                    "¥1,500-6,500). A same-day single-act ticket (\"makumiseki\") lets you watch " +
                    "just one act if a full programme feels long, and an English audio guide is " +
                    "available. Occasional performances are also held at the **National Theatre** in " +
                    "**Tokyo**."
            )

            SectionHeader("Kabuki")
            BodyText(
                "A classical dance-drama known for stylized acting, elaborate costumes and " +
                    "makeup, and an all-male cast, including male actors trained to play female " +
                    "roles (onnagata)."
            )
            BodyText(
                "Where to watch: the main venue is **Kabuki-za** in **Ginza**, **Tokyo** (also **Minamiza** in " +
                    "**Kyoto** and **Shochikuza** in **Osaka**). Tourists commonly use single-act tickets - no " +
                    "reservation needed, just walk up - which cost about ¥1,000 for a daytime act " +
                    "or ¥2,000 for an evening act, cash only. These are sold at a separate box " +
                    "office that opens 90 minutes before the act starts. English/Chinese subtitle " +
                    "guides can be rented to follow along."
            )

            SectionHeader("Tea Ceremony (Chado / Sado)")
            BodyText(
                "A ritualized way of preparing and serving matcha (see Food and Cafes for what " +
                    "matcha is), rooted in Zen Buddhism and the idea of ichigo ichie - \"one time, " +
                    "one meeting\" - treating each gathering as unrepeatable. Every movement, from " +
                    "whisking the tea to handling the bowl, is precise and unhurried, and a " +
                    "seasonal wagashi sweet is usually eaten just before drinking to balance the " +
                    "tea's bitterness."
            )
            BodyText(
                "Length and ages: casual, tourist-oriented sessions typically run 45 minutes to " +
                    "1 hour; more traditional sessions, especially in **Kyoto**, can run 1-2.5 hours. " +
                    "It's suitable for all ages including kids, though sitting seiza (kneeling) " +
                    "through a full traditional session can be tiring for young children - most " +
                    "tourist-oriented venues let you sit normally instead."
            )
            BodyText(
                "Where to try it: **Kyoto**, especially around **Gion**, has the deepest and most " +
                    "traditional options. **Tokyo** has several English-guided studios (some bundled " +
                    "with kimono rental) plus a low-cost 20-minute, ¥500 option at the **Kyoto City " +
                    "Information Center** inside Tokyo Station, offered Friday-Sunday afternoons. " +
                    "**Osaka** has shorter, casual English-guided sessions aimed at visitors. Prices " +
                    "generally range from around ¥500 for a very short intro up to ¥6,000-7,000 " +
                    "for a full kimono-plus-tea-ceremony experience."
            )

            SectionHeader("Sumo")
            BodyText(
                "Japan's national sport: two wrestlers (rikishi) try to force each other out of a " +
                    "ring or make any part of the opponent besides the soles of their feet touch " +
                    "the ground, rooted in centuries of Shinto ritual."
            )
            BodyText(
                "Where to watch: six Grand Tournaments (honbasho) are held each year, 15 days " +
                    "each - **Tokyo** (January, May, September, at **Ryogoku Kokugikan**), **Osaka** (March), " +
                    "**Nagoya** (July), and **Fukuoka** (November). Tickets range from about ¥2,200 for " +
                    "same-day general admission up to ¥20,000+ for ringside seats. Outside " +
                    "tournament dates, some sumo stables (heya) let visitors watch free morning " +
                    "practice - for example, **Arashio Stable** in **Ryogoku** allows walk-up viewing " +
                    "through a street-facing window on weekday mornings (roughly 6-9am), no " +
                    "reservation needed; other stables allow visits but require contacting them in " +
                    "advance."
            )
            Spacer(Modifier.height(16.dp))
        }
    }
}
