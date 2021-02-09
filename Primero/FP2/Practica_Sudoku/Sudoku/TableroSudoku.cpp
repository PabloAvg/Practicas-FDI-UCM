#include"TableroSudoku.h"
using namespace std;

bool cargar(tTableroSudoku & tablero, string nombreFichero) {
	ifstream file(nombreFichero);
	tCoor casilla;
	bool ok = false;
	char celda;
	int numcelda;
	if (file.is_open()) {
		for (int i = 0; i < MAX_CAS; i++) {
			for (int j = 0; j < MAX_CAS; j++) {
				file.get(celda);
				numcelda=charToInt(celda);
				if (numcelda == 0) {
					iniciar(tablero.matriz[i][j], numcelda, vacia);
					tablero.numVac++;
				}
				else
					iniciar(tablero.matriz[i][j], numcelda, fija);
			}
			file.ignore();
		}
		actualizarCandidatosTablero(tablero);
		ok = true;
	}
	return ok;
}

void mostrar(const tTableroSudoku & tablero) {
	for (int i = 0; i < MAX_CAS; i++) {
		if (i % 3 == 0)
			cout << barraH << endl;
		for (int j = 0; j < MAX_CAS; j++) {
			if (j % 3 == 0)
				cout << barraV;
			mostrar(tablero.matriz[i][j]);
		}
		cout << barraV;
		cout<< endl;
	}
	cout << barraH << endl;
}

void mostrarCandidatos(const tTableroSudoku & tablero, tCoor cor) {
	if (tablero.matriz[cor.x][cor.y].estado == vacia) {
		mostrar(tablero.matriz[cor.x][cor.y].candidatos);
	}
	else
		cout << "\nLa casilla consultada tiene que ser vacia\n"<<endl;
}

int charToInt(char celda) {
	int numcelda;
	switch (celda) {
	case '1': numcelda = 1;
		break;
	case '2': numcelda = 2;
		break;
	case '3': numcelda = 3;
		break;
	case '4': numcelda = 4;
		break;
	case '5': numcelda = 5;
		break;
	case '6': numcelda = 6;
		break;
	case '7': numcelda = 7;
		break;
	case '8': numcelda = 8;
		break;
	case '9': numcelda = 9;
		break;
	default: numcelda = 0;
	}
	return numcelda;
}

void actualizarCandidatosTablero(tTableroSudoku & tablero) {
	tCoor posEsqIzq;
	for (int i = 0; i < MAX_CAS; i++) {
		for (int j = 0; j < MAX_CAS; j++) {
			if (tablero.matriz[i][j].estado == fija) {
				actualizarCandidatosFila(i, tablero.matriz[i][j].numero, tablero);
				actualizarCandidatosColumna(j, tablero.matriz[i][j].numero, tablero);
				posEsqIzq = calcularParcela(i, j, tablero);
				actualizarCandidatosParcela(posEsqIzq, tablero.matriz[i][j].numero, tablero);
			}
		}
	}
}

bool actualizar(tTableroSudoku & tablero, tCoor cor, int num) {
	bool ok = true;
	int ColMax, FilMax;
	tCoor posEsqIzq;
	if (actualizar(tablero.matriz[cor.x][cor.y], num, rellena)) {

		for (int i = 0; i < MAX_CAS; i++) {
			quitar(tablero.matriz[cor.x][i].candidatos, num);
			quitar(tablero.matriz[i][cor.y].candidatos, num);
		}

		posEsqIzq = calcularParcela(cor.x, cor.y, tablero);
		actualizarCandidatosParcela(posEsqIzq,num,tablero);
		tablero.numVac--;
	}
	else
		ok = false;
	return ok;
}

