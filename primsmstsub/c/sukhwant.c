#include<stdio.h>
#include<limits.h>

int graph[3001][3001]={0};
int visited[3001]={0};
int distance[3001],totalNode;


int findMin(){
	
	int i,index,min=INT_MAX;
	for(i=1;i<=totalNode;i++){
		if(visited[i]==0 && distance[i]<=min){
			min=distance[i];
			index=i;
		}
	}
	return index;
}

long long int prims(int startNode){
	distance[startNode]=0;
	int totalVisited=0,next;
	int sum=0,i;
	while(totalVisited<totalNode){
		next=findMin();
		sum+=distance[next];
		totalVisited++;
		visited[next]=1;
		// adj nodes update
		for(i=1;i<=totalNode;i++){
			if(graph[next][i]>=0 && visited[i]==0 && distance[i]>graph[next][i]){
				distance[i]=graph[next][i];
			}
		}
	}
	return sum;
}
int main(){
	
	int n,e,src,dest,weight,i,startNode,j;
	scanf("%d%d",&n,&e);
	
	for(i=1;i<=n;i++){
		distance[i]=INT_MAX;
		visited[i]=0;
	}
	
	for(i=1;i<=n;i++){
		for(j=1;j<=n;j++)
			graph[i][j]=-1;
	}
	totalNode=n;
	for(i=0;i<e;i++){
		scanf("%d%d%d",&src,&dest,&weight);
		if(graph[src][dest]==-1){
			graph[src][dest]=weight;
			graph[dest][src]=weight;
		}
		
		else if(graph[src][dest]>weight){
			graph[src][dest]=weight;
			graph[dest][src]=weight;
		}
	}
	scanf("%d",&startNode);	
	long long int sum=prims(startNode);
	
	printf("%lld\n",sum);
	return 0;
}