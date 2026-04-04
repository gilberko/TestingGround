package com.example.linuxapp.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.linuxapp.screens.kernel.BodyText
import com.example.linuxapp.screens.kernel.SectionCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LinuxHistoryScreen(onBack: () -> Unit) {
    Scaffold(
        containerColor = Color.Black,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Linux History",
                        color = Color(0xFF00FF41),
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color(0xFF00FF41)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Black)
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                start = 16.dp,
                end = 16.dp,
                top = innerPadding.calculateTopPadding() + 8.dp,
                bottom = innerPadding.calculateBottomPadding() + 8.dp
            )
        ) {
            item {
                SectionCard(title = "Unix Origins (1969)") {
                    BodyText("Unix was born at Bell Labs (AT&T) in 1969, created by Ken Thompson and Dennis Ritchie. Thompson originally wrote it to run a game (Space Travel) on a spare PDP-7 computer.")
                    Spacer(Modifier.height(8.dp))
                    BodyText("Ritchie then invented the C programming language specifically to rewrite Unix, making it one of the first operating systems written in a high-level language. This made Unix portable — it could be compiled and run on different hardware, which was revolutionary.")
                    Spacer(Modifier.height(8.dp))
                    BodyText("Unix introduced ideas that are still foundational today: everything is a file, small single-purpose tools connected via pipes, a hierarchical filesystem, and a clean separation between the kernel and user space.")
                }
                Spacer(Modifier.height(8.dp))
            }
            item {
                SectionCard(title = "Unix Spreads") {
                    BodyText("AT&T licensed Unix to universities for a nominal fee. The University of California, Berkeley received a copy and began improving it heavily — their version became BSD (Berkeley Software Distribution). BSD contributors added virtual memory, the TCP/IP networking stack, and many of the tools that make modern Unix systems work.")
                    Spacer(Modifier.height(8.dp))
                    BodyText("By the late 1970s, Unix was the OS of choice in academia and research labs. AT&T saw commercial potential and began selling commercial Unix licenses. The source code was licensed, not given away — companies had to pay, and use was tightly controlled.")
                }
                Spacer(Modifier.height(8.dp))
            }
            item {
                SectionCard(title = "The Unix Wars (1980s–1990s)") {
                    BodyText("As Unix became valuable, every major tech company forked it into their own proprietary variant:")
                    Spacer(Modifier.height(8.dp))
                    BodyText("• IBM → AIX\n• HP → HP-UX\n• Sun Microsystems → SunOS / Solaris\n• Silicon Graphics → IRIX\n• SCO → SCO Unix\n• Digital Equipment → Ultrix\n• NeXT → NeXTSTEP (BSD-based)")
                    Spacer(Modifier.height(8.dp))
                    BodyText("These variants were largely incompatible with each other. Software written for Solaris would not run on AIX without significant porting effort. Customers were locked into vendors. Licenses were expensive — a commercial Unix license could cost tens of thousands of dollars.")
                    Spacer(Modifier.height(8.dp))
                    BodyText("Industry groups fought proxy wars over standards. AT&T and Sun pushed System V Interface Definition (SVID). A competing consortium — OSF (Open Software Foundation) — formed around IBM, HP, and others and backed a rival standard. IEEE's POSIX emerged as a neutral standard attempt, but real-world incompatibilities persisted.")
                    Spacer(Modifier.height(8.dp))
                    BodyText("This era — often called the Unix Wars — was commercially messy and expensive. The fragmentation held back the industry and made Unix increasingly unattractive compared to what was coming next.")
                }
                Spacer(Modifier.height(8.dp))
            }
            item {
                SectionCard(title = "The GNU Project (1983)") {
                    BodyText("Richard Stallman was a programmer at MIT who deeply believed software should be free — not as in price, but as in freedom: the freedom to read, modify, and share source code. In 1983, frustrated by increasingly proprietary software, he announced the GNU Project (GNU's Not Unix) with the goal of creating a completely free Unix-compatible operating system.")
                    Spacer(Modifier.height(8.dp))
                    BodyText("Stallman founded the Free Software Foundation (FSF) in 1985 and wrote the GNU General Public License (GPL) — a legal tool that allows anyone to use and modify software as long as they share their changes under the same terms.")
                    Spacer(Modifier.height(8.dp))
                    BodyText("The GNU Project produced many essential tools still in use today:\n• gcc — the GNU C Compiler\n• glibc — the GNU C library\n• bash — the GNU shell\n• coreutils — ls, cp, mv, cat, grep, and more\n• emacs — the GNU text editor\n• gdb — the GNU debugger")
                    Spacer(Modifier.height(8.dp))
                    BodyText("GNU was building a complete OS, but the one missing piece was the kernel. The GNU Hurd kernel was designed with ambitious goals but proved extremely difficult to implement correctly. By the early 1990s, GNU had all the tools but no working kernel.")
                }
                Spacer(Modifier.height(8.dp))
            }
            item {
                SectionCard(title = "Enter Linux (1991)") {
                    BodyText("Linus Torvalds was a 21-year-old computer science student at the University of Helsinki in Finland. He wanted to learn more about the 80386 processor he had just bought, and he wanted an OS that worked like Unix — but he could not afford expensive commercial Unix licenses, and MINIX (a small Unix-like OS written by professor Andrew Tanenbaum for teaching) was too limited.")
                    Spacer(Modifier.height(8.dp))
                    BodyText("On August 25, 1991, Torvalds posted to the comp.os.minix newsgroup:")
                    Spacer(Modifier.height(8.dp))
                    BodyText("\"I'm doing a (free) operating system (just a hobby, won't be big and professional like gnu) for 386(486) AT clones.\"")
                    Spacer(Modifier.height(8.dp))
                    BodyText("He released version 0.01 in September 1991. In 1992 he switched to the GPL, and the Linux kernel merged with the GNU tools to form what we now call GNU/Linux — a complete, free, Unix-compatible operating system that anyone could run, study, modify, and share.")
                    Spacer(Modifier.height(8.dp))
                    BodyText("The combination was exactly what the world had been waiting for. Developers around the world started contributing, and the project grew exponentially through the 1990s.")
                }
                Spacer(Modifier.height(8.dp))
            }
            item {
                SectionCard(title = "Linux Today") {
                    BodyText("Linux grew from a student hobby project into the most widely deployed operating system kernel in the world. Today it runs:")
                    Spacer(Modifier.height(8.dp))
                    BodyText("• Over 96% of the world's top web servers\n• All of the world's top 500 supercomputers\n• The Android mobile OS (billions of devices)\n• Most cloud infrastructure (AWS, GCP, Azure all run Linux VMs)\n• Embedded systems: routers, TVs, cars, medical devices\n• The International Space Station")
                    Spacer(Modifier.height(8.dp))
                    BodyText("The Linux kernel today has over 30 million lines of code and thousands of contributors from companies including Google, Meta, Microsoft, Intel, Red Hat, and Samsung. Linus Torvalds still maintains it.")
                }
                Spacer(Modifier.height(8.dp))
            }
            item {
                SectionCard(title = "Unix Lives On") {
                    BodyText("Several operating systems today are direct descendants of the original Unix code from Bell Labs and BSD — not just Unix-compatible like Linux, but carrying actual Unix lineage:")
                    Spacer(Modifier.height(8.dp))
                    BodyText("FreeBSD — descended from BSD, used in servers, Netflix's CDN, Sony PlayStation firmware, and Nintendo Switch. Known for performance and a clean, well-documented codebase.")
                    Spacer(Modifier.height(8.dp))
                    BodyText("OpenBSD — also BSD-descended, with an extreme focus on security and correct code. The origin of OpenSSH, which is used on virtually every Linux and Unix server in the world.")
                    Spacer(Modifier.height(8.dp))
                    BodyText("macOS — Apple's OS is built on Darwin, which is BSD-derived. When Steve Jobs returned to Apple he brought NeXTSTEP with him — NeXT had built their OS on top of BSD Unix. This lineage makes macOS a certified UNIX™ (it holds the official trademark from The Open Group).")
                    Spacer(Modifier.height(8.dp))
                    BodyText("So while Linux replaced commercial Unix in most contexts, the original Unix bloodline lives on in FreeBSD, OpenBSD, and the Mac in your pocket.")
                }
                Spacer(Modifier.height(24.dp))
            }
        }
    }
}
