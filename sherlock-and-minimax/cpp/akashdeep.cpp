#include <cstdio>
#include <cmath>
#include <iostream>
#include <set>
#include <algorithm>
#include <vector>
#include <map>
#include <cassert>
#include <string>
#include <cstring>

using namespace std;

#define rep(i,a,b) for(int i = a; i < b; i++)
#define S(x) scanf("%d",&x)
#define P(x) printf("%d\n",x)

typedef long long int LL;
int n;
int A[101];

int val(int x) {
	int mn = abs(x-A[0]);
	rep(i,0,n) mn = min(mn, (int)abs(x-A[i]));
	return mn;
}

int main() {
	S(n);
	rep(i,0,n) S(A[i]);
	int p,q;
	scanf("%d%d",&p,&q);
	sort(A,A+n);

	vector<pair<int, int > > v;

	if(p <= A[0]) {
		v.push_back(make_pair(A[0]-p, p));
	}

	rep(i,1,n) {
		int mi = (A[i]+A[i-1]) / 2;
		if(mi < p || mi > q) {
			if(q >= A[i-1] && q < mi) mi = q;
			else if(p <= A[i] && q > mi) mi = p;
			else mi = -1;
		}
		if(mi != -1) v.push_back(make_pair(val(mi), mi));
	}

	if(q >= A[n-1]) v.push_back(make_pair(q-A[n-1], q));

	int mx = v[0].first;
	rep(i,0,v.size()) mx = max(mx, v[i].first);

	int ans = -1;
	rep(i,0,v.size()) if(v[i].first == mx) {
		if(ans == -1 || v[i].second < ans) ans = v[i].second;
	}
	P(ans);

	return 0;
}