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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp

// Each form is "Arabic arabizi". iPres/iPast/iFut = I; m* = you (m); f* = you (f).
private data class CommonVerbEntry(
    val en: String,
    val ar: String,
    val tr: String,
    val note: String? = null,
    val iPres: String,
    val iPast: String,
    val iFut: String,
    val mPres: String,
    val mPast: String,
    val mImp: String,
    val fPres: String,
    val fPast: String,
    val fImp: String,
    val example: String? = null,
)

private val movementVerbs = listOf(
    CommonVerbEntry("to walk", "مشى", "masha",
        iPres = "بمشي bamshi", iPast = "مشيت masheet", iFut = "رح امشي ra7 amshi",
        mPres = "بتمشي btimshi", mPast = "مشيت masheet", mImp = "امشي imshi",
        fPres = "بتمشي btimshi (same as m)", fPast = "مشيتِ masheeti", fImp = "امشي imshi (same as m)"),
    CommonVerbEntry("to run", "ركض", "rakad",
        iPres = "بركض barkod", iPast = "ركضت rakadt", iFut = "رح اركض ra7 arkod",
        mPres = "بتركض btirkod", mPast = "ركضت rakadt", mImp = "اركض urkod",
        fPres = "بتركضي btirkodi", fPast = "ركضتِ rakadti", fImp = "اركضي urkodi"),
    CommonVerbEntry("to go", "راح", "raa7",
        iPres = "بروح baruu7", iPast = "رحت ru7t", iFut = "رح أروح ra7 aruu7",
        mPres = "بتروح bitruu7", mPast = "رحت ru7t", mImp = "روح ruu7",
        fPres = "بتروحي bitruu7i", fPast = "رحتِ ru7ti", fImp = "روحي ruu7i"),
    CommonVerbEntry("to return", "رجع", "rija3",
        iPres = "برجع barja3", iPast = "رجعت rji3t", iFut = "رح ارجع ra7 arja3",
        mPres = "بترجع btirja3", mPast = "رجعت rji3t", mImp = "ارجع irja3",
        fPres = "بترجعي btirja3i", fPast = "رجعتِ rji3ti", fImp = "ارجعي irja3i"),
    CommonVerbEntry("to drive", "ساق", "saa2", note = "qaf → glottal stop",
        iPres = "بسوق basuu2", iPast = "سقت su2t", iFut = "رح أسوق ra7 asuu2",
        mPres = "بتسوق bitsuu2", mPast = "سقت su2t", mImp = "سوق suu2",
        fPres = "بتسوقي bitsuu2i", fPast = "سقتِ su2ti", fImp = "سوقي suu2i"),
    CommonVerbEntry("to stop / to stand", "وقف", "wi2if",
        note = "Same word; context distinguishes: \"o2af!\" = Stop! / \"wi2if 3ala rijleih\" = standing on his feet. Stop someone / something (a car, a taxi) = وقّف wa22af: wa22if hon! = stop here!",
        iPres = "بوقف bo2af", iPast = "وقفت wi2ift", iFut = "رح اوقف ra7 o2af",
        mPres = "بتوقف bto2af", mPast = "وقفت wi2ift", mImp = "اوقف o2af",
        fPres = "بتوقفي bto2afi", fPast = "وقفتِ wi2ifti", fImp = "اوقفي o2afi"),
    CommonVerbEntry("to turn (right / left)", "لفّ", "laff",
        note = "liff 3al-yamiin / liff 3ash-shmaal = turn right / turn left. Syrian/Lebanese also برم baram: brom 3al-yamiin. In a taxi you'll also hear خود يمين khud yamiin = take a right",
        iPres = "بلفّ baliff", iPast = "لفّيت laffeet", iFut = "رح الفّ ra7 aliff",
        mPres = "بتلفّ btliff", mPast = "لفّيت laffeet", mImp = "لفّ liff",
        fPres = "بتلفّي btliffi", fPast = "لفّيتِ laffeeti", fImp = "لفّي liffi"),
    CommonVerbEntry("to continue / to keep going", "كمّل", "kammal",
        note = "kammel dughri = keep going straight. ضلّ dall + verb = keep doing something: dallak maashi = keep going",
        iPres = "بكمّل bakammel", iPast = "كمّلت kammalt", iFut = "رح اكمّل ra7 akammel",
        mPres = "بتكمّل btkammel", mPast = "كمّلت kammalt", mImp = "كمّل kammel",
        fPres = "بتكمّلي btkammli", fPast = "كمّلتِ kammalti", fImp = "كمّلي kammli"),
    CommonVerbEntry("to jump", "نطّ", "natt",
        note = "Everyday word in Palestinian and Syrian/Lebanese; قفز 2afaz is the formal/MSA word",
        iPres = "بنطّ banott", iPast = "نطّيت natteet", iFut = "رح انطّ ra7 anott",
        mPres = "بتنطّ bitnott", mPast = "نطّيت natteet", mImp = "نطّ nott",
        fPres = "بتنطّي bitnotti", fPast = "نطّيتِ natteeti", fImp = "نطّي notti"),
    CommonVerbEntry("to swim", "سبح", "sibi7",
        note = "Vowels vary by region (sbi7t / saba7t). Palestinian/Jordanian also عام 3aam (ba3uum, 3umt, 3uum!)",
        iPres = "بسبح basba7", iPast = "سبحت sbi7t", iFut = "رح اسبح ra7 asba7",
        mPres = "بتسبح btisba7", mPast = "سبحت sbi7t", mImp = "اسبح isba7",
        fPres = "بتسبحي btisba7i", fPast = "سبحتِ sbi7ti", fImp = "اسبحي isba7i"),
    CommonVerbEntry("to dive", "غطس", "ghitis",
        note = "Also غاص ghaas (diving deep, scuba). غطس also means to dip / go under the water",
        iPres = "بغطس baghtas", iPast = "غطست ghtist", iFut = "رح اغطس ra7 aghtas",
        mPres = "بتغطس btightas", mPast = "غطست ghtist", mImp = "اغطس ightas",
        fPres = "بتغطسي btightasi", fPast = "غطستِ ghtisti", fImp = "اغطسي ightasi"),
    CommonVerbEntry("to arrive", "وصل", "wisil",
        note = "Present vowels vary: buusal / bawsal; Syrian/Lebanese biwsal. Imperative is rare (used like \"get there!\")",
        iPres = "بوصل buusal", iPast = "وصلت wsilt", iFut = "رح اوصل ra7 uusal",
        mPres = "بتوصل btuusal", mPast = "وصلت wsilt", mImp = "اوصل uusal",
        fPres = "بتوصلي btuusali", fPast = "وصلتِ wsilti", fImp = "اوصلي uusali"),
    CommonVerbEntry("to leave", "طلع", "tili3",
        note = "Leave a place = tili3 min (lit. \"go out of\"). Leave something / someone behind = ترك tarak or خلّى khalla. \"I'm off\" = ana maashi; Lebanese also فلّ fall = to leave / head off",
        iPres = "بطلع batla3", iPast = "طلعت tli3t", iFut = "رح اطلع ra7 atla3",
        mPres = "بتطلع btitla3", mPast = "طلعت tli3t", mImp = "اطلع itla3",
        fPres = "بتطلعي btitla3i", fPast = "طلعتِ tli3ti", fImp = "اطلعي itla3i"),
    CommonVerbEntry("to enter", "فات", "faat",
        note = "The everyday Levantine word; دخل dakhal is also used (a bit more formal). فوت! fuut! = come in!",
        iPres = "بفوت bafuut", iPast = "فتت futt", iFut = "رح افوت ra7 afuut",
        mPres = "بتفوت bitfuut", mPast = "فتت futt", mImp = "فوت fuut",
        fPres = "بتفوتي bitfuuti", fPast = "فتتِ futti", fImp = "فوتي fuuti"),
    CommonVerbEntry("to exit / to go out", "طلع", "tili3",
        note = "Same verb as \"to leave\" (and also \"to go up\"). Exit (sign) = مخرج makhraj. itla3 barra! = get out!",
        iPres = "بطلع batla3", iPast = "طلعت tli3t", iFut = "رح اطلع ra7 atla3",
        mPres = "بتطلع btitla3", mPast = "طلعت tli3t", mImp = "اطلع itla3",
        fPres = "بتطلعي btitla3i", fPast = "طلعتِ tli3ti", fImp = "اطلعي itla3i"),
)

