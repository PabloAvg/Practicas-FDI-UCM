#include "RegistroSudoku.h"

using namespace std;

bool cargar(tRegSudoku &sudoku, istream& infile) {
	bool ok = false;
	string nombreaux="";
	int puntosaux=0;
	infile >> nombreaux >> puntosaux;
	if (!infile.fail()) {
		sudoku.archivo = nombreaux;
		sudoku.puntos = puntosaux;
		ok = true;
	}
	return ok;
}

void mostrar(const tRegSudoku &sudoku) {
	int espacio = 25 - sudoku.archivo.length();
	cout << sudoku.archivo << setw(espacio) << right << sudoku.puntos <<endl;
}
void iniciar(tRegSudoku & registro, string file, int puntos) {
	registro.archivo = file;
	registro.puntos = puntos;
}
bool guardar(const tRegSudoku & registro, ostream & outfile) {
	bool ok = true;
	outfile << registro.archivo << " " << registro.puntos<<"\n";
	if (outfile.fail())
		ok = false;
	return ok;

}
bool operator<(const tRegSudoku & regIzq, const tRegSudoku & regDer) {
	bool ok=false;
	if (regIzq.puntos < regDer.puntos)
		ok = true;
	else if (regIzq.puntos == regDer.puntos && regIzq.archivo < regDer.archivo) {
		ok = true;
	}
	return ok;
}