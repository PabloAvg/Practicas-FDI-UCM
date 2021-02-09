#include <iostream>
using namespace std;

int v[300000];

int esconder(int v[], int n, int k) {
	int ret = 0, sumaMaxima = 0, sumaActual = 0, i = k;
	while (i < n) {
		sumaActual += v[i];
		sumaActual -= v[i - k];
		if (sumaActual > sumaMaxima) {
			ret = i - k + 1;
			sumaMaxima = sumaActual;
		}
		i++;
	}
	return ret;
}

int main() {
	int n, k, c;

	cin >> c;

	for (int i = 0; i < c; i++) {
		cin >> n >> k;
		for (int j = 0; j < n; j++) cin >> v[j];
		cout << esconder(v, n, k) << '\n';
	}

	return 0;
}