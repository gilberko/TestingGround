package com.example.arabicapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun NumbersLearningScreen() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        NLSectionHeader("Numbers 0 – 10")
        NLBody("These are the forms you use when counting or saying a number on its own (a phone number, a price, \"how many? — five\").")
        NLBody("0 — صفر — sifr")
        NLBody("1 — واحد / وحدة — waa7ad (m) / wa7de (f)")
        NLBody("2 — تنين / تنتين — tnein (m) / tintein (f)  (also اتنين itnein)")
        NLBody("3 — تلاتة — tlaate  (Palestinian also talaate)")
        NLBody("4 — أربعة — arba3a")
        NLBody("5 — خمسة — khamse")
        NLBody("6 — ستة — sitte")
        NLBody("7 — سبعة — sab3a")
        NLBody("8 — تمانية — tamaanye  (Lebanese/Syrian tmaane)")
        NLBody("9 — تسعة — tis3a")
        NLBody("10 — عشرة — 3ashara")
        NLNote("Only 1 and 2 have a masculine and a feminine form. 3 and up do NOT change for gender in spoken Levantine (more below).")

        NLSectionHeader("11 – 19")
        NLBody("11 — حدعش — 7da3sh")
        NLBody("12 — تنعش — tna3sh")
        NLBody("13 — تلتعش — tlatta3sh")
        NLBody("14 — أربعتعش — arba3ta3sh")
        NLBody("15 — خمستعش — khamsta3sh")
        NLBody("16 — ستعش — sitta3sh")
        NLBody("17 — سبعتعش — saba3ta3sh")
        NLBody("18 — تمنتعش — tamanta3sh")
        NLBody("19 — تسعتعش — tisi3ta3sh")
        NLNote("Before a noun they add -ar: حدعشر ولد — 7da3shar walad — 11 boys · خمستعشر سنة — khamsta3shar sane — 15 years.")

        NLSectionHeader("Tens, and Numbers Like 35")
        NLBody("20 — عشرين — 3ishriin · 30 — تلاتين — tlaatiin · 40 — أربعين — arb3iin · 50 — خمسين — khamsiin")
        NLBody("60 — ستين — sittiin · 70 — سبعين — sab3iin · 80 — تمانين — tamaaniin · 90 — تسعين — tis3iin")
        Spacer(modifier = Modifier.height(8.dp))
        NLBody("Yes — you're right: the ONES come first, then w- (and), then the TENS. 35 is literally \"five and thirty\":")
        NLExample("35 — خمسة وتلاتين — khamse w-tlaatiin  (5 and 30)")
        NLExample("21 — واحد وعشرين — waa7ad w-3ishriin  (1 and 20)")
        NLExample("48 — تمانية وأربعين — tamaanye w-arb3iin  (8 and 40)")
        NLExample("99 — تسعة وتسعين — tis3a w-tis3iin  (9 and 90)")
        NLNote("Hebrew does the opposite (שלושים וחמש = thirty and five). German does the same as Arabic (fünfunddreißig), and so did old English (\"five and twenty\").")

        NLSectionHeader("Hundreds and Thousands")
        NLBody("100 — مية — miyye · 200 — ميتين — mitein · 300 — تلتمية — tlatmiyye · 1000 — ألف — alf · 2000 — ألفين — alfein · 3000 — تلت آلاف — tlat-t-aalaaf")
        NLExample("135 — مية وخمسة وتلاتين — miyye w-khamse w-tlaatiin")
        NLNote("Before a noun, miyye becomes miit: ميت شيكل — miit shekel — 100 shekels.")

        NLSectionHeader("Counting Things — 1 Bird, 2 Birds, 3 Birds")
        NLBody("bird — عصفور — 3asfuur (m)")
        NLInfo("m · 2: عصفورين 3asfuurein · pl: عصافير 3asafiir   (also طير teir, pl. طيور tyuur)")
        Spacer(modifier = Modifier.height(8.dp))
        NLSubHeader("One — the noun alone, or the number AFTER it (like Hebrew)")
        NLExample("عندي عصفور — 3indi 3asfuur — I have a bird / one bird")
        NLExample("عندي عصفور واحد — 3indi 3asfuur waa7ad — I have ONE bird  (stressing the number — like ציפור אחת)")
        NLSubHeader("Two — the dual ending -ein (no number word needed)")
        NLExample("عندي عصفورين — 3indi 3asfuurein — I have two birds")
        NLBody("Just like Hebrew יומיים, שבועיים — but in Arabic the dual is used for almost every noun. To stress it you can add tnein after: 3asfuurein tnein.")
        NLSubHeader("3 to 10 — short number BEFORE + PLURAL noun")
        NLExample("عندي تلت عصافير — 3indi tlat 3asafiir — I have 3 birds")
        NLExample("عندي خمس عصافير — 3indi khams 3asafiir — I have 5 birds")
        NLBody("Before a noun the number drops its final -e/-a: tlaate → tlat, arba3a → arba3, khamse → khams, sitte → sitt, sab3a → saba3, tamaanye → taman, tis3a → tisa3, 3ashara → 3ashar.")
        NLNote("Exceptions: money and measure words keep the long form: تلاتة شيكل — tlaate shekel · خمسة كيلو — khamse kiilo. Before aalaaf (thousands) and iyyaam (days) a t is added: tlat-t-iyyaam — 3 days.")
        NLSubHeader("11 and up — number BEFORE + SINGULAR noun")
        NLExample("عندي حدعشر عصفور — 3indi 7da3shar 3asfuur — I have 11 birds")
        NLExample("عندي عشرين عصفور — 3indi 3ishriin 3asfuur — I have 20 birds")
        NLExample("عندي مية عصفور — 3indi miit 3asfuur — I have 100 birds")
        Spacer(modifier = Modifier.height(8.dp))
        NLBody("So you were partly right: like Hebrew, \"one\" comes after the noun and the other numbers come before. The difference is \"two\", which in Arabic is normally just the dual ending.")

        NLSectionHeader("Does the Number Match the Gender?")
        NLBody("Only 1 and 2. Example with a feminine noun: cat — بسّة — bisse (f)")
        NLExample("بسّة وحدة — bisse wa7de — one cat  (wa7de, feminine)  ·  عصفور واحد — 3asfuur waa7ad — one bird (masculine)")
        NLExample("بسّتين — bisstein — two cats  ·  stressed: bisstein tintein")
        NLExample("تلت بسس — tlat bisas — 3 cats  ·  تلت عصافير — tlat 3asafiir — 3 birds  (same tlat for both)")
        NLNote("From 3 up, spoken Levantine does NOT match gender. Formal Arabic (MSA) does have a gender rule for 3–10 (the number takes the opposite gender), and Hebrew has שלוש/שלושה — but you don't need either in everyday speech. tlaate vs tlat is not masculine vs feminine; it's \"alone\" vs \"before a noun\".")

        NLSectionHeader("Singular or Plural Noun? — Summary")
        NLBody("1 → singular noun (number optional, after)")
        NLBody("2 → dual -ein (number optional, after)")
        NLBody("3–10 → short number + PLURAL noun")
        NLBody("11–99 → number (-ar form for 11–19) + SINGULAR noun")
        NLBody("100, 1000 → miit / alf + SINGULAR noun")

        NLSectionHeader("Ordinal Numbers — First, Second, Third")
        NLBody("m / f forms:")
        NLBody("1st — أوّل / أولى — awwal / uula")
        NLBody("2nd — تاني / تانية — taani / taanye")
        NLBody("3rd — تالت / تالتة — taalit / taalte")
        NLBody("4th — رابع / رابعة — raabi3 / raab3a")
        NLBody("5th — خامس / خامسة — khaamis / khaamse")
        NLBody("6th — سادس / سادسة — saadis / saadse")
        NLBody("7th — سابع / سابعة — saabi3 / saab3a")
        NLBody("8th — تامن / تامنة — taamin / taamne")
        NLBody("9th — تاسع / تاسعة — taasi3 / taas3a")
        NLBody("10th — عاشر / عاشرة — 3aashir / 3aashra")
        NLBody("last — آخر — aakhir")

        NLSubHeader("Two ways to place them")
        NLBody("A) BEFORE the noun — always the masculine form, no el-, NO agreement at all (the most common way to say \"first\"):")
        NLExample("أوّل بيت — awwal beit — the first house")
        NLExample("أوّل مرا — awwal mara — the first wife  (mara = woman / wife — and still awwal, not uula)")
        NLExample("أوّل جوز — awwal jooz — the first husband")
        NLExample("أوّل أرباح بالكازينو — awwal arbaa7 bil-kaaziino — the first winnings at the casino  (arbaa7 = profits/winnings, plural — and still awwal)")
        NLExample("تاني يوم — taani yoom — the next day / the second day · آخر مرة — aakhir marra — the last time")
        Spacer(modifier = Modifier.height(8.dp))
        NLBody("B) AFTER the noun — el- on both words, and the ordinal MATCHES the noun's gender (like an adjective):")
        NLExample("البيت الأوّل — el-beit el-awwal — the first house  (m)")
        NLExample("المرا الأولى — el-mara el-uula — the first wife  (f)  ·  or with possessive: martu el-uula — his first wife")
        NLExample("الجوز الأوّل — ej-jooz el-awwal — the first husband  (m)  ·  jooz-ha el-awwal — her first husband")
        NLExample("الأرباح الأولى — el-arbaa7 el-uula — the first winnings  (plural things usually take the feminine singular form)")
        NLNote("Plural ordinals exist for people (taanyiin = second ones, awaa2il = the first ones) but are rare — for \"first winnings\" just say awwal arbaa7.")
        Spacer(modifier = Modifier.height(8.dp))
        NLBody("Careful: أوّل مرّة — awwal marra (double r) = the first TIME;  أوّل مرا — awwal mara (single r) = the first WIFE.")

        NLSubHeader("Above 10th — use the normal number AFTER the noun")
        NLExample("الطابق الحدعش — et-taabi2 el-7da3sh — the 11th floor")
        NLExample("المرة العشرين — el-marra el-3ishriin — the 20th time")

        NLSectionHeader("Quick Summary")
        NLBody("1. 35 = khamse w-tlaatiin — ones first, then tens")
        NLBody("2. one bird = 3asfuur (waa7ad) · two birds = 3asfuurein · 3 birds = tlat 3asafiir · 11 birds = 7da3shar 3asfuur")
        NLBody("3. Only 1 and 2 change for gender (waa7ad/wa7de, tnein/tintein)")
        NLBody("4. Ordinal before the noun = awwal for everything (awwal beit, awwal mara, awwal arbaa7)")
        NLBody("5. Ordinal after the noun = agrees: el-beit el-awwal, el-mara el-uula")

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun NLSectionHeader(text: String) {
    Spacer(modifier = Modifier.height(20.dp))
    Text(
        text = fixArabizi(text),
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.fillMaxWidth()
    )
    Spacer(modifier = Modifier.height(8.dp))
    HorizontalDivider()
    Spacer(modifier = Modifier.height(8.dp))
}

@Composable
private fun NLSubHeader(text: String) {
    Spacer(modifier = Modifier.height(10.dp))
    Text(
        text = fixArabizi(text),
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(vertical = 2.dp)
    )
}

@Composable
private fun NLBody(text: String) {
    Text(
        text = fixArabizi(text),
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(vertical = 2.dp)
    )
}

@Composable
private fun NLExample(text: String) {
    Text(
        text = fixArabizi("• $text"),
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(start = 12.dp, top = 2.dp, bottom = 2.dp)
    )
}

@Composable
private fun NLInfo(text: String) {
    Text(
        text = fixArabizi("    $text"),
        style = MaterialTheme.typography.bodySmall,
        modifier = Modifier.padding(vertical = 1.dp)
    )
}

@Composable
private fun NLNote(text: String) {
    Text(
        text = fixArabizi(text),
        style = MaterialTheme.typography.bodySmall,
        fontStyle = FontStyle.Italic,
        modifier = Modifier.padding(vertical = 4.dp)
    )
}
