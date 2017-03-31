#include <cstdio>
#include <cstdlib>
#include <cstring>
#include <algorithm>
#include <vector>
#include <string>
#include <iostream>
#include <queue>
#include <set>
#include <map>
#include <cmath>
using namespace std;

typedef long long llint;
const int INF = 0x3f3f3f3f;
const int MOD = 1000000007;

#define pii pair< int, int >
#define piiLL pair< llint, llint >
#define mp make_pair
#define pb push_back
#define lu lower_bound
#define uu upper_bound
#define all( a ) ( a ).begin( ), ( a ).end( )
#define GC getchar( )
#define x first
#define y second

const int N = 105;

int n;
int p;
int q;

int A[ N ];

vector< int > V;

int main( void ) {
    scanf( "%d", &n );
    for( int i = 0; i < n; i++ ) scanf( "%d", &A[ i ] ), V.pb( A[ i ] );
    sort( A, A + n );
    scanf( "%d %d", &p, &q );
    V.pb( p ), V.pb( q );
    for( int i = 0; i < n - 1; i++ ) {
        V.pb( ( A[ i ] + A[ i + 1 ] ) / 2 );
        V.pb( ( A[ i ] + A[ i + 1 ] ) / 2 + 1 );
    }
    sort( all( V ) );
    int ans = 0;
    int idx = -1;
    for( int i = 0; i < V.size( ); i++ ) {
        int x = V[ i ];
        int mini = INF;
        for( int j = 0; j < n; j++ ) mini = min( mini, abs( x - A[ j ] ) );
        if( x >= p && x <= q && mini > ans ) ans = mini, idx = x;
    }
    printf( "%d\n", idx );
    //system( "Pause" );
    return 0;
}



    
        
    
    
        
        


