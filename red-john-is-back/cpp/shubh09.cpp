#include <iostream>
#include <vector>
#include <cstdio>
#include <cstring>
#include <string>
#include <cmath>
#include <algorithm>
#include <utility>
#include <stack>
#include <sstream>
#include <list>
#include <map>
#include <queue>
#include <set>
#include <deque>
#include <bitset>
#include <functional>
#include <numeric>
#include <utility>
#include <iomanip>
#include <cmath>
#include <cstdlib>
#include <ctime>
#include <climits>
#include <cassert>

using namespace std;

#define FOR(i,a,b)				for (i=a;i<b;i++)
#define s(n)					scanf("%d",&n)
#define p(n)					printf("%d\n",n)
#define pl(n)					printf("%lld\n",n)
#define sd(n)					int n;scanf("%d",&n)
#define sl(n)					scanf("%lld",&n)
#define sld(n)					long long int n;scanf("%lld",&n)
#define pb(n)                                   push_back(n)
#define all(c)                                  (c).begin(),(c).end()
#define tr(container,it)                        for (typeof(container.begin()) it=container.begin();it!=container.end();it++ )
#define sz(a)                                   int((a).size())
#define clr(a)                                  memset(a,0,sizeof(a))
#define mp(a,b)                                 make_pair(a,b)

#define INF                                     INT_MAX
#define UINF                                    UINT_MAX
#define INF_LL                                  LLONG_MAX
#define UINF_LL                                 ULLONG_MAX
#define PI 3.14159265358979323846

typedef long long ll;
typedef vector <int> vi;
typedef vector <vi> vvi;
typedef vector <string> vstr;
typedef pair<int,int> pii;
typedef vector<vector<pair<int,int> > > TG;

vector<bool> sieve(int n)		//O(nloglogn)
{
    //ans[i]=true if i is prime
    vector<bool> ans(n+1,true);
    ans[0]=ans[1]=false;
    int i;
    int hi=(int)sqrt(n);
    FOR(i,2,hi+1)
    {
        if (ans[i]==true)
        {
            //i is prime
            int t=i*i;
            while (t<=n)
            {
                ans[t]=false;
                t+=i;
            }
        }
    }
    return ans;
}


int main()
{
    sd(T);
    int t;
    int i,j;
    //preprocessing
    vi arr(41);
    arr[1]=arr[2]=arr[3]=1;
    arr[4]=2;
    FOR(i,5,41) arr[i]=arr[i-1]+arr[i-4];
    vector<bool> vb=sieve(300000);
    vi arr2(300000);
    arr2[0]=0;
    FOR(i,1,300000) arr2[i]=arr2[i-1]+vb[i];
//    FOR(i,1,41) printf("%d ",arr[i]);
//    p(arr[40]);
    FOR(t,0,T)
    {
        sd(n);
//        p(arr[n]);
        p(arr2[arr[n]]);
    }
}