bool borrar(tTableroSudoku & tablero, tCoor cor) {
	bool ok = false; 
	tCoor posEsqIzq;
	int FilMax;
	int ColMax;

	if (tablero.matriz[cor.x][cor.y].estado == rellena) {

		tablero.matriz[cor.x][cor.y].estado = vacia;

		for (int i = 0; i < MAX_CAS; i++) {
			incluir(tablero.matriz[cor.x][i].candidatos, tablero.matriz[cor.x][cor.y].numero);
			incluir(tablero.matriz[i][cor.y].candidatos, tablero.matriz[cor.x][cor.y].numero);
		}

		posEsqIzq = calcularParcela(cor.x, cor.y, tablero);
		FilMax = posEsqIzq.x + 3; 
		ColMax = posEsqIzq.y + 3;

		for(int i=posEsqIzq.x; i < FilMax; i++){
			for (int j = posEsqIzq.y; j < ColMax; j++) {
				incluir(tablero.matriz[i][j].candidatos, tablero.matriz[cor.x][cor.y].numero);
			}
		}

		tablero.matriz[cor.x][cor.y].numero = 0;
		tablero.numVac++;
		actualizarCandidatosTablero(tablero);
		ok = true;
	}

	return ok;
}

void completarSimples(tTableroSudoku & tablero) {
	tCoor posSimple, posEsqIzq;
	for (int i = 0; i < MAX_CAS; i++){
		for (int j = 0; j < MAX_CAS; j++) {
			if (actualizarSimple(tablero.matriz[i][j])) {
				actualizarCandidatosFila(i, tablero.matriz[i][j].numero, tablero);
				actualizarCandidatosColumna(j, tablero.matriz[i][j].numero,tablero);
				posEsqIzq = calcularParcela(i, j, tablero);
				actualizarCandidatosParcela(posEsqIzq, tablero.matriz[i][j].numero, tablero);
				tablero.numVac--;
			}

		}
	}
}

void reiniciarTablero(tTableroSudoku & tablero) {
	for (int i = 0; i < MAX_CAS; i++) {
		for (int j = 0; j < MAX_CAS; j++) {
			if (tablero.matriz[i][j].estado == rellena || tablero.matriz[i][j].estado == vacia) {

				tablero.matriz[i][j].candidatos.cardinal = 0;
				iniciar(tablero.matriz[i][j], 0, vacia);

				if(tablero.matriz[i][j].estado == rellena)
					tablero.numVac++;
			}
		}
	}
	actualizarCandidatosTablero(tablero);
}

void actualizarCandidatosFila(int fila, int num,  tTableroSudoku & tablero) {
	for (int j = 0; j < MAX_CAS; j++) {
		if(tablero.matriz[fila][j].estado != fija)
			quitar(tablero.matriz[fila][j].candidatos, num);
	}
}

void actualizarCandidatosColumna(int col, int num, tTableroSudoku & tablero) {
	
	for (int i = 0; i < MAX_CAS; i++) {
		if (tablero.matriz[i][col].estado != fija)
			quitar(tablero.matriz[i][col].candidatos, num);
	}
}

void actualizarCandidatosParcela(tCoor pos, int num, tTableroSudoku & tablero) {
	int FilMax = pos.x + 3;
	int ColMax = pos.y + 3;
	for (int i = pos.x; i < FilMax; i++) {
		for (int j = pos.y; j < ColMax; j++) {
			if (tablero.matriz[i][j].estado != fija)
				quitar(tablero.matriz[i][j].candidatos,num);
		}
	}
}

tCoor calcularParcela(int x, int y, tTableroSudoku tablero) {
	tCoor posEsquinaIzq;
	posEsquinaIzq.x = (x / 3) * 3;
	posEsquinaIzq.y = (y / 3) * 3;
	return posEsquinaIzq;
}

bool comprobarVictoria(tTableroSudoku & tablero, int puntos) {
	bool ok = false;
	if (tablero.numVac == 0) {
		ok = true;
		cout <<endl << "\nBuen trabajo!" << "Has ganado " << puntos << " punto/s!\n" << endl;
		system("pause");
	}
	return ok;
}