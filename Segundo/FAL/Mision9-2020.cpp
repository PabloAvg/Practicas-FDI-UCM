//---------------------------------------------------
// Ejercicio "Una tarde en el cine"
// Estructura de Datos y Algoritmos
// Facultad de Informática
// Universidad Complutense de Madrid
//---------------------------------------------------


#include <iostream>
#include <algorithm> // Para función abs().
using namespace std;

#define MAX_N 10
#define MAX_R 20

struct Restriccion {
    int a;
    int b;
    bool amigos; // true si son amigos (dist será la distancia máxima)
    int dist; // Siempre positivo (se quita el signo en la lectura)
};

bool esPrometedora(Restriccion restricciones[], int nRest, int k, int nuevo, int solp[]) {
    bool ret = true;

    for (int i = 0; i < nRest && ret; i++) { // Si se cumplen las restricciones
        if (restricciones[i].a == nuevo) {
            if (restricciones[i].amigos) {
                for (int j = 0; j < (k - 1) - restricciones->dist && ret; j++) {
                    if (solp[j] == restricciones[i].b) ret = false;
                }
            }
            else {
                int aux;
                if (k - 1 - restricciones->dist > 0) aux = 0;
                else aux = k - 1 - restricciones->dist + 1;
                for (aux; aux < k - 1 && ret; aux++) {
                    if (solp[aux] == restricciones[i].b) ret = false;
                }
            }
        
        }
        else if (restricciones[i].b == nuevo) {
            if (restricciones[i].amigos) {
                for (int j = 0; j < (k - 1) - restricciones->dist && ret; j++) {
                    if (solp[j] == restricciones[i].a) ret = false;
                }
            }
            else {
                int aux;
                if (k - 1 - restricciones->dist > 0) aux = 0;
                else aux = k - 1 - restricciones->dist + 1;
               for (aux; aux < k - 1 && ret; aux++) {
                   if (solp[aux] == restricciones[i].a) ret = false;
               }
            }
        }
    }

    return ret;
}

int cuantas(
    // Descripción del problema
    int n, // Personas a sentar
    Restriccion restricciones[], // Lista de restricciones
    int nRestricciones, // Número de restricciones que hay

    // ... Añade otros parámetros si los necesitas ...
    int k, // Por el que voy
    int solp[], // Sol parcial
    bool marc[] // Marcaje
    ) {

    int ret = 0;

    for (int i = 0; i < n; i++) {
        if (!marc[i]) {
            solp[k] = i;
            marc[i] = true;

            if (esPrometedora(restricciones, nRestricciones, k + 1, i, solp)) {
                if (k == n - 1) {
                    ret++;
                }
                else {
                    ret += cuantas(n, restricciones, nRestricciones, k + 1, solp, marc);
                }
            }
            marc[i] = false;
        }
    }

    return ret;
}


bool resuelve() {

    Restriccion restricciones[MAX_R];
    // ... Añade otras declaraciones si las necesitas ...
    int solP[MAX_N];
    bool marc[MAX_N] = {false};

    // Lectura
    int n, m;
    cin >> n >> m;

    if (!n && !m)
        return false;

    for (int i = 0; i < m; ++i) {
        cin >> restricciones[i].a
            >> restricciones[i].b
            >> restricciones[i].dist;
        restricciones[i].amigos = restricciones[i].dist > 0;
        restricciones[i].dist = abs(restricciones[i].dist);
    }

    int ret = cuantas(n, restricciones, m, 0, solP, marc);

    cout << ret << '\n';

    return true;

} // resuelve

int main() {

    while (resuelve())
        ;

    return 0;
} // main