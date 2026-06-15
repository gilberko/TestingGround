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
fun ObjectsOverviewScreen(navController: NavController) {
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
            text = "OBJECTS OVERVIEW",
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

        SectionHeader("THE OBJECT MANAGER")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "The Windows Object Manager (part of ntoskrnl.exe) is the kernel subsystem that " +
            "creates, tracks, and destroys kernel objects. It provides:\n\n" +
            "  Reference counting — objects are freed only when no references remain.\n" +
            "  A unified hierarchical namespace (rooted at \\) for named objects.\n" +
            "  Security — every named object can have a security descriptor.\n" +
            "  Type enforcement — each object has a type (OBJECT_TYPE) that defines " +
            "valid operations and security access rights.\n\n" +
            "You can browse the namespace with Sysinternals WinObj or the WinDbg " +
            "command !object \\."
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("WHAT IS A KERNEL OBJECT?")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Every kernel object is a heap allocation with a hidden OBJECT_HEADER prefix " +
            "immediately before the type-specific body. The header contains:\n\n" +
            "  PointerCount  — total reference count (kernel pointers).\n" +
            "  HandleCount   — number of open handles.\n" +
            "  Type          — pointer to OBJECT_TYPE describing this object class.\n" +
            "  SecurityDescriptor — access control for named objects.\n" +
            "  NameInfoOffset, CreatorInfoOffset — optional name and creator info.\n\n" +
            "The body that follows is the type-specific struct (e.g., _ETHREAD, _KEVENT). " +
            "Kernel APIs work with pointers to the body, not the header."
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("DISPATCHER OBJECTS")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "A dispatcher object is any kernel object whose body begins with a " +
            "DISPATCHER_HEADER. This header contains a SignalState field and a wait " +
            "list. Objects with this header can be waited on using " +
            "KeWaitForSingleObject or KeWaitForMultipleObjects.\n\n" +
            "Dispatcher objects:\n" +
            "  Event       (_KEVENT)    — signaled/not-signaled\n" +
            "  Mutex       (_KMUTEX)    — owned/not-owned\n" +
            "  Semaphore   (_KSEMAPHORE) — counting\n" +
            "  Timer       (_KTIMER)    — fires at a deadline\n" +
            "  Thread      (_KTHREAD inside _ETHREAD) — signaled when thread exits\n" +
            "  Process     (_KPROCESS inside _EPROCESS) — signaled when last thread exits\n\n" +
            "Non-dispatcher objects (File, Device, Driver, Token, etc.) cannot be waited on directly."
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("OBJECT REFERENCE TABLE")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "Object      Kernel Struct     Named?           Dispatcher?\n" +
            "────────────────────────────────────────────────────────────\n" +
            "Thread      _ETHREAD          Optional         Yes (on exit)\n" +
            "Process     _EPROCESS         Optional         Yes (on exit)\n" +
            "File        _FILE_OBJECT      No (by path)     No\n" +
            "Device      _DEVICE_OBJECT    Yes (\\Device\\)  No\n" +
            "Driver      _DRIVER_OBJECT    Yes (\\Driver\\)  No\n" +
            "Event       _KEVENT           Optional         Yes\n" +
            "Mutex       _KMUTEX           Optional         Yes\n" +
            "Semaphore   _KSEMAPHORE       Optional         Yes\n" +
            "Timer       _KTIMER           Optional         Yes\n" +
            "Section     SECTION_OBJECT    Optional         No\n" +
            "Callback    CALLBACK_OBJECT   Yes (\\Callback\\) No\n" +
            "Token       _TOKEN            No               No\n" +
            "Job         _EJOB             Optional         No"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("All of the above are reference counted by the Object Manager.")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("NAMED OBJECT DIRECTORIES")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Named objects live in directories within the object namespace:\n\n" +
            "  \\Device\\             — device objects (most drivers)\n" +
            "  \\Driver\\             — driver objects\n" +
            "  \\KernelObjects\\      — system events (LowMemoryCondition, etc.)\n" +
            "  \\Callback\\           — callback objects\n" +
            "  \\ObjectTypes\\        — object type objects\n" +
            "  \\BaseNamedObjects\\   — global named events/mutexes/semaphores\n" +
            "  \\Sessions\\0\\BaseNamedObjects\\ — user-mode named objects in session 0\n\n" +
            "Not all objects are named. File objects are never named through the object " +
            "manager (they are opened by path via the file system). Thread and process " +
            "objects are rarely named. Device and driver objects are almost always named."
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("ObReferenceObject FAMILY")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Increment reference count — prevents the object from being freed:")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock("ObReferenceObject(Object);")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Get object pointer from a handle (e.g., handle returned by PsCreateSystemThread):")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "PVOID obj;\n" +
            "ObReferenceObjectByHandle(\n" +
            "    handle,\n" +
            "    THREAD_ALL_ACCESS,  // desired access\n" +
            "    NULL,               // any object type\n" +
            "    KernelMode,\n" +
            "    &obj,\n" +
            "    NULL);              // optional handle info"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Look up a named object by its NT namespace path (kernel-mode only):")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "ObReferenceObjectByName(\n" +
            "    &name,              // UNICODE_STRING path\n" +
            "    OBJ_CASE_INSENSITIVE,\n" +
            "    NULL,               // access state\n" +
            "    0,                  // desired access\n" +
            "    *IoDeviceObjectType,// expected type\n" +
            "    KernelMode,\n" +
            "    NULL,\n" +
            "    (PVOID*)&obj);"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Decrement reference count — if it reaches 0, the object is freed:")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "ObDereferenceObject(obj);\n\n" +
            "// Tagged variant — useful for debugging leaked references:\n" +
            "ObDereferenceObjectWithTag(obj, 'MyTg');"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Every ObReferenceObject* call must be paired with exactly one ObDereferenceObject. " +
            "Missing the dereference leaks the object; dereferencing too many times causes a " +
            "bugcheck (attempt to free a still-referenced object)."
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("FINDING AN OBJECT BY NAME — EXAMPLE")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Suppose one driver needs to get a pointer to another driver's device object " +
            "by name. Use ObReferenceObjectByName with *IoDeviceObjectType:"
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "UNICODE_STRING name =\n" +
            "    RTL_CONSTANT_STRING(L\"\\\\Device\\\\TargetDevice\");\n" +
            "PDEVICE_OBJECT devObj = NULL;\n\n" +
            "NTSTATUS status = ObReferenceObjectByName(\n" +
            "    &name,\n" +
            "    OBJ_CASE_INSENSITIVE,\n" +
            "    NULL,\n" +
            "    0,\n" +
            "    *IoDeviceObjectType,\n" +
            "    KernelMode,\n" +
            "    NULL,\n" +
            "    (PVOID*)&devObj);\n\n" +
            "if (NT_SUCCESS(status)) {\n" +
            "    // Use devObj — it won't be freed while we hold the ref.\n" +
            "    IoCallDriver(devObj, irp);\n" +
            "    ObDereferenceObject(devObj);  // release when done\n" +
            "}"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "For user-mode objects (events, mutexes, sections), open them with ZwOpenEvent, " +
            "ZwOpenMutant, ZwOpenSection, etc., which return handles. Then use " +
            "ObReferenceObjectByHandle to get the object pointer from the handle."
        )

        Spacer(modifier = Modifier.height(32.dp))

        HackerButton("BACK") { navController.popBackStack() }
    }
}
