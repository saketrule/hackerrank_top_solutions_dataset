#include "stdio.h"
#include "memory.h"

int iValues[ 1000 ];

int main( int argc, char ** argv )
{
	int n, i, x;

	memset( &iValues, 0, 1000 * sizeof( int ));

	scanf( "%d\n", &n );
	for( i = 0; i < n; i++ )
	{
		scanf( "%d", &x );
		iValues[ x ]++;
	}

	for( i = 0; i < 1000; i++ )
	{
		if( iValues[ i ])
		{
			printf( "%d\n", n );
			n -= iValues[ i ];
		}
	}

	return 0;
}
