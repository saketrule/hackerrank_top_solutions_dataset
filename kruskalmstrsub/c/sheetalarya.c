#include <stdio.h>
#include <string.h>
#include <math.h>
#include <stdlib.h>
#include <malloc.h>

int N, E, start=0,ans=0;

struct Edge
{
	int src;
	int dest;
	int wt;
};

struct Graph
{
	int V;
	int E;
	struct Edge *edges;
};

struct Edge *Res;

struct Graph* CreateGraph(int V, int E)
{
	struct Graph *G = (struct Graph*)malloc (sizeof(struct Graph));
	G->E = E;
	G->V = V;

	G->edges = (struct Edge*)malloc (sizeof(struct Edge)*E);

	return G;
}

void swap (struct Edge *E1, struct Edge *E2)
{
	struct Edge temp = *E1;
	*E1 = *E2;
	*E2 = temp;
}

int partition(struct Edge *edges, int start, int end)
{
	int pivot = edges[end].wt;
	int pIdx = start;
	int i;

	for(i=0;i<end;i++)
	{
		if (edges[i].wt < pivot)
		{
			swap (&edges[i], &edges[pIdx]);
			pIdx++;
		}
		else if (edges[i].wt == pivot)
		{
			if ((edges[i].src+edges[i].dest+edges[i].wt) < (edges[end].src+edges[end].dest+edges[end].wt))
			{
				swap (&edges[i], &edges[pIdx]);
				pIdx++;
			}
		}
	}

	swap(&edges[end], &edges[pIdx]);

	return pIdx;
}


void QSort(struct Edge *edges, int start, int end)
{
	if (start<end)
	{
		int idx = partition (edges, start,end);
		QSort (edges, start, idx);
		QSort (edges, idx+1, end);
	}
}

void quickSort (struct Graph *G)
{
	QSort (G->edges, 0, (G->E)-1);
}

void BubbleSort(struct Edge edges[],int E)
{
	int i,j;


	for (i=0;i<E-1;i++)
	{
		for (j=0;j<E-i-1;j++)
		{
			if (edges[j].wt > edges[j+1].wt)
			{
				struct Edge temp;
				temp = edges[j];
				edges[j] = edges[j+1];
				edges[j+1] = temp;
			}
			else if (edges[j].wt == edges[j+1].wt)
			{
				if ((edges[j].src + edges[j].wt + edges[j].dest) > (edges[j+1].src + edges[j+1].wt + edges[j+1].dest) ) 
				{
					struct Edge temp;
					temp = edges[j];
					edges[j] = edges[j+1];
					edges[j+1] = temp;
				}
			
			}
		}
	}
}

int find (int parent[], int item)
{
	if (parent[item] == -1)
		return item;

	return find(parent, parent[item]);

}

void Union (int parent[], int s, int d)
{
	int x_set = find (parent, s);
	int y_set = find (parent,d);

	parent[x_set] = y_set;
}

void KrsuskalMST(struct Graph* G)
{
	int i,e=0;
	int parent[3001];
	
	BubbleSort(G->edges, G->E);

	for(i=1;i<=N;i++)
	{
		parent[i] = -1;		
	}

	for (i=0;i<G->E;i++)
	{
		int x = find (parent, G->edges[i].src);
		int y = find (parent, G->edges[i].dest);

		if (  x != y)
		{
			Res[e++] = G->edges[i];
			ans+=G->edges[i].wt;
			Union (parent, x, y);
		}
	}

}

int main() {

    /* Enter your code here. Read input from STDIN. Print output to STDOUT */    

	int i;
	scanf ("%d %d", &N, &E);

	Res = (struct Edge*)malloc (sizeof(struct Edge)*E);
	struct Graph *KG = CreateGraph(N, E);
	
	for (i=0;i<E;i++)
	{
		scanf ("%d %d %d", &(KG->edges[i].src), &(KG->edges[i].dest), &(KG->edges[i].wt));
		
	}

	scanf ("%d", &start);

	KrsuskalMST(KG);

	
	printf("%d\n",ans);

	free(Res);
    return 0;
}
