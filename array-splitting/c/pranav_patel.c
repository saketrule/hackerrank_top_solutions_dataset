#include <stdio.h>
#include <string.h>
#include <math.h>
#include <stdlib.h>

int max(int a,int b){
    return a>b?a:b;
}

int getMaxPoints(int *arr,int s,int e){
    
    int total=0,i,sum=0,pos=-1;
    if(s+1==e)
        return 0;
    for(i=s;i<e;i++){
        total+=arr[i];
    }
    for(i=s;i<e;i++){
       sum+=arr[i]; 
       if(total==sum*2)
       {
           pos=i;
           break;
       }
    }
    if(pos>=0)
        return max(getMaxPoints(arr,s,i+1),getMaxPoints(arr,i+1,e))+1;
    else
        return 0;
}


int main() {

    /* Enter your code here. Read input from STDIN. Print output to STDOUT */
    int T,N,i,k=0;
    scanf("%d",&T);
    int ans[T];
    while(T>0){
        scanf("%d",&N);
        int arr[N];
        for(i=0;i<N;i++){
            scanf("%d",&arr[i]);
        }
        ans[k++]=getMaxPoints(arr,0,N);
        T--;
    }
    for(i=0;i<k;i++){
        printf("%d\n",ans[i]);
    }
    return 0;
}
