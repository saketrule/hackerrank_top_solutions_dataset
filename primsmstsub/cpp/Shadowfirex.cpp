#include <cstdlib>
#include <iostream>
#include <vector>
#include <cstdio>
#include <string.h>
#include <string>
#include <map>
#include <math.h>
#include <sstream>
#include <queue>
#include <set>
#include <stack>
#include <cmath>
#include <algorithm>
#include <list>
#include <deque>
#include <utility>
#define mp  make_pair
#define pb  push_back
#define eps 1e-9
#define iseq(a,b) (fabs(a-b)<eps)
#define readfiles freopen("in.txt","r",stdin); freopen("out.txt","w",stdout)
#define readfile freopen("in.txt","r",stdin)
#define fastio ios::sync_with_stdio(false)
#define oo 0x7fffffff
#define forv(i,v) for(int i=0;i<v.size();i++)
#define all(v) v.begin(),v.end()
#define OO 0x7fffffffffffffff
#define readcases int cases;cin>>cases;while(cases--)
#define MAX_N 105
#define in_node(x) x
#define out_node(x) x+n
#define adjust(x) x+100
#define UPPER(x) x+26
using namespace std;
typedef long long ll;
typedef unsigned long long ull;
typedef unsigned int uint;

vector< vector< pair<ll, int> > > g;
bool visited[3005];
int n, m, s;

ll prim() {
    ll ans = 0;
    int e = 0;
    priority_queue< pair<ll, int>, vector< pair<ll, int> >, greater< pair<ll, int> > > pq;
    pq.push(mp(0, s));
    while (!pq.empty()) {
        pair<ll, int> p = pq.top();
        pq.pop();
        int u = p.second;
        ll d = p.first;
        if (!visited[u]) {
            visited[u] = true;
            ans += d;
            e++;
            if (e == n)
                break;
            for (int i = 0; i < g[u].size(); i++) {
                int v = g[u][i].second;
                ll dv = g[u][i].first;
                if (!visited[v]) {
                    pq.push(mp(dv, v));
                }
            }
        }
    }
    return ans;
}

int main() {
#ifndef ONLINE_JUDGE 
   // readfiles;
  //  double startTime = clock();
#endif
    cin >> n>>m;
    g.clear();
    g.resize(n + 5);
    for (int i = 0; i < m; i++) {
        int u, v, w;
        cin >> u >> v>>w;
        u--, v--;
        g[u].pb(mp(w, v));
        g[v].pb(mp(w, u));
    }
    cin>>s;
    s--;
    memset(visited, 0, sizeof visited);
    cout << prim() << endl;
#ifndef ONLINE_JUDGE
  //  cout << "Runtime is : " << (clock() - startTime) / CLOCKS_PER_SEC << endl;
#endif
}