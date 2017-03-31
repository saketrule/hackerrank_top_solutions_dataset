#include <stdio.h>
#include <string.h>
#include <math.h>
#include <stdlib.h>
long long int a[20000];
long long int min(long long int a,long long int b)
    {
    if(a>b)
        return a;
    else
        return b;
}
long long int dp(long long int i,long long int j)
    {
    long long int first,last,middle,search;
    if(i==j)
        return 0;
    else if(a[i-1]==a[j])
        return j-i;
    else
        {
        if((a[i-1]+a[j])%2==0)
            {
            first=i;
            last=j;
            middle=(i+j)/2;
            search=(a[i-1]+a[j])/2;
            while(first<=last)
                {
                if(search>a[middle])
                    first=middle+1;
                else if(search==a[middle])
                    break;
                else
                    last=middle-1;
                middle=(first+last)/2;
            }
            if(first<=last)
                return 1+min(dp(i,middle),dp(middle+1,j));
            else
                return 0;
        }
        else
            return 0;
    }
}
int main() {
    long long int t,i,val,temp,n;
    scanf("%lld",&t);
    while(t--)
        {
        scanf("%lld",&n);
        a[0]=0;
        for(i=1;i<=n;i++)
            {
            scanf("%lld",&temp);
            a[i]=temp+a[i-1];
        }
        val=dp(1,n);
        printf("%lld\n",val);
    }
    return 0;
}
