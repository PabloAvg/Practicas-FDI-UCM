#include<windows.h>
#include"UtilidadesWin.h"


void colorFondo(tColor color) {
	HANDLE handle = GetStdHandle(STD_OUTPUT_HANDLE);
	SetConsoleTextAttribute(handle, 15 | (color << 4));
}