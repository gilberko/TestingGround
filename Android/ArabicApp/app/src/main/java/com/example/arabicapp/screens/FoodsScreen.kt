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
        FdBodyText("meat — لحمة — lahme")
        FdBodyText("chicken — دجاج — djaj")
        FdBodyText("turkey (the bird) — ديك رومي — dik rumi  (literally: Roman rooster)")
        FdBodyText("eggs — بيض — beid")
        FdBodyText("egg (one) — بيضة — beida")
        FdBodyText("cheese — جبنة — jibne")
        FdBodyText("cream cheese — جبنة كريمة — jibne krema")
        FdBodyText("butter — زبدة — zibde")
        FdBodyText("hummus — حمّص — hummus  (also the name of the chickpea itself)")
        FdBodyText("falafel — فلافل — falafel")
        FdBodyText("meatballs — كفتة — kafta  (in Levantine, kafta is spiced ground meat, often grilled or baked)")

        FdSectionHeader("Vegetables")
        FdBodyText("cucumber — خيار — khiyar")
        FdBodyText("tomato — بندورة — bandura  (Levantine; MSA: طماطم / tamatim)")
        FdBodyText("onion — بصل — basal")
        FdBodyText("garlic — توم — tum  (Levantine; MSA: ثوم / thum)")
        FdBodyText("salad — سلطة — salata")
        FdBodyText("french fries — بطاطا مقلية — batata ma'liyye  (batata = potato; ma'liyye = fried)")
        FdBodyText("potato — بطاطا — batata")
        FdBodyText("eggplant / aubergine — باذنجان — batinjan  (very common in Levantine cooking)")
        FdBodyText("zucchini — كوسا — kusa  (a Levantine staple — stuffed with rice and meat)")
        FdBodyText("lentils — عدس — adas  (used in soups and mujaddara)")

        FdSectionHeader("Fruit and Sweets")
        FdBodyText("strawberries — فراولة — farawle")
        FdBodyText("cherries — كرز — karaz")
        FdBodyText("ice cream — بوظة — buza  (the Levantine/Syrian word; also: آيس كريم / ice cream)")
        FdBodyText("donut — دونات — donut  (borrowed word)")
        FdBodyText("pastry — معجنات — ma'junat  (general term for baked pastries)")
        FdBodyText("croissant — كرواسون — krwason  (borrowed from French)")
        FdBodyText("knafeh — كنافة — knafe  (shredded wheat or semolina pastry filled with white cheese, soaked in sugar syrup — a classic Levantine dessert, especially from Nablus)")
        FdBodyText("baklava — بقلاوة — ba'lawa  (layers of filo, nuts, and syrup — very popular throughout the region)")
        FdBodyText("ma'amoul — معمول — ma'mul  (date- or nut-filled shortbread cookies — traditional at Eid)")
        FdBodyText("halva — حلاوة — halawa  (sesame-based sweet, common everywhere)")

        FdSectionHeader("Herbs, Spices, and Seasonings")
        FdBodyText("sugar — سكر — sukkar")
        FdBodyText("salt — ملح — milh")
        FdBodyText("pepper — فلفل — filfil")
        FdBodyText("black pepper — فلفل أسود — filfil aswad")
        FdBodyText("basil — ريحان — rihan")
        FdBodyText("dill — شبت — shibbit")
        FdBodyText("parsley — بقدونس — ba'dunis")
        FdBodyText("cinnamon — قرفة — 'irfe  (qaf → glottal stop in Palestinian)")
        FdBodyText("za'atar — زعتر — za'tar  (dried thyme-oregano blend mixed with sumac and sesame — eaten with olive oil and bread, quintessentially Levantine)")
        FdBodyText("sumac — سماق — summak  (tart red spice sprinkled on salads and meats)")
        FdBodyText("cumin — كمون — kammun")

        FdSectionHeader("Condiments and Sauces")
        FdBodyText("tahini — طحينة — tahine  (sesame paste — the base of hummus and many sauces)")
        FdBodyText("mayonnaise — مايونيز — mayonez")
        FdBodyText("ketchup — كاتشب — ketchup  (borrowed word)")
        FdBodyText("sauce — صوص — sos")
        FdBodyText("oil — زيت — zeit")
        FdBodyText("olive oil — زيت زيتون — zeit zeitun  (a staple of Levantine cooking)")
        FdBodyText("vinegar — خل — khall")
        FdBodyText("lemon juice — عصير ليمون — 'asir lemon")

        FdSectionHeader("Grains and Carbs")
        FdBodyText("bread — خبز — khubz")
        FdBodyText("pita bread — خبز عربي — khubz 'arabi  (literally: Arabic bread)")
        FdBodyText("rice — رز — ruzz")
        FdBodyText("pasta — معكرونة — ma'karune")
        FdBodyText("pizza — بيتزا — pizza  (borrowed word)")
        FdBodyText("spaghetti — سباغيتي — spaghetti  (borrowed word)")
        FdBodyText("risotto — ريزوتو — risotto  (borrowed word)")
        FdBodyText("ravioli — رافيولي — ravioli  (borrowed word)")
        FdBodyText("mujaddara — مجدرة — mujaddara  (lentils cooked with rice or bulgur and topped with caramelized onions — a classic Levantine comfort food)")
        FdBodyText("bulgur — برغل — burghul  (cracked wheat — used in tabbouleh and kibbeh)")

        FdSectionHeader("Drinks")
        FdBodyText("coffee — قهوة — 'ahwe  (qaf → glottal stop; Arabic coffee/espresso culture is very strong)")
        FdBodyText("tea — شاي — shai")
        FdBodyText("water — ميّ — mayy  (Levantine; MSA: ماء / ma')")
        FdBodyText("milk — حليب — halib")
        FdBodyText("yoghurt — لبن — laban  (in Levantine, laban = yoghurt; not the drink labneh)")
        FdBodyText("labneh — لبنة — labne  (strained yoghurt, thicker than yoghurt — a Levantine staple eaten with olive oil and za'atar)")
        FdBodyText("juice — عصير — 'asir")
        FdBodyText("lemonade — عصير ليمون — 'asir lemon")

        FdSectionHeader("Kitchenware")
        FdBodyText("knife — سكين — sikkin")
        FdBodyText("fork — شوكة — shoke")
        FdBodyText("spoon — ملعقة — mal'a'a")
        FdBodyText("teaspoon — ملعقة صغيرة — mal'a'a zghire  (zghire = small)")
        FdBodyText("tablespoon — ملعقة كبيرة — mal'a'a kbire  (kbire = big)")
        FdBodyText("plate — صحن — sahn")
        FdBodyText("glass — كاس — kas")
        FdBodyText("cup — فنجان — finjan  (traditionally for coffee; also used for small cups)")
        FdBodyText("pot — قدر — 'idr")
        FdBodyText("pan — مقلاة — ma'laya  (frying pan)")
        FdBodyText("tray — صينية — siniyye")

        FdSectionHeader("Restaurant Words")
        FdBodyText("restaurant — مطعم — mat'am")
        FdBodyText("breakfast — فطور — ftur")
        FdBodyText("lunch — غدا — ghada")
        FdBodyText("dinner — عشا — ʕasha")
        FdBodyText("table — طاولة — tawle")
        FdBodyText("chair — كرسي — kursi")
        FdBodyText("menu — منيو / قائمة — menu / 'a'ime")
        FdBodyText("bill — حساب — hisab")
        FdBodyText("waiter — نادل — nadil  (m); نادلة (nadile) f")
        FdBodyText("reservation — حجز — hajz")
        FdBodyText("takeaway — تيك أوي / سفري — take away / safari")

        FdSectionHeader("Cooking Methods (adjectives describing how food is prepared)")
        FdBodyText("fried — مقلي — ma'li  (shallow fried)")
        FdBodyText("deep fried — مقلي بالزيت — ma'li bil-zeit  (deep fried in oil)")
        FdBodyText("stewed — مطبوخ — matbukh  (cooked/stewed, the general past-participle of \"to cook\")")
        FdBodyText("boiled — مسلوق — maslu'")
        FdBodyText("seared — محمّر — muhammar  (browned/seared on high heat)")
        FdBodyText("baked — مخبوز — makhbuz")
        FdBodyText("grilled — مشوي — mishwi  (a Levantine specialty — grilled meats and vegetables)")
        FdBodyText("poached — مسلوق برفق — maslu' b-rif'  (gently boiled; also: مصطاد mastad in some contexts)")
        FdBodyText("frozen — مجمّد — mujammad")
        FdBodyText("melted — مذاب / مسيّل — mzab / msayyil")
        FdBodyText("raw — نيّ — nayy")
        FdBodyText("well done — مطبوخ كتير — matbukh ktir  (for meat: well cooked)")
        FdBodyText("medium — وسط — wasat")
        FdBodyText("rare — أحمر — ahmar  (literally: red)")

        FdSectionHeader("Cooking Verbs")
        FdBodyText("to eat — ياكل — yakul  (imperfect/present: he eats)")
        FdBodyText("to cook — يطبخ — yutbukh")
        FdBodyText("to prepare — يحضّر — yuhaddur")
        FdBodyText("to cut — يقطع — yi'ta'")
        FdBodyText("to chop — يفرم — yufrum  (mince/chop finely)")
        FdBodyText("to fry — يقلي — yi'li")
        FdBodyText("to boil — يسلق — yislu'")
        FdBodyText("to bake — يخبز — yikhbuz")
        FdBodyText("to grill — يشوي — yishwi")
        FdBodyText("to sear — يحمّر — yuhammir")
        FdBodyText("to poach — يسلق برفق — yislu' b-rif'")
        FdBodyText("to heat — يسخّن — yisakhkhin")
        FdBodyText("to cool — يبرّد — yibarrid")
        FdBodyText("to stir — يحرّك — yuharrik")
        FdBodyText("to melt — يذوب — yadub  (intransitive: it melts); يذيب — yuzib (transitive: to melt something)")
        FdBodyText("to freeze — يجمّد — yujammid")
        FdBodyText("to add — يضيف — yudif")
        FdBodyText("to mix — يخلط — yikhlut")
        FdBodyText("to taste — يذوق — yadhu'")

        FdSectionHeader("Taste Descriptors")
        FdBodyText("sweet — حلو — hilu  (m) / حلوة (hilwe) f")
        FdBodyText("sour — حامض — hamid  (m) / حامضة (hamde) f")
        FdBodyText("salty — مالح — malih  (m) / مالحة (malihe) f")
        FdBodyText("savory — لذيذ — laziz  (delicious/savory; a general compliment for food)")
        FdBodyText("spicy — حار — harr  (m) / حارة (harre) f — can mean \"hot\" (spicy OR temperature)")
        FdBodyText("hot (temperature) — ساخن — sakhin  (m) / ساخنة (sakhne) f")
        FdBodyText("cold — بارد — barid  (m) / باردة (baride) f")
        FdBodyText("tasty / delicious — لذيذ — laziz  (m) / لذيذة (lazize) f")
        FdBodyText("bland — تافه — tafeh  (m) / تافهة (tafhe) f")
        FdBodyText("bitter — مرّ — murr  (m) / مرّة (murre) f")
        FdBodyText("fresh — طازج — tazij  (m) / طازجة (tazije) f")

        FdSectionHeader("Dietary and Allergens")
        FdBodyText("allergy — حساسية — hassasiyye")
        FdBodyText("allergens — مسببات الحساسية — musabbibat el-hassasiyye")
        FdBodyText("gluten — غلوتين — glooten  (borrowed word)")
        FdBodyText("gluten free — بدون غلوتين — bidun glooten  (without gluten)")
        FdBodyText("milk — حليب — halib")
        FdBodyText("lactose — لاكتوز — lactose  (borrowed word)")
        FdBodyText("nuts — مكسرات — mukassarat")
        FdBodyText("vegetarian — نباتي — nabati  (m) / نباتية (nabatiyye) f")
        FdBodyText("vegan — نباتي صارم — nabati sarim  (strict vegetarian)")
        FdBodyText("halal — حلال — halal")

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
