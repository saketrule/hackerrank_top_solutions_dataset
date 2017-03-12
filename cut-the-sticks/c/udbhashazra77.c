#include<stdio.h>
#include<stdlib.h>
int cmpfunc (const void * a, const void * b)
{
   return ( *(int*)a - *(int*)b );
}
main()
{
	int n,i=0,c=0,j,min,d;
	scanf("%d",&n);
	int a[n];
	for(i=0;i<n;i++) scanf("%d",&a[i]);
	qsort(a, n, sizeof(int), cmpfunc);
	i=0;
	while(a[n-1]!=0)
	{
		min=a[i];
		c=i;
		d=0;
		while(min==a[c])
		{
			a[c]=0;
			d++;
			c++;
		}
	//	printf("c=%d\n",c);
		for(j=c;j<n;j++) 
		{
			a[j]-=min;
			d++;
		}
		printf("%d\n",d);
		i=c;
	}
	return 0;
}