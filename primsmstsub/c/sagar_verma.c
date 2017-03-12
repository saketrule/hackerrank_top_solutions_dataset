#include <stdio.h>
#include <string.h>
#include <math.h>
#include <stdlib.h>
#include <limits.h>

#define min(a,b) a<b?a:b
#define max(a,b) a>b?a:b

struct adjListNode{
    int dest;
    int cost;
    struct adjListNode *next;
};

struct adjList{
    int src;
    struct adjListNode *head;
};

struct graph{
    int v;
    int e;
    struct adjList *arr;
};

typedef struct adjListNode adjListNode;
typedef struct adjList adjList;
typedef struct graph graph;

struct queue{
    int v;
    struct queue *next;
};

typedef struct queue queue;
    
void enqueue(queue **f, queue **r, int v){
    queue *q = (queue *)malloc(sizeof(queue));
    q->v = v;
    q->next = NULL;
    if(*f == NULL){
        *f = *r = q;
        return;
    }
    (*r)->next = q;
    *r = q;
}

int dequeue(queue **f, queue **r){
    if(*f == *r){
        int v = (*f)->v;
        free(*f);
        *f = *r = NULL;
        return v;
    }
    queue *t = *f;
    *f = (*f)->next;
    int v = t->v;
    free(t);
    return v;
}

int isEmpty(queue **f, queue **r){
    if(*r == NULL && *f == NULL)
        return 1;
    else
        return 0;
}

adjListNode *newNode(int dest, int cost){
    adjListNode *n = (adjListNode *)malloc(sizeof(adjListNode));
    n->dest = dest;
    n->cost = cost;
    n->next = NULL;
    return n;
}

graph *createGraph(int v, int e){
    graph *g = (graph *)malloc(sizeof(graph));
    g->v = v;
    g->e = e;
    adjList *arr = (adjList *)malloc(v * sizeof(adjList));
    g->arr = arr;
    int i = 0;
    while(i < v){
        g->arr[i++].head = NULL;
    }
    return g;
}

void addEdge(graph *g, int src, int dest, int cost){
    adjListNode *n = newNode(dest, cost);
    n->next = g->arr[src].head;
    g->arr[src].head = n;
    
    n = newNode(src, cost);
    n->next = g->arr[dest].head;
    g->arr[dest].head = n;
}

int minDistance(int *dist, int *sptSet, int v){
    int min = INT_MAX, min_index, i = 0;
    while(i < v){
        if(!sptSet[i] && dist[i] <= min){
            min = dist[i];
            min_index = i;
        }
        i++;
    }
    return min_index;
}

void printSolution(int *dist, int v, int s){
    int i=0;
    while(i < v){
        if(i != s && dist[i] != INT_MAX)
            printf("%d ", dist[i]);
        else if(i != s)
            printf("-1 ");
        i++;
    }
}
void dijkstra(graph *g, int src, int d){

    int dist[g->v];
    int sptSet[g->v];
    int prev[g->v];
    int i=0;
    while(i<g->v){
        dist[i] = INT_MAX;
        sptSet[i] = 0;
        prev[i] = -1;
        i++;
    }

    //printf("a\n");
    dist[src] = 0;
    prev[src] = 0;
    i = 0;
    while(i < g->v-1){
        int u = minDistance(dist, sptSet, g->v);
        //printf("b\n");
        sptSet[u] = 1;

        adjListNode *aln = g->arr[u].head;
        while(aln){
            if(!sptSet[aln->dest] && dist[u] != INT_MAX && dist[u] + aln->cost < dist[aln->dest]){
                dist[aln->dest] = dist[u] + aln->cost;
                prev[aln->dest] = u;
            }
            aln = aln->next;
        }
        i++;
    }
    //printSolution(dist, g->v, src, d);
    if(dist[d] != INT_MAX)
        printf("%d", dist[d]);
    else
        printf("-1");
}

