#include <stdio.h>
#include <string.h>
#include <math.h>
#include <stdlib.h>

int cmpfunc (const void * a, const void * b)
{
   return ( *(int*)a - *(int*)b );
}

int main() {

    int t,m,n,x[1000001],y[1000001],i,j,a,b;
   long long s=0;
    scanf("%d",&t);
    while(t--)
        {
        s=0;
        a=1;
        b=1;
        scanf("%d%d",&n,&m);
        n--;
        m--;
        for(j=0;j<n;j++)
            {
            scanf("%d",&y[j]);
        }
        y[n]=0;
        for(j=0;j<m;j++)
            {
            scanf("%d",&x[j]);
        }
        x[m]=0;
        qsort(y, n+1, sizeof(int), cmpfunc);
        qsort(x, m+1, sizeof(int), cmpfunc);
        for(i=n,j=m;i>0 || j>0;)
            {
            if(y[i]==0 &x[j]==0)
                {
                a++;
                b++;
                i--;
                j--;
            }
            else if(y[i]>=x[j])
                {
                a++;
                s+=(long long)y[i]*(long long)b;
               s=s-(long long)1000000007*(long long)(s/(long long)1000000007);
                i--;
            }
            else
                {
                b++;
                s+=(long long)x[j]*(long long)a;
                 s=s-(long long)1000000007*(long long)(s/(long long)1000000007);
                j--;
            }
        }
       h: printf("%lld\n",s);
    }
    return 0;
}
