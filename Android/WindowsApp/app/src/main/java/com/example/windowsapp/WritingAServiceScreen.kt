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
fun WritingAServiceScreen(navController: NavController) {
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
            text = "WRITING A SERVICE",
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

        SectionHeader("SERVICE OVERVIEW")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("A Windows Service is a long-running background process managed by the Service Control Manager (SCM), which runs as services.exe. Services can start automatically at boot, run without a logged-in user, and receive lifecycle notifications from the OS.")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Services are registered in the registry under:")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock("HKLM\\SYSTEM\\CurrentControlSet\\Services\\<ServiceName>")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("A service runs in its own process (SERVICE_WIN32_OWN_PROCESS) or shares a svchost.exe process with other services (SERVICE_WIN32_SHARE_PROCESS). Driver services (SERVICE_KERNEL_DRIVER) are a separate category.")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("ENTRY POINTS")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("A service EXE has a specific structure. The main function registers a service table and hands control to the SCM, which then calls ServiceMain in a new thread:")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "SERVICE_TABLE_ENTRY table[] = {\n" +
            "    { L\"MyService\", ServiceMain },\n" +
            "    { NULL, NULL }  // terminator\n" +
            "};\n\n" +
            "int wmain() {\n" +
            "    // Blocks until all services in the table have stopped\n" +
            "    StartServiceCtrlDispatcher(table);\n" +
            "    return 0;\n" +
            "}"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("ServiceMain is your service's entry point. The first thing it must do is register a control handler and report SERVICE_START_PENDING to the SCM:")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "SERVICE_STATUS_HANDLE gSsh;\n\n" +
            "VOID WINAPI ServiceMain(DWORD argc, LPTSTR* argv) {\n" +
            "    gSsh = RegisterServiceCtrlHandlerEx(\n" +
            "        L\"MyService\",\n" +
            "        HandlerEx,    // control handler function\n" +
            "        NULL);        // context passed to HandlerEx\n\n" +
            "    // Report: starting\n" +
            "    SERVICE_STATUS ss = {0};\n" +
            "    ss.dwServiceType  = SERVICE_WIN32_OWN_PROCESS;\n" +
            "    ss.dwCurrentState = SERVICE_START_PENDING;\n" +
            "    ss.dwWaitHint     = 3000; // expect to start in 3s\n" +
            "    SetServiceStatus(gSsh, &ss);\n\n" +
            "    // ... do initialization ...\n\n" +
            "    // Report: running\n" +
            "    ss.dwCurrentState     = SERVICE_RUNNING;\n" +
            "    ss.dwControlsAccepted = SERVICE_ACCEPT_STOP |\n" +
            "                           SERVICE_ACCEPT_SHUTDOWN;\n" +
            "    SetServiceStatus(gSsh, &ss);\n\n" +
            "    // Main work loop (or WaitForSingleObject on stop event)\n" +
            "}"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("SERVICE STATES")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("dwCurrentState in SERVICE_STATUS can be:")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "SERVICE_START_PENDING    // initializing\n" +
            "SERVICE_RUNNING          // fully operational\n" +
            "SERVICE_PAUSE_PENDING    // pausing\n" +
            "SERVICE_PAUSED           // paused\n" +
            "SERVICE_CONTINUE_PENDING // resuming\n" +
            "SERVICE_STOP_PENDING     // shutting down\n" +
            "SERVICE_STOPPED          // stopped"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("During PENDING states, set dwCheckPoint (incrementing counter) and dwWaitHint (milliseconds until next checkpoint). The SCM uses these to detect a stuck service and will eventually force-terminate it if no progress is reported.")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("On exit, set dwWin32ExitCode = NO_ERROR (0) for success, or ERROR_SERVICE_SPECIFIC_ERROR and a non-zero dwServiceSpecificExitCode for a service-defined error.")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("CONTROL NOTIFICATIONS")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("The SCM calls HandlerEx with control codes. Your handler must be fast — it runs in a separate thread from ServiceMain:")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "DWORD WINAPI HandlerEx(\n" +
            "    DWORD  dwControl,\n" +
            "    DWORD  dwEventType,   // for SESSION_CHANGE, POWER_EVENT\n" +
            "    LPVOID lpEventData,\n" +
            "    LPVOID lpContext\n" +
            ") {\n" +
            "    switch (dwControl) {\n" +
            "    case SERVICE_CONTROL_STOP:\n" +
            "    case SERVICE_CONTROL_SHUTDOWN:\n" +
            "        ReportPending(SERVICE_STOP_PENDING);\n" +
            "        SetEvent(gStopEvent); // signal main loop\n" +
            "        break;\n" +
            "    case SERVICE_CONTROL_INTERROGATE:\n" +
            "        SetServiceStatus(gSsh, &gCurrentStatus);\n" +
            "        break;\n" +
            "    }\n" +
            "    return NO_ERROR;\n" +
            "}"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Control codes and their meaning:")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "SERVICE_CONTROL_STOP          // stop the service\n" +
            "SERVICE_CONTROL_PAUSE         // pause (if accepted)\n" +
            "SERVICE_CONTROL_CONTINUE      // resume from pause\n" +
            "SERVICE_CONTROL_INTERROGATE   // return current status\n" +
            "SERVICE_CONTROL_SHUTDOWN      // system is shutting down\n" +
            "SERVICE_CONTROL_PRESHUTDOWN   // earlier shutdown warning\n" +
            "                              //   (requires ACCEPT_PRESHUTDOWN)\n" +
            "SERVICE_CONTROL_SESSIONCHANGE // user logon/logoff/lock\n" +
            "                              //   dwEventType: WTS_SESSION_LOGON,\n" +
            "                              //   WTS_SESSION_LOGOFF, WTS_SESSION_LOCK\n" +
            "SERVICE_CONTROL_POWEREVENT    // suspend/resume\n" +
            "                              //   dwEventType: PBT_APMSUSPEND,\n" +
            "                              //   PBT_APMRESUMEAUTOMATIC\n" +
            "SERVICE_CONTROL_PARAMCHANGE   // configuration changed"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("You must advertise which controls you accept in dwControlsAccepted. Controls not listed there will not be sent to your HandlerEx. SERVICE_CONTROL_INTERROGATE is always accepted.")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("SCM INTERACTION")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Command line (sc.exe):")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "sc create MyService binPath= \"C:\\MyService.exe\" start= auto\n" +
            "sc start MyService\n" +
            "sc stop  MyService\n" +
            "sc query MyService\n" +
            "sc delete MyService"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Programmatic API:")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "SC_HANDLE hScm = OpenSCManager(\n" +
            "    NULL, NULL, SC_MANAGER_ALL_ACCESS);\n\n" +
            "SC_HANDLE hSvc = CreateService(\n" +
            "    hScm, L\"MyService\", L\"My Service\",\n" +
            "    SERVICE_ALL_ACCESS,\n" +
            "    SERVICE_WIN32_OWN_PROCESS,\n" +
            "    SERVICE_AUTO_START,\n" +
            "    SERVICE_ERROR_NORMAL,\n" +
            "    L\"C:\\\\MyService.exe\",\n" +
            "    NULL, NULL, NULL, NULL, NULL);\n\n" +
            "StartService(hSvc, 0, NULL);\n" +
            "// or: ControlService(hSvc, SERVICE_CONTROL_STOP, &ss);\n\n" +
            "CloseServiceHandle(hSvc);\n" +
            "CloseServiceHandle(hScm);"
        )

        Spacer(modifier = Modifier.height(32.dp))

        HackerButton("< BACK") { navController.popBackStack() }
    }
}
