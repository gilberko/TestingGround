package com.example.russianapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
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

private data class PrepEntry(
    val prep: String,
    val case: String,
    val english: String,
    val russian: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrepositionsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Prepositions") },
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

            // ── Movement & Origin ──────────────────────────────────────────────
            item { PrepSectionHeader("Movement & Origin") }
            item {
                PrepTableCard(
                    entries = listOf(
                        PrepEntry("из", "Genitive", "from (inside a place / country)", "Он из Аргентины — He is from Argentina"),
                        PrepEntry("с", "Genitive", "from (activity / open place)", "Он пришёл с работы — He came from work"),
                        PrepEntry("в", "Accusative", "to (enclosed place)", "Он идёт в офис — He is going to the office"),
                        PrepEntry("на", "Accusative", "to (event / performance)", "Он идёт на шоу — He is going to a show")
                    ),
                    note = "Use в for enclosed spaces (office, house, city) and на for open areas and events (concert, show, work, market). " +
                            "Their opposites follow the same split: из (from inside) vs. с (from a surface/activity)."
                )
            }

            // ── Towards / To a Person ──────────────────────────────────────────
            item { PrepSectionHeader("Towards / To a Person (к)") }
            item {
                PrepTableCard(
                    entries = listOf(
                        PrepEntry("к", "Dative", "towards / to (a person)", "Я иду к врачу — I am going to the doctor's\nЯ иду к маме — I am going to mum's"),
                        PrepEntry("к", "Dative", "towards / to (a place)", "Он подошёл к школе — He walked up to the school")
                    ),
                    note = "к implies heading toward a destination — the journey may not be complete. " +
                            "It pairs with от (away from): Я ушёл от него = I left from him / his place. " +
                            "Compare with до (all the way to a destination): Я дошёл до школы = I got as far as the school."
                )
            }

            // ── At / Near / Possession ─────────────────────────────────────────
            item { PrepSectionHeader("At / Near / Possession (у)") }
            item {
                PrepTableCard(
                    entries = listOf(
                        PrepEntry("у", "Genitive", "at (someone's place)", "Я у мамы — I am at mum's place\nОн у друга — He is at his friend's"),
                        PrepEntry("у", "Genitive", "near / by (a thing or place)", "Я стою у окна — I am standing by the window\nОн сидит у входа — He is sitting by the entrance"),
                        PrepEntry("у", "Genitive", "possession (I have / he has…)", "У меня есть машина — I have a car\nУ него нет времени — He has no time")
                    ),
                    note = "У меня есть… = I have… (lit. 'at me there is'). Negation uses нет + Genitive: У меня нет машины. " +
                            "This is the standard Russian way to express possession — there is no verb 'to have' as a standalone word."
                )
            }

            // ── Along / Around / By / Distribution ────────────────────────────
            item { PrepSectionHeader("Along / Around / By / Distribution (по)") }
            item {
                PrepTableCard(
                    entries = listOf(
                        PrepEntry("по", "Dative", "along / through", "идти по улице — walk along the street\nбежать по коридору — run along the corridor"),
                        PrepEntry("по", "Dative", "around (inside a place)", "гулять по парку — walk around the park\nходить по магазинам — go around the shops"),
                        PrepEntry("по", "Dative", "by (means / medium)", "по телефону — by phone\nпо телевизору — on TV\nпо почте — by mail"),
                        PrepEntry("по", "Dative", "on (recurring days)", "по понедельникам — on Mondays\nпо выходным — on weekends"),
                        PrepEntry("по", "Dative", "each / per (distribution)", "дать всем по яблоку — give everyone one apple each\nпо одному — one at a time"),
                        PrepEntry("по", "Dative", "on the subject of", "книга по истории — a book on history\nэкзамен по математике — exam in maths")
                    ),
                    note = "по is one of the most versatile Russian prepositions — context determines the meaning. " +
                            "The core spatial idea is 'movement along a surface', which extends into medium (along the phone line), " +
                            "time (along/through Mondays), and distribution (one along to each person)."
                )
            }

            // ── Through / Across / In (time) ──────────────────────────────────
            item { PrepSectionHeader("Through / Across / In (future time) (через)") }
            item {
                PrepTableCard(
                    entries = listOf(
                        PrepEntry("через", "Accusative", "through / across", "через лес — through the forest\nчерез реку — across the river\nчерез дорогу — across the road"),
                        PrepEntry("через", "Accusative", "in (time from now)", "через час — in an hour (from now)\nчерез неделю — in a week\nчерез год — in a year")
                    ),
                    note = "через час = one hour from now (a future point). " +
                            "Compare: за час = it took one hour (duration to complete something). " +
                            "Я прочитал книгу за два дня — I read the book in two days (it took two days). " +
                            "Я приду через два дня — I'll come in two days (two days from now)."
                )
            }

            // ── Near / Around / Approximately ─────────────────────────────────
            item { PrepSectionHeader("Near / Around / Approximately (около, вокруг, рядом с)") }
            item {
                PrepTableCard(
                    entries = listOf(
                        PrepEntry("около", "Genitive", "near / close to", "около школы — near the school\nоколо дома — near the house"),
                        PrepEntry("около", "Genitive", "approximately (with numbers)", "около пяти часов — around 5 o'clock\nоколо ста человек — around a hundred people"),
                        PrepEntry("вокруг", "Genitive", "around (encircling)", "вокруг стола — around the table\nвокруг города — around the city\nвокруг него — around him"),
                        PrepEntry("рядом с", "Instrumental", "next to / beside", "рядом с тобой — next to you\nрядом с домом — next to the house")
                    ),
                    note = "около means both 'near' (spatial) and 'approximately' (quantity). " +
                            "вокруг implies encircling or going all the way around. " +
                            "рядом с always takes the Instrumental case."
                )
            }

            // ── From … Until ──────────────────────────────────────────────────
            item { PrepSectionHeader("From … Until") }
            item {
                PrepTableCard(
                    entries = listOf(
                        PrepEntry("от … до", "Genitive (both)", "from … until (distance)", "от Москвы до Петербурга — from Moscow to St. Petersburg"),
                        PrepEntry("от … до", "Genitive (both)", "from … to (clock hours)", "от пяти утра до десяти вечера — from 5 in the morning to 10 in the evening"),
                        PrepEntry("с … по", "Genitive / Accusative", "from … through (days)", "с понедельника по пятницу — from Monday to Friday")
                    ),
                    note = "For clock times and distances use от…до (both Genitive). " +
                            "For days and weeks, с…по is the natural pairing."
                )
            }

            // ── Time Relationships ─────────────────────────────────────────────
            item { PrepSectionHeader("Time Relationships") }
            item {
                PrepTableCard(
                    entries = listOf(
                        PrepEntry("до", "Genitive", "before / until", "Я жду до этого — I'm waiting until this happens\nдо понедельника — until Monday"),
                        PrepEntry("перед", "Instrumental", "right before (immediately preceding)", "перед встречей — right before the meeting"),
                        PrepEntry("после", "Genitive", "after", "после этого — after this\nэто случилось после — it happened after"),
                        PrepEntry("во время", "Genitive", "during", "во время урока — during the lesson\nво время войны — during the war"),
                        PrepEntry("пока", "(conjunction)", "while", "пока это происходило — while this was happening")
                    ),
                    note = "Use до for 'until' a point in time (Genitive). Use перед for 'just before' a specific event (Instrumental). " +
                            "Пока is a conjunction, not a preposition — it introduces a clause."
                )
            }

            // ── With & Between ─────────────────────────────────────────────────
            item { PrepSectionHeader("With & Between") }
            item {
                PrepTableCard(
                    entries = listOf(
                        PrepEntry("с", "Instrumental", "with", "Борис идёт с семьёй — Boris goes with his family\nЯ с тобой — I am with you"),
                        PrepEntry("между", "Instrumental", "between (items, people, groups)", "между двумя командами — between two teams\nмежду нами — between us")
                    )
                )
            }

            // ── Against ────────────────────────────────────────────────────────
            item { PrepSectionHeader("Against") }
            item {
                PrepTableCard(
                    entries = listOf(
                        PrepEntry("против", "Genitive", "against", "против него — against him\nпротив этого решения — against this decision\nплыть против течения — swim against the current")
                    )
                )
            }

            // ── For / Instead of / Except ──────────────────────────────────────
            item { PrepSectionHeader("For / Instead of / Except (для, вместо, кроме)") }
            item {
                PrepTableCard(
                    entries = listOf(
                        PrepEntry("для", "Genitive", "for (purpose / benefit)", "это для тебя — this is for you\nдля здоровья — for one's health\nхорошо для детей — good for children"),
                        PrepEntry("вместо", "Genitive", "instead of", "вместо него — instead of him\nвместо кофе — instead of coffee\nвместо этого — instead of this"),
                        PrepEntry("кроме", "Genitive", "except / besides", "все кроме меня — everyone except me\nкроме этого — besides this / in addition to this")
                    ),
                    note = "All three always take the Genitive case. " +
                            "кроме can mean both 'except' and 'in addition to' depending on context — the meaning is usually clear from the sentence."
                )
            }

            // ── Position: Above / Below / In Front / Behind ────────────────────
            item { PrepSectionHeader("Position: Above / Below / In Front / Behind") }
            item {
                PrepTableCard(
                    entries = listOf(
                        PrepEntry("над", "Instrumental", "above", "лампа над столом — the lamp above the table"),
                        PrepEntry("под", "Instrumental", "below / under", "под столом — below the table"),
                        PrepEntry("перед", "Instrumental", "in front of", "Я стоял перед тобой в очереди — I was in front of you in line"),
                        PrepEntry("за", "Instrumental", "behind", "Я стоял за тобой — I was behind you")
                    ),
                    note = "All four take Instrumental when expressing static position (no movement). " +
                            "When movement toward the position is involved, they take Accusative instead. " +
                            "Example: Поставь коробку под стол — Put the box under the table (movement → Accusative)."
                )
            }

            // ── About — о/об and про ───────────────────────────────────────────
            item { PrepSectionHeader("About — о/об and про") }
            item {
                PrepTableCard(
                    entries = listOf(
                        PrepEntry("о / об", "Prepositional", "about (neutral / formal)", "Я думал о тебе — I was thinking about you\nоб этом — about this\nкнига об истории — a book about history"),
                        PrepEntry("про", "Accusative", "about (colloquial)", "расскажи мне про это — tell me about this\nОн говорил про фильм — He was talking about the film")
                    ),
                    note = "о and об are the same preposition: use об before words starting with a vowel sound (об этом, об Анне, об истории). " +
                            "про is more casual and colloquial; о/об is neutral and preferred in writing. " +
                            "In everyday spoken Russian, both are widely used — you can say 'говорить о работе' or 'говорить про работу' with no difference in meaning."
                )
            }

            // ── Under / During / Given (при) ───────────────────────────────────
            item { PrepSectionHeader("Under / At / Given (при)") }
            item {
                PrepTableCard(
                    entries = listOf(
                        PrepEntry("при", "Prepositional", "under (a regime or era)", "при Сталине — under Stalin\nпри коммунизме — under communism\nпри советской власти — under Soviet rule"),
                        PrepEntry("при", "Prepositional", "at / attached to (a place)", "при входе — at the entrance\nпри школе — attached to / part of the school\nслужба при президенте — a service under the president"),
                        PrepEntry("при", "Prepositional", "in the presence of / on one's person", "при мне — in my presence / on me (I have it)\nпри всех — in front of everyone"),
                        PrepEntry("при", "Prepositional", "given / if / provided that", "при желании — if one wishes / if you want to\nпри необходимости — if necessary\nпри условии — on condition that")
                    ),
                    note = "при always takes the Prepositional case. " +
                            "The core idea is 'in the vicinity of / attached to' — this extends to time periods (in the era of), " +
                            "physical proximity (at the entrance), presence (on my person), and conditional context (given the condition)."
                )
            }

            // ── Cause, Contrast & Result ───────────────────────────────────────
            item { PrepSectionHeader("Cause, Contrast & Result") }
            item {
                PrepTableCard(
                    entries = listOf(
                        PrepEntry("потому что", "(conjunction)", "because", "Я остался дома, потому что был болен — I stayed home because I was sick"),
                        PrepEntry("поэтому", "(adverb)", "therefore / that's why", "Я был болен, поэтому остался дома — I was sick, so I stayed home"),
                        PrepEntry("и поэтому", "(adverb)", "and therefore / and so", "Шёл дождь, и поэтому мы остались дома — It was raining, and so we stayed home"),
                        PrepEntry("хотя", "(conjunction)", "although / even though", "Хотя было холодно, он вышел — Although it was cold, he went out"),
                        PrepEntry("без", "Genitive", "without", "Он пришёл без денег — He came without money"),
                        PrepEntry("из-за", "Genitive", "because of / due to\n(also: from behind)", "Из-за дождя мы остались дома — Because of the rain we stayed home\nОн вышел из-за угла — He came out from behind the corner")
                    ),
                    note = "«Потому что» gives the reason; «поэтому» states the consequence — they are mirror images. " +
                            "Хотя introduces a concessive clause (the result is surprising given the condition). " +
                            "Без and из-за always take Genitive."
                )
            }

            // ── Conjunctions ──────────────────────────────────────────────────
            item { PrepSectionHeader("Conjunctions — и, а, но, или") }
            item {
                PrepTableCard(
                    entries = listOf(
                        PrepEntry("и", "(conjunction)", "and (additive)", "Он работает и учится — He works and studies\nЯ купил хлеб и молоко — I bought bread and milk"),
                        PrepEntry("а", "(conjunction)", "while / whereas (contrast)", "Он врач, а она учитель — He's a doctor, while she's a teacher\nЯ работаю в офисе, а он — дома — I work in the office, while he works at home"),
                        PrepEntry("но", "(conjunction)", "but (contradiction / limitation)", "Я хотел пойти, но заболел — I wanted to go, but I got sick\nОн хочет, но не может — He wants to, but he can't"),
                        PrepEntry("или", "(conjunction)", "or", "Чай или кофе? — Tea or coffee?\nТы придёшь или нет? — Are you coming or not?")
                    ),
                    note = "а and но both translate as 'but' in English, but they work differently. " +
                            "а contrasts two different subjects or facts without implying surprise: Он молодой, а опытный (He's young, yet experienced — neutral contrast). " +
                            "но introduces a contradiction or limitation: Он молодой, но опытный (He's young, but surprisingly experienced — the second fact is unexpected). " +
                            "In practice: use а when describing two different people/things side by side; use но when the second clause limits or contradicts the first."
                )
            }

            // ═══════════════════════════════════════════════════════════════════
            item { PrepSectionHeader("Confusing Pairs — When to Use Which") }
            // ═══════════════════════════════════════════════════════════════════

            // ── в vs на ────────────────────────────────────────────────────────
            item {
                PrepTableCard(
                    entries = listOf(
                        PrepEntry("в", "Prep / Acc", "in (enclosed location) / into (movement)", "в офисе — in the office\nв парке — in the park\nв Москве — in Moscow\nидти в магазин — go to the store"),
                        PrepEntry("на", "Prep / Acc", "at/on (open/functional place) / to (movement)", "на улице — on the street\nна стадионе — at the stadium\nна работе — at work\nидти на концерт — go to a concert")
                    ),
                    note = "Rule: в for enclosed spaces and countries/cities; на for open areas, surfaces, events, and some functional places (работа, рынок, почта, вокзал, факультет). " +
                            "Movement uses the same preposition but Accusative case instead of Prepositional."
                )
            }

            // ── из vs от ──────────────────────────────────────────────────────
            item {
                PrepTableCard(
                    entries = listOf(
                        PrepEntry("из", "Genitive", "from (inside / country / material) — pair of в", "из России — from Russia\nиз стакана — from the glass\nсделан из дерева — made of wood"),
                        PrepEntry("от", "Genitive", "from (person / source / away from) — pair of к", "письмо от мамы — a letter from mum\nдалеко от дома — far from home\nот усталости — from fatigue")
                    ),
                    note = "Rule: из pairs with в (in ↔ out): пришёл из магазина (came from the store). " +
                            "от pairs with к (toward ↔ away from): отошёл от него (stepped away from him). " +
                            "If you'd use в to go there, use из to come from there. If you'd use к to go to them, use от to come away from them."
                )
            }

            // ── к vs до ───────────────────────────────────────────────────────
            item {
                PrepTableCard(
                    entries = listOf(
                        PrepEntry("к", "Dative", "towards (direction, may not arrive)", "Я иду к врачу — I am going to the doctor's\nОн повернулся к окну — He turned towards the window"),
                        PrepEntry("до", "Genitive", "as far as / all the way to (endpoint reached)", "Я дошёл до магазина — I got as far as the store\nот Москвы до Киева — from Moscow to Kyiv")
                    ),
                    note = "Rule: к = direction of movement (you are heading there). до = the endpoint reached or a boundary. " +
                            "Я иду к врачу — I'm on my way to the doctor's (focus: destination). " +
                            "Я дошёл до врача — I walked all the way to the doctor's (focus: reaching the endpoint)."
                )
            }

            // ── за vs через (time) ────────────────────────────────────────────
            item {
                PrepTableCard(
                    entries = listOf(
                        PrepEntry("за", "Accusative", "in (duration to complete — how long it took)", "Я прочитал книгу за два дня — I read the book in two days\nОн сделал это за час — He did it in an hour"),
                        PrepEntry("через", "Accusative", "in (time from now — when it will happen)", "Я приду через два дня — I'll come in two days\nОн позвонит через час — He'll call in an hour")
                    ),
                    note = "Rule: за + time = it took this long to complete (past action). " +
                            "через + time = this many units from now (future point). " +
                            "Both use Accusative case. The confusion is natural because English 'in an hour' covers both meanings."
                )
            }

            // ── перед vs до (temporal) ────────────────────────────────────────
            item {
                PrepTableCard(
                    entries = listOf(
                        PrepEntry("до", "Genitive", "before (any point before — general)", "до понедельника — before Monday\nдо встречи — before the meeting (in general)\nдо войны — before the war"),
                        PrepEntry("перед", "Instrumental", "right before (immediately preceding)", "перед встречей — right before the meeting (just as it started)\nперед сном — right before sleep\nперед отъездом — right before departure")
                    ),
                    note = "Rule: до = any time before a point (could be hours, days, or years before). " +
                            "перед = the moment immediately preceding an event (temporal adjacency). " +
                            "Я позвоню до встречи — I'll call before the meeting (could be hours earlier). " +
                            "Я позвоню перед встречей — I'll call right before the meeting (just before it starts)."
                )
            }

            item { Spacer(modifier = Modifier.height(16.dp)) }
        }
    }
}

