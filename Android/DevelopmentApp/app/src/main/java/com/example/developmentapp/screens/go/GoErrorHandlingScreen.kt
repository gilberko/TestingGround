package com.example.developmentapp.screens.go

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
fun GoErrorHandlingScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Go — Error Handling",
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
                SectionCard(title = "Errors Are Values, Not Exceptions") {
                    BodyText("Go has no try/catch/throw. There are no exceptions. Instead, errors are ordinary values — a function that can fail returns (result, error) as its last two return values. The caller receives the error alongside the result and must check it explicitly.")
                    BodyText("This design is intentional: error handling is visible in the code, never hidden behind a catch block somewhere up the call stack. If you want the error to propagate, you return it. If you want to handle it, you handle it right where it occurred.")
                    CodeBlock(
                        "// Typical Go error-handling pattern\n" +
                        "result, err := doSomething()\n" +
                        "if err != nil {\n" +
                        "    // handle or return the error\n" +
                        "    return fmt.Errorf(\"doSomething failed: %w\", err)\n" +
                        "}\n" +
                        "// use result — guaranteed no error here"
                    )
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard(title = "The error Interface") {
                    BodyText("error is a predeclared built-in interface in Go. Any type that implements a single method — Error() string — satisfies the error interface. nil means no error.")
                    CodeBlock(
                        "// The built-in error interface (declared by the language itself)\n" +
                        "type error interface {\n" +
                        "    Error() string\n" +
                        "}\n" +
                        "\n" +
                        "// Any type with Error() string is an error:\n" +
                        "type MyError struct{ Code int }\n" +
                        "func (e *MyError) Error() string {\n" +
                        "    return fmt.Sprintf(\"error code %d\", e.Code)\n" +
                        "}\n" +
                        "\n" +
                        "var err error = &MyError{Code: 404}   // valid"
                    )
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard(title = "Creating and Returning Errors") {
                    BodyText("errors.New creates a simple immutable error from a string. Two calls to errors.New with the same string produce two distinct error values (pointer identity).")
                    BodyText("fmt.Errorf formats a message and optionally wraps another error using %w (available since Go 1.13). Wrapping preserves the original error in a chain that can be inspected later.")
                    CodeBlock(
                        "import (\n" +
                        "    \"errors\"\n" +
                        "    \"fmt\"\n" +
                        ")\n" +
                        "\n" +
                        "// Sentinel errors — declare once, compare with errors.Is\n" +
                        "var ErrNotFound  = errors.New(\"not found\")\n" +
                        "var ErrForbidden = errors.New(\"forbidden\")\n" +
                        "\n" +
                        "func findUser(id int) (string, error) {\n" +
                        "    if id <= 0 {\n" +
                        "        // Wrap ErrNotFound so callers can match it with errors.Is\n" +
                        "        return \"\", fmt.Errorf(\"findUser: invalid id %d: %w\", id, ErrNotFound)\n" +
                        "    }\n" +
                        "    return \"Alice\", nil\n" +
                        "}"
                    )
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard(title = "Handling Errors — errors.Is and errors.As") {
                    BodyText("errors.Is(err, target) — walks the entire unwrap chain and returns true if any error in the chain equals target. Use it to compare against sentinel errors like ErrNotFound.")
                    BodyText("errors.As(err, &target) — walks the chain and assigns the first error that can be assigned to the type of target. Use it when you need to access fields of a concrete error type.")
                    BodyText("errors.Unwrap(err) — returns the next error in the chain (the one wrapped with %w), or nil.")
                    CodeBlock(
                        "name, err := findUser(-1)\n" +
                        "if errors.Is(err, ErrNotFound) {\n" +
                        "    fmt.Println(\"user does not exist\")\n" +
                        "} else if err != nil {\n" +
                        "    fmt.Println(\"unexpected error:\", err)\n" +
                        "}\n" +
                        "\n" +
                        "// errors.As — extract concrete type from chain\n" +
                        "var myErr *MyError\n" +
                        "if errors.As(err, &myErr) {\n" +
                        "    fmt.Println(\"error code:\", myErr.Code)\n" +
                        "}"
                    )
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard(title = "Custom Error Types") {
                    BodyText("Define a struct that implements the error interface to attach structured context to an error — a status code, a field name, an HTTP status, etc.")
                    CodeBlock(
                        "type ValidationError struct {\n" +
                        "    Field   string\n" +
                        "    Message string\n" +
                        "}\n" +
                        "\n" +
                        "func (e *ValidationError) Error() string {\n" +
                        "    return e.Field + \": \" + e.Message\n" +
                        "}\n" +
                        "\n" +
                        "func validate(age int) error {\n" +
                        "    if age < 0 {\n" +
                        "        return &ValidationError{Field: \"age\", Message: \"must be non-negative\"}\n" +
                        "    }\n" +
                        "    return nil\n" +
                        "}\n" +
                        "\n" +
                        "// Caller:\n" +
                        "if err := validate(-1); err != nil {\n" +
                        "    var ve *ValidationError\n" +
                        "    if errors.As(err, &ve) {\n" +
                        "        fmt.Println(\"field:\", ve.Field) // \"age\"\n" +
                        "    }\n" +
                        "}"
                    )
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard(title = "What If You Don't Handle an Error?") {
                    BodyText("Discarding an error with _ is valid Go syntax. No panic occurs, no exception is thrown, nothing unusual happens at runtime — the error is simply lost.")
                    BodyText("Unlike languages with exceptions, there is no automatic propagation. If you don't return the error, it disappears. This is almost always a bug: a missing error check means you continue as if the operation succeeded.")
                    BodyText("Go linters (staticcheck, errcheck) flag ignored errors. The idiomatic rule: always check errors from functions that can fail.")
                    CodeBlock(
                        "// Bad — silently discards the error\n" +
                        "result, _ := riskyOperation()\n" +
                        "\n" +
                        "// Also risky — assigning to a new err variable without checking\n" +
                        "f, err := os.Open(\"file.txt\")\n" +
                        "data, err := io.ReadAll(f) // shadows previous err — old one lost!\n" +
                        "// if Open failed, f is nil and ReadAll panics\n" +
                        "\n" +
                        "// Correct pattern: check after each step\n" +
                        "f, err := os.Open(\"file.txt\")\n" +
                        "if err != nil {\n" +
                        "    return err\n" +
                        "}\n" +
                        "defer f.Close()\n" +
                        "data, err := io.ReadAll(f)\n" +
                        "if err != nil {\n" +
                        "    return err\n" +
                        "}"
                    )
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard(title = "panic and recover") {
                    BodyText("panic is Go's mechanism for truly unrecoverable situations: an index out of bounds, a nil pointer dereference, or an explicit panic(\"msg\"). When a panic occurs, the current function stops, all deferred functions run, and the stack unwinds upward until the program crashes with a stack trace.")
                    BodyText("recover() inside a deferred function can intercept a panic and convert it back into a normal return path. This is how libraries protect their callers from unexpected panics.")
                    BodyText("Important: do not use panic for ordinary error handling. Use it only for bugs that should never happen. Return errors for expected failure conditions.")
                    CodeBlock(
                        "func safeDiv(a, b int) (result int, err error) {\n" +
                        "    defer func() {\n" +
                        "        if r := recover(); r != nil {\n" +
                        "            err = fmt.Errorf(\"recovered from panic: %v\", r)\n" +
                        "        }\n" +
                        "    }()\n" +
                        "    return a / b, nil // panics if b == 0; recover catches it\n" +
                        "}\n" +
                        "\n" +
                        "result, err := safeDiv(10, 0)\n" +
                        "fmt.Println(result, err) // 0, recovered from panic: runtime error: integer divide by zero"
                    )
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
