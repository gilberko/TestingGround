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
fun SeoulWhereToStayScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "Where To Stay", onBack = onBack) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(24.dp)
        ) {
            SectionHeader("Families")
            BodyText(boldNames("Families generally do well staying around City Hall/Jung-gu or Yeouido — both are quieter than the nightlife districts, well connected by subway to the palaces and major attractions, and close to spacious hotel options. Gangnam also works well for families who want easy access to COEX Mall and its aquarium and library.", listOf("City Hall", "Jung-gu", "Yeouido", "Gangnam", "COEX Mall")))

            SectionHeader("Business Travelers")
            BodyText(boldNames("Gangnam and Yeouido are the two best bases for business travel — Gangnam has a dense concentration of corporate offices, COEX's convention center, and reliable subway access, while Yeouido is Seoul's financial district, home to the Korea Exchange and many corporate headquarters.", listOf("Gangnam", "Yeouido", "COEX", "Korea Exchange")))

            SectionHeader("Young Adults & Nightlife")
            BodyText(boldNames("Hongdae, Itaewon, and Gangnam are the three main nightlife hubs. Hongdae skews younger and more indie/club-focused around Hongik University; Itaewon has the most international crowd and widest variety of bars; Gangnam has a more upscale, polished club scene. All three have good late-night transit and taxi availability, since Kakao T runs 24 hours.", listOf("Hongdae", "Itaewon", "Gangnam", "Hongik University", "Kakao T")))

            SectionHeader("Safety")
            BodyText(boldNames("Seoul is generally considered one of the safer major capital cities in the world for tourists, with a low rate of violent crime — solo travelers, including women, commonly report feeling comfortable walking around at night in most areas. As with any big city, though, ordinary caution still applies.", listOf("Seoul")))
            BodyText(boldNames("Stay alert in the densest nightlife strips — Itaewon, Hongdae, and Gangnam — especially late at night around closing time, when alcohol-related disturbances are more likely; keep an eye on drinks and belongings in crowded bars and clubs. In busy tourist markets like Myeongdong and Dongdaemun, standard pickpocket and scam awareness (as in any crowded tourist area) is worth keeping in mind, though outright theft is relatively uncommon by international standards.", listOf("Itaewon", "Hongdae", "Gangnam", "Myeongdong", "Dongdaemun")))
            BodyText(boldNames("A few pockets are worth avoiding or are simply not relevant to a typical tourist visit — parts of Yeongdeungpo-gu contain a red-light/adult-entertainment district that isn't tourist-oriented and is best skipped, and isolated back-alleys near very late-night drinking areas anywhere in the city are best approached with the same care you'd use in any country. Overall, though, these are exceptions rather than the rule — Seoul's reputation for safety is well earned, and most neighborhoods are comfortable to walk through at any hour.", listOf("Yeongdeungpo-gu", "Seoul")))
        }
    }
}
