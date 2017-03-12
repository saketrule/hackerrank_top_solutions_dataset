#include <stdio.h>

#define NMAX 101 // Numbering is from 1

typedef struct edge {
    int end;     // The node number of the far end of the edge
    struct edge *next;
} Edge;

typedef struct node {
    int ntree;   // Number of nodes in subtree rooted here
    int nedge;   // Number of edges from here to neighbours
    Edge *edges; // Linked list of edges
} Node;

Edge edges[2*NMAX];
Node nodes[NMAX];
int edgeidx;

// Insert edge to "e" from neighbour at "s"
void edgeinsert(int s, int e)
{
    Edge *tmp;

    edges[edgeidx].end = e;
    tmp = nodes[s].edges;
    nodes[s].edges = &edges[edgeidx];
    nodes[s].edges->next = tmp;
    ++edgeidx;
    ++nodes[s].nedge;
}

// Remove the edge to "e" from node "s"
// We assume that the edge exists...
void edgeremove(int s, int e)
{
    Edge *p, *n;

    p = NULL;
    n = nodes[s].edges;
    while (n->end != e) {
        p = n;
        n = n->next;
    }

    if (p) {
        p->next = n->next;
    } else {
        nodes[s].edges = n->next;
    }

    --nodes[s].nedge;
}

int main(void)
{
    int N, M;
    int i;
    int s, e;
    int edgesremoved;
    int nodesremoved;

    scanf("%d %d", &N, &M);

    // Initialise nodes
    for (i=1; i<=N; i++) {
        nodes[i].ntree = 1;
        nodes[i].nedge = 0;
        nodes[i].edges = NULL;
    }

    // Read in data and build tree
    edgeidx = 1;
    while (M--) {
        scanf("%d %d", &s, &e);
        edgeinsert(s, e);
        edgeinsert(e, s);
    }

    edgesremoved = 0;
    nodesremoved = 0;
    while (nodesremoved < N-1) {

        // Repeatedly cycle over remaining nodes removing a layer of leaves each time
        for(i=1; i<=N; i++) {

            // Leaves represent subtrees containing .ntree nodes
            if (nodes[i].nedge == 1) {

                // This is a leaf (representing a subtree)

                if (nodes[i].ntree%2 == 0) {

                    // Even subtree - we can remove the edge to it and count it
                    ++edgesremoved;
                    //printf("%d %d\n", nodes[i].edges->end, i);

                } else {

                    // Odd subtree - we can merge it with its parent
                    nodes[nodes[i].edges->end].ntree += nodes[i].ntree;

                }

                // Remove the edge from the parent
                edgeremove(nodes[i].edges->end, i);

                // Remove the leaf
                nodes[i].nedge = 0;
                ++nodesremoved;

            }
        }
    }

    printf("%d\n", edgesremoved);

    return 0;
}
