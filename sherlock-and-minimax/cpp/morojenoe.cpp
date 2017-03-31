#include <iostream>
#include <cstdlib>
#include <cmath>
#include <cstdio>
#include <vector>
#include <memory.h>
#include <map>
#include <set>
#include <bitset>
#include <algorithm>
#include <cmath>
#include <stack>
#include <string>
#include <cstring>
#include <string.h>
#include <sstream>
#include <cmath>
#include <math.h>
#include <queue>
#include <deque>
#include <cassert>
#include <time.h>

#define forn(i,n) for (int i = 0; i < (int)n; i++)
#define fornd(i, n) for (int i = (int)n - 1; i >= 0; i--)
#define forab(i,a,b) for (int i = (int)a; i <= (int)b; i++)
#define forabd(i, b, a) for (int i = (int)(b); i >= (int)(a); i--)
#define forit(i, a) for (__typeof((a).begin()) i = (a).begin(); i != (a).end(); i++)
#define _(a, val) memset (a, val, sizeof (a))
#define sz(a) (int)(a).size()
#define pb push_back
#define mp make_pair
#define all(v) (v).begin(), (v).end()

#ifdef _DEBUG
#define dbg(...) {fprintf(stderr, __VA_ARGS__); fflush(stderr);}
#define dbgx(x) {cerr << #x << " = " << x << endl;}
#define dbgv(v) {cerr << #v << " = {"; for (typeof(v.begin()) it = v.begin(); it != v.end(); it ++) cerr << *it << ", "; cerr << "}"; cerr << endl;}
#else
#define dbg(...) { }
#define dbgx(x) { }
#define dbgv(v) { }
#endif

typedef long long lint;
typedef unsigned long long ull;
typedef long double ld;

const lint LINF = 1000000000000000000LL;
const int INF = 1000000000;
const long double eps = 1e-9;
const long double PI = 3.1415926535897932384626433832795l;

using namespace std;

void prepare (string s)
{
#ifdef _DEBUG
	freopen ("input.txt", "r", stdin);
	//freopen ("output.txt", "w", stdout);
#else
	if (s.length() != 0)
	{
		freopen ((s + ".in").c_str(), "r", stdin);
		freopen ((s + ".out").c_str(), "w", stdout);
	}
#endif
}

const int NMAX = 155;
int n;
lint a[NMAX];
int p, q;

void read()
{
	scanf("%d", &n);
	forn(i, n)
		scanf("%lld", &a[i]);
	scanf("%d %d", &p, &q);
}

void upd(lint &ans, lint &res, lint prv, lint cur, lint val)
{
	if (ans < min(val - prv, cur - val))
	{
		ans = min(val - prv, cur - val);
		res = val;
	}
	else
	{
		if (ans == min(val - prv, cur - val))
			res = min(res, val);
	}
}

void solve()
{
	a[n] = -LINF;
	a[n + 1] = LINF;
	n += 2;
	sort(a, a + n);
	lint ans = 0, res = LINF;
	for(int i = 1; i <= n; i++)
	{
		lint cur = a[i];
		lint prv = a[i - 1];
		lint mid = (cur + prv) >> 1;
		if (prv > q || p > cur) continue;
		if (prv <= p && p <= cur)
			upd(ans, res, prv, cur, p);
		if (prv <= q && q <= cur)
			upd(ans, res, prv, cur, q);
		for(lint j = mid - 5; j <= mid + 5; j++)
		{
			if (p <= j && j <= q && prv <= j && j <= cur)
				upd(ans, res, prv, cur, j);
		}
	}
	
	printf("%lld\n", res);
}

int main ()
{
	prepare ("");

	int t;
	//scanf("%d", &t);
	t = 1;
	forn(i, t)
	{
		read();
		solve();
	}

	return 0;
}