@Composable
private fun PrepSectionHeader(title: String) {
    Column(modifier = Modifier.fillMaxWidth().padding(top = 16.dp, bottom = 4.dp)) {
        Text(title, style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.primary)
        HorizontalDivider(modifier = Modifier.padding(top = 4.dp), color = MaterialTheme.colorScheme.primary)
    }
}

@Composable
private fun PrepTableCard(entries: List<PrepEntry>, note: String? = null) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Column headers
            Row(modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp)) {
                Text("Prep", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(0.7f))
                Text("Case", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.1f))
                Text("English / Russian Example", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(2.2f))
            }
            HorizontalDivider(modifier = Modifier.padding(bottom = 4.dp))

            entries.forEachIndexed { i, entry ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            if (i % 2 == 0) MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
                            else MaterialTheme.colorScheme.surface
                        )
                        .padding(vertical = 6.dp)
                ) {
                    Text(
                        text = entry.prep,
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.weight(0.7f)
                    )
                    Text(
                        text = entry.case,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier.weight(1.1f)
                    )
                    Column(modifier = Modifier.weight(2.2f)) {
                        Text(
                            text = entry.english,
                            style = MaterialTheme.typography.bodySmall,
                            fontStyle = FontStyle.Italic
                        )
                        Text(
                            text = entry.russian,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }

            if (note != null) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = note,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontStyle = FontStyle.Italic
                )
            }
        }
    }
}
