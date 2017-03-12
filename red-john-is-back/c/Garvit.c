#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<stdbool.h>
int inline scan()
{
    int N = 0;
    char C;
    C=getchar();
    while (C < '0' || C>'9') C=getchar();
    while (C >= '0' && C <= '9')
    {
        N = (N<<3) + (N<<1) + C - '0';
        C=getchar();
    }

    return N;
}
int arr[41][41];
void pascals()
{
    int i,j;
    arr[0][0]=1;
    for(i=1;i<=40;i++)
    {
        arr[i][0]=1;
        for(j=1;j<=i;j++)
        {
            arr[i][j]=(arr[i-1][j-1]+arr[i-1][j]);
        }
    }
}
int count[1000001];
bool pr[1000001];
int total;
void sieve()
{
    int j,i;
    count[1]=0;
    total=0;
    for(i=2;i<=1000000;i++)
    {
        if(pr[i]==0)
        {
            for(j=2*i;j<=1000000;j+=i)
            {
                pr[j]=1;
            }
            count[i]=++total;
        }
        else
            count[i]=total;

    }

}
int main()
{
    sieve();
    pascals();
    int ans=0,i,t,n;
    t=scan();
    while(t--)
    {
        n=scan();
        ans=0;
        for(i=0;i<=n/4;i++)
        {
            ans+=arr[n-3*i][i];
        }
        printf("%d\n",count[ans]);
    }
    
}
