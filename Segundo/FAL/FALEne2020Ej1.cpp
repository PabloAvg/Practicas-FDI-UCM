#include <iostream>

using namespace std;

// { Pre: 0 <= n }
bool entretenido(unsigned long long n) /* Return ret */ { // Complejidad: O(x), donde x es el número de dígitos de n ( Lineal )
	// if (n < 10) return true; // Caso base

	int aux = n % 10; // Caso iterativo
	n /= 10;
	while (n != 0 && aux != n % 10) { // { I: ( 0 <= n ) ^ ( 0 <= aux <= 9 ) }
		aux = n % 10;
		n /= 10;
	} // { Cota: i }

	return n == 0; 
}
// { Post: ret = true : P.t. i : 0 <= i < i + 1 < dig(n) : v[i] != v[i + 1] }

int main() {
	unsigned long long n;

	cin >> n;

	while (n != -1) {
		if (entretenido(n)) cout << "ENTRENIDO\n";
		else cout << "NO ENTRETENIDO\n";
		cin >> n;
	}

	return 0;
}