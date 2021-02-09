#include <iostream>
using namespace std;
#include "lista.h"


// EJERCICIO 20
// procedimiento gen�rico que muestra por pantalla una lista (asume elementos imprimibles)

template <class T>
void pinta(const Lista<T>& lista) {
	typename Lista<T>::ConstIterator it = lista.cbegin();
	while (it != lista.cend()) {
		cout << it.elem() << endl;
		it.next();
	}
}

// EJERCICIO 24
// subprograma que reciba dos listas de enteros ordenados crecientemente 
// y devuelva una nueva lista ordenada con la uni�n de los enteros de las 
// dos listas iniciales
Lista<int> mezclar(const Lista<int>& l1, const Lista<int>& l2) {
	
	Lista<int> listaret;
	int i_lista1=0; int i_lista2=0;

	if (l1.esVacia()) {
		listaret = l2;
		if (l2.esVacia()) {
			return l2;
		}
		return l2;
	}

	if (l2.esVacia()) {
		listaret = l1;
		if (l1.esVacia()) {
			return l1;
		}
		return l1;
	}



	for (int i = 0; i < l1.longitud() + l2.longitud(); i++) {

		if (i_lista1 == l1.longitud()) {
			listaret.pon_final(l2.elem(i_lista2));
			i_lista2++;
		}
		else if (i_lista2 == l2.longitud()) {
			listaret.pon_final(l1.elem(i_lista1));
			i_lista1++;
		}
		else if (l1.elem(i_lista1) <= l2.elem(i_lista2)) {
			listaret.pon_final(l1.elem(i_lista1));
			i_lista1++;
		}
		else {
			listaret.pon_final(l2.elem(i_lista2));
			i_lista2++;
		}
	}

	return listaret;
}


// EJERCICIO 25
// subprograma que elimina los n�meros pares de una lista de enteros
void quitaPares(Lista<int>& lista) {
	int longitud = lista.longitud();

	if (lista.esVacia()) {
		return;
	}

	for (int i = 0; i < longitud; i++) {
		if (lista.elem(i) % 2 != 0) {
			lista.pon_final(lista.elem(i));
		}
	}
	
	for (int i = 0; i < longitud; i++) {
		lista.quita_ppio();
	}
}

int main() {

	// listas para las pruebas
	Lista<int> listaVacia, lista1, lista2, lo1, lo2;

	for (int i = 1; i <= 6; i++)
		for (int repeticiones = 1; repeticiones <= 2; repeticiones++)
			lista1.pon_final(i);

	for (int i = 2; i <= 6; i = i + 2)
		lista2.pon_ppio(i);

	for (int i = 1; i < 10; i = i + 2)
		lo1.pon_final(i);

	for (int i = 0; i <= 10; i = i + 2)
		lo2.pon_final(i);



	cout << "\n\n--- EJ24: uso de MEZCLAR para MEZCLAR DOS LISTAS ORDENADAS ---\n";

	cout << "Cuando mezclo dos listas vacias resulta\n";
	pinta(mezclar(listaVacia, listaVacia));
	cout << endl;

	cout << "Cuando mezclo la lista vacia con \n";
	pinta(lo1);
	cout << "Resulta\n";
	pinta(mezclar(listaVacia, lo1));

	cout << "Cuando mezclo\n";
	pinta(lo1);
	cout << "con la lista vacia resulta\n";
	pinta(mezclar(lo1, listaVacia));

	cout << "Cuando mezclo\n";
	pinta(lo1);
	cout << "con\n";
	pinta(lo2);
	cout << "Resulta\n";
	pinta(mezclar(lo1, lo2));

	cout << "Cuando mezclo\n";
	pinta(lo2);
	cout << "con\n";
	pinta(lo1);
	cout << "Resulta\n";
	pinta(mezclar(lo2, lo1));


	cout << "\n\n--- EJ25: uso de ELIMINAR para ELIMINAR LOS PARES DE lista ---\n";

	cout << "Cuando elimino los numeros pares de la lista vacia resulta\n";
	quitaPares(listaVacia);
	pinta(listaVacia);
	cout << endl;

	cout << "Cuando elimino los numeros pares de la lista\n";
	pinta(lista1);
	cout << "Resulta\n";
	quitaPares(lista1);
	pinta(lista1);
	cout << endl;

	cout << "Cuando elimino los numeros pares de la lista\n";
	pinta(lista2);
	cout << "Resulta\n";
	quitaPares(lista2);
	pinta(lista2);
	cout << endl;


	system("PAUSE");
	return 0;
}