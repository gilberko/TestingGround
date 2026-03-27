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

private data class PrepNote(val pattern: String, val example: String)

private data class UsefulVerbEntry(
    val verb: String,
    val english: String,
    val eu: String,
    val tu: String,
    val ele: String,
    val nos: String,
    val eles: String,
    val prepNotes: List<PrepNote> = emptyList()
)

private val usefulVerbs = listOf(
    UsefulVerbEntry("ter", "to have", "tenho", "tens", "tem", "temos", "têm", listOf(
        PrepNote("ter de + infinitive", "Tenho de ir. — I have to go."),
        PrepNote("ter com (to deal with)", "Não tenho nada com isso. — I have nothing to do with that.")
    )),
    UsefulVerbEntry("estar", "to be (state / location)", "estou", "estás", "está", "estamos", "estão", listOf(
        PrepNote("estar em (location)", "Estou em casa. — I am at home."),
        PrepNote("estar a + infinitive (ongoing action)", "Estou a comer. — I am eating.")
    )),
    UsefulVerbEntry("ser", "to be (identity / permanent)", "sou", "és", "é", "somos", "são", listOf(
        PrepNote("ser de (origin / material)", "Sou de Lisboa. — I am from Lisbon.")
    )),
    UsefulVerbEntry("fazer", "to do / make", "faço", "fazes", "faz", "fazemos", "fazem", listOf(
        PrepNote("fazer + noun (no preposition needed)", "Faço o jantar. — I make dinner.")
    )),
    UsefulVerbEntry("saber", "to know (facts / skills)", "sei", "sabes", "sabe", "sabemos", "sabem", listOf(
        PrepNote("saber + infinitive (know how to)", "Sei nadar. — I know how to swim."),
        PrepNote("saber de (know about)", "Sabes de alguma novidade? — Do you know of any news?")
    )),
    UsefulVerbEntry("ir", "to go", "vou", "vais", "vai", "vamos", "vão", listOf(
        PrepNote("ir a (temporary destination)", "Vou ao supermercado. — I am going to the supermarket."),
        PrepNote("ir para (destination / direction)", "Vou para Portugal. — I am going to Portugal.")
    )),
    UsefulVerbEntry("sair", "to leave / go out", "saio", "sais", "sai", "saímos", "saem", listOf(
        PrepNote("sair de (leave a place)", "Saio do trabalho às seis. — I leave work at six."),
        PrepNote("sair com (go out with)", "Saio com os amigos. — I go out with friends.")
    )),
    UsefulVerbEntry("ver", "to see / watch", "vejo", "vês", "vê", "vemos", "veem", listOf(
        PrepNote("ver + noun (no preposition needed)", "Vejo televisão. — I watch TV.")
    )),
    UsefulVerbEntry("vir", "to come", "venho", "vens", "vem", "vimos", "vêm", listOf(
        PrepNote("vir de (come from)", "Venho de Lisboa. — I come from Lisbon."),
        PrepNote("vir para / a (come to)", "Vem para cá. — Come here.")
    )),
    UsefulVerbEntry("comer", "to eat", "como", "comes", "come", "comemos", "comem", listOf(
        PrepNote("comer + noun (no preposition needed)", "Como pão. — I eat bread.")
    )),
    UsefulVerbEntry("achar", "to think / find", "acho", "achas", "acha", "achamos", "acham", listOf(
        PrepNote("achar que (think that)", "Acho que sim. — I think so."),
        PrepNote("achar + noun (find something)", "Achei as chaves. — I found the keys.")
    )),
    UsefulVerbEntry("pensar", "to think", "penso", "pensas", "pensa", "pensamos", "pensam", listOf(
        PrepNote("pensar em (think about)", "Penso em ti. — I think about you."),
        PrepNote("pensar sobre (think about / reflect on)", "Penso sobre o assunto. — I think about the matter.")
    )),
    UsefulVerbEntry("conhecer", "to know (people / places)", "conheço", "conheces", "conhece", "conhecemos", "conhecem", listOf(
        PrepNote("conhecer + noun (no preposition needed)", "Conheço Lisboa. — I know Lisbon.")
    )),
    UsefulVerbEntry("ficar", "to stay / remain / be located", "fico", "ficas", "fica", "ficamos", "ficam", listOf(
        PrepNote("ficar em (stay at)", "Fico em casa. — I stay at home."),
        PrepNote("ficar com (keep / stay with)", "Fico com o troco. — I'll keep the change.")
    )),
    UsefulVerbEntry("encontrar", "to find / meet", "encontro", "encontras", "encontra", "encontramos", "encontram", listOf(
        PrepNote("encontrar-se com (meet with)", "Encontro-me com ela às três. — I am meeting her at three."),
        PrepNote("encontrar + noun (find something)", "Encontrei a solução. — I found the solution.")
    )),
    UsefulVerbEntry("dar", "to give", "dou", "dás", "dá", "damos", "dão", listOf(
        PrepNote("dar a (give to)", "Dou o livro ao João. — I give the book to João."),
        PrepNote("dar com (come across)", "Dei com ela no mercado. — I came across her at the market.")
    )),
    UsefulVerbEntry("tocar", "to touch / play (instrument)", "toco", "tocas", "toca", "tocamos", "tocam", listOf(
        PrepNote("tocar em (touch)", "Não toques nisso. — Don't touch that."),
        PrepNote("tocar + instrument (no preposition)", "Toco guitarra. — I play guitar.")
    )),
    UsefulVerbEntry("apanhar", "to catch / pick up / take (transport)", "apanho", "apanhas", "apanha", "apanhamos", "apanham", listOf(
        PrepNote("apanhar + transport (no preposition)", "Apanho o autocarro. — I take the bus.")
    )),
    UsefulVerbEntry("receber", "to receive", "recebo", "recebes", "recebe", "recebemos", "recebem", listOf(
        PrepNote("receber + noun (no preposition needed)", "Recebi uma carta. — I received a letter.")
    )),
    UsefulVerbEntry("precisar", "to need", "preciso", "precisas", "precisa", "precisamos", "precisam", listOf(
        PrepNote("precisar de (need)", "Preciso de ajuda. — I need help."),
        PrepNote("precisar de + infinitive (need to)", "Preciso de sair. — I need to leave.")
    )),
    UsefulVerbEntry("gostar", "to like", "gosto", "gostas", "gosta", "gostamos", "gostam", listOf(
        PrepNote("gostar de (like something)", "Gosto de música. — I like music."),
        PrepNote("gostar de + infinitive (like doing)", "Gosto de correr. — I like running.")
    )),
    UsefulVerbEntry("querer", "to want", "quero", "queres", "quer", "queremos", "querem", listOf(
        PrepNote("querer + noun (want something)", "Quero um café. — I want a coffee."),
        PrepNote("querer + infinitive (want to)", "Quero sair. — I want to go out.")
    )),
    UsefulVerbEntry("poder", "can / be able to", "posso", "podes", "pode", "podemos", "podem", listOf(
        PrepNote("poder + infinitive (can do)", "Posso ajudar. — I can help.")
    )),
    UsefulVerbEntry("começar", "to begin / start", "começo", "começas", "começa", "começamos", "começam", listOf(
        PrepNote("começar a + infinitive (start to)", "Começo a estudar. — I start to study."),
        PrepNote("começar por + infinitive (start by)", "Começa por ler. — Start by reading.")
    )),
    UsefulVerbEntry("falar", "to speak / talk", "falo", "falas", "fala", "falamos", "falam", listOf(
        PrepNote("falar com (talk to)", "Falo com ela. — I speak with her."),
        PrepNote("falar de / sobre (talk about)", "Falo sobre o trabalho. — I talk about work.")
    )),
    UsefulVerbEntry("esperar", "to wait / hope", "espero", "esperas", "espera", "esperamos", "esperam", listOf(
        PrepNote("esperar por (wait for)", "Espero por ti. — I wait for you."),
        PrepNote("esperar que (hope that)", "Espero que estejas bem. — I hope you are well.")
    )),
    UsefulVerbEntry("trabalhar", "to work", "trabalho", "trabalhas", "trabalha", "trabalhamos", "trabalham", listOf(
        PrepNote("trabalhar em (work at / in)", "Trabalho em Lisboa. — I work in Lisbon."),
        PrepNote("trabalhar para (work for)", "Trabalho para uma empresa. — I work for a company.")
    )),
    UsefulVerbEntry("morar", "to live / reside", "moro", "moras", "mora", "moramos", "moram", listOf(
        PrepNote("morar em (live in)", "Moro em Portugal. — I live in Portugal.")
    )),
    UsefulVerbEntry("ouvir", "to hear / listen", "ouço", "ouves", "ouve", "ouvimos", "ouvem", listOf(
        PrepNote("ouvir + noun (hear / listen to)", "Ouço música. — I listen to music."),
        PrepNote("ouvir falar de (hear about)", "Ouvi falar desse lugar. — I heard about that place.")
    )),
    UsefulVerbEntry("ler", "to read", "leio", "lês", "lê", "lemos", "leem", listOf(
        PrepNote("ler + noun (no preposition needed)", "Leio um livro. — I read a book.")
    ))
)

