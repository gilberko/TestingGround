#include "WavFile.h"
#include <cmath>
#include <iostream>

#ifndef M_PI
#define M_PI 3.14159265358979323846
#endif

int main() {
    try {
        // Create a new 16-bit stereo WAV at 44100 Hz
        const uint32_t sampleRate = 44100;
        const double duration = 1.0; // 1 second
        const uint32_t numSamples = static_cast<uint32_t>(sampleRate * duration);

        {
            WavFile wav("test_output.wav", 2, sampleRate, 16);

            // Generate a 440 Hz sine wave on channel 0 and 880 Hz on channel 1
            std::vector<double> ch0(numSamples), ch1(numSamples);
            for (uint32_t i = 0; i < numSamples; ++i) {
                double t = static_cast<double>(i) / sampleRate;
                ch0[i] = 0.5 * std::sin(2.0 * M_PI * 440.0 * t);
                ch1[i] = 0.3 * std::sin(2.0 * M_PI * 880.0 * t);
            }

            wav.writeSamples(0, 0, ch0);
            wav.writeSamples(1, 0, ch1);
            wav.save();

            std::cout << "Created test_output.wav\n";
            std::cout << "  Channels:       " << wav.numChannels() << "\n";
            std::cout << "  Sample rate:    " << wav.sampleRate() << " Hz\n";
            std::cout << "  Bits/sample:    " << wav.bitsPerSample() << "\n";
            std::cout << "  Total samples:  " << wav.numSamples() << "\n";
            std::cout << "  Duration:       " << wav.duration() << " s\n";
        }

        // Re-open and verify
        {
            WavFile wav("test_output.wav");

            std::cout << "\nRe-opened test_output.wav\n";
            std::cout << "  Channels:       " << wav.numChannels() << "\n";
            std::cout << "  Sample rate:    " << wav.sampleRate() << " Hz\n";
            std::cout << "  Bits/sample:    " << wav.bitsPerSample() << "\n";
            std::cout << "  Total samples:  " << wav.numSamples() << "\n";
            std::cout << "  Duration:       " << wav.duration() << " s\n";

            // Read first 10 samples from channel 0
            auto samples = wav.readSamples(0, 0, 10);
            std::cout << "\n  First 10 samples (ch0): ";
            for (double s : samples)
                std::cout << s << " ";
            std::cout << "\n";

            // Halve the volume on channel 0 for first 1000 samples
            auto chunk = wav.readSamples(0, 0, 1000);
            for (auto& s : chunk) s *= 0.5;
            wav.writeSamples(0, 0, chunk);

            // Save modified version
            wav.saveAs("test_modified.wav");
            std::cout << "\nSaved modified copy to test_modified.wav\n";
        }

        std::cout << "\nAll tests passed!\n";

    } catch (const std::exception& e) {
        std::cerr << "Error: " << e.what() << "\n";
        return 1;
    }

    return 0;
}
