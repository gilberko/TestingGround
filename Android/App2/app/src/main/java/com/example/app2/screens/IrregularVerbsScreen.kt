package com.example.app2.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

// ─────────────────────────────────────────────────────────────────────────────
// Data: one verb → all tenses. Forms are space-separated in the order
// eu, tu, ele/ela/você, nós, vós, eles/elas/vocês. Imperatives have "—" for eu;
// negative imperatives are stored without "não" (it is added on display).
// ─────────────────────────────────────────────────────────────────────────────

private enum class IrrTense(val label: String, val pt: String) {
    PRES("Present", "Presente do Indicativo"),
    PERF("Past (completed)", "Pretérito Perfeito"),
    IMPF("Past (ongoing / habitual)", "Pretérito Imperfeito"),
    FUT("Future", "Futuro do Indicativo"),
    IMP_POS("Imperative (positive)", "Imperativo Afirmativo"),
    IMP_NEG("Imperative (negative)", "Imperativo Negativo"),
    COND("Conditional", "Condicional"),
    CPRES("Subjunctive present", "Presente do Conjuntivo"),
    CIMPF("Subjunctive past", "Pretérito Imperfeito do Conjuntivo"),
    CFUT("Subjunctive future", "Futuro do Conjuntivo")
}

private data class IrrVerb(
    val verb: String,
    val meaning: String,
    val forms: Map<IrrTense, String>
)

private fun irrVerb(verb: String, meaning: String, vararg f: Pair<IrrTense, String>) =
    IrrVerb(verb, meaning, f.toMap())

