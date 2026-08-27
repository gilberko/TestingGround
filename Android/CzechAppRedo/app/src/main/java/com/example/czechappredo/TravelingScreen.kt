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
fun TravelingScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Traveling", fontSize = 18.sp, fontWeight = FontWeight.Bold) },
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

            TRSection("Preparing to Travel")
            TRRow("balit / sbalit", "to pack")
            TRRow("doklady", "documents")
            TRRow("řidičský průkaz", "driver's license")
            TRRow("peněženka", "wallet")
            TRRow("batoh", "backpack")
            TRRow("plánovat", "to plan")
            TRRow("mapa", "a map")
            TRRow("výlet", "a trip", "a shorter excursion/outing — see the note under \"a road\" below for cesta, the broader word for \"way/journey.\"")
            TRRow("cestovat", "to travel")
            TRRow("cestování", "travel / traveling (noun)")

            TRSection("Getting Around")
            TRRow("půjčit si kolo", "to rent a bicycle")
            TRRow("kolo / jízdní kolo", "bicycle")
            TRRow("silnice", "a road", "cesta also works and is more general — it can mean \"way,\" \"path,\" or \"journey\" as well as \"road.\"")
            TRRow("dálnice", "highway")
            TRRow("parkoviště", "parking")
            TRRow("vesnice", "village")
            TRRow("město", "city")
            TRRow("místo", "place")

            TRSection("Around Town")
            TRRow("náměstí", "city square")
            TRRow("ulice", "street")
            TRRow("třída", "avenue / boulevard", "a wide city street; alej (below) is the tree-lined kind.")
            TRRow("alej", "avenue (tree-lined)")
            TRRow("kopec", "hill")

            TRSection("Nature and Views")
            TRRow("výhled", "a view")
            TRRow("kochat se výhledem / užívat si výhled", "to enjoy the view")
            TRRow("sever / jih / západ / východ", "north / south / west / east")
            TRRow("zeleň", "greenery")
            TRRow("les", "forest")
            TRRow("písek", "sand")
            TRRow("pole", "field")
            TRRow("květiny", "flowers")
            TRRow("tráva", "grass")

            TRSection("Packing Essentials")
            TRRow("lahev na vodu", "water bottle")
            TRRow("polní láhev", "canteen (flask)", "a sturdier carried water flask — distinct from a plain water bottle.")
            TRRow("lékárnička", "first aid kit")
            TRRow("obvazy", "bandages")

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun TRSection(text: String) {
    Spacer(modifier = Modifier.height(20.dp))
    Text(text = text, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = ButtonBlue)
    Spacer(modifier = Modifier.height(6.dp))
}

@Composable
private fun TRRow(czech: String, english: String, note: String = "") {
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
