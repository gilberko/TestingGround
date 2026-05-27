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
fun TimeScreen() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        TmSectionHeader("Basic Time Units")
        TmBodyText("second — ثانية — sanye")
        TmBodyText("minute — دقيقة — da'i'a")
        TmBodyText("hour — ساعة — sa'a")
        TmBodyText("day — يوم — yom")
        TmBodyText("week — أسبوع — isbu'")
        TmBodyText("month — شهر — shahar")
        TmBodyText("year — سنة — sana")
        TmBodyText("decade — عقد — 'a'd  (ten years)")
        TmBodyText("century — قرن — 'arn")
        Spacer(modifier = Modifier.height(8.dp))
        TmBodyText("ألف ليلة وليلة — alf lela w-lela — One Thousand and One Nights")
        TmBodyText("  (The famous collection of Middle Eastern folk tales — Scheherazade's stories.  ألف = thousand, ليلة = night, وليلة = and a night.  A cultural touchstone across the Arabic-speaking world.)")

        TmSectionHeader("Times of Day")
        TmBodyText("morning — صباح — sabah")
        TmBodyText("noon / midday — ضهر — duhr  (also: نص النهار, nuss el-nahar = middle of the day)")
        TmBodyText("afternoon — بعد الضهر — ba'd el-duhr  (literally: after noon)")
        TmBodyText("evening — مسا — masa")
        TmBodyText("night — ليل — leil")
        TmBodyText("midnight — نص الليل — nuss el-leil  (literally: middle of the night)")
        TmBodyText("midday — نص النهار — nuss el-nahar")
        TmBodyText("tonight — الليلة — el-lele")
        TmBodyText("this morning — الصبح — el-subh  (the morning = this morning in context)")
        TmBodyText("this evening — المسا — el-masa")

        TmSectionHeader("Relative Days")
        TmBodyText("today — اليوم — el-yom")
        TmBodyText("yesterday — مبارح — mbarih  (Levantine; MSA: أمس / ams)")
        TmBodyText("tomorrow — بكرا — bukra  (Levantine; MSA: غد / ghad)")
        TmBodyText("the day before yesterday — أول أمس — awwal ams")
        TmBodyText("the day after tomorrow — بعد بكرا — ba'd bukra")

        TmSectionHeader("Relative Weeks, Months, and Years")
        TmBodyText("last week — الأسبوع اللي فات — el-isbu' illi fat  (literally: the week that passed)")
        TmBodyText("next week — الأسبوع الجاي — el-isbu' el-jaii  (literally: the coming week)")
        TmBodyText("this week — هالأسبوع — hal-isbu'")
        Spacer(modifier = Modifier.height(4.dp))
        TmBodyText("last month — الشهر اللي فات — el-shahar illi fat")
        TmBodyText("next month — الشهر الجاي — el-shahar el-jaii")
        TmBodyText("this month — هالشهر — hal-shahar")
        Spacer(modifier = Modifier.height(4.dp))
        TmBodyText("last year — السنة اللي فاتت — el-sana illi fatat")
        TmBodyText("next year — السنة الجاية — el-sana el-jayye")
        TmBodyText("this year — هالسنة — hal-sana")

        TmSectionHeader("Approximate Time")
        TmBodyText("how long? — 'addesh — 'addeish?  (how long? / how much?)")
        TmBodyText("about / approximately — تقريباً — ta'riban  (formal/MSA)")
        TmBodyText("about / around — حوالي — hawali  (Levantine: common informal usage)")
        TmBodyText("about an hour — حوالي ساعة — hawali sa'a")
        TmBodyText("about 2 minutes — حوالي دقيقتين — hawali da'i'tein  (daqiqtein = two minutes, dual form)")
        TmBodyText("about 5 minutes — حوالي خمس دقايق — hawali khamas da'ayiq")
        TmBodyText("a few minutes — بضع دقايق — bid' da'ayiq")
        TmBodyText("a long time — وقت طويل — wa't tawil")
        TmBodyText("a short time — وقت قصير — wa't 'asir")
        TmBodyText("right now — هلق — halla'  (Levantine: now, right this moment)")
        TmBodyText("soon — بعدين شوي — ba'dein shwai  (a little after; also: قريباً, 'ariban = soon)")
        TmBodyText("later — بعدين — ba'dein  (later / afterwards)")
        TmBodyText("already — بالفعل — bil-fi'l  (already done; also: already in the sense of \"by now\": هلق هلق = now now)")

        TmSectionHeader("Telling the Time")
        TmBodyText("What time is it? — قديش الساعة — 'addeish el-sa'a?")
        TmBodyText("It is one o'clock — الساعة وحدة — el-sa'a wahde")
        TmBodyText("It is two o'clock — الساعة تنتين — el-sa'a tintein")
        TmBodyText("It is three o'clock — الساعة تلاتة — el-sa'a tlate")
        TmBodyText("half past — ونص — w-nuss  (e.g. الساعة تلاتة ونص = 3:30)")
        TmBodyText("quarter past — وربع — w-rub'  (e.g. الساعة أربعة وربع = 4:15)")
        TmBodyText("quarter to — إلا ربع — illa rub'  (e.g. الساعة خمسة إلا ربع = 4:45)")

        TmSectionHeader("Days of the Week")
        TmBodyText("Note: the Arabic week starts on Sunday; Friday is the holy day; Saturday (السبت) shares the root with Hebrew Shabbat")
        Spacer(modifier = Modifier.height(8.dp))
        TmBodyText("Sunday — الأحد — el-ahad")
        TmBodyText("Monday — الاثنين — el-itnein")
        TmBodyText("Tuesday — الثلاثاء — et-tlata")
        TmBodyText("Wednesday — الأربعاء — el-arb'a")
        TmBodyText("Thursday — الخميس — el-khamis")
        TmBodyText("Friday — الجمعة — el-jum'a  (the prayer day; جمعة = gathering/congregation)")
        TmBodyText("Saturday — السبت — es-sabt  (from Sabbath / Shabbat)")
        Spacer(modifier = Modifier.height(8.dp))
        TmBodyText("What day is it? — شو اليوم؟ — shu el-yom?")
        TmBodyText("Today is Monday — اليوم الاثنين — el-yom el-itnein")

        TmSectionHeader("Months of the Year")
        TmBodyText("Levantine Arabic uses both Western (yanayir etc.) and Eastern/Syriac-rooted (kanun etc.) names — both are in everyday use in Israel and Palestine.")
        Spacer(modifier = Modifier.height(8.dp))
        TmBodyText("January — يناير — yanayir  /  كانون الثاني — kanun et-tani")
        TmBodyText("February — فبراير — febrayer  /  شباط — shbat")
        TmBodyText("March — مارس — mars  /  آذار — adar")
        TmBodyText("April — أبريل — abril  /  نيسان — nisan")
        TmBodyText("May — مايو — mayo  /  أيار — iyyar")
        TmBodyText("June — يونيو — yunyo  /  حزيران — haziran")
        TmBodyText("July — يوليو — yuliyo  /  تموز — tammuz")
        TmBodyText("August — أغسطس — aghustus  /  آب — ab")
        TmBodyText("September — سبتمبر — sibtambar  /  أيلول — eilul")
        TmBodyText("October — أكتوبر — uktober  /  تشرين الأول — tishrin el-awwal")
        TmBodyText("November — نوفمبر — nofambar  /  تشرين الثاني — tishrin et-tani")
        TmBodyText("December — ديسمبر — disambar  /  كانون الأول — kanun el-awwal")
        Spacer(modifier = Modifier.height(8.dp))
        TmBodyText("What month is it? — شو الشهر؟ — shu el-shahar?")

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun TmSectionHeader(text: String) {
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
private fun TmBodyText(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(vertical = 2.dp)
    )
}
