#include "ListaSudoku.h"

using namespace std;

void iniciar(tListaSudokus & lista) {
	lista.cont = 0;
	for(int i = 0; i < MAX_SDK; i++){
		lista.registros[i].puntos = 0;
		lista.registros[i].archivo = "";
	}
}

bool cargar(tListaSudokus & lista) {
	bool ok=true;
	ifstream aregistro("registroSudokus.txt");
	if (aregistro.is_open()) {
		while (!aregistro.eof()) {
			if (cargar(lista.registros[lista.cont], aregistro))
				lista.cont++;
			else if(lista.registros[lista.cont].archivo !="")
				ok = false;
				
		}
	}
	return ok;
}
void mostrar(const tListaSudokus &lista) {
	cout << "LISTA DE SUDOKUS REGISTRADOS:\n\tFICHERO DE CARGA\tNIVEL\n";
	for (int i = 0; i < lista.cont; i++) {
		cout << i + 1 << ".\t";
		mostrar(lista.registros[i]);
	}
}

bool guardar(const tListaSudokus & lista) {
	bool ok = true;
	ofstream outfile("registroSudokus.txt");
	if (outfile.is_open()) {
		for (int i = 0; i < lista.cont && ok == true; i++) {
			ok = guardar(lista.registros[i], outfile);
		}
	}
	return ok;
}

bool buscar(const tListaSudokus & lista, string nombArch) {
	bool enc = false;
	int i = 0;
	while (!enc && i < lista.cont) {
		if (lista.registros[i].archivo == nombArch) {
			enc = true;
		}
		i++;
	}
	return enc;
}

bool insertar(tListaSudokus & lista, const tRegSudoku & sudoku) {
	bool ok = false, enc = false;
	int pos = lista.cont;
	if (!buscar(lista, sudoku.archivo ) && lista.cont < MAX_SDK) {//y no está llena!!!!!!!!!!!!!!!!!!!!
		while (pos > 0 && sudoku < lista.registros[pos - 1]) {
			lista.registros[pos] = lista.registros[pos - 1];
			--pos;
		}
		lista.registros[pos] = sudoku;
		++lista.cont;
		ok = true;
	}
	else
		cout << "Nombre repetido o lista llena"<< endl;
	return ok;
}