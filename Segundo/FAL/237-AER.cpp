#include <iostream>

using namespace std;

bool polidivisible(long long n, int& cif) {
	if (n < 10) { // Caso base
		cif = 1;
		return true;
	}

	if (polidivisible(n / 10, cif)) {
		cif++;
		if (n % cif == 0) return true;
		else return false;
	}
	else return false;
}


int main() {
	long long n;
	int cif;

	cin >> n;

	while (cin) {
		if (polidivisible(n, cif)) cout << "POLIDIVISIBLE\n";
		else cout << "NO POLIDIVISIBLE\n";
		cin >> n;
	}

	return 0;
}