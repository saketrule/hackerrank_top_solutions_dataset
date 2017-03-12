/* Enter your code here. Read input from STDIN. Print output to STDOUT */

#include<stdio.h>

#define MAX_EDGES 99

typedef struct {
    int one;
    int two;
} Edge;

Edge edges[MAX_EDGES];
int no_vertice;
int no_edges;
    

int no_children(int parent, int grand_parent)
{
    int r = 1;
    
    for(int i=0; i<no_edges; i++) {
        if(edges[i].one == parent) {
            if(edges[i].two != grand_parent) {
                r += no_children(edges[i].two, parent);
            }
        }
        else if(edges[i].two == parent) {
            if(edges[i].one != grand_parent) {
                r += no_children(edges[i].one, parent);
            }
        }
    }
    
    return r;
}

int main()
{
    int a = 0;
    
    // read values
    fscanf(stdin, "%d %d", &no_vertice, &no_edges);
    
    for(int i=0; i < no_edges; i++) {
        fscanf(stdin, "%d %d", &(edges[i].one), &(edges[i].two));
    }
    
    for(int i=0; i < no_edges; i++) {
        if((no_children(edges[i].one, edges[i].two) % 2) == 0) {
            a++;
        }
    }
    
    printf("%d", a);
}