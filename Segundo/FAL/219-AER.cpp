#include <iostream>
using namespace std;

// Pre: 0 <= n <= longitud(v)
int peniatletica(int v[], int n) { /*return ret*/
	int i = 0, ret = 0;

	while (i < n) { // I: 0 <= i < n
		if (v[i] % 2 == 0) ret++;
		i++;
	}

	return ret;
} // Complejidad: O(n)
// Post: ret = #i : 0 <= i < n : v[i] % 2 = 0

int main() {
	int c, a, n;
	int v[10000];

	cin >> c;

	for (int i = 0; i < c; i++) {
		cin >> a;
		for (int j = 0; j < a; j++) {
			cin >> n;
			v[j] = n;
		}
		cout << peniatletica(v, a) << '\n';
	}

	return 0;
}