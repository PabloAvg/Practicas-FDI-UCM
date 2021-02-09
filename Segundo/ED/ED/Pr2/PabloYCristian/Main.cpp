//Pablo Alvarez, Cristian Emanuel Anei

#include <iostream>
#include <string>
#include "pila.h"
using namespace std;

bool esEquilibrada(const string& cadena) {
    bool ret = true;
    Pila<char> pila;
    
    unsigned long long int i = 0;
    while(i < cadena.length() && ret) {
      
        if ((cadena[i] == '(') || (cadena[i] == '{') || (cadena[i] == '[')) {
            pila.apila(cadena[i]);
        }
        else if ((cadena[i] == ')') || (cadena[i] == '}') || (cadena[i] == ']')) {
           
            if (pila.esVacia()) ret = false;
            else if (
                   (cadena[i] == ')' && !(pila.cima() == '('))
                || (cadena[i] == ']' && !(pila.cima() == '['))
                || (cadena[i] == '}' && !(pila.cima() == '{'))
                )
            {
                ret = false;
            }
            else pila.desapila();
        }
        i++;
    }
    if (!pila.esVacia())
        ret = false;
    return ret;
}


int main() {

    string cadena;
    while (getline(cin, cadena)) {
        if (esEquilibrada(cadena))
            cout << "EQUILIBRADA" << endl;
        else
            cout << "NO_EQUILIBRADA" << endl;
    }
    return 0;
}
