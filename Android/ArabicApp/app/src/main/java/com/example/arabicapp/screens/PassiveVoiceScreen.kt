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
fun PassiveVoiceScreen() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        PasSectionHeader("How the Passive Works in Levantine Arabic")
        PasBodyText("In Modern Standard Arabic (MSA), the passive is formed by changing vowels inside the verb root — but Levantine Arabic does NOT use that pattern in normal speech.")
        Spacer(modifier = Modifier.height(8.dp))
        PasBodyText("Instead, Palestinian/Levantine Arabic has three main ways to express passive meaning:")
        PasBodyText("1. Pattern VII — انفعل (infa'al): the most common colloquial passive")
        PasBodyText("2. Pattern V — تفعّل (tfa''al): passive of intensive/causative verbs")
        PasBodyText("3. Implicit passive — 3rd person plural: 'they did X' = 'X was done'")
        Spacer(modifier = Modifier.height(8.dp))
        PasBodyText("The passive is used when the focus is on the action or the result, not on who performed it.")

        PasSectionHeader("Pattern VII — انفعل (infa'al)")
        PasBodyText("This is the main passive/reflexive pattern in Levantine Arabic. It is added as a prefix to Form I verb roots.")
        Spacer(modifier = Modifier.height(4.dp))
        PasBodyText("Formation: add إن (in-) before the verb root in the past, ين (yin-) / تين (tin-) / بين (bin-) in the present")
        Spacer(modifier = Modifier.height(8.dp))
        PasBodyText("Active → Passive examples:")
        PasBodyText("• كسر (ksar, broke) → انكسر (inkasar, was broken)")
        PasBodyText("• فتح (fataḥ, opened) → انفتح (infataḥ, was opened)")
        PasBodyText("• باع (ba', sold) → انباع (inba', was sold)")
        PasBodyText("• أكل (akal, ate) → انأكل (in'akal, was eaten)")
        PasBodyText("• شرب (sharib, drank) → انشرب (inshirib, was drunk / got drunk)")
        PasBodyText("• قطع ('ata', cut) → انقطع (in'ata', was cut)")
        PasBodyText("• بنى (bana, built) → انبنى (inbana, was built)")
        PasBodyText("• وجد (wajad, found) → اتوجد / انوجد — less common, often replaced by لقي (li'i)")

        PasSectionHeader("Past Tense — Pattern VII Conjugation")
        PasBodyText("Using انكسر (inkasar — was broken) as the model:")
        Spacer(modifier = Modifier.height(4.dp))
        PasBodyText("• هو — inkasar — it/he was broken")
        PasBodyText("• هي — inkasarat — it/she was broken")
        PasBodyText("• أنا — inkasart — I was broken [rarely used for people]")
        PasBodyText("• إنت — inkasart — you (m) — something was broken [on you]")
        PasBodyText("• إنتي — inkasarti — you (f)")
        PasBodyText("• هم — inkasaru — they were broken / it broke apart")
        PasBodyText("• إحنا — inkasarna — we were broken")
        Spacer(modifier = Modifier.height(8.dp))
        PasBodyText("More examples in past tense:")
        PasBodyText("• الكاسة انكسرت — il-kase inkasarat — the glass was broken")
        PasBodyText("• الباب انفتح — il-bab infataḥ — the door was opened")
        PasBodyText("• الأكل انأكل — il-akl in'akal — the food was eaten")
        PasBodyText("• الكتاب انباع — il-ktab inba' — the book was sold")
        PasBodyText("• الطريق انقطع — iṭ-ṭari' in'ata' — the road was cut off")

        PasSectionHeader("Present Tense — Pattern VII Conjugation")
        PasBodyText("Using انكسر in present (byinkasar — it gets broken):")
        Spacer(modifier = Modifier.height(4.dp))
        PasBodyText("• هو — byinkasar — it/he gets broken")
        PasBodyText("• هي — btinkasar — it/she gets broken")
        PasBodyText("• أنا — binkasar — I get broken")
        PasBodyText("• إنت — btinkasar — you (m) get broken")
        PasBodyText("• إنتي — btinkasari — you (f) get broken")
        PasBodyText("• هم — byinkasaru — they get broken")
        Spacer(modifier = Modifier.height(8.dp))
        PasBodyText("Examples:")
        PasBodyText("• هيك الأشياء بتنكسر — hek il-ashya btinkasar — things like that get broken")
        PasBodyText("• الباب بينفتح بسهولة — il-bab byinfataḥ bi-suhule — the door opens easily")
        PasBodyText("• هاد ما بيناكلش — had ma byinakish — this is not to be eaten / not edible")
        PasBodyText("• الصفقة بتنباع بسرعة — iṣ-ṣaf'a btinba' bi-sur'a — the deal gets sold quickly")

        PasSectionHeader("Future Tense — Pattern VII")
        PasBodyText("Add رح (raḥ) before the present tense form:")
        Spacer(modifier = Modifier.height(4.dp))
        PasBodyText("• هو — رح ينكسر — raḥ yinkasar — it/he will be broken")
        PasBodyText("• هي — رح تنكسر — raḥ tinkasar — it/she will be broken")
        PasBodyText("• أنا — رح انكسر — raḥ inkasar — I will be broken")
        PasBodyText("• إنت — رح تنكسر — raḥ tinkasar — you (m) will be broken")
        PasBodyText("• إنتي — رح تنكسري — raḥ tinkasari — you (f) will be broken")
        PasBodyText("• هم — رح ينكسرو — raḥ yinkasaru — they will be broken")
        Spacer(modifier = Modifier.height(8.dp))
        PasBodyText("Examples:")
        PasBodyText("• رح ينباع المحل — raḥ yinba' il-maḥall — the shop will be sold")
        PasBodyText("• رح ينفتح الباب — raḥ yinfataḥ il-bab — the door will be opened")
        PasBodyText("• رح ينأكل الأكل — raḥ yin'akal il-akl — the food will be eaten")

        PasSectionHeader("Pattern V — تفعّل (tfa''al)")
        PasBodyText("Pattern V is the passive/reflexive of Pattern II (the intensive/causative pattern). Pattern II doubles the middle root letter; Pattern V adds تـ (t-) prefix and has the same doubling.")
        Spacer(modifier = Modifier.height(8.dp))
        PasBodyText("Active (Pattern II) → Passive (Pattern V):")
        PasBodyText("• علّم ('allam, taught) → تعلّم (t'allam, was taught / learned)")
        PasBodyText("• جرّب (jarrab, tried/tested) → تجرّب (tjarrab, was tried / got tested)")
        PasBodyText("• وظّف (waddaf, employed) → توظّف (twaddaf, was employed / got hired)")
        PasBodyText("• دمّر (dammar, destroyed) → تدمّر (tdammar, was destroyed)")
        PasBodyText("• حوّل (ḥawwal, transferred/transformed) → تحوّل (tḥawwal, was transformed)")
        Spacer(modifier = Modifier.height(8.dp))
        PasBodyText("Examples in sentences:")
        PasBodyText("• تعلّمت عربي — t'allamit 'arabi — I learned Arabic (was taught Arabic)")
        PasBodyText("• المبنى تدمّر — il-mabna tdammar — the building was destroyed")
        PasBodyText("• توظّف في شركة كبيرة — twaddaf fi sharike kbire — he was hired by a big company")

        PasSectionHeader("Implicit Passive — 3rd Person Plural")
        PasBodyText("The most natural passive in Palestinian spoken Arabic is simply using 'they' (هم) as an unspecified subject. This is very common in everyday speech.")
        Spacer(modifier = Modifier.height(4.dp))
        PasBodyText("Pattern: 3rd person plural verb, no explicit subject")
        Spacer(modifier = Modifier.height(8.dp))
        PasBodyText("Examples:")
        PasBodyText("• قالولي — 'alu-li — they told me = I was told")
        PasBodyText("• قالولو — 'alu-lo — they told him = he was told")
        PasBodyText("• عملولي هيك — 'amalu-li hayk — they did this to me = this was done to me")
        PasBodyText("• بعثولو رسالة — ba'atu-lo risale — they sent him a message = he was sent a message")
        PasBodyText("• فتحولو الباب — fataḥu-lo il-bab — they opened the door for him = the door was opened for him")
        PasBodyText("• ما خلّوه يدخل — ma khalluu yidkhul — they didn't let him in = he wasn't allowed in")
        Spacer(modifier = Modifier.height(8.dp))
        PasBodyText("This form avoids the need for passive conjugation entirely and sounds very natural in speech.")

        PasSectionHeader("Examples in Context")
        PasBodyText("PAST — things that happened:")
        PasBodyText("• الشباك انكسر إمبارح — ish-shubbak inkasar imbarih — the window was broken yesterday")
        PasBodyText("• البيت انباع بسرعة — il-beit inba' bi-sur'a — the house was sold quickly")
        PasBodyText("• قالولي إنو الموضوع خلص — 'alu-li inno il-mawdu' khalaṣ — I was told the matter was settled")
        PasBodyText("• الشغل تعمل مزبوط — ish-shughul t'amal mazbut — the work was done properly")
        Spacer(modifier = Modifier.height(8.dp))
        PasBodyText("PRESENT — ongoing or habitual:")
        PasBodyText("• المحل بيتساوى كتير — il-maḥall biytisawa ktir — the shop is worth a lot / is valued highly")
        PasBodyText("• هيك الأشياء ما بتنعمل — hek il-ashya ma btinmil — things like that are not done")
        PasBodyText("• بيحكى إنو... — byiḥka inno... — it is said that... / they say that...")
        Spacer(modifier = Modifier.height(8.dp))
        PasBodyText("FUTURE — what will happen:")
        PasBodyText("• رح ينباع كل شي — raḥ yinba' kull shi — everything will be sold")
        PasBodyText("• رح يتعمل التقرير بكرا — raḥ yit'amal it-ta'rir bukra — the report will be done tomorrow")
        PasBodyText("• رح يقولولك الجواب — raḥ yu'ulu-lak il-jawab — they will tell you the answer = you will be told the answer")

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun PasSectionHeader(text: String) {
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
private fun PasBodyText(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(vertical = 2.dp)
    )
}
