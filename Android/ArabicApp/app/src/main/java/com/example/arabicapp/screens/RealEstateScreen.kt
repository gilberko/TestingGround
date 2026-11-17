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
fun RealEstateScreen() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        RENote("Words for looking at, renting and buying a home. Palestinian first, Lebanese/Syrian where it differs. Contract and legal words are formal Arabic. Arabic speakers in Israel often use the Hebrew word for the paperwork (חוזה, טאבו, משכנתא).")

        RESectionHeader("Homes")
        REWord("apartment — شقّة — sha22a")
        REInfo("f · 2: شقّتين sha22tein · pl: شقق shu2a2")
        REWord("house / home — بيت — beit")
        REInfo("m · 2: بيتين beitein · pl: بيوت byuut")
        REWord("studio apartment — ستوديو — studio;  also: شقّة غرفة وحدة — sha22a ghurfe wa7de (a one-room apartment)")
        REWord("2 bedrooms — غرفتين نوم — ghurftein noom;  a 2-bedroom apartment: شقّة غرفتين نوم — sha22a ghurftein noom")
        REWord("3 bedrooms — تلت غرف نوم — tlat ghuraf noom;  a 3-bedroom apartment: شقّة تلت غرف نوم — sha22a tlat ghuraf noom")
        RESmall("ghurftein is the dual of ghurfe (two rooms); from 3 to 10 the plural ghuraf is used (tlat ghuraf, arba3 ghuraf).")
        REWord("real estate / properties — عقارات — 3a2aaraat  (one property: عقار — 3a2aar)")
        REInfo("m · 2: عقارين 3a2aarein · pl: عقارات 3a2aaraat")
        REExample("biddi sha22a tlat ghuraf noom bi-7ayy haadi — بدي شقّة تلت غرف نوم بحي هادي — I want a 3-bedroom apartment in a quiet neighborhood.")

        RESectionHeader("Rooms")
        REWord("room — غرفة — ghurfe  (also pronounced ghorfe;  Lebanese/Syrian also: أوضة — 2ooDa)")
        REInfo("f · 2: غرفتين ghurftein · pl: غرف ghuraf")
        REWord("rooms — غرف — ghuraf;  how many rooms? كم غرفة؟ — kam ghurfe?  (singular after kam)")
        REWord("bedroom — غرفة نوم — ghurfet noom  (Leb/Syr: أوضة نوم — 2ooDet noom)")
        REWord("living room — صالون — Saaloon;  also: غرفة قعدة — ghurfet 2a3de (sitting room)")
        REInfo("m · 2: صالونين Saaloonein · pl: صالونات Saaloonaat")
        REWord("kitchen — مطبخ — maTbakh")
        REInfo("m · 2: مطبخين maTbakhein · pl: مطابخ maTaabikh")
        REWord("bathroom — حمّام — 7ammaam")
        REInfo("m · 2: حمّامين 7ammaamein · pl: حمّامات 7ammaamaat")
        REWord("toilet (the room) — تواليت — twaaleit;  also: بيت مي — beit mayy (Palestinian, lit. \"water house\")")
        REWord("toilets — تواليتات — twaaleitaat")
        REWord("toilet (the bowl) — كرسي الحمّام — kursi el-7ammaam;  also just twaaleit")
        REWord("corridor / hallway — ممر — mamarr;  also: كوريدور — kooridoor")
        REInfo("m · 2: ممرّين mamarrein · pl: ممرّات mamarraat")
        REExample("fi 7ammaamein w twaaleit — في حمّامين وتواليت — There are two bathrooms and a toilet.")

        RESectionHeader("Floors and Outside")
        REWord("floor / story — طابق — Taabi2;  Lebanese/Syrian also: طابق — Taabe2")
        REInfo("m · 2: طابقين Taab2ein · pl: طوابق Tawaabi2")
        REWord("ground floor — الطابق الأرضي — eT-Taabi2 el-arDi")
        REWord("first floor — الطابق الأول — eT-Taabi2 el-awwal")
        REWord("second floor — الطابق التاني — eT-Taabi2 et-taani")
        RESmall("Like in Israel and Lebanon, \"first floor\" is the one above the ground floor.")
        REWord("basement — طابق تحت الأرض — Taabi2 ta7t el-arD;  cellar / storage room: مخزن — makhzan;  Lebanese also: سو سول — soo-sol (French sous-sol)")
        RESmall("Not confirmed by a dictionary source: which basement word is most common varies; makhzan is used for a storage basement.")
        REWord("roof — سطح — saT7")
        REInfo("m · 2: سطحين saT7ein · pl: سطوح sTuu7")
        REWord("balcony — برندة — baranda;  also: بلكون — balkoon")
        REInfo("f · 2: برندتين barandtein · pl: برندات barandaat")
        REWord("garden / yard — جنينة — jneine;  also: حديقة — 7adii2a (formal, also a public park)")
        REInfo("f · 2: جنينتين jneintein · pl: جناين jnaayin")
        REWord("stairs — درج — daraj")
        REInfo("m · pl: أدراج adraaj (rare)")
        REWord("elevator — مصعد — maS3ad;  Lebanese/Syrian: أسانسير — asanseer")
        REWord("view — منظر — manZar;  a sea view: منظر عالبحر — manZar 3al-ba7r")
        REInfo("m · 2: منظرين manZarein · pl: مناظر manaaZir")
        REWord("Does it have a view? — في منظر؟ — fi manZar?;  is there a view of the sea? — في منظر عالبحر؟ — fi manZar 3al-ba7r?")
        REExample("esh-sha22a bit-Taabi2 et-taalit, fi asanseer w manZar 3al-ba7r — الشقّة بالطابق التالت، في أسانسير ومنظر عالبحر — The apartment is on the third floor, there's an elevator and a sea view.")

        RESectionHeader("Inside the Apartment")
        REWord("wall — حيط — 7eiT")
        REInfo("m · 2: حيطين 7eiTein · pl: حيطان 7iiTaan")
        REWord("ceiling — سقف — sa2f")
        REInfo("m · 2: سقفين sa2fein · pl: سقوف s2uuf")
        REWord("window — شبّاك — shubbaak")
        REInfo("m · 2: شبّاكين shubbaakein · pl: شبابيك shabaabiik")
        REWord("sink — مجلى — majla (kitchen sink);  bathroom sink: مغسلة — maghsale")
        REInfo("m · 2: مجليين majlayein · pl: مجالي majaali")
        REWord("shower — دوش — duush")
        REInfo("m · 2: دوشين duushein · pl: دوشات duushaat")
        REWord("bath / bathtub — بانيو — baanyo")
        REWord("cupboard / closet — خزانة — khzaane;  Lebanese/Syrian also: خزانة — khzeene")
        REInfo("f · 2: خزانتين khzaantein · pl: خزاين khazaayin")
        REWord("closet (wardrobe) — خزانة أواعي — khzaanet awaa3i (lit. \"clothes cupboard\");  built-in closet: خزانة بالحيط — khzaane bil-7eiT")
        REWord("carpet / rug — سجّادة — sajjaade")
        REInfo("f · 2: سجّادتين sajjaadtein · pl: سجّاد sajjaad")
        REWord("curtains / drapes — برادي — baraadi  (one curtain: برداية — burdaaye);  also: ستارة — sitaara (pl: ستاير sataayir)")
        RESmall("baraadi (from French bordure / Turkish perde) is the everyday Levantine word; sitaara is more formal and also means a theater curtain.")
        REExample("esh-shubbaak kbiir, laazim nishtri baraadi — الشبّاك كبير، لازم نشتري برادي — The window is big, we need to buy curtains.")

        RESectionHeader("Furniture")
        REWord("furniture — عفش — 3afsh;  also: أثاث — asaas (formal)")
        REWord("bed — تخت — takht;  formal: سرير — sariir")
        REInfo("m · 2: تختين takhtein · pl: تخوت tkhuut")
        REWord("chair — كرسي — kursi")
        REInfo("m · 2: كرسيين kursiyyein · pl: كراسي karaasi")
        REWord("table — طاولة — Taawle")
        REInfo("f · 2: طاولتين Taawiltein · pl: طاولات Taawlaat")
        REWord("sofa / couch — كنباية — kanabaaye;  also: كنب — kanabe")
        REInfo("f · pl: كنبايات kanabaayaat")
        REWord("furnished — مفروشة — mafruushe (apartment, f);  unfurnished: مش مفروشة — mish mafruushe")
        REExample("esh-sha22a mafruushe: fi takht, kanabaaye w Taawle — الشقّة مفروشة: في تخت، كنباية وطاولة — The apartment is furnished: there's a bed, a sofa and a table.")

        RESectionHeader("Building and Renovation")
        REWord("to paint — دهن — dahan / yidhan;  paint: دهان — dhaan")
        REWord("concrete — باطون — baaToon  (Lebanese: بيتون — beton, from French béton);  formal: خرسانة — kharasaane")
        REWord("drywall / plasterboard — جبس — jibs;  drywall board: جبس بورد — jibs bord")
        RESmall("Not confirmed by a dictionary source: in construction slang \"gypsum board\" is often just called jibs (= gypsum / plaster).")
        REWord("renovation — ترميم — tarmiim;  to renovate: رمّم — rammam / yrammim")
        REExample("biddna nidhan el-7iiTaan abyaD — بدنا ندهن الحيطان أبيض — We want to paint the walls white.")

        RESectionHeader("People")
        REWord("neighbor — جار — jaar (m) / جارة — jaara (f)")
        REInfo("m · 2: جارين jaarein · pl: جيران jiiraan")
        REWord("neighbors — جيران — jiiraan")
        REWord("real estate agent — سمسار — simsaar (m) / سمسارة — simsaara (f);  formal: وكيل عقارات — wakiil 3a2aaraat")
        REInfo("m · 2: سمسارين simsaarein · pl: سماسرة samaasra")
        REWord("real estate appraiser — مثمّن عقارات — muthammin 3a2aaraat (m) / مثمّنة عقارات — muthammne 3a2aaraat (f)")
        REWord("lawyer — محامي — mu7aami (m) / محامية — mu7aamye (f)")
        REInfo("m · 2: محاميين mu7aamiyein · pl: محامين mu7aamiin")
        REWord("engineer — مهندس — muhandis (m) / مهندسة — muhandse (f)")
        REInfo("m · 2: مهندسين muhandsein · pl: مهندسين muhandsiin")
        REWord("landlord / owner — صاحب الشقّة — saa7ib esh-sha22a (m) / صاحبة الشقّة — saa7bet esh-sha22a (f)")
        REWord("tenant — مستأجر — mista2jir (m) / مستأجرة — mista2jre (f)")
        REExample("el-jiiraan ktiir mnaa7 — الجيران كتير مناح — The neighbors are very nice.")

        RESectionHeader("Buying, Renting and Selling")
        REWord("contract — عقد — 3a2d;  rental contract: عقد إيجار — 3a2d iijaar")
        REInfo("m · 2: عقدين 3a2dein · pl: عقود 3u2uud")
        REWord("to buy — اشترى — ishtara / yishtri")
        REWord("to rent (as a tenant) — استأجر — ista2jar / yista2jir;  everyday: أجّر — ajjar")
        REWord("to rent out (as the owner) — أجّر — ajjar / y2ajjir")
        RESmall("ajjar is used both ways in everyday speech; context tells you who pays whom.")
        REWord("rent (the payment) — إيجار — iijaar;  also: أجار — ajaar")
        REWord("for rent — للإيجار — lil-iijaar;  for sale — للبيع — lil-bee3")
        REWord("to sell — باع — baa3 / ybii3")
        REExample("2addeish el-iijaar bish-shahr? — قديش الإيجار بالشهر؟ — How much is the rent per month?")
        REExample("ishtareina beit w ra7 nbii3 esh-sha22a — اشترينا بيت ورح نبيع الشقّة — We bought a house and we're going to sell the apartment.")

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun RESectionHeader(text: String) {
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
private fun REWord(text: String) {
    Text(
        text = fixArabizi(text),
        style = MaterialTheme.typography.bodyMedium,
        fontWeight = FontWeight.SemiBold,
        modifier = Modifier.padding(top = 6.dp, bottom = 2.dp)
    )
}

@Composable
private fun REInfo(text: String) {
    Text(
        text = fixArabizi("    $text"),
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(vertical = 1.dp)
    )
}

@Composable
private fun REExample(text: String) {
    Text(
        text = fixArabizi("    e.g. $text"),
        style = MaterialTheme.typography.bodyMedium,
        fontStyle = FontStyle.Italic,
        modifier = Modifier.padding(vertical = 1.dp)
    )
}

@Composable
private fun RESmall(text: String) {
    Text(
        text = fixArabizi("    $text"),
        style = MaterialTheme.typography.bodySmall,
        modifier = Modifier.padding(vertical = 2.dp)
    )
}

@Composable
private fun RENote(text: String) {
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
