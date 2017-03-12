#include <cstdio>
#include <vector>
#include <cstring>
#include <algorithm>
using namespace std;

#define REP(i,n) for(int i=0,_n=(n);i<_n;++i)
#define FOR(i,a,b) for(int i=(a),_b=(b);i<=_b;++i)
#define FORD(i,a,b) for(int i=(a),_b=(b);i>=_b;--i)
#define FOREACH(it,arr) for (__typeof((arr).begin()) it=(arr).begin(); it!=(arr).end(); it++)

int dp[50];
int f(int x) {
	if ( x == 0 ) return 1;
	if ( dp[x] != -1 ) return dp[x];
	int &ret = dp[x] = 0;
	if ( x - 1 >= 0 ) ret += f(x-1);
	if ( x - 4 >= 0 ) ret += f(x-4);
	return ret;
}

bool isp[300005];
vector <int> p;

int main()
{
	memset(dp,-1,sizeof(dp));

	memset(isp,true,sizeof(isp));
	isp[0] = isp[1] = false;
	for ( int i = 2; i * i <= 300000; i++ ) if ( isp[i] )
		for ( int j = i * i; j <= 300000; j += i ) isp[j] = false;
	for ( int i = 2; i <= 300000; i++ ) if ( isp[i] )
		p.push_back(i);

	int T;
	scanf( "%d", &T );
	while ( T-- ) {
		int n;
		scanf( "%d", &n );
		printf( "%d\n", upper_bound(p.begin(),p.end(),f(n)) - p.begin() );
	}

	return 0;
}
