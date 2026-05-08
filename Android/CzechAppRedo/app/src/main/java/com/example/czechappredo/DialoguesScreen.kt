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
            "Asking for Directions" to "dialogue_directions",
            "Doctor's Appointment" to "dialogue_doctor",
            "Introducing Yourself" to "dialogue_intro",
            "At the Supermarket" to "dialogue_supermarket",
            "At the Train Station" to "dialogue_train_station"
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DoctorAppointmentDialogueScreen(navController: NavController) {
    val czechLines = listOf(
        DialogueLine("Doktorka", "Dobrý den. Posaďte se prosím. Co vás trápí?"),
        DialogueLine("Pacient", "Dobrý den. Necítím se dobře. Mám horečku a zimnici, a bolí mě hlava."),
        DialogueLine("Doktorka", "Jak vysokou horečku máte?"),
        DialogueLine("Pacient", "Mám 39,5 stupňů. Také mě bolí svaly — záda a ramena."),
        DialogueLine("Doktorka", "Jak dlouho máte tyto příznaky?"),
        DialogueLine("Pacient", "Od včera večera."),
        DialogueLine("Doktorka", "Dobře. Nechte mě vás vyšetřit."),
        DialogueLine("Doktorka", "Pravděpodobně máte chřipku. Nic vážného, ale potřebujete odpočívat."),
        DialogueLine("Pacient", "Mám brát nějaké léky?"),
        DialogueLine("Doktorka", "Ano. Doporučuji prášky na snížení horečky. Jsou bez receptu — koupíte je v lékárně."),
        DialogueLine("Pacient", "Kolikrát denně je mám brát?"),
        DialogueLine("Doktorka", "Třikrát denně, po jídle. A pijte hodně tekutin."),
        DialogueLine("Pacient", "Jak dlouho budu nemocný?"),
        DialogueLine("Doktorka", "Odpočívejte několik dní. Mělo by vám být lépe do týdne."),
        DialogueLine("Pacient", "Děkuji vám, doktorko."),
        DialogueLine("Doktorka", "Brzy se uzdravte. Na shledanou.")
    )
    val englishLines = listOf(
        DialogueLine("Doctor", "Good day. Please sit down. What is troubling you?"),
        DialogueLine("Patient", "Good day. I don't feel well. I have a fever and chills, and my head hurts."),
        DialogueLine("Doctor", "How high is your fever?"),
        DialogueLine("Patient", "I have 39.5 degrees. My muscles also ache — my back and shoulders."),
        DialogueLine("Doctor", "How long have you had these symptoms?"),
        DialogueLine("Patient", "Since yesterday evening."),
        DialogueLine("Doctor", "Alright. Let me examine you."),
        DialogueLine("Doctor", "You probably have the flu. Nothing serious, but you need to rest."),
        DialogueLine("Patient", "Should I take any medicine?"),
        DialogueLine("Doctor", "Yes. I recommend fever-reducing tablets. They are without prescription — you can buy them at the pharmacy."),
        DialogueLine("Patient", "How many times a day should I take them?"),
        DialogueLine("Doctor", "Three times a day, after meals. And drink plenty of fluids."),
        DialogueLine("Patient", "How long will I be sick?"),
        DialogueLine("Doctor", "Rest for a few days. You should feel better within a week."),
        DialogueLine("Patient", "Thank you, doctor."),
        DialogueLine("Doctor", "Get well soon. Goodbye.")
    )
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Doctor's Appointment", fontSize = 18.sp, fontWeight = FontWeight.Bold) },
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
fun IntroducingYourselfDialogueScreen(navController: NavController) {
    val czechLines = listOf(
        DialogueLine("Tomáš", "Dobrý den. Jmenuji se Tomáš. Jak se jmenujete?"),
        DialogueLine("Jana", "Dobrý den, Tomáši. Já jsem Jana. Odkud jste?"),
        DialogueLine("Tomáš", "Jsem z Prahy. Narodil jsem se tady. A vy, odkud jste?"),
        DialogueLine("Jana", "Jsem z Brna. Čím se zabýváte?"),
        DialogueLine("Tomáš", "Pracuji jako programátor. A co vy?"),
        DialogueLine("Jana", "Já pracuji jako učitelka. Jak dlouho jste v Praze?"),
        DialogueLine("Tomáš", "Celý život. Je mi třicet dva let. A vám?"),
        DialogueLine("Jana", "Mně je dvacet devět let. Máte nějaké koníčky?"),
        DialogueLine("Tomáš", "Rád čtu knihy a chodím na výlety. A vy?"),
        DialogueLine("Jana", "Mám ráda hudbu a sport. Hraju tenis."),
        DialogueLine("Tomáš", "To je skvělé. Těší mě."),
        DialogueLine("Jana", "I mě těší. Na shledanou, Tomáši.")
    )
    val englishLines = listOf(
        DialogueLine("Tomáš", "Good day. My name is Tomáš. What is your name?"),
        DialogueLine("Jana", "Good day, Tomáš. I am Jana. Where are you from?"),
        DialogueLine("Tomáš", "I am from Prague. I was born here. And you, where are you from?"),
        DialogueLine("Jana", "I am from Brno. What do you do for work?"),
        DialogueLine("Tomáš", "I work as a programmer. And you?"),
        DialogueLine("Jana", "I work as a teacher. How long have you been in Prague?"),
        DialogueLine("Tomáš", "My whole life. I am thirty-two years old. And you?"),
        DialogueLine("Jana", "I am twenty-nine years old. Do you have any hobbies?"),
        DialogueLine("Tomáš", "I enjoy reading books and going on trips. And you?"),
        DialogueLine("Jana", "I like music and sport. I play tennis."),
        DialogueLine("Tomáš", "That is great. Nice to meet you."),
        DialogueLine("Jana", "Nice to meet you too. Goodbye, Tomáš.")
    )
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Introducing Yourself", fontSize = 18.sp, fontWeight = FontWeight.Bold) },
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
fun SupermarketDialogueScreen(navController: NavController) {
    val czechLines = listOf(
        DialogueLine("Zákazník", "Dobrý den. Hledám zeleninu na polévku. Kde najdu mrkev a celer?"),
        DialogueLine("Pracovnice", "Dobrý den. Zelenina je v zadní části obchodu, ve třetí uličce."),
        DialogueLine("Zákazník", "Děkuji. A kde jsou koření a bylinky?"),
        DialogueLine("Pracovnice", "Koření najdete v páté uličce, vedle těstovin."),
        DialogueLine("Zákazník", "Skvělé. Prodáváte taky potraviny bez lepku? Hledám bezlepkové těstoviny."),
        DialogueLine("Pracovnice", "Ano. Bezlepkové produkty jsou ve druhé uličce, vedle mléčných výrobků."),
        DialogueLine("Zákazník", "A máte sekci pro dietní nebo keto produkty?"),
        DialogueLine("Pracovnice", "Ano, keto a dietní produkty jsou na konci druhé uličky, napravo."),
        DialogueLine("Zákazník", "Výborně. Ještě jedna otázka — kde jsou čerstvé bylinky, třeba petržel?"),
        DialogueLine("Pracovnice", "Čerstvé bylinky jsou u zeleniny, vedle rajčat."),
        DialogueLine("Zákazník", "Moc děkuji za pomoc."),
        DialogueLine("Pracovnice", "Rádo se stalo. Přeji hezký den.")
    )
    val englishLines = listOf(
        DialogueLine("Customer", "Good day. I am looking for vegetables for soup. Where can I find carrot and celery?"),
        DialogueLine("Shop Worker", "Good day. The vegetables are at the back of the store, in the third aisle."),
        DialogueLine("Customer", "Thank you. And where are the spices and herbs?"),
        DialogueLine("Shop Worker", "You will find spices in the fifth aisle, next to the pasta."),
        DialogueLine("Customer", "Great. Do you also sell gluten-free food? I am looking for gluten-free pasta."),
        DialogueLine("Shop Worker", "Yes. Gluten-free products are in the second aisle, next to the dairy products."),
        DialogueLine("Customer", "And do you have a section for dietary or keto products?"),
        DialogueLine("Shop Worker", "Yes, keto and dietary products are at the end of the second aisle, on the right."),
        DialogueLine("Customer", "Excellent. One more question — where are the fresh herbs, for example parsley?"),
        DialogueLine("Shop Worker", "Fresh herbs are by the vegetables, next to the tomatoes."),
        DialogueLine("Customer", "Thank you very much for your help."),
        DialogueLine("Shop Worker", "You're welcome. Have a nice day.")
    )
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("At the Supermarket", fontSize = 18.sp, fontWeight = FontWeight.Bold) },
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
fun TrainStationDialogueScreen(navController: NavController) {
    val czechLines = listOf(
        DialogueLine("Cestující", "Dobrý den. Chtěl bych jet do Prahy. Kdy odjíždí nejbližší vlak?"),
        DialogueLine("Zaměstnanec", "Dobrý den. Máte dvě možnosti. Za dvacet minut odjíždí vlak, ale musíte přestupovat v Pardubicích. Přímý vlak jede až za hodinu."),
        DialogueLine("Cestující", "Rozumím. A kde si mohu koupit jízdenku?"),
        DialogueLine("Zaměstnanec", "Jízdenku si můžete koupit na pokladně — je hned tady napravo — nebo v automatech na jízdenky, které jsou u vchodu."),
        DialogueLine("Cestující", "Děkuji. Ještě se chci zeptat — jak funguje MHD v Praze? Platí se za každou jízdu, nebo je lepší koupit denní nebo týdenní jízdenku?"),
        DialogueLine("Zaměstnanec", "Obojí je možné. Jednorázová jízdenka stojí čtyřicet korun a platí devadesát minut. Denní jízdenka stojí sto deset korun a týdenní třista deset korun. Záleží na tom, kolik plánujete cestovat."),
        DialogueLine("Cestující", "Jasně. A musím jízdenku nějak označit nebo zkompostovat?"),
        DialogueLine("Zaměstnanec", "Ano, určitě. V Praze jsou žluté kompostéry — označovací přístroje. Musíte jízdenku označit před nastoupením nebo ihned po nastoupení do vozidla. Bez označení je jízdenka neplatná."),
        DialogueLine("Cestující", "A jsou kompostéry přímo ve vozidlech?"),
        DialogueLine("Zaměstnanec", "Ano, jsou uvnitř — v tramvajích, autobusech i v metru u vstupu na nástupiště. Nemůžete je přehlédnout, jsou žluté."),
        DialogueLine("Cestující", "Výborně. Moc vám děkuji za informace."),
        DialogueLine("Zaměstnanec", "Prosím. Přeji vám příjemnou cestu do Prahy!")
    )
    val englishLines = listOf(
        DialogueLine("Traveler", "Good day. I would like to go to Prague. When does the next train depart?"),
        DialogueLine("Employee", "Good day. You have two options. A train leaves in twenty minutes, but you have to transfer in Pardubice. A direct train goes in one hour."),
        DialogueLine("Traveler", "I understand. And where can I buy a ticket?"),
        DialogueLine("Employee", "You can buy a ticket at the ticket booth — it is right here on the right — or at the ticket vending machines, which are by the entrance."),
        DialogueLine("Traveler", "Thank you. I also want to ask — how does public transportation work in Prague? Do you pay per ride, or is it better to buy a daily or weekly pass?"),
        DialogueLine("Employee", "Both are possible. A single ticket costs forty crowns and is valid for ninety minutes. A daily pass costs one hundred and ten crowns and a weekly pass three hundred and ten crowns. It depends on how much you plan to travel."),
        DialogueLine("Traveler", "I see. And do I need to validate or stamp the ticket somehow?"),
        DialogueLine("Employee", "Yes, definitely. In Prague there are yellow validators — called kompostéry. You must validate the ticket before boarding or immediately after boarding the vehicle. Without validation the ticket is invalid."),
        DialogueLine("Traveler", "And are the validators right inside the vehicles?"),
        DialogueLine("Employee", "Yes, they are inside — on trams, buses, and in the metro at the entrance to the platform. You cannot miss them; they are yellow."),
        DialogueLine("Traveler", "Excellent. Thank you very much for the information."),
        DialogueLine("Employee", "You are welcome. I wish you a pleasant journey to Prague!")
    )
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("At the Train Station", fontSize = 18.sp, fontWeight = FontWeight.Bold) },
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
