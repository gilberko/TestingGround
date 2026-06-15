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
fun RegisteringForCallbacksScreen(navController: NavController) {
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
            text = "REGISTERING FOR CALLBACKS",
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

        // CALLBACK OBJECTS
        SectionHeader("CALLBACK OBJECTS (ExCreateCallback)")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "The Executive provides a general publish/subscribe mechanism via named callback " +
            "objects. Any driver can create a callback object, notify it, or register to " +
            "receive its notifications."
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "// Create or open a callback object\n" +
            "PCALLBACK_OBJECT CallbackObject;\n" +
            "UNICODE_STRING Name;\n" +
            "OBJECT_ATTRIBUTES ObjAttr;\n\n" +
            "RtlInitUnicodeString(&Name, L\"\\\\Callback\\\\MyDriverCallback\");\n" +
            "InitializeObjectAttributes(&ObjAttr, &Name, OBJ_CASE_INSENSITIVE, NULL, NULL);\n\n" +
            "ExCreateCallback(\n" +
            "    &CallbackObject,\n" +
            "    &ObjAttr,\n" +
            "    TRUE,    // Create if it doesn't exist\n" +
            "    TRUE);   // Allow multiple registrations\n\n" +
            "// Register to receive notifications\n" +
            "PVOID Registration = ExRegisterCallback(\n" +
            "    CallbackObject,\n" +
            "    MyCallbackRoutine,  // VOID (*)(PVOID Context, PVOID Arg1, PVOID Arg2)\n" +
            "    MyContext);\n\n" +
            "// Notify all registered callbacks\n" +
            "ExNotifyCallback(CallbackObject, Arg1, Arg2);\n\n" +
            "// Unregister\n" +
            "ExUnregisterCallback(Registration);\n" +
            "ObDereferenceObject(CallbackObject);"
        )

        Spacer(modifier = Modifier.height(16.dp))

        // KNOWN CALLBACK OBJECTS
        SectionHeader("KNOWN CALLBACK OBJECTS")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Windows creates several named callback objects that drivers can open with " +
            "ExCreateCallback (Create=FALSE) to monitor system events:"
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "\\Callback\\SetSystemTime\n" +
            "  Notified when the system time is changed.\n" +
            "  Arg1 = pointer to old time, Arg2 = pointer to new time.\n\n" +
            "\\Callback\\PowerState\n" +
            "  Notified on power state transitions (sleep, hibernate, resume).\n\n" +
            "\\Callback\\ProcessorAdd\n" +
            "  Notified when a processor is hot-added (NUMA / server systems).\n\n" +
            "\\Callback\\SecurityPolicyChange\n" +
            "  Notified when a security policy value (LSA) changes.\n\n" +
            "\\Callback\\NetworkProviderLoad\n" +
            "  Notified when a network provider DLL is loaded."
        )

        Spacer(modifier = Modifier.height(16.dp))

        // LOAD IMAGE NOTIFY
        SectionHeader("LOAD IMAGE NOTIFY")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "PsSetLoadImageNotifyRoutine registers a callback that fires every time an " +
            "image (EXE or DLL, user or kernel) is mapped into memory."
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "// Register\n" +
            "PsSetLoadImageNotifyRoutine(MyLoadImageCallback);\n\n" +
            "// Remove\n" +
            "PsRemoveLoadImageNotifyRoutine(MyLoadImageCallback);\n\n" +
            "// Also available with flags:\n" +
            "PsSetLoadImageNotifyRoutineEx(MyLoadImageCallback, PS_IMAGE_NOTIFY_CONFLICTING_ARCHITECTURE);\n\n" +
            "// Callback signature\n" +
            "VOID MyLoadImageCallback(\n" +
            "    PUNICODE_STRING FullImageName, // full path of the image\n" +
            "    HANDLE          ProcessId,     // process being loaded into (NULL=kernel)\n" +
            "    PIMAGE_INFO     ImageInfo      // base, size, SystemModeImage flag\n" +
            ") {\n" +
            "    if (ImageInfo->SystemModeImage)\n" +
            "        // kernel driver being loaded\n" +
            "    else\n" +
            "        // user-mode DLL / EXE being loaded\n" +
            "}"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Synchronous: the callback is called synchronously as part of the image load. " +
            "The load is blocked (paused) while your callback runs — the image is already " +
            "mapped but execution has not started.\n\n" +
            "Can you block the load? No — the callback is void; there is no return value " +
            "to fail the load from this callback. You can observe and log, but not prevent."
        )

        Spacer(modifier = Modifier.height(16.dp))

        // REGISTRY CALLBACKS
        SectionHeader("REGISTRY CALLBACKS (CmRegisterCallback)")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "CmRegisterCallback(Ex) intercepts registry operations before and after " +
            "they execute. Your callback receives the operation class and an operation-" +
            "specific information structure."
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "LARGE_INTEGER Cookie;\n\n" +
            "// Register (preferred: Ex variant with altitude)\n" +
            "CmRegisterCallbackEx(\n" +
            "    MyRegistryCallback,\n" +
            "    &Altitude,         // UNICODE_STRING altitude string e.g. L\"385200\"\n" +
            "    DriverObject,\n" +
            "    NULL,              // context\n" +
            "    &Cookie,\n" +
            "    NULL);\n\n" +
            "// Unregister\n" +
            "CmUnRegisterCallback(Cookie);\n\n" +
            "// Callback signature\n" +
            "NTSTATUS MyRegistryCallback(\n" +
            "    PVOID CallbackContext,\n" +
            "    PVOID Argument1,   // REG_NOTIFY_CLASS (operation type)\n" +
            "    PVOID Argument2    // operation-specific struct\n" +
            ") {\n" +
            "    REG_NOTIFY_CLASS notifyClass = (REG_NOTIFY_CLASS)(ULONG_PTR)Argument1;\n" +
            "    if (notifyClass == RegNtPreSetValueKey) {\n" +
            "        PREG_SET_VALUE_KEY_INFORMATION info =\n" +
            "            (PREG_SET_VALUE_KEY_INFORMATION)Argument2;\n" +
            "        // Inspect info->ValueName, info->Data, etc.\n" +
            "        // Block the write:\n" +
            "        return STATUS_ACCESS_DENIED;\n" +
            "    }\n" +
            "    return STATUS_SUCCESS;  // allow all others\n" +
            "}"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Pre vs Post operations:\n" +
            "  RegNtPreXxx  — called before the operation; returning a non-SUCCESS status\n" +
            "                 BLOCKS the operation (the registry write/delete never happens)\n" +
            "  RegNtPostXxx — called after the operation completes; cannot block it\n\n" +
            "Common pre-operation classes:\n" +
            "  RegNtPreCreateKey, RegNtPreOpenKey\n" +
            "  RegNtPreSetValueKey, RegNtPreDeleteValueKey\n" +
            "  RegNtPreDeleteKey, RegNtPreQueryValueKey\n" +
            "  RegNtPreQueryKey, RegNtPreEnumerateKey"
        )

        Spacer(modifier = Modifier.height(16.dp))

        // OBJECT CALLBACKS
        SectionHeader("OBJECT CALLBACKS (ObRegisterCallbacks)")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "ObRegisterCallbacks intercepts handle creation and duplication for processes, " +
            "threads, and desktop objects. Used by EDR products and anti-cheat drivers to " +
            "protect sensitive processes."
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "OB_OPERATION_REGISTRATION opReg = {};\n" +
            "opReg.ObjectType  = PsProcessType;   // or PsThreadType / ExDesktopObjectType\n" +
            "opReg.Operations  = OB_OPERATION_HANDLE_CREATE |\n" +
            "                    OB_OPERATION_HANDLE_DUPLICATE;\n" +
            "opReg.PreOperation  = MyPreOpCallback;\n" +
            "opReg.PostOperation = MyPostOpCallback; // optional\n\n" +
            "OB_CALLBACK_REGISTRATION cbReg = {};\n" +
            "cbReg.Version                    = OB_FLT_REGISTRATION_VERSION;\n" +
            "cbReg.OperationRegistrationCount = 1;\n" +
            "cbReg.Altitude                   = RtlInitUnicodeString(..., L\"385200\");\n" +
            "cbReg.OperationRegistration      = &opReg;\n\n" +
            "PVOID Handle;\n" +
            "ObRegisterCallbacks(&cbReg, &Handle);\n\n" +
            "// Unregister\n" +
            "ObUnRegisterCallbacks(Handle);"
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "// Pre-operation callback\n" +
            "OB_PREOP_CALLBACK_STATUS MyPreOpCallback(\n" +
            "    PVOID Context,\n" +
            "    POB_PRE_OPERATION_INFORMATION Info\n" +
            ") {\n" +
            "    if (Info->ObjectType == PsProcessType) {\n\n" +
            "        // Detect new handle being opened:\n" +
            "        if (Info->Operation == OB_OPERATION_HANDLE_CREATE)\n" +
            "            // someone is calling OpenProcess on this target\n\n" +
            "        // Detect handle duplication:\n" +
            "        if (Info->Operation == OB_OPERATION_HANDLE_DUPLICATE)\n" +
            "            // DuplicateHandle on this target\n\n" +
            "        // Strip PROCESS_TERMINATE from the requested access mask:\n" +
            "        Info->Parameters->CreateHandleInformation.DesiredAccess\n" +
            "            &= ~PROCESS_TERMINATE;\n" +
            "        // The handle is created but with reduced rights\n" +
            "    }\n" +
            "    return OB_PREOP_SUCCESS;\n" +
            "}"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Blocking: you cannot return a failure code from the pre-callback — the return " +
            "type only allows OB_PREOP_SUCCESS. To effectively block an access, strip all " +
            "meaningful bits from DesiredAccess (set it to 0 or to a harmless value). " +
            "The caller's OpenProcess/NtOpenThread will succeed but receive a handle with " +
            "no useful rights — subsequent operations using it will fail.\n\n" +
            "Requirement: the driver image must be signed and its altitude must be valid. " +
            "Altitude determines stack order when multiple drivers register callbacks."
        )

        Spacer(modifier = Modifier.height(16.dp))

        // PROCESS/THREAD NOTIFY
        SectionHeader("PROCESS AND THREAD NOTIFY")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Note: the actual API names are PsSetCreateProcessNotifyRoutine and " +
            "PsSetCreateThreadNotifyRoutine (not PsSetProcessNotifyRoutine / " +
            "PsSetThreadNotifyRoutine, which do not exist)."
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "// Process notifications\n" +
            "PsSetCreateProcessNotifyRoutine(\n" +
            "    MyProcessCallback, FALSE);  // FALSE = add, TRUE = remove\n\n" +
            "// Extended variant — preferred; gives more info and allows blocking\n" +
            "PsSetCreateProcessNotifyRoutineEx(\n" +
            "    MyProcessCallbackEx, FALSE);\n\n" +
            "// Thread notifications\n" +
            "PsSetCreateThreadNotifyRoutine(MyThreadCallback);\n" +
            "PsRemoveCreateThreadNotifyRoutine(MyThreadCallback);\n\n" +
            "// Process callback (Ex variant)\n" +
            "VOID MyProcessCallbackEx(\n" +
            "    PEPROCESS            Process,\n" +
            "    HANDLE               ProcessId,\n" +
            "    PPS_CREATE_NOTIFY_INFO CreateInfo  // NULL on process exit\n" +
            ") {\n" +
            "    if (CreateInfo) {\n" +
            "        // Process is being CREATED\n" +
            "        // CreateInfo->ImageFileName, CreateInfo->CommandLine available\n" +
            "        // Block the creation:\n" +
            "        CreateInfo->CreationStatus = STATUS_ACCESS_DENIED;\n" +
            "    } else {\n" +
            "        // Process is EXITING — CreateInfo is NULL\n" +
            "    }\n" +
            "}\n\n" +
            "// Thread callback\n" +
            "VOID MyThreadCallback(\n" +
            "    HANDLE ProcessId, HANDLE ThreadId, BOOLEAN Create\n" +
            ") {\n" +
            "    if (Create) { /* new thread */ } else { /* thread exiting */ }\n" +
            "}"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Synchronous: the callbacks are called synchronously in the context of the " +
            "creating/exiting thread. The process or thread creation/exit is paused while " +
            "you handle the callback.\n\n" +
            "What is already set up on CREATION when your callback runs:\n" +
            "  • EPROCESS / ETHREAD object is created and initialized\n" +
            "  • The executable image is mapped into the new process address space\n" +
            "  • Process ID, parent PID, image path are available\n" +
            "  • The process has NOT yet started executing user-mode code\n\n" +
            "What is still present on EXIT when your callback runs:\n" +
            "  • EPROCESS / ETHREAD is still valid (not yet freed)\n" +
            "  • Process is in the termination sequence: user-mode threads are dead,\n" +
            "    handles are being closed, but the kernel object still exists\n" +
            "  • You can still dereference the EPROCESS pointer safely\n\n" +
            "Blocking process creation: only PsSetCreateProcessNotifyRoutineEx supports " +
            "this by setting CreateInfo->CreationStatus to an error status. The basic " +
            "PsSetCreateProcessNotifyRoutine callback is void and cannot block."
        )

        Spacer(modifier = Modifier.height(32.dp))

        HackerButton("BACK") { navController.popBackStack() }
    }
}
