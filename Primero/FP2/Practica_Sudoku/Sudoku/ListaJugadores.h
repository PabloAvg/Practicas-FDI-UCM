#pragma once
#include "RegistroJugador.h"
#include <fstream>

/*int const MAX_JGD = 100;*/ //Ya no se usa esta Constante

int const MAX_JUG = 3;

typedef struct {
	tRegJugador** regjug;
	int cont;
	int capacidad;
}tListaJugadores;

void iniciar(tListaJugadores & lista);
bool cargar(tListaJugadores & lista);
void mostrar(const tListaJugadores & lista);
bool guardar(const tListaJugadores & lista);
bool buscar(const tListaJugadores & lista, string id, int &pos, int ini, int fin);
bool actualizar(tListaJugadores & lista, const tRegJugador & jugador);
void mostrarXRanking(const tListaJugadores & lista);
void ordenarAlf(tListaJugadores & lista);
void ampliar(tListaJugadores & lista, int num);
void liberar(tListaJugadores & lista);