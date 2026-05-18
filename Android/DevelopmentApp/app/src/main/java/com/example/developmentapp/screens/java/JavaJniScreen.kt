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
fun JavaJniScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Java — JNI",
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
                SectionCard(title = "What Is JNI?") {
                    BodyText(
                        "Java Native Interface (JNI) is the bridge between JVM-managed Java code and " +
                        "native C/C++ code. Use it when you need to:\n\n" +
                        "• Call OS-specific DLLs (Windows) or .so libraries (Linux/macOS)\n" +
                        "• Wrap an existing C/C++ library from Java\n" +
                        "• Perform low-level hardware or OS access not exposed by the Java API\n" +
                        "• Squeeze performance from a hot path in native code\n\n" +
                        "JNI works in both directions: Java can call native functions, and native " +
                        "functions can call back into Java using the same JNIEnv API."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Declaring a Native Method") {
                    BodyText(
                        "The native keyword marks a method that has no body in Java — its " +
                        "implementation will be provided by a loaded native library. Native methods " +
                        "can be instance or static, and accept and return any JNI-compatible type."
                    )
                    CodeBlock(
                        "public class MathLib {\n" +
                        "    public native int    add(int a, int b);\n" +
                        "    public native String greet(String name);\n" +
                        "    public static native void init();\n" +
                        "}"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Loading the Library") {
                    BodyText(
                        "Call System.loadLibrary() in a static initializer so the native code is " +
                        "loaded before any native method is first called. Pass the library name " +
                        "without the platform-specific prefix or extension:\n\n" +
                        "• Windows: loads mathlib.dll\n" +
                        "• Linux:   loads libmathlib.so  (lib prefix is added automatically)\n\n" +
                        "The static block runs once when the JVM first loads the class."
                    )
                    CodeBlock(
                        "public class MathLib {\n" +
                        "    static {\n" +
                        "        System.loadLibrary(\"mathlib\");\n" +
                        "        // Windows → mathlib.dll\n" +
                        "        // Linux   → libmathlib.so\n" +
                        "    }\n" +
                        "    public native int add(int a, int b);\n" +
                        "}"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "JNI Function Naming") {
                    BodyText(
                        "The C/C++ function name must match a strict convention so the JVM can " +
                        "locate it at runtime:\n\n" +
                        "    Java_<ClassName>_<methodName>\n" +
                        "    Java_<pkg_ClassName>_<methodName>  (dots in package → underscores)\n\n" +
                        "An underscore inside a class or method name becomes _1. Use javac -h . " +
                        "on your .java file to auto-generate a C/C++ header with the correct " +
                        "signatures — don't guess the mangled names by hand."
                    )
                    CodeBlock(
                        "// Java: class MathLib, method add  (no package)\n" +
                        "Java_MathLib_add\n\n" +
                        "// Java: package com.example, class MathLib, method add\n" +
                        "Java_com_example_MathLib_add\n\n" +
                        "// Underscore in method name (get_value → _1)\n" +
                        "Java_MathLib_get_1value\n\n" +
                        "// Generate the header automatically:\n" +
                        "// javac -h . MathLib.java"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "JNIEnv and Hidden Parameters") {
                    BodyText(
                        "Every native function receives two implicit parameters prepended by the JVM:\n\n" +
                        "JNIEnv* env  — pointer to the JVM's JNI function table. This is the gateway " +
                        "to everything: creating Java strings, reading/writing object fields, calling " +
                        "Java methods from native code, handling exceptions, and more.\n\n" +
                        "jobject thiz — for instance methods, the Java object that called the method " +
                        "(equivalent to \"this\" in Java).\n" +
                        "jclass cls   — for static methods, the Class object."
                    )
                    CodeBlock(
                        "// Instance method:\n" +
                        "JNIEXPORT jint JNICALL\n" +
                        "Java_MathLib_add(JNIEnv* env, jobject thiz, jint a, jint b) { ... }\n\n" +
                        "// Static method:\n" +
                        "JNIEXPORT void JNICALL\n" +
                        "Java_MathLib_init(JNIEnv* env, jclass cls) { ... }"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Primitive Types") {
                    BodyText(
                        "JNI defines C typedef aliases for every Java primitive. They map directly to " +
                        "the underlying C/C++ types and can be used in native code as-is — no " +
                        "conversion or special handling needed."
                    )
                    CodeBlock(
                        "jboolean  →  uint8_t   (JNI_TRUE / JNI_FALSE)\n" +
                        "jbyte     →  int8_t\n" +
                        "jchar     →  uint16_t  (UTF-16 code unit)\n" +
                        "jshort    →  int16_t\n" +
                        "jint      →  int32_t\n" +
                        "jlong     →  int64_t\n" +
                        "jfloat    →  float\n" +
                        "jdouble   →  double\n\n" +
                        "// Return directly — no conversion:\n" +
                        "JNIEXPORT jint JNICALL\n" +
                        "Java_MathLib_add(JNIEnv* env, jobject thiz, jint a, jint b) {\n" +
                        "    return a + b;   // jint arithmetic works like int\n" +
                        "}"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Strings") {
                    BodyText(
                        "Java strings arrive as jstring — a JVM object reference, not a char*. You " +
                        "must use JNI API calls to convert between jstring and a C string. Always " +
                        "release what you get; failing to release leaks JVM resources.\n\n" +
                        "GetStringUTFChars / ReleaseStringUTFChars use Modified UTF-8, which handles " +
                        "all BMP characters correctly (equivalent to standard UTF-8 for common text)."
                    )
                    CodeBlock(
                        "// Reading a jstring passed from Java:\n" +
                        "JNIEXPORT void JNICALL\n" +
                        "Java_MathLib_greet(JNIEnv* env, jobject thiz, jstring name) {\n" +
                        "    const char* chars = env->GetStringUTFChars(name, nullptr);\n" +
                        "    printf(\"Hello, %s!\\n\", chars);\n" +
                        "    env->ReleaseStringUTFChars(name, chars);  // always release\n" +
                        "}\n\n" +
                        "// Returning a new Java string:\n" +
                        "JNIEXPORT jstring JNICALL\n" +
                        "Java_MathLib_getVersion(JNIEnv* env, jobject thiz) {\n" +
                        "    return env->NewStringUTF(\"1.0.0\");\n" +
                        "}"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Objects") {
                    BodyText(
                        "Java objects arrive as jobject. Accessing their fields requires a JNI " +
                        "reflection-style API: look up a field or method ID once (can cache it), then " +
                        "read or write through that ID. Type descriptor strings follow the JVM type " +
                        "signature format: I=int, J=long, D=double, Z=boolean, Ljava/lang/String; " +
                        "for String, [I for int[]."
                    )
                    CodeBlock(
                        "// Reading a field from a Java object:\n" +
                        "jclass   cls = env->GetObjectClass(obj);\n" +
                        "jfieldID fid = env->GetFieldID(cls, \"score\", \"I\");  // \"I\" = int\n" +
                        "jint   score = env->GetIntField(obj, fid);\n\n" +
                        "// Writing a field:\n" +
                        "env->SetIntField(obj, fid, score + 10);\n\n" +
                        "// String field:\n" +
                        "jfieldID nameFid = env->GetFieldID(cls, \"name\",\n" +
                        "                                   \"Ljava/lang/String;\");\n" +
                        "jobject nameStr  = env->GetObjectField(obj, nameFid);"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Full Example") {
                    BodyText(
                        "Step 1 — Java side: declare native methods and load the library."
                    )
                    CodeBlock(
                        "// MathLib.java\n" +
                        "public class MathLib {\n" +
                        "    static { System.loadLibrary(\"mathlib\"); }\n\n" +
                        "    public native int add(int a, int b);\n\n" +
                        "    public static void main(String[] args) {\n" +
                        "        MathLib ml = new MathLib();\n" +
                        "        System.out.println(\"3 + 4 = \" + ml.add(3, 4));\n" +
                        "    }\n" +
                        "}"
                    )
                    BodyText(
                        "Step 2 — Generate the C/C++ header (run once after any signature change):"
                    )
                    CodeBlock(
                        "javac -h . MathLib.java\n" +
                        "// generates MathLib.h with the correct function signature"
                    )
                    BodyText(
                        "Step 3 — C++ implementation:"
                    )
                    CodeBlock(
                        "// MathLib.cpp\n" +
                        "#include <jni.h>\n" +
                        "#include \"MathLib.h\"\n\n" +
                        "extern \"C\" {\n" +
                        "JNIEXPORT jint JNICALL\n" +
                        "Java_MathLib_add(JNIEnv* env, jobject thiz, jint a, jint b) {\n" +
                        "    return a + b;\n" +
                        "}\n" +
                        "}"
                    )
                    BodyText(
                        "Step 4 — Build the native library:"
                    )
                    CodeBlock(
                        "// Windows (MSVC):\n" +
                        "cl /LD MathLib.cpp /I\"%JAVA_HOME%\\include\" \\\n" +
                        "       /I\"%JAVA_HOME%\\include\\win32\" /Fe:mathlib.dll\n\n" +
                        "// Linux (GCC):\n" +
                        "g++ -shared -fPIC MathLib.cpp \\\n" +
                        "    -I${'$'}JAVA_HOME/include \\\n" +
                        "    -I${'$'}JAVA_HOME/include/linux \\\n" +
                        "    -o libmathlib.so"
                    )
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
