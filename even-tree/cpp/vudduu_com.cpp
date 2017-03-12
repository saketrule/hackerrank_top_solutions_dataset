#include <iostream>
#include <sstream>
#include <utility>
#include <cstdlib>
#include <cstdio>
#include <cctype>
#include <cmath>
#include <functional>
#include <algorithm>
#include <numeric>
#include <string>
#include <vector>
#include <queue>
#include <stack>
#include <list>
#include <map>
#include <set>
#include <stdio.h>
#include <string.h>
using namespace std;

#define FOR(i,a,b)  for(int i=(a),_##i=(b);i<_##i;++i)
#define F(i,a)      FOR(i,0,a)
#define ALL(x)      x.begin(),x.end()
#define PB          push_back
#define S           size()
#define MP          make_pair
typedef long long   LL;

int N, maxlevel;
vector<bool> vis(101, false);
vector<int> Tdad(101), Tlevel(101), Tn(101, 0);
vector<vector<int> > Tcon(101);

void createTree(int node = 1, int dad = 0, int level = 0) {
	Tdad[ node ] = dad;
	Tlevel[ node ] = level;
	maxlevel = max(maxlevel, level);
	F(i, Tcon[node].S){
		if(Tcon[node][i] == dad) continue;
		createTree(Tcon[node][i], node, level+1);
	}
}

int solve(){
	int r = 0;
	while(maxlevel){
		FOR(node, 1, N+1){
			if(Tlevel[node] == maxlevel) {
				if(Tn[node]&1) r++;
				else Tn[ Tdad[node] ] += Tn[node]+1;
			}
		}
		maxlevel--;
	}
	return r;
}

int main(){
	//freopen("a.in", "r", stdin);
	int m, a, b;
	scanf("%d %d", &N, &m);
	F(i, m){
		scanf("%d %d", &a, &b);
		Tcon[a].PB(b);
		Tcon[b].PB(a);
	}
	maxlevel = 1;
	createTree();
	printf("%d\n", solve());
}
