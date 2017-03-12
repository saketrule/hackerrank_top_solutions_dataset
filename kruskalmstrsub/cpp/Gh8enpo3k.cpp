#include <bits/stdc++.h>
using namespace std;

int main()
{
	ios_base::sync_with_stdio(false);
	int n, m;
	cin >> n >> m;
	vector<int> g(n, 0);
	vector< vector<int> > c(n, vector<int>());
	for(int i=0; i<n; ++i) {
		g[i] = i;
		c[i].push_back(i);
	}
	using pii = pair<int,int>;
	priority_queue<pii> q;
	for(int i=0; i<m; ++i) {
		int x, y, r;
		cin >> x >> y >> r;
		--x; --y;
		q.push(pii{-r, (x<<16) + y});
	}
	int res = 0;
	while(!q.empty()) {
		pii p = q.top();
		q.pop();
		int i = p.second & 0x0000ffff;
		int j = (p.second & 0xffff0000)>>16;
		if(g[i]!=g[j]) {
			res -= p.first;
			int gi = g[i];
			int gj = g[j];
			for(int k: c[gj]) {
				c[gi].push_back(k);
				g[k] = gi;
			}
			c[gj].clear();
		}
	}
	cout << res << endl;
	return 0;
}