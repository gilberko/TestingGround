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
fun DaclSddlScreen(navController: NavController) {
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
            text = "DACL AND SDDL",
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

        SectionHeader("SECURITY DESCRIPTORS")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Every securable Windows object — file, registry key, process, named pipe, kernel object, service, window station, desktop — has a Security Descriptor attached to it. The Security Descriptor contains four components:")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "Owner SID    — who owns the object\n" +
            "Group SID    — primary group (used by POSIX subsystem)\n" +
            "DACL         — Discretionary Access Control List\n" +
            "               controls WHO can do WHAT\n" +
            "SACL         — System Access Control List\n" +
            "               controls AUDITING (event log)"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("WHAT IS A DACL?")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("The DACL (Discretionary Access Control List) is a list of ACEs (Access Control Entries). Each ACE grants or denies specific access rights to a specific SID (Security Identifier). Important distinctions:")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "NULL DACL\n" +
            "  No DACL pointer in the descriptor.\n" +
            "  Result: EVERYONE has FULL ACCESS.\n" +
            "  (Dangerous — do not confuse with empty)\n\n" +
            "Empty DACL (0 ACEs)\n" +
            "  DACL exists but has no entries.\n" +
            "  Result: NOBODY has ANY access.\n\n" +
            "Normal DACL\n" +
            "  Evaluated top-to-bottom.\n" +
            "  Deny ACEs conventionally come before Allow ACEs\n" +
            "  to ensure denials are checked first."
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("ACE TYPES")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Each ACE in a DACL is one of these types:")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "ACCESS_ALLOWED_ACE\n" +
            "  Grants specific access rights to a SID\n\n" +
            "ACCESS_DENIED_ACE\n" +
            "  Denies specific access rights to a SID\n\n" +
            "Inheritance flags per ACE:\n" +
            "  CONTAINER_INHERIT_ACE  (CI) — child containers\n" +
            "  OBJECT_INHERIT_ACE     (OI) — child objects\n" +
            "  INHERIT_ONLY_ACE       (IO) — inherited only, not direct\n" +
            "  NO_PROPAGATE_INHERIT_ACE (NP) — one level only"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("WHEN IS THE DACL EVALUATED?")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Every time a process tries to open a securable object — OpenProcess, CreateFile, RegOpenKey, OpenServiceHandle, OpenEvent, etc. — the kernel calls SeAccessCheck. It compares the object's DACL against the caller's token (which lists all the caller's SIDs including group memberships). Access is granted only if the DACL allows it.")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("SETTING A DACL PROGRAMMATICALLY")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "SECURITY_DESCRIPTOR sd;\n" +
            "BYTE aclBuf[512];\n" +
            "PACL pAcl = (PACL)aclBuf;\n\n" +
            "InitializeSecurityDescriptor(&sd,\n" +
            "    SECURITY_DESCRIPTOR_REVISION);\n" +
            "InitializeAcl(pAcl, sizeof(aclBuf), ACL_REVISION);\n\n" +
            "// Allow Administrators full access\n" +
            "AddAccessAllowedAce(pAcl, ACL_REVISION,\n" +
            "    FILE_ALL_ACCESS, pAdminSid);\n\n" +
            "// Allow Users read access\n" +
            "AddAccessAllowedAce(pAcl, ACL_REVISION,\n" +
            "    GENERIC_READ, pUserSid);\n\n" +
            "SetSecurityDescriptorDacl(&sd, TRUE, pAcl, FALSE);\n\n" +
            "// Apply to a file:\n" +
            "SetFileSecurity(L\"C:\\\\MyFile.txt\",\n" +
            "    DACL_SECURITY_INFORMATION, &sd);\n\n" +
            "// Apply to a registry key:\n" +
            "RegSetKeySecurity(hKey,\n" +
            "    DACL_SECURITY_INFORMATION, &sd);"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("WHAT IS SDDL?")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("SDDL (Security Descriptor Definition Language) is a compact string format for security descriptors. It is used everywhere: PowerShell, sc.exe, icacls, Group Policy ADMX templates, registry ACL exports, and Windows error messages. It allows a full security descriptor to be expressed as a human-readable (though terse) string.")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "Top-level format:\n" +
            "  O:<owner> G:<group> D:<dacl-flags>(<ace>)... S:<sacl>(<ace>)...\n\n" +
            "ACE format inside D: or S::\n" +
            "  (ace_type ; ace_flags ; rights ; ; ; account_sid)\n" +
            "  Fields: type  flags  rights  obj_guid  inh_guid  sid\n" +
            "  (obj_guid and inh_guid are usually empty)\n\n" +
            "ace_type values:\n" +
            "  A  = ACCESS_ALLOWED\n" +
            "  D  = ACCESS_DENIED\n" +
            "  AU = SYSTEM_AUDIT (for SACL)\n\n" +
            "ace_flags (inheritance):\n" +
            "  OI = Object Inherit\n" +
            "  CI = Container Inherit\n" +
            "  NP = No Propagate Inherit\n" +
            "  IO = Inherit Only\n\n" +
            "rights (predefined shortcuts):\n" +
            "  GA = Generic All\n" +
            "  GR = Generic Read\n" +
            "  GW = Generic Write\n" +
            "  FA = File All Access\n" +
            "  FR = File Read\n" +
            "  FW = File Write\n" +
            "  KA = Key All Access (registry)\n" +
            "  KR = Key Read (registry)\n" +
            "  Or use a hex mask: 0x001F01FF"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("WELL-KNOWN SID ABBREVIATIONS")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "BA  BUILTIN\\Administrators\n" +
            "SY  NT AUTHORITY\\SYSTEM\n" +
            "WD  Everyone  (World)\n" +
            "BU  BUILTIN\\Users\n" +
            "AU  NT AUTHORITY\\Authenticated Users\n" +
            "LS  NT AUTHORITY\\LOCAL SERVICE\n" +
            "NS  NT AUTHORITY\\NETWORK SERVICE\n" +
            "CO  CREATOR OWNER\n" +
            "DA  Domain Admins\n" +
            "IU  BUILTIN\\Interactive Users"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("SDDL EXAMPLES")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Allow Full Access to Administrators and System only:")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock("D:(A;;FA;;;BA)(A;;FA;;;SY)")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Protected DACL — System gets full access, Users get read, no inheritance from parent object:")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock("D:P(A;;FA;;;SY)(A;;GR;;;BU)")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Admins get Generic All, inherited by all child objects and containers (OI+CI):")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock("D:(A;OICI;GA;;;BA)")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Explicitly deny Everyone, then allow Admins (deny checked first):")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock("D:(D;;WD;;;WD)(A;;FA;;;BA)")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Full descriptor with owner and group — grant specific hex rights to Everyone:")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock("O:BAG:SYD:(A;;0x1200a9;;;WD)")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("CONVERTING BETWEEN SDDL AND BINARY")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "// SDDL string → binary Security Descriptor:\n" +
            "PSECURITY_DESCRIPTOR pSD = NULL;\n" +
            "ConvertStringSecurityDescriptorToSecurityDescriptor(\n" +
            "    L\"D:(A;;FA;;;BA)(A;;GR;;;BU)\",\n" +
            "    SDDL_REVISION_1,\n" +
            "    &pSD,\n" +
            "    NULL);\n" +
            "// Free with: LocalFree(pSD)\n\n" +
            "// Binary Security Descriptor → SDDL string:\n" +
            "LPWSTR sddlStr = NULL;\n" +
            "ConvertSecurityDescriptorToStringSecurityDescriptor(\n" +
            "    pSD,\n" +
            "    SDDL_REVISION_1,\n" +
            "    OWNER_SECURITY_INFORMATION |\n" +
            "    DACL_SECURITY_INFORMATION,\n" +
            "    &sddlStr,\n" +
            "    NULL);\n" +
            "// Free with: LocalFree(sddlStr)"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("PRACTICAL TOOLS")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "icacls C:\\path\n" +
            "  View or set file/directory DACL\n\n" +
            "sc.exe sdset <service> \"<sddl>\"\n" +
            "  Set service Security Descriptor\n\n" +
            "sc.exe sdshow <service>\n" +
            "  Read service Security Descriptor as SDDL\n\n" +
            "Get-Acl / Set-Acl  (PowerShell)\n" +
            "  Object ACL management\n\n" +
            "accesschk.exe  (Sysinternals)\n" +
            "  Check effective access for a specific user\n\n" +
            "Process Explorer  (Sysinternals)\n" +
            "  View process / object security descriptors"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("SACL (SYSTEM ACCESS CONTROL LIST)")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("The SACL uses the same ACE structure as the DACL but controls auditing: which access attempts are written to the Windows Security event log. Accessing or modifying a SACL requires SeSecurityPrivilege (held by administrators). In SDDL the SACL appears after S::")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "S:(AU;SA;FA;;;WD)\n" +
            "  Audit Everyone's successful file-all-access attempts\n\n" +
            "S:(AU;FA;FA;;;WD)\n" +
            "  Audit Everyone's failed file-all-access attempts\n\n" +
            "AU = SYSTEM_AUDIT ace type\n" +
            "SA = SUCCESSFUL_ACCESS_ACE_FLAG\n" +
            "FA = FAILED_ACCESS_ACE_FLAG"
        )

        Spacer(modifier = Modifier.height(32.dp))

        HackerButton("BACK") { navController.popBackStack() }
    }
}
