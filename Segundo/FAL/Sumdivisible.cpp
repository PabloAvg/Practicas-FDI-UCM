#include <iostream>

using namespace std;

// Complejidad: O(log n)
bool sumdivisible(int num, int& ant, int& dig) {
	if (num == 0) { dig = 1; ant = 0; return true; } // Caso base.

	if (sumdivisible(num / 10, ant, dig)) {
		ant += num % 10;
		if (ant % dig == 0) {
			dig++;
			return true;
		}
		else {
			return false;
		}
	}
	return false;
}

int main() {
	int num, ant = 0, dig = 0;

	cin >> num;

	while (num != 0) {
		if (sumdivisible(num, ant, dig)) {
			cout << "SI\n";
		}
		else {
			cout << "NO\n";
		}
		cin >> num;
	}

	return 0;
}