package com.example.developmentapp.screens.kotlin

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
fun KotlinReflectionsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Kotlin — Reflections",
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
                SectionCard(title = "What Is Reflection") {
                    BodyText(
                        "Reflection is the ability of a program to inspect and manipulate its own " +
                        "structure at runtime: discover classes, list their properties and functions, " +
                        "call methods by name, and read or write fields without compile-time knowledge " +
                        "of the type.\n\n" +
                        "Kotlin reflection is provided by the kotlin-reflect library, which must be " +
                        "added as a dependency. Without it, only basic Java reflection (via Class<T>) " +
                        "is available.\n\n" +
                        "Typical uses: serialization (Gson, Moshi, kotlinx.serialization), dependency " +
                        "injection (Koin, Hilt), test frameworks, and generic data mapping utilities."
                    )
                    CodeBlock(
                        "// build.gradle.kts\n" +
                        "implementation(\"org.jetbrains.kotlin:kotlin-reflect\")\n" +
                        "\n" +
                        "// Kotlin reflection vs Java reflection\n" +
                        "// KClass<T>  — Kotlin's type descriptor (richer than Class<T>)\n" +
                        "// Class<T>   — Java's type descriptor (always available, less metadata)"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Getting a KClass") {
                    BodyText(
                        "KClass<T> is Kotlin's runtime representation of a class. There are three " +
                        "ways to obtain one:\n\n" +
                        "• ClassName::class — class literal from the type name (compile-time).\n" +
                        "• obj::class — KClass of a specific instance (respects the actual runtime type).\n" +
                        "• obj.javaClass.kotlin — convert a Java Class to KClass.\n\n" +
                        "Key KClass properties: simpleName, qualifiedName, isAbstract, isSealed, " +
                        "isData, objectInstance (for object singletons), supertypes."
                    )
                    CodeBlock(
                        "data class User(val name: String, val age: Int)\n" +
                        "\n" +
                        "val kClass = User::class              // KClass<User>\n" +
                        "println(kClass.simpleName)            // User\n" +
                        "println(kClass.qualifiedName)         // com.example.User\n" +
                        "println(kClass.isData)                // true\n" +
                        "\n" +
                        "val user = User(\"Gil\", 30)\n" +
                        "println(user::class.simpleName)       // User (runtime type)\n" +
                        "\n" +
                        "// From a Java Class\n" +
                        "val fromJava: KClass<User> = user.javaClass.kotlin"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Constructors") {
                    BodyText(
                        "KClass exposes primaryConstructor (may be null for Java classes or " +
                        "object declarations) and constructors (all of them). Each KFunction " +
                        "parameter has a name, type, and isOptional flag. Call a constructor " +
                        "with .call(args...) or use createInstance() for no-arg constructors."
                    )
                    CodeBlock(
                        "import kotlin.reflect.full.*\n" +
                        "\n" +
                        "data class Point(val x: Int, val y: Int)\n" +
                        "\n" +
                        "val ctor = Point::class.primaryConstructor!!\n" +
                        "\n" +
                        "// List parameter names\n" +
                        "ctor.parameters.forEach { println(\"\${it.name}: \${it.type}\") }\n" +
                        "// x: kotlin.Int\n" +
                        "// y: kotlin.Int\n" +
                        "\n" +
                        "// Invoke by position\n" +
                        "val p1 = ctor.call(3, 4)   // Point(x=3, y=4)\n" +
                        "\n" +
                        "// Invoke by name (callBy takes a Map)\n" +
                        "val p2 = ctor.callBy(mapOf(\n" +
                        "    ctor.parameters[0] to 10,\n" +
                        "    ctor.parameters[1] to 20\n" +
                        "))   // Point(x=10, y=20)"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Properties") {
                    BodyText(
                        "memberProperties returns all public properties declared or inherited by the " +
                        "class. declaredMemberProperties returns only those declared directly in the " +
                        "class. Each KProperty1<T, R> has a .get(instance) method. For mutable " +
                        "properties (KMutableProperty1) there is also a .set(instance, value). " +
                        "Private properties require isAccessible = true."
                    )
                    CodeBlock(
                        "import kotlin.reflect.full.*\n" +
                        "\n" +
                        "data class Config(val host: String, var port: Int)\n" +
                        "\n" +
                        "val config = Config(\"localhost\", 8080)\n" +
                        "val kClass = Config::class\n" +
                        "\n" +
                        "// Read all properties\n" +
                        "for (prop in kClass.memberProperties) {\n" +
                        "    println(\"\${prop.name} = \${prop.get(config)}\")\n" +
                        "}\n" +
                        "// host = localhost\n" +
                        "// port = 8080\n" +
                        "\n" +
                        "// Write a mutable property\n" +
                        "val portProp = kClass.memberProperties\n" +
                        "    .filterIsInstance<KMutableProperty1<Config, Int>>()\n" +
                        "    .first { it.name == \"port\" }\n" +
                        "portProp.set(config, 9090)\n" +
                        "println(config.port)   // 9090"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Functions") {
                    BodyText(
                        "memberFunctions returns all public member functions including those inherited " +
                        "from supertypes. declaredMemberFunctions returns only those declared in the " +
                        "class itself. Each KFunction has parameters (first param is the receiver " +
                        "instance for member functions). Use .call(instance, args...) to invoke."
                    )
                    CodeBlock(
                        "import kotlin.reflect.full.*\n" +
                        "\n" +
                        "class Greeter {\n" +
                        "    fun greet(name: String) = \"Hello, \$name!\"\n" +
                        "    fun farewell(name: String) = \"Goodbye, \$name!\"\n" +
                        "}\n" +
                        "\n" +
                        "val greeter = Greeter()\n" +
                        "val kClass  = Greeter::class\n" +
                        "\n" +
                        "// List declared functions\n" +
                        "kClass.declaredMemberFunctions.forEach { println(it.name) }\n" +
                        "// greet, farewell\n" +
                        "\n" +
                        "// Find and call by name\n" +
                        "val fn = kClass.declaredMemberFunctions.first { it.name == \"greet\" }\n" +
                        "println(fn.call(greeter, \"World\"))   // Hello, World!\n" +
                        "\n" +
                        "// Parameter introspection\n" +
                        "fn.parameters.forEach { println(\"\${it.kind}: \${it.name}\") }\n" +
                        "// INSTANCE: null  (the receiver)\n" +
                        "// VALUE:    name"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Annotations") {
                    BodyText(
                        "To read an annotation at runtime it must be retained at runtime: " +
                        "@Retention(AnnotationRetention.RUNTIME). At compile time " +
                        "(SOURCE/BINARY retention) the annotation is stripped and unavailable.\n\n" +
                        "Use findAnnotation<T>() from kotlin.reflect.full to get a single annotation " +
                        "or annotations to get all of them. This works on KClass, KFunction, and " +
                        "KProperty."
                    )
                    CodeBlock(
                        "import kotlin.reflect.full.*\n" +
                        "\n" +
                        "@Retention(AnnotationRetention.RUNTIME)\n" +
                        "@Target(AnnotationTarget.PROPERTY)\n" +
                        "annotation class Validate(val minLength: Int = 0)\n" +
                        "\n" +
                        "data class Form(@Validate(minLength = 3) val username: String,\n" +
                        "               @Validate(minLength = 8) val password: String)\n" +
                        "\n" +
                        "fun validate(form: Form): List<String> {\n" +
                        "    val errors = mutableListOf<String>()\n" +
                        "    for (prop in Form::class.declaredMemberProperties) {\n" +
                        "        val ann = prop.findAnnotation<Validate>() ?: continue\n" +
                        "        val value = prop.get(form) as? String ?: continue\n" +
                        "        if (value.length < ann.minLength)\n" +
                        "            errors += \"\${prop.name} too short (min \${ann.minLength})\"\n" +
                        "    }\n" +
                        "    return errors\n" +
                        "}"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Performance & Caveats") {
                    BodyText(
                        "Reflection is significantly slower than direct calls — typically 10–100× — " +
                        "because the JVM cannot inline, devirtualize, or JIT-optimize reflective " +
                        "invocations as effectively as direct ones.\n\n" +
                        "Guidelines:\n" +
                        "• Cache KClass, KProperty, and KFunction references; don't re-resolve them " +
                        "on every call.\n" +
                        "• Prefer code generation (kapt/ksp, @Parcelize, kotlinx.serialization) over " +
                        "runtime reflection in hot paths.\n" +
                        "• kotlin-reflect adds roughly 2 MB to the APK. If you only need Java " +
                        "reflection, Class<T> is always available without the extra dependency.\n" +
                        "• @JvmField on a property removes the generated getter/setter, letting Java " +
                        "reflection access the backing field directly."
                    )
                    CodeBlock(
                        "// Anti-pattern: re-resolving KClass on every call\n" +
                        "fun serialize(obj: Any): String {\n" +
                        "    return obj::class.memberProperties  // resolved fresh each time\n" +
                        "        .joinToString { \"\${it.name}=\${it.get(obj)}\" }\n" +
                        "}\n" +
                        "\n" +
                        "// Better: cache per class\n" +
                        "private val propCache = mutableMapOf<KClass<*>, List<KProperty1<*, *>>>()\n" +
                        "fun serializeFast(obj: Any): String {\n" +
                        "    val kClass = obj::class\n" +
                        "    val props = propCache.getOrPut(kClass) { kClass.memberProperties.toList() }\n" +
                        "    @Suppress(\"UNCHECKED_CAST\")\n" +
                        "    return props.joinToString {\n" +
                        "        \"\${it.name}=\${(it as KProperty1<Any, *>).get(obj)}\"\n" +
                        "    }\n" +
                        "}"
                    )
                }
            }
            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
