package com.example.russianapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AndAboutIScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("And — About и") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(innerPadding),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ) {

            // ── Intro ────────────────────────────────────────────────────────
            item {
                Card(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "и — much more than just \"and\"",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "и is one of the most common words in Russian. At its core it means \"and\", " +
                                    "but it also works as an emphatic particle, a focus marker, and a building " +
                                    "block in many fixed phrases. This screen covers the main uses you will encounter.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                }
            }

            // ── и as "and" ───────────────────────────────────────────────────
            item { AndIHeader("и as \"and\" — the basic conjunction") }
            item {
                AndICard(title = "Connecting words, phrases, and clauses") {
                    Text(
                        text = "Like English \"and\", и joins nouns, adjectives, verbs, or whole clauses. " +
                                "It adds one thing to another with no contrast implied.",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    AndIExampleBlock(
                        listOf(
                            Pair("Он и она пришли.", "He and she came."),
                            Pair("Я читаю и пишу.", "I read and write."),
                            Pair("Красивый и умный.", "Beautiful and smart."),
                            Pair("Мы пришли и сели.", "We came and sat down."),
                            Pair("Он купил хлеб и молоко.", "He bought bread and milk.")
                        )
                    )
                }
            }

            // ── а vs и ───────────────────────────────────────────────────────
            item { AndIHeader("А — the other \"and\" (contrastive)") }
            item {
                AndICard(title = "и adds, а contrasts") {
                    Text(
                        text = "Russian has two words for \"and\". They are not interchangeable:\n\n" +
                                "• и — simply adds one thing to another (additive)\n" +
                                "• а — contrasts, compares, or shifts focus (\"while\", \"whereas\", \"and on the other hand\")\n\n" +
                                "Even when а translates smoothly as \"and\", it always carries a sense of " +
                                "comparison or contrast between the two sides.",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    AndIExampleBlock(
                        listOf(
                            Pair("Я читаю, а он смотрит телевизор.", "I'm reading, while he's watching TV."),
                            Pair("Она работает, а он отдыхает.", "She's working, while he's resting."),
                            Pair("Я пью чай, а она — кофе.", "I drink tea, and she (drinks) coffee."),
                            Pair("Мне нравится зима, а ей — лето.", "I like winter, and she likes summer.")
                        )
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Note: а is NOT the same as но (but). но expresses a stronger opposition or " +
                                "unexpected result — \"Я хотел пойти, но заболел\" (I wanted to go, but I got sick). " +
                                "а simply contrasts two parallel situations without implying surprise.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontStyle = FontStyle.Italic
                    )
                }
            }

            // ── и as emphatic particle ───────────────────────────────────────
            item { AndIHeader("и as an emphatic particle") }
            item {
                AndICard(title = "и meaning \"even\", \"also\", \"indeed\", \"too\"") {
                    Text(
                        text = "When и appears alone — not between two things — it is an emphatic particle. " +
                                "It highlights the word that follows, adds weight, or signals that something is " +
                                "exactly as expected (or surprisingly so). It is often translated as \"even\", " +
                                "\"also\", \"too\", or \"indeed\".",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    AndIExampleBlock(
                        listOf(
                            Pair("Он и не думал об этом.", "He didn't even think about it."),
                            Pair("Я и сам не знаю.", "I myself don't even know."),
                            Pair("Это и есть правда.", "This is indeed the truth. / This IS the truth."),
                            Pair("Она и говорит, и поёт хорошо.", "She both speaks and sings well."),
                            Pair("Он и не пытался.", "He didn't even try.")
                        )
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Tip: when и comes directly before a verb or pronoun rather than between two " +
                                "equal parts, it is almost always this emphatic particle, not the conjunction.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontStyle = FontStyle.Italic
                    )
                }
            }

            // ── а вот и... ───────────────────────────────────────────────────
            item { AndIHeader("а вот и... — \"and there is / here comes\"") }
            item {
                AndICard(title = "The phrase а вот и...") {
                    Text(
                        text = "This is a fixed colloquial phrase used when something or someone you expected " +
                                "— or were just talking about — suddenly arrives or appears. " +
                                "It expresses mild surprise or a \"speak of the devil\" / \"finally!\" feeling.\n\n" +
                                "Breaking it down:\n" +
                                "• а = contrastive shift (\"and now\", \"well\")\n" +
                                "• вот = here / there (a pointing word)\n" +
                                "• и = emphatic particle (\"indeed\", \"right there\")",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    AndIExampleBlock(
                        listOf(
                            Pair("А вот и она!", "And there she is! / Here she comes!"),
                            Pair("А вот и он!", "And there he is! / Here he comes!"),
                            Pair("А вот и такси!", "And there's the taxi!"),
                            Pair("А вот и ответ.", "And there's the answer."),
                            Pair("А вот и наш автобус!", "And there's our bus!"),
                            Pair("А вот и твой друг.", "And there's your friend.")
                        )
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "You can also drop а — \"Вот и она!\" is slightly more neutral. " +
                                "Adding а makes it more conversational and warm. " +
                                "Dropping both вот and а, leaving just \"И она?\" would be a confused question: \"And her too?\"",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontStyle = FontStyle.Italic
                    )
                }
            }

            // ── и...и... ─────────────────────────────────────────────────────
            item { AndIHeader("и... и... — both... and...") }
            item {
                AndICard(title = "Repeated и for emphasis") {
                    Text(
                        text = "When и is repeated before each item in a list, it means \"both... and...\" — " +
                                "stressing that all elements are equally and simultaneously true.",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    AndIExampleBlock(
                        listOf(
                            Pair("И я, и он пришли.", "Both I and he came."),
                            Pair("И дети, и взрослые любят это.", "Both children and adults love this."),
                            Pair("Она и читает, и пишет хорошо.", "She both reads and writes well."),
                            Pair("И холодно, и ветрено.", "It's both cold and windy."),
                            Pair("Он и умный, и добрый.", "He is both smart and kind.")
                        )
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Compare with ни... ни... (neither... nor...) — the negative counterpart. " +
                                "\"Ни я, ни он не пришли.\" — Neither I nor he came.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontStyle = FontStyle.Italic
                    )
                }
            }

            // ── и то ─────────────────────────────────────────────────────────
            item { AndIHeader("и то — \"and even then\" / \"even so\"") }
            item {
                AndICard(title = "и то — concession with a qualifier") {
                    Text(
                        text = "и то follows a statement to add a limiting qualifier — " +
                                "\"and even that is barely enough\" or \"and even then, only sometimes\". " +
                                "It softens or downgrades what was just said.",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    AndIExampleBlock(
                        listOf(
                            Pair("Иногда вижу её, и то редко.", "I sometimes see her, and even then rarely."),
                            Pair("Есть немного денег, и то не всегда.", "There's a little money, and even that's not always."),
                            Pair("Он говорит немного по-русски, и то с ошибками.", "He speaks a little Russian, and even then with mistakes."),
                            Pair("Успел позавтракать, и то быстро.", "I managed to have breakfast, and even that was rushed.")
                        )
                    )
                }
            }

            // ── и всё ────────────────────────────────────────────────────────
            item { AndIHeader("и всё — \"and that's it\" / \"and that's all\"") }
            item {
                AndICard(title = "и всё — closing a matter with finality") {
                    Text(
                        text = "и всё ends a statement with finality — \"and that's all there is to it\", " +
                                "\"end of story\", \"full stop\". It signals no further discussion is needed or expected.",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    AndIExampleBlock(
                        listOf(
                            Pair("Он сказал нет, и всё.", "He said no, and that's that."),
                            Pair("Поел и всё, лёг спать.", "Ate and that was it — went to sleep."),
                            Pair("Не нравится — уходи, и всё.", "If you don't like it — leave, and that's all."),
                            Pair("Просто позвони ей, и всё.", "Just call her, and that's it.")
                        )
                    )
                }
            }

            // ── Quick Reference ──────────────────────────────────────────────
            item { AndIHeader("Quick Reference") }
            item {
                Card(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp)) {
                            Text("Form", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.2f))
                            Text("Meaning", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
                            Text("Key idea", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.6f))
                        }
                        HorizontalDivider(modifier = Modifier.padding(bottom = 4.dp))
                        listOf(
                            Triple("и", "and", "additive — joins equal elements, no contrast"),
                            Triple("а", "and / while / whereas", "contrastive — always implies a comparison"),
                            Triple("но", "but", "stronger opposition — unexpected or unwanted contrast"),
                            Triple("и (particle)", "even / indeed / too", "emphatic — highlights the word that follows"),
                            Triple("а вот и...", "and there is... / here comes...", "points to something expected that has just arrived"),
                            Triple("и... и...", "both... and...", "all listed items are equally and simultaneously true"),
                            Triple("и то", "and even then / even so", "limits or qualifies what was just said"),
                            Triple("и всё", "and that's it / that's all", "closes a statement with finality")
                        ).forEachIndexed { i, (form, meaning, idea) ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(
                                        if (i % 2 == 0) MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
                                        else MaterialTheme.colorScheme.surface
                                    )
                                    .padding(vertical = 5.dp)
                            ) {
                                Text(form, style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary, modifier = Modifier.weight(1.2f))
                                Text(meaning, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.secondary, modifier = Modifier.weight(1f))
                                Text(idea, style = MaterialTheme.typography.bodySmall, modifier = Modifier.weight(1.6f))
                            }
                        }
                    }
                }
            }

            item { Spacer(modifier = Modifier.height(16.dp)) }
        }
    }
}

@Composable
private fun AndIHeader(title: String) {
    Column(modifier = Modifier.fillMaxWidth().padding(top = 16.dp, bottom = 4.dp)) {
        Text(title, style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.primary)
        HorizontalDivider(modifier = Modifier.padding(top = 4.dp), color = MaterialTheme.colorScheme.primary)
    }
}

@Composable
private fun AndICard(title: String, content: @Composable ColumnScope.() -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(title, style = MaterialTheme.typography.titleSmall, color = MaterialTheme.colorScheme.primary)
            Spacer(modifier = Modifier.height(8.dp))
            content()
        }
    }
}

@Composable
private fun AndIExampleBlock(examples: List<Pair<String, String>>) {
    examples.forEachIndexed { i, (russian, english) ->
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    if (i % 2 == 0) MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
                    else MaterialTheme.colorScheme.surface
                )
                .padding(vertical = 5.dp)
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(russian, style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold)
                Text(english, style = MaterialTheme.typography.bodySmall, fontStyle = FontStyle.Italic, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }
    }
}
