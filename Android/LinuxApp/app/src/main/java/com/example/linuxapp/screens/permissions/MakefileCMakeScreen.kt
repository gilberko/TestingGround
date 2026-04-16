package com.example.linuxapp.screens.permissions

import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.linuxapp.screens.kernel.BodyText
import com.example.linuxapp.screens.kernel.CodeBlock
import com.example.linuxapp.screens.kernel.SectionCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MakefileCMakeScreen(onBack: () -> Unit) {
    Scaffold(
        containerColor = Color.Black,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Makefile and CMake",
                        color = Color(0xFF00FF41),
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Black)
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 12.dp)
        ) {
            item { Spacer(Modifier.height(8.dp)) }

            // ── MAKEFILE ──────────────────────────────────────────────────────

            // Section 1: What Is a Makefile?
            item {
                SectionCard(title = "What Is a Makefile?") {
                    BodyText(
                        "A Makefile is a script read by GNU Make — a build-automation tool. " +
                        "It describes which files depend on which other files, and what commands to run " +
                        "when those dependencies change."
                    )
                    Spacer(Modifier.height(6.dp))
                    BodyText(
                        "The key benefit: Make only recompiles files that have actually changed, " +
                        "not the entire project. In large C/C++ projects this saves significant time."
                    )
                    Spacer(Modifier.height(6.dp))
                    BodyText("To invoke Make, simply run:")
                    CodeBlock("make")
                    BodyText(
                        "Make looks for a file named 'Makefile' (or 'makefile') in the current directory " +
                        "and executes the first target it finds."
                    )
                }
            }

            // Section 2: Basic Syntax
            item {
                SectionCard(title = "Basic Syntax") {
                    BodyText(
                        "A Makefile rule has three parts: a target, its dependencies (prerequisites), " +
                        "and the recipe (commands to run). The recipe MUST be indented with a real TAB " +
                        "character — spaces will not work."
                    )
                    CodeBlock(
                        "target: dependency1 dependency2\n" +
                        "\tcommand_to_run\n" +
                        "\tanother_command"
                    )
                    BodyText("A minimal working example:")
                    CodeBlock(
                        "hello: hello.c\n" +
                        "\tgcc -o hello hello.c"
                    )
                    BodyText(
                        "Running 'make' will execute 'gcc -o hello hello.c' only if hello.c is newer " +
                        "than the 'hello' binary (or if 'hello' doesn't exist yet)."
                    )
                }
            }

            // Section 3: Running Make
            item {
                SectionCard(title = "Running Make") {
                    BodyText("Build the default target (the first one in the file):")
                    CodeBlock("make")
                    BodyText("Build a specific named target:")
                    CodeBlock("make clean\nmake install\nmake debug")
                    BodyText("Use a different Makefile (not the default 'Makefile'):")
                    CodeBlock("make -f MyOtherMakefile\nmake -f MyOtherMakefile clean")
                    BodyText("Run in a different directory:")
                    CodeBlock("make -C /path/to/project")
                    BodyText("Show commands without executing them (dry run):")
                    CodeBlock("make -n")
                    BodyText("Force rebuild even if files are up to date:")
                    CodeBlock("make -B")
                }
            }

            // Section 4: Variables
            item {
                SectionCard(title = "Variables") {
                    BodyText(
                        "Variables reduce repetition. By convention, compiler and flag variables " +
                        "are uppercase. Reference a variable with \$(VAR_NAME)."
                    )
                    CodeBlock(
                        "CC      = gcc\n" +
                        "CFLAGS  = -Wall -Wextra -g\n" +
                        "TARGET  = myapp\n\n" +
                        "\$(TARGET): main.c utils.c\n" +
                        "\t\$(CC) \$(CFLAGS) -o \$(TARGET) main.c utils.c"
                    )
                    BodyText("Assignment flavours:")
                    CodeBlock(
                        "CC = gcc         # Simple assignment (evaluated each use)\n" +
                        "CC := gcc        # Immediate assignment (evaluated once at definition)\n" +
                        "CC ?= gcc        # Assign only if CC is not already set\n" +
                        "CFLAGS += -g     # Append to existing value"
                    )
                    BodyText("Override a variable from the command line:")
                    CodeBlock("make CC=clang CFLAGS=\"-O2\"")
                }
            }

            // Section 5: Pattern Rules & Automatic Variables
            item {
                SectionCard(title = "Pattern Rules & Automatic Variables") {
                    BodyText(
                        "Pattern rules let you write one rule that applies to many files. " +
                        "The '%' acts as a wildcard matching any string."
                    )
                    CodeBlock(
                        "# Compile every .c file into a .o file\n" +
                        "%.o: %.c\n" +
                        "\t\$(CC) \$(CFLAGS) -c \$< -o \$@"
                    )
                    BodyText("Key automatic variables available inside a recipe:")
                    CodeBlock(
                        "\$@   # The target name (e.g. main.o)\n" +
                        "\$<   # The first prerequisite (e.g. main.c)\n" +
                        "\$^   # All prerequisites (e.g. main.c utils.c)\n" +
                        "\$*   # The stem matched by '%' (e.g. 'main' from main.o)"
                    )
                    BodyText("Linking all objects into the final binary:")
                    CodeBlock(
                        "OBJS = main.o utils.o\n\n" +
                        "myapp: \$(OBJS)\n" +
                        "\t\$(CC) -o \$@ \$^"
                    )
                }
            }

            // Section 6: Phony Targets
            item {
                SectionCard(title = "Phony Targets") {
                    BodyText(
                        "Targets that don't produce a file (like 'clean' or 'all') are called phony. " +
                        "Declare them with .PHONY so Make doesn't confuse them with actual files."
                    )
                    CodeBlock(
                        ".PHONY: all clean install\n\n" +
                        "all: myapp\n\n" +
                        "clean:\n" +
                        "\trm -f \$(OBJS) myapp\n\n" +
                        "install: myapp\n" +
                        "\tcp myapp /usr/local/bin/"
                    )
                    BodyText(
                        "Without .PHONY: if a file named 'clean' existed in the directory, " +
                        "'make clean' would do nothing because Make would see the file as up to date."
                    )
                }
            }

            // Section 7: Makefile Complete Example
            item {
                SectionCard(title = "Makefile — Complete Example") {
                    BodyText(
                        "A multi-file C project: main.c and utils.c compiled into the 'app' binary. " +
                        "Each .c file is first compiled to a .o object, then linked."
                    )
                    BodyText("Project layout:")
                    CodeBlock(
                        ".\n" +
                        "├── Makefile\n" +
                        "├── main.c\n" +
                        "├── utils.c\n" +
                        "└── utils.h"
                    )
                    BodyText("Makefile:")
                    CodeBlock(
                        "CC      = gcc\n" +
                        "CFLAGS  = -Wall -Wextra -g\n" +
                        "TARGET  = app\n" +
                        "OBJS    = main.o utils.o\n\n" +
                        ".PHONY: all clean\n\n" +
                        "all: \$(TARGET)\n\n" +
                        "\$(TARGET): \$(OBJS)\n" +
                        "\t\$(CC) \$(CFLAGS) -o \$@ \$^\n\n" +
                        "%.o: %.c\n" +
                        "\t\$(CC) \$(CFLAGS) -c \$< -o \$@\n\n" +
                        "# utils.o also depends on the header\n" +
                        "utils.o: utils.c utils.h\n\n" +
                        "clean:\n" +
                        "\trm -f \$(OBJS) \$(TARGET)"
                    )
                    BodyText("Build and clean:")
                    CodeBlock(
                        "make          # builds 'app'\n" +
                        "make clean    # removes app, main.o, utils.o\n" +
                        "make -j4      # parallel build using 4 jobs"
                    )
                }
            }

            // ── CMAKE ─────────────────────────────────────────────────────────

            // Section 8: What Is CMake?
            item {
                SectionCard(title = "What Is CMake?") {
                    BodyText(
                        "CMake is a cross-platform meta-build system. It does not compile code directly — " +
                        "instead it reads CMakeLists.txt files and generates native build files: " +
                        "Makefiles on Linux, Ninja build files, Visual Studio projects on Windows, etc."
                    )
                    Spacer(Modifier.height(6.dp))
                    BodyText(
                        "Why use CMake over a raw Makefile?\n" +
                        "• Scales better for large multi-directory projects\n" +
                        "• Handles dependency detection automatically\n" +
                        "• Cross-platform: same CMakeLists.txt builds on Linux, macOS, Windows\n" +
                        "• Rich ecosystem of find-modules for third-party libraries\n" +
                        "• Supports out-of-source builds (build dir separate from source)"
                    )
                }
            }

            // Section 9: CMakeLists.txt Placement
            item {
                SectionCard(title = "CMakeLists.txt Placement") {
                    BodyText(
                        "Every directory that contributes source files gets its own CMakeLists.txt. " +
                        "The top-level CMakeLists.txt bootstraps the project and pulls in sub-directories " +
                        "with add_subdirectory()."
                    )
                    CodeBlock(
                        "project/\n" +
                        "├── CMakeLists.txt     <- top-level (project root)\n" +
                        "├── mylib/\n" +
                        "│   ├── CMakeLists.txt <- defines the shared library\n" +
                        "│   ├── mylib.c\n" +
                        "│   └── mylib.h\n" +
                        "└── myapp/\n" +
                        "    ├── CMakeLists.txt <- defines the executable\n" +
                        "    └── main.c"
                    )
                }
            }

            // Section 10: Essential CMake Commands
            item {
                SectionCard(title = "Essential CMake Commands") {
                    BodyText("Set the minimum CMake version and declare the project:")
                    CodeBlock(
                        "cmake_minimum_required(VERSION 3.16)\n" +
                        "project(MyProject VERSION 1.0 LANGUAGES C)"
                    )
                    BodyText("Define a shared library target from source files:")
                    CodeBlock(
                        "add_library(mylib SHARED\n" +
                        "    mylib.c\n" +
                        ")"
                    )
                    BodyText("Define an executable target:")
                    CodeBlock(
                        "add_executable(myapp\n" +
                        "    main.c\n" +
                        ")"
                    )
                    BodyText("Link a library to a target (also implies build-order dependency):")
                    CodeBlock("target_link_libraries(myapp PRIVATE mylib)")
                    BodyText("Add include directories to a target:")
                    CodeBlock(
                        "target_include_directories(mylib PUBLIC\n" +
                        "    \${CMAKE_CURRENT_SOURCE_DIR}\n" +
                        ")"
                    )
                    BodyText("PUBLIC vs PRIVATE vs INTERFACE:")
                    CodeBlock(
                        "PRIVATE   # Only this target uses the setting\n" +
                        "PUBLIC    # This target AND targets that link to it\n" +
                        "INTERFACE # Only targets that link to it (not this target itself)"
                    )
                }
            }

            // Section 11: Build Workflow
            item {
                SectionCard(title = "Build Workflow (Out-of-Source)") {
                    BodyText(
                        "Never run cmake inside the source directory. Use a separate 'build' folder " +
                        "so generated files don't pollute your source tree."
                    )
                    CodeBlock(
                        "# From the project root:\n" +
                        "mkdir build\n" +
                        "cd build\n" +
                        "cmake ..           # configure; generate Makefiles\n" +
                        "make               # compile everything\n" +
                        "make install       # install (if install() rules are defined)"
                    )
                    BodyText("Useful cmake flags at configure time:")
                    CodeBlock(
                        "# Choose build type (Debug adds -g, Release adds -O3)\n" +
                        "cmake .. -DCMAKE_BUILD_TYPE=Release\n\n" +
                        "# Use Ninja instead of Make (faster)\n" +
                        "cmake .. -G Ninja\n" +
                        "ninja\n\n" +
                        "# Install to a custom prefix\n" +
                        "cmake .. -DCMAKE_INSTALL_PREFIX=/opt/myapp"
                    )
                    BodyText("Rebuild after source changes:")
                    CodeBlock(
                        "# Inside the build directory:\n" +
                        "make          # incremental rebuild\n" +
                        "make -j\$(nproc)  # parallel rebuild"
                    )
                }
            }

            // Section 12: Shared Library + Executable Example
            item {
                SectionCard(title = "CMake — Shared Library + Executable") {
                    BodyText("Full CMakeLists.txt files for the layout shown in section 9.")
                    Spacer(Modifier.height(4.dp))
                    BodyText("project/CMakeLists.txt (top-level):")
                    CodeBlock(
                        "cmake_minimum_required(VERSION 3.16)\n" +
                        "project(MyProject LANGUAGES C)\n\n" +
                        "# Order matters for visibility, but target_link_libraries\n" +
                        "# handles actual build-order automatically.\n" +
                        "add_subdirectory(mylib)\n" +
                        "add_subdirectory(myapp)"
                    )
                    BodyText("project/mylib/CMakeLists.txt:")
                    CodeBlock(
                        "add_library(mylib SHARED\n" +
                        "    mylib.c\n" +
                        ")\n\n" +
                        "# Make mylib.h visible to targets that link mylib\n" +
                        "target_include_directories(mylib PUBLIC\n" +
                        "    \${CMAKE_CURRENT_SOURCE_DIR}\n" +
                        ")"
                    )
                    BodyText("project/myapp/CMakeLists.txt:")
                    CodeBlock(
                        "add_executable(myapp\n" +
                        "    main.c\n" +
                        ")\n\n" +
                        "# Link to mylib — CMake automatically:\n" +
                        "#  1. Adds mylib's PUBLIC include dirs to myapp\n" +
                        "#  2. Ensures mylib is built before myapp\n" +
                        "#  3. Passes -lmylib to the linker\n" +
                        "target_link_libraries(myapp PRIVATE mylib)"
                    )
                    BodyText("Build:")
                    CodeBlock(
                        "mkdir build && cd build\n" +
                        "cmake ..\n" +
                        "make\n" +
                        "# Result:\n" +
                        "#   build/mylib/libmylib.so\n" +
                        "#   build/myapp/myapp"
                    )
                }
            }

            // Section 13: Explicit Build Dependencies
            item {
                SectionCard(title = "Explicit Build Dependencies") {
                    BodyText(
                        "target_link_libraries() already implies that mylib must be built before myapp. " +
                        "You rarely need to add an explicit dependency, but you can with add_dependencies()."
                    )
                    CodeBlock(
                        "# Explicit dependency (usually not needed when using\n" +
                        "# target_link_libraries — shown here for completeness)\n" +
                        "add_dependencies(myapp mylib)"
                    )
                    BodyText(
                        "Use add_dependencies() when one target needs another to finish first " +
                        "but doesn't actually link to it — for example, a code-generator that " +
                        "produces source files that a second target compiles."
                    )
                    Spacer(Modifier.height(6.dp))
                    BodyText("Build order summary:")
                    CodeBlock(
                        "target_link_libraries(A PRIVATE B)\n" +
                        "# => B is compiled first, then A\n" +
                        "# => -lB is passed when linking A\n" +
                        "# => B's PUBLIC include dirs are added to A\n\n" +
                        "add_dependencies(A B)\n" +
                        "# => B is built first, then A\n" +
                        "# => No automatic include or link flags"
                    )
                }
            }

            // Section 14: Using a Pre-built .so + Headers
            item {
                SectionCard(title = "Using a Pre-built .so + Headers") {
                    BodyText(
                        "If you already have a compiled shared library (e.g. libfoo.so) and its " +
                        "header files, tell CMake about them using an IMPORTED target. No recompilation " +
                        "of the library happens — CMake just wires up the paths."
                    )
                    CodeBlock(
                        "# Declare an imported target for the pre-built library\n" +
                        "add_library(extfoo SHARED IMPORTED)\n\n" +
                        "# Point CMake at the actual .so file\n" +
                        "set_target_properties(extfoo PROPERTIES\n" +
                        "    IMPORTED_LOCATION /path/to/libfoo.so\n" +
                        ")\n\n" +
                        "# Add the directory containing foo.h\n" +
                        "target_include_directories(myapp PRIVATE\n" +
                        "    /path/to/include\n" +
                        ")\n\n" +
                        "# Link your target against the imported library\n" +
                        "target_link_libraries(myapp PRIVATE extfoo)"
                    )
                    BodyText("Alternatively, use find_library() to locate the .so automatically:")
                    CodeBlock(
                        "# Search for libfoo.so in standard system paths or HINTS\n" +
                        "find_library(FOO_LIB\n" +
                        "    NAMES foo\n" +
                        "    HINTS /custom/lib /usr/local/lib\n" +
                        ")\n\n" +
                        "if(NOT FOO_LIB)\n" +
                        "    message(FATAL_ERROR \"libfoo not found\")\n" +
                        "endif()\n\n" +
                        "target_include_directories(myapp PRIVATE /path/to/include)\n" +
                        "target_link_libraries(myapp PRIVATE \${FOO_LIB})"
                    )
                    BodyText(
                        "After building, if libfoo.so is not in a standard system path, set " +
                        "LD_LIBRARY_PATH so the dynamic linker can find it at runtime:"
                    )
                    CodeBlock(
                        "export LD_LIBRARY_PATH=/path/to:${'$'}LD_LIBRARY_PATH\n" +
                        "./myapp"
                    )
                }
            }

            item { Spacer(Modifier.height(16.dp)) }
        }
    }
}
