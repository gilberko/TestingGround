# Runs the offline level/physics checks with the LuaJIT that ships inside the Defold editor jar.
$ErrorActionPreference = "Stop"
$Pkg = "C:\Defold-x86_64-win32\Defold\packages"
$Jar = (Get-ChildItem $Pkg -Filter "defold-*.jar" | Select-Object -First 1).FullName
$Tmp = Join-Path $env:TEMP "netta-luajit"
$Lua = Join-Path $Tmp "luajit-64.exe"
if (-not (Test-Path $Lua)) {
    New-Item -ItemType Directory -Force $Tmp | Out-Null
    Add-Type -AssemblyName System.IO.Compression.FileSystem
    $zip = [System.IO.Compression.ZipFile]::OpenRead($Jar)
    $entry = $zip.Entries | Where-Object { $_.FullName -eq "libexec/x86_64-win32/luajit-64.exe" }
    [System.IO.Compression.ZipFileExtensions]::ExtractToFile($entry, $Lua, $true)
    $zip.Dispose()
}
$Root = Split-Path $PSScriptRoot
& $Lua (Join-Path $PSScriptRoot "check_levels.lua") $Root
exit $LASTEXITCODE
