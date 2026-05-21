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
fun ObjectPronounsScreen() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        OBJSectionHeader("Object Pronouns — ضمائر المفعول")
        OBJBodyText("In Palestinian Arabic, object pronouns (him, her, me, you, them...) are suffixes attached directly to the verb. There is no separate \"object marker\" word — the suffix IS the object.")
        Spacer(modifier = Modifier.height(8.dp))
        OBJBodyText("Compare with Hebrew: Hebrew uses \"את\" (et) before a direct object. Arabic skips this entirely and just glues the suffix to the verb.")

        OBJSectionHeader("Direct Object Suffixes")
        OBJBodyText("Using شاف (shaaf = he saw) as the model verb:")
        Spacer(modifier = Modifier.height(8.dp))
        OBJBodyText("شافني (shaafni) — he saw me")
        OBJBodyText("شافك (shaafak) — he saw you (masculine)")
        OBJBodyText("شافك (shaafik) — he saw you (feminine)")
        OBJBodyText("شافه (shaafo) — he saw him")
        OBJBodyText("شافها (shaafha) — he saw her")
        OBJBodyText("شافنا (shaafna) — he saw us")
        OBJBodyText("شافكم (shaafkum) — he saw you (plural)")
        OBJBodyText("شافهم (shaafhum) — he saw them")
        Spacer(modifier = Modifier.height(8.dp))
        OBJBodyText("More examples with different verbs:")
        OBJBodyText("• سمعتك — simi'tak — I heard you (m)")
        OBJBodyText("• بحبك — bḥibbak — I love you (m)")
        OBJBodyText("• بحبك — bḥibbik — I love you (f)  [same script, different vowel]")
        OBJBodyText("• شفته — shuftah — I saw him")
        OBJBodyText("• شفتها — shuftaha — I saw her")
        OBJBodyText("• أكلته — akaltah — I ate it (masculine noun)")
        OBJBodyText("• أكلتها — akaltaha — I ate it (feminine noun)")

        OBJSectionHeader("The Hebrew Analogy — Direct vs Indirect")
        OBJBodyText("Hebrew makes a clear structural distinction:")
        OBJBodyText("• ראה אותו (ra'a oto) — he saw HIM — uses the direct object marker את + him")
        OBJBodyText("• נתן לו (natan lo) — he gave TO HIM — uses the preposition ל + him")
        Spacer(modifier = Modifier.height(8.dp))
        OBJBodyText("Arabic handles both cases, but differently. Let's look at each.")

        OBJSectionHeader("Direct Object — Suffix on Verb")
        OBJBodyText("\"He saw him\" = شافه (shaafo)")
        OBJBodyText("The suffix -o/-ah attaches directly to the verb. No separate marker word is needed.")
        Spacer(modifier = Modifier.height(8.dp))
        OBJBodyText("Examples:")
        OBJBodyText("• شفته (shuftah) — I saw him")
        OBJBodyText("• شفتها (shuftaha) — I saw her")
        OBJBodyText("• لقيته (la'eitah) — I found him")
        OBJBodyText("• خذته (akhadhtu) — I took it (m)")
        OBJBodyText("• فهمتها (fahimtaha) — I understood it (f)")
        OBJBodyText("• بعرفك — ba'refak — I know you (m)")
        OBJBodyText("• بعرفك — ba'refik — I know you (f)")

        OBJSectionHeader("Indirect Object — ل + Suffix (Cliticized)")
        OBJBodyText("\"He gave to him\" — indirect objects use the preposition ل (for/to). In everyday Palestinian Arabic, ل often cliticizes (glues) onto the verb, creating a combined form.")
        Spacer(modifier = Modifier.height(8.dp))
        OBJBodyText("قلت (ult = I said) + له (ilu = to him) → قلتله ('ultilu = I told him)")
        Spacer(modifier = Modifier.height(8.dp))
        OBJBodyText("Examples with cliticized ل:")
        OBJBodyText("• قلتله ('ultilu) — I told him (قلت + له)")
        OBJBodyText("• قلتلها ('ultilha) — I told her")
        OBJBodyText("• قلتلك ('ultilak) — I told you (m)")
        OBJBodyText("• قلتلي ('ultili) — I told myself / he told me")
        OBJBodyText("• عطيتله ('aṭeitle) — I gave (to) him")
        OBJBodyText("• عطيتلها ('aṭeitlha) — I gave (to) her")
        OBJBodyText("• عطيتلك ('aṭeitlak) — I gave (to) you (m)")
        OBJBodyText("• بعتله (bi'tilu) — I sold (to) him")
        Spacer(modifier = Modifier.height(8.dp))
        OBJBodyText("The standalone (non-cliticized) form also works:")
        OBJBodyText("• عطيت له كتاب — 'aṭeit lu ktaab — I gave him a book (له = to him, separate)")
        OBJBodyText("• قلت له — 'ult ilu — I said to him / I told him")

        OBJSectionHeader("When Both Objects Appear — Direct + Indirect")
        OBJBodyText("When a verb has both a direct and indirect object (\"I gave it to him\"), Palestinian Arabic typically names the indirect object with ل and places the direct object as a full noun, or uses إياه for the standalone emphatic direct form:")
        Spacer(modifier = Modifier.height(8.dp))
        OBJBodyText("• عطاه الكتاب — 'aṭaah il-ktaab — he gave him the book")
        OBJBodyText("  (ه suffix = indirect \"to him\"; الكتاب = the direct object as full noun)")
        OBJBodyText("• عطاني المصاري — 'aṭaani il-maṣaari — he gave me the money")
        OBJBodyText("• قلتله الحقيقة — 'ultilu il-ḥa'ii'a — I told him the truth")
        Spacer(modifier = Modifier.height(8.dp))
        OBJBodyText("For two pronoun objects, إياه (iyyaah) carries the direct object:")
        OBJBodyText("• عطاه ياه — 'aṭaah iyyaah — he gave it to him (both are pronouns)")

        OBJSectionHeader("Emphatic Standalone Object Forms — إياـ")
        OBJBodyText("For emphasis (\"YOU specifically\", \"HIM, not me\"), use إياـ + suffix:")
        Spacer(modifier = Modifier.height(8.dp))
        OBJBodyText("إياي (iyyaay) — me (emphatic)")
        OBJBodyText("إياك (iyyaak) — you (m, emphatic)")
        OBJBodyText("إياك (iyyaaki) — you (f, emphatic)")
        OBJBodyText("إياه (iyyaah) — him (emphatic)")
        OBJBodyText("إياها (iyyaaha) — her (emphatic)")
        OBJBodyText("إيانا (iyyaana) — us (emphatic)")
        OBJBodyText("إياهم (iyyaahum) — them (emphatic)")
        Spacer(modifier = Modifier.height(8.dp))
        OBJBodyText("Examples:")
        OBJBodyText("• بدّه إياك — baddo iyyaak — he wants YOU (not someone else)")
        OBJBodyText("• شاف إياها — shaaf iyyaaha — he saw HER specifically")
        OBJBodyText("• بحبك إياك — bḥibbak iyyaak — I love you — YOU")

        OBJSectionHeader("Quick Reference")
        OBJBodyText("Direct object: verb + suffix (-ni/-ak/-ik/-o/-ha/-na/-kum/-hum)")
        OBJBodyText("Indirect object: verb + ل + suffix (cliticized, e.g. قلتله)")
        OBJBodyText("Emphatic: إياـ + suffix (إياه, إياك...)")
        OBJBodyText("No separate object marker word like Hebrew את — the suffix is enough.")

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun OBJSectionHeader(text: String) {
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
private fun OBJBodyText(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(vertical = 2.dp)
    )
}
