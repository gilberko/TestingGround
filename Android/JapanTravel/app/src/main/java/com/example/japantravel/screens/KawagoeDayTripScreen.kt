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
fun KawagoeDayTripScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "Kawagoe", onBack = onBack) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            SectionHeader("Getting there")
            BodyText(
                "The Tobu Tojo Line express runs direct from **Ikebukuro** to **Kawagoe**, about 30-31 " +
                    "minutes, roughly ¥450-490, with frequent service."
            )

            SectionHeader("What to see")
            BodyText(
                "**Kawagoe** is nicknamed \"Koedo\" (Little Edo), evoking the Edo period when samurai " +
                    "ruled Japan. The **Kurazukuri** warehouse street is the main draw - rows of " +
                    "dark-tiled Edo/Meiji-era merchant warehouses, many still in use as shops. " +
                    "**Toki no Kane**, a wooden bell tower that has marked the hours since the Edo " +
                    "period, anchors the street. **Kashiya Yokocho** (\"**Candy Alley**\") is a " +
                    "traditional-sweets shopping lane nearby. For a fuller day, add **Kitain Temple** " +
                    "and the **Honmaru Goten** palace."
            )

            SectionHeader("When to head back")
            BodyText(
                "The Tobu Tojo Line runs frequently with no reservation needed, so return timing " +
                    "is flexible - a half day (3-4 hours) comfortably covers the warehouse street, " +
                    "**Candy Alley**, and the bell tower, while a full day allows adding **Kitain Temple** " +
                    "and a sit-down lunch."
            )

            SectionHeader("How long is the train")
            BodyText(
                "About 30-31 minutes each way on the Tobu Tojo Line express from **Ikebukuro**."
            )
            Spacer(Modifier.height(16.dp))
        }
    }
}
