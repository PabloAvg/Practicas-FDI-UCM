#pragma once
#include "Conjunto_1_9.h"
#include "UtilidadesWin.h"
#include <iostream>

typedef enum tEstadoCasilla {vacia, fija, rellena};
typedef tColor tArrayColores[3];
typedef struct {
	tEstadoCasilla estado;
	int numero;
	tConjunto_1_9 candidatos;
}tCasillaSudoku;
const int MAX_CAS = 9;

void iniciar(tCasillaSudoku & casilla, int numero, tEstadoCasilla estado);
bool actualizar(tCasillaSudoku & casilla, int numero, tEstadoCasilla estado);
bool actualizarSimple(tCasillaSudoku & casilla);
void mostrar(const tCasillaSudoku & casilla);
