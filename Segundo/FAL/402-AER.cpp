#include <iostream>
#include <math.h>

using namespace std;

// { Pre: 0 <= n <= longitud(v) }
int puzle(int n) /* Return ret */ {
	int ret = sqrt(n);

	while (n % ret != 0) ret--;
	
	return n / ret;
}

int main() {
	int n;

	cin >> n;

	while (n != 0) {
		cout << puzle(n) << '\n';

		cin >> n;
	}
	return 0;
}