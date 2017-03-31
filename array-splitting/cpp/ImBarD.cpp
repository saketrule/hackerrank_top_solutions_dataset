#include <iostream>
#include <vector>
#include <algorithm>
#include <string>
#include <ctype.h>
#include <deque>
#include <queue>
#include <cstring>
#include <set>
#include <list>
#include <map>
#include <unordered_map>
#include <stdio.h>

using namespace std;

typedef long long ll;
typedef std::vector<int> vi;
typedef std::vector<bool> vb;
typedef std::vector<string> vs;
typedef std::vector<double> vd;
typedef std::vector<long long> vll;
typedef std::vector<std::vector<int> > vvi;
typedef std::vector<std::vector<bool> > vvb;
typedef std::vector<std::pair<int, int> > vpi;
typedef std::pair<int, int> pi;
typedef std::pair<ll, ll> pll;
typedef std::vector<pll> vpll;

long long mod=1000000007;

#define all(c) (c).begin(),(c).end()
#define sz(c) (int)(c).size()
#define forn(i, a, b) for(int i = a; i < b; i++)

#define pb push_back
#define mp make_pair

vi a;
vll chsum;
ll cursum = 0;
int go(int l, int r) {
    if(cursum&1) return 0;
    int m = lower_bound(chsum.begin(), chsum.end(), chsum[l-1] + cursum/2) - chsum.begin();
    if(chsum[m]!=chsum[l-1]+cursum/2) return 0;
    else{cursum/=2;
        int ret =1+ max(go(l,m), go(m+1,r));

        cursum*=2;
        return ret;
    }
}
int main()
{

    int t;
    cin>>t;
    forn(ahfg, 0, t) {
        int n,b;
        cin>>n;
        a.resize(n);
        ll sum = 0;
        chsum.clear();
        chsum.pb(0);
        forn(i,0,n) {
            cin>>a[i];
            sum+=a[i];
            chsum.pb(sum);
        }
        ll ans=0;
        if(sum == 0) {
            cout<<n-1<<endl;
            continue;
        }
        cursum=sum;
        cout<<go(1,n)<<endl;
    
    }
    // cout<<ans;
}