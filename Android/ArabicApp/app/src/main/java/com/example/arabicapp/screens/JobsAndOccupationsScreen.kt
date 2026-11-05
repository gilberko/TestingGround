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

        JoSectionHeader("Engineering and Architecture")
        JoBodyText("engineer — مهندس — muhandis (m) / مهندسة — muhandise (f)")
        JoBodyText("    m: 2 مهندسين muhandsein · pl مهندسين muhandsiin  |  f: 2 مهندستين muhandistein · pl مهندسات muhandisaat")
        JoBodyText("software engineer — مهندس برمجيات — muhandis barmajiyyat (m) / مهندسة برمجيات — muhandise barmajiyyat (f)")
        JoBodyText("    pl: مهندسين برمجيات muhandsiin barmajiyyaat (m) / مهندسات برمجيات muhandisaat barmajiyyaat (f)")
        JoBodyText("architect — مهندس معماري — muhandis mi3mari (m) / مهندسة معمارية — muhandise mi3mariyye (f)")
        JoBodyText("    pl: مهندسين معماريين muhandsiin mi3mariyyiin (m) / مهندسات معماريات muhandisaat mi3mariyyaat (f)")

        JoSectionHeader("Legal and Management")
        JoBodyText("lawyer — محامي — mu7ami (m) / محامية — mu7amiyye (f)")
        JoBodyText("    m: 2 محاميين mu7amiyein · pl محامين mu7amiin  |  f: 2 محاميتين mu7amiytein · pl محاميات mu7amiyyaat")
        JoBodyText("manager — مدير — mudir (m) / مديرة — mudire (f)")
        JoBodyText("    m: 2 مديرين mudirein · pl مدرا mudara (also مديرين mudiriin)  |  f: 2 مديرتين mudirtein · pl مديرات mudiraat")
        JoBodyText("politician — سياسي — siyasi (m) / سياسية — siyasiyye (f)")
        JoBodyText("    m: 2 سياسيين siyasiyyein · pl سياسيين siyasiyyiin  |  f: 2 سياسيتين siyasiytein · pl سياسيات siyasiyyaat")

        JoSectionHeader("Business and Commerce")
        JoBodyText("merchant — تاجر — tajer (m) / تاجرة — tajere (f)")
        JoBodyText("    m: 2 تاجرين tajrein · pl تجّار tujjaar  |  f: 2 تاجرتين tajirtein · pl تاجرات tajraat")
        JoBodyText("businessman — رجل أعمال — rijjal a3mal (m) / امرأة أعمال — imra2et a3mal (f)")
        JoBodyText("    pl: رجال أعمال rjaal a3maal (m) / سيدات أعمال sayyidaat a3maal (f)")
        JoBodyText("salesperson — بيّاع — bayyya3 (m) / بيّاعة — bayyya3a (f)")
        JoBodyText("    m: 2 بيّاعين bayyaa3ein · pl بيّاعين bayyaa3iin  |  f: 2 بيّاعتين bayyaa3tein · pl بيّاعات bayyaa3aat")
        JoBodyText("vendor — بائع — baaye3 (m) / بائعة — baay3a (f)  (street vendor / stall seller)")
        JoBodyText("    m: 2 بائعين baay3ein · pl بائعين baay3iin  |  f: 2 بائعتين baay3itein · pl بائعات baay3aat")
        JoBodyText("cashier — كاشير — kashir (m) / كاشيرة — kashira (f); also: صراف — sarraf")
        JoBodyText("    m: 2 كاشيرين kashirein · pl كاشيرية kashiriyye  |  f: 2 كاشيرتين kashirtein · pl كاشيرات kashiraat")

        JoSectionHeader("Property and Appraisal")
        JoBodyText("real estate agent / realtor — سمسار عقارات — simsar 3a2araat  (broker/agent)")
        JoBodyText("    m · 2: سمسارين simsarein · pl: سماسرة samaasra")
        JoBodyText("car appraiser — مثمّن سيارات — muthammin sayyarat")
        JoBodyText("    m · 2: مثمّنين muthamminein · pl: مثمّنين muthamminiin")
        JoBodyText("real estate appraiser — مثمّن عقارات — muthammin 3a2araat")
        JoBodyText("    m · 2: مثمّنين muthamminein · pl: مثمّنين muthamminiin")

        JoSectionHeader("Trades and Services")
        JoBodyText("construction worker — عامل بناء — 3amel bina (m) / عاملة بناء — 3amle bina (f)")
        JoBodyText("    m: 2 عاملين 3amlein · pl عمّال 3ummaal  |  f: 2 عاملتين 3amiltein · pl عاملات 3amlaat")
        JoBodyText("barber — حلاق — 7allaa2 (m) / حلاقة — 7allaa2a (f)")
        JoBodyText("    m: 2 حلاقين 7allaa2ein · pl حلاقين 7allaa2iin  |  f: 2 حلاقتين 7allaa2tein · pl حلاقات 7allaa2aat")
        JoBodyText("mechanic — ميكانيكي — mikaniki; also colloquially: بنشرجي — banshirji")
        JoBodyText("    m · 2: ميكانيكيين mikanikiyyein · pl: ميكانيكية mikanikiyye (banshirji → بنشرجية banshirjiyye)")

        JoSectionHeader("Education")
        JoBodyText("teacher — معلم — mu3allam (m) / معلمة — mu3allame (f)")
        JoBodyText("    m: 2 معلمين mu3allmein · pl معلمين mu3allmiin  |  f: 2 معلمتين mu3allimtein · pl معلمات mu3allmaat")
        JoBodyText("professor — بروفيسور — profissor; also: أستاذ جامعي — ustaz jami3i (m) / أستاذة جامعية — ustaze jami3iyye (f)")
        JoBodyText("    pl: بروفيسورات profissoraat / أساتذة جامعيين asaatze jaami3iyyiin (m) / أستاذات ustazaat (f)")
        JoBodyText("student — طالب — taleb (m) / طالبة — talebe (f)")
        JoBodyText("    m: 2 طالبين talbein · pl طلاب tullaab  |  f: 2 طالبتين talibtein · pl طالبات taalbaat")

        JoSectionHeader("Sports and Fitness")
        JoBodyText("athlete — رياضي — riyadi (m) / رياضية — riadiyye (f)")
        JoBodyText("    m: 2 رياضيين riyadiyyein · pl رياضيين riyadiyyiin  |  f: 2 رياضيتين riyadiytein · pl رياضيات riyadiyyaat")
        JoBodyText("trainer / coach — مدرّب — mudarrib (m) / مدرّبة — mudarrabe (f)")
        JoBodyText("    m: 2 مدرّبين mudarrbein · pl مدرّبين mudarrbiin  |  f: 2 مدرّبتين mudarribtein · pl مدرّبات mudarrbaat")

        JoSectionHeader("Security and Military")
        JoBodyText("soldier — جندي — jundi (m) / جندية — jundiyye (f)")
        JoBodyText("    m: 2 جنديين jundiyyein · pl جنود jnuud  |  f: 2 جنديتين jundiytein · pl جنديات jundiyyaat")
        JoBodyText("policeman / policewoman — شرطي — shurtyi (m) / شرطية — shurtiyye (f)")
        JoBodyText("    m: 2 شرطيين shurtiyyein · pl شرطيين shurtiyyiin (the police = الشرطة ish-shurta)  |  f: 2 شرطيتين shurtiytein · pl شرطيات shurtiyyaat")

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun JoSectionHeader(text: String) {
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
private fun JoBodyText(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(vertical = 2.dp)
    )
}
