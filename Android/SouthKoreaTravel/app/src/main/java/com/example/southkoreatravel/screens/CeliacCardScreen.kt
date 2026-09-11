package com.example.southkoreatravel.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun CeliacCardScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "Celiac Card", onBack = onBack) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(24.dp)
        ) {
            SectionHeader("What Is a Celiac Card?")
            BodyText("A celiac (gluten-free) card is a short written explanation of your condition in the local language that you hand to restaurant staff, so there's no ambiguity from a spoken conversation in a language you don't share. It's especially useful in Korea, where wheat hides in less-obvious places — soy sauce and gochujang (red chili paste) are both usually made with wheat, and much of Korean fried food uses a wheat-flour batter, so a simple \"no bread\" explanation isn't enough.")

            SectionHeader("Example Card (Korean)")
            Card(
                modifier = Modifier.fillMaxSize(),
                colors = CardDefaults.cardColors()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "저는 글루텐에 알레르기가 있습니다. 밀, 보리, 간장, 고추장, 튀김 요리를 먹을 수 없습니다. 저를 위해 안전한 음식을 만들어 주실 수 있나요?",
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            BodyText("English translation: \"I have an allergy to gluten. I cannot eat wheat, barley, soy sauce, gochujang, or fried dishes. Could you please prepare safe food for me?\"")
            BodyText("This covers the main hidden-gluten culprits in Korean cooking (soy sauce, gochujang, and fried batter), but it's a conversation-starter, not a medical-grade guarantee — cross-contamination in a shared kitchen is still a real risk, so use your own judgment on how strict you need to be about where you eat.")

            SectionHeader("Getting a Fuller Card")
            BodyText("For a more complete, professionally translated card — covering things like cross-contamination warnings and a longer list of gluten sources — printable Korean celiac cards are available from resources like Celiac Travel (celiactravel.com) and Equal Eats (equaleats.com), either as a free printable PDF or a durable plastic card you can keep in your wallet.")
        }
    }
}
