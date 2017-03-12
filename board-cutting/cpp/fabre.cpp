#include<stdio.h>
#include<algorithm>
#define MAX 100000
#define MOD 1000000007

using namespace std;
long long int cmp(long long int a,long long int b)
{
  return (a>b);
}

int main()
{
  int t;
  scanf("%d",&t);
  while(t--)
    {
      long long int i,m,n,j=0,k=0,x=1,y=1;
      scanf("%lld %lld",&m,&n);
      long long int X[MAX]={0},Y[MAX]={0};
      for(i=1;i<m;i++)
	scanf("%lld",&Y[i]);
      for(i=1;i<n;i++)
	scanf("%lld",&X[i]);
      sort(Y,Y+m,cmp);
      sort(X,X+n,cmp);
      long long int cost=0;
      for(i=0;i<m+n-2;i++)
	{
	  if(Y[j] > X[k])
	    {
	      cost=(cost + (Y[j]*y))% MOD;
	      x++;
	      j++;
	    }
	  else
	    {
	      cost=(cost + (X[k]*x))% MOD;
	      y++;
	      k++;
	    }
	}
      printf("%lld\n",cost);
    }
  return 0;
}   
