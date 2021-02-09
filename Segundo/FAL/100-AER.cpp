#include <iostream>
#include <algorithm>

using namespace std;

const int KAP = 6174, MAX = 4;

int asc(int v[]) { // De mayor a menor
	int ret = 0, i = MAX - 1;

	sort(v, v + MAX, greater<int>());

	while (i >= 0) {
		ret = (ret * 10) + v[i];
		i--;
	}

	return ret;
}

int desc(int v[]) { // de menor a mayor
	int ret = 0, i = 0;

	sort(v, v + MAX, greater<int>());

	while (i < MAX) {
		ret = (ret * 10) + v[i];
		i++;
	}

	return ret;
}

int kaprekar(int v[], int y) {
	int ret = 0, x = y, aux = y, i = 0;

	/*while(i < MAX) {
		x = (x * 10) + v[i];
	}*/

	while (x != KAP && ret <= 7) {
		ret++;
		x = desc(v) - asc(v);
		aux = x;
		while (i < MAX) {
			v[i] = aux % 10;
			aux /= 10;
			i++;
		}
		i = 0;
	}
	return ret;
}

int main() {
	int n, x, y;
	int v[4];

	cin >> n;

	for (int i = 0; i < n; i++) {
		cin >> x;
		y = x;
		for (int i = 0; i < MAX; i++) { v[i] = x % 10; x /= 10; }
		cout << kaprekar(v, y) << '\n';
	}

	return 0;
}