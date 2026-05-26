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

private data class PfxEntry(val verb: String, val english: String)
private data class PfxGroup(val prefix: String, val meaning: String, val verbs: List<PfxEntry>)
private data class PfxConnEntry(
    val prefix: String,
    val spatialMeaning: String,
    val verb: String,
    val english: String
)
private data class PfxMotionEntry(
    val prefix: String,
    val perfective: String,
    val imperfective: String,
    val meaning: String
)

private val pfxConnEntries = listOf(
    PfxConnEntry("в / во", "into", "войти / внести", "enter / bring in"),
    PfxConnEntry("вы-", "out (pairs with в)", "выйти / вынести", "go out / carry out"),
    PfxConnEntry("из-", "out of / from inside", "извлечь / изучить", "extract / study thoroughly"),
    PfxConnEntry("на-", "onto / completion", "написать / наклеить", "write / paste on"),
    PfxConnEntry("за-", "behind / beyond / start", "зайти / закрыть", "stop by / close"),
    PfxConnEntry("от-", "away from", "отойти / отрезать", "step away / cut off"),
    PfxConnEntry("до-", "up to / as far as", "дойти / дочитать", "reach / finish reading"),
    PfxConnEntry("под-", "under / up toward", "подойти / подписать", "approach / sign"),
    PfxConnEntry("при-", "at / near / arrival", "прийти / прикрепить", "arrive / attach"),
    PfxConnEntry("пере-", "across / over / re-", "перейти / переписать", "cross / rewrite"),
    PfxConnEntry("по-", "along / start of motion", "пойти / поговорить", "set off on foot / have a chat"),
    PfxConnEntry("у-", "away (departure)", "уйти / убрать", "leave / put away"),
    PfxConnEntry("про-", "through / past", "пройти / прочитать", "walk past / read through"),
    PfxConnEntry("об- / о-", "around / covering", "обойти / описать", "walk around / describe"),
    PfxConnEntry("раз- / рас-", "apart / undo / onset", "разделить / раскрыть", "divide / open up / reveal"),
    PfxConnEntry("с-", "together / down from", "собрать / сделать", "gather / do (make)")
)

