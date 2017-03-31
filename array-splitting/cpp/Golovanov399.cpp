#include <bits/stdc++.h>

#define all(x) (x).begin(), (x).end()

using namespace std;

inline int nxt(){
	int n;
	scanf("%d", &n);
	return n;
}

void solve(){
	int n = nxt();
	vector<int> a(n);
	for (int i = 0; i < n; i++)
		a[i] = nxt();
	long long s = 0;
	for (int i = 0; i < n; i++)
		s += a[i];

	if (s == 0){
		cout << n - 1 << "\n";
		return;
	}

	vector<int> ok(n);
	ok[0] = n;
	int res = 0;
	while (1){
		// for (int i = 0; i < n; i++)
		// 	cerr << ok[i] << " ";
		// cerr << "\n";
		if (s % 2)
			break;
		s /= 2;
		vector<int> nw(n);
		int j = 0;
		long long cr = 0;
		for (int i = 0; i < n; i++){
			while (j < n && cr < s){
				cr += a[j];
				j++;
			}
			nw[i] = (cr == s) ? j : 0;
			cr -= a[i];
		}
		vector<int> nok(n);
		for (int i = 0; i < n; i++){
			if (ok[i]){
				nok[i] = nw[i];
				nok[nw[i]] = nw[nw[i]];
			}
		}
		nok.swap(ok);
		bool flag = 0;
		for (int i = 0; i < n; i++){
			if (ok[i])
				flag = 1;
		}
		if (flag)
			res++;
		else
			break;
	}

	cout << res << "\n";
}

int main(){

	int T = nxt();
	while (T--){
		solve();
	}

	return 0;
}