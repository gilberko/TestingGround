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
fun KDramasScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "K-Dramas", onBack = onBack) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(24.dp)
        ) {
            SectionHeader("What Are K-Dramas?")
            BodyText(boldNames("K-dramas are Korean television dramas, almost always produced as limited series — typically 16-20 episodes airing two at a time each week — rather than the open-ended, multi-season format common in the US. They span genres from romance and melodrama to historical (sageuk), thriller, fantasy, and medical or legal procedurals, and are one of the two pillars (alongside K-pop) of the broader \"Hallyu\" (Korean Wave) that has driven Korean culture's global popularity.", listOf("K-dramas", "sageuk", "Hallyu")))

            SectionHeader("Well-Known Actors")
            BodyText(boldNames("Some of the most recognizable K-drama leads include Lee Min-ho, Song Joong-ki, Song Hye-kyo, Park Seo-joon, IU (Lee Ji-eun), Kim Soo-hyun, Park Bo-gum, and Son Ye-jin. Kim Soo-hyun won the Baeksang Arts Award for Most Popular Actor in 2024, and Hyun Bin won Best Actor - Television at the 2026 Baeksang Awards; Cha Eun-woo and Park Bo-gum are frequently cited among the biggest draws more recently, alongside rising names like Wi Ha-joon and Hwang In-youp.", listOf("Lee Min-ho", "Song Joong-ki", "Song Hye-kyo", "Park Seo-joon", "IU", "Kim Soo-hyun", "Park Bo-gum", "Son Ye-jin", "Baeksang Arts Award", "Hyun Bin", "Cha Eun-woo", "Wi Ha-joon", "Hwang In-youp")))

            SectionHeader("Studio Tours and Museums")
            BodyText(boldNames("The KBS Suwon Drama Center is a real production facility with an open set used for historical dramas as well as commercials, visitable through its own tour program or Suwon's city tour. MBC Dramia in Yongin (also called Dae Jang Geum Park) is a large open set recreating Joseon-era palaces, villages, and a fortress, used for dramas like Jumong, Queen Seon-deok, and Dae Jang Geum — commercial half-day tours from Seoul are widely bookable. The Korean Folk Village, also in Yongin, is an outdoor living museum with over 260 traditional structures used in many historical dramas and open to the public daily, not just for filming.", listOf("KBS Suwon Drama Center", "MBC Dramia", "Dae Jang Geum Park", "Jumong", "Queen Seon-deok", "Dae Jang Geum", "Korean Folk Village")))
            BodyText(boldNames("Nami Island, near Chuncheon, is famous as the filming location of Winter Sonata, including its Metasequoia Lane and a statue commemorating the drama in Gongsaengwon Garden — freely visitable and often paired with Petite France in Gapyeong, which appeared in My Love from the Star and Secret Garden.", listOf("Nami Island", "Winter Sonata", "Petite France", "My Love from the Star", "Secret Garden")))

            SectionHeader("Filming Location Tracker Websites")
            BodyText("If you've heard of a site called \"kspotmap,\" the closest real match is K-SPOT Travel (kspottravel.com), an interactive map of verified K-content filming locations — that's likely what you're remembering, though the exact name differs. A few other well-established alternatives worth knowing: K-Drama Spots (kdramaspots.com), which covers hundreds of dramas with episode-by-episode location guides; K-Drama Locations (kdramalocations.com), filterable by region; and Noona's Noonchi, a regularly updated filming-location blog and map.")

            SectionHeader("Common Filming Locations")
            BodyText(boldNames("Contemporary K-dramas tend to film across ordinary Seoul neighborhoods, university campuses, and cafes rather than one dedicated park, though Nami Island and Petite France (above) are recurring favorites. For historical/sageuk dramas, the dedicated open sets do the heavy lifting: besides the Korean Folk Village and MBC Dramia already mentioned, the Mungyeongsaejae Open Set in Mungyeong recreates Joseon-era Hanyang streets and government offices specifically for period dramas, and Damyang's Juknokwon bamboo forest has been used for historical dramas including Iljimae and Mask, with on-site markers showing exact filming spots. The Hapcheon Image Theme Park (also called Yeongsang Theme Park) is unusual in also recreating 1930s-1980s Seoul and Pyongyang streetscapes for dramas and films set in that era, such as Mr. Sunshine and A Taxi Driver.", listOf("Mungyeongsaejae Open Set", "Mungyeong", "Juknokwon", "Damyang", "Iljimae", "Mask", "Hapcheon Image Theme Park", "Yeongsang Theme Park", "Mr. Sunshine", "A Taxi Driver")))
        }
    }
}
