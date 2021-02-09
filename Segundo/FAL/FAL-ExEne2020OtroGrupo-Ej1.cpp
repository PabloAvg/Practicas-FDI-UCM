#include<iostream>

using namespace std;

int v[1000];

void escAscendente(int& ini, int fin, int v[], int& ancho, int& iz) {
	int aux = 1;
	iz++;
	while (ini < fin && v[ini] <= v[ini + 1]) {
		if (v[ini] == v[ini + 1]) aux++;
		else {
			if (aux > ancho) ancho = aux;
			aux = 1;
		}

		iz++;
		ini++;
	}

	if (aux > ancho) ancho = aux;
}

void escDescendente(int ini, int& fin, int v[], int& ancho, int& der) {
	int aux = 1;
	der++;
	while (fin > ini && v[fin] <= v[fin - 1]) {
		if (v[fin] == v[fin - 1]) aux++;
		else {
			if (aux > ancho) ancho = aux;
			aux = 1;
		}
		der++;
		fin--;
	}

	if (aux > ancho) ancho = aux;
}


bool escalera(int fin, int v[], int& ancho, int& iz, int& der) {
	if (fin == -1) return false; // Si no hay nada, pues no hay escalera
	if (fin == 0) { ancho = 1; return true; } // Casos Base, si solo hay un número hay escalera de 1
	
	int anIz = 0, anDer = 0, m = 0; // m es donde me voy quedando

	escAscendente(m, fin, v, anIz, iz); // le paso m por parámetro para sabeer donde se queda
	if (m == fin) { ancho = anIz; return true; } // si ha acabado con todo el vector, me salgo con true
	escDescendente(m + 1, fin, v, anDer, der); // paso el fin sin parámetro
	if(m + 1 != fin) return false; // si no ha acabado es que no es descendiente, me salgo con false

	if (anIz >= anDer) ancho = anIz; // ancho = ancho de la izquierda si es mayor o igual que el de la dcha
	else ancho = anDer; // si no, ancho = al de la dcha

	return true; // Retorno true
}

void escalera(int fin, int v[]) {
	int ancho = 0, iz = 0, der = 0;

	if (escalera(fin, v, ancho, iz, der)) cout << "SI " << ancho << '\n';
	else cout << "NO " << iz << ' ' << der << '\n';
}


int main() {
	int n;

	cin >> n;

	while (n != -1) {
		for (int i = 0; i < n; i++) cin >> v[i];
		escalera(n - 1, v);
		cin >> n;
	}

	return 0;
}

/*
	** IN **

5
1 2 3 2 1

8
1 2 2 2 1 -2 -2 -3

4
1 2 1 2

7
-3 -3 0 -2 1 -4 -4

6
2 2 2 2 2 2

1
4

8
1 1 2 2 3 3 4 4

8
4 4 3 3 2 2 1 1

5
1 2 3 2 2

	** OUT **

	SI 1

	SI 3

	NO 2 1

	NO 3 3

	SI 6

	SI 1

	SI 2

	SI 2

	SI 2

*/