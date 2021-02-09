#include"ListaJugadores.h"

void iniciar(tListaJugadores & lista) {
	lista.regjug = new tRegJugador * [MAX_JUG];
	lista.cont = 0;
	lista.capacidad = MAX_JUG;
}

bool cargar(tListaJugadores & lista) {
	ifstream archivo("registroJugadores.txt");
	bool ok = true;
	if (archivo.is_open()) {
		while (!archivo.eof()) {
			lista.regjug[lista.cont] = new tRegJugador;
			if (cargar(*lista.regjug[lista.cont], archivo))
				lista.cont++;
			else if (lista.regjug[lista.cont]->id != "")
				ok = false;
		}
	}
	else
		cout << "no ok" << endl;
		ok = false;
	return ok;
}

void mostrar(const tListaJugadores & lista) {
	cout << "--------------------------------" << endl;
	cout << "\tLISTA DE JUGADORES:" << endl;
	cout << "Nombre:         Puntos:"<< endl;

	for (int i = 0; i < lista.cont; i++) {
		cout << i << " - ";
		mostrar(*lista.regjug[i]);
	}
	cout << "--------------------------------" << endl;
	
}

bool guardar(const tListaJugadores & lista) {
	bool ok = true;
	ofstream archivo("registroJugadoresJJ.txt");
	if (archivo.is_open()) {
		for (int i = 0; i < lista.cont && ok == true; i++) {
			archivo <<lista.regjug[i]->id << " " << lista.regjug[i]->puntos << endl;
			if (archivo.fail())
				ok = false;
		}
	}
	else
		ok = false;

	return ok;
}

bool buscar(const tListaJugadores & lista, string id, int &pos, int ini, int fin) {//busqueda binaria [OK]
	bool encontrado = false;
	if (ini <= fin) {
		pos = (ini + fin) / 2;
		if (id < lista.regjug[pos]->id) return buscar(lista, id, pos, ini, pos - 1);
		else if (lista.regjug[pos]->id < id) return buscar(lista, id, pos, pos + 1, fin);
		else return true;
	}
	 else { pos = ini; return false; }
}

bool actualizar(tListaJugadores & lista, const tRegJugador & jugador) {
	bool ok = false;
	int pos = 0;
	if (buscar(lista, jugador.id, pos, 0, lista.cont-1)) {
		actualizar(*lista.regjug[pos], jugador.puntos);
		ok = true;
	}
	else {
		int posaux = lista.cont;
		while (posaux > pos )
		{
			lista.regjug[posaux] = lista.regjug[posaux - 1];
			--posaux;
		}
		lista.regjug[posaux + 1]->id = jugador.id;
		lista.regjug[posaux + 1]->puntos= jugador.puntos;
		++lista.cont;
		ok = true;
	}
	return ok;
}

void mostrarXRanking(const tListaJugadores & lista) {
	int i = 0, pos;
	bool enc;
	tListaJugadores laux;

	laux.regjug = new tRegJugador *[lista.capacidad];
	laux.cont = lista.cont;
	laux.capacidad = lista.capacidad;

	//Pedir a Jin
	/*for (int i = 0; i < lista.cont - 1; i++) {
		if (menorXRanking(*lista.regjug[i], *lista.regjug[i + 1])) {
			laux.regjug[0] = lista.regjug[i + 1];
			lista.regjug[i] =
			i = -1;
		}
	}*/
	i = 0;
	while (i < laux.cont) {
			cout << laux.regjug[i]->id<< " " << laux.regjug[i]->puntos << endl;
		i++;
	}

	liberar(laux);
}/*Recorres la lista con for anidado y usando menorXRanking vas guardando el maximo (max_aux, elem[i]),
cada bucle interno tienes que buscar el maximo pero ignorando los maximos que vas poninedo por consola*/

void ordenarAlf(tListaJugadores & lista) {
		int i, pos; 
		tRegJugador elemento; 
		bool enc;

		for (i = 1; i < lista.cont; i++) {
			elemento = *lista.regjug[i];
			pos = 0; enc = false;
			//búsqueda con esquema asimétrico
			while ((pos < i) && !enc) {
				if ( elemento < *lista.regjug[pos]) enc = true;
				else pos++;
			}
			if (enc) { // pos ‐‐> primer elemento mayor
				for (int j = i; j > pos; j--)
					lista.regjug[j] = lista.regjug[j-1];
				lista.regjug[pos] = &elemento;
			}
		}
}

void ampliar(tListaJugadores & lista, int num){
	tRegJugador** aux = nullptr;
	aux = new tRegJugador*[2 * num];
	for (int i = 0; i < lista.cont; i++)
	{
		aux[i] = lista.regjug[i];
	}
	delete[] lista.regjug; //OJO! Memoria -> [] para listaes
	lista.regjug = aux;
	lista.capacidad += num;
	aux = nullptr;
}

void liberar(tListaJugadores & lista) {
	delete[] lista.regjug;
	lista.regjug = nullptr;
	lista.cont = 0;
}