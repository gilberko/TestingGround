package com.example.arabicapp.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

private data class ReadingSentenceWord(
    val arabic: String,
    val arabizi: String,
    val meaning: String,
    val tip: String? = null
)

private data class ReadingSentenceEntry(
    val arabic: String,
    val arabizi: String,
    val english: String,
    val region: String,
    val literal: String? = null,
    val note: String? = null,
    val words: List<ReadingSentenceWord>
)

private fun sw(arabic: String, arabizi: String, meaning: String, tip: String? = null) =
    ReadingSentenceWord(arabic, arabizi, meaning, tip)

private val READING_SENTENCES = listOf(
    ReadingSentenceEntry(
        "مرحبا، كيفك؟", "mar7aba, kiifak?", "Hello, how are you? (to a man)", REGION_LEV,
        words = listOf(
            sw("مرحبا", "mar7aba", "hello", "The h is ح (7)."),
            sw("كيفك", "kiifak", "how are you (m)", "kiif (how) + -ak (you, m). To a woman the same letters are read kiifik.")
        )
    ),
    ReadingSentenceEntry(
        "شو أخبارك؟", "shu akhbaarak?", "What's new? (to a man)", REGION_LEV,
        literal = "What (are) your news?",
        words = listOf(
            sw("شو", "shu", "what", "و is the long u."),
            sw("أخبارك", "akhbaarak", "your news (m)", "akhbaar (news, plural of khabar) + -ak (your, m). خ = kh. To a woman: akhbaarik.")
        )
    ),
    ReadingSentenceEntry(
        "الحمدلله، منيح", "el-7amdilla, mnii7", "Thank God, I'm good. (said by a man)", REGION_LEV,
        note = "A woman says: el-7amdilla, mnii7a — الحمدلله، منيحة.",
        words = listOf(
            sw("الحمدلله", "el-7amdilla", "thank God", "Often written as one word in chat (formal: الحمد لله). ح is a moon letter, so the l of el- is said. The final ه is silent in speech."),
            sw("منيح", "mnii7", "good / well (m)", "Starts with two consonants: m-nii7.")
        )
    ),
    ReadingSentenceEntry(
        "يسلمو إيديك", "yislamu ideik", "Thank you! — said to someone who cooked or made something for you (to a man)", REGION_LEV,
        literal = "May your hands be safe.",
        words = listOf(
            sw("يسلمو", "yislamu", "may they be safe", "From the root s-l-m, like Hebrew שלום."),
            sw("إيديك", "ideik", "your hands (m)", "iid (hand) → ideik (your hands). To a woman: ideiki — إيديكي.")
        )
    ),
    ReadingSentenceEntry(
        "الله يعطيك العافية", "allah ya3tiik el-3aafye", "Said to someone who is working: 'Bless your efforts / give you strength'", REGION_LEV,
        literal = "May God give you health.",
        note = "The reply: allah y3aafiik — الله يعافيك.",
        words = listOf(
            sw("الله", "allah", "God", "ا + ل + ل + ه. The ll is said full and heavy."),
            sw("يعطيك", "ya3tiik", "give you", "ya3ti (he gives) + -k (you). ط = emphatic t."),
            sw("العافية", "el-3aafye", "health / strength", "ع is a moon letter → el- is said fully. ة = -e.")
        )
    ),
    ReadingSentenceEntry(
        "صحتين", "sa7tein", "Bon appétit! / Enjoy!", REGION_LEV,
        literal = "Two healths.",
        words = listOf(
            sw("صحتين", "sa7tein", "two healths", "sa77a (health) + -tein (the dual, 'two of'). The ة of sa77a turns into ت before the ending.")
        )
    ),
    ReadingSentenceEntry(
        "على قلبك", "3ala 2albak", "The reply to sa7tein (to a man)", REGION_LEV,
        literal = "On your heart.",
        words = listOf(
            sw("على", "3ala", "on", "Ends in ى (alef maqsura) = long a."),
            sw("قلبك", "2albak", "your heart (m)", "2alb (heart, like Hebrew לב) + -ak. ق = 2. To a woman: 2albik.")
        )
    ),
    ReadingSentenceEntry(
        "ما في مشكلة", "ma fi mushkile", "No problem.", REGION_LEV,
        literal = "There isn't a problem.",
        words = listOf(
            sw("ما", "ma", "not"),
            sw("في", "fi", "there is", "Like Hebrew יש; ma fi = אין."),
            sw("مشكلة", "mushkile", "problem", "ة after ل = -e.")
        )
    ),
    ReadingSentenceEntry(
        "بدّي أروح عالبيت", "biddi aruu7 3al-beit", "I want to go home.", REGION_LEV,
        words = listOf(
            sw("بدّي", "biddi", "I want", "Shadda doubles the d."),
            sw("أروح", "aruu7", "(that) I go", "a- = I. No b- on a verb after biddi."),
            sw("عالبيت", "3al-beit", "to the house / home", "Short for على البيت (3ala el-beit): ع + ال + بيت glued together.")
        )
    ),
    ReadingSentenceEntry(
        "شو بدّك تشرب؟", "shu biddak tishrab?", "What do you want to drink? (to a man)", REGION_LEV,
        note = "To a woman: shu biddik tishrabi? — شو بدّك تشربي؟",
        words = listOf(
            sw("شو", "shu", "what"),
            sw("بدّك", "biddak", "you want (m)", "To a woman the same letters are read biddik."),
            sw("تشرب", "tishrab", "(that) you drink", "t- = you (m). ش = sh.")
        )
    ),
    ReadingSentenceEntry(
        "بدّي قهوة بلا سكّر", "biddi 2ahwe bala sukkar", "I want coffee without sugar.", REGION_LEV,
        words = listOf(
            sw("بدّي", "biddi", "I want"),
            sw("قهوة", "2ahwe", "coffee", "ق = 2;  ة = -e."),
            sw("بلا", "bala", "without", "Ends in the lam-alef shape."),
            sw("سكّر", "sukkar", "sugar", "Shadda doubles the k.")
        )
    ),
    ReadingSentenceEntry(
        "وين الحمّام لو سمحت؟", "wein el-7ammaam law sama7t?", "Where is the bathroom, please? (to a man)", REGION_LEV,
        words = listOf(
            sw("وين", "wein", "where", "و is the consonant w;  ي = ei."),
            sw("الحمّام", "el-7ammaam", "the bathroom", "ح is a moon letter → el- is said. Shadda doubles the m."),
            sw("لو", "law", "if"),
            sw("سمحت", "sama7t", "you allowed", "law sama7t = 'if you allowed' = please / excuse me. To a woman: law sama7ti.")
        )
    ),
    ReadingSentenceEntry(
        "ما بعرف، مش متأكد", "ma ba3ref, mish mit2akkid", "I don't know, I'm not sure. (said by a man)", REGION_LEV,
        words = listOf(
            sw("ما", "ma", "not (before verbs)"),
            sw("بعرف", "ba3ref", "I know", "b- + a3ref."),
            sw("مش", "mish", "not (before adjectives)"),
            sw("متأكد", "mit2akkid", "sure (m)", "أ in the middle = glottal stop 2. A woman says mit2akkde — متأكدة.")
        )
    ),
    ReadingSentenceEntry(
        "لسّا ما أكلت", "lissa ma akalt", "I haven't eaten yet.", REGION_PAL_JOR,
        words = listOf(
            sw("لسّا", "lissa", "still / yet"),
            sw("ما", "ma", "not"),
            sw("أكلت", "akalt", "I ate", "a-k-l (eat, like Hebrew אכל) + -t (I).")
        )
    ),
    ReadingSentenceEntry(
        "بعد ما أكلت", "ba3d ma akalt", "I haven't eaten yet.", REGION_LEB_SYR,
        note = "ba3d ma + past can also mean 'after I ate' — the context and the tone tell which one. Lebanese also say akalet.",
        words = listOf(
            sw("بعد", "ba3d", "still / yet"),
            sw("ما", "ma", "not"),
            sw("أكلت", "akalt", "I ate")
        )
    ),
    ReadingSentenceEntry(
        "يلّا نروح", "yalla nruu7", "Come on, let's go!", REGION_LEV,
        words = listOf(
            sw("يلّا", "yalla", "come on", "Shadda doubles the l, then the lam-alef ending."),
            sw("نروح", "nruu7", "we go / let's go", "n- = we. و = long uu.")
        )
    ),
    ReadingSentenceEntry(
        "على راسي", "3ala raasi", "With pleasure! / Gladly! (answer to a request)", REGION_LEV,
        literal = "On my head.",
        words = listOf(
            sw("على", "3ala", "on"),
            sw("راسي", "raasi", "my head", "raas (head, like Hebrew ראש) + -i (my).")
        )
    ),
    ReadingSentenceEntry(
        "يخرب بيتك", "yikhrab beitak", "You rascal! / Wow! (joking; to a man) — can also be a real curse", REGION_LEV,
        literal = "May your house be ruined.",
        words = listOf(
            sw("يخرب", "yikhrab", "may it be ruined", "y- (it) + kh-r-b (ruin, like Hebrew חרב)."),
            sw("بيتك", "beitak", "your house (m)", "beit (house, בית) + -ak. To a woman: beitik.")
        )
    ),
    ReadingSentenceEntry(
        "بكرا في المشمش", "bukra fil-mishmish", "Never — 'when pigs fly'", REGION_LEV,
        literal = "Tomorrow, in apricot (season).",
        note = "Apricot season is very short, so a promise 'for apricot time' will probably never happen. Also used in Egypt.",
        words = listOf(
            sw("بكرا", "bukra", "tomorrow"),
            sw("في", "fi", "in", "Here fi = 'in' (not 'there is'). fi + el- → fil-."),
            sw("المشمش", "el-mishmish", "the apricots", "م is a moon letter → the l of el- is said.")
        )
    ),
    ReadingSentenceEntry(
        "القرد بعين أمّه غزال", "el-2ird b-3ein immo ghazaal", "Every mother thinks her child is beautiful.", REGION_ARAB_WIDE,
        literal = "The monkey, in his mother's eye, is a gazelle.",
        words = listOf(
            sw("القرد", "el-2ird", "the monkey", "ق is a moon letter → el- is said. ق = 2."),
            sw("بعين", "b-3ein", "in the eye of", "b- (in) + 3ein (eye, like Hebrew עין)."),
            sw("أمّه", "immo", "his mother", "imm (mother, אמא) + -o (his). The ه ending is read -o."),
            sw("غزال", "ghazaal", "gazelle", "غ = gh.")
        )
    ),
    ReadingSentenceEntry(
        "إيد وحدة ما بتصفّق", "iid wa7de ma bitsaffi2", "You can't do it alone — it takes two.", REGION_LEV,
        literal = "One hand doesn't clap.",
        note = "You may also hear the Lebanese / Syrian verb za22af (to clap): ma bitza22if — ما بتزقّف.",
        words = listOf(
            sw("إيد", "iid", "hand", "Formal يد (yad), like Hebrew יד."),
            sw("وحدة", "wa7de", "one (f)", "Feminine because iid is feminine. ة = -e."),
            sw("ما", "ma", "not"),
            sw("بتصفّق", "bitsaffi2", "(it) claps", "bi- + t- = she / it (present). Shadda doubles the ف;  ق = 2.")
        )
    ),
    ReadingSentenceEntry(
        "الصبر مفتاح الفرج", "es-sabr miftaa7 el-faraj", "Patience is the key to relief.", REGION_ARAB_WIDE,
        words = listOf(
            sw("الصبر", "es-sabr", "the patience", "ص is a sun letter: the l of el- is NOT said and the s is doubled → es-sabr."),
            sw("مفتاح", "miftaa7", "key", "Like Hebrew מפתח."),
            sw("الفرج", "el-faraj", "the relief", "ف is a moon letter → el- is said.")
        )
    ),
    ReadingSentenceEntry(
        "خلّيها على الله", "khalliiha 3ala allah", "Leave it to God / Let it go.", REGION_LEV,
        words = listOf(
            sw("خلّيها", "khalliiha", "leave it (to a man)", "khalli (leave) + -ha (it, f). Shadda doubles the l. To a woman: khalliiha too (khalli + i)."),
            sw("على", "3ala", "on"),
            sw("الله", "allah", "God", "3ala + allah run together in speech: 3alalla.")
        )
    ),
    ReadingSentenceEntry(
        "كل شي بوقته حلو", "kull shi b-wa2to 7ilu", "Everything is good in its own time.", REGION_LEV,
        words = listOf(
            sw("كل", "kull", "every / all", "Said with a doubled l, but the shadda is usually not written."),
            sw("شي", "shi", "thing"),
            sw("بوقته", "b-wa2to", "in its time", "b- (in) + wa2t (time) + -o (its). ق = 2;  ه = -o."),
            sw("حلو", "7ilu", "nice / sweet", "Lebanese say 7elo.")
        )
    ),
    ReadingSentenceEntry(
        "الله معك", "allah ma3ak", "Goodbye (to a man)", REGION_LEV,
        literal = "God (be) with you.",
        words = listOf(
            sw("الله", "allah", "God"),
            sw("معك", "ma3ak", "with you (m)", "ma3 (with, like Hebrew עם) + -ak. To a woman: ma3ik.")
        )
    )
)

