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
fun TourismTravelScreen() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        TTNote("Words and phrases for trips abroad and around the region. Palestinian first; Lebanese/Syrian variants where they differ. A lot of travel vocabulary is borrowed from French or English (basbor, asanseer, taksi), especially in Lebanon.")

        TTSectionHeader("Tourism")
        TTWord("tourist — سائح — saayi7  (f: سائحة — saay7a)")
        TTInfo("m · pl: سيّاح siyyaa7")
        TTWord("tourism — سياحة — siyaa7a;  touristy: سياحي — siyaa7i")
        TTWord("vacation / holiday — عطلة — 3uTle;  leave from work: إجازة — ijaaze")
        TTWord("resort — منتجع — muntaja3;  ski resort: منتجع تزلج — muntaja3 tazalluj")
        TTWord("hotel — فندق — funduq  (Lebanese also أوتيل — oteel)")
        TTWord("beach — شط — shaTT  (formal شاطئ — shaaTi2)")
        TTExample("ra7 nsaafir 3a lubnaan bil-3uTle — رح نسافر على لبنان بالعطلة — We're traveling to Lebanon on vacation.")

        TTSectionHeader("Booking and Tickets")
        TTWord("reservation / booking — حجز — 7ajz  (one word for both)")
        TTInfo("m · pl: حجوزات 7ujuuzaat")
        TTWord("to book — حجز — 7ajaz / yi7jiz;  to book a hotel: حجز فندق — 7ajaz funduq")
        TTWord("ticket — تذكرة — tazkara  (pl: تذاكر tazaakir);  Lebanese/Syrian also: بطاقة — biTaa2a / تيكت — tiket")
        TTWord("entrance ticket (to a site, museum…) — تذكرة دخول — tazkaret dukhuul")
        TTWord("train ticket — تذكرة قطار — tazkaret 2iTaar")
        TTWord("bus ticket — تذكرة باص — tazkaret baaS")
        TTWord("flight ticket — تذكرة طيارة — tazkaret Tayyaara")
        TTExample("biddi a7jiz ghurfe la-shakhSein — بدي أحجز غرفة لشخصين — I'd like to book a room for two.")

        TTSectionHeader("Flying")
        TTWord("airplane — طيارة — Tayyaara  (formal طائرة — Taa2ira)")
        TTWord("airport — مطار — maTaar")
        TTWord("flight — رحلة — ri7le;  formal: رحلة طيران — ri7let Tayaraan")
        TTWord("to fly — طار — Taar / yTiir  (for a bird or a plane);  for people: سافر بالطيارة — saafar bit-Tayyaara")
        TTWord("luggage — شنط — shunaT  (one suitcase: شنطة — shanTa);  formal: أمتعة — amti3a")
        TTWord("flight delay — تأخير — ta2khiir;  the flight is delayed: الرحلة متأخرة — er-ri7le mit2akhkhra")
        TTWord("cancelled flight — رحلة ملغية — ri7le malghiyye;  they cancelled the flight: لغوا الرحلة — laghu er-ri7le")
        TTWord("passport — جواز سفر — jawaaz safar  (often just jawaaz);  everyday: باسبور — basbor")
        TTSmall("basbor comes from French passeport; it's common in Lebanon and Syria and heard everywhere. jawaaz safar is the official word, printed on the passport itself.")
        TTWord("border — حدود — 7uduud  (pl. form used for 'the border');  border crossing: معبر — ma3bar")
        TTExample("er-ri7le mit2akhkhra saa3tein — الرحلة متأخرة ساعتين — The flight is delayed by two hours.")

        TTSectionHeader("Getting Around")
        TTWord("taxi — تكسي — taksi;  shared taxi (fixed route): سرفيس — servees")
        TTWord("bus — باص — baaS")
        TTWord("train — قطار — 2iTaar  (Lebanese also ترين — treen)")
        TTWord("bus stop — محطة باص — m7aTTet baaS")
        TTWord("train station — محطة قطار — m7aTTet 2iTaar")
        TTWord("to rent a car — أجّر سيارة — ajjar sayyaara;  formal: استأجر — ista2jar")
        TTWord("car rental — تأجير سيارات — ta2jiir sayyaaraat")
        TTWord("driver's license — رخصة سواقة — rukhSet swaa2a  (formal: رخصة قيادة — rukhSet 2iyaade);  in Lebanon also heard: دفتر سواقة — daftar swaa2a")
        TTWord("elevator — مصعد — maS3ad;  Lebanese/Syrian: أسانسير — asanseer")
        TTExample("wein m7aTTet el-baaS? — وين محطة الباص؟ — Where is the bus stop?")

        TTSectionHeader("At the Hotel")
        TTWord("room — غرفة — ghurfe  (also pronounced ghorfe: same word, different vowel);  Lebanese/Syrian also: أوضة — 2ooDa")
        TTInfo("f · 2: غرفتين ghurftein · pl: غرف ghuraf")
        TTWord("shower — دوش — duush")
        TTWord("bath / bathtub — بانيو — baanyo")
        TTWord("view — منظر — manZar;  a room with a sea view: غرفة مع منظر عالبحر — ghurfe ma3 manZar 3al-ba7r")
        TTWord("first floor — الطابق الأول — eT-Taabi2 el-awwal;  second floor — الطابق التاني — eT-Taabi2 et-taani")
        TTWord("How much for one night? — قديش لليلة وحدة؟ — 2addeish la-leile wa7de?")
        TTWord("How much for the night? — قديش الليلة؟ — 2addeish el-leile?")
        TTWord("The price is 300 dollars per night — السعر تلت مية دولار بالليلة — es-si3r tlat miit dollaar bil-leile")
        TTWord("How can I help you? — كيف بقدر أساعدك؟ — kiif ba2dar asaa3dak? (to m) / asaa3dik? (to f);  Lebanese/Syrian: كيف فيني ساعدك؟ — kiif fiini saa3dak?")
        TTWord("Do you need any help? — بدّك مساعدة؟ — biddak musaa3ade? (to m) / biddik musaa3ade? (to f)")
        TTWord("What can I do for you? — شو بتأمري؟ — shu bétamri? (to f) / شو بتأمر؟ — shu bétamor? (to m);  Palestinian: shu bto2mori? / shu bto2mor?")
        TTSmall("Literally \"what do you command?\", a very polite thing for a receptionist, waiter or shopkeeper to say. Your \"shu bétamri\" is the Lebanese way to say it to a woman. The é is how Lebanese writing in Latin letters marks a short e sound, like a Hebrew segol (בֶּ). In speech the 2 (hamza) of bit2amri drops, so it comes out as bé-tamri. A shopkeeper may also just say 2u2mor! (\"at your service\"). Not confirmed by a dictionary source: the m form bétamor follows the same pattern but wasn't checked, and the vowels vary between speakers (bta2mor, bto2mor).")
        TTWord("Do you accept credit cards? — بتقبلو بطاقة ائتمان؟ — bti2balu biTaa2it i2timaan?;  everyday: بتاخدو فيزا؟ — btaakhdu viiza?")
        TTWord("Do you accept cheques? — بتقبلو شيكات؟ — bti2balu sheekaat?")
        TTWord("Can I pay with a credit card? — بقدر أدفع بالفيزا؟ — ba2dar adfa3 bil-viiza?;  Lebanese/Syrian: فيني إدفع بالكارت؟ — fiini idfa3 bil-kart?")
        TTWord("excuse me — لو سمحت — law sama7t (to m) / law sama7ti (to f)")
        TTWord("sorry — آسف — aasif (m speaker) / آسفة — aasfe (f speaker)")
        TTWord("never mind / it's OK — معلش — ma3leesh  (more uses: First Words → Courtesy)")
        TTExample("fi 3indkum ghurfe la-leile wa7de? — في عندكم غرفة لليلة وحدة؟ — Do you have a room for one night?")

        TTSectionHeader("Money")
        TTWord("exchange rate — سعر الصرف — si3r eS-Sarf")
        TTWord("to change money — صرّف مصاري — Sarraf maSaari / ySarrif")
        TTWord("money changer — صرّاف — Sarraaf;  exchange office: محل صرافة — ma7all Siraafe")
        TTExample("addeish si3r eS-Sarf el-yoom? — قديش سعر الصرف اليوم؟ — What's the exchange rate today?")

        TTSectionHeader("Sights and Activities")
        TTWord("sights / landmarks — معالم — ma3aalim;  tourist sights: معالم سياحية — ma3aalim siyaa7iyye")
        TTWord("attractions — أماكن سياحية — amaakin siyaa7iyye  (lit. tourist places)")
        TTWord("sightseeing / a tour — جولة سياحية — jawle siyaa7iyye")
        TTWord("diving — غطس — ghaTs;  to dive: غطس — ghaTas / yighTas")
        TTWord("scuba diving — غوص — ghooS  (with a tank: bi-anbuubet oksijiin)")
        TTWord("skiing — تزلج — tazalluj;  to ski: تزلّج — tzallaj / yitzallaj")
        TTExample("shu el-ma3aalim es-siyaa7iyye hoon? — شو المعالم السياحية هون؟ — What are the sights here?")

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun TTSectionHeader(text: String) {
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
private fun TTWord(text: String) {
    Text(
        text = fixArabizi(text),
        style = MaterialTheme.typography.bodyMedium,
        fontWeight = FontWeight.SemiBold,
        modifier = Modifier.padding(top = 6.dp, bottom = 2.dp)
    )
}

@Composable
private fun TTInfo(text: String) {
    Text(
        text = fixArabizi("    $text"),
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(vertical = 1.dp)
    )
}

@Composable
private fun TTExample(text: String) {
    Text(
        text = fixArabizi("    e.g. $text"),
        style = MaterialTheme.typography.bodyMedium,
        fontStyle = FontStyle.Italic,
        modifier = Modifier.padding(vertical = 1.dp)
    )
}

@Composable
private fun TTSmall(text: String) {
    Text(
        text = fixArabizi("    $text"),
        style = MaterialTheme.typography.bodySmall,
        modifier = Modifier.padding(vertical = 2.dp)
    )
}

@Composable
private fun TTNote(text: String) {
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
