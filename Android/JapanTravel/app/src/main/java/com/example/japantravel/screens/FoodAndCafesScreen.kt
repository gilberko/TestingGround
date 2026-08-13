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
fun FoodAndCafesScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "Food and Cafes", onBack = onBack) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            SectionHeader("Japanese food chains (nationwide)")
            BodyText(
                "Gyudon (beef bowl): **Yoshinoya**, **Sukiya**, and **Matsuya** are the \"Big 3\" - fast, " +
                    "cheap rice bowls topped with simmered beef, found at nearly every train " +
                    "station."
            )
            BodyText(
                "Ramen: **Ichiran** is known for tonkotsu (pork-bone broth) ramen served in " +
                    "individual booths for solo, no-distraction dining; **Ippudo** is another major " +
                    "tonkotsu ramen chain."
            )
            BodyText(
                "Curry: **CoCo Ichibanya** lets you customize spice level, toppings, and rice amount " +
                    "on a Japanese-style curry rice."
            )
            BodyText(
                "Conveyor-belt sushi: **Sushiro** and **Kura Sushi** are the biggest chains - plates " +
                    "circulate on a belt (or are sent directly to your table via a small track), " +
                    "usually priced per plate."
            )
            BodyText(
                "Family restaurants: **Saizeriya** (budget Italian-Japanese fusion) and **Ootoya** " +
                    "(home-style Japanese set meals) are common all-purpose options with picture " +
                    "menus."
            )
            BodyText(
                "Tempura: **Tenya** serves fast, affordable tempura rice bowls."
            )

            SectionHeader("Cafe chains")
            BodyText(
                "Yes - **% Arabica** is real and Japan-founded, originating in **Kyoto**. Its flagship " +
                    "store is in **Higashiyama**, with other **Kyoto** locations in **Arashiyama** " +
                    "(overlooking the river near **Togetsukyo Bridge**) and **Kawaramachi**. It has since " +
                    "expanded to **Tokyo**, **Osaka**, and internationally. Other well-known Japan-founded " +
                    "coffee chains include **Doutor**, **Komeda's Coffee**, and **Tully's**."
            )
            BodyText(
                "Drip vs. espresso, chain by chain: **% Arabica** is espresso-based - its whole " +
                    "identity is built around a signature latte pulled on high-end espresso " +
                    "machines, not filter coffee. **Doutor** is drip/siphon-brewed at its core (the " +
                    "classic \"**Doutor** Blend\"), but also serves espresso-based drinks like " +
                    "cappuccino and cafe au lait. **Komeda's Coffee**, a **Nagoya**-style kissaten chain, " +
                    "centers on siphon-brewed \"**Komeda** Blend\" drip coffee, with espresso drinks as " +
                    "a smaller part of the menu. **Tully's** is a full Seattle-style espresso bar " +
                    "chain (closer to **Starbucks**) - espresso-based through and through."
            )
            BodyText(
                "**Quaro**, a striking new specialty coffee space on Cat Street in **Jingumae**/**Harajuku** " +
                    "(**Shibuya**), is worth calling out separately: a four-floor concept combining " +
                    "coffee, design, art, and music, with an in-house roasting room in the " +
                    "basement. It serves both hand-drip/pour-over and espresso (on La Marzocco " +
                    "Strada X and Linea Classic S machines), and offers a reservation-only tasting " +
                    "course pairing drip, espresso, latte, and cold brew from the same beans."
            )
            BodyText(
                "More multi-branch specialty chains worth knowing: **Blue Bottle Coffee** now has " +
                    "around 28 Japan cafes, including several in **Tokyo** (**Shibuya**/**Hiroo**, " +
                    "**Nakameguro**, **Shinagawa** Station, **Shinjuku**, and more), two in **Kyoto** " +
                    "(**Higashiyama**, by **Nanzen-ji**; and **Rokkaku**, near **Karasuma Oike**), and one in " +
                    "**Osaka** (**Umeda Chayamachi**). **Higuma Doughnuts & Coffee** pairs specialty coffee " +
                    "with doughnuts, with locations in **Kyoto** (**Nakagyo**, at Rissei Garden Hulic near " +
                    "**Kawaramachi** Station) and **Tokyo** (**Omotesando**, a collaboration with **Coffee " +
                    "Wrights**). **Glitch Coffee & Roasters**, a widely praised light-roast specialty " +
                    "chain, has its flagship in **Jimbocho** (**Tokyo**), plus branches in **Ginza**, " +
                    "**Nihonbashi**, and **Akasaka**, as well as an **Osaka** location. **Lilo Coffee " +
                    "Roasters** has its flagship in **Shinsaibashi** (**Osaka**) and a **Kyoto** branch " +
                    "called \"In The Coffee\", near **Karasuma** Station."
            )

            SectionHeader("Recommended independent roasteries and cafes (not chains)")
            BodyText(
                "**Tokyo**: **Fuglen Tokyo** (Nordic-style light roasts, filter/drip-forward but also " +
                    "serves espresso drinks; a second branch, **Fuglen Asakusa**, opened in 2018) and " +
                    "**Koffee Mameya** - the original stand-only bar in **Omotesando**, plus **Koffee " +
                    "Mameya Kakeru** in **Kiyosumi-Shirakawa** (a pour-over/drip tasting-course format, " +
                    "ranked among the World's 100 Best Coffee Shops)."
            )
            BodyText(
                "More **Tokyo** picks: **Onibus Coffee**, a well-loved local roaster with branches in " +
                    "**Okusawa** (the original), **Nakameguro**, and **Jiyugaoka**; **The Roastery by Nozy " +
                    "Coffee**, on Cat Street in **Harajuku**; **Allpress Espresso Tokyo Roastery & Cafe**, " +
                    "the New Zealand roaster's first overseas cafe, in a converted warehouse in " +
                    "**Kiyosumi-Shirakawa**; **Unlimited Coffee Bar**, a two-story cafe right by **Tokyo " +
                    "Skytree** in **Sumida**; and **Bongen Coffee**, with **Ginza** and **Nihonbashi** locations."
            )
            BodyText(
                "**Osaka**: **Ult Coffee** (also ranked among the World's 100 Best, founded by 2023 World " +
                    "Barista Champion Boram Um) and **Spot Coffee**, a small-batch roastery in the " +
                    "**Nakazakinishi** neighborhood - both, like most high-end Japanese specialty " +
                    "roasteries, typically offer espresso-based drinks alongside pour-over/drip. Also " +
                    "worth seeking out: **Mill Pour**, in **Shinsaibashi**, and **Granknot Coffee**, in the " +
                    "**Yotsubashi** area near **Shinsaibashi**."
            )
            BodyText(
                "**Kyoto**: **Weekenders Coffee** (Tomikoji) is a long-standing favorite among specialty " +
                    "coffee fans, known primarily for pour-over/drip; the **% Arabica** **Arashiyama** " +
                    "location is also worth a stop for the riverside view alone, even though it's " +
                    "a chain (and espresso-based, as above). **Kurasu** has a shop near **Kyoto** Station " +
                    "and a roastery cafe a short walk from **Fushimi Inari**. **Drip & Drop Coffee " +
                    "Supply** has several **Kyoto** branches, including **Teramachi** and near **Ginkakuji**. " +
                    "**Walden Woods Kyoto**, in a renovated Taisho-era building in **Shimogyo** near " +
                    "**Gojo**, home-roasts on a Probat roaster. **Clamp Coffee Sarasa** and **Nijo Koya** " +
                    "are both tucked into quiet alleys near **Nijo Castle** in **Nakagyo**. **Wife & " +
                    "Husband**, in **Kitaoji** by the **Kamo River**, roasts its own beans in an " +
                    "antique-filled old house. **Le Labo**'s only cafe (as opposed to its perfume " +
                    "stores) is in **Kyoto**, inside a renovated machiya in **Nakagyo**. And **Here Cafe** " +
                    "has two **Kyoto** locations - one near **Nijo Castle**, another on the **Sannenzaka** " +
                    "slope by **Kiyomizu-dera** - plus a smaller outpost in **Tokyo**."
            )

            SectionHeader("Matcha")
            BodyText(
                "Matcha is finely ground, shade-grown green tea powder, whisked directly into hot " +
                    "water rather than steeped like regular tea - the basis of the traditional " +
                    "tea ceremony (see Classical Japanese Culture), but just as common in " +
                    "everyday lattes, ice cream, and sweets. Quality varies widely, from " +
                    "ceremonial-grade (smoother, made for drinking on its own) down to culinary-" +
                    "grade (more bitter, meant for baking/lattes)."
            )
            BodyText(
                "**Kyoto**/**Uji**: **Uji**, about 35 minutes south of **Kyoto** by train, is matcha's historic " +
                    "heartland. **Nakamura Tokichi** and **Tsujiri** both have flagship stores there, and " +
                    "**Tsujiri** and **Itoh Kyuemon** also run branches in central **Kyoto**, including **Gion**."
            )
            BodyText(
                "**Tokyo**: **Gion Kitagawa Hanbei** runs a specialty tea counter at the Marunouchi " +
                    "Building near Tokyo Station."
            )
            BodyText(
                "**Osaka**: **Gion Kitagawa Hanbei** also has a counter at **Takashimaya Osaka**, and the " +
                    "**Hankyu Umeda** department store carries specialty matcha as well."
            )

            SectionHeader("Celiac-friendly dining")
            BodyText(
                "**T's Kitchen** (also marketed as \"Gluten Free **T's Kitchen**\") is a 100% gluten-free " +
                    "restaurant with two **Tokyo** locations (**Roppongi** and **Ueno**), certified by the " +
                    "Gluten Intolerance Group - the first such certification in Asia. The menu " +
                    "covers Japanese comfort food (gyoza, okonomiyaki, ramen, tempura) as well as " +
                    "Western dishes, all prepared gluten-free; reservations are recommended due to " +
                    "limited seating. See the About Food section for a printable Japanese " +
                    "explanation card to use elsewhere."
            )
            Spacer(Modifier.height(16.dp))
        }
    }
}
