#include <stdio.h>
#include <string.h>
#include <math.h>
#include <stdlib.h>
int point(int *A, int n)
{
   int i;
    long long sum=0,sum1=0;
    int s1,s2;
    for(i=0;i<n;i++)
        sum+=A[i];
    for(i=1;i<n;i++)
        {
        sum1+=A[i-1];
            if((2*sum1)==sum)
            {
                s1=point(A,i);
                s2=point(A+i,n-i);
                //printf("%d %d %d\n",n,s1,s2);
                if(s1>s2)
                    return s1+1;
                else
                    return s2+1;
            }
            else if((2*sum1)>sum)
            return 0;
    }
    return 0;
    
}
int main() {
    int t;
    int i,n,j;
    scanf("%d",&t);
    for(i=0;i<t;i++)
        {
        scanf("%d",&n);
        int B[n];
        int *A=B;
        for(j=0;j<n;j++)
            scanf("%d",&A[j]);
        
        printf("%d\n",point(A,n));
    }
    /* Enter your code here. Read input from STDIN. Print output to STDOUT */    
    return 0;
}
