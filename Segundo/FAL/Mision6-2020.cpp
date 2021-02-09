#include <iostream>
#include <string>

using namespace std;

string v[10000]; // Strings de hasta 7 caracteres

/*
** IN **
4
D-123 A-123 B-123 C-123
4
B-345 A-123 A-123 C-123
3
DCDC DCDC DCDC
7
a a a b b c d
10
a a a a a a b b b b

** OUT **
1
2
3
3
6
*/

void numCopias(string v[], int a, int b, /*out*/ int& libro, /*out*/ int& primerLibro, /*out*/ int& ultimoLibro) {
	if (a >= b) { // Si lo he partido hasta que solo queda un libro
		libro = 1;
		primerLibro = 1;
		ultimoLibro = 1;
		return;
	}

	if (v[a] == v[b]) { // Más velocidad
		libro = b - a + 1;
		primerLibro = libro;
		ultimoLibro = libro;
		return;
	}

	//if (b == a + 1) { // Más velocidad
	//	if (v[a] == v[b]) {
	//		libro = 2;
	//		primerLibro = 2;
	//		ultimoLibro = 2;
	//	}
	//	else {
	//		libro = 1;
	//		primerLibro = 1;
	//		ultimoLibro = 1;
	//	}
	//	return;
	//}

	int libIz = 0, libDer = 0, primIz = 0, primDer = 0, ultIz = 0, ultDer = 0, n = a + (b - a) / 2; // a + eso por el desfase al mirar la dcha

	numCopias(v, a, n, libIz, primIz, ultIz);
	numCopias(v, n + 1, b, libDer, primDer, ultDer);

	libDer >= libIz ? libro = libDer : libro = libIz;

	int libMed = 0;
	if (v[n] == v[n + 1]) { 
		libMed = primDer + ultIz;
		if (v[a] == v[n]) primIz = libMed;
		if (v[b] == v[n + 1]) ultDer = libMed;
	}
	if (libMed > libro) libro = libMed;

	primerLibro = primIz;
	ultimoLibro = ultDer;

}

int main() {
	int n, libro = 0, primerLibro = 0, ultimoLibro = 0;

	cin >> n;

	while (n != 0) {
		for (int i = 0; i < n; i++) cin >> v[i];
		numCopias(v, 0, n - 1, libro, primerLibro, ultimoLibro);
		cout << libro << '\n';
		cin >> n;
		libro = 0;
		primerLibro = 0;
		ultimoLibro = 0;
	}

	return 0;
}