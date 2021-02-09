#include <iostream>

using namespace std;

int v[100000];

bool esPrometedora(int i, int ant, int sum, int sump) {
	if (sump > sum) return false;
	if (ant == i) return false;
	return true;
}

int divBack(int solp[] /* Solución parcial */, int k /* Por donde voy */, int sump /* La suma que llevo */,
	int n /* Los digitos que debe tener */, int sum /* La suma de las cifras */) {

	if (n == 1) { // Caso Base
		if (v[0] <= sum) return 1;
		else return 0;
	}

	int ret = 0;

	for (int i = 0; i < 10; i++) { // Backtrack
		solp[k] = i;
		sump += i;

		if (esPrometedora(i, v[k - 1] /* Anterior a mí */, sum, sump)) {
			if (k == n - 1) {
				ret++;
			}
			else {
				ret += divBack(solp, k + 1, sump, n, sum);
			}
		}
		sump -= i;
	}

	return ret;
}

int main() {
	int n, sum;

	cin >> n;

	while (n != -1) {
		cin >> v[0] >> sum;

		cout << divBack(v, 1, v[0], n, sum) << '\n';

		cin >> n;
	}

	return 0;
}