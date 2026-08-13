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
fun SpecialAnimalPlacesScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "Special Places with Animals", onBack = onBack) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            SectionHeader("Snow Monkey Hot Springs (Jigokudani Monkey Park)")
            BodyText(
                "In **Nagano Prefecture** - not **Tokyo**, **Osaka**, or **Kyoto** - wild Japanese macaques " +
                    "(\"snow monkeys\") have been bathing in a natural hot spring here since " +
                    "1964. Winter gives the best (though not guaranteed) chance of seeing them " +
                    "soak. It's a roughly 3-hour day trip from **Tokyo**: take the Shinkansen to " +
                    "**Nagano**, then a bus to the park. Online ticketing is recommended, especially " +
                    "for busy winter dates."
            )

            SectionHeader("Wild monkey park in Kyoto (Iwatayama Monkey Park)")
            BodyText(
                "Right in **Arashiyama**, **Kyoto** - about 120 wild Japanese macaques roam freely on a " +
                    "mountain. A roughly 20-minute uphill walk (120 steps) leads to a rest-area " +
                    "hut with panoramic views over **Kyoto**, where you can feed the monkeys through a " +
                    "feeding board (direct hand-feeding and touching are not allowed - these are " +
                    "wild animals, not tame ones). ¥800 for adults, ¥400 for children (4-15), cash " +
                    "only at the gate; open 9:00am-4:30pm, last entry 4:00pm, closed on some " +
                    "holidays and in heavy rain or snow. There's no hot-spring bathing here, " +
                    "unlike **Jigokudani**, but it's far more convenient since it's already in **Kyoto** " +
                    "rather than a 3-hour trip to **Nagano**."
            )

            SectionHeader("Cat Island")
            BodyText(
                "**Tashirojima**, in **Miyagi Prefecture**, is the more accessible of Japan's two " +
                    "\"cat islands\" - free-roaming cats outnumber the human residents, and " +
                    "there's a small shrine dedicated to cats. Getting there from **Tokyo** takes " +
                    "about half a day: Shinkansen to **Sendai**, a local train to **Ishinomaki**, then a " +
                    "45-60 minute ferry. **Aoshima**, in **Ehime Prefecture**, is a quieter alternative " +
                    "with only a handful of residents and very few facilities - harder to visit " +
                    "but less touristy."
            )

            SectionHeader("Zoos")
            BodyText(
                "**Ueno Zoo** (**Tokyo**) is Japan's oldest zoo - note that as of early 2026 it no " +
                    "longer has giant pandas, since its last two were returned to China in " +
                    "January 2026. **Osaka** has **Tennoji Zoo**, and **Kyoto** has the compact, family-" +
                    "friendly **Kyoto City Zoo**."
            )

            SectionHeader("Nara's deer")
            BodyText(
                "Also worth remembering: Nara Park is home to around 1,200 free-roaming sacred " +
                    "deer that will bow for crackers - see the Nara section under Places for full " +
                    "details."
            )
            Spacer(Modifier.height(16.dp))
        }
    }
}
