#pragma once
#include<string>
#include <fstream>
#include<iostream>
#include<iomanip>

using namespace std;

typedef struct {
	int puntos;
	string archivo;
}tRegSudoku;

bool cargar(tRegSudoku &sudoku, istream& infile);
void mostrar(const tRegSudoku &sudoku);
void iniciar(tRegSudoku & registro, string file, int puntos);
bool guardar(const tRegSudoku & registro, ostream & outfile);
bool operator<(const tRegSudoku & regIzq, const tRegSudoku & regDer);