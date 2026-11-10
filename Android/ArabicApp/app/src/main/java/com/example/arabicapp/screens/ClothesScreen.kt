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
fun ClothesScreen() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        CLNote("Clothes in everyday Levantine are أواعي — awaa3i (Palestinian/Jordanian);  Lebanese/Syrian: تياب — tyaab;  formal: ملابس — malaabis. Palestinian forms come first; Lebanese/Syrian variants are shown where they differ.")

        CLSectionHeader("Wearing and Taking Off")
        CLWord("to wear / put on — لبس — libis / yilbas")
        CLExample("shu ra7 tilbas el-yoom? — شو رح تلبس اليوم؟ — What are you going to wear today?")
        CLWord("to take off — شلح — shala7 / yishla7")
        CLExample("ishla7 el-kundara 3al-baab — اشلح الكندرة عالباب — Take off your shoes at the door.")
        CLWord("it looks good on you — حلو عليك — 7ilu 3aleik  (to a woman: 7ilu 3aleiki)")

        CLSectionHeader("Tops")
        CLWord("shirt (with buttons) — قميص — 2amiis")
        CLInfo("m · 2: قميصين 2amiisein · pl: قمصان 2umsaan")
        CLWord("blouse — بلوزة — bluuze")
        CLInfo("f · 2: بلوزتين bluuztein · pl: بلايز blaayiz")
        CLWord("top / T-shirt — بلوزة — bluuze  (any casual top);  تي شيرت — tii-shirt;  توب — toop (a short or sleeveless top)")
        CLWord("sweater — كنزة — kanze")
        CLInfo("f · 2: كنزتين kanztein · pl: كنزات kanzaat")
        CLExample("2amiis abyad — قميص أبيض — a white shirt")

        CLSectionHeader("Bottoms")
        CLWord("pants / trousers — بنطلون — bantaloon")
        CLInfo("m · 2: بنطلونين bantaloonein · pl: بناطيل banaatiil")
        CLWord("jeans — جينز — jiinz  (also بنطلون جينز — bantaloon jiinz)")
        CLWord("skirt — تنورة — tannuura")
        CLInfo("f · 2: تنورتين tannuurtein · pl: تنانير tanaaniir")
        CLWord("belt — قشاط — 2shaat  (shared Levantine);  formal: حزام — 7izaam")
        CLInfo("m · 2: قشاطين 2shaatein · pl: قشاطات 2shaataat")
        CLExample("hal-bantaloon kbiir 3alay — هالبنطلون كبير علي — These pants are too big for me.")

        CLSectionHeader("Dresses, Gowns and Outerwear")
        CLWord("dress — فستان — fustaan")
        CLInfo("m · 2: فستانين fustaanein · pl: فساتين fasaatiin")
        CLWord("gown / evening gown — فستان سهرة — fustaan sahra  (lit. a dress for an evening out)")
        CLWord("robe / bathrobe — روب — roob")
        CLWord("wedding dress — فستان عرس — fustaan 3urs")
        CLWord("coat — كبوت — kabbuut  (shared Levantine);  formal: معطف — mi3taf;  also مانطو — manto (a long coat)")
        CLInfo("m · 2: كبوتين kabbuutein · pl: كبابيت kabaabiit")
        CLWord("jacket — جاكيت — jakeet")
        CLInfo("m · 2: جاكيتين jakeetein · pl: جاكيتات jakeetaat")
        CLWord("cape / cloak — عباية — 3abaaye  (a traditional long cloak, also the women's abaya);  a superhero cape: كاب — kaab (loanword, usage varies)")
        CLInfo("f · 2: عبايتين 3abaaytein · pl: عبايات 3abaayaat")
        CLWord("scarf — شال — shaal;  winter scarf (Lebanese/Syrian): لفحة — laf7a;  headscarf: حجاب — 7ijaab / منديل — mandiil")
        CLInfo("m · 2: شالين shaalein · pl: شالات shaalaat")
        CLExample("ilbas el-kabbuut, barra bard — البس الكبوت، برا برد — Put on your coat, it's cold outside.")

        CLSectionHeader("Nightwear and Underwear")
        CLWord("nightgown — قميص نوم — 2amiis noom")
        CLWord("pajamas — بيجاما — bijaama")
        CLInfo("f · 2: بيجامتين bijaamtein · pl: بيجامات bijaamaat")
        CLWord("underwear (general) — أواعي داخلية — awaa3i daakhiliyye;  formal: ملابس داخلية — malaabis daakhiliyye")
        CLWord("underpants / briefs — كلسون — kalsoon")
        CLInfo("m · 2: كلسونين kalsoonein · pl: كلاسين kalaasiin")
        CLWord("panties — كلسون — kalsoon  (same word);  Lebanese/Syrian also: كيلوت — kiloot (from French culotte)")
        CLWord("bra — صدرية — sadriyye;  Lebanese/Syrian also: سوتيان — sutyaan (from French soutien-gorge)")
        CLInfo("f · 2: صدريتين sadriytein · pl: صدريات sadriyyaat")
        CLWord("socks — كلسات — kalsaat  (one sock: كلسة — kalse);  Palestinian/Jordanian also: جرابات — jaraabaat")
        CLExample("wein el-bijaama taba3ti? — وين البيجاما تبعتي؟ — Where are my pajamas?")

        CLSectionHeader("Footwear")
        CLWord("shoes — كندرة — kundara  (one shoe / a pair is also kundara);  formal: حذاء — 7izaa2")
        CLInfo("f · 2: كندرتين kundartein · pl: كنادر kanaadir")
        CLWord("a pair of shoes — جوز كنادر — jooz kanaadir")
        CLWord("sandals — صندل — sandal")
        CLInfo("m · 2: صندلين sandalein · pl: صنادل sanaadil")
        CLWord("flip-flops / slippers — شحاطة — sha77aata")
        CLInfo("f · 2: شحاطتين sha77aattein · pl: شحاطات sha77aataat")
        CLExample("hal-kundara bitwaj3ni — هالكندرة بتوجعني — These shoes hurt me.")

        CLSectionHeader("Headwear and Eyewear")
        CLWord("hat — برنيطة — burneita  (a brimmed hat);  cap: طاقية — taa2iyye;  formal: قبعة — 2ubba3a")
        CLInfo("f · 2: برنيطتين burneitein · pl: برانيط baraaniit")
        CLWord("winter hat / beanie — طاقية صوف — taa2iyyet suuf  (lit. wool cap)")
        CLInfo("taa2iyye: f · 2: طاقيتين taa2iytein · pl: طواقي tawaa2i")
        CLWord("yarmulka / kippa — كيبا — kiippa  (loan from Hebrew כיפה);  descriptively: طاقية — taa2iyye (the same word as a Muslim prayer skullcap)")
        CLSmall("Hebrew parallel: כיפה kippa. In Arabic a small skullcap of any religion is a taa2iyye; Palestinians in Israel usually just say kippa for the Jewish one.")
        CLWord("eyeglasses — نضارات — naddaraat  (one pair is already plural in form);  formal: نظارة — nazzaara")
        CLWord("sunglasses — نضارات شمس — naddaraat shams")
        CLExample("nseit naddaraat esh-shams bil-beit — نسيت نضارات الشمس بالبيت — I forgot my sunglasses at home.")

        Spacer(modifier = Modifier.height(24.dp))
    }
}


@Composable
private fun CLSectionHeader(text: String) {
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
private fun CLWord(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        fontWeight = FontWeight.SemiBold,
        modifier = Modifier.padding(top = 6.dp, bottom = 2.dp)
    )
}

@Composable
private fun CLInfo(text: String) {
    Text(
        text = "    $text",
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(vertical = 1.dp)
    )
}

@Composable
private fun CLExample(text: String) {
    Text(
        text = "    e.g. $text",
        style = MaterialTheme.typography.bodyMedium,
        fontStyle = FontStyle.Italic,
        modifier = Modifier.padding(vertical = 1.dp)
    )
}

@Composable
private fun CLSmall(text: String) {
    Text(
        text = "    $text",
        style = MaterialTheme.typography.bodySmall,
        modifier = Modifier.padding(vertical = 2.dp)
    )
}

@Composable
private fun CLNote(text: String) {
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
