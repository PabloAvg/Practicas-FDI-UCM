#include <iostream>

using namespace std;

int v[100000];

int cuantasParejas(int v[], int n, int ancho) {
	int a = 0, b = 1, ret = 0;

	while (a < n - 1) {
		if (v[a] + v[b] == ancho) {
			ret++;
			v[a] = 0;
			v[b] = 0;
			swap(v[b], v[a + 1]);
			a += 2;
			b = a + 1;
		}
	/*	else if (v[a] > v[b]) {
			swap(v[a], v[b]);
			b = a + 1;
		}*/
		else {
			if (b != n - 1) b++;
			else { a++; b = a + 1; }
		}
	}
	return ret;
}

int main() {
	int n, d;

	cin >> n >> d;

	while (n != 0 || d != 0) {
		for (int i = 0; i < n; i++) cin >> v[i];
		cout << cuantasParejas(v, n, d) << '\n';
		cin >> n >> d;
	}

	return 0;
}