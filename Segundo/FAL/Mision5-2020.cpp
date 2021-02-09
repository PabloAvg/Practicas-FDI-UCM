#include <iostream>
using namespace std;


long long gardner(long long n) { // No funciona
	if (n <= 0) {
		return 0;
	}

	long long ret = 0, aux = n;

	if (n < 10) {
		while (aux % 10 != 0) {
			ret += aux % 10;
			aux -= 1;
		}
		return ret;
	}

	if (n % 10 == 0) { 
		ret = 45; // 1 + 2 + 3 + 4 + 5 + 6 + 7 + 8 + 9 = 45
		while (aux % 10 == 0) aux /= 10; // Si tengo el 100, quierro el 1 -> 100 10 1 y paro
		ret += aux; // cojo el 1
		return gardner(n - 10) + ret;
	}
	else {
		while (aux % 10 != 0) {
			ret += aux % 10;
			aux -= 1;
		}
		return gardner(n - 1) + ret;
	}
}


long long cuantos(long long n) {

	if (n == 0)
		return 0;

	long long raiz = n / 10;
	int ultDigito = n % 10;

	return 45 * raiz + ultDigito * (ultDigito + 1) / 2 + cuantos(raiz);//()
}

int main() {
	long long n;

	cin >> n;

	while (n != 0) {
		//cout << gardner(n) << '\n';
		cout << cuantos(n) << '\n';
		cin >> n;
	}

	return 0;
}