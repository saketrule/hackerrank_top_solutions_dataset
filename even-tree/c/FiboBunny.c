/* Enter your code here. Read input from STDIN. Print output to STDOUT */#include<stdio.h>
#include<stdlib.h>
struct adj{
        int index;
        int score;
        struct adj *next;
};
typedef struct adj adj;
struct node{
        adj *list;
};
typedef struct node node;
int N;
node *arr;


void display(node a[],int n){
        adj *ptr=a[n].list;
        while(ptr!=NULL){
                printf("%d,%d  ",ptr->index,ptr->score);
                
                ptr=ptr->next;
        }
}

void add(node a[],int n1,int n2){
        adj *temp1=(adj*)malloc(sizeof(adj));
        adj *temp2=(adj*)malloc(sizeof(adj));
        temp1->index=n1;
        temp1->score=-1;
        temp1->next=NULL;
        temp2->index=n2;
        temp2->score=-1;
        temp2->next=NULL;
        if(a[n1].list==NULL){
                a[n1].list=temp2;
        }                                                                                                                              
        else{
                
                adj *ptr=a[n1].list;
                while(ptr->next!=NULL){
                        ptr=ptr->next;
                }
                ptr->next=temp2;
        }
        if(a[n2].list==NULL){
                a[n2].list=temp1;
        }
        else{
                
                adj *ptr=a[n2].list;
                while(ptr->next!=NULL){
                        ptr=ptr->next;
                }
                ptr->next=temp1;
        }
}

int dfs(int node1,int inNode){
        adj *ptr=arr[node1].list;
        int score=0;
        int temp;
        while(ptr!=NULL){
                if(ptr->index==inNode){
                        ptr=ptr->next;
                        continue;
                }
                else{
                        temp=dfs(ptr->index,node1);
                        ptr->score=temp;
                        score+=temp;
                        ptr=ptr->next;
                }
                
                
        }
        score++;
        return score;
        
}


void dfsStart(node arr[],int start){
        int i=0;
        adj *ptr=arr[start].list;
        int temp=0;
        while(ptr!=NULL){
                temp=dfs(ptr->index,start);
                ptr->score=temp;
                
                ptr=ptr->next;
        }
}
void msg(int node1,int inNode){
        adj *ptr=arr[inNode].list;
        int temp=1;
        while(ptr!=NULL){
                if(ptr->index!=node1){
                        temp+=ptr->score;
                }       
                ptr=ptr->next;  
        }
        adj *ptr2=arr[node1].list;
        while(ptr2!=NULL){
                if(ptr2->index==inNode){
                        ptr2->score=temp;
                }
                ptr2=ptr2->next;
        }
        ptr2=arr[node1].list;
        while(ptr2!=NULL){
                if(ptr2->index!=inNode)
                        msg(ptr2->index,node1);
                ptr2=ptr2->next;
        }
} 

void message(int start){
        adj *ptr=arr[start].list;
        while(ptr!=NULL){
                msg(ptr->index,start);
                ptr=ptr->next;
        }
        
}

int check(){
        int count=0;
        int i;
        for(i=1;i<=N;i++){
                adj *ptr=arr[i].list;
                
                while(ptr!=NULL){
                        if(ptr->score%2==0&&ptr->index>i){
                                adj *ptr2=arr[ptr->index].list;
                                while(ptr2!=NULL){
                                        if(ptr2->index==i&&ptr2->score%2==0){
                                                count++;                                
                                        }
                                        ptr2=ptr2->next;
                                }
                        }
                ptr=ptr->next;
                }
        }
        return count;
}

int main(){
        int i,n,e;
        int index1,index2;
        scanf("%d %d",&N,&e);
        arr=(node*)malloc(sizeof(node)*(N+1));
        for(i=1;i<=N;i++){
                arr[i].list=NULL;
        }

        for(i=0;i<e;i++){
                scanf("%d %d",&index1,&index2);
                add(arr,index1,index2);
        }
        
        dfsStart(arr,1);
/*      for(i=1;i<=N;i++){
                printf("%d :  ",i);
                display(arr,i);
                printf("\n");
        }
*/
        message(1);
/*      getchar();      
        for(i=1;i<=N;i++){
                printf("%d :  ",i);
                display(arr,i);
                printf("\n");
        }
*/
        printf("%d",check());
        return(1);
}
