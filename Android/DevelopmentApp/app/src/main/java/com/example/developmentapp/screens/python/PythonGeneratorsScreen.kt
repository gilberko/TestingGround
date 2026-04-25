package com.example.developmentapp.screens.python

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
fun PythonGeneratorsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Generator Functions",
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
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard("What Is a Generator Function") {
                    BodyText("A generator function looks like a normal function but uses yield instead of return. Calling it does not run any of its code — instead it returns a generator object immediately.")
                    BodyText("The code inside only runs when you ask for the next value. After producing a value the function suspends itself, keeping all its local variables and the exact point of execution frozen until the next request.")
                    CodeBlock("""
def count_up(start):
    n = start
    while True:
        yield n       # suspend here, hand n to caller
        n += 1        # resumes here on next call

gen = count_up(5)     # no code runs yet
print(type(gen))      # <class 'generator'>
                    """.trimIndent())
                    BodyText("A generator is a lazy sequence — values are produced one at a time on demand, not all at once. This makes it possible to work with sequences too large (or infinite) to store in memory.")
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("The yield Keyword") {
                    BodyText("yield is like return with memory. When Python hits yield it:")
                    BodyText("  1. Evaluates the expression and sends the value to the caller\n  2. Suspends the function — local variables, loop counters, the program counter — all frozen\n  3. Returns control to whoever called next()")
                    BodyText("When next() is called again, execution picks up on the line immediately after the yield, with every local variable exactly as it was.")
                    CodeBlock("""
def three_steps():
    print("step 1")
    yield "A"
    print("step 2")
    yield "B"
    print("step 3")
    yield "C"
    # function ends — StopIteration is raised next call

gen = three_steps()
next(gen)   # prints "step 1", returns "A"
next(gen)   # prints "step 2", returns "B"
next(gen)   # prints "step 3", returns "C"
next(gen)   # raises StopIteration
                    """.trimIndent())
                    BodyText("Contrast with a normal function: every call starts fresh from the top, all local state is gone. A generator remembers where it left off.")
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("next() and StopIteration") {
                    BodyText("Use next(gen) to pull the next value from a generator. When the function body finishes (falls off the end or hits a bare return), Python raises StopIteration automatically.")
                    CodeBlock("""
def two_values():
    yield 10
    yield 20

gen = two_values()

print(next(gen))        # 10
print(next(gen))        # 20
print(next(gen))        # raises StopIteration
                    """.trimIndent())
                    BodyText("To avoid an exception when you are unsure whether values remain, provide a default:")
                    CodeBlock("""
val = next(gen, None)   # returns None instead of raising
if val is None:
    print("generator exhausted")
                    """.trimIndent())
                    BodyText("Generators are single-use — once exhausted you cannot rewind them. Create a new generator object to start over.")
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("Iterating Over a Generator") {
                    BodyText("A generator implements the iterator protocol: it has both __iter__ (returns self) and __next__. This means you can use it anywhere Python expects an iterable.")
                    CodeBlock("""
def squares(n):
    for i in range(n):
        yield i * i

# for loop calls next() internally, catches StopIteration
for s in squares(5):
    print(s)   # 0, 1, 4, 9, 16

# Materialise all values at once
result = list(squares(5))   # [0, 1, 4, 9, 16]

# Unpack (only safe if finite)
a, b, c = squares(3)        # a=0, b=1, c=4
                    """.trimIndent())
                    BodyText("Generator expressions are the inline equivalent of a list comprehension but lazy:")
                    CodeBlock("""
# List comprehension — builds the whole list in memory
big_list = [x * 2 for x in range(1_000_000)]

# Generator expression — produces one value at a time
gen_expr = (x * 2 for x in range(1_000_000))

total = sum(gen_expr)   # never holds all values at once
                    """.trimIndent())
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("Use Cases") {
                    BodyText("Streaming large files — read one line at a time without loading the whole file:")
                    CodeBlock("""
def read_lines(filepath):
    with open(filepath) as f:
        for line in f:
            yield line.rstrip()

for line in read_lines("huge_log.txt"):
    process(line)          # only one line in memory at a time
                    """.trimIndent())
                    BodyText("Infinite sequences — a generator can yield forever; the caller decides when to stop:")
                    CodeBlock("""
def fibonacci():
    a, b = 0, 1
    while True:
        yield a
        a, b = b, a + b

fib = fibonacci()
first_10 = [next(fib) for _ in range(10)]
# [0, 1, 1, 2, 3, 5, 8, 13, 21, 34]
                    """.trimIndent())
                    BodyText("Lazy pipelines — chain generators together; each stage produces values one at a time:")
                    CodeBlock("""
def integers():
    n = 0
    while True:
        yield n
        n += 1

def evens(source):
    for n in source:
        if n % 2 == 0:
            yield n

def take(n, source):
    for i, val in enumerate(source):
        if i >= n:
            return
        yield val

pipeline = take(5, evens(integers()))
print(list(pipeline))   # [0, 2, 4, 6, 8]
                    """.trimIndent())
                    BodyText("At no point does any stage materialise the full sequence. This pattern scales to arbitrarily large data sets with constant memory usage.")
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
