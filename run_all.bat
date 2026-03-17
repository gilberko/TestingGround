@echo off
"C:\Program Files\Microsoft Visual Studio\2022\Professional\VC\Auxiliary\Build\vcvarsall.bat" x64 >nul 2>&1

echo ============================================
echo === chord.wav ===
echo ============================================
C:\Code\TestingGround\wav_play.exe C:\temp_wav\chord.wav
echo.

echo ============================================
echo === chimes.wav ===
echo ============================================
C:\Code\TestingGround\wav_play.exe C:\temp_wav\chimes.wav
echo.

echo ============================================
echo === ding.wav ===
echo ============================================
C:\Code\TestingGround\wav_play.exe C:\temp_wav\ding.wav
echo.

echo ============================================
echo === Alarm01.wav ===
echo ============================================
C:\Code\TestingGround\wav_play.exe C:\temp_wav\Alarm01.wav
