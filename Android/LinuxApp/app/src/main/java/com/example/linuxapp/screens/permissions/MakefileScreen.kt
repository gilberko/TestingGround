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
fun MakefileScreen(onBack: () -> Unit) {
    Scaffold(
        containerColor = Color.Black,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Makefile",
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

            // Section 7: Complete Example
            item {
                SectionCard(title = "Complete Example") {
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

            item { Spacer(Modifier.height(16.dp)) }
        }
    }
}
