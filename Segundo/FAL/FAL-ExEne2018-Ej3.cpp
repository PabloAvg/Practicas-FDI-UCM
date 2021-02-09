#include <iostream>

using namespace std;

typedef struct {
	int a; // Ancho de máquina
	int c[50]; // Calidad en cada carretera
}tMaquina;

int v[50] = {0}; // Marcaje

bool esPrometedora(int marc[], int i, tMaquina maq[], int cancho[], int k) {
	if (marc[i] > 1) return false;
	if (cancho[i] < maq[k].a) return false;
	return true;
}

int backcarreteras(int k /* por donde voy */, int m /* máquinas que hay */, int c /* carreteras que hay */, int marc[] /* carretras usadas */, 
	tMaquina maq[] /* Máquinas que hay */, int calidadp /* Calidad acumulada */, int cancho[] /* Ancho de las carreteras */) {

	if (m == 0) return 0; // Caso Base

	int ret = 0;

	for (int i = 0; i < c; i++) { // Backtrack
		calidadp += maq[k].c[i];
		marc[i]++;

		if (esPrometedora(marc, i, maq, cancho, k)) {
			if (k == m - 1) {
				if (ret < calidadp) ret = calidadp;
			}
			else {
				int aux = backcarreteras(k + 1, m, c, marc, maq, calidadp, cancho);
				if (aux > ret) ret = aux;
			}
		}
		calidadp -= maq[k].c[i];
		marc[i]--;
	}
	return ret;
}

int main() {
	int casos, m, c;
	tMaquina maq[49];
	int caranc[50]; // Ancho de cada carretera

	cin >> casos;

	for (int i = 0; i < casos; i++) {
		cin >> m >> c;
		
		for (int i = 0; i < m; i++) cin >> maq[i].a;
		for (int i = 0; i < c; i++) cin >> caranc[i];

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < c; j++) {
				cin >> maq[i].c[j];
			}
		}
		cout << backcarreteras(0, m, c, v, maq, 0, caranc) << "\n\n";
	}

	return 0;
}