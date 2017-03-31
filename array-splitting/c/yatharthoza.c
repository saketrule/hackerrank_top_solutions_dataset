#include <stdio.h>
#include <string.h>
#include <math.h>
#include <stdlib.h>
long long max(long long a,long long b)
    {
        if(a>=b)
            return a;
        else
            return b;
    }
long long split(long long a[16384],long long i,long long j,long long n)
    {
    if(i<0||i>n-1||j<0||j>n-1)
        return 0;
    if(i==j)
        return 0;
    long long k,sum=0;
    for(k=i;k<=j;k++)
        sum=sum+a[k];
    if(sum%2==1)
        return 0;
    else if(sum==0)
        return j-i;
    
        k=i;
        long long temp=0;
        sum=sum/2;
        while(temp<sum)
            {
            temp=temp+a[k];
            k++;
        }
        if(temp>sum)
            return 0;
        //printf("%lld %lld\n",split(a,k,j,n)+1,split(a,i,k-1,n)+1);
        return max(split(a,k,j,n)+1,split(a,i,k-1,n)+1);
}
int main() {

    /* Enter your code here. Read input from STDIN. Print output to STDOUT */    
    long long t,n,a[16384],i;
    scanf("%lld",&t);
    while(--t>=0)
        {
        scanf("%lld",&n);
        for(i=0;i<n;i++)
            {
            scanf("%lld",&a[i]);
        }
        printf("%lld\n",split(a,0,n-1,n));
    }
    return 0;
}
