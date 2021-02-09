#include <iostream>

using namespace std;

int digitos(long long int n) {
	if (n < 10) {
		cout << n;
		return n;
	}
	int yo = n % 10;
	int ret = digitos(n / 10) + yo;
	cout << " + " << yo;

	return ret;
}

int main() {
	long long int n;
	cin >> n;
	while (n != -1) {
		cout <<" = " << digitos(n) << '\n';
		cin >> n;
	}
	return 0;
}