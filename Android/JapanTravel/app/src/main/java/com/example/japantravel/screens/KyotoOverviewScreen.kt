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
fun KyotoOverviewScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "Overview", onBack = onBack) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            SectionHeader("Fushimi Inari Shrine")
            BodyText(
                "Thousands of vermillion torii gates, donated by businesses over centuries, " +
                    "climb the wooded slopes of **Mt. Inari**. The shrine is dedicated to Inari, the " +
                    "deity of rice and business success, and the fox statues around the grounds " +
                    "are considered Inari's messengers. It's free and open 24 hours - a full " +
                    "loop to the summit and back takes 2-3 hours, but most visitors just walk the " +
                    "first 20-30 minutes for the classic gate-tunnel photos. Take the JR Nara " +
                    "Line to Inari Station (2 stops, about 5 minutes from Kyoto Station) or the " +
                    "Keihan Line to Fushimi-Inari Station."
            )

            SectionHeader("Kiyomizudera")
            BodyText(
                "A wooden temple founded in 778 CE, famous for its huge stage that juts out over " +
                    "the hillside on wooden pillars - built entirely without nails - and for " +
                    "sweeping views over the city. The approach winds uphill through **Higashiyama**'s " +
                    "preserved old lanes (**Sannenzaka** and **Ninenzaka**), lined with traditional shops " +
                    "and tea houses. Take a city bus from Kyoto Station (about 15-20 minutes), " +
                    "then walk uphill."
            )

            SectionHeader("Gion")
            BodyText(
                "**Kyoto**'s most famous geisha (geiko) and apprentice-geisha (maiko) district: " +
                    "traditional wooden machiya townhouses, teahouses, and **Hanamikoji Street**, " +
                    "where you might glimpse a geiko or maiko heading to an evening appointment " +
                    "(be respectful with photos - it's their commute, not a photo op). It's " +
                    "walkable from **Kiyomizudera** or a short bus/train ride from Kyoto Station."
            )

            SectionHeader("Arashiyama Bamboo Grove")
            BodyText(
                "A towering, glowing corridor of bamboo in western **Kyoto** - one of the most " +
                    "photographed spots in Japan. It's free and always accessible, but go early " +
                    "morning to see it without dense crowds. Take the JR Sagano Line to " +
                    "Saga-Arashiyama Station, about 15 minutes from Kyoto Station."
            )

            SectionHeader("Tenryu-ji")
            BodyText(
                "A UNESCO World Heritage Zen temple right next to the bamboo grove, founded in " +
                    "1339. Its main draw is the **Sogenchi Garden**, a landscape garden designed to " +
                    "frame the surrounding mountains as \"borrowed scenery\" - one of Japan's " +
                    "oldest garden designs still in its original form."
            )

            SectionHeader("Togetsukyo Bridge")
            BodyText(
                "**Arashiyama**'s iconic wooden-railed bridge spanning the **Katsura River**, a short " +
                    "walk from the bamboo grove and **Tenryu-ji**. It's a classic photo spot year-" +
                    "round, especially stunning during cherry blossom season and autumn foliage."
            )

            SectionHeader("Kinkaku-ji (Golden Pavilion)")
            BodyText(
                "A pavilion with its top two floors covered in gold leaf, set beside a reflecting " +
                    "pond - arguably **Kyoto**'s single most photographed sight. Originally a " +
                    "retirement villa, it became a Zen temple after its owner's death. Take a bus " +
                    "from Kyoto Station (about 40 minutes) toward Kinkakuji-michi."
            )

            SectionHeader("Ginkaku-ji (Silver Pavilion)")
            BodyText(
                "Despite the name, it was never actually covered in silver. A more understated, " +
                    "contemplative counterpart to **Kinkaku-ji**, with a meticulously raked sand " +
                    "garden (representing a sea and **Mt. Fuji**) and mossy grounds. It sits at the " +
                    "northern end of the **Philosopher's Path**."
            )

            SectionHeader("Philosopher's Path")
            BodyText(
                "A quiet, canal-side walking path about 2km long, lined with cherry trees, " +
                    "connecting **Ginkaku-ji** in the north to **Nanzen-ji** in the south. It's named " +
                    "after a Kyoto University philosophy professor who reportedly walked it daily " +
                    "for meditation. A pleasant, unhurried way to link the two temples, with small " +
                    "cafes and shops along the way."
            )

            SectionHeader("Nishiki Market")
            BodyText(
                "Nicknamed \"**Kyoto**'s Kitchen\" - a narrow, covered shopping street packed with " +
                    "100+ stalls selling pickles, skewered street food, sweets, tea, and " +
                    "kitchenware (including handmade knives). It's centrally located and easily " +
                    "walkable from downtown **Kyoto**, and a good stop between sightseeing rather than " +
                    "a dedicated trip."
            )

            SectionHeader("Himeji (day trip)")
            BodyText(
                "**Himeji** isn't geographically close to **Kyoto** (about 124km away), but it's a fast, " +
                    "easy day trip by Shinkansen on the same Tokaido/Sanyo line **Kyoto** already " +
                    "sits on - Nozomi trains take about 45 minutes direct, Hikari about 55 " +
                    "minutes (Nozomi isn't covered by the Japan Rail Pass; Hikari and Kodama are). " +
                    "The reward is **Himeji Castle**, Japan's best-preserved original wooden castle " +
                    "keep and a UNESCO World Heritage Site - about a 15-20 minute walk straight " +
                    "from Himeji Station."
            )
            Spacer(Modifier.height(16.dp))
        }
    }
}
