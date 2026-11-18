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

private data class ReadingWordEntry(
    val arabic: String,
    val english: String,
    val arabizi: String,
    val region: String,
    val note: String? = null
)

// 90 words taken from Simple Dictionary → First Words (same spelling as there).
private val READING_WORDS = listOf(
    // Pronouns
    ReadingWordEntry("أنا", "I", "ana", REGION_LEV, "The final ا is the long a of ana (said short in speech)."),
    ReadingWordEntry("إنتَ", "you (to a man)", "inta", REGION_LEV, "The small fatha mark on ت shows the -a ending (to a man)."),
    ReadingWordEntry("إنتِ", "you (to a woman)", "inti", REGION_LEV, "The kasra mark under ت shows the -i ending (to a woman). Without marks إنت can be either."),
    ReadingWordEntry("هو", "he", "huwwe", REGION_LEV, "Spoken huwwe: the doubled w and the final -e aren't written."),
    ReadingWordEntry("هي", "she", "hiyye", REGION_LEV, "Spoken hiyye: the doubled y and the final -e aren't written."),
    ReadingWordEntry("إحنا", "we", "i7na", REGION_PAL_JOR, "Lebanese / Syrian say ni7na — نحنا."),
    ReadingWordEntry("نحنا", "we", "ni7na", REGION_LEB_SYR, "Palestinian / Jordanian say i7na — إحنا."),
    ReadingWordEntry("إنتو", "you (plural)", "intu", REGION_LEV, "The final و is the long u ending."),
    ReadingWordEntry("همّ", "they", "humme", REGION_PAL, "The shadda doubles the m; the final -e isn't written. Lebanese / Syrian say hinne — هنّي."),
    ReadingWordEntry("هنّي", "they", "hinne", REGION_LEB_SYR, "The shadda doubles the n; the final ي is read -e here."),
    // Basic words
    ReadingWordEntry("عندي", "I have", "3indi", REGION_LEV, "3ind (at) + -i (me) = 'at me', like Hebrew יש לי."),
    ReadingWordEntry("آه", "yes", "ah", REGION_LEV, "آ (alef madda) = a long a; the ه is a soft breath at the end."),
    ReadingWordEntry("أيوه", "yes", "aywa", REGION_LEV, "The ه at the end is silent: aywa(h)."),
    ReadingWordEntry("لا", "no", "la2", REGION_LEV, "The whole word is the lam-alef shape. The final glottal stop (2) isn't written."),
    ReadingWordEntry("صحّ", "correct / right", "sa77", REGION_LEV, "The shadda on ح doubles it: sa77."),
    ReadingWordEntry("مزبوط", "correct / exact", "mazbuut", REGION_LEV, "و is the long uu. Formal spelling مضبوط (with ض)."),
    ReadingWordEntry("أكيد", "sure", "akiid", REGION_LEV, "ي is the long ii."),
    ReadingWordEntry("طبعاً", "of course", "tab3an", REGION_FORMAL, "The ending اً is read -an (tanween), not -aa."),
    ReadingWordEntry("فجأة", "suddenly", "faj2a", REGION_LEV, "أ = the glottal stop 2;  ة = the -a ending."),
    ReadingWordEntry("بس", "but / only / enough", "bas", REGION_LEV),
    ReadingWordEntry("لكن", "but (more formal)", "laakin", REGION_FORMAL, "The long aa after ل is NOT written — an old spelling kept from formal Arabic."),
    ReadingWordEntry("إذا", "if (real condition)", "iza", REGION_LEV, "ذ is said z in spoken Levantine (formal idhaa)."),
    ReadingWordEntry("لو", "if (unreal / wishes)", "law", REGION_LEV, "Here و is a consonant w (law), not a vowel."),
    ReadingWordEntry("يمكن", "maybe", "yimkin", REGION_LEV, "Short vowels i / i aren't written — only y-m-k-n."),
    ReadingWordEntry("ممكن", "possible / could be", "mumkin", REGION_LEV),
    ReadingWordEntry("ولّا", "or (in questions)", "willa", REGION_LEV, "Doubled l (shadda) + lam-alef ending."),
    ReadingWordEntry("مش", "not", "mish", REGION_LEV, "Lebanese also mush."),
    ReadingWordEntry("سؤال", "question", "su2aal", REGION_LEV, "ؤ = glottal stop 2 sitting on a waw."),
    ReadingWordEntry("جواب", "answer", "jawaab", REGION_LEV),
    // This / now / still
    ReadingWordEntry("هادا", "this (m)", "haada", REGION_PAL, "Lebanese / Syrian: hayda — هيدا. Also written هاد (haad)."),
    ReadingWordEntry("هيدا", "this (m)", "hayda", REGION_LEB_SYR, "Palestinian: haada — هادا."),
    ReadingWordEntry("هاي", "this (f)", "haay", REGION_PAL, "Lebanese / Syrian: haydi — هيدي."),
    ReadingWordEntry("هلّق", "now", "halla2", REGION_LEV, "The ق is said as a glottal stop: halla2. Shadda doubles the l."),
    ReadingWordEntry("لسّا", "still / not yet", "lissa", REGION_PAL_JOR, "Lebanese / Syrian use ba3d + ending: ba3dni, ba3do."),
    ReadingWordEntry("خلص", "enough / done / already", "khalas", REGION_LEV, "No vowels written at all — kh-l-s → khalas."),
    ReadingWordEntry("بيجوز", "could be", "bijuuz", REGION_LEV),
    // People
    ReadingWordEntry("زلمة", "man", "zalame", REGION_LEV, "ة is read -e here (after most letters in Levantine)."),
    ReadingWordEntry("مرا", "woman", "mara", REGION_LEV, "Also written مرة. Formal: imra2a — امرأة."),
    ReadingWordEntry("ختيار", "old man", "khitiyaar", REGION_LEV),
    ReadingWordEntry("قديم", "old (thing)", "2adiim", REGION_LEV, "ق at the start = glottal stop: 2adiim."),
    ReadingWordEntry("كبير", "big / old (person)", "kbiir", REGION_LEV, "The first vowel is dropped in speech: kbiir (formal kabiir)."),
    ReadingWordEntry("صغير", "small / young", "zghiir", REGION_LEV, "The ص is voiced to z before غ: zghiir (formal saghiir)."),
    ReadingWordEntry("شاب", "young man", "shabb", REGION_LEV, "Said with a doubled b (shabb); the shadda is usually not written."),
    ReadingWordEntry("صبيّة", "young woman / girl", "sabiyye", REGION_LEV, "Shadda doubles the y; ة = -e."),
    ReadingWordEntry("تعبان", "tired (m)", "ta3baan", REGION_LEV),
    ReadingWordEntry("منيح", "good / well (m)", "mnii7", REGION_LEV, "Starts with two consonants: m-nii7."),
    ReadingWordEntry("ممتاز", "excellent (m)", "mumtaaz", REGION_LEV),
    // How often / when
    ReadingWordEntry("عادةً", "usually", "3aadatan", REGION_FORMAL, "ة + tanween = -tan. Everyday: bil-3aade."),
    ReadingWordEntry("دايماً", "always", "daayman", REGION_LEV, "Ending اً = -an."),
    ReadingWordEntry("أبداً", "never (with ma + verb)", "abadan", REGION_LEV),
    ReadingWordEntry("أحياناً", "sometimes", "a7yaanan", REGION_FORMAL),
    ReadingWordEntry("مرّة", "a time / once", "marra", REGION_LEV, "ة after ر is read -a (not -e)."),
    ReadingWordEntry("مرّات", "times / sometimes", "marraat", REGION_LEV, "The plural ending -aat: ا + ت."),
    ReadingWordEntry("مبارح", "yesterday", "mbaari7", REGION_LEV),
    ReadingWordEntry("بكرا", "tomorrow", "bukra", REGION_LEV, "Spoken spelling. Formal: bukra — بكرة."),
    ReadingWordEntry("زمان", "long ago / back then", "zamaan", REGION_LEV),
    ReadingWordEntry("وقت", "time", "wa2t", REGION_LEV, "ق = glottal stop: wa2t."),
    ReadingWordEntry("ساعة", "hour / clock", "saa3a", REGION_LEV, "ة after ع is read -a."),
    ReadingWordEntry("سنة", "year", "sane", REGION_LEV, "ة after ن is read -e."),
    ReadingWordEntry("أسبوع", "week", "usbuu3", REGION_LEV, "أ here starts with u."),
    ReadingWordEntry("جمعة", "Friday / week", "jum3a", REGION_LEV, "In spoken Levantine jum3a means both Friday and week."),
    ReadingWordEntry("يوم", "day", "yoom", REGION_LEV, "و is the long oo here."),
    // Depending / without / except
    ReadingWordEntry("حسب", "depending on", "7asab", REGION_LEV),
    ReadingWordEntry("بكفّي", "enough!", "bikaffi", REGION_LEV),
    ReadingWordEntry("بلا", "without", "bala", REGION_LEV, "Ends in lam-alef."),
    ReadingWordEntry("غير", "except / other / different", "gheer", REGION_LEV, "ي is the long ee here."),
    // Wanting / loving
    ReadingWordEntry("بدّي", "I want", "biddi", REGION_LEV),
    ReadingWordEntry("بحبّ", "I love / I like", "ba7ibb", REGION_LEV, "The shadda on the final ب doubles it."),
    ReadingWordEntry("حدا", "someone", "7ada", REGION_LEV),
    ReadingWordEntry("مكان", "place", "makaan", REGION_LEV),
    ReadingWordEntry("مطرح", "place", "matra7", REGION_LEV),
    // Places, things
    ReadingWordEntry("هون", "here", "hon", REGION_LEV, "و is the long o."),
    ReadingWordEntry("برّا", "outside", "barra", REGION_LEV),
    ReadingWordEntry("بيت", "house / home", "beit", REGION_LEV, "ي is read ei here."),
    ReadingWordEntry("بحر", "sea", "ba7r", REGION_LEV),
    ReadingWordEntry("قهوة", "coffee", "2ahwe", REGION_LEV, "ق = glottal stop; ة = -e."),
    ReadingWordEntry("سكّر", "sugar", "sukkar", REGION_LEV),
    ReadingWordEntry("سيّارة", "car", "sayyaara", REGION_LEV, "ة after ر is read -a."),
    ReadingWordEntry("كلب", "dog", "kalb", REGION_LEV),
    ReadingWordEntry("ناس", "people", "naas", REGION_LEV),
    ReadingWordEntry("شغل", "work", "shughl", REGION_LEV),
    ReadingWordEntry("اجتماع", "meeting", "ijtimaa3", REGION_LEV, "The first ا (no hamza drawn) carries the i."),
    ReadingWordEntry("سعر", "price", "si3r", REGION_LEV),
    ReadingWordEntry("مشكلة", "problem", "mushkile", REGION_LEV, "ة after ل is read -e."),
    // Greetings and courtesy
    ReadingWordEntry("مرحبا", "hello", "mar7aba", REGION_LEV, "The h is a ح (7): mar7aba. Often written marhaba in English letters."),
    ReadingWordEntry("أهلا", "hi / welcome", "ahlan", REGION_LEV, "The -an ending: usually written أهلاً with tanween; here without the mark."),
    ReadingWordEntry("شكراً", "thank you", "shukran", REGION_LEV),
    ReadingWordEntry("يسلمو", "thanks (warm)", "yislamu", REGION_LEV),
    ReadingWordEntry("معلش", "never mind / sorry", "ma3leesh", REGION_LEV, "Spoken spelling — the long ee isn't written. Also written معليش."),
    ReadingWordEntry("آسف", "sorry (said by a man)", "aasif", REGION_LEV, "آ = long aa at the start. A woman says aasfe — آسفة.")
)

