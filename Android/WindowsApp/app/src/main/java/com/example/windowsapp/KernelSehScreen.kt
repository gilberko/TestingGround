package com.example.windowsapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.windowsapp.ui.theme.HackerGreen

@Composable
fun KernelSehScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp, vertical = 48.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "TRY / CATCH IN THE KERNEL",
            color = HackerGreen,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "─".repeat(28),
            color = HackerGreen,
            fontFamily = FontFamily.Monospace,
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(24.dp))

        SectionHeader("C++ EXCEPTIONS VS SEH")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "If you are coming from user-mode C++ development, you might expect to use throw and catch in kernel drivers. You cannot.\n\n" +
            "C++ exception handling (throw / try / catch) requires the C++ runtime library (CRT) — specifically, the runtime support for __cxa_throw, std::type_info, exception matching, and the C++ personality routine. None of this is available in the Windows kernel environment (IRQL constraints, no CRT, no C++ standard library).\n\n" +
            "What the kernel does have is Structured Exception Handling (SEH) — a compiler and OS feature that predates C++ exceptions and operates at a lower level. SEH uses:\n" +
            "  __try    — marks a guarded block\n" +
            "  __except — catches and handles exceptions\n" +
            "  __finally — executes cleanup code regardless of how the block exits\n\n" +
            "SEH is a Microsoft extension to C and C++. It is fully supported in kernel driver code compiled with MSVC. It works at PASSIVE_LEVEL, APC_LEVEL, and DISPATCH_LEVEL (with important restrictions — see below)."
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("__try / __except BASICS")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Basic syntax and behavior of SEH exception handling:"
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "__try {\n" +
            "    // Code that might raise an exception\n" +
            "    *(PULONG)SomeAddress = 0;  // might fault if SomeAddress is bad\n" +
            "} __except (EXCEPTION_EXECUTE_HANDLER) {\n" +
            "    // Handler — runs if an exception occurred in the __try block\n" +
            "    KdPrint((\"Caught an exception\\n\"));\n" +
            "    // Execution continues here after the __except block\n" +
            "}\n" +
            "// Execution continues here after the entire __try/__except construct\n" +
            "\n" +
            "// Filter expression return values:\n" +
            "// EXCEPTION_EXECUTE_HANDLER  (1)  — run the handler, then continue after __except\n" +
            "// EXCEPTION_CONTINUE_SEARCH  (0)  — propagate exception up the call stack\n" +
            "// EXCEPTION_CONTINUE_EXECUTION (-1) — retry the faulting instruction\n" +
            "//   WARNING: EXCEPTION_CONTINUE_EXECUTION is dangerous and rarely correct\n" +
            "\n" +
            "// Example: only handle access violations\n" +
            "__try {\n" +
            "    x = *(PULONG)ptr;\n" +
            "} __except (GetExceptionCode() == STATUS_ACCESS_VIOLATION\n" +
            "             ? EXCEPTION_EXECUTE_HANDLER\n" +
            "             : EXCEPTION_CONTINUE_SEARCH) {\n" +
            "    // Only runs for access violations, other exceptions propagate\n" +
            "    status = STATUS_INVALID_ADDRESS;\n" +
            "}"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("EXCEPTION FILTERS IN DETAIL")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "The expression inside __except(...) is the filter expression. It is evaluated before the handler runs, to decide what to do with the exception.\n\n" +
            "Two key intrinsics available only inside the filter expression:"
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "// GetExceptionCode() — returns the NTSTATUS exception code\n" +
            "// Can only be called directly in the filter expression (not in a function called from it)\n" +
            "\n" +
            "// GetExceptionInformation() — returns EXCEPTION_POINTERS*\n" +
            "// Points to two structures describing the exception:\n" +
            "\n" +
            "LONG MyExceptionFilter(PEXCEPTION_POINTERS pExInfo) {\n" +
            "    PEXCEPTION_RECORD rec = pExInfo->ExceptionRecord;\n" +
            "    PCONTEXT ctx = pExInfo->ContextRecord;\n" +
            "\n" +
            "    KdPrint((\"Exception 0x%08X at address %p\\n\",\n" +
            "             rec->ExceptionCode,\n" +
            "             rec->ExceptionAddress));\n" +
            "\n" +
            "    // ExceptionRecord also contains:\n" +
            "    // rec->NumberParameters — count of exception parameters\n" +
            "    // rec->ExceptionInformation[] — parameter array\n" +
            "    // For STATUS_ACCESS_VIOLATION:\n" +
            "    //   [0] = 0 (read) or 1 (write)\n" +
            "    //   [1] = the faulting address\n" +
            "\n" +
            "    // ContextRecord contains all CPU registers at the time of the fault\n" +
            "    KdPrint((\"RIP=%p RSP=%p\\n\", (PVOID)ctx->Rip, (PVOID)ctx->Rsp));\n" +
            "\n" +
            "    return EXCEPTION_EXECUTE_HANDLER;\n" +
            "}\n" +
            "\n" +
            "// Using it — GetExceptionInformation() is called in filter position:\n" +
            "__try {\n" +
            "    // risky code\n" +
            "} __except (MyExceptionFilter(GetExceptionInformation())) {\n" +
            "    // handler\n" +
            "}"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("__try / __finally")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "__finally guarantees that cleanup code runs regardless of how the __try block exits — whether it exits normally (falls through), via a goto/return (\"abnormal\" exit), or due to an exception unwinding through the frame.\n\n" +
            "Unlike __except, __finally does NOT catch or stop the exception — it only runs cleanup. The exception continues to propagate up the stack after __finally completes."
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "ExAcquireFastMutex(&g_mutex);\n" +
            "__try {\n" +
            "    // Do work — safe to return or raise exceptions here\n" +
            "    if (conditionFailed) {\n" +
            "        ExRaiseStatus(STATUS_INSUFFICIENT_RESOURCES); // will still release mutex\n" +
            "    }\n" +
            "    ProcessData();\n" +
            "} __finally {\n" +
            "    // Always runs — whether ProcessData returned normally,\n" +
            "    // or ExRaiseStatus was called, or an access violation occurred\n" +
            "    ExReleaseFastMutex(&g_mutex);\n" +
            "\n" +
            "    // AbnormalTermination() returns TRUE if we got here due to\n" +
            "    // an exception or abnormal exit, FALSE if normal fall-through\n" +
            "    if (AbnormalTermination()) {\n" +
            "        KdPrint((\"Cleanup after exception/abnormal exit\\n\"));\n" +
            "    }\n" +
            "}\n" +
            "\n" +
            "// CAUTION: Do NOT use 'return' inside __try when using __finally\n" +
            "// in kernel code — it causes an implicit unwinding that can\n" +
            "// be expensive and may interact badly with IRQL management."
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("READING POTENTIALLY INVALID MEMORY")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "A common kernel driver task is reading a buffer provided by user mode. If the user passes a bad pointer, the kernel must not crash. The correct pattern uses ProbeForRead followed by __try/__except.\n\n" +
            "Why both? ProbeForRead validates that the address range is in user-mode address space and is properly aligned — it raises an exception if not. But even if the probe passes, the memory might be unmapped or become invalid before the actual read. __try catches any fault during the read itself."
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "NTSTATUS SafeReadUserBuffer(\n" +
            "    PVOID UserBuffer,\n" +
            "    ULONG Length,\n" +
            "    PVOID KernelDest)\n" +
            "{\n" +
            "    NTSTATUS status = STATUS_SUCCESS;\n" +
            "\n" +
            "    __try {\n" +
            "        // Step 1: Probe — raises exception if address is kernel-mode\n" +
            "        // or not aligned to the specified alignment (here: 1 byte)\n" +
            "        ProbeForRead(UserBuffer, Length, 1);\n" +
            "\n" +
            "        // Step 2: Perform the actual read inside __try\n" +
            "        // If the page becomes unmapped between probe and read,\n" +
            "        // __except catches the resulting access violation\n" +
            "        RtlCopyMemory(KernelDest, UserBuffer, Length);\n" +
            "\n" +
            "    } __except (EXCEPTION_EXECUTE_HANDLER) {\n" +
            "        status = GetExceptionCode();\n" +
            "        KdPrint((\"SafeReadUserBuffer: exception 0x%08X\\n\", status));\n" +
            "    }\n" +
            "\n" +
            "    return status;\n" +
            "}"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "For writable buffers provided by user mode, use ProbeForWrite instead. ProbeForWrite additionally writes a test byte to verify the memory is writable (not a read-only mapping)."
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("ProbeForRead AND ProbeForWrite")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "ProbeForRead and ProbeForWrite are kernel APIs that validate user-mode buffer addresses before the driver attempts to access them."
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "// Signatures:\n" +
            "VOID ProbeForRead(\n" +
            "    const volatile VOID *Address,\n" +
            "    SIZE_T Length,\n" +
            "    ULONG Alignment);  // 1, 2, 4, or 8\n" +
            "\n" +
            "VOID ProbeForWrite(\n" +
            "    volatile VOID *Address,\n" +
            "    SIZE_T Length,\n" +
            "    ULONG Alignment);\n" +
            "\n" +
            "// What ProbeForRead checks:\n" +
            "// 1. Address is in user-mode address space\n" +
            "//    (i.e., Address < MmUserProbeAddress)\n" +
            "//    If kernel-mode address: raises STATUS_ACCESS_VIOLATION\n" +
            "// 2. Address is aligned to Alignment bytes\n" +
            "//    If not: raises STATUS_DATATYPE_MISALIGNMENT\n" +
            "// 3. Address + Length does not overflow user space\n" +
            "//    If it wraps: raises STATUS_ACCESS_VIOLATION\n" +
            "\n" +
            "// What ProbeForRead does NOT check:\n" +
            "// - Whether the pages are currently mapped/accessible\n" +
            "// - Whether the memory remains valid after the call returns\n" +
            "// Therefore: ALWAYS call inside __try, AND keep the actual access\n" +
            "// inside the same __try block\n" +
            "\n" +
            "// MUST be called at PASSIVE_LEVEL\n" +
            "// (Probing involves potential page faults which require PASSIVE_LEVEL)"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("HOW SEH WORKS UNDER THE HOOD")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "The implementation of SEH differs between x86 and x64:\n\n" +
            "x64 — Table-Based (zero overhead on happy path):\n" +
            "  The MSVC compiler emits a .pdata section containing RUNTIME_FUNCTION entries for every function in the binary. Each entry maps a range of code (BeginAddress → EndAddress) to an UNWIND_INFO structure.\n\n" +
            "  UNWIND_INFO describes:\n" +
            "    - How to unwind the stack frame (push/pop sequences, frame pointer)\n" +
            "    - The exception handler (ExceptionHandler field) — points to __C_specific_handler for SEH\n" +
            "    - The handler data (scope table) — describes the __try/__except/__finally nesting\n\n" +
            "  On exception:\n" +
            "    1. RtlLookupFunctionEntry(RIP) — find the RUNTIME_FUNCTION for the faulting RIP\n" +
            "    2. RtlVirtualUnwind — walk the UNWIND_INFO to simulate unwinding\n" +
            "    3. __C_specific_handler evaluates the __except filter at each scope\n" +
            "    4. If filter returns EXCEPTION_EXECUTE_HANDLER → unwind stack + transfer control\n\n" +
            "  No overhead on the happy path because there are no registrations at runtime — just static tables.\n\n" +
            "x86 — Frame-Based (runtime overhead on entry/exit of each __try block):\n" +
            "  On x86, the OS uses a per-thread exception registration chain stored at FS:[0] (Thread Environment Block offset 0).\n" +
            "  Each __try block pushes an EXCEPTION_REGISTRATION_RECORD onto the stack and links it into the chain:\n\n" +
            "    struct EXCEPTION_REGISTRATION_RECORD {\n" +
            "        struct EXCEPTION_REGISTRATION_RECORD *Next;  // previous record\n" +
            "        PEXCEPTION_ROUTINE Handler;                   // dispatch routine\n" +
            "    };\n\n" +
            "  On exception, the OS walks FS:[0] chain calling each handler in turn. This has runtime overhead (push/pop on __try entry/exit) and is vulnerable to stack-based attacks that overwrite the chain — hence x64 moved to the table-based model."
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("EXCEPTIONS THAT CANNOT BE CAUGHT")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Not all exceptional conditions can be caught with __try/__except in kernel code:\n\n" +
            "KeBugCheckEx — NOT an exception:\n" +
            "  KeBugCheckEx does not raise a software exception. It immediately halts the system (BSOD). There is no way to catch or recover from a bug check using SEH. The machine stops, writes a kernel dump, and reboots. KeRegisterBugCheckCallback can run code during the crash, but cannot prevent the BSOD.\n\n" +
            "Hardware double fault:\n" +
            "  If a fault occurs while processing another fault (e.g., a fault in the fault handler itself, or during NMI), the CPU generates a double fault exception (#DF). This is handled by a dedicated processor TSS and is unrecoverable — the system always BSODs.\n\n" +
            "Stack overflow in kernel:\n" +
            "  Kernel threads have a limited stack (typically 12KB on x64). Excessive recursion causes a kernel stack overflow. The overflow itself raises EXCEPTION_STACK_OVERFLOW (0xC00000FD). You can attempt to catch it with __except, but since the stack is already overflowed, there may not be enough stack space to run the handler reliably. This typically results in a BSOD (KERNEL_STACK_INPAGE_ERROR or similar).\n\n" +
            "Non-continuable exceptions:\n" +
            "  Some exceptions have the EXCEPTION_NONCONTINUABLE flag set in ExceptionRecord.ExceptionFlags. If an __except filter returns EXCEPTION_CONTINUE_EXECUTION for a non-continuable exception, the OS raises STATUS_NONCONTINUABLE_EXCEPTION immediately — another exception on top of the current one. This cascades until the thread is terminated or the system crashes.\n\n" +
            "Exceptions that CAN be caught:\n" +
            "  STATUS_ACCESS_VIOLATION (0xC0000005) — the most common one caught in drivers\n" +
            "  STATUS_INTEGER_DIVIDE_BY_ZERO (0xC0000094) — divide by zero\n" +
            "  STATUS_ILLEGAL_INSTRUCTION (0xC000001D) — invalid opcode\n" +
            "  STATUS_INTEGER_OVERFLOW (0xC0000095) — INT overflow (if detected)\n" +
            "  ExRaiseStatus / ExRaiseAccessViolation — software exceptions raised intentionally"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("THROWING EXCEPTIONS IN KERNEL CODE")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Kernel code can raise software exceptions using ExRaiseStatus and related functions. These are SEH-compatible — they can be caught by __try/__except in the caller's call stack."
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "// ExRaiseStatus — raise any NTSTATUS as an exception\n" +
            "// This exception can be caught by __except in any caller frame\n" +
            "DECLSPEC_NORETURN VOID ExRaiseStatus(NTSTATUS Status);\n" +
            "\n" +
            "// ExRaiseAccessViolation — specifically raises STATUS_ACCESS_VIOLATION\n" +
            "DECLSPEC_NORETURN VOID ExRaiseAccessViolation(VOID);\n" +
            "\n" +
            "// RtlRaiseException — raise a custom exception record\n" +
            "VOID RtlRaiseException(PEXCEPTION_RECORD ExceptionRecord);\n" +
            "\n" +
            "// Example: internal kernel helper that signals an error\n" +
            "// by raising an exception rather than returning NTSTATUS\n" +
            "VOID ParseUserData(PVOID data, SIZE_T length) {\n" +
            "    if (length < sizeof(MY_HEADER)) {\n" +
            "        ExRaiseStatus(STATUS_INVALID_BUFFER_SIZE);\n" +
            "        // DECLSPEC_NORETURN — no return here\n" +
            "    }\n" +
            "    // ... parse the data ...\n" +
            "}\n" +
            "\n" +
            "// Caller wraps in __try to catch it:\n" +
            "NTSTATUS DispatchControl(PDEVICE_OBJECT DevObj, PIRP Irp) {\n" +
            "    NTSTATUS status;\n" +
            "    __try {\n" +
            "        ParseUserData(inputBuf, inputLen);\n" +
            "        status = STATUS_SUCCESS;\n" +
            "    } __except (EXCEPTION_EXECUTE_HANDLER) {\n" +
            "        status = GetExceptionCode();\n" +
            "    }\n" +
            "    return status;\n" +
            "}\n" +
            "\n" +
            "// IMPORTANT: ExRaiseStatus is NOT C++ throw\n" +
            "// It raises a Windows SEH exception, not a C++ exception\n" +
            "// The caller uses __except, not catch(...)"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("SUMMARY AND BEST PRACTICES")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "When to use SEH in kernel drivers:\n\n" +
            "At IRP dispatch entry points:\n" +
            "  Wrap the body of IRP_MJ_DEVICE_CONTROL and other dispatch routines in __try/__except as a last-resort safety net. If an unexpected exception occurs, return a failure NTSTATUS rather than crashing.\n\n" +
            "When accessing user-mode buffers:\n" +
            "  Always use ProbeForRead/Write followed by the actual access inside __try/__except. Never trust user-provided pointers without this pattern.\n\n" +
            "For lock release (use __finally):\n" +
            "  If you acquire a lock (spin lock, FAST_MUTEX, ERESOURCE) and then do something that might raise an exception, put the release in __finally to guarantee it is always released.\n\n" +
            "What NOT to do:\n" +
            "  Do NOT catch exceptions to silently hide bugs. An access violation in core driver logic is a bug, not a recoverable condition. Use SEH at boundary points, not as a general error-suppression mechanism.\n\n" +
            "IRQL considerations:\n" +
            "  SEH can be used at DISPATCH_LEVEL, but with caution. Stack unwinding at DISPATCH_LEVEL can be problematic — some cleanup operations (like releasing a FAST_MUTEX or ERESOURCE) require PASSIVE_LEVEL or APC_LEVEL and cannot safely run during unwind at DISPATCH_LEVEL. ProbeForRead must be called at PASSIVE_LEVEL (it may page fault)."
        )

        Spacer(modifier = Modifier.height(32.dp))

        HackerButton("BACK") { navController.popBackStack() }
    }
}
