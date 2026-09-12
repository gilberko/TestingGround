package com.example.southkoreatravel.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BusanWhereToStayScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "Where To Stay", onBack = onBack) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(24.dp)
        ) {
            SectionHeader("Families")
            BodyText(boldNames("Haeundae is the best overall base for families — a short walk to Haeundae Beach, close to the SEA LIFE Busan Aquarium, Haeundae Market for kid-friendly street food, and Haeundae Blueline Park, with good subway access to the rest of the city and family-oriented hotel amenities (cribs, family suites) common in the area.", listOf("Haeundae", "Haeundae Beach", "SEA LIFE Busan Aquarium", "Haeundae Market", "Haeundae Blueline Park")))
            BodyText(boldNames("Less ideal for families: the area right around Busan Station, including the Chinatown/Texas Street strip (see safety notes below), and the dense late-night entertainment zones of central Seomyeon or the Kyungsungdae/Pukyong (KSU) university nightlife strip, which are livelier and noisier after dark.", listOf("Busan Station", "Chinatown", "Texas Street", "Seomyeon", "Kyungsungdae", "Pukyong")))

            SectionHeader("Business Travelers")
            BodyText(boldNames("Seomyeon is Busan's central commercial and business district, sitting at the interchange of Subway Line 1 and Line 2, with strong transit connectivity plus shopping and dining nearby. For a convention-specific trip, the BEXCO area in Haeundae-gu/Centum is the better choice, since hotels cluster directly around the Busan Exhibition & Convention Center.", listOf("Seomyeon", "BEXCO", "Busan Exhibition & Convention Center")))
            BodyText(boldNames("Less ideal for business stays: far-flung beach towns like Songjeong or Gijang, and the Gamcheon or Huinnyeoul cultural villages — scenic, but poorly connected for meeting schedules and lacking business-hotel density.", listOf("Songjeong", "Gijang", "Gamcheon", "Huinnyeoul")))

            SectionHeader("Young Adults and Nightlife")
            BodyText(boldNames("Seomyeon is Busan's biggest general nightlife hub — neon-lit streets, bars, and clubs, very central via the Line 1/2 interchange. The Kyungsungdae/Pukyong University area (KSU), in Nam-gu, is the most intense and youthful nightlife zone, driven by three nearby universities, with bars and clubs busy into the early morning on weekends. Gwangalli is known for scenic beachfront rooftop bars and waterfront dining, trendier and more relaxed than a dense club scene. Haeundae leans upscale and beachfront, with cocktail bars and lounges pairing nightlife with ocean views rather than heavy clubbing.", listOf("Seomyeon", "Kyungsungdae", "Pukyong University", "Gwangalli", "Haeundae")))

            SectionHeader("Safety")
            BodyText(boldNames("Busan is broadly considered a safe city for tourists, including solo travelers. The area directly across from Busan Station — known as Busan Chinatown or Texas Street — is repeatedly flagged by travel sources as having a reputation for prostitution and crime risk after dark, and is worth avoiding, especially for women traveling alone.", listOf("Busan Station", "Busan Chinatown", "Texas Street")))
            BodyText(boldNames("Standard big-city nightlife precautions apply in dense bar streets like Seomyeon — don't accept open drinks from strangers, use official taxis late at night, and stick to well-lit main streets, the same care worth taking around any lively entertainment district.", listOf("Seomyeon")))
        }
    }
}
