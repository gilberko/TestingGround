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
fun KotlinExceptionsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Kotlin — Exceptions",
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
                SectionCard(title = "Throwing Exceptions") {
                    BodyText(
                        "Exceptions are thrown with the throw keyword. Kotlin uses the same exception " +
                        "hierarchy as Java — all exceptions are subclasses of Throwable, the two main " +
                        "branches being Exception and Error.\n\n" +
                        "throw is an expression in Kotlin — it has type Nothing, meaning you can use " +
                        "throw in any expression context (e.g. in an Elvis operator or when arm)."
                    )
                    CodeBlock(
                        "// Throw a standard exception:\n" +
                        "throw IllegalArgumentException(\"Age must be positive\")\n" +
                        "throw NullPointerException()\n" +
                        "throw RuntimeException(\"Something went wrong\")\n\n" +
                        "// Custom exception (subclass Exception):\n" +
                        "class ValidationException(message: String) : Exception(message)\n\n" +
                        "fun validate(value: Int) {\n" +
                        "    if (value < 0) throw ValidationException(\"Value \$value is negative\")\n" +
                        "}\n\n" +
                        "// throw in an expression (Elvis operator):\n" +
                        "val name: String = getName() ?: throw IllegalStateException(\"Name required\")\n\n" +
                        "// throw in when:\n" +
                        "val result = when (status) {\n" +
                        "    \"ok\"  -> processData()\n" +
                        "    else  -> throw IllegalStateException(\"Unknown status: \$status\")\n" +
                        "}"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "try-catch") {
                    BodyText(
                        "try-catch in Kotlin works the same as Java. You can catch multiple exception " +
                        "types in separate catch blocks. The first matching catch block is executed.\n\n" +
                        "Catch the most specific exception types first — if a broader type appears " +
                        "first, the more specific ones below it will never match."
                    )
                    CodeBlock(
                        "// Basic try-catch:\n" +
                        "try {\n" +
                        "    val n = \"abc\".toInt()  // throws NumberFormatException\n" +
                        "} catch (e: NumberFormatException) {\n" +
                        "    println(\"Not a number: \${e.message}\")\n" +
                        "}\n\n" +
                        "// Multiple catch blocks:\n" +
                        "try {\n" +
                        "    val list = listOf<Int>()\n" +
                        "    val value = list[0]       // throws IndexOutOfBoundsException\n" +
                        "} catch (e: IndexOutOfBoundsException) {\n" +
                        "    println(\"Index error: \${e.message}\")\n" +
                        "} catch (e: Exception) {\n" +
                        "    println(\"General error: \${e.message}\")\n" +
                        "}\n\n" +
                        "// Catching a parent type catches all subclasses:\n" +
                        "try {\n" +
                        "    riskyOperation()\n" +
                        "} catch (e: Exception) {\n" +
                        "    println(\"Caught: \${e::class.simpleName}: \${e.message}\")\n" +
                        "}"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "finally") {
                    BodyText(
                        "The finally block always runs after try and any catch — whether an exception " +
                        "was thrown or not, and even if no catch block matched. Use it for cleanup " +
                        "that must always happen (closing resources, releasing locks).\n\n" +
                        "Note: for resource cleanup Kotlin's use { } extension is usually more " +
                        "idiomatic than a manually written finally block."
                    )
                    CodeBlock(
                        "var connection: Connection? = null\n" +
                        "try {\n" +
                        "    connection = openConnection()\n" +
                        "    connection.execute(query)\n" +
                        "} catch (e: DatabaseException) {\n" +
                        "    println(\"DB error: \${e.message}\")\n" +
                        "} finally {\n" +
                        "    connection?.close()   // always runs, even if exception was thrown\n" +
                        "    println(\"Connection closed\")\n" +
                        "}\n\n" +
                        "// finally runs even when try succeeds:\n" +
                        "try {\n" +
                        "    println(\"try\")\n" +
                        "} catch (e: Exception) {\n" +
                        "    println(\"catch\")    // not reached\n" +
                        "} finally {\n" +
                        "    println(\"finally\")  // always reached\n" +
                        "}\n" +
                        "// Output: try, finally\n\n" +
                        "// Idiomatic Kotlin — use { } replaces try/finally for Closeable:\n" +
                        "File(\"data.txt\").bufferedReader().use { reader ->\n" +
                        "    println(reader.readLine())\n" +
                        "}  // close() guaranteed, no finally needed"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "No Checked Exceptions") {
                    BodyText(
                        "Kotlin has NO checked exceptions — all exceptions are unchecked.\n\n" +
                        "In Java, some exceptions (IOException, SQLException, etc.) are 'checked': " +
                        "the compiler forces you to either catch them or declare them with throws. " +
                        "This creates a lot of boilerplate and often leads to swallowed exceptions.\n\n" +
                        "In Kotlin, there are no throws declarations. You can call a function that " +
                        "throws IOException without any try-catch — the compiler does not require it. " +
                        "You can still catch exceptions; the language just doesn't force you to.\n\n" +
                        "When calling Java methods that declare checked exceptions from Kotlin, you " +
                        "can catch them but are not required to. The @Throws annotation in Kotlin " +
                        "is used when writing Kotlin code that will be called from Java — it adds " +
                        "the throws declaration to the generated bytecode so Java callers see it."
                    )
                    CodeBlock(
                        "// Kotlin — no throws declarations needed:\n" +
                        "fun readFile(path: String): String {\n" +
                        "    return File(path).readText()  // may throw IOException\n" +
                        "}                                 // no 'throws IOException' required\n\n" +
                        "// Java equivalent would require:\n" +
                        "// public String readFile(String path) throws IOException { ... }\n\n" +
                        "// You can still catch if you want:\n" +
                        "try {\n" +
                        "    val text = readFile(\"/etc/passwd\")\n" +
                        "} catch (e: java.io.IOException) {\n" +
                        "    println(\"File error: \${e.message}\")\n" +
                        "}\n\n" +
                        "// @Throws — for Java interop:\n" +
                        "@Throws(IOException::class)\n" +
                        "fun writeFile(path: String, content: String) {\n" +
                        "    File(path).writeText(content)\n" +
                        "}\n" +
                        "// Java code: writeFile(path, text);  — sees 'throws IOException'"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "try as an Expression") {
                    BodyText(
                        "In Kotlin, try is an expression — it produces a value. The value is the " +
                        "last expression in the try block if no exception was thrown, or the last " +
                        "expression in the catch block if one was caught.\n\n" +
                        "This lets you cleanly assign the result of a risky operation with a fallback " +
                        "value, without an explicit intermediate variable."
                    )
                    CodeBlock(
                        "// try as an expression — assign the result:\n" +
                        "val number: Int = try {\n" +
                        "    \"42\".toInt()           // succeeds → number = 42\n" +
                        "} catch (e: NumberFormatException) {\n" +
                        "    -1                      // fallback value\n" +
                        "}\n" +
                        "println(number)   // 42\n\n" +
                        "val bad: Int = try {\n" +
                        "    \"abc\".toInt()          // throws\n" +
                        "} catch (e: NumberFormatException) {\n" +
                        "    -1                      // caught → bad = -1\n" +
                        "}\n" +
                        "println(bad)   // -1\n\n" +
                        "// With finally — finally does not contribute to the value:\n" +
                        "val result = try {\n" +
                        "    getData()\n" +
                        "} catch (e: Exception) {\n" +
                        "    null\n" +
                        "} finally {\n" +
                        "    cleanup()   // runs, but its return value is ignored\n" +
                        "}"
                    )
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
