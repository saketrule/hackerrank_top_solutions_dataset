#include<stdio.h>
#include<iostream>
#include<algorithm>
#define max 10000001
#define mod 1000000007
using namespace std;
long long int a[max],b[max];
int main()
{
    int t,n,m;
    scanf("%d",&t);

    while(t-->0)
    {
              scanf("%d%d",&m,&n);
              long long int sum=0;
              int x=1, y=1, q,p;
              int i=0;
              while(m-1>0)
              {
                        scanf("%lld",&a[i++]);
                        --m;
              }
              int j=0;
              while(n-1>0)
              {
                        scanf("%lld",&b[j++]);
                        --n;
              }
              sort(a,&a[i]);
              sort(b,&b[j]);
              for( p=i-1, q=j-1;p>=0&&q>=0;)
              {
                                           if(a[p]>=b[q])
                                           {
                                                        sum=sum+ ( a[p]*y ) % mod;
                                                        sum = sum % mod;
                                                        --p;
                                                        x++;
                                           }
                                           else
                                           {
                                                             sum=sum+ ( b[q]*x ) % mod;
                                                             sum = sum % mod;
                                                             --q;
                                                             ++y;
                                           }

              }
              while(p>=0)
              {
                sum=sum+ ( a[p]*y) % mod;
                --p;
                sum = sum % mod;
                x++;
              }
              while(q>=0)
              {
                sum=sum+( b[q]*x ) % mod;
                --q;
                sum = sum % mod;
                y++;
              }
              printf("%lld\n",sum%mod);
    }
  //  system("pause");
    return 0;
}



