#include <iostream>

using namespace std;

bool esprometedora(int i, unsigned long long solp, unsigned long long n, unsigned long long aux) {
	return i != 1 && solp <= n && aux != solp;
}

unsigned long long numsinunoback(unsigned long long n, int k, long long solp) {
	int ret = 0;
	unsigned long long aux = solp;

	for (int i = 0; i < 10 && i <= n; i++) {
		solp = solp * 10 + i;

		if (esprometedora(i, solp, n, aux)) {
			ret++;			
			numsinunoback(n, k + 1, solp);
		}
		solp = aux;
	}

	return ret;
}


int main() {
	unsigned long long n;

	cin >> n;

	while (cin) {
		cout << numsinunoback(n, 0, -1) << '\n';
	}

	return 0;
}
