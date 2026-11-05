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
fun FoodsScreen() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        FdSectionHeader("Meat and Protein")
        FdBodyText("meat — لحمة — la7me")
        FdBodyText("chicken — دجاج — djaj")
        FdBodyText("turkey (the bird) — ديك رومي — dik rumi  (literally: Roman rooster)")
        FdBodyText("eggs — بيض — beid")
        FdBodyText("egg (one) — بيضة — beida")
        FdBodyText("cheese — جبنة — jibne")
        FdBodyText("cream cheese — جبنة كريمة — jibne krema")
        FdBodyText("butter — زبدة — zibde")
        FdBodyText("hummus — حمّص — 7ummus  (also the name of the chickpea itself)")
        FdBodyText("falafel — فلافل — falafel")
        FdBodyText("meatballs — كفتة — kafta  (in Levantine, kafta is spiced ground meat, often grilled or baked)")
        FdBodyText("minced meat — لحمة مفرومة — la7me mafrume")

        FdSectionHeader("Vegetables")
        FdBodyText("cucumber — خيار — khiyar")
        FdBodyText("tomato — بندورة — bandura  (Levantine; MSA: طماطم / tamatim)")
        FdBodyText("onion — بصل — basal")
        FdBodyText("garlic — توم — tum  (Levantine; MSA: ثوم / thum)")
        FdBodyText("salad — سلطة — salata")
        FdBodyText("french fries — بطاطا مقلية — batata ma2liyye  (batata = potato; ma2liyye = fried)")
        FdBodyText("potato — بطاطا — batata")
        FdBodyText("eggplant / aubergine — باذنجان — batinjan  (very common in Levantine cooking)")
        FdBodyText("zucchini — كوسا — kusa  (a Levantine staple — stuffed with rice and meat)")
        FdBodyText("lentils — عدس — 3adas  (used in soups and mujaddara)")
        FdBodyText("corn — ذرة — dhura  (also: ذرة حلوة dhura 7ilwe = sweet corn)")
        FdBodyText("fried onions — بصل محمر — basal mu7ammar  (caramelized / browned onions)")

        FdSectionHeader("Fruit and Sweets")
        FdBodyText("strawberries — فراولة — farawle")
        FdBodyText("cherries — كرز — karaz")
        FdBodyText("ice cream — بوظة — buza  (the Levantine/Syrian word; also: آيس كريم / ice cream)")
        FdBodyText("donut — دونات — donut  (borrowed word)")
        FdBodyText("pastry — معجنات — ma3junat  (general term for baked pastries)")
        FdBodyText("croissant — كرواسون — krwason  (borrowed from French)")
        FdBodyText("knafeh — كنافة — knafe  (shredded wheat or semolina pastry filled with white cheese, soaked in sugar syrup — a classic Levantine dessert, especially from Nablus)")
        FdBodyText("baklava — بقلاوة — ba2lawa  (layers of filo, nuts, and syrup — very popular throughout the region)")
        FdBodyText("ma'amoul — معمول — ma3mul  (date- or nut-filled shortbread cookies — traditional at Eid)")
        FdBodyText("halva — حلاوة — 7alawa  (sesame-based sweet, common everywhere)")

        FdSectionHeader("Herbs, Spices, and Seasonings")
        FdBodyText("sugar — سكر — sukkar")
        FdBodyText("salt — ملح — mil7")
        FdBodyText("pepper — فلفل — filfil")
        FdBodyText("black pepper — فلفل أسود — filfil aswad")
        FdBodyText("basil — ريحان — ri7an")
        FdBodyText("dill — شبت — shibbit")
        FdBodyText("parsley — بقدونس — ba2dunis")
        FdBodyText("cinnamon — قرفة — 2irfe  (qaf → glottal stop in Palestinian)")
        FdBodyText("za'atar — زعتر — za3tar  (dried thyme-oregano blend mixed with sumac and sesame — eaten with olive oil and bread, quintessentially Levantine)")
        FdBodyText("sumac — سماق — summa2  (tart red spice sprinkled on salads and meats)")
        FdBodyText("cumin — كمون — kammun")

        FdSectionHeader("Condiments and Sauces")
        FdBodyText("tahini — طحينة — ta7ine  (sesame paste — the base of hummus and many sauces)")
        FdBodyText("mayonnaise — مايونيز — mayonez")
        FdBodyText("ketchup — كاتشب — ketchup  (borrowed word)")
        FdBodyText("sauce — صوص — sos")
        FdBodyText("oil — زيت — zeit")
        FdBodyText("olive oil — زيت زيتون — zeit zeitun  (a staple of Levantine cooking)")
        FdBodyText("vinegar — خل — khall")
        FdBodyText("lemon juice — عصير ليمون — 3asir lemon")
        FdBodyText("olives — زيتون — zeitun")
        FdBodyText("pickles — مخللات — mukhallalat (pl) / مخلل — mukhalal (sg)")

        FdSectionHeader("Grains and Carbs")
        FdBodyText("bread — خبز — khubz")
        FdBodyText("pita bread — خبز عربي — khubz 3arabi  (literally: Arabic bread)")
        FdBodyText("rice — رز — ruzz")
        FdBodyText("pasta — معكرونة — ma3karune")
        FdBodyText("pizza — بيتزا — pizza  (borrowed word)")
        FdBodyText("spaghetti — سباغيتي — spaghetti  (borrowed word)")
        FdBodyText("risotto — ريزوتو — risotto  (borrowed word)")
        FdBodyText("ravioli — رافيولي — ravioli  (borrowed word)")
        FdBodyText("mujaddara — مجدرة — mujaddara  (lentils cooked with rice or bulgur and topped with caramelized onions — a classic Levantine comfort food)")
        FdBodyText("bulgur — برغل — burghul  (cracked wheat — used in tabbouleh and kibbeh)")
        FdBodyText("bun / bread roll — خبزة صغيرة — khubze sghire  (small bread roll; burger bun: خبز الهمبرغر)")

        FdSectionHeader("Drinks")
        FdBodyText("coffee — قهوة — 2ahwe  (qaf → glottal stop; Arabic coffee/espresso culture is very strong)")
        FdBodyText("tea — شاي — shai")
        FdBodyText("water — ميّ — mayy  (Levantine; MSA: ماء / ma2)")
        FdBodyText("milk — حليب — 7alib")
        FdBodyText("yoghurt — لبن — laban  (in Levantine, laban = yoghurt; not the drink labneh)")
        FdBodyText("labneh — لبنة — labne  (strained yoghurt, thicker than yoghurt — a Levantine staple eaten with olive oil and za'atar)")
        FdBodyText("juice — عصير — 3asir")
        FdBodyText("lemonade — عصير ليمون — 3asir lemon")
        Spacer(modifier = Modifier.height(8.dp))
        FdBodyText("to drink — يشرب — yishrab  (present: he drinks; I drink = بشرب bshrab)")
        FdBodyText("beverage / drink — مشروب — mashreb  (pl: مشروبات mashrubbat)")
        FdBodyText("light beverage — مشروب خفيف — mashreb khafif  (soft drink; fizzy: مشروب غازي mashreb ghazi)")

        FdSectionHeader("Kitchenware")
        FdBodyText("knife — سكين — sikkin")
        FdBodyText("fork — شوكة — shoke")
        FdBodyText("spoon — ملعقة — mal3a2a")
        FdBodyText("teaspoon — ملعقة صغيرة — mal3a2a zghire  (zghire = small)")
        FdBodyText("tablespoon — ملعقة كبيرة — mal3a2a kbire  (kbire = big)")
        FdBodyText("plate — صحن — sa7n")
        FdBodyText("glass — كاس — kas")
        FdBodyText("cup — فنجان — finjan  (traditionally for coffee; also used for small cups)")
        FdBodyText("pot — قدر — 2idr")
        FdBodyText("pan — مقلاة — ma2laya  (frying pan)")
        FdBodyText("tray — صينية — siniyye")

        FdSectionHeader("Restaurant Words")
        FdBodyText("restaurant — مطعم — mat3am")
        FdBodyText("breakfast — فطور — ftur")
        FdBodyText("lunch — غدا — ghada")
        FdBodyText("dinner — عشا — 3asha")
        FdBodyText("table — طاولة — tawle")
        FdBodyText("chair — كرسي — kursi")
        FdBodyText("menu — منيو / قائمة — menu / 2a2ime")
        FdBodyText("bill — حساب — 7isab")
        FdBodyText("waiter — نادل — nadil  (m); نادلة (nadile) f")
        FdBodyText("reservation — حجز — 7ajz")
        FdBodyText("takeaway — تيك أوي / سفري — take away / safari")
        FdBodyText("dessert — حلو — 7ilu  (lit: sweet; the colloquial word for the dessert course); also: حلويات — 7alawiyyat")

        FdSectionHeader("Cooking Methods (adjectives describing how food is prepared)")
        FdBodyText("fried — مقلي — ma2li  (shallow fried)")
        FdBodyText("deep fried — مقلي بالزيت — ma2li bil-zeit  (deep fried in oil)")
        FdBodyText("stewed — مطبوخ — matbukh  (cooked/stewed, the general past-participle of \"to cook\")")
        FdBodyText("boiled — مسلوق — maslu2")
        FdBodyText("seared — محمّر — mu7ammar  (browned/seared on high heat)")
        FdBodyText("baked — مخبوز — makhbuz")
        FdBodyText("grilled — مشوي — mishwi  (a Levantine specialty — grilled meats and vegetables)")
        FdBodyText("poached — مسلوق برفق — maslu2 b-rif2  (gently boiled; also: مصطاد mastad in some contexts)")
        FdBodyText("frozen — مجمّد — mujammad")
        FdBodyText("melted — مذاب / مسيّل — mzab / msayyil")
        FdBodyText("raw — نيّ — nayy")
        FdBodyText("minced / ground — مفروم — mafrum  (past participle of فرم = to mince; e.g. توم مفروم = minced garlic)")
        FdBodyText("well done — مطبوخ كتير — matbukh ktir  (for meat: well cooked)")
        FdBodyText("medium — وسط — wasat")
        FdBodyText("rare — أحمر — a7mar  (literally: red)")

        FdSectionHeader("Cooking Verbs")
        FdBodyText("to eat — ياكل — yakul  (imperfect/present: he eats)")
        FdBodyText("to cook — يطبخ — yutbukh")
        FdBodyText("to prepare — يحضّر — yu7addur")
        FdBodyText("to cut — يقطع — yi2ta3")
        FdBodyText("to chop — يفرم — yufrum  (mince/chop finely)")
        FdBodyText("to fry — يقلي — yi2li")
        FdBodyText("to boil — يسلق — yislu2")
        FdBodyText("to bake — يخبز — yikhbuz")
        FdBodyText("to grill — يشوي — yishwi")
        FdBodyText("to sear — يحمّر — yu7ammir")
        FdBodyText("to poach — يسلق برفق — yislu2 b-rif2")
        FdBodyText("to heat — يسخّن — yisakhkhin")
        FdBodyText("to cool — يبرّد — yibarrid")
        FdBodyText("to stir — يحرّك — yu7arrik")
        FdBodyText("to melt — يذوب — yadub  (intransitive: it melts); يذيب — yuzib (transitive: to melt something)")
        FdBodyText("to freeze — يجمّد — yujammid")
        FdBodyText("to add — يضيف — yudif")
        FdBodyText("to mix — يخلط — yikhlut")
        FdBodyText("to taste — يذوق — yadhu2")

        FdSectionHeader("Taste Descriptors")
        FdBodyText("sweet — حلو — 7ilu  (m) / حلوة (7ilwe) f")
        FdBodyText("sour — حامض — 7amid  (m) / حامضة (7amde) f")
        FdBodyText("salty — مالح — mali7  (m) / مالحة (mali7e) f")
        FdBodyText("savory — لذيذ — laziz  (delicious/savory; a general compliment for food)")
        FdBodyText("spicy — حار — 7arr  (m) / حارة (7arre) f — can mean \"hot\" (spicy OR temperature)")
        FdBodyText("hot (temperature) — ساخن — sakhin  (m) / ساخنة (sakhne) f")
        FdBodyText("cold — بارد — barid  (m) / باردة (baride) f")
        FdBodyText("tasty / delicious — لذيذ — laziz  (m) / لذيذة (lazize) f")
        FdBodyText("bland — تافه — tafeh  (m) / تافهة (tafhe) f")
        FdBodyText("bitter — مرّ — murr  (m) / مرّة (murre) f")
        FdBodyText("fresh — طازج — tazij  (m) / طازجة (tazije) f")
        Spacer(modifier = Modifier.height(8.dp))
        FdBodyText("I don't like it — ما بحبه — ma b7ibbe (m object) / ما بحبها — ma b7ibba (f object)")
        FdBodyText("it's very good — كتير لذيذ — ktir laziz  (very delicious); كتير حلو — ktir 7ilu (very tasty)")

        FdSectionHeader("Dietary and Allergens")
        FdBodyText("allergy — حساسية — 7assasiyye")
        FdBodyText("allergens — مسببات الحساسية — musabbibat el-7assasiyye")
        FdBodyText("gluten — غلوتين — glooten  (borrowed word)")
        FdBodyText("gluten free — بدون غلوتين — bidun glooten  (without gluten)")
        FdBodyText("milk — حليب — 7alib")
        FdBodyText("lactose — لاكتوز — lactose  (borrowed word)")
        FdBodyText("nuts — مكسرات — mukassarat")
        FdBodyText("vegetarian — نباتي — nabati  (m) / نباتية (nabatiyye) f")
        FdBodyText("vegan — نباتي صارم — nabati sarim  (strict vegetarian)")
        FdBodyText("halal — حلال — 7alal")
        FdBodyText("kosher — كوشير — koshir  (used in Israeli context; not a native Arabic word)")
        FdBodyText("diabetic — سكري — sukari (m) / سكرية — sukariyye (f)  (a person with diabetes)")
        FdBodyText("  suitable for diabetics: مناسب لمرضى السكري — munasib l-marda es-sukari")

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun FdSectionHeader(text: String) {
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
private fun FdBodyText(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(vertical = 2.dp)
    )
}
