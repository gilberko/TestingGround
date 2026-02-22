#include "WavFile.h"

#include <algorithm>
#include <cstring>
#include <stdexcept>

// ── Helpers ──────────────────────────────────────────────────────────────────

static void expectTag(std::fstream& f, const char* tag) {
    char buf[4];
    f.read(buf, 4);
    if (!f || std::memcmp(buf, tag, 4) != 0)
        throw std::runtime_error(std::string("Expected tag '") + tag + "' not found");
}

template <typename T>
static T readLE(std::fstream& f) {
    T val = 0;
    f.read(reinterpret_cast<char*>(&val), sizeof(T));
    if (!f) throw std::runtime_error("Unexpected end of file");
    return val;
}

template <typename T>
static void writeLE(std::fstream& f, T val) {
    f.write(reinterpret_cast<const char*>(&val), sizeof(T));
}

// ── Construction / destruction ──────────────────────────────────────────────

WavFile::WavFile(const std::string& filepath) : filepath_(filepath) {
    file_.open(filepath, std::ios::in | std::ios::out | std::ios::binary);
    if (!file_)
        throw std::runtime_error("Cannot open file: " + filepath);
    parseHeader();
}

WavFile::WavFile(const std::string& filepath, uint16_t numChannels,
                 uint32_t sampleRate, uint16_t bitsPerSample)
    : filepath_(filepath) {

    if (bitsPerSample != 8 && bitsPerSample != 16 &&
        bitsPerSample != 24 && bitsPerSample != 32)
        throw std::runtime_error("Unsupported bits per sample: " +
                                 std::to_string(bitsPerSample));

    header_.audioFormat = 1; // PCM
    header_.channels = numChannels;
    header_.sampleRate_ = sampleRate;
    header_.bitsPerSample_ = bitsPerSample;
    header_.blockAlign = numChannels * (bitsPerSample / 8);
    header_.byteRate = sampleRate * header_.blockAlign;
    header_.dataSize = 0;
    header_.chunkSize = 36; // header only, no data yet

    file_.open(filepath, std::ios::in | std::ios::out | std::ios::binary | std::ios::trunc);
    if (!file_)
        throw std::runtime_error("Cannot create file: " + filepath);

    writeHeader();
    dataOffset_ = file_.tellp();
}

WavFile::~WavFile() {
    if (file_.is_open())
        file_.close();
}

WavFile::WavFile(WavFile&& other) noexcept
    : filepath_(std::move(other.filepath_)),
      file_(std::move(other.file_)),
      header_(other.header_),
      dataOffset_(other.dataOffset_) {}

WavFile& WavFile::operator=(WavFile&& other) noexcept {
    if (this != &other) {
        if (file_.is_open()) file_.close();
        filepath_ = std::move(other.filepath_);
        file_ = std::move(other.file_);
        header_ = other.header_;
        dataOffset_ = other.dataOffset_;
    }
    return *this;
}

// ── Header parsing ──────────────────────────────────────────────────────────

void WavFile::parseHeader() {
    file_.seekg(0);

    expectTag(file_, "RIFF");
    header_.chunkSize = readLE<uint32_t>(file_);
    expectTag(file_, "WAVE");

    // Find fmt chunk (skip unknown chunks)
    bool foundFmt = false, foundData = false;
    while (file_ && !(foundFmt && foundData)) {
        char chunkId[4];
        file_.read(chunkId, 4);
        if (!file_) break;

        uint32_t chunkSize = readLE<uint32_t>(file_);

        if (std::memcmp(chunkId, "fmt ", 4) == 0) {
            if (chunkSize < 16)
                throw std::runtime_error("fmt chunk too small");

            header_.audioFormat = readLE<uint16_t>(file_);
            header_.channels = readLE<uint16_t>(file_);
            header_.sampleRate_ = readLE<uint32_t>(file_);
            header_.byteRate = readLE<uint32_t>(file_);
            header_.blockAlign = readLE<uint16_t>(file_);
            header_.bitsPerSample_ = readLE<uint16_t>(file_);

            if (header_.audioFormat != 1)
                throw std::runtime_error("Only PCM format is supported (audioFormat=" +
                                         std::to_string(header_.audioFormat) + ")");

            // Skip any extra fmt bytes
            if (chunkSize > 16)
                file_.seekg(chunkSize - 16, std::ios::cur);

            foundFmt = true;

        } else if (std::memcmp(chunkId, "data", 4) == 0) {
            header_.dataSize = chunkSize;
            dataOffset_ = file_.tellg();
            foundData = true;
            // Don't skip past data — we'll seek into it later

        } else {
            // Skip unknown chunk
            file_.seekg(chunkSize, std::ios::cur);
        }
    }

    if (!foundFmt)
        throw std::runtime_error("No fmt chunk found");
    if (!foundData)
        throw std::runtime_error("No data chunk found");

    if (header_.bitsPerSample_ != 8 && header_.bitsPerSample_ != 16 &&
        header_.bitsPerSample_ != 24 && header_.bitsPerSample_ != 32)
        throw std::runtime_error("Unsupported bits per sample: " +
                                 std::to_string(header_.bitsPerSample_));
}

