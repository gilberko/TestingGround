package com.example.windowsapp

import androidx.compose.foundation.background
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
fun CertificateStoreScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp, vertical = 48.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "CERTIFICATE STORE",
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

        SectionHeader("WHAT IS A CERTIFICATE (X.509)")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "An X.509 certificate (RFC 5280) is a digitally signed data structure that binds a public key to an identity (a person, server, organisation, or CA).\n\n" +
            "Key fields:\n" +
            "  Subject — the identity this certificate belongs to (Distinguished Name: CN, O, C...)\n" +
            "  Issuer — who signed it (the CA's Distinguished Name)\n" +
            "  SerialNumber — unique within the issuing CA\n" +
            "  Validity — NotBefore and NotAfter timestamps\n" +
            "  SubjectPublicKeyInfo — the public key and algorithm\n" +
            "  Signature — the CA's signature over all the above fields\n\n" +
            "Common extensions:\n" +
            "  SubjectAltName (SAN) — additional names (DNS names, IPs)\n" +
            "  ExtendedKeyUsage (EKU) — allowed uses: TLS server auth, code signing, email...\n" +
            "  BasicConstraints — whether this is a CA cert and path length\n\n" +
            "Encoding: DER (binary ASN.1) or PEM (base64-encoded DER wrapped in -----BEGIN CERTIFICATE----- / -----END CERTIFICATE----- headers)."
        )
        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("CERTIFICATE AUTHORITY AND REVOCATION")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Certificate Authority (CA): an entity that issues and signs certificates. It is trusted to verify identities before signing.\n\n" +
            "Root CA: self-signed (its own Issuer == Subject). The trust anchor. Stored in trust stores. Its private key signs intermediate CA certificates.\n\n" +
            "Intermediate CA: signed by the root (or another intermediate). Issues end-entity certificates. Compromise of an intermediate can be mitigated without revoking the root.\n\n" +
            "Certificate Revocation List (CRL): a signed list of certificate serial numbers that the CA has revoked before their expiry. Has its own validity period and must be refreshed periodically. Endpoint URL embedded in certificate's CDP (CRL Distribution Point) extension.\n\n" +
            "OCSP (Online Certificate Status Protocol): real-time per-certificate status query to an OCSP responder. Faster than downloading a full CRL. Endpoint URL in the certificate's AIA (Authority Information Access) extension.\n\n" +
            "OCSP Stapling: the server pre-fetches a signed OCSP response and includes it in the TLS handshake, avoiding a separate client round-trip."
        )
        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("TRUST CHAIN VERIFICATION")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "To verify a certificate:\n" +
            "1. Build the chain — leaf certificate → intermediate CA(s) → root CA.\n" +
            "2. For each link, verify the signature using the issuer's public key.\n" +
            "3. Check validity periods — all certificates must be within NotBefore..NotAfter.\n" +
            "4. Check revocation — CRL or OCSP for each non-root certificate.\n" +
            "5. The root must be in the trusted store — if it is, the chain is trusted.\n\n" +
            "A chain that is cryptographically valid but whose root is not in the trust store is still untrusted. The trust store is the ultimate arbiter.\n\n" +
            "Path length constraints (BasicConstraints) limit how many intermediate CAs can appear below a given CA."
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock("""
leaf cert (e.g. www.example.com)
  ← signed by Intermediate CA (e.g. DigiCert TLS RSA SHA256 2020 CA1)
      ← signed by Root CA (e.g. DigiCert Global Root CA)
          ← self-signed; must be in Windows ROOT store
        """.trimIndent())
        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("WINDOWS CERTIFICATE STORE")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Windows organises certificates into named logical stores:\n\n" +
            "  MY — personal certificates with their private keys (used for TLS client auth, code signing, email encryption).\n\n" +
            "  CA — intermediate CA certificates (not root CAs). Windows builds chains through these.\n\n" +
            "  ROOT — trusted root CAs. This is the trust anchor store. Placing a CA certificate here tells Windows to trust all certificates signed (directly or transitionally) by that CA.\n\n" +
            "  TrustedPublisher — code-signing certificates trusted for Authenticode. Used by Device Installation and WFP driver signing.\n\n" +
            "  AuthRoot — Microsoft-managed third-party root CAs, distributed via Windows Update (the Microsoft Trusted Root Program).\n\n" +
            "  TRUST — Certificate Trust Lists (CTLs), rarely used directly.\n\n" +
            "Two scopes:\n" +
            "  Machine store — HKLM\\SOFTWARE\\Microsoft\\SystemCertificates\\{store} — all users on the machine.\n" +
            "  User store — HKCU\\SOFTWARE\\Microsoft\\SystemCertificates\\{store} — current user only.\n\n" +
            "Adding a self-signed CA to the ROOT store in the machine scope makes Windows trust all certificates issued by that CA for all users — this is the intended mechanism for enterprise internal PKI (Active Directory Certificate Services)."
        )
        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("APPS THAT USE THEIR OWN STORES")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Not all applications use the Windows certificate store. Some manage their own trust store entirely:\n\n" +
            "Firefox — historically used Mozilla NSS (Network Security Services) with its own root CA bundle, independent of Windows. Firefox 49+ added an option to also check Windows ROOT store.\n\n" +
            "Chrome — prior to version 105 used its own root store on Windows; since Chrome 105 it uses the Chrome Root Store, also independent of Windows.\n\n" +
            "Java — uses a cacerts keystore managed with the keytool command. An enterprise root CA added to Windows ROOT will not be trusted by Java unless also added to cacerts.\n\n" +
            "OpenSSL — uses a CA bundle (PEM file), usually at /etc/ssl/certs/ on Linux. On Windows, applications using OpenSSL directly must configure their own trust bundle.\n\n" +
            "This matters for enterprise environments: adding an internal CA to Windows ROOT is not sufficient — browser and JVM policies may need separate configuration."
        )
        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("KEY APIS")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock("""
// Open a named store (machine scope)
HCERTSTORE hStore = CertOpenSystemStore(0, L"ROOT");

// Find a certificate by subject string
PCCERT_CONTEXT pCert = CertFindCertificateInStore(
    hStore, X509_ASN_ENCODING | PKCS_7_ASN_ENCODING,
    0, CERT_FIND_SUBJECT_STR, L"My CA Name", NULL);

// Build and validate the certificate chain
CERT_CHAIN_PARA chainPara = { sizeof(CERT_CHAIN_PARA) };
PCCERT_CHAIN_CONTEXT pChain;
CertGetCertificateChain(
    NULL,        // default engine
    pCert,       // end-entity certificate
    NULL,        // use current time
    hStore,      // additional store to search
    &chainPara,
    CERT_CHAIN_REVOCATION_CHECK_CHAIN,
    NULL, &pChain);

// Verify against a specific policy (e.g. TLS server auth)
CERT_CHAIN_POLICY_PARA policyPara = { sizeof(policyPara) };
CERT_CHAIN_POLICY_STATUS policyStatus = { sizeof(policyStatus) };
CertVerifyCertificateChainPolicy(
    CERT_CHAIN_POLICY_SSL, pChain, &policyPara, &policyStatus);
// policyStatus.dwError == 0 means valid

// Higher-level: Authenticode code-signing check
WINTRUST_FILE_INFO fileInfo = { sizeof(fileInfo), L"C:\\app.exe" };
WINTRUST_DATA wtData = { sizeof(wtData) };
wtData.dwUnionChoice = WTD_CHOICE_FILE;
wtData.pFile = &fileInfo;
wtData.dwUIChoice = WTD_UI_NONE;
wtData.fdwRevocationChecks = WTD_REVOKE_WHOLECHAIN;
wtData.dwProvFlags = WTD_CACHE_ONLY_URL_RETRIEVAL;
HRESULT hr = WinVerifyTrust(NULL,
    &WINTRUST_ACTION_GENERIC_VERIFY_V2, &wtData);
// S_OK means valid Authenticode signature

// Cleanup
CertFreeCertificateChain(pChain);
CertFreeCertificateContext(pCert);
CertCloseStore(hStore, 0);
        """.trimIndent())
        Spacer(modifier = Modifier.height(32.dp))

        HackerButton("< BACK") { navController.popBackStack() }
    }
}
