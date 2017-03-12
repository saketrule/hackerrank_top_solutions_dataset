#include <stdio.h>
#include <stdlib.h>

struct tnode;

typedef struct tlist {
    struct tnode *node;
    struct tlist *next;
} TreeList;

typedef struct tnode {
    int id;
    struct tlist *children;
} TreeNode;

int walk_tree(TreeNode *root, int *rmcount) {
    if (root == 0)
        return 0;

    int count = 1;
    TreeList *p;
    for (p=root->children; p != NULL; p=p->next) {
        count += walk_tree(p->node, rmcount);
    }
 
    if (count % 2 == 0 && root->id != 1) {
        ++*rmcount;
    }
    
    return count;
}

void free_tree(TreeNode *root) {
    if (root == 0)
        return;

    TreeList *p, *q;
    for (p=root->children; p != NULL; p=q) {
        free_tree(p->node);
        q = p->next;
        free(p);
    }
}

int main(int argv, char **argc) {
    int nodecount, edgecount;
    scanf("%d %d", &nodecount, &edgecount);

    TreeNode *tnodes = (TreeNode*)calloc(nodecount, sizeof(TreeNode));    

    int v, p;
    while(scanf("%d %d", &p, &v) != EOF) {
        tnodes[v-1].id = v;
        tnodes[p-1].id = p;

        TreeList *tl = (TreeList*)malloc(sizeof(TreeList));
        tl->next = NULL;
        tl->node = &tnodes[p-1];

        if (tnodes[v-1].children == NULL) {
            tnodes[v-1].children = tl;
        }
        else {
            struct tlist *ptr;
            for (ptr=tnodes[v-1].children; ptr->next != NULL; ptr = ptr->next);
            ptr->next = tl;
        }
    }

    int rmcount = 0;
    walk_tree(&tnodes[0], &rmcount);
    printf("%d\n", rmcount);

    free_tree(&tnodes[0]);
    free(tnodes);
    return 0;
}
