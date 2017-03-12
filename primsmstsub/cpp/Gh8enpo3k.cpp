#include <bits/stdc++.h>
using namespace std;

int main()
{
	ios_base::sync_with_stdio(false);
	int n, m;
	cin >> n >> m;
	using pii = pair<int,int>;
	vector< vector<pii> > adj(n, vector<pii>());
	for(int i=0; i<m; ++i) {
		int x, y, r;
		cin >> x >> y >> r;
		--x; --y;
		adj[x].push_back(pii{y,r});
		adj[y].push_back(pii{x,r});
	}
	int s;
	cin >> s;
	--s;
	vector<int> v(n, 0);
	priority_queue<pii> q;
	q.push(pii{0,s});
	int res = 0;
	while(!q.empty()) {
		pii p = q.top();
		q.pop();
		int i = p.second;
		if(v[i])
			continue;
		v[i] = 1;
		res -= p.first;
		for(auto& a: adj[i]) if(!v[a.first]) {
			q.push(pii{-a.second, a.first});
		}
		if(q.empty()) {
			for(int j=0; j<n; ++j) {
				if(!v[j]) {
					q.push(pii{0,j});
					break;
				}
			}
		}
	}
	cout << res << endl;
	return 0;
}