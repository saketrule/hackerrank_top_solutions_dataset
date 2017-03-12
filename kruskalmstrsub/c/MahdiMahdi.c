#include <stdio.h>
#include <string.h>
#include <math.h>
#include <stdlib.h>

struct nodes {
    int mark;
    int pre;
    int val;
};

int n;
int m;
int s;
int e[3001][3001];
struct nodes node[3001];


int min_mark() {
    int j,m=0,max=100001;
    for(j=1;j<=n;j++) if(node[j].mark!=1 && node[j].val < max) {m=j; max=node[j].val;} 
    return m;
}

void SP(int s) {
    int j;
    for(j=1;j<=n;j++) 
    if(e[s][j]!=-1 && node[j].mark!=1) { 
       if(node[j].pre==0)  {node[j].pre=s; node[j].val= e[s][j];}
       else if(node[j].pre!=0 && node[j].val > e[s][j]) {node[j].pre=s; node[j].val= e[s][j];}   
    }
}

int main() {
    int i,j,k,l,d;
        
    scanf("%d",&n); scanf("%d",&m);
        
    for(k=1;k<=n;k++)
            for(l=1;l<=n;l++) e[k][l]=-1;
            
        for(j=0;j<m;j++) {
            scanf("%d",&k); scanf("%d",&l); scanf("%d",&d);
            if(e[k][l]==-1) e[k][l]=d;
            else if(e[k][l] > d) e[k][l]=d; 
            e[l][k]=e[k][l];
        }
        
    scanf("%d",&s);
    
    //s=1;
        for(k=1;k<=n;k++) {
            if(k==s) {node[k].mark=1; node[k].pre=0; node[k].val=0;}
            else {node[k].mark=0; node[k].pre=0; node[k].val= 100000;}
        }
    SP(s);
        d=min_mark();
        while(d!=0) {node[d].mark=1; SP(d); d=min_mark();}
    d=0;
   for(k=1;k<=n;k++) if(node[k].pre!=0) d+=e[k][node[k].pre];
       printf("%d",d);
       //printf("%d:\tmark= %d\tpre= %d\tval= %d\n",k,node[k].mark,node[k].pre,node[k].val);
    return 0;
}
