#include<stdio.h>
#include<math.h>
int countprime(int a,int k)
{
int i;
for(i=a;i>=2;i--)
{
if(k%i==0)
return 0;
}
return 1;
}


int checkprime(int n)
{
	int i,count=1;
	int x;
	if(n==1)
	return 0;
for(i=3;i<=n;i++)
{
x=sqrt(i);
count+=countprime(floor(x),i);
}
return count;
}

int noways(int n)
{
	if(n==0) return 1;
	if(n<0) return 0;
	
	return noways(n-4)+noways(n-1);
}
int main()
{
	int T,N,i;
	int result;
	int *a;
	scanf("%d",&T);
	a=(int *)malloc(T*sizeof(int *));
	for(i=0;i<T;i++)
	{
		scanf("%d",&a[i]);
	}
	for(i=0;i<T;i++)
	{
		printf("%d\n",checkprime(noways(a[i])));	
	}
	return 0;	
}
