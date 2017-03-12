#include <stdio.h>
#include <string.h>
#include <math.h>
#include <stdlib.h>

int isprime(int n)
{
    int i;
    for(i=2;i<=sqrt(n);i++)
    if(n%i==0)
    return 0;
    return 1;
}

int main() {
    int t,n,count,i,u,k;
    scanf("%d",&t);
    while(t--)
    {
        count=0;
        scanf("%d",&n);
        int arr[n+1];
        arr[1]=1;
        arr[2]=1;
        arr[3]=1;
        arr[4]=2;
        for(i=5;i<=n;i++)
        arr[i]=arr[i-1]+arr[i-4];
        k=arr[n];
        for(u=2;u<=k;u++)
        if(isprime(u))
        count++;
        printf("%d\n",count);

    }
    /* Enter your code here. Read input from STDIN. Print output to STDOUT */
    return 0;
}
