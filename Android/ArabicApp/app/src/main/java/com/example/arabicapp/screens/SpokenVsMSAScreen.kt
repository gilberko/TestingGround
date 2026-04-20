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
fun SpokenVsMSAScreen() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        SectionHeader2("MSA — Modern Standard Arabic (الفصحى)")
        BodyText2("Modern Standard Arabic (MSA), called الفصحى (al-fusha, \"the eloquent\"), is the formal, standardized variety of Arabic used across the Arab world.")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText2("You encounter MSA in:")
        BodyText2("• Newspapers and books")
        BodyText2("• Official government documents")
        BodyText2("• Formal speeches and broadcasts")
        BodyText2("• News television (Al Jazeera, BBC Arabic)")
        BodyText2("• The Quran and classical literature")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText2("Crucially, MSA is not anyone's native spoken language. No child grows up speaking MSA at home. It is a formal register that Arabs learn and develop through school and literacy.")
        Spacer(modifier = Modifier.height(4.dp))
        BodyText2("MSA is based on Classical Arabic (the language of the Quran) but modernized with vocabulary for contemporary concepts like technology, science, and international affairs.")

        SectionHeader2("Colloquial Arabic — العامية (al-'ammiyya)")
        BodyText2("العامية (al-'ammiyya, \"the common/everyday\") refers to the spoken dialects that Arab people actually use in daily life — at home, with friends, in markets, in casual conversation.")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText2("Every Arab country and region has its own dialect. These dialects evolved from Classical Arabic over many centuries, mixing with local languages, Turkish, Persian, French, and other influences.")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText2("Colloquial Arabic differs from MSA in:")
        BodyText2("• Pronunciation — many sounds are simplified or shifted")
        BodyText2("• Grammar — case endings and some grammatical rules are dropped")
        BodyText2("• Vocabulary — everyday words are often entirely different")
        BodyText2("• Speed and rhythm — natural speech flows differently")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText2("Colloquial dialects are primarily spoken, not written. When Arabs write casually (texts, social media), they may write in their dialect — but this is informal. Formal writing defaults to MSA.")

        SectionHeader2("Diglossia — Two Languages, One Community")
        BodyText2("Linguists call this situation diglossia — a community that uses two varieties of the same language for different purposes.")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText2("For Arabic speakers, this means:")
        BodyText2("• HIGH register (MSA): used for education, religion, formal settings, and pan-Arab communication")
        BodyText2("• LOW register (dialect): used for everything else — home, friendship, humor, emotion")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText2("Arabs switch registers naturally without thinking about it. A Palestinian teacher might explain a grammar rule in MSA, then switch to colloquial to crack a joke with the class. A news anchor reads in MSA but uses colloquial in off-camera conversation.")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText2("This creates an interesting situation for learners:")
        BodyText2("• If you learn only MSA, you can read newspapers and be formally understood — but locals will not speak to you the way they speak to each other.")
        BodyText2("• If you learn the dialect, you can have real conversations — but you won't be able to read a newspaper easily.")
        Spacer(modifier = Modifier.height(4.dp))
        BodyText2("This app focuses on Palestinian colloquial Arabic — the language of real, everyday life.")

        SectionHeader2("The Arabic Dialect Map")
        BodyText2("Arabic dialects fall into several broad regional groups. Speakers from different groups can communicate but may need to simplify or switch to MSA for clarity.")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText2("Levantine (الشامي ash-shami)")
        BodyText2("Spoken in: Palestine, Jordan, Syria, Lebanon. The dialect covered in this app. Characterized by: qaf → glottal stop (urban), jim → 'j', definite article il-.")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText2("Egyptian (المصري al-masri)")
        BodyText2("Spoken in: Egypt. The most widely understood dialect due to Egypt's dominance in cinema and television. Characterized by: jim → hard 'g', different vocabulary.")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText2("Gulf (الخليجي al-khaliji)")
        BodyText2("Spoken in: Saudi Arabia, UAE, Kuwait, Qatar, Bahrain, Oman. Closer to classical roots in some respects. Qaf is often retained as 'q'.")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText2("Maghrebi (المغربي al-maghribi)")
        BodyText2("Spoken in: Morocco, Algeria, Tunisia, Libya. Heavily influenced by French and Berber languages. Often the hardest for eastern Arabs to understand.")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText2("Iraqi (العراقي al-'iraqi)")
        BodyText2("Spoken in: Iraq. Distinct features influenced by Persian and Turkish. Retains some sounds lost in other dialects.")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText2("Understanding these groups helps you appreciate why a Palestinian and a Moroccan might struggle to follow each other's casual speech despite sharing a written language.")

        SectionHeader2("Why Palestinian Arabic?")
        BodyText2("This app focuses on Palestinian Arabic as spoken by Arab citizens of Israel and Palestinians in the West Bank.")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText2("Who speaks it:")
        BodyText2("• Arab citizens of Israel (approximately 20% of Israel's population), living in cities like Haifa, Nazareth, Acre, and the Triangle region")
        BodyText2("• Palestinians in Jerusalem, Ramallah, Nablus, Hebron, Bethlehem, and surrounding areas")
        BodyText2("• Palestinian diaspora communities worldwide")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText2("This dialect is a variety of Levantine Arabic. It shares much with Jordanian and Syrian Arabic but has its own vocabulary, expressions, and rhythms shaped by local history and by contact with Hebrew.")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText2("Some features you will notice:")
        BodyText2("• Hebrew loanwords have entered the dialect, especially for modern concepts and everyday objects")
        BodyText2("• Urban and rural speech differ — city dwellers use the glottal stop for qaf; some village and Bedouin speakers retain 'q'")
        BodyText2("• There is variation between generations and regions, but the core grammar and vocabulary are consistent")
        Spacer(modifier = Modifier.height(4.dp))
        BodyText2("Learning this dialect lets you communicate authentically with Arabic-speaking communities in Israel and the Palestinian territories.")

        SectionHeader2("MSA vs Palestinian: Key Differences")
        BodyText2("GRAMMAR")
        Spacer(modifier = Modifier.height(4.dp))
        BodyText2("Case endings (إعراب i'rab): MSA marks grammatical roles (subject, object, possessive) with short vowel endings on nouns. Palestinian colloquial drops all of these.")
        BodyText2("MSA: al-waladu (the boy, subject) / al-walada (the boy, object)\nPalestinian: il-walad (same form regardless)")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText2("THE DEFINITE ARTICLE")
        Spacer(modifier = Modifier.height(4.dp))
        BodyText2("MSA: al-  →  al-bayt (the house)\nPalestinian: il-  →  il-beit (the house)\nSun letter assimilation still applies in both: ash-shams / ish-shams")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText2("VOCABULARY")
        Spacer(modifier = Modifier.height(4.dp))
        BodyText2("Many common words are completely different:")
        Spacer(modifier = Modifier.height(4.dp))
        BodyText2("To speak/talk:\nMSA: يتحدث (yataḥaddath)\nPalestinian: يحكي (yiḥki)")
        Spacer(modifier = Modifier.height(4.dp))
        BodyText2("To want:\nMSA: يريد (yurīd)\nPalestinian: بدّه (biddo) — from the word for \"will/desire\"")
        Spacer(modifier = Modifier.height(4.dp))
        BodyText2("Now:\nMSA: الآن (al-ān)\nPalestinian: هلّق (halla') or هلأ (halla)")
        Spacer(modifier = Modifier.height(4.dp))
        BodyText2("What:\nMSA: ماذا (mādhā)\nPalestinian: شو (shu)")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText2("PRONUNCIATION SUMMARY")
        Spacer(modifier = Modifier.height(4.dp))
        BodyText2("ق → ʔ (glottal stop) in urban Palestinian")
        BodyText2("ث → t  (e.g. talate for three)")
        BodyText2("ذ → d  (e.g. hada for this)")
        BodyText2("ظ → d or z")
        BodyText2("ج → j  (same as MSA, unlike Egyptian g)")
        BodyText2("al- → il-  (definite article)")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText2("These differences mean Palestinian colloquial and MSA sound quite distinct, even when discussing the same topic. Both are Arabic — but knowing one does not mean you automatically understand the other in spoken form.")

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun SectionHeader2(text: String) {
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
private fun BodyText2(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(vertical = 2.dp)
    )
}
