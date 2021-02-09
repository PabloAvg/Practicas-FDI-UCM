#include <iostream>

using namespace std;

int v[100000]; // Necesitamos que sea un array pq si no en el último caso no llega a 20 cifras ni con long long

bool sumdivisibleparc(int num[], int dig) { // Como ya voy mirando los anteriores, con comprobar solo si lo soy ahora me vale
	int aux = 0;
	
	for (int i = 0; i < dig; i++) { // Sumo cada cifra
		aux += num[i];
	}
	
	aux %= dig; // Hallo el módulo

	return aux == 0; // Compruebo si la suma de los dígitos es divisible entre el número de dígitos
}

int backsumdivisible(int solp[] /* sol parcial */, int k /* por donde voy */, int dig /* cantidad de digitos */) {
	if (dig == 1) return 1; // Caso Base

	int ret = 0; // Mi variable de retorno

	for (int i = 0; i < 10; i++) { // Compruebo los dígitos posibles, del 0 al 9 incluidos
		solp[k] = i; // Le añado un dígito
		
		if (sumdivisibleparc(solp, k + 1)) { // Si es prometedora, en este caso, si de momento es sumdivisible
			if (k == dig - 1) { // si he alcanzado el tamaño
				ret++; // Aumento los números que hay sumdivisibles
			}
			else { // Si no lo he alcanzado
				ret += backsumdivisible(solp, k + 1, dig); // Le añado más cifras
			}
		}
	}

	return ret; // Retorno
}

int main() {
	int d; // Cifra por la que deben empezar
	int p; // Número de dígitos

	cin >> d;
	
	while (d != 0) {
		cin >> p;
		v[0] = d;
		cout << backsumdivisible(v, 1, p) << '\n';
		cin >> d;
	}

	return 0;
}