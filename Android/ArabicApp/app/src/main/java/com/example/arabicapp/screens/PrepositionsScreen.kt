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

// Markup in this screen (see RichText.kt): **x** = bold, __x__ = bold + underline.
// Arabic prefixes (و / بـ / لـ / عـ) are marked on the whole word so the letters keep joining.

@Composable
fun PrepositionsScreen() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        PrepSectionHeader("Conjunctions")
        PrepBodyText("These words link phrases and clauses together.")
        Spacer(modifier = Modifier.height(8.dp))
        PrepTerm("AND — و (w)")
        PrepBodyText("• Written as a prefix attached to the next word")
        PrepBodyText("• رحت **وشفته** — ru7t **w**-shifto — I went **and** saw him")
        PrepBodyText("• بياكل **وبيشرب** — byakol **w**-byishrab — he eats **and** drinks")
        Spacer(modifier = Modifier.height(8.dp))
        PrepTerm("OR — أو (aw) / ولا (wella)")
        PrepBodyText("• **ولا** (**wella / walla / willa**) — the \"or\" of questions that ask you to choose (Pal, Leb, Syr)")
        PrepBodyText("• **أو** (**aw**) — the \"or\" of statements (\"two or three\"); also the formal / written \"or\"")
        PrepBodyText("• بدك شاي **ولا** قهوة؟ — biddak shai **wella** 2ahwe? — do you want tea **or** coffee?")
        PrepBodyText("• جاي معي **ولا** لأ؟ — jaay ma3i **wella** la2? — are you coming with me **or** not?")
        PrepBodyText("• بضل يومين **أو** تلاتة — baDall yoomein **aw** tlaate — I'll stay two **or** three days")
        Spacer(modifier = Modifier.height(8.dp))
        PrepTerm("NOT — مش (mish) / ما...ش (ma...sh)")
        PrepBodyText("• **مش** (**mish**) negates nouns, adjectives, non-verb phrases")
        PrepBodyText("• **ما...ش** (**ma...sh**) wraps around a verb for verb negation")
        PrepBodyText("• **مش** كبير — **mish** kbir — **not** big")
        PrepBodyText("• **ما** **بعرفش** — **ma** ba3ref**sh** — I **don't** know")
        PrepBodyText("• The **-sh** ending is Palestinian (and Jordanian). Lebanese and Syrian use just **ما** (**ma**): ما بعرف — ma ba3ref. For non-verbs Lebanese says **mish / mesh**, Syrian says **مو** (**mu**): mu kbiir — not big.")
        Spacer(modifier = Modifier.height(8.dp))
        PrepTerm("WITHOUT — بلا (bala) / بدون (bidun)")
        PrepBodyText("• **بلا** (**bala**) — colloquial (Pal, Leb, Syr)")
        PrepBodyText("• **بدون** (**bidun**) — neutral / slightly more formal (also MSA), used everywhere")
        PrepBodyText("• **بلا** سكر — **bala** sukkar — **without** sugar")
        PrepBodyText("• **بدون** أكل — **bidun** akl — **without** food")
        PrepBodyText("• رحت **بلاه** — ru7t **bala**h — I went **without** him")
        Spacer(modifier = Modifier.height(8.dp))
        PrepTerm("THEREFORE / SO — لهيك (la-hek) / يعني (ya3ni)")
        PrepBodyText("• **لهيك** (**la-hek**) — therefore, so (because of that)")
        PrepBodyText("• **يعني** (**ya3ni**) — literally 'it means', used as 'so/therefore/basically'")
        PrepBodyText("• ما كانش عنده وقت, **لهيك** ما جاش — ma kan 3ando wa2it, **la-hek** ma jash — he didn't have time, **so** he didn't come")
        PrepBodyText("• **يعني** بدنا نروح بكير — **ya3ni** bidna nru7 bikir — **so** we need to go early")
        Spacer(modifier = Modifier.height(8.dp))
        PrepTerm("EVEN THOUGH / ALTHOUGH — مع إنو (ma3 inno) / رغم إنو (raghm inno)")
        PrepBodyText("• **مع إنو** (**ma3 inno**) — even though, despite the fact that")
        PrepBodyText("• **رغم إنو** (**raghm inno**) — despite the fact that (slightly stronger)")
        PrepBodyText("• جاء **مع إنو** كان تعبان — ja **ma3 inno** kan ta3ban — he came **even though** he was tired")
        PrepBodyText("• **رغم إنو** ما بعرف, بحكي — **raghm inno** ma ba3ref, b7ki — **even though** I don't know, I'll speak")
        Spacer(modifier = Modifier.height(8.dp))
        PrepTerm("WHILE — و + pronoun (w-humme…) / بينما (beinama)")
        PrepBodyText("• **و** + pronoun + verb/adjective = '**while** [he/she/they] was doing' (lit. \"and they …\")")
        PrepBodyText("• **بينما** (**beinama**) — formal / MSA \"while\"")
        PrepBodyText("• **وهمّي** نايمين, إجا — **w-humme** naymiin, ija — **while** they were sleeping, he came (Leb **w-hinne**)")
        PrepBodyText("• **وهي** تحكي, كنت بفكر — **w**-hiyye t7ki, kunt bfakker — **while** she was talking, I was thinking")
        Spacer(modifier = Modifier.height(8.dp))
        PrepTerm("BUT — بس (bas) / لاكن (laakin)")
        PrepBodyText("• **بس** (**bas**) — the everyday \"but\" (it also means \"only / just\" and \"enough!\")")
        PrepBodyText("• **لاكن** (**laakin**) — more formal; Lebanese also **leekin**. In speech it has largely been replaced by bas (Wiktionary)")
        PrepBodyText("• بدي أروح, **بس** ما عندي وقت — biddi aru7, **bas** ma 3indi wa2it — I want to go, **but** I don't have time")
        Spacer(modifier = Modifier.height(8.dp))
        PrepTerm("HOWEVER — بس (bas) / لاكن (laakin) / مع هيك (ma3 hek)")
        PrepBodyText("• Spoken Arabic usually just says **bas** or **laakin** at the start of the sentence; formal: **غير أنّ** (**ghayr anna**)")
        PrepBodyText("• الأكل كان طيّب. **بس** الخدمة كانت بطيئة — il-akl kan Tayyib. **bas** il-khidme kaanat baTii2a — the food was good. **However**, the service was slow")
        Spacer(modifier = Modifier.height(8.dp))
        PrepTerm("NEVERTHELESS / EVEN SO — مع هيك (ma3 hek) / رغم هيك (raghm hek)")
        PrepBodyText("• **مع هيك** (**ma3 hek**) — lit. \"with this\" = even so, still · **رغم هيك** (**raghm hek**) — despite this · formal: **مع ذلك** (**ma3a dhaalik**)")
        PrepBodyText("• كان تعبان, **ومع هيك** إجا عالشغل — kan ta3baan, **w-ma3 hek** ija 3ash-shughl — he was tired; **nevertheless**, he came to work")
        PrepBodyText("• Compare **ma3 inno** (above): ma3 inno + a sentence = even though; ma3 hek on its own = nevertheless (\"with that\"). ma3 hek, gheir hek, ba3d hek and 2abl hek are commonly heard; dictionaries only list 3ashaan hek.")
        Spacer(modifier = Modifier.height(8.dp))
        PrepTerm("MOREOVER / IN ADDITION — وكمان (w-kamaan) / وغير هيك (w-gheir hek) / بالإضافة لـ (bil-iDaafe la-)")
        PrepBodyText("• **وكمان** (**w-kamaan**) — and also, and on top of that — the everyday way (kamaan = also, too)")
        PrepBodyText("• **وغير هيك** (**w-gheir hek**) — and besides that (lit. \"and other than this\")")
        PrepBodyText("• **بالإضافة لـ** (**bil-iDaafe la-**) — in addition to; **بالإضافة لهيك** (**bil-iDaafe la-hek**) — in addition (to that); formal: **علاوة على ذلك** (**3ilaawatan 3ala dhaalik**) — moreover")
        PrepBodyText("• الشقّة كبيرة, **وكمان** رخيصة — ish-sha22a kbiire, **w-kamaan** rkhiiSa — the apartment is big, and **moreover** it's cheap")
        PrepBodyText("• **بالإضافة للمعاش**, في بونس — **bil-iDaafe lal**-ma3aash, fi bonus — **in addition to** the salary, there's a bonus")
        Spacer(modifier = Modifier.height(8.dp))
        PrepTerm("AND THEN / AND AFTER THAT / AND LATER — وبعدين (w-ba3dein) / وبعد هيك (w-ba3d hek)")
        PrepBodyText("• **وبعدين** (**w-ba3dein**) — and then; and later (ba3dein = then, later, afterwards)")
        PrepBodyText("• **وبعد هيك** (**w-ba3d hek**) — and after that (lit. \"and after this\")")
        PrepBodyText("• **وبعد شوي** (**w-ba3d shwayy**) — and a little later · formal: **ولاحقاً** (**w-laa7i2an**) — and later")
        PrepBodyText("• فقت, **وبعدين** شربت قهوة, **وبعد هيك** رحت عالشغل — fi2t, **w-ba3dein** shribt 2ahwe, **w-ba3d hek** ru7t 3ash-shughl — I woke up, **and then** I drank coffee, **and after that** I went to work")
        PrepBodyText("• بحكيلك **بعدين** — ba7kiilak **ba3dein** — I'll tell you **later**")
        PrepBodyText("• Careful: **وبعدين؟** (**w-ba3dein?**) as a question also means \"and then what?\" / \"so what now?\" — said in annoyance it's \"enough already!\" (commonly heard).")
        Spacer(modifier = Modifier.height(8.dp))
        PrepTerm("BUT BEFORE THAT — بس قبل هيك (bas 2abl hek)")
        PrepBodyText("• **بس قبل هيك** (**bas 2abl hek**) — but before that (lit. \"but before this\")")
        PrepBodyText("• رح نروح عالبحر, **بس قبل هيك** لازم ناكل — ra7 nruu7 3al-ba7r, **bas 2abl hek** laazim naakul — we'll go to the beach, **but before that** we have to eat")
        PrepBodyText("• Same pattern as **ba3d hek**: **2abl hek** = before that, **ba3d hek** = after that, **3ashaan hek / la-hek** = because of that.")

        PrepSectionHeader("Prepositions of Time")
        PrepTerm("AFTER — بعد (ba3d)")
        PrepBodyText("• **بعد** الأكل — **ba3d** il-akl — **after** eating")
        PrepBodyText("• **بعد** بكرا — **ba3d** bukra — the day **after** tomorrow")
        PrepBodyText("• **بعد ما** يرجع — **ba3d ma** yirja3 — **after** he comes back")
        Spacer(modifier = Modifier.height(8.dp))
        PrepTerm("BEFORE — قبل (2abl)")
        PrepBodyText("• **قبل ما** نروح — **2abl ma** nru7 — **before** we go")
        PrepBodyText("• **قبل** الضهر — **2abl** id-duhr — **before** noon")
        PrepBodyText("• **قبل** سنتين — **2abl** santein — two years **ago** (lit. '**before** two years')")
        Spacer(modifier = Modifier.height(8.dp))
        PrepTerm("UNTIL — لغايت (lghayt) / على ما (3ala ma)")
        PrepBodyText("• **لغايت** بكرا — **lghayt** bukra — **until** tomorrow")
        PrepBodyText("• **على ما** يرجع — **3ala ma** yirja3 — **until** he comes back")
        PrepBodyText("• **لغايت** هلق — **lghayt** halla2 — **until** now")
        Spacer(modifier = Modifier.height(8.dp))
        PrepTerm("WHEN — لمّا (lamma) / وقت (wa2it ma)")
        PrepBodyText("• **لمّا** (**lamma**) — **when** (at the time that)")
        PrepBodyText("• **لمّا** جيت, كان نايم — **lamma** jiit, kan naym — **when** you came, he was sleeping")
        PrepBodyText("• **وقت ما** بدي حاجة, بسألك — **wa2it ma** biddi 7aje, bas2alak — **when** I need something, I'll ask you")
        Spacer(modifier = Modifier.height(8.dp))
        PrepTerm("FROM [TIME] TO [TIME] — من...لـ (min...la)")
        PrepBodyText("• **من** التسعة **لاثنعش** — **min** it-tis3a **la**-itnashr — **from** nine **to** twelve")
        PrepBodyText("• **من** الصبح **لالليل** — **min** is-subu7 **la**l-leil — **from** morning **to** night")
        PrepBodyText("• **من** اثنين **لاربعة** — **min** itnein **la**-arba3a — **from** two **to** four")

        PrepSectionHeader("Prepositions of Movement & Direction")
        PrepTerm("TO (place/direction) — لـ (la-) / على (3ala)")
        PrepBodyText("• **لـ** (**la-**) — **to** [a place], towards, for a person")
        PrepBodyText("• **على** (**3ala**) — onto, towards, used with movement verbs")
        PrepBodyText("• رحت **لبيته** — ru7t **la**-beto — I went **to** his house")
        PrepBodyText("• طلعت **عالجبل** — Tli3t **3a**l-jabal — I went up **to** the mountain")
        Spacer(modifier = Modifier.height(8.dp))
        PrepTerm("FROM — من (min) / من عند (min 3ind)")
        PrepBodyText("• **من** (**min**) — **from** [a place]")
        PrepBodyText("• **من عند** (**min 3ind**) — **from** [a person], **from** someone's place")
        PrepBodyText("• جيت **من** الشغل — jiit **min** ish-shughul — I came **from** work")
        PrepBodyText("• جيت **من عند** أمي — jiit **min 3ind** immi — I came **from** my mother's place")
        Spacer(modifier = Modifier.height(8.dp))
        PrepTerm("TO WHERE — لوين (la-wein)")
        PrepBodyText("• رايح **لوين**؟ — rayyi7 **la-wein**? — **where** are you going (**to**)?")
        PrepBodyText("• **لوين** بدك تروح؟ — **la-wein** biddak tru7? — **where** do you want to go (**to**)?")
        Spacer(modifier = Modifier.height(8.dp))
        PrepTerm("TO WHOM — لمين (la-miin)")
        PrepBodyText("• حكيت **لمين**؟ — 7akiit **la-miin**? — who did you speak **to**?")
        PrepBodyText("• بعتّها **لمين**؟ — ba3atta **la-miin**? — who did you send it **to**?")
        Spacer(modifier = Modifier.height(8.dp))
        PrepTerm("FROM WHOM — من مين (min miin) / من عند مين (min 3ind miin)")
        PrepBodyText("• أخدتو **من** مين؟ — akhatto **min** miin? — who did you get it **from**?")
        PrepBodyText("• جيت **من عند** مين؟ — jiit **min 3ind** miin? — whose place are you coming **from**?")
        Spacer(modifier = Modifier.height(8.dp))
        PrepTerm("WHOM / WHO — مين (miin)")
        PrepBodyText("• مين حكالك؟ — miin 7aka-lak? — who told you?")
        PrepBodyText("• شفت مين؟ — shift miin? — who did you see?")

        PrepSectionHeader("Prepositions of Place")
        PrepTerm("INSIDE — جوا (jawwa) / بالداخل (bil-dakhil)")
        PrepBodyText("• **جوا** البيت — **jawwa** l-beit — **inside** the house")
        PrepBodyText("• هو **جوا** — huwa **jawwa** — he is **inside**")
        Spacer(modifier = Modifier.height(8.dp))
        PrepTerm("OUTSIDE — برا (barra)")
        PrepBodyText("• هي **برا** — hiyye **barra** — she is **outside**")
        PrepBodyText("• **برا** البلد — **barra** l-balad — **outside** the city")
        Spacer(modifier = Modifier.height(8.dp))
        PrepTerm("ON / ABOVE — فوق (foo2) / على (3ala)")
        PrepBodyText("• **على** الطاولة — **3ala** t-tawle — **on** the table")
        PrepBodyText("• **فوق** السطح — **foo2** is-saT7 — **on / up on** the roof (سقف sa2f = ceiling)")
        Spacer(modifier = Modifier.height(8.dp))
        PrepTerm("BELOW / UNDER — تحت (ta7t)")
        PrepBodyText("• **تحت** الكرسي — **ta7t** il-kursi — **under** the chair")
        PrepBodyText("• **تحت** الأرض — **ta7t** il-ard — underground / **below** the ground")
        Spacer(modifier = Modifier.height(8.dp))
        PrepTerm("IN — في (fi) / بـ (b-)")
        PrepBodyText("• **بالبيت** — **bi**l-beit — **in** the house (the everyday Levantine form)")
        PrepBodyText("• **في** البيت — **fi**l-beit — **in** the house (also correct; more MSA / careful speech)")
        PrepBodyText("• **في** إيدو — **fi** iido — **in** his hand (= **b**-iido)")
        PrepBodyText("• With a pronoun the form is **fii-**: **فيه** fiih / **فيها** fiiha — in it. See \"Two Words, Same Meaning\" below for when to use which.")
        Spacer(modifier = Modifier.height(8.dp))
        PrepTerm("BY / NEXT TO — جنب (janb) / عند (3and)")
        PrepBodyText("• **جنب** الباب — **janb** il-bab — **next to** the door")
        PrepBodyText("• وقف **عندو** — wi2if **3and**o — stood **next to** him / **by** him")
        Spacer(modifier = Modifier.height(8.dp))
        PrepTerm("NEAR — قريب من (2arib min)")
        PrepBodyText("• **قريب من** البيت — **2arib min** il-beit — **near** the house")
        PrepBodyText("• هو **قريب منك** — huwa **2arib minn**ak — he is **near** you")
        Spacer(modifier = Modifier.height(8.dp))
        PrepTerm("FAR FROM / AWAY FROM — بعيد عن (b3id 3an)")
        PrepBodyText("• **بعيد عن** البلد — **b3id 3an** il-balad — **far from** the city")
        PrepBodyText("• ابعد **عن** هون — ib3id **3an** hawn — get **away from** here / move **away from** here")
        PrepBodyText("• **بعيد عنك** — **b3id 3ann**ak — **away from** you / **far from** you")
        Spacer(modifier = Modifier.height(8.dp))
        PrepTerm("TOWARDS — نحو (na7wa) / لعند (la-3and) / لجهة (la-jihet)")
        PrepBodyText("• **نحو** (**na7wa**) — **towards** [a direction], slightly formal")
        PrepBodyText("• **لعند** (**la-3and**) — **towards** a person, going in someone's direction")
        PrepBodyText("• **لجهة** (**la-jihet**) — **in the direction of** [a place]")
        PrepBodyText("• امشي **نحو** الباب — imshi **na7w** il-baab — walk **towards** the door")
        PrepBodyText("• جا **لعندي** — ja **la-3and**i — he came **towards** me")
        PrepBodyText("• روح **لجهة** الشمال — ru7 **la-jihet** ish-shmal — go **towards** the north")

        PrepSectionHeader("With a Person / From a Person")
        PrepTerm("WITH — مع (ma3)")
        PrepBodyText("• جيت **معه** — jiit **ma3**o — I came **with** him")
        PrepBodyText("• بدي أحكي **معك** — biddi a7ki **ma3**ak — I want to talk **with** you")
        PrepBodyText("• **مع** بعض — **ma3** ba3ad — together (lit. '**with** each other')")
        Spacer(modifier = Modifier.height(8.dp))
        PrepTerm("FROM A PERSON — من (min) / من عند (min 3ind)")
        PrepBodyText("• أخدت الكتاب **منه** — akhatt il-ktab **minn**o — I took the book **from** him")
        PrepBodyText("• سمعت **من عند** أهلها — simi3t **min 3ind** ahilha — I heard **from** her family")
        PrepBodyText("• هاي رسالة **منك**؟ — hay risaale **minn**ak? (Leb hayde) — is this a message **from** you?")
        Spacer(modifier = Modifier.height(8.dp))
        PrepTerm("TO A PERSON — لـ (la-) / عند (3and)")
        PrepBodyText("• **بعتلو** رسالة — ba3it**l**o risale — I sent **(to)** him a message")
        PrepBodyText("• **قلتلها** — 2ulti**l**ha — I told her (lit. 'I said **to** her')")
        PrepBodyText("• **عنده** مشكلة — **3and**o mushkile — he has a problem (lit. '**at** him a problem')")

        PrepSectionHeader("For")
        PrepTerm("FOR — لـ (la-) for benefit; عشان/3ashaan or مشان/mishaan for purpose")
        Spacer(modifier = Modifier.height(4.dp))
        PrepTerm("BENEFIT — لـ (la-) [doing something on behalf of / for the sake of]")
        PrepBodyText("• عم بشتغل هيك **لشغلتي** — 3am bishtaghil hayk **la**-shughilti — I'm doing it this way **for** my job")
        PrepBodyText("• هاد **إلك** — had **il**ak — this is **for** you")
        PrepBodyText("• عامل هيك **لصالحك** — 3amil hayk **la**-sali7ak — doing this **for** your benefit")
        Spacer(modifier = Modifier.height(8.dp))
        PrepTerm("PURPOSE — عشان (3ashaan) / مشان (mishaan) [so that / in order that]")
        PrepBodyText("• **عشان** (**3ashaan**) — so that, in order to, because. The main Palestinian (and Jordanian) form, from على شان \"on account of\"")
        PrepBodyText("• **مشان / منشان** (**mishaan / minshaan**) — the same word in Syrian and Lebanese; also heard in northern Palestine")
        PrepBodyText("• بيشتغل كتير **مشان** يوفّر مصاري — byishtighil ktiir **mishaan** ywaffer maSaari — he works a lot **in order to** save money")
        PrepBodyText("• **عشان** هيك ما جيت — **3ashaan** hek ma jiit — that's why I didn't come (**for** that reason)")
        PrepBodyText("• **مشان** تنجح لازم تدرس — **mishaan** tinja7 laazim tidrus — **in order to** succeed you need to study")
        Spacer(modifier = Modifier.height(8.dp))
        PrepTerm("HOPING FOR — بتمنّى (batmanna) / بتمنّى إنّو (batmanna inno)")
        PrepBodyText("• بتمنّى **إنّو** يصير هيك — batmanna **inno** ySiir hayk — I'm hoping **for** this to happen (lit. I hope **that**…)")
        PrepBodyText("• بتمنّى أسمع منك خبر — batmanna asma3 minnak khabar — hoping **for** news from you (lit. I hope to hear news from you)")
        PrepBodyText("• بتمنى يزبط الموضوع — batmanna yizbut il-mawdu3 — hoping **for** things to work out")

        PrepSectionHeader("Two Words, Same Meaning — Which One To Use")
        PrepBodyText("Pal = Palestinian, Leb = Lebanese, Syr = Syrian. \"Levantine\" = all three. Usage varies by town and speaker, so treat these as tendencies, not hard rules.")

        PrepSubHeader("IN — بـ (b-) vs في (fi)")
        PrepBodyText("• Before a noun, both mean \"in\" and both are correct: **بالبيت** bil-beit = **في البيت** fil-beit — in the house.")
        PrepBodyText("• **b-** is the everyday Levantine choice (Pal, Leb, Syr): ساكن **ب**بيروت — saakin **b**-Beirut — I live in Beirut; **بالشام** — **bi**sh-Shaam — in Damascus.")
        PrepBodyText("• **fi** + noun is the MSA (and Egyptian) form. In the Levant you hear it too, more in careful speech and some Palestinian speakers, but **b-** is more common.")
        PrepBodyText("• With a pronoun ending, Levantine uses **fii-**, not b-: **فيه** fiih / **فيها** fiiha — in it. الغرفة **فيها** شبّاك — il-ghurfe **fiiha** shibbaak — the room has a window (lit. **in it** a window).")
        PrepBodyText("• Only **b-** means \"with / by means of\": **بالسكّينة** — **bi**s-sikkiine — **with** the knife; **بالباص** — **bi**l-baaS — **by** bus; **بعشرة** شيكل — **bi**-3ashra shekel — **for** ten shekels.")
        PrepBodyText("• **fi** has two other jobs: on its own it means \"there is\": **في** قهوة؟ — **fi** 2ahwe? — is there coffee?; **ما في** — ma fi — there isn't. With a pronoun + verb it can mean \"can\": **فيني / فيّي** روح — **fiini / fiyyi** ruu7 — I can go. This \"can\" is mostly Leb and Syr; Pal usually says **بقدر** — ba2dar.")
        PrepBodyText("• So **فيه** can mean \"in it\", \"he can\" or (with ما) \"there's no…\". Context and the verb that follows tell you which.")
        PrepBodyText("• Don't confuse the preposition **b-** with the **b-** present-tense prefix on verbs (**ب**عرف b-a3ref, **بي**حكي byi7ki). They are different things.")
        PrepBodyText("• Hebrew parallel: **b-** works like Hebrew ב (בבית = in the house, בסכין = with a knife).")

        PrepSubHeader("OR — ولا (wella) vs أو (aw)")
        PrepBodyText("• **wella / walla / willa** — for a question that asks you to choose: شاي **ولا** قهوة؟ — tea **or** coffee? Also \"…**ولا** لأ؟\" — wella la2? — or not? (Pal, Leb, Syr)")
        PrepBodyText("• **aw** — in statements: يومين **أو** تلاتة — yoomein **aw** tlaate — two **or** three days. It is also the only \"or\" in writing / MSA.")

        PrepSubHeader("BUT — بس (bas) vs لكن (laakin)")
        PrepBodyText("• **bas** is the everyday \"but\" everywhere in the Levant. **laakin** (Leb **leekin**) sounds more formal or emphatic.")

        PrepSubHeader("WITHOUT — بلا (bala) vs بدون (biduun)")
        PrepBodyText("• Both are used in Pal, Leb and Syr. **bala** is more colloquial (**بلا** سكّر — without sugar); **biduun** is neutral and also MSA.")

        PrepSubHeader("BECAUSE / SO THAT — عشان vs مشان vs كرمال vs لأنّو")
        PrepBodyText("• **عشان** (**3ashaan**) — Pal / Jordanian. **مشان / منشان** (**mishaan / minshaan**) — Syr / Leb. Both mean so that, in order to, because of.")
        PrepBodyText("• **كرمال** (**karmaal**, Leb **kermel**) — Leb / Syr: for the sake of, so that. **كرمالك** — karmaalak — for your sake.")
        PrepBodyText("• **لأنّو** (**la2inno**) — because + a full sentence. Used everywhere. لأنّو تعبان — la2inno ta3baan — because he's tired.")

        PrepSubHeader("TO (a place) — لـ (la-) vs على / عـ (3ala / 3a-)")
        PrepBodyText("• Both work with going verbs: رحت **عالبيت** = رحت **للبيت** — I went home. **3a-** is extremely common in Pal, Leb and Syr speech (**3a**sh-shughl, **3a** Beirut).")
        PrepBodyText("• For people (tell, send, give to) use **la-**: **قلتلّه** — 2ultillo — I told him. Not 3ala.")

        PrepSubHeader("AT — عند (3ind) vs بـ (b-) vs على (3ala)")
        PrepBodyText("• **3ind** = at someone's place (like Hebrew אצל): **عند** صاحبي — at my friend's. With a pronoun it also means \"have\": **عندي** — I have.")
        PrepBodyText("• **b-** = at / in a place: **بالجامعة** — at the university. **3ala / 3a-** = at a time: **عالساعة** تلاتة — at three o'clock.")

        PrepSubHeader("WITH — مع (ma3) vs بـ (b-)")
        PrepBodyText("• **ma3** = together with (a person or thing): إجيت **معه** — I came with him. **b-** = using a tool or means: كتبت **بالقلم** — I wrote with the pen.")

        PrepSubHeader("UNTIL — لغاية (la-ghaayet) vs لحدّ (la-7add) vs لحتّى (la-7atta)")
        PrepBodyText("• All three mean \"until\" before a noun: **لغاية / لحدّ** هلّق — until now. **la-ghaayet** is very Pal; **la-7add** is common in Pal, Leb and Syr; **la-7atta** is mainly Syr / Leb.")
        PrepBodyText("• **على ما** (**3ala ma**) + verb = until / by the time: **على ما** يرجع — until (by the time) he comes back.")

        PrepSubHeader("WHEN — لمّا (lamma) vs إيمتى (eemta)")
        PrepBodyText("• **lamma** = when, in a statement: **لمّا** إجيت، كان نايم — **when** I came, he was asleep.")
        PrepBodyText("• **eemta / imta** (Pal **imta**, Leb / Syr **eemta**) = when? as a question: **إيمتى** جاي؟ — **when** are you coming? Never ask with lamma.")
        PrepBodyText("• **وقت ما** (**wa2t ma**) = at the time that, whenever.")

        PrepSubHeader("NOT — مش (mish) vs مو (mu) vs ما (ma)")
        PrepBodyText("• Non-verbs: **mish** in Pal and Leb (Leb often **mesh**); **mu** in Syr: **مو** منيح — mu mnii7 — not good.")
        PrepBodyText("• Verbs: **ma** everywhere. Pal adds **-sh** (ما بعرفش ma ba3rafsh); Leb and Syr usually don't (ما بعرف ma ba3ref).")

        PrepSectionHeader("Quick Reference")
        PrepRef("و (w) — and")
        PrepRef("أو (aw) / ولا (wella) — or")
        PrepRef("مش (mish) — not (non-verb)")
        PrepRef("ما...ش (ma...sh) — not (verb negation)")
        PrepRef("بلا (bala) / بدون (bidun) — without")
        PrepRef("لهيك (la-hek) — therefore")
        PrepRef("مع إنو (ma3 inno) — even though")
        PrepRef("رغم إنو (raghm inno) — although / despite")
        PrepRef("وهم (w-humm) — while (they were...)")
        PrepRef("بس (bas) / لاكن (laakin) — but; however")
        PrepRef("مع هيك (ma3 hek) / رغم هيك (raghm hek) — nevertheless")
        PrepRef("وكمان (w-kamaan) / وغير هيك (w-gheir hek) — moreover")
        PrepRef("بالإضافة لـ (bil-iDaafe la-) — in addition (to)")
        PrepRef("وبعدين (w-ba3dein) — and then / and later")
        PrepRef("وبعد هيك (w-ba3d hek) — and after that")
        PrepRef("بس قبل هيك (bas 2abl hek) — but before that")
        PrepRef("بعد (ba3d) — after")
        PrepRef("قبل (2abl) — before")
        PrepRef("لغايت (lghayt) / على ما (3ala ma) — until")
        PrepRef("لمّا (lamma) — when")
        PrepRef("من...لـ (min...la) — from...to")
        PrepRef("لـ (la-) — to [place/person]")
        PrepRef("من (min) — from")
        PrepRef("من عند (min 3ind) — from [a person's place]")
        PrepRef("لوين (la-wein) — to where")
        PrepRef("لمين (la-miin) — to whom")
        PrepRef("من مين (min miin) — from whom")
        PrepRef("مين (miin) — who / whom")
        PrepRef("جوا (jawwa) — inside")
        PrepRef("برا (barra) — outside")
        PrepRef("فوق (foo2) / على (3ala) — on / above")
        PrepRef("تحت (ta7t) — below / under")
        PrepRef("في (fi) / بـ (b-) — in")
        PrepRef("جنب (janb) — next to")
        PrepRef("قريب من (2arib min) — near")
        PrepRef("بعيد عن (b3id 3an) — far from / away from")
        PrepRef("نحو (na7wa) / لجهة (la-jihet) — towards")
        PrepRef("مع (ma3) — with")
        PrepRef("لـ (la-) — for [benefit]")
        PrepRef("عشان (3ashaan) / مشان (mishaan) — for [purpose] / so that")

        PrepSectionHeader("Prepositions With Useful Verbs")
        PrepBodyText("Many verbs come with a fixed preposition, and it is often NOT the one English uses (think about = fakkar FI, look for = dawwar 3ALA). Learn the verb and its preposition together. The preposition is bold and underlined below.")
        PrepBodyText("Pronoun endings attach straight to the preposition: fiyyi / fiik / fiiki / fiih (me / you m / you f / him), 3alayyi / 3aleik / 3aleiki / 3aleih, minni / minnak / minnik / minno, ma3i / ma3ak / ma3ik / ma3o, -li / -lak / -lik / -lo (la- + pronoun, glued to the verb). Where Lebanese differs, it's shown as (Leb …). Careful: in Leb / Syr, fiyyi / fiik + a verb also means \"I can / you can\".")
        PrepBodyText("In Arabic script a one-letter preposition (بـ b- / لـ l- / عـ 3a-) is written joined to the next word, so the whole joined word is underlined.")

        PrepSubHeader("Thinking, Remembering, Dreaming")
        PrepVerb("fakkar fi — to think about")
        PrepBodyText("• فكّرت __فيّي__؟ — fakkart __fiyyi__? (Leb, to a woman: fakkarte __fiyye__?) — did you think __about__ me?")
        PrepBodyText("• عم بفكّر __فيك__ كل يوم — 3am bfakker __fiik__ kull yoom — I'm thinking __about__ you every day")
        PrepVerb("nisi 3an — to forget about  ·  tzakkar — to remember (no preposition)")
        PrepBodyText("• نسيت __عن__ الاجتماع؟ — nsiit __3an__ el-ijtimaa3? — did you forget __about__ the meeting? (also just: nsiit el-ijtimaa3?)")
        PrepVerb("7ilim fi / 7ilim bi — to dream about")
        PrepBodyText("• حلمت __فيك__ مبارح — 7lomt __fiik__ mbaari7 — I dreamt __about__ you yesterday")

        PrepSubHeader("Getting, Giving, Sending — la- (for / to)")
        PrepVerb("jaab la- — to bring / get (something) for someone")
        PrepBodyText("• __جبتلّي__ هديّة؟ — jibt__illi__ hdiyye? (to a woman: jibti__lli__) — did you get me a present? (lit. did you bring __for__ me)")
        PrepBodyText("• __جبتلها__ ورد — jibt__ilha__ ward — I got flowers __for__ her")
        PrepVerb("ishtara la- — to buy for")
        PrepBodyText("• اشتريت __لإمّي__ كتاب — ishtareit __la__-immi ktaab — I bought a book __for__ my mother")
        PrepVerb("ba3at la- — to send to")
        PrepBodyText("• __بعتتلك__ رسالة — ba3att__illak__ risaale — I sent you a message (__to__ you)")
        PrepVerb("2aal la- — to say to / tell")
        PrepBodyText("• شو __قلتلّه__؟ — shu 2ult__illo__? — what did you tell him (say __to__ him)?")
        PrepVerb("rajja3 la- — to give back to  ·  a3Ta — to give (no preposition)")
        PrepBodyText("• __رجّعلي__ الكتاب — rajji3__li__ el-ktaab — give the book back __to__ me")

        PrepSubHeader("Time — min … la- / la-7add (from … until)")
        PrepBodyText("• كنت __بالشغل__ __من__ الساعة تمانية __لهلّق__ — kunt __bi__sh-shughl __min__ es-saa3a tmaanye __la__-halla2 (Leb __la-7add__ halla2) — I was __at__ work __from__ 8:00 __until__ now")
        PrepBodyText("• بشتغل __من__ الصبح __للمسا__ — bishtghil __min__ eS-Sub7 __la__l-masa — I work __from__ morning __till__ evening")
        PrepBodyText("• استنّيتك __من__ ساعة — stanneitak __min__ saa3a — I've been waiting for you __for__ an hour (lit. __since__ an hour)")
        PrepVerb("stanna — to wait for (direct object: 'wait-you', no preposition)")
        PrepBodyText("• __صرلي__ ساعة بستنّى — Sar__li__ saa3a bastanna — I've been waiting __for__ an hour (Sar-li, lit. 'it has become __to__ me')")

        PrepSubHeader("Talking, Asking, Looking")
        PrepVerb("7aka ma3 — to talk with  ·  7aka 3an — to talk about")
        PrepBodyText("• حكيت __معه__ __عن__ الشغل — 7akeit __ma3o__ __3an__ esh-shughl — I talked __with__ him __about__ work")
        PrepVerb("sa2al 3an — to ask about")
        PrepBodyText("• سألت __عنك__ — sa2alt __3annak__ — I asked __about__ you (= I checked how you were)")
        PrepVerb("dawwar 3ala — to look for")
        PrepBodyText("• عم بدوّر __على__ مفاتيحي — 3am bdawwer __3ala__ mafatii7i — I'm looking __for__ my keys")
        PrepVerb("tfarraj 3ala — to watch")
        PrepBodyText("• تفرّجنا __على__ فيلم — tfarrajna __3ala__ film — we watched a film (lit. watched __on__)")
        PrepVerb("simi3 3an — to hear about  ·  simi3 min — to hear from")
        PrepBodyText("• سمعت __عنها__ كتير — smi3t __3anha__ ktiir — I heard a lot __about__ her")
        PrepBodyText("• سمعت __منه__ مبارح — smi3t __minno__ mbaari7 — I heard __from__ him yesterday")

        PrepSubHeader("Feelings and Relationships")
        PrepVerb("khaaf min — to be afraid of")
        PrepBodyText("• بخاف __من__ الكلاب — bkhaaf __min__ el-klaab — I'm afraid __of__ dogs")
        PrepVerb("zi3il min — to be upset with")
        PrepBodyText("• زعلان __منّي__؟ — za3laan __minni__? — are you upset __with__ me?")
        PrepVerb("ishta2 la- — to miss (someone)")
        PrepBodyText("• __اشتقتلك__ — ishta2t__illak__ (to a woman: ishta2t__illik__) — I missed you (lit. I longed __for__ you)")
        PrepVerb("t3arraf 3ala — to get to know / meet")
        PrepBodyText("• تعرّفت __عليه__ __بالجامعة__ — t3arraft __3aleih__ __bi__l-jaam3a — I met him __at__ university (lit. got to know __on__ him)")
        PrepVerb("t2akhkhar 3ala — to be late for")
        PrepBodyText("• تأخّرت __على__ الشغل — t2akhkhart __3ala__ esh-shughl — I was late __for__ work")
        PrepVerb("Di7ik 3ala — to laugh at (also: to fool someone)")
        PrepBodyText("• عم تضحك __عليّي__؟ — 3am tiD7ak __3alayyi__? — are you laughing __at__ me? / are you fooling me?")
        PrepVerb("saa3ad bi / saa3ad fi — to help with")
        PrepBodyText("• ساعدني __بالواجب__ — saa3idni __bi__l-waajib — help me __with__ the homework")
        PrepVerb("7abb — to love (no preposition)")
        PrepBodyText("• بحبّك — b7ibbak / b7ibbik — I love you (m / f) — no preposition, like Hebrew אוהב אותך")

        PrepSubHeader("Going, Coming, Riding")
        PrepVerb("raa7 3ala / raa7 la- — to go to  ·  ija min — to come from")
        PrepBodyText("• رحت __عالسوق__ — ru7t __3a__-s-suu2 — I went __to__ the market (3ala → 3a- before el-)")
        PrepBodyText("• إجيت __من__ البيت __عالشغل__ — jiit __min__ el-beit __3a__-sh-shughl — I came __from__ home __to__ work")
        PrepVerb("rikib bi — to get on / ride  ·  nizil min — to get off")
        PrepBodyText("• ركبت __بالباص__ ونزلت __منه__ — rkibt __bi__l-baaS w nzilt __minno__ — I got __on__ the bus and got __off__ it")
        PrepVerb("wiSil 3ala / wiSil la- — to arrive at")
        PrepBodyText("• وصلت __عالبيت__؟ — wSilt __3a__-l-beit? — did you get home (arrive __at__ the house)?")
        PrepVerb("saafar 3ala / saafar la- — to travel to")
        PrepBodyText("• مسافر __على__ لبنان بكرا — msaafer __3ala__ Libnaan bukra — I'm travelling __to__ Lebanon tomorrow")
        PrepVerb("Tili3 min — to leave / go out of")
        PrepBodyText("• طلعت __من__ البيت بكّير — Tli3t __min__ el-beit bakkiir — I left (went out __of__) the house early")

        PrepSubHeader("At, In, On, With — Being Somewhere or Busy With Something")
        PrepBodyText("• كنت __عند__ صاحبي — kunt __3ind__ Saa7bi — I was __at__ my friend's (place)  (3ind = אצל)")
        PrepBodyText("• ساكن __بحيفا__ — saakin __b__-7eifa — I live __in__ Haifa")
        PrepBodyText("• قاعد __عالكنبة__ — 2aa3id __3a__-l-kanabe — sitting __on__ the sofa")
        PrepBodyText("• مشغول __بالشغل__ — mashghuul __bi__sh-shughl — busy __with__ work")
        PrepBodyText("• مبسوط __فيك__ — mabsuuT __fiik__ — I'm pleased __with__ you")

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun PrepSectionHeader(text: String) {
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
private fun PrepSubHeader(text: String) {
    Spacer(modifier = Modifier.height(12.dp))
    Text(
        text = fixArabizi(text),
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.fillMaxWidth()
    )
    Spacer(modifier = Modifier.height(4.dp))
}

// A heading line naming the preposition itself (English, Arabic and arabizi) — all bold.
@Composable
private fun PrepTerm(text: String) {
    Text(
        text = fixArabizi(text),
        style = MaterialTheme.typography.bodyMedium,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(vertical = 2.dp)
    )
}

// A verb + its preposition, shown before its examples.
@Composable
private fun PrepVerb(text: String) {
    Spacer(modifier = Modifier.height(6.dp))
    Text(
        text = fixArabizi(text),
        style = MaterialTheme.typography.bodyMedium,
        fontWeight = FontWeight.SemiBold,
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(vertical = 2.dp)
    )
}

// Quick-reference line: the term (before " — ") in bold.
@Composable
private fun PrepRef(text: String) {
    val idx = text.indexOf(" — ")
    PrepBodyText(if (idx > 0) "**${text.substring(0, idx)}**${text.substring(idx)}" else text)
}

@Composable
private fun PrepBodyText(text: String) {
    Text(
        text = richText(text),
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(vertical = 2.dp)
    )
}
