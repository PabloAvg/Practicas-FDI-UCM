#include <iostream>

using namespace std;

int v[1000];

int altoEscalera(int n, int a, int v[], int ini, int fin) {
	if (n == a) return v[0]; // Caso base

	if (fin == ini) return v[fin];

	int m = (fin + ini) / 2;

	if (v[m] < v[m + 1]) {
		return altoEscalera(n, a, v, m + 1, fin);
	}
	else if (v[m] < v[m - 1]) {
		return altoEscalera(n, a, v, ini, m);
	}
	else if (v[m] == v[m + 1] && v[m - 1] == v[m]) {
		int iz = altoEscalera(n, a, v, ini, m);
		int der = altoEscalera(n, a, v, m + 1, fin);

		if (iz >= der) return iz;
		else return der;
	}
	else if (v[m] == v[m + 1]) {
		return altoEscalera(n, a, v, m + 1, fin);
	}
	else if (v[m] == v[m - 1]) {
		return altoEscalera(n, a, v, ini, m);
	}
	else /* v[m] más grande que los dos */ {
		return v[m];
	}
}

int altoEscalera(int n, int a, int v[]) {
	return altoEscalera(n, a, v, 0, n - 1);
}


int main() {
	int n, a;

	cin >> n;

	while (n != -1) {
		cin >> a;
		for (int i = 0; i < n; i++) cin >> v[i];
		cout << altoEscalera(n, a, v) << '\n';
		cin >> n;
	}

	return 0;
}

/*
	** IN **

5 1
1 2 3 2 1

8 3
1 2 2 2 1 -2 -2 -3

6 6
2 2 2 2 2 2

1 1
4

8 2
1 1 2 2 3 3 4 4

8 2
4 4 3 3 2 2 1 1

15 6
1 1 1 1 1 1 5 4 3 2 2 2 2 2 2

15 6
2 2 2 2 2 2 3 4 5 1 1 1 1 1 1

	** OUT **

3

2

2

4

4

4

5

5

*/