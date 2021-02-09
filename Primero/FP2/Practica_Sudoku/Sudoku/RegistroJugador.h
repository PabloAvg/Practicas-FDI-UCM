#pragma once
#include <iostream>
#include <string>
#include<fstream>
#include<iomanip>
using namespace std;

typedef struct {
	string id;
	int puntos;
}tRegJugador;

void iniciar(tRegJugador & jugador, string id, int puntos);
void mostrar(const tRegJugador & jugador);
void actualizar(tRegJugador & jugador, int puntos);
bool operator<(const tRegJugador & regIzq, const tRegJugador & regDer);
bool menorXRanking(const tRegJugador & jug1, const  tRegJugador	& jug2);
bool cargar(tRegJugador & jugador , ifstream & archivo);