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
fun SubjectPronounsScreen() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        SPSectionHeader("Subject Pronouns — الضمائر")
        SPBodyText("Subject pronouns are the words for I, you, he, she, we, they. In Palestinian Arabic they are:")
        Spacer(modifier = Modifier.height(12.dp))

        SPBodyText("SINGULAR")
        Spacer(modifier = Modifier.height(4.dp))
        SPBodyText("I — أنا — ana")
        SPBodyText("You (masculine) — أنت — inta")
        SPBodyText("You (feminine) — أنتِ — inti")
        SPBodyText("He — هو — huwwe")
        SPBodyText("She — هي — hiyye")
        Spacer(modifier = Modifier.height(12.dp))
        SPBodyText("PLURAL")
        Spacer(modifier = Modifier.height(4.dp))
        SPBodyText("We — إحنا — iḥna")
        SPBodyText("You (plural, m/f) — إنتو — intu")
        SPBodyText("They (masculine / mixed) — هم — humme")
        SPBodyText("They (feminine) — هنّ — hinne")

        SPSectionHeader("Singular Pronouns in Detail")
        SPBodyText("أنا (ana) — I")
        SPBodyText("Used by anyone regardless of gender. There is no separate masculine/feminine form for \"I\".")
        Spacer(modifier = Modifier.height(4.dp))
        SPBodyText("• ana taʕban — أنا تعبان — I am tired (said by a male)")
        SPBodyText("• ana taʕbane — أنا تعبانة — I am tired (said by a female)")
        SPBodyText("The pronoun itself stays the same; the adjective changes for gender.")
        Spacer(modifier = Modifier.height(12.dp))

        SPBodyText("أنت (inta) — You (masculine singular)")
        SPBodyText("أنتِ (inti) — You (feminine singular)")
        SPBodyText("Palestinian Arabic distinguishes \"you\" by gender in the singular.")
        Spacer(modifier = Modifier.height(4.dp))
        SPBodyText("• inta min wen? — أنت من وين؟ — Where are you from? (to a male)")
        SPBodyText("• inti min wen? — أنتِ من وين؟ — Where are you from? (to a female)")
        Spacer(modifier = Modifier.height(4.dp))
        SPBodyText("In rapid speech, inta and inti can sound nearly the same — context and the verb form clarify the gender.")
        Spacer(modifier = Modifier.height(12.dp))

        SPBodyText("هو (huwwe) — He")
        SPBodyText("هي (hiyye) — She")
        SPBodyText("Arabic has no neutral \"it\" — every noun has a grammatical gender (masculine or feminine). Use هو for masculine nouns and هي for feminine nouns, even when referring to objects.")
        Spacer(modifier = Modifier.height(4.dp))
        SPBodyText("• il-beit kbir — huwwe kbir — البيت كبير — هو كبير — The house is big — it (the house, m) is big")
        SPBodyText("• is-sayyara jadide — hiyye jadide — السيارة جديدة — هي جديدة — The car is new — it (the car, f) is new")

        SPSectionHeader("Plural Pronouns in Detail")
        SPBodyText("إحنا (iḥna) — We")
        SPBodyText("No gender distinction. إحنا is used by all groups.")
        Spacer(modifier = Modifier.height(4.dp))
        SPBodyText("• iḥna min il-'ard — إحنا من الأرض — We are from the land")
        SPBodyText("• iḥna mish ta'banin — إحنا مش تعبانين — We are not tired")
        Spacer(modifier = Modifier.height(12.dp))

        SPBodyText("إنتو (intu) — You (plural)")
        SPBodyText("Used for both mixed groups and all-female groups in everyday Palestinian speech. A strictly feminine plural إنتن (inten) exists but is rare in colloquial use.")
        Spacer(modifier = Modifier.height(4.dp))
        SPBodyText("• intu min wen? — إنتو من وين؟ — Where are you all from?")
        SPBodyText("• intu kwayyisin — إنتو كويسين — You (all) are good / doing well")
        Spacer(modifier = Modifier.height(12.dp))

        SPBodyText("هم (humme) — They (masculine or mixed group)")
        SPBodyText("هنّ (hinne) — They (feminine group)")
        SPBodyText("In practice, هم (humme) is used for any group in everyday Palestinian speech. هنّ (hinne) is used when the group is clearly all-female, but humme is increasingly heard even then.")
        Spacer(modifier = Modifier.height(4.dp))
        SPBodyText("• humme raḥu — هم راحو — They went")
        SPBodyText("• hinne fi il-beit — هنّ في البيت — They (f) are in the house")

        SPSectionHeader("Pronouns are Often Dropped")
        SPBodyText("Palestinian Arabic is a pro-drop language — the subject pronoun is usually omitted because the verb ending already encodes the person and gender.")
        Spacer(modifier = Modifier.height(8.dp))
        SPBodyText("• btishrab 'ahwe? — بتشرب قهوة؟ — Do you (m) drink coffee?")
        SPBodyText("  (no inta needed — the بتـ prefix on the verb signals \"you masculine\")")
        Spacer(modifier = Modifier.height(4.dp))
        SPBodyText("• raḥat il-su' — راحت السوق — She went to the market")
        SPBodyText("  (no hiyye needed — the ـت ending signals feminine past)")
        Spacer(modifier = Modifier.height(8.dp))
        SPBodyText("Pronouns ARE used for emphasis or contrast:")
        SPBodyText("• ana mish raḥ, bass inta ruḥ — أنا مش رح، بس أنت روح — I'm not going, but YOU go")
        SPBodyText("• huwwe 'amel hada, mish iḥna — هو عمل هاد، مش إحنا — HE did this, not us")

        SPSectionHeader("Masculine / Feminine Summary")
        SPBodyText("The key gender distinctions in Palestinian pronouns:")
        Spacer(modifier = Modifier.height(8.dp))
        SPBodyText("Singular 2nd person — inta (m) vs inti (f)")
        SPBodyText("Singular 3rd person — huwwe (m) vs hiyye (f)")
        SPBodyText("Plural 3rd person — humme (m/mixed) vs hinne (f, less common in colloquial)")
        Spacer(modifier = Modifier.height(8.dp))
        SPBodyText("No gender distinction in: ana (I), iḥna (we), intu (you pl.)")

        SPSectionHeader("Quick Reference")
        SPBodyText("أنا (ana) — I")
        SPBodyText("أنت (inta) — You (m.s.)")
        SPBodyText("أنتِ (inti) — You (f.s.)")
        SPBodyText("هو (huwwe) — He / It (masculine noun)")
        SPBodyText("هي (hiyye) — She / It (feminine noun)")
        SPBodyText("إحنا (iḥna) — We")
        SPBodyText("إنتو (intu) — You (plural)")
        SPBodyText("هم (humme) — They (m / mixed)")
        SPBodyText("هنّ (hinne) — They (f)")

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun SPSectionHeader(text: String) {
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
private fun SPBodyText(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(vertical = 2.dp)
    )
}
