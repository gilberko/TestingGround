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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

data class DialogueLine(val speaker: String, val text: String)

@Composable
private fun DialogueSection(header: String, lines: List<DialogueLine>) {
    Text(
        text = header,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        color = ButtonBlue,
        modifier = Modifier.padding(top = 24.dp, bottom = 8.dp)
    )
    lines.forEach { line ->
        Text(
            text = buildAnnotatedString {
                withStyle(SpanStyle(fontWeight = FontWeight.Bold, fontSize = 15.sp, color = Color.Black)) {
                    append("${line.speaker}: ")
                }
                withStyle(SpanStyle(fontSize = 15.sp, color = Color.DarkGray)) {
                    append(line.text)
                }
            },
            modifier = Modifier.padding(vertical = 4.dp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DialoguesHubScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Sample Dialogues", fontSize = 18.sp, fontWeight = FontWeight.Bold) },
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
        val items = listOf(
            "At the Restaurant" to "dialogue_restaurant",
            "Asking for Directions" to "dialogue_directions"
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 32.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items.chunked(2).forEach { pair ->
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    pair.forEach { (label, route) ->
                        DictNavButton(label = label, modifier = Modifier.weight(1f)) {
                            navController.navigate(route)
                        }
                    }
                    if (pair.size == 1) Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RestaurantDialogueScreen(navController: NavController) {
    val czechLines = listOf(
        DialogueLine("Přítel 1", "Dobrý den. Máte stůl pro dva?"),
        DialogueLine("Číšník", "Dobrý den. Ano, pojďte dál, prosím. Tady je váš stůl."),
        DialogueLine("Přítel 2", "Děkujeme. Jaké máte dnes polévky?"),
        DialogueLine("Číšník", "Dnes máme hovězí vývar s nudlemi a krémovou bramborovou polévku."),
        DialogueLine("Přítel 1", "Výborně. A jaká jsou dnešní hlavní jídla?"),
        DialogueLine("Číšník", "Máme pečenou kachnu s červeným zelím a knedlíky za čtyři sta padesát korun, grilovaného lososa s bramborovým salátem za tři sta osmdesát korun a svíčkovou na smetaně s knedlíky za tři sta dvacet korun."),
        DialogueLine("Přítel 2", "Co je to svíčková?"),
        DialogueLine("Číšník", "Svíčková na smetaně je tradiční české jídlo. Je to hovězí svíčková vařená v omáčce ze zeleniny a smetany, podávaná s houskými knedlíky a brusinkami."),
        DialogueLine("Přítel 1", "To zní skvěle. Dám si svíčkovou."),
        DialogueLine("Přítel 2", "A já si dám pečenou kachnu. Je v ceně jídla i polévka?"),
        DialogueLine("Číšník", "Ano, polévka je zahrnuta v ceně. Co si dáte k pití?"),
        DialogueLine("Přítel 1", "Dvě červená vína, prosím."),
        DialogueLine("Číšník", "Samozřejmě. Hned to přinesu.")
    )
    val englishLines = listOf(
        DialogueLine("Friend 1", "Good afternoon. Do you have a table for two?"),
        DialogueLine("Waiter", "Good afternoon. Yes, please come in. Here is your table."),
        DialogueLine("Friend 2", "Thank you. What soups do you have today?"),
        DialogueLine("Waiter", "Today we have beef broth with noodles and cream of potato soup."),
        DialogueLine("Friend 1", "Excellent. And what are today's main dishes?"),
        DialogueLine("Waiter", "We have roast duck with red cabbage and dumplings for four hundred and fifty crowns, grilled salmon with potato salad for three hundred and eighty crowns, and svíčková na smetaně with dumplings for three hundred and twenty crowns."),
        DialogueLine("Friend 2", "What is svíčková?"),
        DialogueLine("Waiter", "Svíčková na smetaně is a traditional Czech dish. It is beef sirloin cooked in a sauce made from root vegetables and cream, served with bread dumplings and cranberries."),
        DialogueLine("Friend 1", "That sounds wonderful. I'll have the svíčková."),
        DialogueLine("Friend 2", "And I'll have the roast duck. Is soup included in the price?"),
        DialogueLine("Waiter", "Yes, soup is included in the price. What would you like to drink?"),
        DialogueLine("Friend 1", "Two red wines, please."),
        DialogueLine("Waiter", "Of course. I'll bring it right away.")
    )
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("At the Restaurant", fontSize = 18.sp, fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
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
            DialogueSection(header = "Czech", lines = czechLines)
            DialogueSection(header = "English", lines = englishLines)
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DirectionsDialogueScreen(navController: NavController) {
    val czechLines = listOf(
        DialogueLine("Muž", "Promiňte, nevíte, jak se dostanu na stadion Eden?"),
        DialogueLine("Kolemjdoucí", "Dobrý den. Ano, vím. Máte dvě možnosti."),
        DialogueLine("Kolemjdoucí", "První možnost je metrem. Jděte rovně asi dvě minuty, pak odbočte doleva na Budějovickou ulici. Uvidíte stanici metra Budějovická — to je linka C, červená linka. Jeďte čtyři zastávky až na Vyšehrad. Na Vyšehradě vystupte, jeďte eskalátory nahoru a pak sejděte schody dolů. Stadion je napravo, asi pět minut pěšky."),
        DialogueLine("Muž", "Rozumím. A druhá možnost?"),
        DialogueLine("Kolemjdoucí", "Druhá možnost je autobusem. Odbočte doleva a jděte asi tři minuty na autobusovou zastávku. Tam jezdí přímý autobus číslo sto osmnáct — nemusíte přestupovat. Vystupte na zastávce Eden – stadion. Jízda trvá asi deset minut."),
        DialogueLine("Muž", "Co je rychlejší?"),
        DialogueLine("Kolemjdoucí", "Metro je rychlejší, ale autobus vás vysadí přímo u stadionu."),
        DialogueLine("Muž", "Díky moc. To je velmi užitečné."),
        DialogueLine("Kolemjdoucí", "Není zač. Příjemný zápas!")
    )
    val englishLines = listOf(
        DialogueLine("Man", "Excuse me, would you happen to know how to get to Eden Stadium?"),
        DialogueLine("Passerby", "Hello. Yes, I know. You have two options."),
        DialogueLine("Passerby", "The first option is by metro. Go straight for about two minutes, then turn left onto Budějovická Street. You will see the metro station Budějovická — that is line C, the red line. Ride four stops to Vyšehrad. At Vyšehrad, get off, take the escalators up, then go down the stairs. The stadium is on the right, about a five-minute walk."),
        DialogueLine("Man", "I understand. And the second option?"),
        DialogueLine("Passerby", "The second option is by bus. Turn left and walk about three minutes to the bus stop. A direct bus, number one hundred and eighteen, runs from there — you do not need to transfer. Get off at the stop called Eden – stadion. The ride takes about ten minutes."),
        DialogueLine("Man", "Which is faster?"),
        DialogueLine("Passerby", "The metro is faster, but the bus drops you off directly at the stadium."),
        DialogueLine("Man", "Thank you very much. That is very helpful."),
        DialogueLine("Passerby", "You're welcome. Enjoy the match!")
    )
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Asking for Directions", fontSize = 18.sp, fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
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
            DialogueSection(header = "Czech", lines = czechLines)
            DialogueSection(header = "English", lines = englishLines)
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}
