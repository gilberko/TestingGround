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
fun OsakaOverviewScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "Overview", onBack = onBack) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            SectionHeader("Umeda Sky Building")
            BodyText(
                "A striking twin-tower skyscraper in **Umeda**, north **Osaka**, connected near the top " +
                    "by the circular **Floating Garden Observatory**. You can view the city from " +
                    "behind glass or step outside onto the open-air rooftop deck for unobstructed " +
                    "360-degree views. The \"**Sky Walk**\" is a glass-floored escalator bridge " +
                    "connecting the two towers at the top - a memorable, slightly vertigo-inducing " +
                    "walk on its own. Other things to do while you're up there: attach an engraved " +
                    "\"**Heart Lock**\" padlock (popular with couples), grab a coffee at cafe **SKY 40**, " +
                    "or head to the basement, which is styled like a nostalgic early-Showa-era " +
                    "town street lined with retro eateries. It's especially popular at sunset and " +
                    "after dark for the night skyline. Open 9:30-22:30 (last admission 22:00), " +
                    "around ¥2,000 for adults."
            )

            SectionHeader("Osaka Castle")
            BodyText(
                "One of Japan's most famous castles, originally built in 1583 by Toyotoshi " +
                    "Hideyoshi. The current keep (rebuilt in concrete in 1931, most recently " +
                    "renovated in 1997) houses a museum on the castle's history and the era of " +
                    "Japanese unification, with an observation deck at the top for skyline views. " +
                    "The surrounding stone walls and moats are original and impressively massive."
            )

            SectionHeader("Osaka Castle Park")
            BodyText(
                "The large park grounds around the castle are worth a couple of hours on their " +
                    "own: wide lawns for walking, a plum grove (best in Feb-Mar) and cherry " +
                    "blossom groves (best late Mar-early Apr), **Nishinomaru Garden** (a paid inner " +
                    "garden with some of the best castle-and-blossom photo spots), and a moat " +
                    "where you can ride a rental rowboat."
            )

            SectionHeader("Dotonbori")
            BodyText(
                "**Osaka**'s most famous nightlife and street food district, centered on a canal " +
                    "lined with glowing signage - most iconically the **Glico \"Running Man\"** " +
                    "billboard, a must-photo spot. This is the place to try **Osaka**'s signature " +
                    "street food: takoyaki (octopus balls) and okonomiyaki (savory pancake), both " +
                    "believed to have originated in the **Osaka** area. Expect crowds, especially at " +
                    "night."
            )

            SectionHeader("Namba")
            BodyText(
                "**Namba** is the big entertainment and shopping district immediately south of " +
                    "**Dotonbori**, built around Namba Station - one of **Osaka**'s two major transit " +
                    "hubs (along with **Umeda** to the north) and the southern terminus of the Nankai " +
                    "line to Kansai Airport. **Namba Parks** (a terraced, garden-topped shopping mall), " +
                    "**Namba City**, and countless arcades and shops make it a full day of shopping and " +
                    "dining on its own, and it flows directly into **Dotonbori** on foot."
            )

            SectionHeader("Famous temples and shrines nearby")
            BodyText(
                "**Shitennoji Temple**, a short subway ride south of **Namba**, is one of Japan's oldest " +
                    "officially administered Buddhist temples, founded in 593 CE - notably older " +
                    "in institutional history than many temples in **Kyoto**, even though **Kyoto** gets " +
                    "more attention for temples overall. **Sumiyoshi Taisha**, a bit further south, is " +
                    "one of Japan's oldest and most important Shinto shrines, known for its " +
                    "distinctive pre-Buddhist architectural style and an iconic arched drum bridge."
            )
            Spacer(Modifier.height(16.dp))
        }
    }
}
