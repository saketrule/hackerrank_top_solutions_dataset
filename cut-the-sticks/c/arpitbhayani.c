#include <stdio.h>
#include <stdlib.h>

#define DEBUG 1

int readline ( char * str ) {

	int i = 0;
	char ch;
	while ( (ch=getchar()) != '\n' ) {
		str[i++] = ch;
	}
	str[i] = '\0';
	return i;
}

int compar ( const void * a , const void * b ) {
	return (*(int *)a) - (*(int *)b);
}

int main ( int argc , char * argv[] ) {

	int t , z;
	scanf("%d" , &t);

	int array[1001];

	for ( z = 0 ; z < t ; z++ ) {
		scanf("%d" , &array[z]);
	}

	int count = t;
	qsort( array , t , sizeof(int) , compar );
	int index = 0 , i;

	printf("%d\n" , count);
	while ( 1 ) {
		int smallest = array[index];
		for ( i = index; i < t-1 ; i++ ) {
			if ( array[i] != array[i+1] )
				break;
		}
		index = i+1;
		if ( t-index == 0 )
			break;
		printf("%d\n" , (t-index));
	}

	return 0;
}
