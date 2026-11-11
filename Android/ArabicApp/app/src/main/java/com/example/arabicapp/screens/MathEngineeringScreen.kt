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
fun MathEngineeringScreen() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        MENote("Math and science words are mostly formal Arabic (fuS7a), even inside colloquial sentences, and engineers often just use the English term. Fun fact: \"algebra\" comes from Arabic الجبر al-jabr, \"algorithm\" from the mathematician الخوارزمي al-Khwaarizmi, and \"zero\" / \"cipher\" from صفر Sifr.")

        MESectionHeader("Arithmetic")
        MEWord("addition — جمع — jam3;  plus: زائد — zaa2id")
        MEWord("subtraction — طرح — Tar7;  minus: ناقص — naa2iS")
        MEWord("multiplication — ضرب — Darb;  times: ضرب — Darb  (tlaate Darb arba3a = 3 × 4)")
        MEWord("division — قسمة — 2isme;  divided by: على — 3ala  (sitte 3ala tneen = 6 ÷ 2)")
        MEWord("remainder — باقي — baa2i  (formal: باقي القسمة — baa2i el-2isme)")
        MEWord("sum — مجموع — majmuu3;  result: ناتج — naatij")
        MEWord("summary (of a text) — ملخّص — mulakhkhaS  (a different word from 'sum')")
        MEExample("khamse zaa2id tlaate bisaawi tamaanye — خمسة زائد تلاتة بساوي تمانية — Five plus three equals eight.")

        MESectionHeader("Powers and Roots")
        MEWord("to the power of — أُس — uss  (tneen uss tamaanye = 2⁸);  formal: مرفوع للقوة — marfuu3 lil-2uwwa")
        MEWord("exponent / power — أُس — uss  (pl: أسس usus)")
        MEWord("squared — تربيع — tarbii3  (x tarbii3 = x²);  cubed: تكعيب — tak3iib")
        MEWord("root — جذر — jidr  (also the root of a plant and the root of a word, like Hebrew שורש)")
        MEInfo("m · pl: جذور jduur")
        MEWord("square root — جذر تربيعي — jidr tarbii3i")
        MEExample("tneen uss tamaanye bisaawi miiteen w sitte w khamsiin — اتنين أس تمانية بساوي ميتين وستة وخمسين — 2 to the power of 8 is 256.")

        MESectionHeader("Geometry")
        MEWord("square — مربّع — murabba3")
        MEWord("rectangle — مستطيل — mustaTiil")
        MEWord("triangle — مثلث — musallas")
        MEWord("circle — دائرة — daa2ira  (everyday daayre)")
        MEWord("radius — نصف قطر — nuSS 2uTr;  diameter: قطر — 2uTr")
        MEWord("angle — زاوية — zaawye;  right angle: زاوية قائمة — zaawye 2aa2ime")
        MEInfo("f · pl: زوايا zawaaya  (also a corner of a room)")

        MESectionHeader("Comparing")
        MEWord("equal / equals — يساوي — bisaawi  (formal yusaawi);  equal (adj.): متساوي — mitsaawi")
        MEWord("different — مختلف — mukhtalif")
        MEWord("bigger than — أكبر من — akbar min")
        MEWord("smaller than — أصغر من — aSghar min  (everyday also azghar min)")
        MEWord("absolute value — القيمة المطلقة — el-2iime el-muTla2a")

        MESectionHeader("Algebra and Functions")
        MEWord("equation — معادلة — mu3aadale")
        MEInfo("f · pl: معادلات mu3aadalaat")
        MEWord("variable — متغيّر — mutaghayyir")
        MEWord("solution — حل — 7all;  to solve — حلّ — 7all / yi7ill")
        MEWord("function — دالّة — daalle;  in Palestinian/Jordanian schools: اقتران — i2tiraan")
        MEWord("range — مدى — mada;  domain: مجال — majaal")
        MEWord("mapping — تطبيق — taTbii2")
        MEWord("matrix — مصفوفة — maSfuufe")
        MEInfo("f · pl: مصفوفات maSfuufaat")
        MEWord("transformation — تحويل — ta7wiil;  Fourier transform: تحويل فورييه — ta7wiil fuuriye")
        MEWord("plane — مستوى — mustawa;  the frequency plane / domain: مجال الترددات — majaal et-taraddudaat")
        MEExample("7ill el-mu3aadale w la2ii 2iimet x — حلّ المعادلة ولاقي قيمة x — Solve the equation and find the value of x.")

        MESectionHeader("Proof and Reasoning")
        MEWord("proof — برهان — burhaan  (also إثبات — isbaat)")
        MEWord("to prove — أثبت — asbat / yisbit  (formal athbata)")
        MEWord("to doubt — شكّ — sha22 / yshukk  (b- = in);  doubt: شك — shakk")
        MEWord("to question (cast doubt on) — شكّك بـ — shakkak b-;  to wonder / ask oneself: تساءل — tasaa2al")
        MEWord("probability — احتمال — i7timaal  (also 'possibility')")
        MEExample("fi i7timaal kbiir inha tshatti bukra — في احتمال كبير إنها تشتي بكرا — There's a high probability it'll rain tomorrow.")

        MESectionHeader("Measurements")
        MEWord("weight — وزن — wazn")
        MEWord("mass — كتلة — kutle")
        MEWord("length — طول — Tuul  (also a person's height)")
        MEWord("width — عرض — 3arD")
        MEWord("height — ارتفاع — irtifaa3  (of a building, a mountain…)")
        MEWord("time — زمن — zaman  (physics);  وقت — wa2t  (everyday)")
        MEWord("direction — اتجاه — ittijaah")

        MESectionHeader("Physics")
        MEWord("force — قوة — 2uwwe")
        MEWord("energy — طاقة — Taa2a;  potential energy: طاقة كامنة — Taa2a kaamne")
        MEWord("gravity — جاذبية — jaazbiyye")
        MEWord("friction — احتكاك — i7tikaak")
        MEWord("movement / motion — حركة — 7arake")
        MEWord("speed — سرعة — sur3a;  speed of light: سرعة الضوء — sur3it eD-Daw2")
        MEWord("acceleration — تسارع — tasaaru3")
        MEWord("solid — صلب — Sulb;  liquid — سائل — saa2il;  gas — غاز — ghaaz")
        MEWord("pulley — بكرة — bakara")

        MESectionHeader("Electricity")
        MEWord("electricity — كهربا — kahraba;  electric — كهربائي — kahrabaa2i")
        MEWord("electric current — تيار كهربائي — tayyaar kahrabaa2i;  current: تيار — tayyaar  (the same word as a sea current)")
        MEWord("alternating current (AC) — تيار متناوب — tayyaar mutanaawib")
        MEWord("direct current (DC) — تيار مستمر — tayyaar mustamirr")
        MEWord("voltage / electric potential — جهد — juhd  (also فولتية — voltiyye)")
        MEWord("resistance — مقاومة — mu2aawame  (also the resistor itself)")
        MEWord("capacitor — مكثّف — mukassif")
        MEWord("switch — مفتاح — muftaa7  (also 'key');  everyday: سويتش — swiitsh")
        MEWord("electric circuit — دارة كهربائية — daara kahrabaa2iyye  (also دائرة — daa2ira, the same word as circle)")
        MEWord("wire — سلك — silk  (pl أسلاك aslaak);  a cable: شريط — shriiT (Levantine everyday)")
        MEWord("conductor — موصل — muwaSSil;  insulator: عازل — 3aazil")
        MEWord("power supply — مزوّد طاقة — muzawwid Taa2a;  everyday: باور سبلاي — power supply")

        MESectionHeader("Signals")
        MEWord("signal — إشارة — ishaara  (the same word as a traffic light or a sign)")
        MEWord("frequency — تردّد — taraddud")
        MEWord("amplitude — سعة — si3a")

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun MESectionHeader(text: String) {
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
private fun MEWord(text: String) {
    Text(
        text = fixArabizi(text),
        style = MaterialTheme.typography.bodyMedium,
        fontWeight = FontWeight.SemiBold,
        modifier = Modifier.padding(top = 6.dp, bottom = 2.dp)
    )
}

@Composable
private fun MEInfo(text: String) {
    Text(
        text = fixArabizi("    $text"),
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(vertical = 1.dp)
    )
}

@Composable
private fun MEExample(text: String) {
    Text(
        text = fixArabizi("    e.g. $text"),
        style = MaterialTheme.typography.bodyMedium,
        fontStyle = FontStyle.Italic,
        modifier = Modifier.padding(vertical = 1.dp)
    )
}

@Composable
private fun MESmall(text: String) {
    Text(
        text = fixArabizi("    $text"),
        style = MaterialTheme.typography.bodySmall,
        modifier = Modifier.padding(vertical = 2.dp)
    )
}

@Composable
private fun MENote(text: String) {
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
