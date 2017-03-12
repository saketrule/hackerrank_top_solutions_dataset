#include<stdio.h>
#include<stdlib.h>
#define mod 1000000007
long long comp(const void *a,const void *b)
{
	return *(long long *)a-*(long long *)b;	
}
int main()
{
int t;scanf("%d",&t);
while(t--)
{
int n,m;scanf("%d %d",&m,&n);
m--;n--;
long long y[m],x[n];
int i;
for(i=0;i<m;i++)
	scanf("%lld",&y[i]);
for(i=0;i<n;i++)
	scanf("%lld",&x[i]);
qsort(y,m,sizeof(long long),comp);
qsort(x,n,sizeof(long long ),comp);
int p=m,q=n,cthz=1,ctvr=1;
long long ans=0;
while(p && q)
{
	if(y[p-1]==x[q-1])
	{
	if(cthz>ctvr)
	{ans=(ans+(x[q-1]*cthz))%mod;ctvr++;q--;}
	else
	{ans=(ans+(y[p-1]*ctvr))%mod;cthz++;p--;}
	}
	else if(y[p-1]>x[q-1])
	{ans=(ans+(y[p-1]*ctvr))%mod;cthz++;p--;}
	else
	{ans=(ans+(x[q-1]*cthz))%mod;ctvr++;q--;}
	//printf("%d %d %lld\n",p,q,ans);
}

if(p)
{int dd;for(dd=p-1;dd>=0;dd--) ans=(ans+y[dd]*ctvr)%mod;}
if(q)
{int dd;for(dd=q-1;dd>=0;dd--) ans=(ans+x[dd]*cthz)%mod;}
printf("%lld\n",ans);
}
return 0;
}
