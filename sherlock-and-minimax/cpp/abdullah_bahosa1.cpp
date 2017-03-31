#define _CRT_SECURE_NO_WARNINGS
#include<iostream>
#include<string>
#include<math.h>
#include<queue>
#include<stack>
#include<stdio.h>
#include<map>
#include<set>
#include<memory.h>
#include<algorithm>
#include<vector>
#include<stdlib.h>
#include<sstream>
using namespace std;
typedef long long ll;
ll ABS(ll x){
	if (x<0)return -x;
	return x;
}
ll gcd(ll a, ll b){
	if (!b)
		return a;
	return gcd(b, a%b);
}
ll lcm(ll a, ll b){
	return b / gcd(a, b)*a;
}
#define FOR(I,N) for(int(i)=0;i<int(N);++i)
#define FORK(I,N,K) for(int(i)=0;i<int(N);i+=int(K))
int n, a[1000], p, q;
vector<int>v;
int main(){
	cin >> n;
	FOR(0, n)
		cin >> a[i];
	cin >> p >> q;
	sort(a, a + n);
	for (int i = 0; i < n - 1; ++i){
		int mid = a[i] + a[i + 1];
		v.push_back(mid / 2);
		v.push_back(mid / 2 + 1);
	}
	v.push_back(p);
	v.push_back(q);
	sort(v.begin(), v.end());
	int best, mx = -1;
	FOR(0, v.size()){
		if (v[i]<p || v[i]>q)
			continue;
		int g = 1 << 30 ;
		for (int j = 0; j < n; ++j)
			g = min(g, (int)ABS(v[i] - a[j]));
		//cout << v[i] << " " << g << endl;
		if (g>mx)
		{
			mx = g;
			best = v[i];
		}
	}
	cout << best << endl;
	cin >> n;
}