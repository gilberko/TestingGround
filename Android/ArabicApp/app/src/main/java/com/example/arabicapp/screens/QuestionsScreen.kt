package com.example.arabicapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun QuestionsScreen() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        QSectionHeader("How Questions Work in Palestinian Arabic")
        QBodyText("Palestinian Arabic forms questions in two main ways:")
        Spacer(modifier = Modifier.height(4.dp))
        QBodyText("1. Yes/No questions — same word order as a statement, but with a rising intonation at the end.")
        QBodyText("   • btishrab 'ahwe? — بتشرب قهوة؟ — Do you drink coffee?")
        QBodyText("   • huwwe hawn? — هو هون؟ — Is he here?")
        Spacer(modifier = Modifier.height(8.dp))
        QBodyText("2. Wh-questions — use a question word (see below). The question word usually comes at the beginning of the sentence.")
        QBodyText("   • shu biddak? — شو بدّك؟ — What do you want?")
        QBodyText("   • wen raḥ? — وين راح؟ — Where did he go?")

        QSectionHeader("What — شو / إيش")
        QBodyText("شو — shu — What (the most common Palestinian form)")
        QBodyText("إيش — esh — What (also common in Palestinian and Jordanian Arabic)")
        Spacer(modifier = Modifier.height(4.dp))
        QBodyText("Both mean the same thing. شو (shu) is the dominant Levantine form.")
        Spacer(modifier = Modifier.height(8.dp))
        QBodyText("Examples:")
        QBodyText("• shu ismak? — شو اسمك؟ — What is your name?")
        QBodyText("• shu fi? — شو في؟ — What is there? / What's going on?")
        QBodyText("• shu biddak tishrab? — شو بدّك تشرب؟ — What do you want to drink?")
        QBodyText("• esh bitsawi? — إيش بتساوي؟ — What are you doing?")

        QSectionHeader("When — إيمتى")
        QBodyText("إيمتى — emta — When")
        Spacer(modifier = Modifier.height(8.dp))
        QBodyText("Examples:")
        QBodyText("• emta btiji? — إيمتى بتيجي؟ — When are you coming?")
        QBodyText("• emta il-'akel? — إيمتى الأكل؟ — When is the food?")
        QBodyText("• emta intalaqtu? — إيمتى انطلقتو؟ — When did you (pl.) leave?")

        QSectionHeader("Where — وين")
        QBodyText("وين — wen — Where")
        Spacer(modifier = Modifier.height(8.dp))
        QBodyText("Examples:")
        QBodyText("• wen il-beit? — وين البيت؟ — Where is the house?")
        QBodyText("• wen raḥ? — وين راح؟ — Where did he go?")
        QBodyText("• wen btishtaghel? — وين بتشتغل؟ — Where do you work?")

        QSectionHeader("From Where — من وين")
        QBodyText("من وين — min wen — From where / Where from")
        Spacer(modifier = Modifier.height(8.dp))
        QBodyText("Examples:")
        QBodyText("• min wen inta? — من وين إنت؟ — Where are you from?")
        QBodyText("• min wen jiitu? — من وين جيتو؟ — Where did you (pl.) come from?")
        QBodyText("• min wen hiyye? — من وين هي؟ — Where is she from?")

        QSectionHeader("To Where — على وين / وين رايح")
        QBodyText("على وين — 'ala wen — To where (direction)")
        QBodyText("وين رايح — wen rayyeḥ — Where are you going (lit. \"where going\")")
        Spacer(modifier = Modifier.height(8.dp))
        QBodyText("Examples:")
        QBodyText("• 'ala wen raḥ? — على وين راح؟ — Where did he go (to)?")
        QBodyText("• wen rayyeḥ? — وين رايح؟ — Where are you going?")
        QBodyText("• 'ala wen il-bus? — على وين الباص؟ — Where does the bus go?")

        QSectionHeader("Which — أيّ")
        QBodyText("أيّ — ayy — Which")
        Spacer(modifier = Modifier.height(8.dp))
        QBodyText("Examples:")
        QBodyText("• ayy beit? — أيّ بيت؟ — Which house?")
        QBodyText("• ayy yom? — أيّ يوم؟ — Which day?")
        QBodyText("• ayy waḥad biddak? — أيّ واحد بدّك؟ — Which one do you want?")
        Spacer(modifier = Modifier.height(4.dp))
        QBodyText("أيّ does not change for gender — it is used with both masculine and feminine nouns.")

        QSectionHeader("Who — مين")
        QBodyText("مين — min — Who")
        Spacer(modifier = Modifier.height(8.dp))
        QBodyText("Examples:")
        QBodyText("• min huwwe? — مين هو؟ — Who is he?")
        QBodyText("• min 'amal hada? — مين عمل هاد؟ — Who did this?")
        QBodyText("• min biddak tshuf? — مين بدّك تشوف؟ — Who do you want to see?")
        Spacer(modifier = Modifier.height(4.dp))
        QBodyText("Note: مين (min, who) and من (min, from) are written differently in Arabic script but sound identical. Context makes the meaning clear.")

        QSectionHeader("How — كيف")
        QBodyText("كيف — kif — How")
        Spacer(modifier = Modifier.height(8.dp))
        QBodyText("Examples:")
        QBodyText("• kif ḥalak? — كيف حالك؟ — How are you? (m)")
        QBodyText("• kif ḥalik? — كيف حالك؟ — How are you? (f)")
        QBodyText("• kif biddi 'amel hada? — كيف بدّي أعمل هاد؟ — How do I do this?")
        QBodyText("• kif wseltu? — كيف وصلتو؟ — How did you (pl.) get here?")
        Spacer(modifier = Modifier.height(4.dp))
        QBodyText("كيف حالك؟ (kif ḥalak/ḥalik) is the standard Palestinian greeting — \"how are you?\"")

        QSectionHeader("Why — ليش")
        QBodyText("ليش — lesh — Why")
        Spacer(modifier = Modifier.height(8.dp))
        QBodyText("Examples:")
        QBodyText("• lesh ma jiit? — ليش ما جيت؟ — Why didn't you come?")
        QBodyText("• lesh biddak truh? — ليش بدّك تروح؟ — Why do you want to go?")
        QBodyText("• lesh? — ليش؟ — Why? (on its own)")

        QSectionHeader("How Much / How Many — قديش / كم")
        QBodyText("قديش — addesh — How much / How many (Palestinian colloquial)")
        QBodyText("كم — kam — How many (more formal, also used in colloquial)")
        Spacer(modifier = Modifier.height(8.dp))
        QBodyText("Examples:")
        QBodyText("• addesh il-ḥisab? — قديش الحساب؟ — How much is the bill?")
        QBodyText("• addesh il-waqt? — قديش الوقت؟ — What time is it? (lit. how much time)")
        QBodyText("• kam walad 'indak? — كم ولد عندك؟ — How many children do you have?")
        Spacer(modifier = Modifier.height(4.dp))
        QBodyText("قديش (addesh) is the everyday Palestinian form. كم (kam) is also understood and used, especially before nouns.")

        QSectionHeader("Quick Reference")
        QBodyText("شو / إيش (shu / esh) — What")
        QBodyText("إيمتى (emta) — When")
        QBodyText("وين (wen) — Where")
        QBodyText("من وين (min wen) — From where")
        QBodyText("على وين (ʕala wen) — To where")
        QBodyText("أيّ (ayy) — Which")
        QBodyText("مين (min) — Who")
        QBodyText("كيف (kif) — How")
        QBodyText("ليش (lesh) — Why")
        QBodyText("قديش (addesh) — How much / How many")

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun QSectionHeader(text: String) {
    Spacer(modifier = Modifier.height(20.dp))
    Text(
        text = text,
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.fillMaxWidth()
    )
    Spacer(modifier = Modifier.height(8.dp))
    HorizontalDivider()
    Spacer(modifier = Modifier.height(8.dp))
}

@Composable
private fun QBodyText(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(vertical = 2.dp)
    )
}
