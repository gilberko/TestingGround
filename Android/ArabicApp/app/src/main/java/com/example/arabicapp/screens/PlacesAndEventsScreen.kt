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
fun PlacesAndEventsScreen() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        PENote("Places around town and the events you go to. Palestinian forms come first; Lebanese/Syrian variants are shown where they differ. Many place names are \"station of …\" / \"office of …\": m7atta (station) becomes m7attet … and maktab (office) stays maktab … before the next word.")

        PESectionHeader("Lessons and Events")
        PEWord("Arabic lesson — درس عربي — dars 3arabi")
        PEInfo("dars: m · 2: درسين darsein · pl: دروس druus")
        PEWord("English lesson — درس إنجليزي — dars inglizi  (also إنكليزي — inkliizi, Lebanese/Syrian)")
        PEWord("party — حفلة — 7afle  (el-7afle = the party; written with ح = 7, not h)")
        PEInfo("f · 2: حفلتين 7afiltein · pl: حفلات 7aflaat")
        PEWord("concert — حفلة موسيقية — 7afle muusii2iyye  (lit. musical party);  el-7afle el-muusii2iyye = the concert")
        PEInfo("f · 2: حفلتين موسيقيات 7afiltein muusii2iyyaat · pl: حفلات موسيقية 7aflaat muusii2iyye")
        PEWord("trip / outing — رحلة — ri7le  (el-ri7le = the trip)")
        PEInfo("f · 2: رحلتين ri7iltein · pl: رحلات ri7laat")
        PEWord("meeting — اجتماع — ijtimaa3  (el-ijtimaa3 = the meeting)")
        PEInfo("m · 2: اجتماعين ijtimaa3ein · pl: اجتماعات ijtimaa3aat")
        PEWord("holiday / vacation / day off — عطلة — 3utle  (el-3utle = the holiday)")
        PEInfo("f · 2: عطلتين 3utultein · pl: عطل 3utal")
        PEWord("weekend — عطلة نهاية الأسبوع — 3utlet nihaayet el-usbuu3;  everyday: الويكند — el-wiikend")
        PEExample("shu ra7 ta3mil bil-3utle? — شو رح تعمل بالعطلة؟ — What are you going to do on the holiday?")
        PEExample("ra7 a7dar 7afle muusii2iyye bukra — رح أحضر حفلة موسيقية بكرا — I'm going to a concert tomorrow.")

        PESectionHeader("Travel and Transport")
        PEWord("airport — مطار — mataar")
        PEInfo("m · 2: مطارين mataarein · pl: مطارات mataaraat")
        PEWord("station — محطة — m7atta  (before a noun: m7attet …)")
        PEInfo("f · 2: محطتين m7attatein · pl: محطات m7attaat")
        PEWord("bus station / bus stop — محطة باصات / محطة الباص — m7attet baasaat / m7attet el-baas")
        PEWord("train station — محطة القطار — m7attet el-qitaar  (city speech also 2itaar;  Lebanese also محطة الترين — m7attet et-treen)")
        PESmall("qitaar is a word taken from formal Arabic, so many speakers keep a real q (deep in the throat). To English ears that q often sounds like a k — that's the \"kitaar\" you hear.")
        PEWord("gas / petrol station — محطة بنزين — m7attet banziin")
        PEWord("gas station (Syrian/Lebanese) — كازية — kaaziyye  (from كاز kaaz = fuel)")
        PEInfo("f · 2: كازيتين kaaziyytein · pl: كازيات kaaziyyaat")
        PEExample("wein m7attet el-baas? — وين محطة الباص؟ — Where is the bus stop?")

        PESectionHeader("City Services")
        PEWord("bank — بنك — bank")
        PEInfo("m · 2: بنكين bankein · pl: بنوك bnuuk")
        PEWord("police station — مركز شرطة — markaz shurta;  Lebanese/Syrian: مخفر — makhfar")
        PEInfo("markaz: m · 2: مركزين markazein · pl: مراكز maraakiz")
        PEWord("post office — مكتب بريد — maktab bariid;  everyday: البوسطة — el-boosta")
        PEWord("embassy — سفارة — safaara")
        PEInfo("f · 2: سفارتين safaartein · pl: سفارات safaaraat")
        PEWord("hospital — مستشفى — mustashfa")
        PEInfo("m · 2: مستشفيين mustashfayein · pl: مستشفيات mustashfayaat")
        PEWord("office — مكتب — maktab")
        PEInfo("m · 2: مكتبين maktabein · pl: مكاتب makaatib")

        PESectionHeader("Shopping, Food and Places to Stay")
        PEWord("market — سوق — suu2  (the shuk / souk)")
        PEInfo("m · 2: سوقين suu2ein · pl: أسواق aswaa2")
        PEWord("restaurant — مطعم — mat3am")
        PEInfo("m · 2: مطعمين mat3amein · pl: مطاعم mataa3im")
        PEWord("coffee shop / café — قهوة — 2ahwe  (traditional coffee house);  كافيه — kafee  (modern café);  مقهى — ma2ha  (formal)")
        PEInfo("kafee: m · 2: كافيهين kafeehein · pl: كافيهات kafeehaat")
        PEWord("hotel — فندق — funduq  (usually said with q);  Lebanese also أوتيل — oteel")
        PEInfo("m · 2: فندقين funduqein · pl: فنادق fanaadiq")
        PEExample("fi mat3am 2ariib min el-funduq? — في مطعم قريب من الفندق؟ — Is there a restaurant near the hotel?")

        PESectionHeader("Education and Culture")
        PEWord("school — مدرسة — madrase")
        PEInfo("f · 2: مدرستين madrastein · pl: مدارس madaaris")
        PEWord("university — جامعة — jaam3a")
        PEInfo("f · 2: جامعتين jaam3itein · pl: جامعات jaam3aat")
        PEWord("library (also bookshop) — مكتبة — maktabe")
        PEInfo("f · 2: مكتبتين maktabtein · pl: مكتبات maktabaat")
        PEWord("museum — متحف — mat7af")
        PEInfo("m · 2: متحفين mat7afein · pl: متاحف mataa7if")
        PEWord("cinema — سينما — sinama")
        PEInfo("f · 2: سينمتين sinamtein · pl: سينمات sinamaat")

        PESectionHeader("Places of Worship")
        PEWord("mosque — جامع — jaame3  (everyday);  مسجد — masjid  (formal / smaller mosque)")
        PEInfo("jaame3: m · 2: جامعين jaam3ein · pl: جوامع jawaame3")
        PEWord("church — كنيسة — kniise")
        PEInfo("f · 2: كنيستين kniistein · pl: كنايس knaayis")
        PEWord("synagogue — كنيس — kniis  (Palestinian / Israeli usage);  formal: معبد يهودي — ma3bad yahuudi")
        PEInfo("m · 2: كنيسين kniisein · pl: كنس kunus")

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun PESectionHeader(text: String) {
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
private fun PEWord(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        fontWeight = FontWeight.SemiBold,
        modifier = Modifier.padding(top = 6.dp, bottom = 2.dp)
    )
}

@Composable
private fun PEInfo(text: String) {
    Text(
        text = "    $text",
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(vertical = 1.dp)
    )
}

@Composable
private fun PEExample(text: String) {
    Text(
        text = "    e.g. $text",
        style = MaterialTheme.typography.bodyMedium,
        fontStyle = FontStyle.Italic,
        modifier = Modifier.padding(vertical = 1.dp)
    )
}

@Composable
private fun PESmall(text: String) {
    Text(
        text = "    $text",
        style = MaterialTheme.typography.bodySmall,
        modifier = Modifier.padding(vertical = 2.dp)
    )
}

@Composable
private fun PENote(text: String) {
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
