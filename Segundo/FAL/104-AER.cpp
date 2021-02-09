#include <iostream>
using namespace std;

bool movil(int p1, int d1, int p2, int d2, int& total) {
	if (p1 != 0 && p2 != 0) { // Caso Base
		total = p1 + p2;
		return p1 * d1 == p2 * d2;
	}

	bool der = true, iz = true;

	if (p1 == 0) { // Recursión izda
		int p1Iz, d1Iz, p2Iz, d2Iz;
		cin >> p1Iz >> d1Iz >> p2Iz >> d2Iz;
		iz = movil(p1Iz, d1Iz, p2Iz, d2Iz, total);
		p1 = total;
	}

	if (p2 == 0) { // Recursión dcha
		int p1Dech, d1Dech, p2Dech, d2Dech;
		cin >> p1Dech >> d1Dech >> p2Dech >> d2Dech;
		der = movil(p1Dech, d1Dech, p2Dech, d2Dech, total);
		p2 = total;
	}

	total = p1 + p2;

	return (p1 * d1 == p2 * d2) && iz && der;
}


int main() {
	int p1, p2, d1, d2, dummy = 0;
	cin >> p1 >> d1 >> p2 >> d2;
	while (!(p1 == 0 && d1 == 0 && p2 == 0 && d2 == 0)) {
		if (movil(p1, d1, p2, d2, dummy)) {
			cout << "SI" << endl;
		}
		else {
			cout << "NO" << endl;
		}
		cin >> p1 >> d1 >> p2 >> d2;
	}
	return 0;
}