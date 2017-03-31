#pragma comment(linker, "/STACK:16777216")

#include<stdio.h>
#include<string.h>
#include<math.h>
#include<stdlib.h>
#include<ctype.h>
#include<assert.h>
#include<iostream>
#include<vector>
#include<stack>
#include<queue>
#include<set>
#include<map>
#include<string>
#include<utility>
#include<algorithm>
#include<list>
using namespace std;

#define CLR(a) memset(a,0,sizeof(a))
#define SET(a) memset(a,-1,sizeof(a))
#define pb push_back
#define SZ(a) ((Long)a.size())
#define ALL(a) a.begin(),a.end()
#define FOREACH(i, c) for( __typeof( (c).begin() ) i = (c).begin(); i != (c).end(); ++i )
#define AREA2(x1,y1,x2,y2,x3,y3) ( x1*(y2-y3) + x2*(y3-y1) + x3*(y1-y2) )
#define SQR(x) ((x)*(x))
#define STR string
#define IT iterator
#define ff first
#define ss second
#define MP make_pair
#define EPS 1e-9
#define INF 1000000007

#define chk(a,k) ((bool)(a&(1<<(k))))
#define set0(a,k) (a&(~(1<<(k))))
#define set1(a,k) (a|(1<<(k)))

typedef long long Long;
typedef vector<long> Vl;
typedef vector<Long> VL;
typedef pair<long,long> Pll;
typedef pair<Long,Long> PLL;
typedef long long Long;

#define MAX 100007

long N;
long A[MAX+7];
long P,Q;

int main( void )
{
    long i,j,Icase,k = 0;

    //freopen("text1.txt","r",stdin );

    scanf("%ld",&N );
    vector<long> vc;
    for( i=1;i<=N;i++ ){
        scanf("%ld",&A[i] );
        vc.pb( A[i] );
    }
    scanf("%ld%ld",&P,&Q );
    if( P>Q ) swap( P,Q );
    vc.pb( P );
    vc.pb( P+1 );
    vc.pb( Q );
    vc.pb( Q-1 );
    sort( A+1,A+N+1 );
    for( i=1;i<N;i++ ){
        long t = ( A[i]+A[i+1] )/2;
        vc.pb( t );
        vc.pb( t+1 );
        vc.pb( t-1 );
    }
    sort( ALL( vc ) );
    vector<long> tmp = vc;
    vc.clear();
    for( i=0;i<tmp.size();i++ ){
        if( tmp[i]<P or tmp[i]>Q ) continue;
        vc.pb( tmp[i] );
    }
    long I = 0,mx = -1;
    for( i=0;i<vc.size();i++ ){
        long m = INF;
        for( j=1;j<=N;j++ ){
            m = min( m,labs( A[j]-vc[i] ) );
        }
        if( m > mx ){
            mx = m;
            I = i;
        }
    }
    printf("%ld\n",vc[I] );

    return 0;
}
