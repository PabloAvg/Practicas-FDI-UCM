#include <stdio.h>
#include <iostream>
using namespace std;

typedef unsigned long long uint64;
uint64 seed;
uint64 state[2] = { seed, seed ^ 0x7263d9bd8409f526 };

uint64 xoroshiro128plus(uint64 s[2]) {
    uint64 s0 = s[0];
    uint64 s1 = s[1];
    uint64 result = s0 + s1;
    s1 ^= s0;
    s[0] = ((s0 << 55) | (s0 >> 9)) ^ s1 ^ (s1 << 14);
    s[1] = (s1 << 36) | (s1 >> 28);
    return result;
}

void initSystem(int seed) {
    state[0] = seed;
    state[1] = ((uint64)seed) ^ 0x7263d9bd8409f526;
}

int clave() {
    return xoroshiro128plus(state) % 100000;
}

bool haySietes(int n) {
    while (n % 10 != 7 && n != 0) n /= 10;
    return n % 10 != 7;
}

int secuenciaMasLarga(int n) {
    int i = 0, ret = 0, acum = 0    ;

    while (i != n) {
        if (haySietes(clave())) acum++;
        else{
            if (acum > ret) ret = acum;
            acum = 0;
        }
        i++;
    }
    if (acum > ret) ret = acum;
    acum = 0;
    return ret;
}

bool resuelve() {

    // Lectura
    int s, n;
    cin >> s >> n;
    if ((s == 0) && (n == 0))
        return false;

    initSystem(s);

    // Cálculo
    int r = secuenciaMasLarga(n);

    // Escritura
    cout << r << '\n';

    return true;

} // resuelve

int main() {

    // Resolvemos
    while (resuelve())
        ;

    return 0;

} // main