#include <iostream>
#include <fstream>
#include <string>
#include <regex>

int main() {
    std::ifstream file("patterns_large.txt");
    int count;
    file >> count;
    file.ignore();
    std::string line;
    for (int i = 1; i <= count && std::getline(file, line); ++i) {
        try {
            std::regex r(line);
        } catch (const std::regex_error& e) {
            std::cout << "Line " << (i+1) << ": " << e.what() << std::endl;
            std::cout << "  Pattern: " << line << std::endl;
        }
    }
    return 0;
}
