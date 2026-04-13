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
fun CMakeScreen(onBack: () -> Unit) {
    Scaffold(
        containerColor = Color.Black,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "CMake",
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

            // Section 1: What Is CMake?
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

            // Section 2: CMakeLists.txt Placement
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

            // Section 3: Essential CMake Commands
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

            // Section 4: Build Workflow
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

            // Section 5: Shared Library + Executable Example
            item {
                SectionCard(title = "Example: Shared Library + Executable") {
                    BodyText("Full CMakeLists.txt files for the layout shown in section 2.")
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

            // Section 6: Explicit Build Dependencies
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

            // Section 7: Using a Pre-built .so + Headers
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
