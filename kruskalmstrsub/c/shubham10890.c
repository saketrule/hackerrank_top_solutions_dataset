#include <stdio.h>
#include <string.h>
#include <math.h>
#include <stdlib.h>
#define INTMAX 1000000

int ADMAT[1000][1000];
int BEST[1000];
int VISIT[1000];

int end_condition(int N)
    {
    int i=0;
    int flag=0;
    for(i=0;i<N;i++)
        {
        if(VISIT[i]==0)
            flag=1;
    }
    return flag;
}
int main() {
    int N=-1,M=-1;
    int i=-1,j=-1,k=-1;
    int p=-1,q=-1,r=-1;
    int cur=-1;
    long int SUM=0,temp=INTMAX;
    int itemp=-1;
    scanf("%d%d",&N,&M);
    /*for(i=0;i<N;i++)
        for(j=0;j<N;j++)
        ADMAT[i][j]=-1;*/
    for(i=0;i<M;i++)
        {
        scanf("%d%d%d",&p,&q,&r);
        if(ADMAT[p-1][q-1]==0)
            {
        ADMAT[p-1][q-1]=r==0?-1:r;
        ADMAT[q-1][p-1]=r==0?-1:r;     
        }
        else if(ADMAT[p-1][q-1]>r)
            {
            ADMAT[p-1][q-1]=r==0?-1:r;
            ADMAT[q-1][p-1]=r==0?-1:r;   
        }
    }
    for(i=0;i<N;i++)
        BEST[i]=INTMAX;
    
    scanf("%d",&cur);
    VISIT[--cur]=1;
    while(end_condition(N))
        {
        /*printf("SUM: %d CUR: %d\n",SUM,cur+1);
        for(i=0;i<N;i++)
            printf("%d ",BEST[i]);
        printf("\n");
        for(i=0;i<N;i++)
            printf("%d ",VISIT[i]);
        printf("\n");*/
        temp=INTMAX;
        for(i=0;i<N;i++)
            {
            if(ADMAT[cur][i]!=0&&ADMAT[cur][i]<BEST[i])
                BEST[i]=ADMAT[cur][i];            
        }
        for(i=0;i<N;i++)
            {
            if(VISIT[i]==0&&BEST[i]<temp)
                {
                temp=BEST[i];
                itemp=i;
            }
        }
        if(temp!=-1)
            SUM+=temp;
        cur=itemp;
        VISIT[itemp]=1;
        
    }
    
    printf("%ld",SUM);
    return 0;
}