private val irrVerbs = listOf(
    irrVerb(
        "ser", "to be (permanent)",
        IrrTense.PRES to "sou és é somos sois são",
        IrrTense.PERF to "fui foste foi fomos fostes foram",
        IrrTense.IMPF to "era eras era éramos éreis eram",
        IrrTense.FUT to "serei serás será seremos sereis serão",
        IrrTense.IMP_POS to "— sê seja sejamos sede sejam",
        IrrTense.IMP_NEG to "— sejas seja sejamos sejais sejam",
        IrrTense.COND to "seria serias seria seríamos seríeis seriam",
        IrrTense.CPRES to "seja sejas seja sejamos sejais sejam",
        IrrTense.CIMPF to "fosse fosses fosse fôssemos fôsseis fossem",
        IrrTense.CFUT to "for fores for formos fordes forem"
    ),
    irrVerb(
        "estar", "to be (temporary / location)",
        IrrTense.PRES to "estou estás está estamos estais estão",
        IrrTense.PERF to "estive estiveste esteve estivemos estivestes estiveram",
        IrrTense.IMPF to "estava estavas estava estávamos estáveis estavam",
        IrrTense.FUT to "estarei estarás estará estaremos estareis estarão",
        IrrTense.IMP_POS to "— está esteja estejamos estai estejam",
        IrrTense.IMP_NEG to "— estejas esteja estejamos estejais estejam",
        IrrTense.COND to "estaria estarias estaria estaríamos estaríeis estariam",
        IrrTense.CPRES to "esteja estejas esteja estejamos estejais estejam",
        IrrTense.CIMPF to "estivesse estivesses estivesse estivéssemos estivésseis estivessem",
        IrrTense.CFUT to "estiver estiveres estiver estivermos estiverdes estiverem"
    ),
    irrVerb(
        "ir", "to go",
        IrrTense.PRES to "vou vais vai vamos ides vão",
        IrrTense.PERF to "fui foste foi fomos fostes foram",
        IrrTense.IMPF to "ia ias ia íamos íeis iam",
        IrrTense.FUT to "irei irás irá iremos ireis irão",
        IrrTense.IMP_POS to "— vai vá vamos ide vão",
        IrrTense.IMP_NEG to "— vás vá vamos vades vão",
        IrrTense.COND to "iria irias iria iríamos iríeis iriam",
        IrrTense.CPRES to "vá vás vá vamos vades vão",
        IrrTense.CIMPF to "fosse fosses fosse fôssemos fôsseis fossem",
        IrrTense.CFUT to "for fores for formos fordes forem"
    ),
    irrVerb(
        "vir", "to come",
        IrrTense.PRES to "venho vens vem vimos vindes vêm",
        IrrTense.PERF to "vim vieste veio viemos viestes vieram",
        IrrTense.IMPF to "vinha vinhas vinha vínhamos vínheis vinham",
        IrrTense.FUT to "virei virás virá viremos vireis virão",
        IrrTense.IMP_POS to "— vem venha venhamos vinde venham",
        IrrTense.IMP_NEG to "— venhas venha venhamos venhais venham",
        IrrTense.COND to "viria virias viria viríamos viríeis viriam",
        IrrTense.CPRES to "venha venhas venha venhamos venhais venham",
        IrrTense.CIMPF to "viesse viesses viesse viéssemos viésseis viessem",
        IrrTense.CFUT to "vier vieres vier viermos vierdes vierem"
    ),
    irrVerb(
        "sair", "to leave / go out",
        IrrTense.PRES to "saio sais sai saímos saís saem",
        IrrTense.PERF to "saí saíste saiu saímos saístes saíram",
        IrrTense.IMPF to "saía saías saía saíamos saíeis saíam",
        IrrTense.FUT to "sairei sairás sairá sairemos saireis sairão",
        IrrTense.IMP_POS to "— sai saia saiamos saí saiam",
        IrrTense.IMP_NEG to "— saias saia saiamos saiais saiam",
        IrrTense.COND to "sairia sairias sairia sairíamos sairíeis sairiam",
        IrrTense.CPRES to "saia saias saia saiamos saiais saiam",
        IrrTense.CIMPF to "saísse saísses saísse saíssemos saísseis saíssem",
        IrrTense.CFUT to "sair saíres sair sairmos sairdes saírem"
    ),
    irrVerb(
        "poder", "can / to be able",
        IrrTense.PRES to "posso podes pode podemos podeis podem",
        IrrTense.PERF to "pude pudeste pôde pudemos pudestes puderam",
        IrrTense.IMPF to "podia podias podia podíamos podíeis podiam",
        IrrTense.FUT to "poderei poderás poderá poderemos podereis poderão",
        IrrTense.IMP_POS to "— pode possa possamos podei possam",
        IrrTense.IMP_NEG to "— possas possa possamos possais possam",
        IrrTense.COND to "poderia poderias poderia poderíamos poderíeis poderiam",
        IrrTense.CPRES to "possa possas possa possamos possais possam",
        IrrTense.CIMPF to "pudesse pudesses pudesse pudéssemos pudésseis pudessem",
        IrrTense.CFUT to "puder puderes puder pudermos puderdes puderem"
    ),
    irrVerb(
        "saber", "to know",
        IrrTense.PRES to "sei sabes sabe sabemos sabeis sabem",
        IrrTense.PERF to "soube soubeste soube soubemos soubestes souberam",
        IrrTense.IMPF to "sabia sabias sabia sabíamos sabíeis sabiam",
        IrrTense.FUT to "saberei saberás saberá saberemos sabereis saberão",
        IrrTense.IMP_POS to "— sabe saiba saibamos sabei saibam",
        IrrTense.IMP_NEG to "— saibas saiba saibamos saibais saibam",
        IrrTense.COND to "saberia saberias saberia saberíamos saberíeis saberiam",
        IrrTense.CPRES to "saiba saibas saiba saibamos saibais saibam",
        IrrTense.CIMPF to "soubesse soubesses soubesse soubéssemos soubésseis soubessem",
        IrrTense.CFUT to "souber souberes souber soubermos souberdes souberem"
    ),
    irrVerb(
        "dar", "to give",
        IrrTense.PRES to "dou dás dá damos dais dão",
        IrrTense.PERF to "dei deste deu demos destes deram",
        IrrTense.IMPF to "dava davas dava dávamos dáveis davam",
        IrrTense.FUT to "darei darás dará daremos dareis darão",
        IrrTense.IMP_POS to "— dá dê demos dai deem",
        IrrTense.IMP_NEG to "— dês dê demos deis deem",
        IrrTense.COND to "daria darias daria daríamos daríeis dariam",
        IrrTense.CPRES to "dê dês dê demos deis deem",
        IrrTense.CIMPF to "desse desses desse déssemos désseis dessem",
        IrrTense.CFUT to "der deres der dermos derdes derem"
    ),
    irrVerb(
        "ter", "to have",
        IrrTense.PRES to "tenho tens tem temos tendes têm",
        IrrTense.PERF to "tive tiveste teve tivemos tivestes tiveram",
        IrrTense.IMPF to "tinha tinhas tinha tínhamos tínheis tinham",
        IrrTense.FUT to "terei terás terá teremos tereis terão",
        IrrTense.IMP_POS to "— tem tenha tenhamos tende tenham",
        IrrTense.IMP_NEG to "— tenhas tenha tenhamos tenhais tenham",
        IrrTense.COND to "teria terias teria teríamos teríeis teriam",
        IrrTense.CPRES to "tenha tenhas tenha tenhamos tenhais tenham",
        IrrTense.CIMPF to "tivesse tivesses tivesse tivéssemos tivésseis tivessem",
        IrrTense.CFUT to "tiver tiveres tiver tivermos tiverdes tiverem"
    ),
    irrVerb(
        "dizer", "to say / tell",
        IrrTense.PRES to "digo dizes diz dizemos dizeis dizem",
        IrrTense.PERF to "disse disseste disse dissemos dissestes disseram",
        IrrTense.IMPF to "dizia dizias dizia dizíamos dizíeis diziam",
        IrrTense.FUT to "direi dirás dirá diremos direis dirão",
        IrrTense.IMP_POS to "— diz diga digamos dizei digam",
        IrrTense.IMP_NEG to "— digas diga digamos digais digam",
        IrrTense.COND to "diria dirias diria diríamos diríeis diriam",
        IrrTense.CPRES to "diga digas diga digamos digais digam",
        IrrTense.CIMPF to "dissesse dissesses dissesse disséssemos dissésseis dissessem",
        IrrTense.CFUT to "disser disseres disser dissermos disserdes disserem"
    ),
    irrVerb(
        "trazer", "to bring",
        IrrTense.PRES to "trago trazes traz trazemos trazeis trazem",
        IrrTense.PERF to "trouxe trouxeste trouxe trouxemos trouxestes trouxeram",
        IrrTense.IMPF to "trazia trazias trazia trazíamos trazíeis traziam",
        IrrTense.FUT to "trarei trarás trará traremos trareis trarão",
        IrrTense.IMP_POS to "— traz traga tragamos trazei tragam",
        IrrTense.IMP_NEG to "— tragas traga tragamos tragais tragam",
        IrrTense.COND to "traria trarias traria traríamos traríeis trariam",
        IrrTense.CPRES to "traga tragas traga tragamos tragais tragam",
        IrrTense.CIMPF to "trouxesse trouxesses trouxesse trouxéssemos trouxésseis trouxessem",
        IrrTense.CFUT to "trouxer trouxeres trouxer trouxermos trouxerdes trouxerem"
    ),
    irrVerb(
        "fazer", "to do / make",
        IrrTense.PRES to "faço fazes faz fazemos fazeis fazem",
        IrrTense.PERF to "fiz fizeste fez fizemos fizestes fizeram",
        IrrTense.IMPF to "fazia fazias fazia fazíamos fazíeis faziam",
        IrrTense.FUT to "farei farás fará faremos fareis farão",
        IrrTense.IMP_POS to "— faz faça façamos fazei façam",
        IrrTense.IMP_NEG to "— faças faça façamos façais façam",
        IrrTense.COND to "faria farias faria faríamos faríeis fariam",
        IrrTense.CPRES to "faça faças faça façamos façais façam",
        IrrTense.CIMPF to "fizesse fizesses fizesse fizéssemos fizésseis fizessem",
        IrrTense.CFUT to "fizer fizeres fizer fizermos fizerdes fizerem"
    ),
    irrVerb(
        "ver", "to see",
        IrrTense.PRES to "vejo vês vê vemos vedes veem",
        IrrTense.PERF to "vi viste viu vimos vistes viram",
        IrrTense.IMPF to "via vias via víamos víeis viam",
        IrrTense.FUT to "verei verás verá veremos vereis verão",
        IrrTense.IMP_POS to "— vê veja vejamos vede vejam",
        IrrTense.IMP_NEG to "— vejas veja vejamos vejais vejam",
        IrrTense.COND to "veria verias veria veríamos veríeis veriam",
        IrrTense.CPRES to "veja vejas veja vejamos vejais vejam",
        IrrTense.CIMPF to "visse visses visse víssemos vísseis vissem",
        IrrTense.CFUT to "vir vires vir virmos virdes virem"
    ),
    irrVerb(
        "ficar", "to stay / become (regular, c → qu before e)",
        IrrTense.PRES to "fico ficas fica ficamos ficais ficam",
        IrrTense.PERF to "fiquei ficaste ficou ficámos ficastes ficaram",
        IrrTense.IMPF to "ficava ficavas ficava ficávamos ficáveis ficavam",
        IrrTense.FUT to "ficarei ficarás ficará ficaremos ficareis ficarão",
        IrrTense.IMP_POS to "— fica fique fiquemos ficai fiquem",
        IrrTense.IMP_NEG to "— fiques fique fiquemos fiqueis fiquem",
        IrrTense.COND to "ficaria ficarias ficaria ficaríamos ficaríeis ficariam",
        IrrTense.CPRES to "fique fiques fique fiquemos fiqueis fiquem",
        IrrTense.CIMPF to "ficasse ficasses ficasse ficássemos ficásseis ficassem",
        IrrTense.CFUT to "ficar ficares ficar ficarmos ficardes ficarem"
    ),
    irrVerb(
        "chegar", "to arrive (regular, g → gu before e)",
        IrrTense.PRES to "chego chegas chega chegamos chegais chegam",
        IrrTense.PERF to "cheguei chegaste chegou chegámos chegastes chegaram",
        IrrTense.IMPF to "chegava chegavas chegava chegávamos chegáveis chegavam",
        IrrTense.FUT to "chegarei chegarás chegará chegaremos chegareis chegarão",
        IrrTense.IMP_POS to "— chega chegue cheguemos chegai cheguem",
        IrrTense.IMP_NEG to "— chegues chegue cheguemos chegueis cheguem",
        IrrTense.COND to "chegaria chegarias chegaria chegaríamos chegaríeis chegariam",
        IrrTense.CPRES to "chegue chegues chegue cheguemos chegueis cheguem",
        IrrTense.CIMPF to "chegasse chegasses chegasse chegássemos chegásseis chegassem",
        IrrTense.CFUT to "chegar chegares chegar chegarmos chegardes chegarem"
    )
)