private val toBeVerbs = listOf(
    CommonVerbEntry("to be", "كان", "kaan",
        note = "In the present there is no verb, just like Hebrew: ana ta3baan = I (am) tired. The present form bakuun is used for habits (what you usually are), after biddi / laazim, and for \"will be\": ana ta3baan = I'm tired (right now), but bakuun ta3baan = I'm (usually) tired. See the habit examples below. " +
            "Yes, كان يا ما كان kaan ya ma kaan (\"once upon a time\") uses this same kaan. It's usually read as \"there was, or there wasn't\" (ما = not). " +
            "False friend: Hebrew כאן (here) sounds like Arabic كان (was). \"Here\" in Arabic is هون hon",
        iPres = "بكون bakuun", iPast = "كنت kunt", iFut = "رح اكون ra7 akuun",
        mPres = "بتكون btkuun", mPast = "كنت kunt", mImp = "كون kuun",
        fPres = "بتكوني btkuuni", fPast = "كنتِ kunti", fImp = "كوني kuuni",
        example = "كنت في البيت — kunt fil-beit — I was at home\n" +
            "بكرا رح اكون بالشغل — bukra ra7 akuun bish-shughl — Tomorrow I'll be at work\n" +
            "Habits (bakuun = I'm usually):\n" +
            "كل يوم بكون بالشغل الساعة تسعة — kull yoom bakuun bish-shughl es-saa3a tis3a — Every day I'm at work at nine\n" +
            "دايماً بكون تعبان بعد الشغل — daayman bakuun ta3baan ba3d esh-shughl — I'm always tired after work\n" +
            "بالصيف بكون الجو شوب — bis-seif bikuun ej-jaw shoob — In summer the weather is (usually) hot\n" +
            "لمّا بشرب قهوة بكون مبسوط — lamma bashrab 2ahwe bakuun mabsuut — When I drink coffee I'm happy\n" +
            "يوم الجمعة ما بكون بالبيت — yoom ej-jum3a ma bakuun bil-beit — On Fridays I'm not at home\n" +
            "After biddi / laazim (no b-):\n" +
            "بدّي اكون دكتور — biddi akuun daktoor — I want to be a doctor\n" +
            "لازم تكون هون بكرا — laazim tkuun hon bukra — You (m) have to be here tomorrow\n" +
            "كون منيح — kuun mnii7 — Be good (to a man)\n" +
            "كان يا ما كان — kaan ya ma kaan — Once upon a time"),
)

private val haveWantVerbs = listOf(
    CommonVerbEntry("to have", "عند", "3ind",
        note = "Not a verb: عند 3ind = \"at / by\" + the possessive ending (-i, -ak, -ik): \"at me (there is)…\". Same idea as Hebrew יש לי yesh li. Have on you right now = مع ma3: ma3ak masaari? = do you have money on you?",
        iPres = "عندي 3indi", iPast = "كان عندي kaan 3indi", iFut = "رح يكون عندي ra7 ykuun 3indi",
        mPres = "عندك 3indak", mPast = "كان عندك kaan 3indak", mImp = "— (no imperative)",
        fPres = "عندِك 3indik", fPast = "كان عندِك kaan 3indik", fImp = "— (no imperative)",
        example = "عندي سيّارة — 3indi sayyaara — I have a car\n" +
            "ما عندي وقت — ma 3indi wa2t — I don't have time\n" +
            "عندك إخوة؟ — 3indak ikhwe? — Do you (m) have brothers and sisters?\n" +
            "كان عندي كلب — kaan 3indi kalb — I had a dog"),
    CommonVerbEntry("to want", "بدّ", "bidd",
        note = "Not a verb: from bi-wuddi \"in my wish\" + the possessive ending. Syrian/Lebanese: baddi / baddak / baddik. Followed by a verb without b-: biddi ashrab. The real verb أراد araada (yuriid) is MSA only",
        iPres = "بدّي biddi", iPast = "كان بدّي kaan biddi", iFut = "رح يكون بدّي ra7 ykuun biddi (rare)",
        mPres = "بدّك biddak", mPast = "كان بدّك kaan biddak", mImp = "— (no imperative)",
        fPres = "بدِّك biddik", fPast = "كان بدِّك kaan biddik", fImp = "— (no imperative)",
        example = "بدّي اشرب قهوة — biddi ashrab 2ahwe — I want to drink coffee\n" +
            "شو بدّك؟ — shu biddak? — What do you (m) want?\n" +
            "ما بدّي — ma biddi — I don't want (to)\n" +
            "كان بدّي اجي بس ما قدرت — kaan biddi aaji bass ma 2dirt — I wanted to come but I couldn't"),
)

