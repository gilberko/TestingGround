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
fun SmallTalkScreen() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        STSectionHeader("Getting to Know Someone")
        STBodyText("What's your name? — شو اسمك؟ — shu ismak? (to a man) / shu ismek? (to a woman)")
        STBodyText("My name is... — اسمي... — ismi...  (e.g. اسمي سارة = ismi Sara)")
        Spacer(modifier = Modifier.height(8.dp))
        STBodyText("Where are you from? — من وين انت؟ — min wein inta? (to a man)")
        STBodyText("  to a woman: من وين انتي؟ — min wein inti?")
        STBodyText("I'm from... — أنا من... — ana min...  (e.g. أنا من إسرائيل = ana min Isra2il)")
        Spacer(modifier = Modifier.height(8.dp))
        STBodyText("Do you speak Arabic? — بتحكي عربي؟ — bti7ki 3arabi?  (same form to m and f in Palestinian)")
        STBodyText("I don't understand — ما بفهم — ma bfaham")
        STBodyText("I don't understand Arabic — ما بفهم عربي — ma bfaham 3arabi")
        STBodyText("  Useful follow-up: بفهم شوي — bfaham shwai — I understand a little")
        Spacer(modifier = Modifier.height(8.dp))
        STBodyText("How old are you? — قدّيش عمرك؟ — 2addeish 3umrak? (to a man) / 2addeish 3umrik? (to a woman)")
        STBodyText("  Lebanese: قدّيه عمرك؟ — 2addeh 3umrak / 3umrek?  ·  also: كم عمرك؟ — kam 3umrak? / kam 3umrik?")
        STBodyText("  Reply: عمري ٣٠ سنة — 3umri tlaatiin sane — I'm 30 (lit. \"my age is 30 years\")")
        Spacer(modifier = Modifier.height(8.dp))
        STBodyText("Are you a student? — إنت طالب؟ — inta Taalib? (to a man)")
        STBodyText("  to a woman: إنتِ طالبة؟ — inti Taalbe?")
        STBodyText("What do you study? — شو بتدرس؟ — shu btidros? (to a man) / shu btidrosi? (to a woman)")
        STBodyText("  Reply: أنا بدرس هندسة — ana badros handase — I study engineering")

        STSectionHeader("Family")
        STBodyText("Where are your parents? — **وين أهلك؟** — **wein ahlak?** (to a man) / **wein ahlik?**, Leb. **wein ahlek?** (to a woman)")
        STBodyText("  أهل (ahl) = family, and very often \"parents\" — ahli = my parents / my family.")
        STBodyText("  More literally: وين إمّك وأبوك؟ — wein immak w-abuuk? (Lebanese: wein emmak w bayyak?)")
        STBodyText("  Reply: أهلي بالبيت — ahli bil-beit — My parents are at home")
        Spacer(modifier = Modifier.height(8.dp))
        STBodyText("Do you have brothers and sisters? — عندك إخوة؟ — 3indak ikhwe? (to a man) / 3indik ikhwe? (to a woman)")
        STBodyText("  Reply: عندي أخ وأخت — 3indi akh w ukht — I have a brother and a sister")

        STSectionHeader("Work")
        STBodyText("شو بتشتغل؟ — shu btishtghil? — What do you do (for work)? (to a man)")
        STBodyText("شو بتشتغلي؟ — shu btishtghili? — What do you do? (to a woman)")
        STBodyText("شو شغلك؟ — shu shughlak? / shughlek? — What's your job? (m / f)")
        STBodyText("أنا بشتغل معلّم — ana bishtghil mu3allim — I work as a teacher")
        STBodyText("أنا طالب / طالبة — ana taalib / taalbe — I'm a student (m / f)")

        STSectionHeader("How's Your Day")
        STBodyText("What did you do today? — شو عملت اليوم؟ — shu 3milt el-yoom? (to a man) / shu 3milti el-yoom? (to a woman)")
        STBodyText("  Lebanese: shu 3mélt el-yoom? / shu 3mélte?")
        STBodyText("  Reply: ولا شي — wala shi — nothing  ·  رحت عالشغل — ru7t 3ash-shughl — I went to work")
        Spacer(modifier = Modifier.height(8.dp))
        STBodyText("What are you doing? — شو عم تعمل؟ — shu 3am ti3mal? (to a man) / shu 3am ti3mali? (to a woman)")
        STBodyText("  Palestinian also: شو بتعمل؟ — shu bti3mal?  ·  Lebanese: shu 3am ta3mel? / shu 3am ta3mle?")
        STBodyText("  Reply: ولا شي، مرتاح — wala shi, mirtaa7 — nothing, just relaxing (m; f: mirtaa7a)")

        STSectionHeader("At the Shop and the Café")
        STBodyText("بكم هاد — bi-kam had — How much is this?")
        STBodyText("بكم — bi-kam — How much?")
        STBodyText("قدّيش حقّه؟ — 2addeish 7a22o? — How much does it cost? (masculine item)")
        STBodyText("قدّيش حقّها؟ — 2addeish 7a22ha? — How much does it cost? (feminine item)")
        STBodyText("  (literally \"how much is its due\" — 7a22 = right / due / price; very Palestinian)")
        STBodyText("شو سعره؟ — shu si3ro? — What's its price? (masculine item)")
        STBodyText("شو سعرها؟ — shu si3rha? — What's its price? (feminine item)")
        STBodyText("غالي — ghali — expensive")
        STBodyText("رخيص — rkhis — cheap / inexpensive")
        STBodyText("هاد غالي كتير — had ghali ktir — That's very expensive")
        STBodyText("هاد رخيص — had rkhis — That's cheap")
        Spacer(modifier = Modifier.height(8.dp))
        STBodyText("شو بدك — shu biddak — What do you want? (to a man)")
        STBodyText("شو بدك — shu biddik — What do you want? (to a woman)")
        STBodyText("بدي اطلب — biddi utlub — I would like to order")
        STBodyText("وين بدفع — wein badfa3 — Where can I pay?")
        STBodyText("بقدر ادفع ببطاقة — bi2dur adfa3 b-bit2a — Can I pay with card?")
        STBodyText("ما معي كاش — ma ma3i kash — I don't have cash")
        STBodyText("كاش — kash — cash")
        STBodyText("الحساب من فضلك — el 7isab min fadlak — The bill please (to a man)")
        Spacer(modifier = Modifier.height(8.dp))
        STBodyText("Do you have change? — معك فراطة؟ — ma3ak fraaTa? (to a man) / ma3ik fraaTa? (to a woman)")
        STBodyText("  فراطة (fraaTa) = small change, coins.  I don't have change: ما معي فراطة — ma ma3i fraaTa")
        STBodyText("Do you have the exact amount? — معك المبلغ مزبوط؟ — ma3ak el-mablagh mazbuuT? (to a woman: ma3ik)")
        STBodyText("  (not confirmed by a source; mazbuuT = exact / correct is standard Levantine)")
        STBodyText("Keep the change — خلّي الباقي — khalli el-baa2i")

        STSectionHeader("Wrapping Up")
        STBodyText("See you soon — **بشوفك قريبا** — **bshuufak 2ariiban** (to a man) / **bshuufik**, Leb. **bshuufek 2ariiban** (to a woman)")
        STBodyText("  Also: منشوفك — mnshuufak — \"we'll see you\" (Lebanese / Syrian).")
        STBodyText("  Replies and other goodbyes: see Politeness and Greetings.")

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun STSectionHeader(text: String) {
    Spacer(modifier = Modifier.height(20.dp))
    Text(
        text = fixArabizi(text),
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.fillMaxWidth()
    )
    Spacer(modifier = Modifier.height(8.dp))
    HorizontalDivider()
    Spacer(modifier = Modifier.height(8.dp))
}

@Composable
private fun STBodyText(text: String) {
    Text(
        text = richText(if (text.startsWith(" ")) boldTermLine(text) else boldHeadwordLine(text)),
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(vertical = 2.dp)
    )
}
