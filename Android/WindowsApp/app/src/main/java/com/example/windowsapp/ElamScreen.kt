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
fun ElamScreen(navController: NavController) {
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
            text = "ELAM",
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

        SectionHeader("WHAT IS ELAM?")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Early Launch Anti-Malware (ELAM) is a Windows security feature introduced in Windows 8. It allows trusted AV and security vendors to load a special kernel driver before ALL other boot-start drivers. The ELAM driver can then inspect each boot-start driver as it is about to be loaded and optionally block it — preventing rootkits and bootkits from taking hold before any other code runs.")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("WHEN ELAM DRIVERS LOAD")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("ELAM drivers have Start = 0 (boot-start) like other early drivers, but a special service type flag distinguishes them. The load order is:")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "winload.efi\n" +
            "  └─ loads ELAM driver binary into memory\n" +
            "      alongside ntoskrnl.exe and hal.dll\n\n" +
            "Kernel Phase 0:\n" +
            "  └─ ELAM driver DriverEntry runs FIRST\n" +
            "      (before any other boot-start driver)\n\n" +
            "Kernel then loads each remaining boot-start driver:\n" +
            "  └─ Calls ELAM callback BEFORE each one\n" +
            "  └─ ELAM callback sets verdict\n" +
            "  └─ Kernel either loads or skips the driver"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("REGISTRATION REQUIREMENTS")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("ELAM drivers have strict requirements that prevent arbitrary code from registering as one:")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "Registry location:\n" +
            "  HKLM\\SYSTEM\\CurrentControlSet\\Control\\EarlyLaunch\n" +
            "    DriverName = <service name>\n\n" +
            "Service parameters:\n" +
            "  Start = 0  (SERVICE_BOOT_START)\n" +
            "  Type  = SERVICE_KERNEL_DRIVER\n\n" +
            "Signing requirement:\n" +
            "  Must be signed with a Microsoft-issued ELAM\n" +
            "  signing certificate. Only approved AV vendors\n" +
            "  (e.g. Microsoft, Symantec, CrowdStrike) can\n" +
            "  obtain this certificate.\n\n" +
            "PE requirement:\n" +
            "  Driver binary must contain a resource section\n" +
            "  named \"MICROSOFTELAM\" (used for PPL grants)\n\n" +
            "INF file: uses the 'Elam' service type keyword"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("CALLBACK REGISTRATION")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("In DriverEntry, the ELAM driver registers a boot driver callback that the kernel will call for each boot-start driver:")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "NTSTATUS IoRegisterBootDriverCallback(\n" +
            "    PBOOT_DRIVER_CALLBACK_FUNCTION CallbackFunction,\n" +
            "    PVOID                          CallbackContext,\n" +
            "    PVOID                          *CallbackHandle);\n\n" +
            "// In DriverUnload:\n" +
            "IoUnregisterBootDriverCallback(CallbackHandle);"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("WHAT THE CALLBACK RECEIVES")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("The callback is invoked with a BDCB_CALLBACK_TYPE and a BDCB_IMAGE_INFORMATION structure for each driver being loaded. The key fields available to the ELAM driver:")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "typedef struct _BDCB_IMAGE_INFORMATION {\n" +
            "    BDCB_CLASSIFICATION  Classification;  // set your verdict here\n" +
            "    ULONG                ImageFlags;       // signed? valid sig?\n" +
            "    UNICODE_STRING       ImageName;        // full driver path\n" +
            "    UNICODE_STRING       RegistryPath;     // service registry key\n" +
            "    UNICODE_STRING       CertificatePublisher;\n" +
            "    UNICODE_STRING       CertificateIssuer;\n" +
            "    PVOID                ImageHash;        // SHA-256 hash bytes\n" +
            "    PVOID                CertificateThumbprint;\n" +
            "    ULONG                ImageHashLength;\n" +
            "    ULONG                ImageHashAlgorithm; // CALG_SHA_256 etc.\n" +
            "} BDCB_IMAGE_INFORMATION;"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("VERDICTS — CAN ELAM BLOCK?")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Yes. The ELAM callback sets the Classification field on the image info structure. The kernel reads this verdict and acts accordingly:")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "BdCbClassificationKnownGoodImage\n" +
            "  Allow — driver is trusted, load normally\n\n" +
            "BdCbClassificationKnownBadImage\n" +
            "  Block — driver is NOT loaded, boot continues\n" +
            "  without it (logged in event log)\n\n" +
            "BdCbClassificationUnknownImage\n" +
            "  Allow — unknown drivers are permitted by\n" +
            "  default Windows policy\n\n" +
            "BdCbClassificationKnownBadImageBootCritical\n" +
            "  Driver is bad but flagged boot-critical;\n" +
            "  allowed to load, event is logged\n\n" +
            "BdCbClassificationEnd\n" +
            "  Callback complete, no more calls needed"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("WHAT CAN ELAM CHECK?")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("The callback data provides several verification surfaces:")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "1. Image hash (SHA-256)\n" +
            "   Compare against an embedded allow/block\n" +
            "   database in the ELAM driver binary itself\n\n" +
            "2. Authenticode certificate\n" +
            "   Publisher, issuer, thumbprint\n" +
            "   Is it signed by a trusted CA?\n\n" +
            "3. ImageFlags\n" +
            "   Is the image signed at all?\n" +
            "   Is the signature valid (not expired/revoked)?\n" +
            "   Is it self-signed?\n\n" +
            "4. Measured Boot log (WBCL)\n" +
            "   TPM PCR values recorded during boot\n" +
            "   Detect if firmware or early components\n" +
            "   were tampered with"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("TOOLS AVAILABLE TO ELAM")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("ELAM runs extremely early — many normal kernel facilities are not yet available. Here is what is and is not accessible:")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "AVAILABLE:\n" +
            "  Registry (SYSTEM hive only — SOFTWARE/SAM\n" +
            "    not loaded yet)\n" +
            "  Non-paged pool memory allocation\n" +
            "  Kernel CNG crypto (BCryptOpenAlgorithmProvider,\n" +
            "    BCryptHash, etc.)\n" +
            "  TPM / measured boot log via\n" +
            "    ExQuerySystemFirmwareTable (ACPI/SLIC)\n\n" +
            "NOT AVAILABLE:\n" +
            "  File I/O (filesystem stack not started yet)\n" +
            "  Networking (no NDIS/TCP stack)\n" +
            "  User-mode communication (no processes yet)\n" +
            "  Most Windows APIs\n\n" +
            "Typical approach: embed a policy database\n" +
            "  as a PE resource in the ELAM driver binary,\n" +
            "  or store a minimal blob in the SYSTEM hive,\n" +
            "  loaded before DriverEntry."
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("MEASURED BOOT INTEGRATION")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Windows Measured Boot records each component loaded during boot into TPM PCR (Platform Configuration Register) registers. The log — called the Windows Boot Configuration Log (WBCL) — is stored by UEFI firmware. ELAM can read this log to verify that the firmware itself, the boot manager, and the kernel were not tampered with before any driver callbacks fire.")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("PROTECTING SERVICES VIA ELAM (PPL GRANTS)")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("An ELAM driver can cause specific services to run as PPL (Protected Process Light) by embedding identity data in a resource section named \"MICROSOFTELAM\" inside the driver PE. This resource contains a table of WD_PROTECTED_PROCESS_IDENTIFIER entries, each specifying a service binary hash and the desired protection level (PPL Type + Signer). When SCM starts a service whose binary hash matches an entry, the kernel automatically applies the corresponding PPL protection.")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "Example: Windows Defender\n" +
            "  WdBoot.sys  (ELAM driver, signed by Microsoft)\n" +
            "    └─ MICROSOFTELAM resource section contains:\n" +
            "        hash of MsMpEng.exe\n" +
            "        protection level: PPL-Antimalware\n" +
            "          (Type=1, Signer=3)\n\n" +
            "  When SCM starts MsMpEng.exe:\n" +
            "    kernel checks ELAM resource, finds a match\n" +
            "    MsMpEng.exe runs as PPL-Antimalware\n" +
            "    even administrators cannot dump its memory\n\n" +
            "Chain of trust:\n" +
            "  Microsoft ELAM cert\n" +
            "  └─ AV vendor signs ELAM driver with that cert\n" +
            "      └─ ELAM driver certifies AV service hash\n" +
            "          └─ Service runs as PPL at boot"
        )

        Spacer(modifier = Modifier.height(32.dp))

        HackerButton("< BACK") { navController.popBackStack() }
    }
}
