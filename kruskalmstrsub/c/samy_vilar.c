#include <stdio.h>
#include <stdlib.h>

/*
 * Find the weight of Minimal Spanning Tree using Kruskals algorithm
 *  on conflict select the vertex with the lower id.
 */

typedef struct {
    unsigned short source, target;
    unsigned weight;
} edge_t;


int cmp_edges(const void *self, const void *other) {
    int delta = (((edge_t *)self)->weight - ((edge_t *)other)->weight);
    return delta ? delta
        : ((int)(((edge_t *)self)->source + ((edge_t *)self)->target)
            - (int)(((edge_t *)other)->source + ((edge_t *)other)->target));
}

int main() {
    unsigned short max_vertex;
    unsigned at, edge_cnt;

    scanf("%hu %u", &max_vertex, &edge_cnt);
    max_vertex++;

    edge_t edges[(edge_cnt << 1) | 1];
    for (at = 0; at < edge_cnt; at++) {
        scanf("%hu %hu %u", &edges[at].source, &edges[at].target, &edges[at].weight);
        edges[at + edge_cnt].target = edges[at].source;
        edges[at + edge_cnt].source = edges[at].target;
        edges[at + edge_cnt].weight = edges[at].weight;
    }

    qsort(edges, (edge_cnt <<= 1), sizeof(edge_t), cmp_edges);

    unsigned short sets[max_vertex];
    for (at = 0; at < max_vertex; at++)
        sets[at] = (unsigned short)at;

    unsigned short history[6002], pending;
    unsigned long total = 0;
    for (at = 0; at < edge_cnt; at++) {
        unsigned short
            self = edges[at].source,
            other = edges[at].target;

        for (pending = 0; sets[self] != self; self = sets[self])
            history[pending++] = self;

        for (; sets[other] != other; other = sets[other])
            history[pending++] = other;

        if (self != other) {
            total += edges[at].weight;
            if (other < self) {
                unsigned short temp = self;
                self = other;
                other = temp;
            }

            for (history[pending++] = other; pending--; sets[history[pending]] = self);
        }
    }

    printf("%lu\n", total);

    return 0;
}
