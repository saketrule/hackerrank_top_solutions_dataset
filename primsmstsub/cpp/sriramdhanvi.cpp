#include <cstdio>
#include <cstdlib>
#include <iostream>
#include <algorithm>
#include <set>

#define ii pair<int,int>
#define vii vector<ii>
#define INF 999999

using namespace std;

int N,M,S;

vii* G;
int* key;

bool compare(const int& u, const int& v)
    {
    if(key[u] != key[v])
        return key[u] < key[v];
    return u < v;
}


int main(int argc, char** argv)
    {
    int x,y,r;
    cin>>N>>M;
    G = new vii[N];
    
    key = new int[N];
    for(int i=0;i<N;i++)
        key[i] = INF;
    
    for(int i=0;i<M;i++)
        {
        cin>>x>>y>>r;
        G[x-1].push_back(ii(y-1,r));
        G[y-1].push_back(ii(x-1,r));
    }
    cin>>S;
    
    key[S-1] = 0;
    
    set<int> mstSet;
    vector<int> V;
    
    for(int i=0;i<N;i++)
        V.push_back(i);
    
    int mstwt = 0;
    
    while(!V.empty())
        {
        sort(V.begin(),V.end(),compare);
        int v = *(V.begin());
        //cout<<" v : "<<v<<endl;
        mstSet.insert(v);
        vii :: iterator it;
        for(it = G[v].begin(); it != G[v].end(); it++)
            {
            if(mstSet.find(it->first) != mstSet.end()) continue;
            int u = it->first;
            int d = it->second;
            if(key[u] > d)
                key[u] = d;
            
        }
        V.erase(V.begin());
    }
    for(int i=0;i<N;i++)
        mstwt += key[i];
    cout<<mstwt<<endl;
    return 0;
}