#include <stdio.h>
#include <string.h>
#include <math.h>
#include <stdlib.h>
#define INF 1000000


struct node{
	int data;
	struct node* next;
	int weight;
	};

struct graph{
	int v;
	struct node* adjarray;
	};

void initiategraph(struct graph* newgraph, int nodes){
	int counter;
	newgraph->v=nodes;
	newgraph->adjarray=(struct node*)malloc(newgraph->v*sizeof(struct node));
	for(counter=0;counter<newgraph->v;counter++){
		newgraph->adjarray[counter].next=NULL;
		}
	}

void insertedges(struct graph* newgraph, int node1, int node2, int cost){
	struct node* newnode=(struct node*)malloc(sizeof(struct node));
	newnode->data=node2;
	newnode->weight=cost;
	newnode->next=newgraph->adjarray[node1].next;
	newgraph->adjarray[node1].next=newnode;
	newnode=(struct node*)malloc(sizeof(struct node));
	newnode->data=node1;
	newnode->weight=cost;
	newnode->next=newgraph->adjarray[node2].next;
	newgraph->adjarray[node2].next=newnode;
	}

void printgraph(struct graph* newgraph){
	int counter;
	for(counter=0;counter<newgraph->v;counter++){
		struct node* temp=newgraph->adjarray[counter].next;
		while(temp!=NULL){
			printf("%d -> %d W: %d \n",counter,temp->data, temp->weight);
			temp=temp->next;
			}
		}
	}		
	
int mstweightsum(struct graph* newgraph, int startnode){
	int nodes=newgraph->v;
	int numusednodes;
	int currentnode;
	int counter;
	int result=0;
	int* nodeq=( int*)malloc(nodes*sizeof(int));
	int* isused=( int*)malloc(nodes*sizeof(int));
	for(counter=0; counter<nodes;counter++){
		nodeq[counter]=INF;
		}
	for(counter=0; counter<nodes;counter++){
		isused[counter]=0;
		}
	isused[startnode]=1;
	numusednodes=1;
	currentnode=startnode;
	while(numusednodes!=nodes){
		struct node* temp=newgraph->adjarray[currentnode].next;
		while(temp!=NULL){
			if(nodeq[temp->data]>temp->weight){
				nodeq[temp->data]=temp->weight;
				}
			temp=temp->next;
			}
		int minweight=INF;
		int minnode=-1;
		for(counter=0;counter<nodes;counter++){
			if(nodeq[counter]<minweight && isused[counter]==0){
				minweight=nodeq[counter];
				minnode=counter;
				}
			}
		isused[minnode]=1;
		numusednodes++;
		result+=minweight;
		currentnode=minnode;
		}
	return result;
	}
		
		

int main() {
	int startnode, nodes, edges;
	scanf("%d",&nodes);
	scanf("%d",&edges);
	struct graph* newgraph=(struct graph*)malloc(sizeof(struct graph));
	initiategraph(newgraph, nodes);
	while(edges!=0){
		edges--;
		int x, y, r;
		scanf("%d",&x);
		scanf("%d",&y);
		scanf("%d",&r);
		insertedges(newgraph, x-1,y-1,r);
		}	
	scanf("%d",&startnode);
	printf("%d",mstweightsum(newgraph, startnode-1));
	return 0;  
}
