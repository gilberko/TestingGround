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
fun ClothesScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Clothes", fontSize = 16.sp, fontWeight = FontWeight.Bold) },
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
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            CLNote("Watch out: kalhoty, šaty, džíny, kraťasy, punčocháče and kalhotky are plural-only, like English \"pants\" — even for one piece: Ty kalhoty jsou nové. (Those pants are new.)")
            CLRow("oblečení", "clothes / clothing", "n. — the general word")

            CLSection("Outerwear")
            CLRow("kabát", "coat")
            CLRow("bunda", "jacket", "a casual / outdoor jacket. sako = suit jacket / blazer.")
            CLRow("pláštěnka", "raincoat")
            CLRow("plášť / pelerína", "cape / cloak", "plášť is also a long coat (e.g. a doctor's white coat); pelerína = a sleeveless cape")
            CLRow("šála", "scarf (warm, for winter)", "šátek = headscarf / neckerchief")
            CLRow("rukavice", "gloves", "f. — the same word for one glove and for the pair; palčáky = mittens")

            CLSection("Tops")
            CLRow("top", "top (a women's fitted top)", "English \"top\" can mean any upper garment; Czech top is a specific short women's top")
            CLRow("tílko", "tank top / vest (sleeveless)")
            CLRow("halenka", "blouse")
            CLRow("tričko", "T-shirt", "tričko s dlouhým rukávem = long-sleeved T-shirt")
            CLRow("košile", "shirt (with buttons and collar)")
            CLRow("svetr", "sweater / jumper")
            CLRow("mikina", "sweatshirt / hoodie", "mikina s kapucí = hoodie")

            CLSection("Bottoms")
            CLRow("kalhoty", "pants / trousers", "plural only")
            CLRow("kraťasy / šortky", "shorts", "plural only")
            CLRow("džíny / rifle", "jeans", "plural only; both words are common")
            CLRow("sukně", "skirt")
            CLRow("pásek / opasek", "belt", "pásek is the everyday word; opasek is a wider / heavier belt")

            CLSection("Dresses and Nightwear")
            CLRow("šaty", "dress", "plural only — even one dress is ty šaty. Also an older word for clothes in general.")
            CLRow("večerní šaty / róba", "gown / evening dress", "róba is also a judge's or academic robe")
            CLRow("noční košile", "nightgown", "lit. \"night shirt\"")
            CLRow("pyžamo", "pajamas", "n. — singular in Czech: to pyžamo")

            CLSection("Underwear and Socks")
            CLRow("spodní prádlo", "underwear", "prádlo alone = laundry / linen")
            CLRow("podprsenka", "bra", "from pod (under) + prsa (breasts)")
            CLRow("kalhotky", "panties", "plural only; lit. \"little pants\"")
            CLRow("boxerky", "boxers / boxer briefs (fitted)")
            CLRow("trenýrky / trenky", "boxer shorts (loose)", "also sports / gym shorts; trenky is colloquial")
            CLRow("slipy", "briefs")
            CLRow("ponožky", "socks", "one sock = ponožka")
            CLRow("punčocháče", "pantyhose / tights", "plural only")
            CLRow("punčochy", "stockings")

            CLSection("Hats and Caps")
            CLRow("čepice", "cap / hat (without a brim)", "the general word for soft headwear")
            CLRow("zimní čepice / kulich", "winter hat / beanie", "kulich is a knitted winter hat, often with a pompom (bambule)")
            CLRow("kšiltovka", "baseball cap", "from kšilt = the visor / peak")
            CLRow("klobouk", "hat (with a brim)")
            CLRow("kipa / jarmulka", "kippah / yarmulke", "kipa is the usual Czech term; jarmulka is also used")

            CLSection("Fabric")
            CLRow("látka", "fabric", "see Colors & Materials → Fabrics and Clothing for bavlna, hedvábí, kůže...")

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun CLSection(text: String) {
    Spacer(modifier = Modifier.height(20.dp))
    Text(text = text, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = ButtonBlue)
    Spacer(modifier = Modifier.height(6.dp))
}

@Composable
private fun CLNote(text: String) {
    Text(
        text = text,
        fontSize = 13.sp,
        fontStyle = FontStyle.Italic,
        color = Color.Gray,
        modifier = Modifier.padding(vertical = 2.dp)
    )
}

@Composable
private fun CLRow(czech: String, english: String, note: String = "") {
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
