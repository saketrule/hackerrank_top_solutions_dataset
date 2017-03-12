#include <stdio.h>
#include <string.h>
#include <math.h>
#include <stdlib.h>
#include <limits.h>

#define SIZE 3000

int Adj[SIZE+1][SIZE+1]={0};
int Cost[SIZE+1][SIZE+1]={0};
int dist[SIZE+1]={0};
int inSet[SIZE+1] = {0};

int N;
int S;

void Init()
{
  int u, v;

  for (u=1; u <=N; u++)
    for (v=1; v <=N; v++)
      Adj[u][v] = 0, Cost[u][v] = 0;

  for(u=0; u <=N; u++)
    dist[u] = INT_MAX, inSet[u] = 0;

  return;
}
int min (int a, int b)
{
  if (a < b)
    return a;
  return b;
}
int minIdx()
{
  int u, MinD=INT_MAX;
  int minU = -1;

  for (u=1; u <= N; u++)
    if (inSet[u] == 0 && dist[u] < MinD)
      MinD = dist[u], minU = u;
  return minU;
}
int primMST()
{
  int mst = 0;
  int u, v, i;

  dist[S] = 0;

  for (i=1; i <=N; i++)
  {
    u = minIdx();
    inSet[u] = 1;

    for(v=1; v <= N; v++)
      if(v != u  && inSet[v] == 0 && Adj[u][v] == 1 && Cost[u][v] < dist[v])
        dist[v] = Cost[u][v];
  }

  for (i=1; i <=N; i++)
    mst += dist[i];

  return mst;
}


int main()
{

  /* Enter your code here. Read input from STDIN. Print output to STDOUT */   
  int M, i;
  int x, y, r;
  {
    scanf("%d %d", &N, &M);
    Init();
    for(i=0; i < M; i++)
    {
      scanf("%d %d %d", &x, &y, &r);
      if (Adj[x][y] == 1)
        Cost[x][y] = Cost[y][x] = min(Cost[x][y], r);
      else
        Adj[x][y] = Adj[y][x] = 1, Cost[x][y] = Cost[y][x]= r;
    }
    scanf("%d", &S);

    printf("%d\n", primMST());
  }

  return 0;
}
