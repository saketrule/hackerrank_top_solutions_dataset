#include<iostream>
#include<stdio.h>
#include<string.h>
#include<stdlib.h>
#include<math.h>
#include<vector>
#include<algorithm>
using namespace std;
#define max(a,b) a>b?a:b
#define min(a,b) a<b?a:b
#define mod 1000000007

int main()
{
	int n,a[1001]={0};
	scanf("%d",&n);
	for(int i=0;i<n;i++)
	{
		int x;
	    scanf("%d",&x);
	    a[x]++;
	}
	
	for(int i=1;i<1001;i++)
	{
		if(a[i]>0)
		{
			printf("%d\n",n);
			n=n-a[i];
		}
	}
	
    return 0;
}
