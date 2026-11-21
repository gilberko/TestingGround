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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun TransportationScreen() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        TrSectionHeader("Vehicles")
        TrBodyText("car — سيارة — sayyara")
        TrBodyText("    f · 2: سيارتين sayyartein · pl: سيارات sayyaraat")
        TrBodyText("motorbike / bike — موتو — moto; also: دراجة نارية — darraje nariyye")
        TrBodyText("    m · 2: تنين موتو tnein moto (loanword — the dual ending is rarely used) · pl: موتورات motoraat")
        TrBodyText("bicycle — بسكليت — basklit; also: دراجة هوائية — darraje hawwayye")
        TrBodyText("    m · 2: بسكليتين basklitein · pl: بسكليتات basklitaat")
        TrBodyText("bus — باص — bas")
        TrBodyText("    m · 2: باصين basein · pl: باصات basaat")
        TrBodyText("train — قطار — 2atar  (qaf → glottal stop)")
        TrBodyText("    m · 2: قطارين 2atarein · pl: قطارات 2ataraat")
        TrBodyText("tram — ترام — tram")
        TrBodyText("    m · 2: تنين ترام tnein tram (loanword) · pl: ترامات tramaat")
        TrBodyText("plane — طيارة — tayyara")
        TrBodyText("    f · 2: طيارتين tayyartein · pl: طيارات tayyaraat")
        TrBodyText("boat — قارب — 2arib")
        TrBodyText("    m · 2: قاربين 2aribein · pl: قوارب 2awaarib")
        TrBodyText("ferry — معدية — ma3adiyye; also: فيري — firi")
        TrBodyText("    f · 2: معديتين ma3adiytein · pl: معديات ma3adiyyaat")
        TrBodyText("ship — باخرة — bakhira; also: سفينة — safine")
        TrBodyText("    f · 2: باخرتين bakhirtein · pl: بواخر bawaakhir")

        TrSectionHeader("People")
        TrBodyText("pedestrian — ماشي — mashi (on foot); مشاة — mushsha (pedestrians, pl)")
        TrBodyText("    m · 2: ماشيين mashyein · pl: مشاة mushaa (as above)")

        TrSectionHeader("Verbs")
        TrBodyText("to drive — يسوق — ysu2  (qaf → glottal stop; present: بيسوق — byisu2)")
        TrBodyText("to fly — يطير — ytir  (present: بيطير — bytir)")
        TrBodyText("to land — ينزل — yinzal  (present: بينزل — byinzal)")
        TrBodyText("to buy a ticket — يشتري تذكرة — yishtri tazkara")

        TrSectionHeader("Tickets")
        TrBodyText("ticket — تذكرة — tazkara")
        TrBodyText("    f · 2: تذكرتين tazkartein · pl: تذاكر tazaakir")
        TrBodyText("train ticket — تذكرة قطار — tazkaret 2atar")
        TrBodyText("    f · 2: تذكرتين قطار tazkartein 2atar · pl: تذاكر قطار tazaakir 2atar")
        TrBodyText("bus ticket — تذكرة باص — tazkaret bas")
        TrBodyText("    f · 2: تذكرتين باص tazkartein bas · pl: تذاكر باص tazaakir bas")

        TrSectionHeader("Roads and Streets")
        TrWord("**road** — **طريق** — **Tarii2**")
        TrInfo("m/f · pl: طرق Turu2 · on the way: bi-T-Tarii2")
        TrWord("**street** — **شارع** — **shaari3**")
        TrInfo("m · 2: شارعين shaar3ein · pl: شوارع shawaari3")
        TrWord("**traffic jam** — **أزمة سير** — **azmet seir**;  short: **أزمة** — **azme** (Pal/Jordanian)")
        TrWord("Leb/Syr: **عجقة** — **3aj2a**;  also: **زحمة** — **zaHme** (crowding, traffic)")
        TrSmall("3aj2a is Lebanese/Syrian and azmet seir Jordanian (thelevantongue); zaHme / zaHmet seir is listed for South Levantine (Wiktionary). Palestinians use azme and zaHme.")
        TrExample("fi azme kbiire 3a-T-Tarii2 — في أزمة كبيرة عالطريق — There's a big traffic jam on the road.")
        TrWord("**traffic** — **سير** — **seir**;  also: **حركة السير** — **7arakt es-seir**")
        TrInfo("m · Wiktionary (South Levantine): seir = traffic, synonym azme")
        TrExample("es-seir waa2if — السير واقف — The traffic is stuck (lit. \"standing\").")
        TrWord("**lane** — **مسار** — **masaar**;  everyday: **خطّ** — **khaTT**")
        TrInfo("m · pl: مسارات masaaraat · the left lane: el-masaar esh-shmaal · khaTT: commonly heard, not confirmed by a source")
        TrWord("**boulevard / avenue** — **جادّة** — **jaadde**  (formal, mostly in street names);  everyday: **شارع عريض** — **shaari3 3ariiD**")
        TrWord("**roundabout** — **دوّار** — **dawwaar**")
        TrInfo("m · pl: دوّارات dawwaaraat")
        TrWord("**corner** — **زاوية** — **zaawye**;  also: **قرنة** — **2urne** (Leb 2orne)")
        TrInfo("at the corner of the street: 3a zaawyet esh-shaari3 / 3a 2urnet esh-shaari3 · 2urne: commonly heard, not confirmed by a source")
        TrWord("**bridge** — **جسر** — **jisr**")
        TrInfo("m · 2: جسرين jisrein · pl: جسورة jsuura (Levantine, Wiktionary);  formal: جسور jusuur")
        TrWord("**tunnel** — **نفق** — **nafa2**")
        TrInfo("m · pl: أنفاق anfaa2")
        TrWord("**traffic light** — **إشارة** — **ishaara**;  also: **ضو** — **Daww** (lit. \"light\")")
        TrInfo("Wiktionary lists Daww = traffic light for both Palestinian and Lebanese; Arabic speakers in Israel also say رمزور — ramzoor (from Hebrew רמזור)")
        TrInfo("f · pl: إشارات ishaaraat · at the light, turn right: 3and el-ishaara liff yamiin · red light: ishaara 7amra")
        TrWord("**traffic signs** — **إشارات مرور** — **ishaaraat muruur**;  also: **شاخصات** — **shaakhiSaat** (formal)")
        TrWord("**speed limit** — **حدّ السرعة** — **7add es-sur3a**;  also: **السرعة المسموحة** — **es-sur3a el-masmuu7a** (the allowed speed)")

        TrSectionHeader("Navigation and Routes")
        TrWord("**route** — **طريق** — **Tarii2** (the way);  formal: **مسار** — **masaar**")
        TrInfo("bus route / bus line: خطّ الباص — khaTT el-baaS · which way are you taking? min wein raayi7?")
        TrWord("**GPS** — **جي بي إس** — **jii-pii-es**;  everyday also: **ويز** — **Waze**;  formal: **نظام الملاحة** — **niZaam el-milaa7a**")
        TrWord("**to navigate (show the way)** — **دلّ عالطريق** — **dall 3a-T-Tarii2** / bidill")
        TrWord("**to navigate with an app** — **مشي عالـ GPS** — **mishi 3al-GPS**;  also: **mishi 3al-Waze**")
        TrInfo("navigation (formal): ملاحة — milaa7a · mishi 3al-GPS / 3al-Waze: commonly heard, not confirmed by a source")
        TrExample("ana mish 3aarif eT-Tarii2, ra7 amshi 3al-Waze — أنا مش عارف الطريق، رح أمشي عالويز — I don't know the way, I'll navigate with Waze.")
        TrWord("**go through the park** — **اقطع من الجنينة** — **i2Ta3 min el-jneine**  (f: i2Ta3i)")
        TrWord("also: **مرق من الجنينة** — **mur2 min el-jneine** (pass through the park;  f: mur2i)")
        TrInfo("mara2 / byumru2 = pass by / go past (Wiktionary, Levantine) · park: jneine (everyday) / 7adii2a (formal)")

        TrSectionHeader("Getting Around")
        TrWord("**to go by foot / on foot** — **مشي** — **mashi**;  also: **عالإجرين** — **3al-ijrein** (on the feet)")
        TrExample("baruu7 mashi — بروح مشي — I'm going on foot.")
        TrWord("**to go by car** — **بالسيارة** — **bis-sayyaara**")
        TrExample("baruu7 bis-sayyaara — بروح بالسيارة — I'm going by car.  ·  by bus: bil-baaS · by train: bil-2iTaar")
        TrWord("**public transportation** — **مواصلات عامّة** — **muwaaSalaat 3aamme**;  everyday: **المواصلات** — **el-muwaaSalaat**")
        TrWord("**shuttle** — **شاتل** — **shattel**")
        TrSmall("shattel (from English) is commonly heard, not confirmed by a source. Don't confuse it with servees (سرفيس): in Lebanon and Palestine that's a shared taxi on a fixed route.")

        TrSectionHeader("Directions While Driving")
        TrWord("**turn right** — **لفّ يمين** — **liff yamiin**  (f: liffi yamiin;  Leb/Syr: brom 3al-yamiin)")
        TrWord("**turn left** — **لفّ شمال** — **liff shmaal**  (f: liffi shmaal)")
        TrWord("**continue straight** — **كمّل دغري** — **kammil dughri**  (f: kammli dughri;  Leb kammel deghri)")
        TrWord("**go back** — **ارجع** — **irja3**  (f: irja3i)")
        TrWord("**turn back / reverse** — **ارجع لورا** — **irja3 la-wara**  (f: irja3i la-wara)")
        TrWord("**make a U-turn** — **اعمل يو-تيرن** — **i3mal yu-tern**;  also: **لفّ وارجع** — **liff w-irja3**")
        TrSmall("yu-tern (from English) and liff w-irja3 are commonly heard, not confirmed by a source.")
        TrWord("**drive faster** — **سوق أسرع** — **suu2 asra3**  (f: suu2i asra3)")
        TrWord("**drive slower** — **سوق أبطأ** — **suu2 abTa2**;  everyday: **على مهلك** — **3ala mahlak** (take it easy;  f: 3ala mahlik;  Leb 3a mahlak)")
        TrExample("shwayy shwayy, fi raadaar! — شوي شوي، في رادار! — Slow down, there's a speed camera!")
        TrSmall("These match Examples → Directions, which has more full sentences.")

        TrSectionHeader("People and Papers")
        TrWord("**police officer** — **شرطي / شرطية** — **shurTi** (m) / **shurTiyye** (f);  everyday: **بوليس** — **booliis**")
        TrInfo("pl: شرطيين shurTiyyiin · the police: شرطة shurTa · Leb: درك — darak (gendarmerie)")
        TrWord("**pilot** — **طيّار / طيّارة** — **Tayyaar** (m) / **Tayyaara** (f)")
        TrInfo("pl: طيّارين Tayyaariin · Tayyaara also means \"plane\" — context decides")
        TrWord("**steward** — **مضيف** — **muDiif**  ·  **stewardess** — **مضيفة** — **muDiife**")
        TrInfo("flight attendant (formal): مضيف طيران — muDiif Tayaraan / مضيفة طيران — muDiifet Tayaraan")
        TrWord("**driving license** — **رخصة سواقة** — **rukhSet swaa2a**;  formal: **رخصة قيادة** — **rukhSet qiyaade**")
        TrInfo("Leb: دفتر سواقة — daftar swaa2a · to get a license: Talla3 rukhSa")
        TrWord("**speeding ticket** — **مخالفة سرعة** — **mukhaalafet sur3a**;  Leb also: **ضبط** — **Dabt**")
        TrInfo("ticket / fine: مخالفة — mukhaalafe (f · pl mukhaalafaat) · I got a ticket: akalt mukhaalafe (lit. \"I ate a ticket\") · Dabt and akalt: commonly heard, not confirmed by a source")

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun TrSectionHeader(text: String) {
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
private fun TrWord(text: String) {
    Text(
        text = richText(text),
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(top = 6.dp, bottom = 2.dp)
    )
}

@Composable
private fun TrInfo(text: String) {
    Text(
        text = fixArabizi("    $text"),
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(vertical = 1.dp)
    )
}

@Composable
private fun TrExample(text: String) {
    Text(
        text = fixArabizi("    e.g. $text"),
        style = MaterialTheme.typography.bodyMedium,
        fontStyle = FontStyle.Italic,
        modifier = Modifier.padding(vertical = 1.dp)
    )
}

@Composable
private fun TrSmall(text: String) {
    Text(
        text = fixArabizi("    $text"),
        style = MaterialTheme.typography.bodySmall,
        modifier = Modifier.padding(vertical = 2.dp)
    )
}

@Composable
private fun TrBodyText(text: String) {
    Text(
        text = richText(boldHeadwordLine(text)),
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(vertical = 2.dp)
    )
}
