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
        ConBodyText("Palestinian Arabic marks an action that is happening RIGHT NOW — as opposed to something that happens regularly — using the progressive particle عم ('am).")
        Spacer(modifier = Modifier.height(8.dp))
        ConBodyText("Compare habitual vs. progressive:")
        ConBodyText("• بياكل (byakol) — he eats [in general / habitually]")
        ConBodyText("• عم بياكل ('am byakol) — he is eating [right now, at this moment]")
        Spacer(modifier = Modifier.height(8.dp))
        ConBodyText("• بتشتغل (btishtaghil) — she works [habitually]")
        ConBodyText("• عم تشتغل ('am tishtaghil) — she is working [right now]")
        Spacer(modifier = Modifier.height(8.dp))
        ConBodyText("The particle عم ('am) is placed directly before the conjugated present-tense verb. In Palestinian urban Arabic, the بـ (b-) prefix is kept on the verb alongside عم.")
        Spacer(modifier = Modifier.height(8.dp))
        ConBodyText("Note: In Lebanese Arabic you may hear عم with the verb without the b- prefix (عم ياكل). Palestinian speech typically keeps the b-: عم بياكل.")

        ConSectionHeader("Present Continuous — عم + verb")
        ConBodyText("Using يشتغل (yishtaghil — to work) as the model:")
        Spacer(modifier = Modifier.height(4.dp))
        ConBodyText("• هو — عم بيشتغل — 'am bishtaghil — he is working")
        ConBodyText("• هي — عم تشتغل — 'am tishtaghil — she is working")
        ConBodyText("• أنا — عم بشتغل — 'am bishtaghil — I am working")
        ConBodyText("• إنت — عم تشتغل — 'am tishtaghil — you (m) are working")
        ConBodyText("• إنتي — عم تشتغلي — 'am tishtaghili — you (f) are working")
        ConBodyText("• هم — عم يشتغلو — 'am yishtaghilu — they are working")
        ConBodyText("• إحنا — عم نشتغل — 'am nishtaghil — we are working")
        Spacer(modifier = Modifier.height(8.dp))
        ConBodyText("10 more examples with different verbs:")
        ConBodyText("• عم بياكل — 'am byakol — he is eating")
        ConBodyText("• عم بتشرب — 'am btishrab — she is drinking")
        ConBodyText("• عم بحكي — 'am bḥki — I am talking")
        ConBodyText("• عم بتحكو — 'am btḥku — you all are talking")
        ConBodyText("• عم بيلعب — 'am bylа'ab — he is playing")
        ConBodyText("• عم بتذاكر — 'am btizakir — she is studying")
        ConBodyText("• عم بيرقد — 'am byir'ud — he is sleeping / lying down")
        ConBodyText("• عم بينتظر — 'am byintaẓir — he is waiting")
        ConBodyText("• عم بيجي — 'am byiji — he is coming")
        ConBodyText("• عم بيروح — 'am byruḥ — he is going")
        ConBodyText("• عم بيمشي — 'am byimshi — he is walking")
        ConBodyText("• عم بيفكر — 'am byfakker — he is thinking")

        ConSectionHeader("Negating the Present Continuous")
        ConBodyText("To say someone is NOT doing something right now, use مش (mish) before عم, or wrap the verb with ما...ش:")
        Spacer(modifier = Modifier.height(8.dp))
        ConBodyText("Option 1 — مش عم (mish 'am): [most common]")
        ConBodyText("• مش عم بياكل — mish 'am byakol — he is not eating")
        ConBodyText("• مش عم بشتغل — mish 'am bishtaghil — I am not working")
        ConBodyText("• مش عم بتسمع — mish 'am btisma' — you are not listening")
        Spacer(modifier = Modifier.height(8.dp))
        ConBodyText("Option 2 — ما عم...ش (ma 'am...sh): [emphatic/expressive]")
        ConBodyText("• ما عم بياكلش — ma 'am byakolsh — he is really not eating")
        ConBodyText("• ما عم بشتغلش — ma 'am bishtaghilsh — I am not working at all")
        Spacer(modifier = Modifier.height(8.dp))
        ConBodyText("In questions, عم gives the progressive sense:")
        ConBodyText("• عم بتاكل؟ — 'am btakol? — are you eating (right now)?")
        ConBodyText("• شو عم بتعمل؟ — shu 'am bta'mal? — what are you doing?")
        ConBodyText("• وين عم بيروح؟ — wein 'am byruḥ? — where is he going?")

        ConSectionHeader("Past Continuous — كان + verb / كان عم")
        ConBodyText("Palestinian Arabic has two past forms that express ongoing action in the past:")
        Spacer(modifier = Modifier.height(8.dp))
        ConBodyText("1. كان + present tense (kan + present): 'used to' or 'was doing'")
        ConBodyText("• كان بياكل — kan byakol — he was eating / he used to eat")
        ConBodyText("• كانت تشتغل — kanit tishtaghil — she was working / she used to work")
        ConBodyText("• كنت بحكي — kunt bḥki — I was talking / I used to talk")
        Spacer(modifier = Modifier.height(8.dp))
        ConBodyText("2. كان عم (kan 'am): emphasizes an action was IN PROGRESS at a specific moment")
        ConBodyText("• كان عم بياكل لمّا جيت — kan 'am byakol lamma jiit — he was (in the middle of) eating when you came")
        ConBodyText("• كانت عم تحكي بالتلفون — kanit 'am tḥki bil-talifon — she was talking on the phone")
        ConBodyText("• كنت عم بشتغل لمّا اتصل — kunt 'am bishtaghil lamma ittaṣal — I was working when he called")
        Spacer(modifier = Modifier.height(8.dp))
        ConBodyText("The difference in practice:")
        ConBodyText("• كان بياكل (kan byakol) — he was eating / he used to eat [habitual or ongoing]")
        ConBodyText("• كان عم بياكل (kan 'am byakol) — he was in the middle of eating [at that specific moment]")
        Spacer(modifier = Modifier.height(8.dp))
        ConBodyText("More examples with كان عم:")
        ConBodyText("• كانو عم يلعبو لمّا وقع — kanu 'am yil'abu lamma wi'i' — they were playing when he fell")
        ConBodyText("• كنت عم بذاكر لمّا انقطع الكهربا — kunt 'am bizakir lamma in'ata' il-kahirba — I was studying when the electricity went out")
        ConBodyText("• كانت عم تغني وهو عم يرقص — kanit 'am tghanni w-huwa 'am yir'uṣ — she was singing and he was dancing")

        ConSectionHeader("Future Continuous")
        ConBodyText("The future continuous exists in Palestinian Arabic but is used less frequently than in English. It expresses an action that will be in progress at a future moment.")
        Spacer(modifier = Modifier.height(8.dp))
        ConBodyText("Form: رح يكون + عم + verb (raḥ yekun + 'am + verb)")
        Spacer(modifier = Modifier.height(4.dp))
        ConBodyText("• هو — رح يكون عم بياكل — raḥ yekun 'am byakol — he will be eating")
        ConBodyText("• هي — رح تكون عم تشتغل — raḥ tekun 'am tishtaghil — she will be working")
        ConBodyText("• أنا — رح كون عم بشتغل — raḥ kun 'am bishtaghil — I will be working")
        ConBodyText("• هم — رح يكونو عم يلعبو — raḥ yekunu 'am yil'abu — they will be playing")
        Spacer(modifier = Modifier.height(8.dp))
        ConBodyText("Examples in context:")
        ConBodyText("• لمّا توصل, رح كون عم بطبخ — lamma toṣal, raḥ kun 'am bṭubbukh — when you arrive, I'll be cooking")
        ConBodyText("• بكرا بهالوقت, رح يكون عم يسافر — bukra bil-wa'it hayk, raḥ yekun 'am yisafir — tomorrow at this time, he'll be travelling")
        ConBodyText("• رح تكوني عم تذاكري لمّا يتصل — raḥ tekuni 'am tizakiri lamma yittaṣal — you'll be studying when he calls")
        Spacer(modifier = Modifier.height(8.dp))
        ConBodyText("Note: In everyday speech, people often just use رح + present tense for future actions without specifying continuousness. The full رح يكون عم construction is used when the 'in-progress' nuance matters.")

        ConSectionHeader("Common Verbs in Continuous")
        ConBodyText("Present continuous (he is...) — عم بيـ ('am bi-):")
        Spacer(modifier = Modifier.height(4.dp))
        ConBodyText("• عم بياكل — 'am byakol — he is eating")
        ConBodyText("• عم بيشرب — 'am byishrab — he is drinking")
        ConBodyText("• عم بيشتغل — 'am bishtaghil — he is working")
        ConBodyText("• عم بيذاكر — 'am byizakir — he is studying")
        ConBodyText("• عم بيرقد — 'am byir'ud — he is sleeping")
        ConBodyText("• عم بيحكي — 'am byiḥki — he is talking")
        ConBodyText("• عم بيمشي — 'am byimshi — he is walking")
        ConBodyText("• عم بيجي — 'am byiji — he is coming")
        ConBodyText("• عم بيروح — 'am byruḥ — he is going")
        ConBodyText("• عم بينتظر — 'am byintaẓir — he is waiting")
        ConBodyText("• عم بيفكر — 'am byfakker — he is thinking")
        ConBodyText("• عم بيكتب — 'am byuktub — he is writing")
        ConBodyText("• عم بي'رأ — 'am byi'ra — he is reading")
        ConBodyText("• عم بيشوف — 'am byishuf — he is watching / looking")
        ConBodyText("• عم بيسمع — 'am byisma' — he is listening")
        ConBodyText("• عم بيضحك — 'am byiḍḥak — he is laughing")
        ConBodyText("• عم بيبكي — 'am bybki — he is crying")
        ConBodyText("• عم بيلعب — 'am bylа'ab — he is playing")
        ConBodyText("• عم بيحضّر — 'am byiḥaḍḍir — he is preparing")
        ConBodyText("• عم بيساعد — 'am byisa'id — he is helping")

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
