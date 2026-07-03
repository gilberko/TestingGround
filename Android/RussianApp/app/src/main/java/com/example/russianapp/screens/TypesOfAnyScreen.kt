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
fun TypesOfAnyScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Types Of Any, Types Of Every") },
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

            // ── что-то ───────────────────────────────────────────────────────
            item { AnySectionHeader("что-то, что-нибудь, что-либо") }
            item {
                AnyCard(title = "что-то — something (it definitely exists)") {
                    Text(
                        text = "Use что-то when the speaker knows something exists but doesn't know exactly what it is, " +
                                "or refers to a specific but vague thing. Most common in statements and past-tense contexts.",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    AnyExampleRow("Кто-то звонил.", "Someone called. (I know it happened, but not who)")
                    AnyExampleRow("Он сказал что-то интересное.", "He said something interesting.")
                    AnyExampleRow("В холодильнике есть что-то.", "There's something in the fridge.")
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "Key signal: a real, definite (if unknown) thing already exists in the situation.",
                        style = MaterialTheme.typography.bodySmall,
                        fontStyle = FontStyle.Italic,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            // ── что-нибудь ───────────────────────────────────────────────────
            item {
                AnyCard(title = "что-нибудь — anything (open / hypothetical)") {
                    Text(
                        text = "Use что-нибудь in questions, imperatives, conditionals, and future-tense sentences — " +
                                "when the outcome is uncertain or the speaker doesn't care which specific thing. " +
                                "\"Any one will do.\"",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    AnyExampleRow("Ты хочешь что-нибудь поесть?", "Do you want anything to eat? (question)")
                    AnyExampleRow("Скажи что-нибудь.", "Say something. (imperative — anything at all)")
                    AnyExampleRow("Если найдёшь что-нибудь интересное, покажи мне.", "If you find anything interesting, show me. (conditional)")
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "Key signal: question mark, imperative verb, если/когда clause, or future tense.",
                        style = MaterialTheme.typography.bodySmall,
                        fontStyle = FontStyle.Italic,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            // ── что-либо ─────────────────────────────────────────────────────
            item {
                AnyCard(title = "что-либо — anything at all (formal / literary)") {
                    Text(
                        text = "что-либо is interchangeable with что-нибудь but carries a more formal or literary register. " +
                                "It often stresses \"anything whatsoever\" and is common in written language, official speech, or legal contexts.",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    AnyExampleRow("Если вы знаете что-либо об этом деле…", "If you know anything at all about this matter…")
                    AnyExampleRow("Не существует что-либо подобного.", "Nothing of the sort exists. (formal)")
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "In everyday speech что-нибудь is far more common; что-либо sounds bookish.",
                        style = MaterialTheme.typography.bodySmall,
                        fontStyle = FontStyle.Italic,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            // ── Quick comparison ─────────────────────────────────────────────
            item {
                AnyCard(title = "Quick comparison") {
                    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp)) {
                        Text("Word", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.3f))
                        Text("Register", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(0.9f))
                        Text("Use when…", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.8f))
                    }
                    HorizontalDivider(modifier = Modifier.padding(bottom = 4.dp))
                    listOf(
                        Triple("что-то",      "Neutral", "You know it exists, but not what"),
                        Triple("что-нибудь",  "Neutral", "Questions, imperatives, future, conditionals"),
                        Triple("что-либо",    "Formal",  "Same as что-нибудь but literary/official")
                    ).forEachIndexed { i, (word, reg, use) ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(
                                    if (i % 2 == 0) MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
                                    else MaterialTheme.colorScheme.surface
                                )
                                .padding(vertical = 5.dp)
                        ) {
                            Text(word, style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary, modifier = Modifier.weight(1.3f))
                            Text(reg, style = MaterialTheme.typography.bodySmall, modifier = Modifier.weight(0.9f))
                            Text(use, style = MaterialTheme.typography.bodySmall, modifier = Modifier.weight(1.8f))
                        }
                    }
                }
            }

            // ── Other question words ──────────────────────────────────────────
            item { AnySectionHeader("The same pattern: где, кто, куда + suffix") }
            item {
                AnyCard(title = "Suffix rule — applies to any question word") {
                    Text(
                        text = "The suffixes -то, -нибудь, and -либо attach to all question words the same way. " +
                                "The meaning rule is identical: -то for something definite-but-unknown, " +
                                "-нибудь for something open/hypothetical, -либо for the formal equivalent of -нибудь.",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp)) {
                        Text("Base word", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.2f))
                        Text("+ -то", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.4f))
                        Text("+ -нибудь", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.4f))
                    }
                    HorizontalDivider(modifier = Modifier.padding(bottom = 4.dp))

                    listOf(
                        Triple("что (what)",       "что-то\n(something)",          "что-нибудь\n(anything)"),
                        Triple("где (where)",      "где-то\n(somewhere)",          "где-нибудь\n(anywhere)"),
                        Triple("кто (who)",        "кто-то\n(someone)",            "кто-нибудь\n(anyone)"),
                        Triple("куда (to where)",  "куда-то\n(somewhere, to)",     "куда-нибудь\n(anywhere, to)")
                    ).forEachIndexed { i, (base, sto, snibud) ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(
                                    if (i % 2 == 0) MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
                                    else MaterialTheme.colorScheme.surface
                                )
                                .padding(vertical = 5.dp)
                        ) {
                            Text(base,   style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.secondary, modifier = Modifier.weight(1.2f))
                            Text(sto,    style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.4f))
                            Text(snibud, style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.4f))
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "куда vs где: где states or asks about a location (where is it?), " +
                                "куда states or asks about a destination (where to?).",
                        style = MaterialTheme.typography.bodySmall,
                        fontStyle = FontStyle.Italic,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    // Highlight card — куда-то vs где-то
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text(
                                text = "куда-то vs где-то:",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = "Он ушёл куда-то.",
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                            Text(
                                text = "He went off somewhere. (movement to a destination)",
                                style = MaterialTheme.typography.bodySmall,
                                fontStyle = FontStyle.Italic,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "Он был где-то.",
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                            Text(
                                text = "He was somewhere. (static location)",
                                style = MaterialTheme.typography.bodySmall,
                                fontStyle = FontStyle.Italic,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        }
                    }
                }
            }

            // ── кое- prefix ──────────────────────────────────────────────────
            item { AnySectionHeader("кое- prefix — something specific, withheld") }
            item {
                AnyCard(title = "кое- prefix — known to the speaker, hidden from the listener") {
                    Text(
                        text = "кое- is a prefix (prepended to the question word, unlike the -то/-нибудь suffixes). " +
                                "It signals that the speaker has a specific referent in mind but is deliberately not revealing it — " +
                                "implying suspense, teasing, or secrecy. The speaker knows; the listener doesn't.",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    AnyExampleRow("Я знаю кое-что интересное.", "I know something interesting. (I know exactly what — just not telling you yet)")
                    AnyExampleRow("Мне нужно сказать тебе кое-что важное.", "I need to tell you something important. (building anticipation)")
                    AnyExampleRow("Я купил кое-что для тебя в подарок.", "I bought something for you as a gift. (a surprise I'm keeping secret)")
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "Key signal: the speaker knows exactly what it is. The vagueness is intentional — aimed at the listener.",
                        style = MaterialTheme.typography.bodySmall,
                        fontStyle = FontStyle.Italic,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            item {
                AnyCard(title = "кое-кто — a certain someone (I know who, but I'm not saying)") {
                    Text(
                        text = "The same кое- mechanic applied to кто (who). The speaker has a specific person in mind " +
                                "but won't name them.",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    AnyExampleRow("Кое-кто уже знает об этом.", "A certain someone already knows about this.")
                    AnyExampleRow("Я видел кое-кого вчера.", "I saw a certain someone yesterday.")
                    AnyExampleRow("Кое-кто обещал помочь.", "A certain someone promised to help.")
                }
            }

            item {
                AnyCard(title = "что-то vs кое-что — a critical distinction") {
                    // Highlight card
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text(
                                text = "что-то vs кое-что:",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = "Я нашёл что-то на полу.",
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                            Text(
                                text = "I found something on the floor. (I don't know what it is)",
                                style = MaterialTheme.typography.bodySmall,
                                fontStyle = FontStyle.Italic,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "Я знаю кое-что об этом.",
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                            Text(
                                text = "I know something about this. (I know exactly what — I'm just not telling you)",
                                style = MaterialTheme.typography.bodySmall,
                                fontStyle = FontStyle.Italic,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        }
                    }
                }
            }

            // ── Everyone, Everything, Everywhere, Always — весь ─────────────────
            item { AnySectionHeader("Everyone, Everything, Everywhere, Always — весь") }
            item {
                AnyCard(title = "весь — the universal pronoun-adjective") {
                    Text(
                        text = "весь means \"all / the whole (of) / every-\". It declines like an adjective, agreeing in " +
                                "gender, number, and case with whatever it modifies — or standing alone to mean " +
                                "\"everyone\" (все) or \"everything\" (всё).",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    AnyExampleRow("Весь день шёл дождь.", "It rained the whole day. (masc., modifying день)")
                    AnyExampleRow("Вся семья собралась.", "The whole family gathered. (fem., modifying семья)")
                    AnyExampleRow("Всё готово.", "Everything is ready. (neut., standalone = \"everything\")")
                    AnyExampleRow("Все пришли.", "Everyone came. (plural, standalone = \"everyone\")")
                }
            }

            item {
                AnyCard(title = "весь — full declension (all 6 cases)") {
                    Text(
                        text = "Like any adjective, весь changes for all six cases and all four gender/number forms:",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp)) {
                        Text("Case", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
                        Text("Masc", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.1f))
                        Text("Fem", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.1f))
                        Text("Neut", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.1f))
                        Text("Plural", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.3f))
                    }
                    HorizontalDivider(modifier = Modifier.padding(bottom = 4.dp))
                    listOf(
                        listOf("Nom.",   "весь",          "вся",         "всё",        "все"),
                        listOf("Gen.",   "всего",         "всей",        "всего",      "всех"),
                        listOf("Dat.",   "всему",         "всей",        "всему",      "всем"),
                        listOf("Acc.",   "весь / всего*", "всю",         "всё",        "все / всех*"),
                        listOf("Instr.", "всем",          "всей (всею)", "всем",       "всеми"),
                        listOf("Prep.",  "(обо) всём",    "(обо) всей",  "(обо) всём", "(обо) всех")
                    ).forEachIndexed { i, row ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(
                                    if (i % 2 == 0) MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
                                    else MaterialTheme.colorScheme.surface
                                )
                                .padding(vertical = 5.dp)
                        ) {
                            Text(row[0], style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary, modifier = Modifier.weight(1f))
                            Text(row[1], style = MaterialTheme.typography.bodySmall, modifier = Modifier.weight(1.1f))
                            Text(row[2], style = MaterialTheme.typography.bodySmall, modifier = Modifier.weight(1.1f))
                            Text(row[3], style = MaterialTheme.typography.bodySmall, modifier = Modifier.weight(1.1f))
                            Text(row[4], style = MaterialTheme.typography.bodySmall, modifier = Modifier.weight(1.3f))
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "* Masc. singular and plural have two accusative forms depending on whether the noun is animate: " +
                                "inanimate → same as nominative (Я убрал весь снег. — I cleared away all the snow.); " +
                                "animate → same as genitive (Он позвал всех гостей. — He called all the guests.).",
                        style = MaterialTheme.typography.bodySmall,
                        fontStyle = FontStyle.Italic,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            item {
                AnyCard(title = "всех vs всем — the critical contrast") {
                    Text(
                        text = "Both всех and всем mean roughly \"everyone\", but they are different cases and answer " +
                                "different questions — mixing them up is one of the most common learner mistakes.",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text(
                                text = "всех — genitive / animate-accusative plural (\"of everyone\" / object \"everyone\"):",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text("У всех есть телефон.", style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onPrimaryContainer)
                            Text("Everyone has a phone. (genitive — \"at all [of them] there is…\")", style = MaterialTheme.typography.bodySmall, fontStyle = FontStyle.Italic, color = MaterialTheme.colorScheme.onPrimaryContainer)
                            Spacer(modifier = Modifier.height(6.dp))
                            Text("Он поздравил всех.", style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onPrimaryContainer)
                            Text("He congratulated everyone. (accusative — people are animate, so acc. = gen.)", style = MaterialTheme.typography.bodySmall, fontStyle = FontStyle.Italic, color = MaterialTheme.colorScheme.onPrimaryContainer)
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                text = "всем — dative plural (\"to / for everyone\"):",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text("Он раздал подарки всем.", style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onPrimaryContainer)
                            Text("He handed out gifts to everyone. (dative — indirect object)", style = MaterialTheme.typography.bodySmall, fontStyle = FontStyle.Italic, color = MaterialTheme.colorScheme.onPrimaryContainer)
                            Spacer(modifier = Modifier.height(6.dp))
                            Text("Спасибо всем за помощь.", style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onPrimaryContainer)
                            Text("Thanks to everyone for the help. (спасибо governs dative)", style = MaterialTheme.typography.bodySmall, fontStyle = FontStyle.Italic, color = MaterialTheme.colorScheme.onPrimaryContainer)
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    AnyExampleRow("Он думает обо всех.", "He thinks about everyone. (prepositional, after о/обо)")
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Rule of thumb: всех answers \"of whom / whom\" (genitive, or accusative for an animate object); " +
                                "всем answers \"to / for whom\" (dative).",
                        style = MaterialTheme.typography.bodySmall,
                        fontStyle = FontStyle.Italic,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            item {
                AnyCard(title = "всего vs всему — same logic, for \"everything\"") {
                    Text(
                        text = "The same genitive/dative split applies to всё (\"everything\"): всего = genitive, всему = dative.",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    AnyExampleRow("У меня всего достаточно.", "I have enough of everything. (genitive)")
                    AnyExampleRow("Он рад всему.", "He is glad about everything. (dative — рад + dative)")
                    AnyExampleRow("Он привык ко всему.", "He's used to everything. (dative — к/ко + dative)")
                }
            }

            item {
                AnyCard(title = "всё — everything, in every case") {
                    Text(
                        text = "A full run through all six cases of всё in natural sentences:",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    AnyExampleRow("Всё хорошо.", "Everything is fine. (nominative)")
                    AnyExampleRow("Я вижу всё.", "I see everything. (accusative)")
                    AnyExampleRow("Я боюсь всего.", "I'm afraid of everything. (genitive)")
                    AnyExampleRow("Он удивляется всему.", "He's amazed by everything. (dative)")
                    AnyExampleRow("Он доволен всем.", "He's satisfied with everything. (instrumental)")
                    AnyExampleRow("Он думает обо всём.", "He thinks about everything. (prepositional)")
                }
            }

            item {
                AnyCard(title = "все — everyone, in every case") {
                    Text(
                        text = "And the same run through for все (\"everyone\"):",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    AnyExampleRow("Здесь собрались все.", "Everyone has gathered here. (nominative)")
                    AnyExampleRow("Учитель видит всех.", "The teacher sees everyone. (accusative, animate)")
                    AnyExampleRow("У всех разные мнения.", "Everyone has different opinions. (genitive)")
                    AnyExampleRow("Всем нравится этот фильм.", "Everyone likes this film. (dative — нравится + dative)")
                    AnyExampleRow("Он гордится всеми.", "He is proud of everyone. (instrumental — гордиться + instrumental)")
                    AnyExampleRow("Он заботится обо всех.", "He cares about everyone. (prepositional)")
                }
            }

            // ── Everywhere & Always ──────────────────────────────────────────────
            item { AnySectionHeader("Everywhere & Always — indeclinable adverbs") }
            item {
                AnyCard(title = "везде, всюду — everywhere (indeclinable)") {
                    Text(
                        text = "Unlike весь, везде and всюду are adverbs — they never change form for case, gender, or number. " +
                                "Both mean \"everywhere\" and are largely interchangeable; всюду leans slightly more literary/emphatic.",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    AnyExampleRow("Везде тихо.", "It's quiet everywhere.")
                    AnyExampleRow("Я искал везде.", "I looked everywhere.")
                    AnyExampleRow("Всюду был снег.", "There was snow everywhere. (more literary tone)")
                    AnyExampleRow("Он всюду опаздывает.", "He's late everywhere he goes. (emphatic)")
                }
            }
            item {
                AnyCard(title = "всегда — always (indeclinable)") {
                    AnyExampleRow("Он всегда опаздывает.", "He's always late.")
                    AnyExampleRow("Я всегда рад тебя видеть.", "I'm always glad to see you.")
                    AnyExampleRow("Это всегда так.", "It's always like that.")
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "всегда never changes form — no case, no gender, no number. Unlike весь/все/всё/вся, it's a plain adverb.",
                        style = MaterialTheme.typography.bodySmall,
                        fontStyle = FontStyle.Italic,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
            item {
                AnyCard(title = "всегда vs каждый раз — always vs every single time") {
                    Text(
                        text = "всегда is a fixed adverb (a general habit or truth). каждый раз (\"every [single] time\") is built " +
                                "from каждый — a declinable adjective agreeing with раз (\"time/occasion\", masc.) — so this phrase can change form.",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    AnyExampleRow("Он всегда опаздывает.", "He's always late. (general habit, invariant)")
                    AnyExampleRow("Каждый раз он опаздывает.", "Every time, he's late. (same idea, declinable phrase)")
                    AnyExampleRow("С каждым разом становится легче.", "With each time, it gets easier. (instrumental — каждым разом)")
                    AnyExampleRow("Каждый раз, когда я прихожу…", "Every time I come… (nominative каждый раз as a time adverbial)")
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Three families, three behaviors: всегда/везде/всюду never decline; каждый declines like an adjective " +
                                "(каждый/каждая/каждое) agreeing with its noun; весь/все/всё/вся has the fullest declension, covered above.",
                        style = MaterialTheme.typography.bodySmall,
                        fontStyle = FontStyle.Italic,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            item { Spacer(modifier = Modifier.height(16.dp)) }
        }
    }
}

@Composable
private fun AnySectionHeader(title: String) {
    Column(modifier = Modifier.fillMaxWidth().padding(top = 16.dp, bottom = 4.dp)) {
        Text(title, style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.primary)
        HorizontalDivider(modifier = Modifier.padding(top = 4.dp), color = MaterialTheme.colorScheme.primary)
    }
}

@Composable
private fun AnyCard(title: String, content: @Composable ColumnScope.() -> Unit) {
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
private fun AnyExampleRow(russian: String, english: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Text(russian, style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold)
        Text(english, style = MaterialTheme.typography.bodySmall, fontStyle = FontStyle.Italic,
            color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
    Spacer(modifier = Modifier.height(2.dp))
}
