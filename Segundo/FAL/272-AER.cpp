#include <iostream>
#include <string>

using namespace std;

long int tresDedos(long int n, int dig) {
	if (n < 6) return n;
	if (n % 6 == 0) return 10 * n / 6;

	int aux = n;
	while (aux % 6 != 0) aux--;

	return tresDedos(n/6, dig + 1) + n % 6;
}

long int tresDedos(long int n) {

	return tresDedos(n, 0);
}

string convierte(long int n) {
	string num_cad = "";

	if (n < 6) { // Caso base, el número es menor que 6.
		num_cad = to_string(n);
	}
	else { //Caso recursivo, todavía es mayor que 6.
		num_cad = convierte(n / 6) + to_string(n % 6);
	}
	return num_cad;
}

int main() {
	long int n, c;

	cin >> c;

	for (int i = 0; i < c; i++) {
		cin >> n;
		cout << convierte(n) << '\n';
		cout << tresDedos(n) << '\n';
	}

	return 0;
}