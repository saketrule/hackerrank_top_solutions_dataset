#include <iostream>
#include <iomanip>
#include <cstdio>
#include <math.h>
#include <algorithm>
#include <queue>
#include <string>
#include <fstream>
#include <vector>
#include <stack>
#include <map>
#include <set>
#include <list>
#include <ctime>

#define all(x) (x).begin(), (x).end()
#define pb push_back
#define double long double
//#define LL long long
#define itn int

using namespace std;

int main(){

//	freopen("combo.in", "r", stdin);
//	freopen("combo.out", "w", stdout);

	int n;
	cin >> n;
	vector<int> a(n);
	for (int i = 0; i < n; i++) cin >> a[i];
	sort(a.begin(), a.end());
	int k = 0;
	for (int i = 1; i < n; i++){
		if (a[i] != a[i - 1]){
			cout << n - k << "\n";
			k = i;
		}
	}
	cout << n - k;
	
	return 0;
	
}