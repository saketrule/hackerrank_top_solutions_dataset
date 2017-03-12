#include <stdio.h>
#include <stdlib.h>
#include <limits.h>
#define INFINITY INT_MAX
#define UNDEFINED -1
#define TRUE 1
#define FALSE 0

typedef int bool;
typedef struct Edge {
    int source, destination, weight;
} Edge;

typedef struct Graph {
    int V; // number of vertices
    int E; // number of edges
    Edge* edges;
} Graph;

int findRep(int* parent, int x) {
    return parent[x] == x ? x : findRep(parent, parent[x]);
}

void unite(int* parent, int x, int y) {
    int xRep = findRep(parent, x),
        yRep = findRep(parent, y);

    parent[xRep] = yRep;
}

int compareEdge(const void *p1, const void *p2) {
        int weight1 = ((Edge*) p1)->weight;
        int weight2 = ((Edge*) p2)->weight;

        if (weight1 < weight2)
            return -1;
        else if (weight1 > weight2)
            return 1;
        else
            return 0;
}

int kruskal(Graph* graph, int S) {
    int V = graph->V,
        E = graph->E,
        X, Y, W,
        parent[V],
        i, sum = 0;

    for (i = 0; i < V; i++) {
        parent[i] = i;
    }

    qsort(graph->edges, graph->E, sizeof(Edge), compareEdge);

    for (i = 0; i < E; i++) {
        X = graph->edges[i].source;
        Y = graph->edges[i].destination;
        W = graph->edges[i].weight;

        if (findRep(parent, X) != findRep(parent, Y)) {
            unite(parent, X, Y);
            sum += W;
        }
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

    Graph* graph = (Graph*) malloc( sizeof(Graph) );
    graph->V = N; graph->E = M;
    graph->edges = (Edge*) malloc( sizeof(Edge) * M );

    for (j = 0; j < M; j++) {
        scanf("%d %d %d", &X, &Y, &R);
        Edge edge = {.source = X - 1, .destination = Y - 1, .weight = R};
        graph->edges[j] = edge;
    }
        
    scanf("%d", &S);
        
    printf("%d\n", kruskal(graph, S - 1));

    return 0;
}