package com.example.arabicapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp


@Composable
fun ArtsMediaScreen() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        AMNote("Art is فن — fann, and media is إعلام — i3laam (\"the media\": el-i3laam). People words are given masculine first, then the feminine (usually just add -e / -a). Palestinian first; Lebanese/Syrian variants where they differ.")

        AMSectionHeader("Film and TV")
        AMWord("film / movie — فيلم — film")
        AMInfo("m · pl: أفلام aflaam  — English \"film\" and \"movie\" are the same word in Arabic")
        AMWord("cinema / movie theater — سينما — sinama")
        AMWord("TV — تلفزيون — talfizyoon  (also tilifizyoon)")
        AMWord("TV show / program — برنامج — barnaamaj")
        AMInfo("m · pl: برامج baraamij")
        AMWord("TV series — مسلسل — musalsal")
        AMInfo("m · pl: مسلسلات musalsalaat  — Ramadan series (مسلسلات رمضان) are a big deal across the Arab world")
        AMWord("actor — ممثل — mumassil  (f: actress — ممثلة — mumassile)")
        AMSmall("MSA mumaththil; in speech the th becomes s. pl: ممثلين mumassiliin")
        AMWord("director — مخرج — mukhrij  (f: مخرجة — mukhrije)")
        AMWord("producer — منتج — muntij  (f: منتجة — muntije)")
        AMWord("scene — مشهد — mashhad  (pl: مشاهد mashaahid)")
        AMExample("shu 3am tetfarraj? musalsal turki — شو عم تتفرج؟ مسلسل تركي — What are you watching? A Turkish series.")
        AMExample("ra7 nruu7 3as-sinama el-leile — رح نروح عالسينما الليلة — We're going to the cinema tonight.")

        AMSectionHeader("Genres")
        AMWord("comedy — كوميديا — koomeedya;  a comedy (film): فيلم كوميدي — film koomeedi")
        AMWord("drama — دراما — draama;  a drama film: فيلم دراما — film draama")
        AMWord("horror — رعب — ru3b;  a horror film: فيلم رعب — film ru3b")
        AMWord("tragedy — مأساة — ma2saa  (a tragic event);  as a genre: تراجيديا — traajiidya")
        AMWord("adventure — مغامرة — mughaamara  (pl: مغامرات mughaamaraat);  an adventure film: فيلم مغامرات — film mughaamaraat")
        AMWord("thriller — فيلم تشويق — film tashwii2  (or فيلم إثارة — film isaara, lit. \"excitement film\")")
        AMWord("action film — فيلم أكشن — film akshen")
        AMExample("ana ma b7ibb aflaam ar-ru3b — أنا ما بحب أفلام الرعب — I don't like horror movies.")

        AMSectionHeader("Theater")
        AMWord("theater — مسرح — masra7")
        AMWord("play (on stage) — مسرحية — masra7iyye")
        AMInfo("f · pl: مسرحيات masra7iyyaat")
        AMWord("stage — خشبة المسرح — khashabet el-masra7  (lit. \"the boards of the theater\");  casually just masra7")
        AMWord("on stage — عالمسرح — 3al-masra7")
        AMExample("tla3 3al-masra7 w-ghanna — طلع عالمسرح وغنّى — He went up on stage and sang.")

        AMSectionHeader("Music")
        AMWord("music — موسيقى — musii2a")
        AMWord("song — أغنية — ughniye")
        AMInfo("f · pl: أغاني aghaani")
        AMWord("lyrics — كلمات الأغنية — kalimaat el-ughniye  (lit. \"the words of the song\")")
        AMWord("singer — مغني — mghanni  (f: مغنية — mghanniye)")
        AMSmall("A classic Arab singer is often called مطرب mutrib (f: مطربة mutribe).")
        AMWord("musician — موسيقي — musii2i  (f: موسيقية — musii2iyye)")
        AMWord("player (of an instrument) — عازف — 3aazif  (f: عازفة — 3aazfe)")
        AMSmall("guitar player: عازف جيتار 3aazif gitaar (f: 3aazfet gitaar); drummer: عازف درامز 3aazif draamz. \"To play\" an instrument is 3azaf 3ala, not li3ib (see Hobbies).")
        AMWord("band — فرقة — fir2a")
        AMInfo("f · pl: فرق fira2  — a rock band: فرقة روك fir2it rok")
        AMWord("concert / party — حفلة — 7afle")
        AMExample("mghanniyti el-mfaDDale fayruuz — مغنيتي المفضّلة فيروز — My favorite singer (f) is Fairuz.")

        AMSectionHeader("Instruments")
        AMWord("instrument — آلة موسيقية — aale muusii2iyye")
        AMWord("guitar — جيتار — gitaar  (Lebanese/Syrian also غيتار — giitaar)")
        AMWord("bass guitar — باص جيتار — baas gitaar;  usually just el-baas")
        AMWord("piano — بيانو — biyaano")
        AMWord("drums (kit) — درامز — draamz;  a drum: طبل — tabl;  the hand drum: دربكة — derbakke")
        AMWord("violin — كمنجة — kamanje")
        AMWord("cello — تشيلو — tshello")
        AMWord("flute — فلوت — floot;  the Arab reed flute: ناي — naay")
        AMWord("harmonica — هارمونيكا — harmoonika")
        AMWord("oud — عود — 3uud  (the classic Arab lute)")
        AMExample("bti3zif 3ala gitaar walla biyaano? — بتعزف على جيتار ولا بيانو؟ — Do you play guitar or piano?")

        AMSectionHeader("Concerts and Festivals")
        AMWord("lights — أضواء — adwaa2;  stage lighting: إضاءة — idaa2a")
        AMWord("speakers — سماعات — sammaa3aat  (sing. سماعة sammaa3a — also means headphones);  in Lebanon you also hear سبيكرز — spiikarz")
        AMWord("festival — مهرجان — mahrajaan")
        AMInfo("m · pl: مهرجانات mahrajaanaat  — e.g. مهرجانات بعلبك — Baalbeck International Festival in Lebanon")
        AMWord("audience — جمهور — jumhuur")
        AMExample("es-sammaa3aat kaanu 3aalyiin ktiir — السماعات كانوا عاليين كتير — The speakers were really loud.")

        AMSectionHeader("Visual Art")
        AMWord("art — فن — fann")
        AMInfo("m · pl: فنون funuun")
        AMWord("artist — فنان — fannaan  (f: فنانة — fannaane)")
        AMSmall("fannaan is also used for singers and actors: a \"star\" of music or TV.")
        AMWord("painting (a picture) — لوحة — lo7a  (pl: لوحات lo7aat)")
        AMWord("painting / drawing (the activity) — رسم — rasm;  to paint: رسم — rasam / yirsum")
        AMWord("painter — رسام — rassaam  (f: رسامة — rassaame)")
        AMWord("sculpture (a piece) — منحوتة — man7uute;  sculpture (the art) — نحت — na7t")
        AMWord("sculptor — نحات — na77aat  (f: نحاتة — na77aate)")
        AMWord("museum — متحف — mat7af;  gallery: معرض — ma3raD")
        AMExample("hay el-lo7a la-fannaane falasTiiniyye — هاي اللوحة لفنانة فلسطينية — This painting is by a Palestinian artist (f).")

        AMSectionHeader("Photo and Video")
        AMWord("photo / picture — صورة — suura")
        AMInfo("f · pl: صور suwar")
        AMWord("camera — كاميرا — kaamira")
        AMWord("video — فيديو — video")
        AMWord("video camera — كاميرا فيديو — kaamira video")
        AMWord("to take a photo / to film — صوّر — sawwar / ysawwir")
        AMWord("photographer / cameraman — مصوّر — msawwir  (f: مصوّرة — msawwira)")
        AMExample("mumkin tsawwirna? — ممكن تصوّرنا؟ — Can you take a picture of us?")

        AMSectionHeader("Books and Writing")
        AMWord("book — كتاب — ktaab")
        AMInfo("m · pl: كتب kutub")
        AMWord("novel — رواية — riwaaye")
        AMInfo("f · pl: روايات riwaayaat")
        AMWord("writer — كاتب — kaatib  (f: كاتبة — kaatbe)")
        AMWord("novelist — روائي — riwaa2i  (f: روائية — riwaa2iyye)")
        AMWord("poet — شاعر — shaa3ir  (f: شاعرة — shaa3ra)")
        AMExample("2areit riwaaye la-ghassaan kanafaani — قريت رواية لغسان كنفاني — I read a novel by Ghassan Kanafani.")

        Spacer(modifier = Modifier.height(24.dp))
    }
}


@Composable
private fun AMSectionHeader(text: String) {
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
private fun AMWord(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        fontWeight = FontWeight.SemiBold,
        modifier = Modifier.padding(top = 6.dp, bottom = 2.dp)
    )
}

@Composable
private fun AMInfo(text: String) {
    Text(
        text = "    $text",
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(vertical = 1.dp)
    )
}

@Composable
private fun AMExample(text: String) {
    Text(
        text = "    e.g. $text",
        style = MaterialTheme.typography.bodyMedium,
        fontStyle = FontStyle.Italic,
        modifier = Modifier.padding(vertical = 1.dp)
    )
}

@Composable
private fun AMSmall(text: String) {
    Text(
        text = "    $text",
        style = MaterialTheme.typography.bodySmall,
        modifier = Modifier.padding(vertical = 2.dp)
    )
}

@Composable
private fun AMNote(text: String) {
    Spacer(modifier = Modifier.height(8.dp))
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(12.dp)
        )
    }
    Spacer(modifier = Modifier.height(4.dp))
}
