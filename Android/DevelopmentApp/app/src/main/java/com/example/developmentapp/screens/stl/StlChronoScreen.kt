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
fun StlChronoScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "C++ — STL Chrono",
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
                SectionCard(title = "What Is <chrono>?") {
                    BodyText("The <chrono> header (C++11) provides type-safe time abstractions built around three concepts: clocks, time points, and durations. Everything is strongly typed so that accidentally mixing seconds with milliseconds is a compile error, not a silent bug.")
                    BodyText("Three standard clocks:")
                    BodyText("std::chrono::system_clock — wall-clock time. Can be converted to time_t (calendar time). May go backward on DST changes or NTP corrections. Use for timestamps that need to match calendar dates.")
                    BodyText("std::chrono::steady_clock — monotonic clock. Never goes backward. Best for measuring intervals and implementing timeouts.")
                    BodyText("std::chrono::high_resolution_clock — highest available tick resolution. Often an alias for steady_clock. Use for micro-benchmarks.")
                    CodeBlock(
                        "#include <chrono>\n" +
                        "#include <thread>\n" +
                        "#include <ctime>\n" +
                        "\n" +
                        "namespace ch = std::chrono;"
                    )
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard(title = "Getting the Current Time") {
                    BodyText("Call ::now() on a clock to get a time_point representing the current instant.")
                    CodeBlock(
                        "auto now_steady = ch::steady_clock::now();   // best for intervals\n" +
                        "auto now_system = ch::system_clock::now();   // wall-clock\n" +
                        "\n" +
                        "// Convert system_clock time_point to calendar time (C API)\n" +
                        "std::time_t tt = ch::system_clock::to_time_t(now_system);\n" +
                        "std::cout << std::ctime(&tt);  // \"Mon Apr 27 12:00:00 2026\\n\"\n" +
                        "\n" +
                        "// time_since_epoch — duration from clock's epoch (Jan 1 1970 for system_clock)\n" +
                        "auto epoch_ms = ch::duration_cast<ch::milliseconds>(\n" +
                        "    now_system.time_since_epoch()).count();\n" +
                        "std::cout << epoch_ms << \" ms since epoch\\n\";"
                    )
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard(title = "Durations") {
                    BodyText("A duration represents a span of time. The standard library provides named aliases for common units.")
                    BodyText("Predefined duration types: nanoseconds, microseconds, milliseconds, seconds, minutes, hours. C++20 adds days, weeks, months, years.")
                    BodyText("C++14 chrono literals (require using namespace std::chrono_literals or using namespace std::literals) let you write 1s, 500ms, 2min, 1h directly.")
                    BodyText("duration_cast<> converts between units (truncates; use round<> from C++17 to round to nearest).")
                    CodeBlock(
                        "using namespace std::chrono_literals;\n" +
                        "\n" +
                        "// Construct durations\n" +
                        "ch::nanoseconds  ns  = 500ns;\n" +
                        "ch::microseconds us  = 500us;\n" +
                        "ch::milliseconds ms  = 500ms;\n" +
                        "ch::seconds      sec = 5s;\n" +
                        "ch::minutes      min = 2min;\n" +
                        "ch::hours        hr  = 1h;\n" +
                        "\n" +
                        "// Arithmetic\n" +
                        "auto total = 1h + 30min + 15s;  // ch::seconds(5415)\n" +
                        "auto half  = sec / 2;            // 2 seconds (integer division)\n" +
                        "\n" +
                        "// duration_cast — convert between units (truncates)\n" +
                        "auto as_ms = ch::duration_cast<ch::milliseconds>(sec); // 5000ms\n" +
                        "\n" +
                        "// Extract the raw tick count\n" +
                        "long long count = sec.count();   // 5"
                    )
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard(title = "Time Points — A Moment in the Future") {
                    BodyText("A time_point is a clock's epoch plus a duration. You create a future time point by adding a duration to now().")
                    CodeBlock(
                        "using namespace std::chrono_literals;\n" +
                        "\n" +
                        "auto now = ch::steady_clock::now();\n" +
                        "\n" +
                        "// Time points in the future\n" +
                        "auto in_500ms  = now + 500ms;\n" +
                        "auto in_2s     = now + 2s;\n" +
                        "auto in_5min   = now + 5min;\n" +
                        "auto in_1h     = now + 1h;\n" +
                        "\n" +
                        "// Difference between two time points → duration\n" +
                        "auto diff = in_5min - now;   // ch::minutes(5) (approximately)\n" +
                        "auto diff_ms = ch::duration_cast<ch::milliseconds>(diff).count();\n" +
                        "// 300000 ms\n" +
                        "\n" +
                        "// time_point type spelled out (rarely needed explicitly):\n" +
                        "// ch::time_point<ch::steady_clock>"
                    )
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard(title = "Waiting — sleep_for and sleep_until") {
                    BodyText("std::this_thread::sleep_for(duration) blocks the current thread for at least the given duration. It may sleep slightly longer due to OS scheduling.")
                    BodyText("std::this_thread::sleep_until(time_point) blocks the current thread until the given time point is reached.")
                    BodyText("Both functions require #include <thread>.")
                    CodeBlock(
                        "#include <thread>\n" +
                        "#include <chrono>\n" +
                        "using namespace std::chrono_literals;\n" +
                        "\n" +
                        "// Wait for a duration\n" +
                        "std::this_thread::sleep_for(500ms);\n" +
                        "std::this_thread::sleep_for(std::chrono::seconds(2));\n" +
                        "std::this_thread::sleep_for(std::chrono::minutes(1));\n" +
                        "\n" +
                        "// Wait until a specific time point\n" +
                        "auto deadline = std::chrono::steady_clock::now() + 3s;\n" +
                        "std::this_thread::sleep_until(deadline);\n" +
                        "\n" +
                        "// Useful pattern: wake at a fixed rate (avoids drift)\n" +
                        "auto next_tick = std::chrono::steady_clock::now();\n" +
                        "while (running) {\n" +
                        "    next_tick += 100ms;\n" +
                        "    doWork();\n" +
                        "    std::this_thread::sleep_until(next_tick); // absorbs work time\n" +
                        "}"
                    )
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard(title = "Measuring Elapsed Time") {
                    BodyText("The most common use of chrono is measuring how long code takes to run. Use steady_clock (not system_clock) so clock adjustments don't distort your measurement.")
                    CodeBlock(
                        "auto start = ch::steady_clock::now();\n" +
                        "\n" +
                        "// ... the work you want to measure ...\n" +
                        "doExpensiveWork();\n" +
                        "\n" +
                        "auto end     = ch::steady_clock::now();\n" +
                        "auto elapsed = end - start;  // duration in steady_clock's native units\n" +
                        "\n" +
                        "// Cast to a readable unit\n" +
                        "auto ms  = ch::duration_cast<ch::milliseconds>(elapsed).count();\n" +
                        "auto us  = ch::duration_cast<ch::microseconds>(elapsed).count();\n" +
                        "auto sec = ch::duration_cast<ch::seconds>(elapsed).count();\n" +
                        "\n" +
                        "std::cout << \"Elapsed: \" << ms << \" ms\\n\";\n" +
                        "\n" +
                        "// C++17: duration as floating-point seconds\n" +
                        "double secs = ch::duration<double>(elapsed).count();\n" +
                        "std::cout << \"Elapsed: \" << secs << \" s\\n\";"
                    )
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard(title = "Condition Variable Timeouts") {
                    BodyText("std::condition_variable's timed wait methods also accept chrono durations and time points, making it easy to implement timeouts in multi-threaded code.")
                    CodeBlock(
                        "#include <mutex>\n" +
                        "#include <condition_variable>\n" +
                        "using namespace std::chrono_literals;\n" +
                        "\n" +
                        "std::mutex mtx;\n" +
                        "std::condition_variable cv;\n" +
                        "bool ready = false;\n" +
                        "\n" +
                        "std::unique_lock<std::mutex> lk(mtx);\n" +
                        "\n" +
                        "// Wait at most 2 seconds\n" +
                        "auto status = cv.wait_for(lk, 2s, []{ return ready; });\n" +
                        "if (status) {\n" +
                        "    // condition became true\n" +
                        "} else {\n" +
                        "    // timed out\n" +
                        "}\n" +
                        "\n" +
                        "// Or wait until an absolute time point\n" +
                        "auto deadline = ch::steady_clock::now() + 500ms;\n" +
                        "cv.wait_until(lk, deadline, []{ return ready; });"
                    )
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
