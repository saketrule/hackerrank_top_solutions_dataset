#include<stdio.h>
#include<stdlib.h>
  int sieve[300000][2];
int main()
{
    int t,i,j,k,n;
    int a[41],x;
    a[1]=1;
    a[0]=0;
    a[2]=1;
    a[3]=1;
    a[4]=2;
    for(i=5;i<=40;i++)
    {
        a[i]=a[i-1]+a[i-4];
    }
   // printf("%d\n",a[40]);
    for(i=0;i<300000;i++)
    {
        for(j=0;j<2;j++)
        sieve[i][j]=0;
    }
    for(i=2;i<300000;i++)
    {
        if(sieve[i][0]==0)
         {
             sieve[i][1]=sieve[i-1][1]+1;
         }
         else
         {
             sieve[i][1]=sieve[i-1][1];
             continue;
         }
        for(j=2*i;j<300000;j=j+i)
        {
            sieve[j][0]=1;
        }
    }
    scanf("%d",&t);
    for(i=0;i<t;i++)
    {
        scanf("%d",&n);
        printf("%d\n",sieve[a[n]][1]);
    }

    return(0);
}
