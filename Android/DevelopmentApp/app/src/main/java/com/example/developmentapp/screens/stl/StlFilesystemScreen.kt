package com.example.developmentapp.screens.stl

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
fun StlFilesystemScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "C++ — STL Filesystem",
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
        LazyColumn(modifier = Modifier.padding(innerPadding).padding(horizontal = 16.dp)) {
            item { Spacer(Modifier.height(16.dp)) }

            item {
                SectionCard(title = "What Is <filesystem>?") {
                    BodyText("<filesystem> was standardised in C++17. It provides portable, type-safe wrappers for file system operations — paths, directory iteration, file metadata, copy, rename, remove, and more.")
                    BodyText("Everything lives in the std::filesystem namespace. It is common to alias it:")
                    CodeBlock(
                        "#include <filesystem>\n" +
                        "namespace fs = std::filesystem;\n" +
                        "\n" +
                        "// Compile with -std=c++17 (GCC/Clang also need -lstdc++fs on older versions)"
                    )
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard(title = "Paths and the / Operator") {
                    BodyText("fs::path represents a file system path. It understands both forward slashes and backslashes (on Windows). You can construct paths from strings and combine them with the / operator, which appends a path component with the correct separator.")
                    CodeBlock(
                        "fs::path p1 = \"/home/user\";\n" +
                        "fs::path p2 = p1 / \"documents\" / \"report.txt\";\n" +
                        "// p2 == \"/home/user/documents/report.txt\"\n" +
                        "\n" +
                        "// Works on Windows too:\n" +
                        "fs::path p3 = \"C:/Users/Alice\";\n" +
                        "fs::path p4 = p3 / \"Desktop\" / \"file.txt\";\n" +
                        "// p4 == \"C:\\\\Users\\\\Alice\\\\Desktop\\\\file.txt\" (native separator)\n" +
                        "\n" +
                        "// Decomposing a path\n" +
                        "fs::path f = \"/home/user/report.txt\";\n" +
                        "f.filename()      // \"report.txt\"\n" +
                        "f.stem()          // \"report\" (filename without extension)\n" +
                        "f.extension()     // \".txt\"\n" +
                        "f.parent_path()   // \"/home/user\"\n" +
                        "f.root_path()     // \"/\" (or \"C:\\\\\" on Windows)\n" +
                        "\n" +
                        "// Convert to string\n" +
                        "std::string s = f.string();      // portable\n" +
                        "std::string n = f.native();      // OS-native encoding"
                    )
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard(title = "Checking Existence and Type") {
                    BodyText("Free functions answer the most common questions about a path. They return bool and do not throw by default when the path simply doesn't exist (they return false). They CAN throw filesystem_error for genuine I/O errors (e.g., permission denied).")
                    CodeBlock(
                        "fs::path p = \"/home/user/documents\";\n" +
                        "\n" +
                        "fs::exists(p)            // true if path exists (any type)\n" +
                        "fs::is_regular_file(p)   // true if it's a plain file\n" +
                        "fs::is_directory(p)      // true if it's a directory\n" +
                        "fs::is_symlink(p)        // true if it's a symbolic link\n" +
                        "fs::is_empty(p)          // true if file has zero bytes OR directory has no entries\n" +
                        "\n" +
                        "// Example guard before reading a file\n" +
                        "if (!fs::exists(p) || !fs::is_regular_file(p)) {\n" +
                        "    std::cerr << \"not a file: \" << p << '\\n';\n" +
                        "    return;\n" +
                        "}"
                    )
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard(title = "File Metadata") {
                    BodyText("fs::file_size returns the size in bytes (uintmax_t). fs::last_write_time returns the last modification time as a file_time_type (a std::chrono time_point). fs::status returns a file_status object containing the type and permissions.")
                    CodeBlock(
                        "fs::path f = \"data.bin\";\n" +
                        "\n" +
                        "// Size in bytes\n" +
                        "uintmax_t bytes = fs::file_size(f);   // throws if f is a directory\n" +
                        "std::cout << bytes << \" bytes\\n\";\n" +
                        "\n" +
                        "// Last write time\n" +
                        "auto lwt = fs::last_write_time(f);    // fs::file_time_type\n" +
                        "// Convert to system_clock for printing (C++20 makes this easier)\n" +
                        "auto sctp = std::chrono::time_point_cast<std::chrono::system_clock::duration>(\n" +
                        "    lwt - fs::file_time_type::clock::now()\n" +
                        "    + std::chrono::system_clock::now());\n" +
                        "std::time_t tt = std::chrono::system_clock::to_time_t(sctp);\n" +
                        "std::cout << std::ctime(&tt);\n" +
                        "\n" +
                        "// Status — type and permissions\n" +
                        "fs::file_status st = fs::status(f);\n" +
                        "st.type();        // fs::file_type::regular / directory / symlink / …\n" +
                        "st.permissions(); // fs::perms bitmask (owner_read, group_write, …)\n" +
                        "\n" +
                        "// Hard link count\n" +
                        "uintmax_t links = fs::hard_link_count(f);"
                    )
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard(title = "Directory Iteration") {
                    BodyText("fs::directory_iterator iterates over the immediate entries of a directory (one level only). fs::recursive_directory_iterator descends into subdirectories automatically.")
                    BodyText("Each element is a fs::directory_entry. It caches commonly queried attributes (is_regular_file, is_directory, file_size, last_write_time) to avoid extra stat() calls on repeated access.")
                    CodeBlock(
                        "// Non-recursive — one level only\n" +
                        "for (const fs::directory_entry& entry : fs::directory_iterator(\"/home/user\")) {\n" +
                        "    const fs::path& p = entry.path();\n" +
                        "\n" +
                        "    if (entry.is_regular_file()) {\n" +
                        "        std::cout << \"FILE  \" << p.filename()\n" +
                        "                  << \"  (\" << entry.file_size() << \" bytes)\\n\";\n" +
                        "    } else if (entry.is_directory()) {\n" +
                        "        std::cout << \"DIR   \" << p.filename() << '\\n';\n" +
                        "    } else if (entry.is_symlink()) {\n" +
                        "        std::cout << \"LINK  \" << p.filename() << '\\n';\n" +
                        "    }\n" +
                        "}\n" +
                        "\n" +
                        "// Recursive — walks the entire tree\n" +
                        "for (const auto& entry : fs::recursive_directory_iterator(\"/home/user\")) {\n" +
                        "    std::cout << entry.path() << '\\n';\n" +
                        "}\n" +
                        "\n" +
                        "// Filter: only .txt files recursively\n" +
                        "for (const auto& entry : fs::recursive_directory_iterator(\".\")) {\n" +
                        "    if (entry.is_regular_file() && entry.path().extension() == \".txt\") {\n" +
                        "        std::cout << entry.path() << '\\n';\n" +
                        "    }\n" +
                        "}"
                    )
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard(title = "Common File Operations") {
                    BodyText("The filesystem library provides high-level operations for copying, moving, creating, and deleting files and directories.")
                    CodeBlock(
                        "// Create directories\n" +
                        "fs::create_directory(\"newdir\");           // one level; fails if exists\n" +
                        "fs::create_directories(\"a/b/c\");          // like mkdir -p; ok if exists\n" +
                        "\n" +
                        "// Copy\n" +
                        "fs::copy_file(\"src.txt\", \"dst.txt\");      // copies a single file\n" +
                        "fs::copy(\"srcdir\", \"dstdir\",\n" +
                        "    fs::copy_options::recursive);           // copy whole tree\n" +
                        "\n" +
                        "// Rename / move\n" +
                        "fs::rename(\"old.txt\", \"new.txt\");\n" +
                        "\n" +
                        "// Remove\n" +
                        "fs::remove(\"file.txt\");                    // file or empty directory\n" +
                        "uintmax_t n = fs::remove_all(\"tmpdir\");    // recursive; returns count removed\n" +
                        "\n" +
                        "// Current working directory\n" +
                        "fs::path cwd = fs::current_path();\n" +
                        "fs::current_path(\"/tmp\");                  // change cwd\n" +
                        "\n" +
                        "// Temp directory\n" +
                        "fs::path tmp = fs::temp_directory_path();   // /tmp on Linux, %TEMP% on Windows"
                    )
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard(title = "Error Handling — Exceptions vs Error Codes") {
                    BodyText("Every filesystem function has two overloads:")
                    BodyText("1. Throwing — takes no error_code. Throws std::filesystem::filesystem_error on failure. Use try/catch for robust code.")
                    BodyText("2. Non-throwing — takes a std::error_code& as the last argument. Sets ec on failure; never throws. Use this in performance-sensitive or hot-path code to avoid exception overhead.")
                    BodyText("std::filesystem::filesystem_error inherits from std::system_error. It carries path1() and path2() (the paths involved) and code() (the OS error code).")
                    CodeBlock(
                        "// ── Throwing overload ──\n" +
                        "try {\n" +
                        "    fs::copy_file(\"src.txt\", \"dst.txt\");\n" +
                        "    uintmax_t sz = fs::file_size(\"dst.txt\");\n" +
                        "    std::cout << \"copied \" << sz << \" bytes\\n\";\n" +
                        "}\n" +
                        "catch (const fs::filesystem_error& e) {\n" +
                        "    // e.what()  — human-readable message\n" +
                        "    // e.path1() — first path involved\n" +
                        "    // e.path2() — second path (for two-path operations)\n" +
                        "    // e.code()  — std::error_code (OS error number)\n" +
                        "    std::cerr << \"fs error: \" << e.what() << '\\n';\n" +
                        "    std::cerr << \"path: \"     << e.path1() << '\\n';\n" +
                        "}\n" +
                        "catch (const std::exception& e) {\n" +
                        "    std::cerr << \"other error: \" << e.what() << '\\n';\n" +
                        "}\n" +
                        "\n" +
                        "// ── Non-throwing overload ── (preferred in tight loops)\n" +
                        "std::error_code ec;\n" +
                        "uintmax_t sz = fs::file_size(\"file.txt\", ec);\n" +
                        "if (ec) {\n" +
                        "    std::cerr << \"error: \" << ec.message() << '\\n';\n" +
                        "} else {\n" +
                        "    std::cout << sz << \" bytes\\n\";\n" +
                        "}\n" +
                        "\n" +
                        "// exists() / is_regular_file() never throw on a missing path;\n" +
                        "// they only throw on an actual I/O error (e.g., permission denied).\n" +
                        "// Check existence before querying metadata to avoid surprises:\n" +
                        "if (fs::exists(p) && fs::is_regular_file(p)) {\n" +
                        "    auto size = fs::file_size(p); // safe\n" +
                        "}"
                    )
                    BodyText("Common exceptions that can be thrown: filesystem_error (from any fs operation on failure), std::bad_alloc (memory exhaustion when constructing paths/strings). In practice, wrapping filesystem operations in a single try/catch(const std::exception&) block is sufficient for most applications.")
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
