#include<cstdio>
#include<cstring>
#include<set>
#include<queue>
#include<vector>
#include<algorithm>
#include<cstdlib>
#include<ctime>
using namespace std;
int T,n,m,i,j,ap[4],x[4];
long long sol;
struct str{int v,t;}a[2000009];
bool cmp(str a,str b)
{
    return a.v>b.v;
}
long long min(long long a,long long b)
{
    if(a<b) return a;
    return b;
}
int main()
{
//freopen("input","r",stdin);
//freopen("output","w",stdout);
scanf("%d",&T);
while(T)
{
    T--;
    scanf("%d",&n);
    scanf("%d",&m);
    n--,m--;
    for(i=1;i<=n;i++)
    {
        scanf("%d",&a[i].v);
        a[i].t=1;
    }
    for(i=1;i<=m;i++)
    {
        scanf("%d",&a[i+n].v);
        a[i+n].t=2;
    }
    sort(a+1,a+n+m+1,cmp);
    ap[1]=ap[2]=0;
    sol=0;
    for(i=1;i<=n+m;i++)
    {
        x[1]=x[2]=0;
        for(j=i;j<=n+m;j++)
            if(a[j].v==a[i].v) x[a[j].t]++;
            else break;
        sol+=min(1LL*x[1]*(ap[2]+1)*a[i].v+1LL*a[i].v*x[2]*(ap[1]+x[1]+1) , 1LL*x[2]*(ap[1]+1)*a[i].v+1LL*a[i].v*x[1]*(ap[2]+x[2]+1));
        ap[1]+=x[1];
        ap[2]+=x[2];
        i=j-1;
    }
    printf("%d\n",sol%1000000007);
}
return 0;
}
