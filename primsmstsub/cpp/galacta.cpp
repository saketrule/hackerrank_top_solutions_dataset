#include<bits/stdc++.h>

using namespace std;

int nodes, edges;

int s;

vector<int> adj[1000006];

bool vis[1000006];

int mat[10006][10006];

void prim(int node)
{
    int dist[10006];
    int parent[1000006];
    int i, j;
    
    for(i=0; i<10006; i++)
    {
        parent[i]=-1;
        dist[i]=INT_MAX;
    }
    
    dist[node]=0;
    vis[node]=1;
    parent[node]=node;
    
    for(i=0; i<nodes-1; i++)
    {
        int min_dist=INT_MAX;
        
        for(j=0; j<=nodes; j++)
        {
            if(!vis[j] && dist[j]<min_dist)
            {
                min_dist=dist[j];
                node=j;
            }
        }
        
        vis[node]=1;
        int elements=adj[node].size();
        
        for(j=0; j<elements; j++)
        {
             if(!vis[adj[node][j]] && dist[adj[node][j]] > mat[node][adj[node][j]])
             {
                 dist[adj[node][j]] = mat[node][adj[node][j]];
                 parent[adj[node][j]]=node;
             }
        }
    }
    
    long long ans=0;
    
    for(i=1;i<=nodes;i++)
    {
    	if(i==s)
    		continue;
    	else
    		ans+=dist[i];
    }
    cout<<ans;
    cout<<endl;
}

int main()
{
     int i, j;

     cin>>nodes;
     cin>>edges;
     
     //Marking all distances as infinity
     for(i=0; i<=nodes; i++)
         for(j=0; j<=nodes; j++)
             mat[i][j]=INT_MAX;
    
    for(i=0;i<=nodes;i++)
    	adj[i].clear();
     
     for(i=0; i<edges; i++)
     {
         int x, y, wt;
         cin>>x>>y>>wt;
         adj[x].push_back(y);
         adj[y].push_back(x);
         if(wt<mat[x][y])
         {
         	mat[x][y]=mat[y][x]=wt;    //putting weight
         }
     }
     
     //int s;
     cin>>s;

    memset(vis,0,sizeof vis);
     prim(s);
     return 0;
     
}

