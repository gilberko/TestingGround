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
            FORow("zelenina", "vegetables (general)", "f. — collective noun: all vegetables collectively")
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
            FORow("ovoce", "fruit (general)", "n. — collective noun, always singular: ovoce je čerstvé = the fruit is fresh")
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
            FORow("skořice", "cinnamon", "f.")
            FORow("paprika", "paprika / spice", "f. — same word as the pepper vegetable; context distinguishes")
            FORow("kopr", "dill", "m.")
            FORow("koriandr", "coriander", "m.")
            FORow("omáčka", "sauce", "f.")
            FORow("koření", "seasoning / spices (general)", "n. — collective noun for all spices and seasonings")
            FORow("sojová omáčka", "soy sauce", "f.")

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
            FORow("grilované kuře", "grilled chicken", "grilované = grilled (adj, n.); kuře = chicken (n.)")

            FOSection("Czech Traditional Dishes (Tradiční česká jídla)")
            FORow("svíčková na smetaně", "beef sirloin in cream sauce", "f. — one of the most iconic Czech dishes. Braised beef sirloin (svíčková = the sirloin/tenderloin cut; named after the candle-like shape — svíčka = candle) in a creamy sauce made from root vegetables and cream (smetana). Always served with houskové knedlíky (bread dumplings), brusinky (cranberry sauce), and a dollop of šlehačka (whipped cream).")
            FORow("knedlíky", "Czech dumplings", "pl. of knedlík (m.) — boiled dough, never fried. Two main types: houskové knedlíky (bread dumplings — made with bread rolls and flour; served alongside svíčková, guláš, and other sauced dishes) and bramborové knedlíky (potato dumplings — denser; often filled with fruit for a dessert version).")
            FORow("houskové knedlíky", "bread dumplings", "pl. — the classic side dish for svíčková, guláš, and other Czech sauced meals. houska = bread roll; knedlíky = dumplings.")
            FORow("bramborové knedlíky", "potato dumplings", "pl. — denser than bread dumplings; used both as a side dish and (with fruit filling) as a dessert.")
            FORow("vepřo-knedlo-zelo", "roast pork with dumplings and sauerkraut", "a beloved Czech pub meal, often listed on menus by this three-part shorthand. vepřo = vepřová (pork), knedlo = knedlíky (dumplings), zelo = zelí (cabbage/sauerkraut — dialectal/archaic form). A classic Czech Sunday meal.")
            FORow("řízek / vepřový řízek", "schnitzel / pork schnitzel", "m. — breaded and pan-fried meat cutlet; the Czech everyday equivalent of Wiener Schnitzel. vepřový = pork (the most common version). kuřecí řízek = chicken schnitzel. Served with brambory (potatoes) or bramborový salát (potato salad).")
            FORow("bramborák", "Czech potato pancake", "m. — also called cmunda or strik in some regions. Made from grated raw potatoes with egg, flour, garlic, and marjoram. Fried until crispy. A popular snack and side dish.")

            FOSection("Desserts & Sweets")
            FORow("zmrzlina", "ice cream", "f.")
            FORow("dort", "cake", "m. — typically a layered celebration cake with cream or ganache")
            FORow("čokoládový dort", "chocolate cake", "m.")
            FORow("závin", "rolled pastry / strudel", "m. — the traditional Czech word for a strudel-type pastry filled with fruit, nuts, or cheese. Apple version = jablečný závin. A Czech classic.")
            FORow("štrúdl", "strudel", "m. — the German/Austrian loanword for the same dish. Závin and štrúdl are interchangeable in everyday Czech; štrúdl feels slightly more borrowed. Also spelled štrudl.")
            FORow("dezert", "dessert", "m. — general term")
            FORow("moučník", "sweet dish / dessert", "m. — traditional Czech term; broader than dezert")
            FORow("koláč", "Czech pastry / kolach", "m. — small round pastry with sweet filling; not the same as dort")
            FORow("vanilkový dort", "vanilla cake", "m.")
            FORow("jahodový dort", "strawberry cake", "m.")
            FORow("cheesecake / tvarohový koláč", "cheesecake", "m. — 'cheesecake' widely used; tvarohový = cream-cheese style")
            FORow("marshmallow", "marshmallow", "n. — borrowed word; pl. marshmallows")
            FORow("trdelník", "trdelník (Czech-Slovak pastry)", "m. — sweet rolled dough wrapped around a stick, grilled over charcoal, then coated with sugar and optionally cinnamon. Very popular street food, especially in Prague's historic centre. Originally a Slovak dish but now closely associated with Czech tourism.")
            FORow("trdelník s cukrem", "trdelník with sugar", "s + instrumental of cukr (m.) → s cukrem")
            FORow("trdelník se skořicí", "trdelník with cinnamon", "se + instrumental of skořice (f.) → se skořicí. se (not s) used before consonant clusters for easier pronunciation.")
            FORow("trdelník s cukrem a skořicí", "trdelník with sugar and cinnamon")
            FONote("Sugar and cinnamon alone: cukr (sugar, m.) — instrumental: s cukrem. skořice (cinnamon, f.) — instrumental: se skořicí. Both use the instrumental case with preposition s/se.")

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
            FORow("suchý / suchá / suché", "dry")
            FORow("šťavnatý / šťavnatá / šťavnaté", "juicy / moist")
            FORow("křehký / křehká / křehké", "tender")
            FORow("grilovaný / grilovaná / grilované", "grilled")

            FOSection("Food Questions & Recommendations")
            FORow("Co doporučujete?", "What do you recommend?")
            FORow("Který dort doporučujete?", "Which cake do you recommend?")
            FORow("Jaký je to dort?", "What kind of cake is it?")
            FORow("Je to chutné?", "Is it tasty?")
            FORow("Jak je propečený steak?", "What is the doneness of the steak?", "propečení = degree of doneness; propečený = cooked through")

            FOSection("Steak Doneness (Propečení)")
            FORow("dobře propečený", "well done", "fully cooked through")
            FORow("středně propečený", "medium-well", "středně = halfway/medium")
            FORow("středně krvavý", "medium", "krvavý = bloody; středně = medium")
            FORow("krvavý / málo propečený", "rare", "krvavý = bloody; málo = little/barely")
            FONote("In Czech restaurants the English terms 'medium' and 'rare' are also widely understood.")

            FOSection("Allergens & Dietary")
            FORow("alergeny", "allergens", "pl., m.")
            FORow("lepek", "gluten", "m.")
            FORow("sladidlo", "sweetener", "n.")
            FORow("umělé sladidlo", "artificial sweetener", "n.")
            FORow("Jsem alergický / alergická na mléko.", "I am allergic to milk.", "alergický = m.; alergická = f.")
            FORow("Jsem alergický / alergická na ořechy.", "I am allergic to nuts.", "ořechy = accusative plural of ořech (nut)")
            FORow("Obsahuje to ořechy?", "Does it contain nuts?", "obsahuje = 3rd sg. of obsahovat (to contain)")
            FORow("Je to bez lepku?", "Is it gluten free?", "bez + genitive: bez lepku")

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

@Composable
private fun FONote(text: String) {
    Text(
        text = text,
        fontSize = 13.sp,
        fontStyle = FontStyle.Italic,
        color = Color.Gray,
        modifier = Modifier.padding(vertical = 2.dp)
    )
}