void merge(int arr[][3], int l, int m, int r){
    int i, j, k;
    int n1 = m - l + 1;
    int n2 = r - m;
    
    int L[n1][3], R[n2][3];
    for(i = 0; i < n1; i++){
        L[i][0] = arr[l + i][0];
        L[i][1] = arr[l + i][1];
        L[i][2] = arr[l + i][2];
    }

    for(j = 0; j < n2; j++){
        R[j][0] = arr[m + 1 + j][0];
        R[j][1] = arr[m + 1 + j][1];
        R[j][2] = arr[m + 1 + j][2];
    }
        
    i = 0;
    j = 0;
    k = l;
    while (i < n1 && j < n2){
        if (L[i][2] < R[j][2]){
            arr[k][0] = L[i][0];
            arr[k][1] = L[i][1];
            arr[k][2] = L[i][2];
            i++;
        }
        else{
            arr[k][0] = R[j][0];
            arr[k][1] = R[j][1];
            arr[k][2] = R[j][2];
            j++;
        }
        k++;
    }
    
    while (i < n1){
        arr[k][0] = L[i][0];
        arr[k][1] = L[i][1];
        arr[k][2] = L[i][2];
        i++;
        k++;
    }
    
    while (j < n2){
        arr[k][0] = R[j][0];
        arr[k][1] = R[j][1];
        arr[k][2] = R[j][2];
        j++;
        k++;
    }
}

void mergeSort(int arr[][3], int l, int r){
    if (l < r){
        int m = l + (r - l) / 2;
        mergeSort(arr, l, m);
        mergeSort(arr, m+1, r);
        merge(arr, l, m, r);
    }
}

void prims(graph *g,int src){

    int dist[g->v];
    int sptSet[g->v];
    int prev[g->v];
    int i=0;
    while(i<g->v){
        dist[i] = INT_MAX;
        sptSet[i] = 0;
        prev[i] = -1;
        i++;
    }

    //printf("a\n");
    dist[src] = 0;
    prev[src] = -1;
    i = 0;
    while(i < g->v-1){
        int u = minDistance(dist, sptSet, g->v);
        //printf("b\n");
        sptSet[u] = 1;

        adjListNode *aln = g->arr[u].head;
        while(aln){
            if(!sptSet[aln->dest] && dist[u] != INT_MAX &&  aln->cost < dist[aln->dest]){
                dist[aln->dest] = aln->cost;
                prev[aln->dest] = u;
            }
            aln = aln->next;
        }
        i++;
    }
    //printSolution(dist, g->v, src, d);
    /*if(dist[d] != INT_MAX)
        printf("%d", dist[d]);
    else
        printf("-1");*/

    i = 0;
    int arr[g->v][3];
    int res=0;
    while(i<g->v){
        adjListNode *aln = g->arr[i].head;
        int temp = 0;
        while(aln){
            if(aln->dest == prev[i]){
                temp = aln->cost;
                break;
            }
            aln = aln->next;
        }
        //printf("%d %d %d\n", prev[i]+1, i+1, temp);
        arr[i][0] = min(prev[i] + 1, i + 1);
        arr[i][1] = max(prev[i] + 1, i + 1);
        arr[i][2] = temp;
        res += temp;
        i++;
    }
    printf("%d\n", res);
    /*
    mergeSort(arr, 0, g->v-1);
    i = 1;
    while(i < g->v){
        printf("%d %d %d\n", arr[i][0], arr[i][1], arr[i][2]);
        i++;
    }*/
}

int main() {

    int t, v, e, s, d, src, cost, dest;
    //scanf("%d", &t);
   // while(t--){
        //printf("1\n");
        scanf("%d%d", &v, &e);
        graph *g = createGraph(v, e);
        //printf("2\n");
        while(e--){
            scanf("%d%d%d", &s, &d, &cost);
            addEdge(g, s-1, d-1, cost);
        }
        scanf("%d", &src);
        prims(g,src-1);
        //printf("3\n");
        //while(t--){
            //scanf("%d%d", &src, &dest);
            //dijkstra(g, src-1, dest-1);
            //printf("\n");
    //}
    return 0;
}
