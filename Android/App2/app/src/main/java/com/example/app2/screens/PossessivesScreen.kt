package com.example.app2.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

/** One row of an owner table: which kind of item, the possessive form, an example. */
private data class PossFormRow(val item: String, val form: String, val example: String, val english: String)

private data class PossOwner(
    val title: String,
    val subtitle: String,
    val rows: List<PossFormRow>,
    val note: String = ""
)

private val possOwners = listOf(
    PossOwner(
        "my — eu", "meu / minha / meus / minhas",
        listOf(
            PossFormRow("masc. sing.", "o meu", "o meu pai", "my father"),
            PossFormRow("fem. sing.", "a minha", "a minha mãe", "my mother"),
            PossFormRow("masc. plur.", "os meus", "os meus pais", "my parents"),
            PossFormRow("fem. plur.", "as minhas", "as minhas casas", "my houses")
        ),
        "a minha casa = my house · as minhas casas = my houses. The form follows the house, not me."
    ),
    PossOwner(
        "your (one person, informal) — tu", "teu / tua / teus / tuas",
        listOf(
            PossFormRow("masc. sing.", "o teu", "o teu carro", "your car"),
            PossFormRow("fem. sing.", "a tua", "a tua irmã", "your sister"),
            PossFormRow("masc. plur.", "os teus", "os teus amigos", "your friends"),
            PossFormRow("fem. plur.", "as tuas", "as tuas chaves", "your keys")
        )
    ),
    PossOwner(
        "your (one person, formal) — você / o senhor / a senhora", "seu / sua / seus / suas",
        listOf(
            PossFormRow("masc. sing.", "o seu", "o seu passaporte", "your passport"),
            PossFormRow("fem. sing.", "a sua", "a sua mala", "your suitcase"),
            PossFormRow("masc. plur.", "os seus", "os seus documentos", "your documents"),
            PossFormRow("fem. plur.", "as suas", "as suas malas", "your suitcases")
        ),
        "In EP, seu/sua is mostly heard as the polite \"your\" (shops, officials, strangers)."
    ),
    PossOwner(
        "his — ele", "dele (never changes)",
        listOf(
            PossFormRow("masc. sing.", "o … dele", "o pai dele", "his father"),
            PossFormRow("fem. sing.", "a … dele", "a mãe dele", "his mother"),
            PossFormRow("masc. plur.", "os … dele", "os livros dele", "his books"),
            PossFormRow("fem. plur.", "as … dele", "as casas dele", "his houses")
        ),
        "dele comes AFTER the noun and does not agree with it. Only the article (o/a/os/as) changes."
    ),
    PossOwner(
        "her — ela", "dela (never changes)",
        listOf(
            PossFormRow("masc. sing.", "o … dela", "o pai dela", "her father"),
            PossFormRow("fem. sing.", "a … dela", "a mãe dela", "her mother"),
            PossFormRow("masc. plur.", "os … dela", "os filhos dela", "her children"),
            PossFormRow("fem. plur.", "as … dela", "as casas dela", "her houses")
        ),
        "dele vs dela shows the OWNER's gender: o carro dele = his car, o carro dela = her car."
    ),
    PossOwner(
        "our — nós", "nosso / nossa / nossos / nossas",
        listOf(
            PossFormRow("masc. sing.", "o nosso", "o nosso apartamento", "our apartment"),
            PossFormRow("fem. sing.", "a nossa", "a nossa casa", "our house"),
            PossFormRow("masc. plur.", "os nossos", "os nossos filhos", "our children"),
            PossFormRow("fem. plur.", "as nossas", "as nossas férias", "our holidays")
        )
    ),
    PossOwner(
        "your (several people) — vocês / vós", "vosso / vossa / vossos / vossas",
        listOf(
            PossFormRow("masc. sing.", "o vosso", "o vosso pai", "your father"),
            PossFormRow("fem. sing.", "a vossa", "a vossa mãe", "your mother"),
            PossFormRow("masc. plur.", "os vossos", "os vossos bilhetes", "your tickets"),
            PossFormRow("fem. plur.", "as vossas", "as vossas casas", "your houses")
        ),
        "In EP, vosso is the normal everyday \"your (plural)\" even though vós itself is rarely used. " +
            "Alternative: o … de vocês (o carro de vocês). BP uses de vocês / seu instead."
    ),
    PossOwner(
        "their — eles / elas", "deles / delas (never change)",
        listOf(
            PossFormRow("masc. sing.", "o … deles", "o pai deles", "their father"),
            PossFormRow("fem. sing.", "a … deles", "a casa deles", "their house"),
            PossFormRow("masc. plur.", "os … delas", "os filhos delas", "their children (owners all female)"),
            PossFormRow("fem. plur.", "as … deles", "as malas deles", "their suitcases")
        ),
        "deles = owners male or mixed; delas = owners all female. The item never changes the form."
    )
)

