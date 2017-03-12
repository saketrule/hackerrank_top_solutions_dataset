#include <stdio.h>
#include <string.h>
#include <math.h>
#include <stdlib.h>

int main() {
    int a[1001]={0},i,n,min,val;
    scanf("%d",&n);
        for(i=0;i<n;i++)
        {
         scanf("%d",&val);
         a[val]++;
        }
       for(i=1;i<=1000;i++)
       {
           if(a[i]!=0)
           {
               printf("%d\n",n);
               n=n-a[i];
               
           }
           
       }
       
        

    /* Enter your code here. Read input from STDIN. Print output to STDOUT */    
    return 0;
}
