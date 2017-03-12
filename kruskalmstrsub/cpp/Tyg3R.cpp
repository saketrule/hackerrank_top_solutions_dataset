#include <algorithm>
#include <cmath>
#include <cstdio>
#include <cstdlib>
#include <cstring>
#include <iostream>
#include <sstream>
#include <map>
#include <set>
#include <queue>
#include <vector>

using namespace std;

#define FOR(prom, a, b) for(int prom = (a); prom < (b); prom++)
#define FORD(prom, a, b) for(int prom = (a); prom > (b); prom--)
#define FORDE(prom, a, b) for(int prom = (a); prom >= (b); prom--)

#define DRI(a) int a; scanf("%d ", &a);
#define DRII(a, b) int a, b; scanf("%d %d ", &a, &b);
#define DRIII(a, b, c) int a, b, c; scanf("%d %d %d ", &a, &b, &c);
#define DRIIII(a, b, c, d) int a, b, c, d; scanf("%d %d %d %d ", &a, &b, &c, &d);
#define RI(a) scanf("%d ", &a);
#define RII(a, b) scanf("%d %d ", &a, &b);
#define RIII(a, b, c) scanf("%d %d %d ", &a, &b, &c);
#define RIIII(a, b, c, d) scanf("%d %d %d %d ", &a, &b, &c, &d);

#define PB push_back
#define MP make_pair

#define ll long long
#define ull unsigned long long

#define MM(co, cim) memset((co), (cim), sizeof((co)))

#define DEB(x) cerr << ">>> " << #x << " : " << x << endl;

vector<int> g[3007], gw[3007];
bool vis[3007];
int dist[3007];

int main ()
{
  int T = 1;
  while(T--) {
    DRII(N,M);
    FOR(i,1,N+1) {
      g[i].clear();
      gw[i].clear();
      vis[i] = false;
      dist[i] = -1;
    }
    while(M--) {
      DRIII(x,y,r);
      g[x].PB(y);
      gw[x].PB(r);
      g[y].PB(x);
      gw[y].PB(r);
    }
    DRI(S);
    priority_queue<pair<int,int> > q;
    q.push(MP(0,S));
    dist[S] = 0;
    int sum = 0;
    while(!q.empty()) {
      int v = q.top().second;
      q.pop();
      if(vis[v]) continue;
      vis[v] = true;
      sum += dist[v];
      FOR(i,0,g[v].size()) {
        int u = g[v][i];
        if(dist[u] == -1 || dist[u] > gw[v][i]) {
          dist[u] = gw[v][i];
          q.push(MP(-dist[u],u));
        }
      }
    }
    printf("%d\n", sum);
  }
  return 0;
}





