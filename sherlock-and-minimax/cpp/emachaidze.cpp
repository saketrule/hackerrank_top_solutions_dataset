#include<iostream>
#include<stdio.h>
#include<cstring>
#include<assert.h>
#include<algorithm>
#include<queue>
#include<vector>
#include<stack>
#include<map>
#include<set>
#define MAX(a,b) ((a)>(b)?(a):(b))
#define MIN(a,b) ((a)<(b)?(a):(b))
#define F first
#define S second
#define mp make_pair
#define pb push_back
#define pp pair<ll,ll>
#define INF 2000000000
#define ll long long
using namespace std;
const int N=105;
int n,i,j,a[N],T,t,ans,x,P,Q,cur,pas;
vector<int> v;

int main()
{scanf("%d",&n);
 for(i=1;i<=n;i++)scanf("%d",&a[i]);
 sort(a+1,a+n+1);
 scanf("%d%d",&P,&Q);
 v.pb(P);
 v.pb(Q);
 for(i=2;i<=n;i++){
    v.pb((a[i]+a[i-1])/2);
    if((a[i]+a[i-1])&1)v.pb((a[i]+a[i-1])/2+1);
 }

 for(i=0;i<v.size();i++){
    x=v[i];
    x=MAX(P,x);
    x=MIN(Q,x);

    cur=INF;
    for(j=1;j<=n;j++)
        cur=MIN(cur,abs(a[j]-x));
    if(cur>ans)ans=cur,pas=INF;
    if(cur==ans)pas=MIN(pas,x);
    ans=MAX(ans,cur);
 }
 cout<<pas<<endl;

 return 0;
}