private val possSummary = listOf(
    listOf("eu", "o meu", "a minha", "os meus", "as minhas"),
    listOf("tu", "o teu", "a tua", "os teus", "as tuas"),
    listOf("você (formal)", "o seu", "a sua", "os seus", "as suas"),
    listOf("ele", "o … dele", "a … dele", "os … dele", "as … dele"),
    listOf("ela", "o … dela", "a … dela", "os … dela", "as … dela"),
    listOf("nós", "o nosso", "a nossa", "os nossos", "as nossas"),
    listOf("vocês", "o vosso", "a vossa", "os vossos", "as vossas"),
    listOf("eles", "o … deles", "a … deles", "os … deles", "as … deles"),
    listOf("elas", "o … delas", "a … delas", "os … delas", "as … delas")
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PossessivesScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Possessives") },
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
                .padding(innerPadding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                PossTextCard(
                    "The Main Rule",
                    listOf(
                        "Possessives agree with the THING owned (its gender and number), not with the owner.",
                        "o meu pai (my father) · a minha mãe (my mother) — the same owner (me), different forms because pai is masculine and mãe is feminine.",
                        "a minha casa (my house) · as minhas casas (my houses) — plural item → plural possessive.",
                        "In European Portuguese the definite article (o / a / os / as) normally goes in front: a minha casa, not just minha casa.",
                        "Exception: dele / dela / deles / delas (his / her / their) go after the noun and never change."
                    )
                )
            }
            item { PossSummaryCard() }
            items(possOwners) { PossOwnerCard(it) }
            item {
                PossTextCard(
                    "Two Ways: seu vs dele / dela",
                    listOf(
                        "Grammatically, seu / sua can mean your (você), his, her, its or their — so it is ambiguous.",
                        "In everyday European Portuguese, seu / sua is understood as the polite \"your\" (talking to você / o senhor / a senhora): \"Este é o seu lugar.\" — This is your seat.",
                        "For his / her / their, EP speakers normally use dele / dela / deles / delas after the noun: \"o carro dele\" (his car), \"a casa dela\" (her house).",
                        "Written or formal texts still use seu for his / her / their when context is clear: \"O autor apresentou o seu livro.\" — The author presented his book.",
                        "For \"your\" to a group: o vosso / a vossa (EP everyday) or o … de vocês."
                    )
                )
            }
            item {
                PossTextCard(
                    "What About Neuter?",
                    listOf(
                        "Portuguese nouns have no neuter gender — every noun is masculine or feminine, so there are only the four forms (m. sing., f. sing., m. plur., f. plur.).",
                        "When the \"thing\" is neuter (isto, isso, aquilo, tudo, o que…), the masculine singular is used: \"Isto é meu.\" — This is mine. \"O que é meu é teu.\" — What's mine is yours.",
                        "Possessives can stand alone as pronouns (\"mine\", \"yours\"): \"O meu é azul.\" — Mine is blue. \"A tua é maior.\" — Yours (fem. item) is bigger."
                    )
                )
            }
            item {
                PossTextCard(
                    "More Useful Points",
                    listOf(
                        "é meu vs é o meu: \"Este livro é meu.\" — This book is mine (belongs to me). \"Este livro é o meu.\" — This book is the one that's mine (not the other ones).",
                        "of mine / of yours: \"um amigo meu\" — a friend of mine; \"uma colega minha\" — a colleague of mine; \"uns primos teus\" — some cousins of yours.",
                        "No article when calling someone or in exclamations: \"Meu Deus!\", \"Minha querida…\".",
                        "Body parts and clothes usually take just the article, not a possessive: \"Dói-me a cabeça.\" — My head hurts. \"Lavei as mãos.\" — I washed my hands. \"Tira o casaco.\" — Take off your coat.",
                        "\"em casa\" = at home (no possessive needed): \"Estou em casa.\" — I'm at home."
                    )
                )
            }
        }
    }
}

@Composable
private fun PossTextCard(title: String, lines: List<String>) {
    ElevatedCard(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            HorizontalDivider(modifier = Modifier.padding(bottom = 4.dp))
            lines.forEach { line ->
                Text("• $line", style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}

@Composable
private fun PossSummaryCard() {
    ElevatedCard(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text("All Forms at a Glance", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            Text(
                "Columns = the item owned. \"…\" = the noun goes there (o carro dele).",
                style = MaterialTheme.typography.bodySmall,
                fontStyle = FontStyle.Italic,
                color = MaterialTheme.colorScheme.tertiary
            )
            HorizontalDivider(modifier = Modifier.padding(bottom = 4.dp))
            PossGridRow(listOf("owner", "m. sing.", "f. sing.", "m. plur.", "f. plur."), header = true)
            HorizontalDivider()
            possSummary.forEach { PossGridRow(it, header = false) }
        }
    }
}

@Composable
private fun PossGridRow(cells: List<String>, header: Boolean) {
    Row(modifier = Modifier.fillMaxWidth()) {
        cells.forEachIndexed { i, cell ->
            Text(
                text = cell,
                style = if (header) MaterialTheme.typography.labelSmall else MaterialTheme.typography.bodySmall,
                fontWeight = if (header || i == 0) FontWeight.Bold else FontWeight.Normal,
                fontStyle = if (!header && i > 0) FontStyle.Italic else FontStyle.Normal,
                modifier = Modifier.weight(if (i == 0) 0.9f else 1f)
            )
        }
    }
}

@Composable
private fun PossOwnerCard(owner: PossOwner) {
    ElevatedCard(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(owner.title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            Text(
                owner.subtitle,
                style = MaterialTheme.typography.bodyMedium,
                fontStyle = FontStyle.Italic,
                color = MaterialTheme.colorScheme.primary
            )
            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                listOf("item" to 0.8f, "form" to 0.8f, "example" to 1.4f).forEach { (h, w) ->
                    Text(
                        h,
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.weight(w)
                    )
                }
            }
            owner.rows.forEach { row ->
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        row.item,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.weight(0.8f)
                    )
                    Text(
                        row.form,
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.weight(0.8f)
                    )
                    Column(modifier = Modifier.weight(1.4f)) {
                        Text(row.example, style = MaterialTheme.typography.bodySmall, fontStyle = FontStyle.Italic)
                        Text(
                            row.english,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
            if (owner.note.isNotEmpty()) {
                Text(
                    owner.note,
                    style = MaterialTheme.typography.bodySmall,
                    fontStyle = FontStyle.Italic,
                    color = MaterialTheme.colorScheme.tertiary,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }
    }
}
