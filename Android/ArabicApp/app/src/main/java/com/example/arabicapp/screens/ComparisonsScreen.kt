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
fun ComparisonsScreen() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        CmpSectionHeader("What We Want To Say")
        CmpBodyText("1. More than: X is bigger / faster / more expensive than Y.")
        CmpBodyText("2. Less than: X is less expensive / less fast than Y.")
        CmpBodyText("3. The most: the biggest, the best, the fastest.")
        CmpBodyText("4. The least: the slowest, the cheapest, the least interesting.")
        Spacer(modifier = Modifier.height(8.dp))
        CmpNote("Checked: this is the right way to split the topic. The interesting part is that Arabic uses ONE word form for both \"bigger\" and \"biggest\" — the af3al form (أفعل, called the elative). Whether it means \"-er\" or \"-est\" depends only on where you put it:\n• akbar MIN… — أكبر من — bigger than…\n• akbar + noun — أكبر بيت — the biggest house\nSo you learn one form and get both.")

        CmpSectionHeader("Step 1 — Building the af3al Form")
        CmpBodyText("Take the 3 root letters of the adjective and put them into the pattern a-_-_-a-_ (a + 1st + 2nd + a + 3rd).")
        CmpSubHeader("Regular adjectives")
        CmpBodyText("big — كبير kbiir → أكبر akbar (bigger / biggest)")
        CmpBodyText("small — زغير zghiir → أزغر azghar")
        CmpBodyText("tall, long — طويل tawiil → أطول atwal")
        CmpBodyText("short — قصير 2asiir → أقصر a2sar")
        CmpBodyText("fast — سريع sarii3 → أسرع asra3")
        CmpBodyText("slow — بطيء bati2 → أبطأ abta2")
        CmpBodyText("cheap — رخيص rkhiis → أرخص arkhas")
        CmpBodyText("new — جديد jdiid → أجدد ajdad")
        CmpBodyText("old (things) — قديم 2adiim → أقدم a2dam")
        CmpBodyText("easy — سهل sahel → أسهل as-hal")
        CmpBodyText("difficult — صعب sa3b → أصعب as3ab")
        CmpBodyText("far — بعيد ba3iid → أبعد ab3ad")
        CmpBodyText("near — قريب 2ariib → أقرب a2rab")
        CmpBodyText("strong — قوي 2awi → أقوى a2wa")
        CmpBodyText("dangerous — خطير khatiir → أخطر akhtar")
        CmpSubHeader("Adjectives ending in a vowel → af3a (ends in -a)")
        CmpBodyText("sweet, nice — حلو 7ilu → أحلى a7la")
        CmpBodyText("expensive — غالي ghaali → أغلى aghla")
        CmpBodyText("high — عالي 3aali → أعلى a3la")
        CmpBodyText("rich — غني ghani → أغنى aghna")
        CmpSubHeader("Doubled roots → af3all (last letter doubled)")
        CmpBodyText("light (weight) — خفيف khafiif → أخفّ akhaff")
        CmpBodyText("few, little — قليل 2aliil → أقلّ a2all (= less / least)")
        CmpBodyText("strong, intense — شديد shadiid → أشدّ ashadd")
        CmpSubHeader("Irregular — learn these by heart")
        CmpBodyText("good — منيح mnii7 → أحسن a7san (better / best)  — a completely different root!")
        CmpBodyText("good, preferable — → أفضل afdal (better / preferable, slightly more formal)")
        CmpBodyText("much, many — كتير ktiir → أكتر aktar (more / most)")
        CmpBodyText("bad — مش منيح mish mnii7 / عاطل 3aatil → أسوأ aswa2 (worse / worst)")
        Spacer(modifier = Modifier.height(8.dp))
        CmpNote("The af3al form does NOT change for gender or number: \"el-bint akbar min akhuuha\" (the girl is older than her brother), \"el-banaat akbar min el-wlaad\" — always akbar. MSA has feminine forms like kubra (الكبرى), but you'll only see them in names and fixed phrases.")

        CmpSectionHeader("Step 2 — More Than: af3al + min")
        CmpBodyText("X + af3al + من min + Y")
        CmpExample("huwwe atwal minni", "هو أطول منّي", "He is taller than me.")
        CmpExample("hai es-sayyara asra3 min hadiik", "هاي السيارة أسرع من هديك", "This car is faster than that one.")
        CmpExample("el-2uds a2dam min tal abiib", "القدس أقدم من تل أبيب", "Jerusalem is older than Tel Aviv.")
        CmpExample("el-akel hon arkhas min 3and-kum", "الأكل هون أرخص من عندكم", "The food here is cheaper than at your place.")
        CmpExample("el-3arabi as3ab min el-ingliizi", "العربي أصعب من الإنجليزي", "Arabic is harder than English.")
        CmpExample("ukhti azghar minni b-sanatein", "أختي أزغر منّي بسنتين", "My sister is two years younger than me.")
        CmpExample("el-2atar a7san min el-bas", "القطار أحسن من الباص", "The train is better than the bus.")
        CmpExample("el-beit el-jdiid a2rab 3al-shughl", "البيت الجديد أقرب عالشغل", "The new house is closer to work.")
        CmpSubHeader("Much more / a bit more")
        CmpExample("asra3 b-ktiir", "أسرع بكتير", "much faster")
        CmpExample("aghla shwai", "أغلى شوي", "a bit more expensive")
        CmpExample("hada a7san b-miit marra", "هاد أحسن بمية مرة", "This is a hundred times better.")

        CmpSectionHeader("Step 3 — No af3al Form? Use aktar (more)")
        CmpBodyText("Some adjectives don't have an af3al form — colors, participles (words starting with m- like mashghuul, mitwaddi3), long or foreign words. Then add أكتر aktar \"more\". In speech it usually comes AFTER the adjective.")
        CmpExample("ana mashghuul aktar minnak", "أنا مشغول أكتر منّك", "I'm busier than you.")
        CmpExample("hada el-2amiis azra2 aktar", "هاد القميص أزرق أكتر", "This shirt is bluer / more blue.")
        CmpExample("hiyye mrattabe aktar min akhuuha", "هي مرتّبة أكتر من أخوها", "She is more organized than her brother.")
        CmpExample("el-film kaan mumti3 aktar min el-ktaab", "الفيلم كان ممتع أكتر من الكتاب", "The film was more interesting than the book.")
        CmpExample("el-code hada modern aktar", "الكود هاد modern أكتر", "This code is more modern.")
        CmpSubHeader("More of something (nouns, verbs)")
        CmpExample("biddi wa2t aktar", "بدّي وقت أكتر", "I want more time.")
        CmpExample("fi naas aktar min mbaari7", "في ناس أكتر من مبارح", "There are more people than yesterday.")
        CmpExample("bishtghil aktar minni", "بيشتغل أكتر منّي", "He works more than me.")

        CmpSectionHeader("Step 4 — Less Than: a2all")
        CmpBodyText("أقلّ a2all = less / fewer. Put it before a noun, or after a verb.")
        CmpExample("hada a2all ghala min hadaak", "هاد أقلّ غلا من هداك", "This is less expensive than that one.")
        CmpExample("fi naas a2all el-yom", "في ناس أقلّ اليوم", "There are fewer people today.")
        CmpExample("laazem tookol a2all sukkar", "لازم توكل أقلّ سكر", "You should eat less sugar.")
        CmpExample("bi7ki a2all min akhuu", "بيحكي أقلّ من أخوه", "He talks less than his brother.")
        CmpNote("In everyday speech people usually prefer the opposite adjective instead of \"less\": instead of \"less expensive\" they say arkhas (cheaper); instead of \"less fast\" they say abta2 (slower). Both are correct — the opposite word just sounds more natural.")
        CmpSubHeader("Not as … as: mish 2add")
        CmpExample("huwwe mish tawiil 2addi", "هو مش طويل قدّي", "He isn't as tall as me.")
        CmpExample("el-akel hon mish taybe 2add akel ummi", "الأكل هون مش طيّب قدّ أكل إمّي", "The food here isn't as good as my mother's.")

        CmpSectionHeader("Step 5 — Equal: 2add / zayy / nafs")
        CmpExample("huwwe 2addi", "هو قدّي", "He's my size / my age (as big as me).")
        CmpExample("tawiile zayy ukhta", "طويلة زيّ أختها", "She's as tall as her sister.")
        CmpExample("et-tnein nafs es-si3r", "التنين نفس السعر", "Both are the same price.")
        CmpExample("zayy ba3d", "زيّ بعض", "the same / alike")

        CmpSectionHeader("Step 6 — The Most: Superlative")
        CmpSubHeader("A. af3al + noun (no \"el-\")")
        CmpBodyText("Put the af3al form BEFORE an indefinite noun. This is the most common way.")
        CmpExample("a7san mat3am bil-balad", "أحسن مطعم بالبلد", "the best restaurant in town")
        CmpExample("asra3 sayyara bil-3aalam", "أسرع سيارة بالعالم", "the fastest car in the world")
        CmpExample("akbar ghalta 3miltha", "أكبر غلطة عملتها", "the biggest mistake I made")
        CmpExample("a7la yom b-7ayaati", "أحلى يوم بحياتي", "the most beautiful day of my life")
        CmpExample("arkhas ishi bil-menu", "أرخص إشي بالمنيو", "the cheapest thing on the menu")
        CmpSubHeader("B. el- + af3al (\"the …-est one\")")
        CmpExample("huwwe el-akbar", "هو الأكبر", "He's the oldest (one).")
        CmpExample("hai el-a7san", "هاي الأحسن", "This one is the best.")
        CmpExample("miin el-asra3?", "مين الأسرع؟", "Who is the fastest?")
        CmpSubHeader("C. af3al + wa7ad / ishi (\"the …-est one / thing\")")
        CmpExample("huwwe asra3 wa7ad fiihom", "هو أسرع واحد فيهم", "He's the fastest of them.")
        CmpExample("hiyye ashtar wa7de bis-saff", "هي أشطر وحدة بالصف", "She's the smartest in the class.")
        CmpExample("a2wa ishi 3indhom el-akel", "أقوى إشي عندهم الأكل", "Their strongest point is the food.")
        CmpSubHeader("D. With aktar for words without af3al")
        CmpExample("aktar wa7ad mashghuul bil-maktab", "أكتر واحد مشغول بالمكتب", "the busiest person in the office")
        CmpExample("aktar ishi bi7ebbo", "أكتر إشي بحبّه", "the thing I love most")

        CmpSectionHeader("Step 7 — The Least / The Slowest")
        CmpExample("abta2 sayyara", "أبطأ سيارة", "the slowest car")
        CmpExample("huwwe abta2 wa7ad bil-fari2", "هو أبطأ واحد بالفريق", "He's the slowest on the team.")
        CmpExample("a2all wa7ad bi7ki", "أقلّ واحد بيحكي", "the one who talks the least")
        CmpExample("a2all ishi mumken", "أقلّ إشي ممكن", "the least possible / the minimum")
        CmpExample("3al-a2all", "عالأقلّ", "at least")
        CmpExample("a2all film 3ajabni", "أقلّ فيلم عجبني", "the film I liked the least")
        CmpExample("aswa2 ishi", "أسوأ إشي", "the worst thing")

        CmpSectionHeader("Bonus — The More …, The More …")
        CmpBodyText("كل ما … كل ما … — kull ma …, kull ma …")
        CmpExample("kull ma tidros aktar, kull ma btifham aktar", "كل ما تدرس أكتر، كل ما بتفهم أكتر", "The more you study, the more you understand.")
        CmpExample("kull ma kibir, kull ma saar ahda", "كل ما كبر، كل ما صار أهدى", "The older he got, the calmer he became.")

        CmpSectionHeader("Lots of Examples")
        CmpExample("ana akbar min akhuuy b-tlat sniin", "أنا أكبر من أخوي بتلات سنين", "I'm three years older than my brother.")
        CmpExample("esh-shita hal-sane aktar min es-sane elli 2abel", "الشتا هالسنة أكتر من السنة اللي قبل", "There's more rain this year than last year.")
        CmpExample("el-beit hada awsa3 b-ktiir", "البيت هاد أوسع بكتير", "This house is much more spacious.")
        CmpExample("el-2ahwe hon a7la", "القهوة هون أحلى", "The coffee here is nicer.")
        CmpExample("ruu7 bil-2atar, a2sar w-arkhas", "روح بالقطار، أقصر وأرخص", "Go by train — it's shorter and cheaper.")
        CmpExample("a3la jabal bil-blaad", "أعلى جبل بالبلاد", "the highest mountain in the country")
        CmpExample("hada a7san 2araar akhadto", "هاد أحسن قرار أخدته", "This is the best decision I've made.")
        CmpExample("shu el-afdal, el-bas willa el-2atar?", "شو الأفضل، الباص ولّا القطار؟", "What's better, the bus or the train?")
        CmpExample("el-laptop el-jdiid akhaff w-asra3", "اللابتوب الجديد أخفّ وأسرع", "The new laptop is lighter and faster.")
        CmpExample("C++ as3ab min Python bas asra3", "C++ أصعب من Python بس أسرع", "C++ is harder than Python, but faster.")
        CmpExample("hada akhtar bug shufto", "هاد أخطر bug شفته", "This is the most dangerous bug I've seen.")
        CmpExample("el-spinlock asra3 min el-mutex la-shaghlaat 2asiire", "ال-spinlock أسرع من ال-mutex لشغلات قصيرة", "A spinlock is faster than a mutex for short tasks.")
        CmpExample("miin a7san la3iib bil-fari2?", "مين أحسن لاعب بالفريق؟", "Who is the best player on the team?")
        CmpExample("hiyye azka bint shufitha", "هي أذكى بنت شفتها", "She's the smartest girl I've seen.")
        CmpExample("ma fi a7la min hek", "ما في أحلى من هيك", "Nothing's better than this.")
        CmpExample("a7san min wala ishi", "أحسن من ولا إشي", "Better than nothing.")
        CmpExample("kul a2all w-imshi aktar", "كول أقلّ وامشي أكتر", "Eat less and walk more.")

        CmpSectionHeader("For Hebrew Speakers")
        CmpBodyText("יותר … מ… (yoter … mi-) ≈ af3al + min, or adjective + aktar + min")
        CmpBodyText("פחות (pachot) ≈ a2all")
        CmpBodyText("הכי (hachi) — Arabic has no separate word for it. Instead: af3al + noun (asra3 sayyara = הרכב הכי מהיר), or el-af3al (el-asra3 = הכי מהיר).")
        CmpBodyText("כמו (kmo) ≈ zayy · באותו גודל ≈ 2add")

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun CmpSectionHeader(text: String) {
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
private fun CmpSubHeader(text: String) {
    Spacer(modifier = Modifier.height(8.dp))
    Text(
        text = fixArabizi(text),
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.SemiBold
    )
    Spacer(modifier = Modifier.height(4.dp))
}

@Composable
private fun CmpBodyText(text: String) {
    Text(
        text = fixArabizi(text),
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(vertical = 2.dp)
    )
}

@Composable
private fun CmpExample(translit: String, ar: String, en: String) {
    Column(modifier = Modifier.padding(vertical = 3.dp)) {
        Text(text = fixArabizi("$ar — $translit"), style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.SemiBold)
        Text(text = fixArabizi("    $en"), style = MaterialTheme.typography.bodyMedium)
    }
}

@Composable
private fun CmpNote(text: String) {
    Spacer(modifier = Modifier.height(4.dp))
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