@Composable
private fun UsefulVerbCard(entry: UsefulVerbEntry) {
    ElevatedCard(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = "${entry.verb} — ${entry.english}",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            // Subject headers (row 1)
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(text = "", style = MaterialTheme.typography.bodySmall, modifier = Modifier.weight(1.2f))
                listOf("eu", "tu", "ele/ela").forEach { subj ->
                    Text(
                        text = subj,
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
            // Forms (row 1)
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(text = "Presente", style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.SemiBold, modifier = Modifier.weight(1.2f))
                listOf(entry.eu, entry.tu, entry.ele).forEach { form ->
                    Text(text = form, style = MaterialTheme.typography.bodySmall, fontStyle = FontStyle.Italic, modifier = Modifier.weight(1f))
                }
            }
            // Subject headers (row 2)
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(text = "", style = MaterialTheme.typography.bodySmall, modifier = Modifier.weight(1.2f))
                listOf("nós", "eles/elas").forEach { subj ->
                    Text(
                        text = subj,
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.weight(1f)
                    )
                }
                // Empty slot to maintain grid alignment
                Text(text = "", style = MaterialTheme.typography.bodySmall, modifier = Modifier.weight(1f))
            }
            // Forms (row 2)
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(text = "", style = MaterialTheme.typography.bodySmall, modifier = Modifier.weight(1.2f))
                listOf(entry.nos, entry.eles).forEach { form ->
                    Text(text = form, style = MaterialTheme.typography.bodySmall, fontStyle = FontStyle.Italic, modifier = Modifier.weight(1f))
                }
                Text(text = "", style = MaterialTheme.typography.bodySmall, modifier = Modifier.weight(1f))
            }

            if (entry.prepNotes.isNotEmpty()) {
                HorizontalDivider(modifier = Modifier.padding(vertical = 6.dp))
                Text(
                    text = "Prepositions:",
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                entry.prepNotes.forEach { note ->
                    Text(
                        text = "• ${note.pattern}",
                        style = MaterialTheme.typography.bodySmall,
                        fontStyle = FontStyle.Italic,
                        modifier = Modifier.padding(start = 4.dp, top = 2.dp)
                    )
                    Text(
                        text = note.example,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(start = 12.dp)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UsefulVerbsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Useful Verbs") },
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
            usefulVerbs.forEach { entry ->
                item { UsefulVerbCard(entry) }
            }
            item { Spacer(modifier = Modifier.height(16.dp)) }
        }
    }
}
