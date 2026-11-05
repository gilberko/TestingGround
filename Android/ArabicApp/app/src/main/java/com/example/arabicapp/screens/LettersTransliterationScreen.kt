package com.example.arabicapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

private data class LTRow(val ar: String, val name: String, val arabizi: String, val howToSay: String)

private val ltLetters = listOf(
    LTRow("ا", "alif", "a / aa / e / i", "Carrier of a long \"aa\" (as in \"father\"). At the start of a word it just carries the vowel: ana, inta, umm."),
    LTRow("ء", "hamza", "2 / '", "Glottal stop — the catch in the throat in \"uh-oh\". Written 2: 2ana, so2al (question)."),
    LTRow("ب", "ba", "b", "Like English b."),
    LTRow("ت", "ta", "t", "Like English t, with the tongue against the teeth."),
    LTRow("ث", "tha", "th / t / s", "Classically \"th\" as in \"think\". Palestinians say t or s: tlate (three), tum (garlic)."),
    LTRow("ج", "jim", "j / g", "Like j in \"jam\" in Palestinian. Egyptians say g, so you may see \"gamil\" online."),
    LTRow("ح", "7a", "7 / h", "A strong, breathy h from deep in the throat — like fogging up a mirror. 7abibi, 7ummus, mar7aba."),
    LTRow("خ", "kha", "kh / 5 / 7'", "Like \"ch\" in German \"Bach\" or Hebrew כ/ח as most Israelis say it. khalas / 5alas / 7'alas."),
    LTRow("د", "dal", "d", "Like English d."),
    LTRow("ذ", "dhal", "dh / th / d / z", "Classically \"th\" as in \"this\". Palestinians say d or z: dahab (gold), hada (this; classical haadha)."),
    LTRow("ر", "ra", "r", "A rolled / tapped r, like Spanish or Italian r."),
    LTRow("ز", "zay", "z", "Like English z."),
    LTRow("س", "sin", "s", "Like English s."),
    LTRow("ش", "shin", "sh / ch", "Like \"sh\" in \"shoe\". French-influenced writers sometimes use ch: chou = shu (what)."),
    LTRow("ص", "sad", "s / 9", "A heavy, emphatic s — tongue low, mouth \"full\", nearby vowels sound darker. 9 is mostly Gulf usage."),
    LTRow("ض", "dad", "d / 9'", "A heavy, emphatic d. Usually just written d."),
    LTRow("ط", "ta (emphatic)", "t / 6", "A heavy, emphatic t. 6 is mostly Gulf usage; Palestinians usually just write t."),
    LTRow("ظ", "za (emphatic)", "z / 6' / dh", "A heavy, emphatic z (classically a heavy \"th\"). Palestinians say a heavy z or d: zarif (nice), duhr (noon)."),
    LTRow("ع", "3ein", "3", "No English equivalent — a squeeze of the throat muscles, like the start of a gag, with voice. 3arabi, ma3, 3am."),
    LTRow("غ", "ghein", "gh / 3'", "A gargled r, like French \"r\" in \"Paris\" or Hebrew ר. ghali (expensive), shughl (work)."),
    LTRow("ف", "fa", "f", "Like English f."),
    LTRow("ق", "2af", "2 / q / 8 / k / g", "Classically a deep k from the back of the throat (q). In Palestinian cities it becomes a glottal stop (2): 2ahwe (coffee), 2alb (heart). Villagers say k (kahwe), Bedouin say g (gahwe). 8 is rare."),
    LTRow("ك", "kaf", "k", "Like English k."),
    LTRow("ل", "lam", "l", "Like English l."),
    LTRow("م", "mim", "m", "Like English m."),
    LTRow("ن", "nun", "n", "Like English n."),
    LTRow("ه", "ha", "h", "A light English h, as in \"hat\". Even at the end of a word it is breathed: wijh (face)."),
    LTRow("و", "waw", "w / o / u / oo / ou", "As a consonant: w (walad). As a vowel: long u or o (shu, yom). French-style writers use ou."),
    LTRow("ي", "ya", "y / i / ee / e / ei", "As a consonant: y (yalla). As a vowel: long i or e (kifak, beit)."),
    LTRow("ة", "ta marbuta", "a / e / eh", "The feminine ending. In Palestinian usually -e (madrase = school, 2ahwe = coffee), but -a after throat and heavy letters (sa3a = hour, salata = salad)."),
    LTRow("ى", "alif maqsura", "a", "Looks like ي without dots; sounds like a final \"a\": 3ala (on), 7atta (until)."),
)

