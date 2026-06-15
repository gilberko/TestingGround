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
fun FSMFPrePostScreen(navController: NavController) {
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
            text = "PRE AND POST OPERATION",
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

        SectionHeader("WHAT ARE PRE AND POST OPERATIONS")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Pre-op callback: called before the I/O operation travels down the filter stack to the file system. You can inspect parameters, modify them, complete the operation early, or pend it for later processing.\n\nPost-op callback: called after the operation has completed and is traveling back up the stack. You can inspect results, modify output buffers, or pend for further work.\n\nBoth are registered per IRP_MJ_* type in the FLT_OPERATION_REGISTRATION array.")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("DO YOU HAVE TO REGISTER BOTH?")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("No — either can be NULL.\n\n• NULL pre-op: the operation passes straight through for that IRP type without calling you.\n\n• NULL post-op: you get the pre-op but no post-op. You cannot return FLT_PREOP_SUCCESS_WITH_CALLBACK if post-op is NULL — the Filter Manager will treat it as FLT_PREOP_SUCCESS_NO_CALLBACK.\n\nExample: register only PostRead to observe read results without touching the pre-op path.")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("ENSURING POST-OP IS CALLED")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Return value from pre-op controls whether your post-op fires:\n\n• FLT_PREOP_SUCCESS_WITH_CALLBACK — post-op will be called\n• FLT_PREOP_SUCCESS_NO_CALLBACK — post-op skipped for this I/O\n• FLT_PREOP_COMPLETE — operation completed in pre-op; post-op NOT called\n• FLT_PREOP_PENDING — operation pended; post-op called when you resume it\n\nThe CompletionContext out-parameter passes data from pre-op to post-op:")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "FLT_PREOP_CALLBACK_STATUS PreCreate(\n" +
            "    PFLT_CALLBACK_DATA Data,\n" +
            "    PCFLT_RELATED_OBJECTS FltObjects,\n" +
            "    PVOID* CompletionContext)\n" +
            "{\n" +
            "    *CompletionContext = AllocContext();\n" +
            "    return FLT_PREOP_SUCCESS_WITH_CALLBACK;\n" +
            "}\n\n" +
            "FLT_POSTOP_CALLBACK_STATUS PostCreate(\n" +
            "    PFLT_CALLBACK_DATA Data,\n" +
            "    PCFLT_RELATED_OBJECTS FltObjects,\n" +
            "    PVOID CompletionContext,\n" +
            "    FLT_POST_OPERATION_FLAGS Flags)\n" +
            "{\n" +
            "    // CompletionContext is what pre-op set\n" +
            "    FreeContext(CompletionContext);\n" +
            "    return FLT_POSTOP_FINISHED_PROCESSING;\n" +
            "}"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("THREAD CONTEXT")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Pre-op: generally called in the context of the thread that issued the I/O (the requestor thread). This means you can safely access thread-local storage and raise/lower IRQL normally.\n\nException: network redirector operations and some paging I/O may arrive in an arbitrary system thread.\n\nPost-op: NOT guaranteed to be in the original thread context. It may run in a system worker thread at IRQL <= DISPATCH_LEVEL. Do not assume you are in the requestor's context.\n\nCheck the FLTFL_POST_OPERATION_DRAINING flag in post-op: if set, the filter is being unloaded. You must not pend; return FLT_POSTOP_FINISHED_PROCESSING immediately and clean up.")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("COMPLETING IN PRE-OP")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("To complete (short-circuit) an operation in pre-op: fill in IoStatus, return FLT_PREOP_COMPLETE. The I/O never reaches the file system and no post-op is called.")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "FLT_PREOP_CALLBACK_STATUS PreCreate(\n" +
            "    PFLT_CALLBACK_DATA Data,\n" +
            "    PCFLT_RELATED_OBJECTS FltObjects,\n" +
            "    PVOID* CompletionContext)\n" +
            "{\n" +
            "    if (IsBlockedFile(Data)) {\n" +
            "        Data->IoStatus.Status =\n" +
            "            STATUS_ACCESS_DENIED;\n" +
            "        Data->IoStatus.Information = 0;\n" +
            "        return FLT_PREOP_COMPLETE;\n" +
            "    }\n" +
            "    return FLT_PREOP_SUCCESS_WITH_CALLBACK;\n" +
            "}"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("PENDING IN PRE-OP")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("To pend an operation: return FLT_PREOP_PENDING. The calling thread is blocked. Resume later from any thread by calling FltCompletePendedPreOperation.")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "// In pre-op: queue work and pend\n" +
            "QueueWorkItem(Data);\n" +
            "return FLT_PREOP_PENDING;\n\n" +
            "// In worker thread: resume the I/O\n" +
            "FltCompletePendedPreOperation(\n" +
            "    Data,\n" +
            "    FLT_PREOP_SUCCESS_WITH_CALLBACK,\n" +
            "    NULL);"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("CALLBACK PARAMETERS")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Pre-op signature:")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "FLT_PREOP_CALLBACK_STATUS PreOpCb(\n" +
            "    PFLT_CALLBACK_DATA Data,\n" +
            "    PCFLT_RELATED_OBJECTS FltObjects,\n" +
            "    PVOID* CompletionContext);"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Post-op signature:")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "FLT_POSTOP_CALLBACK_STATUS PostOpCb(\n" +
            "    PFLT_CALLBACK_DATA Data,\n" +
            "    PCFLT_RELATED_OBJECTS FltObjects,\n" +
            "    PVOID CompletionContext,\n" +
            "    FLT_POST_OPERATION_FLAGS Flags);"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Key fields in FLT_CALLBACK_DATA:\n• Iopb->MajorFunction — which IRP_MJ_* this is\n• Iopb->Parameters — union of per-op params (e.g. Create.DesiredAccess, Read.Length)\n• IoStatus — status/information returned to caller (writable in both callbacks)\n• RequestorMode — KernelMode or UserMode\n• Thread — the requestor thread (may differ in post-op)\n\nKey fields in FLT_RELATED_OBJECTS:\n• Filter — your PFLT_FILTER\n• Volume — the volume this operation is on\n• Instance — which instance (volume attachment)\n• FileObject — the file being operated on")

        Spacer(modifier = Modifier.height(32.dp))
        HackerButton("BACK") { navController.popBackStack() }
    }
}
