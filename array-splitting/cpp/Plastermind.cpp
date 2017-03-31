#include<iostream>
#include<cstdio>

using namespace std;

long long n, t, tab[100010], sum[100010];

int licz( int pocz, int kon )
{
	long long s = sum[kon] - sum[pocz-1];
	long long r = 0;
	if( pocz == kon )return 0;
	for( int a = pocz; a < kon; a++ )
	{
		r += tab[a];
		if( 2 * r == s )
		{
			return max( licz( pocz, a ), licz( a + 1, kon ) ) + 1;
		}
	}
	return 0;
}
int main()
{
	ios_base::sync_with_stdio( 0 );
	cin>>t;
	while( t-- )
	{
		cin>>n;
		for( int a = 0; a <= n; a++ )sum[a] = 0;
		for( int a = 1; a <= n; a++ )cin>>tab[a], sum[a] = sum[a-1] + tab[a];
		cout<<licz( 1, n )<<endl; 
	}
	return 0;
}