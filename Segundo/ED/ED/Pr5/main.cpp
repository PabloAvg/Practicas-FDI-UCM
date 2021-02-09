// Álvaro Corrochano López y Antonio Fernández Martín
#include <iostream>
using namespace std;
#include <string>

#include "lista.h"
// INCLUIR EL RESTO DE TADS QUE SE CONSIDEREN OPORTUNOS
#include "pila.h"

void invierteSecuenciasNoVocales(Lista<char> &mensaje) {
	Pila<char> p;
	int elemEnPila = 0;
	Lista<char>::Iterator it = mensaje.begin();
	while (it != mensaje.end()) {
		if (it.elem() != 'a' && it.elem() != 'e' && it.elem() != 'i' && // Consonantes
			it.elem() != 'o' && it.elem() != 'u' &&
			it.elem() != 'A' && it.elem() != 'E' && it.elem() != 'I' &&
			it.elem() != 'O' && it.elem() != 'U') {

			p.apila(it.elem());
			elemEnPila++;
			it = mensaje.eliminar(it);
		}
		else { // Vocales
			if (!p.esVacia()) { // Por si dos vocales seguidas o empieza por vocal
			
				for (int j = 0; j < elemEnPila; j++) {

					mensaje.insertar(p.cima(), it);
					p.desapila();
				}

				elemEnPila = 0;
			}
			it.next();
		}
	}

	if (!p.esVacia()) { // Pila final

		for (int j = 0; j < elemEnPila; j++) {

			mensaje.insertar(p.cima(), it);
			p.desapila();
		}

		elemEnPila = 0;
	}

}

 
// Imprime la lista por la salida estandar
void imprime(const Lista<char>& l) {
	l.print();
	cout << endl;
}


// Codifica el mensaje
void codifica(Lista<char>& mensaje) {
	invierteSecuenciasNoVocales(mensaje);
	mensaje.enredar();
}

  // Transforma la linea en una lista de caracteres
void construyeMensaje(const string& linea, Lista<char>& mensaje) {
	for(unsigned int i=0; i < linea.size(); i++)
		mensaje.pon_final(linea[i]);  
}

int main() {
	string linea;
	while(getline(cin,linea)) {
	   Lista<char> mensaje;
	   construyeMensaje(linea,mensaje);
       codifica(mensaje);
	   imprime(mensaje);
	}
	return 0;
}	
