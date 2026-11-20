package com.example.arabicapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// Markup: **x** = bold, __x__ = bold + underline (the preposition / connecting word).
// Attached prefixes (بـ / لـ / عـ) are marked on the whole Arabic word so the letters keep joining.

@Composable
fun PrepositionsExamplesScreen() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        ExBody("Each sentence shows the preposition or connecting word __underlined__ in Arabic and arabizi. Palestinian first; Lebanese where it differs. The grammar behind them: Learning → Prepositions.")

        ExSection("Where Something Is")
        ExItem(
            "The dog is on the carpet.",
            "الكلب __على__ السجّادة — el-kalb __3ala__ es-sajjaade",
            note = "In fast speech 3ala + el- often becomes 3a-: el-kalb 3as-sajjaade."
        )
        ExItem(
            "The dog is under the table.",
            "الكلب __تحت__ الطاولة — el-kalb __ta7t__ eT-Taawle"
        )
        ExItem(
            "The pen is on the table.",
            "القلم __على__ الطاولة — el-2alam __3ala__ eT-Taawle  (or 3aT-Taawle)"
        )
        ExItem(
            "The money is in the wallet.",
            "المصاري __بالجزدان__ — el-maSaari __bij__-juzdaan",
            "or: المصاري __في__ الجزدان — el-maSaari __fi__ ej-juzdaan",
            note = "b- (بـ) and fi (في) both mean \"in\"; b- is the everyday one and glues onto the word (b + el- = bil-). Wallet: جزدان — Palestinian juzdaan, Lebanese jizdeen (Wiktionary, Lingualism); formal محفظة ma7faZa."
        )
        ExItem(
            "The dog is between the table and the chair.",
            "الكلب __بين__ الطاولة والكرسي — el-kalb __bein__ eT-Taawle w-el-kursi"
        )
        ExItem(
            "The money is behind the television.",
            "المصاري __ورا__ التلفزيون — el-maSaari __wara__ et-talfizyoon"
        )
        ExItem(
            "The vase is in front of the television.",
            "المزهرية __قدّام__ التلفزيون — el-mazhariyye __2uddaam__ et-talfizyoon",
            note = "Vase: مزهرية mazhariyye (from zahra, flower); also فازة faaze. Commonly heard; not confirmed by a dictionary source."
        )
        ExItem(
            "John is near Cindy.",
            "جون __قريب من__ سيندي — John __2ariib min__ Cindy  ·  also: جون __جنب__ سيندي — John __janb__ Cindy (next to)"
        )
        ExItem(
            "John is far from Cindy.",
            "جون __بعيد عن__ سيندي — John __b3iid 3an__ Cindy",
            note = "near = 2ariib MIN, far = b3iid 3AN (also b3iid min) — learn them with their preposition, like Hebrew קרוב ל / רחוק מ."
        )

        ExSection("In and Out — Inside, Outside")
        ExItem(
            "The cat is in the house.",
            "البسّة __بالبيت__ — el-bisse __bil__-beit",
            "or: البسّة __جوّا__ البيت — el-bisse __juwwa__ el-beit  (inside the house)",
            note = "bil- (b + el-) is the everyday \"in\"; juwwa = inside — it stresses that the cat is indoors."
        )
        ExItem(
            "The cat is out of the house.",
            "البسّة __برّا__ البيت — el-bisse __barra__ el-beit",
            "also: البسّة __برّات__ البيت — el-bisse __barraat__ el-beit  (commonly heard before a noun)",
            note = "No separate \"of\": barra + the noun directly — compare Hebrew מחוץ לבית, which needs ל."
        )
        ExItem(
            "Go inside!",
            "(m) فوت __جوّا__ — fuut __juwwa__",
            "(f) فوتي __جوّا__ — fuuti __juwwa__",
            note = "faat / yfuut = go in, enter (as in \"the dog came in\" below). udkhul (ادخل) is also common."
        )
        ExItem(
            "Go outside!",
            "(m) اطلع __برّا__ — iTla3 __barra__",
            "(f) اطلعي __برّا__ — iTla3i __barra__",
            note = "Tili3 / yiTla3 = go out (also go up). Said sharply, iTla3 barra! — or just barra! — means \"Get out!\"."
        )

        ExSection("Movement — Into, Out Of, To, From")
        ExItem(
            "Put the money in the wallet.",
            "(m) حطّ المصاري __بالجزدان__ — 7uTT el-maSaari __bij__-juzdaan",
            "(f) حطّي المصاري __بالجزدان__ — 7uTTi el-maSaari __bij__-juzdaan"
        )
        ExItem(
            "Take the money out of your pocket.",
            "(m) طلّع المصاري __من__ جيبتك — Talli3 el-maSaari __min__ jeibtak",
            "(f) طلّعي المصاري __من__ جيبتك — Talli3i el-maSaari __min__ jeibtik",
            note = "Talla3 = take out (lit. \"make go out\", from Tili3 = go out). Pocket: jeibe (pl jiyab); my pocket = jeibti."
        )
        ExItem(
            "The dog came in while I was cleaning the room.",
            "الكلب فات وأنا بنضّف الغرفة — el-kalb faat __w__-ana bnaDDif el-ghurfe",
            "or: الكلب فات __لمّا__ كنت عم نضّف الغرفة — el-kalb faat __lamma__ kunt 3am naDDif el-ghurfe",
            note = "w- + a pronoun (w-ana, w-huwwe…) = \"while I…\" — a very common Arabic way to say \"while\". lamma = when."
        )
        ExItem(
            "He is going to the school.",
            "رايح __عالمدرسة__ — raayi7 __3al__-madrase  ·  or: __للمدرسة__ — __lal__-madrase",
            note = "3a- (= 3ala) and la- both mean \"to\" with places; 3a- is the everyday one."
        )
        ExItem(
            "He came back from the school.",
            "رجع __من__ المدرسة — rije3 __min__ el-madrase"
        )

        ExSection("With People — With, Without, To, From, About")
        ExItem(
            "He is going with you.",
            "رايح __معك__ — raayi7 __ma3ak__ (to m)  ·  __ma3ik__ (to f)"
        )
        ExItem(
            "He is going without you.",
            "رايح __بلاك__ — raayi7 __balaak__ (to m)  ·  __balaaki__ (to f)",
            "also: رايح __بدونك__ — raayi7 __biduunak__",
            note = "bala / biduun = without; with a pronoun ending: balaak, biduunak (without you). balaak / balaaki is everyday Levantine (WordReference, thelevantongue)."
        )
        ExItem(
            "Give it to Johnny.",
            "(m) أعطيه __لجوني__ — a3Tiih __la__-Johnny  (it = m thing)",
            "(m) أعطيها __لجوني__ — a3Tiiha __la__-Johnny  (it = f thing)",
            note = "Arabic has no neutral \"it\": choose -o/-h (m) or -ha (f) by the thing's gender. Lebanese drops the h: a3Tiya la-Johnny."
        )
        ExItem(
            "Take it from Johnny.",
            "(m) خوده __من__ جوني — khoodo __min__ Johnny  ·  خودها — khoodha (it = f)"
        )
        ExItem(
            "He is talking about me.",
            "بيحكي __عنّي__ — bi7ki __3anni__",
            note = "3an + -i = 3anni (about me): 3annak (about you), 3anno (about him), 3anha (about her)."
        )
        ExItem(
            "He is talking about Johnny.",
            "بيحكي __عن__ جوني — bi7ki __3an__ Johnny"
        )
        ExItem(
            "I don't want to talk about this.",
            "ما بدّي أحكي __عن__ هالموضوع — ma biddi a7ki __3an__ hal-mawDuu3",
            "short: ما بدّي أحكي __فيه__ — ma biddi a7ki __fii__  (lit. \"in it\")"
        )
        ExItem(
            "He is alone.",
            "هو __لحاله__ — huwwe __la7aalo__  ·  she: هي لحالها — hiyye la7aalha",
            note = "Lebanese/Syrian also wa7do (alone, lit. \"his one\")."
        )

        ExSection("Time — At, Until, From … To")
        ExItem(
            "He starts school at 8:00.",
            "بيبلّش المدرسة __الساعة__ تمانية — biballish el-madrase __es-saa3a__ tamaanye",
            note = "\"at\" + a clock time = just es-saa3a + number (no extra word), or 3a-s-saa3a tamaanye. ballash = start (also Pal bibda)."
        )
        ExItem(
            "He will be at school until 13:00.",
            "رح يكون بالمدرسة __لحدّ__ الساعة وحدة — ra7 ykuun bil-madrase __la-7add__ es-saa3a wa7de",
            note = "13:00 is said as \"one o'clock\" (+ ba3d eD-Duhr if needed). until = la-7add / 7atta (+ time)."
        )
        ExItem(
            "The store is open from Sunday to Thursday.",
            "المحلّ فاتح __من__ الأحد __للخميس__ — el-ma7all faati7 __min__ el-a7ad __lal__-khamiis",
            note = "min … la- = from … to (like Hebrew מ… עד). Also min el-a7ad la-7add el-khamiis."
        )

        ExSection("Cause and Order — Because, Therefore, Before, After")
        ExItem(
            "He didn't go to school because he went to the doctor.",
            "ما راح عالمدرسة __لأنّه__ راح عالدكتور — ma raa7 3al-madrase __la2inno__ raa7 3ad-doktoor",
            "or: … __عشان__ راح عالدكتور — … __3ashaan__ raa7 3ad-doktoor"
        )
        ExItem(
            "He will go to school after he returns from the doctor.",
            "رح يروح عالمدرسة __بعد ما__ يرجع من عند الدكتور — ra7 yruu7 3al-madrase __ba3d ma__ yirja3 min 3ind ed-doktoor",
            note = "ba3d ma + verb (after he returns) · ba3d + noun (ba3d ed-doktoor = after the doctor). min 3ind = from (someone's place) — like Hebrew מאצל."
        )
        ExItem(
            "He is going to the doctor before school.",
            "رايح عالدكتور __قبل__ المدرسة — raayi7 3ad-doktoor __2abl__ el-madrase"
        )
        ExItem(
            "He is sick and therefore he is going to the doctor.",
            "هو مريض، __عشان هيك__ رايح عالدكتور — huwwe mariiD, __3ashaan heik__ raayi7 3ad-doktoor",
            "also: __لهيك__ — __la-heik__",
            note = "3ashaan heik / la-heik = \"because of this\" = therefore, so. Formal: لذلك lidhaalik."
        )
        ExItem(
            "He went to the doctor because he is sick.",
            "راح عالدكتور __لأنّه__ مريض — raa7 3ad-doktoor __la2inno__ mariiD"
        )
        ExItem(
            "He checked the car before driving.",
            "فحص السيارة __قبل ما__ ساق — fa7as es-sayyaara __2abl ma__ saa2",
            note = "2abl + noun (2abl el-madrase) · 2abl ma + verb (2abl ma saa2 = before he drove). Arabic says \"before he drove\", not \"before driving\"."
        )

        ExSection("Purpose, Means and Comparison — For, To, By, Than")
        ExItem(
            "He is saving money for a guitar.",
            "بيوفّر مصاري __لجيتار__ — biwaffir maSaari __la__-gitaar",
            "also: بيوفّر مصاري __عشان__ الجيتار — biwaffir maSaari __3ashaan__ el-gitaar",
            note = "waffar / biwaffir = save (money, time, effort) — widely understood. The colloquial Levantine word for saving up money is حوّش 7awwash: bi7awwish maSaari (lit. \"collects\"). la- and 3ashaan + a noun = \"for\"."
        )
        ExItem(
            "He is saving money to buy a guitar.",
            "بيوفّر مصاري __عشان__ يشتري جيتار — biwaffir maSaari __3ashaan__ yishtri gitaar",
            "Leb: … __تا__ يشتري — __ta__ yishtri (commonly heard)  ·  Syr: … __منشان__ يشتري — __minshaan__ yishtri",
            note = "3ashaan + a verb WITHOUT b- = \"in order to\" (yishtri, not biyishtri) — compare 3ashaan + noun above."
        )
        ExItem(
            "He is saving money by going less to restaurants.",
            "بيوفّر مصاري __إنّه__ بيروح أقلّ عالمطاعم — biwaffir maSaari __inno__ biruu7 a2all 3al-maTaa3im",
            "more formal: … __عن طريق__ إنّه بيروح أقلّ عالمطاعم — … __3an Tarii2__ inno biruu7 a2all 3al-maTaa3im",
            note = "Arabic has no \"by + -ing\". You say \"(the fact) that he goes less\" — inno + a full verb. 3an Tarii2 = lit. \"by way of\". The short inno form is commonly heard; not confirmed by a source."
        )
        ExItem(
            "He gave more money than that.",
            "أعطى مصاري __أكتر من__ هيك — a3Ta maSaari __aktar min__ heik"
        )
        ExItem(
            "He gave me less money than that.",
            "أعطاني مصاري __أقلّ من__ هيك — a3Taani maSaari __a2all min__ heik",
            note = "\"than\" = min, right after the comparative — like Hebrew יותר מ / פחות מ. heik here = \"that (amount)\". With a number: aktar min miit shekel — more than 100 shekels."
        )
        ExItem(
            "He is taller than you.",
            "هو __أطول منّك__ — huwwe __atwal minnak__ (to m)  ·  __minnik__ (to f)"
        )
        ExItem(
            "He is shorter than you.",
            "هو __أقصر منّك__ — huwwe __a2sar minnak__ (to m)  ·  __minnik__ (to f)",
            note = "The af3al form doesn't change for gender: hiyye atwal minnak — she is taller than you. More in Learning → Comparisons."
        )

        ExSection("Quick Reference")
        ExBody("**على / عـ — 3ala / 3a-** — on; to (a place)  ·  **تحت — ta7t** — under  ·  **بـ / في — b- / fi** — in")
        ExBody("**جوّا — juwwa** — inside, in  ·  **برّا — barra** — outside, out (of)")
        ExBody("**بين — bein** — between  ·  **ورا — wara** — behind  ·  **قدّام — 2uddaam** — in front of  ·  **جنب — janb** — next to")
        ExBody("**قريب من — 2ariib min** — near  ·  **بعيد عن — b3iid 3an** — far from  ·  **من — min** — from, out of")
        ExBody("**لـ — la-** — to (a person / place)  ·  **مع — ma3** — with  ·  **بلا / بدون — bala / biduun** — without  ·  **عن — 3an** — about")
        ExBody("**لحاله — la7aalo** — alone  ·  **لحدّ — la-7add** — until  ·  **من … لـ — min … la-** — from … to")
        ExBody("**لأنّه — la2inno** / **عشان — 3ashaan** — because  ·  **عشان هيك / لهيك — 3ashaan heik / la-heik** — therefore")
        ExBody("**لـ / عشان — la- / 3ashaan** — for, in order to  ·  **عن طريق — 3an Tarii2** — by (means of)  ·  **أكتر / أقلّ من — aktar / a2all min** — more / less than")
        ExBody("**قبل / قبل ما — 2abl / 2abl ma** — before  ·  **بعد / بعد ما — ba3d / ba3d ma** — after  ·  **و + pronoun — w-ana…** — while")

        Spacer(modifier = Modifier.height(24.dp))
    }
}