private val feelingsVerbs = listOf(
    CommonVerbEntry("to cry", "بكى", "baka",
        note = "عيّط 3ayyat is also heard for \"to cry\" (it's the standard word in Egyptian), but in parts of the Levant it means \"to shout / call out\". Usage varies, so baka is the safe choice",
        iPres = "ببكي babki", iPast = "بكيت bakeet", iFut = "رح ابكي ra7 abki",
        mPres = "بتبكي btibki", mPast = "بكيت bakeet", mImp = "ابكي ibki",
        fPres = "بتبكي btibki (same as m)", fPast = "بكيتِ bakeeti", fImp = "ابكي ibki (same as m)"),
    CommonVerbEntry("to laugh", "ضحك", "di7ik",
        note = "Laugh at = di7ik 3ala, which also means \"to fool / trick\" (di7ik 3alei = he fooled him)",
        iPres = "بضحك bad7ak", iPast = "ضحكت d7ikt", iFut = "رح اضحك ra7 ad7ak",
        mPres = "بتضحك btid7ak", mPast = "ضحكت d7ikt", mImp = "اضحك id7ak",
        fPres = "بتضحكي btid7aki", fPast = "ضحكتِ d7ikti", fImp = "اضحكي id7aki"),
    CommonVerbEntry("to smile", "ابتسم", "ibtasam",
        note = "Also تبسّم tbassam. A smile = ابتسامة ibtisaame",
        iPres = "ببتسم babtisim", iPast = "ابتسمت ibtasamt", iFut = "رح ابتسم ra7 abtisim",
        mPres = "بتبتسم btibtisim", mPast = "ابتسمت ibtasamt", mImp = "ابتسم ibtisim",
        fPres = "بتبتسمي btibtismi", fPast = "ابتسمتِ ibtasamti", fImp = "ابتسمي ibtismi"),
)

private val techCheckingVerbs = listOf(
    CommonVerbEntry("to record", "سجّل", "sajjal",
        note = "Record audio / video; also \"to register / sign up\"",
        iPres = "بسجّل basajjel", iPast = "سجّلت sajjalt", iFut = "رح اسجّل ra7 asajjel",
        mPres = "بتسجّل btsajjel", mPast = "سجّلت sajjalt", mImp = "سجّل sajjel",
        fPres = "بتسجّلي btsajjli", fPast = "سجّلتِ sajjalti", fImp = "سجّلي sajjli"),
    CommonVerbEntry("to connect", "وصّل", "wassal",
        note = "Connect a cable / device. Also \"to give a ride\": wasselni = drop me off. Connect to Wi-Fi: itwassal bil-wifi (wording varies)",
        iPres = "بوصّل bawassel", iPast = "وصّلت wassalt", iFut = "رح اوصّل ra7 awassel",
        mPres = "بتوصّل btwassel", mPast = "وصّلت wassalt", mImp = "وصّل wassel",
        fPres = "بتوصّلي btwassli", fPast = "وصّلتِ wassalti", fImp = "وصّلي wassli"),
    CommonVerbEntry("to disconnect", "فصل", "fasal",
        note = "Unplug / cut off. The connection dropped = الخطّ انقطع el-khatt in2ata3",
        iPres = "بفصل bafsil", iPast = "فصلت fasalt", iFut = "رح افصل ra7 afsil",
        mPres = "بتفصل btifsil", mPast = "فصلت fasalt", mImp = "افصل ifsil",
        fPres = "بتفصلي btifsli", fPast = "فصلتِ fasalti", fImp = "افصلي ifsli"),
    CommonVerbEntry("to check", "فحص", "fa7as",
        note = "Examine / test (a check-up = فحص fa7s). Everyday \"check\" is also شيّك shayyak, from English: shayyik 3al-imeil = check the email",
        iPres = "بفحص baf7as", iPast = "فحصت fa7ast", iFut = "رح افحص ra7 af7as",
        mPres = "بتفحص btif7as", mPast = "فحصت fa7ast", mImp = "افحص if7as",
        fPres = "بتفحصي btif7asi", fPast = "فحصتِ fa7asti", fImp = "افحصي if7asi"),
    CommonVerbEntry("to verify / to make sure", "تأكّد", "t2akkad",
        note = "t2akkad min = verify / make sure of. Are you sure? = متأكّد؟ mit2akked? (f: mit2akkde?)",
        iPres = "بتأكّد bat2akkad", iPast = "تأكّدت t2akkadt", iFut = "رح اتأكّد ra7 at2akkad",
        mPres = "بتتأكّد btit2akkad", mPast = "تأكّدت t2akkadt", mImp = "تأكّد t2akkad",
        fPres = "بتتأكّدي btit2akkadi", fPast = "تأكّدتِ t2akkadti", fImp = "تأكّدي t2akkadi"),
)

private val communicationVerbs = listOf(
    CommonVerbEntry("to speak / to talk", "حكى", "7aka", note = "Same word for both",
        iPres = "بحكي ba7ki", iPast = "حكيت 7akeet", iFut = "رح احكي ra7 a7ki",
        mPres = "بتحكي bti7ki", mPast = "حكيت 7akeet", mImp = "احكي i7ki",
        fPres = "بتحكي bti7ki (same as m)", fPast = "حكيتِ 7akeeti", fImp = "احكي i7ki (same as m)"),
    CommonVerbEntry("to say", "قال", "2aal", note = "qaf → glottal stop",
        iPres = "بقول ba2uul", iPast = "قلت 2ult", iFut = "رح أقول ra7 a2uul",
        mPres = "بتقول bit2uul", mPast = "قلت 2ult", mImp = "قول 2uul",
        fPres = "بتقولي bit2uuli", fPast = "قلتِ 2ulti", fImp = "قولي 2uuli"),
    CommonVerbEntry("to shout / to yell", "صرخ", "sarakh",
        note = "Same word for both; زعق zi3i2 is also common",
        iPres = "بصرخ basrukh", iPast = "صرخت sarakht", iFut = "رح اصرخ ra7 asrukh",
        mPres = "بتصرخ btusrukh", mPast = "صرخت sarakht", mImp = "اصرخ usrukh",
        fPres = "بتصرخي btusrukhi", fPast = "صرختِ sarakhti", fImp = "اصرخي usrukhi"),
    CommonVerbEntry("to apologize", "اعتذر", "i3tazar",
        note = "Apologize to someone = i3tazar min. In practice people usually just say آسف aasif (f: آسفة aasfe) = sorry, or سامحني saam7ni = forgive me",
        iPres = "بعتذر ba3tazer", iPast = "اعتذرت i3tazart", iFut = "رح اعتذر ra7 a3tazer",
        mPres = "بتعتذر bti3tazer", mPast = "اعتذرت i3tazart", mImp = "اعتذر i3tazer",
        fPres = "بتعتذري bti3tazri", fPast = "اعتذرتِ i3tazarti", fImp = "اعتذري i3tazri"),
    CommonVerbEntry("to thank", "شكر", "shakar",
        note = "bashkurak (to a man) / bashkurik (to a woman) = I thank you. Everyday: shukran; Syrian/Lebanese also yislamu",
        iPres = "بشكر bashkur", iPast = "شكرت shakart", iFut = "رح اشكر ra7 ashkur",
        mPres = "بتشكر btushkur", mPast = "شكرت shakart", mImp = "اشكر ushkur",
        fPres = "بتشكري btushkri", fPast = "شكرتِ shakarti", fImp = "اشكري ushkri"),
    CommonVerbEntry("to ask", "سأل", "sa2al",
        note = "Ask someone = sa2al + the person: is2alo = ask him. Ask for something / request / order = طلب talab",
        iPres = "بسأل bas2al", iPast = "سألت sa2alt", iFut = "رح اسأل ra7 as2al",
        mPres = "بتسأل btis2al", mPast = "سألت sa2alt", mImp = "اسأل is2al",
        fPres = "بتسألي btis2ali", fPast = "سألتِ sa2alti", fImp = "اسألي is2ali"),
    CommonVerbEntry("to answer", "جاوب", "jaawab",
        note = "Reply / answer the phone = ردّ radd (3ala): ma radd 3alayy = he didn't answer me",
        iPres = "بجاوب bajaawib", iPast = "جاوبت jaawabt", iFut = "رح اجاوب ra7 ajaawib",
        mPres = "بتجاوب btjaawib", mPast = "جاوبت jaawabt", mImp = "جاوب jaawib",
        fPres = "بتجاوبي btjaawbi", fPast = "جاوبتِ jaawabti", fImp = "جاوبي jaawbi"),
    CommonVerbEntry("to call someone on the phone", "اتّصل ب", "ittasal b-",
        note = "Literally \"connected with\". Very common alternative: رنّ على rann 3ala (\"rang\"), e.g. رنّيت عليه ranneet 3alei = I called him",
        iPres = "بتّصل battasel", iPast = "اتّصلت ittasalt", iFut = "رح اتّصل ra7 attasel",
        mPres = "بتتّصل btittasel", mPast = "اتّصلت ittasalt", mImp = "اتّصل ittasel",
        fPres = "بتتّصلي btittasli", fPast = "اتّصلتِ ittasalti", fImp = "اتّصلي ittasli",
        example = "بدّي اتّصل بإمّي بكرا الصبح — biddi attasel b-immi bukra es-sub7 — I want to call my mother tomorrow morning\n" +
            "رنّ عليّ لمّا توصل — rinn 3alayy lamma tuusal — Call me when you arrive (to a man)\n" +
            "اتّصلت فيك مبارح بس ما رديت — ittasalt fiik mbaari7 bass ma raddeet — I called you yesterday but you didn't answer"),
)

