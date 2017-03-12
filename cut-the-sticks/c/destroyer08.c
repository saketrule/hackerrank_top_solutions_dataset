#include <stdio.h>
#include <string.h>
#include <math.h>
#include <stdlib.h>

int main() {

    /* Enter your code here. Read input from STDIN. Print output to STDOUT */  
    int n, ar[1000],i,j,cn[1001]={0},min=1001;
    scanf("%d",&n);
    for(i=0;i<n;i++)
        scanf("%d",&ar[i]);
    
    for(i=0;i<n;i++){
        cn[ar[i]]++;
        if(ar[i]<min)
            min =ar[i];
    }
    j =min;
    printf("%d\n",n);
    while(n>0)
        {
        for(i=1;i<=j;i++){
            n-=cn[i];
            cn[i] = 0;
        }
        if(n==0)
            break;
        printf("%d\n",n);
        for(i=j;i<1001;i++)
            {
            if(cn[i]!=0){
                min = i-min;
                j += min;
                min = j;
                break;
            }
        }
        
    }
    
    
    
    
    return 0;
}
