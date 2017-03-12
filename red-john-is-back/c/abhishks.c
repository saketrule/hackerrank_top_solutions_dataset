#include<stdio.h>

int main()
{
	int tc,n,a,way,i,j,tp;
	int arr[217287]={0},p[217287]={0};
	sieve(arr,p);
//	for (i=0;i<100;i++)
	//printf("%d %d\n",i,p[i]);
	scanf("%d",&tc);
	while (tc)
	{
		way=w(n,0);
		scanf("%d",&n);
		a=n/4;
		for (i=1;i<=a;i++)
		{
			for (j=1;j<=i;j++)
			{
				if (n-i*4+1>=j)
				{
					tp=w(n-i*4+1,j);
			//		printf("tp=%d ",tp);
					tp=tp*w(i-1,j-1);
					way=way+tp;
				}
			//	way=way+w(n-i*4+1,i);
		//		printf("j=%d %d ",j,way);
			}
		//	way=way+w(n-i*4+1,j);
		//	way=way+w(n-i*4+1,i);
		//	printf("%d ",way);
		}
		printf("%d\n",p[way]);
		tc--;
	}
	return 0;
}

int w(int a,int b)
{
	int min,i,max,tp;
	if (b>a-b)
	{
		min=a-b;
		max=b;
	}
	else
	{
		min=b;
		max=a-b;
	}
	tp=1;
	for (i=1+max;i<=a;i++)
	{
		tp=tp*i;
	}
	for (i=2;i<=min;i++)
	{
		tp=tp/i;
	}
	return tp;
}

sieve(int arr[217287],int p[217287])
{
	int chk=0,n=217287,i,j;
	p[0]=0;
	p[1]=0;
	for (i=2;i<n;i++)
	{
		if (arr[i]==0)
		{
			chk++;
		}
		p[i]=chk;
		for (j=i+i;j<n;j=j+i)
		{
			arr[j]=1;
		}
	//	printf("%d hi\n",i);
	}
}