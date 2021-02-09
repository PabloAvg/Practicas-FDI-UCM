#include <iostream>
using namespace std;


const int MAX_OBRAS = 10000;

int pesos[MAX_OBRAS];

int minCarga(int v[], int n, int maxViajes, int p) {
    int ret = 0;

    if(minCarga(v, n, maxViajes, p) < maxViajes)
    
    return ret;
}

// Indica la complejidad.
// Puedes implementar funciones auxiliares
int minCarga(int v[], int n, int maxViajes) {
    int p = 1;
    return minCarga(v, n, maxViajes, p);
}


bool resuelve() {
    int n, maxViajes;

    // Lectura
    cin >> n >> maxViajes;
    if (!n && !maxViajes)
        return false;

    for (int i = 0; i < n; ++i)
        cin >> pesos[i];

    // Cálculo
    int ret = minCarga(pesos, n, maxViajes);

    // Escritura
    cout << ret << '\n';

    return true;
}

int main() {
    while (resuelve())
        ;
    return 0;
}