#include <bits/stdc++.h>
using namespace std;


int main() {
    int n,m,k,t,a,x,y,z;
    cin>>n>>m>>k;
    vector<vector<int> > times(n,vector<int> (pow(2,k),INT_MAX) );
    vector<int> fish(n,0);
    vector<vector<pair<int,int> > > edges(n);
    priority_queue<pair<int,pair<int,int> >,vector<pair<int,pair<int,int> > >,greater<pair<int,pair<int,int> > > > pq;
    for(int i=0;i<n;++i){
        cin>>t;
        while(t--){
            cin>>a;
            fish[i] |= (1<<(a-1) );
        }
    }
    for(int i=0;i<m;++i){
        cin>>x>>y>>z;
        edges[x-1].push_back(make_pair(z,y-1) );
        edges[y-1].push_back(make_pair(z,x-1) );
    }
    pq.push(make_pair(0,make_pair(0,fish[0])));
    while(!pq.empty()){
        auto cur = pq.top();
        pq.pop();
        int time = cur.first, node = cur.second.first, fsh = cur.second.second;
        if(times[node][fsh]>time){
            times[node][fsh]=time;
            for(auto edge:edges[node]){
                int nfsh = (fsh | fish[edge.second]);
                if(times[edge.second][nfsh]>(time + edge.first))
                    pq.push(make_pair((time + edge.first),make_pair(edge.second,nfsh)));
            }
        }
    }
    int least = INT_MAX;
    for(int i=0;i<pow(2,k);++i)
        for(int j=i;j<pow(2,k);++j){
        if((i | j)==(pow(2,k) - 1) and times[n-1][i]!=INT_MAX and times[n-1][j]!=INT_MAX)
            least = min(least,max(times[n-1][i],times[n-1][j]));
    }
    cout<<least;
    return 0;
}
