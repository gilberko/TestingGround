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
        TmBodyText("    f · 2: ثانيتين sanyetein · pl: ثواني sawaani")
        TmBodyText("minute — دقيقة — da2i2a")
        TmBodyText("    f · 2: دقيقتين da2i2tein · pl: دقايق da2aye2")
        TmBodyText("hour — ساعة — sa3a")
        TmBodyText("    f · 2: ساعتين sa3tein · pl: ساعات sa3aat")
        TmBodyText("day — يوم — yom")
        TmBodyText("    m · 2: يومين yomein · pl: أيام ayyaam (after 3–10: tlatt iyyaam)")
        TmBodyText("week — أسبوع — isbu3")
        TmBodyText("    m · 2: أسبوعين isbu3ein · pl: أسابيع asabii3")
        TmBodyText("month — شهر — shahar")
        TmBodyText("    m · 2: شهرين shahrein · pl: شهور shhuur (after 3–10: tlatt ishhur)")
        TmBodyText("year — سنة — sana")
        TmBodyText("    f · 2: سنتين santein · pl: سنين sniin / سنوات sanawaat")
        TmBodyText("decade — عقد — 3a2d  (ten years)")
        TmBodyText("    m · 2: عقدين 3a2dein · pl: عقود 3u2uud")
        TmBodyText("century — قرن — 2arn")
        TmBodyText("    m · 2: قرنين 2arnein · pl: قرون 2uruun")
        Spacer(modifier = Modifier.height(8.dp))
        TmBodyText("ألف ليلة وليلة — alf lela w-lela — One Thousand and One Nights")
        TmBodyText("  (The famous collection of Middle Eastern folk tales — Scheherazade's stories.  ألف = thousand, ليلة = night, وليلة = and a night.  A cultural touchstone across the Arabic-speaking world.)")

        TmSectionHeader("Times of Day")
        TmBodyText("morning — صباح — saba7")
        TmBodyText("    m · pl: صباحات saba7aat (mornings) · dual rarely used")
        TmBodyText("noon / midday — ضهر — duhr  (also: نص النهار, nuss el-nahar = middle of the day)")
        TmBodyText("    m · uncountable")
        TmBodyText("afternoon — بعد الضهر — ba3d el-duhr  (literally: after noon)")
        TmBodyText("evening — مسا — masa")
        TmBodyText("    m · pl: مسايات masayaat (evenings) · dual rarely used")
        TmBodyText("night — ليل — leil")
        TmBodyText("    m · uncountable as \"night-time\"; to count nights use ليلة leile (f) → 2 ليلتين leiltein → pl ليالي layaali")
        TmBodyText("midnight — نص الليل — nuss el-leil  (literally: middle of the night)")
        TmBodyText("midday — نص النهار — nuss el-nahar")
        TmBodyText("tonight — الليلة — el-lele")
        TmBodyText("this morning — الصبح — el-sub7  (the morning = this morning in context)")
        TmBodyText("this evening — المسا — el-masa")

        TmSectionHeader("Relative Days")
        TmBodyText("today — اليوم — el-yom")
        TmBodyText("yesterday — مبارح — mbari7  (Levantine; MSA: أمس / ams)")
        TmBodyText("tomorrow — بكرا — bukra  (Levantine; MSA: غد / ghad)")
        TmBodyText("the day before yesterday — أول أمس — awwal ams")
        TmBodyText("the day after tomorrow — بعد بكرا — ba3d bukra")

        TmSectionHeader("Relative Weeks, Months, and Years")
        TmBodyText("last week — الأسبوع اللي فات — el-isbu3 illi fat  (literally: the week that passed)")
        TmBodyText("next week — الأسبوع الجاي — el-isbu3 el-jaii  (literally: the coming week)")
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
        TmBodyText("how long? — 2addeish?  (how long? / how much?)")
        TmBodyText("about / approximately — تقريباً — ta2riban  (formal/MSA)")
        TmBodyText("about / around — حوالي — 7awali  (Levantine: common informal usage)")
        TmBodyText("about an hour — حوالي ساعة — 7awali sa3a")
        TmBodyText("about 2 minutes — حوالي دقيقتين — 7awali da2i2tein  (daqiqtein = two minutes, dual form)")
        TmBodyText("about 5 minutes — حوالي خمس دقايق — 7awali khamas da2aye2")
        TmBodyText("a few minutes — بضع دقايق — bid3 da2aye2")
        TmBodyText("a long time — وقت طويل — wa2t tawil")
        TmBodyText("a short time — وقت قصير — wa2t 2asir")
        TmBodyText("right now — هلق — halla2  (Levantine: now, right this moment)")
        TmBodyText("soon — بعدين شوي — ba3dein shwai  (a little after; also: قريباً, 2ariban = soon)")
        TmBodyText("later — بعدين — ba3dein  (later / afterwards)")
        TmBodyText("already — بالفعل — bil-fi3l  (already done; also: already in the sense of \"by now\": هلق هلق = now now)")

        TmSectionHeader("Telling the Time")
        TmBodyText("What time is it? — قديش الساعة — 2addeish el-sa3a?")
        TmBodyText("It is one o'clock — الساعة وحدة — el-sa3a wa7de")
        TmBodyText("It is two o'clock — الساعة تنتين — el-sa3a tintein")
        TmBodyText("It is three o'clock — الساعة تلاتة — el-sa3a tlate")
        TmBodyText("half past — ونص — w-nuss  (e.g. الساعة تلاتة ونص = 3:30)")
        TmBodyText("quarter past — وربع — w-rub3  (e.g. الساعة أربعة وربع = 4:15)")
        TmBodyText("quarter to — إلا ربع — illa rub3  (e.g. الساعة خمسة إلا ربع = 4:45)")

        TmSectionHeader("Days of the Week")
        TmBodyText("Note: the Arabic week starts on Sunday; Friday is the holy day; Saturday (السبت is-sabt) shares the root with Hebrew Shabbat")
        Spacer(modifier = Modifier.height(8.dp))
        TmBodyText("Sunday — الأحد — el-a7ad")
        TmBodyText("    m · two Sundays: يومين أحد yomein a7ad · every Sunday: كل أحد kull a7ad · pl: أيام الأحد ayyaam il-a7ad")
        TmBodyText("Monday — الاثنين — el-itnein")
        TmBodyText("    m · two Mondays: يومين اثنين yomein itnein · every Monday: كل اثنين kull itnein")
        TmBodyText("Tuesday — الثلاثاء — et-tlata")
        TmBodyText("    m · two Tuesdays: يومين تلاتا yomein tlata · every Tuesday: كل تلاتا kull tlata")
        TmBodyText("Wednesday — الأربعاء — el-arb3a")
        TmBodyText("    m · two Wednesdays: يومين أربعا yomein arb3a · every Wednesday: كل أربعا kull arb3a")
        TmBodyText("Thursday — الخميس — el-khamis")
        TmBodyText("    m · two Thursdays: يومين خميس yomein khamis · every Thursday: كل خميس kull khamis")
        TmBodyText("Friday — الجمعة — el-jum3a  (the prayer day; جمعة = gathering/congregation)")
        TmBodyText("    f · two Fridays: جمعتين jum3atein · pl: جمعات jum3aat · every Friday: كل جمعة kull jum3a")
        TmBodyText("Saturday — السبت — es-sabt  (from Sabbath / Shabbat)")
        TmBodyText("    m · two Saturdays: سبتين sabtein · every Saturday: كل سبت kull sabt")
        Spacer(modifier = Modifier.height(8.dp))
        TmBodyText("What day is it? — شو اليوم؟ — shu el-yom?")
        TmBodyText("Today is Monday — اليوم الاثنين — el-yom el-itnein")

        TmSectionHeader("Months of the Year")
        TmBodyText("Levantine Arabic uses both Western (yanayir etc.) and Eastern/Syriac-rooted (kanun etc.) names — both are in everyday use in Israel and Palestine.")
        Spacer(modifier = Modifier.height(8.dp))
        TmBodyText("January — يناير — yanayir  /  كانون الثاني — kanun et-tani")
        TmBodyText("    All month names are m and are not normally made plural — say شهرين shahrein (two months) or كل كانون التاني kull kanun et-tani (every January)")
        TmBodyText("February — فبراير — febrayer  /  شباط — shbat")
        TmBodyText("March — مارس — mars  /  آذار — adar")
        TmBodyText("April — أبريل — abril  /  نيسان — nisan")
        TmBodyText("May — مايو — mayo  /  أيار — iyyar")
        TmBodyText("June — يونيو — yunyo  /  حزيران — 7aziran")
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
