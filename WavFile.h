#pragma once

#include <cstdint>
#include <fstream>
#include <string>
#include <vector>

class WavFile {
public:
    // Open existing WAV file for reading/modifying
    explicit WavFile(const std::string& filepath);

    // Create new WAV file with given parameters
    WavFile(const std::string& filepath, uint16_t numChannels,
            uint32_t sampleRate, uint16_t bitsPerSample);

    ~WavFile();

    // No copy, allow move
    WavFile(const WavFile&) = delete;
    WavFile& operator=(const WavFile&) = delete;
    WavFile(WavFile&& other) noexcept;
    WavFile& operator=(WavFile&& other) noexcept;

    // Header info
    uint16_t numChannels() const;
    uint32_t sampleRate() const;
    uint16_t bitsPerSample() const;
    uint32_t numSamples() const;   // total samples per channel
    double duration() const;        // seconds

    // Streaming read — reads `count` samples starting at `sampleOffset` for a given channel
    // Samples are normalized to [-1.0, 1.0] doubles
    std::vector<double> readSamples(uint16_t channel, uint32_t sampleOffset, uint32_t count);

    // Streaming write — writes samples at `sampleOffset` for a given channel
    void writeSamples(uint16_t channel, uint32_t sampleOffset, const std::vector<double>& samples);

    // Save changes (rewrites header with updated sizes if data was appended)
    void save();

    // Save a copy to a new file path
    void saveAs(const std::string& filepath);

private:
    struct WavHeader {
        // RIFF chunk
        uint32_t chunkSize = 0;

        // fmt sub-chunk
        uint16_t audioFormat = 1;   // PCM = 1
        uint16_t channels = 0;
        uint32_t sampleRate_ = 0;
        uint32_t byteRate = 0;
        uint16_t blockAlign = 0;
        uint16_t bitsPerSample_ = 0;

        // data sub-chunk
        uint32_t dataSize = 0;
    };

    void parseHeader();
    void writeHeader();
    void writeHeaderTo(std::fstream& file);
    std::streampos sampleByteOffset(uint16_t channel, uint32_t sampleOffset) const;
    int32_t readRawSample(std::fstream& file);
    void writeRawSample(std::fstream& file, int32_t value);
    double normalizeToDouble(int32_t raw) const;
    int32_t denormalizeFromDouble(double value) const;

    std::string filepath_;
    std::fstream file_;
    WavHeader header_;
    std::streampos dataOffset_ = 0;  // byte position where audio data begins
};
