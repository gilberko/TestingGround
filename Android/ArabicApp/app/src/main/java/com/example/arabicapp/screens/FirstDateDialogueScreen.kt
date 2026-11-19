package com.example.arabicapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.dp

private data class FirstDateDLine(val speaker: String, val ar: String, val translit: String, val en: String)

private val firstDateDialogue = listOf(
    FirstDateDLine("K", "مرحبا! إنتِ جيل؟", "Mar7aba! Inti Jill?", "Hi! Are you Jill?"),
    FirstDateDLine("J", "أيوا، أنا جيل. وإنتَ كيفن؟ ميريام حكتلي عنّك كتير.", "Aywa, ana Jill. W inta Kevin? Miriam 7akatli 3annak ktiir.", "Yes, I'm Jill. And you're Kevin? Miriam told me a lot about you."),
    FirstDateDLine("K", "والله؟ وحكتلي عنّك كمان — بس أشيا حلوة!", "Walla? W 7akatli 3annik kamaan — bass ashya 7ilwe!", "Really? She told me about you too — only nice things!"),
    FirstDateDLine("J", "هههه، منيح. كيفك؟", "Hahaha, mnii7. Kiifak?", "Haha, good. How are you?"),
    FirstDateDLine("K", "منيح، الحمدلله. وإنتِ كيفك؟", "Mnii7, el-7amdilla. W inti kiifik?", "Good, thank God. And how are you?"),
    FirstDateDLine("J", "تمام، بس شوي متوترة. أول مرة بطلع مع حدا ما بعرفه.", "Tamaam, bass shwayy mitwattra. Awwal marra baTla3 ma3 7ada ma ba3rfo.", "Fine, just a bit nervous. It's the first time I'm going out with someone I don't know."),
    FirstDateDLine("K", "وأنا كمان! يلا نقعد.", "W ana kamaan! Yalla ni23od.", "Me too! Come on, let's sit down."),
    FirstDateDLine("J", "قدّيش عمرك يا كيفن؟", "2addeish 3umrak ya Kevin?", "How old are you, Kevin?"),
    FirstDateDLine("K", "عمري تنين وتلاتين سنة. وإنتِ؟", "3umri tneen w talaatiin sane. W inti?", "I'm thirty-two. And you?"),
    FirstDateDLine("J", "أنا عمري تسعة وعشرين.", "Ana 3umri tis3a w 3ishriin.", "I'm twenty-nine."),
    FirstDateDLine("K", "ومن وين إنتِ؟", "W min wein inti?", "And where are you from?"),
    FirstDateDLine("J", "أنا من حيفا، بس هلّق ساكنة بالقدس. وإنتَ؟", "Ana min 7eifa, bass halla2 saakne bil-Quds. W inta?", "I'm from Haifa, but now I live in Jerusalem. And you?"),
    FirstDateDLine("K", "أنا من لندن أصلاً، بس صرلي خمس سنين هون.", "Ana min London asLan, bass Sarli khams sniin hoon.", "I'm originally from London, but I've been here for five years."),
    FirstDateDLine("J", "وشو بتشتغل؟", "W shu btishtghil?", "And what do you do for work?"),
    FirstDateDLine("K", "أنا مهندس برمجة بشركة هاي-تك. وإنتِ شو بتشتغلي؟", "Ana muhandis barmaje b-sharkit hai-tek. W inti shu btishtghli?", "I'm a software engineer at a high-tech company. And what do you do?"),
    FirstDateDLine("J", "أنا ممرّضة بمستشفى.", "Ana mumarriDa b-mustashfa.", "I'm a nurse at a hospital."),
    FirstDateDLine("K", "واو، شغل صعب! وشو بتحبّي تعملي بوقت الفراغ؟", "Waw, shughl Sa3b! W shu bt7ibbi ti3mali b-wa2t el-faraagh?", "Wow, a hard job! And what do you like to do in your free time?"),
    FirstDateDLine("J", "بحبّ أركض وأقرا كتب، وبحبّ أطبخ كمان. وإنتَ؟ شو هواياتك؟", "B7ibb arkoD w a2ra kutub, w b7ibb aTbukh kamaan. W inta? Shu hiwaayaatak?", "I like running and reading books, and I also like cooking. And you? What are your hobbies?"),
    FirstDateDLine("K", "بلعب فوتبول كل سبت، وبحبّ أصوّر.", "Bal3ab futbool kull sabt, w b7ibb aSawwir.", "I play football every Saturday, and I like taking photos."),
    FirstDateDLine("J", "شو نوع الموسيقى اللي بتحبّها؟", "Shu noo3 el-musii2a elli bt7ibbha?", "What kind of music do you like?"),
    FirstDateDLine("K", "بحبّ الروك والجاز، والصبح بسمع فيروز. وإنتِ؟", "B7ibb er-rok wil-jaaz, w eS-Sub7 basma3 Feiruuz. W inti?", "I like rock and jazz, and in the morning I listen to Fairuz. And you?"),
    FirstDateDLine("J", "أنا بحبّ البوب، وكمان الموسيقى العربية القديمة — أم كلثوم مثلاً.", "Ana b7ibb el-pop, w kamaan el-musii2a el-3arabiyye el-2adiime — Umm Kulthuum masalan.", "I like pop, and also old Arabic music — Umm Kulthum, for example."),
    FirstDateDLine("K", "وشو نوع الأكل اللي بتحبّيه؟", "W shu noo3 el-akl elli bt7ibbii?", "And what kind of food do you like?"),
    FirstDateDLine("J", "بموت عالأكل الإيطالي! بس كمان بحبّ الحمّص والفلافل. وإنتَ؟", "Bmuut 3al-akl el-iTaali! Bass kamaan b7ibb el-7ummuS wil-falaafel. W inta?", "I'm crazy about Italian food! But I also love hummus and falafel. And you?"),
    FirstDateDLine("K", "أنا بحبّ الأكل الهندي — كل ما كان حار أكتر، أحسن!", "Ana b7ibb el-akl el-hindi — kull ma kaan 7aarr aktar, a7san!", "I love Indian food — the spicier, the better!"),
    FirstDateDLine("J", "لا، أنا ما بحبّ الأكل الحار أبداً.", "La2, ana ma b7ibb el-akl el-7aarr abadan.", "No, I don't like spicy food at all."),
    FirstDateDLine("K", "طيّب، المرّة الجاي منروح على مطعم إيطالي؟", "Tayyib, el-marra ej-jaaye mnruu7 3ala maT3am iTaali?", "OK, next time shall we go to an Italian restaurant?"),
    FirstDateDLine("J", "أكيد! لازم نشكر ميريام.", "Akiid! Laazim nushkur Miriam.", "Definitely! We have to thank Miriam.")
)

