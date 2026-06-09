package com.example.linuxapp.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.linuxapp.screens.kernel.BodyText
import com.example.linuxapp.screens.kernel.CodeBlock
import com.example.linuxapp.screens.kernel.SectionCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LicensingScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Software Licensing",
                        color = Color(0xFF00FF41),
                        fontFamily = FontFamily.Monospace,
                        fontSize = 16.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Black)
            )
        },
        containerColor = Color.Black
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            item {
                SectionCard(title = "Why Licensing Matters") {
                    BodyText("Software is automatically protected by copyright the moment it is created. Without an explicit license, copyright law applies by default: all rights reserved — no one may copy, modify, or distribute your work.")
                    Spacer(Modifier.height(8.dp))
                    BodyText("A license is a legal document that grants specific rights. Open-source licenses let anyone use, study, and modify the code, but they impose conditions — some very light (keep the copyright notice), others strong (release derivative works under the same license).")
                    Spacer(Modifier.height(8.dp))
                    BodyText("Two broad families:")
                    CodeBlock(
                        "PERMISSIVE:  few conditions; you can use the code in\n" +
                        "             proprietary products without revealing source.\n" +
                        "             Examples: MIT, Apache 2.0, BSD\n" +
                        "\n" +
                        "COPYLEFT:    share-alike requirement; if you distribute\n" +
                        "             a product that incorporates the code, your\n" +
                        "             product must also be open-source under the\n" +
                        "             same (or compatible) license.\n" +
                        "             Examples: GPL, LGPL, AGPL, MPL"
                    )
                }
                Spacer(Modifier.height(8.dp))
            }
            item {
                SectionCard(title = "GPL v2 — GNU General Public License") {
                    BodyText("GPL v2 is the license of the Linux kernel, Git, and many core GNU tools. It is strong copyleft (sometimes called 'viral' or 'infectious').")
                    Spacer(Modifier.height(8.dp))
                    BodyText("Key rules:")
                    CodeBlock(
                        "1. You may use, study, modify, and distribute GPL code.\n" +
                        "2. If you distribute a binary that incorporates GPL code\n" +
                        "   you MUST provide the corresponding full source code.\n" +
                        "3. The distributed work as a whole must also be GPL.\n" +
                        "4. You cannot add additional restrictions beyond what GPL\n" +
                        "   requires.\n" +
                        "\n" +
                        "SaaS loophole: Running GPL software on a server and\n" +
                        "letting users access it over the network is NOT\n" +
                        "'distribution'. You do NOT have to share your source.\n" +
                        "(AGPL closes this loophole.)"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("Kernel modules and GPL:")
                    CodeBlock(
                        "// Required to use GPL-only kernel symbols\n" +
                        "MODULE_LICENSE(\"GPL\");\n" +
                        "\n" +
                        "// EXPORT_SYMBOL_GPL makes a symbol available only to\n" +
                        "// modules with MODULE_LICENSE(\"GPL\"):\n" +
                        "EXPORT_SYMBOL_GPL(my_kernel_function);\n" +
                        "\n" +
                        "// EXPORT_SYMBOL is available to any module (grey area):\n" +
                        "EXPORT_SYMBOL(my_other_function);"
                    )
                }
                Spacer(Modifier.height(8.dp))
            }
            item {
                SectionCard(title = "GPL v3") {
                    BodyText("GPL v3 (2007) adds two important provisions on top of v2:")
                    Spacer(Modifier.height(8.dp))
                    CodeBlock(
                        "1. Explicit patent grant + termination:\n" +
                        "   Contributors grant users a patent license covering\n" +
                        "   their contributions. If a licensee sues anyone for\n" +
                        "   patent infringement related to the software, their\n" +
                        "   GPL license terminates automatically.\n" +
                        "\n" +
                        "2. Anti-tivoization:\n" +
                        "   If you distribute GPL v3 software in a device, you\n" +
                        "   must provide the means for users to install modified\n" +
                        "   versions (not just the source code). Prevents the\n" +
                        "   TiVo model of giving source but locking the device\n" +
                        "   with signed firmware checks."
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("Important: many Linux kernel files are explicitly 'GPL-2.0-only'. They cannot be relicensed to v3. Linus Torvalds has stated the kernel will stay on GPL v2. GPL v3 software and GPL v2-only software are license-incompatible and cannot be combined in the same binary.")
                }
                Spacer(Modifier.height(8.dp))
            }
            item {
                SectionCard(title = "LGPL — Lesser GPL") {
                    BodyText("LGPL (Lesser/Library GPL) is weaker copyleft, designed for shared libraries so they can be used from proprietary applications.")
                    Spacer(Modifier.height(8.dp))
                    CodeBlock(
                        "DYNAMIC LINKING from proprietary code -> OK\n" +
                        "  Your application stays proprietary.\n" +
                        "  You just need to allow users to swap in a modified\n" +
                        "  version of the LGPL library (typically satisfied by\n" +
                        "  linking dynamically).\n" +
                        "\n" +
                        "STATIC LINKING or MODIFYING the library itself ->\n" +
                        "  Your modifications to the LGPL code must be released\n" +
                        "  under LGPL. Your application code can stay private.\n" +
                        "\n" +
                        "Notable LGPL projects:\n" +
                        "  glibc (GNU C Library)     — LGPL 2.1\n" +
                        "  Qt framework              — LGPL 3 option\n" +
                        "  GStreamer                  — LGPL 2.1\n" +
                        "  GTK (older versions)       — LGPL"
                    )
                }
                Spacer(Modifier.height(8.dp))
            }
            item {
                SectionCard(title = "MIT License") {
                    BodyText("The MIT License is one of the most permissive and widely used open-source licenses. It has essentially two requirements:")
                    Spacer(Modifier.height(8.dp))
                    CodeBlock(
                        "1. Keep the copyright notice and the license text\n" +
                        "   in all copies or substantial portions of the Software.\n" +
                        "2. That's it.\n" +
                        "\n" +
                        "You can:\n" +
                        "  - Use in commercial products\n" +
                        "  - Incorporate into proprietary closed-source software\n" +
                        "  - Sublicense under any terms\n" +
                        "  - NOT infectious — combined works do NOT become MIT\n" +
                        "\n" +
                        "Notable MIT projects:\n" +
                        "  Node.js, jQuery, React, Ruby on Rails,\n" +
                        "  .NET Core (some parts), many npm packages"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("No patent grant. If a contributor holds a patent that their MIT-licensed code necessarily infringes, they have not explicitly granted you a patent license. Apache 2.0 fixes this.")
                }
                Spacer(Modifier.height(8.dp))
            }
            item {
                SectionCard(title = "Apache 2.0") {
                    BodyText("Apache 2.0 is permissive like MIT but adds an explicit patent grant and a contributor retaliation clause.")
                    Spacer(Modifier.height(8.dp))
                    CodeBlock(
                        "Key terms:\n" +
                        "  - Use, copy, modify, distribute — freely\n" +
                        "  - NOT infectious — proprietary use is fine\n" +
                        "  - PATENT GRANT: each contributor grants you a\n" +
                        "    royalty-free patent license covering their contribution\n" +
                        "  - PATENT RETALIATION: if you sue anyone for patent\n" +
                        "    infringement related to the software, your license\n" +
                        "    terminates\n" +
                        "  - Must preserve copyright notices\n" +
                        "  - Must include the NOTICE file (if present) in\n" +
                        "    distributions\n" +
                        "\n" +
                        "Notable Apache 2.0 projects:\n" +
                        "  Kubernetes, Cilium, Android (userspace),\n" +
                        "  Rust standard library, TensorFlow, Kafka,\n" +
                        "  Hadoop, Spark, Swift"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("Apache 2.0 is the preferred license for corporate open-source because of the explicit patent protection. GPL v2 and Apache 2.0 are considered license-incompatible — you cannot distribute a combined binary that includes both.")
                }
                Spacer(Modifier.height(8.dp))
            }
            item {
                SectionCard(title = "BSD Licenses") {
                    BodyText("The BSD family are permissive licenses originating from the University of California, Berkeley. They are not infectious.")
                    Spacer(Modifier.height(8.dp))
                    CodeBlock(
                        "BSD 2-Clause (Simplified / FreeBSD):\n" +
                        "  1. Keep copyright notice in source distributions.\n" +
                        "  2. Keep copyright notice in binary distributions\n" +
                        "     (in docs or About box).\n" +
                        "\n" +
                        "BSD 3-Clause (New / Revised):\n" +
                        "  Same as 2-clause, plus:\n" +
                        "  3. May NOT use the project name or contributors'\n" +
                        "     names to endorse/promote derived products without\n" +
                        "     written permission.\n" +
                        "\n" +
                        "BSD 0-Clause (Zero-Clause):\n" +
                        "  No conditions at all — effectively public domain.\n" +
                        "\n" +
                        "Notable BSD projects:\n" +
                        "  FreeBSD, OpenBSD, NetBSD kernel & userland\n" +
                        "  LLVM/Clang (Apache 2.0 + LLVM exceptions)\n" +
                        "  Many networking tools (ping, netcat, etc.)"
                    )
                }
                Spacer(Modifier.height(8.dp))
            }
            item {
                SectionCard(title = "AGPL — Affero GPL") {
                    BodyText("AGPL (GNU Affero GPL) adds one critical rule to GPL: network use counts as distribution.")
                    Spacer(Modifier.height(8.dp))
                    CodeBlock(
                        "GPL rule:  You must share source when you DISTRIBUTE\n" +
                        "           a binary. Running GPL software on a server\n" +
                        "           and letting users access it is NOT distribution.\n" +
                        "\n" +
                        "AGPL adds: If users interact with your AGPL software\n" +
                        "           over a NETWORK, you must provide them with\n" +
                        "           the complete corresponding source code.\n" +
                        "           This closes the SaaS loophole.\n" +
                        "\n" +
                        "Result:    A SaaS product built on AGPL code must\n" +
                        "           open-source its entire server-side code.\n" +
                        "\n" +
                        "Notable AGPL projects:\n" +
                        "  MongoDB (was AGPL, now SSPL)\n" +
                        "  Grafana AGPL edition\n" +
                        "  Nextcloud, Mastodon\n" +
                        "  Neo4j Community Edition"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("AGPL v3 is compatible with GPL v3 (you can combine them), but NOT with GPL v2-only code.")
                }
                Spacer(Modifier.height(8.dp))
            }
            item {
                SectionCard(title = "GPL in Practice: Is It Really Infectious?") {
                    BodyText("GPL infectiousness only applies to distribution of combined works. Understand the exact scenarios:")
                    Spacer(Modifier.height(8.dp))
                    CodeBlock(
                        "SCENARIO                          MUST OPEN SOURCE?\n" +
                        "------------------------------------------------------\n" +
                        "Link GPL library into distributed  YES — the combined\n" +
                        "binary (static or dynamic)         binary must be GPL\n" +
                        "\n" +
                        "Use GPL tool at BUILD TIME         NO — GCC, Make etc.\n" +
                        "(compiler, code generator)         have compiler exception\n" +
                        "\n" +
                        "Run GPL software on your server,   NO — not distribution\n" +
                        "users access via network (SaaS)    (AGPL would require YES)\n" +
                        "\n" +
                        "Internal use only (never           NO — distribution\n" +
                        "distributed)                       never occurs\n" +
                        "\n" +
                        "Kernel module using                YES — must be GPL\n" +
                        "EXPORT_SYMBOL_GPL symbols          (enforced at insmod)\n" +
                        "\n" +
                        "Kernel module using                GREY AREA — legal\n" +
                        "EXPORT_SYMBOL symbols only         ambiguity; kernel\n" +
                        "                                   community says GPL\n" +
                        "                                   required; courts\n" +
                        "                                   have not ruled"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("'Distributing source code' means making it available to anyone who receives the binary — not necessarily posting it publicly. You can distribute it privately to the specific recipient of the binary, or offer it for 3 years on written request.")
                }
                Spacer(Modifier.height(8.dp))
            }
            item {
                SectionCard(title = "Quick Reference") {
                    CodeBlock(
                        "License    Copyleft  Infectious  Patent Grant\n" +
                        "-----------------------------------------------\n" +
                        "GPL v2     Strong    YES         No\n" +
                        "GPL v3     Strong    YES         YES\n" +
                        "AGPL v3    Strong    YES+SaaS    YES\n" +
                        "LGPL v2/3  Weak      Library     No/YES\n" +
                        "MPL 2.0    File-lvl  File-only   YES\n" +
                        "Apache 2.0 None      NO          YES\n" +
                        "MIT        None      NO          No\n" +
                        "BSD 2/3    None      NO          No\n" +
                        "BSD 0      None      NO          No"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("'Infectious' in the table means: if you distribute a binary combining your code with this licensed code, must your code also be released under this license? 'YES+SaaS' means yes, even for network service use.")
                }
                Spacer(Modifier.height(8.dp))
            }
        }
    }
}
