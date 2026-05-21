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
fun ConditionsScreen() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        CONSectionHeader("Conditional Sentences in Palestinian Arabic")
        CONBodyText("Palestinian Arabic has three main conditional types, matching English real / hypothetical / counterfactual. The two main condition words are:")
        Spacer(modifier = Modifier.height(8.dp))
        CONBodyText("إذا (idha) — if (real, open, genuinely possible)")
        CONBodyText("لو (law) — if (hypothetical, unlikely, or counterfactual)")
        Spacer(modifier = Modifier.height(8.dp))
        CONBodyText("Both translate as \"if\" in English, but they signal different levels of likelihood.")

        CONSectionHeader("Type 1 — Real Condition (Open / Likely)")
        CONBodyText("Used when the condition is genuinely possible — it might happen.")
        CONBodyText("Structure: إذا + (present or past) + رح + imperfect")
        Spacer(modifier = Modifier.height(8.dp))
        CONBodyText("Examples:")
        Spacer(modifier = Modifier.height(4.dp))
        CONBodyText("إذا كان الجو منيح، رح نروح على البحر")
        CONBodyText("idha kaan il-jaw mniḥ, raḥ nruuḥ 'al-baḥar")
        CONBodyText("If the weather is good, we'll go to the beach")
        Spacer(modifier = Modifier.height(8.dp))
        CONBodyText("إذا جيت بكرا، رح تشوف الكل")
        CONBodyText("idha jiit bukra, raḥ tshuuf il-kull")
        CONBodyText("If you come tomorrow, you'll see everyone")
        Spacer(modifier = Modifier.height(8.dp))
        CONBodyText("إذا ما أكلتش، رح تتعب")
        CONBodyText("idha ma akaltish, raḥ tit'ab")
        CONBodyText("If you don't eat, you'll get tired")
        Spacer(modifier = Modifier.height(8.dp))
        CONBodyText("إذا لقيت شغل، رح أستأجر شقة")
        CONBodyText("idha li'iit shughl, raḥ asta'jir sha''a")
        CONBodyText("If I find work, I'll rent an apartment")
        Spacer(modifier = Modifier.height(8.dp))
        CONBodyText("إذا بدّك، قول")
        CONBodyText("idha baddak, 'uul")
        CONBodyText("If you want, just say so")
        Spacer(modifier = Modifier.height(8.dp))
        CONBodyText("إذا سمعتش مني، رح تندم")
        CONBodyText("idha ma simi'tish minni, raḥ tindam")
        CONBodyText("If you don't listen to me, you'll regret it")
        Spacer(modifier = Modifier.height(8.dp))
        CONBodyText("إذا اتصلت، رح أرد")
        CONBodyText("idha ittaṣalt, raḥ aredd")
        CONBodyText("If you call, I'll answer")

        CONSectionHeader("Type 2 — Hypothetical Condition (Unlikely but Possible)")
        CONBodyText("Used for imagined scenarios — less likely, wishful thinking, or speculation.")
        CONBodyText("Structure: لو + past + كان (kaan) + past or imperfect")
        Spacer(modifier = Modifier.height(8.dp))
        CONBodyText("Examples:")
        Spacer(modifier = Modifier.height(4.dp))
        CONBodyText("لو فزت باللوتو، كنت اشتريت بيت جديد")
        CONBodyText("law fuzt bil-luutu, kunt ishtareit beit jadiid")
        CONBodyText("If I won the lottery, I would buy a new house")
        Spacer(modifier = Modifier.height(8.dp))
        CONBodyText("لو كان عندي وقت، كنت اجيت")
        CONBodyText("law kaan 'indi wa'it, kunt ijiit")
        CONBodyText("If I had time, I would come")
        Spacer(modifier = Modifier.height(8.dp))
        CONBodyText("لو بتحكي عربي منيح، رح يكونوا مبسوطين")
        CONBodyText("law bitḥki 'arabi mniḥ, raḥ ykuunu mabsuuṭiin")
        CONBodyText("If you spoke good Arabic, they would be pleased")
        Spacer(modifier = Modifier.height(8.dp))
        CONBodyText("لو ما كان في حرب، الحياة كانت أحسن")
        CONBodyText("law ma kaan fii ḥarb, il-ḥayaah kaanat aḥsan")
        CONBodyText("If there were no war, life would be better")
        Spacer(modifier = Modifier.height(8.dp))
        CONBodyText("لو بدّي اسألك سؤال، رح تزعل؟")
        CONBodyText("law baddi as'alak su'aal, raḥ tiz'al?")
        CONBodyText("If I were to ask you something, would you be upset?")
        Spacer(modifier = Modifier.height(8.dp))
        CONBodyText("لو عندي مليون دولار، كنت سافرت")
        CONBodyText("law 'indi milyoon dollar, kunt saafart")
        CONBodyText("If I had a million dollars, I would have traveled")

        CONSectionHeader("Type 3 — Counterfactual (What Could Have Been)")
        CONBodyText("Used for past situations that did NOT happen — regret, reflection, \"what if\".")
        CONBodyText("Structure: لو + كنت + past (what didn't happen) + كنت + past (what the result would have been)")
        Spacer(modifier = Modifier.height(8.dp))
        CONBodyText("Examples:")
        Spacer(modifier = Modifier.height(4.dp))
        CONBodyText("لو كنت استثمرت بهالسهم، كنت صرت مليونير")
        CONBodyText("law kunt istatsmart bi-haa-s-sahm, kunt ṣirt milyuuner")
        CONBodyText("If I had invested in that stock, I would have been a millionaire")
        Spacer(modifier = Modifier.height(8.dp))
        CONBodyText("لو كنت درست أكتر، كنت نجحت")
        CONBodyText("law kunt darasat aktar, kunt nijaḥt")
        CONBodyText("If I had studied more, I would have passed")
        Spacer(modifier = Modifier.height(8.dp))
        CONBodyText("لو كنت جيت مبكر، كنت شفته")
        CONBodyText("law kunt jiit mibakkir, kunt shuftu")
        CONBodyText("If I had come early, I would have seen him")
        Spacer(modifier = Modifier.height(8.dp))
        CONBodyText("لو ما كنت اشتريت السيارة هديك، ما كنت خسرت هالمصاري")
        CONBodyText("law ma kunt ishtareit is-sayyaara hadiik, ma kunt khisirt hal-maṣaari")
        CONBodyText("If I hadn't bought that car, I wouldn't have lost that money")
        Spacer(modifier = Modifier.height(8.dp))
        CONBodyText("لو كنت إنتِ معي، كان كل شي تمام")
        CONBodyText("law kunt inti ma'i, kaan kill shi tamaam")
        CONBodyText("If you (f) had been with me, everything would have been fine")
        Spacer(modifier = Modifier.height(8.dp))
        CONBodyText("لو ما كنت قلتله، كان ما عرفش")
        CONBodyText("law ma kunt 'ultilu, kaan ma 'arefsh")
        CONBodyText("If I hadn't told him, he wouldn't have known")
        Spacer(modifier = Modifier.height(8.dp))
        CONBodyText("لو كنت نمت بدري، ما كنت تعبت هلق")
        CONBodyText("law kunt nimt badri, ma kunt ta'abt hala'")
        CONBodyText("If I had slept early, I wouldn't be tired now")

        CONSectionHeader("إذا vs لو — The Core Distinction")
        CONBodyText("إذا (idha) = real and open → \"if it rains\" (it genuinely might)")
        CONBodyText("لو (law) = hypothetical or counterfactual → \"if I were rich\" / \"if I had studied\"")
        Spacer(modifier = Modifier.height(8.dp))
        CONBodyText("In rapid everyday Palestinian speech, لو is often used for all three types — it's the more common word. But إذا clearly marks something as a real possibility, while لو signals imagination or regret.")
        Spacer(modifier = Modifier.height(8.dp))
        CONBodyText("Compare:")
        CONBodyText("• إذا مطر بكرا، مش رح نروح — idha maṭar bukra, mish raḥ nruuḥ — if it rains tomorrow, we won't go  (إذا: real possibility)")
        CONBodyText("• لو كنت طيار، كنت سافرت كل يوم — law kunt ṭayyaar, kunt saafart kill yoom — if I were a pilot, I would travel every day  (لو: hypothetical)")

        CONSectionHeader("Useful Conditional Phrases")
        CONBodyText("إذا ما غلطتش — idha ma ghalaṭtish — if I'm not mistaken")
        CONBodyText("لو بتعرف — law bti'ref — if you happen to know")
        CONBodyText("بس لو... — bass law... — if only...")
        CONBodyText("  • بس لو كنت عارف! — bass law kunt 'aarif! — if only I had known!")
        CONBodyText("يا ريت لو... — ya reet law... — I wish that... (for impossible things)")
        CONBodyText("  • يا ريت لو كنت معنا — ya reet law kunt ma'na — I wish you had been with us")
        CONBodyText("  • يا ريت لو عندي وقت — ya reet law 'indi wa'it — I wish I had time")
        CONBodyText("في حالة — fii ḥaalat — in the case that / in case")
        CONBodyText("  • في حالة ما جاش، رح نروح بدونه — fii ḥaalat ma jaash, raḥ nruuḥ biduuno — in case he doesn't come, we'll go without him")

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun CONSectionHeader(text: String) {
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
private fun CONBodyText(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(vertical = 2.dp)
    )
}
