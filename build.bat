@echo off
"C:\Program Files\Microsoft Visual Studio\2022\Professional\VC\Auxiliary\Build\vcvarsall.bat" x64 >nul 2>&1 && (
  cd /d C:\Code\TestingGround
  echo === Compiling ===
  cl /std:c++17 /EHsc WavFile.cpp wav_play.cpp /Fe:wav_play.exe
  echo === Running ===
  C:\Code\TestingGround\wav_play.exe %*
) > C:\Code\TestingGround\build_log.txt 2>&1
