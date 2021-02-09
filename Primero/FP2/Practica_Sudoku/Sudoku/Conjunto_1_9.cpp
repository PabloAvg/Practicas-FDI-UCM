#include <iostream>
#include<string>
#include <fstream>
#include "Conjunto_1_9.h"

using namespace std;

void iniVacio(tConjunto_1_9 & c) {
	c.cardinal = 0;
	for (int i = 0; i < MAX_CONJ; i++) {
		c.valor[i] = false;
	}
	c.cardinal = 0;
}

void iniLLeno(tConjunto_1_9 & c) {
	for (int i = 0; i < MAX_CONJ; i++) {
		c.valor[i] = true;
		c.cardinal++;
	}
}

bool pertenece(const tConjunto_1_9 & c, int e) {
	bool pertenece = false;
	if (c.valor[e-1])
		pertenece = true;
	return pertenece;
}

void incluir(tConjunto_1_9 & c, int e) {
	if (!pertenece(c, e)) {
		c.valor[e-1] = true;
		++c.cardinal;
	}
}

void quitar(tConjunto_1_9 & c, int e) {
	if (pertenece(c, e)) {
		c.valor[e-1] = false;
		--c.cardinal;
	}
}

int numElems(const tConjunto_1_9 & c) {
	return c.cardinal;
}

void mostrar(const tConjunto_1_9 & c) {
	cout << '\n';
	for (int i = 0; i < MAX_CONJ; i++) {
		if (c.valor[i] == true) {
			cout << "[" << i + 1 << "]";
		}
	}
	cout << '\n'<<endl;
}
