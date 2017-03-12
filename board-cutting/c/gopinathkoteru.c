#include<stdio.h>

int compare (const void *a, const void *b)
{
	    const int *ia = (const int *)a; // casting pointer types 
	    const int *ib = (const int *)b;
	    return *ia  - *ib; 
}
int main()
{
	int t,m,n;
	int x[1000000],y[1000000],i,j;
	long long int count,xcount,ycount;
	scanf("%d",&t);
	while(t--)
	{
		count=0;
		xcount=1;
		ycount=1;
		
		scanf("%d%d",&m,&n);
		for(i=0;i<m-1;i++)
		{
			scanf("%d",&x[i]);
		}
		for(i=0;i<n-1;i++)
		{
			scanf("%d",&y[i]);
		}
		qsort (x, m-1, sizeof(int), compare);
		qsort (y, n-1, sizeof(int), compare);

		i=m-2;
		j=n-2;
		while(i!=-1 && j!=-1)
		{
			if(x[i] > y[j])
			{
				count=(count+(ycount*x[i])%1000000007)%1000000007;
				xcount=xcount+1;
				i=i-1;
			}
			else if(x[i] < y[j])
			{
				count=(count+(xcount*y[j])%1000000007)%1000000007;
				ycount=ycount+1;
				j=j-1;
			}
			else if(xcount > ycount)
			{
				count=(count+(xcount*y[j])%1000000007)%1000000007;
				ycount=ycount+1;
				j=j-1;
			}
			else
			{
				count=(count+(ycount*x[i])%1000000007)%1000000007;
				xcount=xcount+1;
				i=i-1;
			}
		}
		while(i!=-1)
		{
			count=(count+(ycount*x[i])%1000000007)%1000000007;
			xcount=xcount+1;
			i=i-1;
		}
		while(j!=-1)
		{
			count=(count+(xcount*y[j])%1000000007)%1000000007;
			ycount=ycount+1;
			j=j-1;
		}
		printf("%lld\n",count);
	}
	return 0;
}
