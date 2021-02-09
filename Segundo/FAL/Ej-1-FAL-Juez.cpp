#include <iostream>

using namespace std;

int v[100000];

// { Pre: ( 0 <= n <= longitud(v) ) ^ ( 1 <= k <= n ) }
int cadenaLarga(int v[], int n, int k) /* Return ret */ { // Complejidad: O(n) (lineal)
	int ret = n + 1, a = 0, b = 0, acum = 0;

	while (a <= b && b < n) { // { I: ( 0 <= a <= b < n ) ^ ( ret <= k ) ^ ( k < n ) } 
        if (v[a] == 0 && a < b) {
            a++;
        }
        else {
            if (v[b] == 1) acum++;
            if (acum == k) {
                if (ret > (b - a + 1)) ret = b - a + 1;
                a++;
                acum--;
            }
            b++;
        }
	}
	return ret;
}
// { Post: ret = min i,j : 0 <= i <= j < n: ((# L : i <= L <= j : v[L] = true) = k) ^ ( j - i + 1 ) }

int main() {
	int tam, k;

	cin >> tam;

	while (tam != -1) {
		for (int i = 0; i < tam; i++) cin >> v[i];
		cin >> k;
		cout << cadenaLarga(v, tam, k) << '\n';
		cin >> tam;
	}

	return 0;
}