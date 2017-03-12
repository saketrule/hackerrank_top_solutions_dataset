#include<stdio.h>
#include<stdlib.h>

struct edge
{
    int data;
    struct edge *next;
};

struct node
{
    struct node *next;
    int data;
    struct edge *enext;
};

void insert_vertex(struct node **,int);
void insert_edge(struct node *,int,int);
struct node * find_vertex(struct node *,int);
int count(struct node *,int);

void main()
{
    struct node *start=NULL,*temp;
    struct edge *ed;
    int v,e,i,l,r;
    int x=0,c;
    scanf("%d %d",&v,&e);
    for(i=0;i<e;i++)
    {
        scanf("%d %d",&l,&r);
        if(find_vertex(start,r)==NULL && find_vertex(start,l)==NULL)
        {
            insert_vertex(&start,r);
            insert_vertex(&start,l);
        }
        else if(find_vertex(start,r)!=NULL && find_vertex(start,l)==NULL)
            insert_vertex(&start,l);
        else if(find_vertex(start,r)==NULL && find_vertex(start,l)!=NULL)
            insert_vertex(&start,r);
        insert_edge(start,r,l);
    }
    temp=start;
    while(temp!=NULL)
    {
        ed=temp->enext;
        while(ed!=NULL)
        {
            c=count(start,ed->data);
            if(c%2!=0)
            x++;
            ed=ed->next;
        }
        temp=temp->next;
    }
    printf("\n%d",x);
}

int count(struct node *p,int d)
{
    struct node *temp;
    struct edge *e;
    int x=0;
    temp=p;
    while(temp->data!=d)
        temp=temp->next;
    if(temp->enext!=NULL)
    {
        x++;
        x+=count(p,(temp->enext)->data);
        e=temp->enext;
        while(e->next!=NULL)
        {
            e=e->next;
            x++;
            x+=count(p,e->data);
        }
    }
    return x;
}

void insert_vertex(struct node **ps,int x)
{
    struct node *p,*temp;
    p=(struct node *)malloc(sizeof(struct node));
    p->data=x;
    p->next=NULL;
    p->enext=NULL;
    if(*ps==NULL)
    {
        *ps=p;
        return;
    }
    temp=*ps;
    while(temp->next!=NULL)
    temp=temp->next;
    temp->next=p;
}

struct node * find_vertex(struct node *p,int x)
{
    struct node *temp;
    if(p==NULL)
        return NULL;
    
    temp=p;
    while(temp)
    {
        if(temp->data==x)
        return temp;
        temp=temp->next;
    }
    return NULL;
}

void insert_edge(struct node *p,int s,int d)
{
    struct node *temp;
    struct edge *newedge,*q;
    if(p==NULL)
    {
        printf("Vertex list empty");
        return;
    }
    temp=find_vertex(p,s);
    newedge=(struct edge *)malloc(sizeof(struct edge));
    newedge->data=d;
    newedge->next=NULL;
    if(temp->enext==NULL)
    {
        temp->enext=newedge;
        return;
    }
    q=temp->enext;
    while(q->next!=NULL)
        q=q->next;
    q->next=newedge;
}
