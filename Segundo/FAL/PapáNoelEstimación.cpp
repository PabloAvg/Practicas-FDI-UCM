#include <iostream>

using namespace std;

const int MAXJ = 15;
const int MAXN = 10;
const int MAX = MAXJ * MAXN;

int v[MAX];
int j[MAXJ] = {0};

bool esValido(int j[], int i, int r) {
	return j[i % r] <= 1; // Si el juguete está o no ya cogido.
}

// Sin parámetro in/out
int satisfaccion(/* Tabla para cada ninio */int v[], /* Marcaje, por que ninio voy */ int k, /* Cuantos regalos hay */int r,
	/* Cuantos ninios hay */int n, /* Lista de juguetes, por si hay alguno asignado */ int j[], /* Suma de mis anteriores */ int suma) {
	int ret = INT_MIN, parc;

	for (int i = k * r /* Niño por el que voy * los regalos que hay */; i < (k * r) + r; i++) { // r = 3; k = 0; 0 hasta 3 (no incl.), r = 3; k = 1; 3 hasta 6 (no incl.)
		parc = v[i];
		j[i % r]++; // Pq el regalo 0 es el mismo para los 3 y no puedo darselo a varios niños
		if (esValido(j, i, r)) {
			suma += parc;
			if (k == n - 1) {
				if (suma > ret) ret = suma;
			}
			else {
				int aux = satisfaccion(v, k + 1, r, n, j, suma);
				if (aux > ret) ret = aux;
			}
			suma -= parc;
		}
		j[i % r]--;
	}
	return ret;
}

void resuelveCaso() {
	int r, n/*, suma = 0*/;

	cin >> r >> n;
	while (cin) {
		// for (int i = 0; i < r; i++) j[i] = 0; // Ya dejo todas a 0 en el algoritmo
		for (int i = 0; i < r * n; i++) cin >> v[i];
		cout << satisfaccion(v, 0, r, n, j, 0) << "\n\n";
		//cout << suma << "\n\n";
		cin >> r >> n;
		//suma = 0;
	}
}

int main() {
	resuelveCaso();
	return 0;
}


/*

** IN ** 

4 3
8 9 3 1
6 4 5 3
2 2 9 9

4 2
10 12 12 15
9 10 20 7

2 2
15 2
17 7

** OUT **

24

35

22

*/



// Con parámetro in/out
//void satisfaccion(/* Tabla para cada ninio */int v[], /* Marcaje, por que ninio voy */ int k, /* Cuantos regalos hay */int r, 
//	/* Cuantos ninios hay */int n , /* Lista de juguetes, por si hay alguno asignado */ int j[], /* Suma de mis anteriores */ int& suma) {
//
//	// Caso base
//	if (n == 1) {
//		suma = v[0];
//		if (r != 1) {
//			for (int i = 1; i < r; i++) {
//				if (v[i] > suma) suma = v[i];
//			}
//		}
//		return;
//	}
//
//	// Caso recursivo
//	else {
//		int parc, sumP = suma, sumAux = suma;
//		for (int i = k * r; i < (k * r) + r; i++) {
//			parc = v[i];
//			j[i % r]++;
//			if (esValido(j, i)) {
//				sumP += parc;
//				if (k == n - 1) {
//					if (sumP > suma) suma = sumP;
//				}
//				else {
//					satisfaccion(v, k + 1, r, n, j, sumP);
//					if (sumP > suma) suma = sumP;
//				}
//				sumP = sumAux;
//			}
//			j[i % r]--;
//		}	
//	}
//}