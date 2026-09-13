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
fun JeonjuGlutenFreeKetoScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "Gluten Free and Keto Friendly", onBack = onBack) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(24.dp)
        ) {
            BodyText("Documented gluten-free/keto options in Jeonju are limited, so treat the names below as a starting point and confirm directly with staff.")

            SectionHeader("Gluten Free")
            BodyText(boldNames("Gyodong Sipwonppang, at the Jeonju Hanok Village branch, is a fully gluten-free bakery making its pancakes and waffles from rice flour rather than wheat. Haeyuldam is a Korean BBQ restaurant in Jeonju reported to be gluten-free friendly.", listOf("Gyodong Sipwonppang", "Jeonju Hanok Village", "Haeyuldam")))

            SectionHeader("Keto Friendly")
            BodyText(boldNames("Jeonju Bibimbap and Korean BBQ are both naturally adaptable — order bibimbap eaten around the rice, or grilled meat without rice or noodles, and ask whether the gochujang or soy-sauce marinades used are wheat-based if you need to avoid gluten as well.", listOf("Jeonju Bibimbap", "gochujang")))
        }
    }
}
