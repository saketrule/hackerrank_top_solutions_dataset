//////////////		MJ		////////////
#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#define LIMIT 10000000 	
int main(int argc, char *argv[])
{
	unsigned long long int i,j;
    int *sieve;
    int n,count,t;
    sieve = (int *)malloc(sizeof(int)*LIMIT);
    int *array=(int *)malloc(sizeof(int)*50);
	for(i=0;i<LIMIT;i++)
		sieve[i]=1;
	sieve[0]=0;
	sieve[1]=0;
	sieve[2]=1;
 	for (i=2;i<LIMIT;i++)
        if (sieve[i])
            for(j=i;i*j<LIMIT;j++)
                sieve[i*j]=0;
	
	array[1]=1;
	array[2]=1;
	array[3]=1;
	array[4]=2;
	array[5]=3;
	array[6]=4;
	array[7]=5;
	array[8]=7;
	for(i=9;i<=41;i++)
	{
		array[i]=array[i-1]+array[i-4];
	}
	scanf("%d",&t);
	while(t--)
	{
		scanf("%d",&n);
		count=0;
			for(i=0;i<=array[n];i++)
				if(sieve[i])
					count++;
		printf("%d\n",count);
	}
	return 0;
}