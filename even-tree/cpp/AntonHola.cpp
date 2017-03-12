
#include <cstring>
#include <string>
#include <vector>
#include <map>
#include <algorithm>
#include <utility>
#include <math.h>

using namespace std;

int n, m;
vector<int> G[ 110 ];
int weight[ 110 ] = {0};

void dfs1( int node, int from=-1 )
{
	weight[ node ] = 1;
	for ( int i = 0; i < G[ node ].size(); i++ )
	{
		int to = G[ node ][ i ];
		if ( to != from )
		{
			dfs1( to, node );
			weight[ node ] += weight[ to ];
		}
	}
}

int dfs2( int node, int from=-1 )
{
	int res = ( from!=-1 && weight[ node ]%2==0 );
	for ( int i = 0; i < G[ node ].size(); i++ )
	{
		int to = G[ node ][ i ];
		if ( from != to )
		{
			res += dfs2( to, node );
		}
	}
	return res;
}

int main()
{
	scanf( "%d%d", &n, &m );
	int root, mmax=0;
	for ( int i = 0; i < m; i++ )
	{
		int a, b; scanf( "%d%d", &a, &b );
		a--, b--;
		G[ a ].push_back( b );
		G[ b ].push_back( a );
		if ( G[ a ].size() > mmax )
		{
			root = a; mmax = G[ a ].size();
		}
		if ( G[ b ].size() > mmax )
		{
			root = b; mmax = G[ b ].size();
		}
	}	
	dfs1( root );
	printf( "%d", dfs2( root ) );
	
	return 0;
}	
