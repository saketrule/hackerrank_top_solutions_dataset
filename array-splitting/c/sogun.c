#include<stdio.h>
typedef long long unsigned U;
typedef unsigned u;
u A[222222];
u F(u l,u r,U s)
{
	u m=l-1,i,j;U x=0llu;
	for(;++m<=r;)
	{
		x+=A[m];
		if((x<<1)>s)break;
		if((x<<1)==s)
		{
			i=F(l,m,x);
			if(i<(j=F(m+1,r,x)))i=j;
			return i+1;
		}
	}
	return 0;
}
int main()
{
	u t,n,i;U s;
	for(scanf("%u",&t);t--;)
	{
		scanf("%u",&n);s=0llu;
		for(i=-1;++i<n;s+=A[i])scanf("%u",A+i);
		if(!s){printf("%u\n",n-1);continue;}
		printf("%u\n",F(0,n-1,s));
	}
	return 0;
}