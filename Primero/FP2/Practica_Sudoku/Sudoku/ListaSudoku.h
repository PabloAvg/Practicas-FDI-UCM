#pragma once
#include "RegistroSudoku.h"

int const MAX_SDK = 9;

typedef struct {
	tRegSudoku registros[MAX_SDK];
	int cont;
}tListaSudokus;

void iniciar(tListaSudokus & lista);
bool cargar(tListaSudokus & lista);
void mostrar(const tListaSudokus & lista);
bool guardar(const tListaSudokus & lista);
bool buscar(const tListaSudokus & lista, string nombArch);
bool insertar(tListaSudokus & lista, const tRegSudoku & sudoku);
