#include "RegexMatcher.h"
#include <chrono>
#include <iostream>
#include <string>

int main(int argc, char* argv[]) {
    if (argc < 2) {
        std::cerr << "Usage: " << argv[0] << " <patterns_file>" << std::endl;
        return 1;
    }

    try {
        RegexMatcher matcher(argv[1]);

        std::string line;
        std::cout << "Enter strings to test (Ctrl+Z to quit):" << std::endl;
        while (std::cout << "> " && std::getline(std::cin, line)) {
            auto start = std::chrono::high_resolution_clock::now();
            bool result = matcher.matches(line);
            auto end = std::chrono::high_resolution_clock::now();
            double ms = std::chrono::duration<double, std::milli>(end - start).count();

            std::cout << "  " << (result ? "MATCH" : "NO MATCH")
                      << " (" << ms << " ms)" << std::endl;
        }
    } catch (const std::exception& e) {
        std::cerr << "Error: " << e.what() << std::endl;
        return 1;
    }

    return 0;
}
