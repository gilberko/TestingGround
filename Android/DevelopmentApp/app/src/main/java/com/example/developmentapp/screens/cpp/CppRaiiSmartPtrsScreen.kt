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
fun CppRaiiSmartPtrsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "C/C++ — RAII and Smart Pointers",
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
                SectionCard("What Is RAII?") {
                    BodyText("RAII — Resource Acquisition Is Initialization — is a C++ idiom where a resource (memory, file handle, mutex, socket, etc.) is tied to the lifetime of an object: the constructor acquires the resource and the destructor releases it.")
                    BodyText("When the object goes out of scope, the destructor runs automatically — even if an exception is thrown. This makes resource management exception-safe without explicit try/finally blocks.")
                    CodeBlock("""
// Manual management — leak-prone
void bad() {
    FILE *f = fopen("data.txt", "r");
    if (!f) return;
    // ... if an exception or early return happens, f is never closed
    fclose(f);
}

// RAII wrapper — destructor always closes
struct FileGuard {
    FILE *f;
    FileGuard(const char *path) : f(fopen(path, "r")) {}
    ~FileGuard() { if (f) fclose(f); }   // always runs
};

void good() {
    FileGuard g("data.txt");
    // use g.f — file is closed when g goes out of scope
}

// std::lock_guard is the canonical RAII mutex wrapper
std::mutex mtx;
void safeFunc() {
    std::lock_guard<std::mutex> lock(mtx);  // locks here
    // ... mutex is unlocked when lock goes out of scope
}
                    """.trimIndent())
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("std::unique_ptr — Sole Ownership") {
                    BodyText("unique_ptr owns its object exclusively. It cannot be copied (copy constructor is deleted), but it can be moved to transfer ownership. When the unique_ptr is destroyed or reset, the managed object is deleted automatically.")
                    BodyText("Prefer std::make_unique over new — it is exception-safe and avoids repeating the type name.")
                    CodeBlock("""
#include <memory>

// Create
auto p = std::make_unique<int>(42);
// equivalent to: std::unique_ptr<int> p(new int(42));

// Access
printf("%d\n", *p);          // dereference
p->someMethod();             // arrow for objects

// Transfer ownership (move)
auto p2 = std::move(p);      // p is now null, p2 owns the int

// Release — caller takes ownership of raw pointer
int *raw = p2.release();     // p2 is now null, caller must delete raw
delete raw;

// Reset — delete current object, optionally assign new one
p2.reset(new int(99));       // deletes previous, owns new int
p2.reset();                  // deletes and becomes null

// Get raw pointer without giving up ownership
int *raw2 = p2.get();        // still owned by p2
                    """.trimIndent())
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("std::shared_ptr — Shared Ownership") {
                    BodyText("shared_ptr uses a reference count (stored in a control block on the heap). Each copy of the shared_ptr increments the count; each destructor decrements it. When the count reaches 0, the managed object is deleted.")
                    BodyText("Prefer std::make_shared — it allocates the object and the control block in a single allocation, improving cache performance and reducing overhead.")
                    CodeBlock("""
#include <memory>

auto sp1 = std::make_shared<std::string>("hello");
printf("%d\n", (int)sp1.use_count());  // 1

{
    auto sp2 = sp1;   // copy — count becomes 2
    printf("%d\n", (int)sp1.use_count());  // 2
    // sp2 goes out of scope — count drops to 1
}

printf("%d\n", (int)sp1.use_count());  // 1
// sp1 goes out of scope — count drops to 0, string is deleted

// Passing to functions
void take(std::shared_ptr<std::string> s) { ... }  // increments count
void view(const std::shared_ptr<std::string> &s) { ... }  // no count change
                    """.trimIndent())
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("Circular Reference Problem") {
                    BodyText("When two objects each hold a shared_ptr to the other, they form a cycle. The reference count of each object is always at least 1 (held by the other), so neither count ever reaches 0. The objects are never deleted — this is a memory leak even though you are using smart pointers.")
                    CodeBlock("""
struct B;  // forward declaration

struct A {
    std::shared_ptr<B> b_ptr;
    ~A() { printf("A destroyed\n"); }
};

struct B {
    std::shared_ptr<A> a_ptr;   // ← cycle!
    ~B() { printf("B destroyed\n"); }
};

{
    auto a = std::make_shared<A>();
    auto b = std::make_shared<B>();
    a->b_ptr = b;   // b's ref count = 2
    b->a_ptr = a;   // a's ref count = 2
}
// a and b go out of scope — counts drop to 1 each
// Neither destructor is ever called — LEAK
                    """.trimIndent())
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("std::weak_ptr — Breaking the Cycle") {
                    BodyText("weak_ptr is a non-owning observer of a shared_ptr-managed object. It does not increment the reference count. To actually use the object, call lock() — it returns a shared_ptr if the object still exists, or an empty shared_ptr if it has been deleted.")
                    BodyText("The fix for circular references: make one side of the cycle a weak_ptr instead of shared_ptr. The side that \"observes\" (the back-pointer) uses weak_ptr; the side that \"owns\" (the forward-pointer) uses shared_ptr.")
                    CodeBlock("""
struct B;

struct A {
    std::shared_ptr<B> b_ptr;   // A owns B
    ~A() { printf("A destroyed\n"); }
};

struct B {
    std::weak_ptr<A> a_ptr;     // B only observes A — no ownership
    ~B() { printf("B destroyed\n"); }

    void useA() {
        if (auto a = a_ptr.lock()) {   // try to get a shared_ptr
            // a is valid here — use it
            printf("A is alive\n");
        } else {
            printf("A is gone\n");
        }
    }
};

{
    auto a = std::make_shared<A>();  // ref count = 1
    auto b = std::make_shared<B>(); // ref count = 1
    a->b_ptr = b;    // b's ref count = 2
    b->a_ptr = a;    // a's ref count still = 1 (weak_ptr doesn't count)
}
// a out of scope — count = 0 → A destroyed → b_ptr released → b count = 0 → B destroyed
// Both destructors called. No leak.
                    """.trimIndent())
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
