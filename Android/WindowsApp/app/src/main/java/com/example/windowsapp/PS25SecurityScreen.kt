package com.example.windowsapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun PS25SecurityScreen(navController: NavController) {
    PsLessonScreen(navController, "25 POWERSHELL SECURITY") {

        PsSection("THE SECURITY MODEL IN ONE SENTENCE")
        PsText("""
            PowerShell can do NOTHING that the user running it couldn't already do — it runs with the user's access token (see Advanced Topics ACCESS TOKENS AND IMPERSONATION). Its security features exist to prevent ACCIDENTS, to make running untrusted code a deliberate choice, and to give defenders VISIBILITY. They are not a sandbox.

            That's why PowerShell is loved by administrators AND attackers: it's a powerful, signed Microsoft binary present on every Windows machine. Good scripting security is about what YOU run, how you handle secrets, and how much privilege you use.
        """)

        PsSection("EXECUTION POLICIES")
        PsCode("""
            Policy         Effect
            ──────────────────────────────────────────────────────────
            Restricted     no scripts at all (commands only)
                           — default for Windows clients in 5.1
            AllSigned      every script must be signed by a trusted
                           publisher, local ones too
            RemoteSigned   local scripts run; scripts from the internet
                           must be signed (or unblocked)
                           — default on Windows Server
            Unrestricted   everything runs; warns for internet scripts
            Bypass         nothing blocked, no warnings
            Undefined      no policy at this scope
        """)
        PsCode("""
            Get-ExecutionPolicy                  # effective policy
            Get-ExecutionPolicy -List            # every scope
        """)
        PsOutput("""
                    Scope ExecutionPolicy
                    ----- ---------------
            MachinePolicy       Undefined      ← Group Policy (computer)
               UserPolicy       Undefined      ← Group Policy (user)
                  Process       Undefined      ← this session only
              CurrentUser    RemoteSigned      ← HKCU
             LocalMachine       Undefined      ← HKLM (needs admin)
        """)
        PsText("""
            Precedence is top to bottom: Group Policy wins, then Process, CurrentUser, LocalMachine. If everything is Undefined, Windows clients behave as Restricted and servers as RemoteSigned. PowerShell 7 on Windows keeps its own setting separately from 5.1 (typically configured as RemoteSigned by its installer — verify with -List). On Linux/macOS, policies aren't enforced.
        """)
        PsWarn("Changing the execution policy modifies PowerShell's configuration (registry / config file). LocalMachine scope affects every user and needs administrator rights. Prefer -Scope CurrentUser.")
        PsCode("""
            Set-ExecutionPolicy RemoteSigned -Scope CurrentUser
            Set-ExecutionPolicy Bypass -Scope Process         # this window only
        """)

        PsSection("EXECUTION POLICY IS NOT A SECURITY BOUNDARY")
        PsText("""
            Microsoft states this explicitly. It's a SAFETY feature that stops users running scripts by accident (e.g. double-clicking an attachment). It is NOT designed to stop a determined user or attacker, because anyone can bypass it without special rights:
        """)
        PsCode("""
            pwsh -ExecutionPolicy Bypass -File script.ps1      # per-process override
            Get-Content script.ps1 | Invoke-Expression          # code as a string, not a file
            Set-ExecutionPolicy Bypass -Scope Process           # no admin needed
        """)
        PsText("""
            The policy only applies to running script FILES; it doesn't restrict commands typed or piped in. Real enforcement comes from application control: WDAC (Windows Defender Application Control) / AppLocker, which can put PowerShell into CONSTRAINED LANGUAGE MODE (§ExecutionContext.SessionState.LanguageMode) — blocking arbitrary .NET, COM and Add-Type for untrusted scripts while still allowing signed, approved ones in Full Language mode.
        """)

        PsSection("MARK OF THE WEB AND Unblock-File")
        PsCode("""
            Get-Item .\downloaded.ps1 -Stream Zone.Identifier -ErrorAction SilentlyContinue
            Get-Content .\downloaded.ps1 -Stream Zone.Identifier     # ZoneId=3 → Internet
            Unblock-File .\downloaded.ps1                             # remove the mark
        """)
        PsText("Browsers tag downloaded files with an NTFS alternate data stream Zone.Identifier (\"Mark of the Web\"). RemoteSigned uses it to decide a script is \"remote\". Only Unblock-File scripts you've read and trust.")

        PsSection("SCRIPT SIGNING")
        PsText("""
            A SIGNATURE (Authenticode — the same mechanism used for .exe/.dll files) proves WHO published a script and that it hasn't been MODIFIED since signing. The signature is appended to the end of the .ps1 as a comment block. If even one character changes, the signature becomes invalid. See the Advanced Topics CERTIFICATE STORE screen for how certificate chains are validated.
        """)
        PsWarn("The following creates a self-signed code-signing certificate in your personal certificate store. Self-signed certificates are for LEARNING only — in production use a certificate from your organization's CA or a public CA. Remove it afterwards from Cert:\\CurrentUser\\My.")
        PsCode("""
            §cert = New-SelfSignedCertificate -Type CodeSigningCert `
                        -Subject 'CN=PSCourse Test Signing' `
                        -CertStoreLocation Cert:\CurrentUser\My

            Set-AuthenticodeSignature -FilePath .\Hello.ps1 -Certificate §cert `
                -TimestampServer 'http://timestamp.digicert.com'

            Get-AuthenticodeSignature .\Hello.ps1 | Format-List Status, StatusMessage, SignerCertificate
        """)
        PsOutput("""
            Status        : UnknownError
            StatusMessage : A certificate chain processed, but terminated in a root certificate which is not trusted by the trust provider.
        """)
        PsText("""
            The status is not Valid because a self-signed certificate isn't trusted. To trust it you'd add it to Trusted Root and Trusted Publishers — acceptable on a lab VM only. The TIMESTAMP keeps the signature valid after the certificate itself expires. Statuses to know: Valid, NotSigned, HashMismatch (file modified after signing), UnknownError (untrusted chain).
        """)

        PsSection("PERMISSIONS AND ADMINISTRATOR ELEVATION")
        PsCode("""
            # Am I elevated?
            §id = [Security.Principal.WindowsIdentity]::GetCurrent()
            §p  = [Security.Principal.WindowsPrincipal]::new(§id)
            §p.IsInRole([Security.Principal.WindowsBuiltInRole]::Administrator)

            whoami /groups | Select-String 'Mandatory Label'      # High = elevated

            # Require elevation for a script (first line of the .ps1):
            #Requires -RunAsAdministrator

            # Relaunch elevated (shows a UAC prompt)
            Start-Process pwsh -Verb RunAs -ArgumentList '-File', "`"§PSCommandPath`""
        """)
        PsText("""
            With UAC, an administrator's PowerShell runs with a FILTERED (medium-integrity) token unless started with "Run as administrator". Principle of LEAST PRIVILEGE: run elevated only for the commands that need it, never as your everyday shell. #Requires -RunAsAdministrator stops the script with a clear message if not elevated. Other #Requires: -Version 7.2, -Modules ActiveDirectory, -PSEdition Core.
        """)

        PsSection("CREDENTIALS")
        PsCode("""
            §cred = Get-Credential -UserName 'CORP\svc_backup' -Message 'Backup account'
            §cred.UserName
            §cred.Password                 # System.Security.SecureString — not plain text

            Invoke-Command -ComputerName srv01 -Credential §cred { hostname }
            New-PSDrive -Name S -PSProvider FileSystem -Root \\srv01\share -Credential §cred
            Get-CimInstance Win32_BIOS -CimSession (New-CimSession srv01 -Credential §cred)
        """)
        PsText("""
            A PSCredential pairs a user name with a SECURESTRING password. Cmdlets with a -Credential parameter accept it directly. SecureString keeps the password encrypted in memory and out of logs and transcripts — but note that Microsoft no longer recommends SecureString as strong protection on non-Windows platforms; treat it as "harder to leak by accident", not encryption you can rely on.
        """)

        PsSection("HANDLING SECRETS: DO AND DON'T")
        PsCode("""
            # DON'T — secret in source code (ends up in git, backups, logs)
            §password = 'P@ssw0rd!'
            §apiKey   = 'sk-123456'

            # BETTER (Windows) — DPAPI-encrypted file, readable only by the
            # same user on the same machine
            §cred | Export-Clixml C:\Secure\backup.cred
            §cred = Import-Clixml C:\Secure\backup.cred

            # BEST — a secret store via SecretManagement
            Install-Module Microsoft.PowerShell.SecretManagement, Microsoft.PowerShell.SecretStore -Scope CurrentUser
            Register-SecretVault -Name LocalStore -ModuleName Microsoft.PowerShell.SecretStore -DefaultVault
            Set-Secret -Name GitHubToken -Secret (Read-Host -AsSecureString)
            §token = Get-Secret -Name GitHubToken -AsPlainText     # only when needed

            # CI/CD — environment variables injected by the pipeline
            §apiKey = §env:API_KEY
        """)
        PsText("""
            Export-Clixml encrypts SecureStrings with Windows DPAPI tied to the USER and MACHINE: another user, or the same file copied to another computer, can't decrypt it. SecretManagement is a common interface over vaults: local SecretStore, Azure Key Vault, KeePass, Windows Credential Manager, HashiCorp Vault… so scripts don't care where secrets live. Never write secrets to the console, transcripts, or Write-Verbose output.
        """)

        PsSection("DOWNLOADING AND EXECUTING SCRIPTS")
        PsWarn("Never run the pattern below on code you haven't reviewed. It downloads text from the internet and executes it immediately with YOUR privileges — nothing is saved for review, and execution policy does NOT apply.")
        PsCode("""
            # The infamous "download cradle":
            irm https://example.com/install.ps1 | iex

            # Safer workflow:
            Invoke-WebRequest https://example.com/install.ps1 -OutFile .\install.ps1
            Get-Content .\install.ps1          # READ IT
            Get-FileHash .\install.ps1         # compare with the publisher's hash
            Get-AuthenticodeSignature .\install.ps1
            .\install.ps1                      # only then
        """)
        PsText("""
            Invoke-Expression (iex) runs any string as code — avoid it in your own scripts entirely, especially with any input from users, files or the network (CODE INJECTION). There's almost always a safer construct: & for commands, splatting for parameters, ConvertFrom-Json for data.

            Use HTTPS only, prefer installers from trusted sources (winget, PSGallery with known authors, signed scripts), and verify hashes or signatures.
        """)

        PsSection("LOGGING AND VISIBILITY (THE DEFENDER'S VIEW)")
        PsText("""
            Because attackers love PowerShell, Windows provides strong auditing:
            • Script Block Logging (Event ID 4104, Microsoft-Windows-PowerShell/Operational) records the code that actually runs — even obfuscated code is logged after de-obfuscation.
            • Module Logging (4103) records pipeline execution details.
            • Transcription (Start-Transcript, or by policy) records everything typed and output.
            • AMSI (Antimalware Scan Interface) lets Defender scan script content at run time, including code built in memory.
            • PowerShell 2.0 lacked these features and was used for "downgrade attacks"; it has been deprecated, and Microsoft has removed it from recent Windows versions — check with Get-WindowsOptionalFeature -Online -FeatureName MicrosoftWindowsPowerShellV2Root.
        """)
        PsCode("""
            Get-WinEvent -LogName 'Microsoft-Windows-PowerShell/Operational' -MaxEvents 5 |
                Where-Object Id -eq 4104 | Select-Object TimeCreated, Message
        """)

        PsSection("CAVEATS")
        PsText("""
            • Execution policy ≠ security; WDAC/AppLocker + Constrained Language Mode do the enforcing.
            • #Requires -RunAsAdministrator only CHECKS; it doesn't elevate.
            • Export-Clixml credential files are Windows-only and bound to user + machine.
            • Remoting sessions pass credentials — prefer Kerberos/HTTPS, and beware the "double hop" problem (credentials don't flow onward from the remote machine without CredSSP or delegation).
        """)

        PsSection("CONNECTIONS")
        PsText("Lesson 2 met the execution policy for the first time. Signing relies on certificates (Advanced Topics CERTIFICATE STORE), elevation on access tokens (Advanced Topics ACCESS TOKENS AND IMPERSONATION). Lesson 15's REST calls are where secret handling matters most.")

        PsExercise("""
            1. Run Get-ExecutionPolicy -List in both powershell.exe and pwsh.exe and explain any differences.
            2. Download any small script from a trusted GitHub repository with Invoke-WebRequest and inspect its Zone.Identifier stream.
            3. Write a script that begins with #Requires -RunAsAdministrator and run it from a non-elevated window — read the error.
        """)
        PsChallenge("""
            On a test machine or VM: create a self-signed code-signing certificate, sign a script, set the policy to AllSigned for the Process scope, and run it — observe the "untrusted publisher" prompt. Modify one character and check Get-AuthenticodeSignature (HashMismatch). Finally, store an API token with SecretManagement and write a script that uses Get-Secret to call a REST API without the token ever appearing in the script file. Clean up the certificate afterwards.
        """)
    }
}
