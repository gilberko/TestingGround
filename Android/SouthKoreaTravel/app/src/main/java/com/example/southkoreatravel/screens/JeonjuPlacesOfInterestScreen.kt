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
fun JeonjuPlacesOfInterestScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "Places Of Interest", onBack = onBack) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(24.dp)
        ) {
            SectionHeader("Jeonju Hanok Village")
            BodyText(boldNames("The largest hanok (traditional house) village in Korea, with more than 700 traditional houses forming a continuous walkable district — the heart of a visit to Jeonju.", listOf("Jeonju Hanok Village", "hanok")))

            SectionHeader("Jeonju Bibimbap")
            BodyText(boldNames("Jeonju is considered the birthplace of the most famous regional style of bibimbap in Korea. Gogung is often cited as the place to try it in its most traditional form.", listOf("Jeonju", "bibimbap", "Gogung")))

            SectionHeader("Gyeonggijeon Shrine")
            BodyText(boldNames("Enshrines the portrait of King Taejo, founder of the Joseon Dynasty, and is one of the first sites you reach entering Hanok Village.", listOf("Gyeonggijeon", "King Taejo", "Joseon")))

            SectionHeader("Jaman Mural Village")
            BodyText(boldNames("A hillside neighborhood about a 10-minute walk from Hanok Village, covered in colorful murals ranging from traditional motifs to contemporary art.", listOf("Jaman Mural Village")))

            SectionHeader("Jeondong Catholic Church")
            BodyText(boldNames("A Romanesque-style cathedral built in 1914 on the grounds where Korean Catholics were martyred in the late 19th century, considered one of the most beautiful cathedrals in Korea.", listOf("Jeondong Catholic Church")))

            SectionHeader("Nambu Traditional Market")
            BodyText(boldNames("The largest traditional market in Jeonju, dating back to the Joseon Dynasty, within walking distance of Hanok Village. Its Night Market runs Friday and Saturday evenings, 5pm-11pm, with street food vendors and a small concert stage.", listOf("Nambu Traditional Market", "Night Market")))

            SectionHeader("Hanji Paper Craft Experiences")
            BodyText(boldNames("Jeonju is the historic home of hanji, traditional mulberry paper once used for royal records. Studios in and around Hanok Village offer paper-making, fan-making, and calligraphy sessions, and shops sell hanji stationery, fans, and decor.", listOf("hanji")))

            SectionHeader("Omokdae and Imokdae")
            BodyText(boldNames("Scenic pavilions overlooking Hanok Village. Omokdae is historically significant as the spot where General Yi Seong-gye, founder of the Joseon Dynasty, celebrated a military victory.", listOf("Omokdae", "Imokdae", "Yi Seong-gye")))
        }
    }
}
