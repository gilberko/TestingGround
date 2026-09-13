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
fun GyeongjuPlacesOfInterestScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "Places Of Interest", onBack = onBack) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(24.dp)
        ) {
            BodyText(boldNames("Gyeongju was the capital of the Silla kingdom for nearly a thousand years, and its historic core (plus Namsan Mountain) is registered as a UNESCO World Heritage \"Historic Area.\" Most travelers plan around two days to see it properly.", listOf("Gyeongju", "Silla", "Namsan")))

            SectionHeader("Bulguksa Temple and Seokguram Grotto")
            BodyText(boldNames("Bulguksa is one of Korea's most important Buddhist temple complexes, a UNESCO World Heritage Site on the slopes of Tohamsan. Seokguram Grotto, part of the same complex and completed in 774 AD, is a National Treasure housing a seated Buddha statue overlooking the East Sea — only an hourly shuttle bus connects it to Bulguksa, so plan your timing.", listOf("Bulguksa", "Seokguram Grotto", "Tohamsan")))

            SectionHeader("Cheomseongdae Observatory")
            BodyText(boldNames("A 9-meter bottle-shaped stone observatory from the 7th century, said to be built from 365 stones — one for each day of the year — and among the oldest astronomical observatories in Asia.", listOf("Cheomseongdae")))

            SectionHeader("Daereungwon Tomb Complex")
            BodyText(boldNames("A royal burial mound complex right in the city center with around 30 Silla-era royal tombs, including Hwangnam Daechong and King Michu's tomb.", listOf("Daereungwon", "Hwangnam Daechong")))

            SectionHeader("Donggung Palace and Wolji Pond")
            BodyText(boldNames("A former Silla secondary palace (Wolji Pond built in 674 AD, Donggung Palace in 679 AD) widely considered Gyeongju's best night view, since the illuminated palace buildings reflect on the pond after dark. Open 9am-10pm (last entry 9:30pm), admission around ₩3,000.", listOf("Donggung Palace", "Wolji Pond")))

            SectionHeader("Gyeongju National Museum")
            BodyText(boldNames("Widely regarded as the best museum in Korea for Silla-era artifacts, a natural stop for context before or after seeing the tombs and temples.", listOf("Gyeongju National Museum")))

            SectionHeader("Yangdong Folk Village")
            BodyText(boldNames("A UNESCO World Heritage Site preserving a Joseon Dynasty clan village largely unchanged since the 1392-1910 era.", listOf("Yangdong Folk Village")))

            SectionHeader("Bomun Lake Resort")
            BodyText(boldNames("A tourist complex about 5km east of the city center, built around an artificial lake, with resort hotels, a ferry port, Yukbu Village (a recreated traditional village), the Gyeongju World amusement park, and traditional dance and music performances at the Bomun Outdoor Performance Theatre from April to October.", listOf("Bomun Lake Resort", "Yukbu Village", "Gyeongju World", "Bomun Outdoor Performance Theatre")))
        }
    }
}
