#include <cmath>
#include <cstdio>
#include <vector>
#include <iostream>
#include <algorithm>
#include <set>
#include <list>
#include <queue>

#define ii pair<int,int>
#define vii vector<ii>

using namespace std;

int N,M,S;
vii* G;

int* parent;
bool* visited;

class Edge
    {
  public :
       int u;
       int v;
       int wt;
};

vector<Edge> edges;

bool edge_compare(const Edge& e1, const Edge& e2)
    {
    if(e1.wt != e2.wt)
        return e1.wt < e2.wt;
    if ((e1.u+e1.v+e1.wt) != (e2.u+e2.v+e2.wt))
        return (e1.u+e1.v+e1.wt) < (e2.u+e2.v+e2.wt);
    return true;
}

bool cycleExists(int u)
    {
    visited[u] = true;
    vii :: iterator it;
    for(it = G[u].begin(); it != G[u].end(); it++)
        {
        if(!visited[it->first])
            {
            parent[it->first] = u;
            if(cycleExists(it->first))
                return true;
        }
        else if(parent[u] != it->first)
            return true;
    }
    return false;
}

bool isCyclic()
    {
    for(int i=0;i<N;i++)
        visited[i] = false;
    
    for(int i=0;i<N;i++)
        if(!visited[i])
            if(cycleExists(i))
                return true;
    return false;
}

int main() {
      
    int x,y,r;
    Edge e;
    int edgeCount = 0,mstwt = 0;
        cin>>N>>M;
        G = new vii[N];
        visited = new bool[N];
        for(int i=0;i<N;i++)
            visited[i] = false;
    
        parent = new int[N];
        for(int i=0;i<N;i++)
            parent[i] = -1;
    
        for(int i=0;i<M;i++)
        {
            cin>>x>>y>>r;
            //G[x-1].push_back(ii(y-1,r));
            //G[y-1].push_back(ii(x-1,r));
            e.u = x-1;
            e.v = y-1;
            e.wt = r;
            edges.push_back(e);
        }
       cin>>S;
       /*for(int i=0;i<N;i++)
           {
           vii :: iterator it2;
           cout<<i<<"-->";
           for(it2 = G[i].begin();it2 != G[i].end(); it2++)
               {
               cout<<"("<<it2->first<<","<<it2->second<<")"<<"  ";
           }
             cout<<endl;
       }
       
       if(isCyclic())
           cout<<"cyclic!!!"<<endl;
        else cout<<"not cyclic !!"<<endl;
        exit(0);*/
       sort(edges.begin(),edges.end(),edge_compare);
       /*vector<Edge> :: iterator it;
        for(it = edges.begin(); it != edges.end(); it++)
            cout<<it->wt<<" ";
        cout<<endl;*/
      vector<Edge> :: iterator it;
      it = edges.begin();
      while(edgeCount<N-1)
          {
          e = *it;
          x = e.u;
          y = e.v;
          r = e.wt;
          //cout<<x+1<<" "<<y+1<<" "<<r<<endl;
          
          G[x].push_back(ii(y,r));
          G[y].push_back(ii(x,r)); 
          
         /*for(int i=0;i<N;i++)
           {
           vii :: iterator it2;
           cout<<i<<"-->";
           for(it2 = G[i].begin();it2 != G[i].end(); it2++)
               {
               cout<<"("<<it2->first<<","<<it2->second<<")"<<"  ";
           }
             cout<<endl;
       }*/
          
          if(isCyclic())
              {
              //cout<<"added wt : "<<endl;
              //cout<<"cyclic!!"<<endl;
              G[x].pop_back();
              G[y].pop_back();
          }
          else 
              {
              //cout<<"not cyclic !!"<<endl;
              mstwt += e.wt;
              //cout<<"added wt : "<<e.wt<<"  "<<"mstwt : "<<mstwt<<endl;
              edgeCount++;
          }
          it++;
      }
    cout<<mstwt<<endl;
    return 0;
}
