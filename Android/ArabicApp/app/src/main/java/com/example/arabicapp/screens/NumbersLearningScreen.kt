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
fun NumbersLearningScreen() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        NLSectionHeader("Numbers 0 – 10")
        NLBody("These are the forms you use when counting or saying a number on its own (a phone number, a price, \"how many? — five\").")
        NLBody("0 — **صفر** — **sifr**")
        NLBody("1 — **واحد / وحدة** — **waa7ad** (m) / **wa7de** (f)")
        NLBody("2 — **تنين / تنتين** — **tnein** (m) / **tintein** (f)  (also **اتنين itnein**)")
        NLBody("3 — **تلاتة** — **tlaate**  (Palestinian also talaate)")
        NLBody("4 — **أربعة** — **arba3a**")
        NLBody("5 — **خمسة** — **khamse**")
        NLBody("6 — **ستة** — **sitte**")
        NLBody("7 — **سبعة** — **sab3a**")
        NLBody("8 — **تمانية** — **tamaanye**  (Lebanese/Syrian tmaane)")
        NLBody("9 — **تسعة** — **tis3a**")
        NLBody("10 — **عشرة** — **3ashara**")
        NLNote("Only 1 and 2 have a masculine and a feminine form. 3 and up do NOT change for gender in spoken Levantine (more below).")

        NLSectionHeader("11 – 19")
        NLBody("11 — **حدعش** — **7da3sh**")
        NLBody("12 — **تنعش** — **tna3sh**")
        NLBody("13 — **تلتعش** — **tlatta3sh**")
        NLBody("14 — **أربعتعش** — **arba3ta3sh**")
        NLBody("15 — **خمستعش** — **khamsta3sh**")
        NLBody("16 — **ستعش** — **sitta3sh**")
        NLBody("17 — **سبعتعش** — **saba3ta3sh**")
        NLBody("18 — **تمنتعش** — **tamanta3sh**")
        NLBody("19 — **تسعتعش** — **tisi3ta3sh**")
        NLNote("Before a noun they add -ar: حدعشر ولد — 7da3shar walad — 11 boys · خمستعشر سنة — khamsta3shar sane — 15 years.")

        NLSectionHeader("Tens, and Numbers Like 35")
        NLBody("20 — **عشرين** — **3ishriin** · 30 — **تلاتين** — **tlaatiin** · 40 — **أربعين** — **arb3iin** · 50 — **خمسين** — **khamsiin**")
        NLBody("60 — **ستين** — **sittiin** · 70 — **سبعين** — **sab3iin** · 80 — **تمانين** — **tamaaniin** · 90 — **تسعين** — **tis3iin**")
        Spacer(modifier = Modifier.height(8.dp))
        NLBody("Yes — you're right: the ONES come first, then w- (and), then the TENS. 35 is literally \"five and thirty\":")
        NLExample("35 — خمسة وتلاتين — khamse w-tlaatiin  (5 and 30)")
        NLExample("21 — واحد وعشرين — waa7ad w-3ishriin  (1 and 20)")
        NLExample("48 — تمانية وأربعين — tamaanye w-arb3iin  (8 and 40)")
        NLExample("99 — تسعة وتسعين — tis3a w-tis3iin  (9 and 90)")
        NLNote("Hebrew does the opposite (שלושים וחמש = thirty and five). German does the same as Arabic (fünfunddreißig), and so did old English (\"five and twenty\").")

        NLSectionHeader("Hundreds and Thousands")
        NLBody("100 — **مية** — **miyye** · 200 — **ميتين** — **mitein** · 300 — **تلتمية** — **tlatmiyye** · 1000 — **ألف** — **alf** · 2000 — **ألفين** — **alfein** · 3000 — **تلت آلاف** — **tlat-t-aalaaf**")
        NLExample("135 — مية وخمسة وتلاتين — miyye w-khamse w-tlaatiin")
        NLNote("Before a noun, miyye becomes miit: ميت شيكل — miit shekel — 100 shekels.")

        NLSectionHeader("Counting Things — 1 Bird, 2 Birds, 3 Birds")
        NLBody("bird — عصفور — 3asfuur (m)")
        NLInfo("m · 2: عصفورين 3asfuurein · pl: عصافير 3asafiir   (also طير teir, pl. طيور tyuur)")
        Spacer(modifier = Modifier.height(8.dp))
        NLSubHeader("One — the noun alone, or the number AFTER it (like Hebrew)")
        NLExample("عندي عصفور — 3indi 3asfuur — I have a bird / one bird")
        NLExample("عندي عصفور واحد — 3indi 3asfuur waa7ad — I have ONE bird  (stressing the number — like ציפור אחת)")
        NLSubHeader("Two — the dual ending -ein (no number word needed)")
        NLExample("عندي عصفورين — 3indi 3asfuurein — I have two birds")
        NLBody("Just like Hebrew יומיים, שבועיים — but in Arabic the dual is used for almost every noun. To stress it you can add tnein after: 3asfuurein tnein.")
        NLSubHeader("3 to 10 — short number BEFORE + PLURAL noun")
        NLExample("عندي تلت عصافير — 3indi tlat 3asafiir — I have 3 birds")
        NLExample("عندي خمس عصافير — 3indi khams 3asafiir — I have 5 birds")
        NLBody("Before a noun the number drops its final -e/-a: tlaate → tlat, arba3a → arba3, khamse → khams, sitte → sitt, sab3a → saba3, tamaanye → taman, tis3a → tisa3, 3ashara → 3ashar.")
        NLNote("Exceptions: money and measure words keep the long form: تلاتة شيكل — tlaate shekel · خمسة كيلو — khamse kiilo. Before aalaaf (thousands) and iyyaam (days) a t is added: tlat-t-iyyaam — 3 days.")
        NLSubHeader("11 and up — number BEFORE + SINGULAR noun")
        NLExample("عندي حدعشر عصفور — 3indi 7da3shar 3asfuur — I have 11 birds")
        NLExample("عندي عشرين عصفور — 3indi 3ishriin 3asfuur — I have 20 birds")
        NLExample("عندي مية عصفور — 3indi miit 3asfuur — I have 100 birds")
        Spacer(modifier = Modifier.height(8.dp))
        NLBody("So you were partly right: like Hebrew, \"one\" comes after the noun and the other numbers come before. The difference is \"two\", which in Arabic is normally just the dual ending.")

        NLSectionHeader("Does the Number Match the Gender?")
        NLBody("Only 1 and 2. Example with a feminine noun: cat — بسّة — bisse (f)")
        NLExample("بسّة وحدة — bisse wa7de — one cat  (wa7de, feminine)  ·  عصفور واحد — 3asfuur waa7ad — one bird (masculine)")
        NLExample("بسّتين — bisstein — two cats  ·  stressed: bisstein tintein")
        NLExample("تلت بسس — tlat bisas — 3 cats  ·  تلت عصافير — tlat 3asafiir — 3 birds  (same tlat for both)")
        NLNote("From 3 up, spoken Levantine does NOT match gender. Formal Arabic (MSA) does have a gender rule for 3–10 (the number takes the opposite gender), and Hebrew has שלוש/שלושה — but you don't need either in everyday speech. tlaate vs tlat is not masculine vs feminine; it's \"alone\" vs \"before a noun\".")

        NLSectionHeader("Singular or Plural Noun? — Summary")
        NLBody("1 → singular noun (number optional, after)")
        NLBody("2 → dual -ein (number optional, after)")
        NLBody("3–10 → short number + PLURAL noun")
        NLBody("11–99 → number (-ar form for 11–19) + SINGULAR noun")
        NLBody("100, 1000 → miit / alf + SINGULAR noun")

        NLSectionHeader("Ordinal Numbers — First, Second, Third")
        NLBody("m / f forms:")
        NLBody("1st — **أوّل / أولى** — **awwal / uula**")
        NLBody("2nd — **تاني / تانية** — **taani / taanye**")
        NLBody("3rd — **تالت / تالتة** — **taalit / taalte**")
        NLBody("4th — **رابع / رابعة** — **raabi3 / raab3a**")
        NLBody("5th — **خامس / خامسة** — **khaamis / khaamse**")
        NLBody("6th — **سادس / سادسة** — **saadis / saadse**")
        NLBody("7th — **سابع / سابعة** — **saabi3 / saab3a**")
        NLBody("8th — **تامن / تامنة** — **taamin / taamne**")
        NLBody("9th — **تاسع / تاسعة** — **taasi3 / taas3a**")
        NLBody("10th — **عاشر / عاشرة** — **3aashir / 3aashra**")
        NLBody("last — **آخر** — **aakhir**")

        NLSubHeader("Two ways to place them")
        NLBody("A) BEFORE the noun — always the masculine form, no el-, NO agreement at all (the most common way to say \"first\"):")
        NLExample("أوّل بيت — awwal beit — the first house")
        NLExample("أوّل مرا — awwal mara — the first wife  (mara = woman / wife — and still awwal, not uula)")
        NLExample("أوّل جوز — awwal jooz — the first husband")
        NLExample("أوّل أرباح بالكازينو — awwal arbaa7 bil-kaaziino — the first winnings at the casino  (arbaa7 = profits/winnings, plural — and still awwal)")
        NLExample("تاني يوم — taani yoom — the next day / the second day · آخر مرة — aakhir marra — the last time")
        Spacer(modifier = Modifier.height(8.dp))
        NLBody("B) AFTER the noun — el- on both words, and the ordinal MATCHES the noun's gender (like an adjective):")
        NLExample("البيت الأوّل — el-beit el-awwal — the first house  (m)")
        NLExample("المرا الأولى — el-mara el-uula — the first wife  (f)  ·  or with possessive: martu el-uula — his first wife")
        NLExample("الجوز الأوّل — ej-jooz el-awwal — the first husband  (m)  ·  jooz-ha el-awwal — her first husband")
        NLExample("الأرباح الأولى — el-arbaa7 el-uula — the first winnings  (plural things usually take the feminine singular form)")
        NLNote("Plural ordinals exist for people (taanyiin = second ones, awaa2il = the first ones) but are rare — for \"first winnings\" just say awwal arbaa7.")
        Spacer(modifier = Modifier.height(8.dp))
        NLBody("Careful: أوّل مرّة — awwal marra (double r) = the first TIME;  أوّل مرا — awwal mara (single r) = the first WIFE.")

        NLSubHeader("Above 10th — use the normal number AFTER the noun")
        NLExample("الطابق الحدعش — et-taabi2 el-7da3sh — the 11th floor")
        NLExample("المرة العشرين — el-marra el-3ishriin — the 20th time")

        NLSubHeader("Floors and School Grades")
        NLBody("floor — طابق — Taabi2 (m; Lebanese Taabe2)  ·  class / grade — صفّ — Saff (m)")
        NLExample("الطابق الأوّل — eT-Taabi2 el-awwal — the first floor  ·  or: أوّل طابق — awwal Taabi2")
        NLExample("الطابق التاني — eT-Taabi2 et-taani — the second floor")
        NLExample("الطابق التالت — eT-Taabi2 et-taalit — the third floor")
        NLExample("ساكن بالطابق التالت — saakin bi-T-Taabi2 et-taalit — I live on the third floor (m;  f: saakne)")
        NLNote("Ground floor = الطابق الأرضي — eT-Taabi2 el-arDi. Like Hebrew (קומת קרקע, then קומה ראשונה), the \"first floor\" is one floor ABOVE the ground floor.")
        NLExample("الصفّ الأوّل — eS-Saff el-awwal — first grade")
        NLExample("الصفّ التاني — eS-Saff et-taani — second grade")
        NLExample("الصفّ التالت — eS-Saff et-taalit — third grade")
        NLExample("بأيّ صفّ إنت؟ — bi-ayy Saff inta? — What grade are you in?  →  بالصفّ التالت — bis-Saff et-taalit — In third grade.")
        NLBody("Both are masculine nouns, so the ordinal after them is masculine: el-awwal, et-taani, et-taalit (not uula / taanye / taalte).")

        NLSectionHeader("Telling the Time")
        NLSubHeader("The words")
        NLBody("**ساعة — saa3a** — hour; also clock / watch  ·  2: saa3tein · pl: saa3aat")
        NLBody("**دقيقة — da2ii2a** — minute  ·  2: da2ii2tein · pl: دقايق da2aaye2")
        NLBody("**ثانية — sanye** — second  ·  2: sanyetein · pl: ثواني sawaani")
        NLBody("**نصّ ساعة — nuSS saa3a** — half an hour  ·  **ربع ساعة — rub3 saa3a** — a quarter of an hour  ·  **تلت ساعة — tilt saa3a** — a third of an hour (20 minutes)")
        NLExample("قديش الساعة؟ — 2addeish es-saa3a? — What time is it?  (Leb 2addeh es-saa3a?)")

        NLSubHeader("How it works")
        NLBody("1. es-saa3a + the hour, using the feminine forms: wa7de (1), tintein (2), tlaate, arba3a, khamse, sitte, sab3a, tamaanye, tis3a, 3ashara, 7da3sh (11), tna3sh (12).")
        NLBody("2. Then add minutes with **w- (and / plus)**, or take them away with **illa (minus / except)**.")
        NLBody("3. The landmarks: **w-rub3** (:15) · **w-tilt** (:20) · **w-nuSS** (:30) · **illa tilt** (:40) · **illa rub3** (:45). Other minutes are counted from the nearest landmark or from the whole hour.")
        NLExample("الساعة تسعة — es-saa3a tis3a — It's 9:00")
        NLExample("تسعة وربع — tis3a w-rub3 — 9:15")
        NLExample("تسعة وتلت — tis3a w-tilt — 9:20")
        NLExample("تسعة ونصّ — tis3a w-nuSS — 9:30")
        NLExample("عشرة إلا تلت — 3ashara illa tilt — 9:40 (ten minus a third)")
        NLExample("عشرة إلا ربع — 3ashara illa rub3 — 9:45 (ten minus a quarter)")

        NLSubHeader("Your examples")
        NLExample("9:01 — تسعة ودقيقة — tis3a w-da2ii2a  (one minute: the noun alone)")
        NLExample("9:02 — تسعة ودقيقتين — tis3a w-da2ii2tein  (two minutes: the dual)")
        NLExample("9:03 in the morning — الساعة تسعة وتلت دقايق الصبح — es-saa3a tis3a w-tlat da2aaye2 eS-Sub7  (3–10: short number + plural, as in Counting above;  casually just tis3a w-tlaate)")
        NLExample("9:10 — تسعة وعشرة — tis3a w-3ashara")
        NLExample("9:15 in the morning — الساعة تسعة وربع الصبح — es-saa3a tis3a w-rub3 eS-Sub7")
        NLExample("9:25 in the morning — الساعة تسعة ونصّ إلا خمسة الصبح — es-saa3a tis3a w-nuSS illa khamse eS-Sub7  (half past, minus 5)")
        NLExample("9:30 in the morning — الساعة تسعة ونصّ الصبح — es-saa3a tis3a w-nuSS eS-Sub7")
        NLExample("9:35 in the morning — الساعة تسعة ونصّ وخمسة الصبح — es-saa3a tis3a w-nuSS w-khamse eS-Sub7  (half past, plus 5)")
        NLExample("9:45 in the morning — الساعة عشرة إلا ربع الصبح — es-saa3a 3ashara illa rub3 eS-Sub7")
        NLNote("9:25 and 9:35 really are said from the half hour: \"half minus five\" and \"half plus five\". A Lebanese source gives exactly this pattern (3:25 = tlaate w-nuSS illa khamse, 3:35 = tlaate w-nuSS w-khamse); Palestinian uses the same system, though I didn't find a separate Palestinian source for these two.")

        NLSubHeader("Can you say \"… to 10\"?  Yes — with illa")
        NLExample("quarter to 10 — عشرة إلا ربع — 3ashara illa rub3")
        NLExample("5 minutes to 10 — عشرة إلا خمسة — 3ashara illa khamse")
        NLExample("2 minutes to 10 — عشرة إلا دقيقتين — 3ashara illa da2ii2tein")
        NLExample("1 minute to 10 — عشرة إلا دقيقة — 3ashara illa da2ii2a")
        NLNote("illa literally means \"except\" (like Hebrew חוץ מ / פחות). In practice: up to :35 people count forward from the hour or the half; from :40 on they count down to the next hour. So 9:45 is normally 3ashara illa rub3 — tis3a w-nuSS w-rub3 would be understood, but it sounds odd.")

        NLSubHeader("Morning, Afternoon, Evening, Night")
        NLBody("Spoken Arabic uses the 12-hour clock and adds the part of the day after the time:")
        NLExample("الصبح — eS-Sub7 — in the morning:  tis3a eS-Sub7 — 9 a.m.")
        NLExample("الضهر — eD-Duhr — at noon / midday:  tna3sh eD-Duhr — 12 noon")
        NLExample("بعد الضهر — ba3d eD-Duhr — in the afternoon:  tlaate ba3d eD-Duhr — 3 p.m.")
        NLExample("المسا — el-masa — in the evening:  sab3a el-masa — 7 p.m.")
        NLExample("بالليل — bil-leil — at night:  7da3sh bil-leil — 11 p.m.")
        NLExample("نصّ الليل — nuSS el-leil — midnight:  es-saa3a tna3sh bil-leil / nuSS el-leil")
        NLNote("\"At\" a time: just put the time after the verb, or use 3ala / 3a: bniltii2i 3a-s-saa3a tamaanye — we'll meet at eight. 24-hour times (21:00) appear on timetables and in official announcements, not in conversation.")

        NLSectionHeader("Years, Months and Dates")
        NLSubHeader("Years — one big number")
        NLBody("A year is read like any big number (thousands, then hundreds, then ones, then tens), usually after **سنة — sanet** (\"the year of\", sane in construct):")
        NLExample("1990 — سنة ألف وتسعمية وتسعين — sanet alf w-tis3miyye w-tis3iin")
        NLExample("1995 — سنة ألف وتسعمية وخمسة وتسعين — sanet alf w-tis3miyye w-khamse w-tis3iin")
        NLExample("2000 — سنة ألفين — sanet alfein")
        NLExample("2026 — سنة ألفين وستة وعشرين — sanet alfein w-sitte w-3ishriin")
        NLExample("وُلدت سنة ٩٥ — wlidt sanet khamse w-tis3iin — I was born in '95  (fi / b-sanet … = in the year …)")
        NLNote("Unlike English, the year isn't split into two halves (\"nineteen ninety\"): it's literally \"a thousand and nine hundred and ninety\".")

        NLSubHeader("The Names of the Months")
        NLBody("The Levant uses the Syriac-rooted names (close to the Hebrew month names); the Western names are understood too. Each month: Levantine name · Western name.")
        NLBody("1. January — **كانون التاني — kaanuun et-taani** · **يناير — yanaayir**")
        NLBody("2. February — **شباط — shbaaT** · **فبراير — fibraayir**")
        NLBody("3. March — **آذار — aadaar** · **مارس — maaris**")
        NLBody("4. April — **نيسان — niisaan** · **أبريل — abriil**")
        NLBody("5. May — **أيّار — ayyaar** · **مايو — maayu**")
        NLBody("6. June — **حزيران — 7aziiraan** · **يونيو — yuunyu**")
        NLBody("7. July — **تمّوز — tammuuz** · **يوليو — yuulyu**")
        NLBody("8. August — **آب — aab** · **أغسطس — aghusTus**")
        NLBody("9. September — **أيلول — eiluul** · **سبتمبر — sibtambar**")
        NLBody("10. October — **تشرين الأوّل — tishriin el-awwal** · **أكتوبر — oktoobar**")
        NLBody("11. November — **تشرين التاني — tishriin et-taani** · **نوفمبر — nuuvambar**")
        NLBody("12. December — **كانون الأوّل — kaanuun el-awwal** · **ديسمبر — diisambar**")
        NLBody("month — **شهر — shahar** (pl **شهور — shhuur**)  ·  in April: **b-niisaan / fi niisaan**")
        NLNote("Hebrew speakers: niisaan = ניסן, ayyaar = אייר, tammuuz = תמוז, aab = אב, eiluul = אלול, shbaaT = שבט, aadaar = אדר, tishriin = תשרי — the same old names, but here they're simply the months of the Western calendar. Many people also just say the month's number: shahar arba3a = April (commonly heard).")

        NLSubHeader("The Seasons")
        NLBody("season — **فصل — faSl** (m · pl **فصول — fuSuul**)")
        NLBody("winter — **شتا — shita**  (formal الشتاء ash-shitaa2)")
        NLBody("spring — **ربيع — rabii3**")
        NLBody("summer — **صيف — Seif**")
        NLBody("fall / autumn — **خريف — khariif**")
        NLExample("بالشتا — bi-sh-shita — in (the) winter  ·  بالصيف — bi-S-Seif — in (the) summer")
        NLExample("هالصيف — haS-Seif — this summer  ·  الصيف الجاي — eS-Seif ej-jaay — next summer  ·  الشتا الماضي — esh-shita el-maaDi — last winter")
        NLExample("بحبّ الربيع أكتر شي — b7ibb er-rabii3 aktar ishi — I like spring the most")
        NLNote("shita is also the everyday word for rain: btishti — it's raining (see Dictionary → The Weather). In Levantine the season is usually šite / shita (transparent.com); the four names match formal Arabic (rabii3, Seif, khariif, shitaa2). Hebrew סתיו (autumn) may be related to Arabic shitaa2 — a suggestion in a Tel Aviv University dictionary note, not certain.")

        NLSubHeader("New Year, New Month, End of the Year / Month")
        NLBody("New Year (the holiday) — **راس السنة — raas es-sane**  (lit. \"head of the year\", like Hebrew ראש השנה)")
        NLBody("New Year's Eve — **ليلة راس السنة — leilet raas es-sane**")
        NLBody("a new year — **سنة جديدة — sane jdiide**  ·  greeting: **sane 7ilwe!** / **kull sane w-inta saalim** (see Greetings)")
        NLBody("beginning of the year — **أوّل السنة — awwal es-sane**")
        NLBody("end of the year — **آخر السنة — aakhir es-sane**  (formal: نهاية السنة nihaayet es-sane)")
        NLBody("a new month — **شهر جديد — shahar jdiid**")
        NLBody("beginning of the month — **أوّل الشهر — awwal esh-shahar**")
        NLBody("end of the month — **آخر الشهر — aakhir esh-shahar**")
        NLExample("شو عامل براس السنة؟ — shu 3aamil b-raas es-sane? — What are you doing for New Year's? (to m)")
        NLExample("منلتقى آخر الشهر — mniltaa2a aakhir esh-shahar — We'll meet at the end of the month")
        NLExample("المعاش بينزل أوّل الشهر — el-ma3aash binzil awwal esh-shahar — The salary comes in at the start of the month")
        NLExample("بخلّص المشروع آخر السنة — bkhalliS el-mashruu3 aakhir es-sane — I'll finish the project at the end of the year")
        NLNote("awwal (first) and aakhir (last) are the same words as in the ordinals above — before a noun they mean \"the beginning of\" / \"the end of\". These are everyday phrases; on its own, raas es-sane means the 1 January New Year.")

        NLSubHeader("Dates — day number + month")
        NLBody("The day is a plain number (not an ordinal), then the month, then the year if needed. The 1st is often **awwal** (first):")
        NLExample("اليوم أوّل نيسان — el-yoom awwal niisaan — Today is the 1st of April  (also: wa7ad niisaan · kizbet awwal niisaan = April Fool's joke)")
        NLExample("اليوم تنين نيسان — el-yoom tnein niisaan — Today is the 2nd of April")
        NLExample("اليوم تلاتة نيسان — el-yoom tlaate niisaan — Today is the 3rd of April")
        NLExample("اليوم خمسة وعشرين نيسان — el-yoom khamse w-3ishriin niisaan — Today is the 25th of April")
        NLExample("With the Western name: wa7ad abriil · tnein abriil · tlaate abriil · khamse w-3ishriin abriil")
        NLExample("خمسة وعشرين نيسان ألفين وستة وعشرين — khamse w-3ishriin niisaan alfein w-sitte w-3ishriin — 25 April 2026")
        NLNote("Ordinal dates like et-taani min niisaan (\"the second of April\") are formal Arabic — fine in writing and the news, but in conversation use the plain number.")

        NLSectionHeader("Quick Summary")
        NLBody("1. 35 = khamse w-tlaatiin — ones first, then tens")
        NLBody("2. one bird = 3asfuur (waa7ad) · two birds = 3asfuurein · 3 birds = tlat 3asafiir · 11 birds = 7da3shar 3asfuur")
        NLBody("3. Only 1 and 2 change for gender (waa7ad/wa7de, tnein/tintein)")
        NLBody("4. Ordinal before the noun = awwal for everything (awwal beit, awwal mara, awwal arbaa7)")
        NLBody("5. Ordinal after the noun = agrees: el-beit el-awwal, el-mara el-uula")
        NLBody("6. Time = es-saa3a + hour (f forms) + w-rub3 / w-tilt / w-nuSS, or next hour + illa tilt / illa rub3 / illa khamse; + eS-Sub7 / ba3d eD-Duhr / el-masa / bil-leil")
        NLBody("7. Year = sanet + the whole number: sanet alf w-tis3miyye w-tis3iin (1990), sanet alfein w-sitte w-3ishriin (2026)")
        NLBody("8. Date = number + month: awwal / tnein / khamse w-3ishriin niisaan")
        NLBody("9. Seasons: shita, rabii3, Seif, khariif · raas es-sane = New Year · awwal / aakhir esh-shahar = start / end of the month")

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun NLSectionHeader(text: String) {
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
private fun NLSubHeader(text: String) {
    Spacer(modifier = Modifier.height(10.dp))
    Text(
        text = fixArabizi(text),
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(vertical = 2.dp)
    )
}

@Composable
private fun NLBody(text: String) {
    Text(
        text = richText(text),
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(vertical = 2.dp)
    )
}

@Composable
private fun NLExample(text: String) {
    Text(
        text = richText("• $text"),
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(start = 12.dp, top = 2.dp, bottom = 2.dp)
    )
}

@Composable
private fun NLInfo(text: String) {
    Text(
        text = richText("    $text"),
        style = MaterialTheme.typography.bodySmall,
        modifier = Modifier.padding(vertical = 1.dp)
    )
}

@Composable
private fun NLNote(text: String) {
    Text(
        text = fixArabizi(text),
        style = MaterialTheme.typography.bodySmall,
        fontStyle = FontStyle.Italic,
        modifier = Modifier.padding(vertical = 4.dp)
    )
}