private val sensesVerbs = listOf(
    CommonVerbEntry("to see", "شاف", "shaaf",
        note = "Syrian/Lebanese past: شفت shift",
        iPres = "بشوف bashuuf", iPast = "شفت shuft", iFut = "رح أشوف ra7 ashuuf",
        mPres = "بتشوف bitshuuf", mPast = "شفت shuft", mImp = "شوف shuuf",
        fPres = "بتشوفي bitshuufi", fPast = "شفتِ shufti", fImp = "شوفي shuufi"),
    CommonVerbEntry("to look", "اطّلّع", "ittalla3",
        note = "To look at = ittalla3 3ala. شوف! shuuf! is also used for \"look!\"",
        iPres = "بطّلّع battalla3", iPast = "اطّلّعت ittalla3t", iFut = "رح اطّلّع ra7 attalla3",
        mPres = "بتطّلّع btittalla3", mPast = "اطّلّعت ittalla3t", mImp = "اطّلّع ittalla3",
        fPres = "بتطّلّعي btittal3i", fPast = "اطّلّعتِ ittalla3ti", fImp = "اطّلّعي ittal3i"),
    CommonVerbEntry("to watch", "تفرّج", "tfarraj",
        note = "tfarraj 3ala = watch (TV, a game, a show). Palestinian also حضر 7idir: 7idirt film = I watched a movie",
        iPres = "بتفرّج batfarraj", iPast = "تفرّجت tfarrajt", iFut = "رح اتفرّج ra7 atfarraj",
        mPres = "بتتفرّج btitfarraj", mPast = "تفرّجت tfarrajt", mImp = "تفرّج tfarraj",
        fPres = "بتتفرّجي btitfarraji", fPast = "تفرّجتِ tfarrajti", fImp = "تفرّجي tfarraji"),
    CommonVerbEntry("to hear", "سمع", "simi3",
        note = "Syrian/Lebanese: sme3",
        iPres = "بسمع basma3", iPast = "سمعت simi3t", iFut = "رح اسمع ra7 asma3",
        mPres = "بتسمع btisma3", mPast = "سمعت simi3t", mImp = "اسمع isma3",
        fPres = "بتسمعي btisma3i", fPast = "سمعتِ simi3ti", fImp = "اسمعي isma3i"),
    CommonVerbEntry("to listen", "سمع", "simi3",
        note = "Same word as \"to hear\" in everyday speech (isma3! = listen!). نصّت nassat = to listen closely / pay attention",
        iPres = "بسمع basma3", iPast = "سمعت simi3t", iFut = "رح اسمع ra7 asma3",
        mPres = "بتسمع btisma3", mPast = "سمعت simi3t", mImp = "اسمع isma3",
        fPres = "بتسمعي btisma3i", fPast = "سمعتِ simi3ti", fImp = "اسمعي isma3i"),
)

