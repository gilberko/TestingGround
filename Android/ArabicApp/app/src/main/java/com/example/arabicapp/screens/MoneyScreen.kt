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
fun MoneyScreen() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        MNNote("Everyday money words are colloquial (masaari, fraata), but banking and stock-market terms are formal Arabic, and people often just use the English word (or, among Palestinians in Israel, the Hebrew one). Palestinian first; Lebanese/Syrian variants where they differ.")

        MNSectionHeader("Cash")
        MNWord("money — مصاري — masaari  (shared Levantine);  Palestinian/Jordanian also: فلوس — fluus;  formal: نقود — nuquud")
        MNInfo("plural in form, uncountable in use")
        MNWord("coins — فراطة — fraata  (loose coins / small change);  formal: عملات معدنية — 3umlaat ma3daniyye")
        MNWord("currency — عملة — 3umle")
        MNInfo("f · 2: عملتين 3umiltein · pl: عملات 3umlaat")
        MNWord("bills / banknotes — ورق — wara2  (lit. paper);  a 100 bill: ورقة مية — wara2it miyye")
        MNWord("small change — فراطة — fraata")
        MNWord("change (money given back) — الباقي — el-baa2i;  keep the change: خلي الباقي — khalli el-baa2i")
        MNExample("ma3ak fraata? — معك فراطة؟ — Do you have small change?")
        MNSmall("fraata means small / loose change (coins and small bills), not \"exact change\". For the exact amount say المبلغ بالزبط — el-mablagh biz-zabT;  do you have the exact amount? — معك المبلغ بالزبط؟ — ma3ak el-mablagh biz-zabT?")
        MNWord("cheque / check — شيك — sheek")
        MNInfo("m · 2: شيكين sheekein · pl: شيكات sheekaat")
        MNWord("credit card — بطاقة ائتمان — biTaa2it i2timaan (formal);  everyday: كريديت كارد — kredit kard / فيزا — viiza")
        MNInfo("f · 2: بطاقتين biTaa2tein · pl: بطاقات biTaa2aat")
        MNSmall("viiza (Visa) is used for any credit card: bidfa3 bil-viiza = I'll pay by card.")

        MNSectionHeader("Asking the Price and Paying")
        MNWord("what — شو — shu")
        MNWord("how much — قديش — 2addeish;  Lebanese: قدّيه — 2addeh (your teacher's \"ade\")")
        MNWord("cost / its price — حقّه — 7a22o (m thing) / حقّها — 7a22ha (f thing);  Lebanese: 7a22a (your \"7a2a\")")
        MNWord("How much does it cost? — قديش حقّه؟ — 2addeish 7a22o?;  Lebanese: قدّيه حقّها؟ — 2addeh 7a22a? (your \"ade 7a2a?\")")
        MNWord("What's its price? — شو سعرها؟ — shu si3rha? (f thing);  Lebanese style: shu si3ra? (your \"shu se3ra?\")")
        MNWord("price — سعر — si3r;  its price (f): سعرها — si3rha / Lebanese si3ra")
        MNSmall("In Lebanese the h of -ha often drops: 7a22ha → 7a22a, si3rha → si3ra. So \"ade 7a2a\" is 2addeh 7a22a. More on this: Simple Dictionary → First Words → Prices.")
        MNExample("2addeh 7a22a hal-kanze? — قدّيه حقّها هالكنزة؟ — How much is this sweater? (Lebanese)")
        MNWord("I don't have change — ما معي فراطة — ma ma3i fraata")
        MNWord("I can't afford that — ما معي حقّه — ma ma3i 7a22o (lit. \"I don't have its price\");  also: ما بقدر عليه — ma ba2dar 3aleih")
        MNSmall("Not confirmed by a dictionary source: these are natural everyday ways to say it; there's no single fixed phrase.")
        MNWord("It's too expensive — غالي كتير — ghaali ktiir;  غالي زيادة — ghaali zyaade (too expensive)")
        MNWord("It's very cheap — رخيص كتير — rkhiiS ktiir")
        MNWord("to bargain / haggle — فاصل — faaSal / yfaaSil")
        MNExample("ghaali ktiir! btinazzil shwayy? — غالي كتير! بتنزّل شوي؟ — It's too expensive! Can you lower it a bit?")
        MNWord("Can I pay by card? — بقدر أدفع بالفيزا؟ — ba2dar adfa3 bil-viiza?;  Lebanese/Syrian: فيني إدفع بالكارت؟ — fiini idfa3 bil-kart?")

        MNSectionHeader("Banking")
        MNWord("bank — بنك — bank")
        MNInfo("m · 2: بنكين bankein · pl: بنوك bnuuk")
        MNWord("banker — مصرفي — masrafi;  everyday: بيشتغل بالبنك — biyishtghil bil-bank (works at the bank)")
        MNWord("accountant — محاسب — mu7aasib  (f: محاسبة — mu7aasbe)")
        MNInfo("m · 2: محاسبين mu7aasbein · pl: محاسبين mu7aasbiin")
        MNWord("tax — ضريبة — dariibe")
        MNInfo("f · 2: ضريبتين dariibtein · pl: ضرايب daraayib")
        MNWord("loan — قرض — 2ard")
        MNInfo("m · 2: قرضين 2ardein · pl: قروض 2ruud")
        MNWord("mortgage — رهن عقاري — rahn 3a2aari  (formal);  everyday: قرض سكن — 2ard sakan (housing loan)")
        MNSmall("Arabic speakers in Israel borrow many Hebrew words for work, banking and government, so you may also hear the Hebrew משכנתא mashkanta; how common it is varies.")
        MNWord("interest — فايدة — faayde;  interest rate: سعر الفايدة — si3r el-faayde")
        MNWord("account — حساب — 7saab;  bank account: حساب بنك — 7saab bank")
        MNExample("akhadt 2ard min el-bank — أخدت قرض من البنك — I took a loan from the bank.")

        MNSectionHeader("Buying and Selling")
        MNWord("to buy — اشترى — ishtara / yishtri")
        MNWord("to sell — باع — baa3 / ybii3")
        MNWord("price — سعر — si3r")
        MNInfo("m · 2: سعرين si3rein · pl: أسعار as3aar")
        MNWord("value — قيمة — 2iime")
        MNInfo("f · 2: قيمتين 2iimtein · pl: قيم 2iyam")
        MNWord("profit / gain — ربح — rib7")
        MNInfo("m · 2: ربحين rib7ein · pl: أرباح arbaa7")
        MNWord("to profit / to win — ربح — rib7 / yirba7")
        MNWord("loss — خسارة — khsaara")
        MNInfo("f · 2: خسارتين khsaartein · pl: خسارات khsaaraat")
        MNWord("to lose (money, a game) — خسر — khisir / yikhsar")
        MNExample("ishtareit bi-miyye w bi3t bi-miyteen — اشتريت بمية وبعت بميتين — I bought for 100 and sold for 200.")

        MNSectionHeader("Companies")
        MNWord("company — شركة — sharike")
        MNInfo("f · 2: شركتين shariktein · pl: شركات sharikaat")
        MNWord("public company — شركة مساهمة عامة — sharike musaahame 3aamme  (a company traded on the stock market)")
        MNWord("private company — شركة خاصة — sharike khaasse")
        MNWord("bankrupt — مفلس — mfallis;  to go bankrupt: فلّس — fallas / yfallis")
        MNExample("esh-sharike fallasat — الشركة فلّست — The company went bankrupt.")

        MNSectionHeader("Stock Market")
        MNWord("stock market — البورصة — el-boorsa;  formal: سوق الأسهم — suu2 el-ashum")
        MNWord("stock / share — سهم — sahm")
        MNInfo("m · 2: سهمين sahmein · pl: أسهم ashum")
        MNWord("investment — استثمار — istismaar  (formal istithmaar)")
        MNWord("to invest — استثمر — istasmar / yistasmir")
        MNWord("stockbroker — وسيط بورصة — wasiit boorsa;  broker in general: سمسار — simsaar")
        MNWord("buy order — أمر شراء — amr shiraa2")
        MNWord("sell order — أمر بيع — amr bee3")
        MNWord("short selling — البيع على المكشوف — el-bee3 3al-makshuuf")
        MNWord("dividend — توزيعات أرباح — tawzii3aat arbaa7  (lit. distribution of profits)")
        MNWord("stock options — خيارات أسهم — khiyaaraat ashum;  at tech companies people usually just say options in English")
        MNExample("2arrart astasmir bil-boorsa — قررت أستثمر بالبورصة — I decided to invest in the stock market.")

        Spacer(modifier = Modifier.height(24.dp))
    }
}


@Composable
private fun MNSectionHeader(text: String) {
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
private fun MNWord(text: String) {
    Text(
        text = fixArabizi(text),
        style = MaterialTheme.typography.bodyMedium,
        fontWeight = FontWeight.SemiBold,
        modifier = Modifier.padding(top = 6.dp, bottom = 2.dp)
    )
}

@Composable
private fun MNInfo(text: String) {
    Text(
        text = fixArabizi("    $text"),
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(vertical = 1.dp)
    )
}

@Composable
private fun MNExample(text: String) {
    Text(
        text = fixArabizi("    e.g. $text"),
        style = MaterialTheme.typography.bodyMedium,
        fontStyle = FontStyle.Italic,
        modifier = Modifier.padding(vertical = 1.dp)
    )
}

@Composable
private fun MNSmall(text: String) {
    Text(
        text = fixArabizi("    $text"),
        style = MaterialTheme.typography.bodySmall,
        modifier = Modifier.padding(vertical = 2.dp)
    )
}

@Composable
private fun MNNote(text: String) {
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
