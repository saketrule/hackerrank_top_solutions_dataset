#include<stdio.h>
#define temp -1
#define perm -2
int n;
int a[3002][3002];
struct node{
	int u;
	int v;
};
int main()
{
	int i,j,m,c,d,p,s,e,q;
	long long int sum;
	sum=0;
	q=0;
	scanf("%d",&n);
	struct node tree[n];
//	printf("2");
//	static int a[n][n];
	for(i=0;i<n;i++)
	for(j=0;j<n;j++)
	a[i][j]=-1;
	//printf("2");
	scanf("%d",&m);
	for(i=0;i<m;i++)
	{
		scanf("%d%d%d",&c,&d,&e);
		if(a[c-1][d-1]!=-1)
	 	{
		 p=a[c-1][d-1];
		 if(p<e)
		 e=p;
	    }
        a[c-1][d-1]=e;
        a[d-1][c-1]=e;
	}
//	for(i=0;i<n;i++)
//	{
//		for(j=0;j<n;j++)
//		printf("%d ",a[i][j]);
//		printf("\n");
//	}
	scanf("%d",&s);
	s=s-1;
	prim(s,tree);
  //  for(i=0;i<n-1;i++)
    //printf("%d->%d  ",tree[i].u,tree[i].v);
	for(i=0;i<n-1;i++)
	sum=sum+a[tree[i].u][tree[i].v];
	printf("%lld",sum);
	
}
prim(int s,struct node tree[])
{   int min,k,i,q;
    q=0;
	int pred[n];
	int dist[n];
	int state[n];
	for(i=0;i<n;i++)
	{
	dist[i]=99999;
	state[i]=temp;
	pred[i]=-1;
    }
	state[s]=perm;
	pred[s]=-1;
	dist[s]=0;
//	printf("%d",s);
	while(1)
	{ //printf("2");
		for(i=0;i<n;i++)
		{
			if(a[s][i]!=-1&&state[i]==temp)
			{
				if(dist[i]>a[s][i])
				{
				dist[i]=a[s][i];
			//	printf("%d",dist[i]);
				pred[i]=s;
				}
				
			}
		}
		k=0;
		min=999999;
		for(i=0;i<n;i++)
		{
			if(state[i]==temp)
			{
				if(dist[i]<min)
				{
					min=dist[i];
					s=i;
				}
			}
			else
			k++;
		}
		if(k==n)
		break;
		state[s]=perm;
		tree[q].u=pred[s];
		tree[q].v=s;
		q++;
	}
	return 0;
}