@Composable
fun ReadingWordsScreen() {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        item {
            ReadingNoteCard(
                "${READING_WORDS.size} words from Simple Dictionary → First Words. Tap a word to see its letters.\n\n" +
                    "Every Arabic letter has up to four shapes: alone, beginning (connects only to the next letter), middle (connects on both sides) and end (connects only to the letter before).\n\n" +
                    "Six letters — ا د ذ ر ز و — never connect to the letter AFTER them, so the next letter starts again in its beginning (or alone) shape. Short vowels are usually not written: you read them from knowing the word."
            )
        }
        itemsIndexed(READING_WORDS) { index, word ->
            ReadingWordCard(index + 1, word)
        }
        item { ReadingSpacerBottom() }
    }
}

@Composable
private fun ReadingWordCard(number: Int, w: ReadingWordEntry) {
    var expanded by rememberSaveable(w.arabic, w.arabizi) { mutableStateOf(false) }
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
                ReadingRegionTag(w.region)
            }
            ReadingBigArabic(w.arabic)
            Text(
                text = fixArabizi(w.arabizi),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Text(text = w.english, style = MaterialTheme.typography.bodyLarge)
            w.note?.let {
                Text(
                    text = fixArabizi(it),
                    style = MaterialTheme.typography.bodySmall,
                    fontStyle = FontStyle.Italic,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
            ReadingExpandHint(expanded, "Tap to see the letters")
            if (expanded) ReadingLetterBreakdown(w.arabic)
        }
    }
}
