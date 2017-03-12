#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int main() {

	int n;
	cin >> n;
	int a[1000], al[1001]={0};
	vector<int> v;
	for(int i=0; i<n; ++i) {
		cin >> a[i];
		al[a[i]]++;
	}
	for(int i=0; i<1001; ++i) {
		if(al[i]) v.push_back(al[i]);
	}
	cout << n << endl;
	for(int i=0; i<v.size(); ++i) {
		n-=v[i];
		if(n) cout << n << endl;
	}
	return 0;
}