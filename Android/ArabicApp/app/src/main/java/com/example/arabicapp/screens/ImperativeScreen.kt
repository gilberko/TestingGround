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
fun ImperativeScreen() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        ImpSectionHeader("What Is the Imperative?")
        ImpBodyText("The imperative is used for commands, requests, and instructions. Palestinian Arabic has three imperative forms, matching the person being addressed:")
        Spacer(modifier = Modifier.height(8.dp))
        ImpBodyText("• إنت (inta) — you, singular male")
        ImpBodyText("• إنتِ (inti) — you, singular female")
        ImpBodyText("• إنتو (intu) — you, plural (multiple people, or politely to a stranger)")
        Spacer(modifier = Modifier.height(8.dp))
        ImpBodyText("There is no \"I\" or \"he\" imperative — commands are always directed at the person in front of you.")

        ImpSectionHeader("Forming the Positive Imperative")
        ImpBodyText("Take the present tense form for إنت (you m.sg) — the bt- form — and drop the bt- prefix. What remains is the imperative:")
        Spacer(modifier = Modifier.height(8.dp))
        ImpBodyText("• بتروح (btaru7) → drop bt- → روح! (ru7!) = go!")
        ImpBodyText("• بتشتغل (btishtaghil) → drop bt- → اشتغل! (ishtaghil!) = work!")
        ImpBodyText("• بتكتب (btiktib) → drop bt- → اكتب! (uktub!) = write!")
        ImpBodyText("• بتاكل (btaakol) → drop bt- → كول! (kol!) = eat!")
        Spacer(modifier = Modifier.height(8.dp))
        ImpBodyText("For إنتِ (you f.sg): add -i to the masculine form")
        ImpBodyText("For إنتو (you pl): add -u to the masculine form")

        ImpSectionHeader("Conjugation — روح (to go)")
        ImpBodyText("إنت روح! — inta ru7! — go! (m.sg)")
        ImpBodyText("إنتِ روحي! — inti ru7i! — go! (f.sg)")
        ImpBodyText("إنتو روحو! — intu ru7u! — go! (pl)")
        Spacer(modifier = Modifier.height(8.dp))
        ImpBodyText("Examples:")
        ImpBodyText("• روح على البيت! — ru7 3al-beit! — go home!")
        ImpBodyText("• روحي على البيت! — ru7i 3al-beit! — go home! (to a woman)")
        ImpBodyText("• روحو من هون! — ru7u min hon! — leave from here! / go away!")

        ImpSectionHeader("Conjugation — اشتغل (to work)")
        ImpBodyText("إنت اشتغل! — inta ishtaghil! — work! (m.sg)")
        ImpBodyText("إنتِ اشتغلي! — inti ishtaghli! — work! (f.sg)")
        ImpBodyText("إنتو اشتغلو! — intu ishtaghlu! — work! (pl)")
        Spacer(modifier = Modifier.height(8.dp))
        ImpBodyText("Examples:")
        ImpBodyText("• اشتغل بسرعة! — ishtaghil bisur3a! — work fast!")
        ImpBodyText("• اشتغلو مع بعض! — ishtaghlu ma3 ba3d! — work together!")

        ImpSectionHeader("Conjugation — كتب (to write)")
        ImpBodyText("إنت اكتب! — inta uktub! — write! (m.sg)")
        ImpBodyText("إنتِ اكتبي! — inti uktibi! — write! (f.sg)")
        ImpBodyText("إنتو اكتبو! — intu uktibu! — write! (pl)")
        Spacer(modifier = Modifier.height(8.dp))
        ImpBodyText("Examples:")
        ImpBodyText("• اكتب اسمك هون! — uktub ismak hon! — write your name here!")
        ImpBodyText("• اكتبو إيميلكم! — uktibu iimeilkum! — write your emails!")

        ImpSectionHeader("Conjugation — أكل (to eat)")
        ImpBodyText("إنت كول! — inta kol! — eat! (m.sg)")
        ImpBodyText("إنتِ كولي! — inti koli! — eat! (f.sg)")
        ImpBodyText("إنتو كولو! — intu kolu! — eat! (pl)")
        Spacer(modifier = Modifier.height(8.dp))
        ImpBodyText("Examples:")
        ImpBodyText("• كول، الأكل بيبرد! — kol, el-akil bibird! — eat, the food is getting cold!")
        ImpBodyText("• كولو، في كتير طعام! — kolu, fi ktir ta3aam! — eat, there's plenty of food!")

        ImpSectionHeader("Irregular — تعال (come here)")
        ImpBodyText("The imperative of \"come\" is irregular — it does NOT come from جا. It comes from تعالى (ta3ala = to come/approach). This is one of the most common words you will hear:")
        Spacer(modifier = Modifier.height(8.dp))
        ImpBodyText("إنت تعال! — inta ta3al! — come! / come here! (m.sg)")
        ImpBodyText("إنتِ تعالي! — inti ta3ali! — come! / come here! (f.sg)")
        ImpBodyText("إنتو تعالو! — intu ta3alu! — come! / come here! (pl)")
        Spacer(modifier = Modifier.height(8.dp))
        ImpBodyText("Examples:")
        ImpBodyText("• تعال هون! — ta3al hon! — come here!")
        ImpBodyText("• تعالي شوفي هاد! — ta3ali shuufi haad! — come look at this! (to a woman)")
        ImpBodyText("• تعالو نشرب قهوة! — ta3alu nishrab 2ahwe! — come, let's drink coffee!")

        ImpSectionHeader("The Negative Imperative — ما...ش (ma...sh)")
        ImpBodyText("To say \"don't do something\", wrap the 2nd-person present form with the ما...ش (ma...sh) circumfix:")
        ImpBodyText("ما (ma) + present tense form + ش (sh)")
        Spacer(modifier = Modifier.height(8.dp))
        ImpBodyText("The present form keeps the pronoun prefix (t- for m.sg, t-...-i for f.sg, t-...-u for pl):")
        Spacer(modifier = Modifier.height(8.dp))
        ImpBodyText("روح / روحي / روحو (ru7 / ru7i / ru7u)  →  don't go:")
        ImpBodyText("• ما تروحش! — ma tru7sh! — don't go! (m.sg)")
        ImpBodyText("• ما تروحيش! — ma tru7iish! — don't go! (f.sg)")
        ImpBodyText("• ما تروحوش! — ma tru7uush! — don't go! (pl)")
        Spacer(modifier = Modifier.height(8.dp))
        ImpBodyText("اشتغل / اشتغلي / اشتغلو (ishtghil / ishtghili / ishtghilu)  →  don't work:")
        ImpBodyText("• ما تشتغلش! — ma tishtaghilsh! — don't work! (m.sg)")
        ImpBodyText("• ما تشتغليش! — ma tishtaghliish! — don't work! (f.sg)")
        ImpBodyText("• ما تشتغلوش! — ma tishtaghluush! — don't work! (pl)")
        Spacer(modifier = Modifier.height(8.dp))
        ImpBodyText("اكتب / اكتبي / اكتبو (uktub / uktubi / uktubu)  →  don't write:")
        ImpBodyText("• ما تكتبش! — ma tiktibsh! — don't write! (m.sg)")
        ImpBodyText("• ما تكتبيش! — ma tiktibiish! — don't write! (f.sg)")
        ImpBodyText("• ما تكتبوش! — ma tiktibuush! — don't write! (pl)")
        Spacer(modifier = Modifier.height(8.dp))
        ImpBodyText("كول / كولي / كولو (kul / kuli / kulu)  →  don't eat:")
        ImpBodyText("• ما تاكلش! — ma takolsh! — don't eat! (m.sg)")
        ImpBodyText("• ما تاكليش! — ma takliish! — don't eat! (f.sg)")
        ImpBodyText("• ما تاكلوش! — ma takluush! — don't eat! (pl)")
        Spacer(modifier = Modifier.height(8.dp))
        ImpBodyText("تعال / تعالي / تعالو (ta3aal / ta3aali / ta3aalu)  →  don't come:")
        ImpBodyText("• ما تجيش! — ma tijjiish! — don't come! (m.sg)")
        ImpBodyText("• ما تجيش! — ma tijjiish! — don't come! (f.sg — same form)")
        ImpBodyText("• ما تجوش! — ma tijuush! — don't come! (pl)")

        ImpSectionHeader("Common One-Word Commands")
        ImpBodyText("These are some of the most frequently heard commands in everyday Palestinian speech:")
        Spacer(modifier = Modifier.height(4.dp))
        ImpBodyText("• يلاّ! — yalla! — let's go! / come on! / hurry up!")
        ImpBodyText("• خلص! — khalas! — that's enough! / done! / stop!")
        ImpBodyText("• بطّل! — battal! — quit it! / stop doing that!")
        ImpBodyText("• اسكت! / اسكتي! / اسكتو! — uskut! / uskuti! / uskutu! — be quiet!")
        ImpBodyText("• انتبه! / انتبهي! — intabih! / intabihi! — be careful! / watch out!")
        ImpBodyText("• هات! / هاتي! — haat! / haati! — give me! / bring it!")
        ImpBodyText("• شوف! / شوفي! — shuuf! / shuufi! — look! / see!")
        ImpBodyText("• اسمع! / اسمعي! — isma3! / isma3i! — listen!")
        ImpBodyText("• انتظر! / انتظري! — intazir! / intaziri! — wait!")
        ImpBodyText("• فوت! / فوتي! — fuut! / fuuti! — come in! / enter!")
        ImpBodyText("• اطلع! / اطلعي! — utla3! / utla3i! — get out! / go up!")

        ImpSectionHeader("Making Commands Polite")
        ImpBodyText("A bare imperative can sound abrupt. Use these to soften a command into a request:")
        Spacer(modifier = Modifier.height(8.dp))
        ImpBodyText("من فضلك (min fazlak) — please (to a male)")
        ImpBodyText("من فضلك (min fazlik) — please (to a female)")
        ImpBodyText("لو سمحت (law sama7t) — if you'll allow me / please (m.sg)")
        Spacer(modifier = Modifier.height(8.dp))
        ImpBodyText("Or use ممكن (mumkin) + present tense to turn a command into a polite question:")
        ImpBodyText("• ممكن تساعدني؟ — mumkin tsa3idni? — can you help me?")
        ImpBodyText("• ممكن تجيبلي مي؟ — mumkin tjiibli mai? — can you bring me water?")
        ImpBodyText("• ممكن تشتغل أكتر شوي؟ — mumkin tishtaghil aktar shwai? — can you work a bit more?")
        ImpBodyText("• ممكن تحكي بالعربي؟ — mumkin ta7ki bil-3arabi? — can you speak Arabic?")
        Spacer(modifier = Modifier.height(8.dp))
        ImpBodyText("Comparison:")
        ImpBodyText("• روح! (ru7!) = go! [direct command]")
        ImpBodyText("• روح من فضلك! (ru7 min fazlak!) = please go! [polite]")
        ImpBodyText("• ممكن تروح؟ (mumkin taru7?) = can you go? [softened request]")

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun ImpSectionHeader(text: String) {
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
private fun ImpBodyText(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(vertical = 2.dp)
    )
}
