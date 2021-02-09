#include "RegistroJugador.h"

void iniciar(tRegJugador & jugador, string id, int puntos) {
	jugador.id = id;
	jugador.puntos = puntos;
}

void mostrar(const tRegJugador & jugador) {
	int espacio = 13 - jugador.id.length();
	cout << jugador.id << setw(espacio) <<right << jugador.puntos << endl;
}
void actualizar(tRegJugador & jugador, int puntos) {
	jugador.puntos += puntos;
}
bool operator<(const tRegJugador & regIzq, const tRegJugador & regDer) {
	bool ok = false;
	if (regIzq.id < regDer.id)
		ok = true;
	return ok;
}
bool menorXRanking(const tRegJugador & jug1, const  tRegJugador	& jug2) {
	bool ok = false;
	if ((jug1.puntos < jug2.puntos) || (jug1.puntos == jug2.puntos && jug2 < jug1))
		ok = true;
	return ok;
}

bool cargar(tRegJugador & jugador, ifstream & archivo) {
	bool ok = false;
	string idaux = "";
	int puntosaux = 0;
	archivo >> idaux >> puntosaux;
	if (!archivo.fail()) {
		jugador.id = idaux;
		jugador.puntos = puntosaux;
		ok = true;
	}
	return ok;
}