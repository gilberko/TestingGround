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
fun ContinuousScreen() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        ConSectionHeader("What Is Continuous Aspect?")
        ConBodyText("Palestinian Arabic marks an action that is happening RIGHT NOW — as opposed to something that happens regularly — using the progressive particle عم (3am).")
        Spacer(modifier = Modifier.height(8.dp))
        ConBodyText("Compare habitual vs. progressive:")
        ConBodyText("• بياكل (byakol) — he eats [in general / habitually]")
        ConBodyText("• عم بياكل (3am byakol) — he is eating [right now, at this moment]")
        Spacer(modifier = Modifier.height(8.dp))
        ConBodyText("• بتشتغل (btishtaghil) — she works [habitually]")
        ConBodyText("• عم تشتغل (3am tishtaghil) — she is working [right now]")
        Spacer(modifier = Modifier.height(8.dp))
        ConBodyText("The particle عم (3am) is placed directly before the conjugated present-tense verb. In Palestinian urban Arabic, the بـ (b-) prefix is kept on the verb alongside عم.")
        Spacer(modifier = Modifier.height(8.dp))
        ConBodyText("Note: In Lebanese Arabic you may hear عم (3am) with the verb without the b- prefix (عم ياكل — 3am yaakol). Palestinian speech typically keeps the b-: عم بياكل.")

        ConSectionHeader("Present Continuous — عم (3am) + verb")
        ConBodyText("Using يشتغل (yishtaghil — to work) as the model:")
        Spacer(modifier = Modifier.height(4.dp))
        ConBodyText("• هو — عم بيشتغل — 3am bishtaghil — he is working")
        ConBodyText("• هي — عم تشتغل — 3am tishtaghil — she is working")
        ConBodyText("• أنا — عم بشتغل — 3am bishtaghil — I am working")
        ConBodyText("• إنت — عم تشتغل — 3am tishtaghil — you (m) are working")
        ConBodyText("• إنتي — عم تشتغلي — 3am tishtaghili — you (f) are working")
        ConBodyText("• هم — عم يشتغلو — 3am yishtaghilu — they are working")
        ConBodyText("• إحنا — عم نشتغل — 3am nishtaghil — we are working")
        Spacer(modifier = Modifier.height(8.dp))
        ConBodyText("10 more examples with different verbs:")
        ConBodyText("• عم بياكل — 3am byakol — he is eating")
        ConBodyText("• عم بتشرب — 3am btishrab — she is drinking")
        ConBodyText("• عم بحكي — 3am b7ki — I am talking")
        ConBodyText("• عم بتحكو — 3am bt7ku — you all are talking")
        ConBodyText("• عم بيلعب — 3am bylа3ab — he is playing")
        ConBodyText("• عم بتذاكر — 3am btizakir — she is studying")
        ConBodyText("• عم بيرقد — 3am byir2ud — he is sleeping / lying down")
        ConBodyText("• عم بينتظر — 3am byintazir — he is waiting")
        ConBodyText("• عم بيجي — 3am byiji — he is coming")
        ConBodyText("• عم بيروح — 3am byru7 — he is going")
        ConBodyText("• عم بيمشي — 3am byimshi — he is walking")
        ConBodyText("• عم بيفكر — 3am byfakker — he is thinking")

        ConSectionHeader("Negating the Present Continuous")
        ConBodyText("To say someone is NOT doing something right now, use مش (mish) before عم, or wrap the verb with ما...ش:")
        Spacer(modifier = Modifier.height(8.dp))
        ConBodyText("Option 1 — مش عم (mish 3am): [most common]")
        ConBodyText("• مش عم بياكل — mish 3am byakol — he is not eating")
        ConBodyText("• مش عم بشتغل — mish 3am bishtaghil — I am not working")
        ConBodyText("• مش عم بتسمع — mish 3am btisma3 — you are not listening")
        Spacer(modifier = Modifier.height(8.dp))
        ConBodyText("Option 2 — ما عم...ش (ma 3am...sh): [emphatic/expressive]")
        ConBodyText("• ما عم بياكلش — ma 3am byakolsh — he is really not eating")
        ConBodyText("• ما عم بشتغلش — ma 3am bishtaghilsh — I am not working at all")
        Spacer(modifier = Modifier.height(8.dp))
        ConBodyText("In questions, عم (3am) gives the progressive sense:")
        ConBodyText("• عم بتاكل؟ — 3am btakol? — are you eating (right now)?")
        ConBodyText("• شو عم بتعمل؟ — shu 3am bta3mal? — what are you doing?")
        ConBodyText("• وين عم بيروح؟ — wein 3am byru7? — where is he going?")

        ConSectionHeader("Past Continuous — كان (kaan) + verb / كان عم (kaan 3am)")
        ConBodyText("Palestinian Arabic has two past forms that express ongoing action in the past:")
        Spacer(modifier = Modifier.height(8.dp))
        ConBodyText("1. كان + present tense (kan + present): 'used to' or 'was doing'")
        ConBodyText("• كان بياكل — kan byakol — he was eating / he used to eat")
        ConBodyText("• كانت تشتغل — kanit tishtaghil — she was working / she used to work")
        ConBodyText("• كنت بحكي — kunt b7ki — I was talking / I used to talk")
        Spacer(modifier = Modifier.height(8.dp))
        ConBodyText("2. كان عم (kan 3am): emphasizes an action was IN PROGRESS at a specific moment")
        ConBodyText("• كان عم بياكل لمّا جيت — kan 3am byakol lamma jiit — he was (in the middle of) eating when you came")
        ConBodyText("• كانت عم تحكي بالتلفون — kanit 3am t7ki bil-talifon — she was talking on the phone")
        ConBodyText("• كنت عم بشتغل لمّا اتصل — kunt 3am bishtaghil lamma ittasal — I was working when he called")
        Spacer(modifier = Modifier.height(8.dp))
        ConBodyText("The difference in practice:")
        ConBodyText("• كان بياكل (kan byakol) — he was eating / he used to eat [habitual or ongoing]")
        ConBodyText("• كان عم بياكل (kan 3am byakol) — he was in the middle of eating [at that specific moment]")
        Spacer(modifier = Modifier.height(8.dp))
        ConBodyText("More examples with كان عم (kaan 3am):")
        ConBodyText("• كانو عم يلعبو لمّا وقع — kanu 3am yil3abu lamma wi2i3 — they were playing when he fell")
        ConBodyText("• كنت عم بذاكر لمّا انقطع الكهربا — kunt 3am bizakir lamma in2ata3 il-kahirba — I was studying when the electricity went out")
        ConBodyText("• كانت عم تغني وهو عم يرقص — kanit 3am tghanni w-huwa 3am yir2us — she was singing and he was dancing")

        ConSectionHeader("Future Continuous")
        ConBodyText("The future continuous exists in Palestinian Arabic but is used less frequently than in English. It expresses an action that will be in progress at a future moment.")
        Spacer(modifier = Modifier.height(8.dp))
        ConBodyText("Form: رح يكون + عم + verb (ra7 yekun + 3am + verb)")
        Spacer(modifier = Modifier.height(4.dp))
        ConBodyText("• هو — رح يكون عم بياكل — ra7 yekun 3am byakol — he will be eating")
        ConBodyText("• هي — رح تكون عم تشتغل — ra7 tekun 3am tishtaghil — she will be working")
        ConBodyText("• أنا — رح كون عم بشتغل — ra7 kun 3am bishtaghil — I will be working")
        ConBodyText("• هم — رح يكونو عم يلعبو — ra7 yekunu 3am yil3abu — they will be playing")
        Spacer(modifier = Modifier.height(8.dp))
        ConBodyText("Examples in context:")
        ConBodyText("• لمّا توصل, رح كون عم بطبخ — lamma tosal, ra7 kun 3am btubbukh — when you arrive, I'll be cooking")
        ConBodyText("• بكرا بهالوقت, رح يكون عم يسافر — bukra b-hal-wa2t, ra7 yekun 3am yisafir — tomorrow at this time, he'll be travelling")
        ConBodyText("• رح تكوني عم تذاكري لمّا يتصل — ra7 tekuni 3am tizakiri lamma yittasal — you'll be studying when he calls")
        Spacer(modifier = Modifier.height(8.dp))
        ConBodyText("Note: In everyday speech, people often just use رح (ra7) + present tense for future actions without specifying continuousness. The full رح يكون عم construction is used when the 'in-progress' nuance matters.")

        ConSectionHeader("Common Verbs in Continuous")
        ConBodyText("Present continuous (he is...) — عم بيـ (3am bi-):")
        Spacer(modifier = Modifier.height(4.dp))
        ConBodyText("• عم بياكل — 3am byakol — he is eating")
        ConBodyText("• عم بيشرب — 3am byishrab — he is drinking")
        ConBodyText("• عم بيشتغل — 3am bishtaghil — he is working")
        ConBodyText("• عم بيذاكر — 3am byizakir — he is studying")
        ConBodyText("• عم بيرقد — 3am byir2ud — he is sleeping")
        ConBodyText("• عم بيحكي — 3am byi7ki — he is talking")
        ConBodyText("• عم بيمشي — 3am byimshi — he is walking")
        ConBodyText("• عم بيجي — 3am byiji — he is coming")
        ConBodyText("• عم بيروح — 3am byru7 — he is going")
        ConBodyText("• عم بينتظر — 3am byintazir — he is waiting")
        ConBodyText("• عم بيفكر — 3am byfakker — he is thinking")
        ConBodyText("• عم بيكتب — 3am byuktub — he is writing")
        ConBodyText("• عم بيقرأ — 3am byi2ra — he is reading")
        ConBodyText("• عم بيشوف — 3am byishuf — he is watching / looking")
        ConBodyText("• عم بيسمع — 3am byisma3 — he is listening")
        ConBodyText("• عم بيضحك — 3am byid7ak — he is laughing")
        ConBodyText("• عم بيبكي — 3am bybki — he is crying")
        ConBodyText("• عم بيلعب — 3am bylа3ab — he is playing")
        ConBodyText("• عم بيحضّر — 3am byi7addir — he is preparing")
        ConBodyText("• عم بيساعد — 3am byisa3id — he is helping")

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun ConSectionHeader(text: String) {
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
private fun ConBodyText(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(vertical = 2.dp)
    )
}
