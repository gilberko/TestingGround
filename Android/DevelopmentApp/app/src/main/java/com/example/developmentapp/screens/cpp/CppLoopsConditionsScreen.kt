package com.example.developmentapp.screens.cpp

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
fun CppLoopsConditionsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "C/C++ — Loops and Conditions",
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
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        ) {
            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("while Loop") {
                    BodyText("Evaluates the condition before each iteration. If false on entry, the body never runs.")
                    CodeBlock("""
while (condition) {
    // body
}

// Classic example
int i = 0;
while (i < 10) {
    printf("%d\n", i);
    i++;
}

// Infinite loop — exit via break
while (true) {
    if (done) break;
}
                    """.trimIndent())
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("do...while Loop") {
                    BodyText("Runs the body at least once, then checks the condition. Useful when you always need one iteration before deciding whether to continue.")
                    CodeBlock("""
do {
    // body runs first, condition checked after
} while (condition);

// Read input until valid
int x;
do {
    printf("Enter positive number: ");
    scanf("%d", &x);
} while (x <= 0);
                    """.trimIndent())
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("for Loop") {
                    BodyText("Packs init, condition, and increment into one line. All three parts are optional — leaving them all out creates an infinite loop.")
                    CodeBlock("""
for (init; condition; increment) {
    // body
}

for (int i = 0; i < 10; i++) { ... }

// Multiple variables
for (int i = 0, j = 10; i < j; i++, j--) { ... }

// Infinite loop
for (;;) { ... }

// Loop over index with explicit size
for (size_t i = 0; i < vec.size(); i++) { ... }
                    """.trimIndent())
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("Range-based for (C++11)") {
                    BodyText("Iterates over any container or array without managing indices. Works on arrays, std::vector, std::map, std::string, and anything with begin()/end().")
                    BodyText("Use auto & to avoid copying each element and allow modification. Use const auto & when you only need to read — the compiler can enforce it.")
                    CodeBlock("""
std::vector<int> nums = {1, 2, 3, 4};

// Copy — modifying 'n' does not affect the vector
for (auto n : nums) { printf("%d\n", n); }

// Reference — modifications affect the vector
for (auto &n : nums) { n *= 2; }

// Read-only reference — no copy, no accidental mutation
for (const auto &n : nums) { printf("%d\n", n); }

// Works on maps too
std::map<std::string, int> scores;
for (const auto &[name, score] : scores) { // C++17 structured binding
    printf("%s: %d\n", name.c_str(), score);
}

// Works on plain arrays
int arr[] = {10, 20, 30};
for (auto x : arr) { ... }
                    """.trimIndent())
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("break and continue") {
                    BodyText("break exits the innermost loop immediately. continue skips the rest of the current iteration and jumps to the next one.")
                    BodyText("Neither can jump out of nested loops directly. If you need to break out of two levels, use a flag variable or goto (rarely appropriate).")
                    CodeBlock("""
// break — stop the loop entirely
for (int i = 0; i < 100; i++) {
    if (i == 5) break;   // exits when i == 5
}

// continue — skip to next iteration
for (int i = 0; i < 10; i++) {
    if (i % 2 == 0) continue;  // skip even numbers
    printf("%d\n", i);          // only odd numbers printed
}

// Breaking out of nested loops with a flag
bool found = false;
for (int i = 0; i < rows && !found; i++) {
    for (int j = 0; j < cols; j++) {
        if (grid[i][j] == target) { found = true; break; }
    }
}
                    """.trimIndent())
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("if / else if / else") {
                    BodyText("Standard conditional branching. Only the first true branch runs. The else is always associated with the nearest if (dangling-else pitfall).")
                    BodyText("C++17 allows declaring a variable inside the if condition — its scope is limited to all branches of the if/else chain.")
                    CodeBlock("""
if (x > 0) {
    printf("positive\n");
} else if (x < 0) {
    printf("negative\n");
} else {
    printf("zero\n");
}

// Dangling-else pitfall — use braces to be explicit
if (a)
    if (b) doB();
    else doElse();   // belongs to inner if, NOT outer if!

// C++17: init-statement in condition
if (int result = compute(); result > 0) {
    use(result);     // result is visible in both branches
} else {
    handle_error(result);
}
                    """.trimIndent())
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("switch / case / default") {
                    BodyText("Matches an integral or enum value against a list of constants. The compiler generates a jump table — typically faster than a long if/else chain for dense values.")
                    BodyText("Execution falls through from one case to the next unless you break. Intentional fall-through should be marked with [[fallthrough]] (C++17) to silence warnings.")
                    CodeBlock("""
switch (value) {
    case 1:
        printf("one\n");
        break;
    case 2:
        printf("two\n");
        break;
    case 3:
    case 4:
        printf("three or four\n");  // shared body via fall-through
        break;
    default:
        printf("other\n");
        break;
}

// Intentional fall-through
switch (event) {
    case KEYDOWN:
        [[fallthrough]];   // C++17 — intentional, suppresses warning
    case KEYPRESS:
        handle_key();
        break;
}

// Declaring variables inside cases needs braces
switch (x) {
    case 1: {
        int tmp = compute();   // scoped to this block
        use(tmp);
        break;
    }
    case 2:
        break;
}
                    """.trimIndent())
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("Ternary Operator  a ? b : c") {
                    BodyText("A compact expression that evaluates to b if a is true, otherwise c. Both b and c must have compatible types — the result is an expression, not a statement.")
                    BodyText("Good for simple assignments or arguments. Avoid deep nesting; an if/else is clearer for complex logic.")
                    CodeBlock("""
int max = (a > b) ? a : b;

// In a function call
printf("%s\n", (x > 0) ? "positive" : "non-positive");

// Nested (readable limit — prefer if/else beyond two levels)
const char *sign = (x > 0) ? "+" : (x < 0) ? "-" : "0";

// Cannot use as a statement alone (unlike if/else)
// (x > 0) ? doA() : doB();  // technically valid for void functions, but confusing
                    """.trimIndent())
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("bool and the ! Operator") {
                    BodyText("bool is a built-in type with values true (1) and false (0). The ! operator inverts a boolean. Applying !! to any value normalizes it to 0 or 1.")
                    CodeBlock("""
bool flag = true;
bool inv  = !flag;    // false

// Negation
if (!flag) { ... }

// Double negation to normalize to 0/1
int x = 42;
bool b = !!x;   // true (1)

// bool arithmetic
int sum = true + true;   // 2 — bools implicitly convert to int
                    """.trimIndent())
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("Truthiness — Numerics and Pointers") {
                    BodyText("In C and C++, any non-zero integer is true and zero is false. Any non-null pointer is true and nullptr (NULL) is false. This lets you write idiomatic conditions without explicit comparisons.")
                    CodeBlock("""
int count = 5;
if (count) { ... }        // true because count != 0

int *ptr = get_ptr();
if (ptr) { ... }           // true if not null
if (!ptr) return;          // early-return on null

// Equivalent but more explicit
if (ptr != nullptr) { ... }
if (count != 0) { ... }

// Common idiom: check return value
FILE *f = fopen("data.txt", "r");
if (!f) {
    perror("fopen");
    return -1;
}
                    """.trimIndent())
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
