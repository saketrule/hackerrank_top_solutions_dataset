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

struct UFS{
    int rep[3005];
    int rank[3005];
    UFS(int n){
        for(int i=0;i<n;i++)
            rep[i]=i,rank[i]=1;
    }
    bool isSame(int a,int b){
        return getRep(a)==getRep(b);
    }
    int getRep(int a){
        return (rep[a]==a)?a:(rep[a]=getRep(rep[a]));
    }
    void unionSet(int a,int b){
        int rep_a=getRep(a);
        int rep_b=getRep(b);
        if(rank[rep_a]>rank[rep_b]){
            rep[rep_b]=rep_a;
        }
        else{
            rep[rep_a]=rep_b;
            if(rank[rep_a]==rank[rep_b])
                rank[rep_b]++;
        }
    }
};

struct edge {
    int a, b;
    ll w;

    edge(int a, int b, ll w) {
        this->a = a;
        this->b = b;
        this->w = w;
    }

    bool operator<(edge e) {
        return this->w < e.w;
    }
};

vector<edge> edgelist;
int n,m,s;
ll kruskal() {
    ll ans = 0;
    int comp = n;
    UFS ufs(n);
    sort(edgelist.begin(), edgelist.end());
    for (int i = 0; i < edgelist.size(); i++) {
        edge e = edgelist[i];
        if (!ufs.isSame(e.a, e.b)) {
            ufs.unionSet(e.a, e.b);
            ans += e.w;
            comp--;
            if (comp == 1)
                break;
        }
    }
    return ans;
}

int main() {
#ifndef ONLINE_JUDGE 
  //  readfiles;
  //  double startTime = clock();
#endif
    cin >> n>>m;
    for (int i = 0; i < m; i++) {
        int u, v, w;
        cin >> u >> v>>w;
        u--, v--;
        edgelist.pb(edge(u, v, w));
    }
    cin>>s;
    s--;
    cout << kruskal() << endl;
#ifndef ONLINE_JUDGE
 //  cout << "Runtime is : " << (clock() - startTime) / CLOCKS_PER_SEC << endl;
#endif
}
