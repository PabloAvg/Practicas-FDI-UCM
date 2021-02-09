#pragma once
#include "RegistroSudoku.h"
#include "TableroSudoku.h"
typedef struct {
	tTableroSudoku tablero;
	tRegSudoku sudoku;
}tJuegoSudoku;

void mostrar(const tJuegoSudoku & juego);
bool cargar(tJuegoSudoku & juego, const tRegSudoku & sudoku);
int jugar(const tRegSudoku & sudoku);
int menuJugar();
void IntroducirCasilla(tCoor& posCas);
