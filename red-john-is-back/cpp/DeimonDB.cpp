#include <cstdio>
#include <cstring>
#include <cmath>
#include <algorithm>
#include <vector>
#include <queue>
#include <stack>
#include <list>
#include <map>
#include <set>
#include <bitset>
#include <string>
#include <iostream>
#include <cassert>
using namespace std;
typedef long long ll;
const double PI = acos(-1);
const double EPS = 1e-7;

#define PB push_back
#define MP make_pair
#define FOR(_i, _from, _to) for (int (_i) = (_from), (_batas) = (_to); (_i) <= (_batas); (_i)++)
#define REP(_i, _n) for (int (_i) = 0, (_batas) = (_n); (_i) < (_batas); (_i)++)
#define SZ(_x) ((int)(_x).size())

const int MAXPRIME = 217286;
ll memo[45];
vector<int> primes;
bool isPrime[MAXPRIME+5];
inline void init_prime() {
	FOR(i, 0, MAXPRIME) isPrime[i] = true;
	isPrime[0] = isPrime[1] = false;
	FOR(i, 2, sqrt(MAXPRIME)) {
		if (isPrime[i])
			for (int j = i*i; j <= MAXPRIME; j += i) isPrime[j] = false;
	}
	FOR(i, 2, MAXPRIME) if (isPrime[i]) primes.PB(i);
}

inline void init_memo() {
	REP(i, 4) memo[i] = 1;
	FOR(i, 4, 40) {
		memo[i] = memo[i-1] + memo[i-4];
		//printf("memo%d = %lld\n", i, memo[i]);
	}
}
inline void solve() {
	int N;
	scanf("%d", &N);
	int ans = upper_bound(primes.begin(), primes.end(), memo[N]) - primes.begin();
	printf("%d\n", ans);
}

int main() {
	init_memo();
	init_prime();
	int T;
	scanf("%d", &T);
	while(T--) solve();
	return 0;
}
