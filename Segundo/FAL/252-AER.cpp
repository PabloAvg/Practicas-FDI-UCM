#include <iostream>
#include <string>

using namespace std;

// { Pre:  }
bool buhos(string caso) {
	int a = 0, b = caso.length() - 1;

	while (b > a && caso[a] == caso[b]) {
		a++;
		b--;
	}

	return caso[a] == caso[b];
}


int main() {
	string caso;
	getline(cin, caso);

	while (caso != "XXX") {
		for (int i = 0; i < caso.length(); i++) {
			while (caso[i] == ' ') {
				caso.erase(i, 1);
			}
			caso[i] = tolower(caso[i]);
		}
		if (buhos(caso)) cout << "SI\n";
		else cout << "NO\n";
		getline(cin, caso);
	}

	return 0;
}