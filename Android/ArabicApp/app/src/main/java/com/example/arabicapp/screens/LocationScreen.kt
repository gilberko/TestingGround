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
fun LocationScreen() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        LcSectionHeader("Cardinal Directions")
        LcBodyText("north — شمال — shamal")
        LcBodyText("south — جنوب — junub")
        LcBodyText("west — غرب — gharb")
        LcBodyText("east — شرق — shar'")
        LcBodyText("northeast — شمال شرق — shamal shar'")
        LcBodyText("northwest — شمال غرب — shamal gharb")
        LcBodyText("southeast — جنوب شرق — junub shar'")
        LcBodyText("southwest — جنوب غرب — junub gharb")

        LcSectionHeader("Basic Directions of Movement")
        LcBodyText("forward / straight ahead — قدام — 'uddam  (also: دغري, doghri = straight, from Turkish doğru)")
        LcBodyText("backward / back — وراء — wara'  (also: للوراء, lil-wara' = go back)")
        LcBodyText("straight — دغري — doghri  (Levantine; a very common word for \"straight ahead\")")
        LcBodyText("left — شمال — shmal  (direction; also used for the cardinal direction north)")
        LcBodyText("  Note: in everyday navigation شمال = left AND north — context tells you which")
        LcBodyText("right — يمين — ymin")
        LcBodyText("up — فوق — fo'")
        LcBodyText("down — تحت — taht")

        LcSectionHeader("Relative Position")
        LcBodyText("behind — ورا — wara  (Levantine form of وراء)")
        LcBodyText("above / on top of — فوق — fo'  (e.g. فوق الطاولة = on top of the table)")
        LcBodyText("below / under — تحت — taht  (e.g. تحت الكرسي = under the chair)")
        LcBodyText("inside — جوّا — jawwa  (Levantine; MSA: داخل / dakhil)")
        LcBodyText("outside — برّا — barra  (Levantine; MSA: خارج / kharij)")
        LcBodyText("in front of — قدام — 'uddam  (in front of; also = forward)")
        LcBodyText("next to / beside — جنب — janb  (e.g. جنب البيت = next to the house)")
        LcBodyText("between — بين — bein  (e.g. بين البيتين = between the two houses)")
        LcBodyText("across from / opposite — قبالة — 'ibale  (e.g. قبالة المطعم = across from the restaurant)")
        LcBodyText("at the corner — على الزاوية — 'ala el-zawiyye")
        LcBodyText("at the end of — على آخر — 'ala akhir  (e.g. على آخر الشارع = at the end of the street)")

        LcSectionHeader("Near and Far")
        LcBodyText("near / close — 'rib — قريب  (m); قريبة ('ribe) f")
        LcBodyText("far — بعيد — b'id  (m); بعيدة (b'ide) f")
        LcBodyText("nearby — هون قريب — hon 'rib  (here nearby / just around here)")
        LcBodyText("it's nearby — هي قريبة — hiyye 'ribe  (f subject) / هو قريب (huwe 'rib) m subject")
        LcBodyText("it's far — هي بعيدة — hiyye b'ide  (f); هو بعيد (huwe b'id) m")
        LcBodyText("near you — 'andak — عندك  (near you / at your place — m); عندك ('andik) f")
        LcBodyText("  Note: عندك literally means \"at your place\" but is used for \"near you\" in context")
        LcBodyText("far from here — بعيد من هون — b'id min hon")
        LcBodyText("not far — مش بعيد — mish b'id")

        LcSectionHeader("Navigation Instructions")
        LcBodyText("turn left — لفّ عشمال — liff 'shmal  (liff = turn; shmal = left)")
        LcBodyText("turn right — لفّ عيمين — liff 'ymin  (ymin = right)")
        LcBodyText("go straight — روح دغري — ruh doghri  (ruh = go; doghri = straight)")
        LcBodyText("on the left — على الشمال — 'ala el-shmal")
        LcBodyText("on the right — على اليمين — 'ala el-ymin")
        LcBodyText("at the traffic light — عند الإشارة — 'and el-ishara")
        LcBodyText("at the roundabout — عند الدوار — 'and el-dawar")
        LcBodyText("after the — بعد — ba'd  (e.g. بعد الإشارة = after the traffic light)")
        LcBodyText("before the — قبل — 'abl  (e.g. قبل الجسر = before the bridge)")
        LcBodyText("continue — كمّل — kammil  (keep going)")
        LcBodyText("stop — و'ّف — wa''if  (stop here)")

        LcSectionHeader("Getting In and Out")
        LcBodyText("get in / enter — فوت — fut  (Levantine imperative: come in / get in)")
        LcBodyText("  فوت هون (fut hon) = come in here / get in here")
        LcBodyText("get out / exit — اطلع — utla'  (Levantine imperative: go out / get out)")
        LcBodyText("  اطلع من هون (utla' min hon) = get out of here")
        LcBodyText("come — تعال — ta'al  (imperative to a man); تعالي (ta'ali) to a woman")
        LcBodyText("go — روح — ruh  (imperative to a man); روحي (ruhi) to a woman")
        LcBodyText("here — هون — hon  (Levantine; MSA: هنا / huna)")
        LcBodyText("there — هناك — hunak  (also: هونيك / honik in Levantine)")
        LcBodyText("this way — من هون — min hon  (from here / this way)")
        LcBodyText("that way — من هناك — min hunak  (that way / from there)")
        LcBodyText("where? — وين — wein?  (Levantine; MSA: أين / ain)")
        LcBodyText("where is...? — وين...؟ — wein...?  (e.g. وين المطعم؟ = where is the restaurant?)")
        LcBodyText("I am lost — أنا تايه — ana tayeh  (m); أنا تايهة (ana tayhe) f")

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun LcSectionHeader(text: String) {
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
private fun LcBodyText(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(vertical = 2.dp)
    )
}
