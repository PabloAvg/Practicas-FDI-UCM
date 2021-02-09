#include <iostream>
#include <cmath>
using namespace std;

int v[200000];

// { ( Pre: 0 <= n <= longitud(v) ) ^ ( 0 <= k ) }
int kemparejados(int v[], int n, int k) /* Return ret */ { // Complejidad = O(n) (Lineal)
	int ret = 0, a = 0, b = 0;

	while (b < n) { //{ I: ( 0 <= a <= b < n ) ^ ( 0 <= ret <= n / 2 ) } 
		if (abs(v[b] - v[a]) == k) {
			ret++;
			a++;
			b++;
		}
		else if (abs(v[b] - v[a]) > k) a++;
		else b++;
	}

	return ret;
}
// { Post: ret = i, j : 0 <= i <= j < n : #( abs( v[i] - v[j] ) = k ) }

int main() {
	int n, k;

	cin >> n;

	while (n != -1){
		cin >> k;
		for (int i = 0; i < n; i++) cin >> v[i];
		cout << kemparejados(v, n, k) << '\n';
		cin >> n;
	}
	return 0;
}