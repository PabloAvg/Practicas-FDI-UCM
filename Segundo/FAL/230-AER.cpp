#include <iostream>

using namespace std;

int v[100000];

int desorden(int v[], int n, int a, int& min, int& max) {
	if (a >= n - 1) { // Return 0
		min = v[n - 1];
		max = min;
		return 0;
	}

	if (a + 1 == n - 1) {
		if()
	}

	int ret = 0, minIz = 0, maxIz = 0, minDer = 0, maxDer = 0, med = a + (n - 1 - a) / 2;
	
	ret += desorden(v, med, a, minIz, maxIz);
	ret += desorden(v, n, med + 1, minDer, maxDer);

	if (maxIz > maxDer) ret += n - med + 1;
	if (minIz > maxDer) ret += (med - a + 1) * (n - med + 1);
	else if (minIz > minDer) ret += 1;

	return ret;
}


int desorden(int v[], int n) {
	int min = 0, max = 0;
	return desorden(v, n, 0, min, max);
}

int main() {
	int n;

	cin >> n;

	while (n != 0) {
		for (int i = 0; i < n; i++) cin >> v[i];
		cout << desorden(v, n) << '\n';
		cin >> n;
	}

	return 0;
}