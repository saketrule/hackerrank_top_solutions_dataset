#include<stdio.h>
#include<stdlib.h>

struct Edge{
	int source;
	int destination;
	int weight;
};
int Find(int parent[],int i){
	if(parent[i]==-1)
		return i;
	return Find(parent,parent[i]);
}

void Union(int parent[],int x,int y){
	int xSet=Find(parent,x);
	int ySet=Find(parent,y);
	parent[xSet]=ySet;
}
void MakeSet(int parent[],int n){
	int i;
	for(i=0;i<n;i++)
		parent[i]=-1;
}

void Print(int arr[],int parent[],int n){
	int i;
	for(i=0;i<n;i++)
		printf("parent of %d is %d\n",i,parent[i]);
	printf("\n\n");
}

int cmpFunction(const void* a,const void* b){
	
	struct Edge* p=(struct Edge*)a;
	struct Edge* q=(struct Edge*)b;
	
	return p->weight-q->weight;
}
int main(){
	int n,e,src,dest,weight,i,root,sum=0;
	scanf("%d%d",&n,&e);
	struct Edge edges[e];
	int parent[n+1];
	
	for(i=0;i<e;i++){
		scanf("%d%d%d",&edges[i].source,&edges[i].destination,&edges[i].weight);
	}
	scanf("%d",&root);
	MakeSet(parent,n+1);
	qsort(edges,e,sizeof(struct Edge),cmpFunction);
	
	for(i=0;i<e;i++){
		if(Find(parent,edges[i].source) == Find(parent,edges[i].destination)){
			continue;
		}
		else{
			sum+=edges[i].weight;
			Union(parent,edges[i].source,edges[i].destination);
		}	
	}
	printf("%d\n",sum);
	return 0;
}