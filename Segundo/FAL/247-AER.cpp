#include <iostream>

using namespace std;

// Pre: 0 < n <= longitud(v)
bool abejas(int v[], int n) { // Return ret
	int i = 0, ant = 0;
	bool ret = true;

	while (i < n && ret) { // I: 0 <= i < n
		if (ant < v[i]) ant = v[i];
		else ret = false;
		i++;
	}

	return ret;
} // Complejidad: O(n)
// Post: ret = true: P.t. i : 0 < i < n : v[i - 1] < v[i] 

int main() {
	int n;
	int v[100];

	cin >> n;

	while (n != 0) {
		for (int i = 0; i < n; i++) cin >> v[i];

		if (abejas(v, n)) cout << "SI\n";
		else cout << "NO\n";

		cin >> n;
	}

	return 0;
}