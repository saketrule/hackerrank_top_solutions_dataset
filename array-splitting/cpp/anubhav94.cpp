#include<bits/stdc++.h>
using namespace std;

#undef _P
#define _P(...) (void)printf(__VA_ARGS__)
#define sd(mark) scanf("%d",&mark)
#define ss(mark) scanf("%s",&mark)
#define slld(mark) scanf("%lld",&mark)
#define clr(mark) memset(mark,0,sizeof(mark))
#define F first
#define S second
#define MP make_pair
#define PB push_back
#define sz(x) (int((x).size()))
#define PII pair<int,int>
#define PIL pair<int,long long>
#define PLL pair<long long,long long>
#define PIS pair<int,string>
#define MII map<int,int>
#define LL long long
#define FILEIO(name) \
    freopen(name".in", "r", stdin); \
    freopen(name".out", "w", stdout);
#define INF 2000000000 // 2 * 10^9
#define INFLL 1000000000000000000LL  // 10^18
#define mod 1000000007

LL power ( LL a,LL b)
{
    LL rs = 1,cr =a;
    while(b)
    {
        if(b&1) rs = (rs*cr)%mod;
        cr = (cr*cr)%mod;
        b >>= 1;
    }
    return rs;
}

#define N 512345
LL a[N],s[N];

int bins(int l,int r,int l0,LL sm)
{
    if(l==r) return l;
    int m = (l+r+1)>>1;
    if(s[m]-s[l0-1]<=sm) return bins(m,r,l0,sm);
    return bins(l,m-1,l0,sm);
}

int fn(int l,int r)
{
    //cout << l << " " << r << endl;
    if(l>=r) return 0;
    LL sm = s[r]-s[l-1];
    if(sm&1) return 0;
    sm >>= 1;
    int m = bins(l,r,l,sm);
    if(s[m]-s[l-1] == sm) return 1 + max(fn(l,m),fn(m+1,r));
    return 0;
}

void solve()
{
    int n,i;
    sd(n);
    bool zr = 1;
    for(i=1;i<=n;++i)
    {
        slld(a[i]);
        if(a[i]!=0) zr = 0;
    }
    if(zr)
    {
        printf("%d\n",n-1);
        return;
    }
    s[0] = 0;
    for(i=1;i<=n;++i)
        s[i] = a[i] + s[i-1];
    printf("%d\n",fn(1,n));
}

int main()
{
    int t = 1;
    scanf("%d",&t);
    while(t--)
    {
        solve();
    }
}