void WavFile::writeHeader() {
    writeHeaderTo(file_);
}

void WavFile::writeHeaderTo(std::fstream& f) {
    f.seekp(0);

    // RIFF header
    f.write("RIFF", 4);
    writeLE<uint32_t>(f, header_.chunkSize);
    f.write("WAVE", 4);

    // fmt sub-chunk
    f.write("fmt ", 4);
    writeLE<uint32_t>(f, 16); // PCM fmt chunk is always 16 bytes
    writeLE<uint16_t>(f, header_.audioFormat);
    writeLE<uint16_t>(f, header_.channels);
    writeLE<uint32_t>(f, header_.sampleRate_);
    writeLE<uint32_t>(f, header_.byteRate);
    writeLE<uint16_t>(f, header_.blockAlign);
    writeLE<uint16_t>(f, header_.bitsPerSample_);

    // data sub-chunk header
    f.write("data", 4);
    writeLE<uint32_t>(f, header_.dataSize);

    f.flush();
}

// ── Accessors ───────────────────────────────────────────────────────────────

uint16_t WavFile::numChannels() const { return header_.channels; }
uint32_t WavFile::sampleRate() const { return header_.sampleRate_; }
uint16_t WavFile::bitsPerSample() const { return header_.bitsPerSample_; }

uint32_t WavFile::numSamples() const {
    if (header_.blockAlign == 0) return 0;
    return header_.dataSize / header_.blockAlign;
}

double WavFile::duration() const {
    if (header_.sampleRate_ == 0) return 0.0;
    return static_cast<double>(numSamples()) / header_.sampleRate_;
}

// ── Sample I/O helpers ──────────────────────────────────────────────────────

std::streampos WavFile::sampleByteOffset(uint16_t channel, uint32_t sampleOffset) const {
    uint16_t bytesPerSample = header_.bitsPerSample_ / 8;
    return dataOffset_ +
           static_cast<std::streamoff>(sampleOffset) * header_.blockAlign +
           static_cast<std::streamoff>(channel) * bytesPerSample;
}

int32_t WavFile::readRawSample(std::fstream& f) {
    uint16_t bytesPerSample = header_.bitsPerSample_ / 8;
    uint8_t buf[4] = {0};
    f.read(reinterpret_cast<char*>(buf), bytesPerSample);
    if (!f) throw std::runtime_error("Failed to read sample data");

    if (header_.bitsPerSample_ == 8) {
        // 8-bit WAV is unsigned (0-255, center at 128)
        return static_cast<int32_t>(buf[0]) - 128;
    }

    // 16, 24, 32-bit are signed little-endian
    int32_t val = 0;
    if (header_.bitsPerSample_ == 16) {
        val = static_cast<int16_t>(buf[0] | (buf[1] << 8));
    } else if (header_.bitsPerSample_ == 24) {
        val = buf[0] | (buf[1] << 8) | (buf[2] << 16);
        if (val & 0x800000) val |= 0xFF000000; // sign extend
    } else { // 32-bit
        val = buf[0] | (buf[1] << 8) | (buf[2] << 16) | (buf[3] << 24);
    }
    return val;
}

void WavFile::writeRawSample(std::fstream& f, int32_t value) {
    uint16_t bytesPerSample = header_.bitsPerSample_ / 8;
    uint8_t buf[4] = {0};

    if (header_.bitsPerSample_ == 8) {
        // 8-bit unsigned
        int32_t v = value + 128;
        buf[0] = static_cast<uint8_t>(std::clamp(v, 0, 255));
    } else if (header_.bitsPerSample_ == 16) {
        int16_t v = static_cast<int16_t>(std::clamp(value, -32768, 32767));
        buf[0] = v & 0xFF;
        buf[1] = (v >> 8) & 0xFF;
    } else if (header_.bitsPerSample_ == 24) {
        int32_t v = std::clamp(value, -8388608, 8388607);
        buf[0] = v & 0xFF;
        buf[1] = (v >> 8) & 0xFF;
        buf[2] = (v >> 16) & 0xFF;
    } else { // 32-bit
        buf[0] = value & 0xFF;
        buf[1] = (value >> 8) & 0xFF;
        buf[2] = (value >> 16) & 0xFF;
        buf[3] = (value >> 24) & 0xFF;
    }

    f.write(reinterpret_cast<const char*>(buf), bytesPerSample);
}

