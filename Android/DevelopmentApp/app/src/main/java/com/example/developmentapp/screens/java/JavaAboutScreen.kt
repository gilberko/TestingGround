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
fun JavaAboutScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Java — About Java",
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
                SectionCard(title = "History") {
                    BodyText(
                        "Java was created by James Gosling and his team at Sun Microsystems, starting in " +
                        "1991. It was originally called Oak and was designed for embedded devices such as " +
                        "interactive TV set-top boxes. The language was renamed Java in 1995 and its first " +
                        "public release came in 1996."
                    )
                    BodyText(
                        "Java's defining promise was \"Write Once, Run Anywhere\" — compile your code once " +
                        "and run it on any platform that has a Java Virtual Machine (JVM). This was a major " +
                        "breakthrough in an era where targeting multiple operating systems required " +
                        "maintaining separate codebases."
                    )
                    BodyText(
                        "Sun Microsystems was acquired by Oracle in 2010, and Oracle has maintained Java " +
                        "since then. Java remains one of the most widely-used programming languages in the " +
                        "world, especially for enterprise back-end systems, Android app development, and " +
                        "large-scale server applications."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "How Java Works — Bytecode and the JVM") {
                    BodyText(
                        "Unlike C or C++, Java does not compile directly to native machine code. Instead, " +
                        "the Java compiler (javac) compiles your .java source files into .class files " +
                        "containing Java bytecode — a compact, platform-neutral instruction set that the " +
                        "Java Virtual Machine (JVM) understands."
                    )
                    BodyText(
                        "When you run a program, the JVM reads the bytecode and executes it. Modern JVMs " +
                        "use Just-In-Time (JIT) compilation: they identify hot code paths (frequently " +
                        "executed loops and methods) and compile those to native machine code on the fly, " +
                        "so long-running Java programs can reach performance close to natively compiled " +
                        "languages."
                    )
                    CodeBlock(
                        "# 1. Compile: produces HelloWorld.class\n" +
                        "javac HelloWorld.java\n\n" +
                        "# 2. Run: JVM loads and executes HelloWorld.class\n" +
                        "java HelloWorld"
                    )
                    BodyText(
                        "Because the JVM is available on Windows, Linux, macOS, and many other platforms, " +
                        "the same .class file runs everywhere without recompilation. The JVM handles all " +
                        "platform differences internally."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Garbage Collector") {
                    BodyText(
                        "Java manages memory automatically via a garbage collector (GC) built into the JVM. " +
                        "You allocate objects with new — the JVM tracks all references to each object. When " +
                        "no more references to an object exist (it is unreachable), the GC reclaims its " +
                        "memory. You never call free() or delete."
                    )
                    BodyText(
                        "The JVM offers several GC algorithms. G1GC (Garbage-First) has been the default " +
                        "since Java 9 and balances throughput with pause times. ZGC and Shenandoah are " +
                        "low-latency collectors designed to keep GC pauses under a millisecond, even on " +
                        "multi-gigabyte heaps. You select them with JVM flags:"
                    )
                    CodeBlock(
                        "java -XX:+UseZGC MyApp           # use ZGC\n" +
                        "java -XX:+UseShenandoahGC MyApp  # use Shenandoah\n" +
                        "java -Xmx2g MyApp                # set max heap to 2 GB"
                    )
                    BodyText(
                        "The GC runs in background threads and generally does not stop your application, " +
                        "though short stop-the-world pauses can still occur. For most applications you " +
                        "never need to tune the GC — the defaults work well."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Common IDEs") {
                    BodyText(
                        "IntelliJ IDEA (by JetBrains) is the most popular Java IDE. It offers outstanding " +
                        "code completion, refactoring, debugging, and build-tool integration. The free " +
                        "Community edition covers pure Java and Kotlin development; the Ultimate edition " +
                        "adds support for web frameworks, databases, and Spring."
                    )
                    BodyText(
                        "Eclipse is a long-standing free and open-source IDE that dominated the Java world " +
                        "through the 2000s and 2010s. It remains widely used in enterprise settings and " +
                        "can be extended with a large plugin ecosystem."
                    )
                    BodyText(
                        "NetBeans is another free IDE, now maintained by the Apache Software Foundation. " +
                        "VS Code with the \"Extension Pack for Java\" (Microsoft) provides a lighter-weight " +
                        "option that works well for smaller projects and scripts."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "The JDK") {
                    BodyText(
                        "The JDK (Java Development Kit) is what you install to write and compile Java code. " +
                        "It includes: the javac compiler, the java launcher (JVM), the jar tool (for " +
                        "packaging), the jdb debugger, the javadoc documentation generator, and the full " +
                        "Java Class Library (the standard library of built-in classes)."
                    )
                    BodyText(
                        "The JRE (Java Runtime Environment) is a subset of the JDK — it contains only " +
                        "what is needed to run compiled Java programs, not to compile them. If you just " +
                        "want to run a .jar file, a JRE is enough. If you want to write and build code, " +
                        "install the JDK."
                    )
                    BodyText(
                        "OpenJDK is the open-source reference implementation of Java, maintained by the " +
                        "community. Several distributions package it: Oracle JDK (with commercial support), " +
                        "Amazon Corretto, Eclipse Temurin, and Microsoft Build of OpenJDK. Long-Term " +
                        "Support (LTS) versions receive years of security patches: Java 8, 11, 17, and 21 " +
                        "are the most widely deployed LTS releases."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Running Native Code — JNI") {
                    BodyText(
                        "Java can call C or C++ code via JNI (Java Native Interface). You declare a method " +
                        "with the native keyword — this tells the compiler that the implementation lives " +
                        "outside Java, in a native shared library:"
                    )
                    CodeBlock(
                        "public class NativeExample {\n" +
                        "    // declares a native method — no body here\n" +
                        "    public native int addNumbers(int a, int b);\n\n" +
                        "    static {\n" +
                        "        // load the compiled native library\n" +
                        "        // (mylib.dll on Windows / libmylib.so on Linux)\n" +
                        "        System.loadLibrary(\"mylib\");\n" +
                        "    }\n" +
                        "}"
                    )
                    BodyText(
                        "You implement the method in C or C++, compile it to a .dll (Windows) or .so " +
                        "(Linux/macOS) shared library, and Java loads it at runtime. JNI is used for " +
                        "performance-critical code, hardware access, or wrapping existing C libraries."
                    )
                    BodyText(
                        "JNI requires writing boilerplate C code with specific function-name conventions. " +
                        "JNA (Java Native Access) is a popular higher-level alternative: it lets you call " +
                        "native functions directly from Java without writing any C glue code, by mapping " +
                        "a Java interface to the native library."
                    )
                }
            }
            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
