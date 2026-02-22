#ifndef REGEX_MATCHER_H
#define REGEX_MATCHER_H

#include <string>
#include <vector>
#include <regex>

class RegexMatcher {
public:
    explicit RegexMatcher(const std::string& filename);

    bool matches(const std::string& text) const;

private:
    std::vector<std::regex> patterns_;
};

#endif
