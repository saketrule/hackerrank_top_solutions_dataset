#include <bits/stdc++.h>
using namespace std;

typedef pair<int, int> ii;
typedef vector<ii> vii;
typedef vector<int> vi;

#define FOR(i,s,e) for (int i = int(s); i < int(e); i++)
#define FORIT(i,c) for (typeof((c).begin()) i = (c).begin(); i != (c).end(); i++)
#define sz(v) (int)v.size()
#define all(c) (c).begin(), (c).end()

typedef long long int ll;

// %I64d for ll in CF

#define MOD 1000000007

int main() {
	int t;
	scanf("%d", &t);
	while (t--) {
		int m, n;
		scanf("%d %d", &m, &n);

		priority_queue<pair<int, int> > pq;

		int ys[m - 1];
		int xs[n - 1];
		int cost = 0;
		for (int i = 0; i < m - 1; i++) {
			scanf("%d", &ys[i]);
			pq.push(make_pair(ys[i], 0));
		}

		for (int j = 0; j < n - 1; j++) {
			scanf("%d", &xs[j]);
			pq.push(make_pair(xs[j], 1));
		}

		int horizontal_segments = 1;
		int vertical_segments = 1;
		while (!pq.empty()) {
			pair<int, int> v = pq.top();
			pq.pop();

			ll d = v.first;
			if (v.second == 0) {
				d = d * vertical_segments;
				horizontal_segments++;
			}
			else {
				d = d * horizontal_segments;
				vertical_segments++;
			}
			cost = (cost + d) % MOD;
		}

		printf("%d\n", cost);
	}
	return 0;
}
