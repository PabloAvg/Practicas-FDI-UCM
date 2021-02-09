#include <iostream>

int fractal(int l) {
	if (l == 1) return 4;
	return 4 *(fractal(l/2) + l);
}


int main() {
	int l;
	
	std::cin >> l;

	while (std::cin) {
		printf("%d\n", fractal(l));
		std::cin >> l;
	}
	return 0;
}