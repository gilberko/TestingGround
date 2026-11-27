# Builds Netta The Super Agent with Defold's command-line builder (Bob) from the installed editor.
#   .\build.ps1                 -> debug-signed APK in dist\android
#   .\build.ps1 -Release        -> release APK, signed with the keystore from keystore.properties (gitignored)
#   .\build.ps1 -Desktop        -> Windows build in dist\desktop (for a quick local smoke run)
param([switch]$Release, [switch]$Desktop)
$ErrorActionPreference = "Stop"

$Pkg = "C:\Defold-x86_64-win32\Defold\packages"
$Java = Join-Path $Pkg "jdk-25.0.4.1+1\bin\java.exe"
$Jar = (Get-ChildItem $Pkg -Filter "defold-*.jar" | Select-Object -First 1).FullName
$Root = $PSScriptRoot

python (Join-Path $Root "tools\make_sprites.py")
if ($LASTEXITCODE -ne 0) { throw "sprite generation failed" }

$common = @("-cp", $Jar, "com.dynamo.bob.Bob", "--root", $Root, "--archive")
if ($Desktop) {
    $args2 = $common + @("--platform", "x86_64-win32", "--variant", "debug", "--bundle-output", (Join-Path $Root "dist\desktop"), "resolve", "build", "bundle")
} else {
    $variant = if ($Release) { "release" } else { "debug" }
    $args2 = $common + @("--platform", "arm64-android", "--architectures", "arm64-android", "--variant", $variant,
        "--bundle-format", "apk", "--bundle-output", (Join-Path $Root "dist\android"))
    if ($Release) {
        $propsFile = Join-Path $Root "keystore.properties"
        if (-not (Test-Path $propsFile)) { throw "keystore.properties not found (storeFile, storePassword, keyAlias, keyPassword)" }
        $props = @{}
        Get-Content $propsFile | Where-Object { $_ -match "=" } | ForEach-Object { $k, $v = $_ -split "=", 2; $props[$k.Trim()] = $v.Trim() }
        $args2 += @("--keystore", (Join-Path $Root $props.storeFile), "--keystore-pass", (Join-Path $Root "build\ks.pass"),
            "--keystore-alias", $props.keyAlias, "--key-pass", (Join-Path $Root "build\key.pass"))
        New-Item -ItemType Directory -Force (Join-Path $Root "build") | Out-Null
        Set-Content -Path (Join-Path $Root "build\ks.pass") -Value $props.storePassword -NoNewline -Encoding ascii
        Set-Content -Path (Join-Path $Root "build\key.pass") -Value $props.keyPassword -NoNewline -Encoding ascii
    }
    $args2 += @("resolve", "build", "bundle")
}
$ErrorActionPreference = "Continue"   # Java prints harmless warnings on stderr
& $Java @args2 2>&1 | ForEach-Object { "$_" } | Where-Object { $_ -notmatch "^WARNING:|Downloading|Reading classes|Cleaning engine" }
$code = $LASTEXITCODE
$ErrorActionPreference = "Stop"
Remove-Item -ErrorAction SilentlyContinue (Join-Path $Root "build\ks.pass"), (Join-Path $Root "build\key.pass")
if ($code -ne 0) { throw "Bob build failed ($code)" }
Get-ChildItem -Recurse (Join-Path $Root "dist") -Include *.apk, *.exe | Select-Object FullName, Length
