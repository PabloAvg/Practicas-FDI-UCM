#include <iostream>
using namespace std;

long long acarreo(long long num1, long long num2, int ac) {
	long long ret = 0;

	if (num1 == 0 && num2 == 0) return 0;

	ret += 0 != ((num1 % 10 + num2 % 10 + ac) / 10);
	ret += acarreo(num1 / 10, num2 / 10, ret);

	return ret;
}

int acarreo(int num1, int num2) {
	int ac = 0;
	return acarreo(num1, num2, ac);
}

int main() {
	long long num1, num2;

	cin >> num1 >> num2;

	while (num1 != 0 || num2 != 0) {
		cout << acarreo(num1, num2) << '\n';
		cin >> num1 >> num2;
	}

	return 0;
}