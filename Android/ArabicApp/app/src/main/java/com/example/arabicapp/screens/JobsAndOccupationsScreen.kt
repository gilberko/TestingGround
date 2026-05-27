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
        JoBodyText("veterinarian — طبيب بيطري — tabib beitari (m) / طبيبة بيطرية — tabibe beitariyye (f)")

        JoSectionHeader("Engineering and Architecture")
        JoBodyText("engineer — مهندس — muhandis (m) / مهندسة — muhandise (f)")
        JoBodyText("software engineer — مهندس برمجيات — muhandis barmajiyyat (m) / مهندسة برمجيات — muhandise barmajiyyat (f)")
        JoBodyText("architect — مهندس معماري — muhandis mi'mari (m) / مهندسة معمارية — muhandise mi'mariyye (f)")

        JoSectionHeader("Legal and Management")
        JoBodyText("lawyer — محامي — muhami (m) / محامية — muhamiyye (f)")
        JoBodyText("manager — مدير — mudir (m) / مديرة — mudire (f)")
        JoBodyText("politician — سياسي — siyasi (m) / سياسية — siyasiyye (f)")

        JoSectionHeader("Business and Commerce")
        JoBodyText("merchant — تاجر — tajer (m) / تاجرة — tajere (f)")
        JoBodyText("businessman — رجل أعمال — rijjal a'mal (m) / امرأة أعمال — imra'et a'mal (f)")
        JoBodyText("salesperson — بيّاع — bayyya' (m) / بيّاعة — bayyya'a (f)")
        JoBodyText("vendor — بائع — baye' (m) / بائعة — baye'a (f)  (street vendor / stall seller)")
        JoBodyText("cashier — كاشير — kashir (m) / كاشيرة — kashira (f); also: صراف — sarraf")

        JoSectionHeader("Property and Appraisal")
        JoBodyText("real estate agent / realtor — سمسار عقارات — simsar 'akarat  (broker/agent)")
        JoBodyText("car appraiser — مثمّن سيارات — muthammin sayyarat")
        JoBodyText("real estate appraiser — مثمّن عقارات — muthammin 'akarat")

        JoSectionHeader("Trades and Services")
        JoBodyText("construction worker — عامل بناء — 'amel bina (m) / عاملة بناء — 'amle bina (f)")
        JoBodyText("barber — حلاق — hallaa' (m) / حلاقة — hallaa'a (f)")
        JoBodyText("mechanic — ميكانيكي — mikaniki; also colloquially: بنشرجي — banshirji")

        JoSectionHeader("Education")
        JoBodyText("teacher — معلم — mu'allam (m) / معلمة — mu'allame (f)")
        JoBodyText("professor — بروفيسور — profissor; also: أستاذ جامعي — ustaz jami'i (m) / أستاذة جامعية — ustaze jami'iyye (f)")
        JoBodyText("student — طالب — taleb (m) / طالبة — talebe (f)")

        JoSectionHeader("Sports and Fitness")
        JoBodyText("athlete — رياضي — riyadi (m) / رياضية — riadiyye (f)")
        JoBodyText("trainer / coach — مدرّب — mudarrib (m) / مدرّبة — mudarrabe (f)")

        JoSectionHeader("Security and Military")
        JoBodyText("soldier — جندي — jundi (m) / جندية — jundiyye (f)")
        JoBodyText("policeman / policewoman — شرطي — shurtyi (m) / شرطية — shurtiyye (f)")

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
