#include <iostream>
#include <cmath>

using namespace std;


// No encuentro ningún caso que dé mal pero el juez da wrong answer
int legionarios(int n) /* Return ret */ {
	if (n == 0) return 0; // Caso Base, si no hay soldados, devuelvo 0
	if (n == 1) { // Caso Base, un solo legionario tiene 5 escudos
		return 5;
	}

	int sol /* Soldados por lado en el cuad. que voy a usar ahora */ = sqrt(n), ret = 0;

	ret += legionarios(n - (sol * sol)); // Recursión con los que no uso

	if (sol == 1) return ret + 5; // Si es por ejemplo el 2, que son dos unos.

	ret += 12; // Sumo las 4 esquinas que tienen 3 escudos
	ret += ((sol - 2) * 4) * 2; // Sumo los de dos escudos
	ret += (sol - 2) * (sol - 2); // Sumo los de un escudo (Todos los de dentro)

	return ret;
}

int calculaEscudos(int n) {//recubo un numero elevado al cuadardo
	int total = 0;

	int raso, lat, esq;//raso, lateral, esquina

	if (n == 1) {//si la formacion es de un soldado, llevara 5 escudos
		total = 5;
	}
	else {
		//escuderos rasos, solo se cubren la cabeza
		raso = pow(sqrt(n) - 2, 2);
		total += raso;

		// los que estan en los laterales pero no en las esquinas, levan 2 escudos
		lat = ((n - raso) - 4);
		total += lat * 2;

		total += 12; //siempre son 4, pues hay 4 esquinas, llevan 3 escudos = 12
	}

	return total;
}

void soldados(int n) {
	int aux, total = 0;

	while (n != 0) {
		aux = sqrt(n);
		total += calculaEscudos(aux * aux);
		n -= pow(aux, 2);
	}
	if (total != 0) {
		cout << total << endl;
	}
}

int main() {
	int n;

	 do {
		cin >> n;
		cout << legionarios(n) << '\n';
	//	soldados(n);
	 } while (n != 0);

	return 0;
}