double WavFile::normalizeToDouble(int32_t raw) const {
    switch (header_.bitsPerSample_) {
        case 8:  return raw / 128.0;                 // range: [-1.0, ~0.992]
        case 16: return raw / 32768.0;
        case 24: return raw / 8388608.0;
        case 32: return raw / 2147483648.0;
        default: return 0.0;
    }
}

int32_t WavFile::denormalizeFromDouble(double value) const {
    value = std::clamp(value, -1.0, 1.0);
    switch (header_.bitsPerSample_) {
        case 8:  return static_cast<int32_t>(value * 127);
        case 16: return static_cast<int32_t>(value * 32767);
        case 24: return static_cast<int32_t>(value * 8388607);
        case 32: return static_cast<int32_t>(value * 2147483647.0);
        default: return 0;
    }
}

// ── Streaming read/write ────────────────────────────────────────────────────

std::vector<double> WavFile::readSamples(uint16_t channel, uint32_t sampleOffset, uint32_t count) {
    if (channel >= header_.channels)
        throw std::out_of_range("Channel " + std::to_string(channel) +
                                " out of range (file has " +
                                std::to_string(header_.channels) + " channels)");

    uint32_t totalSamples = numSamples();
    if (sampleOffset >= totalSamples)
        throw std::out_of_range("Sample offset " + std::to_string(sampleOffset) +
                                " beyond end of data (" +
                                std::to_string(totalSamples) + " samples)");

    // Clamp count to available samples
    count = std::min(count, totalSamples - sampleOffset);

    std::vector<double> result;
    result.reserve(count);

    uint16_t bytesPerSample = header_.bitsPerSample_ / 8;

    for (uint32_t i = 0; i < count; ++i) {
        file_.seekg(sampleByteOffset(channel, sampleOffset + i));
        int32_t raw = readRawSample(file_);
        result.push_back(normalizeToDouble(raw));
    }

    return result;
}

void WavFile::writeSamples(uint16_t channel, uint32_t sampleOffset,
                           const std::vector<double>& samples) {
    if (channel >= header_.channels)
        throw std::out_of_range("Channel " + std::to_string(channel) +
                                " out of range (file has " +
                                std::to_string(header_.channels) + " channels)");

    for (uint32_t i = 0; i < samples.size(); ++i) {
        file_.seekp(sampleByteOffset(channel, sampleOffset + i));
        int32_t raw = denormalizeFromDouble(samples[i]);
        writeRawSample(file_, raw);
    }

    // Update data size if we wrote past the current end
    uint32_t lastFrame = sampleOffset + static_cast<uint32_t>(samples.size());
    uint32_t requiredDataSize = lastFrame * header_.blockAlign;
    if (requiredDataSize > header_.dataSize) {
        header_.dataSize = requiredDataSize;
        header_.chunkSize = 36 + header_.dataSize;
    }

    file_.flush();
}

// ── Save ────────────────────────────────────────────────────────────────────

void WavFile::save() {
    header_.chunkSize = 36 + header_.dataSize;
    writeHeader();
    file_.flush();
}

void WavFile::saveAs(const std::string& filepath) {
    std::fstream out(filepath, std::ios::out | std::ios::binary | std::ios::trunc);
    if (!out)
        throw std::runtime_error("Cannot create file: " + filepath);

    // Write header
    writeHeaderTo(out);

    // Stream data in 64KB chunks
    file_.seekg(dataOffset_);
    const size_t bufSize = 65536;
    std::vector<char> buf(bufSize);
    uint32_t remaining = header_.dataSize;

    while (remaining > 0) {
        size_t toRead = std::min(static_cast<size_t>(remaining), bufSize);
        file_.read(buf.data(), toRead);
        auto bytesRead = file_.gcount();
        if (bytesRead <= 0) break;
        out.write(buf.data(), bytesRead);
        remaining -= static_cast<uint32_t>(bytesRead);
    }

    out.flush();
    out.close();
}
