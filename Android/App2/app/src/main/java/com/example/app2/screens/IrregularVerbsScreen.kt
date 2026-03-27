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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ElevatedCard
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

private data class TenseRow(
    val name: String,
    val eu: String,
    val tu: String,
    val ele: String,
    val nos: String,
    val vos: String,
    val eles: String
)

private data class IrregularVerb(
    val verb: String,
    val meaning: String,
    val tenses: List<TenseRow>
)

private val irregularVerbs = listOf(
    IrregularVerb(
        verb = "ser", meaning = "to be (permanent characteristics)",
        tenses = listOf(
            TenseRow("Presente",          "sou",    "és",     "é",     "somos",     "sois",     "são"),
            TenseRow("Pret. Perfeito",    "fui",    "foste",  "foi",   "fomos",     "fostes",   "foram"),
            TenseRow("Pret. Imperfeito",  "era",    "eras",   "era",   "éramos",    "éreis",    "eram"),
            TenseRow("Futuro",            "serei",  "serás",  "será",  "seremos",   "sereis",   "serão"),
            TenseRow("Condicional",       "seria",  "serias", "seria", "seríamos",  "seríeis",  "seriam"),
            TenseRow("Conj. Presente",    "seja",   "sejas",  "seja",  "sejamos",   "sejais",   "sejam"),
            TenseRow("Conj. Imperfeito",  "fosse",  "fosses", "fosse", "fôssemos",  "fôsseis",  "fossem"),
            TenseRow("Conj. Futuro",      "for",    "fores",  "for",   "formos",    "fordes",   "forem")
        )
    ),
    IrregularVerb(
        verb = "estar", meaning = "to be (states, locations)",
        tenses = listOf(
            TenseRow("Presente",          "estou",     "estás",      "está",      "estamos",      "estais",      "estão"),
            TenseRow("Pret. Perfeito",    "estive",    "estiveste",  "esteve",    "estivemos",    "estivestes",  "estiveram"),
            TenseRow("Pret. Imperfeito",  "estava",    "estavas",    "estava",    "estávamos",    "estáveis",    "estavam"),
            TenseRow("Futuro",            "estarei",   "estarás",    "estará",    "estaremos",    "estareis",    "estarão"),
            TenseRow("Condicional",       "estaria",   "estarias",   "estaria",   "estaríamos",   "estaríeis",   "estariam"),
            TenseRow("Conj. Presente",    "esteja",    "estejas",    "esteja",    "estejamos",    "estejais",    "estejam"),
            TenseRow("Conj. Imperfeito",  "estivesse", "estivesses", "estivesse", "estivéssemos", "estivésseis", "estivessem"),
            TenseRow("Conj. Futuro",      "estiver",   "estiveres",  "estiver",   "estivermos",   "estiverdes",  "estiverem")
        )
    ),
    IrregularVerb(
        verb = "ir", meaning = "to go",
        tenses = listOf(
            TenseRow("Presente",          "vou",   "vais",   "vai",   "vamos",    "ides",    "vão"),
            TenseRow("Pret. Perfeito",    "fui",   "foste",  "foi",   "fomos",    "fostes",  "foram"),
            TenseRow("Pret. Imperfeito",  "ia",    "ias",    "ia",    "íamos",    "íeis",    "iam"),
            TenseRow("Futuro",            "irei",  "irás",   "irá",   "iremos",   "ireis",   "irão"),
            TenseRow("Condicional",       "iria",  "irias",  "iria",  "iríamos",  "iríeis",  "iriam"),
            TenseRow("Conj. Presente",    "vá",    "vás",    "vá",    "vamos",    "vades",   "vão"),
            TenseRow("Conj. Imperfeito",  "fosse", "fosses", "fosse", "fôssemos", "fôsseis", "fossem"),
            TenseRow("Conj. Futuro",      "for",   "fores",  "for",   "formos",   "fordes",  "forem")
        )
    ),
    IrregularVerb(
        verb = "vir", meaning = "to come",
        tenses = listOf(
            TenseRow("Presente",          "venho",  "vens",    "vem",   "vimos",     "vindes",   "vêm"),
            TenseRow("Pret. Perfeito",    "vim",    "vieste",  "veio",  "viemos",    "viestes",  "vieram"),
            TenseRow("Pret. Imperfeito",  "vinha",  "vinhas",  "vinha", "vínhamos",  "vínheis",  "vinham"),
            TenseRow("Futuro",            "virei",  "virás",   "virá",  "viremos",   "vireis",   "virão"),
            TenseRow("Condicional",       "viria",  "virias",  "viria", "viríamos",  "viríeis",  "viriam"),
            TenseRow("Conj. Presente",    "venha",  "venhas",  "venha", "venhamos",  "venhais",  "venham"),
            TenseRow("Conj. Imperfeito",  "viesse", "viesses", "viesse","viéssemos", "viésseis", "viessem"),
            TenseRow("Conj. Futuro",      "vier",   "vieres",  "vier",  "viermos",   "vierdes",  "vierem")
        )
    ),
    IrregularVerb(
        verb = "sair", meaning = "to leave, go out",
        tenses = listOf(
            TenseRow("Presente",          "saio",   "sais",    "sai",   "saímos",    "saís",     "saem"),
            TenseRow("Pret. Perfeito",    "saí",    "saíste",  "saiu",  "saímos",    "saístes",  "saíram"),
            TenseRow("Pret. Imperfeito",  "saía",   "saías",   "saía",  "saíamos",   "saíeis",   "saíam"),
            TenseRow("Futuro",            "sairei", "sairás",  "sairá", "sairemos",  "saireis",  "sairão"),
            TenseRow("Condicional",       "sairia", "sairias", "sairia","sairíamos", "sairíeis", "sairiam"),
            TenseRow("Conj. Presente",    "saia",   "saias",   "saia",  "saiamos",   "saiais",   "saiam"),
            TenseRow("Conj. Imperfeito",  "saísse", "saísses", "saísse","saíssemos", "saísseis", "saíssem"),
            TenseRow("Conj. Futuro",      "sair",   "saíres",  "sair",  "sairmos",   "sairdes",  "saírem")
        )
    ),
    IrregularVerb(
        verb = "saber", meaning = "to know (facts)",
        tenses = listOf(
            TenseRow("Presente",          "sei",      "sabes",     "sabe",     "sabemos",     "sabeis",     "sabem"),
            TenseRow("Pret. Perfeito",    "soube",    "soubeste",  "soube",    "soubemos",    "soubestes",  "souberam"),
            TenseRow("Pret. Imperfeito",  "sabia",    "sabias",    "sabia",    "sabíamos",    "sabíeis",    "sabiam"),
            TenseRow("Futuro",            "saberei",  "saberás",   "saberá",   "saberemos",   "sabereis",   "saberão"),
            TenseRow("Condicional",       "saberia",  "saberias",  "saberia",  "saberíamos",  "saberíeis",  "saberiam"),
            TenseRow("Conj. Presente",    "saiba",    "saibas",    "saiba",    "saibamos",    "saibais",    "saibam"),
            TenseRow("Conj. Imperfeito",  "soubesse", "soubesses", "soubesse", "soubéssemos", "soubésseis", "soubessem"),
            TenseRow("Conj. Futuro",      "souber",   "souberes",  "souber",   "soubermos",   "souberdes",  "souberem")
        )
    ),
    IrregularVerb(
        verb = "fazer", meaning = "to do, make",
        tenses = listOf(
            TenseRow("Presente",          "faço",    "fazes",    "faz",     "fazemos",    "fazeis",    "fazem"),
            TenseRow("Pret. Perfeito",    "fiz",     "fizeste",  "fez",     "fizemos",    "fizestes",  "fizeram"),
            TenseRow("Pret. Imperfeito",  "fazia",   "fazias",   "fazia",   "fazíamos",   "fazíeis",   "faziam"),
            TenseRow("Futuro",            "farei",   "farás",    "fará",    "faremos",    "fareis",    "farão"),
            TenseRow("Condicional",       "faria",   "farias",   "faria",   "faríamos",   "faríeis",   "fariam"),
            TenseRow("Conj. Presente",    "faça",    "faças",    "faça",    "façamos",    "façais",    "façam"),
            TenseRow("Conj. Imperfeito",  "fizesse", "fizesses", "fizesse", "fizéssemos", "fizésseis", "fizessem"),
            TenseRow("Conj. Futuro",      "fizer",   "fizeres",  "fizer",   "fizermos",   "fizerdes",  "fizerem")
        )
    ),
    IrregularVerb(
        verb = "ter", meaning = "to have",
        tenses = listOf(
            TenseRow("Presente",          "tenho",   "tens",     "tem",    "temos",     "tendes",    "têm"),
            TenseRow("Pret. Perfeito",    "tive",    "tiveste",  "teve",   "tivemos",   "tivestes",  "tiveram"),
            TenseRow("Pret. Imperfeito",  "tinha",   "tinhas",   "tinha",  "tínhamos",  "tínheis",   "tinham"),
            TenseRow("Futuro",            "terei",   "terás",    "terá",   "teremos",   "tereis",    "terão"),
            TenseRow("Condicional",       "teria",   "terias",   "teria",  "teríamos",  "teríeis",   "teriam"),
            TenseRow("Conj. Presente",    "tenha",   "tenhas",   "tenha",  "tenhamos",  "tenhais",   "tenham"),
            TenseRow("Conj. Imperfeito",  "tivesse", "tivesses", "tivesse","tivéssemos","tivésseis", "tivessem"),
            TenseRow("Conj. Futuro",      "tiver",   "tiveres",  "tiver",  "tivermos",  "tiverdes",  "tiverem")
        )
    ),
    IrregularVerb(
        verb = "dar", meaning = "to give",
        tenses = listOf(
            TenseRow("Presente",          "dou",       "dás",       "dá",       "damos",       "dais",       "dão"),
            TenseRow("Pret. Perfeito",    "dei",       "deste",     "deu",      "demos",       "destes",     "deram"),
            TenseRow("Pret. Imperfeito",  "dava",      "davas",     "dava",     "dávamos",     "dáveis",     "davam"),
            TenseRow("Futuro",            "darei",     "darás",     "dará",     "daremos",     "dareis",     "darão"),
            TenseRow("Condicional",       "daria",     "darias",    "daria",    "daríamos",    "daríeis",    "dariam"),
            TenseRow("Conj. Presente",    "dê",        "dês",       "dê",       "demos",       "deis",       "deem"),
            TenseRow("Conj. Imperfeito",  "desse",     "desses",    "desse",    "déssemos",    "désseis",    "dessem"),
            TenseRow("Conj. Futuro",      "der",       "deres",     "der",      "dermos",      "derdes",     "derem")
        )
    ),
    IrregularVerb(
        verb = "trazer", meaning = "to bring",
        tenses = listOf(
            TenseRow("Presente",          "trago",      "trazes",      "traz",      "trazemos",      "trazeis",      "trazem"),
            TenseRow("Pret. Perfeito",    "trouxe",     "trouxeste",   "trouxe",    "trouxemos",     "trouxestes",   "trouxeram"),
            TenseRow("Pret. Imperfeito",  "trazia",     "trazias",     "trazia",    "trazíamos",     "trazíeis",     "traziam"),
            TenseRow("Futuro",            "trarei",     "trarás",      "trará",     "traremos",      "trareis",      "trarão"),
            TenseRow("Condicional",       "traria",     "trarias",     "traria",    "traríamos",     "traríeis",     "trariam"),
            TenseRow("Conj. Presente",    "traga",      "tragas",      "traga",     "tragamos",      "tragais",      "tragam"),
            TenseRow("Conj. Imperfeito",  "trouxesse",  "trouxesses",  "trouxesse", "trouxéssemos",  "trouxésseis",  "trouxessem"),
            TenseRow("Conj. Futuro",      "trouxer",    "trouxeres",   "trouxer",   "trouxermos",    "trouxerdes",   "trouxerem")
        )
    ),
    IrregularVerb(
        verb = "ver", meaning = "to see",
        tenses = listOf(
            TenseRow("Presente",          "vejo",   "vês",    "vê",    "vemos",    "vedes",   "veem"),
            TenseRow("Pret. Perfeito",    "vi",     "viste",  "viu",   "vimos",    "vistes",  "viram"),
            TenseRow("Pret. Imperfeito",  "via",    "vias",   "via",   "víamos",   "víeis",   "viam"),
            TenseRow("Futuro",            "verei",  "verás",  "verá",  "veremos",  "vereis",  "verão"),
            TenseRow("Condicional",       "veria",  "verias", "veria", "veríamos", "veríeis", "veriam"),
            TenseRow("Conj. Presente",    "veja",   "vejas",  "veja",  "vejamos",  "vejais",  "vejam"),
            TenseRow("Conj. Imperfeito",  "visse",  "visses", "visse", "víssemos", "vísseis", "vissem"),
            TenseRow("Conj. Futuro",      "vir",    "vires",  "vir",   "virmos",   "virdes",  "virem")
        )
    )
)

