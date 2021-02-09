#pragma once

const int MAX_CONJ = 9;

typedef struct {
	bool valor[MAX_CONJ];
	int cardinal=0;
}tConjunto_1_9;

void iniVacio(tConjunto_1_9 & c);
void iniLLeno(tConjunto_1_9 & c);
bool pertenece(const tConjunto_1_9 & c, int e);
void incluir(tConjunto_1_9 & c, int e);
void quitar(tConjunto_1_9 & c, int e);
int numElems(const tConjunto_1_9 & c);
void mostrar(const tConjunto_1_9 & c);
