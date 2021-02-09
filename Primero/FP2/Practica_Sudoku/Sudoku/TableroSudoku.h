#pragma once
#include "CasillaSudoku.h"
#include <string>
#include <fstream>
typedef struct {
	int x, y;
}tCoor;
typedef struct {
	tCasillaSudoku matriz[MAX_CAS][MAX_CAS];
	int numVac = 0;
}tTableroSudoku;
const string barraH = "-------------------------------";
const char barraV = '|';
using namespace std;

bool cargar(tTableroSudoku & tablero, string nombreFichero);
void mostrar(const tTableroSudoku & tablero);
void mostrarCandidatos(const tTableroSudoku & tablero, tCoor cor);
int charToInt(char celda);
void actualizarCandidatosFila(int fila, int num, tTableroSudoku & tablero);
void actualizarCandidatosColumna(int col, int num, tTableroSudoku & tablero);
void actualizarCandidatosParcela(tCoor pos, int num, tTableroSudoku & tablero);
tCoor calcularParcela(int x, int y, tTableroSudoku tablero);
void reiniciarTablero(tTableroSudoku & tablero);
void actualizarCandidatosTablero(tTableroSudoku & tablero);
bool actualizar(tTableroSudoku & tablero, tCoor cor, int num);
void completarSimples(tTableroSudoku & tablero);
bool borrar(tTableroSudoku & tablero, tCoor cor);
bool comprobarVictoria(tTableroSudoku & tablero, int puntos);