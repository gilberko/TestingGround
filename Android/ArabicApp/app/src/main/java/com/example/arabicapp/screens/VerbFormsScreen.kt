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
fun VerbFormsScreen() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        VFNote("Arabic verbs are built like Hebrew verbs: a root of (usually) 3 letters is poured into a pattern. Each pattern is a \"verb form\". Palestinian forms first, Lebanese/Syrian where they differ. In each pair below the first verb is the past (\"he did\") and the second is the present (\"he does\").")

        VFSectionHeader("1. What Are Verb Forms? Are They Like בניינים?")
        VFBody("Yes, they are the same idea as the Hebrew בניינים. The root carries the basic meaning, and the pattern (the form) adds a shade of meaning: doing something to someone else, doing it to yourself, doing it together, being done (passive), and so on.")
        VFBody("In Arabic grammar a pattern is called وزن wazn (pl. أوزان awzaan), \"weight\". Like Hebrew, which names the בניינים after פ-ע-ל (פָּעַל, פִּעֵל, הִתְפַּעֵל), Arabic names the patterns after the root ف-ع-ل f-3-l: فعل fa3al, فعّل fa33al, تفعّل tfa33al…")
        VFBody("Western textbooks number them with Roman numerals, Form I to Form X. Forms I, II, III, V, VI, VII, VIII and X are common in speech. Form IV is mostly MSA, and Form IX is only for colors.")
        VFExample("root ك-س-ر k-s-r (breaking): kasar = he broke · kassar = he smashed (to pieces) · inkasar = it got broken · tkassar = it got smashed")

        VFSectionHeader("2. The Forms Side by Side With Hebrew")
        VFWord("Form I — فعل fa3al — basic verb")
        VFInfo("≈ פָּעַל (kal): katab كتب = he wrote, like כָּתַב")
        VFWord("Form II — فعّل fa33al — doubled middle letter: intensive or causative (making someone do it)")
        VFInfo("≈ פִּעֵל (also with a doubled middle letter, the dagesh): 3allam = he taught, like לִמֵּד")
        VFWord("Form III — فاعل faa3al — long aa after the first letter: doing something with or toward someone")
        VFInfo("no real Hebrew equivalent: saa3ad = he helped, 7aaka = he talked (with someone)")
        VFWord("Form IV — أفعل af3al — a- prefix: causative")
        VFInfo("≈ הִפְעִיל: mostly MSA (aflas = went bankrupt, أفلس, is heard in speech). In dialect Form II usually does this job instead.")
        VFWord("Form V — تفعّل tfa33al — t- + Form II: reflexive or passive of Form II")
        VFInfo("≈ הִתְפַּעֵל: t3allam = he learned (\"taught himself\"), like הִתְלַמֵּד")
        VFWord("Form VI — تفاعل tfaa3al — t- + Form III: doing it to each other")
        VFInfo("≈ the reciprocal use of הִתְפַּעֵל: t3aawanu = they cooperated, like הִתְכַּתְּבוּ (they wrote to each other)")
        VFWord("Form VII — انفعل infa3al — in- prefix: passive of Form I (\"got done\")")
        VFInfo("≈ נִפְעַל (also an n- prefix!): inkasar = it got broken, like נִשְׁבַּר")
        VFWord("Form VIII — افتعل ifta3al — t inserted after the first letter")
        VFInfo("no single Hebrew equivalent: ishtaghal = he worked, intazar = he waited")
        VFWord("Form IX — افعلّ if3all — colors and physical defects only: becoming that color")
        VFInfo("i7marr = turned red (blushed), iSfarr = turned yellow / pale. Rare.")
        VFWord("Form X — استفعل istaf3al — ista- prefix: asking for, or considering something to be")
        VFInfo("no Hebrew equivalent: ista3mal = he used, ista2jar = he rented (asked for rent)")
        VFSmall("The parallels are close but not exact: a Hebrew verb and its Arabic cousin don't always land in the same form, and meanings drift. Use the table as a memory aid, not a rule.")

        VFSectionHeader("3. Each Common Form With Examples")

        VFSubHeader("Form I — فعل fa3al (the basic verb)")
        VFBody("Vowels vary from verb to verb and must be learned (katab, shirib, fihim). The present has b- + y + the root: byuktub, byishrab.")
        VFWord("كتب katab / بيكتب byuktub — to write")
        VFWord("درس daras / بيدرس byudrus — to study")
        VFWord("فهم fihim / بيفهم byifham — to understand")
        VFExample("katabt risaale la-sa7bi — كتبت رسالة لصاحبي — I wrote a letter to my friend.")

        VFSubHeader("Form II — فعّل fa33al (doubled middle letter)")
        VFBody("Two main jobs: 1) making someone do the Form I action (causative), 2) doing it intensively. The present is bi- + fa33il: bi3allim, binaDDif.")
        VFWord("علّم 3allam / بيعلّم bi3allim — to teach (Form I 3ilim = to know → \"make someone know\")")
        VFWord("فهّم fahham / بيفهّم bifahhim — to explain (Form I fihim = understand → \"make understand\")")
        VFWord("نضّف naDDaf / بينضّف binaDDif — to clean")
        VFWord("صلّح Salla7 / بيصلّح biSalli7 — to fix, to repair")
        VFWord("طلّع Talla3 / بيطلّع biTalli3 — to take out (Form I Tili3 = to go out)")
        VFWord("كسّر kassar / بيكسّر bikassir — to smash, to break into pieces (intensive of kasar)")
        VFExample("el-mu3allme bit3allim el-wlaad 3arabi — المعلمة بتعلّم الولاد عربي — The teacher (f) teaches the kids Arabic.")

        VFSubHeader("Form III — فاعل faa3al (long aa: with or toward someone)")
        VFBody("Often an action that involves another person. Present: bi- + faa3il.")
        VFWord("ساعد saa3ad / بيساعد bisaa3id — to help")
        VFWord("حاكى 7aaka / بيحاكي bi7aaki — to talk with someone (Form I 7aka = to speak)")
        VFWord("سافر saafar / بيسافر bisaafir — to travel")
        VFWord("حاول 7aawal / بيحاول bi7aawil — to try")
        VFExample("mumkin tsaa3idni? — ممكن تساعدني؟ — Can you help me? (to m)")

        VFSubHeader("Form V — تفعّل tfa33al (t- + Form II: to yourself, or got done)")
        VFBody("The t- turns the Form II action back onto the subject, exactly like הִתְ- in Hebrew. Present: byit- + fa33al.")
        VFWord("تعلّم t3allam / بيتعلّم byit3allam — to learn (\"teach yourself\"; Form II 3allam = teach)")
        VFWord("تجوّز tjawwaz / بيتجوّز byitjawwaz — to get married (Form II jawwaz = marry someone off); also تزوّج tzawwaj")
        VFWord("تأخّر t2akhkhar / بيتأخّر byit2akhkhar — to be late (Form II akhkhar = to delay something)")
        VFWord("تفرّج tfarraj / بيتفرّج byitfarraj — to watch (TV, a show)")
        VFExample("3am bit3allam 3arabi — عم بتعلّم عربي — I'm learning Arabic. (= אני לומד/מתלמד)")

        VFSubHeader("Form VI — تفاعل tfaa3al (t- + Form III: to each other)")
        VFBody("Mostly used in the plural, because it takes two. Present: byit- + faa3al.")
        VFWord("تعاونوا t3aawanu / بيتعاونوا byit3aawanu — they cooperated")
        VFWord("تقابلوا t2aabalu / بيتقابلوا byit2aabalu — they met each other")
        VFWord("تفاهموا tfaahamu / بيتفاهموا byitfaahamu — they understood each other, came to an agreement")
        VFExample("t2aabalna bil-balad — تقابلنا بالبلد — We met (each other) in town.")

        VFSubHeader("Form VII — انفعل infa3al (in-: got done)")
        VFBody("The everyday passive of Form I. Present: byin- + fi3il. Details: Learning → Passive Voice.")
        VFWord("انكسر inkasar / بينكسر byinkisir — to get broken (kasar = to break)")
        VFWord("انباع inbaa3 / بينباع byinbaa3 — to get sold (baa3 = to sell)")
        VFWord("انفتح infata7 / بينفتح byinfiti7 — to get opened, to open by itself (fata7 = to open)")
        VFExample("el-kaase inkasarat — الكاسة انكسرت — The glass got broken.")

        VFSubHeader("Form VIII — افتعل ifta3al (t after the first letter)")
        VFBody("Very common, with many meanings: often reflexive or \"for yourself\". Present: byif- + ti3il.")
        VFWord("اشتغل ishtaghal / بيشتغل byishtighil — to work (shughl = work)")
        VFWord("انتظر intazar / بينتظر byintizir — to wait")
        VFWord("اجتمع ijtama3 / بيجتمع byijtimi3 — to meet, to gather (jama3 = to collect)")
        VFWord("احترم i7taram / بيحترم byi7tirim — to respect")
        VFExample("ishtaghalt 3ashr saa3aat el-yoom — اشتغلت عشر ساعات اليوم — I worked ten hours today.")

        VFSubHeader("Form X — استفعل istaf3al (ista-: ask for, consider)")
        VFBody("Present: byista- + f3il.")
        VFWord("استعمل ista3mal / بيستعمل byista3mil — to use (3amal = to do)")
        VFWord("استأجر ista2jar / بيستأجر byista2jir — to rent (as a tenant), \"to ask for rent\" (ajjar = to rent out)")
        VFWord("استعجل ista3jal / بيستعجل byista3jil — to hurry (3ajal = haste)")
        VFWord("استنّى istanna / بيستنّى byistanna — to wait (everyday Levantine for intazar)")
        VFExample("istanna shwayy! — استنّى شوي — Wait a bit! (to m)")

        VFSectionHeader("4. One Root, Many Forms")
        VFSubHeader("ع-ل-م 3-l-m (knowing) — compare Hebrew ל-מ-ד")
        VFWord("3ilim — he knew / found out (Form I) — compare לָמַד")
        VFWord("3allam — he taught (Form II) — compare לִמֵּד")
        VFWord("t3allam — he learned (Form V) — compare הִתְלַמֵּד")
        VFWord("m3allim / mu3allim — teacher (Form II participle) — compare מְלַמֵּד")
        VFWord("3ilm — science, knowledge · 3aalim — scientist · ma3luume — a piece of information")
        VFSubHeader("ك-س-ر k-s-r (breaking)")
        VFWord("kasar — he broke (I) · kassar — he smashed (II) · inkasar — it got broken (VII) · tkassar — it got smashed (V)")
        VFWord("maksuur — broken (Form I passive participle) · mkassar — smashed")
        VFSubHeader("ف-ه-م f-h-m (understanding)")
        VFWord("fihim — he understood (I) · fahham — he explained (II) · tfaahamu — they understood each other (VI) · istafham — he asked about something, inquired (X, formal)")

        VFSectionHeader("5. How to Tell Active From Passive")
        VFBody("Arabic has no single \"passive form\" in speech. Look for these signs:")
        VFSubHeader("1) The in- prefix (Form VII) = got done")
        VFWord("kasar (he broke it) → inkasar (it got broken)")
        VFWord("fata7 (he opened it) → infata7 (it got opened)")
        VFSmall("Like Hebrew נִ- (שָׁבַר → נִשְׁבַּר). If a Form I verb has an in- twin, the in- one is passive.")
        VFSubHeader("2) The t- prefix (Forms V and VI) = done to itself / got done")
        VFWord("Salla7 (he fixed it) → tSalla7 (it got fixed)")
        VFWord("kassar (he smashed it) → tkassar (it got smashed)")
        VFSmall("Like Hebrew הִתְ-. Careful: t- forms also have active meanings (t3allam = he learned, tfarraj = he watched). Context decides.")
        VFSubHeader("3) \"They did it\" = it was done")
        VFWord("sara2u sayyarti — سرقوا سيارتي — \"they stole my car\" = my car was stolen")
        VFSmall("The most common way of all, like Hebrew גנבו לי את האוטו. No special form needed.")
        VFSubHeader("4) Participles: the doer vs the thing it was done to")
        VFWord("Form I: faa3il = doing (active) vs maf3uul = done (passive)")
        VFInfo("kaatib (writing / writer) vs maktuub (written) — like כּוֹתֵב vs כָּתוּב")
        VFInfo("faati7 (opening, open — of a shop) vs maftuu7 (opened)")
        VFInfo("kaasir (breaking) vs maksuur (broken)")
        VFWord("Forms II–X: m- + present stem; the last vowel is i for active, a for passive")
        VFInfo("m3allim (teacher, one who teaches) vs m3allam (taught, trained) — like מְלַמֵּד vs מְלֻמָּד")
        VFInfo("mista3mil (user) vs mista3mal (used, second-hand)")
        VFInfo("mrattib (one who arranges) vs mrattab (arranged, tidy)")
        VFSmall("In fast speech the i/a difference can be hard to hear; the meaning usually makes it clear.")
        VFSubHeader("5) The MSA passive (kutiba, \"it was written\")")
        VFBody("MSA makes a passive by changing the inside vowels (kataba → kutiba, \"it was written\"). You'll see it in news and signs, but it isn't used in everyday speech. In speech use Form VII or \"they\" instead.")

        VFSectionHeader("Summary")
        VFBody("I fa3al — basic — katab, daras")
        VFBody("II fa33al — make someone do / intensive — 3allam, naDDaf (פִּעֵל)")
        VFBody("III faa3al — with someone — saa3ad, saafar")
        VFBody("IV af3al — causative, mostly MSA (הִפְעִיל)")
        VFBody("V tfa33al — to yourself / passive of II — t3allam (הִתְפַּעֵל)")
        VFBody("VI tfaa3al — each other — t2aabalu")
        VFBody("VII infa3al — passive of I — inkasar (נִפְעַל)")
        VFBody("VIII ifta3al — various — ishtaghal, intazar")
        VFBody("IX if3all — colors — i7marr")
        VFBody("X istaf3al — ask for / consider — ista3mal, ista2jar")
        Spacer(modifier = Modifier.height(8.dp))
        VFNote("Tip: when you meet a new verb, find the 3 root letters and the pattern. If you already know another word from the same root, you can often guess the meaning, just as in Hebrew.")

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun VFSectionHeader(text: String) {
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
private fun VFSubHeader(text: String) {
    Spacer(modifier = Modifier.height(10.dp))
    Text(
        text = fixArabizi(text),
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.SemiBold
    )
    Spacer(modifier = Modifier.height(4.dp))
}

@Composable
private fun VFBody(text: String) {
    Text(
        text = fixArabizi(text),
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(vertical = 2.dp)
    )
}

@Composable
private fun VFWord(text: String) {
    Text(
        text = fixArabizi(text),
        style = MaterialTheme.typography.bodyMedium,
        fontWeight = FontWeight.SemiBold,
        modifier = Modifier.padding(top = 6.dp, bottom = 2.dp)
    )
}

@Composable
private fun VFInfo(text: String) {
    Text(
        text = fixArabizi("    $text"),
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(vertical = 1.dp)
    )
}

@Composable
private fun VFExample(text: String) {
    Text(
        text = fixArabizi("    e.g. $text"),
        style = MaterialTheme.typography.bodyMedium,
        fontStyle = FontStyle.Italic,
        modifier = Modifier.padding(vertical = 1.dp)
    )
}

@Composable
private fun VFSmall(text: String) {
    Text(
        text = fixArabizi("    $text"),
        style = MaterialTheme.typography.bodySmall,
        modifier = Modifier.padding(vertical = 2.dp)
    )
}

@Composable
private fun VFNote(text: String) {
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
