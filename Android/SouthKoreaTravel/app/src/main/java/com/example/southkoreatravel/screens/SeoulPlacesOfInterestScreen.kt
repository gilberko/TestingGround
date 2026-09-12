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
fun SeoulPlacesOfInterestScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "Places Of Interest", onBack = onBack) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(24.dp)
        ) {
            SectionHeader("Gyeongbokgung Palace")
            BodyText("Gyeongbokgung Palace (경복궁) is Seoul's main royal palace, in Sejongno, Jongno-gu — the nearest station is Gyeongbokgung Station (Line 3), Exit 5. Anyone wearing hanbok (traditional Korean clothing) gets in free — a policy that actually applies to all five of Seoul's royal palaces, but Gyeongbokgung is the largest and most iconic choice for it, and hanbok rental shops line the streets right outside the main gate (roughly ₩15,000-20,000 for a two-hour rental). Don't miss the Royal Guard Changing Ceremony at Gwanghwamun Gate, held around 10am and 2pm (closed Tuesdays), the scenic Gyeonghoeru Pavilion on its lotus pond, and the National Folk Museum of Korea, which sits right on the palace grounds.")

            SectionHeader("Starfield Library")
            BodyText("The famous library inside a shopping mall is the Starfield Library (별마당도서관), located inside COEX Mall in Samseong-dong, Gangnam — nearest station Samseong (Line 2), directly connected to the mall. Bookshelves rise about 13 meters across two floors holding roughly 70,000 books. It's free to visit and one of the most photographed spots in Seoul.")

            SectionHeader("K-Star Road")
            BodyText("K-Star Road runs along Apgujeong-ro in the Apgujeong/Cheongdam area of Gangnam — nearest station Apgujeong Rodeo — and is lined with 17 life-size \"GangnamDol\" bear statues, each decorated in the style of a different K-pop group or agency. It's free, outdoor, and no ticket is needed. Note that SM Entertainment's nearby K-pop experience center, COEX Artium/SUM, permanently closed back in 2020, so it's no longer part of a K-pop-themed visit here.")

            SectionHeader("Museums")
            BodyText("The National Museum of Korea (국립중앙박물관) in Yongsan-gu — nearest station Ichon — is the country's flagship, largest museum, covering Korean history and art from prehistoric times to the modern era, with free admission to the permanent collection. One quirk worth knowing: the permanent galleries close on the first Monday of March, April, June, September, November, and December for maintenance.")
            BodyText("The National Museum of Korean Contemporary History sits right next to Gwanghwamun Square — nearest station Gwanghwamun (Line 5) — and covers Korea's modern history from the late 19th century through today, with free admission and a rooftop observatory overlooking the square. It's a good complement to the National Museum of Korea's broader historical scope.")

            SectionHeader("Jogyesa Temple")
            BodyText("Jogyesa Temple (조계사), near Insadong in Jongno-gu (nearest station Anguk), is the head temple of the Jogye Order — effectively the center of Korean Buddhism — and is free and open to the public year-round. It's most spectacular around Buddha's Birthday, when it serves as the finishing point of the UNESCO-recognized Lotus Lantern Festival (Yeondeunghoe) parade, with the temple grounds covered in illuminated lotus-shaped lanterns from around 6pm to midnight during the festival period.")
        }
    }
}
