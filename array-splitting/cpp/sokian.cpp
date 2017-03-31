#include <bits/stdc++.h>

#define ford(i, n) for(int i = (int)(n) - 1; i >= 0; i--)
#define forn(i, n) for(int i = 0; i < (int)(n); i++)
#define for1(i, n) for(int i = 1; i <= (int)(n); i++)
#define all(x) (x).begin(), (x).end()
#define pb push_back
#define mp make_pair
#define prev asdfsdf
#define x first
#define y second

using namespace std;
typedef long double ld;
typedef long long ll;
typedef pair<int, int> PII;
typedef pair<int, int> pii;
typedef vector<int> vi;
typedef long long i64;

int nxt() {
    int x;
    scanf("%d", &x);
    return x;
}

ll gcd(ll a, ll b) {
    while (b) {
        a %= b;
        swap(a, b);
    }
    return a;
}
struct pt {
    ll x, y;
    pt() {}
    pt(ll x, ll y) : x(x), y(y) {}

    pt operator - (const pt &r) const {
        return pt(x - r.x, y - r.y);
    }

    bool operator < (const pt &r) const {
        if (x != r.x) return x < r.x;
        return y < r.y;
    }
    bool operator == (const pt &r) const {
        return x == r.x && y == r.y;
    }
};

ll cross(const pt &l, const pt &r) {
    return l.x * r.y - l.y * r.x;
}

ll dot(const pt &l, const pt &r) {
    return l.x * r.x + l.y * r.y;
}

ll pwmod(ll a, ll n, ll mod) {
    ll ret = 1;
    while (n) {
        if (n & 1) ret = ret * a % mod;
        a = a * a % mod;
        n >>= 1;
    }
    return ret;
}

const int N = 1 << 15;
ll a[N];
ll s[N];

int solve(int l, int r) {
    if (l + 1 == r) return 0;
    ll sum = s[r] - s[l];
    if (sum % 2 == 1) return 0;
    ll s2 = sum >> 1;
    for (int i = l; i < r; ++i) {
        if (s[i + 1] - s[l] == s2) {
            return max(1 + solve(l, i + 1), 1 + solve(i + 1, r));
        }
    }
    return 0;
}

void solve() {
    int n = nxt();
    forn(i, n) a[i] = nxt();
    s[0] = 0;
    forn(i, n) {
        s[i + 1] = s[i] + a[i];
    }
    if (s[n] == 0) {
        cout << (n - 1) << "\n";
        return;
    }
    int ans = solve(0, n);
    cout << ans << "\n";
}

int main()
{
#ifdef LOCAL
    freopen("input.txt", "r", stdin);
#endif
    ios_base::sync_with_stdio(false);
    cin.tie(0);

    int t = nxt();
    forn(i, t) {
        solve();
    }

    cerr << "Time " << clock() / (double)CLOCKS_PER_SEC << endl;
    return 0;
}
