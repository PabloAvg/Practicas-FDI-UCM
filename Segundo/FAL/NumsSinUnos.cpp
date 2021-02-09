#include <iostream>

using namespace std;

bool noTengoUno(unsigned long long n) {
	if (n < 10) { // Caso Base
		return n != 1;
	}

	if (n % 10 != 1 && noTengoUno(n / 10)) return true;
	else return false;
}

unsigned long long numsinuno(int n) {
	if (n == 0) return 1;

	int ret = 0;

	if (noTengoUno(n)) ret++;

	ret += numsinuno(n - 1);
}

int main() {
	unsigned long long n;

	cin >> n;

	while (cin) {
		cout << numsinuno(n) << '\n';
		cin >> n;
	}

	return 0;
}