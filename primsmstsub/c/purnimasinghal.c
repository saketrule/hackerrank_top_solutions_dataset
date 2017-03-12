#include <stdio.h>

int distance[3001], heap_size = 0, mat[3001][3001];

void swap( int* a, int* b)
{
	int temp = *a;
	*a = *b;
	*b = temp;
}

void Heapify_Down(int heap[], int index, int locations[])
{
	int l = 2 * index + 1;
	int r = 2 * index + 2;
	int min;
	if ( l >= heap_size )
		return;
	if ( r >= heap_size )
		min = l;
	else min = distance[heap[l]] < distance[heap[r]] ? l : r;

	if ( distance[heap[index]] > distance[heap[min]] )
	{
		swap(&heap[index], &heap[min]);
		locations[heap[index]] = index;
		locations[heap[min]] = min;
		Heapify_Down(heap,  min, locations);
	}
}
	
void Heapify_Up(int heap[], int index, int locations[])
{
	if ( index <= 0 )
		return;
	int parent = (index - 1)/2;
	if ( distance[heap[parent]] > distance[heap[index]])
	{
		swap(&heap[parent], &heap[index]);
		locations[heap[parent]] = parent;
		locations[heap[index]] = index;
		Heapify_Up(heap, parent, locations);
	}
}

void updateHeap(int heap[], int index, int locations[])
{
	int loc = locations[index];
	Heapify_Up(heap, loc, locations);
}

int extractMin(int heap[], int locations[])
{
	int temp = heap[0];
	locations[temp] = -1;
	heap[0] = heap[--heap_size];

	locations[heap[0]] = 0;

	Heapify_Down(heap, 0, locations);

	return temp;
}

void insert(int heap[], int value, int locations[])
{
	heap[heap_size++] = value;
	locations[value] = heap_size - 1;
	Heapify_Up(heap, heap_size - 1, locations);
}

void Prims(int src, int n)
{
	int heap[n + 1], locations[n + 1], visited[3001] = {0}, v, i;
	insert(heap, src, locations);
	distance[src] = 0;

	while(heap_size)
	{
		int u = extractMin(heap, locations);
		visited[u] = 1;
	
		for ( v = 1; v <= n; v++)
		{
			if (!visited[v] && mat[u][v] != 100001)
			{
				if ( distance[v] == -1 )
				{ 
					distance[v] = mat[u][v];
					insert(heap, v, locations);
				}
				else if ( distance[v] > mat[u][v])
				{
					distance[v] = mat[u][v];
					updateHeap(heap, v, locations);
				}
				else{}
			}
		}

	}
}

int main()
{
	int n, m, u, v, r, source, i, j;

	scanf("%d %d", &n, &m);

	
	for ( i = 1; i <= n; i++)
	{
		distance[i] = -1;
		for ( j = 1; j <= n; j++)
			mat[i][j] = 100001;
	}

	for ( i = 0; i < m; i++)
	{
		scanf("%d %d %d", &u, &v, &r);
		if ( mat[u][v] > r)
			mat[u][v] = mat[v][u] = r;
	}

	scanf("%d", &source);

	Prims(source, n);
	long int sum = 0;

	for ( i = 1; i <= n; i++)
		sum += distance[i];
	
	printf("%ld", sum);

	return 0;
}
