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
fun FirstWordsScreen() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        FWNote("The first words to learn. Palestinian forms come first; where Lebanese, Syrian or Jordanian speakers say it differently, the variant is shown too. Arabic changes words for talking to a man (m) or a woman (f) — both forms are shown wherever they differ.")

        FWSectionHeader("Pronouns — I, You, He, She, We, You (pl.), They")
        FWWord("I — أنا — ana  (same for a man and a woman)")
        FWExample("ana ta3baan (m) / ana ta3baane (f) — أنا تعبان / أنا تعبانة — I'm tired.")
        FWSmall("The word ana doesn't change, but adjectives and participles after it do: add -e / -a for a woman.")
        FWWord("you (to a man) — إنتَ — inta")
        FWWord("you (to a woman) — إنتِ — inti")
        FWExample("inta mnii7? (m) / inti mnii7a? (f) — إنت منيح؟ / إنتِ منيحة؟ — Are you well?")
        FWWord("he — هو — huwwe  (Lebanese/Syrian also huwwe / hu)")
        FWWord("she — هي — hiyye  (Lebanese/Syrian also hiyye / hi)")
        FWWord("we — إحنا — i7na  (same for men and women;  Lebanese ni7na — نحنا)")
        FWWord("you (plural) — إنتو — intu  (same for men and women)")
        FWWord("they — همّ — humme  (Palestinian;  also humma)  /  هنّي — hinne  (Lebanese/Syrian)  — same for men and women")
        FWNote("Masculine vs feminine: in the singular, 'you' has two forms (inta / inti) and 'he/she' are different words (huwwe / hiyye). In the plural, spoken Levantine Arabic uses ONE form for both genders: i7na, intu, humme/hinne cover men, women and mixed groups.\nMSA (formal Arabic) still has separate forms — antum (m) / antunna (f), hum (m) / hunna (f) — you'll see them in writing and news, but not in everyday speech.")

        FWSectionHeader("Having — I Have, I Don't Have")
        FWWord("I have — عندي — 3indi")
        FWExample("3indi su2aal — عندي سؤال — I have a question.")
        FWWord("I don't have — ما عندي — ma 3indi  (Palestinian also ما عنديش — ma 3indiish)")
        FWExample("ma 3indi wa2t — ما عندي وقت — I don't have time.")
        FWSmall("3indi doesn't change for gender — the same for a man and a woman. 3indak (you have, m) / 3indik (you have, f).")

        FWSectionHeader("Basic Words")
        FWWord("yes — آه — ah;  أيوه — aywa;  نعم — na3am (formal)")
        FWWord("no — لا — la2  (Lebanese/Syrian also la)")
        FWWord("correct — صحّ — sa77;  مزبوط — mazbuut")
        FWWord("sure / of course — أكيد — akiid;  طبعاً — tab3an")
        FWWord("exactly — بالزبط — bizzabt;  بالضبط — bid-dabt;  تماماً — tamaaman")
        FWWord("suddenly — فجأة — faj2a")
        FWExample("faj2a ija — فجأة إجا — Suddenly he came.")
        FWWord("but — بس — bas;  لكن — laakin (more formal)")
        FWWord("if — إذا — iza  (real conditions);  لو — law  (unreal / wishes)")
        FWExample("iza biddak, ta3aal — إذا بدّك، تعال — If you want, come.")
        FWWord("maybe — يمكن — yimkin;  ممكن — mumkin")
        FWWord("or — أو — aw (statements);  ولّا — willa (questions)")
        FWExample("shai willa 2ahwe? — شاي ولّا قهوة؟ — Tea or coffee?")
        FWWord("not — مش — mish (before adjectives, nouns;  Lebanese also mush);  ما — ma (before verbs)")
        FWExample("mish ta3baan — مش تعبان — not tired  ·  ma ba3rif — ما بعرف — I don't know")
        FWWord("I don't know — ما بعرف — ma ba3rif;  مش عارف / مش عارفة — mish 3aarif (m) / mish 3aarfe (f)")
        FWWord("question — سؤال — su2aal")
        FWInfo("m · 2: سؤالين su2aalein · pl: أسئلة as2ile")
        FWWord("answer — جواب — jawaab")
        FWInfo("m · 2: جوابين jawaabein · pl: أجوبة ajwibe")

        FWSectionHeader("This, That, Now")
        FWWord("this (m) — هاد / هادا — haad / haada  (Palestinian);  هيدا — hayda  (Lebanese/Syrian)")
        FWExample("haada beiti — هادا بيتي — This is my house.  ·  Lebanese: hayda beiti — هيدا بيتي")
        FWWord("this (f) — هاي / هادي — haay / haadi  (Palestinian);  هيدي — haydi  (Lebanese/Syrian; Lebanese say it like \"hayde\")")
        FWExample("haay sayyarti — هاي سيارتي — This is my car.  ·  Lebanese: haydi sayyarti — هيدي سيارتي")
        FWSmall("You may hear \"haytha\" — but the spoken word has a d, not a th: hayda / haydi. The th belongs to formal Arabic هذا (haadha) / هذه (haadhihi).")
        FWWord("now / right now — هلّق / هلأ — halla2 / hala2;  Jordanian also هسّا — hassa")
        FWExample("ana mashghuul halla2 — أنا مشغول هلّق — I'm busy right now.")
        FWWord("until now / so far — لهلّق — la-halla2  (also لحدّ هلّق — la-7add halla2)")
        FWExample("la-halla2 ma ija — لهلّق ما إجا — He hasn't come yet (until now he didn't come).")
        FWWord("I'm going to watch TV — رح أتفرّج عالتلفزيون — ra7 atfarraj 3at-telfizyon  (most common in Palestinian)")
        FWWord("I'm going to watch TV — رح أحضر تلفزيون — ra7 a7dar telfizyon  (Lebanese/Syrian/Jordanian)")
        FWSmall("Both mean the same. tfarraj 3ala = watch (lit. look at); 7idir = watch (TV / a film / a match), more Lebanese-Syrian. 3al + t → 3at- (the l blends into the sun letter t). ra7 = going to (future).")

        FWSectionHeader("About Me")
        FWWord("I am from ___ — أنا من ___ — ana min ___")
        FWExample("ana min Yaafa — أنا من يافا — I'm from Jaffa.")
        FWWord("I am ___ years old — عمري ___ سنة — 3umri ___ sane  (lit. 'my age is ___ years')")
        FWExample("3umri 3ishriin sane — عمري عشرين سنة — I'm twenty years old.")
        FWExample("3umri khamas sniin — عمري خمس سنين — I'm five years old.  (3–10 take the plural sniin)")
        FWWord("I am a man — أنا زلمة — ana zalame;  also أنا رجّال — ana rajjaal (Palestinian/Jordanian)")
        FWInfo("zalame — m · 2: زلمتين zalamtein · pl: زلام zlaam")
        FWWord("I am a woman — أنا مرا — ana mara;  also أنا ست — ana sitt (polite: lady)")
        FWInfo("mara — f · 2: مرتين martein (rare) · pl: نسوان niswaan")
        FWWord("old (person) — كبير بالعمر — kbiir bil-3umr (m) / kbiire bil-3umr (f);  ختيار — khitiyaar (m) / ختيارة — khitiyaara (f) (an old man / woman)")
        FWWord("old (thing) — قديم — 2adiim (m) / قديمة — 2adiime (f)")
        FWSmall("For people use kbiir (bil-3umr) or khitiyaar; for things use 2adiim. Saying a person is 2adiim sounds like they're an antique!")
        FWWord("young — صغير — zghiir (m) / zghiire (f);  شاب — shabb (a young man) / صبيّة — sabiyye (a young woman)")
        FWWord("tired — تعبان — ta3baan (m) / تعبانة — ta3baane (f)")
        FWWord("Where are you from? — من وين إنت؟ — min wein inta? (m) / min wein inti? (f)")
        FWWord("How old are you? — قديش عمرك؟ — 2addeish 3umrak? (m) / 2addeish 3umrik? (f);  also كم عمرك؟ — kam 3umrak / 3umrik?")

        FWSectionHeader("Work Today")
        FWWord("When did you start working today? — إمتى بلّشت الشغل اليوم؟ — emta ballasht esh-shughl el-yoom? (to m) / إمتى بلّشتِ الشغل اليوم؟ — emta ballashti esh-shughl el-yoom? (to f)")
        FWSmall("Also with a verb: emta ballasht tishtghil? (m) / emta ballashti tishtighli? (f). ballash = to start (no b- on the verb after it).")
        FWExample("ballasht es-saa3a tmaanye — بلّشت الساعة تمانية — I started at eight.")
        FWWord("When did you finish working today? — إمتى خلّصت الشغل اليوم؟ — emta khallast esh-shughl el-yoom? (to m) / إمتى خلّصتِ الشغل اليوم؟ — emta khallasti esh-shughl el-yoom? (to f)")
        FWExample("khallast es-saa3a khamse — خلّصت الساعة خمسة — I finished at five.")
        FWWord("How many hours did you work? — كم ساعة اشتغلت؟ — kam saa3a shtaghalt? (to m) / كم ساعة اشتغلتِ؟ — kam saa3a shtaghalti? (to f)")
        FWExample("shtaghalt tamaan saa3aat — اشتغلت تمان ساعات — I worked eight hours.")
        FWSmall("kam (how many) takes a SINGULAR noun: kam saa3a, kam yoom — unlike Hebrew כמה שעות. 2addeish also works: 2addeish saa3a shtaghalt?")

        FWSectionHeader("How Often")
        FWWord("usually — عادةً — 3aadatan;  بالعادة — bil-3aade")
        FWExample("3aadatan bashrab 2ahwe es-sub7 — عادةً بشرب قهوة الصبح — I usually drink coffee in the morning.")
        FWWord("always — دايماً — daayman;  also عطول — 3a-tuul (all the time)")
        FWExample("daayman mit2akhkhir! — دايماً متأخّر! — (You're) always late! (to m)")
        FWWord("never — أبداً — abadan  (with ma + verb);  عمري ما — 3umri ma… (never in my life)")
        FWExample("ma bashrab abadan — ما بشرب أبداً — I never drink.  ·  3umri ma shuft-o — عمري ما شفته — I've never seen him (in my life).")
        FWSmall("3umri = 'my life / my age', so 3umri ma shuft = 'my life, I didn't see'. For you: 3umrak ma… (m) / 3umrik ma… (f).")
        FWWord("sometimes — أحياناً — a7yaanan;  مرّات — marraat (more everyday)")
        FWExample("marraat baruu7 3al-ba7r — مرّات بروح عالبحر — Sometimes I go to the sea.")
        FWWord("occasionally / from time to time — من وقت لوقت — min wa2t la-wa2t")

        FWSectionHeader("Some- and Every- Words")
        FWWord("someone — حدا — 7ada")
        FWExample("fi 7ada bil-beit? — في حدا بالبيت؟ — Is anyone home?")
        FWWord("everyone / everybody — كل الناس — kull en-naas;  الكل — el-kull;  كل واحد — kull wa7ad (each one)")
        FWWord("everywhere — بكل مكان — b-kull makaan;  also بكل مطرح — b-kull matra7")
        FWWord("somewhere — شي مكان — shi makaan;  شي مطرح — shi matra7  (Palestinian also ishi = something)")
        FWWord("some time — at some point: شي مرّة — shi marra / شي يوم — shi yoom;  a bit of time: شويّة وقت — shwayyet wa2t")
        FWSmall("More (nobody, nothing, nowhere…): Learning The Language → Every, Some, . . .")

        FWSectionHeader("Going")
        FWWord("go home — روح عالبيت — ruu7 3al-beit (to m) / روحي عالبيت — ruu7i 3al-beit (to f)")
        FWExample("ra7 aruu7 3al-beit — رح أروح عالبيت — I'm going (to go) home.")
        FWWord("go outside — اطلع برّا — itla3 barra (to m) / اطلعي برّا — itla3i barra (to f)")
        FWWord("go away — روح من هون — ruu7 min hon (to m) / روحي من هون — ruu7i min hon (to f)")
        FWSmall("Blunt. امشي imshi! (same for m and f) = 'get lost!' — rude, use with care.")

        FWSectionHeader("Next / Last")
        FWWord("next week — الأسبوع الجاي — el-usbuu3 ej-jaay;  also الجمعة الجاية — ej-jum3a ej-jaaye")
        FWWord("next year — السنة الجاية — es-sane ej-jaaye")
        FWWord("last week — الأسبوع اللي فات — el-usbuu3 elli faat;  also الأسبوع الماضي — el-usbuu3 el-maadi")
        FWWord("last year — السنة اللي فاتت — es-sane elli faatat;  also السنة الماضية — es-sane el-maadye")
        FWSmall("jaay / jaaye = 'coming' (m / f), elli faat / faatat = 'that passed'. The ending follows the noun: usbuu3 is m, sane is f. jum3a means both 'Friday' and 'week' in spoken Levantine.")

        FWSectionHeader("Meetings")
        FWWord("meeting — اجتماع — ijtimaa3")
        FWInfo("m · 2: اجتماعين ijtimaa3ein · pl: اجتماعات ijtimaa3aat")
        FWWord("I have a meeting next week — عندي اجتماع الأسبوع الجاي — 3indi ijtimaa3 el-usbuu3 ej-jaay")
        FWWord("I had a meeting last year — كان عندي اجتماع السنة اللي فاتت — kaan 3indi ijtimaa3 es-sane elli faatat")
        FWWord("I have a meeting now — عندي اجتماع هلّق — 3indi ijtimaa3 halla2")
        FWWord("I have a meeting in two hours — عندي اجتماع بعد ساعتين — 3indi ijtimaa3 ba3d saa3tein")
        FWSmall("'in two hours' = ba3d saa3tein (lit. 'after two hours'). saa3tein is the dual of saa3a. In 5 minutes = ba3d khams da2aayi2.")

        FWSectionHeader("I Have, I Had, I Used To Have, I Will Have")
        FWWord("I have ___ — عندي ___ — 3indi ___")
        FWExample("3indi sayyaara — عندي سيّارة — I have a car.")
        FWWord("I had ___ — كان عندي ___ — kaan 3indi ___")
        FWExample("kaan 3indi wa2t — كان عندي وقت — I had time.")
        FWWord("I used to have ___ — كان عندي ___ — kaan 3indi ___  (+ زمان zamaan / من زمان min zamaan = 'back then')")
        FWExample("zamaan kaan 3indi kalb — زمان كان عندي كلب — I used to have a dog (back then).")
        FWWord("I will have ___ — رح يكون عندي ___ — ra7 ykuun 3indi ___")
        FWExample("bukra ra7 ykuun 3indi wa2t — بكرا رح يكون عندي وقت — Tomorrow I'll have time.")
        FWSmall("Exactly like Hebrew: יש לי / היה לי / יהיה לי. 'had' and 'used to have' are the same words in both languages (היה לי = kaan 3indi); context or zamaan shows it was a habit.")

        FWSectionHeader("There Is (like Hebrew יש)")
        FWWord("there is / there are — في — fi  (= יש)")
        FWExample("fi 2ahwe? — في قهوة؟ — Is there coffee?  ·  fi naas hon — في ناس هون — There are people here.")
        FWWord("there isn't / there aren't — ما في — ma fi  (= אין;  Palestinian also ما فيش — ma fiish)")
        FWExample("ma fi mushkile — ما في مشكلة — No problem (there's no problem).")
        FWWord("there was — كان في — kaan fi  (= היה / היו)")
        FWWord("there will be — رح يكون في — ra7 ykuun fi  (= יהיה)")
        FWSmall("fi = something exists. For owning use 3indi: 'I have coffee' = 3indi 2ahwe (יש לי), 'there's coffee' = fi 2ahwe (יש קפה).")

        FWSectionHeader("Prices")
        FWWord("What is the price of this? — شو سعر هاد؟ — shu si3r hada? (m thing) / شو سعر هادي؟ — shu si3r hadi? (f thing)")
        FWSmall("si3r hada is a construct (iDaafa): si3r 'price' + hada 'this' = 'the price of this'. si3r takes no el- — it's already definite through what follows (like Hebrew smichut). hada / hadi = this;  hadaak / hadiik = that.")
        FWWord("What's the price? — شو السعر؟ — shu es-si3r?")
        FWWord("What's its price? — شو سعره؟ — shu si3ro? (m) / شو سعرها؟ — shu si3rha? (f);  Lebanese style: shu si3ra? (f)")
        FWSmall("Why no el- in si3ro / si3rha / si3ra? The ending -o / -ha / -a ('its') already makes the noun definite, and Arabic never puts el- together with a possessive ending (es-si3ra is wrong). Same as Hebrew: מחירו / מחירה, never המחירו. Use el- only when there's no ending: shu es-si3r? = 'what's the price?'")
        FWWord("How much does it cost? — قديش بكلّف؟ — 2addeish bikallif? (m) / قديش بتكلّف؟ — 2addeish bitkallif? (f)")
        FWWord("How much is it? — قديش حقّه؟ — 2addeish 7a22o? (m) / قديش حقّها؟ — 2addeish 7a22ha? (f);  also قديش سعره؟ — 2addeish si3ro? / si3rha?")
        FWWord("How much is it? (Lebanese) — قدّيه حقّه؟ — 2addeh 7a22o? (m) / قدّيه حقّها؟ — 2addeh 7a22a? (f)")
        FWSmall("Here m / f refer to the THING you're asking about (el-ktaab is m, el-blouze is f), not to the person you're talking to. 2addeish 7a22o is the most common way at the market in Palestine, Syria and Lebanon. 2addeh (often written 'adde' / 'addeh') is the Lebanese short form of 2addeish. Lebanese also tends to drop the h of -ha ('her / its'), so 7a22a = 7a22ha and si3ra = si3rha.")

        FWSectionHeader("Greetings")
        FWWord("hi / hello — مرحبا — marhaba;  أهلا — ahlan;  هلا — hala  (Lebanese also hai)")
        FWExample("reply to marhaba: marhabtein — مرحبتين (lit. 'two hellos');  reply to ahlan: ahlein — أهلين")
        FWWord("good morning — صباح الخير — sabaa7 el-kheir")
        FWWord("    reply: صباح النور — sabaa7 en-nuur  (lit. 'morning of light')")
        FWWord("good evening — مسا الخير — masa el-kheir")
        FWWord("    reply: مسا النور — masa en-nuur  (lit. 'evening of light')")
        FWNote("You remembered these correctly! Greetings in Arabic work as call and response: the first person says … el-kheir (… of goodness) and the other answers … en-nuur (… of light). In Arabizi: sabaa7 el-kheir / sabaa7 en-nuur, masa el-kheir / masa en-nuur. Many people also reply sabaa7 el-kheir back, or add fancier replies like sabaa7 el-ward (morning of roses).")
        FWWord("good day / have a nice day — نهارك سعيد — nhaarak sa3iid (to m) / nhaarik sa3iid (to f)")
        FWWord("    reply: نهارك سعيد ومبارك — nhaarak sa3iid w-mbaarak (to m) / nhaarik sa3iid w-mbaarak (to f)")
        FWWord("How are you? — كيفك؟ — kiifak? (m) / kiifik? (f) / kiifkom? (pl);  also كيف حالك؟ — kiif 7aalak / 7aalik?;  Jordanian/Syrian also شلونك؟ — shloonak / shloonik?")

        FWSectionHeader("How Are You? — Answers")
        FWWord("I'm well — منيح — mnii7 (m) / منيحة — mnii7a (f);  الحمدلله — el-7amdilla (thank God)")
        FWExample("mnii7, el-7amdilla, w-inta? — منيح، الحمدلله، وإنت؟ — Good, thank God, and you?")
        FWWord("I'm super / great — ممتاز — mumtaaz (m) / ممتازة — mumtaaze (f);  عالآخر — 3al-2aakhir (lit. 'to the max', both)")
        FWWord("Not so good — مش كتير منيح — mish ktiir mnii7 (m) / mish ktiir mnii7a (f);  نصّ نصّ — nuss nuss (so-so, both)")
        FWWord("Thanks for asking — تسلم عالسؤال — tislam 3as-su2aal (to m) / تسلمي عالسؤال — tislami 3as-su2aal (to f);  also الله يسلّمك — allah ysallmak (m) / ysallmik (f)")

        FWSectionHeader("Courtesy")
        FWWord("thank you — شكراً — shukran;  يسلمو — yislamu (very common, warm);  Lebanese also ميرسي — merci")
        FWExample("reply: 3afwan — عفواً — You're welcome.")
        FWWord("please — من فضلك — min fadlak (to m) / min fadlik (to f);  لو سمحت — law sama7t (to m) / law sama7ti (to f)")
        FWWord("What do you want? — شو بدّك؟ — shu biddak? (to m) / shu biddik? (to f);  Palestinian also eish biddak?")
        FWWord("I want ___ — بدّي ___ — biddi ___  (same for a man and a woman)")
        FWExample("biddi 2ahwe, min fadlak — بدّي قهوة، من فضلك — I want a coffee, please.")
        FWExample("biddi aruu7 3al-beit — بدّي أروح عالبيت — I want to go home.")

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun FWSectionHeader(text: String) {
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
private fun FWWord(text: String) {
    Text(
        text = fixArabizi(text),
        style = MaterialTheme.typography.bodyMedium,
        fontWeight = FontWeight.SemiBold,
        modifier = Modifier.padding(top = 6.dp, bottom = 2.dp)
    )
}

@Composable
private fun FWInfo(text: String) {
    Text(
        text = fixArabizi("    $text"),
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(vertical = 1.dp)
    )
}

@Composable
private fun FWExample(text: String) {
    Text(
        text = fixArabizi("    e.g. $text"),
        style = MaterialTheme.typography.bodyMedium,
        fontStyle = FontStyle.Italic,
        modifier = Modifier.padding(vertical = 1.dp)
    )
}

@Composable
private fun FWSmall(text: String) {
    Text(
        text = fixArabizi("    $text"),
        style = MaterialTheme.typography.bodySmall,
        modifier = Modifier.padding(vertical = 2.dp)
    )
}

@Composable
private fun FWNote(text: String) {
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
