#include <iostream>
#include<string>
#include <fstream>
#include <Windows.h>
#include "CasillaSudoku.h"
#include"UtilidadesWin.h"
using namespace std;


void iniciar(tCasillaSudoku & casilla, int numero, tEstadoCasilla estado) {
	casilla.estado = estado;
	casilla.numero = numero;
	iniLLeno(casilla.candidatos);
}

bool actualizar(tCasillaSudoku & casilla, int numero, tEstadoCasilla estado) {
	bool ok = false;
	if (casilla.estado != fija && pertenece(casilla.candidatos, numero)) {
		casilla.estado = estado;
		casilla.numero = numero;
		quitar(casilla.candidatos, numero);
		ok = true;
	}
	return ok;
}

bool actualizarSimple(tCasillaSudoku & casilla) {
	bool  encontrado = false; //ok = false;
	int i = 0;
	if (casilla.candidatos.cardinal == 1 && casilla.estado==vacia) {
		//ok = true;
		casilla.estado = rellena;
		while (!encontrado && i < MAX_CAS) {
			if (actualizar(casilla, i + 1,rellena))
				encontrado = true;
			i++;
		}
	}
	return encontrado;
}

void mostrar(const tCasillaSudoku & casilla) {
	tArrayColores arraycolores = { 0,1,4 };
	cout << ' ';
	if (casilla.estado == vacia) {
		colorFondo(arraycolores[vacia]);
		cout << ' ';
	}
	else if (casilla.estado == fija) {

		colorFondo((arraycolores[fija]));
		cout << casilla.numero;
		colorFondo((arraycolores[vacia]));
	}
	else if (casilla.estado == rellena) {
		colorFondo((arraycolores[rellena]));
		cout << casilla.numero;
		colorFondo((arraycolores[vacia]));
	}
	cout << ' ';
}


