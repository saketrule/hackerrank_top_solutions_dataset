#include "bits/stdc++.h"

typedef long long ll;
typedef unsigned long long ull;
typedef double dbl;

using namespace std;

int T;
int n;
int mas[100005];
ll sum[100005];

void load () {
	cin >> n;
	for (int i = 1; i <= n; i++) {
		cin >> mas[i];
		sum[i] = sum[i - 1] + mas[i];
	}
}

ll get (int l, int r) {
	return sum[r] - sum[l - 1];
}

int bs (int l, int r) {
	int L = 1;
	int R = r - l;
	int ans = 1;
	while (L <= R) {
		int m = L + R;
		m >>= 1;
		ll s1 = get (l, l + m - 1);
		ll s2 = get (l + m, r);
		if (s1 <= s2) {
			ans = m;
			L = m + 1;
		}
		else {
			R = m - 1;
		}
	}
	return ans;
}

int solve (int l, int r) {
	if (r - l + 1 <= 1) {
		return 0;
	}
	int cnt = bs (l, r);
	ll s1 = get (l, l + cnt - 1);
	ll s2 = get (l + cnt, r);
	if (s1 != s2)
		return 0;
	return max (solve (l, l + cnt - 1), solve (l + cnt, r)) + 1;
}

#ifdef LOCAL
#include "debug.h"
#define eprintf(...) fprintf(stderr, __VA_ARGS__)
#else
#define Print(...) (void)42;
#define eprintf(...) (void)42;
#endif

int main () {
#ifdef LOCAL
	freopen ("file.in", "r", stdin);
	freopen ("file.out", "w", stdout);
#endif 

#ifdef TIMER
	timer Tm;
	Tm.start ();
#endif

	ios_base :: sync_with_stdio (false);

	cin >> T;
	while (T --> 0) {
		load ();
		cout << solve (1, n) << endl;
	}

#ifdef TIMER
	Tm.stop ();
	Tm.print_time (stderr);
#endif

	return 0;
}