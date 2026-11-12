package com.example.arabicapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun EverySomeScreen() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        ESNote("How to say every, some, no, always, never… in spoken Levantine Arabic. Almost everything is built from a few little words:\n" +
            "• كل kull = every / all\n" +
            "• شي shi = some / something (Palestinian also إشي ishi for 'something')\n" +
            "• حدا 7ada = someone\n" +
            "• ما ma / ولا wala = not / not even one\n" +
            "Combine them with person / thing / place / time and you get the whole family.")

        ESSectionHeader("1. Every / Each / All — كل kull")
        ESBody("kull + a noun WITHOUT el- = every / each.  kull + a noun WITH el- = all / the whole.")
        ESBody("Exactly like Hebrew: כל יום (every day) vs כל היום (the whole day).")
        ESWord("every day — كل يوم — kull yoom")
        ESExample("kull yoom bashrab 2ahwe — كل يوم بشرب قهوة — Every day I drink coffee.")
        ESWord("the whole day / all day — كل اليوم — kull el-yoom")
        ESExample("naamet kull el-yoom — نامت كل اليوم — She slept all day.")
        ESWord("every week — كل أسبوع — kull usbuu3;  also كل جمعة — kull jum3a")
        ESWord("every month / every year — كل شهر — kull shahr  ·  كل سنة — kull sane")
        ESWord("every dog — كل كلب — kull kalb")
        ESExample("mish kull kalb bi3ad — مش كل كلب بيعض — Not every dog bites.")
        ESWord("every cat — كل بسّة — kull bisse  (Palestinian also قطّة — 2utta)")
        ESWord("all the dogs / all the cats — كل الكلاب — kull el-klaab  ·  كل البسس — kull el-bisas")
        ESWord("every time — كل مرّة — kull marra")
        ESExample("kull marra baji, inta mish hon — كل مرّة بجي، إنت مش هون — Every time I come, you're not here.")
        ESSmall("kull doesn't change for m / f or singular / plural. It always comes BEFORE the noun.")

        ESSectionHeader("2. People — someone, everyone, no one")
        ESWord("someone / somebody — حدا — 7ada")
        ESExample("7ada da22 el-baab — حدا دقّ الباب — Someone knocked on the door.")
        ESExample("fi 7ada hon? — في حدا هون؟ — Is anyone here?")
        ESWord("everyone / everybody — كل الناس — kull en-naas;  الكل — el-kull")
        ESExample("kull en-naas bi3rfu — كل الناس بيعرفو — Everybody knows.  ·  el-kull ija — الكل إجا — Everyone came.")
        ESWord("each one / everyone (individually) — كل واحد — kull wa7ad (m) / كل وحدة — kull wa7de (f)")
        ESExample("kull wa7ad biddo yi7ki — كل واحد بدّه يحكي — Everyone wants to talk.")
        ESWord("no one / nobody — ما حدا — ma 7ada;  ولا حدا — wala 7ada (stronger: 'not a single one')")
        ESExample("ma 7ada ija — ما حدا إجا — Nobody came.  ·  ma shuft wala 7ada — ما شفت ولا حدا — I didn't see anyone at all.")
        ESSmall("'everyone' and 'everybody' are the same in Arabic: kull en-naas / el-kull. 'no one' and 'nobody' are also the same: ma 7ada.")

        ESSectionHeader("3. Things — something, everything, nothing")
        ESWord("something — إشي — ishi (Palestinian)  /  شي — shi (Lebanese/Syrian)")
        ESExample("biddi a7kiilak ishi — بدّي أحكيلك إشي — I want to tell you something.  ·  Lebanese: baddi 2ellak shi")
        ESWord("everything — كل إشي — kull ishi (Palestinian)  /  كل شي — kull shi (Lebanese/Syrian)")
        ESExample("kull ishi tamaam — كل إشي تمام — Everything is fine.")
        ESWord("nothing — ولا إشي — wala ishi  /  ولا شي — wala shi;  also ما في إشي — ma fi ishi (there's nothing)")
        ESExample("ma fhimt wala shi — ما فهمت ولا شي — I didn't understand anything.  ·  shu fi? — ma fi ishi — What's up? — Nothing.")
        ESWord("some ___ / a ___ (any) — شي + noun — shi + noun")
        ESExample("shi ktaab — شي كتاب — some book  ·  shi marra — شي مرّة — some time / once  ·  shi yoom — شي يوم — some day")
        ESSmall("Before a noun everyone says shi (also in Palestinian). ishi is used on its own for 'something / a thing'.")

        ESSectionHeader("4. Places — somewhere, everywhere, nowhere")
        ESWord("somewhere — شي مكان — shi makaan;  شي مطرح — shi matra7 (matra7 = place, very Levantine)")
        ESExample("7atteet el-mafaatii7 b-shi makaan — حطّيت المفاتيح بشي مكان — I put the keys somewhere.")
        ESWord("everywhere — بكل مكان — b-kull makaan;  بكل مطرح — b-kull matra7")
        ESExample("dawwart b-kull makaan — دوّرت بكل مكان — I looked everywhere.")
        ESWord("nowhere — ولا مكان — wala makaan  (with ma on the verb)")
        ESExample("ma ru7t wala makaan — ما رحت ولا مكان — I didn't go anywhere / I went nowhere.")
        ESSmall("Arabic has no single word for 'nowhere': you say 'I didn't go to any place'. Same in Hebrew: לא הלכתי לשום מקום.")

        ESSectionHeader("5. Time — sometimes, some day, always, never")
        ESWord("sometimes — أحياناً — a7yaanan;  مرّات — marraat (more everyday)")
        ESExample("marraat bashtghil min el-beit — مرّات بشتغل من البيت — Sometimes I work from home.")
        ESWord("some day / one day — شي يوم — shi yoom;  يوم من الأيّام — yoom min el-ayyaam ('one of these days')")
        ESExample("shi yoom ra7 ashtiri beit — شي يوم رح أشتري بيت — Some day I'm going to buy a house.")
        ESExample("yoom min el-ayyaam ra7 aruu7 3a-Beiruut — يوم من الأيّام رح أروح عبيروت — One day I'll go to Beirut.")
        ESWord("always — دايماً — daayman")
        ESWord("every time — كل مرّة — kull marra")
        ESWord("never — أبداً — abadan  (with ma + verb)")
        ESExample("ma bashrab 2ahwe abadan — ما بشرب قهوة أبداً — I never drink coffee.")
        ESWord("never (in my life) — عمري ما — 3umri ma…")
        ESExample("3umri ma rkibt tayyaara — عمري ما ركبت طيّارة — I've never been on a plane.")
        ESWord("not once — ولا مرّة — wala marra")
        ESExample("ma ija wala marra — ما إجا ولا مرّة — He didn't come even once.")

        ESSectionHeader("6. Double Negation — Just Like Hebrew")
        ESBody("Words like 7ada, ishi, wala, abadan need ma (or mish) on the verb to become 'no one / nothing / never'. Hebrew works the same: לא ראיתי אף אחד (not 'I saw no one').")
        ESExample("ma shuft 7ada — ما شفت حدا — I didn't see anyone (= לא ראיתי אף אחד)")
        ESExample("ma 3indi ishi — ما عندي إشي — I don't have anything (= אין לי כלום)")
        ESExample("ma ru7t wala makaan — ما رحت ولا مكان — I didn't go anywhere (= לא הלכתי לשום מקום)")
        ESSmall("Short one-word answers can drop the verb: 'Who came?' — wala 7ada (no one). 'What do you want?' — wala ishi (nothing).")

        ESSectionHeader("Quick Reference")
        ESBody("• Person: someone 7ada · everyone kull en-naas / el-kull · no one ma 7ada / wala 7ada")
        ESBody("• Thing: something ishi / shi · everything kull ishi / kull shi · nothing wala ishi / wala shi")
        ESBody("• Place: somewhere shi makaan · everywhere b-kull makaan · nowhere wala makaan (+ ma)")
        ESBody("• Time: sometimes a7yaanan / marraat · some day shi yoom · always daayman · every time kull marra · never abadan / 3umri ma")
        ESBody("• Every: kull + noun (kull yoom)  ·  All / whole: kull + el-noun (kull el-yoom)")

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun ESSectionHeader(text: String) {
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
private fun ESWord(text: String) {
    Text(
        text = fixArabizi(text),
        style = MaterialTheme.typography.bodyMedium,
        fontWeight = FontWeight.SemiBold,
        modifier = Modifier.padding(top = 6.dp, bottom = 2.dp)
    )
}

@Composable
private fun ESBody(text: String) {
    Text(
        text = fixArabizi(text),
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(vertical = 2.dp)
    )
}

@Composable
private fun ESExample(text: String) {
    Text(
        text = fixArabizi("    e.g. $text"),
        style = MaterialTheme.typography.bodyMedium,
        fontStyle = FontStyle.Italic,
        modifier = Modifier.padding(vertical = 1.dp)
    )
}

@Composable
private fun ESSmall(text: String) {
    Text(
        text = fixArabizi("    $text"),
        style = MaterialTheme.typography.bodySmall,
        modifier = Modifier.padding(vertical = 2.dp)
    )
}

@Composable
private fun ESNote(text: String) {
    Spacer(modifier = Modifier.height(8.dp))
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer)
    ) {
        Text(
            text = fixArabizi(text),
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(12.dp)
        )
    }
    Spacer(modifier = Modifier.height(4.dp))
}
