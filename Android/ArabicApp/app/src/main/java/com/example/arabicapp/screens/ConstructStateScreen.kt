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
fun ConstructStateScreen() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        CSNote("Construct state in Arabic is إضافة — iDaafa. It's the same thing as Hebrew סמיכות: two nouns side by side, where the first one \"belongs to\" the second. As in Hebrew, there is no word for \"of\": you just put the nouns next to each other.")

        CSSectionHeader("The Idea")
        CSBodyText("Hebrew: בית + המנהל → בית המנהל — the manager's house")
        CSBodyText("Arabic: بيت + المدير → بيت المدير — beit el-mudiir — the manager's house")
        Spacer(modifier = Modifier.height(4.dp))
        CSBodyText("The first noun (the owned thing) comes first; the second noun (the owner) comes after. Exactly the same order as in Hebrew.")

        CSSectionHeader("Ta Marbuta: Alone vs In a Construct")
        CSBodyText("This is what you noticed: a word ending in ة sounds different alone and as the first word of a construct.")
        Spacer(modifier = Modifier.height(4.dp))
        CSBodyText("• Alone (or as the LAST word): the ة is silent and you just hear a vowel, -e or -a.")
        CSExample("مدرسة — madrase — school")
        CSExample("سيارة — sayyaara — car")
        CSBodyText("• As the FIRST word of a construct: the hidden t comes out and you hear -et / -it.")
        CSExample("مدرسة البنات — madraset el-banaat — the girls' school")
        CSExample("سيارة أبوي — sayyaaret abuuy — my dad's car")
        CSBodyText("• Before a possessive ending (my, your…) it's also a t:")
        CSExample("سيارة → سيارتي — sayyaara → sayyaarti — my car  (like מכונית → מכוניתי)")
        CSExample("مدرسة → مدرستنا — madrase → madrasitna — our school")
        CSNote("This is exactly the Hebrew -ה → -ת change (מלכה → מלכת), and for the same historical reason: both languages go back to an old feminine ending -at. Hebrew changes the letter in writing (ה becomes ת). Arabic keeps writing ة either way; only the sound changes.")
        CSBodyText("-e or -a? In Palestinian and Lebanese, a word said alone ends in -e after most letters (madrase, malake) and in -a after the \"heavy\" letters ق ص ض ط ظ ع ح خ غ and usually ر (sayyaara, lugha, saa3a). In the construct both become -et / -it.")

        CSSectionHeader("Your Examples, Side by Side")
        CSSubHeader("מלכה → מלכת אנגליה")
        CSBodyText("ملكة — malake — queen")
        CSBodyText("ملكة إنجلترا — malket ingiltra — the Queen of England  (also heard malakit)")
        CSSmall("Same root as Hebrew: م.ل.ك = מ.ל.ך. ملك malik = מלך.")
        CSSubHeader("טיפה → טיפת גשם")
        CSBodyText("نقطة — nu2Ta — drop")
        CSBodyText("نقطة شتا — nu2Tet shita — a raindrop  (in speech \"rain\" is شتا shita; MSA مطر maTar: nu2Tet maTar)")
        CSSubHeader("משרד → משרד האוצר")
        CSBodyText("وزارة — wizaara — ministry")
        CSBodyText("وزارة المالية — wizaaret el-maaliyye — the Ministry of Finance  (ta marbuta → t)")
        CSBodyText("مكتب — maktab — office")
        CSBodyText("مكتب المدير — maktab el-mudiir — the manager's office  (no ة, so the first word doesn't change)")
        CSSubHeader("בית → בית ספר")
        CSBodyText("بيت — beit — house")
        CSBodyText("بيت سيدي — beit siidi — my grandpa's house")
        CSSmall("Careful: \"school\" in Arabic isn't built with beit. It's مدرسة madrase (same root as מדרשה, د.ر.س = ד.ר.ש). But you know بيت لحم beit la7m, Bethlehem = בית לחם.")

        CSSectionHeader("Where Does el- Go?")
        CSBodyText("Just like Hebrew: the article goes only on the LAST noun, and it makes the whole phrase definite.")
        Spacer(modifier = Modifier.height(4.dp))
        CSBodyText("بيت مدير — beit mudiir — a manager's house  (בית מנהל)")
        CSBodyText("بيت المدير — beit el-mudiir — the manager's house  (בית המנהל)")
        CSBodyText("✗ البيت المدير — never put el- on the first word, just as Hebrew never says הבית המנהל.")
        Spacer(modifier = Modifier.height(4.dp))
        CSBodyText("Plural works the same way:")
        CSExample("نقط الشتا — nu2aT esh-shita — the raindrops  (טיפות הגשם)")
        CSExample("وزارات الحكومة — wizaaraat el-7ukuume — the government ministries")
        CSSmall("Sun letters still swallow the l: el + shita → esh-shita, el + sayyaara → es-sayyaara.")

        CSSectionHeader("Adjectives and Longer Chains")
        CSBodyText("An adjective can't go in the middle. It comes after the whole construct and agrees with the noun it describes:")
        CSExample("بيت المدير الكبير — beit el-mudiir el-kbiir — the manager's big house / the big manager's house")
        CSSmall("Ambiguous, just like בית המנהל הגדול. With a feminine noun the agreement helps: سيارة المدير الجديدة — sayyaaret el-mudiir ej-jdiide — the manager's NEW car (jdiide is feminine, so it describes the car).")
        CSBodyText("You can chain three or more nouns, again with el- only at the end:")
        CSExample("مفتاح بيت المدير — muftaa7 beit el-mudiir — the key of the manager's house  (מפתח בית המנהל)")

        CSSectionHeader("Differences From Hebrew")
        CSBodyText("• Hebrew often changes the vowels of the first word too (דָּבָר → דְּבַר, בַּיִת → בֵּית). In Arabic only the ending changes (or nothing, if there's no ة).")
        CSBodyText("• Hebrew masculine plurals shorten (מורים → מורי בית הספר). In spoken Arabic they don't: مدرسين المدرسة — mudarrisiin el-madrase — the school's teachers. (Formal MSA does shorten: مدرّسو المدرسة.)")

        CSSectionHeader("The Everyday Alternative: تبع = של")
        CSBodyText("Just as Hebrew can say הבית של המנהל instead of בית המנהל, spoken Arabic can use تبع taba3 (\"belonging to\"):")
        CSExample("البيت تبع المدير — el-beit taba3 el-mudiir — the manager's house  (הבית של המנהל)")
        CSExample("السيارة تبعت المدير — es-sayyaara taba3it el-mudiir — the manager's car  (feminine noun → taba3it)")
        CSExample("الكتب تبعون المدير — el-kutub taba3uun el-mudiir — the manager's books  (plural → taba3uun)")
        CSSmall("Here el- goes on both nouns, like הבית של המנהל. taba3 also takes endings: taba3i (mine), taba3ak (yours). The construct is used for fixed phrases (beit siidi, wizaaret el-maaliyye, madraset el-banaat); taba3 is common in casual speech, especially with loanwords and longer phrases.")

        CSSectionHeader("More Practice Pairs")
        CSBodyText("مدينة → مدينة القدس — madiine → madiinet el-quds — the city of Jerusalem")
        CSBodyText("فنجان → فنجان قهوة — finjaan → finjaan 2ahwe — a cup of coffee")
        CSBodyText("كاسة → كاسة مي — kaase → kaaset mayy — a glass of water")
        CSBodyText("عيلة → عيلة أحمد — 3eile → 3eilet a7mad — Ahmad's family")
        CSBodyText("ساعة → ساعة إيدي — saa3a → saa3it iidi — wristwatch (lit. \"my hand's watch\")")
        CSBodyText("باب → باب الدار — baab → baab ed-daar — the door of the house")

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun CSSectionHeader(text: String) {
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
private fun CSSubHeader(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.SemiBold,
        modifier = Modifier.padding(top = 10.dp, bottom = 2.dp)
    )
}

@Composable
private fun CSBodyText(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(vertical = 2.dp)
    )
}

@Composable
private fun CSExample(text: String) {
    Text(
        text = "    $text",
        style = MaterialTheme.typography.bodyMedium,
        fontStyle = FontStyle.Italic,
        modifier = Modifier.padding(vertical = 1.dp)
    )
}

@Composable
private fun CSSmall(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodySmall,
        modifier = Modifier.padding(vertical = 2.dp)
    )
}

@Composable
private fun CSNote(text: String) {
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
