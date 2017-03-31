#include <iostream>
#include <iomanip>
#include <sstream>
#include <fstream>
#include <cstdio>
#include <algorithm>
#include <deque>
#include <vector>
#include <map>
#include <cmath>
#include <cstdlib>
#include <set>
#include <queue>
#include <stack>
#include <string>
#include <cstring>
#include <climits>
#include <cctype>
#include <utility>
#include <cassert>
#include <ctime>
using namespace std;

#define ft first
#define sd second
#define pb push_back
#define endl '\n'
#define buli(x) __builtin_popcountll(x)
#define cpy(a,e) memcpy(a,e,sizeof(e))
#define clr(a,e) memset(a,e,sizeof(a))
#define iter(c) __typeof((c).begin())
#define tr(c,i) for (iter(c) i=(c).begin();i!=(c).end();i++)
#define eprintf(...) fprintf(stderr, __VA_ARGS__),fflush(stderr)
#define rep(i,n) for (int (i)=0;(i)<(n);i++)
#define repd(i,n) for (int (i)=(n)-1;(i)>=0;i--)
#define reps(i,s,e) for (int (i)=(s);(i)<=(e);i++)
#define repds(i,s,e) for (int (i)=(s);(i)>=(e);i--)
#define repl(i,s,e) for (int (i)=(s);(i);i=e[i])

#define TASK "B"

int t;
int n;
long long a[1 << 15], d[1 << 15];
          
int rec(int l, int r) {
	if (l == r) return 0;

	if ((d[r] - d[l - 1]) % 2) return 0;

	long long fin = (d[r] - d[l - 1]) / 2LL;

	int ret = 0;

 	for (int i = l; i < r; i++) {
 	      	if (d[i] - d[l - 1] == fin) {
 	      	 	ret = max(rec(l, i), rec(i + 1, r)) + 1;
			break;	
		}
	}     

	return ret;
}                                                         
int main() {
	#ifdef home
		freopen(TASK".in","r",stdin);
		freopen(TASK".out","w",stdout);
	#endif
	ios::sync_with_stdio(false);
	cin.tie(0); 

	cin >> t;

	while (t--) {
	 	cin >> n;

		clr(a, 0); clr(d, 0);
		for (int i = 1; i <= n; i++) cin >> a[i];
		for (int i = 1; i <= n; i++) d[i] = d[i - 1] + a[i];

		cout << rec(1, n) << endl;
	}
           
	#ifdef home
		eprintf("time = %d ms\n", (int)(clock() * 1000. / CLOCKS_PER_SEC));
	#endif                                                                          
	return 0;
}