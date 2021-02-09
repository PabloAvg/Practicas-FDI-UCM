#include <iostream>
using namespace std;
#include "lista.h"


// EJERCICIO 20
// procedimiento gen?rico que muestra por pantalla una lista (asume elementos imprimibles)

template <class T>
void pinta(const Lista<T>& lista) {
	typename Lista<T>::ConstIterator it = lista.cbegin();
	while (it != lista.cend()) {
		cout << it.elem() << endl;
		it.next();
	}
}


// EJERCICIO 21
// Implementa un subprograma que reciba una lista de enteros 
// y cuente cu?ntas posiciones hay en ella tales que el elemento que hay 
// en esa posici?n es igual a la suma de todos sus precedentes

int numPicos(const Lista<int>& l) {

	int acumulado=0;	//Suma hasta el momento
	int ret = 0;		//Numero de elementos solucion

	for (int i = 0; i < l.longitud(); i++) {
		int elemento = l.elem(i);
		if (acumulado == elemento) {
			ret++;
		}
		acumulado += elemento;
	}
	return ret;
}


// EJERCICIO 23
// funci?n que duplica los elementos de una lista de enteros
// si la lista es [1,2,3] la transforma en [1,1,2,2,3,3] 

void repiteElementos(Lista<int>& lista) {

	int longlista = lista.longitud();	//Longitud de la lista inicial

	for (int i = 0; i < longlista; i++) {	//Bucle de duplicacion al final ~ 1 2 3 => 1 2 3 1 1 2 2 3 3
		int elemento = lista.elem(i);
		lista.pon_final(elemento);
		lista.pon_final(elemento);
	}

	for (int i = 0; i < longlista; i++) {	//Bucle de eliminacion  al principio ~ 1 2 3 => 1 1 2 2 3 3
		lista.quita_ppio();
	}
}


int main() {
	// listas para las pruebas
	Lista<int> listaVacia, lista1, lista2;

	for (int i = 1; i <= 5; i++)
		lista1.pon_final(i);

	for (int i = 1; i <= 5; i++)
		lista2.pon_ppio(i);

	Lista<int> listapicos;
	listapicos.pon_final(1);
	listapicos.pon_final(2);
	listapicos.pon_final(3);
	listapicos.pon_final(6);
	listapicos.pon_final(10);
	listapicos.pon_final(22);
	

	cout << "\n\n--- EJ 20: USO DE PROCEDIMIENTOS GENERICOS PARA MOSTRAR UNA LISTA ---\n";
	cout << "Lista vacia\n";
	pinta(listaVacia);
	cout << endl;
	cout << "Lista lista1\n";
	pinta(lista1);
	cout << endl;

	cout << "\n\n--- EJ 21: USO DE NUMPICOS PARA CONTAR EL NUMERO DE PICOS DE UNA LISTA ---\n";
	cout << "La lista vacia tiene " << numPicos(listaVacia) << " pico(s)\n";
	cout << "La lista \n";
	pinta(lista1);
	cout << "Tiene " << numPicos(lista1) << " pico(s)\n";
	cout << "La lista \n";
	pinta(lista2);
	cout << "Tiene " << numPicos(lista2) << " pico(s)\n";
	cout << "La lista \n";
	pinta(listapicos);
	cout << "Tiene " << numPicos(listapicos) << " pico(s)\n";
	cout << endl;

	cout << "\n\n--- EJ23: uso de REPITEELEMENTOS para DUPLICAR LOS ELEMENTOS DE lista ---\n";
	cout << "Cuando repito la lista vacia\n";
	pinta(listaVacia);
	cout << endl;
	cout << "Resulta\n";
	repiteElementos(listaVacia);
	pinta(listaVacia);
	cout << endl;
	cout << "Cuando repito la lista\n";
	pinta(lista1);
	cout << "Resulta\n";
	repiteElementos(lista1);
	pinta(lista1);
	cout << endl;

	system("PAUSE");
	return 0;
}