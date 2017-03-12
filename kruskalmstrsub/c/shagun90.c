#include <stdio.h>
#include <string.h>
#include <math.h>
#include <stdlib.h>
#define N 3001
#define M 4600000
#define MAX 1100000
int count;
int S;
struct edge {
    int s;
    int d;
    int wt;
};
int parent[N];
struct edge arr[M];

void uni(int x,int y);
int find(int x);

void uni(int x,int y)
{
     int u = find(x);
     int v = find(y);
     
     parent[v] = u;
}
int find(int x)
{
    if(parent[x] == x) return x;
    return find(parent[x]);
}
int compare(const void *u,const void* v)
{
  struct edge *u1 = (struct edge*)u;
  struct edge *v1 = (struct edge*)v;
  return ((u1->wt - v1->wt)!=0?(u1->wt - v1->wt):(u1->wt+u1->d+u1->s)- (v1->wt+v1->s+v1->d));
}
int main() {
    int i,j,k,l,m,n;
    scanf("%d %d",&n,&m);
    for(i=1;i<=n;i++)
    {
     parent[i] = i;
    }
    for(i=0;i<m;i++)
    {
     scanf("%d %d %d",&arr[i].s,&arr[i].d,&arr[i].wt);
    }
    l = 0;
    count = 0;
    qsort(arr,m,sizeof(struct edge),compare);
    for(i=0;i<m;i++)
    {
     j=arr[i].s;
     k=arr[i].d;
     if(find(j) != find(k))
     {
                uni(j,k);
                count++;
                if(count > n-1) break;
                else l = l + arr[i].wt;
     }
    }
    printf("%d",l);
    return 0;
}
