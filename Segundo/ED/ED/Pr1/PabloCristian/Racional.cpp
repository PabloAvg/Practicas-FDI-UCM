#include "Racional.h"
#include <cmath>


//Metodos publicos

  	//**** COMPLETAR
	//  Deben implementarse los metodos publicos de la clase
	//****

Racional::Racional() {
	_numer = new long;
	*_numer = 0;
	_denom = new long;
	*_denom = 1;
}

Racional::Racional(long n, long d) {
	if (d == 0) throw EDenominadorCero();
	_numer = new long;
	*_numer = n;
	_denom = new long;
	*_denom = d;
}

/*Racional:: ~Racional() {
	delete _numer;
	delete _denom;
}*/

Racional Racional::suma(const Racional& c)const {

	long aux = mcm(*this->_denom, *c._denom);

	Racional r = Racional(
		(*this->_numer*(aux/ *this->_denom)) + 
		(*c._numer*(aux/ *c._denom))
		,aux);

	r.reduce();
	return r;
}

Racional Racional::operator-(const Racional& c)const {
	
	long aux = mcm(*this->_denom, *c._denom);

	Racional r = Racional(
		(*this->_numer * (aux / *this->_denom)) -
		(*c._numer * (aux / *c._denom))
		, aux);

	r.reduce();
	return r;
}

void Racional::operator*=(const Racional& c) {
	*this->_numer *= *c._numer;
	*this->_denom *= *c._denom;
	this->reduce();
}

void Racional::divideYActualiza(const Racional& c) {
	if(*c._numer == 0) throw EDivisionPorCero();
	*this->_numer *= *c._denom;
	*this->_denom *= *c._numer;
	this->reduce();
}

bool Racional::operator==( Racional& c) {
	this->reduce();
	c.reduce();
	return ((*this->_numer == *c._numer) && (*this->_denom == *c._denom));
}

	
	
// Funciones amigas
ostream& operator<<(ostream& out, const Racional& f) {
	out << *f._numer << "/" << *f._denom;
	return out;
}

// Metodos privados
void Racional::reduce() {
	  // Se asegura que el denominador siempre sea positivo   
	if (*_denom < 0) {
		*_numer = -(*_numer);
		*_denom = -(* _denom);
	}
	  // Se divide numerador y denominador por el maximo comun divisor de ambos
	long fsimp = mcd(*_numer, *_denom);
	*_numer /= fsimp;
	*_denom /= fsimp;
}

long Racional::mcd(long v1, long v2) {
	  // El calculo del maximo comun divisor se hace por el algoritmo de Euclides
	v1 = abs(v1); 
    v2 = abs(v2);	
	if (v1 < v2) {
		long tmp = v1;
		v1 = v2;
		v2 = tmp;
	}
	while (v2 != 0) {
		long nv2 = v1%v2;
		v1 = v2;
		v2 = nv2;
	}
	return v1;
}

long Racional::mcm(long v1, long v2) {
	return v1*v2 / mcd(v1, v2);
}

