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

// Markup: **x** = bold (see RichText.kt).

@Composable
fun ExistsHaveWantScreen() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        EHWBody("Three of the most useful words in spoken Arabic are not real verbs: **fi** (there is), **3ind-** (have) and **bidd-** (want). They don't conjugate like verbs — they take pronoun endings, like Hebrew יש לי / אין לי. To move them into the past or the future you put **kaan** (was) or **ra7 ykuun** (will be) in front of them.")

        // ───────────── EXISTS ─────────────
        EHWSection("1. Exists — في fi / ما في ma fi")
        EHWBody("**في — fi** = there is / there are (= Hebrew **יש**)")
        EHWBody("**ما في — ma fi** = there isn't / there aren't (= Hebrew **אין**). Palestinian also says **ما فيش — ma fiish**.")
        EHWBody("fi never changes — not for gender, not for number: fi walad, fi bint, fi wlaad.")
        EHWSub("Present")
        EHWExample("**في** حدا بالبيت؟ — **fi** 7ada bil-beit? — Is there anyone at home?")
        EHWExample("**في** مطعم منيح قريب من هون — **fi** maT3am mnii7 2ariib min hoon — There's a good restaurant near here")
        EHWExample("**ما في** مشكلة — **ma fi** mushkile — There's no problem / No problem")
        EHWExample("**ما في** كهربا اليوم — **ma fi** kahraba el-yoom — There's no electricity today")
        EHWExample("**ما فيش** وقت — **ma fiish** wa2t (Pal) — There's no time")
        EHWSub("Past — كان في kaan fi")
        EHWExample("**كان في** ناس كتير بالحفلة — **kaan fi** naas ktiir bil-7afle — There were a lot of people at the party")
        EHWExample("**ما كان في** حدا — **ma kaan fi** 7ada — There wasn't anybody")
        EHWSub("Future — رح يكون في ra7 ykuun fi")
        EHWExample("**رح يكون في** أكل كتير — **ra7 ykuun fi** akl ktiir — There will be a lot of food")
        EHWExample("بكرا **ما رح يكون في** شغل — bukra **ma ra7 ykuun fi** shughl — Tomorrow there won't be any work")
        EHWNote("Don't confuse it with the preposition في fi = in (fi l-beit = in the house). Same spelling, different word: \"fi 7ada fi l-beit?\" = Is there anyone in the house?")
        EHWNote("Lebanese/Syrian فيّ fiyy- + ending = can: fiyyi = I can, fiik = you can. That's a third, separate fi.")

        // ───────────── WANT ─────────────
        EHWSection("2. Want — بدّي biddi")
        EHWBody("**bidd-** + pronoun ending = want. Palestinian **biddi**, Lebanese **baddi**, Syrian closer to **béddi**. It comes from bi-wuddi (\"in my wish\"), so it works like 3indi, not like a verb.")
        EHWSub("All the persons (Palestinian / Lebanese)")
        EHWTable("ana (I) — بدّي — **biddi** / **baddi**")
        EHWTable("inta (you, m) — بدّك — **biddak** / **baddak**")
        EHWTable("inti (you, f) — بدّك — **biddik** / **baddek**")
        EHWTable("huwwe (he) — بدّه — **biddo** / **baddo**")
        EHWTable("hiyye (she) — بدها — **bidha** / **badda**")
        EHWTable("i7na (we) — بدنا — **bidna** / **badna**")
        EHWTable("intu (you, pl) — بدكم — **biddkum** / **baddkon**")
        EHWTable("hinne (they) — بدهم — **biddhum** / **baddon**")
        EHWNote("Masculine and feminine differ only in 'you' (biddak / biddik) and 'he / she' (biddo / bidha). The plural (we, you pl, they) is the same for men and women.")
        EHWSub("Want + noun, want + verb")
        EHWBody("+ noun: **بدّي قهوة — biddi 2ahwe** — I want coffee.")
        EHWBody("+ verb: bidd- + present verb WITHOUT the b-: **biddi aakol** (not biddi baakol) — I want to eat.")
        EHWBody("+ 'it': **biddi yyaah** (m) / **biddi yyaaha** (f) — I want it (iyyaa- carries the pronoun).")
        EHWSub("Present")
        EHWExample("**بدّي** أروح عالبيت — **biddi** aruu7 3al-beit — I want to go home")
        EHWExample("شو **بدّك** تشرب؟ — shu **biddak** tishrab? (f: **biddik** tishrabi) — What do you want to drink?")
        EHWExample("**بدها** تتعلّم عربي — **bidha** tit3allam 3arabi — She wants to learn Arabic")
        EHWExample("**بدهم** بيت أكبر — **biddhum** beit akbar — They want a bigger house")
        EHWSub("Not wanting — ما بدّي ma biddi")
        EHWExample("**ما بدّي** شي، شكراً — **ma biddi** ishi, shukran (Leb **ma baddi** shi) — I don't want anything, thanks")
        EHWExample("**ما بدّه** ييجي — **ma biddo** yiiji — He doesn't want to come")
        EHWNote("**mish biddi** is also heard (more emphatic: \"I really don't want\").")
        EHWSub("Past — كان بدّي kaan biddi (I wanted)")
        EHWExample("**كان بدّي** أتصل فيك، بس نسيت — **kaan biddi** attiSil fiik, bass nsiit — I wanted to call you, but I forgot")
        EHWExample("**ما كان بدها** تروح — **ma kaan bidha** truu7 — She didn't want to go")
        EHWNote("kaan stays kaan for every person here: kaan biddi, kaan bidha, kaan bidhum.")
        EHWSub("Future")
        EHWBody("A future of 'want' is rare, just like in English ('I will want'). People keep biddi and add a time word: **bukra biddi aruu7** — tomorrow I want to go. **ra7 ykuun biddi** is grammatical but sounds heavy; you'll hear it in phrases like \"ra7 ykuun biddak tiSbir\" (you'll need to be patient).")
        EHWNote("Careful: **bidd-** can itself mean 'going to / about to': **bidha tshatti** — it's going to rain; **biddo yiTla3 ba3d 12 da2ii2a** — it's about to leave in 12 minutes (see The Future below).")

        // ───────────── HAVE ─────────────
        EHWSection("3. Have — عندي 3indi")
        EHWBody("**3ind** means 'at' (a person's place) — just like Hebrew **אצל**. **3indi** = 'at me' = I have. Hebrew does the same with יש לי (lit. 'there is to me'); Arabic says 'at me'.")
        EHWSub("All the persons (Palestinian / Lebanese)")
        EHWTable("ana (I) — عندي — **3indi** / **3andi**")
        EHWTable("inta (you, m) — عندك — **3indak** / **3andak**")
        EHWTable("inti (you, f) — عندك — **3indik** / **3andek**")
        EHWTable("huwwe (he) — عنده — **3indo** / **3ando**")
        EHWTable("hiyye (she) — عندها — **3indha** / **3anda**")
        EHWTable("i7na (we) — عنّا — **3inna** / **3anna**")
        EHWTable("intu (you, pl) — عندكم — **3indkum** / **3andkon**")
        EHWTable("hinne (they) — عندهم — **3indhum** / **3andon**")
        EHWNote("Again only 'you' and 'he / she' show gender; the plural forms are the same for men and women.")
        EHWSub("Present")
        EHWExample("**عندي** سؤال — **3indi** su2aal — I have a question")
        EHWExample("**عندها** أخين وأخت — **3indha** akhein w ukht — She has two brothers and a sister")
        EHWExample("**عنّا** اجتماع الساعة عشرة — **3inna** ijtimaa3 es-saa3a 3ashra — We have a meeting at ten")
        EHWSub("Not having — ما عندي ma 3indi")
        EHWExample("**ما عندي** وقت — **ma 3indi** wa2t (Pal also **ma 3indiish** wa2t) — I don't have time")
        EHWExample("**ما عنده** سيارة — **ma 3indo** sayyaara — He doesn't have a car")
        EHWSub("Past — كان عندي kaan 3indi (I had)")
        EHWExample("**كان عندي** كلب لمّا كنت صغير — **kaan 3indi** kalb lamma kunt zghiir — I had a dog when I was little")
        EHWExample("**ما كان عنّا** مصاري — **ma kaan 3inna** maSaari — We didn't have money")
        EHWNote("kaan usually stays kaan even before a feminine thing (kaan 3indi sayyaara); some speakers say kaanat with a feminine noun — both are heard.")
        EHWSub("Future — رح يكون عندي ra7 ykuun 3indi (I will have)")
        EHWExample("بكرا **رح يكون عندي** وقت — bukra **ra7 ykuun 3indi** wa2t — Tomorrow I'll have time")
        EHWExample("**ما رح يكون عندهم** شغل — **ma ra7 ykuun 3indhum** shughl — They won't have work")
        EHWSub("3indi vs ma3i vs ili")
        EHWBody("**ma3i** (\"with me\") = I have it on me right now: **ma3ak maSaari?** — do you have money (on you)? / **ma ma3i kaash** — I don't have cash on me.")
        EHWBody("**ili** (la- + me) = it's mine / it belongs to me: **hada ili** — this is mine; **ilak 3indi maSaari** — I owe you money (lit. 'to you, at me, money').")

        // ───────────── fi + 3ind ─────────────
        EHWSection("4. fii 3andkon liiste? — Why Both?")
        EHWBody("You heard **في عندكم ليستة الأكل؟ — fii 3andkon liiste le-l-akel?** = Do you have the menu?")
        EHWBody("Your analysis is right: **fi** = there is (יש), **3andkon** = at you (pl) (אצלכם). Word for word: **\"is there, at your place, the food list?\"** — like Hebrew \"יש אצלכם תפריט?\".")
        EHWSub("Are all three OK?")
        EHWExample("**في عندكم** ليستة؟ — **fii 3andkon** liiste? — Do you have a menu (here, at your place)? — the typical way to ask a shop or restaurant 'do you have X?'")
        EHWExample("**عندكم** ليستة؟ — **3andkon** liiste? — Do you have a menu? — also completely correct, a bit shorter")
        EHWExample("**في** ليستة؟ — **fii** liiste? — Is there a menu? — correct, but more general: it asks whether a menu exists at all, not specifically 'do you (the staff) have one'")
        EHWNote("In practice all three get you the menu. fi 3and- is the most common shop phrasing: **fi 3indkum khubz?** — do you have bread? **fi 3indak firaata?** — do you have change?")
        EHWNote("liiste / liista (from French liste) is a Lebanese everyday word for a list or menu; Palestinians more often say menu (منيو) or qaa2ime. In Pal pronunciation: fi 3indkum menu?")

        // ───────────── FUTURE ─────────────
        EHWSection("5. The Future — Is It Just ra7?")
        EHWBody("ra7 is the most common, but there are a few options. All take the present verb WITHOUT the b-:")
        EHWTable("**رح — ra7** + verb — the general future everywhere in the Levant: **ra7 aruu7** — I'll go. Negative: **ma ra7 aruu7**.")
        EHWTable("**ح — 7a-** + verb — short form, heard a lot in Lebanon and Syria: **7aruu7** — I'll go.")
        EHWTable("**لح — la7** + verb — Lebanese/Syrian variant: **la7 aruu7**.")
        EHWTable("**bidd-** + verb — near future / 'about to': **bidha tshatti** — it's going to rain; **biddo yiTla3 halla2** — he's about to leave.")
        EHWTable("**b-** + present — plans and intentions, like English 'I'm going tomorrow': **bukra baruu7 3al-ba7r** — tomorrow I'm going to the sea.")
        EHWNote("Sources differ on which form is most common in which country (some say 7a- is the main Lebanese/Syrian form, others describe ra7 there too), so treat these as regional preferences, not rules. ra7 is understood everywhere.")
        EHWNote("ra7 comes from raayi7 (going) — exactly like English 'going to'.")

        // ───────────── CONDITIONS ─────────────
        EHWSection("6. Conditions With Have, Want, Exists")
        EHWBody("**Real conditions** (could really happen) — **iza** (إذا) + past or present, then a normal result:")
        EHWExample("**iza fi** wa2t, ra7 niiji — إذا في وقت، رح نيجي — If there's time, we'll come")
        EHWExample("**iza 3indak** wa2t, ta3a la-3indi — إذا عندك وقت، تعا لعندي — If you have time, come over to my place")
        EHWExample("**iza biddak**, mnruu7 halla2 — إذا بدّك، منروح هلّق — If you want, we'll go now")
        EHWExample("**iza ma fi** khubz, jiib ka3k — إذا ما في خبز، جيب كعك — If there's no bread, bring ka3k")
        Spacer(modifier = Modifier.height(6.dp))
        EHWBody("**Hypothetical conditions** (unlikely / imaginary) — **law** (لو) + have/want/exists, then **kaan** + verb (or kunt + past) in the result:")
        EHWExample("**law 3indi** milyoon dolaar, **kaan biddi** ashtiri Ferrari — لو عندي مليون دولار، كان بدّي اشتري فيراري — If I had a million dollars, I would want to buy a Ferrari")
        EHWExample("**law 3indi** milyoon dolaar, **kunt ishtareit** Ferrari — … كنت اشتريت فيراري — If I had a million dollars, I would buy a Ferrari")
        EHWExample("**law fi** ba7r 3indna, **kunt** sbi7t kull yoom — لو في بحر عنّا، كنت سبحت كل يوم — If there were a sea near us, I'd swim every day")
        Spacer(modifier = Modifier.height(6.dp))
        EHWBody("**Past counterfactual** (didn't happen) — **law kaan** + have/exists, **kunt** + past:")
        EHWExample("**law kaan 3indi** wa2t, **kunt** jiit — لو كان عندي وقت، كنت جيت — If I had had time, I would have come")
        EHWExample("**law ma kaan fi** 3aj2a, **kunna** wSilna 3al-wa2t — لو ما كان في عجقة، كنّا وصلنا عالوقت — If there hadn't been traffic, we'd have arrived on time")
        EHWNote("More on iza vs law: Verbs And Tenses → Conditions.")

        // ───────────── EXAMPLES ─────────────
        EHWSection("7. More Examples")
        EHWSub("Exists / Doesn't exist")
        EHWExample("**fi** ma7all 2ariib? — في محل قريب؟ — Is there a shop nearby?")
        EHWExample("**fi** 3aj2a ktiir el-yoom — في عجقة كتير اليوم — There's a lot of traffic today")
        EHWExample("**ma fi** 7ada hoon — ما في حدا هون — There's nobody here")
        EHWExample("**ma fi** ishi bil-barraad — ما في شي بالبرّاد — There's nothing in the fridge")
        EHWExample("**kaan fi** 3urs mbaari7 — كان في عرس مبارح — There was a wedding yesterday")
        EHWExample("**ra7 ykuun fi** shams bukra — رح يكون في شمس بكرا — It'll be sunny tomorrow (there'll be sun)")
        EHWSub("Have / Don't have")
        EHWExample("**3indak** wlaad? — عندك ولاد؟ — Do you have children? (to a man)  ·  **3indik** wlaad? (to a woman)")
        EHWExample("**3indi** bintein — عندي بنتين — I have two daughters")
        EHWExample("**3inna** Dyuuf el-leile — عنّا ضيوف الليلة — We have guests tonight")
        EHWExample("**ma 3indhum** internet — ما عندهم إنترنت — They don't have internet")
        EHWExample("**kaan 3indo** maT3am bi-Yaafa — كان عنده مطعم بيافا — He had a restaurant in Jaffa")
        EHWExample("**ra7 ykuun 3indkum** 3uTle? — رح يكون عندكم عطلة؟ — Will you (pl) have a holiday?")
        EHWSub("Want / Don't want")
        EHWExample("**biddi** ashuufak — بدّي أشوفك — I want to see you")
        EHWExample("**bidna** nsaafir 3a-libnaan — بدنا نسافر عَلبنان — We want to travel to Lebanon")
        EHWExample("**biddkum** ishi tishrabu? — بدكم شي تشربوا؟ — Do you (pl) want something to drink?")
        EHWExample("**ma bidha** ta7ki ma3o — ما بدها تحكي معه — She doesn't want to talk to him")
        EHWExample("**kaan biddhum** yiiju, bass maraDu — كان بدهم ييجوا، بس مرضوا — They wanted to come, but they got sick")
        EHWExample("**ma kaan biddi** a2uul ishi — ما كان بدّي أقول شي — I didn't want to say anything")

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun EHWSection(text: String) {
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
private fun EHWSub(text: String) {
    Spacer(modifier = Modifier.height(10.dp))
    Text(
        text = fixArabizi(text),
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.fillMaxWidth()
    )
    Spacer(modifier = Modifier.height(4.dp))
}

@Composable
private fun EHWBody(text: String) {
    Text(
        text = richText(text),
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(vertical = 2.dp)
    )
}

@Composable
private fun EHWTable(text: String) {
    Text(
        text = richText("  $text"),
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(vertical = 1.dp)
    )
}

@Composable
private fun EHWExample(text: String) {
    Text(
        text = richText("• $text"),
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(start = 8.dp, top = 2.dp, bottom = 2.dp)
    )
}

@Composable
private fun EHWNote(text: String) {
    Text(
        text = richText(text),
        style = MaterialTheme.typography.bodySmall,
        fontStyle = FontStyle.Italic,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        modifier = Modifier.padding(vertical = 3.dp)
    )
}
