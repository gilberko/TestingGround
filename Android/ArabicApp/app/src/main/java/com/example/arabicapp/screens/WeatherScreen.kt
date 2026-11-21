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
fun WeatherScreen() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        WENote("Weather words, Palestinian first, with Lebanese/Syrian forms where they differ. Many weather sentences use 3am (عم) for \"it's …-ing right now\" (3am btishti = it's raining), or fi (في, \"there is\") (fi hawa = it's windy). More nature and sky words: Simple Dictionary → City and Nature.")

        WESectionHeader("Asking About the Weather")
        WEWord("What's the weather (like)? — كيف الجو؟ — kiif ej-jaww?;  شو الطقس اليوم؟ — shu eT-Ta2s el-yoom?")
        WEWord("weather — طقس — Ta2s;  everyday: جو — jaww (lit. \"air / atmosphere\")")
        WEInfo("m · uncountable")
        WESmall("Both are used everywhere. jaww is more everyday (ej-jaww 7ilu = the weather is nice); Ta2s is the forecast / news word (nashrit eT-Ta2s = the weather report).")
        WEExample("ej-jaww 7ilu el-yoom — الجو حلو اليوم — The weather is nice today.")

        WESectionHeader("Sun and Heat")
        WEWord("sun — شمس — shams")
        WEInfo("f · pl: شموس shmuus (rare)")
        WEWord("it's sunny — مشمسة — mshamse;  في شمس — fi shams (there's sun)")
        WESmall("mshamse is feminine because it agrees with ed-dinya (the world / the weather): ed-dinya mshamse = it's sunny. Same pattern for many weather adjectives below.")
        WEWord("warm — دافي — daafi (m) / دافية — daafye (f)")
        WEWord("hot — شوب — shoob (it's hot: الدنيا شوب — ed-dinya shoob);  حامي — 7aami (m) / حامية — 7aamye (f) (hot to the touch)")
        WESmall("For weather say shoob or 7arr (حرّ, heat): ana 7arraan = I'm hot (m), 7arraane (f). 7aami is for things you touch (el-mayy 7aamye = the water is hot).")
        WEWord("sweat — عرق — 3ara2")
        WEInfo("m · uncountable")
        WEWord("to sweat — عرق — 3iri2 / yi3ra2")
        WEWord("I'm sweating — عم بعرق — 3am ba3ra2;  أنا عرقان — ana 3ar2aan (m) / عرقانة — 3ar2aane (f) (I'm all sweaty)")
        WEExample("ed-dinya shoob ktiir, 3am ba3ra2 — الدنيا شوب كتير، عم بعرق — It's very hot, I'm sweating.")

        WESectionHeader("Clouds, Rain and Fog")
        WEWord("cloud — غيمة — gheime")
        WEInfo("f · 2: غيمتين gheimtein · pl: غيوم ghyuum")
        WEWord("it's cloudy — مغيّمة — mghayyme;  الدنيا مغيّمة — ed-dinya mghayyme")
        WEWord("rain — شتا — shita  (everyday);  مطر — maTar (formal / written)")
        WEInfo("m · uncountable")
        WESmall("shita means both \"rain\" and \"winter\" (see Seasons). The context tells you which.")
        WEWord("it's raining — عم بتشتي — 3am btishti;  Lebanese/Syrian: عم تشتي — 3am tishti")
        WEWord("raindrop — نقطة شتا — nu2Tet shita;  formal: قطرة مطر — 2aTret maTar")
        WEInfo("f · 2: نقطتين nu2Ttein · pl: نقط nu2aT")
        WEWord("fog — ضباب — Dabaab")
        WEInfo("m · uncountable")
        WEWord("it's foggy — في ضباب — fi Dabaab;  الدنيا ضباب — ed-dinya Dabaab")
        WEWord("morning dew / dew — ندى — nada")
        WEInfo("m · uncountable;  also a girl's name, Nada")
        WEExample("btishti barra, khud shamsiyye — بتشتي برا، خود شمسية — It's raining outside, take an umbrella. (to m)")

        WESectionHeader("Snow, Ice and Cold")
        WEWord("snow — تلج — talj")
        WEInfo("m · uncountable")
        WEWord("it's snowing — عم بتتلّج — 3am bitallij;  Lebanese/Syrian: عم تتلّج — 3am titallij")
        WEWord("snowflake — ندفة تلج — nadfet talj")
        WEInfo("f · 2: ندفتين nadftein · pl: ندف nudaf")
        WEWord("cold — برد — bard (the cold);  it's cold: الدنيا برد — ed-dinya bard")
        WEWord("cold (adjective) — بارد — baarid (m) / باردة — baarde (f);  I'm cold: بردان — bardaan (m) / بردانة — bardaane (f)")
        WEWord("freezing — برد كتير — bard ktiir (very cold);  متجمّد — mitjammid (m) / متجمّدة — mitjammde (f) (frozen)")
        WEWord("ice — جليد — jaliid (on the road, on a lake);  ice cubes: تلج — talj")
        WEInfo("m · uncountable")
        WEWord("the road is icy — في جليد عالطريق — fi jaliid 3aT-Tarii2 (there's ice on the road)")
        WEWord("slippery — بزحلق — bizaHHli2 (it makes you slip);  formal: زلق — zalaq")
        WESmall("Not confirmed by a dictionary source: bizaHHli2 (from zaHla2, to slide) is the everyday-style form; formal Arabic says zalaq. Treat it as a guide and check with your teacher.")
        WEExample("diir baalak, eT-Tarii2 bizaHHli2 — دير بالك، الطريق بزحلق — Watch out, the road is slippery. (to m)")
        WEWord("to catch a cold — أخد برد — akhad bard / yaakhud bard;  also: انرشح — inrasha7 / yinrishi7")
        WEExample("akhadt bard — أخدت برد — I caught a cold.  ·  ana marshuu7 — أنا مرشوح — I have a cold (m;  f: marshuu7a).")

        WESectionHeader("Wind and Storms")
        WEWord("wind — هوا — hawa (everyday, also means \"air\");  formal: ريح — rii7 (pl: رياح riyaa7)")
        WEInfo("m · uncountable")
        WEWord("it's windy — في هوا — fi hawa;  في هوا قوي — fi hawa 2awi (it's very windy)")
        WEWord("storm — عاصفة — 3aaSfe")
        WEInfo("f · 2: عاصفتين 3aaSiftein · pl: عواصف 3awaaSif")
        WEWord("a flood — فيضان — fayaDaan")
        WEInfo("m · 2: فيضانين fayaDaanein · pl: فيضانات fayaDaanaat")
        WEExample("fi 3aaSfe bukra, ma tiTla3sh — في عاصفة بكرا، ما تطلعش — There's a storm tomorrow, don't go out. (to m)")

        WESectionHeader("Seasons")
        WEWord("season — فصل — faSl")
        WEInfo("m · 2: فصلين faSlein · pl: فصول fuSuul")
        WEWord("winter — شتا — shita  (also: rain)")
        WEWord("summer — صيف — Seif")
        WEWord("spring — ربيع — rabii3")
        WEWord("fall / autumn — خريف — khariif")
        WEExample("bil-Seif ed-dinya shoob w bish-shita btishti — بالصيف الدنيا شوب وبالشتا بتشتي — In summer it's hot and in winter it rains.")

        WESectionHeader("At Home")
        WEWord("air conditioner — مكيّف — mkayyef")
        WEInfo("m · 2: مكيّفين mkayyfein · pl: مكيّفات mkayyfaat")
        WEWord("fan — مروحة — marwa7a")
        WEInfo("f · 2: مروحتين marwa7tein · pl: مراوح maraawi7")
        WEWord("heating — تدفئة — tadfiye;  central heating (Lebanese/Syrian): شوفاج — shofaaj (from French chauffage)")
        WEWord("heater — صوبة — Sobba")
        WEInfo("f · 2: صوبتين Sobbtein · pl: صوبات Sobbaat")
        WEWord("window — شبّاك — shubbaak")
        WEInfo("m · 2: شبّاكين shubbaakein · pl: شبابيك shabaabiik")
        WEExample("shaghghil el-mkayyef w sakkir esh-shubbaak — شغّل المكيّف وسكّر الشبّاك — Turn on the AC and close the window. (to m)")

        WESectionHeader("What to Wear and Bring")
        WEWord("umbrella — شمسية — shamsiyye  (same word for a sun umbrella)")
        WEInfo("f · 2: شمسيتين shamsiytein · pl: شماسي shamaasi")
        WEWord("sunglasses — نظارات شمس — naDDaaraat shams")
        WEWord("scarf — لفحة — laf7a;  also: شال — shaal;  Lebanese also: إشارب — esharp")
        WEInfo("f · 2: لفحتين laf7tein · pl: لفحات laf7aat")
        WEWord("gloves — كفوف — kfuuf  (one glove: كف — kaff)")
        WEWord("jacket — جاكيت — jaakeit;  also: جاكيتة — jaakeite")
        WEInfo("m · 2: جاكيتين jaakeitein · pl: جاكيتات jaakeitaat")
        WEWord("boots — بوط — booT;  جزمة — jazme (rubber boots / high boots)")
        WEInfo("m · pl: بواط bwaaT")
        WEWord("sunscreen — كريم شمس — kreem shams;  formal: واقي شمس — waa2i shams")
        WEWord("to put on / wear / use sunscreen — حط كريم شمس — 7aTT kreem shams / y7uTT kreem shams")
        WESmall("Arabic just says \"put\" (7aTT) for applying sunscreen: 7uTT kreem shams! (to m) / 7uTTi kreem shams! (to f).")
        WEExample("ed-dinya shams, 7uTTi kreem shams w-ilbsi naDDaaraat — الدنيا شمس، حطي كريم شمس والبسي نظارات — It's sunny, put on sunscreen and wear sunglasses. (to f)")

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun WESectionHeader(text: String) {
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
private fun WEWord(text: String) {
    Text(
        text = richText(boldHeadwordLine(text)),
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(top = 6.dp, bottom = 2.dp)
    )
}

@Composable
private fun WEInfo(text: String) {
    Text(
        text = fixArabizi("    $text"),
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(vertical = 1.dp)
    )
}

@Composable
private fun WEExample(text: String) {
    Text(
        text = fixArabizi("    e.g. $text"),
        style = MaterialTheme.typography.bodyMedium,
        fontStyle = FontStyle.Italic,
        modifier = Modifier.padding(vertical = 1.dp)
    )
}

@Composable
private fun WESmall(text: String) {
    Text(
        text = fixArabizi("    $text"),
        style = MaterialTheme.typography.bodySmall,
        modifier = Modifier.padding(vertical = 2.dp)
    )
}

@Composable
private fun WENote(text: String) {
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
