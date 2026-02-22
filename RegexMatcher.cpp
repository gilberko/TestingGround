#include "RegexMatcher.h"
#include <fstream>
#include <stdexcept>

RegexMatcher::RegexMatcher(const std::string& filename) {
    std::ifstream file(filename);
    if (!file.is_open()) {
        throw std::runtime_error("Could not open file: " + filename);
    }

    int count;
    file >> count;
    file.ignore(); // skip the newline after the number

    patterns_.reserve(count);
    std::string line;
    for (int i = 0; i < count && std::getline(file, line); ++i) {
        patterns_.emplace_back(line, std::regex::ECMAScript | std::regex::optimize);
    }
}

bool RegexMatcher::matches(const std::string& text) const {
    for (const auto& pattern : patterns_) {
        if (std::regex_search(text, pattern)) {
            return true;
        }
    }
    return false;
}
