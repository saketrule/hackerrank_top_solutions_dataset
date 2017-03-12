#include <iostream>
#include <cstdio>
#include <climits>
#include <algorithm>
#include <queue>
#include <cstring>
#include <cmath>
#include <vector>
#include <list>
#include <stack>
#include <bitset>
#include <string>
#include <stack>
#include <set>
#include <map>
#include <deque>
#include <ctime>

#define ALL(i,n)    for(i = 0; i < (n); i++)
#define FOR(i,a,b)  for(i = (a); i < (b); i++)
#define SET(p)      memset(p,-1,sizeof(p))
#define CLR(p)      memset(p,0,sizeof(p))
#define S(n)	    scanf("%d",&n)
#define P(n)	    printf("%d\n",n)
#define Sl(n)	    scanf("%lld",&n)
#define Pl(n)	    printf("%lld\n",n)
#define Sf(n)       scanf("%lf",&n)
#define Ss(n)       scanf("%s",n)
#define LL long long
#define ULL unsigned long long
#define pb push_back
using namespace std;

int a[50];
int pr[1000100],cnt[10001000];
void prime(int n)
{
    int i,j;
    pr[1]=1;
    for(i=4;i<=n;i+=2)
        pr[i]=1;
    for(i=3;i*i<=n;i+=2)
    {
        if(pr[i]==0)
        {
            for(j=i*i;j<=n;j+=i+i)
            {
                pr[j]=1;
            }
        }
    }

    for(i=1;i<=n;i++)
    {
        cnt[i]=cnt[i-1]+(1-pr[i]);
    }
}

int main()
{
    int t,n,m,i,j,k;
    prime(300000);

    for(i=1;i<=3;i++) a[i]=1;
    a[4]=2;
    for(i=5;i<=40;i++) a[i]=a[i-1]+a[i-4];

    S(t);
    while(t--)
    {
        S(n);
        P(cnt[a[n]]);
    }
    return 0;
}
