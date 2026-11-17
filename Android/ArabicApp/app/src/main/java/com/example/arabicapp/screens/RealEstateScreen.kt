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
        REWord("renovation (שיפוץ) — ترميم — tarmiim;  to renovate (לשפץ): رمّم — rammam / yrammim")
        RESmall("Lebanese/Syrian also say تصليحات — taSlii7aat (repairs) for smaller renovation work. Israeli Arabic speakers often just say shiputz.")
        REWord("to fix / repair — صلّح — Salla7 / ySalli7;  it got fixed: تصلّح — tSalla7")
        REWord("repair — تصليح — taSlii7;  repairman / technician: فنّي — fanni (m) / فنّية — fanniyye (f)")
        REExample("el-ghassaale kharbaane, laazim nSalli7ha — الغسالة خربانة، لازم نصلّحها — The washing machine is broken, we need to fix it.")
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
        REWord("house committee (ועד בית) — لجنة البناية — lajnet el-binaaye;  also: لجنة البيت — lajnet el-beit")
        RESmall("The Arabic pages of Kol Zchut (Israeli rights site) use لجنة البناية for ועד בית. Many Arabic speakers in Israel just say va3ad bayit.")
        REWord("building / apartment block — بناية — binaaye")
        REInfo("f · 2: بنايتين binaaytein · pl: بنايات binaayaat")
        REWord("shared building (בית משותף) — بيت مشترك — beit mushtarak")
        REWord("building fees (דמי ועד) — رسوم البناية — rusuum el-binaaye  (not confirmed by a source; often just: مصاري اللجنة — maSaari el-lajne)")
        REExample("lajnet el-binaaye ra7 tSalli7 el-asanseer — لجنة البناية رح تصلّح الأسانسير — The house committee is going to fix the elevator.")
        REExample("el-jiiraan ktiir mnaa7 — الجيران كتير مناح — The neighbors are very nice.")

        RESectionHeader("Buying, Renting and Selling")
        REWord("contract (חוזה) — عقد — 3a2d")
        REInfo("m · 2: عقدين 3a2dein · pl: عقود 3u2uud")
        REWord("rent agreement / lease (חוזה שכירות) — عقد إيجار — 3a2d iijaar")
        REWord("purchase / sale contract (חוזה מכר) — عقد بيع — 3a2d bee3;  purchase contract: عقد شرا — 3a2d shira")
        REWord("to sign a contract — وقّع عقد — wa22a3 3a2d / ywa22i3")
        REWord("to buy — اشترى — ishtara / yishtri")
        REWord("to rent (as a tenant, לשכור) — استأجر — ista2jar / yista2jir;  everyday: أجّر — ajjar")
        REWord("to rent out (as the owner, להשכיר) — أجّر — ajjar / y2ajjir")
        RESmall("ajjar is used both ways in everyday speech; context tells you who pays whom.")
        REWord("rent (the payment) — إيجار — iijaar;  also: أجار — ajaar")
        REWord("for rent — للإيجار — lil-iijaar;  for sale — للبيع — lil-bee3")
        REWord("to sell — باع — baa3 / ybii3")
        REExample("2addeish el-iijaar bish-shahr? — قديش الإيجار بالشهر؟ — How much is the rent per month?")
        REExample("ishtareina beit w ra7 nbii3 esh-sha22a — اشترينا بيت ورح نبيع الشقّة — We bought a house and we're going to sell the apartment.")

        RESectionHeader("Money, Loans and Negotiating")
        REWord("loan — قرض — 2arD  (formal qarD)")
        REInfo("m · 2: قرضين 2arDein · pl: قروض 2uruuD")
        REWord("to take a loan — أخد قرض — akhad 2arD / yaakhod;  from the bank: من البنك — min el-bank")
        REWord("debt — دين — dein;  in debt: مديون — madyuun (m) / مديونة — madyuune (f)")
        REWord("mortgage (משכנתא) — رهن عقاري — rahn 3a2aari (formal);  housing loan: قرض سكني — 2arD sakani")
        RESmall("Arabic speakers in Israel usually say mashkanta (the Hebrew word). In Lebanon you'll hear qarD sakani (housing loan) more than rahn.")
        REWord("down payment — دفعة أولى — daf3a uula;  installments: أقساط — a2saaT (one: قسط — 2isT)")
        REWord("to negotiate — فاوض — faawaD / yfaawiD (formal);  negotiation: مفاوضات — mufaawaDaat")
        REWord("to bargain / haggle over the price — فاصل — faaSal / yfaaSil;  bargaining: مفاصلة — mfaaSale")
        RESmall("faawaD is for formal negotiations (contracts, lawyers); for haggling with a landlord or seller people say faaSal: ba3d el-mfaaSale = after the haggling.")
        REExample("faaSalna ma3 saa7ib esh-sha22a w nazzal el-iijaar mitein shekel — فاصلنا مع صاحب الشقّة ونزّل الإيجار ميتين شيكل — We haggled with the landlord and he brought the rent down by 200 shekels.")

        RESectionHeader("Cleaning and Housework")
        REWord("to clean — نضّف — naDDaf / ynaDDif;  clean: نضيف — nDiif (m) / نضيفة — nDiife (f)")
        REWord("to wash — غسل — ghasal / yighsil;  to do the dishes: جلى — jala / yijli (Leb/Syr also غسل الصحون — ghasal eS-S7uun)")
        REWord("to tidy up — رتّب — rattab / yrattib;  tidy: مرتّب — mrattab (m) / mrattabe (f)")
        RESmall("Lebanese also: ضبّ — Dabb (to put away, tidy up) — not confirmed by a dictionary source.")
        REWord("vacuum cleaner — مكنسة كهربا — maknaset kahraba  (formal: مكنسة كهربائية — maknase kahrabaa2iyye)")
        REWord("broom — مكنسة — maknase;  to sweep: كنس — kanas / yuknus")
        REWord("to mop the floor — مسح الأرض — masa7 el-arD / yimsa7")
        REWord("washing machine — غسّالة — ghassaale;  dishwasher: جلّاية — jallaaye")
        REExample("laazim nnaDDif esh-sha22a w nrattibha 2abl ma niTla3 — لازم ننضّف الشقّة ونرتّبها قبل ما نطلع — We have to clean the apartment and tidy it up before we move out.")

        RESectionHeader("Moving")
        REWord("to move (house) — نقل — na2al / yin2al;  we moved: نقلنا — na2alna")
        REWord("to move into an apartment — نقل على شقّة — na2al 3a sha22a / yin2al;  formal: انتقل — inta2al")
        REWord("to move out of an apartment — طلع من الشقّة — Tili3 min esh-sha22a / yiTla3")
        REWord("Lebanese/Syrian: عزّل — 3azzal / y3azzil (to move house);  moving: تعزيل — ta3ziil")
        RESmall("Not confirmed by a dictionary source: 3azzal for moving house is common in Lebanon and Syria; Palestinians more often say na2al. Movers: shirket na2il (moving company).")
        REExample("na2alna 3a sha22a jdiide esh-shahr el-maaDi — نقلنا على شقّة جديدة الشهر الماضي — We moved into a new apartment last month.")
        REExample("lamma Tli3t min esh-sha22a, rajja3li saa7ib esh-sha22a et-ta2miin — لمّا طلعت من الشقّة رجّعلي صاحب الشقّة التأمين — When I moved out, the landlord gave me back the deposit.")
        RESmall("security deposit: تأمين — ta2miin;  a deposit to hold a deal (earnest money): عربون — 3arbuun (same Semitic word as Hebrew עֵרָבוֹן).")

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
