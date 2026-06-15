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
fun AccessTokensAndImpersonationScreen(navController: NavController) {
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
            text = "ACCESS TOKENS AND IMPERSONATION",
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

        SectionHeader("WHAT IS AN ACCESS TOKEN")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("An access token is a kernel object that describes the security context of a process or thread. It is created when a user logs in and is attached to every process started in that logon session.")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("A token contains:")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "- User SID           // who is running this code\n" +
            "- Group SIDs         // groups the user belongs to\n" +
            "- Privileges         // special rights (e.g., SeDebugPrivilege)\n" +
            "- Integrity level    // Low / Medium / High / System\n" +
            "- Logon session ID   // links to the interactive session\n" +
            "- Owner SID          // default owner for new objects\n" +
            "- Primary group SID\n" +
            "- Default DACL       // default ACL for objects this process creates"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Every process has a primary token (assigned at creation). A thread can additionally have an impersonation token that overrides the process token for that thread's security checks.")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("WHO CHECKS TOKENS")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("The Security Reference Monitor (SRM), a component of the Windows kernel (ntoskrnl.exe), performs access checks. When a process attempts to open a securable object (file, registry key, mutex, process, etc.), SRM compares the caller's token against the object's Security Descriptor.")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("The check has two phases:")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "1. DACL check: compare token's user/group SIDs against\n" +
            "   the object's Discretionary ACL (access control list).\n" +
            "   Each ACE in the DACL is an allow or deny entry.\n\n" +
            "2. Mandatory Integrity Control (MIC): token's integrity\n" +
            "   level must be >= object's integrity level for write access.\n" +
            "   A Medium-integrity process cannot write to a High-integrity\n" +
            "   object (e.g., UAC-protected files)."
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Privileges bypass DACL checks for specific operations. For example, SeDebugPrivilege allows OpenProcess on any process regardless of its DACL.")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("GETTING A TOKEN")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Getting the current process's token:")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "HANDLE hToken;\n" +
            "OpenProcessToken(\n" +
            "    GetCurrentProcess(),  // pseudo-handle, no OpenProcess needed\n" +
            "    TOKEN_QUERY,\n" +
            "    &hToken);"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Getting another process's token (requires PROCESS_QUERY_INFORMATION access):")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "HANDLE hProcess = OpenProcess(\n" +
            "    PROCESS_QUERY_INFORMATION, FALSE, pid);\n" +
            "HANDLE hToken;\n" +
            "OpenProcessToken(hProcess, TOKEN_QUERY, &hToken);\n" +
            "CloseHandle(hProcess);"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Getting a thread's impersonation token:")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "HANDLE hToken;\n" +
            "OpenThreadToken(\n" +
            "    GetCurrentThread(),\n" +
            "    TOKEN_QUERY,\n" +
            "    FALSE,  // FALSE: check thread's own access, not process's\n" +
            "    &hToken);\n" +
            "// Returns ERROR_NO_TOKEN if thread is not impersonating"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Reading token information:")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "// First call to get required buffer size:\n" +
            "DWORD len = 0;\n" +
            "GetTokenInformation(hToken, TokenUser, NULL, 0, &len);\n\n" +
            "TOKEN_USER* pUser = (TOKEN_USER*)malloc(len);\n" +
            "GetTokenInformation(hToken, TokenUser, pUser, len, &len);\n" +
            "// pUser->User.Sid is the user's SID\n\n" +
            "// Common TokenInformationClass values:\n" +
            "// TokenUser, TokenGroups, TokenPrivileges,\n" +
            "// TokenOwner, TokenPrimaryGroup,\n" +
            "// TokenIntegrityLevel, TokenElevationType"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Always CloseHandle(hToken) when done — access tokens are reference-counted kernel objects.")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("IMPERSONATION")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Impersonation allows a thread to execute with a different security context than the process. This is commonly used by servers: the server temporarily adopts the client's identity to check whether the client has permission to access a resource.")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Common impersonation APIs:")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "// Impersonate a named pipe client:\n" +
            "ImpersonateNamedPipeClient(hPipe);\n\n" +
            "// Impersonate a logged-on user (given their token):\n" +
            "ImpersonateLoggedOnUser(hUserToken);\n\n" +
            "// Set a specific token on the current thread manually:\n" +
            "SetThreadToken(NULL, hImpersonationToken);\n" +
            "// (NULL = current thread)\n\n" +
            "// Revert to the process primary token:\n" +
            "RevertToSelf();"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Impersonation levels control what the server can do with the client's identity:")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "SecurityAnonymous       // server cannot identify the client\n" +
            "SecurityIdentification  // server can identify, but not use token\n" +
            "SecurityImpersonation   // server can act as client locally\n" +
            "SecurityDelegation      // server can act as client on remote systems"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("The impersonation level is set when the client connects or creates a token. A server cannot elevate the level — it can only use the level the client granted.")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("TOKEN ELEVATION AND UAC")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("When UAC is enabled, an admin user gets two tokens at logon: a filtered (standard-user) token and a full-admin token. Most processes run with the filtered token; elevated processes get the full-admin token.")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "// Check elevation type:\n" +
            "TOKEN_ELEVATION_TYPE type;\n" +
            "DWORD len;\n" +
            "GetTokenInformation(hToken, TokenElevationType,\n" +
            "                    &type, sizeof(type), &len);\n\n" +
            "// type values:\n" +
            "// TokenElevationTypeDefault  -- UAC disabled or not applicable\n" +
            "// TokenElevationTypeFull     -- this IS the elevated token\n" +
            "// TokenElevationTypeLimited  -- this is the filtered token\n" +
            "//   (linked elevated token available via TokenLinkedToken)"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("To create a modified copy of a token (e.g., with added/removed privileges or a different integrity level), use DuplicateTokenEx:")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "HANDLE hDup;\n" +
            "DuplicateTokenEx(\n" +
            "    hToken,\n" +
            "    TOKEN_ALL_ACCESS,\n" +
            "    NULL,\n" +
            "    SecurityImpersonation,    // impersonation level\n" +
            "    TokenImpersonation,       // or TokenPrimary for CreateProcessAsUser\n" +
            "    &hDup);"
        )

        Spacer(modifier = Modifier.height(32.dp))

        HackerButton("BACK") { navController.popBackStack() }
    }
}
