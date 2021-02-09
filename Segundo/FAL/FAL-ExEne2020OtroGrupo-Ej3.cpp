#include <iostream>

using namespace std;

const int MAX_E = 10;
const int MAX_C = 5;

typedef struct {
	int cantidad;
	int precio;
}tPintura;

int anchos[MAX_E];
int sol[MAX_E];
int solp[MAX_E];

bool esPrometedora(int act, int ant, int k, int anchos[], tPintura p[]) {
	if (act == ant) return false;
	if (p[act].cantidad < anchos[k]) return false;
	return true;
}

void pintaBack(int k/* Por donde voy */, int sol[] /* Solución óptima */, int solp[] /* Solución parcial */, 
	int anchos[] /* Ancho de cada escalón */, tPintura p[] /* Cantidad y precio de cada color */, int e /* Escalones totales */, 
	int c /* Colores distintos que tengo */, int ant /* Pintura escogida por mi anterior */, int precp /* precio que llevo */, int& max /* Precio max */) {
	
	if (e == 0 || c == 0) return; // Caso Base

	for (int i = 0; i < c; i++) {
		solp[k] = i;

		if (esPrometedora(i, ant, k, anchos, p)) {
			p[i].cantidad -= anchos[k];
			precp += (anchos[k] * p[i].precio);
			
			if (k == e - 1) {
				if (precp < max) {
					max = precp;
					for (int i = 0; i < e; i++)  sol[i] = solp[i];
				}
			}
			else {
				pintaBack(k + 1, sol, solp, anchos, p, e, c, i, precp, max);
			}

			p[i].cantidad += anchos[k];
			precp -= (anchos[k] * p[i].precio);
		}
	}
}

int main() {
	int e, c, max;
	tPintura p[MAX_C];

	cin >> e;

	while (e != -1) {
		max = INT_MAX;
		cin >> c;
		for (int i = 0; i < e; i++) cin >> anchos[i];
		for (int i = 0; i < c; i++) cin >> p[i].cantidad >> p[i].precio;

		pintaBack(0, sol, solp, anchos, p, e, c, -1, 0, max);

		if (max == INT_MAX) {
			cout << "NO\n";
		}
		else {
			cout << max << '\n';
			for (int i = 0; i < e; i++) cout << sol[i] << ' ';
			cout << '\n';
		}

		cin >> e;
	}

	return 0;
}

/*
	** IN **

3 2
2 2 2
3 1
4 2

5 2
1 1 1 1 1
3 3
3 1

5 3
1 3 1 2 1
6 10
2 3
6 6

2 1
1 1
3 2

	** OUT **

10
1 0 1

9
1 0 1 0 1

46
0 2 1 2 1

NO

*/