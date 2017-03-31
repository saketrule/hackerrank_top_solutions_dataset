#include <iostream>
#include <cstring>
#include <cstdio>
#include <cmath>
#include <algorithm>
#include <set>
#include <map>
#include <queue>
#include <vector>
#include <cstdlib>
#include <fstream>
#include <sstream>
#include <deque>
#include <cassert>

using namespace std;

#ifdef WIN32
	#define I64 "%I64d"
#else
	#define I64 "%lld"
#endif

typedef long long ll;

#define F first
#define S second
#define mp make_pair
#define pb push_back
#define all(s) s.begin(), s.end()
#define sz(s) (int(s.size()))
#define fname "a"
#define ms(a,x) memset(a, x, sizeof(a))
#define forit(it,s) for(__typeof(s.begin()) it = s.begin(); it != s.end(); ++it)
#define MAXN 1000001

int n, p, q;
int a[MAXN];
int ans;
int best;

inline void check(int x)
{
	if (x < p || x > q) return;
	int r = abs(a[0] - x);
	for (int i = 1; i < n; ++i)
		r = min(r, abs(a[i] - x));
	if (best < r || (r == best && ans > x))
	{
		best = r;
		ans = x;
	}
}

int main()
{
	#ifdef LOCAL
	freopen(fname".in", "r", stdin);
	freopen(fname".out", "w", stdout);
	#endif

	scanf("%d", &n);
	for (int i = 0; i < n; ++i)
		scanf("%d", &a[i]);
	scanf("%d%d", &p, &q);
	sort(a, a + n);
	best = -1;
	check(p);
	check(q);
	for (int i = 0; i < n - 1; ++i)
	{
		check((a[i] + a[i + 1]) / 2);
		check((a[i] + a[i + 1]) / 2 + 1);
	}
	printf("%d\n", ans);
	return 0;
}