private val mindVerbs = listOf(
    CommonVerbEntry("to know", "عرف", "3irif",
        note = "Also \"to find out\". Syrian/Lebanese past: 3refet",
        iPres = "بعرف ba3ref", iPast = "عرفت 3rift", iFut = "رح اعرف ra7 a3ref",
        mPres = "بتعرف bti3ref", mPast = "عرفت 3rift", mImp = "اعرف i3ref",
        fPres = "بتعرفي bti3rfi", fPast = "عرفتِ 3rifti", fImp = "اعرفي i3rfi"),
    CommonVerbEntry("to think", "فكّر", "fakkar",
        note = "fakkar fi / fakkar 3an = think about. \"I think that…\" = بفكّر إنّه bafakker inno, or بظنّ bazunn",
        iPres = "بفكّر bafakker", iPast = "فكّرت fakkart", iFut = "رح افكّر ra7 afakker",
        mPres = "بتفكّر btfakker", mPast = "فكّرت fakkart", mImp = "فكّر fakker",
        fPres = "بتفكّري btfakkri", fPast = "فكّرتِ fakkarti", fImp = "فكّري fakkri"),
    CommonVerbEntry("to believe", "صدّق", "sadda2",
        note = "Believe a person or a story (ma bsadde2! = I don't believe it!). Believe in God / an idea = آمن ب aaman b-",
        iPres = "بصدّق basadde2", iPast = "صدّقت sadda2t", iFut = "رح اصدّق ra7 asadde2",
        mPres = "بتصدّق btsadde2", mPast = "صدّقت sadda2t", mImp = "صدّق sadde2",
        fPres = "بتصدّقي btsad2i", fPast = "صدّقتِ sadda2ti", fImp = "صدّقي sad2i"),
    CommonVerbEntry("to understand", "فهم", "fihim",
        note = "fhimt? = got it? / ma fhimt = I didn't understand. Syrian/Lebanese past: fhemet",
        iPres = "بفهم bafham", iPast = "فهمت fhimt", iFut = "رح افهم ra7 afham",
        mPres = "بتفهم btifham", mPast = "فهمت fhimt", mImp = "افهم ifham",
        fPres = "بتفهمي btifhami", fPast = "فهمتِ fhimti", fImp = "افهمي ifhami"),
    CommonVerbEntry("to forget", "نسي", "nisi",
        iPres = "بنسى bansa", iPast = "نسيت niseet", iFut = "رح انسى ra7 ansa",
        mPres = "بتنسى btinsa", mPast = "نسيت niseet", mImp = "انسى insa",
        fPres = "بتنسي btinsi", fPast = "نسيتِ niseeti", fImp = "انسي insi"),
    CommonVerbEntry("to remember", "تذكّر", "tzakkar",
        note = "Also heard as tdhakkar. \"Do you remember?\" is often فاكر؟ faaker? (f: faakra?)",
        iPres = "بتذكّر batzakkar", iPast = "تذكّرت tzakkart", iFut = "رح اتذكّر ra7 atzakkar",
        mPres = "بتتذكّر btitzakkar", mPast = "تذكّرت tzakkart", mImp = "تذكّر tzakkar",
        fPres = "بتتذكّري btitzakkari", fPast = "تذكّرتِ tzakkarti", fImp = "تذكّري tzakkari"),
)

private val dailyLifeVerbs = listOf(
    CommonVerbEntry("to eat", "أكل", "akal",
        iPres = "باكل baakol", iPast = "أكلت akalt", iFut = "رح آكل ra7 aakol",
        mPres = "بتاكل btaakol", mPast = "أكلت akalt", mImp = "كُل kul",
        fPres = "بتاكلي btaakli", fPast = "أكلتِ akalti", fImp = "كُلي kuli",
        note = "Lebanese (often Syrian too): the past akalt (I / you m) is usually said with a short helping vowel, akal(e)t, stressed a-KAL-et. Lebanese you (f) is often heard as akalte. Don't confuse it with she ate = akalit / akalet, stressed on the first a (A-ka-let). Palestinian keeps akalt / akalti"),
    CommonVerbEntry("to drink", "شرب", "shirib",
        iPres = "بشرب bashrab", iPast = "شربت shiribt", iFut = "رح اشرب ra7 ashrab",
        mPres = "بتشرب btishrab", mPast = "شربت shiribt", mImp = "اشرب ishrab",
        fPres = "بتشربي btishrabi", fPast = "شربتِ shiribti", fImp = "اشربي ishrabi"),
    CommonVerbEntry("to sleep", "نام", "naam",
        iPres = "بنام banaam", iPast = "نمت nimt", iFut = "رح انام ra7 anaam",
        mPres = "بتنام bitnaam", mPast = "نمت nimt", mImp = "نام naam",
        fPres = "بتنامي bitnaami", fPast = "نمتِ nimti", fImp = "نامي naami"),
    CommonVerbEntry("to sit", "قعد", "2a3ad", note = "qaf → glottal stop. جلس jalas is the more formal word",
        iPres = "بقعد ba23od", iPast = "قعدت 2a3adt", iFut = "رح اقعد ra7 a23od",
        mPres = "بتقعد bti23od", mPast = "قعدت 2a3adt", mImp = "اقعد u23od",
        fPres = "بتقعدي bti23di", fPast = "قعدتِ 2a3adti", fImp = "اقعدي u23di"),
    CommonVerbEntry("to rest", "ارتاح", "irtaa7",
        iPres = "برتاح bartaa7", iPast = "ارتحت irta7t", iFut = "رح ارتاح ra7 artaa7",
        mPres = "بترتاح btirtaa7", mPast = "ارتحت irta7t", mImp = "ارتاح irtaa7",
        fPres = "بترتاحي btirtaa7i", fPast = "ارتحتِ irta7ti", fImp = "ارتاحي irtaa7i"),
    CommonVerbEntry("to turn on", "ولّع", "walla3",
        note = "Lit. \"ignited\": lights, fire, stove. For machines/TV also شغّل shaghghal",
        iPres = "بولّع bawalle3", iPast = "ولّعت walla3t", iFut = "رح اولّع ra7 awalle3",
        mPres = "بتولّع btwalle3", mPast = "ولّعت walla3t", mImp = "ولّع walle3",
        fPres = "بتولّعي btwal3i", fPast = "ولّعتِ walla3ti", fImp = "ولّعي wal3i"),
    CommonVerbEntry("to turn off", "طفّى", "taffa",
        iPres = "بطفّي bataffi", iPast = "طفّيت taffeet", iFut = "رح اطفّي ra7 ataffi",
        mPres = "بتطفّي btaffi", mPast = "طفّيت taffeet", mImp = "طفّي taffi",
        fPres = "بتطفّي btaffi (same as m)", fPast = "طفّيتِ taffeeti", fImp = "طفّي taffi (same as m)"),
    CommonVerbEntry("to behave / to act", "تصرّف", "tsarraf",
        note = "Behave nicely = tsarraf mnii7. The other meaning of Hebrew נהוג, \"it's customary\", = el-3aade inno… (عادة 3aade = custom / habit)",
        iPres = "بتصرّف batsarraf", iPast = "تصرّفت tsarraft", iFut = "رح اتصرّف ra7 atsarraf",
        mPres = "بتتصرّف btitsarraf", mPast = "تصرّفت tsarraft", mImp = "تصرّف tsarraf",
        fPres = "بتتصرّفي btitsarrafi", fPast = "تصرّفتِ tsarrafti", fImp = "تصرّفي tsarrafi"),
)

private val shoppingVerbs = listOf(
    CommonVerbEntry("to buy", "اشترى", "ishtara",
        iPres = "بشتري bashtri", iPast = "اشتريت ishtareet", iFut = "رح اشتري ra7 ashtri",
        mPres = "بتشتري btishtri", mPast = "اشتريت ishtareet", mImp = "اشتري ishtri",
        fPres = "بتشتري btishtri (same as m)", fPast = "اشتريتِ ishtareeti", fImp = "اشتري ishtri (same as m)"),
    CommonVerbEntry("to sell", "باع", "baa3",
        iPres = "ببيع babii3", iPast = "بعت bi3t", iFut = "رح ابيع ra7 abii3",
        mPres = "بتبيع bitbii3", mPast = "بعت bi3t", mImp = "بيع bii3",
        fPres = "بتبيعي bitbii3i", fPast = "بعتِ bi3ti", fImp = "بيعي bii3i"),
)

