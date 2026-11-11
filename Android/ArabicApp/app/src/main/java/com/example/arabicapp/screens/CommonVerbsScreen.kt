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
        note = "Same word; context distinguishes: \"o2af!\" = Stop! / \"wi2if 3ala rijleih\" = standing on his feet",
        iPres = "بوقف bo2af", iPast = "وقفت wi2ift", iFut = "رح اوقف ra7 o2af",
        mPres = "بتوقف bto2af", mPast = "وقفت wi2ift", mImp = "اوقف o2af",
        fPres = "بتوقفي bto2afi", fPast = "وقفتِ wi2ifti", fImp = "اوقفي o2afi"),
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
        fPres = "بتاكلي btaakli", fPast = "أكلتِ akalti", fImp = "كُلي kuli"),
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
    CommonVerbEntry("to open", "فتح", "fata7",
        iPres = "بفتح bafta7", iPast = "فتحت fata7t", iFut = "رح افتح ra7 afta7",
        mPres = "بتفتح btifta7", mPast = "فتحت fata7t", mImp = "افتح ifta7",
        fPres = "بتفتحي btifta7i", fPast = "فتحتِ fata7ti", fImp = "افتحي ifta7i"),
    CommonVerbEntry("to close", "سكّر", "sakkar", note = "Colloquial; lit. \"locked / sealed\"",
        iPres = "بسكّر basakker", iPast = "سكّرت sakkart", iFut = "رح اسكّر ra7 asakker",
        mPres = "بتسكّر btsakker", mPast = "سكّرت sakkart", mImp = "سكّر sakker",
        fPres = "بتسكّري btsakkri", fPast = "سكّرتِ sakkarti", fImp = "سكّري sakkri"),
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

        CVSectionHeader("Movement")
        movementVerbs.forEach { CVVerbCard(it) }

        CVSectionHeader("Communication")
        communicationVerbs.forEach { CVVerbCard(it) }

        CVSectionHeader("Senses")
        sensesVerbs.forEach { CVVerbCard(it) }

        CVSectionHeader("Mind")
        mindVerbs.forEach { CVVerbCard(it) }

        CVSectionHeader("Daily Life")
        dailyLifeVerbs.forEach { CVVerbCard(it) }

        CVSectionHeader("Shopping")
        shoppingVerbs.forEach { CVVerbCard(it) }

        CVSectionHeader("Actions & Tasks")
        actionVerbs.forEach { CVVerbCard(it) }

        CVSectionHeader("Work, Study & Play")
        workStudyPlayVerbs.forEach { CVVerbCard(it) }

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun CVVerbCard(v: CommonVerbEntry) {
    Column(modifier = Modifier.padding(bottom = 12.dp)) {
        Text(
            text = "${v.en} — ${v.ar} — ${v.tr}",
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 2.dp)
        )
        v.note?.let {
            Text(
                text = "  ($it)",
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
                Text(text = "    • $line", style = MaterialTheme.typography.bodySmall)
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
                append(form)
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
private fun CVBodyText(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(vertical = 2.dp)
    )
}
