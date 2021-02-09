#include <iostream>
#include <cmath>

using namespace std;

int v[10000];

int cercano(int x, int v[], int ini, int fin) { // Complejidad: a = 1, b = 2, c = 0; O(log(n))
	if (ini == fin ) return v[fin]; // Caso Base

	int aux = ini + (fin - ini) / 2;

	if (abs(x - v[aux]) < abs(x - v[aux + 1])) {
		return cercano(x, v, ini, aux); // Recursión por la izda
	}
	else if(abs(x - v[aux]) > abs(x - v[aux + 1])) {
		return cercano(x, v, aux + 1, fin); // recursión por la dcha
	}
	else {
		int iz = cercano(x, v, ini, aux); // Recursión por la izda
		int der = cercano(x, v, aux + 1, fin); // recursión por la dcha
		
		if (abs(x - iz) <= abs(x - der)) return iz;
		else return der;
	}
}

int cercano(int x, int n, int v[]) {
	return cercano(x, v, 0, n - 1);
}

int main() {
	int casos, n, x;
	
	cin >> casos;

	while (casos != 0) {
		cin >> x >> n;

		for (int i = 0; i < n; i++) cin >> v[i];

		cout << cercano(x, n, v) << "\n\n";

		casos--;
	}

	return 0;
}