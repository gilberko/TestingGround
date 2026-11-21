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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun JobsAndOccupationsScreen() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        JoSectionHeader("Healthcare")
        JoBodyText("doctor — دكتور — doktor (m) / دكتورة — doktora (f)")
        JoBodyText("    m: 2 دكتورين doktorein · pl دكاترة dakaatra  |  f: 2 دكتورتين doktortein · pl دكتورات doktoraat")
        JoBodyText("veterinarian — طبيب بيطري — tabib beitari (m) / طبيبة بيطرية — tabibe beitariyye (f)")
        JoBodyText("    m: 2 طبيبين tabibein · pl أطبّاء بيطريين atibba beitariyyiin  |  f: 2 طبيبتين tabibtein · pl طبيبات بيطريات tabibaat beitariyyaat")

        JoBodyText("nurse — ممرّض — mumarriD (m) / ممرّضة — mumarriDa (f)")
        JoBodyText("    m: 2 ممرّضين mumarriDein · pl ممرّضين mumarriDiin  |  f: 2 ممرّضتين mumarriDtein · pl ممرّضات mumarriDaat")
        JoBodyText("pharmacist — صيدلي — Saydali (m) / صيدلية — Saydaliyye (f)  (the pharmacy: صيدلية Saydaliyye too)")
        JoBodyText("    m: 2 صيدليين Saydaliyyein · pl صيادلة Sayaadle  |  f: 2 صيدليتين Saydaliytein · pl صيدليات Saydaliyyaat")

        JoSectionHeader("Engineering and Architecture")
        JoBodyText("engineer — مهندس — muhandis (m) / مهندسة — muhandise (f)")
        JoBodyText("    m: 2 مهندسين muhandsein · pl مهندسين muhandsiin  |  f: 2 مهندستين muhandistein · pl مهندسات muhandisaat")
        JoBodyText("software engineer — مهندس برمجيات — muhandis barmajiyyat (m) / مهندسة برمجيات — muhandise barmajiyyat (f)")
        JoBodyText("    pl: مهندسين برمجيات muhandsiin barmajiyyaat (m) / مهندسات برمجيات muhandisaat barmajiyyaat (f)")
        JoBodyText("architect — مهندس معماري — muhandis mi3mari (m) / مهندسة معمارية — muhandise mi3mariyye (f)")
        JoBodyText("    pl: مهندسين معماريين muhandsiin mi3mariyyiin (m) / مهندسات معماريات muhandisaat mi3mariyyaat (f)")

        JoBodyText("construction (civil) engineer — مهندس مدني — muhandis madani (m) / مهندسة مدنية — muhandse madaniyye (f);  also: مهندس بناء — muhandis binaa2")
        JoBodyText("    pl: مهندسين مدنيين muhandsiin madaniyyiin (m) / مهندسات مدنيات muhandsaat madaniyyaat (f)")

        JoSectionHeader("Legal and Management")
        JoBodyText("lawyer — محامي — mu7ami (m) / محامية — mu7amiyye (f)")
        JoBodyText("    m: 2 محاميين mu7amiyein · pl محامين mu7amiin  |  f: 2 محاميتين mu7amiytein · pl محاميات mu7amiyyaat")
        JoBodyText("manager — مدير — mudir (m) / مديرة — mudire (f)")
        JoBodyText("    m: 2 مديرين mudirein · pl مدرا mudara (also مديرين mudiriin)  |  f: 2 مديرتين mudirtein · pl مديرات mudiraat")
        JoBodyText("politician — سياسي — siyasi (m) / سياسية — siyasiyye (f)")
        JoBodyText("    m: 2 سياسيين siyasiyyein · pl سياسيين siyasiyyiin  |  f: 2 سياسيتين siyasiytein · pl سياسيات siyasiyyaat")

        JoBodyText("judge — قاضي — 2aaDi (m) / قاضية — 2aaDye (f)")
        JoBodyText("    m: 2 قاضيين 2aaDiyein · pl قضاة 2uDaa  |  f: 2 قاضيتين 2aaDyitein · pl قاضيات 2aaDyaat")

        JoSectionHeader("Business and Commerce")
        JoBodyText("merchant — تاجر — tajer (m) / تاجرة — tajere (f)")
        JoBodyText("    m: 2 تاجرين tajrein · pl تجّار tujjaar  |  f: 2 تاجرتين tajirtein · pl تاجرات tajraat")
        JoBodyText("businessman — رجل أعمال — rijjal a3mal (m) / امرأة أعمال — imra2et a3mal (f)")
        JoBodyText("    pl: رجال أعمال rjaal a3maal (m) / سيدات أعمال sayyidaat a3maal (f)")
        JoBodyText("salesperson — بيّاع — bayyaa3 (m) / بيّاعة — bayyaa3a (f)")
        JoBodyText("    m: 2 بيّاعين bayyaa3ein · pl بيّاعين bayyaa3iin  |  f: 2 بيّاعتين bayyaa3tein · pl بيّاعات bayyaa3aat")
        JoBodyText("vendor — بائع — baaye3 (m) / بائعة — baay3a (f)  (street vendor / stall seller)")
        JoBodyText("    m: 2 بائعين baay3ein · pl بائعين baay3iin  |  f: 2 بائعتين baay3itein · pl بائعات baay3aat")
        JoBodyText("cashier — كاشير — kashir (m) / كاشيرة — kashira (f); also: صراف — sarraf")
        JoBodyText("    m: 2 كاشيرين kashirein · pl كاشيرية kashiriyye  |  f: 2 كاشيرتين kashirtein · pl كاشيرات kashiraat")

        JoBodyText("accountant — محاسب — mu7aasib (m) / محاسبة — mu7aasbe (f)")
        JoBodyText("    m: 2 محاسبين mu7aasbein · pl محاسبين mu7aasbiin  |  f: 2 محاسبتين mu7aasibtein · pl محاسبات mu7aasbaat")
        JoBodyText("secretary — سكرتير — sikirteir (m) / سكرتيرة — sikirteira (f)")
        JoBodyText("    m: 2 سكرتيرين sikirteirein · pl سكرتيرية sikirteiriyye  |  f: 2 سكرتيرتين sikirteirtein · pl سكرتيرات sikirteiraat")
        JoBodyText("marketing — تسويق — taswii2;  marketing person: موظّف تسويق — mwaZZaf taswii2 (m) / موظّفة تسويق — mwaZZafet taswii2 (f)")
        JoBodyText("    marketing manager: مدير تسويق — mudiir taswii2 (m) / مديرة تسويق — mudiiret taswii2 (f) · e.g. bishtighil bit-taswii2 — he works in marketing")

        JoSectionHeader("Property and Appraisal")
        JoBodyText("real estate agent / realtor — سمسار عقارات — simsaar 3a2aaraat (m) / سمسارة عقارات — simsaara 3a2aaraat (f)  (broker/agent)")
        JoBodyText("    m: 2 سمسارين simsaarein · pl سماسرة samaasra  |  f: 2 سمسارتين simsaartein · pl سمسارات simsaaraat")
        JoBodyText("car appraiser — مثمّن سيارات — muthammin sayyaaraat (m) / مثمّنة سيارات — muthammne sayyaaraat (f)")
        JoBodyText("    m: 2 مثمّنين muthamminein · pl مثمّنين muthamminiin  |  f: 2 مثمّنتين muthammintein · pl مثمّنات muthammnaat")
        JoBodyText("real estate appraiser — مثمّن عقارات — muthammin 3a2aaraat (m) / مثمّنة عقارات — muthammne 3a2aaraat (f)")
        JoBodyText("    m: 2 مثمّنين muthamminein · pl مثمّنين muthamminiin  |  f: 2 مثمّنتين muthammintein · pl مثمّنات muthammnaat")

        JoSectionHeader("Trades and Services")
        JoBodyText("construction worker — عامل بناء — 3amel bina (m) / عاملة بناء — 3amle bina (f)")
        JoBodyText("    m: 2 عاملين 3amlein · pl عمّال 3ummaal  |  f: 2 عاملتين 3amiltein · pl عاملات 3amlaat")
        JoBodyText("barber — حلاق — 7allaa2 (m) / حلاقة — 7allaa2a (f)")
        JoBodyText("    m: 2 حلاقين 7allaa2ein · pl حلاقين 7allaa2iin  |  f: 2 حلاقتين 7allaa2tein · pl حلاقات 7allaa2aat")
        JoBodyText("mechanic — ميكانيكي — mikaniiki (m) / ميكانيكية — mikaniikiyye (f, rare but correct)")
        JoBodyText("    m: 2 ميكانيكيين mikaniikiyyein · pl ميكانيكية mikaniikiyye  |  f: pl ميكانيكيات mikaniikiyyaat")
        JoBodyText("tire repairman — بنشرجي — banshirji (m) / بنشرجية — banshirjiyye (f, rare)  (fixes flat tires: بنشر banshar = a flat tire)")
        JoBodyText("    m · pl: بنشرجية banshirjiyye")
        JoBodyText("driver — سوّاق — sawwaa2 (m) / سوّاقة — sawwaa2a (f);  formal: سائق — saa2i2")
        JoBodyText("    m: 2 سوّاقين sawwaa2ein · pl سوّاقين sawwaa2iin  |  f: 2 سوّاقتين sawwaa2tein · pl سوّاقات sawwaa2aat")
        JoBodyText("security guard — حارس — 7aaris (m) / حارسة — 7aarse (f);  in Israel also: سكيوريتي — sekyuuriti / شومير — shomer (from Hebrew)")
        JoBodyText("    m: 2 حارسين 7aarsein · pl حرّاس 7urraas  |  f: 2 حارستين 7aaristein · pl حارسات 7aarsaat")
        JoBodyText("gardener — جنيناتي — jneinaati (m) / جنيناتية — jneinaatiyye (f);  formal: بستاني — bustaani")
        JoBodyText("    m: 2 جنيناتيين jneinaatiyyein · pl جنيناتية jneinaatiyye  |  f: pl جنيناتيات jneinaatiyyaat")

        JoSectionHeader("Education")
        JoBodyText("teacher — معلم — mu3allim (m) / معلمة — mu3allme (f)")
        JoBodyText("    m: 2 معلمين mu3allmein · pl معلمين mu3allmiin  |  f: 2 معلمتين mu3allimtein · pl معلمات mu3allmaat")
        JoBodyText("professor — بروفيسور — profissor (m) / بروفيسورة — profissora (f); also: أستاذ جامعي — ustaz jami3i (m) / أستاذة جامعية — ustaze jami3iyye (f)")
        JoBodyText("    pl: بروفيسورات profissoraat / أساتذة جامعيين asaatze jaami3iyyiin (m) / أستاذات ustazaat (f)")
        JoBodyText("student — طالب — taleb (m) / طالبة — talebe (f)")
        JoBodyText("    m: 2 طالبين talbein · pl طلاب tullaab  |  f: 2 طالبتين talibtein · pl طالبات taalbaat")

        JoSectionHeader("Media and Arts")
        JoBodyText("journalist — صحفي — Sa7afi (m) / صحفية — Sa7afiyye (f)")
        JoBodyText("    m: 2 صحفيين Sa7afiyyein · pl صحفيين Sa7afiyyiin  |  f: 2 صحفيتين Sa7afiytein · pl صحفيات Sa7afiyyaat")
        JoBodyText("actor / actress — ممثّل — mumassil (m) / ممثّلة — mumassle (f)")
        JoBodyText("    m: 2 ممثّلين mumasslein · pl ممثّلين mumassliin  |  f: 2 ممثّلتين mumassiltein · pl ممثّلات mumasslaat")
        JoBodyText("singer — مغنّي — mghanni (m) / مغنّية — mghanniye (f);  formal: مطرب — muTrib (m) / مطربة — muTribe (f)")
        JoBodyText("    m: 2 مغنّيين mghanniyein · pl مغنّيين mghanniyiin  |  f: 2 مغنّيتين mghanniytein · pl مغنّيات mghanniyaat")
        JoBodyText("writer / author — كاتب — kaatib (m) / كاتبة — kaatbe (f)")
        JoBodyText("    m: 2 كاتبين kaatbein · pl كتّاب kuttaab  |  f: 2 كاتبتين kaatibtein · pl كاتبات kaatbaat")
        JoBodyText("poet (משורר) — شاعر — shaa3ir (m) / شاعرة — shaa3ra (f)")
        JoBodyText("    m: 2 شاعرين shaa3rein · pl شعرا shu3ara  |  f: 2 شاعرتين shaa3irtein · pl شاعرات shaa3raat")
        JoBodyText("    poetry: شعر — shi3r (not related to Hebrew שיר: shaa3ir comes from the root ش-ع-ر, \"to feel / sense\", as in شعور shu3uur = feeling);  a poem: قصيدة — 2aSiide")

        JoSectionHeader("Sports and Fitness")
        JoBodyText("athlete — رياضي — riyadi (m) / رياضية — riadiyye (f)")
        JoBodyText("    m: 2 رياضيين riyadiyyein · pl رياضيين riyadiyyiin  |  f: 2 رياضيتين riyadiytein · pl رياضيات riyadiyyaat")
        JoBodyText("trainer / coach — مدرّب — mudarrib (m) / مدرّبة — mudarrbe (f)")
        JoBodyText("    m: 2 مدرّبين mudarrbein · pl مدرّبين mudarrbiin  |  f: 2 مدرّبتين mudarribtein · pl مدرّبات mudarrbaat")

        JoSectionHeader("Security and Military")
        JoBodyText("soldier — جندي — jundi (m) / جندية — jundiyye (f)")
        JoBodyText("    m: 2 جنديين jundiyyein · pl جنود jnuud  |  f: 2 جنديتين jundiytein · pl جنديات jundiyyaat")
        JoBodyText("policeman / policewoman — شرطي — shurTi (m) / شرطية — shurtiyye (f)")
        JoBodyText("    m: 2 شرطيين shurtiyyein · pl شرطيين shurtiyyiin (the police = الشرطة ish-shurta)  |  f: 2 شرطيتين shurtiytein · pl شرطيات shurtiyyaat")

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun JoSectionHeader(text: String) {
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
private fun JoBodyText(text: String) {
    Text(
        text = richText(boldHeadwordLine(text)),
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(vertical = 2.dp)
    )
}
