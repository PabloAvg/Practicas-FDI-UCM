#include <iostream>
#include <cmath>
#include <algorithm>
#include <time.h>

using namespace std;

int v[1000];

// { Pre: 2 <= l <= n  ^ 0 <= n <= longitud(v) }
int segmentos(int v[], int n, int l) /* Return ret */ { // Complejidad O(n) (lineal)
	int ret = 0, act = 1, i = 1;

	while (i < n) { // { I: ( 1 <= i < n ) ^ ( 1 <= act <= n) ^ ( 0 <= ret <= n/2 ) }
		if (abs(v[i] - v[i - 1]) <= 1) act++;
		else {
			if (act >= l) ret++;
			act = 1;
		}
		i++;
	}

	if (act >= l) ret++;

	return ret;
}
// { Post: ret = sum(seg) : seg = sum(v[i]) : 1 <= i <= n : abs(v[i] - v[i-1]) <= 1 : seg >= l }

int ex1(const int V[], const int N, const int l)
{
	int left, n, num;
	for (n = min(N, 1), num = left = 0; n < N; n++)
		if (abs(V[n] - V[n - 1]) <= 1)
			num += (n - left) == (l - 1);
		else
			left = n;
	return num;
}

int main() {
	int n, l;
	cin >> n >> l;

	while (n != 0 || l != 0) {
		for (int i = 0; i < n; i++) cin >> v[i];
		cout << segmentos(v, n, l) << '\n';
		cin >> n >> l;
	}
	
	return 0;
}