private val irrByName = irrVerbs.associateBy { it.verb }

/** Which verbs to list in each tense, and what to say about the rest. */
private data class IrrTenseInfo(
    val tense: IrrTense,
    val irregular: List<String>,
    val spelling: List<String>,
    val note: String
)

private val allIrr = listOf("ser", "estar", "ir", "vir", "sair", "poder", "saber", "dar", "ter", "dizer", "trazer", "fazer", "ver")

private val irrTenseInfos = listOf(
    IrrTenseInfo(
        IrrTense.PRES, allIrr, emptyList(),
        "ficar and chegar are regular here (fico, chego). Many irregular verbs are irregular only in the eu form: faço, digo, trago, posso, saio, vejo."
    ),
    IrrTenseInfo(
        IrrTense.PERF, allIrr, listOf("ficar", "chegar"),
        "ser and ir share the same forms (fui = I was / I went). EP writes the nós form with an accent in -ar verbs (ficámos, chegámos) to tell it apart from the present (ficamos). ficar / chegar change only in the eu form: fiquei, cheguei."
    ),
    IrrTenseInfo(
        IrrTense.IMPF, listOf("ser", "ter", "vir"), emptyList(),
        "Only ser, ter, vir (and pôr: punha) are truly irregular here. Every other verb is regular: estava, ia, saía, podia, sabia, dava, dizia, trazia, fazia, via, ficava, chegava."
    ),
    IrrTenseInfo(
        IrrTense.FUT, listOf("dizer", "trazer", "fazer"), emptyList(),
        "Only dizer, trazer and fazer have a short stem (dir-, trar-, far-). All the others add -ei, -ás, -á… to the infinitive: serei, estarei, irei, virei, poderei, terei… In speech, ir + infinitive (vou fazer) is more common than the simple future."
    ),
    IrrTenseInfo(
        IrrTense.IMP_POS, allIrr, listOf("ficar", "chegar"),
        "tu form = the ele form of the present (diz, faz, traz, vem, tem, vai, fica). você / nós / vocês forms come from the subjunctive present (diga, digamos, digam). The vós form (sede, ide, vinde) is archaic. poder's imperative is practically unused; the forms are shown only for completeness."
    ),
    IrrTenseInfo(
        IrrTense.IMP_NEG, allIrr, listOf("ficar", "chegar"),
        "ALL persons take the subjunctive present: não digas, não vás, não sejas, não fiques. The tu form is the one learners get wrong: Faz! but Não faças!"
    ),
    IrrTenseInfo(
        IrrTense.COND, listOf("dizer", "trazer", "fazer"), emptyList(),
        "Same short stems as the future (diria, traria, faria). All others are regular: seria, estaria, iria, poderia, teria… In speech the imperfeito often replaces it: Podias ajudar-me? = Could you help me?"
    ),
    IrrTenseInfo(
        IrrTense.CPRES, allIrr, listOf("ficar", "chegar"),
        "Most are built from the eu form of the present minus -o: faço → faça, digo → diga, tenho → tenha, venho → venha, posso → possa. Fully irregular: seja, esteja, vá, saiba, dê. ficar / chegar: fique, chegue."
    ),
    IrrTenseInfo(
        IrrTense.CIMPF, allIrr.filter { it != "sair" }, listOf("sair"),
        "Built from the eles form of the pretérito perfeito minus -ram, + -sse: fizeram → fizesse, tiveram → tivesse, foram → fosse, disseram → dissesse. That's why ser and ir share forms (fosse). sair just keeps its accent: saísse."
    ),
    IrrTenseInfo(
        IrrTense.CFUT, allIrr.filter { it != "sair" }, listOf("sair"),
        "Same stem as the conj. imperfeito: fizeram → fizer, tiveram → tiver, foram → for, viram → vir (ver!). For regular verbs it looks like the infinitive: quando chegar, se ficares."
    )
)

