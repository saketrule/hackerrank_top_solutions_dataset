#include <bits/stdc++.h>
using namespace std;

vector<int> pset, setrank;
int findset(int n){
	return (pset[n] == n) ? n : (pset[n] = findset(pset[n]));
}
void unionset(int a, int b){
	int x = findset(a), y = findset(b);
	if (setrank[x] > setrank[y])
		pset[y] = x;
	else{
		pset[x] = y;
		if (setrank[x] == setrank[y])
			++setrank[y];
	}
}

int main(){
	cin.sync_with_stdio(0); cin.tie(0);
	int n, m; cin >> n >> m;
	pset.assign(n+1,0);
	for (int i = 1; i <= n; ++i)
		pset[i] = i;
	setrank.assign(n+1,1);
	vector<pair<int,pair<int,int>>> adj (m);
	for (int i = 0; i < m; ++i)
		cin >> adj[i].second.first >> adj[i].second.second >> adj[i].first;
	sort(adj.begin(),adj.end());
	long long ans = 0;
	for (int i = 0; i < m; ++i){
		int a = adj[i].second.first, b = adj[i].second.second, r = adj[i].first;
		if (findset(a) != findset(b)){
			unionset(a,b);
			ans += r;
		}
	}
	cout << ans << '\n';
return 0;}
