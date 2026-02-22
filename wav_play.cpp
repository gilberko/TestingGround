#include "WavFile.h"
#include "FFT.h"
#include <algorithm>
#include <cmath>
#include <cstdint>
#include <iomanip>
#include <iostream>
#include <string>
#include <vector>
#define NOMINMAX
#include <windows.h>
#pragma comment(lib, "winmm.lib")

static const uint32_t FFT_SIZE = 2048;
static const int TOP_PEAKS = 5;

// Map a frequency (Hz) to the nearest musical note name (e.g. "A4", "C#5")
static std::string frequencyToNote(double freq) {
    if (freq <= 0.0) return "---";
    static const char* noteNames[] = {
        "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"
    };
    // Semitones from A4 (440Hz)
    double semitones = 12.0 * std::log2(freq / 440.0);
    int semi = static_cast<int>(std::round(semitones));
    // A4 is index 9 in octave 4 (C=0, A=9)
    int noteIndex = ((semi % 12) + 12 + 9) % 12;
    int octave = 4 + (semi + 9) / 12 - (noteIndex < 9 ? 1 : 0);
    // Simpler octave calculation: A4=440, each 12 semitones is one octave
    // MIDI note number approach: A4 = MIDI 69
    int midi = 69 + semi;
    octave = (midi / 12) - 1;
    noteIndex = midi % 12;
    return std::string(noteNames[noteIndex]) + std::to_string(octave);
}

// Find top N frequency peaks by magnitude, skipping DC (bin 0)
struct FreqPeak {
    double frequency;
    double magnitude;
};

static std::vector<FreqPeak> findTopPeaks(const std::vector<double>& mag, uint32_t sampleRate) {
    size_t n = mag.size(); // N/2 bins
    double binWidth = static_cast<double>(sampleRate) / (n * 2);

    // Collect all bins except DC
    std::vector<std::pair<double, size_t>> bins; // (magnitude, index)
    for (size_t i = 1; i < n; ++i) {
        bins.push_back({mag[i], i});
    }

    // Partial sort to get top peaks
    size_t count = std::min(static_cast<size_t>(TOP_PEAKS), bins.size());
    std::partial_sort(bins.begin(), bins.begin() + count, bins.end(),
        [](const auto& a, const auto& b) { return a.first > b.first; });

    std::vector<FreqPeak> peaks(count);
    for (size_t i = 0; i < count; ++i) {
        peaks[i].frequency = bins[i].second * binWidth;
        peaks[i].magnitude = bins[i].first;
    }
    return peaks;
}

