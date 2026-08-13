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
fun TokyoCityRegionsScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "City Regions", onBack = onBack) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            SectionHeader("Shibuya")
            BodyText(
                "Nearest station: Shibuya Station (JR Yamanote Line; Tokyo Metro Ginza, " +
                    "Hanzomon, and Fukutoshin lines; Tokyu and Keio Inokashira lines)."
            )
            BodyText(
                "Famous for **Shibuya Scramble Crossing**, one of the world's busiest pedestrian " +
                    "crossings, and the **Hachiko statue** nearby. This is the crossing you've " +
                    "probably seen on screen - it's the one famously filmed in Lost in " +
                    "Translation, among many other movies and shows. For a bird's-eye view of the " +
                    "crossing and the wider skyline, head up to **Shibuya Sky**, an open-air rooftop " +
                    "observation deck. Big shopping (**Shibuya 109**, **Center Gai**) and a youth-culture, " +
                    "nightlife hub."
            )

            SectionHeader("Shinjuku")
            BodyText(
                "Nearest station: Shinjuku Station (JR lines; Odakyu; Keio; Tokyo Metro Marunouchi " +
                    "Line; Toei Shinjuku and Oedo lines) - one of the busiest train stations in " +
                    "the world."
            )
            BodyText(
                "A major transit hub with excellent train/subway links, skyscraper observation " +
                    "decks (the **Tokyo Metropolitan Government Building**'s observatory is free), " +
                    "endless dining, and **Kabukicho** nightlife. **Golden Gai** and **Omoide Yokocho** are " +
                    "tiny, atmospheric alleys packed with miniature bars and food stalls. Often " +
                    "recommended as the best base for first-time visitors. Just north of Shinjuku " +
                    "Station on the Yamanote Line is Shin-Okubo Station, for **Shin-Okubo**, **Tokyo**'s " +
                    "Koreatown - a dense strip of K-pop merchandise shops, Korean cosmetics stores, " +
                    "and Korean BBQ and street food, technically its own neighborhood but an easy " +
                    "walk from **Shinjuku** (see Places of Interest for more on **Shin-Okubo**'s K-pop " +
                    "scene)."
            )

            SectionHeader("Asakusa")
            BodyText(
                "Nearest station: Asakusa Station (Tokyo Metro Ginza Line; Toei Asakusa Line; " +
                    "Tobu Skytree Line)."
            )
            BodyText(
                "**Tokyo**'s old town feel: **Sensoji**, the city's oldest temple, reached via the " +
                    "**Nakamise shopping street**. Nearby is **Hoppy Street**, a retro alley of izakayas, " +
                    "and **Kappabashi**, a street specializing in kitchenware and cookware. Across the " +
                    "**Sumida River** you get one of the best views of **Tokyo Skytree**, the tall " +
                    "broadcasting tower with its own observation decks."
            )

            SectionHeader("Ginza")
            BodyText(
                "Nearest station: Ginza Station (Tokyo Metro Ginza, Marunouchi, and Hibiya lines)."
            )
            BodyText(
                "Upscale shopping and fine dining - flagship department stores, luxury brand " +
                    "boutiques, art galleries, and the landmark **Ginza Wako** clock tower."
            )

            SectionHeader("Akihabara")
            BodyText(
                "Nearest station: Akihabara Station (JR Yamanote and Chuo-Sobu lines; Tokyo Metro " +
                    "Hibiya Line; Tsukuba Express)."
            )
            BodyText(
                "This is the region for all things anime, manga, and otaku culture in **Tokyo** - " +
                    "multi-floor stores like **Mandarake** stacked with manga, anime figures, and " +
                    "trading cards, retro and modern video game shops, arcades, and maid cafes, " +
                    "alongside its original identity as an electronics district. If **Akihabara** " +
                    "feels too touristy, **Nakano Broadway** - a shopping arcade right by Nakano Station " +
                    "(JR Chuo Line; Tokyo Metro Tozai Line), a few stops west of **Shinjuku** - is a " +
                    "second, more " +
                    "local-feeling otaku shopping hub worth knowing about. **Akihabara** is also one " +
                    "of the two main concentration points for **Tokyo**'s animal cafes: **Akiba " +
                    "Fukurou**, one of the largest owl cafes in Japan, is here (see the **Tokyo** - " +
                    "Special Cafes screen for details and other animal cafes)."
            )
            BodyText(
                "Video game arcades: **GiGO** (formerly **Sega**'s arcades) and **Taito Station** are the two " +
                    "big chains, with multi-floor locations across **Akihabara**, **Ikebukuro**, and " +
                    "**Shinjuku**. Most games are around 100 yen a play - claw/UFO catcher machines, " +
                    "photo booths (purikura), rhythm games like Taiko no Tatsujin, and racing/" +
                    "shooting cabinets. These are generally fine for kids during the day; some " +
                    "locations even split floors by audience (**Taito Station Shibuya**, for example, " +
                    "puts kid-oriented games on one floor and more mature games on another), so " +
                    "it's worth a quick look at the floor guide near the entrance rather than " +
                    "assuming every floor suits children."
            )

            SectionHeader("Harajuku")
            BodyText(
                "Nearest station: Harajuku Station (JR Yamanote Line) or Meiji-jingumae Station " +
                    "(Tokyo Metro Chiyoda and Fukutoshin lines) - both sit right at the entrance " +
                    "to **Takeshita Street** and **Meiji Jingu**."
            )
            BodyText(
                "Youth fashion and street style along **Takeshita Street**, right next to the calm, " +
                    "forested grounds of **Meiji Jingu** (**Meiji Shrine**). **Meiji Jingu** is a major Shinto " +
                    "shrine dedicated to Emperor Meiji and Empress Shoken, set inside a dense, " +
                    "deliberately planted forest of around 100,000 trees - entered right by " +
                    "Harajuku Station and bordering **Yoyogi Park**, it's a striking, quiet contrast " +
                    "to the fashion crowds just outside its gates. This is also the other main " +
                    "animal-cafe concentration point in **Tokyo**, with several cat cafes, puppy " +
                    "cafes, and multi-animal spots like **Harry's Zoo Cafe** (again, see the **Tokyo** - " +
                    "Special Cafes screen for the full list)."
            )

            SectionHeader("Odaiba")
            BodyText(
                "Nearest station: Daiba or Odaiba-Kaihinkoen Station (Yurikamome Line) or Tokyo " +
                    "Teleport Station (Rinkai Line)."
            )
            BodyText(
                "A waterfront, man-made island with digital-art attractions (**teamLab Planets**), " +
                    "shopping malls, **Joypolis**, and views of the **Rainbow Bridge** and **Tokyo** skyline."
            )

            SectionHeader("Ueno")
            BodyText(
                "Nearest station: Ueno Station (JR Yamanote Line; Tokyo Metro Ginza and Hibiya " +
                    "lines; Keisei)."
            )
            BodyText(
                "Home to **Ueno Park**, several major museums (**Tokyo National Museum** among them), " +
                    "**Ueno Zoo**, and **Ameyoko**, a lively market street."
            )

            SectionHeader("Recommended shopping")
            BodyText(
                "**Ginza**: upscale, polished luxury - flagship department stores and designer " +
                    "boutiques (see above). **Shibuya**: trendy streetwear and youth fashion, malls " +
                    "like **PARCO**, and **Shibuya 109**. **Shinjuku**: a one-stop shopping hub with " +
                    "department stores covering every budget plus discount electronics. **Harajuku** " +
                    "(**Takeshita Street**): kawaii, vintage, and playful DIY fashion. **Akihabara**: " +
                    "electronics and anime/manga goods. A good one-day route is to start the " +
                    "morning on **Takeshita Street** in **Harajuku**, spend the afternoon in nearby " +
                    "**Shibuya**, and finish the evening in **Shinjuku** - all connected by a short walk " +
                    "or train ride."
            )

            SectionHeader("Fish markets (Tsukiji & Toyosu)")
            BodyText(
                "**Toyosu Market** opened in 2018 and took over the wholesale tuna auction from the " +
                    "old **Tsukiji Market**. Yes, an early start is genuinely needed to see the " +
                    "auction itself - it runs roughly 5:30-6:30am and is viewed from a public " +
                    "observation deck overlooking the auction floor. **Toyosu** is reachable via the " +
                    "Yurikamome Line to Shijomae Station."
            )
            BodyText(
                "The old **Tsukiji** site (nearest station: Tsukiji Station on the Toei Oedo Line) " +
                    "is still very much alive as the **Tsukiji Outer Market** - rows " +
                    "of retail food stalls, knife shops, and sushi breakfast counters that keep " +
                    "normal daytime hours (roughly 9am-2pm), so there's no need for a pre-dawn " +
                    "visit for this part. A good plan is **Toyosu** first thing in the morning for the " +
                    "auction viewing, then the **Tsukiji Outer Market** afterward for a relaxed sushi " +
                    "breakfast and browsing."
            )

            SectionHeader("Just outside these core regions")
            BodyText(
                "A few well-known spots sit outside the 7 central regions above but are covered " +
                    "elsewhere in this app: **Tokyo Dome City** is actually in **Bunkyo** City near " +
                    "Suidobashi Station (JR Chuo-Sobu Line; Toei Mita Line) or Korakuen Station " +
                    "(Tokyo Metro Marunouchi and Namboku lines), a bit north of **Ueno**/**Akihabara** " +
                    "(see **Tokyo** - Parks and Attractions). **Ghibli Museum** is further out near " +
                    "Mitaka Station (JR Chuo Line) plus a short community bus ride, in **Mitaka**, " +
                    "western **Tokyo** (also under **Tokyo** - Parks and Attractions). And if you were " +
                    "looking for **Nintendo " +
                    "Museum** here - it's not actually in **Tokyo** at all, it's in **Uji**, **Kyoto** " +
                    "Prefecture (see **Kyoto** - Parks and Attractions)."
            )
            Spacer(Modifier.height(16.dp))
        }
    }
}
