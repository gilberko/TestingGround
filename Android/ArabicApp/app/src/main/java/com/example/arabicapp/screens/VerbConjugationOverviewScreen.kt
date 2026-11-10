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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun VerbConjugationOverviewScreen() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        VCOSectionHeader("One Verb, All the Way Through — أكل (to eat)")
        VCOBodyText("This screen takes a single verb — أكل akal, \"to eat\" — and walks it through the present, \"I want to eat\", the past, the future and the imperative (giving commands: \"Eat!\"), so you can see how the pieces fit together. At the end we look at the noun built from the same root — like Hebrew עבודה from לעבוד.")
        Spacer(modifier = Modifier.height(8.dp))
        VCOBodyText("The pronouns we'll use:")
        VCOBodyText("• أنا — ana — I")
        VCOBodyText("• إنت — inta — you (to a man)")
        VCOBodyText("• إنتِ — inti — you (to a woman)")
        VCOBodyText("• هو — huwwe — he")
        VCOBodyText("• هي — hiyye — she")
        VCOBodyText("• إحنا — i7na — we")
        VCOBodyText("• إنتو — intu — you (plural)")
        VCOBodyText("• هم — humme — they")
        Spacer(modifier = Modifier.height(8.dp))
        VCONote("The root of \"eat\" is ء-ك-ل (2-k-l). In everyday Palestinian speech the hamza is softened into a long \"aa\", so you hear baakol, not ba2kol.")

        VCOSectionHeader("1. Present — I eat, you eat…")
        VCOBodyText("The present (habitual: \"I eat\", \"I usually eat\") = b- + the verb with a person prefix:")
        Spacer(modifier = Modifier.height(4.dp))
        VCOBodyText("أنا باكل — ana baakol — I eat")
        VCOBodyText("إنت بتاكل — inta btaakol — you (m) eat")
        VCOBodyText("إنتِ بتاكلي — inti btaakli — you (f) eat")
        VCOBodyText("هو بياكل — huwwe byaakol — he eats")
        VCOBodyText("هي بتاكل — hiyye btaakol — she eats")
        VCOBodyText("إحنا بناكل — i7na bnaakol — we eat")
        VCOBodyText("إنتو بتاكلو — intu btaaklu — you (pl) eat")
        VCOBodyText("هم بياكلو — humme byaaklu — they eat")
        Spacer(modifier = Modifier.height(8.dp))
        VCOSubHeader("Masculine vs feminine")
        VCOBodyText("• you (m) btaakol vs you (f) btaakli — the woman's form adds -i at the end.")
        VCOBodyText("• he byaakol vs she btaakol — he takes y-, she takes t-.")
        VCOBodyText("• Watch out: \"you (m)\" and \"she\" are the same word (btaakol). Only the pronoun or the context tells them apart.")
        VCOBodyText("• I, we, you (pl) and they have no gender difference.")
        Spacer(modifier = Modifier.height(8.dp))
        VCONote("Variation: you'll also hear bokel / btokel / btokli / byokel and, for \"we\", mnaakol (b- becomes m- before n). All are normal Palestinian.")

        VCOSectionHeader("2. Wanting — I want to eat")
        VCOBodyText("\"Want\" in Palestinian is بدّ bidd + a person ending:")
        Spacer(modifier = Modifier.height(4.dp))
        VCOBodyText("بدّي — biddi — I want")
        VCOBodyText("بدّك — biddak — you (m) want")
        VCOBodyText("بدّك — biddek — you (f) want")
        VCOBodyText("بدّو — biddo — he wants")
        VCOBodyText("بدّها — bidha — she wants")
        VCOBodyText("بدّنا — bidna — we want")
        VCOBodyText("بدّكم — biddkum — you (pl) want")
        VCOBodyText("بدّهم — biddhum — they want")
        Spacer(modifier = Modifier.height(8.dp))
        VCOSubHeader("Is بدّ a verb?")
        VCOBodyText("Not really. bidd is a noun meaning roughly \"wish / will\", and the endings are the same possessive endings as in ismi (my name), ismak (your name): biddi = \"my wish (is)…\". That's why it changes with the person but has no b- prefix and no past form of its own. For the past you put كان kaan in front: kaan biddi = I wanted.")
        Spacer(modifier = Modifier.height(8.dp))
        VCONote("Remark — is biddi really \"to want\"?\n\n• In meaning, yes: biddi is the everyday way to say \"I want\", and it behaves like a verb in a sentence (biddi aakol = I want to eat). Grammarians call words like this pseudo-verbs. 3ind (3indi = I have) is another one: \"have\" in meaning, but grammatically a preposition plus an ending.\n\n• In form, no: it never takes verb prefixes (no b-, no y-/t-), it has no past or imperative of its own, and you negate it with ma (ma biddi). It's usually traced back to بِوُدّي bi-wuddi, \"in my wish\", from wudd = love / wish.\n\n• Only in Levantine? Yes. biddi is typical of Levantine Arabic: Palestinian, Jordanian, Lebanese and Syrian. Other varieties use different words, and most of them are real verbs or participles:\n   – MSA: أريد uriid (verb arāda / yurīd)\n   – Egyptian: عايز 3aayiz / عايزة 3ayza (a participle that agrees in gender)\n   – Gulf: أبي abi / أبغى abgha\n   – Iraqi: أريد ariid\n   – Moroccan: بغيت bghit\n   Outside the Levant, biddi is understood but marks you as speaking Levantine.")
        Spacer(modifier = Modifier.height(4.dp))
        VCOBodyText("Notice biddak (m) vs biddek (f) — they are written the same in Arabic (بدّك) but pronounced differently.")
        Spacer(modifier = Modifier.height(8.dp))
        VCOSubHeader("Does \"eat\" change after بدّ? Yes!")
        VCOBodyText("The second verb also agrees with the person — including masculine/feminine — but it drops the b- prefix:")
        Spacer(modifier = Modifier.height(4.dp))
        VCOBodyText("بدّي آكل — biddi aakol — I want to eat")
        VCOBodyText("بدّك تاكل — biddak taakol — you (m) want to eat")
        VCOBodyText("بدّك تاكلي — biddek taakli — you (f) want to eat")
        VCOBodyText("بدّو ياكل — biddo yaakol — he wants to eat")
        VCOBodyText("بدّها تاكل — bidha taakol — she wants to eat")
        VCOBodyText("بدّنا ناكل — bidna naakol — we want to eat")
        VCOBodyText("بدّكم تاكلو — biddkum taaklu — you (pl) want to eat")
        VCOBodyText("بدّهم ياكلو — biddhum yaaklu — they want to eat")
        Spacer(modifier = Modifier.height(8.dp))
        VCOBodyText("Compare: btaakol (you eat) → biddak taakol (you want to eat). Same verb, minus the b-. This b-less form is used after bidd, after ra7 (future), after laazim (must), and after mumkin (maybe / can).")
        Spacer(modifier = Modifier.height(8.dp))
        VCOBodyText("Wanting something different from someone else? Just change the second verb's person: biddi taakol = I want you (m) to eat; biddo naakol = he wants us to eat.")
        Spacer(modifier = Modifier.height(8.dp))
        VCOBodyText("Negative: ما بدّي آكل — ma biddi aakol — I don't want to eat (also: mish biddi).")

        VCOSectionHeader("3. Past — I ate, you ate…")
        VCOBodyText("The past uses endings instead of prefixes, and no b-:")
        Spacer(modifier = Modifier.height(4.dp))
        VCOBodyText("أنا أكلت — ana akalt — I ate")
        VCOBodyText("إنت أكلت — inta akalt — you (m) ate")
        VCOBodyText("إنتِ أكلتِ — inti akalti — you (f) ate")
        VCOBodyText("هو أكل — huwwe akal — he ate")
        VCOBodyText("هي أكلت — hiyye akalat — she ate")
        VCOBodyText("إحنا أكلنا — i7na akalna — we ate")
        VCOBodyText("إنتو أكلتو — intu akaltu — you (pl) ate")
        VCOBodyText("هم أكلو — humme akalu — they ate")
        Spacer(modifier = Modifier.height(8.dp))
        VCOSubHeader("Masculine vs feminine")
        VCOBodyText("• you (m) akalt vs you (f) akalti — again the woman's form adds -i.")
        VCOBodyText("• he akal vs she akalat — \"he\" is the bare verb; \"she\" adds -at (also heard as akalit).")
        VCOBodyText("• Watch out: \"I\" and \"you (m)\" are the same word (akalt).")
        Spacer(modifier = Modifier.height(8.dp))
        VCOBodyText("Negative: ما أكلتش — ma akaltish — I didn't eat.")

        VCOSectionHeader("4. Future — I'm going to eat")
        VCOBodyText("You were right: ana ra7 + verb means \"I'm going to…\". رح ra7 goes in front of the same b-less verb you used after biddi:")
        Spacer(modifier = Modifier.height(4.dp))
        VCOBodyText("أنا رح آكل — ana ra7 aakol — I'm going to eat")
        VCOBodyText("إنت رح تاكل — inta ra7 taakol — you (m) are going to eat")
        VCOBodyText("إنتِ رح تاكلي — inti ra7 taakli — you (f) are going to eat")
        VCOBodyText("هو رح ياكل — huwwe ra7 yaakol — he's going to eat")
        VCOBodyText("هي رح تاكل — hiyye ra7 taakol — she's going to eat")
        VCOBodyText("إحنا رح ناكل — i7na ra7 naakol — we're going to eat")
        VCOBodyText("إنتو رح تاكلو — intu ra7 taaklu — you (pl) are going to eat")
        VCOBodyText("هم رح ياكلو — humme ra7 yaaklu — they're going to eat")
        Spacer(modifier = Modifier.height(8.dp))
        VCOSubHeader("Where does ra7 come from?")
        VCOBodyText("ra7 is a worn-down form of رايح raayi7, \"going\" — the active participle of the verb راح raa7, \"to go\". So ana ra7 aakol is literally \"I (am) going (to) eat\" — exactly like English \"I'm gonna eat\", where \"gonna\" is a worn-down \"going to\".")
        Spacer(modifier = Modifier.height(4.dp))
        VCOBodyText("The verb raa7 itself conjugates normally (ru7t = I went, ra7at = she went, biruu7 = he goes). But the shortened ra7 has become a fixed future marker and never changes: ana ra7, hiyye ra7, humme ra7 — only the verb after it changes.")
        Spacer(modifier = Modifier.height(4.dp))
        VCOBodyText("You will sometimes hear the full participle, which does agree with the speaker:")
        VCOBodyText("• رايح آكل — raayi7 aakol — I'm going to eat (said by a man)")
        VCOBodyText("• رايحة آكل — raay7a aakol — I'm going to eat (said by a woman)")
        VCOBodyText("• رايحين ناكل — raay7iin naakol — we're going to eat")
        Spacer(modifier = Modifier.height(8.dp))
        VCONote("Other dialects shorten it differently: Egyptian uses 7a- (7aakol), some Levantine speakers say rah or ri7. In Palestine ra7 is the everyday form.")
        Spacer(modifier = Modifier.height(8.dp))
        VCOBodyText("Negative: مش رح آكل — mish ra7 aakol — I'm not going to eat.")

        VCOSectionHeader("5. Imperative — Eat!")
        VCOBodyText("A command only exists for \"you\", so there are just three forms:")
        Spacer(modifier = Modifier.height(4.dp))
        VCOBodyText("كُل — kul — eat! (to a man)")
        VCOBodyText("كُلي — kuli — eat! (to a woman)")
        VCOBodyText("كُلو — kulu — eat! (to a group)")
        Spacer(modifier = Modifier.height(8.dp))
        VCOBodyText("Same endings as in the present: -i for a woman, -u for a group. You'll also hear kol / koli / kolu, especially further north in the Levant (Lebanon, Syria).")
        Spacer(modifier = Modifier.height(8.dp))
        VCOSubHeader("akal is irregular")
        VCOBodyText("The hamza of the root disappears and you're left with a short kul — exactly like MSA كُلْ kul. أخد akhad (to take) does the same thing: خُد khud / خُدي khudi / خُدو khudu — take!")
        Spacer(modifier = Modifier.height(8.dp))
        VCOSubHeader("A regular verb for comparison — شرب (to drink)")
        VCOBodyText("اِشرب — ishrab — drink! (m)")
        VCOBodyText("اِشربي — ishrabi — drink! (f)")
        VCOBodyText("اِشربو — ishrabu — drink! (pl)")
        Spacer(modifier = Modifier.height(4.dp))
        VCOBodyText("The rule for most verbs: take the b-less \"you\" form (tishrab, as in biddak tishrab) and drop the t-: (t)ishrab → ishrab. The i- at the front is only there so the word doesn't start with two consonants.")
        Spacer(modifier = Modifier.height(8.dp))
        VCOSubHeader("Don't eat! — the negative command")
        VCOBodyText("For \"don't\" you don't use the imperative. You use the b-less present form with la or ma:")
        Spacer(modifier = Modifier.height(4.dp))
        VCOBodyText("لا تاكل — la taakol — don't eat! (m)")
        VCOBodyText("لا تاكلي — la taakli — don't eat! (f)")
        VCOBodyText("لا تاكلو — la taaklu — don't eat! (pl)")
        Spacer(modifier = Modifier.height(4.dp))
        VCOBodyText("Also common: ما تاكلش — ma taakolsh (m), ma taaklish (f), ma taakluush (pl).")
        Spacer(modifier = Modifier.height(8.dp))
        VCONote("You'll hear this one constantly as a guest: تفضّل كُل! — tfaDDal, kul! — \"Please, eat!\" (to a woman: tfaDDali, kuli!)")

        VCOSectionHeader("6. The Noun From the Same Root — المصدر maSdar")
        VCOBodyText("Arabic builds words from a root of (usually) three consonants, just like Hebrew. Every verb has a noun that names the action itself, called the مصدر maSdar (\"verbal noun\"). It is the same idea as the Hebrew שם פעולה:")
        Spacer(modifier = Modifier.height(4.dp))
        VCOBodyText("• Hebrew: ע.ב.ד → לעבוד (to work) → עבודה (work)")
        VCOBodyText("• Arabic: ء.ك.ل → أكل akal (he ate) → أكل akl (food / eating)")
        Spacer(modifier = Modifier.height(4.dp))
        VCOBodyText("akl is the Arabic counterpart of Hebrew אכילה: same root, same idea. In Arabic, akal (verb) and akl (noun) are even written the same, أكل; only the vowels differ.")
        Spacer(modifier = Modifier.height(8.dp))
        VCONote("Cognate trap: Arabic has the same root as עבד, ع.ب.د, but its meaning went a different way. عبادة 3ibaade = worship (compare Hebrew עבודת אלוהים), and عبد 3abd = servant. \"Work\" in Arabic is شغل shughl (from اشتغل ishtaghal, to work) or عمل 3amal (from عمل 3imil, to do / make).")
        Spacer(modifier = Modifier.height(8.dp))
        VCOSubHeader("More verbs and their nouns")
        VCOBodyText("شرب shirib (drank) → شُرب shurb — drinking")
        VCOBodyText("نام naam (slept) → نوم noom — sleep")
        VCOBodyText("لعب li3ib (played) → لعب la3b — playing")
        VCOBodyText("طبخ Tabakh (cooked) → طبخ Tabkh — cooking")
        VCOBodyText("سافر saafar (travelled) → سفر safar — travel")
        VCOBodyText("درس daras (studied) → دراسة diraase — studying")
        VCOBodyText("كتب katab (wrote) → كتابة kitaabe — writing")
        VCOBodyText("اشتغل ishtaghal (worked) → شغل shughl — work")
        VCOBodyText("عمل 3imil (did) → عمل 3amal — work / deed")
        Spacer(modifier = Modifier.height(8.dp))
        VCOSubHeader("How it's used")
        VCOBodyText("The maSdar often translates as English \"-ing\" or as a plain noun:")
        VCOBodyText("• بحبّ السفر — b7ibb is-safar — I love traveling")
        VCOBodyText("• الأكل هون طيّب — il-akl hoon Tayyeb — the food here is good")
        VCOBodyText("• النوم مهمّ — in-noom muhimm — sleep is important")
        Spacer(modifier = Modifier.height(8.dp))
        VCOSubHeader("Other nouns from the same root")
        VCOBodyText("The same root gives a whole family of words. Here are two common patterns:")
        VCOBodyText("• ma- + root = the place: مكتب maktab (office / desk), مطبخ maTbakh (kitchen), مدرسة madrase (school), مكتبة maktabe (library / bookstore)")
        VCOBodyText("• -aa-i- = the doer: كاتب kaatib (writer), لاعب laa3ib (player)")
        VCOBodyText("• doubled middle letter + aa = the profession: طبّاخ Tabbaakh (cook), خبّاز khabbaaz (baker)")
        VCOBodyText("• And more: كتاب ktaab (book), مدرّس mudarris (teacher), مشروب mashruub (a drink)")
        Spacer(modifier = Modifier.height(8.dp))
        VCONote("For basic verbs like akal, shirib and naam the maSdar pattern isn't predictable (akl, shurb, noom), so learn it together with the verb. For the longer derived verbs it is regular. In MSA, saafar → musaafara and ishtaghal → ishtighaal; in everyday speech people often just use safar and shughl.")

        VCOSectionHeader("Summary")
        VCOBodyText("Present / want to / past / future:")
        Spacer(modifier = Modifier.height(4.dp))
        VCOBodyText("I — baakol / biddi aakol / akalt / ra7 aakol")
        VCOBodyText("you (m) — btaakol / biddak taakol / akalt / ra7 taakol")
        VCOBodyText("you (f) — btaakli / biddek taakli / akalti / ra7 taakli")
        VCOBodyText("he — byaakol / biddo yaakol / akal / ra7 yaakol")
        VCOBodyText("she — btaakol / bidha taakol / akalat / ra7 taakol")
        VCOBodyText("we — bnaakol / bidna naakol / akalna / ra7 naakol")
        VCOBodyText("you (pl) — btaaklu / biddkum taaklu / akaltu / ra7 taaklu")
        VCOBodyText("they — byaaklu / biddhum yaaklu / akalu / ra7 yaaklu")
        Spacer(modifier = Modifier.height(4.dp))
        VCOBodyText("Imperative: kul (m) / kuli (f) / kulu (pl) — don't: la taakol / la taakli / la taaklu")
        VCOBodyText("Noun (maSdar): akl — food / eating")
        Spacer(modifier = Modifier.height(8.dp))
        VCONote("The pattern: present = b- + verb. Drop the b- and you get the form used after biddi, ra7, laazim and mumkin, and with la for \"don't\". Drop the t- from the \"you\" form as well and you get the imperative (tishrab → ishrab). The past is a separate set built with endings. The maSdar is the noun for the action itself, built from the same root.")

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun VCOSectionHeader(text: String) {
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
private fun VCOSubHeader(text: String) {
    Spacer(modifier = Modifier.height(8.dp))
    Text(
        text = text,
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.SemiBold
    )
    Spacer(modifier = Modifier.height(4.dp))
}

@Composable
private fun VCOBodyText(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(vertical = 2.dp)
    )
}

@Composable
private fun VCONote(text: String) {
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
}
