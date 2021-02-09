#include <iostream>

using namespace std;

int v[100000];

// { Pre: 0 <= n <= longitud(v) }
int erasmus(int v[], int n) /* Return ret */ { // Complejidad: O(n) (Lineal)	
	int i = 1, sumAc = v[0], ret = 0;

	while (i < n) {			// { I: ( 0 <= i < n ) ^
		ret += v[i] * sumAc; // ( sumAc = sum v[i] : 0 <= i < n ) ^
		sumAc += v[i];		// ( ret = sumAc * v[i] : 0 <= i < n ) }
		i++;
	}

	return ret;
}
// { Post: ret = sum( sumAc * v[i] ) : sumAc = sum v[i] : 0 <= i < n }

int main() {
	int n;

	cin >> n;

	while (n != 0) {
		for (int i = 0; i < n; i++) { cin >> v[i]; };

		cout << erasmus(v, n) << '\n';

		cin >> n;
	}
	return 0;
}