private val actionVerbs = listOf(
    CommonVerbEntry("to start / to begin (להתחיל)", "بلّش", "ballash",
        note = "The everyday word in Palestinian, Syrian and Lebanese. بدأ bada2 is formal / MSA. Start doing something = ballash + a verb without b-: ballasht ashtghil = I started working. Start work = ballash esh-shughl",
        iPres = "ببلّش baballesh", iPast = "بلّشت ballasht", iFut = "رح ابلّش ra7 aballesh",
        mPres = "بتبلّش btballesh", mPast = "بلّشت ballasht", mImp = "بلّش ballesh",
        fPres = "بتبلّشي btballshi", fPast = "بلّشتِ ballashti", fImp = "بلّشي ballshi",
        example = "بلّشت الشغل الساعة تمانية — ballasht esh-shughl es-saa3a tmaanye — I started work at eight\n" +
            "الفيلم بلّش — el-film ballash — The film has started\n" +
            "بلّش تحكي! — ballesh ti7ki! — Start talking! (to a man)"),
    CommonVerbEntry("to finish / to end (לסיים)", "خلّص", "khallas",
        note = "Finish something = khallas. It ended / it's over (no object) = خلص khilis: el-film khilis. خلص! khalas! = enough / that's it / done (Hebrew slang חלאס comes from it)",
        iPres = "بخلّص bakhalles", iPast = "خلّصت khallast", iFut = "رح اخلّص ra7 akhalles",
        mPres = "بتخلّص btkhalles", mPast = "خلّصت khallast", mImp = "خلّص khalles",
        fPres = "بتخلّصي btkhallsi", fPast = "خلّصتِ khallasti", fImp = "خلّصي khallsi",
        example = "خلّصت الشغل — khallast esh-shughl — I finished work\n" +
            "لمّا تخلّص، تعال — lamma tkhalles, ta3aal — When you finish, come (to a man)\n" +
            "الفيلم خلص — el-film khilis — The film is over"),
    CommonVerbEntry("to open", "فتح", "fata7",
        iPres = "بفتح bafta7", iPast = "فتحت fata7t", iFut = "رح افتح ra7 afta7",
        mPres = "بتفتح btifta7", mPast = "فتحت fata7t", mImp = "افتح ifta7",
        fPres = "بتفتحي btifta7i", fPast = "فتحتِ fata7ti", fImp = "افتحي ifta7i"),
    CommonVerbEntry("to close", "سكّر", "sakkar", note = "Colloquial; lit. \"locked / sealed\"",
        iPres = "بسكّر basakker", iPast = "سكّرت sakkart", iFut = "رح اسكّر ra7 asakker",
        mPres = "بتسكّر btsakker", mPast = "سكّرت sakkart", mImp = "سكّر sakker",
        fPres = "بتسكّري btsakkri", fPast = "سكّرتِ sakkarti", fImp = "سكّري sakkri"),
    CommonVerbEntry("to lock", "قفل", "2afal",
        note = "A lock = قفل 2ifl, key = مفتاح miftaa7. People also just say sakker el-baab bil-miftaa7 = lock the door (lit. close it with the key)",
        iPres = "بقفل ba2fil", iPast = "قفلت 2afalt", iFut = "رح اقفل ra7 a2fil",
        mPres = "بتقفل bti2fil", mPast = "قفلت 2afalt", mImp = "اقفل i2fil",
        fPres = "بتقفلي bti2fli", fPast = "قفلتِ 2afalti", fImp = "اقفلي i2fli"),
    CommonVerbEntry("to give back / to return (something)", "رجّع", "rajja3",
        note = "The doubled-middle form of rija3 (to come back) means to bring back, the same idea as Hebrew לחזור → להחזיר",
        iPres = "برجّع barajje3", iPast = "رجّعت rajja3t", iFut = "رح ارجّع ra7 arajje3",
        mPres = "بترجّع btrajje3", mPast = "رجّعت rajja3t", mImp = "رجّع rajje3",
        fPres = "بترجّعي btraj3i", fPast = "رجّعتِ rajja3ti", fImp = "رجّعي raj3i"),
    CommonVerbEntry("to try", "جرّب", "jarrab",
        note = "Also try on clothes and taste food. Try hard / attempt = حاول 7aawal",
        iPres = "بجرّب bajarreb", iPast = "جرّبت jarrabt", iFut = "رح اجرّب ra7 ajarreb",
        mPres = "بتجرّب btjarreb", mPast = "جرّبت jarrabt", mImp = "جرّب jarreb",
        fPres = "بتجرّبي btjarrbi", fPast = "جرّبتِ jarrabti", fImp = "جرّبي jarrbi"),
    CommonVerbEntry("to help", "ساعد", "saa3ad",
        note = "ممكن تساعدني؟ mumken tsaa3idni? = Can you help me?",
        iPres = "بساعد basaa3ed", iPast = "ساعدت saa3adt", iFut = "رح اساعد ra7 asaa3ed",
        mPres = "بتساعد btsaa3ed", mPast = "ساعدت saa3adt", mImp = "ساعد saa3ed",
        fPres = "بتساعدي btsaa3di", fPast = "ساعدتِ saa3adti", fImp = "ساعدي saa3di"),
    CommonVerbEntry("to take", "أخد", "akhad",
        note = "Syrian/Lebanese imperative: khood / khédi",
        iPres = "باخد baakhod", iPast = "أخدت akhadt", iFut = "رح آخد ra7 aakhod",
        mPres = "بتاخد btaakhod", mPast = "أخدت akhadt", mImp = "خُد khud",
        fPres = "بتاخدي btaakhdi", fPast = "أخدتِ akhadti", fImp = "خُدي khudi"),
    CommonVerbEntry("to give", "عطى", "3ata",
        note = "\"Give me!\" = اعطيني a3tiini, or هات haat (f: هاتي haati)",
        iPres = "بعطي ba3ti", iPast = "عطيت 3ateet", iFut = "رح اعطي ra7 a3ti",
        mPres = "بتعطي bti3ti", mPast = "عطيت 3ateet", mImp = "اعطي a3ti",
        fPres = "بتعطي bti3ti (same as m)", fPast = "عطيتِ 3ateeti", fImp = "اعطي a3ti (same as m)"),
    CommonVerbEntry("to make", "عمل", "3imil",
        note = "Same verb as \"to do\". سوّى sawwa is also heard. Cook a meal = طبخ tabakh",
        iPres = "بعمل ba3mal", iPast = "عملت 3milt", iFut = "رح اعمل ra7 a3mal",
        mPres = "بتعمل bti3mal", mPast = "عملت 3milt", mImp = "اعمل i3mal",
        fPres = "بتعملي bti3mali", fPast = "عملتِ 3milti", fImp = "اعملي i3mali"),
    CommonVerbEntry("to do", "عمل", "3imil",
        note = "شو بتعمل؟ shu bti3mal? = What are you doing? / What do you do?",
        iPres = "بعمل ba3mal", iPast = "عملت 3milt", iFut = "رح اعمل ra7 a3mal",
        mPres = "بتعمل bti3mal", mPast = "عملت 3milt", mImp = "اعمل i3mal",
        fPres = "بتعملي bti3mali", fPast = "عملتِ 3milti", fImp = "اعملي i3mali"),
    CommonVerbEntry("to prepare", "جهّز", "jahhaz",
        note = "Get something ready. For food also حضّر 7addar",
        iPres = "بجهّز bajahhez", iPast = "جهّزت jahhazt", iFut = "رح اجهّز ra7 ajahhez",
        mPres = "بتجهّز btjahhez", mPast = "جهّزت jahhazt", mImp = "جهّز jahhez",
        fPres = "بتجهّزي btjahhzi", fPast = "جهّزتِ jahhazti", fImp = "جهّزي jahhzi"),
    CommonVerbEntry("to paint / to draw", "رسم", "rasam",
        note = "Paint a wall = دهن dahan",
        iPres = "برسم barsom", iPast = "رسمت rasamt", iFut = "رح ارسم ra7 arsom",
        mPres = "بترسم btirsom", mPast = "رسمت rasamt", mImp = "ارسم ursom",
        fPres = "بترسمي btirsmi", fPast = "رسمتِ rasamti", fImp = "ارسمي ursmi"),
    CommonVerbEntry("to read", "قرأ", "2ara", note = "qaf → glottal stop",
        iPres = "بقرا ba2ra", iPast = "قريت 2areet", iFut = "رح اقرا ra7 a2ra",
        mPres = "بتقرا bti2ra", mPast = "قريت 2areet", mImp = "اقرا i2ra",
        fPres = "بتقري bti2ri", fPast = "قريتِ 2areeti", fImp = "اقري i2ri"),
    CommonVerbEntry("to write", "كتب", "katab",
        iPres = "بكتب baktub", iPast = "كتبت katabt", iFut = "رح اكتب ra7 aktub",
        mPres = "بتكتب btuktub", mPast = "كتبت katabt", mImp = "اكتب uktub",
        fPres = "بتكتبي btuktbi", fPast = "كتبتِ katabti", fImp = "اكتبي uktbi"),
    CommonVerbEntry("to pick up / to lift", "شال", "shaal",
        note = "Lift / carry / take away. Pick up from the floor = لقط la2at. Pick someone up (by car) = مرّ على marr 3ala. Pick up the phone = ردّ ع التلفون radd 3at-talafoon",
        iPres = "بشيل bashiil", iPast = "شلت shilt", iFut = "رح اشيل ra7 ashiil",
        mPres = "بتشيل bitshiil", mPast = "شلت shilt", mImp = "شيل shiil",
        fPres = "بتشيلي bitshiili", fPast = "شلتِ shilti", fImp = "شيلي shiili"),
    CommonVerbEntry("to pick / to choose", "اختار", "ikhtaar",
        note = "Also نقّى na22a = to pick out (the best ones). Pick fruit / flowers = قطف 2ataf",
        iPres = "بختار bakhtaar", iPast = "اخترت ikhtart", iFut = "رح اختار ra7 akhtaar",
        mPres = "بتختار btikhtaar", mPast = "اخترت ikhtart", mImp = "اختار ikhtaar",
        fPres = "بتختاري btikhtaari", fPast = "اخترتِ ikhtarti", fImp = "اختاري ikhtaari"),
    CommonVerbEntry("to put", "حطّ", "7att",
        iPres = "بحطّ ba7ott", iPast = "حطّيت 7atteet", iFut = "رح احطّ ra7 a7ott",
        mPres = "بتحطّ bit7ott", mPast = "حطّيت 7atteet", mImp = "حطّ 7ott",
        fPres = "بتحطّي bit7otti", fPast = "حطّيتِ 7atteeti", fImp = "حطّي 7otti"),
    CommonVerbEntry("to send", "بعت", "ba3at",
        note = "MSA: أرسل arsal",
        iPres = "ببعت bab3at", iPast = "بعتت ba3att", iFut = "رح ابعت ra7 ab3at",
        mPres = "بتبعت btib3at", mPast = "بعتت ba3att", mImp = "ابعت ib3at",
        fPres = "بتبعتي btib3ati", fPast = "بعتتِ ba3atti", fImp = "ابعتي ib3ati"),
    CommonVerbEntry("to receive", "استلم", "istalam",
        note = "Receive a package, salary, keys. Receive a guest = استقبل ista2bal. \"I got a message\" = اجتني رسالة ijatni risaale",
        iPres = "بستلم bastalem", iPast = "استلمت istalamt", iFut = "رح استلم ra7 astalem",
        mPres = "بتستلم btistalem", mPast = "استلمت istalamt", mImp = "استلم istalem",
        fPres = "بتستلمي btistalmi", fPast = "استلمتِ istalamti", fImp = "استلمي istalmi"),
    CommonVerbEntry("to fix / to repair", "صلّح", "salla7",
        iPres = "بصلّح basalle7", iPast = "صلّحت salla7t", iFut = "رح اصلّح ra7 asalle7",
        mPres = "بتصلّح btsalle7", mPast = "صلّحت salla7t", mImp = "صلّح salle7",
        fPres = "بتصلّحي btsal7i", fPast = "صلّحتِ salla7ti", fImp = "صلّحي sal7i"),
    CommonVerbEntry("to change", "غيّر", "ghayyar",
        note = "Change clothes = غيّر أواعيه ghayyar awa3ii. Change money = صرّف sarraf. Something changed (by itself) = تغيّر tghayyar",
        iPres = "بغيّر baghayyer", iPast = "غيّرت ghayyart", iFut = "رح اغيّر ra7 aghayyer",
        mPres = "بتغيّر btghayyer", mPast = "غيّرت ghayyart", mImp = "غيّر ghayyer",
        fPres = "بتغيّري btghayyri", fPast = "غيّرتِ ghayyarti", fImp = "غيّري ghayyri"),
)

