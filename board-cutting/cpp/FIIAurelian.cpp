#include<cstdio>
#include<algorithm>
#define NMAX 1000010
using namespace std;
int A[NMAX],B[NMAX],n,m,i,T,vertical,horizontal,j;
long long SOL,MOD;
bool cmp(int x,int y)
{
    if(x>y)return 1;
    return 0;
}
int main()
{
    scanf("%d",&T);
    MOD=1;
    for(i=1;i<=9;i++)MOD*=10;
    MOD+=7;
    for(;T;--T)
    {
        scanf("%d%d",&n,&m);
        SOL=0;
        for(i=1;i<n;i++)scanf("%d",&A[i]);
        for(i=1;i<m;i++)scanf("%d",&B[i]);
        sort(A+1,A+n,cmp);
        sort(B+1,B+m,cmp);
        i=j=1;
        for(;i<n || j<m;)
        {
            if(i==n)
            {
                SOL=(1LL*SOL+(1LL*B[j]*i))%MOD;
                j++;
                continue;
            }
            if(j==m)
            {
                SOL=(1LL*SOL+(1LL*A[i]*j))%MOD;
                i++;
                continue;
            }
            if(A[i]>B[j])
            {
                SOL=(1LL*SOL+(1LL*A[i]*j))%MOD;
                i++;
                continue;
            }
            if(A[i]<B[j])
            {
                SOL=(1LL*SOL+(1LL*B[j]*i))%MOD;
                j++;
                continue;
            }
            if(A[i]==B[j])
            {
                if((n-i)<=(m-j))
                {
                    SOL=(1LL*SOL+(1LL*A[i]*j))%MOD;
                    i++;
                    continue;
                }
                SOL=(1LL*SOL+(1LL*B[j]*i))%MOD;
                j++;
            }
        }
        printf("%lld\n",SOL);
    }
    return 0;
}
