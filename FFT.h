#pragma once

#include <cmath>
#include <complex>
#include <vector>

#ifndef M_PI
#define M_PI 3.14159265358979323846
#endif

namespace FFT {

// In-place Cooley-Tukey radix-2 FFT
inline void fft(std::vector<std::complex<double>>& data) {
    size_t n = data.size();
    if (n <= 1) return;

    // Bit-reversal permutation
    for (size_t i = 1, j = 0; i < n; ++i) {
        size_t bit = n >> 1;
        while (j & bit) {
            j ^= bit;
            bit >>= 1;
        }
        j ^= bit;
        if (i < j) std::swap(data[i], data[j]);
    }

    // Butterfly stages
    for (size_t len = 2; len <= n; len <<= 1) {
        double angle = -2.0 * M_PI / static_cast<double>(len);
        std::complex<double> wn(std::cos(angle), std::sin(angle));
        for (size_t i = 0; i < n; i += len) {
            std::complex<double> w(1.0, 0.0);
            for (size_t j = 0; j < len / 2; ++j) {
                auto u = data[i + j];
                auto v = w * data[i + j + len / 2];
                data[i + j] = u + v;
                data[i + j + len / 2] = u - v;
                w *= wn;
            }
        }
    }
}

// Take real samples, apply Hann window, return magnitude spectrum (N/2 bins)
inline std::vector<double> magnitudeSpectrum(const std::vector<double>& samples) {
    size_t n = samples.size();
    std::vector<std::complex<double>> data(n);

    // Apply Hann window and convert to complex
    for (size_t i = 0; i < n; ++i) {
        double window = 0.5 * (1.0 - std::cos(2.0 * M_PI * i / (n - 1)));
        data[i] = std::complex<double>(samples[i] * window, 0.0);
    }

    fft(data);

    // Extract magnitudes for first N/2 bins
    size_t half = n / 2;
    std::vector<double> mag(half);
    for (size_t i = 0; i < half; ++i) {
        mag[i] = std::abs(data[i]) / static_cast<double>(n);
    }
    return mag;
}

} // namespace FFT
