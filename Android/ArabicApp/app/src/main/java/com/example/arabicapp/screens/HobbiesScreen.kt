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
fun HobbiesScreen() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        HBNote("A hobby is هواية — hiwaaye (pl. هوايات — hiwaayaat). To say you like doing something, use b7ibb + the verb or the noun: b7ibb atbukh (I like to cook) or b7ibb et-tabikh (I like cooking). Palestinian first; Lebanese/Syrian variants where they differ.")
        HBWord("What are your hobbies? — شو هواياتك؟ — shu hiwaayaatak?  (to a woman: hiwaayaatik)")

        HBSectionHeader("Sports and Outdoors")
        HBWord("golf — غولف — golf;  to play golf: لعب غولف — li3ib golf / yil3ab golf")
        HBWord("hiking — مشي بالطبيعة — mashi bit-tabii3a  (lit. walking in nature);  also هايكنج — haaiking (loanword, common in Lebanon)")
        HBWord("to hike / walk — مشي — mishi / yimshi")
        HBWord("traveling — سفر — safar")
        HBWord("to travel — سافر — saafar / ysaafir")
        HBWord("driving — سواقة — swaa2a")
        HBWord("to drive — ساق — saa2 / ysuu2")
        HBExample("b7ibb asaafir 3ala blaad jdiide — بحب أسافر على بلاد جديدة — I love traveling to new countries.")

        HBSectionHeader("Music")
        HBWord("playing an instrument — العزف على آلة موسيقية — el-3azf 3ala aale muusii2iyye")
        HBWord("to play (an instrument) — عزف على — 3azaf 3ala / yi3zif 3ala")
        HBSmall("Instruments are 'played' with 3azaf (or casually da22 3ala for drums), not li3ib, which is for games and sports.")
        HBWord("playing the guitar — بعزف جيتار — ba3zif gitaar  (gitaar — جيتار)")
        HBWord("playing the piano — بعزف بيانو — ba3zif biyaano  (biyaano — بيانو)")
        HBWord("playing the violin — بعزف كمنجة — ba3zif kamanje  (kamanje — كمنجة)")
        HBWord("playing the drums — بدق طبل — bdu22 tabl;  drum kit: درامز — draamz")
        HBWord("oud — عود — 3uud  (the classic Arab lute)")
        HBWord("singing — غنا — ghana")
        HBWord("to sing — غنّى — ghanna / yghanni")
        HBWord("song — أغنية — ughniye")
        HBInfo("f · 2: أغنيتين ughniytein · pl: أغاني aghaani")
        HBWord("songwriting — كتابة أغاني — ktaabet aghaani;  he writes songs: بيكتب أغاني — biyiktib aghaani")
        HBExample("bti3zif 3ala shi aale? — بتعزف على شي آلة؟ — Do you play any instrument?")

        HBSectionHeader("Art and Crafts")
        HBWord("drawing / painting — رسم — rasm")
        HBWord("to draw / to paint (a picture) — رسم — rasam / yirsum")
        HBSmall("rasam covers both drawing and painting a picture. Painting a wall is a different verb: دهن — dahan / yidhan.")
        HBWord("painting with colors — رسم بالألوان — rasm bil-alwaan")
        HBWord("knitting — صنارة — sinnaara  (lit. knitting needle / crochet hook);  shughl es-sinnaara = knitting work;  formal: حياكة — 7iyaake")
        HBExample("sitti bti7ibb shughl es-sinnaara — ستي بتحب شغل الصنارة — My grandmother loves knitting.")

        HBSectionHeader("Words and Mind")
        HBWord("reading — قراية — 2raaye")
        HBWord("to read — قرا — 2ara / yi2ra")
        HBWord("writing — كتابة — ktaabe")
        HBWord("to write — كتب — katab / yiktib")
        HBWord("rhyme — قافية — 2aafye;  poetry: شعر — shi3r")
        HBWord("rhyming / improvised folk poetry — زجل — zajal  (the Levantine tradition of rhymed sung poetry, often as a duel between poets)")
        HBWord("to write poetry — كتب شعر — katab shi3r")
        HBWord("debating — مناظرة — munaazara  (a formal debate)")
        HBInfo("f · 2: مناظرتين munaazartein · pl: مناظرات munaazaraat")
        HBWord("to debate / argue a point — ناقش — naa2ash / ynaa2ish")
        HBExample("b7ibb a2ra 2abl ma anaam — بحب أقرا قبل ما أنام — I like to read before I go to sleep.")

        HBSectionHeader("Home and Food")
        HBWord("cooking — طبخ — tabikh")
        HBWord("to cook — طبخ — tabakh / yutbukh")
        HBWord("renovating — ترميم — tarmiim  (also tasliihaat — repairs)")
        HBWord("to renovate — رمّم — rammam / yrammim;  to fix: صلّح — salla7 / ysalli7")
        HBExample("3am nrammim el-beit — عم نرمم البيت — We're renovating the house.")

        HBSectionHeader("Wine and Coffee")
        HBWord("wine — نبيد — nbiid  (formal نبيذ nabiiz)")
        HBWord("winery — معمل نبيد — ma3mal nbiid  (lit. wine factory)")
        HBWord("vineyard — كرم — karm")
        HBSmall("Hebrew parallel: כרם kerem (vineyard). Well-known Levantine wineries include Cremisan near Beit Jala and Ksara in Lebanon's Bekaa valley.")
        HBWord("wine tasting — تذوق النبيد — tazawwu2 en-nbiid")
        HBWord("coffee — قهوة — 2ahwe")
        HBWord("coffee lover — بحب القهوة — b7ibb el-2ahwe;  hooked on coffee: مدمن قهوة — mudmin 2ahwe")
        HBWord("to roast coffee — حمّص القهوة — 7ammas el-2ahwe")
        HBExample("ana mudmin 2ahwe — أنا مدمن قهوة — I'm addicted to coffee.")

        HBSectionHeader("Collecting")
        HBWord("collecting — جمع — jam3")
        HBWord("to collect — جمع — jama3 / yijma3")
        HBWord("collection — مجموعة — majmuu3a")
        HBInfo("f · 2: مجموعتين majmuu3tein · pl: مجموعات majmuu3aat")
        HBWord("stamps — طوابع — tawaabi3  (one stamp: طابع — taabi3)")
        HBWord("stamp collection — مجموعة طوابع — majmuu3et tawaabi3")
        HBWord("antiques — أنتيكا — antiika  (antiques shop: محل أنتيكا — ma7all antiika)")
        HBWord("coins (to collect) — عملات قديمة — 3umlaat 2adiime  (old coins)")
        HBExample("abuuy biyijma3 tawaabi3 — أبوي بيجمع طوابع — My father collects stamps.")

        Spacer(modifier = Modifier.height(24.dp))
    }
}


@Composable
private fun HBSectionHeader(text: String) {
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
private fun HBWord(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        fontWeight = FontWeight.SemiBold,
        modifier = Modifier.padding(top = 6.dp, bottom = 2.dp)
    )
}

@Composable
private fun HBInfo(text: String) {
    Text(
        text = "    $text",
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(vertical = 1.dp)
    )
}

@Composable
private fun HBExample(text: String) {
    Text(
        text = "    e.g. $text",
        style = MaterialTheme.typography.bodyMedium,
        fontStyle = FontStyle.Italic,
        modifier = Modifier.padding(vertical = 1.dp)
    )
}

@Composable
private fun HBSmall(text: String) {
    Text(
        text = "    $text",
        style = MaterialTheme.typography.bodySmall,
        modifier = Modifier.padding(vertical = 2.dp)
    )
}

@Composable
private fun HBNote(text: String) {
    Spacer(modifier = Modifier.height(8.dp))
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(12.dp)
        )
    }
    Spacer(modifier = Modifier.height(4.dp))
}
