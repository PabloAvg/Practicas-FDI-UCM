#include <iostream>
#include <cmath>

using namespace std;

// Complejidad: a = 1, b = 2, k = 0; O(log(n))
void tortuga(int cx, int cy, int px, int py, int mitadLado, /*out*/ int& numCuadrados, /*out*/ int& longLados) {
	
	if (mitadLado == 0) return;

	if (cx + mitadLado >= px && cx - mitadLado <= px && cy + mitadLado >= py && cy - mitadLado <= py) {
		numCuadrados = 1;
		longLados = (mitadLado * 2) * 4;
	}
	
	int numC = 0, longL = 0;

	if (px > cx && py > cy) { // Arriba - derecha
		tortuga(cx + mitadLado, cy + mitadLado, px, py, mitadLado / 2, numC, longL);
	}
	else if (px > cx && py < cy) { // Abajo - Derecha
		tortuga(cx + mitadLado, cy - mitadLado, px, py, mitadLado / 2, numC, longL);
	}
	else if (px < cx&& py < cy) { // Abajo - Izquierda
		tortuga(cx - mitadLado, cy - mitadLado, px, py, mitadLado / 2, numC, longL);
	}
	else if (px < cx && py > cy) { // Arriba - Izquierda
		tortuga(cx - mitadLado, cy + mitadLado, px, py, mitadLado / 2, numC, longL);
	}

	if (numC != 0) {
		numCuadrados += numC;
		longLados += longL;
	}

}

int main() {
	int cx, cy, px, py, mitadLado, numCuadrados = 0, longLados = 0;

	cin >> cx >> cy >> px >> py >> mitadLado;

	while (cin) {
		tortuga(cx, cy, px, py, mitadLado, numCuadrados, longLados);
		cout << numCuadrados << ' ' << longLados << '\n';
		cin >> cx >> cy >> px >> py >> mitadLado;
		numCuadrados = 0, longLados = 0;
	}

	return 0;
}