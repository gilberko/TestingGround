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
fun PrepositionsConnectingWordsScreen() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        PCWNote("Little words that glue a sentence together. Many of them attach directly to the next word (w-, b-, la-, fa-) and most take pronoun endings: ma3i (with me), minno (from him), 3aleiha (on her), 3anhom (about them).")

        PCWSectionHeader("Position — Where Something Is")
        PCWBodyText("on top of / above — فوق — fo2")
        PCWExample("el-ktaab fo2 el-khzaane — الكتاب فوق الخزانة — The book is on top of the cupboard.")
        PCWExample("et-tayyara taayra fo2 el-ghyuum — الطيارة طايرة فوق الغيوم — The plane is flying above the clouds.")
        PCWBodyText("on — على — 3ala  (often shortened to 3a- / 3al-)")
        PCWExample("el-mafatii7 3at-tawle — المفاتيح عالطاولة — The keys are on the table.")
        PCWBodyText("below / under — تحت — ta7t")
        PCWExample("el-bisse ta7t el-kanabaye — البسّة تحت الكنباية — The cat is under the sofa.")
        PCWExample("saakniin ta7tna — ساكنين تحتنا — They live below us.")
        PCWBodyText("in — في — fi;  also the prefix ب — b- / bi-")
        PCWExample("ana fil-beit — أنا في البيت — I'm at home.")
        PCWExample("bashtghil b-tal abiib — بشتغل بتل أبيب — I work in Tel Aviv.")
        PCWBodyText("near — قريب من — 2ariib min;  next to — جنب — janb")
        PCWExample("el-mat3am 2ariib min el-ma7atta — المطعم قريب من المحطة — The restaurant is near the station.")
        PCWExample("u23od janbi — اقعد جنبي — Sit next to me.")
        PCWBodyText("between — بين — bein")
        PCWExample("el-beit bein el-madrase wil-jaame3 — البيت بين المدرسة والجامع — The house is between the school and the mosque.")
        PCWExample("hada beini w-beinak — هاد بيني وبينك — This is between me and you.")

        PCWSectionHeader("Relation — From, To, With, About")
        PCWBodyText("from — من — min")
        PCWExample("ana min 7eifa — أنا من حيفا — I'm from Haifa.")
        PCWExample("jibto min es-soo2 — جبته من السوق — I brought it from the market.")
        PCWBodyText("to (a place) — على — 3ala / 3a-")
        PCWExample("raay7 3al-beit — رايح عالبيت — I'm going home.")
        PCWBodyText("to (a person, or 'for') — لـ — la- / il-")
        PCWExample("ba3atit risaale la-ummi — بعتت رسالة لإمّي — I sent a message to my mother.")
        PCWExample("ilak — إلك — for you / to you")
        PCWBodyText("to (formal, MSA) — إلى — ila  (seen in writing and news, rare in speech)")
        PCWBodyText("with — مع — ma3")
        PCWExample("ruu7 ma3i — روح معي — Go with me.")
        PCWBodyText("with (using a tool) — ب — b-")
        PCWExample("u2ta3o bis-sikkiine — اقطعه بالسكينة — Cut it with the knife.")
        PCWBodyText("without — بلا — bala;  also: بدون — biduun")
        PCWExample("2ahwe bala sukkar — قهوة بلا سكر — Coffee without sugar.")
        PCWExample("ma btitla3 biduun maftaa7 — ما بتطلع بدون مفتاح — Don't go out without a key.")
        PCWBodyText("about — عن — 3an")
        PCWExample("7akeina 3an esh-shughl — حكينا عن الشغل — We talked about work.")
        PCWNote("Thinking about: Arabic doesn't use 3an here. The verb fakkar (to think) takes fi or b-:\n• bfakker fik — بفكّر فيك — I'm thinking about you.\n• shu bitfakker? / b-shu bitfakker? — شو بتفكّر؟ / بشو بتفكّر؟ — What are you thinking about?\n• lissa bfakker bil-mawduu3 — لسّا بفكّر بالموضوع — I'm still thinking about the matter.\nUse 3an for talking, asking and hearing about something: 7aka 3an (talk about), sa2al 3an (ask about), sime3 3an (hear about).")

        PCWSectionHeader("Adding — And, In Addition, Moreover")
        PCWBodyText("and — و — w- / u-  (attached to the next word)")
        PCWExample("khubz w-jibne — خبز وجبنة — bread and cheese")
        PCWExample("ana w-inta — أنا وإنت — me and you")
        PCWBodyText("also / in addition — كمان — kamaan")
        PCWExample("ana kamaan biddi 2ahwe — أنا كمان بدّي قهوة — I also want coffee.")
        PCWBodyText("in addition to — بالإضافة لـ — bil-idaafe la-  (more formal)")
        PCWExample("bil-idaafe la-esh-shughl, bidros — بالإضافة للشغل، بدرس — In addition to work, I study.")
        PCWBodyText("moreover / on top of that — وفوق هيك — w-fo2 hek;  besides that — غير هيك — ghair hek")
        PCWExample("el-beit kbiir, w-fo2 hek rkhiis — البيت كبير، وفوق هيك رخيص — The house is big, and on top of that it's cheap.")
        PCWExample("ghair hek, ma 3indi wa2t — غير هيك، ما عندي وقت — Besides that, I don't have time.")

        PCWSectionHeader("Contrast — But, However, In Spite Of")
        PCWBodyText("but — بس — bas  (most common in speech);  also: لكن — laakin")
        PCWExample("biddi aruu7, bas ta3baan — بدّي أروح، بس تعبان — I want to go, but I'm tired.")
        PCWBodyText("however — لكن — laakin;  in speech often just بس — bas")
        PCWExample("el-akel kaan tayyeb, laakin el-as3aar ghaalye — الأكل كان طيّب، لكن الأسعار غالية — The food was good; however, the prices are high.")
        PCWBodyText("in spite of — رغم — raghm / بالرغم من — bir-raghm min  (+ noun)")
        PCWExample("tla3na raghm esh-shita — طلعنا رغم الشتا — We went out in spite of the rain.")
        PCWBodyText("even though — مع إنّه — ma3 inno  (+ sentence)")
        PCWExample("ija ma3 inno kaan mariid — إجا مع إنّه كان مريض — He came even though he was sick.")
        PCWBodyText("nevertheless / even so — مع هيك — ma3 hek;  also: بالرغم من هيك — bir-raghm min hek")
        PCWExample("kaan ta3baan, w-ma3 hek kammal — كان تعبان، ومع هيك كمّل — He was tired; nevertheless, he carried on.")

        PCWSectionHeader("Choice — Or")
        PCWBodyText("or — أو — aw  (in statements)")
        PCWExample("mniiji bukra aw ba3d bukra — منيجي بكرا أو بعد بكرا — We'll come tomorrow or the day after.")
        PCWBodyText("or (in questions) — ولّا — willa")
        PCWExample("shai willa 2ahwe? — شاي ولّا قهوة؟ — Tea or coffee?")

        PCWSectionHeader("Cause and Result — Because, Therefore")
        PCWBodyText("because — لأنّه — la2inno  (+ sentence)")
        PCWExample("ma jiit la2inno kunt mariid — ما جيت لأنّه كنت مريض — I didn't come because I was sick.")
        PCWBodyText("because / because of / for — عشان — 3ashaan;  also: مشان — mishaan")
        PCWExample("ta2akhkhart 3ashaan el-3aj2a — تأخّرت عشان العجقة — I was late because of the traffic.")
        PCWBodyText("therefore / that's why — عشان هيك — 3ashaan hek;  also: لهيك — la-hek")
        PCWExample("kaan fi 3aj2a, 3ashaan hek ta2akhkhart — كان في عجقة، عشان هيك تأخّرت — There was traffic, so I was late.")
        PCWBodyText("so / then — فـ — fa-  (joins a result to the sentence before it)")
        PCWExample("ma la2eito, fa-rja3t — ما لقيته، فرجعت — I didn't find him, so I went back.")

        PCWSectionHeader("Quick Reference")
        PCWBodyText("fo2 — on top of / above · 3ala — on · ta7t — under · fi / b- — in · 2ariib min — near · janb — next to · bein — between")
        PCWBodyText("min — from · 3ala — to (place) · la- — to (person) · ma3 — with · bala / biduun — without · 3an — about (fakkar fi — think about)")
        PCWBodyText("w- — and · kamaan — also · bil-idaafe la- — in addition to · w-fo2 hek / ghair hek — moreover")
        PCWBodyText("bas / laakin — but, however · raghm — in spite of · ma3 inno — even though · ma3 hek — nevertheless")
        PCWBodyText("aw — or · willa — or (questions) · la2inno / 3ashaan — because · 3ashaan hek / la-hek / fa- — therefore")

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun PCWSectionHeader(text: String) {
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
private fun PCWBodyText(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        fontWeight = FontWeight.SemiBold,
        modifier = Modifier.padding(top = 6.dp, bottom = 2.dp)
    )
}

@Composable
private fun PCWExample(text: String) {
    Text(
        text = "    e.g. $text",
        style = MaterialTheme.typography.bodyMedium,
        fontStyle = FontStyle.Italic,
        modifier = Modifier.padding(vertical = 1.dp)
    )
}

@Composable
private fun PCWNote(text: String) {
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