private val workStudyPlayVerbs = listOf(
    CommonVerbEntry("to work", "اشتغل", "ishtaghal",
        iPres = "بشتغل bashtghel", iPast = "اشتغلت ishtaghalt", iFut = "رح اشتغل ra7 ashtghel",
        mPres = "بتشتغل btishtghel", mPast = "اشتغلت ishtaghalt", mImp = "اشتغل ishtghel",
        fPres = "بتشتغلي btishtghli", fPast = "اشتغلتِ ishtaghalti", fImp = "اشتغلي ishtghli"),
    CommonVerbEntry("to practice", "تمرّن", "tmarran",
        note = "Practise / train / exercise (sport, instrument, a language: tmarran 3ala). تدرّب tdarrab = to train (for a job, a team)",
        iPres = "بتمرّن batmarran", iPast = "تمرّنت tmarrant", iFut = "رح اتمرّن ra7 atmarran",
        mPres = "بتتمرّن btitmarran", mPast = "تمرّنت tmarrant", mImp = "تمرّن tmarran",
        fPres = "بتتمرّني btitmarrani", fPast = "تمرّنتِ tmarranti", fImp = "تمرّني tmarrani"),
    CommonVerbEntry("to play", "لعب", "li3ib",
        note = "Play a game or sport: li3ib football. Play an instrument = عزف على 3azaf 3ala",
        iPres = "بلعب bal3ab", iPast = "لعبت li3ibt", iFut = "رح العب ra7 al3ab",
        mPres = "بتلعب btil3ab", mPast = "لعبت li3ibt", mImp = "العب il3ab",
        fPres = "بتلعبي btil3abi", fPast = "لعبتِ li3ibti", fImp = "العبي il3abi"),
    CommonVerbEntry("to study", "درس", "daras",
        iPres = "بدرس badrus", iPast = "درست darast", iFut = "رح ادرس ra7 adrus",
        mPres = "بتدرس btudrus", mPast = "درست darast", mImp = "ادرس udrus",
        fPres = "بتدرسي btudrsi", fPast = "درستِ darasti", fImp = "ادرسي udrsi"),
    CommonVerbEntry("to learn", "تعلّم", "t3allam",
        iPres = "بتعلّم bat3allam", iPast = "تعلّمت t3allamt", iFut = "رح اتعلّم ra7 at3allam",
        mPres = "بتتعلّم btit3allam", mPast = "تعلّمت t3allamt", mImp = "تعلّم t3allam",
        fPres = "بتتعلّمي btit3allami", fPast = "تعلّمتِ t3allamti", fImp = "تعلّمي t3allami"),
    CommonVerbEntry("to teach", "علّم", "3allam",
        iPres = "بعلّم ba3allem", iPast = "علّمت 3allamt", iFut = "رح اعلّم ra7 a3allem",
        mPres = "بتعلّم bt3allem", mPast = "علّمت 3allamt", mImp = "علّم 3allem",
        fPres = "بتعلّمي bt3allmi", fPast = "علّمتِ 3allamti", fImp = "علّمي 3allmi"),
)

