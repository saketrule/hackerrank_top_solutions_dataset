#include <stdio.h>
#include <string.h>
#include <math.h>
#include <stdlib.h>

int main() {

    /* Enter your code here. Read input from STDIN. Print output to STDOUT */  
    int n;
    int i;
    int q;
    int max=-1;
    scanf("%d", &n);
    int stick[1000];
    stick[0]=0;    
    for(i=0;i<1000;i++)
        stick[i]=0;
    for(i=0;i<n;i++)
    {
        scanf("%d", &q);
        if(q>max)
           max = q;
        stick[q]++;     
    }
    int numcut=0;
    
       		printf("%d\n", n);
    for(i=1;i<max;i++)
    {
        
       if(stick[i])
       {
           numcut+=stick[i];
       		printf("%d\n", n - numcut);
    	}
    }
    return 0;
}