private val haverForms = listOf(
    "Presente" to "há",
    "Pret. Perfeito" to "houve",
    "Pret. Imperfeito" to "havia",
    "Futuro" to "haverá",
    "Condicional" to "haveria",
    "Conj. Presente" to "haja",
    "Conj. Imperfeito" to "houvesse",
    "Conj. Futuro" to "houver"
)

private fun irrForms(verb: IrrVerb, tense: IrrTense): List<String> {
    val raw = verb.forms[tense]?.split(" ") ?: List(6) { "—" }
    return if (tense == IrrTense.IMP_NEG) raw.map { if (it == "—") it else "não $it" } else raw
}

// ─────────────────────────────────────────────────────────────────────────────
// UI
// ─────────────────────────────────────────────────────────────────────────────

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IrregularVerbsScreen(onBack: () -> Unit) {
    var tab by rememberSaveable { mutableIntStateOf(0) }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Irregular Verbs") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
            TabRow(selectedTabIndex = tab) {
                Tab(selected = tab == 0, onClick = { tab = 0 }, text = { Text("By Tense") })
                Tab(selected = tab == 1, onClick = { tab = 1 }, text = { Text("By Verb") })
            }
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                item { Spacer(modifier = Modifier.height(4.dp)) }
                if (tab == 0) {
                    item {
                        Text(
                            "Each card lists the verbs that are irregular in that tense. Verbs marked \"spelling\" are regular but change a letter to keep the sound (c → qu, g → gu, accent on i).",
                            style = MaterialTheme.typography.bodySmall,
                            fontStyle = FontStyle.Italic,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    items(irrTenseInfos) { IrrTenseCard(it) }
                } else {
                    items(irrVerbs) { IrrVerbCard(it) }
                    item { IrrHaverCard() }
                }
                item { Spacer(modifier = Modifier.height(16.dp)) }
            }
        }
    }
}

