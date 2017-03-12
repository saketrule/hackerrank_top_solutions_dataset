#include <stdio.h>
#include <string.h>
#include <math.h>
#include <stdlib.h>
#include<limits.h>
int minWt(int dist[], int visit[], int n)
{
   int v;
   int min = INT_MAX, min_index;
   for (v = 0; v < n; v++)
     if (visit[v] == 0 && dist[v] < min){
         min = dist[v];
         min_index = v;
   }
 
   return min_index;
}
int find(int** arr,int n,int src)
{
    int x,count;
    int wt=0;
    int* visit= (int*)malloc(n * sizeof(int));
    int* parent= (int*)malloc(n * sizeof(int));
    int* dist= (int*)malloc(n * sizeof(int));
    for(int x=0;x<n;x++)
    {
      visit[x]=0;
      dist[x]= INT_MAX;
    }
    dist[src]=0;
    parent[src]=src;
    for (count = 0; count < n-1; count++)
    {
        int u = minWt(dist, visit,n);
        visit[u] = 1;
        for (x = 0; x < n; x++)
          if (arr[u][x]!=-1 && visit[x] == 0 && arr[u][x] <  dist[x])
             parent[x]  = u, dist[x] = arr[u][x];
     }
    for (x = 0; x < n; x++)
    {
            if(x!=src)
                wt = wt +  arr[x][parent[x]];
    }
    return wt;
}
int main() {

    int n,e,x,y,r,src;
    int result=0;
    int **arr;
    scanf("%d %d",&n,&e);
    arr=(int**)malloc(n * sizeof(int*));
    for(x=0;x<n;x++)
        arr[x]=(int*)malloc(n * sizeof(int));
    for(x=0;x<n;x++)
        for(y=0;y<n;y++)
        arr[x][y]=-1;
    while(e--)
    {
        scanf("%d %d %d",&x,&y,&r);
        if(arr[x-1][y-1] == -1 || r < arr[x-1][y-1])
        {
            arr[x-1][y-1]=r;
            arr[y-1][x-1]=r;
        }
        else{
        }
    }
    scanf("%d",&src);
    result=find(arr,n,src-1);
    printf("%d\n",result);
    return 0;
}
