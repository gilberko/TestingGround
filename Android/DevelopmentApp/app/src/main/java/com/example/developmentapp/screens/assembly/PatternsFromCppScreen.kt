package com.example.developmentapp.screens.assembly

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
fun PatternsFromCppScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Patterns from C\\C++",
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
            modifier       = Modifier.fillMaxSize().padding(innerPadding),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ) {
            // 1 — Why These Patterns Matter
            item {
                SectionCard("Why These Patterns Matter") {
                    BodyText(
                        "Compiled output is not a fixed translation of source code — it depends heavily on build " +
                        "configuration. Debug builds (MSVC /Od, GCC/Clang -O0) translate constructs almost " +
                        "literally: every local variable gets a real stack slot, every intermediate value is " +
                        "stored and reloaded, frame pointers are kept, and nothing is inlined. Release builds " +
                        "(-O2/-O3, MSVC /O2) optimize aggressively: dead stores vanish, small functions inline " +
                        "away, virtual calls may devirtualize, branches may become branchless, and loops may " +
                        "unroll. Many of the 'textbook' patterns below are far more reliable fingerprints in " +
                        "Debug disassembly than in Release — that distinction is called out section by section."
                    )
                }
            }

            // 2 — Stack Variables: Zeroing, 0xCD, 0xCC
            item {
                SectionCard("Stack Variables: Zeroing, 0xCD, 0xCC") {
                    BodyText(
                        "Explicit zero-initialization (e.g. 'int x = 0;' or 'char buf[64] = {0};') compiles to a " +
                        "direct write for a single scalar, or a fill loop / memset call for an array or struct:"
                    )
                    CodeBlock(
                        "mov  dword [ebp-4], 0        ; int x = 0;\n\n" +
                        "; char buf[64] = {0};  — larger blocks become a fill:\n" +
                        "lea   edi, [ebp-64]\n" +
                        "xor   eax, eax\n" +
                        "mov   ecx, 16\n" +
                        "rep   stosd                   ; or: push 64 / push 0 / push edi / call memset"
                    )
                    BodyText(
                        "Two other byte patterns are debug-build artifacts, not source-level zeroing — they come " +
                        "from the toolchain, not from your code. MSVC Debug builds with Run-Time Error Checks " +
                        "(/RTCs, on by default for Debug configs) insert a fill loop at the very top of the " +
                        "function, before any user code runs, stamping every local's stack slot with " +
                        "0xCCCCCCCC:"
                    )
                    CodeBlock(
                        "mov  eax, 0CCCCCCCCh\n" +
                        "mov  ecx, <dword count of locals>\n" +
                        "lea  edi, [ebp-N]\n" +
                        "rep  stosd                    ; fill entire local frame with 0xCC pattern"
                    )
                    BodyText(
                        "An uninitialized local that you forgot to set will still read back as 0xCCCCCCCC later — " +
                        "and because 0xCC happens to be the INT3 (breakpoint) opcode, accidentally jumping into " +
                        "that memory (e.g. through an uninitialized function pointer) traps straight into the " +
                        "debugger instead of executing garbage. 0xCD ('Clean Memory') is the heap's equivalent: " +
                        "the debug CRT fills newly allocated, not-yet-written heap memory with 0xCD, so 0xCC " +
                        "marks an uninitialized stack slot while 0xCD marks an uninitialized heap allocation. " +
                        "Both patterns disappear entirely in Release (no /RTCs, retail CRT) — except real " +
                        "zeroing that the optimizer is forbidden from deleting, such as " +
                        "SecureZeroMemory/RtlSecureZeroMemory/memset_s, which exist specifically because a plain " +
                        "memset() on a buffer that's never read afterward is dead-store-eliminated by Release " +
                        "optimizers."
                    )
                }
            }

            // 3 — fastcall
            item {
                SectionCard("Calling Convention: __fastcall") {
                    BodyText(
                        "32-bit MSVC __fastcall passes the first two integer/pointer arguments in ECX and EDX, " +
                        "remaining arguments right-to-left on the stack, and the callee cleans up the stack on " +
                        "return. Decorated symbol form: @FuncName@N (N = total bytes of stack arguments)."
                    )
                    CodeBlock(
                        "; int __fastcall Add3(int a, int b, int c)   — a→ECX, b→EDX, c on stack\n" +
                        "Add3:\n" +
                        "    push  ebp\n" +
                        "    mov   ebp, esp\n" +
                        "    mov   [ebp-4], ecx       ; spill 'a' to a local slot (common in Debug —\n" +
                        "    mov   [ebp-8], edx       ;  Release keeps it in the register if unused elsewhere)\n" +
                        "    ; ... function body ...\n" +
                        "    mov   esp, ebp\n" +
                        "    pop   ebp\n" +
                        "    ret   4                  ; callee pops the one stack arg ('c'), 4 bytes\n\n" +
                        "; call site:\n" +
                        "    mov   ecx, a\n" +
                        "    mov   edx, b\n" +
                        "    push  c\n" +
                        "    call  Add3\n" +
                        "    ; no stack adjustment here — the callee's RET 4 already cleaned it up"
                    )
                }
            }

            // 4 — stdcall
            item {
                SectionCard("Calling Convention: __stdcall") {
                    BodyText(
                        "__stdcall is the convention almost the entire Win32 API uses: all arguments right-to-left " +
                        "on the stack (no register arguments), and the callee cleans up via RET N. Decorated " +
                        "symbol form: _FuncName@N."
                    )
                    CodeBlock(
                        "; int __stdcall Add3(int a, int b, int c)\n" +
                        "_Add3@12:\n" +
                        "    push  ebp\n" +
                        "    mov   ebp, esp\n" +
                        "    mov   eax, [ebp+8]      ; first arg\n" +
                        "    mov   ecx, [ebp+12]     ; second arg\n" +
                        "    mov   edx, [ebp+16]     ; third arg\n" +
                        "    ; ... function body ...\n" +
                        "    mov   esp, ebp\n" +
                        "    pop   ebp\n" +
                        "    ret   12                 ; callee pops all 12 bytes of arguments\n\n" +
                        "; call site:\n" +
                        "    push  c\n" +
                        "    push  b\n" +
                        "    push  a\n" +
                        "    call  _Add3@12\n" +
                        "    ; again, no post-call cleanup — RET 12 already did it"
                    )
                }
            }

            // 5 — cdecl
            item {
                SectionCard("Calling Convention: __cdecl") {
                    BodyText(
                        "__cdecl is the default C/C++ convention: all arguments right-to-left on the stack, but " +
                        "this time the CALLER cleans up the stack after the call. Decorated symbol form: " +
                        "_FuncName (no @N suffix). It's the only one of the four that variadic functions " +
                        "(printf-style) can use, since the callee has no fixed argument count to base a RET N on."
                    )
                    CodeBlock(
                        "; int __cdecl Add3(int a, int b, int c)\n" +
                        "_Add3:\n" +
                        "    push  ebp\n" +
                        "    mov   ebp, esp\n" +
                        "    mov   eax, [ebp+8]\n" +
                        "    mov   ecx, [ebp+12]\n" +
                        "    mov   edx, [ebp+16]\n" +
                        "    ; ... function body ...\n" +
                        "    mov   esp, ebp\n" +
                        "    pop   ebp\n" +
                        "    ret                       ; plain return — callee does NOT adjust ESP\n\n" +
                        "; call site:\n" +
                        "    push  c\n" +
                        "    push  b\n" +
                        "    push  a\n" +
                        "    call  _Add3\n" +
                        "    add   esp, 12             ; caller restores the stack itself\n" +
                        "    ; (compilers sometimes batch this across several consecutive cdecl calls)"
                    )
                }
            }

            // 6 — thiscall
            item {
                SectionCard("Calling Convention: __thiscall") {
                    BodyText(
                        "MSVC's convention for non-static C++ member functions on 32-bit x86: the implicit 'this' " +
                        "pointer travels in ECX, any explicit arguments go right-to-left on the stack, and the " +
                        "callee cleans up — the same stack-cleanup rule as __stdcall, just with 'this' carved out " +
                        "into a register."
                    )
                    CodeBlock(
                        "; void Account::Deposit(int amount)   — 'this'→ECX, amount on stack\n" +
                        "?Deposit@Account@@QAEXH@Z:\n" +
                        "    push  ebp\n" +
                        "    mov   ebp, esp\n" +
                        "    mov   [ebp-4], ecx       ; spill 'this' to a local slot\n" +
                        "    mov   eax, [ebp-4]\n" +
                        "    mov   ecx, [ebp+8]       ; amount\n" +
                        "    add   [eax+4], ecx       ; this->balance += amount  (member at offset 4)\n" +
                        "    mov   esp, ebp\n" +
                        "    pop   ebp\n" +
                        "    ret   4\n\n" +
                        "; call site:  account.Deposit(50);\n" +
                        "    lea   ecx, [account]      ; ECX = &account ('this')\n" +
                        "    push  50\n" +
                        "    call  ?Deposit@Account@@QAEXH@Z"
                    )
                    BodyText(
                        "The 'this in ECX, then dereference [ecx+offset] for a member' shape is the disassembly " +
                        "fingerprint of a non-static member function call. GCC/the Itanium C++ ABI has no " +
                        "register-based thiscall at all — 'this' is simply the ordinary first parameter, pushed " +
                        "on the stack like any other cdecl argument. And on x86-64, all four of these 32-bit " +
                        "conventions collapse into one ABI: 'this' is just the implicit first argument in RCX " +
                        "(Windows x64) or RDI (System V) — there's no separate __thiscall to speak of."
                    )
                }
            }

            // 7 — switch/case
            item {
                SectionCard("switch / case") {
                    BodyText(
                        "Dense, contiguous case values compile to a bounds check followed by an indexed jump " +
                        "through a table of case addresses — O(1) dispatch regardless of how many cases exist:"
                    )
                    CodeBlock(
                        "    cmp   eax, 4              ; highest case value\n" +
                        "    ja    .default\n" +
                        "    jmp   [jump_table + eax*4]\n" +
                        "jump_table:\n" +
                        "    dd    .case0, .case1, .case2, .case3, .case4"
                    )
                    BodyText(
                        "Sparse case values make a jump table wasteful, so the compiler instead emits a chain of " +
                        "cmp/je comparisons, or — for more than a handful of sparse cases — a balanced " +
                        "binary-search tree of cmp/jl/jg comparisons that narrows the range in O(log n) steps. A " +
                        "switch with only two or three cases is usually indistinguishable from an equivalent " +
                        "if/else-if chain. The bounds check before the indexed jump is mandatory — without it, " +
                        "an out-of-range value would jump to whatever garbage address sits past the table."
                    )
                }
            }

            // 8 — if / else if / else
            item {
                SectionCard("if / else if / else") {
                    BodyText(
                        "A plain if/else compiles the condition inverted: the jcc tests for the OPPOSITE of the " +
                        "source condition and jumps PAST the true-block straight to the else-block, so the " +
                        "true-block itself is reached by falling through rather than by a taken jump:"
                    )
                    CodeBlock(
                        "    cmp   eax, 0\n" +
                        "    jle   .else_block        ; inverted: skip the 'if' body when NOT (eax > 0)\n" +
                        ".if_block:\n" +
                        "    ; ... if-true code ...\n" +
                        "    jmp   .end_if             ; unconditional skip over the else-block\n" +
                        ".else_block:\n" +
                        "    ; ... else code ...\n" +
                        ".end_if:"
                    )
                    BodyText(
                        "else-if chains are just a sequence of these guarded blocks stacked one after another, " +
                        "each jcc testing the next condition only if all previous ones were false. Release-only " +
                        "twist: a simple, side-effect-free if/else (e.g. picking between two values) can be " +
                        "'if-converted' into a branchless sequence using CMOVcc or SETcc, eliminating the jump " +
                        "entirely — a pattern you will essentially never see in Debug output."
                    )
                }
            }

            // 9 — Loops
            item {
                SectionCard("Loops") {
                    BodyText(
                        "A literal translation of a while/for loop checks the condition at the top and jumps back " +
                        "to it at the bottom — two branches taken per iteration (one to re-check, one back to the " +
                        "top). Compilers almost universally rotate this into a single trailing conditional branch " +
                        "instead, jumping into the condition check just once up front:"
                    )
                    CodeBlock(
                        "; for (i = 0; i < n; i++) { body(i); }\n" +
                        "    mov   dword [i], 0\n" +
                        "    jmp   .cond              ; one-time entry jump into the check\n" +
                        ".body:\n" +
                        "    ; ... body using i ...\n" +
                        "    inc   dword [i]\n" +
                        ".cond:\n" +
                        "    mov   eax, [i]\n" +
                        "    cmp   eax, [n]\n" +
                        "    jl    .body              ; only one conditional branch per iteration now"
                    )
                    BodyText(
                        "This 'loop rotation' is so common it shows up even at light optimization levels — its " +
                        "absence (a check-then-jump-back shape) is itself a signal of an unoptimized or very " +
                        "simple build. The legacy LOOP instruction (decrement ECX, jump if nonzero) is essentially " +
                        "never compiler-generated, since it's slower than cmp/jcc on every modern core — seeing " +
                        "it is a strong sign of hand-written assembly. Release builds (-O2/-O3) may additionally " +
                        "unroll short, fixed-trip-count loops into several inlined copies of the body plus a " +
                        "smaller remainder loop for the leftover iterations."
                    )
                }
            }

            // 10 — DLL Imports & the IAT (Windows)
            item {
                SectionCard("DLL Imports & the IAT (Windows)") {
                    BodyText(
                        "When code calls a function imported from a DLL, the compiler cannot bake in an absolute " +
                        "address: with ASLR, the DLL's base address (and therefore the function's address) isn't " +
                        "known until the loader actually maps it at runtime. Instead, the call goes through a " +
                        "fixed memory cell — a slot in the Import Address Table (IAT) — that the loader fills in " +
                        "with the real address while loading the executable."
                    )
                    CodeBlock(
                        "; Direct indirect call through the IAT slot:\n" +
                        "call  dword ptr [__imp__SomeFunction]   ; opcode FF 15 — CALL m32\n\n" +
                        "; Or, via a tiny linker-generated jump-stub trampoline:\n" +
                        "SomeFunction_stub:\n" +
                        "    jmp   dword ptr [__imp__SomeFunction]   ; opcode FF 25 — JMP m32\n" +
                        "; ...other code calls the stub with an ordinary relative call:\n" +
                        "    call  SomeFunction_stub                 ; CALL rel32, fixed address"
                    )
                    BodyText(
                        "The stub form exists because plain CALL rel32 instructions are cheaper to encode and " +
                        "patch than scattering indirect calls everywhere — every caller targets one fixed stub " +
                        "address, and that stub is the only place doing the indirection. The Windows loader walks " +
                        "the PE Import Directory Table at load time and patches every normal (non-delay-load) " +
                        "IAT slot eagerly, before the program's entry point ever runs. Delay-loaded imports " +
                        "(/DELAYLOAD) are the one built-in exception: the first call through a delay-loaded slot " +
                        "routes through a helper stub that performs LoadLibrary + GetProcAddress and patches the " +
                        "slot itself — every later call then hits the now-patched slot directly, like a normal " +
                        "import. This is opt-in per import; ordinary imports are never lazy on Windows."
                    )
                }
            }

            // 11 — Shared Objects & the PLT/GOT (Linux)
            item {
                SectionCard("Shared Objects & the PLT/GOT (Linux)") {
                    BodyText(
                        "ELF shared objects (.so) solve the same address-not-known-until-load-time problem with " +
                        "a different default: lazy binding, built into every dynamically linked call rather than " +
                        "opt-in. A call to an imported function compiles to a call through that function's " +
                        "Procedure Linkage Table (PLT) stub:"
                    )
                    CodeBlock(
                        "    call  SomeFunction@plt\n\n" +
                        "SomeFunction@plt:\n" +
                        "    jmp   [GOT+n]            ; jump through this function's Global Offset Table slot\n" +
                        "    push  reloc_index        ; (only reached the FIRST time — see below)\n" +
                        "    jmp   PLT0\n\n" +
                        "PLT0:                        ; shared resolver trampoline, one per binary\n" +
                        "    push  [GOT+1]             ; link_map pointer\n" +
                        "    jmp   [GOT+2]             ; jumps into ld.so's _dl_runtime_resolve"
                    )
                    BodyText(
                        "On the very first call, [GOT+n] doesn't yet hold the function's real address — it still " +
                        "points back into the PLT stub's own resolver path, which calls into the dynamic linker " +
                        "(ld.so). ld.so looks the symbol up in whichever loaded shared object exports it, then " +
                        "patches [GOT+n] in place with the resolved address. Every later call through that same " +
                        "PLT stub now jumps straight through the now-patched GOT slot to the real function — one " +
                        "extra indirection forever, but the expensive symbol lookup happens only once."
                    )
                    BodyText(
                        "This lazy-by-default behavior can be turned off: setting LD_BIND_NOW=1, or linking with " +
                        "full RELRO (-z now), makes ld.so resolve and patch every GOT entry up front at load time " +
                        "instead of on first use, then mprotect the GOT read-only — closing off GOT-overwrite " +
                        "exploits at the cost of slightly slower startup. That eager mode is the direct " +
                        "equivalent of how Windows always resolves normal (non-delay-load) IAT entries. The far " +
                        "more common partial RELRO only makes the data segment's relocations read-only early; " +
                        "the lazy function PLT/GOT path described above stays exactly as shown."
                    )
                }
            }

            // 12 — Virtual Function Calls
            item {
                SectionCard("Virtual Function Calls") {
                    BodyText(
                        "Calling a virtual function requires two extra loads beyond a normal call: first fetch the " +
                        "object's vptr (a hidden pointer to its vtable, stored as the object's first member under " +
                        "both the Itanium and MSVC ABIs for single inheritance), then index into the vtable at " +
                        "the function's fixed slot to get the actual function address:"
                    )
                    CodeBlock(
                        "; obj->VirtualMethod();    'this' already in ECX/RCX per the active convention\n" +
                        "    mov   eax, [ecx]         ; eax = vptr  (load from *this)\n" +
                        "    call  [eax+8]            ; call through vtable slot 2 (offset 8 = slot*4 on x86-32)\n\n" +
                        "; x86-64 equivalent:\n" +
                        "    mov   rax, [rcx]         ; rax = vptr\n" +
                        "    call  qword ptr [rax+16] ; slot 2, offset = slot*8"
                    )
                    BodyText(
                        "Compare this to a non-virtual call: a plain 'call FunctionAddress' with no memory " +
                        "indirection at all, resolved once at link time. That double indirection — object to vptr, " +
                        "vptr to slot, slot to function — is the disassembly fingerprint that distinguishes " +
                        "virtual dispatch from every other kind of call."
                    )
                    BodyText(
                        "Release-only twist: when the optimizer can prove the concrete type at a call site — a " +
                        "local stack object, or after inlining narrows it down — it can devirtualize: collapse " +
                        "the vtable indirection into a plain direct call (and often inline the function entirely). " +
                        "So a function marked 'virtual' in source is not a guarantee of vtable indirection in the " +
                        "binary. Debug builds reliably show the full two-step indirection above, since " +
                        "devirtualization needs whole-program-style analysis that's normally disabled in Debug."
                    )
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
