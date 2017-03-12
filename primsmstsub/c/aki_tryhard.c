#include <stdio.h>
#include <string.h>
#include <math.h>
#include <stdlib.h>
#define INF 1000000
int minimum(int dist[],int node,int visited[])
	{
	int i,k;
	int min =INF;
	for(i=0;i<node;i++)
		{
		if(dist[i]!=-1 && dist[i] != INF && visited[i] ==0)
			{
			if(dist[i] < min)
				{
					min = dist[i];
					k=i;
				}
			}
		}
	return k;
	}
void prims(int node,int start,int matrix[][node])
    {
    int visited[node],dist[node];
    int i;int count =0,j,min,k;long totalcost=0;
    for(i=0;i<node;i++)
{
        visited[i]=0;
    dist[i]=INF;
}
visited[start]=1; 
dist[start]=-1;
    while(count<node-1)
        {
        for(j=0;j<node;j++)
            {
            
            if(matrix[start][j]!=INF  && visited[j] ==0 )
                {
                
                if(matrix[start][j] < dist[j])
                    {
		   
                    dist[j] = matrix[start][j];
                   
		     
                }
            }
        }
        min = minimum(dist,node,visited);
        totalcost = totalcost+dist[min];
	 visited[min] = 1;
	start = min;
	//printf("start is %d    ",start);
       
        count++;
    }
	printf("%ld\n",totalcost);
}
int main() {
    int node,edges;
    scanf("%d%d",&node,&edges);
    int matrix[node][node];
    int i,j,start,end,value,source;
    for(i=0;i<node;i++)
        {
        for(j=0;j<node;j++)
            {
                
            matrix[i][j]=INF;
        }
    }
    for(i=0;i<edges;i++)
        {
        scanf("%d %d%d",&start,&end,&value);
        matrix[start-1][end-1] = value;
	 matrix[end-1][start-1] = value;
    }
    scanf("%d",&source);
   
    prims(node,source-1,matrix);
    return 0;
}