@Composable
fun ReadingSentencesScreen() {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        item {
            ReadingNoteCard(
                "${READING_SENTENCES.size} everyday sentences and sayings. Tap one to read it word by word, letter by letter.\n\n" +
                    "el- (ال, 'the'): before a sun letter (ت ث د ذ ر ز س ش ص ض ط ظ ل ن) the l is silent and the next letter is doubled — es-sabr. Before a moon letter the l is said — el-faraj.\n\n" +
                    "Small words like b- (in), w- (and) and 3a- (on / to) are glued to the next word."
            )
        }
        itemsIndexed(READING_SENTENCES) { index, s ->
            ReadingSentenceCard(index + 1, s)
        }
        item { ReadingSpacerBottom() }
    }
}

@Composable
private fun ReadingSentenceCard(number: Int, s: ReadingSentenceEntry) {
    var expanded by rememberSaveable(s.arabic, s.region) { mutableStateOf(false) }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .clickable { expanded = !expanded }
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("$number.", style = MaterialTheme.typography.labelLarge)
                ReadingRegionTag(s.region)
            }
            ReadingBigArabic(s.arabic, sizeSp = 30)
            Text(
                text = fixArabizi(s.arabizi),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Text(text = s.english, style = MaterialTheme.typography.bodyLarge)
            s.literal?.let {
                Text(
                    text = "Literally: $it",
                    style = MaterialTheme.typography.bodyMedium,
                    fontStyle = FontStyle.Italic
                )
            }
            s.note?.let {
                Text(
                    text = fixArabizi(it),
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
            ReadingExpandHint(expanded, "Tap to read it word by word (${s.words.size} words)")
            if (expanded) {
                s.words.forEachIndexed { i, w -> ReadingSentenceWordBlock(i + 1, w) }
            }
        }
    }
}

@Composable
private fun ReadingSentenceWordBlock(number: Int, w: ReadingSentenceWord) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(top = 10.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Text(
                text = "Word $number",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.primary
            )
            ReadingBigArabic(w.arabic, sizeSp = 30)
            Text(
                text = fixArabizi("${w.arabizi} — ${w.meaning}"),
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.SemiBold
            )
            w.tip?.let {
                Text(
                    text = fixArabizi(it),
                    style = MaterialTheme.typography.bodySmall,
                    fontStyle = FontStyle.Italic,
                    modifier = Modifier.padding(top = 2.dp)
                )
            }
            ReadingLetterBreakdown(w.arabic)
        }
    }
}
