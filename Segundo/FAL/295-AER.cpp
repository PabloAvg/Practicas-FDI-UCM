#include <iostream>

using namespace std;

const int K = 31543;

unsigned long long elevame(unsigned long long x, unsigned long long n) { // Complejidad: a = 2 , b = 2 , k = 0 ; O(log(n)) (Logaritmica)
	if (n == 0) return 1;
	unsigned long long mitad = n / 2;
	unsigned long long aux = elevame(x, mitad);
	aux = aux * aux;
	if (n % 2 != 0) return (aux * x) % K;
	else return (aux) % K;
}

int main() {
	unsigned long long x, n;

	cin >> x >> n;

	while (x != 0 || n != 0) {
		cout << elevame(x, n) << '\n';
		cin >> x >> n;
	}

	return 0;
}