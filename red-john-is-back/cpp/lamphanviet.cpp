//============================================================================
// Author       : LAM PHAN VIET - lamphanviet@gmail.com
// Problem Name : 
// Time Limit   : .000s
// Description  : 
//============================================================================
#include <algorithm>
#include <bitset>
#include <cctype>
#include <cmath>
#include <cstdio>
#include <cstring>
#include <iostream>
#include <map>
#include <queue>
#include <set>
#include <stack>
#include <string>
#include <vector>

using namespace std;

#define fr(i,a,b) for (int i = (a), _b = (b); i <= _b; i++)
#define frr(i,a,b) for (int i = (a), _b = (b); i >= _b; i--)
#define rep(i,n) for (int i = 0, _n = (n); i < _n; i++)
#define repr(i,n) for (int i = (n) - 1; i >= 0; i--)
#define foreach(it, ar) for ( typeof(ar.begin()) it = ar.begin(); it != ar.end(); it++ )
#define fill(ar, val) memset(ar, val, sizeof(ar))

#define uint64 unsigned long long
#define int64 long long
#define all(ar) ar.begin(), ar.end()
#define pb push_back
#define mp make_pair
#define ff first
#define ss second

#define BIT(n) (1<<(n))
#define sqr(x) ((x) * (x))

typedef pair<int, int> ii;
typedef pair<int, ii> iii;
typedef vector<ii> vii;
typedef vector<int> vi;

#define PI 3.1415926535897932385
#define INF 1000111222
#define eps 1e-7
#define maxN 42
#define maxM 300000

int n, f[maxN], res[maxN];
bool isPrime[maxM];

void sieve() {
	fill(isPrime, true);
	isPrime[0] = isPrime[1] = false;
	for (int i = 4; i < maxM; i += 2) isPrime[i] = false;
	for (int i = 3; i * i < maxM; i += 2)
		if (isPrime[i])
			for (int j = i * i; j < maxM; j += i + i)
				isPrime[j] = false;
}

int main() {
	f[0] = 1;
	fr(i, 1, maxN - 1) {
		f[i] = f[i - 1];
		if (i - 4 >= 0) f[i] += f[i - 4];
	}
	sieve();
	res[0] = 0;
	for (int i = 1, j = 1; i < maxN; i++) {
		res[i] = res[i - 1];
		while (j <= f[i]) {
			if (isPrime[j]) res[i]++;
			j++;
		}
	}
	int cases;
	for (scanf("%d", &cases); cases--; ) {
		scanf("%d", &n);
		printf("%d\n", res[n]);
	}
	return 0;
}
