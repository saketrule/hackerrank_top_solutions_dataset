#include <stdio.h>
#define MAXIMUM 214748364

int a[3000][3000];
int min(int s,int n)
{
	int i,min=MAXIMUM+2,res=-1;
	for(i=0;i<n;i++)
	{
		if(i!=s && min>a[s][i])
		{
			min=a[s][i];
			res=i;
		}
	}
	return res;
}
int ekfunction(int near[],int n)
{
	int i,min=MAXIMUM+2,index;
	for(i=0;i<n;i++)
	{
		if(near[i]!=-1 && min>a[i][near[i]])
		{
			min=a[i][near[i]];
			index=i;
		}
	}
	return index;
}	
void prim(int n,int m,int s)
{
	int i,j,k,cur,temp;
	long long int mincost=0;
	int near[n];
	cur=min(s,n);
	mincost+=a[s][cur];
	for(i=0;i<n;i++)
	{
		if(a[s][i]<a[cur][i])
		near[i]=s;
		else
		near[i]=cur;
	}
	near[s]=-1;
	near[cur]=-1;
	for(i=1;i<n-1;i++)
	{
		j=ekfunction(near,n);
		mincost=mincost+a[near[j]][j];
		near[j]=-1;
		for(k=0;k<n;k++)
		{
			if(near[k]!=-1 && a[k][near[k]]>a[k][j])
			near[k]=j;
		}
	}
	printf("%lld\n",mincost);
}
int main()
{
    int i,s,n,m,x,y,r,j;
	scanf("%d%d",&n,&m);
	for(i=0;i<n;i++)
	{
		for(j=0;j<n;j++)
		{
			if(a[i][j]==0)
			a[i][j]=MAXIMUM;
		}
	}
	for(i=0;i<m;i++)
	{
	   scanf("%d%d%d",&x,&y,&r);
	   x--;
	   y--;
	   if(a[x][y])
	   a[x][y]=a[x][y]>r?r:a[x][y];
	   else
	   a[x][y]=r;
	   a[y][x]=a[x][y];
    }
    scanf("%d",&s);
    s--;
    prim(n,m,s);
    return 0;
}