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
fun KotlinNullSafetyScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Kotlin — Null Safety",
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
                SectionCard(title = "The Problem") {
                    BodyText(
                        "In Java any reference can be null, and calling a method on a null reference " +
                        "throws a NullPointerException at runtime — the infamous NPE. Kotlin moves null " +
                        "checks to compile time by making nullability part of the type system. A type " +
                        "that can hold null is written T? (e.g. String?); a type that cannot is just T " +
                        "(e.g. String). The compiler rejects code that could dereference a null without " +
                        "a guard, so most NPEs become compilation errors instead of runtime crashes."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Nullable Types") {
                    BodyText(
                        "String and String? are different types. You cannot assign null to a String " +
                        "variable or call .length on a String? without handling the null case first."
                    )
                    CodeBlock(
                        "val a: String  = \"hello\"   // never null\n" +
                        "val b: String? = null       // may be null\n" +
                        "\n" +
                        "println(a.length)           // OK\n" +
                        "println(b.length)           // compile error: b may be null\n" +
                        "\n" +
                        "// Nullability lives in the type, not in a runtime flag.\n" +
                        "// Int? boxes the primitive; Int stays unboxed on the JVM."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Safe Call ?.") {
                    BodyText(
                        "The safe-call operator ?. calls a method or accesses a property only if the " +
                        "receiver is non-null. If it is null the entire expression evaluates to null " +
                        "instead of throwing. Chains are short-circuited: if any link is null the " +
                        "whole chain returns null."
                    )
                    CodeBlock(
                        "val city: String? = user?.address?.city\n" +
                        "// city is null if user is null OR address is null\n" +
                        "\n" +
                        "val upper: String? = name?.uppercase()   // null if name is null\n" +
                        "\n" +
                        "// Works on function calls too\n" +
                        "listOrNull?.forEach { println(it) }"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Elvis Operator ?:") {
                    BodyText(
                        "The Elvis operator ?: provides a default value when the left-hand side is null. " +
                        "The right-hand side can be any expression — including return or throw — which " +
                        "makes it a concise early-exit mechanism."
                    )
                    CodeBlock(
                        "val name: String = user?.name ?: \"Anonymous\"\n" +
                        "\n" +
                        "// Early return from a function\n" +
                        "fun process(input: String?) {\n" +
                        "    val value = input ?: return\n" +
                        "    println(value.length)   // value is non-null here\n" +
                        "}\n" +
                        "\n" +
                        "// Throw on null\n" +
                        "val id = userId ?: throw IllegalStateException(\"No user id\")"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Non-Null Assertion !!") {
                    BodyText(
                        "The !! operator asserts that a value is not null. If it is null, " +
                        "KotlinNullPointerException is thrown at runtime. This re-introduces the " +
                        "danger that null safety is designed to prevent. Use it only when you have " +
                        "external knowledge the compiler cannot see, and prefer structural guarantees " +
                        "(smart casts, requireNotNull) over !! in production code."
                    )
                    CodeBlock(
                        "val len = str!!.length   // throws KotlinNPE if str is null\n" +
                        "\n" +
                        "// Acceptable in tests where a null is a test failure anyway\n" +
                        "val result = findUser(42)!!\n" +
                        "\n" +
                        "// Better alternative:\n" +
                        "val result2 = requireNotNull(findUser(42)) { \"user 42 not found\" }"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "let with Nullables") {
                    BodyText(
                        "Combining ?. with let executes a block only when the receiver is non-null. " +
                        "Inside the block the parameter it (or a named param) is smart-cast to the " +
                        "non-null type, so no further null checks are needed."
                    )
                    CodeBlock(
                        "val email: String? = getEmail()\n" +
                        "\n" +
                        "email?.let { e ->\n" +
                        "    println(e.uppercase())   // e is String, not String?\n" +
                        "    sendEmail(e)\n" +
                        "}\n" +
                        "\n" +
                        "// With it (implicit parameter name)\n" +
                        "name?.let { println(it.length) }\n" +
                        "\n" +
                        "// let also returns the last expression\n" +
                        "val len: Int? = email?.let { it.length }"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Smart Casts") {
                    BodyText(
                        "After a null check the compiler narrows the type automatically. Inside the " +
                        "checked branch the variable is treated as non-null without any extra syntax. " +
                        "Smart casts also work with is checks for subtypes."
                    )
                    CodeBlock(
                        "fun printLength(s: String?) {\n" +
                        "    if (s != null) {\n" +
                        "        println(s.length)   // s is String inside this block\n" +
                        "    }\n" +
                        "}\n" +
                        "\n" +
                        "// Works with && too\n" +
                        "if (s != null && s.length > 0) { /* s is non-null */ }\n" +
                        "\n" +
                        "// when expressions\n" +
                        "when {\n" +
                        "    s == null -> println(\"null\")\n" +
                        "    else      -> println(s.length)  // s is non-null here\n" +
                        "}\n" +
                        "\n" +
                        "// Smart cast requires the var to be immutable (val) or a local var\n" +
                        "// that is not captured by a lambda between the check and use."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Safe Cast as?") {
                    BodyText(
                        "The as? operator casts to a type and returns null if the object is not of " +
                        "that type, instead of throwing ClassCastException. Combine with ?: to " +
                        "provide a fallback."
                    )
                    CodeBlock(
                        "val animal: Any = Cat()\n" +
                        "\n" +
                        "val dog: Dog? = animal as? Dog   // null, not an exception\n" +
                        "val cat: Cat? = animal as? Cat   // Cat instance\n" +
                        "\n" +
                        "// Combine with Elvis\n" +
                        "val dogName = (animal as? Dog)?.name ?: \"not a dog\"\n" +
                        "\n" +
                        "// Regular cast throws ClassCastException on mismatch\n" +
                        "val forcedDog = animal as Dog    // throws if animal is not Dog"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Platform Types (Java Interop)") {
                    BodyText(
                        "When calling Java code, Kotlin cannot determine nullability from the Java " +
                        "type alone. Such types are called platform types and written T! in error " +
                        "messages. The compiler allows both nullable and non-null treatment, passing " +
                        "the responsibility to the developer. Adding @Nullable or @NotNull (from " +
                        "JetBrains, AndroidX, or JSR-305) to Java code resolves the ambiguity and " +
                        "lets Kotlin enforce the correct contract."
                    )
                    CodeBlock(
                        "// Java method with no annotation:\n" +
                        "// public String getName() { ... }\n" +
                        "\n" +
                        "// Kotlin sees: getName(): String!  (platform type)\n" +
                        "val n1: String  = obj.name   // allowed, but risky\n" +
                        "val n2: String? = obj.name   // safe, treat as nullable\n" +
                        "\n" +
                        "// Java method with annotation:\n" +
                        "// @Nullable public String getName() { ... }\n" +
                        "// Kotlin now enforces: getName(): String?\n" +
                        "\n" +
                        "// Android SDK is fully annotated — most APIs surface correct nullability."
                    )
                }
            }
            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
