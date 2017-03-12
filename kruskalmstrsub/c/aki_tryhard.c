#include <stdio.h>
#include <string.h>
#include <math.h>
#include <stdlib.h>
struct edges
{
int u,v,w;
};
 struct edges * sort( struct edges * edge,int no)
     {
     int i,temp,j;
     for(i=0;i<no;i++)
         {
         for(j=0;j<no-i-1;j++)
             {
             if(edge[j].w > edge[j+1].w)
                 {
                 temp = edge[j].w;
                 edge[j].w = edge[j+1].w;
                 edge[j+1].w = temp;
                 temp = edge[j].u;
                 edge[j].u = edge[j+1].u;
                 edge[j+1].u = temp;
                 temp = edge[j].v;
                 edge[j].v = edge[j+1].v;
                 edge[j+1].v= temp;
             }
             else if(edge[j].w == edge[j+1].w)
		{
		if((edge[j].w + edge[j].u + edge[j].v) > (edge[j+1].w + edge[j+1].u + edge[j+1].v))  
			{
			temp = edge[j].w;
                 edge[j].w = edge[j+1].w;
                 edge[j+1].w = temp;
                 temp = edge[j].u;
                 edge[j].u = edge[j+1].u;
                 edge[j+1].u = temp;
                 temp = edge[j].v;
                 edge[j].v = edge[j+1].v;
                 edge[j+1].v= temp;
		}
		}
         }
     }
     return edge;
 }
int find(int parent[],int s)
    {
    if(parent[s] != s)
    parent[s] = find(parent,parent[s]);
        return parent[s];
    
}
void unionset(int parent[],int x,int y)
    {
    int xset =find(parent,x);
    int yset=find(parent,y);
    parent[xset]=yset;
}
void kruskal(int node,struct edges * edge,int start,int edgeno)
    {
    int parent[node],e=0,s=0;struct edges result[node-1];int i;
    
       
    struct edges * sortedge = sort(edge,edgeno);
    
    for(i=0;i<node;i++)
     parent[i] = i;
    while(s<node-1)
        {
    struct edges nextedge = sortedge[e++];
        int x = find(parent,nextedge.u);
        int y = find(parent,nextedge.v);
        if(x!=y)
            {
         // printf("eeeeeeeeeeee %d",nextedge.w);
            result[s++] = nextedge;
            unionset(parent,x,y);
        }
    }
    int sum=0;
    for(i=0;i<node-1;i++)
        {
        sum = sum + result[i].w;
    }
printf("%d",sum);
}
int main() {
	int node,edgesno,i;
    scanf("%d%d",&node,&edgesno);
    
        struct edges *  edge = (struct edges *)malloc(sizeof(struct edges) * edgesno);
    for(i=0;i<edgesno;i++)
        {
	int start,end,value;
        scanf("%d %d%d",&start,&end,&value);
        edge[i].u=start-1;
        edge[i].v=end-1;
        edge[i].w=value;
    }
   int source;
    scanf("%d",&source);
   
  kruskal(node,edge,source-1,edgesno);
   return 0;
}
