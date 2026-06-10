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
fun LinuxIdesScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Development IDEs",
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
                SectionCard(title = "JetBrains Overview") {
                    BodyText("JetBrains is a Czech software company (founded 2000, headquartered in Prague) that makes a family of IDEs all built on the same IntelliJ IDEA platform. Because they share a common foundation, all JetBrains IDEs have the same general UI layout, keyboard shortcuts, plugin system, built-in terminal, Git integration, and remote development support — so switching between them feels familiar.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Licensing model: 30-day free trial, then a paid annual subscription (personal or organization). Free for: students and teachers (JetBrains Educational License), open-source project maintainers, and several IDEs have a free Community edition. JetBrains also offers a free tier through their Toolbox App for managing installations.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Key shared features across all JetBrains IDEs:")
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("Deep language intelligence — not just syntax highlighting; full semantic analysis, type inference, go-to-definition, find-all-usages, rename refactoring that updates every reference.")
                    BodyText("Integrated debugger — breakpoints, watches, evaluate expressions, memory view, step into/over/out.")
                    BodyText("Built-in Git — diff viewer, commit, push, pull, merge conflict resolver, blame, log graph.")
                    BodyText("Remote development — JetBrains Gateway lets you connect to a remote Linux server over SSH and run the full IDE backend there while the UI stays local.")
                    BodyText("Plugin ecosystem — thousands of plugins: themes, database tools, frameworks, Docker, Kubernetes, AI assistant.")
                }
                Spacer(modifier = Modifier.height(8.dp))
            }

            item {
                SectionCard(title = "CLion — C and C++") {
                    BodyText("CLion is JetBrains' IDE for C and C++. It is one of the most capable C++ IDEs available on Linux.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Build system support: CMake (first-class), Makefile (via compilation database), Gradle, Meson. For CMake projects, CLion reads CMakeLists.txt and fully understands the project structure.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Code analysis — powered by Clang and CLion's own engine: detects null dereferences, out-of-bounds access, use-after-free, uninitialized variables, and hundreds of other issues inline as you type.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Refactoring — rename symbol (updates every reference across all files), extract function/variable, inline, change function signature, move file (updates includes). Safe, semantics-aware refactoring that a text editor cannot do.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Debugger — integrates GDB and LLDB. Set breakpoints, step into STL containers with pretty-printers, inspect memory as hex dump, view CPU registers, evaluate C++ expressions.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Dynamic analysis integration — run under Valgrind/Memcheck to find leaks, or with AddressSanitizer/ThreadSanitizer. CLion parses the output and highlights the offending lines.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Remote development — define a remote toolchain (SSH to Linux server): source stays local, all compilation and execution happens on the remote machine. Essential when developing for a Linux target from a Windows or macOS workstation.")
                    CodeBlock("""
# Typical CLion CMake workflow:
mkdir build && cd build
cmake ..          # CLion auto-runs this
make              # or ninja; CLion shows output in Build window
# Then: Run > Debug with breakpoints set in editor
                    """.trimIndent())
                }
                Spacer(modifier = Modifier.height(8.dp))
            }

            item {
                SectionCard(title = "GoLand — Go") {
                    BodyText("GoLand is JetBrains' dedicated Go IDE. It provides the most complete Go development experience available in a commercial IDE.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Go modules — full support for go.mod/go.sum, automatic module downloads, vendor directory management, tidy on save.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Goroutine-aware debugger — when paused, you can see all goroutines, switch between them, and inspect their stacks independently. Essential for debugging concurrency issues.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Interface inspector — shows which types implement an interface and which interfaces a type satisfies. Click-through navigation between interface definition and its implementations.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Testing — run/debug individual tests or the entire package from the editor gutter. Benchmarks and fuzz tests supported. Table-driven test generation.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Code analysis — go vet, staticcheck, and GoLand's own inspections run in the background. Suggests idiomatic Go rewrites (e.g., simplify range loop, use errors.Is instead of ==).")
                    CodeBlock("""
# GoLand understands go commands:
go build ./...
go test ./... -race -count=1
go mod tidy
# All runnable from the IDE terminal or via Run Configurations
                    """.trimIndent())
                }
                Spacer(modifier = Modifier.height(8.dp))
            }

            item {
                SectionCard(title = "PyCharm — Python") {
                    BodyText("PyCharm is JetBrains' Python IDE. It comes in two editions:")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Community Edition (free, open-source) — pure Python development: debugger, code completion, refactoring, virtual environment management, pytest/unittest integration.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Professional Edition (paid) — adds: Django/Flask/FastAPI web framework support, database tools (run SQL queries, browse tables), remote development over SSH, Jupyter notebook integration, scientific tools (NumPy/pandas data frame viewer, matplotlib plot viewer).")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Virtual environment management — create, select, and manage venv/conda/pipenv/poetry environments from within the IDE. Each project can have its own interpreter.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Type checking — integrates Mypy and Pyright for static type analysis. Inline errors for type mismatches, missing arguments, wrong return types.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Debugger — step through Python code, inspect variables, evaluate expressions in the console, set conditional breakpoints. Works for scripts, Django servers, and pytest runs.")
                    CodeBlock("""
# PyCharm understands virtual environments:
python -m venv .venv          # creates venv
# PyCharm detects it and offers to use it as the project interpreter
pip install -r requirements.txt  # PyCharm shows outdated packages
                    """.trimIndent())
                }
                Spacer(modifier = Modifier.height(8.dp))
            }

            item {
                SectionCard(title = "RustRover — Rust") {
                    BodyText("Yes, JetBrains has a dedicated Rust IDE. It started as the 'Rust' plugin for IntelliJ IDEA and became the standalone product RustRover in 2023. Currently free for non-commercial use.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Cargo integration — understands Cargo.toml, runs cargo build/test/run/clippy/fmt from the IDE. Workspace support. Dependency graph viewer.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Borrow checker visualization — inline hints show lifetime annotations, ownership transfers, and borrow errors. The IDE highlights exactly why the borrow checker rejects code, with suggested fixes.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Macro expansion — expand proc macros and declarative macros inline to see the generated code. Invaluable for debugging complex macros.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Debugger — GDB and LLDB with Rust-aware pretty-printers for Vec, String, Option, Result, HashMap. Step through async code with tokio support.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("rustfmt and clippy — format on save (rustfmt), run clippy lints on the fly with inline warnings and one-click fixes.")
                    CodeBlock("""
# RustRover Run Configurations:
cargo build --release
cargo test -- --nocapture
cargo clippy -- -D warnings
# All triggered from the IDE gutter or Run menu
                    """.trimIndent())
                }
                Spacer(modifier = Modifier.height(8.dp))
            }

            item {
                SectionCard(title = "Other JetBrains IDEs") {
                    BodyText("IntelliJ IDEA — Java, Kotlin, Scala, Groovy. Community edition is free and covers core Java/Kotlin. Ultimate (paid) adds Spring, Jakarta EE, database tools, and profiler. The original JetBrains IDE that the entire platform is built on.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("WebStorm — JavaScript, TypeScript, Node.js, React, Vue, Angular, Svelte. Deep JS/TS support: flow analysis, auto-import, npm/yarn/pnpm integration. Paid only (no free community edition, but free for students).")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Rider — C#, F#, .NET, Unity, Unreal Engine (C++). Runs on Linux via Mono/.NET runtime. The only first-class C# IDE on Linux besides VSCode.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("DataGrip — database IDE supporting MySQL, PostgreSQL, SQLite, Oracle, SQL Server, MongoDB, Redis, and more. Write and run queries, browse schemas, generate ERDs, refactor SQL.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("PhpStorm — PHP with full framework support (Laravel, Symfony, WordPress).")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("All JetBrains IDEs share the same keymap (Ctrl+Shift+F = Find in Files, Ctrl+Alt+L = reformat code, Shift+Shift = Search Everywhere, Ctrl+B = Go to Definition) and the same plugin marketplace — so a plugin installed in one IDE is available in all.")
                }
                Spacer(modifier = Modifier.height(8.dp))
            }

            item {
                SectionCard(title = "VSCode") {
                    BodyText("Visual Studio Code is Microsoft's free, open-source (MIT license), cross-platform code editor. It is the most popular developer tool in the world by usage surveys. Not a full IDE out of the box — it becomes one through extensions.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Built-in features: IntelliSense (language-aware autocomplete), integrated terminal, Git integration (stage/commit/diff/push/pull from the sidebar), debugger via DAP (Debug Adapter Protocol — same protocol, different language backends), multi-cursor editing, Emmet for HTML/CSS, Zen mode.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Language support via extensions — install from the Extensions panel (Ctrl+Shift+X):")
                    CodeBlock("""
C/C++       — "C/C++" by Microsoft + "clangd" by LLVM
             (clangd provides the best code intelligence via LSP)
Go          — "Go" by Google  (installs gopls, dlv debugger)
Python      — "Python" + "Pylance" by Microsoft
Rust        — "rust-analyzer" by The Rust Foundation
             (essential; provides all rust intelligence via LSP)
Java        — "Extension Pack for Java" by Microsoft
             (wraps Eclipse JDT language server)
                    """.trimIndent())
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Remote Development — the killer feature for Linux developers. Three modes:")
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("Remote - SSH: connect to a Linux machine over SSH. The full editor runs on your local machine, but all files, terminals, and language servers run on the remote. Works seamlessly for kernel development on a remote VM.")
                    BodyText("Dev Containers: open a folder inside a Docker container. The devcontainer.json defines the image and tools; your project runs in an isolated environment.")
                    BodyText("WSL: on Windows, open a folder inside WSL (Windows Subsystem for Linux) with the same seamless experience.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Install VSCode on Linux:")
                    CodeBlock("""
# Debian/Ubuntu:
sudo apt install code   # after adding Microsoft apt repo
# or download .deb from code.visualstudio.com

# Fedora/RHEL:
sudo rpm --import https://packages.microsoft.com/keys/microsoft.asc
sudo dnf install code   # after adding Microsoft dnf repo
# or download .rpm from code.visualstudio.com
                    """.trimIndent())
                }
                Spacer(modifier = Modifier.height(8.dp))
            }

            item {
                SectionCard(title = "JetBrains vs VSCode") {
                    BodyText("Both are excellent choices. The right one depends on your workflow:")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("JetBrains IDEs:")
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("Deep language intelligence out of the box — no configuration, just open a project and everything works: go-to-definition, refactoring, debugger, inspections.")
                    BodyText("Heavier resource usage — ~500 MB–1.5 GB RAM at idle depending on project size. Startup time is slower.")
                    BodyText("Commercial — paid subscription, though free options exist (Community editions, student licenses, RustRover non-commercial).")
                    BodyText("Best for: large codebases in a single language where deep analysis and refactoring matter; Java/Kotlin/C++/Go/Python teams.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("VSCode:")
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("Free and open-source. Lightweight — ~150–300 MB RAM at idle.")
                    BodyText("Extension-dependent — quality varies. The best extensions (rust-analyzer, clangd, Pylance, gopls) are excellent; lesser-known languages may have weaker support.")
                    BodyText("Highly configurable — settings.json, tasks.json, launch.json give full control.")
                    BodyText("Best for: polyglot work (switching between languages), web frontend development, remote SSH workflows, quick edits, teams on tight budgets.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Both support: Language Server Protocol (LSP), DAP debugging, Git, Docker, remote SSH, Vim keybindings (via plugins/extensions), and AI coding assistants (GitHub Copilot, JetBrains AI Assistant).")
                }
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}