private val pfxGroups = listOf(
    PfxGroup("по-", "start / brief action / set off", listOf(
        PfxEntry("пойти", "set off on foot (begin going in one direction)"),
        PfxEntry("поехать", "set off by vehicle"),
        PfxEntry("поговорить", "have a chat (brief, bounded talk)"),
        PfxEntry("поесть", "have a bite to eat"),
        PfxEntry("посидеть", "sit for a while"),
        PfxEntry("посмотреть", "take a look / watch (briefly)"),
        PfxEntry("полюбить", "come to love (beginning of feeling)")
    )),
    PfxGroup("за-", "beginning / going behind / closing / booking", listOf(
        PfxEntry("зайти", "stop by (also: go around the back) — зайти к кому-то"),
        PfxEntry("заплакать", "burst into tears (sudden onset)"),
        PfxEntry("запеть", "start singing"),
        PfxEntry("засмеяться", "start laughing / burst out laughing"),
        PfxEntry("закрыть", "close / shut"),
        PfxEntry("заполнить", "fill in (a form)"),
        PfxEntry("забронировать", "book / reserve"),
        PfxEntry("записать", "write down / record")
    )),
    PfxGroup("вы-", "out / extraction / achievement (pairs with в-)", listOf(
        PfxEntry("выйти", "go out"),
        PfxEntry("вынести", "carry out"),
        PfxEntry("вылететь", "fly out"),
        PfxEntry("вырасти", "grow up"),
        PfxEntry("выучить", "learn thoroughly / memorize"),
        PfxEntry("выиграть", "win (emerge victorious)"),
        PfxEntry("вылечить", "cure completely"),
        PfxEntry("высказаться", "speak out / express oneself fully")
    )),
    PfxGroup("в- / во-", "into / inward (pairs with вы-)", listOf(
        PfxEntry("войти", "enter / go in"),
        PfxEntry("внести", "bring in / carry in"),
        PfxEntry("влететь", "fly in"),
        PfxEntry("вбежать", "run in"),
        PfxEntry("вчитаться", "read carefully into / get absorbed in reading"),
        PfxEntry("вникнуть", "delve into / understand in depth")
    )),
    PfxGroup("при-", "arrival / attachment / slight action", listOf(
        PfxEntry("прийти", "arrive (on foot)"),
        PfxEntry("приехать", "arrive (by transport)"),
        PfxEntry("прилететь", "arrive (by plane)"),
        PfxEntry("прибежать", "arrive running"),
        PfxEntry("прикрепить", "attach / fasten"),
        PfxEntry("пришить", "sew on"),
        PfxEntry("приклеить", "glue on / paste on"),
        PfxEntry("приоткрыть", "open slightly (ajar)"),
        PfxEntry("приподнять", "lift slightly"),
        PfxEntry("присесть", "sit down / squat (briefly)")
    )),
    PfxGroup("у-", "departure / removal / away (pairs with при-)", listOf(
        PfxEntry("уйти", "leave / go away (on foot)"),
        PfxEntry("уехать", "leave (by transport)"),
        PfxEntry("улететь", "fly away"),
        PfxEntry("убрать", "put away / clean up"),
        PfxEntry("унести", "carry away"),
        PfxEntry("убить", "kill (remove from life)"),
        PfxEntry("уменьшить", "reduce / diminish"),
        PfxEntry("успеть", "manage to do in time")
    )),
    PfxGroup("от-", "away from / completion / separation / opening", listOf(
        PfxEntry("отойти", "step away"),
        PfxEntry("отъехать", "drive away"),
        PfxEntry("открыть", "open (move away from closed state)"),
        PfxEntry("отрезать", "cut off"),
        PfxEntry("отломить", "break off"),
        PfxEntry("отработать", "finish working / work off (a debt)"),
        PfxEntry("отслужить", "finish one's service (in the army)")
    )),
    PfxGroup("пере-", "re- / across / over / too much", listOf(
        PfxEntry("перейти", "cross (on foot)"),
        PfxEntry("переехать", "cross by vehicle / move house"),
        PfxEntry("перелететь", "fly across"),
        PfxEntry("переписать", "rewrite"),
        PfxEntry("перечитать", "reread"),
        PfxEntry("пересмотреть", "reconsider / rewatch"),
        PfxEntry("перевести", "translate / transfer"),
        PfxEntry("перегрузить", "overload"),
        PfxEntry("переесть", "overeat")
    )),
    PfxGroup("про-", "through / past / duration / missing", listOf(
        PfxEntry("пройти", "walk through / walk past"),
        PfxEntry("проехать", "drive through / drive past / miss a stop"),
        PfxEntry("прочитать", "read through (finish reading)"),
        PfxEntry("прослушать", "listen through / miss (fail to catch)"),
        PfxEntry("проспать", "oversleep / sleep through"),
        PfxEntry("пропустить", "miss / let through / skip"),
        PfxEntry("провести", "spend (time) / conduct / lead through")
    )),
    PfxGroup("на-", "onto / accumulation / satiation", listOf(
        PfxEntry("написать", "write (complete)"),
        PfxEntry("нарисовать", "draw (complete)"),
        PfxEntry("наклеить", "paste / stick on"),
        PfxEntry("набрать", "gather / dial (a number) / score"),
        PfxEntry("накопить", "accumulate"),
        PfxEntry("наесться", "eat one's fill"),
        PfxEntry("насмотреться", "see enough / watch one's fill"),
        PfxEntry("начать", "begin / start (на + чать)")
    )),
    PfxGroup("из- / ис-", "out of / thoroughness / exhaustion", listOf(
        PfxEntry("изучить", "study thoroughly / master"),
        PfxEntry("издать", "publish (put out)"),
        PfxEntry("извлечь", "extract"),
        PfxEntry("изменить", "change / betray"),
        PfxEntry("исправить", "correct / fix"),
        PfxEntry("исписать", "use up (paper by writing)"),
        PfxEntry("испугать", "frighten (thoroughly)")
    )),
    PfxGroup("раз- / рас-", "apart / undoing / intensification / emotional onset", listOf(
        PfxEntry("разделить", "divide"),
        PfxEntry("разобрать", "take apart / analyze / understand"),
        PfxEntry("раскрыть", "open up / unfold / reveal (a secret)"),
        PfxEntry("расплакаться", "burst into tears"),
        PfxEntry("расхохотаться", "burst out laughing"),
        PfxEntry("разозлить", "anger / make furious"),
        PfxEntry("рассказать", "tell / narrate"),
        PfxEntry("расставить", "arrange / place (in order)")
    )),
    PfxGroup("с-", "together / down from / completion", listOf(
        PfxEntry("сделать", "do / make (completed)"),
        PfxEntry("собрать", "gather / assemble"),
        PfxEntry("сказать", "say (completed utterance)"),
        PfxEntry("сварить", "cook (by boiling) / weld"),
        PfxEntry("сойти", "come down / get off (transport)"),
        PfxEntry("съехать", "drive down / move out (of a flat)"),
        PfxEntry("сфотографировать", "photograph (take a photo)")
    )),
    PfxGroup("до-", "up to / completion / additional", listOf(
        PfxEntry("дойти", "reach / walk all the way to"),
        PfxEntry("доехать", "reach (by vehicle)"),
        PfxEntry("долететь", "reach (by plane)"),
        PfxEntry("дочитать", "finish reading"),
        PfxEntry("доесть", "finish eating"),
        PfxEntry("досмотреть", "finish watching"),
        PfxEntry("добавить", "add"),
        PfxEntry("доплатить", "pay additionally / pay the difference")
    )),
    PfxGroup("под-", "up to / slightly / from below", listOf(
        PfxEntry("подойти", "come up to / approach"),
        PfxEntry("подъехать", "drive up to"),
        PfxEntry("подбежать", "run up to"),
        PfxEntry("подумать", "think a bit / consider"),
        PfxEntry("подождать", "wait a bit"),
        PfxEntry("подписать", "sign (lit. write underneath)"),
        PfxEntry("подложить", "put under"),
        PfxEntry("поддержать", "support / hold up from below")
    )),
    PfxGroup("об- / о-", "around / thorough coverage", listOf(
        PfxEntry("обойти", "walk around"),
        PfxEntry("объехать", "drive around"),
        PfxEntry("облететь", "fly around"),
        PfxEntry("обыскать", "search thoroughly"),
        PfxEntry("описать", "describe"),
        PfxEntry("осмотреть", "examine / look around"),
        PfxEntry("обмануть", "deceive"),
        PfxEntry("обсудить", "discuss (go around a topic)")
    )),
    PfxGroup("вз- / вс-", "up / upward / sudden intensity", listOf(
        PfxEntry("взбежать", "run up (stairs)"),
        PfxEntry("вскочить", "jump up / leap to one's feet"),
        PfxEntry("взлететь", "take off / fly up"),
        PfxEntry("вздохнуть", "take a deep breath / sigh"),
        PfxEntry("вскрикнуть", "cry out / exclaim"),
        PfxEntry("взволновать", "excite / agitate")
    )),
    PfxGroup("недо-", "under / insufficiently", listOf(
        PfxEntry("недоспать", "not get enough sleep"),
        PfxEntry("недоесть", "not eat enough"),
        PfxEntry("недооценить", "underestimate"),
        PfxEntry("недопонять", "misunderstand / only half-understand"),
        PfxEntry("недоплатить", "underpay")
    ))
)

