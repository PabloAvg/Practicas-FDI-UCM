//Cristian Emanuel Anei, Pablo Alvarez Garcia

#include "cola.h"
#include <iostream>
using namespace std;


// A IMPLEMENTAR
void rellena(Cola<int>& cola, int n) {
	for (int i = 1; i < n + 1; i++) {
		cola.pon(i);
	}

}

void supervivientes(int n, Cola<int>& cola) {
	int m = 2;
	int dist = n;
	rellena(cola, n);
	while (m <= cola.longitud()) {
		int i = 1;
		while (i <= dist) {
			if (i % m == 1) {
				cola.quita();
			}
			else {
				cola.pon(cola.primero());
				cola.quita();
			}

			i++;
		}
		dist = cola.longitud();
		m++;
	}
}

int main() {
	
	int n;
	Cola<int> cola;

	cin >> n;
	while (n != 0){

		supervivientes(n, cola);

		cout << n << ":";
		while (!cola.esVacia()) {
			cout << " " << cola.primero();
			cola.quita();
		}
		cout << endl;
		
		cin >> n;	
	}
		
	return 0;
}
