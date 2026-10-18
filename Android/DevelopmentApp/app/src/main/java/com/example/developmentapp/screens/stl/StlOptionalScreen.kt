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
fun StlOptionalScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "C++ — STL Optional",
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
                SectionCard(title = "What Is std::optional?") {
                    BodyText("std::optional<T> (C++17, header <optional>) is a wrapper that either holds a value of type T or holds nothing. It expresses \"maybe a value\" directly in the type system.")
                    BodyText("The value lives inside the optional object itself — there is NO heap allocation. Its size is roughly sizeof(T) plus a bool \"engaged\" flag, rounded up for alignment. The contained T is only constructed when a value is actually set.")
                    BodyText("It replaces older patterns such as magic sentinel values (-1, empty string, INT_MAX), returning a nullable pointer, or returning bool and passing the result via an out-parameter.")
                    BodyText("Typical use: return values of functions that may legitimately produce no result — lookups, parsing, searching, optional configuration fields.")
                    BodyText("Restrictions: T cannot be a reference (std::optional<int&> is ill-formed — C++26 adds optional references), an array, std::nullopt_t, or std::in_place_t.")
                    CodeBlock(
                        "#include <optional>\n" +
                        "#include <string>\n" +
                        "#include <iostream>\n" +
                        "\n" +
                        "std::optional<int>         maybeNumber;   // int or nothing\n" +
                        "std::optional<std::string> maybeName;     // string or nothing"
                    )
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard(title = "Creating an Optional WITH a Value") {
                    BodyText("An optional containing a value is called \"engaged\". There are several ways to create one:")
                    CodeBlock(
                        "std::optional<int> a = 42;                  // implicit from T\n" +
                        "std::optional<std::string> s{\"hello\"};      // direct-init\n" +
                        "auto d = std::make_optional(3.14);           // optional<double>\n" +
                        "\n" +
                        "struct Point { int x, y; Point(int x, int y) : x(x), y(y) {} };\n" +
                        "\n" +
                        "// std::in_place — construct T directly inside the optional (no temporary)\n" +
                        "std::optional<Point> p(std::in_place, 1, 2);\n" +
                        "auto p2 = std::make_optional<Point>(3, 4);\n" +
                        "\n" +
                        "// Set a value on an existing optional\n" +
                        "std::optional<Point> q;\n" +
                        "q = Point(5, 6);        // assign a value\n" +
                        "q.emplace(7, 8);        // destroy old value (if any), construct new one in place"
                    )
                    BodyText("emplace() returns a reference to the newly constructed value and is the most efficient way to (re)set a value of a non-trivial type.")
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard(title = "Creating an Optional WITHOUT a Value") {
                    BodyText("An empty optional is called \"disengaged\". std::nullopt is the special constant that means \"no value\".")
                    CodeBlock(
                        "std::optional<int> e1;                 // default — empty\n" +
                        "std::optional<int> e2 = std::nullopt;  // explicitly empty\n" +
                        "std::optional<int> e3 = {};            // also empty\n" +
                        "\n" +
                        "std::optional<int> x = 10;\n" +
                        "x.reset();             // now empty (destroys the contained value)\n" +
                        "x = std::nullopt;      // same effect"
                    )
                    BodyText("The most common pattern — a function that returns either a value or nothing:")
                    CodeBlock(
                        "std::optional<std::string> findUser(int id) {\n" +
                        "    if (id == 1) return \"Alice\";      // engaged\n" +
                        "    if (id == 2) return \"Bob\";\n" +
                        "    return std::nullopt;               // nothing found\n" +
                        "}"
                    )
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard(title = "Checking If It Has a Value") {
                    BodyText("has_value() returns true if the optional is engaged. The optional is also contextually convertible to bool, so it can be used directly in if / while / ! / && conditions.")
                    CodeBlock(
                        "auto user = findUser(1);\n" +
                        "\n" +
                        "if (user.has_value()) { /* ... */ }    // explicit\n" +
                        "if (user)             { /* ... */ }    // same thing — contextual bool\n" +
                        "if (!user)            { /* empty */ }\n" +
                        "if (user != std::nullopt) { /* ... */ } // comparison with nullopt\n" +
                        "\n" +
                        "// C++17 if-with-initializer — scope the optional to the if\n" +
                        "if (auto u = findUser(2); u) {\n" +
                        "    std::cout << \"Found \" << *u << '\\n';\n" +
                        "}\n" +
                        "\n" +
                        "// bool b = user;      // ERROR — the conversion is explicit\n" +
                        "bool b = user.has_value();             // OK\n" +
                        "bool c = static_cast<bool>(user);      // OK"
                    )
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard(title = "Using the Value") {
                    BodyText("*opt and opt->member — access the value WITHOUT any check. Fast, but only safe after you have verified the optional is engaged.")
                    BodyText("opt.value() — access the value WITH a check. Throws if empty (see next section).")
                    BodyText("opt.value_or(fallback) — returns the value if present, otherwise a copy of the fallback. Never throws because of emptiness.")
                    CodeBlock(
                        "std::optional<std::string> name = findUser(1);\n" +
                        "\n" +
                        "if (name) {\n" +
                        "    std::cout << *name << '\\n';          // unchecked dereference\n" +
                        "    std::cout << name->size() << '\\n';   // member access via ->\n" +
                        "}\n" +
                        "\n" +
                        "std::cout << name.value() << '\\n';      // checked access\n" +
                        "\n" +
                        "std::string n = findUser(99).value_or(\"Guest\");  // \"Guest\"\n" +
                        "\n" +
                        "// Modify the contained value in place\n" +
                        "std::optional<int> count = 5;\n" +
                        "*count += 1;                 // 6\n" +
                        "count.value() *= 2;          // 12"
                    )
                    BodyText("C++23 monadic operations let you chain work without nested ifs. transform maps the value, and_then chains a function that itself returns an optional, or_else supplies an alternative when empty:")
                    CodeBlock(
                        "std::optional<int> parseInt(const std::string& s);\n" +
                        "\n" +
                        "auto len = findUser(1)\n" +
                        "    .transform([](const std::string& s) { return s.size(); }); // optional<size_t>\n" +
                        "\n" +
                        "auto v = std::optional<std::string>{\"42\"}\n" +
                        "    .and_then(parseInt)                                // optional<int>\n" +
                        "    .or_else([] { return std::optional<int>{0}; });    // fallback if empty"
                    )
                    BodyText("Comparisons: optionals compare by their contained values; an empty optional compares equal to another empty one and is LESS than any engaged optional. You can also compare an optional directly with a T: (opt == 5) is false when opt is empty.")
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard(title = "What Happens If It's Empty? (Exceptions)") {
                    BodyText("It depends on HOW you access it:")
                    BodyText("1. opt.value() on an empty optional — YES, throws std::bad_optional_access. It is declared in <optional> and derives from std::exception, so catch (const std::exception&) also catches it. The text returned by what() is implementation-defined (e.g. \"bad optional access\").")
                    CodeBlock(
                        "std::optional<int> empty;\n" +
                        "\n" +
                        "try {\n" +
                        "    int v = empty.value();          // throws\n" +
                        "} catch (const std::bad_optional_access& e) {\n" +
                        "    std::cerr << \"Error: \" << e.what() << '\\n';\n" +
                        "}"
                    )
                    BodyText("2. *opt or opt-> on an empty optional — NO exception. This is UNDEFINED BEHAVIOR: there is no check, so the program reads uninitialized storage (or the remains of a previously destroyed value), may appear to \"work\", return garbage, or crash.")
                    CodeBlock(
                        "std::optional<int> empty;\n" +
                        "int bad = *empty;     // UB — no throw, no error, anything can happen\n" +
                        "\n" +
                        "std::optional<std::string> s;\n" +
                        "s->size();            // UB — calling a member on a non-existent string"
                    )
                    BodyText("Debug / hardened standard libraries may catch this with an assertion that aborts the program (not an exception): MSVC debug builds (_ITERATOR_DEBUG_LEVEL / _CONTAINER_DEBUG_LEVEL), libstdc++ with -D_GLIBCXX_ASSERTIONS, libc++ hardening modes. Never rely on it in release builds.")
                    BodyText("3. opt.value_or(x) on an empty optional — never throws because of emptiness; it simply returns x. (It can only throw if copying/converting T itself throws.)")
                    BodyText("Rule of thumb: check first and use *opt, or use value() when an empty optional is a genuine bug you want reported as an exception, or use value_or() when there is a sensible default.")
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard(title = "optional vs Alternatives") {
                    BodyText("Raw pointer / nullptr — also expresses \"maybe\", but implies ownership/lifetime questions and usually heap allocation. optional holds the value by value.")
                    BodyText("Sentinel values (-1, \"\", INT_MAX) — ambiguous when the sentinel is also a valid value, and easy to forget to check. optional makes absence explicit in the type.")
                    BodyText("Exceptions — appropriate when absence is a real error. optional is for when \"no result\" is a normal, expected outcome.")
                    BodyText("std::expected<T, E> (C++23) — like optional, but the empty case carries an error value explaining WHY there is no result. Prefer it when the caller needs the reason.")
                    CodeBlock(
                        "// C++23\n" +
                        "#include <expected>\n" +
                        "std::expected<int, std::string> parse(const std::string& s) {\n" +
                        "    if (s.empty()) return std::unexpected(\"empty input\");\n" +
                        "    return std::stoi(s);\n" +
                        "}"
                    )
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
