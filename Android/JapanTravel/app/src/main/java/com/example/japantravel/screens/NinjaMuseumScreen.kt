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
fun NinjaMuseumScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "Ninja Museum", onBack = onBack) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            SectionHeader("What it is")
            BodyText(
                "The Samurai & Ninja Museum Kyoto (with Experience) - a museum plus hands-on " +
                    "activity venue where you can try on samurai armor and learn basic ninja " +
                    "skills such as shuriken (throwing star) throwing, guided by staff."
            )

            SectionHeader("Prices and hours")
            BodyText(
                "A basic ticket costs roughly ¥4,400 for adults and ¥4,100 for children, with " +
                    "private/premium ticket options available at a higher price. The museum is " +
                    "open approximately 9:30am-7:00pm, though it's worth checking the official " +
                    "site for seasonal changes."
            )

            SectionHeader("Age and height rules")
            BodyText(
                "Rules vary by specific experience: some experiences require participants to be " +
                    "18 or older. Under-18s can join with parental supervision but generally need " +
                    "to be at least 150cm tall. Children under 3 cannot take part in the ninja " +
                    "experience itself, though they can still enter the venue."
            )

            SectionHeader("Tips")
            BodyText(
                "Plan for about an hour total. Groups are kept small and weekend slots fill up " +
                    "quickly, so booking in advance online is recommended over walking in."
            )
            Spacer(Modifier.height(16.dp))
        }
    }
}
