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
fun BodyScreen() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        BDNote("Paired body parts usually take the dual -ein: 3ein → 3einein (two eyes), iid → iideen (two hands). 'My …' is a suffix: raas → raasi (my head), iid → iidi (my hand), 3ein → 3eini (my eye). Many words share a root with Hebrew; those are marked. Palestinian first; Lebanese/Syrian variants where they differ.")

        BDSectionHeader("Head and Face")
        BDWord("body — جسم — jism")
        BDInfo("m · 2: جسمين jismein · pl: أجسام ajsaam")
        BDWord("head — راس — raas")
        BDInfo("m · 2: راسين raasein · pl: روس ruus")
        BDSmall("Hebrew parallel: ראש rosh.")
        BDWord("face — وجه — wijh  (Palestinian often wijj / wejj)")
        BDInfo("m · 2: وجهين wijhein · pl: وجوه wjuuh")
        BDWord("hair — شعر — sha3r  (collective);  one hair: شعرة — sha3ra")
        BDInfo("collective · one-unit: شعرة sha3ra · 2: شعرتين sha3irtein")
        BDWord("eye — عين — 3ein;  eyes: عيون — 3yuun;  both eyes: عينين — 3einein")
        BDInfo("f · 2: عينين 3einein · pl: عيون 3yuun")
        BDSmall("Hebrew parallel: עין ayin.")
        BDWord("ear — دينة — deine  (Lebanese / northern Levantine);  Syrian also: إدن — idin;  southern Levantine (Palestinian/Jordanian) also: دان — daan;  formal: أذن — udhun")
        BDInfo("f · pl: دينين dineen (Lebanese) / آذان aadaan (formal)  — the everyday form varies by area")
        BDWord("eyebrows — حواجب — 7awaajib  (one eyebrow: حاجب — 7aajib)")
        BDInfo("m · 2: حاجبين 7aajbein · pl: حواجب 7awaajib")
        BDWord("eyelashes — رموش — rmuush  (one eyelash: رمش — rimsh)")
        BDInfo("m · pl: رموش rmuush")
        BDWord("eyelids — جفون — jfuun  (one eyelid: جفن — jifn)")
        BDInfo("m · 2: جفنين jifnein · pl: جفون jfuun")
        BDWord("nose — أنف — anf  (the main word everywhere);  also منخار — mankhaar (used for the nose or a nostril, depending on the speaker)")
        BDInfo("m · 2: أنفين anfein · pl: أنوف unuuf")
        BDWord("nostrils — مناخير — manaakhiir  (plural of mankhaar; can also mean the whole nose);  formal: فتحات الأنف — fat7aat el-anf")
        BDWord("mouth — تم — timm  (shared Levantine);  formal: فم — fam")
        BDInfo("m · 2: تمين timmein · pl: تمام tmaam  (plural rarely used)")
        BDWord("tongue — لسان — lsaan")
        BDInfo("m · 2: لسانين lsaanein · pl: ألسنة alsine")
        BDSmall("Hebrew parallel: לשון lashon.")
        BDWord("teeth — سنان — snaan  (one tooth: سن — sinn)")
        BDInfo("m · 2: سنين sinnein · pl: سنان snaan")
        BDSmall("Hebrew parallel: שן shen / שיניים shinayim.")
        BDWord("cheek — خد — khadd;  cheeks: خدود — khduud")
        BDInfo("m · 2: خدين khaddein · pl: خدود khduud")
        BDWord("mustache — شوارب — shawaarib  (plural form used for one mustache)")
        BDWord("beard — دقن — da2en")
        BDExample("3indo 3yuun zur2 — عنده عيون زرق — He has blue eyes.")

        BDSectionHeader("Neck and Torso")
        BDWord("neck — رقبة — ra2abe")
        BDInfo("f · 2: رقبتين ra2abtein · pl: رقاب r2aab")
        BDWord("shoulder — كتف — kitf;  shoulders: كتاف — ktaaf")
        BDInfo("m · 2: كتفين kitfein · pl: كتاف ktaaf")
        BDSmall("Hebrew parallel: כתף katef.")
        BDWord("chest — صدر — sidr  (formal sadr)")
        BDInfo("m · 2: صدرين sidrein · pl: صدور sduur")
        BDWord("back — ضهر — dahr")
        BDInfo("m · 2: ضهرين dahrein · pl: ضهور dhuur")
        BDWord("stomach / belly — بطن — batn;  the organ (stomach): معدة — mi3de")
        BDInfo("batn: f · 2: بطنين batnein · pl: بطون btuun")
        BDWord("skin — جلد — jild")
        BDInfo("m · pl: جلود jluud  (also leather: jakeet jild = a leather jacket)")
        BDWord("butt / buttocks (polite) — مؤخرة — mu2akhkhara  (the neutral, polite / medical word)")
        BDWord("ass (rude) — طيز — Tiiz")
        BDSmall("Tiiz is the everyday word but it is vulgar — fine among close friends, rude in polite company. In polite speech say mu2akhkhara, or just point and say min wara (from behind).")
        BDWord("groin — منطقة العانة — manTi2et el-3aane  (medical);  inguinal area: الأربية — el-urbiyye")
        BDSmall("In polite everyday speech people avoid naming it and say bein el-ijrein (between the legs) or just ta7t (down there).")
        BDExample("dahri biyooja3ni — ضهري بيوجعني — My back hurts.")

        BDSectionHeader("Arms and Hands")
        BDWord("hand / arm — إيد — iid  (everyday speech uses iid for the whole arm too);  arm (formal): دراع — draa3")
        BDInfo("iid: f · 2: إيدين iideen · pl: أيادي ayaadi;  draa3: m · pl: دراعات draa3aat")
        BDSmall("Hebrew parallel: יד yad.")
        BDWord("elbow — كوع — koo3")
        BDInfo("m · 2: كوعين koo3ein · pl: كواع kwaa3")
        BDWord("finger — إصبع — isbi3  (also usba3)")
        BDInfo("m · 2: إصبعين isba3ein · pl: أصابع asaabi3")
        BDSmall("Hebrew parallel: אצבע etzba.")
        BDWord("fingernail / toenail — ضفر — dufr")
        BDInfo("m · 2: ضفرين dufrein · pl: ضوافر dawaafir")
        BDSmall("Hebrew parallel: ציפורן tziporen (same root, צ = ض here).")
        BDWord("palm of the hand — كف — kaff;  also باطن الإيد — baaTin el-iid")
        BDInfo("m · 2: كفين kaffein · pl: كفوف kfuuf")
        BDSmall("Hebrew parallel: כף kaf (כף יד).")
        BDWord("armpit — باط — baaT;  formal: إبط — ibT")
        BDInfo("m · 2: باطين baaTein · pl: باطات baaTaat")
        BDExample("ighsil iideik 2abl el-akl — اغسل إيديك قبل الأكل — Wash your hands before eating.")

        BDSectionHeader("Legs and Feet")
        BDWord("leg / foot — إجر — ijr  (shared Levantine; covers both the leg and the foot);  also رجل — rijl")
        BDInfo("f · 2: إجرين ijrein · pl: إجرين ijrein  (plural rarely used)")
        BDSmall("Hebrew parallel: רגל regel (leg/foot) — same root as rijl.")
        BDWord("foot (when you need to be specific) — قدم — 2adam;  sole of the foot: باطن الإجر — baatin el-ijr")
        BDInfo("2adam: m · 2: قدمين 2admein · pl: أقدام a2daam")
        BDWord("thigh — فخد — fakhd  (formal فخذ fakhdh)")
        BDInfo("m · 2: فخدين fakhdein · pl: فخاد fkhaad")
        BDWord("knee — ركبة — rukbe")
        BDInfo("f · 2: ركبتين rukubtein · pl: ركب rukab")
        BDSmall("Hebrew parallel: ברך berech.")
        BDWord("toes — أصابع الإجرين — asaabi3 el-ijrein  (lit. fingers of the feet)")
        BDWord("toenails — ضوافر الإجرين — dawaafir el-ijrein")
        BDExample("rukbti btooja3ni lamma amshi — ركبتي بتوجعني لما أمشي — My knee hurts when I walk.")

        BDSectionHeader("Inside the Body")
        BDWord("heart — قلب — 2alb")
        BDInfo("m · 2: قلبين 2albein · pl: قلوب 2luub")
        BDWord("lungs — رئة — ri2a;  both lungs: رئتين — ri2atein")
        BDWord("liver — كبد — kibde  (everyday);  formal: kabid")
        BDWord("kidney — كلوة — kilwe;  kidneys: كلاوي — klaawi")
        BDInfo("f · 2: كلوتين kilwitein · pl: كلاوي klaawi")
        BDSmall("Hebrew parallel: כליה kilya / כליות klayot.")
        BDWord("brain — مخ — mukh;  also دماغ — dmaagh")
        BDInfo("m · 2: مخين mukhein · pl: مخاخ mkhaakh")
        BDWord("muscle — عضلة — 3adale")
        BDInfo("f · 2: عضلتين 3adaltein · pl: عضلات 3adalaat")
        BDWord("fat (on the body / in meat) — دهن — dihn;  also شحم — sha7m")
        BDWord("blood — دم — damm")
        BDExample("2albo 2awi — قلبه قوي — He has a strong heart.")

        Spacer(modifier = Modifier.height(24.dp))
    }
}


@Composable
private fun BDSectionHeader(text: String) {
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
private fun BDWord(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        fontWeight = FontWeight.SemiBold,
        modifier = Modifier.padding(top = 6.dp, bottom = 2.dp)
    )
}

@Composable
private fun BDInfo(text: String) {
    Text(
        text = "    $text",
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(vertical = 1.dp)
    )
}

@Composable
private fun BDExample(text: String) {
    Text(
        text = "    e.g. $text",
        style = MaterialTheme.typography.bodyMedium,
        fontStyle = FontStyle.Italic,
        modifier = Modifier.padding(vertical = 1.dp)
    )
}

@Composable
private fun BDSmall(text: String) {
    Text(
        text = "    $text",
        style = MaterialTheme.typography.bodySmall,
        modifier = Modifier.padding(vertical = 2.dp)
    )
}

@Composable
private fun BDNote(text: String) {
    Spacer(modifier = Modifier.height(8.dp))
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(12.dp)
        )
    }
    Spacer(modifier = Modifier.height(4.dp))
}
