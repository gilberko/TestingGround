package com.example.arabicapp.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.dp

private data class TechInterviewDLine(
    val speaker: String,
    val ar: String,
    val translit: String,
    val en: String
)

private const val RAMI = "A (interviewer — Rami)"
private const val LINA = "B (interviewer — Lina)"
private const val SAMER = "C (candidate — Samer)"

private val techInterviewDialogue = listOf(
    // Introductions
    TechInterviewDLine(RAMI, "أهلا وسهلا سامر، تفضّل اقعد. أنا رامي، وهاي لينا. إحنا التنين senior engineers بالفريق.", "ahlan wa-sahlan samer, tfaddal u23od. ana rami, w-hai lina. i7na et-tnein senior engineers bil-farii2.", "Welcome, Samer, please sit down. I'm Rami and this is Lina. We're both senior engineers on the team."),
    TechInterviewDLine(SAMER, "أهلا فيكم، تشرّفنا. شكرًا إنكم عزمتوني.", "ahlan fiikum, tsharrafna. shukran innkum 3azamtuuni.", "Hello, nice to meet you. Thanks for inviting me."),
    TechInterviewDLine(LINA, "احكيلنا شوي عن حالك وعن الخبرة تبعتك.", "i7kiilna shwai 3an 7aalak w-3an el-khibra taba3tak.", "Tell us a bit about yourself and your experience."),
    TechInterviewDLine(SAMER, "أنا بشتغل بالمجال من حوالي تنعشر سنة. بلّشت بشركة security، كنت أكتب kernel drivers لل-antivirus.", "ana bashtghil bil-majaal min 7awaali tna3shar sane. ballasht b-sharikat security, kunt aktob kernel drivers lal-antivirus.", "I've been working in the field for about twelve years. I started at a security company, writing kernel drivers for the antivirus."),
    TechInterviewDLine(SAMER, "بعدين انتقلت لشركة كبيرة، واشتغلت سبع سنين بفريق ال-file system filter drivers.", "ba3dein inta2alt la-sharike kbiire, w-ishtaghalt sab3 sniin b-farii2 el-file system filter drivers.", "Then I moved to a big company and worked for seven years on the file system filter drivers team."),
    TechInterviewDLine(SAMER, "آخر تلات سنين أنا team lead بفريق ال-EDR. منكتب drivers بال-C وال-C++، ومنعمل debugging كتير بال-WinDbg.", "aakhir tlat sniin ana team lead b-farii2 el-EDR. minuktob drivers bil-C wil-C++, w-mni3mal debugging ktiir bil-WinDbg.", "For the last three years I've been a team lead on the EDR team. We write drivers in C and C++, and we do a lot of debugging with WinDbg."),
    TechInterviewDLine(RAMI, "ممتاز. وليش بدّك تترك هلّأ؟", "mumtaaz. w-leish biddak titrok halla2?", "Excellent. And why do you want to leave now?"),
    TechInterviewDLine(SAMER, "بدّي تحدّي جديد، وبحب أشتغل أكتر عال-kernel نفسه، مش بس عال-drivers.", "biddi ta7addi jdiid, w-ba7ebb ashtghil aktar 3al-kernel nafso, mish bas 3al-drivers.", "I want a new challenge, and I'd like to work more on the kernel itself, not just on drivers."),

    // Process vs thread
    TechInterviewDLine(LINA, "تمام. خلّينا نبلّش بسؤال أساسي: شو الفرق بين process و-thread؟", "tamaam. khallina nballish b-su2aal asaasi: shu el-far2 bein process w-thread?", "Great. Let's start with a basic question: what's the difference between a process and a thread?"),
    TechInterviewDLine(SAMER, "ال-process هو زيّ حاوية: إلو virtual address space خاص فيه، handle table، و-access token تبع ال-security.", "el-process hu zayy 7aawye: ilo virtual address space khaas fii, handle table, w-access token taba3 el-security.", "A process is like a container: it has its own private virtual address space, a handle table, and a security access token."),
    TechInterviewDLine(SAMER, "ال-process لحالو ما بينفّذ ولا إشي. اللي بينفّذ الكود هو ال-thread.", "el-process la7aalo ma bi-naffiz wala ishi. elli bi-naffiz el-code hu el-thread.", "A process by itself doesn't execute anything. What executes the code is the thread."),
    TechInterviewDLine(SAMER, "كل thread إلو stack خاص فيه، registers، و-context. وال-scheduler بيعمل schedule لل-threads، مش لل-processes.", "kull thread ilo stack khaas fii, registers, w-context. wil-scheduler bi3mal schedule lal-threads, mish lal-processes.", "Each thread has its own stack, registers and context. And the scheduler schedules threads, not processes."),
    TechInterviewDLine(RAMI, "وشو بيتشاركوا ال-threads بنفس ال-process؟", "w-shu bitsharaku el-threads b-nafs el-process?", "And what do threads in the same process share?"),
    TechInterviewDLine(SAMER, "بيتشاركوا كل الذاكرة وال-handles. عشان هيك التواصل بيناتهم أسهل وأسرع، بس لازم synchronization، وإلّا بيصير race conditions.", "bitsharaku kull ez-zaakra wil-handles. 3ashaan hek et-tawaasul beinaathom as-hal w-asra3, bas laazem synchronization, w-illa bisiir race conditions.", "They share all the memory and the handles. That's why communication between them is easier and faster, but you need synchronization, otherwise you get race conditions."),
    TechInterviewDLine(LINA, "وشو أغلى: إنك تعمل process جديد ولّا thread جديد؟", "w-shu aghla: innak ta3mal process jdiid willa thread jdiid?", "And what's more expensive: creating a new process or a new thread?"),
    TechInterviewDLine(SAMER, "ال-process أغلى بكتير، لازم النظام يعمل address space جديد ويحمّل ال-image. وكمان ال-context switch بين threads بنفس ال-process أرخص، لأنّه ما في تغيير لل-page tables.", "el-process aghla b-ktiir, laazem en-nizaam ya3mal address space jdiid w-y7ammil el-image. w-kamaan el-context switch bein threads b-nafs el-process arkhas, la2inno ma fi taghyiir lal-page tables.", "A process is much more expensive — the system has to create a new address space and load the image. Also, a context switch between threads of the same process is cheaper, because the page tables don't change."),

    // Mutex vs spinlock
    TechInterviewDLine(RAMI, "منيح كتير. هلّأ سؤال عن synchronization: شو الفرق بين mutex و-spinlock؟", "mnii7 ktiir. halla2 su2aal 3an synchronization: shu el-far2 bein mutex w-spinlock?", "Very good. Now a question about synchronization: what's the difference between a mutex and a spinlock?"),
    TechInterviewDLine(SAMER, "لمّا thread بيستنّى mutex، ال-scheduler بيحطّه ب-wait state وبيعمل context switch ل-thread تاني. يعني ما بيصرف CPU وهو مستنّي.", "lamma thread bistanna mutex, el-scheduler bi7ottu b-wait state w-bi3mal context switch la-thread taani. ya3ni ma bisrof CPU w-hu mistanni.", "When a thread waits on a mutex, the scheduler puts it in a wait state and context-switches to another thread. So it doesn't burn CPU while it waits."),
    TechInterviewDLine(SAMER, "ال-spinlock بالعكس: ال-CPU بيضل يلفّ ب-loop لحتّى يتحرّر ال-lock. هاد اسمه busy waiting.", "el-spinlock bil-3aks: el-CPU bidall yliff b-loop la-7atta yit7arrar el-lock. hada ismo busy waiting.", "A spinlock is the opposite: the CPU keeps spinning in a loop until the lock is released. That's called busy waiting."),
    TechInterviewDLine(SAMER, "وبال-Windows kernel، لمّا تاخد spinlock، ال-IRQL بيطلع ل-DISPATCH_LEVEL، يعني ال-scheduler ما بيقدر يعمل preempt لل-thread على هاد ال-CPU.", "w-bil-Windows kernel, lamma taakhod spinlock, el-IRQL byitla3 la-DISPATCH_LEVEL, ya3ni el-scheduler ma bi2dar ya3mal preempt lal-thread 3ala hada el-CPU.", "And in the Windows kernel, when you acquire a spinlock, the IRQL is raised to DISPATCH_LEVEL, which means the scheduler can't preempt the thread on that CPU."),
    TechInterviewDLine(LINA, "طيّب، إمتى بتستعمل كل واحد فيهم؟", "tayyeb, imta btista3mel kull wa7ad fiihom?", "OK, when do you use each one?"),
    TechInterviewDLine(SAMER, "spinlock بس ل-critical sections قصيرة كتير، أو لمّا أنا أصلًا ب-DISPATCH_LEVEL، مثلًا ب-DPC — هناك ممنوع تستنّى على mutex. ومع ISR بتستعمل interrupt spinlock.", "spinlock bas la-critical sections 2asiire ktiir, aw lamma ana aslan b-DISPATCH_LEVEL, masalan b-DPC — hunaak mamnuu3 tistanna 3ala mutex. w-ma3 ISR btista3mel interrupt spinlock.", "A spinlock only for very short critical sections, or when I'm already at DISPATCH_LEVEL, for example in a DPC — there you're not allowed to wait on a mutex. And with an ISR you use an interrupt spinlock."),
    TechInterviewDLine(SAMER, "mutex للشغل الأطول، ب-IRQL أقلّ من DISPATCH_LEVEL، مثلًا لمّا لازم أوصل ل-paged memory أو أعمل I/O.", "mutex lash-shughl el-atwal, b-IRQL a2all min DISPATCH_LEVEL, masalan lamma laazem awsal la-paged memory aw a3mal I/O.", "A mutex for longer work, at an IRQL below DISPATCH_LEVEL — for example when I need to access paged memory or do I/O."),
    TechInterviewDLine(RAMI, "وشو أخطر غلط ممكن الواحد يعمله مع spinlock؟", "w-shu akhtar ghalat mumken el-waa7ad ya3malo ma3 spinlock?", "And what's the most dangerous mistake you can make with a spinlock?"),
    TechInterviewDLine(SAMER, "إنك توصل ل-pageable memory وإنت ماسك spinlock. إذا صار page fault ب-DISPATCH_LEVEL، النظام بيوقع — بتطلع IRQL_NOT_LESS_OR_EQUAL، يعني blue screen.", "innak tuusal la-pageable memory w-inta maasek spinlock. iza saar page fault b-DISPATCH_LEVEL, en-nizaam byoo2a3 — btitla3 IRQL_NOT_LESS_OR_EQUAL, ya3ni blue screen.", "Touching pageable memory while holding a spinlock. If a page fault happens at DISPATCH_LEVEL, the system crashes — you get IRQL_NOT_LESS_OR_EQUAL, meaning a blue screen."),
    TechInterviewDLine(SAMER, "وكمان ممنوع تستنّى أو تعمل sleep وإنت ماسكه، ولازم تمسكه أقصر وقت ممكن.", "w-kamaan mamnuu3 tistanna aw ta3mal sleep w-inta maasko, w-laazem timsako a2sar wa2t mumken.", "Also, you must not wait or sleep while holding it, and you have to hold it for the shortest time possible."),
    TechInterviewDLine(LINA, "جواب ممتاز. وب-user mode؟", "jawaab mumtaaz. w-b-user mode?", "Excellent answer. And in user mode?"),
    TechInterviewDLine(SAMER, "ب-user mode عادةً بستعمل critical section أو SRW lock. ال-critical section ممكن تعمل spin شوي قبل ما تنام، وهي أسرع من ال-mutex، لأنّ ال-mutex هو kernel object وكل مرّة في system call. ال-critical section ما بتدخل عال-kernel إلّا إذا في contention.", "b-user mode 3aadatan bista3mel critical section aw SRW lock. el-critical section mumken ta3mal spin shwai 2abel ma tnaam, w-hiyye asra3 min el-mutex, la2inn el-mutex hu kernel object w-kull marra fi system call. el-critical section ma btidkhol 3al-kernel illa iza fi contention.", "In user mode I usually use a critical section or an SRW lock. A critical section can spin a little before going to sleep, and it's faster than a mutex, because a mutex is a kernel object and every operation is a system call. A critical section only enters the kernel when there's contention."),

    // Closing
    TechInterviewDLine(RAMI, "ممتاز. عندك أسئلة إلنا؟", "mumtaaz. 3indak as2ile ilna?", "Excellent. Do you have any questions for us?"),
    TechInterviewDLine(SAMER, "أيوا، شو أكبر تحدّي عندكم بالفريق هلّأ؟", "aywa, shu akbar ta7addi 3indkum bil-farii2 halla2?", "Yes — what's the biggest challenge your team has right now?"),
    TechInterviewDLine(LINA, "إحنا منشتغل على driver جديد، وأصعب إشي هو ال-performance تحت load عالي.", "i7na mnishtghil 3ala driver jdiid, w-as3ab ishi hu el-performance ta7t load 3aali.", "We're working on a new driver, and the hardest part is performance under high load."),
    TechInterviewDLine(RAMI, "شكرًا كتير سامر، كانت مقابلة ممتعة. منرجعلك خلال هالأسبوع.", "shukran ktiir samer, kaanat mu2aabale mumti3a. mnirja3lak khilaal hal-usbuu3.", "Thanks a lot, Samer, it was an enjoyable interview. We'll get back to you this week."),
    TechInterviewDLine(SAMER, "شكرًا إلكم، يعطيكم العافية!", "shukran ilkum, ya3tiikum el-3aafye!", "Thank you — all the best to you (lit. may God give you health)!")
)