@Composable
fun LettersTransliterationScreen() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        LTSectionHeader("What Is Arabizi?")
        LTBodyText("When Arabs text, chat, or post online in Latin letters, they use an informal system called Arabizi (عربيزي — 3arabizi, from 3arabi + inglizi), also known as \"Franco-Arab\" or the \"chat alphabet\".")
        Spacer(modifier = Modifier.height(8.dp))
        LTBodyText("It grew up in the 1990s–2000s when phones and computers had no Arabic keyboard. Most Arabic sounds map to an English letter, but a few have no English equivalent — for those, people use digits that look like the Arabic letter:")
        Spacer(modifier = Modifier.height(8.dp))
        LTBodyText("• 2 looks like ء (hamza)")
        LTBodyText("• 3 looks like a mirrored ع (3ein)")
        LTBodyText("• 7 looks like ح (7a)")
        Spacer(modifier = Modifier.height(8.dp))
        LTBodyText("There is no official standard — spellings vary from person to person and country to country. This screen shows all the common options, and the last section shows the single spelling this app uses everywhere.")

        LTSectionHeader("The Number Letters")
        LTBodyText("Common everywhere, including Palestine:")
        LTBodyText("• 2 = ء hamza, and also ق in Palestinian city speech (glottal stop) — 2ahwe = coffee")
        LTBodyText("• 3 = ع 3ein — 3arabi = Arabic")
        LTBodyText("• 7 = ح 7a — 7abibi = my dear")
        Spacer(modifier = Modifier.height(8.dp))
        LTBodyText("Variants with an apostrophe (= \"the dotted version\"):")
        LTBodyText("• 7' = خ (ح with a dot) — 7'alas = enough / done")
        LTBodyText("• 3' = غ (ع with a dot) — 3'ali = expensive")
        Spacer(modifier = Modifier.height(8.dp))
        LTBodyText("Less common in Palestine, more common in the Gulf:")
        LTBodyText("• 5 = خ — 5alas")
        LTBodyText("• 6 = ط — 6ayyara (plane)")
        LTBodyText("• 6' = ظ")
        LTBodyText("• 9 = ص — 9aba7 (morning)")
        LTBodyText("• 9' = ض")
        LTBodyText("• 8 = ق (when pronounced q)")

        LTSectionHeader("Every Letter and Its Options")
        LTBodyText("Arabic letter — name — Latin options — how to pronounce it:")
        Spacer(modifier = Modifier.height(8.dp))
        ltLetters.forEach { row ->
            LTLetterCard(row)
        }

        LTSectionHeader("Vowels in Arabizi")
        LTBodyText("Arabic writing mostly leaves out short vowels, but Arabizi writes them in, as they are pronounced:")
        LTBodyText("• Short vowels: a, i, u (and in Palestinian also e and o) — kalb (dog), 2ibn (son), 3umr (age)")
        LTBodyText("• Long vowels: often doubled to be clear — aa, ee / ii, oo / uu — or just written single: baab or bab (door), kbiir or kbir (big)")
        LTBodyText("• Doubled consonant (shadda ّ): write the letter twice — 7ammam (bathroom), sitt (lady)")
        LTBodyText("• \"ei\" and \"o\" for the Palestinian diphthongs: beit (house), yom (day)")

        LTSectionHeader("Worked Examples")
        LTBodyText("مرحبا — mar7aba — hello")
        LTBodyText("حبيبي — 7abibi — my dear (to a man)")
        LTBodyText("كيفك؟ — kifak? — how are you? (to a man)")
        LTBodyText("شو اسمك؟ — shu ismak? — what's your name?")
        LTBodyText("عربي — 3arabi — Arabic")
        LTBodyText("قهوة — 2ahwe — coffee")
        LTBodyText("مع السلامة — ma3 is-salame — goodbye")
        LTBodyText("خلص — khalas / 5alas / 7'alas — enough / done / that's it")
        LTBodyText("غالي — ghali / 3'ali — expensive")
        LTBodyText("إن شاء الله — inshalla / in sha2 allah — God willing")
        LTBodyText("الحمد لله — il-7amdilla — thank God")
        LTBodyText("يلا — yalla — come on / let's go")
        LTBodyText("صباح الخير — saba7 il-kheir — good morning")
        LTBodyText("طيب — tayyib — OK / good")

        LTSectionHeader("This App's Convention")
        LTBodyText("Every transliteration in this app uses one consistent Arabizi spelling:")
        LTBodyText("• 2 = ء and ق (city glottal stop)")
        LTBodyText("• 3 = ع")
        LTBodyText("• 7 = ح")
        LTBodyText("• kh = خ, gh = غ, sh = ش")
        LTBodyText("• The heavy letters ص ض ط ظ are written as plain s, d, t, z")
        LTBodyText("• ث is written t or s, and ذ is written d or z — as Palestinians say them")
        LTBodyText("• The article ال is written il- / el-")
        Spacer(modifier = Modifier.height(8.dp))
        LTBodyText("When you see a number inside a word, it is never a digit — it is a sound.")

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun LTLetterCard(row: LTRow) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = row.ar,
                style = MaterialTheme.typography.displaySmall,
                modifier = Modifier.width(56.dp)
            )
            Column {
                Text(
                    text = "${row.name}  →  ${row.arabizi}",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = row.howToSay,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Composable
private fun LTSectionHeader(text: String) {
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
private fun LTBodyText(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(vertical = 2.dp)
    )
}
