package com.example.developmentapp.screens.java

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
fun JavaPackagesScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Java — Packages",
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
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "What Is a Package?") {
                    BodyText(
                        "A package is a named group of related classes and interfaces — a namespace " +
                        "that keeps code organized and prevents name collisions. Every class in Java " +
                        "belongs to exactly one package. Two classes with the same name can coexist in " +
                        "different packages: java.util.Date and java.sql.Date are distinct types that " +
                        "live side by side without conflict."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Declaring a Package") {
                    BodyText(
                        "The package statement must be the very first non-comment statement in a .java " +
                        "file. If the class is in com.example.myapp, its source file must live under " +
                        "the directory path com/example/myapp/ — the folder structure mirrors the " +
                        "package name exactly."
                    )
                    CodeBlock(
                        "// File: com/example/myapp/Greeter.java\n" +
                        "package com.example.myapp;\n\n" +
                        "public class Greeter {\n" +
                        "    public void hello() {\n" +
                        "        System.out.println(\"Hello!\");\n" +
                        "    }\n" +
                        "}"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "The Default Package") {
                    BodyText(
                        "If a .java file has no package declaration, its class lives in the unnamed " +
                        "default package. Classes in the default package cannot be imported by classes " +
                        "in any named package — there is no import statement that can reach them. " +
                        "This is fine for quick single-file scripts; avoid it in any real project."
                    )
                    CodeBlock(
                        "// No package declaration → default package\n" +
                        "public class Quick {\n" +
                        "    public static void main(String[] args) {\n" +
                        "        System.out.println(\"Quick script\");\n" +
                        "    }\n" +
                        "}"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Package-Private Access") {
                    BodyText(
                        "A field or method with no access modifier has package-private (also called " +
                        "\"default\") access. It is visible to all classes in the same package and " +
                        "invisible to everything outside. This is the same level described in the " +
                        "Access Modifiers section of Classes 101."
                    )
                    CodeBlock(
                        "package com.example.zoo;\n\n" +
                        "class Animal {           // package-private class\n" +
                        "    String name;         // package-private field\n" +
                        "    void breathe() {}    // package-private method\n" +
                        "}\n\n" +
                        "// Another class in com.example.zoo can access all of these.\n" +
                        "// A class in com.example.farm cannot — compile error."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Naming Convention and the Hierarchy Illusion") {
                    BodyText(
                        "The convention is reverse domain name in all lowercase: com.example.myapp, " +
                        "org.apache.commons.lang. The dots look like a folder hierarchy but they are " +
                        "purely a naming convention. com.example is NOT a parent of com.example.util — " +
                        "they are completely independent packages. There is no automatic visibility " +
                        "between them just because one name starts with the other."
                    )
                    CodeBlock(
                        "// com.example  and  com.example.util  are unrelated packages.\n" +
                        "// A class in com.example gets no special access to\n" +
                        "// package-private members of com.example.util, and vice versa.\n\n" +
                        "// The dots are just a naming convention — not folder containment."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Common Standard Library Packages") {
                    BodyText(
                        "java.lang            — auto-imported; String, Integer, Math, System, Object, Thread\n" +
                        "java.util            — ArrayList, HashMap, LinkedList, Scanner, Random, Optional\n" +
                        "java.io              — File, InputStream, OutputStream, BufferedReader, PrintWriter\n" +
                        "java.nio             — Path, Paths, Files, ByteBuffer, channels\n" +
                        "java.net             — URL, Socket, ServerSocket, HttpURLConnection\n" +
                        "java.time            — LocalDate, LocalDateTime, ZonedDateTime, Duration (Java 8+)\n" +
                        "java.math            — BigInteger, BigDecimal\n" +
                        "java.util.concurrent — ExecutorService, Future, ConcurrentHashMap"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Importing Classes") {
                    BodyText(
                        "import places a single class name in scope for the file. The wildcard form " +
                        "imports all public classes in one package but does NOT pull in sub-packages: " +
                        "import java.util.* does not include java.util.concurrent. java.lang is the " +
                        "only package imported automatically. If two packages have a class with the " +
                        "same name, you must fully qualify at least one."
                    )
                    CodeBlock(
                        "import java.util.ArrayList;    // single class import\n" +
                        "import java.util.*;             // all public classes in java.util\n" +
                        "                               // does NOT include java.util.concurrent\n\n" +
                        "// Disambiguating two Date classes:\n" +
                        "import java.util.Date;          // util.Date in scope by short name\n" +
                        "// ...elsewhere in file:\n" +
                        "java.sql.Date sqlDate = ...;    // sql.Date must be fully qualified"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "import static") {
                    BodyText(
                        "import static brings a class's static members (fields and methods) into scope " +
                        "so you can use their short name without the class prefix. Handy when you " +
                        "call the same static utilities repeatedly; less readable in general code where " +
                        "knowing the origin of a name matters."
                    )
                    CodeBlock(
                        "import static java.lang.Math.PI;\n" +
                        "import static java.lang.Math.sqrt;\n" +
                        "// or bring in all Math statics:\n" +
                        "import static java.lang.Math.*;\n\n" +
                        "double circumference = 2 * PI * r;   // no Math. prefix\n" +
                        "double hyp = sqrt(a*a + b*b);        // no Math. prefix"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "No \"using namespace\" Equivalent") {
                    BodyText(
                        "Java has no equivalent to C++'s \"using namespace std;\". The closest is a " +
                        "wildcard import (import java.util.*;) but it covers only one package at a time, " +
                        "applies only to the current file, and creates no namespace alias. There is no " +
                        "import-as rename or global namespace-opening mechanism in Java."
                    )
                    CodeBlock(
                        "// C++ (not Java):\n" +
                        "// using namespace std;   ← opens entire namespace globally\n\n" +
                        "// Java — closest equivalent:\n" +
                        "import java.util.*;  // one package, this file only, no alias\n\n" +
                        "// No alias or rename is possible — use the short name\n" +
                        "// after import, or fully qualify where needed."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Using Classes Without import") {
                    BodyText(
                        "You can always refer to a class by its fully qualified name and skip import " +
                        "entirely. Useful when two same-named classes are needed in one file, or when " +
                        "you want the type's origin to be explicit at the point of use."
                    )
                    CodeBlock(
                        "// No import — use fully qualified name inline:\n" +
                        "java.util.ArrayList<String> list = new java.util.ArrayList<>();\n\n" +
                        "// Both Date classes in one file without any import trick:\n" +
                        "java.util.Date utilDate = new java.util.Date();\n" +
                        "java.sql.Date  sqlDate  = new java.sql.Date(0);"
                    )
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
