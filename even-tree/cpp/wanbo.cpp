#include<map>
#include<ctime>
#include<cmath>
#include<queue>
#include<bitset>
#include<vector>
#include<cstdio>
#include<string>
#include<cassert>
#include<cstring>
#include<numeric>
#include<sstream>
#include<iterator>
#include<iostream>
#include<algorithm>
using namespace std;
typedef long long LL;
#define MM(a, x) memset(a, x, sizeof(a))
#define P(x) cout << #x << " = " << x << endl;

int nv, ne;
vector<int> g[101];
vector<int> d[101];
int dp[101];
int v[101];

int F(int c) {
	int& r = dp[c];
	if(r == -1) {
		r = 1;
		for(int i = 0; i < d[c].size(); i++) {
			r += F(d[c][i]);
		}
	}
	return r;
}

void dfs(int c) {
	v[c] = 1;
	for(int i = 0; i < g[c].size(); i++) {
		int t = g[c][i];
		if(v[t]) continue;
		d[c].push_back(t);
		dfs(t);
	}
}

int main() {
	cin >> nv >> ne;
	for(int i = 0; i < ne; i++) {
		int A, B;
		cin >> A >> B;
		g[A].push_back(B);
		g[B].push_back(A);
	}
	dfs(1);
	MM(dp, -1);
	F(1);
	assert(dp[1] % 2 == 0);
	int r = -1;
	for(int i = 1; i <= nv; i++) if(dp[i] % 2 == 0) r++;
	cout << r << endl;
	system("pause");
	return 0;
}