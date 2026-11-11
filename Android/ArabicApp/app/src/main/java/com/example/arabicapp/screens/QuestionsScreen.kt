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
fun QuestionsScreen() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        QSectionHeader("How Questions Work in Palestinian and Levantine Arabic")
        QBodyText("Palestinian Arabic (and Levantine Arabic in general) forms questions in two main ways:")
        Spacer(modifier = Modifier.height(4.dp))
        QBodyText("1. Yes/No questions — same word order as a statement, but with a rising intonation at the end.")
        QBodyText("   • btishrab 2ahwe? — بتشرب قهوة؟ — Do you drink coffee?")
        QBodyText("   • huwwe hoon? — هو هون؟ — Is he here?")
        Spacer(modifier = Modifier.height(8.dp))
        QBodyText("2. Wh-questions — use a question word (see below). The question word usually comes at the beginning of the sentence.")
        QBodyText("   • shu biddak? — شو بدّك؟ — What do you want?")
        QBodyText("   • wein raa7? — وين راح؟ — Where did he go?")
        QNote("Answering tip: in an answer you normally just replace the question word with the information and keep the rest of the sentence — or drop everything and give only the new information, exactly like in English.\n• wein raa7? → raa7 3al-beit. / 3al-beit. — Where did he go? → He went home. / Home.")

        QSectionHeader("Yes / No Questions — Sample Answers")
        QBodyText("btishrab 2ahwe? — بتشرب قهوة؟ — Do you drink coffee?")
        QAnswer("ah, bashrab — آه، بشرب — Yes, I drink (it).")
        QAnswer("la2, ma bashrab 2ahwe — لا، ما بشرب قهوة — No, I don't drink coffee.")
        QAnswer("ah, akiid! — آه، أكيد! — Yes, sure!")
        QBodyText("huwwe hoon? — هو هون؟ — Is he here?")
        QAnswer("ah, hoon — آه، هون — Yes, (he's) here.")
        QAnswer("la2, mish hoon — لا، مش هون — No, he isn't here.")
        QNote("Answer yes/no by repeating the verb or word from the question: ah + the same verb, or la2 + the negated verb (ma…  for verbs, mish for everything else). Just ah / la2 alone is fine too, but repeating the verb sounds more natural.")

        QSectionHeader("What — شو / إيش (shu / eish)")
        QBodyText("شو — shu — What (the most common Palestinian and Levantine form)")
        QBodyText("إيش — eish — What (also common in Palestinian and Jordanian Arabic; in Lebanon and Syria shu dominates)")
        Spacer(modifier = Modifier.height(8.dp))
        QBodyText("Examples:")
        QBodyText("• shu ismak? / shu ismik? — شو اسمك؟ — What is your name? (m / f)")
        QBodyText("• shu fi? — شو في؟ — What is there? / What's going on?")
        QBodyText("• shu biddak tishrab? — شو بدّك تشرب؟ — What do you want to drink?")
        QBodyText("• eish bitsawwi? — إيش بتسوّي؟ — What are you doing?")
        QSubHeader("Sample answers")
        QAnswer("shu ismak? → ismi Ahmad — اسمي أحمد — My name is Ahmad.")
        QAnswer("shu fi? → wala shi — ولا شي — Nothing.  /  fi 3aj2a — في عجقة — There's traffic.")
        QAnswer("shu biddak tishrab? → biddi 2ahwe, min fadlak — بدّي قهوة، من فضلك — I want coffee, please.")
        QAnswer("eish bitsawwi? → bashtghil — بشتغل — I'm working.")
        QNote("Answer a 'what' question with a noun (ismi…, 2ahwe) or a verb in the present (bashtghil). wala shi (nothing) is the most common short answer.")

        QSectionHeader("When — إيمتى (emta)")
        QBodyText("إيمتى — emta — When  (same across Levantine Arabic; Lebanese/Syrian also aymta / 2eemta)")
        Spacer(modifier = Modifier.height(8.dp))
        QBodyText("Examples:")
        QBodyText("• emta btiiji? — إيمتى بتيجي؟ — When are you coming?")
        QBodyText("• emta el-akel? — إيمتى الأكل؟ — When is the food?")
        QBodyText("• emta tla3tu? — إيمتى طلعتو؟ — When did you (pl.) leave?")
        QSubHeader("Sample answers")
        QAnswer("bukra — بكرا — Tomorrow.")
        QAnswer("ba3d saa3a — بعد ساعة — In an hour (lit. after an hour).")
        QAnswer("es-saa3a tamaanye — الساعة تمانية — At eight o'clock.")
        QAnswer("tla3na mbaari7 — طلعنا مبارح — We left yesterday.")
        QAnswer("ma ba3rif lissa — ما بعرف لسّا — I don't know yet.")
        QNote("For time answers use a time word (bukra, mbaari7, hala2), ba3d + amount (ba3d saa3a = in an hour), 2abl + amount (2abl yoomein = two days ago) or es-saa3a + number for clock time.")

        QSectionHeader("Since When / Until When")
        QBodyText("من إيمتى؟ — min emta? — Since when?")
        QBodyText("لإيمتى؟ — la-emta? — Until when?  (Lebanese/Syrian also la-2eemta / la-aymta;  la-7add emta? is also heard)")
        Spacer(modifier = Modifier.height(8.dp))
        QBodyText("Examples:")
        QBodyText("• min emta saakin hoon? — من إيمتى ساكن هون؟ — Since when have you lived here?")
        QBodyText("• min emta btishtghil hoon? — من إيمتى بتشتغل هون؟ — Since when have you been working here?")
        QBodyText("• la-emta ra7 tu23od? — لإيمتى رح تقعد؟ — Until when will you stay?")
        QBodyText("• la-emta el-ma7al faate7? — لإيمتى المحل فاتح؟ — Until when is the shop open?")
        QSubHeader("Sample answers")
        QAnswer("min 2010 — من ٢٠١٠ — Since 2010.  (min + time)")
        QAnswer("min lamma tkharrajt — من لمّا تخرّجت — Since I graduated.  (min lamma + sentence)")
        QAnswer("la-bukra — لبكرا — Until tomorrow.  (la + time)")
        QAnswer("la-7add es-saa3a 3ashra — لحدّ الساعة عشرة — Until ten o'clock.")
        QAnswer("7atta yikhlas esh-shughl — حتى يخلص الشغل — Until the work is finished.  (7atta + sentence)")
        QBodyText("Related — 'how long have you been…?':")
        QBodyText("• 2addeish sar-lak hoon? — قديش صارلك هون؟ — How long have you been here? (m)  ·  sar-lik (f)")
        QAnswer("sar-li sane — صارلي سنة — I've been (here) a year.")
        QAnswer("sar-li shahrein bat3allam 3arabi — صارلي شهرين بتعلّم عربي — I've been learning Arabic for two months.")
        QNote("sar-li / sar-lak / sar-lik / sar-lo / sar-la (lit. 'it has become to me…') is the everyday Levantine way to say 'for (a period) up to now'. It's used in Palestine, Jordan, Syria and Lebanon alike.")

        QSectionHeader("Where — وين (wein)")
        QBodyText("وين — wein — Where")
        Spacer(modifier = Modifier.height(8.dp))
        QBodyText("Examples:")
        QBodyText("• wein el-beit? — وين البيت؟ — Where is the house?")
        QBodyText("• wein raa7? — وين راح؟ — Where did he go?")
        QBodyText("• wein btishtghil? — وين بتشتغل؟ — Where do you work?")
        QSubHeader("Sample answers")
        QAnswer("hoon — هون — Here.  /  hunaak — هناك — There.")
        QAnswer("el-beit janb el-jaame3 — البيت جنب الجامع — The house is next to the mosque.")
        QAnswer("bashtghil b-7eifa — بشتغل بحيفا — I work in Haifa.")
        QNote("For place answers use a place word (hoon, hunaak, barra, jawwa) or a preposition + place: fi / b- (in), janb (next to), 2ariib min (near), wara (behind), 2uddaam (in front of).")

        QSectionHeader("From Where — من وين (min wein)")
        QBodyText("من وين — min wein — From where / Where from")
        Spacer(modifier = Modifier.height(8.dp))
        QBodyText("Examples:")
        QBodyText("• min wein inta? / min wein inti? — من وين إنت؟ — Where are you from? (m / f)")
        QBodyText("• min wein jiitu? — من وين جيتو؟ — Where did you (pl.) come from?")
        QBodyText("• min wein hiyye? — من وين هي؟ — Where is she from?")
        QSubHeader("Sample answers")
        QAnswer("ana min Yaafa — أنا من يافا — I'm from Jaffa.")
        QAnswer("hiyye min Lubnaan — هي من لبنان — She's from Lebanon.")
        QAnswer("jiina min es-soo2 — جينا من السوق — We came from the market.")

        QSectionHeader("To Where — على وين / وين رايح (3ala wein / wein raaye7)")
        QBodyText("على وين — 3ala wein — To where (direction)")
        QBodyText("وين رايح — wein raaye7 / raay7a — Where are you going? (m / f)")
        Spacer(modifier = Modifier.height(8.dp))
        QBodyText("Examples:")
        QBodyText("• 3ala wein raa7? — على وين راح؟ — Where did he go (to)?")
        QBodyText("• wein raaye7? — وين رايح؟ — Where are you going?")
        QBodyText("• 3ala wein el-baas? — على وين الباص؟ — Where does the bus go?")
        QSubHeader("Sample answers")
        QAnswer("3al-beit — عالبيت — Home.")
        QAnswer("raaye7 3ash-shughl — رايح عالشغل — I'm going to work.")
        QAnswer("el-baas 3ala el-ma7atta el-markaziyye — الباص على المحطة المركزية — The bus goes to the central station.")

        QSectionHeader("Which — أيّ (ayy)")
        QBodyText("أيّ — ayy — Which  (Lebanese/Syrian also anu / ani — أنو / أني, m / f)")
        Spacer(modifier = Modifier.height(8.dp))
        QBodyText("Examples:")
        QBodyText("• ayy beit? — أيّ بيت؟ — Which house?")
        QBodyText("• ayy yoom? — أيّ يوم؟ — Which day?")
        QBodyText("• ayy wa7ad biddak? — أيّ واحد بدّك؟ — Which one do you want?")
        Spacer(modifier = Modifier.height(4.dp))
        QBodyText("أيّ does not change for gender — it is used with both masculine and feminine nouns.")
        QSubHeader("Sample answers")
        QAnswer("el-beit el-abyad — البيت الأبيض — The white house.")
        QAnswer("yoom el-khamiis — يوم الخميس — Thursday.")
        QAnswer("hada — هاد — This one (m).  /  hadi — هادي — This one (f).  /  el-kbiir — الكبير — The big one.")

        QSectionHeader("Who — مين (miin)")
        QBodyText("مين — miin — Who")
        Spacer(modifier = Modifier.height(8.dp))
        QBodyText("Examples:")
        QBodyText("• miin huwwe? — مين هو؟ — Who is he?")
        QBodyText("• miin 3amal hada? — مين عمل هاد؟ — Who did this?")
        QBodyText("• miin biddak tshuuf? — مين بدّك تشوف؟ — Who do you want to see?")
        Spacer(modifier = Modifier.height(4.dp))
        QBodyText("Note: مين (miin, who) and من (min, from) are written differently and the vowel of miin is longer. Context makes the meaning clear.")
        QSubHeader("Sample answers")
        QAnswer("huwwe akhuuy — هو أخوي — He's my brother.")
        QAnswer("ana! — أنا! — Me!  /  ma 7ada — ما حدا — Nobody.")
        QAnswer("biddi ashuuf el-mudiir — بدّي أشوف المدير — I want to see the manager.")

        QSectionHeader("How — كيف (kiif)")
        QBodyText("كيف — kiif — How  (Jordanian/Syrian/Iraqi-influenced also shloon — شلون)")
        Spacer(modifier = Modifier.height(8.dp))
        QBodyText("Examples:")
        QBodyText("• kiif 7aalak? — كيف حالك؟ — How are you? (m)")
        QBodyText("• kiif 7aalik? — كيف حالك؟ — How are you? (f)")
        QBodyText("• kiif ba3mal hada? — كيف بعمل هاد؟ — How do I do this?")
        QBodyText("• kiif wsiltu? — كيف وصلتو؟ — How did you (pl.) get here?")
        QSubHeader("Sample answers")
        QAnswer("kiif 7aalak? → mnii7, el-7amdilla — منيح، الحمدلله — Good, thank God.  (f: mnii7a)")
        QAnswer("kiif ba3mal hada? → hek — هيك — Like this.")
        QAnswer("kiif wsiltu? → wsilna bil-baas — وصلنا بالباص — We came by bus.")
        QNote("For 'how' use hek (like this), b- + means (bil-baas = by bus, bis-sayyara = by car), or an adverb (bisur3a = quickly, shwayy shwayy = slowly).")

        QSectionHeader("Why — ليش (leish)")
        QBodyText("ليش — leish — Why")
        Spacer(modifier = Modifier.height(8.dp))
        QBodyText("Examples:")
        QBodyText("• leish ma jiit? — ليش ما جيت؟ — Why didn't you come?")
        QBodyText("• leish biddak truu7? — ليش بدّك تروح؟ — Why do you want to go?")
        QBodyText("• leish? — ليش؟ — Why? (on its own)")
        QSubHeader("Sample answers")
        QAnswer("la2inno kunt mariid — لأنّه كنت مريض — Because I was sick.  (la2inno + sentence)")
        QAnswer("3ashaan esh-shughl — عشان الشغل — Because of work.  (3ashaan + noun)")
        QAnswer("biddi aruu7 3ashaan ashuuf ahli — بدّي أروح عشان أشوف أهلي — I want to go to see my family.  (3ashaan + verb = in order to)")
        QAnswer("hek! — هيك! — Just because!")
        QNote("la2inno (because) takes a full sentence. 3ashaan / mishaan (Syrian minshaan) works with a noun (because of) or a verb (in order to).")

        QSectionHeader("How Much / How Many — قديش / كم (2addeish / kam)")
        QBodyText("قديش — 2addeish — How much / How many (Palestinian and Levantine colloquial)")
        QBodyText("كم — kam — How many (more formal, also used in colloquial, usually before a noun)")
        Spacer(modifier = Modifier.height(8.dp))
        QBodyText("Examples:")
        QBodyText("• 2addeish el-7saab? — قديش الحساب؟ — How much is the bill?")
        QBodyText("• 2addeish es-saa3a? — قديش الساعة؟ — What time is it?")
        QBodyText("• kam walad 3indak? — كم ولد عندك؟ — How many children do you have?")
        QBodyText("• 2addeish 3umrak? / 3umrik? — قديش عمرك؟ — How old are you? (m / f)")
        QSubHeader("Sample answers")
        QAnswer("khamsiin shekel — خمسين شيكل — Fifty shekels.")
        QAnswer("es-saa3a tlaate w-nuss — الساعة تلاتة ونص — It's half past three.")
        QAnswer("3indi tlat wlaad — عندي تلت ولاد — I have three children.")
        QAnswer("3umri 3ishriin sane — عمري عشرين سنة — I'm twenty years old.")
        QNote("Numbers 3–10 take a plural noun (tlat wlaad), 11 and up take a singular noun (3ishriin sane). For age always use 3umri (lit. 'my age') + number + sane.")

        QSectionHeader("Answering: Key Words and Phrases")
        QBodyText("question — سؤال — su2aal  (pl. as2ile — أسئلة)")
        QBodyText("answer — جواب — jawaab  (pl. ajwibe — أجوبة);  also: ردّ — radd (reply)")
        QExample("3indi su2aal — عندي سؤال — I have a question.")
        QExample("shu el-jawaab? — شو الجواب؟ — What's the answer?")

        QSubHeader("Yes, Correct, Sure")
        QBodyText("yes — آه — ah;  أيوه — aywa;  نعم — na3am (formal / polite)")
        QBodyText("correct — صحّ — sa77;  مزبوط — mazbuut (Lebanese/Syrian also very common)")
        QExample("sa77, hada el-jawaab — صحّ، هاد الجواب — Correct, that's the answer.")
        QBodyText("sure / of course — أكيد — akiid;  طبعاً — tab3an")
        QExample("ah, akiid! — آه، أكيد! — Yes, sure!")

        QSubHeader("No + Negating the Sentence")
        QBodyText("no — لا — la2  (Lebanese/Syrian also la)")
        QExample("la2, ana mish juu3aan — لا، أنا مش جوعان — No, I'm not hungry. (m)")
        QExample("la2, ana mish juu3aane — لا، أنا مش جوعانة — No, I'm not hungry. (f)")
        QExample("la2, ma bashrab 2ahwe — لا، ما بشرب قهوة — No, I don't drink coffee.")
        QExample("la2, ma ruu7t — لا، ما رحت — No, I didn't go.")
        QNote("After la2 repeat the sentence in the negative:\n• mish (Lebanese also mush) — before adjectives, nouns, places and 'ra7' future: mish ta3baan, mish hoon, mish ra7 aruu7.\n• ma — before verbs (present and past): ma bashrab, ma ruu7t. In Palestinian speech -sh is often added to the verb: ma bashrabish, ma ruu7tish.")

        QSubHeader("Maybe, Probably")
        QBodyText("maybe — يمكن — yimkin;  ممكن — mumkin")
        QExample("yimkin aaji bukra — يمكن آجي بكرا — Maybe I'll come tomorrow.")
        QBodyText("probably — عالأغلب — 3al-aghlab;  غالباً — ghaaliban")
        QExample("3al-aghlab ah — عالأغلب آه — Probably (yes).")
        QBodyText("probably not — عالأغلب لا — 3al-aghlab la2;  ما بظنّ — ma bzunn (I don't think so)")
        QExample("3al-aghlab la2, 3indi shughl — عالأغلب لا، عندي شغل — Probably not, I have work.")

        QSubHeader("Not Knowing, Not Understanding, Not Sure")
        QBodyText("I don't know — ما بعرف — ma ba3rif;  مش عارف / مش عارفة — mish 3aarif / mish 3aarfe (m / f)")
        QBodyText("I don't understand — مش فاهم / مش فاهمة — mish faahim / mish faahme (m / f);  ما فهمت — ma fhimt (I didn't understand)")
        QBodyText("I'm not sure — مش متأكّد / مش متأكّدة — mish mit2akkid / mish mit2akkde (m / f)")
        QExample("ma ba3rif, mish mit2akkid — ما بعرف، مش متأكّد — I don't know, I'm not sure.")
        QExample("3eid, min fadlak, ma fhimt — عيد، من فضلك، ما فهمت — Repeat, please, I didn't understand.")

        QSubHeader("Not Wanting to Answer")
        QBodyText("I'd rather not answer — بفضّل ما جاوب — bfaddel ma jaawib")
        QBodyText("I don't want to tell you — ما بدّي أقولّك — ma biddi 2ullak (to m)  /  ما بدّي أقولّك — ma biddi 2ullik (to f)")
        QBodyText("I don't want to answer this — ما بدّي جاوب على هالسؤال — ma biddi jaawib 3ala has-su2aal")
        QBodyText("You should ask someone else — أحسن تسأل حدا تاني — a7san tis2al 7ada taani (to m)  /  أحسن تسألي حدا تاني — a7san tis2ali 7ada taani (to f)")
        QExample("is2al 7ada taani — اسأل حدا تاني — Ask someone else. (direct, imperative)")
        QNote("a7san (lit. 'better') + present verb = 'you'd better / you should'. It's softer than the imperative is2al (ask!).")

        QSectionHeader("Quick Reference")
        QBodyText("شو / إيش (shu / eish) — What")
        QBodyText("إيمتى (emta) — When")
        QBodyText("من إيمتى (min emta) — Since when  ·  لإيمتى (la-emta) — Until when")
        QBodyText("قديش صارلك (2addeish sar-lak) — How long have you been…")
        QBodyText("وين (wein) — Where")
        QBodyText("من وين (min wein) — From where")
        QBodyText("على وين (3ala wein) — To where")
        QBodyText("أيّ (ayy) — Which")
        QBodyText("مين (miin) — Who")
        QBodyText("كيف (kiif) — How")
        QBodyText("ليش (leish) — Why")
        QBodyText("قديش / كم (2addeish / kam) — How much / How many")
        Spacer(modifier = Modifier.height(4.dp))
        QBodyText("Answers: ah / aywa (yes) · la2 (no) · sa77 / mazbuut (correct) · akiid / tab3an (sure) · yimkin (maybe) · 3al-aghlab (probably) · 3al-aghlab la2 (probably not) · ma ba3rif (I don't know) · mish faahim/faahme (I don't understand) · mish mit2akkid/mit2akkde (I'm not sure)")

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun QSectionHeader(text: String) {
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
private fun QSubHeader(text: String) {
    Spacer(modifier = Modifier.height(10.dp))
    Text(
        text = fixArabizi(text),
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.SemiBold,
        color = MaterialTheme.colorScheme.primary
    )
    Spacer(modifier = Modifier.height(4.dp))
}

@Composable
private fun QBodyText(text: String) {
    Text(
        text = fixArabizi(text),
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(vertical = 2.dp)
    )
}

@Composable
private fun QAnswer(text: String) {
    Text(
        text = fixArabizi("    → $text"),
        style = MaterialTheme.typography.bodyMedium,
        fontStyle = FontStyle.Italic,
        modifier = Modifier.padding(vertical = 1.dp)
    )
}

@Composable
private fun QExample(text: String) {
    Text(
        text = fixArabizi("    e.g. $text"),
        style = MaterialTheme.typography.bodyMedium,
        fontStyle = FontStyle.Italic,
        modifier = Modifier.padding(vertical = 1.dp)
    )
}

@Composable
private fun QNote(text: String) {
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
