#include<iostream>
#include<cmath>
#include<cstring>
#include<string>
#include<algorithm>
#include<vector>
#include<map>
#include<stack>
#include<queue>
#define MIN(a,b) ((a)<(b)?(a):(b))
#define MAX(a,b) ((a)>(b)?(a):(b))

typedef long long ll;
typedef long l;

using namespace std;
const double pi = acos(-1.0);

/*
inline void fast(int &x)
{
    register int c = getchar_unlocked();
    x = 0;
    int neg = 0;

    for(;((c<48 || c>57) && c != '-');
    c = getchar_unlocked());

    if(c=='-')
    {
        neg = 1;
        c = getchar_unlocked();
    }

    for(; c>47 && c<58 ; c = getchar_unlocked())
    {
        x =(x<<1)+(x<<3) + c - 48;
    }

    if(neg)
        x = -x;
}

void op(int x)
{
    if(x<0)
    {
         putchar('-');
         x=-x;
    }
    int len=0,data[20];
    while(x)
    {
        data[len++]=x%10;
        x/=10;
    }
    if(!len)
       data[len++]=0;
    while(len--)
        putchar(data[len]+48);
    putchar('\n');
} */
#define mod 1000000007
int main()
{
    int t,n,m,x[1000004],y[1000004];
    scanf("%d",&t);
    while(t--)
    {
        scanf("%d %d",&n,&m);
        n=n-1;
        m=m-1;
        
        for(int i=0;i<n;++i) 
              scanf("%d",&x[i]);
        for(int i=0;i<m;++i) 
              scanf("%d",&y[i]);
        
        ll ans,s1 = 0,s2 = 0;
        for(int i=0;i<n;++i) 
              s1 += x[i];
        for(int i=0;i<m;++i) 
              s2 += y[i];
        ans = (s1+s2)%mod;
        
        sort(x,x+n);
        sort(y,y+m);
        
        for(int i=n-1,j=m-1;i>=0&&j>=0;)
        {
            if(y[j]>=x[i])
            {
                ans=(ans+s1)%mod;
                s2 -= y[j];
                --j;
            }
            else
            {
                ans=(ans+s2)%mod;
                s1 -= x[i];
                --i;
            }
        }
        
        printf("%lld\n",ans%mod);
    }
    
    return 0;
}