private val haverForms = listOf(
    "Presente"         to "há",
    "Pret. Perfeito"   to "houve",
    "Pret. Imperfeito" to "havia",
    "Futuro"           to "haverá",
    "Condicional"      to "haveria",
    "Conj. Presente"   to "haja",
    "Conj. Imperfeito" to "houvesse",
    "Conj. Futuro"     to "houver"
)

@Composable
private fun VerbCard(data: IrregularVerb) {
    ElevatedCard(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = "${data.verb} — ${data.meaning}",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            // Subject header rows
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.weight(1.4f)
                )
                listOf("eu", "tu", "ele/ela").forEach { subj ->
                    Text(
                        text = subj,
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.weight(1.4f)
                )
                listOf("nós", "vós", "eles/elas").forEach { subj ->
                    Text(
                        text = subj,
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))
            data.tenses.forEachIndexed { index, tense ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 1.dp)
                ) {
                    Text(
                        text = tense.name,
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.weight(1.4f)
                    )
                    listOf(tense.eu, tense.tu, tense.ele).forEach { form ->
                        Text(
                            text = form,
                            style = MaterialTheme.typography.bodySmall,
                            fontStyle = FontStyle.Italic,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 2.dp)
                ) {
                    Text(
                        text = "",
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.weight(1.4f)
                    )
                    listOf(tense.nos, tense.vos, tense.eles).forEach { form ->
                        Text(
                            text = form,
                            style = MaterialTheme.typography.bodySmall,
                            fontStyle = FontStyle.Italic,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
                if (index < data.tenses.lastIndex) {
                    HorizontalDivider(modifier = Modifier.padding(vertical = 2.dp))
                }
            }
        }
    }
}

@Composable
private fun HaverCard() {
    ElevatedCard(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = "haver — there to be (existential)",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 2.dp)
            )
            Text(
                text = "Used only in the 3rd person singular (impersonal)",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontStyle = FontStyle.Italic,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Tense",
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.weight(1.3f)
                )
                Text(
                    text = "Form",
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.weight(1f)
                )
            }
            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))
            haverForms.forEach { (tense, form) ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 2.dp)
                ) {
                    Text(
                        text = tense,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.weight(1.3f)
                    )
                    Text(
                        text = form,
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IrregularVerbsScreen(onBack: () -> Unit) {
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
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item { Spacer(modifier = Modifier.height(4.dp)) }
            irregularVerbs.forEach { verb ->
                item { VerbCard(data = verb) }
            }
            item { HaverCard() }
            item { Spacer(modifier = Modifier.height(16.dp)) }
        }
    }
}
