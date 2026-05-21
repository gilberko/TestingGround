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
fun PastTenseScreen() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        PTSectionHeader("Past Tense in Palestinian Arabic — الماضي")
        PTBodyText("Arabic verbs are built on a three-consonant root. The past tense (\"he\" form) is the bare root with no prefix — it's also the dictionary form. To conjugate for other persons, you add suffixes to this root.")
        Spacer(modifier = Modifier.height(8.dp))
        PTBodyText("Example root: k-t-b (write) → كتب (katab) = he wrote")

        PTSectionHeader("Conjugation — كتب (katab = he wrote)")
        PTBodyText("أنا كتبت — ana katabt — I wrote")
        PTBodyText("إنت كتبت — inta katabt — you (m.s.) wrote")
        PTBodyText("إنتِ كتبتِ — inti katabti — you (f.s.) wrote")
        PTBodyText("هو كتب — huwwe katab — he wrote")
        PTBodyText("هي كتبت — hiyye katabit — she wrote")
        PTBodyText("إحنا كتبنا — iḥna katabna — we wrote")
        PTBodyText("إنتو كتبتو — intu katabtu — you (pl.) wrote")
        PTBodyText("هم كتبو — humme katabu — they wrote")

        PTSectionHeader("The Suffixes")
        PTBodyText("(no suffix) → he (3rd person masculine singular) — bare root")
        PTBodyText("-t → I  OR  you (masculine singular)  — same form!")
        PTBodyText("-ti → you (feminine singular)")
        PTBodyText("-it → she (3rd person feminine singular)")
        PTBodyText("-na → we")
        PTBodyText("-tu → you (plural)")
        PTBodyText("-u → they")
        Spacer(modifier = Modifier.height(8.dp))
        PTBodyText("Important: \"I wrote\" (ana katabt) and \"you (m) wrote\" (inta katabt) are identical. The pronoun or context makes the meaning clear.")

        PTSectionHeader("Second Example — أكل (akal = he ate)")
        PTBodyText("Root: a-k-l")
        Spacer(modifier = Modifier.height(4.dp))
        PTBodyText("أنا أكلت — ana akalt — I ate")
        PTBodyText("إنت أكلت — inta akalt — you (m) ate")
        PTBodyText("إنتِ أكلتِ — inti akalti — you (f) ate")
        PTBodyText("هو أكل — huwwe akal — he ate")
        PTBodyText("هي أكلت — hiyye akalit — she ate")
        PTBodyText("إحنا أكلنا — iḥna akalna — we ate")
        PTBodyText("إنتو أكلتو — intu akaltu — you (pl) ate")
        PTBodyText("هم أكلو — humme akalu — they ate")

        PTSectionHeader("Third Example — راح (raḥ = he went)")
        PTBodyText("Root: r-w-ḥ — this is a \"hollow\" verb (middle root letter is a long vowel). It behaves slightly differently when suffixes are added.")
        Spacer(modifier = Modifier.height(4.dp))
        PTBodyText("أنا رحت — ana ruḥt — I went")
        PTBodyText("إنت رحت — inta ruḥt — you (m) went")
        PTBodyText("إنتِ رحتِ — inti ruḥti — you (f) went")
        PTBodyText("هو راح — huwwe raḥ — he went")
        PTBodyText("هي راحت — hiyye raaḥat — she went")
        PTBodyText("إحنا رحنا — iḥna ruḥna — we went")
        PTBodyText("إنتو رحتو — intu ruḥtu — you (pl) went")
        PTBodyText("هم راحو — humme raaḥu — they went")
        Spacer(modifier = Modifier.height(4.dp))
        PTBodyText("Notice: the long aa of راح shortens to u in the suffixed forms (ruḥt, ruḥna, ruḥtu) — this is a regular pattern for hollow verbs.")

        PTSectionHeader("Fourth Example — شاف (shaaf = he saw)")
        PTBodyText("Another hollow verb (root sh-y-f):")
        Spacer(modifier = Modifier.height(4.dp))
        PTBodyText("أنا شفت — ana shift — I saw")
        PTBodyText("إنت شفت — inta shift — you (m) saw")
        PTBodyText("إنتِ شفتِ — inti shifti — you (f) saw")
        PTBodyText("هو شاف — huwwe shaaf — he saw")
        PTBodyText("هي شافت — hiyye shaafat — she saw")
        PTBodyText("إحنا شفنا — iḥna shifna — we saw")
        PTBodyText("إنتو شفتو — intu shiftu — you (pl) saw")
        PTBodyText("هم شافو — humme shaafu — they saw")

        PTSectionHeader("Negating the Past")
        PTBodyText("Use the ما...ش circumfix: ما + verb + ش (see Negation screen for full detail).")
        Spacer(modifier = Modifier.height(4.dp))
        PTBodyText("• ما كتبتش — ma katabtish — I/you (m) didn't write")
        PTBodyText("• ما أكلش — ma akalsh — he didn't eat")
        PTBodyText("• ما راحتش — ma raaḥatsh — she didn't go")
        PTBodyText("• ما رحناش — ma ruḥnaash — we didn't go")
        PTBodyText("• ما شافوش — ma shaafuush — they didn't see")

        PTSectionHeader("More Examples in Context")
        PTBodyText("• أنا شفت الفيلم — ana shift il-film — I saw the movie")
        PTBodyText("• هو راح على البيت — huwwe raḥ 'al-beit — he went home")
        PTBodyText("• هي أكلت، بس أنا ما أكلتش — hiyye akalit, bass ana ma akaltish — she ate, but I didn't")
        PTBodyText("• إحنا وصلنا متأخرين — iḥna wiṣilna mit'akhkhrin — we arrived late")
        PTBodyText("• هم كتبو الإجابات — humme katabu il-ijaabaat — they wrote the answers")
        PTBodyText("• إنت شفت الخبر؟ — inta shift il-khabar? — did you (m) see the news?")
        PTBodyText("• إنتِ وين رحتِ؟ — inti wen ruḥti? — where did you (f) go?")
        PTBodyText("• ما فهمتش شي — ma fahimtish shi — I didn't understand anything")

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun PTSectionHeader(text: String) {
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
private fun PTBodyText(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(vertical = 2.dp)
    )
}
