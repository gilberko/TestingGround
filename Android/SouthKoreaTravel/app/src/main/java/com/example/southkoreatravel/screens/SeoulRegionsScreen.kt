package com.example.southkoreatravel.screens

import com.example.southkoreatravel.R
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SeoulRegionsScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "Regions", onBack = onBack) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(24.dp)
        ) {
            SectionHeader("Gangnam")
            PlacePhoto(R.drawable.region_seoul_gangnam, "Teheran-ro, Gangnam", "Teheran-ro, Gangnam — Photo: kallerna, CC BY-SA 4.0, via Wikimedia Commons")
            BodyText(boldNames("Gangnam is Seoul's upscale, modern district south of the Han River — wide boulevards, luxury shopping, K-pop agency offices, and a dense nightlife/bar scene. Highlights include COEX Mall (with the Starfield Library and SEA LIFE Coex Aquarium), K-Star Road, and the K-Pop Square 3D billboard. It's polished and convenient, but can feel less \"traditional Korea\" than other districts.", listOf("Gangnam", "COEX Mall", "Starfield Library", "SEA LIFE Coex Aquarium", "K-Star Road", "K-Pop Square")))

            SectionHeader("Jongno-gu / Jung-gu (Old Town)")
            PlacePhoto(R.drawable.seoul_bukchon, "Bukchon Hanok Village", "Bukchon Hanok Village — Photo: Basile Morin, CC BY-SA 4.0, via Wikimedia Commons")
            BodyText(boldNames("This is historic central Seoul, home to Gyeongbokgung Palace, Bukchon Hanok Village's traditional wooden houses, and the Insadong district's tea houses, galleries, and craft shops. It's the best base for palace-hopping and getting a feel for pre-modern Korean architecture and culture.", listOf("Jongno-gu", "Jung-gu", "Gyeongbokgung Palace", "Bukchon Hanok Village", "Insadong")))

            SectionHeader("Hongdae / Mapo-gu")
            PlacePhoto(R.drawable.region_seoul_hongdae, "Hongdae street", "Hongdae street — Photo: U0894629, CC BY-SA 4.0, via Wikimedia Commons")
            BodyText(boldNames("Centered around Hongik University, Hongdae is Seoul's youthful, artsy nightlife hub — indie music clubs, street performances on weekend nights, and a dense cluster of bars and clubs that stay lively into the early morning. It's also home to a growing specialty coffee scene.", listOf("Hongdae", "Mapo-gu", "Hongik University")))

            SectionHeader("Itaewon / Yongsan-gu")
            PlacePhoto(R.drawable.region_seoul_itaewon, "Itaewon street", "Itaewon street — Photo: Aatu Dorochenko, CC BY-SA 4.0, via Wikimedia Commons")
            BodyText(boldNames("Itaewon is Seoul's most internationally-flavored district, historically popular with expats and foreign visitors — it has the city's widest range of international cuisine and a foreigner-friendly nightlife strip. Nearby Yongsan-gu also has the National Museum of Korea and easy access to Han River parks.", listOf("Itaewon", "Yongsan-gu", "National Museum of Korea")))

            SectionHeader("Seongsu-dong")
            PlacePhoto(R.drawable.region_seoul_seongsu, "Seongsu-dong storefronts", "Seongsu-dong storefronts — Photo: CartoonChess, CC BY-SA 4.0, via Wikimedia Commons")
            BodyText(boldNames("Nicknamed the \"Korean Brooklyn,\" Seongsu-dong is a former industrial/shoe-factory district reborn as a trendy area of converted warehouses, design showrooms, and some of the city's most photogenic specialty cafes, including Mesh Coffee and Lowkey Coffee.", listOf("Seongsu-dong", "Mesh Coffee", "Lowkey Coffee")))

            SectionHeader("Dongdaemun-gu")
            PlacePhoto(R.drawable.region_seoul_dongdaemun, "Heunginjimun (Dongdaemun Gate)", "Heunginjimun (Dongdaemun Gate) — Photo: Karl Baron, CC BY-SA 2.0, via Wikimedia Commons")
            BodyText(boldNames("Dongdaemun is Seoul's round-the-clock shopping and market district, anchored by the futuristic Dongdaemun Design Plaza (DDP) and sprawling wholesale/retail malls that stay open late into the night — a good stop for late-night shopping or browsing fabric and fashion markets.", listOf("Dongdaemun", "Dongdaemun Design Plaza")))

            SectionHeader("Yeouido")
            PlacePhoto(R.drawable.region_seoul_yeouido, "Yeouido skyline and the National Assembly", "Yeouido skyline and the National Assembly — Photo: Ox1997cow, CC BY-SA 4.0, via Wikimedia Commons")
            BodyText(boldNames("Yeouido is Seoul's business and finance island on the Han River, home to the National Assembly building and major broadcasters. It's quieter and more corporate than other districts, but Yeouido Hangang Park is one of the best spots in the city for cherry blossoms each spring.", listOf("Yeouido", "National Assembly", "Yeouido Hangang Park")))
        }
    }
}
