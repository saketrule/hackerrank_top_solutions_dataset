#include<stdio.h>



int main() {
    int N,Array[1001]={0},temp=0;
    scanf("%d",&N);
    for(int i=0; i<N;i++)
        {
        scanf("%d",&temp);
        Array[temp]++;
    }
    
    for(int i=0; i<= 1000 && N!=0 ; i++)
        if(Array[i]!=0)
        {
        printf("%d\n",N);
        N =N- Array[i];
        
    }
    return 0;
}
