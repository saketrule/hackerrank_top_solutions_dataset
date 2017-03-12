#include <stdio.h>
#include <stdlib.h>
#include <limits.h>
#define INFINITY INT_MAX
#define UNDEFINED -1
#define TRUE 1
#define FALSE 0

typedef int bool;

int prim(int V, int** graph, int S) {
    bool* marked = (bool*) malloc( sizeof(bool) * V );

    // loop counters
    int j, k, min_weight, min_vertex;

    // mark all vertices unmarked
    for (j = 0; j < V; j++)
        marked[j] = FALSE;

    // initially mark source vertex marked
    marked[S] = TRUE;

    // number of unmarked vertices
    int count = V - 1;

    // weight of minimum spanning tree
    int sum = 0;

    // reduce count since we're marking a node at the end of the loop
    while (count--) {
        min_weight = INFINITY;
        min_vertex = UNDEFINED;
        // loop through all vertices
        for (j = 0; j < V; j++) {
            // if marked 
            if (marked[j] == TRUE) {
                // for each adjacent vertex of the marked vertex
                for (k = 0; k < V; k++) {
                    // if edge exist   if unmarked destination if weight less than min
                    if ((graph[j][k] != UNDEFINED) && (marked[k] == FALSE) && (graph[j][k] < min_weight)) {
                        min_weight = graph[j][k];
                        min_vertex = k;
                    }
                }
            }
        }

        marked[min_vertex] = TRUE;
        sum += min_weight;
    }

    return sum;
}

int main() {
    // loop counters
    int j, k;
    
    // N - vertices, M - edges, S - source vertex
    int N, M, S;
    scanf("%d %d", &N, &M);
    
    // X and Y are adjacent vertices
    int X, Y, R;

    // adjacency matrix
    int** graph;

    // allocate memory for matrix
    graph = (int**) malloc( sizeof(int*) * N);
    for (j = 0; j < N; j++) {
        graph[j] = (int*) malloc( sizeof(int) * N );
    }
    
    for (j = 0; j < N; j++) {
        for (k = 0; k < N; k++) {
            graph[j][k] = UNDEFINED;
        }
    }
    
    for (j = 0; j < M; j++) {
        scanf("%d %d %d", &X, &Y, &R);
        if (graph[X - 1][Y - 1] == UNDEFINED || graph[X - 1][Y - 1] > R) {
            graph[X - 1][Y - 1] = R;
            graph[Y - 1][X - 1] = R;
        }
    }
        
    scanf("%d", &S);
        
    printf("%d\n", prim(N, graph, S - 1));

    // Anton's Law of Deallocation
    for (j = 0; j < N; j++) {
        free(graph[j]);
    }

    free(graph);

    return 0;
}