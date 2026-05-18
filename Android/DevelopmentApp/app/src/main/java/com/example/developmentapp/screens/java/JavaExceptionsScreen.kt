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
fun JavaExceptionsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Java — Exceptions",
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
                SectionCard(title = "The Throwable Hierarchy") {
                    BodyText(
                        "In Java, only objects that are instances of Throwable (or a subclass) can be " +
                        "thrown. You cannot throw a primitive, a String, or an arbitrary object — unlike " +
                        "C++ where you can throw anything.\n\n" +
                        "Throwable has two direct subclasses:\n\n" +
                        "  Error — JVM-level problems (OutOfMemoryError, StackOverflowError). You " +
                        "generally should NOT catch these; they signal that the JVM is in a state it " +
                        "cannot recover from.\n\n" +
                        "  Exception — application-level problems. This splits further:\n" +
                        "    RuntimeException (and subclasses) — unchecked; no declaration required.\n" +
                        "    All other Exception subclasses — checked; compiler enforces handling.\n\n" +
                        "Common unchecked: NullPointerException, IllegalArgumentException,\n" +
                        "  ArrayIndexOutOfBoundsException, ClassCastException\n" +
                        "Common checked:   IOException, SQLException, ParseException"
                    )
                    CodeBlock(
                        "// The hierarchy (simplified)\n" +
                        "//\n" +
                        "//  Throwable\n" +
                        "//  ├── Error                     ← don't catch (JVM fatal)\n" +
                        "//  │   ├── OutOfMemoryError\n" +
                        "//  │   └── StackOverflowError\n" +
                        "//  └── Exception\n" +
                        "//      ├── RuntimeException      ← unchecked (no throws needed)\n" +
                        "//      │   ├── NullPointerException\n" +
                        "//      │   ├── IllegalArgumentException\n" +
                        "//      │   └── ArrayIndexOutOfBoundsException\n" +
                        "//      ├── IOException           ← checked (must declare or catch)\n" +
                        "//      └── SQLException          ← checked\n\n" +
                        "// Custom exception — extend RuntimeException for unchecked:\n" +
                        "class InsufficientFundsException extends RuntimeException {\n" +
                        "    InsufficientFundsException(double amount) {\n" +
                        "        super(\"Need \" + amount + \" more\");\n" +
                        "    }\n" +
                        "}"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "How to Throw an Exception") {
                    BodyText(
                        "Use the throw keyword followed by a Throwable instance. You almost always " +
                        "construct a new exception object at the throw site. The exception object " +
                        "carries a message (readable via getMessage()), an optional cause (another " +
                        "exception that triggered this one), and the stack trace captured at the " +
                        "moment of construction."
                    )
                    CodeBlock(
                        "void setAge(int age) {\n" +
                        "    if (age < 0) {\n" +
                        "        throw new IllegalArgumentException(\"age must be >= 0, got: \" + age);\n" +
                        "    }\n" +
                        "    this.age = age;\n" +
                        "}\n\n" +
                        "void withdraw(double amount) throws InsufficientFundsException {\n" +
                        "    if (amount > balance) {\n" +
                        "        throw new InsufficientFundsException(amount - balance);\n" +
                        "    }\n" +
                        "    balance -= amount;\n" +
                        "}\n\n" +
                        "// You can throw any Throwable, including Error (though you should not):\n" +
                        "// throw new Error(\"fatal\");  // valid but unusual\n\n" +
                        "// The stack trace is captured when the object is constructed:\n" +
                        "Throwable e = new RuntimeException(\"oops\");  // trace captured here\n" +
                        "// ... some time later ...\n" +
                        "throw e;  // thrown here, but trace points to the construction site"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "How to Catch an Exception") {
                    BodyText(
                        "A try block wraps the code that might throw. One or more catch blocks follow, " +
                        "each naming the exception type to handle. The catch block runs only if the " +
                        "thrown exception is an instance of (or a subclass of) the named type.\n\n" +
                        "Inside the catch block, the exception variable gives you access to the " +
                        "message, the cause, and the full stack trace."
                    )
                    CodeBlock(
                        "try {\n" +
                        "    String text = Files.readString(Path.of(\"data.txt\"));\n" +
                        "    System.out.println(text);\n" +
                        "} catch (IOException e) {\n" +
                        "    System.err.println(\"Could not read file: \" + e.getMessage());\n" +
                        "    e.printStackTrace();  // prints full stack trace to stderr\n" +
                        "}\n\n" +
                        "// Useful methods on any exception:\n" +
                        "// e.getMessage()   — the string passed to the constructor\n" +
                        "// e.getCause()     — the wrapped cause (or null)\n" +
                        "// e.getClass()     — the runtime type (e.g. IOException.class)\n" +
                        "// e.printStackTrace() — prints to stderr\n" +
                        "// e.getStackTrace()   — returns StackTraceElement[]\n\n" +
                        "// Execution continues normally after the catch block.\n" +
                        "// If no exception was thrown, the catch block is skipped entirely."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Multiple catch Blocks + Multi-catch") {
                    BodyText(
                        "You can chain multiple catch blocks after one try. They are tested top-to-bottom; " +
                        "the first matching type wins. Put more-specific types before less-specific ones — " +
                        "the compiler will error if an earlier catch already covers a later one (unreachable " +
                        "catch block).\n\n" +
                        "Since Java 7, you can catch multiple unrelated types in one block using | (pipe). " +
                        "This avoids duplicated handler code. The exception variable in a multi-catch is " +
                        "implicitly final."
                    )
                    CodeBlock(
                        "try {\n" +
                        "    int n    = Integer.parseInt(args[0]);   // may throw NumberFormatException\n" +
                        "    int[] a  = new int[n];                  // may throw NegativeArraySizeException\n" +
                        "    String s = Files.readString(Path.of(\"f.txt\")); // may throw IOException\n" +
                        "    System.out.println(s.charAt(n));        // may throw StringIndexOutOfBoundsException\n" +
                        "} catch (NumberFormatException e) {\n" +
                        "    System.err.println(\"Not a number: \" + e.getMessage());\n" +
                        "} catch (IOException e) {\n" +
                        "    System.err.println(\"File error: \" + e.getMessage());\n" +
                        "} catch (RuntimeException e) {\n" +
                        "    // Catches NegativeArraySizeException, StringIndexOutOfBounds, etc.\n" +
                        "    // Must come AFTER the more-specific NumberFormatException.\n" +
                        "    System.err.println(\"Runtime error: \" + e.getMessage());\n" +
                        "}\n\n" +
                        "// Multi-catch — handle two unrelated types the same way:\n" +
                        "try {\n" +
                        "    process();\n" +
                        "} catch (IOException | NumberFormatException e) {\n" +
                        "    log(e);   // e is implicitly final here\n" +
                        "    throw e;  // can rethrow unchanged\n" +
                        "}"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Catching Everything") {
                    BodyText(
                        "catch (Exception e) catches all checked and unchecked exceptions, but NOT " +
                        "Error subclasses (like OutOfMemoryError).\n\n" +
                        "catch (Throwable t) catches literally everything — Exceptions AND Errors. " +
                        "This is the Java equivalent of C++'s catch(...). It is rarely the right " +
                        "choice, but useful in top-level framework code (servers, test runners) that " +
                        "must stay alive even if one task explodes.\n\n" +
                        "Unlike C++, Java has no bare catch without a type — you must always name a type."
                    )
                    CodeBlock(
                        "// Catch all exceptions (not Error):\n" +
                        "try {\n" +
                        "    riskyOperation();\n" +
                        "} catch (Exception e) {\n" +
                        "    System.err.println(\"Something went wrong: \" + e.getMessage());\n" +
                        "}\n\n" +
                        "// Catch absolutely everything including Error:\n" +
                        "try {\n" +
                        "    runTask();\n" +
                        "} catch (Throwable t) {\n" +
                        "    // Java's equivalent of C++ catch (...)\n" +
                        "    logger.error(\"Unexpected throwable\", t);\n" +
                        "    // Do NOT swallow — at minimum log and rethrow or re-wrap.\n" +
                        "}\n\n" +
                        "// C++ for comparison:\n" +
                        "// try { ... } catch (...) { /* catches everything */ }\n\n" +
                        "// In Java you CANNOT write just: catch { }  — must name a type."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "What Happens If You Don't Catch It") {
                    BodyText(
                        "If an exception propagates out of a method without being caught, the JVM " +
                        "unwinds the call stack frame by frame, looking for a matching catch block. " +
                        "If none is found all the way up to the thread's top-most frame:\n\n" +
                        "1. The thread's UncaughtExceptionHandler is called (default: prints the " +
                        "stack trace to System.err).\n" +
                        "2. The thread terminates.\n\n" +
                        "If the thread that terminated was the main thread, the JVM exits (assuming " +
                        "no other non-daemon threads are running). If it was a background thread, " +
                        "only that thread dies — the rest of the program continues."
                    )
                    CodeBlock(
                        "// main thread — uncaught exception shuts down the JVM:\n" +
                        "public static void main(String[] args) {\n" +
                        "    throw new RuntimeException(\"forgot to catch this\");\n" +
                        "    // JVM prints the stack trace and exits with status 1.\n" +
                        "}\n\n" +
                        "// Background thread — only that thread dies:\n" +
                        "Thread t = new Thread(() -> {\n" +
                        "    throw new RuntimeException(\"boom\"); // thread terminates\n" +
                        "});\n" +
                        "t.start();\n" +
                        "// main thread keeps running; JVM stays alive\n\n" +
                        "// Stack trace printed by default looks like:\n" +
                        "// Exception in thread \"main\" java.lang.RuntimeException: forgot to catch this\n" +
                        "//     at Main.main(Main.java:3)\n" +
                        "//     at java.base/... (JVM internals)"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Checked vs. Unchecked — The throws Keyword") {
                    BodyText(
                        "Java divides exceptions into two categories, enforced at compile time:\n\n" +
                        "Checked exceptions (all Exception subclasses EXCEPT RuntimeException and its " +
                        "subclasses): the compiler requires that a method either catches them or " +
                        "declares them in its throws clause. The caller must then handle them too.\n\n" +
                        "Unchecked exceptions (RuntimeException subclasses, and Error): the compiler " +
                        "never requires declaration. You may still add throws for documentation.\n\n" +
                        "throws on a method signature is a declaration, not a guarantee — it says " +
                        "\"this method MAY throw X\", not \"it definitely will\".\n\n" +
                        "Java has no noexcept equivalent. There is no compiler guarantee that a " +
                        "method will never throw."
                    )
                    CodeBlock(
                        "// Checked — compiler FORCES you to handle or declare:\n" +
                        "void readFile(String path) throws IOException {  // must declare\n" +
                        "    String content = Files.readString(Path.of(path));\n" +
                        "    System.out.println(content);\n" +
                        "}\n\n" +
                        "// Caller must catch or propagate:\n" +
                        "void process() throws IOException {\n" +
                        "    readFile(\"data.txt\");   // propagate up\n" +
                        "}\n" +
                        "// — or —\n" +
                        "void processSafe() {\n" +
                        "    try { readFile(\"data.txt\"); }\n" +
                        "    catch (IOException e) { /* handle */ }\n" +
                        "}\n\n" +
                        "// Unchecked — no declaration needed (but you can add it):\n" +
                        "int divide(int a, int b) {   // no throws needed\n" +
                        "    if (b == 0) throw new ArithmeticException(\"division by zero\");\n" +
                        "    return a / b;\n" +
                        "}\n\n" +
                        "// Multiple checked exceptions:\n" +
                        "void fetch() throws IOException, InterruptedException { ... }"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Rethrowing an Exception") {
                    BodyText(
                        "After catching an exception you have three choices:\n\n" +
                        "1. Rethrow as-is: throw e; — the original stack trace is preserved. The " +
                        "exception continues propagating exactly as if it had never been caught.\n\n" +
                        "2. Wrap and rethrow: throw new RuntimeException(\"context\", e); — adds " +
                        "context and makes the original accessible via getCause(). Useful for " +
                        "converting a checked exception into an unchecked one so the caller does " +
                        "not need to declare it.\n\n" +
                        "3. Throw a different exception: sometimes you want to normalize many " +
                        "low-level exceptions into one domain-specific type."
                    )
                    CodeBlock(
                        "// 1. Rethrow as-is — original stack trace fully preserved:\n" +
                        "try {\n" +
                        "    riskyOp();\n" +
                        "} catch (IOException e) {\n" +
                        "    logger.error(\"Failed\", e);\n" +
                        "    throw e;   // caller still sees IOException with original trace\n" +
                        "}\n\n" +
                        "// 2. Wrap — add context, convert checked to unchecked:\n" +
                        "try {\n" +
                        "    Files.readString(path);\n" +
                        "} catch (IOException e) {\n" +
                        "    throw new RuntimeException(\"Cannot load config from \" + path, e);\n" +
                        "}                // caller sees RuntimeException; original IOE via getCause()\n\n" +
                        "// Unwrap the cause:\n" +
                        "try {\n" +
                        "    loadConfig();\n" +
                        "} catch (RuntimeException e) {\n" +
                        "    Throwable cause = e.getCause();  // the original IOException\n" +
                        "    System.out.println(cause.getMessage());\n" +
                        "}\n\n" +
                        "// 3. Throw a domain exception:\n" +
                        "} catch (SQLException e) {\n" +
                        "    throw new DatabaseException(\"User lookup failed\", e);\n" +
                        "}"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "The finally Block") {
                    BodyText(
                        "A finally block runs after the try and any catch blocks, regardless of " +
                        "what happened: whether an exception was thrown, whether it was caught, or " +
                        "whether a return statement was hit inside try or catch.\n\n" +
                        "finally is guaranteed to run even if:\n" +
                        "  - The try completes normally\n" +
                        "  - An exception was thrown and caught\n" +
                        "  - An exception was thrown and NOT caught (finally still runs before the\n" +
                        "    exception propagates to the caller)\n" +
                        "  - A return statement is in the try or catch block\n\n" +
                        "Use it to release resources. Prefer try-with-resources for AutoCloseable " +
                        "objects — it is cleaner and handles edge cases that finally does not."
                    )
                    CodeBlock(
                        "Connection conn = null;\n" +
                        "try {\n" +
                        "    conn = DriverManager.getConnection(url);\n" +
                        "    return conn.query(\"SELECT 1\");  // return does NOT skip finally!\n" +
                        "} catch (SQLException e) {\n" +
                        "    logger.error(e);\n" +
                        "    throw new DatabaseException(e);\n" +
                        "} finally {\n" +
                        "    if (conn != null) conn.close();  // ALWAYS runs\n" +
                        "}\n\n" +
                        "// Timing of finally:\n" +
                        "//   try body runs → return value computed → finally runs → method returns\n" +
                        "//   try body throws → (catch runs if matches) → finally runs → propagate\n\n" +
                        "// Caveat: if finally has a return, it OVERRIDES the try's return value:\n" +
                        "int example() {\n" +
                        "    try   { return 1; }\n" +
                        "    finally { return 2; }  // method returns 2, not 1 — avoid this!\n" +
                        "}"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "try-with-resources") {
                    BodyText(
                        "Introduced in Java 7, try-with-resources automatically calls close() on " +
                        "any resource that implements AutoCloseable, at the end of the try block — " +
                        "whether the block completed normally or threw an exception. This is the " +
                        "preferred replacement for finally { resource.close(); }.\n\n" +
                        "Multiple resources can be declared, separated by semicolons. They are " +
                        "closed in reverse declaration order (last declared, first closed)."
                    )
                    CodeBlock(
                        "// Single resource:\n" +
                        "try (BufferedReader reader = new BufferedReader(new FileReader(\"data.txt\"))) {\n" +
                        "    String line;\n" +
                        "    while ((line = reader.readLine()) != null) {\n" +
                        "        System.out.println(line);\n" +
                        "    }\n" +
                        "}  // reader.close() called automatically here\n\n" +
                        "// Multiple resources — closed in reverse order (out before in):\n" +
                        "try (InputStream  in  = new FileInputStream(\"src.bin\");\n" +
                        "     OutputStream out = new FileOutputStream(\"dst.bin\")) {\n" +
                        "    in.transferTo(out);\n" +
                        "}  // out.close() then in.close()\n\n" +
                        "// Can still add catch/finally after the try-with-resources block:\n" +
                        "try (Connection c = DriverManager.getConnection(url)) {\n" +
                        "    c.createStatement().execute(sql);\n" +
                        "} catch (SQLException e) {\n" +
                        "    throw new DatabaseException(e);\n" +
                        "}"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Exception Inside finally — Unlike C++ terminate()") {
                    BodyText(
                        "In C++, if an exception is thrown while another exception is already " +
                        "propagating (e.g. a destructor throws during stack unwinding), " +
                        "std::terminate() is called and the program crashes immediately. This is " +
                        "why C++ destructors must never throw.\n\n" +
                        "Java is different: if a finally block throws, the new exception REPLACES " +
                        "the original propagating exception — the original is silently lost. Java " +
                        "does NOT crash or terminate; execution continues with the new exception.\n\n" +
                        "This is why finally blocks should avoid throwing, and why try-with-resources " +
                        "is preferred: if close() throws while a body exception is already propagating, " +
                        "try-with-resources SUPPRESSES the close exception (attaches it to the " +
                        "primary exception via addSuppressed()) rather than discarding the original."
                    )
                    CodeBlock(
                        "// ── finally throwing: original exception is LOST ─────────────────\n" +
                        "try {\n" +
                        "    throw new RuntimeException(\"original\");  // starts propagating\n" +
                        "} finally {\n" +
                        "    throw new RuntimeException(\"from finally\"); // REPLACES original!\n" +
                        "}\n" +
                        "// Caller sees: \"from finally\". \"original\" is gone without a trace.\n\n" +
                        "// ── try-with-resources: close() exception is suppressed ────────────\n" +
                        "class BadResource implements AutoCloseable {\n" +
                        "    public void close() throws Exception {\n" +
                        "        throw new Exception(\"close failed\");\n" +
                        "    }\n" +
                        "}\n\n" +
                        "try (BadResource r = new BadResource()) {\n" +
                        "    throw new RuntimeException(\"body failed\");  // primary exception\n" +
                        "}\n" +
                        "// Caller sees: RuntimeException(\"body failed\")  ← primary is preserved\n" +
                        "// The close() exception is suppressed — attached to the primary:\n\n" +
                        "try {\n" +
                        "    try (BadResource r = new BadResource()) {\n" +
                        "        throw new RuntimeException(\"body failed\");\n" +
                        "    }\n" +
                        "} catch (RuntimeException e) {\n" +
                        "    System.out.println(e.getMessage());            // body failed\n" +
                        "    System.out.println(e.getSuppressed()[0].getMessage()); // close failed\n" +
                        "}\n\n" +
                        "// In C++: destructor throws during unwind → std::terminate() → crash\n" +
                        "// In Java: finally throws during unwind   → original exception LOST\n" +
                        "// try-with-resources: close() throws during unwind → suppressed, primary kept"
                    )
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
