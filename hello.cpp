#include <iostream>

long long fibonacci(int n) {
    if (n <= 1) return n;
    long long a = 0, b = 1;
    for (int i = 2; i <= n; i++) {
        long long temp = a + b;
        a = b;
        b = temp;
    }
    return b;
}

int main() {
    int n;
    std::cout << "Enter n: ";
    std::cin >> n;
    if (n < 0) {
        std::cerr << "n must be non-negative" << std::endl;
        return 1;
    }
    std::cout << "Fibonacci(" << n << ") = " << fibonacci(n) << std::endl;
    return 0;
}
