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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun NumbersScreen() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        NumSectionHeader("0 – 10")
        NumBodyText("0 — صفر — sifr")
        NumBodyText("1 — واحد — waḥad")
        NumBodyText("2 — اثنين — ithnen")
        NumBodyText("3 — ثلاثة — talate")
        NumBodyText("4 — أربعة — arba'a")
        NumBodyText("5 — خمسة — khamse")
        NumBodyText("6 — ستة — sitte")
        NumBodyText("7 — سبعة — sab'a")
        NumBodyText("8 — ثمانية — tmane")
        NumBodyText("9 — تسعة — tis'a")
        NumBodyText("10 — عشرة — 'ashara")

        NumSectionHeader("Tens: 10 – 90")
        NumBodyText("10 — عشرة — 'ashara")
        NumBodyText("20 — عشرين — 'ashrin")
        NumBodyText("30 — ثلاثين — tlathin")
        NumBodyText("40 — أربعين — arba'in")
        NumBodyText("50 — خمسين — khamsin")
        NumBodyText("60 — ستين — sittin")
        NumBodyText("70 — سبعين — sab'in")
        NumBodyText("80 — ثمانين — tmanin")
        NumBodyText("90 — تسعين — tis'in")

        NumSectionHeader("Combinations — e.g. 51")
        NumBodyText("In Palestinian Arabic, compound numbers are formed: ones + و (w, \"and\") + tens.")
        Spacer(modifier = Modifier.height(4.dp))
        NumBodyText("Pattern: ones w-tens")
        Spacer(modifier = Modifier.height(8.dp))
        NumBodyText("21 — واحد وعشرين — waḥad w-'ashrin")
        NumBodyText("35 — خمسة وثلاثين — khamse w-tlathin")
        NumBodyText("51 — واحد وخمسين — waḥad w-khamsin")
        NumBodyText("47 — سبعة وأربعين — sab'a w-arba'in")
        NumBodyText("99 — تسعة وتسعين — tis'a w-tis'in")
        Spacer(modifier = Modifier.height(8.dp))
        NumBodyText("Note: the ones digit always comes first in Palestinian colloquial (opposite to the English order \"fifty-one\").")

        NumSectionHeader("Hundreds and 1000")
        NumBodyText("100 stands alone as مية (miyye). 200 uses the dual form. 300–900 combine the digit with مية.")
        Spacer(modifier = Modifier.height(8.dp))
        NumBodyText("100 — مية — miyye")
        NumBodyText("200 — ميتين — mitein")
        NumBodyText("300 — تلاتمية — tlatmiyye")
        NumBodyText("400 — أربعمية — arba'miyye")
        NumBodyText("500 — خمسمية — khamsmiyye")
        NumBodyText("600 — ستمية — sittmiyye")
        NumBodyText("700 — سبعمية — sab'miyye")
        NumBodyText("800 — تمانمية — tmanmiyye")
        NumBodyText("900 — تسعمية — tis'miyye")
        NumBodyText("1000 — ألف — alf")
        Spacer(modifier = Modifier.height(8.dp))
        NumBodyText("Examples with hundreds:")
        NumBodyText("150 — مية وخمسين — miyye w-khamsin")
        NumBodyText("375 — تلاتمية وخمسة وسبعين — tlatmiyye w-khamse w-sab'in")
        NumBodyText("2000 — ألفين — alfein (dual of alf)")
        NumBodyText("5000 — خمسة آلاف — khamse alaf")

        NumSectionHeader("Ordinal Numbers (1st, 2nd, 3rd...)")
        NumBodyText("Ordinal numbers come AFTER the noun they describe and agree with it in gender.")
        Spacer(modifier = Modifier.height(8.dp))
        NumBodyText("Masculine / Feminine:")
        NumBodyText("1st — أوّل / أولى — awwal / ula")
        NumBodyText("2nd — تاني / تانية — tani / tanye")
        NumBodyText("3rd — تالت / تالتة — talit / talte")
        NumBodyText("4th — رابع / رابعة — rabe' / rabe'a")
        NumBodyText("5th — خامس / خامسة — khames / khamse")
        NumBodyText("6th — سادس / سادسة — sades / sadse")
        NumBodyText("7th — سابع / سابعة — sabe' / sabe'a")
        NumBodyText("8th — تامن / تامنة — tamen / tamne")
        NumBodyText("9th — تاسع / تاسعة — tase' / tase'a")
        NumBodyText("10th — عاشر / عاشرة — 'asher / 'ashre")
        Spacer(modifier = Modifier.height(8.dp))
        NumBodyText("Examples:")
        NumBodyText("• il-bab it-tani — الباب التاني — the second door (m)")
        NumBodyText("• il-ghurfe it-talte — الغرفة التالتة — the third room (f)")
        NumBodyText("• il-marra il-ula — المرة الأولى — the first time (f)")
        Spacer(modifier = Modifier.height(8.dp))
        NumBodyText("\"First\" (awwal/ula) is irregular — it comes BEFORE the noun without the definite article:")
        NumBodyText("• awwal marra — أوّل مرة — the first time")
        NumBodyText("• awwal walad — أوّل ولد — the first boy")

        NumSectionHeader("Negative Numbers")
        NumBodyText("To express negative numbers, use ناقص (na'is, \"minus\") before the number.")
        Spacer(modifier = Modifier.height(4.dp))
        NumBodyText("ناقص — na'is — minus")
        Spacer(modifier = Modifier.height(8.dp))
        NumBodyText("Examples:")
        NumBodyText("• -5 — ناقص خمسة — na'is khamse")
        NumBodyText("• -10 — ناقص عشرة — na'is 'ashara")
        NumBodyText("• -3 degrees — تلاتة تحت الصفر — talate taḥt is-sifr (three below zero)")
        Spacer(modifier = Modifier.height(4.dp))
        NumBodyText("تحت الصفر (taḥt is-sifr, \"below zero\") is also commonly used for temperatures.")

        NumSectionHeader("Do Numbers Change for Masculine / Feminine?")
        NumBodyText("Yes — but the rules differ by range.")
        Spacer(modifier = Modifier.height(8.dp))
        NumBodyText("1 and 2 — agree with the noun")
        NumBodyText("• waḥad walad — one boy (masculine)")
        NumBodyText("• waḥde bint — one girl (feminine)")
        NumBodyText("• ithnen wlad — two boys")
        NumBodyText("• tinten banat — two girls (tinten is the feminine form of ithnen)")
        Spacer(modifier = Modifier.height(8.dp))
        NumBodyText("3–10 — gender polarity (the number takes the OPPOSITE gender from the noun)")
        NumBodyText("This is a classical rule that survives in colloquial speech:")
        NumBodyText("• talate wlad — ثلاثة ولاد — three boys (masculine noun → number with ة, feminine form)")
        NumBodyText("• tlat banat — ثلاث بنات — three girls (feminine noun → number without ة, masculine form)")
        Spacer(modifier = Modifier.height(4.dp))
        NumBodyText("In everyday Palestinian speech, the ة form (talate, arba'a, khamse...) is often used for both genders when counting casually. The polarity rule is most strictly observed in careful or formal speech.")
        Spacer(modifier = Modifier.height(8.dp))
        NumBodyText("11 and above — no gender change; the number is used as-is.")

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun NumSectionHeader(text: String) {
    Spacer(modifier = Modifier.height(20.dp))
    Text(
        text = text,
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.fillMaxWidth()
    )
    Spacer(modifier = Modifier.height(8.dp))
    HorizontalDivider()
    Spacer(modifier = Modifier.height(8.dp))
}

@Composable
private fun NumBodyText(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(vertical = 2.dp)
    )
}
