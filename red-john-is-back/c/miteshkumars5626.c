#include<stdio.h>
int k,m,cnt,t,n,f[50],p[1000055],store[1000055];
void sieve()
{
    int i,j;
    p[1]=1;
    for(i=4;i<=1000005;i=i+2)
    p[i]=1;
    for(i=3;i*i<=1000005;i=i+2)
    {
        for(j=i*i;j<=1000005;j=j+2*i)
        {
            if(p[j]==0)
            p[j]=1;
        }

    }
    k=0;
    for(i=2;i<=1000005;i++)
    {
        if(p[i]==0)
        store[k++]=i;
    }
}
int main()
{
    int i,j;
    sieve();
    /*for(j=0;j<=55;j++)
    printf("%d ",store[j]);
    printf("\n");*/

    f[1]=1;f[2]=1;f[3]=1;f[4]=2;
    for(i=5;i<=40;i++)
    f[i]=f[i-1]+f[i-4];
    scanf("%d",&t);
    while(t--)
    {
        cnt=0;
        scanf("%d",&n);
        m=f[n];
        i=0;
        cnt=0;
        while(store[i]<=m)
        {
            cnt++;
            i++;
        }
        printf("%d\n",cnt);


    }
    return 0;
}
