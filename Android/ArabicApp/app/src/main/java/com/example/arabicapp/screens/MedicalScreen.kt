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
fun MedicalScreen() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        MDNote("Medical words are a mix of everyday Levantine and formal Arabic, and doctors often use the English (or, in Israel, Hebrew) term. Palestinian first; Lebanese/Syrian variants where they differ.")

        MDSectionHeader("People and Places")
        MDWord("doctor — دكتور — doktoor  (f: دكتورة — doktoora);  formal: طبيب — tabiib")
        MDInfo("m · 2: دكتورين doktoorein · pl: دكاترة dakaatra")
        MDWord("nurse — ممرض — mumarrid  (f: ممرضة — mumarrida)")
        MDInfo("m · 2: ممرضين mumarridein · pl: ممرضين mumarridiin")
        MDWord("clinic — عيادة — 3iyaade")
        MDInfo("f · 2: عيادتين 3iyaadtein · pl: عيادات 3iyaadaat")
        MDWord("hospital — مستشفى — mustashfa")
        MDInfo("m · 2: مستشفيين mustashfayein · pl: مستشفيات mustashfayaat")
        MDWord("emergency room — الطوارئ — et-tawaari2  (also قسم الطوارئ — 2ism et-tawaari2)")
        MDWord("lab — مختبر — mukhtabar;  Lebanese/Syrian also: مخبر — makhbar")
        MDInfo("m · 2: مختبرين mukhtabarein · pl: مختبرات mukhtabaraat")
        MDWord("ambulance — إسعاف — is3aaf")
        MDWord("pharmacy — صيدلية — Saydaliyye")
        MDInfo("f · 2: صيدليتين Saydaliytein · pl: صيدليات Saydaliyyaat")
        MDWord("pharmacist — صيدلي — Saydali  (f: صيدلانية — Saydalaaniyye);  formal m: صيدلاني — Saydalaani")
        MDSmall("Saydaliyye can mean both 'pharmacy' and 'female pharmacist', so for a woman people usually say Saydalaaniyye (or simply doktoora).")
        MDWord("prescription — وصفة — waSfe  (formal: وصفة طبية — waSfe Tibbiyye);  Egyptian: روشتة — roshetta (from Italian ricetta), understood in the Levant from Egyptian TV")
        MDInfo("f · 2: وصفتين waSiftein · pl: وصفات waSfaat")
        MDExample("laazim nruu7 3al-tawaari2 — لازم نروح عالطوارئ — We have to go to the emergency room.")

        MDSectionHeader("Medicines")
        MDWord("medicine / medication — دوا — dawa")
        MDInfo("m · 2: دواءين dawaa2ein · pl: أدوية adwiye")
        MDWord("pills — حبوب — 7buub  (one pill: حبة — 7abbe)")
        MDWord("antibiotics — مضاد حيوي — mudaad 7ayawi  (pl: مضادات حيوية — mudaadaat 7ayawiyye);  everyday also antibiotic in English")
        MDWord("dose — جرعة — jur3a")
        MDInfo("f · 2: جرعتين jur3itein · pl: جرعات jur3aat")
        MDWord("overdose — جرعة زايدة — jur3a zaayde")
        MDWord("ointment / cream — مرهم — marham")
        MDInfo("m · 2: مرهمين marhamein · pl: مراهم maraahim")
        MDWord("iodine — يود — yood")
        MDWord("antiseptic / disinfectant — معقّم — mu3a22im;  to disinfect: عقّم — 3a22am / y3a22im")
        MDExample("khud 7abbe kull tamaan saa3aat — خود حبة كل تمن ساعات — Take one pill every eight hours.")

        MDSectionHeader("First Aid and Injuries")
        MDWord("first aid — إسعاف أولي — is3aaf awwali")
        MDWord("bandage — ضمادة — dimaade;  gauze: شاش — shaash;  band-aid: لزقة — laz2a")
        MDWord("injury — إصابة — isaabe;  he got injured: انصاب — insaab")
        MDInfo("f · 2: إصابتين isaabtein · pl: إصابات isaabaat")
        MDWord("wound / cut — جرح — jur7")
        MDInfo("m · 2: جرحين jur7ein · pl: جروح jruu7")
        MDWord("blood — دم — damm")
        MDSmall("Hebrew parallel: דם dam.")
        MDWord("broken leg — إجر مكسورة — ijr maksuura  (also رجل — rijl)")
        MDWord("broken arm — إيد مكسورة — iid maksuura")
        MDWord("cast — جبصين — jibsiin  (lit. plaster);  they put a cast on him: 7attuulo jibsiin")
        MDWord("poison / venom — سم — samm  (one word for both)")
        MDWord("venomous snake — حية سامة — 7ayye saamme")
        MDWord("bleeding — نزيف — naziif;  it's bleeding: عم بينزف — 3am binzif")
        MDWord("stitches — قطب — 2uTab  (one stitch: قطبة — 2uTbe);  they stitched it: قطّبوه — 2aTTabuu")
        MDExample("wi2i3t w 2aTTabuuli khams 2uTab — وقعت وقطّبولي خمس قطب — I fell and they gave me five stitches.")
        MDExample("inkasarat iidi w 7attuuli jibsiin — انكسرت إيدي وحطولي جبصين — I broke my arm and they put a cast on it.")

        MDSectionHeader("Symptoms and Illnesses")
        MDWord("fever — حرارة — 7araara;  I have a fever: عندي حرارة — 3indi 7araara;  also سخونة — skhuune")
        MDWord("rash — طفح جلدي — tafa7 jildi;  allergy: حساسية — 7assaasiyye")
        MDWord("infection / inflammation — التهاب — iltihaab")
        MDInfo("m · 2: التهابين iltihaabein · pl: التهابات iltihaabaat")
        MDWord("diabetes — سكري — sukkari;  everyday: عنده سكر — 3indo sukkar (he has diabetes)")
        MDWord("autoimmune disease — مرض مناعي ذاتي — marad manaa3i zaati;  immunity: مناعة — manaa3a")
        MDWord("cancer — سرطان — sarataan")
        MDSmall("Many people avoid saying sarataan aloud and use the euphemism هداك المرض — hadaak el-marad (that disease).")
        MDWord("lungs — رئة — ri2a;  both lungs: رئتين — ri2atein")
        MDWord("virus — فيروس — fayruus  (also a computer virus)")
        MDInfo("m · pl: فيروسات fayruusaat")
        MDWord("bacteria — بكتيريا — bakteerya;  germs: جراثيم — jaraatiim")
        MDWord("sick — مريض — mariid (f: مريضة — mariida);  feeling unwell: تعبان — ta3baan")
        MDWord("it hurts me — بيوجعني — biyooja3ni  (f subject: bitooja3ni)")
        MDExample("raasi biyooja3ni w 3indi 7araara — راسي بيوجعني وعندي حرارة — My head hurts and I have a fever.")

        MDSectionHeader("Treatments")
        MDWord("treatment — علاج — 3ilaaj")
        MDInfo("m · 2: علاجين 3ilaajein · pl: علاجات 3ilaajaat")
        MDWord("injection / shot — إبرة — ibre  (lit. needle);  formal: حقنة — 7u2ne")
        MDInfo("f · 2: إبرتين ibirtein · pl: إبر ibar")
        MDWord("I.V. drip — سيروم — seerum  (everyday);  infusion: تسريب — tasriib (formal)")
        MDWord("chemotherapy — علاج كيماوي — 3ilaaj kiimaawi;  everyday: الكيماوي — el-kiimaawi")
        MDWord("immunotherapy — علاج مناعي — 3ilaaj manaa3i")
        MDWord("radiotherapy — علاج بالأشعة — 3ilaaj bil-ashi33a  (also علاج إشعاعي — 3ilaaj ishaa3i)")
        MDWord("surgery / operation — عملية — 3amaliyye;  he had an operation: عمل عملية — 3imil 3amaliyye")
        MDInfo("f · 2: عمليتين 3amaliytein · pl: عمليات 3amaliyyaat  |  surgeon: جرّاح — jarraa7 (f: jarraa7a)")
        MDExample("7attuulo seerum bil-mustashfa — حطوله سيروم بالمستشفى — They put him on an I.V. at the hospital.")

        MDSectionHeader("Tests")
        MDWord("test / examination — فحص — fa7s")
        MDInfo("m · 2: فحصين fa7sein · pl: فحوصات f7uusaat")
        MDWord("blood test — فحص دم — fa7s damm")
        MDWord("urine sample — عينة بول — 3ayyinet bool")
        MDWord("blood pressure — ضغط الدم — daght ed-damm;  everyday: عنده ضغط — 3indo daght (he has high blood pressure)")
        MDWord("thermometer — ميزان حرارة — miizaan 7araara  (lit. heat scale)")
        MDExample("laazim ta3mil fa7s damm — لازم تعمل فحص دم — You need to do a blood test.")

        MDSectionHeader("At the Dentist")
        MDWord("dentist — دكتور سنان — doktoor snaan  (f: دكتورة سنان — doktoora snaan);  formal: طبيب أسنان — Tabiib asnaan")
        MDWord("cavity / tooth decay — سوس — suus  (lit. woodworm);  formal: تسوّس — tasawwus")
        MDWord("my tooth has a cavity — سني مسوّس — sinni msawwis")
        MDWord("filling — حشوة — 7ashwe")
        MDInfo("f · 2: حشوتين 7ashwitein · pl: حشوات 7ashwaat")
        MDWord("root canal — علاج عصب — 3ilaaj 3aSab  (lit. nerve treatment);  everyday also سحب عصب — sa7b 3aSab (pulling the nerve)")
        MDWord("to pull out a tooth — خلع سن — khala3 sinn;  everyday: قلع — 2ala3 / yi2la3  (he pulled my tooth: 2ala3li sinni)")
        MDExample("ed-doktoor 2alli laazim a3mil 3ilaaj 3aSab — الدكتور قلّي لازم أعمل علاج عصب — The dentist told me I need a root canal.")

        Spacer(modifier = Modifier.height(24.dp))
    }
}


@Composable
private fun MDSectionHeader(text: String) {
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
private fun MDWord(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        fontWeight = FontWeight.SemiBold,
        modifier = Modifier.padding(top = 6.dp, bottom = 2.dp)
    )
}

@Composable
private fun MDInfo(text: String) {
    Text(
        text = "    $text",
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(vertical = 1.dp)
    )
}

@Composable
private fun MDExample(text: String) {
    Text(
        text = "    e.g. $text",
        style = MaterialTheme.typography.bodyMedium,
        fontStyle = FontStyle.Italic,
        modifier = Modifier.padding(vertical = 1.dp)
    )
}

@Composable
private fun MDSmall(text: String) {
    Text(
        text = "    $text",
        style = MaterialTheme.typography.bodySmall,
        modifier = Modifier.padding(vertical = 2.dp)
    )
}

@Composable
private fun MDNote(text: String) {
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