private val pfxMotionEntries = listOf(
    PfxMotionEntry("при-", "прийти", "приходить", "arrive (on foot)"),
    PfxMotionEntry("у-", "уйти", "уходить", "leave (on foot)"),
    PfxMotionEntry("вы-", "выйти", "выходить", "go out"),
    PfxMotionEntry("в-", "войти", "входить", "enter"),
    PfxMotionEntry("пере-", "перейти", "переходить", "cross"),
    PfxMotionEntry("до-", "дойти", "доходить", "reach / walk to"),
    PfxMotionEntry("под-", "подойти", "подходить", "approach"),
    PfxMotionEntry("за-", "зайти", "заходить", "stop by"),
    PfxMotionEntry("об-", "обойти", "обходить", "walk around"),
    PfxMotionEntry("от-", "отойти", "отходить", "step away"),
    PfxMotionEntry("про-", "пройти", "проходить", "walk through / past"),
    PfxMotionEntry("раз-", "разойтись", "расходиться", "go in different directions / disperse")
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VerbPrefixesScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Prefixes For Verbs") },
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

            // ── What Are Verb Prefixes? ────────────────────────────────────────
            item { PrefixSectionHeader("What Are Verb Prefixes?") }
            item {
                Card(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            "Russian verb prefixes are syllables attached to the beginning of a verb to modify or refine its meaning — direction, completion, beginning, repetition, emotional onset, and more.",
                            style = MaterialTheme.typography.bodySmall
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            "• Almost always perfective: Adding a prefix to a simple imperfective base typically creates a perfective verb.",
                            style = MaterialTheme.typography.bodySmall
                        )
                        Text(
                            "  писать (write, impf) → написать (write / finish writing, pf)",
                            style = MaterialTheme.typography.bodySmall,
                            fontStyle = FontStyle.Italic,
                            color = MaterialTheme.colorScheme.secondary
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            "• Secondary imperfectives: The new perfective can then generate an imperfective partner by adding a suffix (-ывать / -ивать / -вать):",
                            style = MaterialTheme.typography.bodySmall
                        )
                        Text(
                            "  записать (pf) → записывать (impf)   переписать (pf) → переписывать (impf)",
                            style = MaterialTheme.typography.bodySmall,
                            fontStyle = FontStyle.Italic,
                            color = MaterialTheme.colorScheme.secondary
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            "• Prefix = preposition: Many prefixes are the same words as spatial prepositions, and their directional meaning carries directly into the verb. войти (enter) = в (into) + идти (go).",
                            style = MaterialTheme.typography.bodySmall
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            "• Verbs of motion are special: they come in unidirectional / multidirectional pairs, and both forms stay in use after prefixing — see the dedicated section below.",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }

            // ── The Prefix–Preposition Connection ────────────────────────────
            item { PrefixSectionHeader("The Prefix–Preposition Connection") }
            item {
                Card(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp)) {
                            Text("Prefix", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(0.9f))
                            Text("Spatial meaning", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.1f))
                            Text("Example verbs", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.3f))
                            Text("English", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.7f))
                        }
                        HorizontalDivider(modifier = Modifier.padding(bottom = 4.dp))
                        pfxConnEntries.forEachIndexed { i, e ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(
                                        if (i % 2 == 0) MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
                                        else MaterialTheme.colorScheme.surface
                                    )
                                    .padding(vertical = 5.dp)
                            ) {
                                Text(e.prefix, style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.primary, modifier = Modifier.weight(0.9f))
                                Text(e.spatialMeaning, style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.secondary, modifier = Modifier.weight(1.1f))
                                Text(e.verb, style = MaterialTheme.typography.bodySmall, modifier = Modifier.weight(1.3f))
                                Text(e.english, style = MaterialTheme.typography.bodySmall, fontStyle = FontStyle.Italic,
                                    modifier = Modifier.weight(1.7f))
                            }
                        }
                    }
                }
            }

            // ── Prefix Reference ──────────────────────────────────────────────
            item { PrefixSectionHeader("Prefix Reference") }
            pfxGroups.forEach { group ->
                item { PrefixGroupCard(group) }
            }

            // ── Verbs of Motion — Special Case ────────────────────────────────
            item { PrefixSectionHeader("Verbs of Motion — Special Case") }
            item {
                Card(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            "Russian motion verbs come in two forms: unidirectional (a specific one-way trip: идти) and multidirectional (habitual or back-and-forth: ходить). When a prefix is added, both forms survive but change roles:",
                            style = MaterialTheme.typography.bodySmall
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            "• Prefixed unidirectional form → becomes the PERFECTIVE verb",
                            style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold
                        )
                        Text(
                            "  прийти (pf) = при + идти",
                            style = MaterialTheme.typography.bodySmall, fontStyle = FontStyle.Italic,
                            color = MaterialTheme.colorScheme.secondary
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            "• Prefixed multidirectional form → becomes the new IMPERFECTIVE verb",
                            style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold
                        )
                        Text(
                            "  приходить (impf) = при + ходить",
                            style = MaterialTheme.typography.bodySmall, fontStyle = FontStyle.Italic,
                            color = MaterialTheme.colorScheme.secondary
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            "The same pattern applies to all motion base pairs:",
                            style = MaterialTheme.typography.bodySmall
                        )
                        Text(
                            "ехать / ездить (by vehicle) → приехать / приезжать",
                            style = MaterialTheme.typography.bodySmall, fontStyle = FontStyle.Italic,
                            color = MaterialTheme.colorScheme.secondary
                        )
                        Text(
                            "лететь / летать (fly) → прилететь / прилетать",
                            style = MaterialTheme.typography.bodySmall, fontStyle = FontStyle.Italic,
                            color = MaterialTheme.colorScheme.secondary
                        )
                        Text(
                            "бежать / бегать (run) → прибежать / прибегать",
                            style = MaterialTheme.typography.bodySmall, fontStyle = FontStyle.Italic,
                            color = MaterialTheme.colorScheme.secondary
                        )
                        Text(
                            "нести / носить (carry) → принести / приносить",
                            style = MaterialTheme.typography.bodySmall, fontStyle = FontStyle.Italic,
                            color = MaterialTheme.colorScheme.secondary
                        )
                    }
                }
            }
            item {
                Card(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp)) {
                            Text("Prefix", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(0.7f))
                            Text("Perfective", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.1f))
                            Text("Imperfective", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.2f))
                            Text("Meaning", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.5f))
                        }
                        HorizontalDivider(modifier = Modifier.padding(bottom = 4.dp))
                        pfxMotionEntries.forEachIndexed { i, e ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(
                                        if (i % 2 == 0) MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
                                        else MaterialTheme.colorScheme.surface
                                    )
                                    .padding(vertical = 5.dp)
                            ) {
                                Text(e.prefix, style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.primary, modifier = Modifier.weight(0.7f))
                                Text(e.perfective, style = MaterialTheme.typography.bodySmall, modifier = Modifier.weight(1.1f))
                                Text(e.imperfective, style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.secondary, modifier = Modifier.weight(1.2f))
                                Text(e.meaning, style = MaterialTheme.typography.bodySmall, fontStyle = FontStyle.Italic,
                                    modifier = Modifier.weight(1.5f))
                            }
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            "Examples in use:",
                            style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold
                        )
                        Text(
                            "Он пришёл — He arrived / has arrived. (pf: completed event)",
                            style = MaterialTheme.typography.bodySmall, fontStyle = FontStyle.Italic,
                            color = MaterialTheme.colorScheme.secondary
                        )
                        Text(
                            "Он приходит каждый день — He comes every day. (impf: habit)",
                            style = MaterialTheme.typography.bodySmall, fontStyle = FontStyle.Italic,
                            color = MaterialTheme.colorScheme.secondary
                        )
                        Text(
                            "Самолёт прилетел — The plane landed. (pf: single event)",
                            style = MaterialTheme.typography.bodySmall, fontStyle = FontStyle.Italic,
                            color = MaterialTheme.colorScheme.secondary
                        )
                        Text(
                            "Самолёты прилетают сюда часто — Planes land here often. (impf: recurring)",
                            style = MaterialTheme.typography.bodySmall, fontStyle = FontStyle.Italic,
                            color = MaterialTheme.colorScheme.secondary
                        )
                    }
                }
            }

            // ── Do Prefixes Always Create Perfective Verbs? ───────────────────
            item { PrefixSectionHeader("Do Prefixes Always Create Perfective Verbs?") }
            item {
                Card(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            "The short answer: yes, almost always.",
                            style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            "• Bare imperfective + prefix → perfective:",
                            style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold
                        )
                        Text(
                            "  читать (impf) → прочитать (pf)\n  писать (impf) → написать (pf)\n  делать (impf) → сделать (pf)",
                            style = MaterialTheme.typography.bodySmall, fontStyle = FontStyle.Italic,
                            color = MaterialTheme.colorScheme.secondary
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            "• Secondary imperfectives (from suffixes -ывать / -ивать / -вать):",
                            style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold
                        )
                        Text(
                            "  записать (pf) → записывать (impf)\n  переписать (pf) → переписывать (impf)\n  подписать (pf) → подписывать (impf)",
                            style = MaterialTheme.typography.bodySmall, fontStyle = FontStyle.Italic,
                            color = MaterialTheme.colorScheme.secondary
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            "• вы- and в- — both create perfectives:",
                            style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold
                        )
                        Text(
                            "  войти (pf) ↔ входить (impf)\n  выйти (pf) ↔ выходить (impf)",
                            style = MaterialTheme.typography.bodySmall, fontStyle = FontStyle.Italic,
                            color = MaterialTheme.colorScheme.secondary
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            "• Verbs of motion: both aspects come from the prefixed pair — see section above.",
                            style = MaterialTheme.typography.bodySmall
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            "• Exceptions — stative verbs (states, not actions):",
                            style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold
                        )
                        Text(
                            "  зависеть (depend) — impf only, no meaningful pf.",
                            style = MaterialTheme.typography.bodySmall, fontStyle = FontStyle.Italic,
                            color = MaterialTheme.colorScheme.secondary
                        )
                        Text(
                            "  ненавидеть (hate) — impf only.",
                            style = MaterialTheme.typography.bodySmall, fontStyle = FontStyle.Italic,
                            color = MaterialTheme.colorScheme.secondary
                        )
                        Text(
                            "  надеяться (hope) — impf only.",
                            style = MaterialTheme.typography.bodySmall, fontStyle = FontStyle.Italic,
                            color = MaterialTheme.colorScheme.secondary
                        )
                        Text(
                            "  For such verbs, aspect is less relevant because they describe ongoing states rather than events or actions.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

            item { Spacer(modifier = Modifier.height(16.dp)) }
        }
    }
}

@Composable
private fun PrefixSectionHeader(title: String) {
    Column(modifier = Modifier.fillMaxWidth().padding(top = 16.dp, bottom = 4.dp)) {
        Text(title, style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.primary)
        HorizontalDivider(modifier = Modifier.padding(top = 4.dp), color = MaterialTheme.colorScheme.primary)
    }
}

@Composable
private fun PrefixGroupCard(group: PfxGroup) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = group.prefix,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = "  —  ${group.meaning}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
            HorizontalDivider(modifier = Modifier.padding(vertical = 6.dp))
            group.verbs.forEachIndexed { i, entry ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            if (i % 2 == 0) MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
                            else MaterialTheme.colorScheme.surface
                        )
                        .padding(vertical = 4.dp)
                ) {
                    Text(
                        text = entry.verb,
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.weight(0.9f)
                    )
                    Text(
                        text = entry.english,
                        style = MaterialTheme.typography.bodySmall,
                        fontStyle = FontStyle.Italic,
                        modifier = Modifier.weight(2.1f)
                    )
                }
            }
        }
    }
}
