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
fun FoodScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Food", fontSize = 18.sp, fontWeight = FontWeight.Bold) },
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
            FOSection("Meal Times")
            FORow("snídaně", "breakfast", "f.")
            FORow("oběd", "lunch", "m.")
            FORow("večeře", "dinner", "f.")
            FORow("svačina", "snack", "f.")
            FORow("jídlo", "food / meal", "n.")

            FOSection("Bread & Bakery")
            FORow("chléb", "bread", "m. — colloquial: chleba")
            FORow("houska / rohlík", "bun", "f. / m. — houska is a round roll; rohlík is a crescent roll")
            FORow("těsto", "dough", "n.")
            FORow("párek v rohlíku", "hot dog", "m.")

            FOSection("Dairy")
            FORow("mléko", "milk", "n.")
            FORow("máslo", "butter", "n.")
            FORow("sýr", "cheese", "m.")
            FORow("tavený sýr", "melted / processed cheese", "e.g. Laughing Cow type; tavený = melted")

            FOSection("Meat & Fish")
            FORow("maso", "meat", "n.")
            FORow("ryba", "fish", "f.")
            FORow("kuřecí maso", "chicken", "n. — kuřecí is an adjective meaning 'chicken'; used with maso or implied")
            FORow("krůtí maso", "turkey meat", "n.")
            FORow("hovězí maso", "beef", "n.")
            FORow("jehněčí maso", "lamb", "n.")
            FORow("vepřové maso", "pork", "n. — vepřové = of pork")
            FORow("steak", "steak", "m.")
            FORow("špízy", "skewers", "pl., m.")

            FOSection("Vegetables")
            FORow("okurka", "cucumber", "f.")
            FORow("salát", "salad", "m.")
            FORow("hlávkový salát", "lettuce", "m.")
            FORow("paprika", "pepper (vegetable)", "f.")
            FORow("cibule", "onion", "f.")
            FORow("rajče", "tomato", "n.")
            FORow("petrželka", "parsley", "f.")
            FORow("brambor", "potato", "m.")
            FORow("batát", "sweet potato", "m.")
            FORow("bramborová kaše", "mashed potatoes", "f.")
            FORow("zelený salát", "green salad / lettuce", "salát = salad or lettuce depending on context")

            FOSection("Fruits")
            FORow("citron", "lemon", "m.")
            FORow("pomeranč", "orange", "m.")
            FORow("jahody", "strawberries", "pl., f.")
            FORow("brusinky", "cranberries", "pl., f.")

            FOSection("Grains & Carbs")
            FORow("rýže", "rice", "f.")
            FORow("těstoviny", "pasta", "pl., f.")
            FORow("pizza", "pizza", "f.")
            FORow("mouka", "flour", "f.")
            FORow("kukuřice", "corn", "f.")
            FORow("pšenice", "wheat", "f.")

            FOSection("Condiments & Spices")
            FORow("majonéza", "mayonnaise", "f.")
            FORow("kečup", "ketchup", "m.")
            FORow("sůl", "salt", "f.")
            FORow("cukr", "sugar", "m.")
            FORow("pepř", "black pepper", "m. — černý pepř")
            FORow("paprika", "paprika / spice", "f. — same word as the pepper vegetable; context distinguishes")
            FORow("kopr", "dill", "m.")
            FORow("koriandr", "coriander", "m.")
            FORow("omáčka", "sauce", "f.")

            FOSection("Utensils & Tableware")
            FORow("vidlička", "fork", "f.")
            FORow("nůž", "knife", "m.")
            FORow("lžíce", "spoon", "f.")
            FORow("čajová lžička", "teaspoon", "f.")
            FORow("talíř", "plate", "m.")
            FORow("miska", "bowl", "f.")
            FORow("sklenice", "glass", "f. — e.g. sklenice koly = a glass of cola")
            FORow("šálek", "cup", "m.")
            FORow("ubrousek", "napkin", "m.")

            FOSection("Cookware & Dishes")
            FORow("hrnec", "pot", "m.")
            FORow("pánev", "pan", "f.")
            FORow("polévka", "soup", "f.")
            FORow("guláš", "stew / goulash", "m.")

            FOSection("Hot Drinks")
            FORow("čaj", "tea", "m.")
            FORow("káva", "coffee", "f.")
            FORow("cappuccino", "cappuccino", "n.")
            FORow("espresso", "espresso", "n.")
            FORow("matcha", "matcha", "f.")

            FOSection("Alcohol & Cold Drinks")
            FORow("voda", "water", "f.")
            FORow("pivo", "beer", "n.")
            FORow("víno", "wine", "n.")
            FORow("vodka", "vodka", "f.")
            FORow("whisky", "whiskey", "f.")
            FORow("bourbon", "bourbon", "m.")
            FORow("žitná", "rye whiskey", "f. — short for žitná whisky; rye grain = žito")
            FORow("gin", "gin", "m.")
            FORow("džus", "juice (packaged)", "šťáva = freshly squeezed juice")
            FORow("neperlivá voda", "still water", "also: voda bez bublinek")
            FORow("perlivá voda", "sparkling water", "colloquially: soda")

            FOSection("Soups (Polévky)")
            FORow("hovězí polévka", "beef soup", "hovězí = beef (adj)")
            FORow("bramborová polévka", "potato soup", "bramborová = potato (adj)")

            FOSection("Popular Dishes")
            FORow("vegetariánská pizza", "vegetarian pizza")
            FORow("italské těstoviny", "Italian pasta", "těstoviny = pasta (always plural)")

            FOSection("Desserts & Sweets")
            FORow("zmrzlina", "ice cream", "f.")
            FORow("dort", "cake", "m. — typically a layered celebration cake with cream or ganache")
            FORow("čokoládový dort", "chocolate cake", "m.")
            FORow("závin", "rolled pastry / strudel", "m. — the traditional Czech word for a strudel-type pastry filled with fruit, nuts, or cheese. Apple version = jablečný závin. A Czech classic.")
            FORow("štrúdl", "strudel", "m. — the German/Austrian loanword for the same dish. Závin and štrúdl are interchangeable in everyday Czech; štrúdl feels slightly more borrowed. Also spelled štrudl.")
            FORow("dezert", "dessert", "m. — general term")
            FORow("moučník", "sweet dish / dessert", "m. — traditional Czech term; broader than dezert")
            FORow("koláč", "Czech pastry / kolach", "m. — small round pastry with sweet filling; not the same as dort")

            FOSection("Ordering")
            FORow("dám si", "I'll have (ordering)", "literally 'I'll give myself'; see Basic Words for full explanation")
            FORow("jídelní lístek", "menu (the printed menu card)", "m. — also commonly just called menu (borrowed word)")
            FORow("menu", "set menu / today's lunch special", "n. — in Czech restaurants, 'menu' often means a fixed-price lunch deal")

            FOSection("Words to Describe Food")
            FORow("pikantní", "spicy")
            FORow("kyselý / kyselá / kyselé", "sour", "adjective agrees with noun gender")
            FORow("sladký / sladká / sladké", "sweet")
            FORow("horký / horká / horké", "hot (temperature)")
            FORow("studený / studená / studené", "cold")
            FORow("chutný / chutná / chutné", "tasty / delicious")
            FORow("nechutný / nechutná / nechutné", "not tasty / disgusting")
            FORow("velmi chutné", "very tasty")
            FORow("příliš pikantní", "too spicy")
            FORow("příliš kyselé", "too sour")
            FORow("příliš", "too / too much", "used before adjectives: příliš sladké = too sweet")

            FOSection("Allergens & Dietary")
            FORow("alergeny", "allergens", "pl., m.")
            FORow("lepek", "gluten", "m.")
            FORow("sladidlo", "sweetener", "n.")
            FORow("umělé sladidlo", "artificial sweetener", "n.")

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun FOSection(text: String) {
    Spacer(modifier = Modifier.height(20.dp))
    Text(text = text, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = ButtonBlue)
    Spacer(modifier = Modifier.height(6.dp))
}

@Composable
private fun FORow(czech: String, english: String, note: String = "") {
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
