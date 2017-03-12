#include <cmath>
#include <cstdio>
#include <vector>
#include <iostream>
#include <algorithm>
using namespace std;


#define EDGE pair< int, int >

// cost, u, v

vector< pair< int, EDGE > > Graph, MST;

int n, parent[10010];
int ver,edge;
int u,v,cost;
int start;

void initializeParent(int vertex)
{
    for(int i = 1; i <= vertex; i++)
        parent[i] = i;
}
int findParent(int n, int *parent)
{
    if(n != parent[n])
        return findParent(parent[n],parent);
    return parent[n];
}
void Kruskal()
{
    int pu, pv;
    initializeParent(ver);
    sort(Graph.begin(),Graph.end());
    int total = 0;
    for(int i = 0; i < edge; i++)
    {
    pu = findParent(Graph[i].second.first,parent);
    pv = findParent(Graph[i].second.second,parent);
        if(pu!=pv)
        {
            MST.push_back(Graph[i]);
            total+= Graph[i].first; // add the cost
            parent[pu] = parent[pv];
        }
    }
   // cout << "Minimum Cost = " << total << endl;
      cout << total << endl;

}

int main()
{

   //cout << "Enter number of vertices" << endl;
   cin >> ver;
  // cout << "Enter number of edges" << endl;
   cin >> edge;
  // cout << "Enter Source, Destination, Cost" << endl;
   for(int i = 0; i < edge; i++)
   {
       cin >> u >> v >> cost;
       Graph.push_back(pair< int, EDGE >(cost, EDGE(u, v)));
   }


   cin >> start;
   Kruskal();

   //Print just edges
   //cout << "Path" << endl;
   for(int i = 0; i < MST.size(); i++)
   {
    //  cout << MST[i].second.first << "->" << MST[i].second.second << endl;
   }



   return 0;
}