@Composable
fun TechInterviewDialogueScreen() {
    LazyColumn(modifier = Modifier.padding(16.dp)) {
        item {
            Text(
                text = "Technical words (process, thread, mutex, IRQL…) stay in English, the way engineers really speak. Notice the comparisons: aghla (more expensive), asra3 (faster), akhtar (most dangerous), a2sar (shortest).",
                style = MaterialTheme.typography.bodyMedium,
                fontStyle = FontStyle.Italic,
                modifier = Modifier.padding(bottom = 4.dp)
            )
        }
        item { TechInterviewSection("Arabic & Pronunciation") }
        items(techInterviewDialogue) { line ->
            TechInterviewCard(line = line, showArabic = true)
        }
        item { TechInterviewSection("English Translation") }
        items(techInterviewDialogue) { line ->
            TechInterviewCard(line = line, showArabic = false)
        }
    }
}

@Composable
private fun TechInterviewSection(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp)
    )
}

@Composable
private fun TechInterviewCard(line: TechInterviewDLine, showArabic: Boolean) {
    val containerColor = when (line.speaker) {
        SAMER -> MaterialTheme.colorScheme.secondaryContainer
        RAMI -> MaterialTheme.colorScheme.tertiaryContainer
        else -> MaterialTheme.colorScheme.primaryContainer
    }

    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(containerColor = containerColor)
    ) {
        Text(
            text = line.speaker,
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(start = 12.dp, top = 8.dp, end = 12.dp)
        )
        if (showArabic) {
            Text(
                text = line.ar,
                style = TextStyle(textDirection = TextDirection.Rtl),
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp, vertical = 2.dp)
            )
            Text(
                text = line.translit,
                style = MaterialTheme.typography.bodyMedium,
                fontStyle = FontStyle.Italic,
                modifier = Modifier.padding(start = 12.dp, end = 12.dp, bottom = 8.dp)
            )
        } else {
            Text(
                text = line.en,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(start = 12.dp, end = 12.dp, bottom = 8.dp, top = 2.dp)
            )
        }
    }
}
