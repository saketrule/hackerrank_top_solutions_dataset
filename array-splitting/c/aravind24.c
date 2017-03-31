#include <stdio.h>
#include <string.h>
#include <math.h>
#include <stdlib.h>
long long int DP(long long int n,long long int ar[n],long long int start,long long int end,long long int sum)
    {
    if(start>=end)
        return 0;
    long long int temp=0,midd=-1,tsum;
    if(sum==0)
       {
       return (end-start);
    }
    else
        {
    for(long long int i=start;i<=end;i++)
        {
        temp=temp+ar[i];
        if((sum-temp)==temp)
            {
            midd=i;
            break;
        }
    }
    }
    if(midd==-1)
    return 0;
        else
        {
        long long int a=DP(n,ar,start,midd,temp);
        long long int b=DP(n,ar,midd+1,end,sum-temp);
            if(a>b)
                return a+1;
            else
                return b+1;
    }
}
int main() {
    int t;
    scanf("%d",&t);
    while(t--)
        {
        long long int n;
        scanf("%lld",&n);
        long long int ar[n],s=0;
        for(long long int i=0;i<n;i++)
            {
            scanf("%lld",&ar[i]);
            s=s+ar[i];
        }
            long long int m=DP(n,ar,0,n-1,s);
        printf("%lld\n",m);
        
    }
    /* Enter your code here. Read input from STDIN. Print output to STDOUT */    
    return 0;
}
