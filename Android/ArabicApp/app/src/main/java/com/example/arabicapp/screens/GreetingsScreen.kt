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
fun GreetingsScreen() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        GrSectionHeader("Terms of Address — يا (ya) and Endearments")
        GrBodyText("يا — ya — vocative particle (\"O\" / \"hey\")")
        GrBodyText("  Used before a name or description when addressing someone directly.")
        GrBodyText("  يا سارة (ya Sara) = \"Hey Sara!\"")
        Spacer(modifier = Modifier.height(8.dp))
        GrBodyText("يا جميل — ya jamil — \"hey handsome / hey beautiful\" (said to a man)")
        GrBodyText("يا جميلة — ya jamile — same, said to a woman")
        GrBodyText("  جميل (jamil) = beautiful, handsome.  يا جميل is a warm, affectionate greeting.")
        Spacer(modifier = Modifier.height(8.dp))
        GrBodyText("حبيبي — 7abibi — \"my love / my dear\" (said to a man or close friend)")
        GrBodyText("حبيبتي — 7abibti — same, said to a woman")
        GrBodyText("  Extremely common in Levantine Arabic — used between friends, family, even strangers in a warm context.")
        Spacer(modifier = Modifier.height(8.dp))
        GrBodyText("يا حبيبي — ya 7abibi — \"oh my dear\" — adds warmth or emphasis")

        GrSectionHeader("Festive and Special Occasion Greetings")
        GrBodyText("سنة حلوة يا جميل — sana 7ilwe ya jamil — Happy New Year, beautiful one")
        GrBodyText("  سنة (sana) = year, حلوة (7ilwe) = sweet/nice, يا جميل = hey beautiful")
        GrBodyText("  (Said to men; يا جميلة (ya jamile) to women.  Some also say سنة حلوة يا جماعة (sane 7ilwe ya jama3a) to a group.)")
        Spacer(modifier = Modifier.height(8.dp))
        GrBodyText("عيد مبارك — 3eid mubarak — Blessed holiday / Happy Eid")
        GrBodyText("  Said for both Eid al-Fitr (end of Ramadan) and Eid al-Adha.")
        GrBodyText("  Response: الله يبارك فيك (Allah yibarak fik = May God bless you)")
        Spacer(modifier = Modifier.height(8.dp))
        GrBodyText("رمضان كريم — Ramadan karim — Generous Ramadan (Happy Ramadan)")
        GrBodyText("مبروك — mabruk — Congratulations!")
        GrBodyText("  Response: الله يبارك فيك (Allah yibarak fik)")

        GrSectionHeader("Morning Greetings")
        GrBodyText("صباح الخير — saba7 el khir — Good morning  (literally: morning of goodness)")
        GrBodyText("  Response: صباح النور (saba7 el nur = morning of light)")
        Spacer(modifier = Modifier.height(8.dp))
        GrBodyText("صباح النور — saba7 el nur — Response to good morning  (morning of light)")
        GrBodyText("  You can also respond: صباح الورد (saba7 el ward = morning of roses) — very warm")

        GrSectionHeader("Evening and Night Greetings")
        GrBodyText("مساء الخير — masa el khir — Good evening  (evening of goodness)")
        GrBodyText("  Response: مساء النور (masa el nur = evening of light)")
        Spacer(modifier = Modifier.height(8.dp))
        GrBodyText("مساء النور — masa el nur — Response to good evening")
        Spacer(modifier = Modifier.height(8.dp))
        GrBodyText("تصبح على خير — tisba7 3ala kheir — Good night  (m / f forms and replies: see Everyday Courtesy below)")

        GrSectionHeader("General Greetings")
        GrBodyText("أهلاً — ahlan — Hi / Welcome")
        GrBodyText("أهلاً وسهلاً — ahlan w-sahlan — Welcome! (fuller form; reply: see Everyday Courtesy below)")
        GrBodyText("هلا — hala — Hi (informal, very common in Levantine)")
        GrBodyText("مرحبا — mar7aba — Hello")
        GrBodyText("  Response: مرحبتين (mar7abtein = double welcome)")
        Spacer(modifier = Modifier.height(8.dp))
        GrBodyText("كيفك — kifak — How are you? (to a man)")
        GrBodyText("كيفك — kifik — How are you? (to a woman)")

        GrSectionHeader("Everyday Courtesy — Greetings and Their Replies")
        GrBodyText("Most of these are call-and-response: a fixed phrase gets a fixed reply. \"to a man\" ends in -ak; \"to a woman\" ends in -ik (Palestinian) or -ek / -e (Lebanese) — both are shown where they differ.")
        Spacer(modifier = Modifier.height(8.dp))

        GrBodyText("نشكر الله — nushkur allah — Thank God  (lit. \"we thank God\")")
        GrBodyText("  When: as an answer to kifak? / shu akhbarak? — \"good, thank God\". Same for a man or a woman (\"we\" doesn't change).")
        GrBodyText("  Note: means almost the same as el-7amdilla, which is more common overall; nushkur allah is commonly heard in Lebanon and Palestine, and many Christian speakers prefer it.")
        Spacer(modifier = Modifier.height(8.dp))

        GrBodyText("أهلا وسهلا — ahla w sahla / ahlan w sahlan — Welcome!")
        GrBodyText("  When: said by the host or shopkeeper when someone arrives — at the door, in a shop, at a table. Same to a man or a woman.")
        GrBodyText("  Reply: أهلا فيك — ahla fiik (to a man) / ahla fiiki, Leb. ahla fike (to a woman) — lit. \"welcome in you\" = welcome to you too")
        GrBodyText("  To a group: ahla fiikon.")
        Spacer(modifier = Modifier.height(8.dp))

        GrBodyText("السلام عليكم — salaamu 3aleikum — Peace be upon you")
        GrBodyText("  When: when entering a place or joining people — a room, a shop, a group. Same to a man, a woman, or a group (3aleikum is plural / respectful).")
        GrBodyText("  Reply: وعليكم السلام — wa 3aleikum es-salaam — and upon you be peace")
        GrBodyText("  Note: a religious-flavored greeting, used mostly by Muslims; Christians more often say mar7aba or ahlan — but everyone understands it and answers it.")
        Spacer(modifier = Modifier.height(8.dp))

        GrBodyText("يعطيك العافية — ya3tiik el-3aafye (to a man) / ya3tiiki, Leb. ya3tike / ta3tike el-3aafye (to a woman) — lit. \"may [God] give you strength / health\"")
        GrBodyText("  Fuller: الله يعطيك العافية — Allah ya3tiik el-3aafye. To a group: ya3tiikon el-3aafye.")
        GrBodyText("  When: (1) to someone working or tired — acknowledging their effort: a builder, a waiter, a cleaner, a friend cooking.")
        GrBodyText("  (2) Yes — also as a greeting when you walk into a shop or office or approach a worker, and as a polite opener before asking something. Some also say it when leaving after being served.")
        GrBodyText("  Reply: الله يعافيك — Allah y3aafiik (to a man) / Allah y3aafiiki, Leb. y3afike (to a woman) — \"and may God give you health\"")
        Spacer(modifier = Modifier.height(8.dp))

        GrBodyText("تصبح على خير — tisba7 3ala kheir (to a man) / تصبحي على خير — tisba7i, Leb. tosba7e 3ala kheir (to a woman) — Good night")
        GrBodyText("  Lit. \"may you wake up to goodness\". To a group: tisb7u 3ala kheir.")
        GrBodyText("  When: at bedtime, or when parting late in the evening / at night.")
        GrBodyText("  Reply: وانت من أهله — w inta min ahlo (to a man) / w inti min ahlo (to a woman) — \"and you are of its people\" = you too — the classic reply")
        GrBodyText("  Also heard: w inta / w inti b-kheir (\"and you in goodness\"), or simply saying tisba7 3ala kheir back.")
        Spacer(modifier = Modifier.height(8.dp))

        GrBodyText("الحمدلله على السلامة — el-7amdilla 3a-s-salaame — Thank God for your safety")
        GrBodyText("  When: to someone who came back from a trip or a long journey, got out of hospital or recovered from an illness, or came through an accident or a scare. Same to a man or a woman.")
        GrBodyText("  Reply: الله يسلمك — Allah ysallmak (to a man) / Allah ysallmik, Leb. ysalmek (to a woman) — may God keep you safe")
        Spacer(modifier = Modifier.height(8.dp))

        GrBodyText("شو أخبارك — shu akhbaarak (to a man) / shu akhbaarik, Leb. akhbarek (to a woman) — What's new? (lit. \"what's your news\")")
        GrBodyText("  When: a casual \"how are you / what's up\", especially with someone you haven't seen for a while.")
        GrBodyText("  Replies: تمام — tamaam / الحمدلله — el-7amdilla / أنا منيح — ana mni7 (m), ana mni7a (f)")
        GrBodyText("  ما في شي جديد — ma fi shi jdiid — nothing new")
        GrBodyText("  And yes — without ma: في شي جديد — fi shi jdiid — there's something new; as a question, fi shi jdiid? = anything new?")
        Spacer(modifier = Modifier.height(8.dp))

        GrBodyText("كيف صحتك — kiif sa77tak (to a man) / kiif sa77tik, Leb. sa77tek (to a woman) — How is your health?")
        GrBodyText("  When: to older people, to someone who was sick, or as a warmer, more caring \"how are you\".")
        GrBodyText("  Reply: الحمدلله — el-7amdilla / منيحة — mnii7a (my health is good) / Allah ysallmak (m) / ysallmik (f) — thanks for caring")
        Spacer(modifier = Modifier.height(8.dp))

        GrBodyText("شكرا — shukran — Thank you  (same to a man or a woman; Lebanese also say merci)")
        GrBodyText("  Replies, and when to use each:")
        GrBodyText("  تكرم — tikram (to a man) / tikrami, Leb. tikrame (to a woman) — the warm, most typical Levantine \"you're welcome\" (lit. \"be honored\" / for you, anything)")
        GrBodyText("  عفوا — 3afwan / العفو — el-3afu — neutral, pan-Arab, a bit more formal; more common in Palestine and Syria than in Lebanon")
        GrBodyText("  ولو — walaw — \"come on, of course!\" — no need to thank me; between friends and family when the favor was natural. Very Lebanese.")
        GrBodyText("  أهلا وسهلا — ahla w sahla — \"you're welcome here\" — typical from a shopkeeper or host")
        GrBodyText("  أكيد — akiid — \"sure / of course\" — heard casually, but not a standard reply to thanks; tikram or walaw sound more natural")
        Spacer(modifier = Modifier.height(8.dp))

        GrBodyText("يسلمو إيديك — yislamu ideik (to a man) / yislamu ideiki (to a woman) — lit. \"may your hands be safe\"")
        GrBodyText("  Lebanese often sound it as yeslamo idak / idake. Short form: يسلمو — yislamu.")
        GrBodyText("  When: when someone brings or serves you food or a drink, cooked for you, or made or fixed something with their hands — thanking their handiwork.")
        GrBodyText("  Reply: وإيديك — w ideik (to a man) / w ideiki (to a woman) — \"and your hands\"; also Allah ysallmak / ysallmik, or صحتين — sa77tein (\"bon appétit\") if you're eating")
        Spacer(modifier = Modifier.height(8.dp))

        GrBodyText("بخاطرك — bkhaaTrak (to a man) / bkhaaTrik, Leb. bkhaaTrek (to a woman) / bkhaaTerkon (to a group) — Bye  (lit. \"with your leave / permission\")")
        GrBodyText("  When: said by the person LEAVING — e.g. when leaving someone's house or a shop.")
        GrBodyText("  Reply (from the person staying): مع السلامة — ma3 es-salaame, or الله معك — Allah ma3ak (m) / Allah ma3ik (f)")
        Spacer(modifier = Modifier.height(8.dp))

        GrBodyText("مع السلامة — ma3 es-salaame — Goodbye  (lit. \"go with safety\")")
        GrBodyText("  When: said by the person STAYING to the one who is leaving. Same to a man or a woman.")
        GrBodyText("  Reply: الله يسلمك — Allah ysallmak → to a MAN")
        GrBodyText("  الله يسلمك — Allah ysallmik, Leb. Allah ysalmek → to a WOMAN")
        GrBodyText("  الله يسلمكم — Allah ysallimkon → to a group")
        Spacer(modifier = Modifier.height(4.dp))
        GrBodyText("  Typical exit from a house:")
        GrBodyText("    Guest: bkhaaTrak!   Host: ma3 es-salaame / Allah ma3ak.   Guest: Allah ysallmak.")
        Spacer(modifier = Modifier.height(8.dp))

        GrBodyText("بشوفك قريبا — bshuufak (to a man) / bshuufik, Leb. bshuufek (to a woman) 2ariiban — See you soon")
        GrBodyText("  Also: منشوفك — mnshuufak — \"we'll see you\" (Lebanese / Syrian).")
        GrBodyText("  Reply: إن شاء الله — inshalla / w ana kamaan — me too")

        GrSectionHeader("Goodbye and Introductions")
        GrBodyText("Goodbye / Bye — مع السلامة — ma3 es-salame  (lit: with safety; who says it and the reply: see Everyday Courtesy above)")
        GrBodyText("  Casual: يلا باي — yalla bye  (very common in everyday speech)")
        GrBodyText("See you — بشوفك — bshufak (to a man) / bshufik, Leb. bshufek (to a woman)")
        GrBodyText("  Also: يلا — yalla — casual farewell / let's go")
        Spacer(modifier = Modifier.height(8.dp))
        GrBodyText("What's your name? — شو اسمك؟ — shu ismak? (to a man) / shu ismek? (to a woman)")
        GrBodyText("My name is... — اسمي... — ismi...  (e.g. اسمي سارة = ismi Sara)")
        Spacer(modifier = Modifier.height(8.dp))
        GrBodyText("Where are you from? — من وين انت؟ — min wein inta? (to a man)")
        GrBodyText("  to a woman: من وين انتي؟ — min wein inti?")
        GrBodyText("I'm from... — أنا من... — ana min...  (e.g. أنا من إسرائيل = ana min Isra2il)")
        Spacer(modifier = Modifier.height(8.dp))
        GrBodyText("Do you speak Arabic? — بتحكي عربي؟ — bti7ki 3arabi?  (same form to m and f in Palestinian)")
        GrBodyText("I don't understand — ما بفهم — ma bfaham")
        GrBodyText("I don't understand Arabic — ما بفهم عربي — ma bfaham 3arabi")
        GrBodyText("  Useful follow-up: بفهم شوي — bfaham shwai — I understand a little")

        GrSectionHeader("Times of Day")
        GrBodyText("صباح — saba7 — morning")
        GrBodyText("ضهر — duhr — noon / midday")
        GrBodyText("بعد الضهر — ba3d el duhr — afternoon")
        GrBodyText("مسا — masa — evening")
        GrBodyText("ليل — leil — night")
        GrBodyText("نص الليل — nuss el leil — midnight")
        GrBodyText("نص النهار — nuss el nahar — midday / noon")

        GrSectionHeader("Asking How Someone Is")
        GrBodyText("كيفك — kifak / kifik — How are you? (m/f)")
        GrBodyText("كيف حالك — kif 7alak / kif 7alik — How are you? (m/f, slightly more formal)")
        GrBodyText("كيف تحس — kif tu7iss — How do you feel?")
        Spacer(modifier = Modifier.height(8.dp))
        GrBodyText("منيح — mni7 — I'm fine / good (m)")
        GrBodyText("منيحة — mni7a — I'm fine (f)")
        GrBodyText("أنا منيح — ana mni7 — I'm good / I'm fine (m)")
        GrBodyText("أنا منيحة — ana mni7a — I'm good / I'm fine (f)")
        GrBodyText("تمام — tamaam — Fine / OK  (as an answer to \"how are you?\" it means \"I'm well\")")
        GrBodyText("أنا تمام — ana tamaam — I'm fine  (same for men and women — tamaam doesn't change)")
        GrBodyText("أنا بخير — ana b-kheir — I'm well  (same for men and women; shared Levantine)")
        GrBodyText("الحمد لله — el 7amdu lillah — Praise God / I'm fine, thank God")
        GrBodyText("  In everyday speech it's said el-7amdilla / il-7amdilla (الحمدلله) — this is the \"alhamdellah\" you hear.")
        GrBodyText("  (The most common response to \"how are you?\" — said regardless of how you actually feel)")
        GrBodyText("نشكر الله — nushkur allah — Thank God  (lit. \"we thank God\"; very common, same for men and women)")
        GrBodyText("  e.g. منيح، نشكر الله — mnii7, nushkur allah — Good, thank God.")
        GrBodyText("  el-7amdilla and nushkur allah mean almost the same; el-7amdilla (\"praise be to God\") is the more common of the two.")
        GrBodyText("شكراً على السؤال — shukran 3ala is-su2aal — Thanks for asking")
        Spacer(modifier = Modifier.height(8.dp))
        GrBodyText("مش كتير منيح — mish ktir mni7 — Not too good")
        GrBodyText("تعبان — ta3ban — I'm tired / I don't feel well (m)")
        GrBodyText("تعبانة — ta3bane — I don't feel well (f)")
        GrBodyText("مش بخير — mish b-khir — Not well")

        GrSectionHeader("Polite Expressions")
        GrBodyText("لو سمحت — law sama7t — Please / Excuse me (to a man)")
        GrBodyText("لو سمحتي — law sama7ti — Please / Excuse me (to a woman)")
        GrBodyText("عفواً — 3afwan — You're welcome / Pardon me / Excuse me")
        GrBodyText("آسف — asif — Sorry (m)")
        GrBodyText("آسفة — asife — Sorry (f)")
        GrBodyText("مش مشكلة — mish mushkile — No worries / No problem")
        GrBodyText("ماشي — mashi — Sure / Okay / Alright")
        GrBodyText("تمام — tamaam — Perfect / Okay / Great  (also \"I'm fine\" — see Asking How Someone Is)")
        GrBodyText("أكيد — akid — Of course / Sure / Definitely")

        GrSectionHeader("Little Words — Connecting")
        GrBodyText("و — w- / wa — and  (attached to the next word: ana w-inta = me and you)")
        GrBodyText("أو — aw — or  (in statements: shai aw 2ahwe = tea or coffee)")
        GrBodyText("ولّا — willa — or  (in questions: shai willa 2ahwe? = tea or coffee?)")
        GrBodyText("يمكن — yimkin — maybe / perhaps")
        GrBodyText("ممكن — mumkin — maybe / possible  (also \"could you…?\": mumkin…)")
        GrBodyText("بلكي — balki — maybe / in case  (more rural / old-fashioned)")
        GrBodyText("بالمناسبة — bil-munasabe — by the way")
        GrBodyText("يعني — ya3ni — I mean / like / sort of  (extremely common filler word)")

        GrSectionHeader("Little Words — Yes, No, Sure")
        GrBodyText("آه — aah — yes  (the everyday yes)")
        GrBodyText("أيوا — aywa — yes  (a bit more emphatic)")
        GrBodyText("نعم — na3am — yes  (formal; in speech often means \"pardon?\")")
        GrBodyText("لأ — la2 — no  (as an answer)")
        GrBodyText("أكيد — akiid — sure / definitely")
        GrBodyText("طبعاً — tab3an — of course / naturally")

        GrSectionHeader("Little Words — \"Not\" With a Verb")
        GrBodyText("ما before a verb negates it — it means \"not\", inside a sentence. (لأ la2 is \"no\" as an answer.)")
        GrBodyText("ما بعرف — ma ba3rif — I don't know")
        GrBodyText("ما رحت — ma ru7t — I didn't go")
        GrBodyText("ما بحكي عربي — ma ba7ki 3arabi — I don't speak Arabic")
        GrBodyText("ما بدّي — ma biddi — I don't want")
        Spacer(modifier = Modifier.height(4.dp))
        GrBodyText("Often a -sh is added to the end of the verb for emphasis: ما بعرفش — ma ba3rifsh; ما رحتش — ma ru7tish. Both are normal Palestinian.")
        GrBodyText("مش — mish — not, for everything that isn't a plain verb: mish mni7 (not good), mish hon (not here), mish ra7 aruu7 (I'm not going to go). You'll also hear mish ba3rif. See the Negation screen.")

        GrSectionHeader("Time References")
        GrBodyText("هلّق / هلأ — halla2 / hala2 — now / right now (both spellings and pronunciations are used)")
        GrBodyText("اليوم — il-yom — today")
        GrBodyText("مبارح — mbaari7 — yesterday")
        GrBodyText("بكرا — bukra — tomorrow")

        GrSectionHeader("Meals")
        GrBodyText("فطور — ftuur — breakfast")
        GrBodyText("غدا — ghada — lunch  (the main meal of the day)")
        GrBodyText("عشا — 3asha — dinner / supper")
        GrBodyText("بدنا نفطر / نتغدى / نتعشى — bidna niftar / nitghadda / nit3ashsha — we want to have breakfast / lunch / dinner")

        GrSectionHeader("Places — Inside and Outside")
        GrBodyText("برّا — barra — outside")
        GrBodyText("جوّا — juwwa — inside")
        GrBodyText("هو برّا — huwwe barra — he's outside")
        GrBodyText("برّا البلاد — barra il-blaad — abroad (literally: outside the country)")

        GrSectionHeader("Small Talk — Work")
        GrBodyText("شو بتشتغل؟ — shu btishtghil? — What do you do (for work)? (to a man)")
        GrBodyText("شو بتشتغلي؟ — shu btishtghili? — What do you do? (to a woman)")
        GrBodyText("شو شغلك؟ — shu shughlak? / shughlek? — What's your job? (m / f)")
        GrBodyText("أنا بشتغل معلّم — ana bishtghil mu3allim — I work as a teacher")
        GrBodyText("أنا طالب / طالبة — ana taalib / taalbe — I'm a student (m / f)")

        GrSectionHeader("Shopping Phrases")
        GrBodyText("بكم هاد — bi-kam had — How much is this?")
        GrBodyText("بكم — bi-kam — How much?")
        GrBodyText("قدّيش حقّه؟ — 2addeish 7a22o? — How much does it cost? (masculine item)")
        GrBodyText("قدّيش حقّها؟ — 2addeish 7a22ha? — How much does it cost? (feminine item)")
        GrBodyText("  (literally \"how much is its due\" — 7a22 = right / due / price; very Palestinian)")
        GrBodyText("شو سعره؟ — shu si3ro? — What's its price? (masculine item)")
        GrBodyText("شو سعرها؟ — shu si3rha? — What's its price? (feminine item)")
        GrBodyText("غالي — ghali — expensive")
        GrBodyText("رخيص — rkhis — cheap / inexpensive")
        GrBodyText("هاد غالي كتير — had ghali ktir — That's very expensive")
        GrBodyText("هاد رخيص — had rkhis — That's cheap")

        GrSectionHeader("Reactions and Exclamations")
        GrBodyText("يسلموا — yislamu — Thank you / bless you  (short for yislamu ideik; full forms and reply: see Everyday Courtesy)")
        GrBodyText("رائع — ra2e3 — Awesome / Wonderful")
        GrBodyText("ممتاز — mumtaz — Excellent / Great")
        GrBodyText("هيك — heik — Like this / that's it / great (Levantine)")
        GrBodyText("هلق! — halla2! — Wow! / Now! (depending on context)")
        GrBodyText("يا إلهي — ya ilahi — Oh my God!")
        GrBodyText("وللا — walla — Really! / I swear! (very common)")
        GrBodyText("مش معقول — mish ma32ul — Unbelievable! / No way!")

        GrSectionHeader("Ordering and Paying")
        GrBodyText("شو بدك — shu biddak — What do you want? (to a man)")
        GrBodyText("شو بدك — shu biddik — What do you want? (to a woman)")
        GrBodyText("بدي اطلب — biddi utlub — I would like to order")
        GrBodyText("وين بدفع — wein badfa3 — Where can I pay?")
        GrBodyText("بقدر ادفع ببطاقة — bi2dur adfa3 b-bit2a — Can I pay with card?")
        GrBodyText("ما معي كاش — ma ma3i kash — I don't have cash")
        GrBodyText("كاش — kash — cash")
        GrBodyText("الحساب من فضلك — el 7isab min fadlak — The bill please (to a man)")

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun GrSectionHeader(text: String) {
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
private fun GrBodyText(text: String) {
    Text(
        text = fixArabizi(text),
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(vertical = 2.dp)
    )
}
