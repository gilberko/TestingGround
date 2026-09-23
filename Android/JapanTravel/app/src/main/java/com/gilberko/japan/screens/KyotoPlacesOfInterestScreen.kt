package com.gilberko.japan.screens

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
fun KyotoPlacesOfInterestScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "Places of Interest", onBack = onBack) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            SectionHeader("Fushimi Inari Taisha")
            BodyText(
                "The head shrine of **Inari**, god of rice and prosperity, famous for the " +
                    "thousands of vermilion **torii** gates that form tunnels winding up the " +
                    "wooded slopes of **Mount Inari** - each gate donated by a business or " +
                    "individual. It's free and open 24 hours, so an early-morning or evening " +
                    "visit is the best way to avoid the crowds that fill the lower gates by " +
                    "mid-morning; the full loop to the summit and back takes 2-3 hours, though " +
                    "most visitors only walk partway up."
            )
            BodyText(
                "Nearest stations: **Inari** (JR Nara Line, one stop from **Kyoto Station**) or " +
                    "**Fushimi Inari** (Keihan Main Line), both right at the shrine's entrance."
            )

            SectionHeader("Kiyomizu-dera + Higashiyama")
            BodyText(
                "**Kiyomizu-dera** is a wooden temple built on a hillside without a single nail, " +
                    "best known for its large wooden stage jutting out over the valley, giving " +
                    "sweeping views back over **Kyoto** - especially striking during cherry " +
                    "blossom and autumn foliage seasons and during evening light-ups."
            )
            BodyText(
                "The temple sits at the top of **Higashiyama**, a preserved old district of " +
                    "narrow, sloped lanes - **Sannenzaka** and **Ninenzaka** - lined with " +
                    "traditional wooden shopfronts, tea houses, and souvenir stores, making the " +
                    "walk up to the temple as much a highlight as the temple itself. It's about a " +
                    "10-15 minute walk (or short bus ride) from **Kyoto** or **Gion-Shijo** " +
                    "Station, as there's no train station directly at the temple."
            )

            SectionHeader("Kinkaku-ji (The Golden Pavilion)")
            BodyText(
                "A Zen temple pavilion whose top two floors are covered entirely in gold leaf, " +
                    "sitting at the edge of a reflecting pond so that on a calm day its image " +
                    "doubles in the water. Visitors follow a fixed one-way path around the " +
                    "pavilion and garden rather than entering the building itself, so a visit is " +
                    "typically brief but photogenic."
            )
            BodyText(
                "It's in northwest **Kyoto**, away from the train network - reached by bus " +
                    "(around 40 minutes) from **Kyoto Station**, or a shorter bus ride if " +
                    "combined with other northern **Kyoto** stops."
            )

            SectionHeader("Arashiyama")
            BodyText(
                "A district in western **Kyoto** built around the **Bamboo Grove** - a short but " +
                    "striking path through towering bamboo stalks - along with the **Togetsukyo " +
                    "Bridge** over the **Katsura River** and **Tenryu-ji**, a UNESCO-listed Zen " +
                    "temple with one of Japan's finest strolling gardens, best visited early to " +
                    "beat both the crowds and the heat in the grove."
            )
            BodyText(
                "Reached via **Arashiyama Station** (Keifuku or Hankyu lines) or **Saga-Arashiyama " +
                    "Station** (JR Sagano/San-in Line), about 15-20 minutes from central Kyoto."
            )

            SectionHeader("Gion")
            BodyText(
                "**Kyoto**'s best-known geisha (**geiko**) and apprentice geisha (**maiko**) " +
                    "district, with streets of preserved wooden **machiya** townhouses, teahouses, " +
                    "and traditional restaurants, centered around **Hanamikoji Street**. Many " +
                    "visitors find it more atmospheric in the early evening, once the lanterns are " +
                    "lit and the daytime tour-group crowds have thinned, though some narrow " +
                    "photo-heavy lanes (like **Hanamikoji**) now restrict photography due to past " +
                    "overtourism, so it's worth checking posted signs and being respectful if you " +
                    "happen to see a geiko or maiko passing by."
            )
            BodyText(
                "Centered around **Gion-Shijo Station** (Keihan Main Line) or a short walk from " +
                    "**Kawaramachi Station** (Hankyu Kyoto Line)."
            )

            SectionHeader("Nijō Castle")
            BodyText(
                "Built as the Kyoto residence of the Tokugawa shoguns, its **Ninomaru Palace** is " +
                    "famous for \"nightingale floors\" - floorboards deliberately built to chirp " +
                    "underfoot as a defense against intruders sneaking up unheard. The grounds " +
                    "also include formal gardens and stone-walled moats typical of Edo-period " +
                    "castle architecture."
            )
            BodyText(
                "A short walk from **Nijojo-mae Station** on the Tozai subway line."
            )

            SectionHeader("Nishiki Market")
            BodyText(
                "A narrow, roofed shopping street nicknamed **\"Kyoto's Kitchen,\"** packed with " +
                    "over a hundred stalls selling fresh seafood, pickles, sweets, knives, tea, " +
                    "and other local specialty foods - many stalls sell small skewered or " +
                    "bite-sized items meant for eating as you walk (etiquette generally expects " +
                    "you to finish it near where you bought it rather than while walking further " +
                    "down the aisle)."
            )
            BodyText(
                "Runs parallel to **Shijo-dori**, a short walk from **Karasuma Station** or " +
                    "**Kawaramachi Station**."
            )

            SectionHeader("Ginkaku-ji + Philosopher's Path")
            BodyText(
                "**Ginkaku-ji** (\"the Silver Pavilion\") was never actually covered in silver as " +
                    "planned, but its understated wooden design, dry-sand garden shaped into a " +
                    "cone representing Mount Fuji, and moss garden are considered a refined " +
                    "counterpoint to the flashier **Kinkaku-ji**."
            )
            BodyText(
                "From its entrance begins the **Philosopher's Path**, a roughly 2km canal-side " +
                    "walking route lined with cherry trees (named for a **Kyoto University** " +
                    "philosopher who reportedly walked it daily for meditation), leading south " +
                    "toward **Nanzen-ji** temple and passing small cafes, shops, and shrines along " +
                    "the way."
            )

            SectionHeader("Sanjūsangen-dō")
            BodyText(
                "A long, narrow wooden hall - its name refers to the 33 bays between its " +
                    "supporting pillars - housing 1,001 life-sized statues of the " +
                    "thousand-armed **Kannon**, arranged in rows around a large central seated " +
                    "figure, with 28 guardian deity statues in front. Photography isn't allowed " +
                    "inside, so it's best appreciated by taking your time walking the hall's " +
                    "length in person."
            )
            BodyText(
                "It's a short walk from **Kyoto National Museum**, itself reachable by bus or a " +
                    "roughly 20-minute walk from **Kyoto Station**."
            )

            SectionHeader("Yasaka Shrine")
            BodyText(
                "Sitting at the entrance to **Gion**, this vermilion shrine is the historical " +
                    "host of the **Gion Matsuri**, one of Japan's most famous festivals, held " +
                    "every July. Its main gate faces directly onto **Shijo-dori**, making it a " +
                    "natural stop when walking between **Gion** and **Higashiyama**."
            )
            BodyText(
                "Reached the same way as **Gion** - a short walk from **Gion-Shijo Station** " +
                    "(Keihan Main Line)."
            )

            SectionHeader("Maruyama Park")
            BodyText(
                "**Kyoto**'s oldest public park, immediately behind **Yasaka Shrine**, centered " +
                    "on a large, iconic weeping cherry tree that's illuminated at night during " +
                    "cherry blossom season and draws huge hanami (flower-viewing) picnic crowds " +
                    "each spring. Even outside cherry blossom season, it's a pleasant, quieter " +
                    "green space to rest after walking **Gion** and **Higashiyama**."
            )
            BodyText(
                "Directly adjoining **Yasaka Shrine**, reached the same way via **Gion-Shijo " +
                    "Station**."
            )
            Spacer(Modifier.height(16.dp))
        }
    }
}
