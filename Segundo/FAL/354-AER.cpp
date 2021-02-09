#include <iostream>

using namespace std;

int v[200000];

// { Pre: 1 <= n <= longitud(v) }
int ninios(int v[], int n) /* Return ret */ { // Complejidad: O(n) (Lineal)
	int ret = 0, i = 1, alto = v[0], ult = v[0];

	while (i < n) {	// { I: ( 1 <= i < n ) ^ ( 1 <= ult <= alto ) ^ ( 0 <= ret <= i ) }	
		if (v[i > alto]) alto = v[i];
		if (v[i] <= ult) { ret = i; ult = alto; }
		i++;
	}
	return ++ret;
}
// { Post: ret = #i : 0 <= i <= j : v[i] < v[j] }

int main() {
	int n;
	cin >> n;

	while (n != 0) {
		for (int i = 0; i < n; i++) cin >> v[i];
		cout << ninios(v, n) << '\n';
		cin >> n;
	}

	return 0;
}