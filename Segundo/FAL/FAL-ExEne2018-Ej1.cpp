#include <iostream>

using namespace std;

int v[1000];

// { Pre: 0 <= n <= longitud(v) }
int pistaLarga(int n, int v[]) /* Return ret */ { // Complejidad: O(n) (Lineal respecto a las alturas que haya)
	if (n == 0) return 0; // Caso Base

	int a = 0, b = 1, ret = 1, aux;

	while (a < n - 1 && b < n) { // { I: ( 0 <= a < b < n ) ^ ( 1 <= aux <= ret < n ) }
		if (v[b - 1] >= v[b]) {
			b++;
		}
		else {
			aux = b - a;
			a = b;
			b++;
			if (aux > ret) ret = aux;
		}
	}
	aux = b - a;
	if (aux > ret) ret = aux;

	return ret;
} // { Cota: n - b }
// { Post: ret = max(j - i) : 0 <= i < j < n : esLargo(i, j, v, n) 
// esLargo(i, j, v, n) = ( P.t. i, j : i < j < n : v[i] > v[j - 1] > v[j]) }

int main() {
	int casos, n;

	cin >> casos;

	while (casos != 0) {
		cin >> n;
		for (int i = 0; i < n; i++) cin >> v[i];
		cout << pistaLarga(n, v) << "\n\n";
		casos--;
	}

	return 0;
}