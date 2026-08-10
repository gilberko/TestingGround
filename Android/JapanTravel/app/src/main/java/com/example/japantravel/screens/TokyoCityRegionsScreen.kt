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
                "Famous for Shibuya Scramble Crossing, one of the world's busiest pedestrian " +
                    "crossings, and the Hachiko statue nearby. This is the crossing you've " +
                    "probably seen on screen - it's the one famously filmed in Lost in " +
                    "Translation, among many other movies and shows. For a bird's-eye view of the " +
                    "crossing and the wider skyline, head up to Shibuya Sky, an open-air rooftop " +
                    "observation deck. Big shopping (Shibuya 109, Center Gai) and a youth-culture, " +
                    "nightlife hub."
            )

            SectionHeader("Shinjuku")
            BodyText(
                "A major transit hub with excellent train/subway links, skyscraper observation " +
                    "decks (the Tokyo Metropolitan Government Building's observatory is free), " +
                    "endless dining, and Kabukicho nightlife. Golden Gai and Omoide Yokocho are " +
                    "tiny, atmospheric alleys packed with miniature bars and food stalls. Often " +
                    "recommended as the best base for first-time visitors. Just north of Shinjuku " +
                    "Station on the Yamanote Line is Shin-Okubo, Tokyo's Koreatown - a dense strip " +
                    "of K-pop merchandise shops, Korean cosmetics stores, and Korean BBQ and " +
                    "street food, technically its own neighborhood but an easy walk from Shinjuku."
            )

            SectionHeader("Asakusa")
            BodyText(
                "Tokyo's old town feel: Sensoji, the city's oldest temple, reached via the " +
                    "Nakamise shopping street. Nearby is Hoppy Street, a retro alley of izakayas, " +
                    "and Kappabashi, a street specializing in kitchenware and cookware. Across the " +
                    "Sumida River you get one of the best views of Tokyo Skytree, the tall " +
                    "broadcasting tower with its own observation decks."
            )

            SectionHeader("Ginza")
            BodyText(
                "Upscale shopping and fine dining - flagship department stores, luxury brand " +
                    "boutiques, art galleries, and the landmark Ginza Wako clock tower."
            )

            SectionHeader("Akihabara")
            BodyText(
                "This is the region for all things anime, manga, and otaku culture in Tokyo - " +
                    "multi-floor stores like Mandarake stacked with manga, anime figures, and " +
                    "trading cards, retro and modern video game shops, arcades, and maid cafes, " +
                    "alongside its original identity as an electronics district. If Akihabara " +
                    "feels too touristy, Nakano Broadway - a shopping arcade near Nakano Station, " +
                    "a few stops west of Shinjuku on the JR Chuo Line - is a second, more " +
                    "local-feeling otaku shopping hub worth knowing about. Akihabara is also one " +
                    "of the two main concentration points for Tokyo's animal cafes: Akiba " +
                    "Fukurou, one of the largest owl cafes in Japan, is here (see the Tokyo - " +
                    "Special Cafes screen for details and other animal cafes)."
            )

            SectionHeader("Harajuku")
            BodyText(
                "Youth fashion and street style along Takeshita Street, right next to the calm, " +
                    "forested grounds of Meiji Jingu (Meiji Shrine). Meiji Jingu is a major Shinto " +
                    "shrine dedicated to Emperor Meiji and Empress Shoken, set inside a dense, " +
                    "deliberately planted forest of around 100,000 trees - entered right by " +
                    "Harajuku Station and bordering Yoyogi Park, it's a striking, quiet contrast " +
                    "to the fashion crowds just outside its gates. This is also the other main " +
                    "animal-cafe concentration point in Tokyo, with several cat cafes, puppy " +
                    "cafes, and multi-animal spots like Harry's Zoo Cafe (again, see the Tokyo - " +
                    "Special Cafes screen for the full list)."
            )

            SectionHeader("Odaiba")
            BodyText(
                "A waterfront, man-made island with digital-art attractions (teamLab Planets), " +
                    "shopping malls, Joypolis, and views of the Rainbow Bridge and Tokyo skyline."
            )

            SectionHeader("Ueno")
            BodyText(
                "Home to Ueno Park, several major museums (Tokyo National Museum among them), " +
                    "Ueno Zoo, and Ameyoko, a lively market street."
            )

            SectionHeader("Just outside these core regions")
            BodyText(
                "A few well-known spots sit outside the 7 central regions above but are covered " +
                    "elsewhere in this app: Tokyo Dome City is actually in Bunkyo City near " +
                    "Suidobashi Station, a bit north of Ueno/Akihabara (see Tokyo - Parks and " +
                    "Attractions). Ghibli Museum is further out in Mitaka, western Tokyo (also " +
                    "under Tokyo - Parks and Attractions). And if you were looking for Nintendo " +
                    "Museum here - it's not actually in Tokyo at all, it's in Uji, Kyoto " +
                    "Prefecture (see Kyoto - Parks and Attractions)."
            )
            Spacer(Modifier.height(16.dp))
        }
    }
}