private val firstDateNotes = listOf(
    "Miriam set the date up: in Arabic you'd say ميريام رتّبتلنا الموعد — Miriam rattabatilna el-maw3ad — Miriam arranged the date for us.",
    "Kevin says -ak forms (kiifak, 3umrak, hiwaayaatak = to a man); Kevin says -ik / -i forms to Jill (kiifik, 3annik, btishtghli, bt7ibbi = to a woman).",
    "3umri … sane — lit. 'my age is … years'. The question is 2addeish 3umrak (m) / 3umrik (f).",
    "Sarli khams sniin hoon — 'I've been here five years' (Sar-li = it has become to me; see Questions → Since When).",
    "shu noo3 … elli bt7ibbo/bt7ibbha — 'what kind of … do you like' (noo3 = kind / type). bt7ibbii = bt7ibbi + -o (it, m): what you (f) like.",
    "bmuut 3ala … — lit. 'I die for …' = I'm crazy about / I love.",
    "kull ma … aktar, a7san — 'the more …, the better' (see Comparisons).",
    "mnruu7 — 'we go / shall we go' (Pal/Leb mn- for b- + n-; also bnruu7)."
)

@Composable
private fun FirstDateSection(title: String) {
    Spacer(modifier = Modifier.height(16.dp))
    Text(title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
    Spacer(modifier = Modifier.height(4.dp))
    HorizontalDivider()
    Spacer(modifier = Modifier.height(8.dp))
}

@Composable
private fun FirstDateCard(line: FirstDateDLine, showArabic: Boolean) {
    val containerColor = if (line.speaker == "K") MaterialTheme.colorScheme.secondaryContainer
    else MaterialTheme.colorScheme.tertiaryContainer
    val labelColor = if (line.speaker == "K") MaterialTheme.colorScheme.onSecondaryContainer
    else MaterialTheme.colorScheme.onTertiaryContainer
    val speakerLabel = if (line.speaker == "K") "Kevin" else "Jill"
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = containerColor)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = speakerLabel,
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Bold,
                color = labelColor
            )
            Spacer(modifier = Modifier.height(4.dp))
            if (showArabic) {
                Text(
                    text = line.ar,
                    style = MaterialTheme.typography.bodyLarge.copy(textDirection = TextDirection.Rtl),
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = fixArabizi(line.translit),
                    style = MaterialTheme.typography.bodyMedium,
                    fontStyle = FontStyle.Italic,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            } else {
                Text(
                    text = line.en,
                    style = MaterialTheme.typography.bodyMedium,
                    fontStyle = FontStyle.Italic,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
fun FirstDateDialogueScreen() {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp)
    ) {
        item {
            Text(
                text = "A blind date: Kevin and Jill have never met. Their friend Miriam set them up, and they meet at a café.",
                style = MaterialTheme.typography.bodyMedium,
                fontStyle = FontStyle.Italic
            )
        }
        item { FirstDateSection("Arabic & Pronunciation") }
        items(firstDateDialogue) { line ->
            FirstDateCard(line = line, showArabic = true)
            Spacer(modifier = Modifier.height(6.dp))
        }
        item { FirstDateSection("English Translation") }
        items(firstDateDialogue) { line ->
            FirstDateCard(line = line, showArabic = false)
            Spacer(modifier = Modifier.height(6.dp))
        }
        item { FirstDateSection("Notes") }
        items(firstDateNotes) { note ->
            Text(
                text = fixArabizi("• $note"),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(vertical = 3.dp)
            )
        }
        item { Spacer(modifier = Modifier.height(16.dp)) }
    }
}