int main(int argc, char* argv[]) {
    try {
        std::string filepath = (argc > 1) ? argv[1] : "C:\\temp_wav\\Alarm08.wav";
        WavFile wav(filepath);

        std::cout << "Opened: " << filepath << "\n";
        std::cout << "  Channels:    " << wav.numChannels() << "\n";
        std::cout << "  Sample rate: " << wav.sampleRate() << " Hz\n";
        std::cout << "  Bits/sample: " << wav.bitsPerSample() << "\n";
        std::cout << "  Duration:    " << wav.duration() << " s\n";
        std::cout << "  Samples:     " << wav.numSamples() << "\n\n";

        uint32_t totalSamples = wav.numSamples();
        uint16_t channels = wav.numChannels();
        uint16_t bps = wav.bitsPerSample();
        uint16_t bytesPerSample = bps / 8;
        uint16_t blockAlign = channels * bytesPerSample;
        uint32_t sr = wav.sampleRate();

        // Read all channel data upfront
        std::vector<std::vector<double>> channelData(channels);
        for (uint16_t ch = 0; ch < channels; ++ch) {
            channelData[ch] = wav.readSamples(ch, 0, totalSamples);
        }

        // Set up waveOut
        WAVEFORMATEX wfx = {};
        wfx.wFormatTag = WAVE_FORMAT_PCM;
        wfx.nChannels = channels;
        wfx.nSamplesPerSec = sr;
        wfx.wBitsPerSample = bps;
        wfx.nBlockAlign = blockAlign;
        wfx.nAvgBytesPerSec = sr * blockAlign;

        HWAVEOUT hWaveOut = nullptr;
        MMRESULT res = waveOutOpen(&hWaveOut, WAVE_MAPPER, &wfx, 0, 0, CALLBACK_NULL);
        if (res != MMSYSERR_NOERROR) {
            std::cerr << "waveOutOpen failed: " << res << "\n";
            return 1;
        }

        std::cout << "Playing with FFT analysis (chunk size: " << FFT_SIZE << " samples)...\n\n";

        // Process audio in chunks
        uint32_t chunkSize = FFT_SIZE;
        uint32_t numChunks = (totalSamples + chunkSize - 1) / chunkSize;

        for (uint32_t c = 0; c < numChunks; ++c) {
            uint32_t start = c * chunkSize;
            uint32_t count = std::min(chunkSize, totalSamples - start);

            // Build PCM buffer for this chunk
            std::vector<uint8_t> pcmBuf(count * blockAlign);
            for (uint32_t i = 0; i < count; ++i) {
                for (uint16_t ch = 0; ch < channels; ++ch) {
                    double val = channelData[ch][start + i];
                    val = (val < -1.0) ? -1.0 : (val > 1.0 ? 1.0 : val);

                    size_t offset = i * blockAlign + ch * bytesPerSample;

                    if (bps == 8) {
                        int32_t raw = static_cast<int32_t>(val * 127) + 128;
                        pcmBuf[offset] = static_cast<uint8_t>(raw < 0 ? 0 : (raw > 255 ? 255 : raw));
                    } else if (bps == 16) {
                        int16_t raw = static_cast<int16_t>(val * 32767);
                        pcmBuf[offset]     = raw & 0xFF;
                        pcmBuf[offset + 1] = (raw >> 8) & 0xFF;
                    } else if (bps == 24) {
                        int32_t raw = static_cast<int32_t>(val * 8388607);
                        pcmBuf[offset]     = raw & 0xFF;
                        pcmBuf[offset + 1] = (raw >> 8) & 0xFF;
                        pcmBuf[offset + 2] = (raw >> 16) & 0xFF;
                    } else if (bps == 32) {
                        int32_t raw = static_cast<int32_t>(val * 2147483647.0);
                        pcmBuf[offset]     = raw & 0xFF;
                        pcmBuf[offset + 1] = (raw >> 8) & 0xFF;
                        pcmBuf[offset + 2] = (raw >> 16) & 0xFF;
                        pcmBuf[offset + 3] = (raw >> 24) & 0xFF;
                    }
                }
            }

            // Submit chunk for playback
            WAVEHDR hdr = {};
            hdr.lpData = reinterpret_cast<LPSTR>(pcmBuf.data());
            hdr.dwBufferLength = static_cast<DWORD>(pcmBuf.size());
            waveOutPrepareHeader(hWaveOut, &hdr, sizeof(hdr));
            waveOutWrite(hWaveOut, &hdr, sizeof(hdr));

            // Run FFT on channel 0 for this chunk
            if (count >= 64) { // Only analyze if chunk is large enough
                std::vector<double> samples(chunkSize, 0.0);
                for (uint32_t i = 0; i < count; ++i) {
                    samples[i] = channelData[0][start + i];
                }

                auto mag = FFT::magnitudeSpectrum(samples);
                auto peaks = findTopPeaks(mag, sr);

                double timeSec = static_cast<double>(start) / sr;
                std::cout << "[" << std::fixed << std::setprecision(2) << timeSec << "s] Top freqs:";
                for (const auto& p : peaks) {
                    std::cout << "  " << static_cast<int>(p.frequency + 0.5) << "Hz "
                              << frequencyToNote(p.frequency)
                              << " (" << std::fixed << std::setprecision(2) << p.magnitude << ")";
                }
                std::cout << "\n";
            }

            // Wait for this chunk to finish playing
            while (!(hdr.dwFlags & WHDR_DONE)) {
                Sleep(10);
            }
            waveOutUnprepareHeader(hWaveOut, &hdr, sizeof(hdr));
        }

        std::cout << "\nDone.\n";
        waveOutClose(hWaveOut);

    } catch (const std::exception& e) {
        std::cerr << "Error: " << e.what() << "\n";
        return 1;
    }

    return 0;
}
