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

// Markup: **x** = bold, __x__ = bold + underline (the direction word). See RichText.kt.

@Composable
fun DirectionsExamplesScreen() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        ExBody("Commands are given to a man (m), a woman (f) and a group (pl). The important direction word is __underlined__. Palestinian first; Lebanese/Syrian where it differs.")

        ExSection("Driving")
        ExItem(
            "Drive carefully.",
            "(m) سوق على مهلك ودير بالك — suu2 __3ala mahlak__ w-__diir baalak__",
            "(f) سوقي على مهلك وديري بالك — suu2i __3ala mahlik__ w-__diiri baalik__",
            "(pl) سوقوا على مهلكم — suu2u __3ala mahlkon__",
            note = "Lit. \"drive at your ease and watch out\". diir baalak = take care / watch out (also on its own: diir baalak! — careful!)."
        )
        ExItem(
            "Drive faster.",
            "(m) سوق أسرع شوي — suu2 __asra3__ shwayy",
            "(f) سوقي أسرع شوي — suu2i __asra3__ shwayy",
            note = "asra3 = faster (from sarii3, fast — like the comparisons af3al pattern). Also: yalla, asra3! — come on, faster!"
        )
        ExItem(
            "Drive slower.",
            "(m) سوق على مهلك / خفّف — suu2 __3ala mahlak__  ·  __khaffif__ (es-sur3a)",
            "(f) سوقي على مهلك / خفّفي — suu2i __3ala mahlik__  ·  __khaffifi__",
            note = "khaffif = reduce, lighten → slow down (khaffif es-sur3a = reduce the speed). Commonly heard; not confirmed by a dictionary source. Also: shwayy shwayy! — slowly!"
        )
        ExItem(
            "Stop.",
            "(m) وقّف — __wa22if__  ·  stop here: وقّف هون — __wa22if__ hoon",
            "(f) وقّفي — __wa22fi__  ·  (pl) وقّفوا — __wa22fu__",
            note = "wa22if = stop (the car), what you tell a taxi driver. A person standing still: o2af (f o2afi) — stand / stop where you are."
        )
        ExItem(
            "Go back.",
            "(m) ارجع (لورا) — __irja3__ (la-wara)",
            "(f) ارجعي — __irja3i__  ·  (pl) ارجعوا — __irja3u__",
            note = "la-wara = backwards; irja3 la-wara also means \"reverse / back up\" in a car."
        )
        ExItem(
            "Continue straight.",
            "(m) كمّل دغري — __kammil dughri__  (Leb __kammel deghri__)",
            "(f) كمّلي دغري — __kammli dughri__  ·  (pl) كمّلوا دغري — __kammlu dughri__"
        )
        ExItem(
            "Continue driving for 10 kilometers.",
            "كمّل دغري عشر كيلومترات — __kammil__ dughri __3ashar kiilomitraat__",
            "everyday: كمّل عشرة كيلو — __kammil 3ashra kiilo__",
            note = "kiilo is used for both kilometers and kilograms. With the full word, 3–10 takes the short number + plural (3ashar kiilomitraat); kiilo is a measure word, so it keeps 3ashra (see Numbers and Time)."
        )
        ExItem(
            "Continue driving for 10 minutes.",
            "كمّل سواقة عشر دقايق — __kammil__ swaa2a __3ashar da2aaye2__",
            "or: سوق كمان عشر دقايق — suu2 kamaan __3ashar da2aaye2__  (drive another 10 minutes)"
        )
        ExItem(
            "Take the second exit.",
            "(m) خود الطلعة التانية — __khood eT-Tal3a et-taanye__",
            "(f) خودي الطلعة التانية — __khoodi eT-Tal3a et-taanye__",
            "at the roundabout: عند الدوّار — 3and ed-dawwaar",
            note = "Tal3a = a way out / turn-off (from Tili3, go out). The formal word for an exit is مخرج makhraj: khood el-makhraj et-taani. Tal3a for a roundabout exit is commonly heard; not confirmed by a dictionary source."
        )
        ExItem(
            "Enter the roundabout.",
            "(m) فوت عالدوّار — __fuut 3ad-dawwaar__",
            "(f) فوتي عالدوّار — __fuuti 3ad-dawwaar__",
            note = "dawwaar = roundabout (Palestinian, Syrian, Lebanese; Lebanese also formal مستديرة mustadiira). faat / yfuut = go in, enter — the same verb as entering a building. fuut 3ad-dawwaar is commonly heard; not confirmed by a source."
        )
        ExItem(
            "At the roundabout, take the 2nd exit.",
            "(m) عند الدوّار خود الطلعة التانية — __3and ed-dawwaar__ __khood eT-Tal3a et-taanye__",
            "(f) عند الدوّار خودي الطلعة التانية — __3and ed-dawwaar__ __khoodi eT-Tal3a et-taanye__",
            "formal: … خود المخرج التاني — khood __el-makhraj et-taani__"
        )

        ExSection("Turning and Where Things Are")
        ExItem(
            "Turn left.",
            "(m) لفّ شمال — __liff shmaal__  ·  (f) لفّي شمال — __liffi shmaal__",
            "Leb/Syr: برم عالشمال — __brom 3a-sh-shmeel__"
        )
        ExItem(
            "Turn right.",
            "(m) لفّ يمين — __liff yamiin__  ·  (f) لفّي يمين — __liffi yamiin__",
            "Leb/Syr: برم عاليمين — __brom 3al-yamiin__",
            note = "shmaal (left) also means \"north\"; formal left is يسار yasaar. Taxi drivers also say خود يمين — khood yamiin (take a right)."
        )
        ExItem(
            "It's on the left.",
            "عالشمال — __3a-sh-shmaal__  ·  on your left: على إيدك الشمال — __3ala iidak esh-shmaal__",
            note = "Lit. \"on your left hand\". To a woman: 3ala iidik."
        )
        ExItem(
            "It's on the right.",
            "عاليمين — __3al-yamiin__  ·  on your right: على إيدك اليمين — __3ala iidak el-yamiin__"
        )
        ExItem(
            "Pass the bank and then turn left.",
            "اقطع البنك وبعدين لفّ شمال — __i2Ta3__ el-bank w-__ba3dein__ __liff shmaal__",
            "simpler: بعد البنك لفّ شمال — __ba3d__ el-bank __liff shmaal__  (after the bank, turn left)",
            note = "i2Ta3 = cross / go past (also: cross the street). ba3d el-bank (\"after the bank\") is the most natural way to say it. i2Ta3 for \"pass\" is commonly heard; not confirmed by a dictionary source."
        )
        ExItem(
            "Pass the post office and then turn right.",
            "بعد ما تقطع البريد، لفّ يمين — __ba3d ma__ ti2Ta3 el-bariid, __liff yamiin__",
            "simpler: بعد البريد لفّ يمين — __ba3d__ el-bariid __liff yamiin__",
            note = "post office: البريد el-bariid or مكتب البريد maktab el-bariid. ba3d + noun (after the post office) · ba3d ma + verb (after you pass)."
        )
        ExItem(
            "Continue until you see the school.",
            "(m) كمّل لحتى تشوف المدرسة — __kammil la-7atta__ tshuuf el-madrase",
            "(f) كمّلي لحتى تشوفي المدرسة — __kammli la-7atta__ tshuufi el-madrase",
            note = "la-7atta / 7atta / la-7add ma + verb = until. Also: kammil la-ma tshuuf …"
        )

        ExSection("Inside a Building")
        ExItem(
            "Enter the building.",
            "(m) فوت عالبناية — __fuut 3al-binaaye__",
            "(f) فوتي عالبناية — __fuuti 3al-binaaye__  ·  (pl) فوتوا — __fuutu__",
            note = "faat / yfuut 3a- = go into, enter (everyday Levantine; already in Common Verbs). Formal: ادخل udkhul. building = بناية binaaye."
        )
        ExItem(
            "Exit the building.",
            "(m) اطلع من البناية — __iTla3 min el-binaaye__",
            "(f) اطلعي من البناية — __iTla3i min el-binaaye__  ·  (pl) اطلعوا — __iTla3u__",
            note = "Tili3 min = go out of. The same verb iTla3 also means \"go up\" (iTla3 3a-T-Taabi2 = go up to the floor) — min (out of) vs 3a- (up to) tells you which."
        )
        ExItem(
            "Take the elevator.",
            "(m) خود المصعد — __khood el-maS3ad__  ·  Leb/Syr: خود الأسانسير — __khood el-asanseer__",
            "(f) خودي المصعد — __khoodi el-maS3ad__",
            "also: اطلع بالمصعد — __iTla3 bil-maS3ad__ (go up by elevator) · انزل بالأسانسير — __inzil bil-asanseer__ (go down by elevator)"
        )
        ExItem(
            "Take the escalators.",
            "(m) خود الدرج الكهربائي — __khood ed-daraj el-kahrabaa2i__",
            "(f) خودي الدرج الكهربائي — __khoodi ed-daraj el-kahrabaa2i__",
            "or: اطلع عالدرج الكهربائي — __iTla3 3ad-daraj el-kahrabaa2i__",
            note = "Lit. \"the electric stairs\" (daraj = stairs). درج كهربائي / درج متحرّك (moving stairs) is the dictionary term (WordReference); I didn't find a source for a more colloquial word. Arabic usually says it in the singular — \"the escalator\"."
        )
        ExItem(
            "Go up to the 3rd floor.",
            "(m) اطلع عالطابق التالت — __iTla3__ 3a-T-Taabi2 et-taalit",
            "(f) اطلعي عالطابق التالت — __iTla3i__ 3a-T-Taabi2 et-taalit"
        )
        ExItem(
            "Go down to the 2nd floor.",
            "(m) انزل عالطابق التاني — __inzil__ 3a-T-Taabi2 et-taani",
            "(f) انزلي عالطابق التاني — __inzili__ 3a-T-Taabi2 et-taani",
            note = "iTla3 / inzil = go up / go down — also get on / get off a bus (inzil hoon = get off here). Floors: Learning → Numbers and Time."
        )

        ExSection("Quick Word List")
        ExBody("**لفّ — liff** — turn  ·  **كمّل — kammil** — continue  ·  **دغري — dughri** — straight")
        ExBody("**يمين — yamiin** — right  ·  **شمال — shmaal** — left  ·  **عاليمين / عالشمال — 3al-yamiin / 3a-sh-shmaal** — on the right / left")
        ExBody("**فوت — fuut** — go in  ·  **اطلع من — iTla3 min** — go out of  ·  **خود — khood** — take  ·  **دوّار — dawwaar** — roundabout  ·  **مصعد / أسانسير — maS3ad / asanseer** — elevator")
        ExBody("**وقّف — wa22if** — stop  ·  **ارجع — irja3** — go back  ·  **اطلع — iTla3** — go up  ·  **انزل — inzil** — go down")
        ExBody("**على مهلك — 3ala mahlak** — slowly, easy  ·  **أسرع — asra3** — faster  ·  **دير بالك — diir baalak** — careful")
        ExBody("**بعد — ba3d** — after (+ noun)  ·  **بعد ما — ba3d ma** — after (+ verb)  ·  **لحتى — la-7atta** — until")
        ExNote("Sources for the core words (dughri = straight, shmaal = left, liff = turn, wa22if = stop): Levantine transport flashcards (knowt), englishtoarabic.org, cours-et-fiches.com, arabicpod101. The rest follows the forms already used in Common Verbs and the Directions dialogue.")

        Spacer(modifier = Modifier.height(24.dp))
    }
}
