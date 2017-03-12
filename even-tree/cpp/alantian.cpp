#include <iostream>
#include <cstdio>
#include <cstring>
#include <vector>

using namespace std;

#define REP(i,s,t) for(int i=s;i<(int)t;i++)

const int MAXN = 110;

int n;
vector<int> e[MAXN];
int m;

int f[MAXN][2];

void update(int&a, int b){ if(a<b)a=b;}

void dfs( int i, int p ){
    vector<int> son;
    REP(x,0,e[i].size()){
        int j = e[i][x];
        if( j != p ){
            dfs(j, i);
            son.push_back(j);
        }
    }

    if ( son.size() == 0 ){
        f[i][0] = -1;
        f[i][1] = 0;
        return;
    }

    f[i][0] = -1, f[i][1] = 0;
    REP(x,0,son.size()){
        int j = son[x];
        int next[2] = {-1,-1};
        // cut it
        REP(a,0,2)if(f[i][a]!=-1&&f[j][0]!=-1){
            update( next[a], f[i][a] + f[j][0] + 1 );
        }
        // link it
        REP(a,0,2)REP(b,0,2) if (f[i][a]!=-1&&f[j][b]!=-1)
            update(next[(a+b)%2], f[i][a]+f[j][b] );
        // store
        f[i][0] = next[0];
        f[i][1] = next[1];
    }
}

int main(){
    cin >> n >> m;
    REP(i,0,m){
        int a,b; cin >> a >> b;
        e[a].push_back(b);
        e[b].push_back(a);
    }

    dfs(1,-1);
    
    int ans = f[1][0];
    cout<<ans<<endl;

    return 0;
}
