#include <iostream>
#include"ListaSudoku.h"
#include"ListaJugadores.h"
#include"JuegoSudoku.h"
#include "checkML.h"
using namespace std;

int menuPrincipal();

int main(){
	_CrtSetDbgFlag ( _CRTDBG_ALLOC_MEM_DF | _CRTDBG_LEAK_CHECK_DF );
	tListaSudokus lista;
	tListaJugadores listaJ;
	iniciar(lista);
	iniciar(listaJ);
	cargar(listaJ);
	ordenarAlf(listaJ);
	int eleccion1 = -1, eleccion2 = -1, puntos;
	bool salir = false;
	tRegJugador Jugador;
	if (cargar(lista)) {
		while (!salir) {
			eleccion1=menuPrincipal();
			switch (eleccion1) {
			case 1:
				mostrar(lista);
				while (eleccion2<1 || eleccion2>lista.cont) {
					cout << "\n" << "Introduce el numero del sudoku al que deseas jugar: ";
					cin >> eleccion2;
				}
				cout << "\n" << "Introduce un nombre: ";
				cin >> Jugador.id;
				Jugador.puntos = jugar(lista.registros[eleccion2 - 1]);
				actualizar(listaJ, Jugador);
				guardar(listaJ);
				break;
			case 2:
				cout << "Jugadores ordenados por identificador:\n";
				mostrar(listaJ);
				guardar(listaJ);
				break;
			case 3:
				cout << "Jugadores ordenados por puntos:\n";
				mostrarXRanking(listaJ);
				guardar(listaJ);
				break;
			case 4: {
				int puntos;
				string arch;
				mostrar(lista);
				cout << "Incorporar Sudoku:\n";
				cout << "Nombre: ";
				cin >> arch;
				cout << "\nPuntos: ";
				cin >> puntos;
				if (puntos > 0) {
					tRegSudoku regNuevo;
					iniciar(regNuevo, arch, puntos);
					if (insertar(lista, regNuevo))
						guardar(lista);
				}
				else
					cout << "Puntos inválidos" << endl;
				break;
			}
			case 0: salir = true;
				liberar(listaJ);
				break;
			}
			eleccion2 = -1;
		}
	}
	else
		cout << "Problema en la carga de sudokus...\n";
	system("pause");
	return 0;
}

int menuPrincipal() {
	int eleccion = -1;
	cout << "\tMENU PRINCIPAL\n\n";
	cout << "1 - Jugar\n";
	cout << "2 - Ver jugadores ordenados por identificador\n";
	cout << "3 - Ver jugadores ordenados por puntos\n";
	cout << "4 - Incorporar Sudoku\n";
	cout << "0 - Salir.\n";
	while (eleccion != 0 && eleccion != 1 && eleccion != 2 && eleccion != 3 && eleccion != 4)  {
		cout << "Eleccion:";
		cin >> eleccion;
	}
	return eleccion;
}