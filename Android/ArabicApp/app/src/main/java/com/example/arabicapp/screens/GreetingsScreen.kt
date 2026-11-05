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
fun GreetingsScreen() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        GrSectionHeader("Terms of Address — يا (ya) and Endearments")
        GrBodyText("يا — ya — vocative particle (\"O\" / \"hey\")")
        GrBodyText("  Used before a name or description when addressing someone directly.")
        GrBodyText("  يا سارة (ya Sara) = \"Hey Sara!\"")
        Spacer(modifier = Modifier.height(8.dp))
        GrBodyText("يا جميل — ya jamil — \"hey handsome / hey beautiful\" (said to a man)")
        GrBodyText("يا جميلة — ya jamile — same, said to a woman")
        GrBodyText("  جميل (jamil) = beautiful, handsome.  يا جميل is a warm, affectionate greeting.")
        Spacer(modifier = Modifier.height(8.dp))
        GrBodyText("حبيبي — 7abibi — \"my love / my dear\" (said to a man or close friend)")
        GrBodyText("حبيبتي — 7abibti — same, said to a woman")
        GrBodyText("  Extremely common in Levantine Arabic — used between friends, family, even strangers in a warm context.")
        Spacer(modifier = Modifier.height(8.dp))
        GrBodyText("يا حبيبي — ya 7abibi — \"oh my dear\" — adds warmth or emphasis")

        GrSectionHeader("Festive and Special Occasion Greetings")
        GrBodyText("سنة حلوة يا جميل — sana 7ilwe ya jamil — Happy New Year, beautiful one")
        GrBodyText("  سنة (sana) = year, حلوة (7ilwe) = sweet/nice, يا جميل = hey beautiful")
        GrBodyText("  (Said to men; يا جميلة (ya jamile) to women.  Some also say سنة حلوة يا جماعة (sane 7ilwe ya jama3a) to a group.)")
        Spacer(modifier = Modifier.height(8.dp))
        GrBodyText("عيد مبارك — 3eid mubarak — Blessed holiday / Happy Eid")
        GrBodyText("  Said for both Eid al-Fitr (end of Ramadan) and Eid al-Adha.")
        GrBodyText("  Response: الله يبارك فيك (Allah yibarak fik = May God bless you)")
        Spacer(modifier = Modifier.height(8.dp))
        GrBodyText("رمضان كريم — Ramadan karim — Generous Ramadan (Happy Ramadan)")
        GrBodyText("مبروك — mabruk — Congratulations!")
        GrBodyText("  Response: الله يبارك فيك (Allah yibarak fik)")

        GrSectionHeader("Morning Greetings")
        GrBodyText("صباح الخير — saba7 el khir — Good morning  (literally: morning of goodness)")
        GrBodyText("  Response: صباح النور (saba7 el nur = morning of light)")
        Spacer(modifier = Modifier.height(8.dp))
        GrBodyText("صباح النور — saba7 el nur — Response to good morning  (morning of light)")
        GrBodyText("  You can also respond: صباح الورد (saba7 el ward = morning of roses) — very warm")

        GrSectionHeader("Evening and Night Greetings")
        GrBodyText("مساء الخير — masa el khir — Good evening  (evening of goodness)")
        GrBodyText("  Response: مساء النور (masa el nur = evening of light)")
        Spacer(modifier = Modifier.height(8.dp))
        GrBodyText("مساء النور — masa el nur — Response to good evening")
        Spacer(modifier = Modifier.height(8.dp))
        GrBodyText("تصبح على خير — tisba7 3ala khir — Good night  (said to a man, literally: may you wake up in goodness)")
        GrBodyText("تصبحي على خير — tisba7i 3ala khir — Good night  (said to a woman)")
        GrBodyText("  Response: وانت من أهل الخير (w-inta min ahl el khir = and you too)")

        GrSectionHeader("General Greetings")
        GrBodyText("أهلاً — ahlan — Hi / Welcome")
        GrBodyText("أهلاً وسهلاً — ahlan w-sahlan — Welcome! (fuller form)")
        GrBodyText("هلا — hala — Hi (informal, very common in Levantine)")
        GrBodyText("مرحبا — mar7aba — Hello")
        GrBodyText("  Response: مرحبتين (mar7abtein = double welcome)")
        Spacer(modifier = Modifier.height(8.dp))
        GrBodyText("كيفك — kifak — How are you? (to a man)")
        GrBodyText("كيفك — kifik — How are you? (to a woman)")
        GrBodyText("شو أخبارك — shu akhbarak — What's your news? (= how are you, to a man)")
        GrBodyText("شو أخباركي — shu akhbaraki — Same, to a woman")

        GrSectionHeader("Goodbye and Introductions")
        GrBodyText("Goodbye / Bye — مع السلامة — ma3 es-salame  (lit: with safety; the standard warm farewell)")
        GrBodyText("  Casual: يلا باي — yalla bye  (very common in everyday speech)")
        GrBodyText("See you — بشوفك — bshufak (to a man) / bshufek (to a woman)")
        GrBodyText("  Also: يلا — yalla — casual farewell / let's go")
        Spacer(modifier = Modifier.height(8.dp))
        GrBodyText("What's your name? — شو اسمك؟ — shu ismak? (to a man) / shu ismek? (to a woman)")
        GrBodyText("My name is... — اسمي... — ismi...  (e.g. اسمي سارة = ismi Sara)")
        Spacer(modifier = Modifier.height(8.dp))
        GrBodyText("Where are you from? — من وين انت؟ — min wein inta? (to a man)")
        GrBodyText("  to a woman: من وين انتي؟ — min wein inti?")
        GrBodyText("I'm from... — أنا من... — ana min...  (e.g. أنا من إسرائيل = ana min Isra2il)")
        Spacer(modifier = Modifier.height(8.dp))
        GrBodyText("Do you speak Arabic? — بتحكي عربي؟ — bti7ki 3arabi?  (same form to m and f in Palestinian)")
        GrBodyText("I don't understand — ما بفهم — ma bfaham")
        GrBodyText("I don't understand Arabic — ما بفهم عربي — ma bfaham 3arabi")
        GrBodyText("  Useful follow-up: بفهم شوي — bfaham shwai — I understand a little")

        GrSectionHeader("Times of Day")
        GrBodyText("صباح — saba7 — morning")
        GrBodyText("ضهر — duhr — noon / midday")
        GrBodyText("بعد الضهر — ba3d el duhr — afternoon")
        GrBodyText("مسا — masa — evening")
        GrBodyText("ليل — leil — night")
        GrBodyText("نص الليل — nuss el leil — midnight")
        GrBodyText("نص النهار — nuss el nahar — midday / noon")

        GrSectionHeader("Asking How Someone Is")
        GrBodyText("كيفك — kifak / kifik — How are you? (m/f)")
        GrBodyText("كيف حالك — kif 7alak / kif 7alik — How are you? (m/f, slightly more formal)")
        GrBodyText("كيف تحس — kif tu7iss — How do you feel?")
        Spacer(modifier = Modifier.height(8.dp))
        GrBodyText("منيح — mni7 — I'm fine / good (m)")
        GrBodyText("منيحة — mni7a — I'm fine (f)")
        GrBodyText("الحمد لله — el 7amdu lillah — Praise God / I'm fine, thank God")
        GrBodyText("  (The most common response to \"how are you?\" — said regardless of how you actually feel)")
        GrBodyText("شكراً على السؤال — shukran 3ala is-su2aal — Thanks for asking")
        Spacer(modifier = Modifier.height(8.dp))
        GrBodyText("مش كتير منيح — mish ktir mni7 — Not too good")
        GrBodyText("تعبان — ta3ban — I'm tired / I don't feel well (m)")
        GrBodyText("تعبانة — ta3bane — I don't feel well (f)")
        GrBodyText("مش بخير — mish b-khir — Not well")

        GrSectionHeader("Polite Expressions")
        GrBodyText("لو سمحت — law sama7t — Please / Excuse me (to a man)")
        GrBodyText("لو سمحتي — law sama7t — Please / Excuse me (to a woman)")
        GrBodyText("عفواً — 3afwan — You're welcome / Pardon me / Excuse me")
        GrBodyText("آسف — asif — Sorry (m)")
        GrBodyText("آسفة — asife — Sorry (f)")
        GrBodyText("مش مشكلة — mish mushkile — No worries / No problem")
        GrBodyText("ماشي — mashi — Sure / Okay / Alright")
        GrBodyText("تمام — tamam — Perfect / Okay / Great")
        GrBodyText("أكيد — akid — Of course / Sure / Definitely")

        GrSectionHeader("Yes, No, and Affirmations")
        GrBodyText("آه — ah — Yes")
        GrBodyText("لا — la — No")
        GrBodyText("أكيد — akid — Of course / Definitely")
        GrBodyText("طبعاً — tab3an — Of course / Naturally")
        GrBodyText("بالمناسبة — bil-munasabe — By the way")
        GrBodyText("يعني — ya3ni — I mean / like / sort of  (extremely common filler word)")

        GrSectionHeader("Time References")
        GrBodyText("هلق — halla2 — now (Levantine)")
        GrBodyText("اليوم — el yom — today")
        GrBodyText("مبارح — mbari7 — yesterday")
        GrBodyText("بكرا — bukra — tomorrow")

        GrSectionHeader("Shopping Phrases")
        GrBodyText("بكم هاد — bi-kam had — How much is this?")
        GrBodyText("بكم — bi-kam — How much?")
        GrBodyText("غالي — ghali — expensive")
        GrBodyText("رخيص — rkhis — cheap / inexpensive")
        GrBodyText("هاد غالي كتير — had ghali ktir — That's very expensive")
        GrBodyText("هاد رخيص — had rkhis — That's cheap")

        GrSectionHeader("Reactions and Exclamations")
        GrBodyText("يسلموا — yislamu — Amazing / Thank you (literally: may your hands be blessed)")
        GrBodyText("  Said when someone does something kind or gives you something")
        GrBodyText("رائع — ra2e3 — Awesome / Wonderful")
        GrBodyText("ممتاز — mumtaz — Excellent / Great")
        GrBodyText("هيك — heik — Like this / that's it / great (Levantine)")
        GrBodyText("هلق! — halla2! — Wow! / Now! (depending on context)")
        GrBodyText("يا إلهي — ya ilahi — Oh my God!")
        GrBodyText("وللا — walla — Really! / I swear! (very common)")
        GrBodyText("مش معقول — mish ma32ul — Unbelievable! / No way!")

        GrSectionHeader("Ordering and Paying")
        GrBodyText("شو بدك — shu biddak — What do you want? (to a man)")
        GrBodyText("شو بدك — shu biddik — What do you want? (to a woman)")
        GrBodyText("بدي اطلب — biddi utlub — I would like to order")
        GrBodyText("وين بدفع — wein badfa3 — Where can I pay?")
        GrBodyText("بقدر ادفع ببطاقة — bi2dur adfa3 b-bit2a — Can I pay with card?")
        GrBodyText("ما معي كاش — ma ma3i kash — I don't have cash")
        GrBodyText("كاش — kash — cash")
        GrBodyText("الحساب من فضلك — el 7isab min fadlak — The bill please (to a man)")

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun GrSectionHeader(text: String) {
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
private fun GrBodyText(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(vertical = 2.dp)
    )
}
