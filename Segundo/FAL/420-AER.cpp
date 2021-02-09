#include <iostream>

using namespace std;

int clave(long long int sum, long long int num) {
	int act = num, i = 0, suma = 0, prim = num, ret = 0;

	while (act / 10 != 0 || act % 10 != 0) {
		suma += act % 10;
		act /= 10;
		if (sum == suma) {
			ret++;
			suma -= prim % 10;
			prim /= 10;
		}
		else if (suma > sum) {
			while (prim != act && suma > sum) {
				suma -= prim % 10;
				prim /= 10;
			}
			if (suma == sum) { ret++; suma = suma - prim % 10; prim /= 10;}
		}
	}
	return ret;
}


int main() {
	long long int c, num;
	int n,i = 0;
	cin >> n;
	while (i < n) {
		cin >> c >> num;
		cout << clave(c, num) << '\n';
		i++;
	}
	return 0;
}