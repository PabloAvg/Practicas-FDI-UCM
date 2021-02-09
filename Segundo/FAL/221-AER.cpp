#include <iostream>

using namespace std;

// Pre: 0 <= n <= longitud(v)
bool cine(int v[], int n, int& k) { // return ret
	int i = 0;
	k = 0;

	while (v[i] % 2 == 0 && i < n) { i++, k++; }; // I: 0 <= i <= n : i = k

	while (v[i] % 2 != 0 && i < n) i++; // I: 0 <= k <= i <= n
	
	return i == n;
} // Complejidad: O(n)
// Post: ret = true : (p.t. i <= k : v[i] % 2 == 0) ^ (p.t. j > k : v[j] % 2 != 0)

int main() {
	int n, p, m, k;
	int v[10000];

	cin >> n;

	for (int i = 0; i < n; i++) {
		cin >> p;
		for (int j = 0; j < p; j++) {
			cin >> m;
			v[j] = m;
		}
		if (cine(v, p, k)) cout << "SI " << k << '\n';
		else cout << "NO\n";
	}

	return 0;
}