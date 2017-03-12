#include <stdio.h>
#include <stdlib.h>

struct Edge
{
	int u;
	int v;
	int r;
};

int cmpfunc( const void* a, const void* b)
{
	int x = ((struct Edge*)a)->r;
	int y = ((struct Edge*)b)->r;

	return x - y;
}

int findSet(int parent[], int node)
{
	if ( parent[node] == -1 || parent[node] == 0)
		return node;

	return findSet(parent, parent[node]);
}

int isCycle(int parent[], int u, int v, int src)
{
	int a = findSet(parent, u);
	int b = findSet(parent, v);
	if ( a == b )
		return 1;
	else
	{
		if ( a == src )	
			parent[b] = a;
		else parent[a] = b;
		return 0;
	}
}

int kruskal(struct Edge edges[], int parent[], int n, int m, int src)
{
	int visited = 0, index = 1, sum = 0;
	parent[src] = 0;
	visited++;

	while( visited < n && index <= m)
	{
		int x = edges[index].u;
		int y = edges[index].v;

		if ( !isCycle(parent, x, y, src))
		{
			sum += edges[index].r;
			visited++;
		}
		index++;
	}
	return sum;
}
	
int main()
{
	int n, m, i, j, source;

	scanf("%d %d", &n, &m);

	int parent[n + 1];
	for ( i = 0; i <= n ; i++)
		parent[i] = -1;

	struct Edge edges[m + 1];
	edges[0].u = edges[0].v = edges[0].r = -1;

	for ( i = 1; i <= m; i++)
		scanf("%d %d %d", &edges[i].u, &edges[i].v, &edges[i].r);

	scanf("%d", &source);

	qsort(edges, m + 1, sizeof(struct Edge), cmpfunc);

	printf("%d",kruskal(edges, parent, n, m, source));

	return 0;
}