@Composable
private fun IrrSubjectHeader(firstColWeight: Float) {
    listOf(listOf("eu", "tu", "ele/você"), listOf("nós", "vós", "eles/vocês")).forEach { trio ->
        Row(modifier = Modifier.fillMaxWidth()) {
            Spacer(modifier = Modifier.weight(firstColWeight))
            trio.forEach {
                Text(
                    it,
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
private fun IrrFormRows(label: String, tag: String, forms: List<String>, firstColWeight: Float) {
    Column(modifier = Modifier.padding(vertical = 2.dp)) {
        forms.chunked(3).forEachIndexed { i, trio ->
            Row(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.weight(firstColWeight)) {
                    if (i == 0) {
                        Text(label, style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold)
                    } else if (tag.isNotEmpty()) {
                        Text(tag, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.tertiary)
                    }
                }
                trio.forEach {
                    Text(
                        it,
                        style = MaterialTheme.typography.bodySmall,
                        fontStyle = FontStyle.Italic,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}

@Composable
private fun IrrTenseCard(info: IrrTenseInfo) {
    ElevatedCard(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(info.tense.label, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            Text(
                info.tense.pt,
                style = MaterialTheme.typography.bodySmall,
                fontStyle = FontStyle.Italic,
                color = MaterialTheme.colorScheme.primary
            )
            HorizontalDivider(modifier = Modifier.padding(vertical = 6.dp))
            IrrSubjectHeader(firstColWeight = 0.8f)
            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))
            val rows = info.irregular.map { it to "" } + info.spelling.map { it to "spelling" }
            rows.forEachIndexed { index, (name, tag) ->
                val verb = irrByName.getValue(name)
                IrrFormRows(name, tag, irrForms(verb, info.tense), firstColWeight = 0.8f)
                if (index < rows.lastIndex) HorizontalDivider(modifier = Modifier.padding(vertical = 2.dp))
            }
            Text(
                info.note,
                style = MaterialTheme.typography.bodySmall,
                fontStyle = FontStyle.Italic,
                color = MaterialTheme.colorScheme.tertiary,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}

@Composable
private fun IrrVerbCard(verb: IrrVerb) {
    ElevatedCard(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                "${verb.verb} — ${verb.meaning}",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            HorizontalDivider(modifier = Modifier.padding(vertical = 6.dp))
            IrrSubjectHeader(firstColWeight = 1.1f)
            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))
            IrrTense.entries.forEachIndexed { index, tense ->
                IrrFormRows(tense.label, "", irrForms(verb, tense), firstColWeight = 1.1f)
                if (index < IrrTense.entries.lastIndex) HorizontalDivider(modifier = Modifier.padding(vertical = 2.dp))
            }
        }
    }
}

@Composable
private fun IrrHaverCard() {
    ElevatedCard(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                "haver — there to be (existential)",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                "Used only in the 3rd person singular (impersonal)",
                style = MaterialTheme.typography.bodySmall,
                fontStyle = FontStyle.Italic,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            HorizontalDivider(modifier = Modifier.padding(vertical = 6.dp))
            haverForms.forEach { (tense, form) ->
                Row(modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp)) {
                    Text(tense, style = MaterialTheme.typography.bodySmall, modifier = Modifier.weight(1.3f))
                    Text(
                        form,
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Italic,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}