@Composable
fun CommonVerbsScreen() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        CVBodyText("Each verb is listed in the past tense, 3rd person masculine singular (\"he did\"), the standard Arabic dictionary form. Below it are the forms for I, you (m) and you (f).")
        CVBodyText("• \"I\" has the same form for a man and a woman in Arabic, so it gets a single line: present, past and future.")
        CVBodyText("• Future = رح ra7 + the present without b-.")
        CVBodyText("• Forms shown are Palestinian. Syrian/Lebanese often say bi- for \"I\" (ana bishrab instead of bashrab); bigger differences are given in the note.")
        CVBodyText("• Past tense in Lebanon: a short helping e often breaks the consonants at the end, so akalt sounds like akal(e)t and shribt like shrebet. The spelling doesn't change.")

        CVSectionHeader("To Be")
        toBeVerbs.forEach { CVVerbCard(it) }

        CVSectionHeader("Have and Want (not regular verbs)")
        CVBodyText("\"To have\" and \"to want\" are not real verbs in spoken Arabic. They are pseudo-verbs: a little word plus the possessive ending you already know from 3indi / beiti (-i me, -ak you m, -ik you f).")
        CVBodyText("• No b- in the present, no past endings, no imperative.")
        CVBodyText("• Past = كان kaan + the form (kaan 3indi = I had). Future = رح يكون ra7 ykuun + the form.")
        CVBodyText("• Negation with ما ma: ma 3indi = I don't have, ma biddi = I don't want.")
        haveWantVerbs.forEach { CVVerbCard(it) }

        CVSectionHeader("Movement")
        movementVerbs.forEach { CVVerbCard(it) }

        CVSectionHeader("Communication")
        communicationVerbs.forEach { CVVerbCard(it) }

        CVSectionHeader("Senses")
        sensesVerbs.forEach { CVVerbCard(it) }

        CVSectionHeader("Mind")
        mindVerbs.forEach { CVVerbCard(it) }

        CVSectionHeader("Feelings")
        feelingsVerbs.forEach { CVVerbCard(it) }

        CVSectionHeader("Daily Life")
        dailyLifeVerbs.forEach { CVVerbCard(it) }

        CVSectionHeader("Shopping")
        shoppingVerbs.forEach { CVVerbCard(it) }

        CVSectionHeader("Actions & Tasks")
        actionVerbs.forEach { CVVerbCard(it) }

        CVSectionHeader("Tech & Checking")
        techCheckingVerbs.forEach { CVVerbCard(it) }

        CVSectionHeader("Work, Study & Play")
        workStudyPlayVerbs.forEach { CVVerbCard(it) }

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun CVVerbCard(v: CommonVerbEntry) {
    Column(modifier = Modifier.padding(bottom = 12.dp)) {
        Text(
            text = fixArabizi("${v.en} — ${v.ar} — ${v.tr}"),
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 2.dp)
        )
        v.note?.let {
            Text(
                text = fixArabizi("  ($it)"),
                style = MaterialTheme.typography.bodySmall,
                fontStyle = FontStyle.Italic
            )
        }
        CVFormLine("I", listOf("present" to v.iPres, "past" to v.iPast, "future" to v.iFut))
        CVFormLine("you (m)", listOf("present" to v.mPres, "past" to v.mPast, "imperative" to v.mImp))
        CVFormLine("you (f)", listOf("present" to v.fPres, "past" to v.fPast, "imperative" to v.fImp))
        v.example?.let { ex ->
            Text(
                text = "  Examples:",
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(top = 4.dp)
            )
            ex.split("\n").forEach { line ->
                Text(text = fixArabizi("    • $line"), style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}

@Composable
private fun CVFormLine(person: String, forms: List<Pair<String, String>>) {
    Text(
        text = buildAnnotatedString {
            append("  ")
            withStyle(SpanStyle(fontWeight = FontWeight.SemiBold)) { append("$person: ") }
            forms.forEachIndexed { i, (label, form) ->
                if (i > 0) append(" · ")
                append("$label ")
                append(fixArabizi(form))
            }
        },
        style = MaterialTheme.typography.bodySmall,
        modifier = Modifier.padding(vertical = 1.dp)
    )
}

@Composable
private fun CVSectionHeader(text: String) {
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
private fun CVBodyText(text: String) {
    Text(
        text = fixArabizi(text),
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(vertical = 2.dp)
    )
}
