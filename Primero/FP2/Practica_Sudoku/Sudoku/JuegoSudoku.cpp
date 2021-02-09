#include"JuegoSudoku.h"
#include <string>
void mostrar(const tJuegoSudoku & juego) {
	mostrar(juego.sudoku);
	mostrar(juego.tablero);
}

bool cargar(tJuegoSudoku & juego, const tRegSudoku & sudoku) {
	bool ok = false;
	if (cargar(juego.tablero, sudoku.archivo)) {
		ok = true;
		juego.sudoku = sudoku;
	}
	return ok;
}

int jugar(const tRegSudoku & sudoku) {
	tJuegoSudoku juego;
	tCoor posCas;
	int puntos = 0, numero=0;
	bool salir = false, victoria=false;
	if (cargar(juego, sudoku)) {
		mostrar(juego.sudoku);
		mostrar(juego.tablero);
		while (!salir) {
			switch (menuJugar()) {
			case 1: cout << "\nPosicion [fila,columna]:";
				IntroducirCasilla(posCas);
				mostrarCandidatos(juego.tablero, posCas);
				mostrar(juego.tablero);
				break;
			case 2: cout << "\nPosicion [fila,columna]:";
				IntroducirCasilla(posCas);
				cout << "\nNumero [1,9]:";
				cin >> numero;
				if (actualizar(juego.tablero, posCas, numero)) {
					cout << endl;
					mostrar(juego.tablero);
					if (comprobarVictoria(juego.tablero, juego.sudoku.puntos)) {
						salir = true;
						victoria = true;
					}
				}
				else
					cout << "\nLa casilla tiene que ser vacia y el numero un candidato\n";
				break;
			case 3: cout << "\nPosicion [fila,columna]:";
				IntroducirCasilla(posCas);
				if (borrar(juego.tablero, posCas)) {
					cout << endl;
					mostrar(juego.tablero);
				}
				else
					cout << "\nLa casilla no puede ser fija o vacia\n"<<endl;
				break;
			case 4: reiniciarTablero(juego.tablero);
				cout << "\nTablero reiniciado\n";
				mostrar(juego.tablero);
				break;
			case 5: completarSimples(juego.tablero);
				cout << endl << endl << endl;
				mostrar(juego.tablero);
				if (comprobarVictoria(juego.tablero, juego.sudoku.puntos)) {
					salir = true;
					victoria = true;
				}
				break;
			case 0: salir = true;
				break;
			}
		}
		
		if (victoria)
			puntos = sudoku.puntos;
	}
	else
		cout << "Error al cargar...\n";
	return puntos;
}

int menuJugar() {
	int eleccion = -1;
	cout << "OPCIONES DE JUEGO\n";
	cout << "1 - Ver posibles valores de una casilla\n";
	cout << "2 - Colocar valor en una casilla\n";
	cout << "3 - Borrar valor de una casilla\n";
	cout << "4 - Reiniciar tablero\n";
	cout << "5 - Autocompletar celdas simples\n";
	cout << "0 - Salir\n";
	while (eleccion != 0 && eleccion != 1 && eleccion != 4 && eleccion != 2 && eleccion != 5 && eleccion != 3) {
		cout << "Eleccion:";
		cin >> eleccion;
	}
	return eleccion;
}
void IntroducirCasilla(tCoor& posCas) {
	cin >> posCas.x;
	cin >> posCas.y;
	while ((posCas.x < 1 || posCas.x > MAX_CAS) || (posCas.y < 1 || posCas.y > MAX_CAS)) {
		cout << "Valores posibles: [1,9]\nPosicion [fila,columna]:"<< '\n' << endl;;
		cin >> posCas.x;
		cin >> posCas.y;
	}
	posCas.x--;
	posCas.y--;
}
