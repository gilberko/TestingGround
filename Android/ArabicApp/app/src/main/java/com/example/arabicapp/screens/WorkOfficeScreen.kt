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
fun WorkOfficeScreen() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        WONote("Words for the office and the working day. Palestinian first, Lebanese/Syrian where it differs. Office Arabic mixes in a lot of English (and, for Arabic speakers in Israel, Hebrew): laptop, email, team leader, code review are often just said in English.")

        WOSectionHeader("Working — The Basics")
        WOWord("to work — اشتغل — ishtaghal / byishtighil")
        WOInfo("I work: bashtighil · you work: btishtighil (m) / btishtighli (f) · where do you work? wein btishtighil?")
        WOWord("work / job — شغل — shughl")
        WOInfo("m · pl: أشغال ashghaal (works, jobs)  ·  formal job / position: وظيفة — waZiife (f · pl waZaayif)")
        WOWord("going to work — رايح عالشغل — raayi7 3ash-shughl  (f: raay7a)")
        WOWord("coming home from work — راجع من الشغل عالبيت — raaje3 min esh-shughl 3al-beit  (f: raaj3a)")
        WOExample("ana raayi7 3ash-shughl, bashuufak bil-masa — أنا رايح عالشغل، بشوفك بالمسا — I'm going to work, see you in the evening.")
        WOWord("office — مكتب — maktab")
        WOInfo("m · 2: مكتبين maktabein · pl: مكاتب makaatib  ·  the word also means desk (see below)")
        WOWord("colleague — زميل / زميلة — zamiil / zamiile  (also pronounced zmiil / zmiile)")
        WOInfo("pl: زملا zumala · my colleague: zamiili · a work colleague: zamiil bish-shughl")
        WOWord("peer — زميل — zamiil  (same word: a peer at work is a colleague at your level)")
        WOWord("team — فريق — farii2;  also just team")
        WOInfo("m · 2: فريقين farii2ein · pl: فرق fira2")
        WOWord("team leader — رئيس فريق — ra2iis farii2;  everyday: team leader / team lead (English)")
        WOSmall("ra2iis farii2 is the formal phrase (like Hebrew ראש צוות). In practice the English \"team leader\" is very common; this is commonly heard, not confirmed by a source.")
        WOWord("lunch — غدا — ghada")
        WOInfo("to have lunch: tghadda / byitghadda · lunch break: استراحة الغدا — istiraa7et el-ghada")
        WOExample("yalla nitghadda? — يلّا نتغدّى؟ — Shall we have lunch?")

        WOSectionHeader("People at Work")
        WOWord("manager — مدير / مديرة — mudiir / mudiire  (fast speech mdiir)")
        WOInfo("pl: مدرا mudara · my manager: mudiiri · the manager's office: maktab el-mudiir")
        WOWord("employer / boss — صاحب الشغل — Saa7ib esh-shughl")
        WOSmall("Lit. \"the owner of the work\". Palestinian colloquial also المعلّم — el-m3allem for the boss of a workshop or small business (it also means teacher).")
        WOWord("employee — موظّف / موظّفة — muwaZZaf / muwaZZafe  (fast speech mwaZZaf)")
        WOInfo("pl: موظّفين muwaZZafiin (m) / موظّفات muwaZZafaat (f)")
        WOWord("secretary — سكرتير / سكرتيرة — sikirteir / sikirteira")
        WOWord("human resources — الموارد البشرية — el-mawaarid el-bashariyye;  everyday: HR (ech-aar)")
        WOSmall("Same calque as Hebrew משאבי אנוש — \"human resources\" word for word.")
        WOWord("board of directors — مجلس الإدارة — majlis el-idaara")
        WOInfo("member of the board: عضو مجلس إدارة — 3uDw majlis idaara · chairman: رئيس مجلس الإدارة — ra2iis majlis el-idaara")

        WOSectionHeader("Hiring, Leaving and Pay")
        WOWord("to hire — وظّف — waZZaf / biwaZZif;  also: شغّل — shaghghal / bishaghghil")
        WOWord("to be hired / get a job — توظّف — twaZZaf / byitwaZZaf")
        WOExample("twaZZaft b-sharike jdiide — توظّفت بشركة جديدة — I got a job at a new company.")
        WOWord("interview — مقابلة — mu2aable  (formal mu2aabala)")
        WOInfo("f · pl: مقابلات mu2aablaat · job interview: مقابلة شغل — mu2aablet shughl")
        WOWord("home task / home assignment — مهمّة بيتية — mahamme beitiyye;  also just home assignment (English)")
        WOSmall("For a task you do at home as part of a job interview, the English term is common; mahamme beitiyye is commonly heard, not confirmed by a source.")
        WOWord("to resign — استقال — ista2aal / byista2iil")
        WOInfo("resignation: استقالة — isti2aale · he handed in his resignation: 2addam isti2aalto")
        WOWord("to fire (someone) — طرد — Tarad / byuTrud;  also: فصل — faSal / byifSil (more formal)")
        WOWord("colloquial: طلّعه من الشغل — Talla3o min esh-shughl  (lit. \"took him out of work\")")
        WOWord("to be fired — انطرد — inTarad / byinTirid;  formal: انفصل — infaSal")
        WOExample("inTarad min esh-shughl — انطرد من الشغل — He got fired.")
        WOWord("salary — معاش — ma3aash;  formal: راتب — raatib")
        WOInfo("m · pl: معاشات ma3aashaat / رواتب rawaatib · the salary comes in: el-ma3aash binzil")
        WOWord("payslip — قسيمة راتب — 2asiimet raatib")
        WOSmall("Arabic speakers in Israel usually say تلوش — tloosh (from Hebrew תלוש); Kol Zchut's Arabic pages write قسيمة الراتب (تلوش).")
        WOWord("bonus — مكافأة — mukaafa2a;  everyday also: bonus (English)")
        WOInfo("f · pl: مكافآت mukaafa2aat")
        WOWord("stocks / shares — أسهم — as-hum  (one share: سهم — sahm)")
        WOInfo("stock options are usually just said in English")
        WOWord("promotion — ترقية — tar2iye")
        WOWord("to promote (someone) — رقّى — ra22a / biri22i")
        WOWord("to be promoted / get a promotion — ترقّى — tra22a / byitra22a;  or: أخد ترقية — akhad tar2iye")
        WOExample("akhadt tar2iye w-Sirt ra2iis farii2 — أخدت ترقية وصرت رئيس فريق — I got a promotion and became a team leader.")

        WOSectionHeader("Time Off")
        WOWord("vacation / leave / PTO — إجازة — ijaaze;  also: عطلة — 3uTle (holiday, day off)")
        WOInfo("f · 2: إجازتين ijaaztein · pl: إجازات ijaazaat")
        WOSmall("PTO usually stands for Paid Time Off; some companies say Personal Time Off — both mean the paid days off you can take. Arabic just says ijaaze.")
        WOWord("I'm on leave / on PTO — أنا بإجازة — ana b-ijaaze")
        WOWord("personal day — إجازة شخصية — ijaaze shakhSiyye")
        WOWord("sick leave — إجازة مرضية — ijaaze maraDiyye")
        WOSmall("Arabic speakers in Israel often say מחלה — ma7ala, from Hebrew (commonly heard, not confirmed by a source).")
        WOExample("bukra ana b-ijaaze maraDiyye — بكرا أنا بإجازة مرضية — Tomorrow I'm on sick leave.")

        WOSectionHeader("Meetings and Talking")
        WOWord("meeting — اجتماع — ijtimaa3")
        WOInfo("m · 2: اجتماعين ijtimaa3ein · pl: اجتماعات ijtimaa3aat")
        WOSmall("Your guess (\"ejtimea\") was right: ijtimaa3 / ejtimaa3, from the root j-m-3 (gather) — the same root as jaami3 (mosque) and jum3a (Friday / week).")
        WOWord("meeting room — غرفة اجتماعات — ghurfet ijtimaa3aat  (Leb/Syr: أوضة اجتماعات — 2ooDet ijtimaa3aat)")
        WOWord("online meeting — اجتماع أونلاين — ijtimaa3 online;  also: ijtimaa3 3a-zoom")
        WOWord("remote call — مكالمة — mukaalame  (a call · on the phone: mukaalame telefooniyye)")
        WOSmall("For a video call people mostly say \"call\" or \"zoom\": 3indi call — I have a call (commonly heard).")
        WOWord("to meet (someone) — التقى — iltaa2a / byiltii2i;  also: شاف — shaaf / bishuuf (lit. \"see\")")
        WOWord("to hold a meeting / meet (as a group) — اجتمع — ijtama3 / byijtimi3")
        WOExample("bniltii2i ba3d eD-Duhr? — منلتقي بعد الضهر؟ — Shall we meet in the afternoon?")
        WOWord("to discuss — ناقش — naa2ash / binaa2ish")
        WOInfo("discussion: نقاش — ni2aash · let's discuss it: yalla nnaa2ish-ha")
        WOWord("to persuade / to convince — أقنع — a2na3 / byi2ni3")
        WOInfo("Arabic uses one verb for both · to be convinced: اقتنع — iqtana3 / byiqtini3 · I'm not convinced: mish miqtini3")
        WOWord("to ask — سأل — sa2al / byis2al")
        WOWord("to question (interrogate) — استجوب — istajwab / byistajwib")
        WOWord("to question (doubt something) — شكّ ب — shakk b- / bishukk b-")
        WOWord("to answer — جاوب — jaawab / bijaawib;  also: ردّ على — radd 3ala (reply to)")
        WOExample("is2al el-mudiir w-huwwe bijaawbak — اسأل المدير وهو بيجاوبك — Ask the manager and he'll answer you.")
        WOWord("meeting summary — ملخّص الاجتماع — mulakhkhaS el-ijtimaa3;  also: summary (English)")
        WOWord("follow up (n) — متابعة — mutaaba3a")
        WOWord("to follow up — تابع — taaba3 / bitaabi3")
        WOExample("bitaabi3 ma3ak bukra — بتابع معك بكرا — I'll follow up with you tomorrow.")

        WOSectionHeader("Email and Phone")
        WOWord("email — إيميل — imeel;  formal: بريد إلكتروني — bariid iliktrooni")
        WOWord("to send an email — بعت إيميل — ba3at imeel / bib3at")
        WOWord("to receive / get an email — وصلني إيميل — wiSilni imeel  (lit. \"an email reached me\")")
        WOInfo("formal: استلم إيميل — istalam imeel · did you get my email? wiSlak el-imeel taba3i?")
        WOWord("phone — تلفون — talafoon;  mobile: موبايل — mobaayl;  Pal also: جوّال — jawwaal;  Leb also: خليوي — khilyawi")
        WOWord("phone number — رقم تلفون — ra2am talafoon;  also: نمرة — nimra")
        WOExample("shu ra2am talafoonak? — شو رقم تلفونك؟ — What's your phone number? (to m)")
        WOWord("address — عنوان — 3unwaan")
        WOInfo("m · pl: عناوين 3anaawiin · email address: 3unwaan el-imeel")

        WOSectionHeader("Tasks and Code")
        WOWord("task — مهمّة — mahamme;  casual: شغلة — shaghle (a thing to do)")
        WOInfo("f · 2: مهمّتين mahammtein · pl: مهمّات mahammaat / مهام mahaam")
        WOWord("to start a task — بلّش بالمهمّة — ballash bil-mahamme / biballish;  also: بدا — bada / bibda")
        WOWord("to finish a task — خلّص المهمّة — khallaS el-mahamme / bikhalliS")
        WOExample("khallaSt el-mahamme, hal2a3 bballish bil-jaay — خلّصت المهمّة، هلّق ببلّش باللي جاي — I finished the task, now I'm starting the next one.")
        WOWord("code review — مراجعة كود — muraaja3et koud;  everyday: code review (English)")
        WOWord("peer review — مراجعة من الزملا — muraaja3a min ez-zumala;  everyday: peer review (English)")
        WOWord("to debug — صلّح الأخطاء — SallaH el-akhTaa2 (fix the errors);  everyday: debug / dibag (English)")
        WOWord("bug — باغ — bug (English);  formal: خلل — khalal · error: غلط — ghalaT")
        WOSmall("In tech jobs the English words (code review, debug, bug, merge, deploy) are what people actually say, in an Arabic sentence: lazim a3mal debug la-hal-bug. The Arabic phrases here are formal translations.")

        WOSectionHeader("Computers and Network")
        WOWord("computer — كمبيوتر — kombyuuter;  formal: حاسوب — 7aasuub")
        WOWord("laptop — لابتوب — laabtob")
        WOWord("keyboard — كيبورد — kiiboord;  formal: لوحة المفاتيح — loo7et el-mafaatii7")
        WOWord("mouse — ماوس — maaws;  formal: فارة — faara")
        WOWord("cable — كبل — kabl;  also: شريط — shriiT (an electric wire, cord)")
        WOWord("network — شبكة — shabake")
        WOInfo("f · pl: شبكات shabakaat")
        WOWord("network cable — كبل شبكة — kabl shabake;  also: kabl internet")
        WOWord("wireless network — شبكة لاسلكية — shabake laa-silkiyye;  everyday: wifi (waay-faay) / wireless")
        WOWord("password — كلمة السرّ — kilmet es-sirr;  also: باسوورد — baswoord")
        WOExample("shu kilmet es-sirr taba3 el-wifi? — شو كلمة السرّ تبع الوايفاي؟ — What's the wifi password?")
        WOWord("to print — طبع — Taba3 / byiTba3")
        WOInfo("printer: طابعة — Taabi3a;  also: برنتر — printer")
        WOWord("to turn on — شغّل — shaghghal / bishaghghil;  lights: ضوّى — Dawwa / biDawwi")
        WOWord("to turn off — طفى — Tafa / byiTfi")
        WOExample("Tfi el-kombyuuter 2abl ma tiTla3 — طفي الكمبيوتر قبل ما تطلع — Turn off the computer before you leave. (to m)")

        WOSectionHeader("Office Furniture and Supplies")
        WOWord("desk — مكتب — maktab;  also: طاولة — Taawle (table)")
        WOWord("chair — كرسي — kursi")
        WOInfo("m · 2: كرسيين kursiyein · pl: كراسي karaasi")
        WOWord("air conditioner — مكيّف — mkayyif;  formal: مكيّف هوا — mukayyif hawa")
        WOSmall("Palestinians in Israel often say مزجان — mazgaan, from Hebrew מזגן (Wikipedia, Levantine vocabulary). In Lebanon you also hear \"AC\" / kondishon (commonly heard, not confirmed by a source).")
        WOExample("shaghghil el-mkayyif, shoob! — شغّل المكيّف، شوب! — Turn on the AC, it's hot!")
        WOWord("board (on the wall) / whiteboard — لوح — loo7;  also: لوح أبيض — loo7 abyaD")
        WOInfo("m · pl: ألواح alwaa7 · blackboard at school: لوح — loo7 / سبّورة — sabbuura")
        WOWord("marker (for the board) — ماركر — markar;  also: قلم لوح — 2alam loo7")
        WOWord("board eraser — مسّاحة — massaa7a")
        WOWord("pen — قلم — 2alam;  ballpoint: قلم حبر — 2alam 7ibr")
        WOInfo("m · 2: قلمين 2alamein · pl: أقلام a2laam")
        WOWord("pencil — قلم رصاص — 2alam raSaaS  (lit. \"lead pen\")")
        WOWord("eraser — محّاية — m7aaye;  formal: ممحاة — mim7aa")
        WOWord("pencil sharpener — برّاية — barraaye")
        WOSmall("massaa7a, m7aaye and barraaye are the everyday school-supply words; commonly heard, not confirmed by a dictionary source.")

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun WOSectionHeader(text: String) {
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
private fun WOWord(text: String) {
    Text(
        text = richText(boldHeadwordLine(text)),
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(top = 6.dp, bottom = 2.dp)
    )
}

@Composable
private fun WOInfo(text: String) {
    Text(
        text = fixArabizi("    $text"),
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(vertical = 1.dp)
    )
}

@Composable
private fun WOExample(text: String) {
    Text(
        text = fixArabizi("    e.g. $text"),
        style = MaterialTheme.typography.bodyMedium,
        fontStyle = FontStyle.Italic,
        modifier = Modifier.padding(vertical = 1.dp)
    )
}

@Composable
private fun WOSmall(text: String) {
    Text(
        text = fixArabizi("    $text"),
        style = MaterialTheme.typography.bodySmall,
        modifier = Modifier.padding(vertical = 2.dp)
    )
}

@Composable
private fun WONote(text: String) {
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
