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
                    "crossings, and the Hachiko statue nearby. Big shopping (Shibuya 109) and a " +
                    "youth-culture, nightlife hub."
            )

            SectionHeader("Shinjuku")
            BodyText(
                "A major transit hub with excellent train/subway links, skyscraper observation " +
                    "decks, endless dining, and Kabukicho nightlife. Often recommended as the best " +
                    "base for first-time visitors."
            )

            SectionHeader("Asakusa")
            BodyText(
                "Tokyo's old town feel: Sensoji, the city's oldest temple, reached via the " +
                    "Nakamise shopping street. Nearby is Hoppy Street, a retro alley of izakayas, " +
                    "and Kappabashi, a street specializing in kitchenware and cookware."
            )

            SectionHeader("Ginza")
            BodyText(
                "Upscale shopping and fine dining - flagship department stores, luxury brand " +
                    "boutiques, art galleries, and the landmark Ginza Wako clock tower."
            )

            SectionHeader("Akihabara")
            BodyText(
                "The center of anime, manga, and electronics culture - multi-floor electronics " +
                    "stores, anime/game shops, and maid cafes."
            )

            SectionHeader("Harajuku")
            BodyText(
                "Youth fashion and street style along Takeshita Street, right next to the calm, " +
                    "forested grounds of Meiji Shrine."
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
            Spacer(Modifier.height(16.dp))
        }
    }
}
