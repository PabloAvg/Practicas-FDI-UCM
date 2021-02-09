#include <iostream>

using namespace std;

int c[1000];
int d[1000];

int cruzados(int c[], int d[], int i, int n) {
	
	if (i == n) {
		if (c[i] < d[i]) return -2;
		else if (d[i] < c[i]) return -1;
		else return i; // si son iguales
	}

	int iz = cruzados(c, d, i, n/2);
	int der = cruzados(c, d, n/2 + 1, n);

	if (iz == i) return i;
	if (der == n) return n;

	if (iz != der) return i;

	return -1;

}

int main() {
	int n, r;

	cin >> n;

	while (n != 0) {
		for (int i = 0; i < n; i++) cin >> c[i];
		for (int i = 0; i < n; i++) cin >> d[i];
		
		r = cruzados(c, d, 0, n - 1);

		cin >> n;
	}


	return 0;
}