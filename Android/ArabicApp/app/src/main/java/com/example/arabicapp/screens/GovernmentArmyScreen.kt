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
fun GovernmentArmyScreen() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        GANote("Politics and army vocabulary is mostly the same formal (MSA) word all over the Levant, said with spoken pronunciation. In formal words many speakers keep a real q (ق) instead of the city 2, so both are shown where it matters. Palestinian first; Lebanese/Syrian variants where they differ.")

        GASectionHeader("Government and Systems")
        GAWord("government — حكومة — 7ukuume")
        GAInfo("f · 2: حكومتين 7ukuumtein · pl: حكومات 7ukuumaat")
        GAWord("politics — سياسة — siyaase")
        GAInfo("f · uncountable in this sense")
        GAWord("politician — سياسي — siyaasi  (f: سياسية — siyaasiyye)")
        GAInfo("m · 2: سياسيين siyaasiyyein · pl: سياسيين siyaasiyyiin")
        GAWord("democracy — ديمقراطية — dimuqraatiyye  (city: dimu2raatiyye)")
        GAWord("dictatorship — ديكتاتورية — diktatooriyye;  dictator: ديكتاتور — diktatoor")
        GAWord("law — قانون — 2aanuun  (also qaanuun)")
        GAInfo("m · 2: قانونين 2aanuunein · pl: قوانين 2awaaniin")
        GAWord("rules — قواعد — 2awaa3id  (one rule: قاعدة — 2aa3de);  also نظام — nizaam (a set of rules / system)")
        GAExample("hay 7ukuume jdiide — هاي حكومة جديدة — This is a new government.")

        GASectionHeader("Leaders and Officials")
        GAWord("president — رئيس — ra2iis  (f: رئيسة — ra2iise)")
        GAInfo("m · 2: رئيسين ra2iisein · pl: رؤسا ru2asa")
        GAWord("minister — وزير — waziir")
        GAInfo("m · 2: وزيرين waziirein · pl: وزرا wuzara")
        GAWord("prime minister — رئيس الوزراء — ra2iis el-wuzara")
        GAWord("parliament — برلمان — barlamaan;  Jordan: مجلس النواب — majlis en-nuwwaab;  the Knesset: الكنيست — el-kniset")
        GAWord("mayor — رئيس البلدية — ra2iis el-baladiyye  (baladiyye = municipality)")
        GAWord("advisor — مستشار — mustashaar")
        GAInfo("m · 2: مستشارين mustashaarein · pl: مستشارين mustashaariin")
        GAWord("Secretary of State / foreign minister — وزير الخارجية — waziir el-khaarjiyye")
        GASmall("A government 'secretary' is a waziir (minister). An office secretary is a different word: سكرتير — sekreteer / سكرتيرة — sekreteera. Secretary-General (UN etc.) is أمين عام — amiin 3aam.")
        GAWord("diplomat — دبلوماسي — diblumaasi")
        GAInfo("m · 2: دبلوماسيين diblumaasiyyein · pl: دبلوماسيين diblumaasiyyiin")
        GAWord("ambassador — سفير — safiir  (related to سفارة safaara — embassy)")
        GAInfo("m · 2: سفيرين safiirein · pl: سفرا sufara")
        GAExample("ra2iis el-baladiyye 7aka ma3 en-naas — رئيس البلدية حكى مع الناس — The mayor talked with the people.")

        GASectionHeader("Monarchy")
        GAWord("king — ملك — malik")
        GAInfo("m · 2: ملكين malikein · pl: ملوك mluuk")
        GASmall("Hebrew parallel: מלך melech — same root م.ل.ك / מ.ל.ך.")
        GAWord("queen — ملكة — malike")
        GAInfo("f · 2: ملكتين maliktein · pl: ملكات malikaat")
        GAWord("prince — أمير — amiir;  princess: أميرة — amiire")
        GAInfo("m · 2: أميرين amiirein · pl: أمرا umara")
        GAWord("heir — وريث — wariis  (formal wariith)")
        GAInfo("m · 2: وريثين wariisein · pl: ورثة warase")
        GAWord("heir to the throne / crown prince — ولي العهد — wali el-3ahd")
        GAWord("throne — عرش — 3arsh")
        GAInfo("m · 2: عرشين 3arshein · pl: عروش 3ruush")
        GAWord("crown — تاج — taaj")
        GAInfo("m · 2: تاجين taajein · pl: تيجان tiijaan")
        GAExample("el-malik 2a3ad 3al-3arsh — الملك قعد عالعرش — The king sat on the throne.")

        GASectionHeader("Country and Territory")
        GAWord("country — بلد — balad  (also hometown)")
        GAInfo("m/f · 2: بلدين baladein · pl: بلاد blaad")
        GAWord("state (a country, politically) — دولة — dawle")
        GAInfo("f · 2: دولتين dawiltein · pl: دول duwal")
        GAWord("state (like a U.S. state) — ولاية — wilaaye")
        GAWord("region / area — منطقة — mante2a")
        GAInfo("f · 2: منطقتين mante2tein · pl: مناطق manaati2")
        GAWord("border — حدود — 7duud  (usually plural;  one border line: حد — 7add)")
        GAExample("wein el-7duud? — وين الحدود؟ — Where is the border?")

        GASectionHeader("Elections")
        GAWord("elections — انتخابات — intikhaabaat")
        GAWord("to vote — صوّت — sawwat / ysawwit;  to elect: انتخب — intakhab / yintikhib")
        GAWord("a vote — صوت — soot  (the same word as voice)")
        GAInfo("m · 2: صوتين sootein · pl: أصوات aswaat")
        GAWord("ballot — ورقة اقتراع — wara2et iqtiraa3;  ballot box: صندوق الاقتراع — sanduu2 el-iqtiraa3")
        GAWord("campaign — حملة انتخابية — 7amle intikhaabiyye  (7amle = campaign)")
        GAInfo("7amle: f · 2: حملتين 7amiltein · pl: حملات 7amlaat")
        GAWord("propaganda — دعاية — di3aaye  (also means an advertisement);  also بروباغندا — propaganda")
        GAWord("political party — حزب — 7izb")
        GAInfo("m · 2: حزبين 7izbein · pl: أحزاب a7zaab")
        GAExample("la miin ra7 tsawwit? — لمين رح تصوت؟ — Who are you going to vote for?")

        GASectionHeader("Army")
        GAWord("army — جيش — jeish")
        GAInfo("m · 2: جيشين jeishein · pl: جيوش jyuush")
        GAWord("soldier — جندي — jundi;  everyday also عسكري — 3askari")
        GAInfo("m · 2: جنديين jundiyyein · pl: جنود jnuud;  3askari pl: عسكر 3askar")
        GAWord("army officer — ضابط — daabit")
        GAInfo("m · 2: ضابطين daabtein · pl: ضباط dubbaat")
        GAWord("uniform (military) — بدلة عسكرية — badle 3askariyye;  any uniform: زي رسمي — zayy rasmi")
        GAWord("military service — خدمة عسكرية — khidme 3askariyye")
        GAExample("akhuuy daabit bil-jeish — أخوي ضابط بالجيش — My brother is an officer in the army.")

        GASectionHeader("Army Ranks (lowest to highest)")
        GANote("Rank names are formal Arabic and are similar across the Jordanian, Syrian, Lebanese and Palestinian forces, but the exact set and the top ranks vary by army. In speech they are usually said with q.")
        GAWord("cadet — تلميذ ضابط — tilmiiz daabit;  also طالب ضابط — taalib daabit  (varies by army)")
        GAWord("private — جندي — jundi;  private first class: جندي أول — jundi awwal")
        GAWord("corporal — عريف — 3ariif")
        GAWord("sergeant — رقيب — raqiib")
        GAWord("lieutenant — ملازم — mulaazim  (first lieutenant: ملازم أول — mulaazim awwal)")
        GAWord("captain — نقيب — naqiib")
        GAWord("major — رائد — raa2id")
        GAWord("lieutenant colonel — مقدم — muqaddam")
        GAWord("colonel — عقيد — 3aqiid")
        GAWord("brigadier — عميد — 3amiid")
        GAWord("major general — لواء — liwaa2")
        GAWord("general — Jordan: فريق — fariiq (lieutenant general), فريق أول — fariiq awwal (general);  Syria: عماد — 3imaad (lieutenant general), عماد أول — 3imaad awwal (general);  everyday: جنرال — jeneraal")
        GAWord("field marshal (the top rank) — مشير — mushiir")
        GAWord("commander — قائد — qaa2id;  commander-in-chief: القائد العام — el-qaa2id el-3aam")
        GAExample("huwwe naqiib bil-jeish — هو نقيب بالجيش — He is a captain in the army.")

        Spacer(modifier = Modifier.height(24.dp))
    }
}


@Composable
private fun GASectionHeader(text: String) {
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
private fun GAWord(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        fontWeight = FontWeight.SemiBold,
        modifier = Modifier.padding(top = 6.dp, bottom = 2.dp)
    )
}

@Composable
private fun GAInfo(text: String) {
    Text(
        text = "    $text",
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(vertical = 1.dp)
    )
}

@Composable
private fun GAExample(text: String) {
    Text(
        text = "    e.g. $text",
        style = MaterialTheme.typography.bodyMedium,
        fontStyle = FontStyle.Italic,
        modifier = Modifier.padding(vertical = 1.dp)
    )
}

@Composable
private fun GASmall(text: String) {
    Text(
        text = "    $text",
        style = MaterialTheme.typography.bodySmall,
        modifier = Modifier.padding(vertical = 2.dp)
    )
}

@Composable
private fun GANote(text: String) {
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
