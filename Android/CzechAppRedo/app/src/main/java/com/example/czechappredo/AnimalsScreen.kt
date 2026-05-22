package com.example.czechappredo

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnimalsScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Animals", fontSize = 18.sp, fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        },
        containerColor = Color.White
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            ANNote("Czech is very fond of diminutives — cute / small / affectionate forms of nouns. The most common suffixes are -ek, -eček, -ík, -íček (masculine), -ka, -ička (feminine), and -ko, -íčko (neuter). They can mark something as literally small, young, or simply a term of endearment. Pet names, children's vocabulary, and animal cartoons all lean heavily on diminutives. The most famous example: Krteček, the beloved Czech animated mole created by Zdeněk Miler in 1956. Where a diminutive is in common everyday use, it's shown below the entry in italics.")
            ANNote("Spelling note: the small wedge on letters like š, č, ž, ř, ě is called a háček (lit. 'little hook'). The acute accent on á, é, í, ó, ú, ý is a čárka. The small ring on ů is a kroužek.")

            ANSection("Birds")
            ANRow("pták", "bird", note = "m.", diminutive = "ptáček — little bird / birdy")
            ANRow("orel", "eagle", "m.")
            ANRow("vrabec", "sparrow", note = "m.", diminutive = "vrabčák / vrabčík — little sparrow (also a colloquial nickname)")
            ANRow("papoušek", "parrot", "m.")
            ANRow("pštros", "ostrich", "m.")
            ANRow("tučňák", "penguin", "m.")
            ANRow("vrána", "crow", "f.")
            ANRow("krkavec", "raven", "m.")
            ANRow("sup", "vulture", "m.")

            ANSection("Sea & Water Animals")
            ANRow("delfín", "dolphin", "m.")
            ANRow("ryba", "fish", note = "f.", diminutive = "rybka / rybička — little fish")
            ANRow("žralok", "shark", "m.")
            ANRow("velryba", "whale", "f.")
            ANRow("úhoř", "eel", "m.")
            ANRow("medúza", "jellyfish", "f.")

            ANSection("Pets & Farm Animals")
            ANRow("pes", "dog", note = "m. — pl. psi", diminutive = "pejsek / psík — doggy / puppy")
            ANRow("kočka", "cat", note = "f.", diminutive = "kočička — kitty (also colloquial for 'sweetie')")
            ANRow("kůň", "horse", note = "m. — pl. koně", diminutive = "koníček — little horse / pony (also means 'hobby'!)")
            ANRow("osel", "donkey", "m.")
            ANRow("ovce", "sheep", note = "f.", diminutive = "ovečka — little sheep / lamb (term of endearment)")
            ANRow("koza", "goat", "f.")
            ANRow("kráva", "cow", note = "f.", diminutive = "kravička — little cow")

            ANSection("African & Asian Animals")
            ANRow("lev", "lion", "m.")
            ANRow("tygr", "tiger", "m.")
            ANRow("leopard", "leopard", "m.")
            ANRow("hyena", "hyena", "f.")
            ANRow("žirafa", "giraffe", "f.")
            ANRow("gorila", "gorilla", "f.")
            ANRow("opice", "monkey", note = "f.", diminutive = "opička — little monkey")
            ANRow("nosorožec", "rhinoceros", "m.")
            ANRow("hroch", "hippopotamus", "m.")
            ANRow("zebra", "zebra", "f.")
            ANRow("gazela", "gazelle", "f.")
            ANRow("lemur", "lemur", "m.")

            ANSection("Reptiles & Bugs")
            ANRow("had", "snake", note = "m.", diminutive = "hádek — little snake")
            ANRow("krokodýl", "crocodile", "m.")
            ANRow("aligátor", "alligator", "m.")
            ANRow("škorpion", "scorpion", "m.")
            ANRow("pavouk", "spider", note = "m.", diminutive = "pavouček — little spider (think 'itsy-bitsy spider')")
            ANRow("želva", "turtle / tortoise", "f. — Czech uses the same word for both")
            ANRow("mravenec", "ant", note = "m.", diminutive = "mraveneček — little ant")
            ANRow("brouk", "beetle", note = "m.", diminutive = "brouček — little beetle (common term of endearment for a small child)")
            ANRow("šváb", "cockroach", "m.")

            ANSection("Other Animals")
            ANRow("medvěd", "bear", note = "m.", diminutive = "medvídek — teddy bear / bear cub")
            ANRow("los", "moose", "m.")
            ANRow("sob", "reindeer", "m.")
            ANRow("bobr", "beaver", "m.")
            ANRow("krtek", "mole", note = "m.", diminutive = "krteček — the beloved Czech cartoon character (Zdeněk Miler, 1956)")
            ANRow("myš", "mouse", note = "f.", diminutive = "myška — little mouse (also affectionate nickname)")
            ANRow("krysa", "rat", "f.")
            ANRow("klokan", "kangaroo", "m.")
            ANRow("koala", "koala", "m.")
            ANRow("jelen", "deer", note = "m.", diminutive = "jelínek — little deer / fawn")
            ANRow("zajíc", "hare", note = "m.", diminutive = "zajíček — little hare / bunny (also 'sweetie')")
            ANRow("králík", "rabbit", note = "m.", diminutive = "králíček — little rabbit / bunny")
            ANRow("veverka", "squirrel", note = "f.", diminutive = "veverka itself is already a diminutive-like form; affectionate: veverčička")

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun ANSection(text: String) {
    Spacer(modifier = Modifier.height(20.dp))
    Text(text = text, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = ButtonBlue)
    Spacer(modifier = Modifier.height(6.dp))
}

@Composable
private fun ANNote(text: String) {
    Text(
        text = text,
        fontSize = 14.sp,
        color = Color.DarkGray,
        modifier = Modifier.padding(vertical = 4.dp)
    )
}

@Composable
private fun ANRow(czech: String, english: String, note: String = "", diminutive: String = "") {
    Column(modifier = Modifier.padding(vertical = 3.dp)) {
        Text(
            text = buildAnnotatedString {
                withStyle(SpanStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.Black)) {
                    append(czech)
                }
                withStyle(SpanStyle(fontSize = 16.sp, color = Color.DarkGray)) {
                    append("  —  $english")
                }
            }
        )
        if (diminutive.isNotEmpty()) {
            Text(
                text = "↳ $diminutive",
                fontSize = 14.sp,
                fontStyle = FontStyle.Italic,
                color = Color.Gray,
                modifier = Modifier.padding(start = 12.dp, top = 1.dp)
            )
        }
        if (note.isNotEmpty()) {
            Text(
                text = note,
                fontSize = 13.sp,
                fontStyle = FontStyle.Italic,
                color = Color.Gray,
                modifier = Modifier.padding(start = 4.dp, top = 1.dp)
            )
        }
    }
}
