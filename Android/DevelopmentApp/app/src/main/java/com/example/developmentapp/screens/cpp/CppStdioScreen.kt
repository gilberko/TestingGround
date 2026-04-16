package com.example.developmentapp.screens.cpp

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.developmentapp.screens.BodyText
import com.example.developmentapp.screens.CodeBlock
import com.example.developmentapp.screens.SectionCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CppStdioScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "C/C++ — Std I/O & Files",
                        color      = Color(0xFF00FF41),
                        fontFamily = FontFamily.Monospace,
                        fontSize   = 16.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector        = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint               = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Black)
            )
        },
        containerColor = Color.Black
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        ) {
            item { Spacer(Modifier.height(12.dp)) }

            // ── stdio.h ───────────────────────────────────────────────────────
            item {
                SectionCard(title = "stdio.h — The C I/O Library") {
                    BodyText(
                        "<stdio.h> is the C standard I/O library. It wraps OS-level file descriptors " +
                        "with buffered FILE streams, providing a portable layer over platform-specific " +
                        "read/write syscalls. Three standard streams are pre-opened:"
                    )
                    CodeBlock(
                        "stdin   // standard input  (keyboard by default)\n" +
                        "stdout  // standard output (terminal, line-buffered)\n" +
                        "stderr  // standard error  (terminal, unbuffered)"
                    )
                    BodyText(
                        "stdout is typically line-buffered when connected to a terminal and fully " +
                        "buffered when redirected to a file or pipe. stderr is unbuffered — writes " +
                        "appear immediately, which is why error messages go there."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── printf / scanf family ─────────────────────────────────────────
            item {
                SectionCard(title = "printf / scanf Family") {
                    BodyText("Formatted output functions:")
                    CodeBlock(
                        "printf(fmt, ...);              // to stdout\n" +
                        "fprintf(stream, fmt, ...);     // to any FILE*\n" +
                        "sprintf(buf, fmt, ...);        // to char buf — DANGEROUS, no size limit\n" +
                        "snprintf(buf, n, fmt, ...);    // safe: writes at most n-1 chars + '\\0'"
                    )
                    BodyText("Formatted input functions (all arguments must be pointers):")
                    CodeBlock(
                        "scanf(fmt, ...);               // from stdin\n" +
                        "fscanf(stream, fmt, ...);      // from any FILE*\n" +
                        "sscanf(str, fmt, ...);         // from a string\n\n" +
                        "int x; float f; char s[64];\n" +
                        "scanf(\"%d %f %63s\", &x, &f, s);  // read int, float, word"
                    )
                    BodyText("printf format specifiers:")
                    CodeBlock(
                        "%d / %i   signed decimal int\n" +
                        "%u        unsigned decimal int\n" +
                        "%x        unsigned hex, lowercase (e.g. ff)\n" +
                        "%X        unsigned hex, UPPERCASE (e.g. FF)\n" +
                        "%p        pointer address (e.g. 0x7fff1234)\n" +
                        "%s        null-terminated char* string\n" +
                        "%S        wide-char wchar_t* string (Windows/MSVC extension)\n" +
                        "%f        float or double (decimal notation)\n" +
                        "%e / %E   scientific notation\n" +
                        "%g / %G   shorter of %f or %e\n" +
                        "%c        single character\n" +
                        "%zu       size_t (use with sizeof results)\n" +
                        "%n        WRITES chars-printed-so-far into the int* argument"
                    )
                    BodyText(
                        "Width and precision: %10d pads to width 10; %-10d left-aligns; " +
                        "%.4f prints 4 decimal places; %08x pads with zeros."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── %n and format string vulnerabilities ──────────────────────────
            item {
                SectionCard(title = "%n and Format String Vulnerabilities") {
                    BodyText(
                        "%n is a special printf specifier: instead of printing something, it " +
                        "writes the number of characters printed so far into the corresponding int* " +
                        "argument. Most modern C libraries (glibc with _FORTIFY_SOURCE, MSVC) " +
                        "disable %n by default as a security measure."
                    )
                    CodeBlock(
                        "int count;\n" +
                        "printf(\"Hello%n World\\n\", &count);\n" +
                        "// count is now 5 — the length of \"Hello\"\n\n" +
                        "// %n with width specifiers can write larger values:\n" +
                        "int n;\n" +
                        "printf(\"%100d%n\", 0, &n);  // prints 100-wide field; n = 100"
                    )
                    BodyText(
                        "printf reads its extra arguments from the stack (via va_list). If the format " +
                        "string contains more specifiers than arguments provided, printf reads past the " +
                        "intended arguments — reading arbitrary stack values with %d or %x, and " +
                        "writing to arbitrary memory addresses with %n. This is the format string " +
                        "vulnerability class, exploitable when user-controlled input is passed " +
                        "directly as the format string:"
                    )
                    CodeBlock(
                        "// VULNERABLE — never do this:\n" +
                        "char input[256];\n" +
                        "fgets(input, sizeof(input), stdin);\n" +
                        "printf(input);         // if input contains %x%x%n, attacker reads/writes stack\n\n" +
                        "// CORRECT:\n" +
                        "printf(\"%s\", input);  // input is treated as data, not a format string"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Character I/O ─────────────────────────────────────────────────
            item {
                SectionCard(title = "Character and Line I/O") {
                    BodyText("Single-character functions:")
                    CodeBlock(
                        "putchar(c)           // write char c to stdout\n" +
                        "int c = getchar()    // read one char from stdin; returns int (EOF on end)\n\n" +
                        "putc(c, stream)      // write char to any stream; may be a macro\n" +
                        "int c = getc(stream) // read char from any stream; may be a macro\n\n" +
                        "fputc(c, stream)     // guaranteed function (not macro) version of putc\n" +
                        "int c = fgetc(stream)// guaranteed function version of getc"
                    )
                    BodyText("Line functions:")
                    CodeBlock(
                        "puts(str)                         // write str + '\\n' to stdout\n" +
                        "fgets(buf, sizeof(buf), stdin)    // read a line safely — PREFERRED\n\n" +
                        "// gets(buf) — DO NOT USE:\n" +
                        "// Reads until '\\n' or EOF with NO size limit.\n" +
                        "// Any input longer than buf overflows the buffer.\n" +
                        "// Deprecated in C99; REMOVED from the standard in C11."
                    )
                    BodyText(
                        "Why gets is dangerous: it writes bytes into buf until a newline, with no " +
                        "knowledge of buf's size. An attacker supplying a long enough input overwrites " +
                        "the stack beyond buf — clobbering the saved return address and enabling " +
                        "arbitrary code execution. The Morris Worm (1988) used gets. Its removal from " +
                        "C11 was the language standard formally acknowledging the design mistake. " +
                        "Stack canaries, NX (non-executable stack), and ASLR exist largely because " +
                        "of this and similar overflow patterns."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── FILE * ────────────────────────────────────────────────────────
            item {
                SectionCard(title = "FILE * — File I/O") {
                    BodyText(
                        "All C I/O operates through FILE* handles. fopen opens a file and returns " +
                        "a handle; fclose flushes buffers and releases the handle. Always check " +
                        "for NULL on fopen — it returns NULL on failure."
                    )
                    CodeBlock(
                        "// Open modes: \"r\" read, \"w\" write (truncate), \"a\" append\n" +
                        "//              \"rb\",\"wb\",\"ab\" — binary variants\n" +
                        "FILE *f = fopen(\"data.bin\", \"rb\");\n" +
                        "if (!f) { perror(\"fopen\"); return; }\n\n" +
                        "// Text / formatted I/O\n" +
                        "fprintf(f, \"value=%d\\n\", 42);\n" +
                        "char line[256];\n" +
                        "fgets(line, sizeof(line), f);     // read one text line\n\n" +
                        "// Binary I/O\n" +
                        "// fread(ptr, elem_size, count, stream) — returns items read\n" +
                        "uint8_t buf[512];\n" +
                        "size_t n = fread(buf, 1, sizeof(buf), f);\n\n" +
                        "// fwrite(ptr, elem_size, count, stream) — returns items written\n" +
                        "fwrite(buf, 1, n, f);\n\n" +
                        "// Position\n" +
                        "fseek(f, 0, SEEK_SET);   // go to start\n" +
                        "fseek(f, -8, SEEK_END);  // 8 bytes before end\n" +
                        "long pos = ftell(f);     // current byte offset\n" +
                        "rewind(f);               // shorthand for fseek(f, 0, SEEK_SET)\n\n" +
                        "// Status\n" +
                        "if (feof(f))   { /* hit end of file */ }\n" +
                        "if (ferror(f)) { /* I/O error occurred */ }\n\n" +
                        "fclose(f);               // always close when done"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── C++ Streams ───────────────────────────────────────────────────
            item {
                SectionCard(title = "C++ Streams — std::cout, std::cin, std::cerr") {
                    BodyText(
                        "<iostream> provides three pre-opened stream objects: std::cout (stdout), " +
                        "std::cin (stdin), and std::cerr (unbuffered stderr). They use the << and >> " +
                        "operators which can be chained to read or write multiple values in one statement."
                    )
                    CodeBlock(
                        "#include <iostream>\n" +
                        "#include <string>\n\n" +
                        "// Output — chain multiple values with <<\n" +
                        "int age = 30;\n" +
                        "std::string name = \"Alice\";\n" +
                        "std::cout << \"Name: \" << name << \", Age: \" << age << \"\\n\";\n\n" +
                        "// std::endl writes '\\n' AND flushes the buffer.\n" +
                        "// Prefer \"\\n\" for performance; use std::endl only when\n" +
                        "// you need an immediate flush (e.g. before a long delay).\n" +
                        "std::cout << \"Flushing now\" << std::endl;\n\n" +
                        "// Error output — unbuffered, appears immediately\n" +
                        "std::cerr << \"Error: file not found\\n\";\n\n" +
                        "// Input — chain multiple reads with >>\n" +
                        "// >> skips leading whitespace and reads until the next whitespace\n" +
                        "int x; double d;\n" +
                        "std::cin >> x >> d;   // reads two values separated by whitespace\n\n" +
                        "// Reading a full line (including spaces):\n" +
                        "std::string line;\n" +
                        "std::getline(std::cin, line);"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── C++ File Streams ──────────────────────────────────────────────
            item {
                SectionCard(title = "C++ File Streams") {
                    BodyText(
                        "<fstream> provides file stream classes that work exactly like cout/cin but " +
                        "operate on files: std::ofstream (write), std::ifstream (read), std::fstream " +
                        "(both). They close automatically when they go out of scope (RAII)."
                    )
                    CodeBlock(
                        "#include <fstream>\n" +
                        "#include <string>\n\n" +
                        "// Writing a file\n" +
                        "std::ofstream out(\"output.txt\");\n" +
                        "if (!out.is_open()) { /* handle error */ }\n" +
                        "out << \"Hello \" << 42 << \"\\n\";\n" +
                        "out.close();   // optional — destructor closes it\n\n" +
                        "// Reading a file line by line\n" +
                        "std::ifstream in(\"input.txt\");\n" +
                        "std::string line;\n" +
                        "while (std::getline(in, line)) {\n" +
                        "    std::cout << line << \"\\n\";\n" +
                        "}\n\n" +
                        "// Read/write — std::fstream\n" +
                        "std::fstream f(\"data.txt\", std::ios::in | std::ios::out);\n" +
                        "f.seekg(0, std::ios::end);    // seek get-pointer to end\n" +
                        "std::streampos size = f.tellg();\n\n" +
                        "// Status checks\n" +
                        "in.good()   // no errors, not at EOF\n" +
                        "in.fail()   // format/logic error\n" +
                        "in.eof()    // reached end of file\n" +
                        "in.bad()    // I/O hardware error"
                    )
                }
            }
            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
